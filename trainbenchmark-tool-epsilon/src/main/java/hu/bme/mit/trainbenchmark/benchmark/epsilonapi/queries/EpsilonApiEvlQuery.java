package hu.bme.mit.trainbenchmark.benchmark.epsilonapi.queries;

import hu.bme.mit.trainbenchmark.benchmark.epsilonapi.driver.EpsilonDriver;
import hu.bme.mit.trainbenchmark.benchmark.epsilonapi.matches.EpsilonApiConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.epsilonapi.matches.EpsilonMatch;
import hu.bme.mit.trainbenchmark.benchmark.epsilonapi.util.EpsilonApiUtil;
import hu.bme.mit.trainbenchmark.benchmark.epsilonapi.util.EpsilonMatchFactory;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import org.eclipse.epsilon.engine.standalone.EpsilonStandaloneEngineFactory;
import org.eclipse.epsilon.engine.standalone.evl.EvlStandaloneEngine;
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EpsilonApiEvlQuery<TPatternMatch extends EpsilonMatch, TDriver extends EpsilonDriver>
		extends ModelQuery<TPatternMatch, TDriver> {

	private static final Logger logger = LoggerFactory.getLogger(EpsilonApiEvlQuery.class);

	private EpsilonStandaloneEngineFactory factory;
	private EvlStandaloneEngine engine;
	private final String evlScriptName;
	private final boolean disposeAfterExecution;

	public EpsilonApiEvlQuery(RailwayQuery query, TDriver driver,
							  EpsilonStandaloneEngineFactory factory, String evlScriptName, boolean disposeAfterExecution) {
		super(query, driver);
		this.factory = factory;
		this.evlScriptName = evlScriptName;
		this.disposeAfterExecution = disposeAfterExecution;
	}

	@Override
	public Collection<TPatternMatch> evaluate() throws Exception {
		logger.info("Evaluating");
		final List<TPatternMatch> matches = new ArrayList<>();
		InputStream resource = EpsilonApiEvlQuery.class.getResourceAsStream(evlScriptName);
		File resourceFile = EpsilonApiUtil.getResourceAsFile(resource);
		if (resourceFile == null) {
			logger.error("Error creating file for EVLScript resource " + evlScriptName + ". Resource was " + resource);
			throw new IllegalStateException("Error creating file for EVLScript resource");
		}
		logger.info("Creating engine");
		engine = factory.getEngine();
		engine.addNativeDelegate(new EpsilonMatchFactory());
		engine.setScript(resourceFile.toPath());
		engine.addModel(getDriver().getModel());
		logger.info("Executing");
		engine.execute();
		for (UnsatisfiedConstraint unsatisfiedConstraint : engine.getUnsatisfiedConstraints()) {
			Object instance = unsatisfiedConstraint.getInstance();
			System.out.println(instance);
			Object match = engine.getModule().getContext().getExtendedProperties().getPropertyValue(instance, "match");
			System.out.println(match);
			matches.add((TPatternMatch) match);
		}
		if (disposeAfterExecution) {
			engine.dispose();
		}
		return matches;
	}

	public void dispose() {
		if (engine != null) {
			engine.dispose();
		}
	}

}
