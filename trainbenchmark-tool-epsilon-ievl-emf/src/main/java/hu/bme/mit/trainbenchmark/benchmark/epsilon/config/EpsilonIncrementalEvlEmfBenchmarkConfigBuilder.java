package hu.bme.mit.trainbenchmark.benchmark.epsilon.config;

import hu.bme.mit.trainbenchmark.benchmark.epsilon.util.EngineFinder;
import org.eclipse.epsilon.engine.standalone.evl.IEvlStandaloneEngine;
import org.eclipse.epsilon.standalone.engine.EpsilonIncrementalStandaloneEngineFactory;
import org.eclipse.epsilon.standalone.model.IncrementalEmfModelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EpsilonIncrementalEvlEmfBenchmarkConfigBuilder
	extends AbstractEpsilonBenchmarkConfigBuilder<EpsilonIncrementalEvlEmfBenchmarkConfig> {

	private static final Logger logger = LoggerFactory.getLogger(EpsilonIncrementalEvlEmfBenchmarkConfigBuilder.class);
	private EngineFinder<IEvlStandaloneEngine, EpsilonIncrementalStandaloneEngineFactory> engineFinder;

	public EpsilonIncrementalEvlEmfBenchmarkConfigBuilder() {
		this.engineFinder = new EngineFinder<>(true, new EpsilonIncrementalStandaloneEngineFactory(), "iEVL");
	}

	@Override
	public EpsilonIncrementalEvlEmfBenchmarkConfig createConfig() {
		checkNotNulls();
		return new EpsilonIncrementalEvlEmfBenchmarkConfig(configBase, modelBuilder, postfix, disposeAfterExecution, engineFinder);
	}

	/**
	 * Use the EmfModelBuilder
	 */
	public EpsilonIncrementalEvlEmfBenchmarkConfigBuilder withEmfModel() {
		Path metamodelFile = Paths.get("../trainbenchmark-format-emf-model/src/railway.ecore");
		try {
			this.modelBuilder = new IncrementalEmfModelBuilder().withMetamodelPath(Paths.get(metamodelFile.toFile().getCanonicalPath()));
		} catch (IOException e) {
			logger.error("Failed to get canonical path from metamodel Path", e);
			throw new IllegalArgumentException(e);
		}
		postfix = ".xmi";
		return this;
	}


	/**
	 * Determine if the EVL engine is disposed after execution. Should be false for incremental EVL.
	 * @param disposeAfterExecution
	 * @return
	 */
	public EpsilonIncrementalEvlEmfBenchmarkConfigBuilder disposeAfterExecution(boolean disposeAfterExecution) {
		this.disposeAfterExecution = disposeAfterExecution;
		return this;
	}
}
