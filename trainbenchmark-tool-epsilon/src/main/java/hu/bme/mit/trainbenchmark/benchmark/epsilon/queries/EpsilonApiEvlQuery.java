package hu.bme.mit.trainbenchmark.benchmark.epsilon.queries;

import hu.bme.mit.trainbenchmark.benchmark.epsilon.driver.EpsilonDriver;
import hu.bme.mit.trainbenchmark.benchmark.epsilon.matches.EpsilonMatch;
import hu.bme.mit.trainbenchmark.benchmark.epsilon.util.EpsilonApiUtil;
import hu.bme.mit.trainbenchmark.benchmark.epsilon.util.EpsilonMatchFactory;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import org.eclipse.epsilon.engine.standalone.EpsilonStandaloneEngineFactory;
import org.eclipse.epsilon.engine.standalone.evl.IEvlStandaloneEngine;
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EpsilonApiEvlQuery<TPatternMatch extends EpsilonMatch, TDriver extends EpsilonDriver>
		extends ModelQuery<TPatternMatch, TDriver> {

	private static final Logger logger = LoggerFactory.getLogger(EpsilonApiEvlQuery.class);

	private EpsilonStandaloneEngineFactory engineFactory;
	private final String evlScriptName;
	private final boolean disposeAfterExecution;
	private String engineName;

	public EpsilonApiEvlQuery(RailwayQuery query, TDriver driver, String evlScriptName,
							  EpsilonStandaloneEngineFactory engineFactory, String engineName, boolean disposeAfterExecution) {
		super(query, driver);
		this.engineFactory = engineFactory;
		this.engineName = engineName;
		this.evlScriptName = evlScriptName;
		this.disposeAfterExecution = disposeAfterExecution;
	}

	@Override
	public Collection<TPatternMatch> evaluate() throws Exception {
		logger.info("Start evaluation");
		final List<TPatternMatch> matches = new ArrayList<>();
		InputStream resource = EpsilonApiEvlQuery.class.getResourceAsStream(evlScriptName);
		File resourceFile = EpsilonApiUtil.getResourceAsFile(resource);
		if (resourceFile == null) {
			logger.error("Error creating file for EVLScript resource " + evlScriptName + ". Resource was " + resource);
			throw new IllegalStateException("Error creating file for EVLScript resource");
		}
		logger.info("Creating engine");
		IEvlStandaloneEngine engine = engineFactory.getEngine(engineName);
		engine.addNativeDelegate(new EpsilonMatchFactory());
		engine.setScript(resourceFile.toPath());
		engine.addModel(getDriver().getModel());
		engine.setDisposeModels(false);
		logger.info("Executing");
		engine.execute();
		Collection<UnsatisfiedConstraint> result = engine.getUnsatisfiedConstraints();
		logger.info("The Evl Query returned " + result.size() + " matches.");
		for (UnsatisfiedConstraint unsatisfiedConstraint : result) {
			Object instance = unsatisfiedConstraint.getInstance();
			logger.debug("Element " + instance + " did not satisfy " + unsatisfiedConstraint.getConstraint().getName());
			Object match = engine.getModule().getContext().getExtendedProperties().getPropertyValue(instance, "match");
			logger.debug("Match created");
			if (match instanceof Collection) {
				matches.addAll((Collection<? extends TPatternMatch>) match);
			}
			else {
				matches.add((TPatternMatch) match);
			}
		}
		// We NEED to dispose the engine since it will be reused. However for incremental, we need it to
		// remain active so it works incrementally. Perhaps what we need is a flag in the incremental
		// so dispose is a "soft" dispose, which will be also used when execute() (and other setter methods )
		// are reinvoked while live, so they don't change anything.
		//if (disposeAfterExecution) {
			//
		engine.dispose();
		//}
		return matches;
	}


}
