/*******************************************************************************
 * Copyright (c) 2018 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Horacio Hoyos Rodriguez - initial API and implementation
 ******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.epsilon.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBase;
import org.eclipse.epsilon.engine.standalone.EpsilonStandaloneEngineFactory;
import org.eclipse.epsilon.engine.standalone.model.IModelBuilder;

/**
 * The Class EpsilonEvlEmfBenchmarkConfig.
 */
public class EpsilonEvlEmfBenchmarkConfig extends BenchmarkConfig implements IEpsilonEvlBenchmarkConfig {

	private final EpsilonStandaloneEngineFactory engineFactory;
	private final IModelBuilder modelBuilder;
	private final String postfix;
	private final boolean disposeAfterExecution;
	private String engineName;

	public EpsilonEvlEmfBenchmarkConfig(BenchmarkConfigBase configBase,
										EpsilonStandaloneEngineFactory factory, IModelBuilder modelBuilder, String postfix, String engineName, boolean disposeAfterExecution) {
		super(configBase);
		this.engineFactory = factory;
		this.modelBuilder = modelBuilder;
		this.postfix = postfix;
		this.disposeAfterExecution = disposeAfterExecution;
		this.engineName = engineName;
	}

	@Override
	public String getToolName() {
		return String.format("Epsilon EVL-%s", modelBuilder.getName());
	}

	@Override
	public String getProjectName() {
		return "epsilon";
	}


	@Override
	public IModelBuilder getModelBuilder() {
		return modelBuilder;
	}

	@Override
	public EpsilonStandaloneEngineFactory getEngineFactory() {
		return engineFactory;
	}

	@Override
	public String getPostfix() {
		return postfix;
	}

	@Override
	public boolean isDisposeAfterExecution() {
		return disposeAfterExecution;
	}

	public String getEngineName() {
		return engineName;
	}
}
