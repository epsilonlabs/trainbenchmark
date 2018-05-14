package hu.bme.mit.trainbenchmark.benchmark.epsilon.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBase;
import hu.bme.mit.trainbenchmark.benchmark.epsilon.util.EngineFinder;
import org.eclipse.epsilon.engine.standalone.EpsilonStandaloneEngineFactory;
import org.eclipse.epsilon.engine.standalone.IEpsilonStandaloneEngine;
import org.eclipse.epsilon.engine.standalone.evl.IEvlStandaloneEngine;
import org.eclipse.epsilon.standalone.engine.EpsilonIncrementalStandaloneEngineFactory;
import org.eclipse.epsilon.engine.standalone.model.IModelBuilder;

import java.io.Serializable;

public class EpsilonIncrementalEvlEmfBenchmarkConfig extends BenchmarkConfig implements
	IEpsilonEvlBenchmarkConfig<EngineFinder<IEvlStandaloneEngine, EpsilonIncrementalStandaloneEngineFactory>>,
	Serializable {

	private static final long serialVersionUID = 8002948061559882836L;
	private final IModelBuilder modelBuilder;
	private final String postfix;
	private final boolean disposeAfterExecution;
	private final EngineFinder<IEvlStandaloneEngine, EpsilonIncrementalStandaloneEngineFactory> engineFinder;

	public EpsilonIncrementalEvlEmfBenchmarkConfig(BenchmarkConfigBase configBase,
												   IModelBuilder modelBuilder,
												   String postfix,
												   boolean disposeAfterExecution,
												   EngineFinder<IEvlStandaloneEngine, EpsilonIncrementalStandaloneEngineFactory> engineFinder) {
		super(configBase);
		this.modelBuilder = modelBuilder;
		this.postfix = postfix;
		this.disposeAfterExecution = disposeAfterExecution;
		this.engineFinder = engineFinder;
	}

	@Override
	public String getToolName() {
		return String.format("Epsilon iEVL-%s", modelBuilder.getName());
	}

	@Override
	public String getProjectName() {
		return "epsilon-ievl-emf";
	}


	@Override
	public IModelBuilder getModelBuilder() {
		return modelBuilder;
	}

	@Override
	public String getPostfix() {
		return postfix;
	}

	@Override
	public boolean isDisposeAfterExecution() {
		return disposeAfterExecution;
	}

	@Override
	public EngineFinder<IEvlStandaloneEngine, EpsilonIncrementalStandaloneEngineFactory> getEngineFinder() {
		return engineFinder;
	}
}
