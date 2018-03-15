/*******************************************************************************
 * Copyright (c) 2018 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Horacio Hoyos Rodriguez - initial API and implementation
 ******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.epsilonapi.operations;

import hu.bme.mit.trainbenchmark.benchmark.epsilonapi.driver.EpsilonDriver;
import hu.bme.mit.trainbenchmark.benchmark.epsilonapi.matches.EpsilonApiConnectedSegmentsInjectMatch;
import hu.bme.mit.trainbenchmark.benchmark.epsilonapi.matches.EpsilonApiConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.epsilonapi.matches.EpsilonMatch;
import hu.bme.mit.trainbenchmark.benchmark.epsilonapi.queries.EpsilonApiEvlQuery;
import hu.bme.mit.trainbenchmark.benchmark.epsilonapi.transformation.EpsilonApiEolQuery;
import hu.bme.mit.trainbenchmark.benchmark.epsilonapi.transformation.EpsilonApiEolTransformation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperation;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelOperationFactory;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import org.eclipse.epsilon.engine.standalone.EpsilonStandaloneEngineFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * A factory for creating EpsilonModelOperation objects.
 *
 * @param <TDriver> the generic type
 */
public class EpsilonModelOperationFactory<TDriver extends EpsilonDriver> extends ModelOperationFactory<EpsilonMatch, TDriver> {

	private static final Logger logger = LoggerFactory.getLogger(EpsilonModelOperationFactory.class);

	private final EpsilonStandaloneEngineFactory factory;
	private final boolean disposeAfterExecution;

	public EpsilonModelOperationFactory(EpsilonStandaloneEngineFactory factory, boolean disposeAfterExecution) {
		super();
		this.factory = factory;
		this.disposeAfterExecution = disposeAfterExecution;
	}


	@Override
	public ModelOperation<? extends EpsilonMatch, TDriver> createOperation(RailwayOperation operationEnum,
			String workspaceDir, final TDriver driver) throws Exception {

		//EpsilonMatchFactory epsilonMatchFactory = new EpsilonMatchFactory();
		switch (operationEnum) {
			// ConnectedSegments
			case CONNECTEDSEGMENTS: {
				logger.info("Creating operation for CONNECTEDSEGMENTS");
				final EpsilonApiEvlQuery<EpsilonApiConnectedSegmentsMatch, TDriver> query =
						new EpsilonApiEvlQuery<>(RailwayQuery.CONNECTEDSEGMENTS, driver, factory, "/queries/ConnectedSegments.evl", disposeAfterExecution);
				final ModelOperation<EpsilonApiConnectedSegmentsMatch, TDriver> operation = ModelOperation.of(query);
				return operation;
			}
			case CONNECTEDSEGMENTS_INJECT: {
				logger.info("Creating operation for CONNECTEDSEGMENTS_INJECT");
				final EpsilonApiEolQuery<EpsilonApiConnectedSegmentsInjectMatch, TDriver> query =
						new EpsilonApiEolQuery<>(RailwayQuery.CONNECTEDSEGMENTS_INJECT, driver, "/transformation/TransformOperations.eol", "ConnectedSegmentsInjectMatch");
				final EpsilonApiEolTransformation<EpsilonApiConnectedSegmentsInjectMatch, TDriver> transformation =
						new EpsilonApiEolTransformation<>(driver, "/transformation/TransformOperations.eol", "InjectConnectedSegments");
				final ModelOperation<EpsilonApiConnectedSegmentsInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}
			case CONNECTEDSEGMENTS_REPAIR: {
				logger.info("Creating operation for CONNECTEDSEGMENTS_REPAIR");
				final EpsilonApiEvlQuery<EpsilonApiConnectedSegmentsMatch, TDriver> query =
						new EpsilonApiEvlQuery<>(RailwayQuery.CONNECTEDSEGMENTS, driver, factory, "/queries/ConnectedSegments.evl", disposeAfterExecution);
				final EpsilonApiEolTransformation<EpsilonApiConnectedSegmentsMatch, TDriver> transformation =
						new EpsilonApiEolTransformation<>(driver, "/transformation/TransformOperations.eol", "RepairConnectedSegments");
				final ModelOperation<EpsilonApiConnectedSegmentsMatch, TDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}

			// PosLength
			case POSLENGTH: {
				logger.info("Creating operation for POSLENGTH");
				final EpsilonApiEvlQuery<EpsilonApiConnectedSegmentsMatch, TDriver> query =
					new EpsilonApiEvlQuery<>(RailwayQuery.POSLENGTH, driver, factory, "/queries/PosLength.evl", disposeAfterExecution);
				final ModelOperation<EpsilonApiConnectedSegmentsMatch, TDriver> operation = ModelOperation.of(query);
				return operation;
			}
			case POSLENGTH_INJECT: {
				logger.info("Creating operation for POSLENGTH_INJECT");
				final EpsilonApiEolQuery<EpsilonApiConnectedSegmentsInjectMatch, TDriver> query =
					new EpsilonApiEolQuery<>(RailwayQuery.POSLENGTH_INJECT, driver, "/transformation/TransformOperations.eol", "PosLengthInjectMatch");
				final EpsilonApiEolTransformation<EpsilonApiConnectedSegmentsInjectMatch, TDriver> transformation =
					new EpsilonApiEolTransformation<>(driver, "/transformation/TransformOperations.eol", "InjectPosLength");
				final ModelOperation<EpsilonApiConnectedSegmentsInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}
			case POSLENGTH_REPAIR: {
				logger.info("Creating operation for POSLENGTH_REPAIR");
				final EpsilonApiEvlQuery<EpsilonApiConnectedSegmentsMatch, TDriver> query =
					new EpsilonApiEvlQuery<>(RailwayQuery.POSLENGTH, driver, factory, "/queries/PosLength.evl", disposeAfterExecution);
				final EpsilonApiEolTransformation<EpsilonApiConnectedSegmentsMatch, TDriver> transformation =
					new EpsilonApiEolTransformation<>(driver, "/transformation/TransformOperations.eol", "RepairPosLength");
				final ModelOperation<EpsilonApiConnectedSegmentsMatch, TDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}

			// RouteSensor
			case ROUTESENSOR: {
				logger.info("Creating operation for ROUTESENSOR");
				final EpsilonApiEvlQuery<EpsilonApiConnectedSegmentsMatch, TDriver> query =
					new EpsilonApiEvlQuery<>(RailwayQuery.ROUTESENSOR, driver, factory, "/queries/RouteSensor.evl", disposeAfterExecution);
				final ModelOperation<EpsilonApiConnectedSegmentsMatch, TDriver> operation = ModelOperation.of(query);
				return operation;
			}
			case ROUTESENSOR_INJECT: {
				logger.info("Creating operation for ROUTESENSOR_INJECT");
				final EpsilonApiEolQuery<EpsilonApiConnectedSegmentsInjectMatch, TDriver> query =
					new EpsilonApiEolQuery<>(RailwayQuery.ROUTESENSOR_INJECT, driver, "/transformation/TransformOperations.eol", "RouteSensorInjectMatch");
				final EpsilonApiEolTransformation<EpsilonApiConnectedSegmentsInjectMatch, TDriver> transformation =
					new EpsilonApiEolTransformation<>(driver, "/transformation/TransformOperations.eol", "InjectRouteSensor");
				final ModelOperation<EpsilonApiConnectedSegmentsInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}
			case ROUTESENSOR_REPAIR: {
				logger.info("Creating operation for ROUTESENSOR_REPAIR");
				final EpsilonApiEvlQuery<EpsilonApiConnectedSegmentsMatch, TDriver> query =
					new EpsilonApiEvlQuery<>(RailwayQuery.ROUTESENSOR, driver, factory, "/queries/RouteSensor.evl", disposeAfterExecution);
				final EpsilonApiEolTransformation<EpsilonApiConnectedSegmentsMatch, TDriver> transformation =
					new EpsilonApiEolTransformation<>(driver, "/transformation/TransformOperations.eol", "RepairRouteSensor");
				final ModelOperation<EpsilonApiConnectedSegmentsMatch, TDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}

			// SemaphoreNeighbor
			case SEMAPHORENEIGHBOR: {
				logger.info("Creating operation for SEMAPHORENEIGHBOR");
				final EpsilonApiEvlQuery<EpsilonApiConnectedSegmentsMatch, TDriver> query =
					new EpsilonApiEvlQuery<>(RailwayQuery.SEMAPHORENEIGHBOR, driver, factory, "/queries/SemaphoreNeighbor.evl", disposeAfterExecution);
				final ModelOperation<EpsilonApiConnectedSegmentsMatch, TDriver> operation = ModelOperation.of(query);
				return operation;
			}
			case SEMAPHORENEIGHBOR_INJECT: {
				logger.info("Creating operation for SEMAPHORENEIGHBOR_INJECT");
				final EpsilonApiEolQuery<EpsilonApiConnectedSegmentsInjectMatch, TDriver> query =
					new EpsilonApiEolQuery<>(RailwayQuery.SEMAPHORENEIGHBOR_INJECT, driver, "/transformation/TransformOperations.eol", "SemaphoreNeighborInjectMatch");
				final EpsilonApiEolTransformation<EpsilonApiConnectedSegmentsInjectMatch, TDriver> transformation =
					new EpsilonApiEolTransformation<>(driver, "/transformation/TransformOperations.eol", "InjectSemaphoreNeighbor");
				final ModelOperation<EpsilonApiConnectedSegmentsInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}
			case SEMAPHORENEIGHBOR_REPAIR: {
				logger.info("Creating operation for SEMAPHORENEIGHBOR_REPAIR");
				final EpsilonApiEvlQuery<EpsilonApiConnectedSegmentsMatch, TDriver> query =
					new EpsilonApiEvlQuery<>(RailwayQuery.SEMAPHORENEIGHBOR, driver, factory, "/queries/SemaphoreNeighbor.evl", disposeAfterExecution);
				final EpsilonApiEolTransformation<EpsilonApiConnectedSegmentsMatch, TDriver> transformation =
					new EpsilonApiEolTransformation<>(driver, "/transformation/TransformOperations.eol", "RepairSemaphoreNeighbor");
				final ModelOperation<EpsilonApiConnectedSegmentsMatch, TDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}


			// SwitchMonitored
			case SWITCHMONITORED: {
				logger.info("Creating operation for SWITCHMONITORED");
				final EpsilonApiEvlQuery<EpsilonApiConnectedSegmentsMatch, TDriver> query =
					new EpsilonApiEvlQuery<>(RailwayQuery.SWITCHMONITORED, driver, factory, "/queries/SwitchMonitored.evl", disposeAfterExecution);
				final ModelOperation<EpsilonApiConnectedSegmentsMatch, TDriver> operation = ModelOperation.of(query);
				return operation;
			}
			case SWITCHMONITORED_INJECT: {
				logger.info("Creating operation for SWITCHMONITORED_INJECT");
				final EpsilonApiEolQuery<EpsilonApiConnectedSegmentsInjectMatch, TDriver> query =
					new EpsilonApiEolQuery<>(RailwayQuery.SWITCHMONITORED_INJECT, driver, "/transformation/TransformOperations.eol", "SwitchMonitoredInjectMatch");
				final EpsilonApiEolTransformation<EpsilonApiConnectedSegmentsInjectMatch, TDriver> transformation =
					new EpsilonApiEolTransformation<>(driver, "/transformation/TransformOperations.eol", "InjectSwitchMonitored");
				final ModelOperation<EpsilonApiConnectedSegmentsInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}
			case SWITCHMONITORED_REPAIR: {
				logger.info("Creating operation for SWITCHMONITORED_REPAIR");
				final EpsilonApiEvlQuery<EpsilonApiConnectedSegmentsMatch, TDriver> query =
					new EpsilonApiEvlQuery<>(RailwayQuery.SWITCHMONITORED, driver, factory, "/queries/SwitchMonitored.evl", disposeAfterExecution);
				final EpsilonApiEolTransformation<EpsilonApiConnectedSegmentsMatch, TDriver> transformation =
					new EpsilonApiEolTransformation<>(driver, "/transformation/TransformOperations.eol", "RepairSwitchMonitored");
				final ModelOperation<EpsilonApiConnectedSegmentsMatch, TDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}


			// SwitchSet
			case SWITCHSET: {
				logger.info("Creating operation for SWITCHSET");
				final EpsilonApiEvlQuery<EpsilonApiConnectedSegmentsMatch, TDriver> query =
					new EpsilonApiEvlQuery<>(RailwayQuery.SWITCHSET, driver, factory, "/queries/SwitchSet.evl", disposeAfterExecution);
				final ModelOperation<EpsilonApiConnectedSegmentsMatch, TDriver> operation = ModelOperation.of(query);
				return operation;
			}
			case SWITCHSET_INJECT: {
				logger.info("Creating operation for SWITCHSET_INJECT");
				final EpsilonApiEolQuery<EpsilonApiConnectedSegmentsInjectMatch, TDriver> query =
					new EpsilonApiEolQuery<>(RailwayQuery.SWITCHSET_INJECT, driver, "/transformation/TransformOperations.eol", "SwitchSetInjectMatch");
				final EpsilonApiEolTransformation<EpsilonApiConnectedSegmentsInjectMatch, TDriver> transformation =
					new EpsilonApiEolTransformation<>(driver, "/transformation/TransformOperations.eol", "InjectSwitchSet");
				final ModelOperation<EpsilonApiConnectedSegmentsInjectMatch, TDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}
			case SWITCHSET_REPAIR: {
				logger.info("Creating operation for SWITCHSET_REPAIR");
				final EpsilonApiEvlQuery<EpsilonApiConnectedSegmentsMatch, TDriver> query =
					new EpsilonApiEvlQuery<>(RailwayQuery.SWITCHSET, driver, factory, "/queries/SwitchSet.evl", disposeAfterExecution);
				final EpsilonApiEolTransformation<EpsilonApiConnectedSegmentsMatch, TDriver> transformation =
					new EpsilonApiEolTransformation<>(driver, "/transformation/TransformOperations.eol", "RepairSwitchSet");
				final ModelOperation<EpsilonApiConnectedSegmentsMatch, TDriver> operation = ModelOperation.of(query, transformation);
				return operation;
			}
			default:
				break;
		}
		logger.error("Operation {} not supported.", operationEnum);
		throw new UnsupportedOperationException("Operation " + operationEnum + " not supported.");
	}

}
