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
import hu.bme.mit.trainbenchmark.benchmark.config.BenchmarkConfigBuilder;
import hu.bme.mit.trainbenchmark.benchmark.epsilon.util.EngineFinder;
import org.eclipse.epsilon.engine.standalone.EpsilonStandaloneEngineFactory;
import org.eclipse.epsilon.engine.standalone.evl.EvlStandaloneEngine;
import org.eclipse.epsilon.engine.standalone.evl.IEvlStandaloneEngine;
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
public abstract class AbstractEpsilonBenchmarkConfigBuilder<MBenchmarkConfig extends BenchmarkConfig>
	extends BenchmarkConfigBuilder<MBenchmarkConfig, AbstractEpsilonBenchmarkConfigBuilder<MBenchmarkConfig>> {

	private static final Logger logger = LoggerFactory.getLogger(AbstractEpsilonBenchmarkConfigBuilder.class);

	protected IModelBuilder modelBuilder;
	protected String postfix;
	protected boolean disposeAfterExecution;


	/**

	public AbstractEpsilonBenchmarkConfigBuilder withEmfModel() {
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
	 *
	public AbstractEpsilonBenchmarkConfigBuilder disposeAfterExecution(boolean disposeAfterExecution) {
		this.disposeAfterExecution = disposeAfterExecution;
		return this;
	}
	*/
}
