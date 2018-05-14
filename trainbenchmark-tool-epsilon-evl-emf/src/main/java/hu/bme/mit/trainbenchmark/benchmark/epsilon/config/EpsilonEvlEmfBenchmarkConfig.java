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
import hu.bme.mit.trainbenchmark.benchmark.epsilon.util.EngineFinder;
import org.eclipse.epsilon.engine.standalone.EpsilonStandaloneEngineFactory;
import org.eclipse.epsilon.engine.standalone.evl.IEvlStandaloneEngine;
import org.eclipse.epsilon.engine.standalone.model.IModelBuilder;

import java.io.Serializable;

/**
 * The Class EpsilonEvlEmfBenchmarkConfig.
 */
public class EpsilonEvlEmfBenchmarkConfig extends BenchmarkConfig implements
	IEpsilonEvlBenchmarkConfig<EngineFinder<IEvlStandaloneEngine, EpsilonStandaloneEngineFactory>>,
	Serializable {


	private static final long serialVersionUID = 7869345265666795529L;
	private final IModelBuilder modelBuilder;
	private final String postfix;
	private final boolean disposeAfterExecution;
	private final EngineFinder<IEvlStandaloneEngine, EpsilonStandaloneEngineFactory> engineFinder;

	public EpsilonEvlEmfBenchmarkConfig(BenchmarkConfigBase configBase,
										IModelBuilder modelBuilder,
										String postfix,
										boolean disposeAfterExecution,
										EngineFinder<IEvlStandaloneEngine, EpsilonStandaloneEngineFactory> engineFinder) {
		super(configBase);
		this.modelBuilder = modelBuilder;
		this.postfix = postfix;
		this.disposeAfterExecution = disposeAfterExecution;
		this.engineFinder = engineFinder;
	}

	@Override
	public String getToolName() {
		return String.format("Epsilon EVL-%s", modelBuilder.getName());
	}

	@Override
	public String getProjectName() {
		return "epsilon-evl-emf";
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
	public EngineFinder<IEvlStandaloneEngine, EpsilonStandaloneEngineFactory> getEngineFinder() {
		return engineFinder;
	}


}
