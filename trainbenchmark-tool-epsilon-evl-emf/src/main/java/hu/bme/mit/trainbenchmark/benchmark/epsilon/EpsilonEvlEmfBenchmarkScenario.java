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
import hu.bme.mit.trainbenchmark.benchmark.epsilon.config.EpsilonEvlEmfBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.epsilon.driver.EpsilonDriver;
import hu.bme.mit.trainbenchmark.benchmark.epsilon.driver.EpsilonDriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.epsilon.matches.EpsilonMatch;
import hu.bme.mit.trainbenchmark.benchmark.epsilon.operations.EpsilonModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;

// TODO: Auto-generated Javadoc
/**
 * The Class EpsilonEvlEmfBenchmarkScenario.
 */
public class EpsilonEvlEmfBenchmarkScenario
		extends BenchmarkScenario<EpsilonMatch, EpsilonDriver, EpsilonEvlEmfBenchmarkConfig> {

	/**
	 * Instantiates a new epsilon api benchmark scenario.
	 *
	 * @param bc the bc
	 * @throws Exception the exception
	 */
	public EpsilonEvlEmfBenchmarkScenario(final EpsilonEvlEmfBenchmarkConfig bc) throws Exception {
		super(new EpsilonDriverFactory(bc),
				new EpsilonModelOperationFactory<>(bc.getEngineFactory(), bc.getEngineName(), bc.isDisposeAfterExecution()),
				new EpsilonMatchComparator(), bc);
	}

}
