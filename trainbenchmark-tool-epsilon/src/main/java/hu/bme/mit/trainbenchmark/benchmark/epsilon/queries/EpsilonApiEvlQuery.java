package hu.bme.mit.trainbenchmark.benchmark.epsilon.queries;

import hu.bme.mit.trainbenchmark.benchmark.epsilon.driver.EpsilonDriver;
import hu.bme.mit.trainbenchmark.benchmark.epsilon.matches.EpsilonMatch;
import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import org.eclipse.epsilon.engine.standalone.evl.IEvlStandaloneEngine;
import org.eclipse.epsilon.evl.execute.UnsatisfiedConstraint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EpsilonApiEvlQuery<TPatternMatch extends EpsilonMatch, TDriver extends EpsilonDriver>
		extends ModelQuery<TPatternMatch, TDriver> {

	private static final Logger logger = LoggerFactory.getLogger(EpsilonApiEvlQuery.class);

	private IEvlStandaloneEngine engine;


	public EpsilonApiEvlQuery(RailwayQuery query, TDriver driver, IEvlStandaloneEngine engine) {
		super(query, driver);
		this.engine = engine;

	}

	@Override
	public Collection<TPatternMatch> evaluate() throws Exception {
		logger.info("Start evaluation");
		final List<TPatternMatch> matches = new ArrayList<>();
		logger.info("Executing");
		engine.addModel(driver.getModel());
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
		engine.dispose();
		return matches;
	}


}
