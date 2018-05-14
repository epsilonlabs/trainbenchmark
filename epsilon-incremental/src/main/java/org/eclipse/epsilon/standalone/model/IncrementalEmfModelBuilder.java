package org.eclipse.epsilon.standalone.model;

import org.eclipse.epsilon.emc.emf.online.OnlineEmfModel;
import org.eclipse.epsilon.engine.standalone.model.*;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IncrementalEmfModelBuilder extends AbstractEmfModelBuilder<IncrementalEmfModelBuilder, OnlineEmfModel>
	implements IEmfModelBuilder<IncrementalEmfModelBuilder, OnlineEmfModel> {


	protected List<String> meteamodelUris;
	protected List<String> fileBasedMetamodelUris;
	protected boolean reuseMetamodels = true;


	public IncrementalEmfModelBuilder() {
		meteamodelUris = new ArrayList<>();
		fileBasedMetamodelUris = new ArrayList<>();
	}

	@Override
	public String getName() {
		return "OnlineEmf";
	}

	@Override
	public IncrementalEmfModelBuilder self() {
		return this;
	}

	@Override
	public IncrementalEmfModelBuilder withName(String name) {
		this.name = name;
		return self();
	}

	@Override
	public IncrementalEmfModelBuilder withModelPath(Path modelpath) {
		this.modelUri = modelpath.toString();
		return self();
	}

	public IncrementalEmfModelBuilder reuseUnmodifiedFileBasedMetamodels(boolean reuse) {
		this.reuseMetamodels = reuse;
		return self();
	}

	public IncrementalEmfModelBuilder withMetamodelUri(String metamodelUri) {
		this.meteamodelUris.add(metamodelUri);
		return self();
	}

	public IncrementalEmfModelBuilder withMetamodelUris(String... metamodelUris) {
		this.meteamodelUris.addAll(Arrays.asList(metamodelUris));
		return self();
	}

	public IncrementalEmfModelBuilder withMetamodelPath(Path metamodelFile) {
		this.fileBasedMetamodelUris.add(metamodelFile.toString());
		return self();
	}

	public IncrementalEmfModelBuilder withMetamodelPaths(Path... metamodelFiles) {
		this.fileBasedMetamodelUris.addAll(Arrays.asList(metamodelFiles).stream().map(Path::toString).collect(Collectors.toList()));
		return self();
	}

	@Override
	public OnlineEmfModel build() {
		OnlineEmfModel model = null;
		try {
			model = new OnlineEmfModel();
		} catch (EolModelLoadingException e) {
			e.printStackTrace();
			return null;
		}
		model.setName(name);
		model.setReadOnLoad(this.readOnLoad);
		model.setStoredOnDisposal(this.storeOnDisposal);
		model.setCachingEnabled(this.useCache);
		model.setExpand(this.expand);
		model.setModelFile(this.modelUri);
		model.setMetamodelUris(this.meteamodelUris);
		model.setMetamodelFiles(this.fileBasedMetamodelUris);
		model.setReuseUnmodifiedFileBasedMetamodels(this.reuseMetamodels);
		return model;
	}
}
