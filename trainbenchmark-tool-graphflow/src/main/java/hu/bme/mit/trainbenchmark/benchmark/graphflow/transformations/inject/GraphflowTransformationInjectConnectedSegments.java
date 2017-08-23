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
package hu.bme.mit.trainbenchmark.benchmark.graphflow.transformations.inject;

import com.google.common.collect.ImmutableMap;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.driver.GraphflowDriver;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.matches.GraphflowConnectedSegmentsInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.graphflow.transformations.GraphflowTransformation;
import hu.bme.mit.trainbenchmark.constants.QueryConstants;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.constants.TrainBenchmarkConstants;
import hu.bme.mit.trainbenchmark.neo4j.Neo4jConstants;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public class GraphflowTransformationInjectConnectedSegments extends GraphflowTransformation<GraphflowConnectedSegmentsInjectMatch> {

	public GraphflowTransformationInjectConnectedSegments(final GraphflowDriver driver, final String workspaceDir) throws IOException {
		super(driver, workspaceDir, RailwayOperation.CONNECTEDSEGMENTS_INJECT);
	}

	@Override
	public void activate(final Collection<GraphflowConnectedSegmentsInjectMatch> matches) throws IOException {
		for (final GraphflowConnectedSegmentsInjectMatch match : matches) {
			final Map<String, Object> parameters = ImmutableMap.of( //
					QueryConstants.VAR_SENSOR, match.getSensor().getProperty(Neo4jConstants.ID), //
					QueryConstants.VAR_SEGMENT1, match.getSegment1().getProperty(Neo4jConstants.ID), //
					QueryConstants.VAR_SEGMENT3, match.getSegment3().getProperty(Neo4jConstants.ID), //
					QueryConstants.VAR_LENGTH, TrainBenchmarkConstants.DEFAULT_SEGMENT_LENGTH //
			);
			driver.runTransformation(transformationDefinition, parameters);
		}
	}

}
