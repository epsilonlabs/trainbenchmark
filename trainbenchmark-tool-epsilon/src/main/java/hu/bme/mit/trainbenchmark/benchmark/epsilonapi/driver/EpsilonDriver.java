package hu.bme.mit.trainbenchmark.benchmark.epsilonapi.driver;

import hu.bme.mit.trainbenchmark.benchmark.driver.Driver;
import org.eclipse.epsilon.engine.standalone.model.IModelBuilder;
import org.eclipse.epsilon.eol.models.IModel;

import java.nio.file.Paths;

public class EpsilonDriver<T extends IModelBuilder> extends Driver {

	private final T modelBuilder;
	private final String postfix;
	private IModel model;

	public EpsilonDriver(T modelBuilder, String postfix) {
		this.modelBuilder = modelBuilder;
		this.postfix = postfix;
	}


	public IModel getModel() {
		return model;
	}

	@Override
	public void read(String modelPath) throws Exception {
		System.out.println("EpsilonDriver read");
		modelBuilder
			.withName("train")
			.withModelUri(Paths.get(modelPath));
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
