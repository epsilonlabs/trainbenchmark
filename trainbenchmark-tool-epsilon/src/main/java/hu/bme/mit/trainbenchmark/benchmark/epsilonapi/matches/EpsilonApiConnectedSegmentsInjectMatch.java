package hu.bme.mit.trainbenchmark.benchmark.epsilonapi.matches;

import hu.bme.mit.trainbenchmark.benchmark.matches.ConnectedSegmentsInjectMatch;

public class EpsilonApiConnectedSegmentsInjectMatch  extends EpsilonMatch implements ConnectedSegmentsInjectMatch {
	
	/** The Constant FIELDS. */
	private final static String[] FIELDS = {"sensor", "segment1","segment3"};

	
	public EpsilonApiConnectedSegmentsInjectMatch(Object sensor, Object segment1, Object segment3) {
		data.put(FIELDS[0], sensor);
		data.put(FIELDS[1], segment1);
		data.put(FIELDS[2], segment3);
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
	public Object getSegment3() {
		return data.get(FIELDS[2]);
	}

}
