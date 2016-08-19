package hu.bme.mit.trainbenchmark.benchmark.tinkergraph.driver;

import hu.bme.mit.trainbenchmark.benchmark.driver.DriverFactory;

public class TinkerGraphDriverFactory extends DriverFactory<TinkerGraphDriver> {

	@Override
	public TinkerGraphDriver createInstance() throws Exception {
		return new TinkerGraphDriver();
	}

}
