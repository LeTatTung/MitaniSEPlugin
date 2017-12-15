package hut.composite.document;

import java.util.ArrayList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import service.Service;

import ws.owl.InstanceData;

import hut.jobs.BKJob;
import hut.jobs.IObjectExecuteJob;

public class JobSaveAnnotation implements IObjectExecuteJob{
	private ArrayList<InstanceData> listInstance;
	private BKJob job;
	private String ontology;
	private String documentFileName;
	

	public ArrayList<InstanceData> getListInstance() {
		return listInstance;
	}

	public JobSaveAnnotation(String ontology, String documentFileName, ArrayList<InstanceData> listInstance) {
		this.listInstance = listInstance;
		this.ontology = ontology;
		this.documentFileName = documentFileName;
	}

	public void saveAnnotation()
	{
		job = new BKJob("Saving annotation to Server ...");
		job.setObjectExecuteJob(JobSaveAnnotation.this);
		job.setUser(true);
		job.schedule();
	}
	
	public void jobExecute(IProgressMonitor monitor) {
		monitor.beginTask("Sending list to server: ("+listInstance.size()+") ...", listInstance.size());
		int GAP = 100;
		for (int i=0; i<listInstance.size(); i += GAP)
		{
			int min = Math.min(i+GAP, listInstance.size());
			monitor.subTask("Sending "+i+" to "+ (min-1));
			Service.webServiceDelegate.saveValuesOfIndividual(ontology, listInstance.subList(i, min), true);
			monitor.worked(min-i);
		}
		
		// luu vao model rong (ko co anotation) va ghi ra file owl
		Service.webServiceDelegate.reloadOntology("JavaDocumentOntology.owl");
		Service.webServiceDelegate.saveValuesOfIndividual("JavaDocumentOntology.owl", listInstance, false);
		Service.webServiceDelegate.saveDocumentToOWL("JavaDocumentOntology.owl", documentFileName);

		PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
			public void run() {
				try {		
					Shell shell = new Shell(Display.getCurrent());
					MessageDialog.openInformation(shell, "Success!", "Data of " + documentFileName + " have been Saved!!");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

}
