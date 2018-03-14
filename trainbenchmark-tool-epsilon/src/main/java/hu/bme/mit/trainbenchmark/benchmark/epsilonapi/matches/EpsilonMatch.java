package hu.bme.mit.trainbenchmark.benchmark.epsilonapi.matches;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import hu.bme.mit.trainbenchmark.benchmark.matches.BaseMatch;

public abstract class EpsilonMatch extends BaseMatch {
	
	protected Map<String, Object> data = new HashMap<>();
	
	abstract public String[] getFields();

	@Override
	public String toString() {
		return "EpsilonMatch [match=" + Arrays.toString(toArray()) + "]";
	}
	
	@Override
	public Object[] toArray() {
		Object[] result = new Object[getFields().length];
		for (int i=0; i < getFields().length; i++) {
			result[i] = data.get(getFields()[i]);
		}
		return result;
	}
	
	

}
