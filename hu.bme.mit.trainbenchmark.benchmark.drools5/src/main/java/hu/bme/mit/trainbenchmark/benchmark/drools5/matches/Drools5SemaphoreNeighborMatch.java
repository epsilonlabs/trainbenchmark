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
package hu.bme.mit.trainbenchmark.benchmark.drools5.matches;

import static hu.bme.mit.trainbenchmark.constants.railway.RailwayQueryConstants.VAR_ROUTE1;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayQueryConstants.VAR_ROUTE2;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayQueryConstants.VAR_SEMAPHORE;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayQueryConstants.VAR_SENSOR1;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayQueryConstants.VAR_SENSOR2;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayQueryConstants.VAR_TE1;
import static hu.bme.mit.trainbenchmark.constants.railway.RailwayQueryConstants.VAR_TE2;
import hu.bme.mit.trainbenchmark.emf.matches.EMFSemaphoreNeighborMatch;
import hu.bme.mit.trainbenchmark.railway.Route;
import hu.bme.mit.trainbenchmark.railway.Semaphore;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import hu.bme.mit.trainbenchmark.railway.TrackElement;

import org.drools.runtime.rule.Row;

public class Drools5SemaphoreNeighborMatch extends EMFSemaphoreNeighborMatch {

	public Drools5SemaphoreNeighborMatch(final Row match) {
		super((Semaphore) match.get(VAR_SEMAPHORE), (Route) match.get(VAR_ROUTE1), (Route) match.get(VAR_ROUTE2), (Sensor) match
				.get(VAR_SENSOR1), (Sensor) match.get(VAR_SENSOR2), (TrackElement) match.get(VAR_TE1), (TrackElement) match.get(VAR_TE2));
	}

}
