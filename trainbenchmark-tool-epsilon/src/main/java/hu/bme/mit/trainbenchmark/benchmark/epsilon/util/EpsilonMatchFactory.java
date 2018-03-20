/*******************************************************************************
 * Copyright (c) 2018 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Horacio Hoyos Rodriguez - initial API and implementation
 ******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.epsilon.util;

import hu.bme.mit.trainbenchmark.benchmark.epsilon.matches.*;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.types.AbstractToolNativeTypeDelegate;


import java.util.*;
import java.util.AbstractMap.SimpleEntry;

/**
 * The Class EpsilonMatchFactory. Uses a map to dispatch class names to factory methods.
 */
public class EpsilonMatchFactory extends AbstractToolNativeTypeDelegate {

	@FunctionalInterface
	public interface VarArgFunction<R, T> {

		R apply(T... args);
	}

	/**
	 * The dispatcher map.
	 * @return
	 */
	protected static Map<String, VarArgFunction> factoryMethods() {

		Map<String, VarArgFunction> map = new HashMap<>();
		for (SimpleEntry<String, VarArgFunction> e : Arrays.asList(
			new SimpleEntry<String, VarArgFunction>("ConnectedSegmentsInjectMatch", EpsilonMatchFactory::createConnectedSegmentsInjectMatch),
			new SimpleEntry<String, VarArgFunction>("ConnectedSegmentsMatch", EpsilonMatchFactory::createConnectedSegmentsMatch),
			new SimpleEntry<String, VarArgFunction>("PosLengthInjectMatch", EpsilonMatchFactory::createPosLengthInjectMatch),
			new SimpleEntry<String, VarArgFunction>("PosLengthMatch", EpsilonMatchFactory::createPosLengthMatch),
			new SimpleEntry<String, VarArgFunction>("RouteSensorInjectMatch", EpsilonMatchFactory::createRouteSensorInjectMatch),
			new SimpleEntry<String, VarArgFunction>("RouteSensorMatch", EpsilonMatchFactory::createRouteSensorMatch),
			new SimpleEntry<String, VarArgFunction>("SemaphoreNeighborInjectMatch", EpsilonMatchFactory::createSemaphoreNeighborInjectMatch),
			new SimpleEntry<String, VarArgFunction>("SemaphoreNeighborMatch", EpsilonMatchFactory::createSemaphoreNeighborMatch),
			new SimpleEntry<String, VarArgFunction>("SwitchMonitoredInjectMatch", EpsilonMatchFactory::craeteSwitchMonitoredInjectMatch),
			new SimpleEntry<String, VarArgFunction>("SwitchMonitoredMatch", EpsilonMatchFactory::createSwitchMonitoredMatch),
			new SimpleEntry<String, VarArgFunction>("SwitchSetInjectMatch", EpsilonMatchFactory::createSwitchSetInjectMatch),
			new SimpleEntry<String, VarArgFunction>("SwitchSetMatch", EpsilonMatchFactory::createSwitchSetMatch)
			)) {
			if (map.put(e.getKey(), e.getValue()) != null) {
				throw new IllegalStateException("Duplicate key");
			}
		}
		return Collections.unmodifiableMap(map);
	}

	/**
	 * Creates the connected segments inject match.
	 *
	 * @param values: the sensor, the segment1 and  the segment 3
	 * @return the epsilon api connected segments inject match
	 */
	public static EpsilonApiConnectedSegmentsInjectMatch createConnectedSegmentsInjectMatch(Object... values) {

		return new EpsilonApiConnectedSegmentsInjectMatch(values[0], values[1], values[2]);
	}

	/**
	 * Creates the connected segments match.
	 *
	 * @param values, the sensor, the segment1, the segment2, the segment3, the segment4, the segment5, the segment6
	 * @return the epsilon api connected segments match
	 */
	public static EpsilonApiConnectedSegmentsMatch createConnectedSegmentsMatch(Object... values) {

		return new EpsilonApiConnectedSegmentsMatch(values[0], values[1], values[2], values[3], values[4], values[5], values[6]);
	}

	/**
	 * Creates the pos length inject match.
	 *
	 * @param values the segment
	 * @return the epsilon api pos length inject match
	 */
	public static EpsilonApiPosLengthInjectMatch createPosLengthInjectMatch(Object... values) {

		return new EpsilonApiPosLengthInjectMatch(values[0]);
	}

	/**
	 * Creates the pos length match.
	 *
	 * @param values the segment
	 * @return the epsilon api pos length match
	 */
	public static EpsilonApiPosLengthMatch createPosLengthMatch(Object... values) {

		return new EpsilonApiPosLengthMatch(values[0]);
	}

	/**
	 * Creates the route sensor inject match.
	 *
	 * @param values the route, the sensor
	 * @return the epsilon api route sensor inject match
	 */
	public static EpsilonApiRouteSensorInjectMatch createRouteSensorInjectMatch(Object... values) {

		return new EpsilonApiRouteSensorInjectMatch(values[0], values[1]);
	}

	/**
	 * Creates the route sensor match.
	 *
	 * @param values the route, the sensor, the swP, the sw
	 * @return the epsilon api route sensor match
	 */
	public static EpsilonApiRouteSensorMatch createRouteSensorMatch(Object... values) {

		return new EpsilonApiRouteSensorMatch(values[0], values[1], values[2], values[3]);
	}

	/**
	 * Creates the semaphore neighbor inject match.
	 *
	 * @param values the route, the semaphore
	 * @return the epsilon api semaphore neighbor inject match
	 */
	public static EpsilonApiSemaphoreNeighborInjectMatch createSemaphoreNeighborInjectMatch(Object... values) {

		return new EpsilonApiSemaphoreNeighborInjectMatch(values[0], values[1]);
	}

	/**
	 * Creates the semaphore neighbor match.
	 *
	 * @param values the semaphore, the route1, the route2, the sensor1, the sensor2, the te1, the te2
	 * @return the epsilon api semaphore neighbor match
	 */
	public static EpsilonApiSemaphoreNeighborMatch createSemaphoreNeighborMatch(Object... values) {

		return new EpsilonApiSemaphoreNeighborMatch(values[0], values[1], values[2], values[3], values[4], values[5], values[6]);
	}

	/**
	 * Creates the set inject match.
	 *
	 * @param values the sw
	 * @return the epsilon api set inject match
	 */
	public static EpsilonApiSwitchSetInjectMatch createSwitchSetInjectMatch(Object... values) {

		return new EpsilonApiSwitchSetInjectMatch(values[0]);
	}

	/**
	 * Creates the set monitored match.
	 * @param values, semaphore, route, swP, sw
	 * @return the epsilon api set monitored match
	 */
	public static EpsilonApiSwitchSetMatch createSwitchSetMatch(Object... values) {

		return new EpsilonApiSwitchSetMatch(values[0], values[1], values[2], values[3]);
	}

	/**
	 * Craete switch monitored inject match.
	 *
	 * @param values the sw
	 * @return the epsilon api switch monitored inject match
	 */
	public static EpsilonApiSwitchMonitoredInjectMatch craeteSwitchMonitoredInjectMatch(Object... values) {

		return new EpsilonApiSwitchMonitoredInjectMatch(values[0]);
	}

	/**
	 * Creates the switch monitored match.
	 *
	 * @param values the sw
	 * @return the epsilon api switch monitored match
	 */
	public static EpsilonApiSwitchMonitoredMatch createSwitchMonitoredMatch(Object... values) {

		return new EpsilonApiSwitchMonitoredMatch(values[0]);
	}

	@Override
	public boolean knowsAbout(String clazz) {
		return factoryMethods().keySet().contains(clazz);
	}

	@Override
	public Object createInstance(String clazz, List<Object> parameters) throws EolRuntimeException {
		try {
			return factoryMethods().get(clazz).apply(parameters.toArray());
		} catch (NullPointerException ex) {
			throw new EolRuntimeException("Unkwon class " + clazz + " requested from  EpsilonMatchFactory.");
		}
	}

}
