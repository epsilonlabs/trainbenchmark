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
import org.eclipse.epsilon.engine.standalone.evl.EvlStandaloneEngine;
import org.eclipse.epsilon.engine.standalone.model.IModelBuilder;

/**
 * The Class EpsilonEvlEmfBenchmarkConfig.
 */
public class EpsilonEvlEmfBenchmarkConfig extends BenchmarkConfig {

	private final EvlStandaloneEngine evlStandaloneEngine;
	private final IModelBuilder modelBuilder;
	private final String postfix;
	private final boolean disposeAfterExecution;

	public EpsilonEvlEmfBenchmarkConfig(BenchmarkConfigBase configBase,
										EvlStandaloneEngine engine, IModelBuilder modelBuilder, String postfix, boolean disposeAfterExecution) {
		super(configBase);
		this.evlStandaloneEngine = engine;
		this.modelBuilder = modelBuilder;
		this.postfix = postfix;
		this.disposeAfterExecution = disposeAfterExecution;
	}

	@Override
	public String getToolName() {
		return String.format("Epsilon %s-%s", evlStandaloneEngine.getName(), modelBuilder.getName());
	}

	@Override
	public String getProjectName() {
		return "epsilon";
	}


	public IModelBuilder getModelBuilder() {
		return modelBuilder;
	}

	public EvlStandaloneEngine getEvlStandaloneEngine() {
		return evlStandaloneEngine;
	}

	public String getPostfix() {
		return postfix;
	}

	public boolean isDisposeAfterExecution() {
		return disposeAfterExecution;
	}

}
