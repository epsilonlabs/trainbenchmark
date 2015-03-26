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

package hu.bme.mit.trainbenchmark.benchmark.eclipseocl.test;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.eclipseocl.EclipseOCLBenchmarkLogic;
import hu.bme.mit.trainbenchmark.benchmark.test.TestBenchmarkInitializer;
import hu.bme.mit.trainbenchmark.constants.Scenario;

public class EclipseOCLBenchmarkInitializer extends TestBenchmarkInitializer<EclipseOCLBenchmarkLogic> {

	@Override
	protected EclipseOCLBenchmarkLogic initializeBenchmark(final String queryName, final Scenario scenario) {
		final BenchmarkConfig bc = new BenchmarkConfig(scenario, size, "EclipseOCL", 1, queryName, iterationCount, modificationMethod,
				modificationConstant);
		return new EclipseOCLBenchmarkLogic(bc);
	}

}