/*******************************************************************************
 * Copyright (c) 2010-2015, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.viatra.transformations.repair;

import java.io.IOException;
import java.util.Collection;

import hu.bme.mit.trainbenchmark.benchmark.viatra.SemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.benchmark.viatra.driver.ViatraDriver;
import hu.bme.mit.trainbenchmark.benchmark.viatra.transformations.ViatraTransformation;

public class ViatraTransformationRepairSemaphoreNeighbor extends ViatraTransformation<SemaphoreNeighborMatch> {

	public ViatraTransformationRepairSemaphoreNeighbor(final ViatraDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<SemaphoreNeighborMatch> matches) throws IOException {
		for (final SemaphoreNeighborMatch match : matches) {
			match.getRoute2().setEntry(match.getSemaphore());
		}
	}

}
