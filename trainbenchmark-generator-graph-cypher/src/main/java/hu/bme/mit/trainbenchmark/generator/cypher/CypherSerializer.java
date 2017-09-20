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

package hu.bme.mit.trainbenchmark.generator.cypher;

import hu.bme.mit.trainbenchmark.generator.ModelSerializer;
import hu.bme.mit.trainbenchmark.generator.cypher.config.CypherFormat;
import hu.bme.mit.trainbenchmark.generator.cypher.config.CypherGeneratorConfig;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.SUPERTYPES;

public class CypherSerializer extends ModelSerializer<CypherGeneratorConfig> {

	private BufferedWriter writer;
	private List<String> queries = new ArrayList<>();


	CypherSerializer(final CypherGeneratorConfig gc) {
		super(gc);
	}

	@Override
	public String syntax() {
		return "Cypher";
	}

	@Override
	public void initModel() throws IOException {
		String extension = gc.getCypherFormat() == CypherFormat.BASIC ? ".cypher" : ".graphflow";
		final String cypherPath = gc.getConfigBase().getModelPathWithoutExtension() + extension;
		final File cypherFile = new File(cypherPath);
		writer = new BufferedWriter(new FileWriter(cypherFile));
	}

	@Override
	public void persistModel() throws IOException, InterruptedException {

		for(String query : queries){
			writer.write(query + "\n");
		}
		writer.close();
	}

	class Node {
		final int id;
		final String type;

		Node(int id, String type) {
			this.id = id;
			this.type = type;
		}

		@Override
		public String toString() {
			return ">> type: " + this.type + " id:" + this.id;
		}
	}

	@Override
	public Object createVertex(final int id, final String type, final Map<String, ? extends Object> attributes, final Map<String, Object> outgoingEdges,
							   final Map<String, Object> incomingEdges) throws IOException {

		if (gc.getCypherFormat() == CypherFormat.BASIC) {

			createBasicFormatVertex(id, type, attributes);

			addOutgoingEdges(id, type, outgoingEdges);
			addIncomingEdges(id, type, incomingEdges);

			return id;

		} else if (gc.getCypherFormat() == CypherFormat.GRAPHFLOW) {

			createGraphflowFormatVertex(id, type, attributes);

			addOutgoingEdges(id, type, outgoingEdges);
			addIncomingEdges(id, type, incomingEdges);

			return new Node(id, type);
		} else {
			throw new NotImplementedException();
		}
	}

	private void createBasicFormatVertex(final int id, final String type, final Map<String, ? extends Object> attributes) throws IOException {

		StringBuilder query = new StringBuilder("CREATE (node");

		//If we have supertypes, we add them first
		if (SUPERTYPES.containsKey(type)) {
			final String ancestorType = SUPERTYPES.get(type);
			query.append(":" + ancestorType);
		}

		//Then we add the type
		query.append(":" + type);

		//Setting the attributes
		query.append(" {id: " + id);
		if (!attributes.isEmpty()) {
			query.append(", ");
			query.append(
				attributes.entrySet().stream().map(
					e -> (e.getKey() + ": " + valueToString(e.getValue()))
				).collect(Collectors.joining(", "))
			);
		}
		query.append("});");

		queries.add(query.toString());
	}

	private void createGraphflowFormatVertex(final int id, final String type, final Map<String, ? extends Object> attributes) throws IOException {

		StringBuilder query = new StringBuilder("CREATE (" + id);


		// REMOVED - GRAPHFLOW SUPPORTS ONLY ONE TYPE
//		if (SUPERTYPES.containsKey(type)) {
//			final String ancestorType = SUPERTYPES.get(type);
//			query.append(":" + ancestorType);
//		}

		query.append(":" + type);

		if (!attributes.isEmpty()) {
			query.append(" {");

			query.append(
				attributes.entrySet().stream().map(
					e -> (e.getKey() + ": " + valueToString(e.getValue()))
				).collect(Collectors.joining(", "))
			);
			query.append("});");
		} else {
			query.append(");");
		}


		queries.add(query.toString());
	}

	private void addOutgoingEdges(final int id, final String type, final Map<String, Object> outgoingEdges) throws IOException {
		switch (gc.getCypherFormat()) {

			case BASIC:
				for (Entry<String, Object> entry : outgoingEdges.entrySet()) {
					if (entry.getValue()==null){
						continue;
					}
					queries.add("MATCH (from {id: " + id + "}), (to {id: " + entry.getValue() + "}) CREATE (from)-[:" + entry.getKey() + "]->(to);");
				}
				break;

			case GRAPHFLOW:
				Node node;
				for (Entry<String, Object> entry : outgoingEdges.entrySet()) {
					if (entry.getValue()==null){
						continue;
					}
					node = (Node) entry.getValue();
					queries.add("CREATE (" + id + ":" + type + ")-[:" + entry.getKey() + "]->(" + node.id +":"+ node.type + ");");
				}
				break;
		}
	}

	private void addIncomingEdges(final int id, final String type, final Map<String, Object> incomingEdges) throws IOException {
		switch (gc.getCypherFormat()){

			case BASIC:
				for (Entry<String, Object> entry : incomingEdges.entrySet()) {
					queries.add("MATCH (from {id: " + entry.getValue() + "}), (to {id: " + id + "}) CREATE (from)-[:" + entry.getKey() + "]->(to);");
				}
				break;

			case GRAPHFLOW:
				Node node;
				for (Entry<String, Object> entry : incomingEdges.entrySet()) {
					node = (Node) entry.getValue();
					queries.add("CREATE (" + node.id + ":" + node.type + ")-[:" + entry.getKey() + "]->(" + id +":"+ type +");");
				}
				break;
		}
	}

	@Override
	public void createEdge(final String label, final Object from, final Object to) throws IOException {
		if (from == null || to == null) {
			return;
		}

		switch (gc.getCypherFormat()){

			case BASIC:
				queries.add("MATCH (from {id: " + from + "}), (to {id: " + to + "}) CREATE (from)-[:" + label + "]->(to);");
				break;

			case GRAPHFLOW:
				Node from_node = (Node) from;
				Node to_node = (Node) to;

				queries.add("CREATE (" + from_node.id + ":" + from_node.type +")-[:" + label + "]->(" + to_node.id + ":" + to_node.type + ");");
				break;
		}
	}

	@Override
	public void setAttribute(final String type, final Object node, final String key, final Object value) throws IOException {
		final String stringValue = valueToString(value);
		switch (gc.getCypherFormat()){

			case BASIC:
				queries.add("MATCH (node:" + type + " {id: " + node + "}) SET node." + key + "=" + stringValue + ";");
				break;

			case GRAPHFLOW:
				Node the_node = (Node) node;

				String regexPattern = "\\(" + the_node.id + ":" + type + " \\{(.*)\\}\\)";
				Pattern pattern = Pattern.compile(regexPattern);
				Matcher matcher;
				Boolean found = false;
				for (int i = queries.size()-1; i>=0; i--){
					matcher = pattern.matcher(queries.get(i));
					if (matcher.find()){
						found = true;
						//TODO CREATE NODE WITH THE SAME ID + OLD ATTRIBUTES AND THE NEW ONE
						break;
					}
				}
				if (!found){
					queries.add("CREATE (" + the_node.id + ":" + type + " {" + key + ":" + stringValue + "});");
				}

				break;
		}
	}

	private String valueToString(final Object value) {
		if (value.toString().equals("true") || value.toString().equals("false")) return value.toString();
		try {
			Integer.parseInt(value.toString());
			return value.toString();
		} catch (NumberFormatException e) {
			return "'" + value + "'";
		}
	}

}
