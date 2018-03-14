package hu.bme.mit.trainbenchmark.benchmark.epsilonapi.transformation;

import hu.bme.mit.trainbenchmark.benchmark.epsilonapi.driver.EpsilonDriver;
import hu.bme.mit.trainbenchmark.benchmark.epsilonapi.matches.EpsilonMatch;
import hu.bme.mit.trainbenchmark.benchmark.epsilonapi.util.EpsilonApiUtil;
import hu.bme.mit.trainbenchmark.benchmark.epsilonapi.util.EpsilonMatchFactory;
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
		final List<TPatternMatch> matches = new ArrayList<>();
		InputStream resource = EpsilonApiEolQuery.class.getResourceAsStream(eolScriptName);
		File resourceFile = EpsilonApiUtil.getResourceAsFile(resource);
		if (resourceFile == null) {
			logger.error("Error creating file for EOLScript resource " + eolScriptName);
			throw new IllegalStateException("Error creating file for EOLScript resource");
		}
		EolStandaloneEngine engine = EpsilonStandaloneEngineFactory.EOL.getEngine();
		engine.addNativeDelegate(new EpsilonMatchFactory());
		engine.setScript(resourceFile.toPath());
		engine.addModel(getDriver().getModel());
		engine.setOperationName(operationName);
		engine.execute();
		engine.dispose();
		matches.addAll((Collection<? extends TPatternMatch>) engine.getResult());
		return matches;
	}
}
