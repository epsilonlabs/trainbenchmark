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
import org.eclipse.epsilon.engine.standalone.model.EmfModelBuilder;
import org.eclipse.epsilon.engine.standalone.model.IModelBuilder;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


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
	public EpsilonApiBenchmarkConfigBuilder withEvlFactory(String engineFactory) {
		this.engineFactory = EpsilonStandaloneEngineFactory.valueOf(engineFactory);
		return this;
	}

	/**
	 * Use the EmfModelBuilder
	 */
	public EpsilonApiBenchmarkConfigBuilder withEmfModel() {
		Path metamodelFile = Paths.get("../trainbenchmark-format-emf-model/src/railway.ecore");
		try {
			this.modelBuilder = new EmfModelBuilder().withMetamodelPath(Paths.get(metamodelFile.toFile().getCanonicalPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		postfix = ".xmi";
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
