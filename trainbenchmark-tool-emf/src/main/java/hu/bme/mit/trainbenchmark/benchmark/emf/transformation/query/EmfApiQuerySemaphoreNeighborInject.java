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

package hu.bme.mit.trainbenchmark.benchmark.emf.transformation.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import hu.bme.mit.trainbenchmark.benchmark.emf.driver.EmfDriver;
import hu.bme.mit.trainbenchmark.benchmark.emf.matches.EmfSemaphoreNeighborInjectMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Semaphore;

public class EmfApiQuerySemaphoreNeighborInject<TDriver extends EmfDriver> extends EmfApiQuery<EmfSemaphoreNeighborInjectMatch, TDriver> {

	public EmfApiQuerySemaphoreNeighborInject(final TDriver driver) {
		super(RailwayQuery.SEMAPHORENEIGHBOR_INJECT, driver);
	}

	@Override
	public Collection<EmfSemaphoreNeighborInjectMatch> evaluate() {
		final List<EmfSemaphoreNeighborInjectMatch> matches = new ArrayList<>();

		for (final Route route : driver.getContainer().getRoutes()) {
			final Semaphore entry = route.getEntry();
			if (entry == null) {
				continue;
			}

			matches.add(new EmfSemaphoreNeighborInjectMatch(route, entry));
		}

		return matches;
	}

}
