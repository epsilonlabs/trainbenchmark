package hu.bme.mit.trainbenchmark.benchmark.epsilon.test;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBase;

import hu.bme.mit.trainbenchmark.benchmark.epsilon.EpsilonEvlEmfBenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.epsilon.config.EpsilonEvlEmfBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.epsilon.config.EpsilonEvlEmfBenchmarkConfigBuilder;
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.test.TrainBenchmarkTest;
import org.eclipse.epsilon.engine.standalone.EpsilonStandaloneEngineFactory;
import org.eclipse.epsilon.engine.standalone.evl.EvlStandaloneEngine;

public class EpsilonEvlEmfTest extends TrainBenchmarkTest {

	/**
	 * Use the default EVL engine and an EMF driver
	 * @param bcb
	 * @return
	 * @throws Exception
	 */
	@Override
	protected BenchmarkResult runTest(final BenchmarkConfigBase bcb) throws Exception {
		EpsilonEvlEmfBenchmarkConfigBuilder configBuilder = new EpsilonEvlEmfBenchmarkConfigBuilder()
			.withEmfModel()
			.disposeAfterExecution(true);
		final EpsilonEvlEmfBenchmarkConfig bc = configBuilder.setConfigBase(bcb).createConfig();
		final EpsilonEvlEmfBenchmarkScenario scenario = new EpsilonEvlEmfBenchmarkScenario(bc);
		final BenchmarkResult result = scenario.performBenchmark();

		return result;
	}
}
