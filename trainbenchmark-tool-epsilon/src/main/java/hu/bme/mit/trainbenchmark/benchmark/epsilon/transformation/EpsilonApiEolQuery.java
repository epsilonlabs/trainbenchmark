package hu.bme.mit.trainbenchmark.benchmark.epsilon.transformation;

import hu.bme.mit.trainbenchmark.benchmark.epsilon.driver.EpsilonDriver;
import hu.bme.mit.trainbenchmark.benchmark.epsilon.matches.EpsilonMatch;
import hu.bme.mit.trainbenchmark.benchmark.epsilon.util.EpsilonApiUtil;
import hu.bme.mit.trainbenchmark.benchmark.epsilon.util.EpsilonMatchFactory;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import org.eclipse.epsilon.engine.standalone.EpsilonStandaloneEngineFactory;
import org.eclipse.epsilon.engine.standalone.eol.EolStandaloneEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EpsilonApiEolQuery<TPatternMatch extends EpsilonMatch, TDriver extends EpsilonDriver>
	extends ModelQuery<TPatternMatch, TDriver> {

	private static final Logger logger = LoggerFactory.getLogger(EpsilonApiEolQuery.class);

	private final String eolScriptName;
	private final String operationName;


	public EpsilonApiEolQuery(RailwayQuery query, TDriver driver, String eolScriptName, String operationName) {
		super(query, driver);
		this.eolScriptName = eolScriptName;
		this.operationName = operationName;
	}

	@Override
	public Collection<TPatternMatch> evaluate() throws Exception {
		logger.info("Start evaluation");
		final List<TPatternMatch> matches = new ArrayList<>();
		InputStream resource = EpsilonApiEolQuery.class.getResourceAsStream(eolScriptName);
		File resourceFile = EpsilonApiUtil.getResourceAsFile(resource);
		if (resourceFile == null) {
			logger.error("Error creating file for EOLScript resource " + eolScriptName);
			throw new IllegalStateException("Error creating file for EOLScript resource");
		}
		logger.info("Creating engine");
		EolStandaloneEngine engine = new EpsilonStandaloneEngineFactory().getEngine("EOL");
		engine.addNativeDelegate(new EpsilonMatchFactory());
		engine.setScript(resourceFile.toPath());
		engine.addModel(getDriver().getModel());
		engine.setOperationName(operationName);
		engine.setDisposeModels(false);
		logger.info("Executing");
		engine.execute();
		Collection<? extends TPatternMatch> result = (Collection<? extends TPatternMatch>) engine.getResult();
		logger.info("The Eol Query returned " + result.size() + " matches.");
		matches.addAll(result);
		engine.dispose();
		return matches;
	}
}
