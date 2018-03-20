package hu.bme.mit.trainbenchmark.benchmark.epsilon.driver;

import hu.bme.mit.trainbenchmark.benchmark.driver.DriverFactory;
import hu.bme.mit.trainbenchmark.benchmark.epsilon.config.IEpsilonEvlBenchmarkConfig;
import org.eclipse.epsilon.engine.standalone.model.IModelBuilder;

public class EpsilonDriverFactory extends DriverFactory<EpsilonDriver> {

	private IModelBuilder modelBuilder;
	private String postfix;

	public EpsilonDriverFactory(IEpsilonEvlBenchmarkConfig bc) {
		this.modelBuilder = bc.getModelBuilder();
		this.postfix =bc.getPostfix();
	}


	@Override
	public EpsilonDriver createInstance() throws Exception {
		return new EpsilonDriver(modelBuilder, postfix);
	}

}
