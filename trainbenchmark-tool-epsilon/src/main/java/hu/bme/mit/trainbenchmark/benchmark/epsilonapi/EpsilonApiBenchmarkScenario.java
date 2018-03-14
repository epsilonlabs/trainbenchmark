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
package hu.bme.mit.trainbenchmark.benchmark.epsilonapi;

import hu.bme.mit.trainbenchmark.benchmark.epsilonapi.comparators.EpsilonMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.epsilonapi.config.EpsilonApiBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.epsilonapi.driver.EpsilonDriver;
import hu.bme.mit.trainbenchmark.benchmark.epsilonapi.driver.EpsilonDriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.epsilonapi.matches.EpsilonMatch;
import hu.bme.mit.trainbenchmark.benchmark.epsilonapi.operations.EpsilonModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;

// TODO: Auto-generated Javadoc
/**
 * The Class EpsilonApiBenchmarkScenario.
 */
public class EpsilonApiBenchmarkScenario
		extends BenchmarkScenario<EpsilonMatch, EpsilonDriver, EpsilonApiBenchmarkConfig> {

	/**
	 * Instantiates a new epsilon api benchmark scenario.
	 *
	 * @param bc the bc
	 * @throws Exception the exception
	 */
	public EpsilonApiBenchmarkScenario(final EpsilonApiBenchmarkConfig bc) throws Exception {
		super(new EpsilonDriverFactory(bc),
				new EpsilonModelOperationFactory<>(bc.getEngineFactory(), bc.isDisposeAfterExecution()),
				new EpsilonMatchComparator(), bc);
	}

}
