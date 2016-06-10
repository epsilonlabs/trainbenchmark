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

package hu.bme.mit.trainbenchmark.generator.rdf.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;

import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigWrapper;
import hu.bme.mit.trainbenchmark.rdf.RdfFormat;

public class RdfGeneratorConfigWrapper extends GeneratorConfigWrapper {
	
	protected boolean inferred;
	protected RdfFormat format; 

	protected RdfGeneratorConfigWrapper() {
	}
	
	public RdfGeneratorConfigWrapper(final GeneratorConfig generatorConfig, final boolean inferred, final RdfFormat format) {
		super(generatorConfig);
		this.inferred = inferred;
		this.format = format;
	}
	
	public boolean isInferred() {
		return inferred;
	}

	public RdfFormat getFormat() {
		return format;
	}
	
	public String getModelFlavor() {
		return isInferred() ? "-inferred" : "-metamodel";
	}

	public String getExtension() {
		return format.getExtension();
	}
	
	public static RdfGeneratorConfigWrapper fromFile(final String path) throws FileNotFoundException {
		final Kryo kryo = new Kryo();
		try (final Input input = new Input(new FileInputStream(path))) {
			final RdfGeneratorConfigWrapper generatorConfigWrapper = kryo.readObject(input, RdfGeneratorConfigWrapper.class);
			return generatorConfigWrapper;
		}
	}
	
}