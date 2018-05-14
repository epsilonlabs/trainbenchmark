/*******************************************************************************
 * Copyright (c) 2008 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Dimitrios Kolovos - initial API and implementation
 *     Horacio Hoyos Rodriguez - improved API and functionality
 ******************************************************************************/
package org.eclipse.epsilon.standalone.engine;


import org.eclipse.epsilon.engine.standalone.AbstractStandaloneEngine;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.evl.IncrementalEvlModule;
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;
import org.eclipse.epsilon.standalone.InMemoryEvlTraceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * The Incremental EVL Standalone engine
 * @author Horacio Hoyos Rodriguez
 */
public class IncrementalEvlStandaloneEngine extends AbstractStandaloneEngine<IncrementalEvlModule>
	implements IIncrementalEvlStandaloneEngine<IncrementalEvlModule> {

	private static final Logger logger = LoggerFactory.getLogger(IncrementalEvlStandaloneEngine.class);

	private Collection<UnsatisfiedConstraint> unsatisfiedConstraints;
	private boolean online;

	public IncrementalEvlStandaloneEngine() {
        super(new IncrementalEvlModule());
        logger.info("Creating the IncrementalEvlStandaloneEngine");
    }

	@Override
	public void preProcess() {
		logger.info("Setting trace manager.");
		module.getContext().setTraceManager(new InMemoryEvlTraceManager());
		module.setOnlineExecution(online);
	}

	@Override
    protected Object execute(IncrementalEvlModule module) throws EolRuntimeException {
        logger.info("Executing EVL script.");
        return module.execute();
    }

	@Override
	public void postProcess() {
		logger.info("Retrieving unsatisfied Constraints.");
		unsatisfiedConstraints = module.getContext().getUnsatisfiedConstraints();
	}

	@Override
	public void dispose() {
		logger.info("Disposing engine.");
		if (!module.isLive()) {
			super.dispose();
		}
		unsatisfiedConstraints.clear();
	}

	@Override
	public Collection<UnsatisfiedConstraint> getUnsatisfiedConstraints() {
		return unsatisfiedConstraints;
	}

	@Override
	public void printResult() {
		if (unsatisfiedConstraints.size() > 0) {
			logger.info("There where {} unsatisfied Constraints.", unsatisfiedConstraints.size() );
			for (UnsatisfiedConstraint uc : unsatisfiedConstraints) {
				logger.info(uc.getMessage());
				System.err.println(uc.getMessage());
			}
		}
		else {
			System.out.println("All constraints have been satisfied");
		}
	}

	@Override
	public boolean isOnlineExecution() {
		return online;
	}

	@Override
	public void setOnlineExecution(boolean online) {
		this.online = online;
	}
}
