package hu.bme.mit.trainbenchmark.benchmark.test.queryspecific;

import org.junit.Rule;
import org.junit.rules.ErrorCollector;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBase;
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkResult;
import hu.bme.mit.trainbenchmark.config.ExecutionConfig;

public abstract class QueryTest {

	protected ExecutionConfig executionConfig = ExecutionConfig.defaultExecutionConfig();
	protected final long timeout = 120;
	protected final int runs = 1;

	protected final String modelFilename = "railway-repair-1";
	protected final int queryTransformationCount = 1;

	@Rule
	public ErrorCollector collector = new ErrorCollector();

	protected abstract BenchmarkResult runTest(BenchmarkConfigBase bcb) throws Exception;

	protected BenchmarkResult performBenchmark(final BenchmarkConfigBase bcb) throws Exception {
		final BenchmarkResult result = runTest(bcb);
		// System.out.println(result);
		// System.out.println(result.csvMatches());
		// System.out.println(result.csvTimes());
		return result;
	}

}
