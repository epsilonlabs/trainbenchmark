package hu.bme.mit.trainbenchmark.benchmark.neo4j.transformations.user;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.LENGTH;
import hu.bme.mit.trainbenchmark.benchmark.neo4j.matches.Neo4jPosLengthMatch;

import java.util.Collection;

import org.neo4j.graphdb.Node;

public class Neo4jTransformationUserSemaphoreNeighbor extends Neo4jTransformationUser<Neo4jPosLengthMatch> {

	public Neo4jTransformationUserSemaphoreNeighbor() {
		super();
	}

	@Override
	public void rhs(final Collection<Neo4jPosLengthMatch> matches) {
		for (final Neo4jPosLengthMatch plm : matches) {
			final Node segment = plm.getSegment();
			final Integer length = (Integer) segment.getProperty(LENGTH);
			segment.setProperty(LENGTH, -length + 1);
		}
	}

}
