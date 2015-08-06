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

package hu.bme.mit.trainbenchmark.generator;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public abstract class Generator {

	protected int id = 1;

	// static configuration
	protected GeneratorConfig generatorConfig;

	protected static final Map<String, Object> emptyMap = Collections.emptyMap();

	protected abstract String syntax();

	protected abstract void initModel() throws IOException;

	public void generate() throws Exception {
		System.out.print("Generating instance model, generator: " + syntax() + ", size: " + generatorConfig.getSize() + "... ");
		initializeConstants();
		initModel();
		generateModel();
		persistModel();
		System.out.println("Done.");
	}
	
	protected abstract void initializeConstants();
	
	protected abstract void generateModel() throws FileNotFoundException, IOException;
	
	protected abstract void persistModel() throws Exception;

	// the createVertex() methods with fewer arguments are final

	protected final Object createVertex(final String type) throws IOException {
		return createVertex(type, emptyMap);
	}

	protected final Object createVertex(final String type, final Map<String, Object> attributes) throws IOException {
		return createVertex(type, attributes, emptyMap);
	}

	protected final Object createVertex(final String type, final Map<String, Object> attributes, final Map<String, Object> outgoingEdges)
			throws IOException {
		return createVertex(type, attributes, outgoingEdges, emptyMap);
	}

	protected final Object createVertex(final String type, final Map<String, Object> attributes, final Map<String, Object> outgoingEdges,
			final Map<String, Object> incomingEdges) throws IOException {
		final Object vertex = createVertex(id, type, attributes, outgoingEdges, incomingEdges);
		id++;
		return vertex;
	}

	protected abstract Object createVertex(final int id, final String type, final Map<String, Object> attributes,
			final Map<String, Object> outgoingEdges, final Map<String, Object> incomingEdges) throws IOException;

	protected abstract void createEdge(String label, Object from, Object to) throws IOException;

	protected abstract void setAttribute(String type, Object node, String key, Object value) throws IOException;

}
