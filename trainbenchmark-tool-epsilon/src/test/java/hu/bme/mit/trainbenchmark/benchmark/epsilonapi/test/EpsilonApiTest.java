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
		Path metamodelFile = Paths.get("../trainbenchmark-format-emf-model/src/railway.ecore");
		EpsilonApiBenchmarkConfigBuilder configBuilder = new EpsilonApiBenchmarkConfigBuilder()
				.withModelBuilder(new EmfModelBuilder().withMetamodelPath(Paths.get(metamodelFile.toFile().getCanonicalPath())))
				.withEvlFactory(EpsilonStandaloneEngineFactory.EVL)
				.disposeAfterExecution(true)
				.withModelPostfix(".xmi");
		final EpsilonApiBenchmarkConfig bc = configBuilder.setConfigBase(bcb).createConfig();
		final EpsilonApiBenchmarkScenario scenario = new EpsilonApiBenchmarkScenario(bc);
		final BenchmarkResult result = scenario.performBenchmark();
		return result;
	}
}
