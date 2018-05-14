package org.eclipse.epsilon.standalone.engine;

import org.eclipse.epsilon.engine.standalone.evl.IEvlStandaloneEngine;
import org.eclipse.epsilon.evl.IEvlModule;


public interface IIncrementalEvlStandaloneEngine<M extends IEvlModule>
	extends IEvlStandaloneEngine<M> {

	boolean isOnlineExecution();

	void setOnlineExecution(boolean online);

}
