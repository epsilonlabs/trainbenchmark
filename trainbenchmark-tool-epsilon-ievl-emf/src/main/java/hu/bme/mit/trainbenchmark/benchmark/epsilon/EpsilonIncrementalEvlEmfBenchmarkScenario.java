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
package hu.bme.mit.trainbenchmark.benchmark.epsilon;

import hu.bme.mit.trainbenchmark.benchmark.epsilon.comparators.EpsilonMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.epsilon.config.EpsilonIncrementalEvlEmfBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.epsilon.driver.EpsilonDriver;
import hu.bme.mit.trainbenchmark.benchmark.epsilon.driver.EpsilonDriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.epsilon.matches.EpsilonMatch;
import hu.bme.mit.trainbenchmark.benchmark.epsilon.operations.EpsilonEvlModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;

// TODO: Auto-generated Javadoc

/**
 * The Class EpsilonIncrementalEvlEmfBenchmarkScenario.
 */
public class EpsilonIncrementalEvlEmfBenchmarkScenario
		extends BenchmarkScenario<EpsilonMatch, EpsilonDriver, EpsilonIncrementalEvlEmfBenchmarkConfig> {

	/**
	 * Instantiates a new epsilon api benchmark scenario.
	 *
	 * @param bc the bc
	 * @throws Exception the exception
	 */
	public EpsilonIncrementalEvlEmfBenchmarkScenario(final EpsilonIncrementalEvlEmfBenchmarkConfig bc) throws Exception {
		super(new EpsilonDriverFactory(bc),
				new EpsilonEvlModelOperationFactory<>(bc.getEngineFinder()),
				new EpsilonMatchComparator(), bc);
	}

}
