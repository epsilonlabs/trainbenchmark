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

import org.eclipse.epsilon.engine.standalone.evl.EvlStandaloneEngine;
import org.eclipse.epsilon.engine.standalone.model.EmfModelBuilder;
import org.eclipse.epsilon.engine.standalone.model.IModelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * The Class AbstractEpsilonBenchmarkConfigBuilder. Allows using the same builder to be used to
 * benchmark different EVL implementations. It also allows a specific Epsilon EMC driver to
 * be used to for the benchmark.
 */
public class EpsilonEvlEmfBenchmarkConfigBuilder extends AbstractEpsilonBenchmarkConfigBuilder<EpsilonEvlEmfBenchmarkConfig> {

	private static final Logger logger = LoggerFactory.getLogger(EpsilonEvlEmfBenchmarkConfigBuilder.class);

	private IModelBuilder modelBuilder;
	private EvlStandaloneEngine evlStandaloneEngine;
	private String postfix;
	private boolean disposeAfterExecution;

	@Override
	public EpsilonEvlEmfBenchmarkConfig createConfig() {
		checkNotNulls();
		return new EpsilonEvlEmfBenchmarkConfig(configBase, evlStandaloneEngine, modelBuilder, postfix, disposeAfterExecution);
	}

	/**
	 * Provide the EpsilonStandaloneEngineFactory that is used to create the EVL module
	 * @param evlStandaloneEngine
	 */
	public AbstractEpsilonBenchmarkConfigBuilder withEvlEngine(EvlStandaloneEngine evlStandaloneEngine) {
		this.evlStandaloneEngine = evlStandaloneEngine;
		return this;
	}

	/**
	 * Use the EmfModelBuilder
	 */
	public AbstractEpsilonBenchmarkConfigBuilder withEmfModel() {
		Path metamodelFile = Paths.get("../trainbenchmark-format-emf-model/src/railway.ecore");
		try {
			this.modelBuilder = new EmfModelBuilder().withMetamodelPath(Paths.get(metamodelFile.toFile().getCanonicalPath()));
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
	public AbstractEpsilonBenchmarkConfigBuilder disposeAfterExecution(boolean disposeAfterExecution) {
		this.disposeAfterExecution = disposeAfterExecution;
		return this;
	}
}
