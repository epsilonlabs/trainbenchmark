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

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.epsilonapi.config.EpsilonApiBenchmarkConfig;


// TODO: Auto-generated Javadoc
/**
 * The Class EpsilonApiBenchmarkMain.
 */
public class EpsilonApiBenchmarkMain {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(final String[] args) throws Exception {
		final EpsilonApiBenchmarkConfig bc = BenchmarkConfig.fromFile(args[0], EpsilonApiBenchmarkConfig.class);
		final EpsilonApiBenchmarkScenario scenario = new EpsilonApiBenchmarkScenario(bc);
		scenario.performBenchmark();
	}

}
