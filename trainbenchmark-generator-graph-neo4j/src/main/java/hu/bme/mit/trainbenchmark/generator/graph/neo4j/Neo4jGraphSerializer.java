/*******************************************************************************
 * Copyright (c) 2010-2015, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.generator.graph.neo4j;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.rmi.RemoteException;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.stream.XMLStreamException;

import org.apache.commons.io.FileUtils;
import org.neo4j.cypher.export.DatabaseSubGraph;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.internal.GraphDatabaseAPI;
import org.neo4j.shell.InterruptSignalHandler;
import org.neo4j.shell.ShellException;
import org.neo4j.shell.SilentLocalOutput;
import org.neo4j.shell.impl.SameJvmClient;
import org.neo4j.shell.kernel.GraphDatabaseShellServer;
import org.neo4j.shell.tools.imp.format.graphml.XmlGraphMLWriter;
import org.neo4j.shell.tools.imp.util.Config;
import org.neo4j.shell.tools.imp.util.ProgressReporter;

import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.generator.ModelSerializer;
import hu.bme.mit.trainbenchmark.generator.graph.neo4j.config.Neo4jGraphGeneratorConfig;
import hu.bme.mit.trainbenchmark.neo4j.Neo4jConstants;

public class Neo4jGraphSerializer extends ModelSerializer<Neo4jGraphGeneratorConfig> {

	protected GraphDatabaseService graphDb;
	protected Transaction tx;
	protected final File databaseDirectory;

	public Neo4jGraphSerializer(final Neo4jGraphGeneratorConfig generatorConfig) {
		super(generatorConfig);
		databaseDirectory = new File(generatorConfig.getConfigBase().getModelDir() + "neo4j-gen/"
				+ generatorConfig.getConfigBase().getModelFileNameWithoutExtension() + ".neo4j");
	}

	@Override
	public String syntax() {
		return "graph";
	}

	@Override
	public void initModel() throws IOException {
		cleanupDatabaseDirectory();
		graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(databaseDirectory);

		try (Transaction tx = graphDb.beginTx()) {
			// bump the initial id from 0 to 1
			graphDb.createNode().delete();
			tx.success();
		}
	}

	@Override
	public Object createVertex(final int id, final String type, final Map<String, ? extends Object> attributes,
			final Map<String, Object> outgoingEdges, final Map<String, Object> incomingEdges) {
		final Node node = graphDb.createNode(Label.label(type));

		// this only works for inheritance hierarchies
		if (ModelConstants.SUPERTYPES.containsKey(type)) {
			final String ancestor = ModelConstants.SUPERTYPES.get(type);
			node.addLabel(Label.label(ancestor));
		}

		for (final Entry<String, ? extends Object> attribute : attributes.entrySet()) {
			final String key = attribute.getKey();
			Object value = attribute.getValue();

			// convert the value to string if it's an enum
			value = enumsToString(value);
			node.setProperty(key, value);
		}

		for (final Entry<String, Object> outgoingEdge : outgoingEdges.entrySet()) {
			final String label = outgoingEdge.getKey();
			if (outgoingEdge.getValue() instanceof Node) {
				final Node targetNode = (Node) outgoingEdge.getValue();
				node.createRelationshipTo(targetNode, relationship(label));
			}
		}

		for (final Entry<String, Object> incomingEdge : incomingEdges.entrySet()) {
			final String label = incomingEdge.getKey();
			if (incomingEdge.getValue() instanceof Node) {
				final Node sourceNode = (Node) incomingEdge.getValue();
				sourceNode.createRelationshipTo(node, relationship(label));
			}
		}

		return node;
	}

	private Object enumsToString(Object value) {
		if (value instanceof Enum) {
			final Enum<?> e = (Enum<?>) value;
			value = e.toString();
		}
		return value;
	}

	@Override
	public void createEdge(final String label, final Object from, final Object to) {
		if (from == null || to == null) {
			return;
		}

		final Node source = (Node) from;
		final Node target = (Node) to;

		final RelationshipType relationshipType = relationship(label);
		source.createRelationshipTo(target, relationshipType);
	}

	@Override
	public void setAttribute(final String type, final Object node, final String key, final Object value) {
		final Node n = (Node) node;

		final Object attributeValue = enumsToString(value);
		n.setProperty(key, attributeValue);
	}

	public RelationshipType relationship(final String label) {
		return RelationshipType.withName(label);
	}

	@Override
	public void persistModel() throws IOException, XMLStreamException, ShellException {
		try {
			switch (gc.getGraphFormat()) {
			case BINARY:
				saveToBinary();
				break;
			case CSV:
				break;
			case GRAPHML:
				saveToGraphMl();
				break;
			default:
				throw new UnsupportedOperationException("Graph format " + gc.getGraphFormat() + " not supported.");
			}
		} finally {
			graphDb.shutdown();

			// cleanup: delete the database directory
			cleanupDatabaseDirectory();
		}
	}

	private void saveToCsv(Transaction tx) {
		throw new UnsupportedOperationException("CSV serialization not yet implemented.");
	}

	private void saveToBinary() throws RemoteException, ShellException {
		final String fileName = gc.getConfigBase().getModelPathWithoutExtension() + "."
				+ Neo4jConstants.BINARY_EXTENSION;

		final SameJvmClient client = new SameJvmClient(Collections.<String, Serializable>emptyMap(), new GraphDatabaseShellServer((GraphDatabaseAPI) graphDb),
				InterruptSignalHandler.getHandler());

        final String exportCommand = String.format("export-binary -o %s", fileName);
        client.evaluate(exportCommand, new SilentLocalOutput());
	}

	private void saveToGraphMl() throws IOException, XMLStreamException {
		try (final Transaction tx = graphDb.beginTx()) {
			final ProgressReporter reporter = new ProgressReporter(null, null);

			final StringWriter writer = new StringWriter();
			final XmlGraphMLWriter xmlGraphMLWriter = new XmlGraphMLWriter();
			final Config config = Config.config();
			xmlGraphMLWriter.write(new DatabaseSubGraph(graphDb), writer, reporter, config.withTypes());
			tx.success();

			final String fileName = gc.getConfigBase().getModelPathWithoutExtension() + "."
					+ Neo4jConstants.GRAPHML_EXTENSION;

			final String graphmlContent = writer.toString();
			// this is required to be compatibile with OrientDB
			// graphmlContent = graphmlContent.replaceAll("<graph id=\"G\"
			// edgedefault=\"directed\">",
			// "<graph id=\"G\" edgedefault=\"directed\">\n<key id=\"labels\"
			// for=\"node\" attr.name=\"labels\"
			// attr.type=\"string\"/>");

			FileUtils.writeStringToFile(new File(fileName), graphmlContent.trim());
		}
	}

	private void cleanupDatabaseDirectory() throws IOException {
		if (databaseDirectory.exists()) {
			FileUtils.deleteDirectory(databaseDirectory);
		}
	}

	@Override
	public void beginTransaction() throws IOException {
		tx = graphDb.beginTx();
	}

	@Override
	public void endTransaction() {
		tx.success();
		tx.close();
	}

}
