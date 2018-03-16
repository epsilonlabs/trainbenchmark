package hu.bme.mit.trainbenchmark.benchmark.epsilonapi.test;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBase;

import hu.bme.mit.trainbenchmark.benchmark.epsilonapi.EpsilonApiBenchmarkScenario;
import hu.bme.mit.trainbenchmark.benchmark.epsilonapi.config.EpsilonApiBenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.epsilonapi.config.EpsilonApiBenchmarkConfigBuilder;
import hu.bme.mit.trainbenchmark.benchmark.runcomponents.BenchmarkResult;
import hu.bme.mit.trainbenchmark.benchmark.test.TrainBenchmarkTest;
import org.eclipse.epsilon.engine.standalone.EpsilonStandaloneEngineFactory;
import org.eclipse.epsilon.engine.standalone.model.EmfModelBuilder;

import java.nio.file.Path;
import java.nio.file.Paths;

public class EpsilonApiTest extends TrainBenchmarkTest {

	/**
	 * Use the default EVL engine and an EMF driver
	 * @param bcb
	 * @return
	 * @throws Exception
	 */
	@Override
	protected BenchmarkResult runTest(final BenchmarkConfigBase bcb) throws Exception {
		EpsilonApiBenchmarkConfigBuilder configBuilder = new EpsilonApiBenchmarkConfigBuilder()
				.withEmfModel()
				.withEvlFactory("EVL")
				.disposeAfterExecution(true);
		final EpsilonApiBenchmarkConfig bc = configBuilder.setConfigBase(bcb).createConfig();
		final EpsilonApiBenchmarkScenario scenario = new EpsilonApiBenchmarkScenario(bc);
		final BenchmarkResult result = scenario.performBenchmark();
		return result;
	}
}
