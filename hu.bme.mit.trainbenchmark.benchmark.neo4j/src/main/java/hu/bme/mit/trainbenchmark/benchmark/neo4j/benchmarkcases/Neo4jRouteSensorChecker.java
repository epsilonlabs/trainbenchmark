/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/

package hu.bme.mit.trainbenchmark.benchmark.neo4j.benchmarkcases;

import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_ROUTE;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SENSOR;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SW;
import static hu.bme.mit.trainbenchmark.constants.QueryConstants.VAR_SWP;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.constants.Neo4jConstants;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.driver.Neo4jDriver;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jRouteSensorMatch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.ResourceIterable;
import org.neo4j.graphdb.Transaction;
import org.neo4j.tooling.GlobalGraphOperations;

public class Neo4jRouteSensorChecker extends Neo4jJavaChecker<Neo4jRouteSensorMatch> {

	public Neo4jRouteSensorChecker(final Neo4jDriver neoDriver) {
		super(neoDriver);
	}

	@Override
	public Collection<Neo4jRouteSensorMatch> check() {
		final Collection<Neo4jRouteSensorMatch> matches = new HashSet<>();

		final GraphDatabaseService graphDb = driver.getGraphDb();
		try (Transaction tx = graphDb.beginTx()) {
			// (route:Route)-[:follows]->()
			final ResourceIterable<Node> routes = GlobalGraphOperations.at(graphDb).getAllNodesWithLabel(Neo4jConstants.labelRoute);
			for (final Node route : routes) {
				final Iterable<Relationship> followss = route.getRelationships(Direction.OUTGOING, Neo4jConstants.relationshipTypeFollows);

				for (final Relationship follows : followss) {
					final Node swP = follows.getEndNode();

					// (swP:switchPosition)-[:switch]->()
					if (!swP.hasLabel(Neo4jConstants.labelSwitchPosition)) {
						continue;
					}
					final Iterable<Relationship> relationshipSwitches = swP.getRelationships(Direction.OUTGOING,
							Neo4jConstants.relationshipTypeSwitch);
					for (final Relationship relationshipSwitch : relationshipSwitches) {
						final Node sw = relationshipSwitch.getEndNode();

						// (switch:Switch)-[:sensor]->()
						if (!sw.hasLabel(Neo4jConstants.labelSwitch)) {
							continue;
						}
						final Iterable<Relationship> relationshipSensors = sw.getRelationships(Direction.OUTGOING,
								Neo4jConstants.relationshipTypeSensor);
						for (final Relationship relationshipSensor : relationshipSensors) {
							final Node sensor = relationshipSensor.getEndNode();

							if (matches.contains(sensor)) {
								continue;
							}

							// (sensor:Sensor)<-[:definedBy]-(Route) NAC
							if (!sensor.hasLabel(Neo4jConstants.labelSensor)) {
								continue;
							}
							final Iterable<Relationship> definedBys = sensor.getRelationships(Direction.INCOMING,
									Neo4jConstants.relationshipTypeDefinedBy);

							final List<Node> routes2 = new ArrayList<>();
							for (final Relationship definedBy : definedBys) {
								final Node route2 = definedBy.getStartNode();
								if (!route2.hasLabel(Neo4jConstants.labelRoute)) {
									continue;
								}
								routes2.add(route2);
							}

							if (!routes2.contains(route)) {
								final Map<String, Object> match = new HashMap<>();
								match.put(VAR_ROUTE, route);
								match.put(VAR_SENSOR, sensor);
								match.put(VAR_SWP, swP);
								match.put(VAR_SW, sw);
								matches.add(new Neo4jRouteSensorMatch(match));
							}
						}
					}
				}
			}
		}

		return matches;
	}
}
