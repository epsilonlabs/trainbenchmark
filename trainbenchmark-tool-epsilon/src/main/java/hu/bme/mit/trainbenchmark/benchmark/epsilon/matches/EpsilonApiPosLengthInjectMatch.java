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

import hu.bme.mit.trainbenchmark.benchmark.matches.PosLengthInjectMatch;

/**
 * The Class EpsilonApiPosLengthInjectMatch.
 */
public class EpsilonApiPosLengthInjectMatch extends EpsilonMatch implements PosLengthInjectMatch {

	/** The Constant FIELDS. */
	private final static String[] FIELDS = {"segment"};

	/**
	 * Instantiates a new epsilon api pos length match.
	 *
	 * @param segment the segment
	 */
	public EpsilonApiPosLengthInjectMatch(Object segment) {
		data.put(FIELDS[0], segment);
	}

	@Override
	public String[] getFields() {
		return FIELDS;
	}

	@Override
	public Object getSegment() {
		return data.get(FIELDS[0]);
	}


}
