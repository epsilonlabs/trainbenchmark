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

package hu.bme.mit.trainbenchmark.benchmark.sqlite;

import java.io.IOException;
import java.util.Comparator;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.transformations.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.matches.LongMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.sql.benchmarkcases.SQLBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.sql.benchmarkcases.SQLChecker;
import hu.bme.mit.trainbenchmark.benchmark.sql.transformations.SQLTransformation;
import hu.bme.mit.trainbenchmark.benchmark.sqlite.driver.SQLiteDriver;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public class SQLiteBenchmarkCase extends SQLBenchmarkCase<BenchmarkConfig, SQLiteDriver> {


	@Override
	public SQLiteDriver createDriver(final BenchmarkConfig benchmarkConfig) throws Exception {
		return new SQLiteDriver(benchmarkConfig.getMaxMemory());
	}

	@Override
	public SQLChecker createChecker(final BenchmarkConfig benchmarkConfig, final SQLiteDriver driver, final RailwayQuery query) throws Exception {
		return new SQLChecker(driver, benchmarkConfig, query);
	}

	@Override
	public Transformation<?, ?> createTransformation(final BenchmarkConfig benchmarkConfig, final SQLiteDriver driver, final RailwayQuery query) throws IOException {
		return SQLTransformation.newInstance(driver, benchmarkConfig, query);
	}

	@Override
	public Comparator<?> createMatchComparator() {
		return new LongMatchComparator();
	}

}