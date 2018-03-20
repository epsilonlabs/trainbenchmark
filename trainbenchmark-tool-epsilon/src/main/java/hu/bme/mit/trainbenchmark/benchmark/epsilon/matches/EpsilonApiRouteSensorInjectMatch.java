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

import hu.bme.mit.trainbenchmark.benchmark.matches.RouteSensorInjectMatch;

/**
 * The Class EpsilonApiRouteSensorInjectMatch.
 */
public class EpsilonApiRouteSensorInjectMatch extends EpsilonMatch implements RouteSensorInjectMatch {

	/** The Constant FIELDS. */
	private final static String[] FIELDS = {"route", "sensor"};


	/**
	 * Instantiates a new epsilon api route sensor inject match.
	 *
	 * @param route the route
	 * @param sensor the sensor
	 */
	public EpsilonApiRouteSensorInjectMatch(Object route, Object sensor) {
		data.put(FIELDS[0], route);
		data.put(FIELDS[1], sensor);
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
	public Object getSensor() {
		return data.get(FIELDS[1]);
	}

}
