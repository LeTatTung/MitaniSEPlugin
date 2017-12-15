package hut.composite.document;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.Job;
public abstract class JobSuper extends Job{

	private ControllerAnnotator controller;

	public JobSuper(String name) {
		super(name);
	}
	
	public ControllerAnnotator getController() {
		return controller;
	}

	public void setController(ControllerAnnotator controller) {
		this.controller = controller;
	}

	@Override
	protected IStatus run(IProgressMonitor arg0) {
		return null;
	}

}
