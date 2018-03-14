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

import hu.bme.mit.trainbenchmark.benchmark.matches.SemaphoreNeighborInjectMatch;

/**
 * The Class EpsilonApiSemaphoreNeighborInjectMatch.
 */
public class EpsilonApiSemaphoreNeighborInjectMatch extends EpsilonMatch implements SemaphoreNeighborInjectMatch {
	
	/** The Constant FIELDS. */
	private final static String[] FIELDS = {"route", "semaphore"};
	
	/**
	 * Instantiates a new epsilon api semaphore neighbor inject match.
	 *
	 * @param route the route
	 * @param semaphore the semaphore
	 */
	public EpsilonApiSemaphoreNeighborInjectMatch(Object route, Object semaphore) {
		data.put(FIELDS[0], route);
		data.put(FIELDS[1], semaphore);
	}

	@Override
	public String[] getFields() {
		return FIELDS;
	}
	
	@Override
	public Object getRoute() {
		return data.get(FIELDS[0]);
	}

	@Override
	public Object getSemaphore() {
		return data.get(FIELDS[1]);
	}

}
