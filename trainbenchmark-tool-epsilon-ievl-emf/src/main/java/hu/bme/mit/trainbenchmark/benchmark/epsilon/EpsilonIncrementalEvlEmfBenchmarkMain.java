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

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.epsilon.config.EpsilonEvlEmfBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.epsilon.config.EpsilonIncrementalEvlEmfBenchmarkConfig;


/**
 * The Class EpsilonIncrementalEvlEmfBenchmarkMain.
 */
public class EpsilonIncrementalEvlEmfBenchmarkMain {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(final String[] args) throws Exception {
		final EpsilonIncrementalEvlEmfBenchmarkConfig bc = BenchmarkConfig.fromFile(args[0], EpsilonIncrementalEvlEmfBenchmarkConfig.class);
		final EpsilonIncrementalEvlEmfBenchmarkScenario scenario = new EpsilonIncrementalEvlEmfBenchmarkScenario(bc);
		scenario.performBenchmark();
	}

}
