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
package hu.bme.mit.trainbenchmark.benchmark.epsilonapi.matches;

import hu.bme.mit.trainbenchmark.benchmark.matches.SwitchMonitoredMatch;

/**
 * The Class EpsilonApiSwitchMonitoredMatch.
 */
public class EpsilonApiSwitchMonitoredMatch extends EpsilonMatch implements SwitchMonitoredMatch {

	/** The Constant FIELDS. */
	private final static String[] FIELDS = {"sw"};
	
	/**
	 * Instantiates a new epsilon api switch monitored match.
	 *
	 * @param sw the sw
	 */
	public EpsilonApiSwitchMonitoredMatch(Object sw) {
		data.put(FIELDS[0], sw);
	}

	@Override
	public String[] getFields() {
		return FIELDS;
	}

	@Override
	public Object getSw() {
		return data.get(FIELDS[0]);
	}

}
