package hut.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.PlatformUI;

public class BKJob extends Job{
	
	private IObjectExecuteJob objectExecuteJob;

	public BKJob(String name) {
		super(name);
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		objectExecuteJob.jobExecute(monitor);		
		
		PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
			public void run() {
				try {							
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		monitor.done();
		return Status.OK_STATUS;
	}

	public IObjectExecuteJob getObjectExecuteJob() {
		return objectExecuteJob;
	}

	public void setObjectExecuteJob(IObjectExecuteJob objectExecuteJob) {
		this.objectExecuteJob = objectExecuteJob;
	}
}
