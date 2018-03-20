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
package hu.bme.mit.trainbenchmark.benchmark.epsilon.matches;

import hu.bme.mit.trainbenchmark.benchmark.matches.SwitchSetMatch;

/**
 * The Class EpsilonApiSetMonitoredMatch.
 */
public class EpsilonApiSwitchSetMatch extends EpsilonMatch implements SwitchSetMatch {

	/** The Constant FIELDS. */
	private final static String[] FIELDS = {"semaphore", "route", "swP", "sw"};

	/**
	 * Instantiates a new epsilon api set monitored match.
	 * @param semaphore
	 * @param route
	 * @param swP
	 * @param sw
	 */
	public EpsilonApiSwitchSetMatch(Object semaphore, Object route, Object swP, Object sw) {
		data.put(FIELDS[0], semaphore);
		data.put(FIELDS[1], route);
		data.put(FIELDS[2], swP);
		data.put(FIELDS[3], sw);
	}

	@Override
	public String[] getFields() {
		return FIELDS;
	}

	@Override
	public Object getSemaphore() {
		return data.get(FIELDS[0]);
	}

	@Override
	public Object getRoute() {
		return data.get(FIELDS[1]);
	}

	@Override
	public Object getSwP() {
		return data.get(FIELDS[2]);
	}

	@Override
	public Object getSw() {
		return data.get(FIELDS[3]);
	}

}
