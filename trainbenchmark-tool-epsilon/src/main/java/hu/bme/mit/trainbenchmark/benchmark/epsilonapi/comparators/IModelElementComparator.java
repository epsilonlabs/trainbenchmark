package hu.bme.mit.trainbenchmark.benchmark.epsilonapi.comparators;

import org.eclipse.epsilon.eol.models.IModel;

import java.util.Comparator;


public class IModelElementComparator implements Comparator<Object> {

	private IModel model;

	public IModel getModel() {
		return model;
	}

	public void setModel(IModel model) {
		this.model = model;
	}

	@Override
	public int compare(Object o1, Object o2) {
		// FIXME!!
		return 0;
	}

}
