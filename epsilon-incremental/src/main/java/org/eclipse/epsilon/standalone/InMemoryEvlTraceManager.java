package org.eclipse.epsilon.standalone;

import org.eclipse.epsilon.evl.incremental.execute.AbstractEvlExecutionTraceManager;


public class InMemoryEvlTraceManager extends AbstractEvlExecutionTraceManager {

	@Override
	public boolean persistTraceInformation() {
		System.out.println("InMemoryEvlTraceManager does not persist information.");
		return false;
	}
}
