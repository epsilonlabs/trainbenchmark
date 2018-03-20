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

import hu.bme.mit.trainbenchmark.benchmark.matches.SwitchSetInjectMatch;

/**
 * The Class EpsilonApiSwitchSetInjectMatch.
 */
public class EpsilonApiSwitchSetInjectMatch extends EpsilonMatch implements SwitchSetInjectMatch {

	/** The Constant FIELDS. */
	private final static String[] FIELDS = {"sw"};

	/**
	 * Instantiates a new epsilon api switch set inject match.
	 *
	 * @param sw the sw
	 */
	public EpsilonApiSwitchSetInjectMatch(Object sw) {
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
