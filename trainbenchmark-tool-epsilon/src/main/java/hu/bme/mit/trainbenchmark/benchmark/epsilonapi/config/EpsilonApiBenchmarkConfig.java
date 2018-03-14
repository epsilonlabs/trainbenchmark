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
package hu.bme.mit.trainbenchmark.benchmark.epsilonapi.config;

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfig;
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBase;
import org.eclipse.epsilon.engine.standalone.EpsilonStandaloneEngineFactory;
import org.eclipse.epsilon.engine.standalone.evl.EvlStandaloneEngine;
import org.eclipse.epsilon.engine.standalone.model.IModelBuilder;

/**
 * The Class EpsilonApiBenchmarkConfig.
 */
public class EpsilonApiBenchmarkConfig extends BenchmarkConfig {

	private final EpsilonStandaloneEngineFactory engineFactory;
	private final IModelBuilder modelBuilder;
	private final String postfix;
	private final boolean disposeAfterExecution;

	public EpsilonApiBenchmarkConfig(BenchmarkConfigBase configBase,
									 EpsilonStandaloneEngineFactory engine, IModelBuilder modelBuilder, String postfix, boolean disposeAfterExecution) {
		super(configBase);
		this.engineFactory = engine;
		this.modelBuilder = modelBuilder;
		this.postfix = postfix;
		this.disposeAfterExecution = disposeAfterExecution;
	}

	@Override
	public String getToolName() {
		return "Epsilon";
	}

	@Override
	public String getProjectName() {
		return "epsilon";
	}


	public IModelBuilder getModelBuilder() {
		return modelBuilder;
	}

	public EpsilonStandaloneEngineFactory getEngineFactory() {
		return engineFactory;
	}

	public String getPostfix() {
		return postfix;
	}

	public boolean isDisposeAfterExecution() {
		return disposeAfterExecution;
	}

}
