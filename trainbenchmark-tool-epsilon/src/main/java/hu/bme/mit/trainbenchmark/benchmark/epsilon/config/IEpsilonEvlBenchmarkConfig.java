package hu.bme.mit.trainbenchmark.benchmark.epsilon.config;

import hu.bme.mit.trainbenchmark.benchmark.epsilon.util.EngineFinder;
import org.eclipse.epsilon.engine.standalone.EpsilonStandaloneEngineFactory;
import org.eclipse.epsilon.engine.standalone.evl.EvlStandaloneEngine;
import org.eclipse.epsilon.engine.standalone.evl.IEvlStandaloneEngine;
import org.eclipse.epsilon.engine.standalone.model.IModelBuilder;

public interface IEpsilonEvlBenchmarkConfig<TEngineFinder> {

	IModelBuilder getModelBuilder();

	TEngineFinder getEngineFinder();

	String getPostfix();

	boolean isDisposeAfterExecution();
}
