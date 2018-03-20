package hu.bme.mit.trainbenchmark.benchmark.epsilon.config;

import org.eclipse.epsilon.engine.standalone.EpsilonStandaloneEngineFactory;
import org.eclipse.epsilon.engine.standalone.evl.EvlStandaloneEngine;
import org.eclipse.epsilon.engine.standalone.model.IModelBuilder;

public interface IEpsilonEvlBenchmarkConfig {

	IModelBuilder getModelBuilder();

	EpsilonStandaloneEngineFactory getEngineFactory();

	String getPostfix();

	boolean isDisposeAfterExecution();
}
