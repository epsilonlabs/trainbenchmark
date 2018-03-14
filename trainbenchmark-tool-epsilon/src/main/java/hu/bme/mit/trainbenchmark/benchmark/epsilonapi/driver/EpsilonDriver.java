package hu.bme.mit.trainbenchmark.benchmark.epsilonapi.driver;

import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import org.eclipse.epsilon.engine.standalone.model.IModelBuilder;
import org.eclipse.epsilon.eol.models.IModel;

public class EpsilonDriver extends Driver {

	private final IModelBuilder modelBuilder;
	private final String postfix;
	protected IModel model;

	public EpsilonDriver(IModelBuilder modelBuilder, String postfix) {
		this.modelBuilder = modelBuilder;
		this.postfix = postfix;
	}

	public IModel getModel() {
		return model;
	}

	@Override
	public void read(String modelPath) throws Exception {
		modelBuilder
			.withName("train")
			.withModelUri(modelPath);
		model = modelBuilder.build();
		model.load();
	}

	@Override
	public String getPostfix() {
		return this.postfix;
	}

	@Override
	public Number generateNewVertexId() throws Exception {
		// TODO Implement EpsilonDriver.generateNewVertexId
		throw new UnsupportedOperationException("Unimplemented Method    EpsilonDriver.generateNewVertexId invoked.");
	}

}
