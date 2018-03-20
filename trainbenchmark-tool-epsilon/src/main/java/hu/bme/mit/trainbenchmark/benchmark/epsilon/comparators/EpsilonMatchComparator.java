package hu.bme.mit.trainbenchmark.benchmark.epsilon.comparators;

import java.util.Comparator;

import hu.bme.mit.trainbenchmark.benchmark.epsilon.matches.EpsilonMatch;
import hu.bme.mit.trainbenchmark.benchmark.matches.comparators.BaseMatchComparator;

public class EpsilonMatchComparator extends BaseMatchComparator<EpsilonMatch, Object> {

	public EpsilonMatchComparator() {
		super(new IModelElementComparator());
	}

}
