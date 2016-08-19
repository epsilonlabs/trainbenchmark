package hu.bme.mit.trainbenchmark.benchmark.mysql;

import hu.bme.mit.trainbenchmark.benchmark.comparators.LongMatchComparator;
import hu.bme.mit.trainbenchmark.benchmark.mysql.config.MySqlBenchmarkConfigWrapper;
import hu.bme.mit.trainbenchmark.benchmark.mysql.driver.MySqlDriver;
import hu.bme.mit.trainbenchmark.benchmark.mysql.driver.MySqlDriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.mysql.operations.MySqlModelOperationFactory;
import hu.bme.mit.trainbenchmark.benchmark.phases.BenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.sql.matches.SqlMatch;

public class MySqlBenchmarkScenario extends BenchmarkScenario<SqlMatch, MySqlDriver, MySqlBenchmarkConfigWrapper> {

	public MySqlBenchmarkScenario(final MySqlBenchmarkConfigWrapper bcw) throws Exception {
		super(new MySqlDriverFactory(), new MySqlModelOperationFactory(), new LongMatchComparator<SqlMatch>(), bcw);
	}

}
