package hu.bme.mit.trainbenchmark.benchmark.epsilon.util;

import org.eclipse.epsilon.engine.standalone.EpsilonStandaloneEngineFactory;
import org.eclipse.epsilon.engine.standalone.IEpsilonStandaloneEngine;
import org.eclipse.epsilon.standalone.engine.IIncrementalEvlStandaloneEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class EngineFinder<TEngine extends IEpsilonStandaloneEngine, TEngineFactory extends EpsilonStandaloneEngineFactory>  {

	private static final Logger logger = LoggerFactory.getLogger(EngineFinder.class);

	private final boolean incremental;

	private final TEngineFactory engineFactory;

	private final String engineName;

	private Map<String, TEngine> cache;

	public EngineFinder(boolean incremental, TEngineFactory engineFactory, String engineName) {
		this.incremental = incremental;
		this.engineFactory = engineFactory;
		this.engineName = engineName;
		if (incremental) {
			cache = new HashMap<>();
		}
	}

	public TEngine getEngine(String scenario, String evlScriptLocation) {
		logger.info("Get EVL engine");
		if (incremental) {
			TEngine engine = cache.get(scenario);
			if (engine == null) {
				logger.info("Creating new EVL engine");
				engine = engineFactory.getEngine(engineName);
				((IIncrementalEvlStandaloneEngine)engine).setOnlineExecution(true);
				configureEngine(engine, evlScriptLocation);
				cache.put(scenario, engine);
			}
			return engine;
		} else {
			TEngine engine = engineFactory.getEngine(engineName);
			configureEngine(engine, evlScriptLocation);
			return engine;
		}
	}

	private void configureEngine(TEngine engine, String evlScriptLocation) {
		logger.info("Configure engine");
		InputStream resource = getClass().getResourceAsStream(evlScriptLocation);
		File resourceFile = EpsilonApiUtil.getResourceAsFile(resource);
		if (resourceFile == null) {
			logger.error("Error creating file for EVLScript resource " + evlScriptLocation + ". Resource was " + resource);
			throw new IllegalStateException("Error creating file for EVLScript resource");
		}
		engine.addNativeDelegate(new EpsilonMatchFactory());
		engine.setScript(resourceFile.toPath());
		engine.setDisposeModels(false);
	}


}
