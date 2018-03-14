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

import hu.bme.mit.trainbenchmark.benchmark.matches.ConnectedSegmentsMatch;

/**
 * The Class EpsilonApiConnectedSegmentMatch.
 */
public class EpsilonApiConnectedSegmentsMatch extends EpsilonMatch implements ConnectedSegmentsMatch {
	
	/** The Constant FIELDS. */
	private final static String[] FIELDS = {"sensor", "segment1","segment2","segment3","segment4","segment5","segment6"};

	/**
	 * Instantiates a new epsilon api connected segment match.
	 *
	 * @param sensor the sensor
	 * @param segment1 the segment 1
	 * @param segment2 the segment 2
	 * @param segment3 the segment 3
	 * @param segment4 the segment 4
	 * @param segment5 the segment 5
	 * @param segment6 the segment 6
	 */
	public EpsilonApiConnectedSegmentsMatch(Object sensor, Object segment1, Object segment2, Object segment3,
			Object segment4, Object segment5, Object segment6) {
		data.put(FIELDS[0], sensor);
		data.put(FIELDS[1], segment1);
		data.put(FIELDS[2], segment2);
		data.put(FIELDS[3], segment3);
		data.put(FIELDS[4], segment4);
		data.put(FIELDS[5], segment5);
		data.put(FIELDS[6], segment6);
		
	}
	
	@Override
	public String[] getFields() {
		return FIELDS;
	}

	@Override
	public Object getSensor() {
		return data.get(FIELDS[0]);
	}

	@Override
	public Object getSegment1() {
		return data.get(FIELDS[1]);
	}

	@Override
	public Object getSegment2() {
		return data.get(FIELDS[2]);
	}

	@Override
	public Object getSegment3() {
		return data.get(FIELDS[3]);
	}

	@Override
	public Object getSegment4() {
		return data.get(FIELDS[4]);
	}

	@Override
	public Object getSegment5() {
		return data.get(FIELDS[5]);
	}

	@Override
	public Object getSegment6() {
		return data.get(FIELDS[6]);
	}

}
