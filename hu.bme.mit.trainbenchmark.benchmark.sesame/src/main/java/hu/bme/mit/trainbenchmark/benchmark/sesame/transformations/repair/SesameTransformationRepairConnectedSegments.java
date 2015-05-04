/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.repair;

import hu.bme.mit.trainbenchmark.benchmark.sesame.driver.SesameDriver;
import hu.bme.mit.trainbenchmark.benchmark.sesame.matches.SesameConnectedSegmentsMatch;

import java.io.IOException;
import java.util.Collection;

import org.openrdf.model.ValueFactory;
import org.openrdf.repository.RepositoryConnection;

public class SesameTransformationRepairConnectedSegments extends SesameTransformationRepair<SesameConnectedSegmentsMatch> {

	public SesameTransformationRepairConnectedSegments(final SesameDriver sesameDriver) {
		super(sesameDriver);
	}

	@Override
	public void rhs(final Collection<SesameConnectedSegmentsMatch> matches) throws IOException {
		final RepositoryConnection con = sesameDriver.getConnection();
		final ValueFactory f = sesameDriver.getValueFactory();

		// final URI lengthProperty = f.createURI(BASE_PREFIX + LENGTH);

		// try {
		// for (final SesameConnectedSegmentsMatch match : matches) {
		// final Resource segment = match.getSegment();
		// final Value length = match.getLength();
		//
		// final RepositoryResult<Statement> statementsToRemove = con.getStatements(segment, lengthProperty, length, true);
		// while (statementsToRemove.hasNext()) {
		// con.remove(statementsToRemove.next());
		// }
		//
		// final Integer lengthInteger = new Integer(length.stringValue());
		// final Literal newLength = f.createLiteral(operation.op(lengthInteger));
		// con.add(segment, lengthProperty, newLength);
		// }
		// } catch (final RepositoryException e) {
		// throw new IOException(e);
		// }
	}
}
