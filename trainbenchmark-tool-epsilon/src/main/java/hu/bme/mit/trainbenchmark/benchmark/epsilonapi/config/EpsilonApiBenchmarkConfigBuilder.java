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

import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBuilder;
import org.eclipse.epsilon.engine.standalone.EpsilonStandaloneEngineFactory;
import org.eclipse.epsilon.engine.standalone.model.IModelBuilder;


/**
 * The Class EpsilonApiBenchmarkConfigBuilder. Allows using the same builder to be used to
 * benchmark different EVL implementations. It also allows a specific Epsilon EMC driver to
 * be used to for the benchmark.
 */
public class EpsilonApiBenchmarkConfigBuilder
	extends BenchmarkConfigBuilder<EpsilonApiBenchmarkConfig, EpsilonApiBenchmarkConfigBuilder> {

	private IModelBuilder modelBuilder;
	private EpsilonStandaloneEngineFactory engineFactory;
	private String postfix;
	private boolean disposeAfterExecution;

	@Override
	public EpsilonApiBenchmarkConfig createConfig() {
		checkNotNulls();
		return new EpsilonApiBenchmarkConfig(configBase, engineFactory, modelBuilder, postfix, disposeAfterExecution);
	}

	/**
	 * Provide the EpsilonStandaloneEngineFactory that is used to create the EVL module
	 * @param engineFactory
	 */
	public EpsilonApiBenchmarkConfigBuilder withEvlFactory(EpsilonStandaloneEngineFactory engineFactory) {
		this.engineFactory = engineFactory;
		return this;
	}

	/**
	 * Provide the specific Model Builder to use
	 * @param modelBuilder
	 */
	public EpsilonApiBenchmarkConfigBuilder withModelBuilder(IModelBuilder modelBuilder) {
		this.modelBuilder = modelBuilder;
		return this;
	}

	/**
	 * Provide a postFix to identify models that can be loaded with the provided Model Builder.
	 * @param postfix
	 * @return
	 */
	public EpsilonApiBenchmarkConfigBuilder withModelPostfix(String postfix) {
		this.postfix = postfix;
		return this;
	}

	/**
	 * Determine if the EVL engine is disposed after execution. Should be false for incremental EVL.
	 * @param disposeAfterExecution
	 * @return
	 */
	public EpsilonApiBenchmarkConfigBuilder disposeAfterExecution(boolean disposeAfterExecution) {
		this.disposeAfterExecution = disposeAfterExecution;
		return this;
	}
}
