package hu.bme.mit.trainbenchmark.benchmark.epsilonapi.queries;

import hu.bme.mit.trainbenchmark.benchmark.epsilonapi.driver.EpsilonDriver;
import hu.bme.mit.trainbenchmark.benchmark.epsilonapi.matches.EpsilonApiConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.benchmark.epsilonapi.matches.EpsilonMatch;
import hu.bme.mit.trainbenchmark.benchmark.epsilonapi.util.EpsilonApiUtil;
import hu.bme.mit.trainbenchmark.benchmark.epsilonapi.util.EpsilonMatchFactory;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.constants.TrainBenchmarkConstants;
import org.eclipse.epsilon.engine.standalone.EpsilonStandaloneEngineFactory;
import org.eclipse.epsilon.engine.standalone.evl.EvlStandaloneEngine;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class EpsilonApiEvlQuery<TPatternMatch extends EpsilonMatch, TDriver extends EpsilonDriver>
		extends ModelQuery<TPatternMatch, TDriver> {

	private static final Logger logger = LoggerFactory.getLogger(EpsilonApiEvlQuery.class);

	private EpsilonStandaloneEngineFactory factory;
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
		logger.info("Start evaluation");
		final List<TPatternMatch> matches = new ArrayList<>();
		InputStream resource = EpsilonApiEvlQuery.class.getResourceAsStream(evlScriptName);
		File resourceFile = EpsilonApiUtil.getResourceAsFile(resource);
		if (resourceFile == null) {
			logger.error("Error creating file for EVLScript resource " + evlScriptName + ". Resource was " + resource);
			throw new IllegalStateException("Error creating file for EVLScript resource");
		}
		logger.info("Creating engine");
		EvlStandaloneEngine engine = factory.getEngine();
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
