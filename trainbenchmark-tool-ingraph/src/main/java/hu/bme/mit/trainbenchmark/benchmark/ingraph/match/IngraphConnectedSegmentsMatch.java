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
package hu.bme.mit.trainbenchmark.benchmark.ingraph.match;

import hu.bme.mit.trainbenchmark.benchmark.matches.ConnectedSegmentsMatch;
import scala.collection.immutable.Map;

public class IngraphConnectedSegmentsMatch extends IngraphMatch implements ConnectedSegmentsMatch {

	public IngraphConnectedSegmentsMatch(final Map<Object, Object> qs) {
		super(qs);
	}

	@Override
	public Long getSegment1() {
		return (Long) qs.get(0).get();
	}

	@Override
	public Long getSegment2() {
		return (Long) qs.get(1).get();
	}

	@Override
	public Long getSegment3() {
		return (Long) qs.get(2).get();
	}

	@Override
	public Long getSegment4() {
		return (Long) qs.get(3).get();
	}

	@Override
	public Long getSegment5() {
		return (Long) qs.get(4).get();
	}

	@Override
	public Long getSegment6() {
		return (Long) qs.get(5).get();
	}

	@Override
	public Long getSensor() {
		return (Long) qs.get(6).get();
	}

}
