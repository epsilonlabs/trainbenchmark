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

import hu.bme.mit.trainbenchmark.benchmark.matches.SemaphoreNeighborMatch;

/**
 * The Class EpsilonApiSemaphoreNeighborMatch.
 */
public class EpsilonApiSemaphoreNeighborMatch extends EpsilonMatch implements SemaphoreNeighborMatch {

	/** The Constant FIELDS. */
	private final static String[] FIELDS = {"semaphore", "route1","route2","sensor1","sensor2","te1","te2"};

	/**
	 * Instantiates a new epsilon api semaphore neighbor match.
	 *
	 * @param semaphore the semaphore
	 * @param route1 the route 1
	 * @param route2 the route 2
	 * @param sensor1 the sensor 1
	 * @param sensor2 the sensor 2
	 * @param te1 the te 1
	 * @param te2 the te 2
	 */
	public EpsilonApiSemaphoreNeighborMatch(Object semaphore, Object route1, Object route2, Object sensor1,
			Object sensor2, Object te1, Object te2) {
		data.put(FIELDS[0], semaphore);
		data.put(FIELDS[1], route1);
		data.put(FIELDS[2], route2);
		data.put(FIELDS[3], sensor1);
		data.put(FIELDS[4], sensor2);
		data.put(FIELDS[5], te1);
		data.put(FIELDS[6], te2);
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
	public Object getRoute1() {
		return data.get(FIELDS[1]);
	}

	@Override
	public Object getRoute2() {
		return data.get(FIELDS[2]);
	}

	@Override
	public Object getSensor1() {
		return data.get(FIELDS[3]);
	}

	@Override
	public Object getSensor2() {
		return data.get(FIELDS[4]);
	}

	@Override
	public Object getTe1() {
		return data.get(FIELDS[5]);
	}

	@Override
	public Object getTe2() {
		return data.get(FIELDS[6]);
	}

}
