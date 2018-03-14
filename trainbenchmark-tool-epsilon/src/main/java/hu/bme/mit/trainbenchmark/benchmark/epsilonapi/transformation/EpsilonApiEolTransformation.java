package hu.bme.mit.trainbenchmark.benchmark.epsilonapi.transformation;

import hu.bme.mit.trainbenchmark.benchmark.epsilonapi.driver.EpsilonDriver;
import hu.bme.mit.trainbenchmark.benchmark.epsilonapi.matches.EpsilonMatch;
import hu.bme.mit.trainbenchmark.benchmark.epsilonapi.util.EpsilonApiUtil;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelTransformation;
import org.eclipse.epsilon.engine.standalone.EpsilonStandaloneEngineFactory;
import org.eclipse.epsilon.engine.standalone.eol.EolStandaloneEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

public class EpsilonApiEolTransformation<TMatch extends EpsilonMatch, TDriver extends EpsilonDriver>
		extends ModelTransformation<TMatch, TDriver>{

	private static final Logger logger = LoggerFactory.getLogger(EpsilonApiEolTransformation.class);


	private final String eolScriptName;
	private final String operationName;

	public EpsilonApiEolTransformation(TDriver driver, String eolScriptName, String operationName) {
		super(driver);
		this.eolScriptName = eolScriptName;
		this.operationName = operationName;
	}

	@Override
	public void activate(Collection<TMatch> tMatches) throws Exception {
		InputStream resource = EpsilonApiEolQuery.class.getResourceAsStream(eolScriptName);
		File resourceFile = EpsilonApiUtil.getResourceAsFile(resource);
		if (resourceFile == null) {
			logger.error("Error creating file for EOLScript resource " + eolScriptName);
			throw new IllegalStateException("Error creating file for EOLScript resource");
		}
		logger.info("Creating engine");
		EolStandaloneEngine engine = EpsilonStandaloneEngineFactory.EOL.getEngine();
		engine.setScript(resourceFile.toPath());
		engine.addModel(getDriver().getModel());
		engine.setOperationName(operationName);
		engine.setOperationArguments(new ArrayList<>(tMatches));
		logger.info("Executing");
		engine.execute();
		engine.dispose();
	}
}
