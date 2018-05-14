package org.eclipse.epsilon.standalone.engine;

import org.eclipse.epsilon.engine.standalone.EpsilonStandaloneEngineFactory;
import org.eclipse.epsilon.engine.standalone.IEpsilonStandaloneEngine;

public class EpsilonIncrementalStandaloneEngineFactory
	extends EpsilonStandaloneEngineFactory {

	public <E extends IEpsilonStandaloneEngine> E getEngine(String engineName) {

		switch (engineName) {
			case "iEVL": {
				return (E) new IncrementalEvlStandaloneEngine();
			}
			default: return super.getEngine(engineName);
		}
	}
}
