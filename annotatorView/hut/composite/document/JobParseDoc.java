package hut.composite.document;

import hut.composite.document.CompositeDocComponentAll;
import hut.doc.DataIntermediateDoc;
import java.awt.Button;
import java.util.ArrayList;
import java.util.List;

import mitaniseplugin.views.ViewDocumentAnotator;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.PlatformUI;
import org.w3c.dom.Document;

/**
 * @author KienCuong
 * Class dung de goi job 
 */
public class JobParseDoc extends JobSuper {
	private String filepath;
	private List<List> listOfLists;
	private Document doc;
	private ViewDocumentAnotator viewDocumentAnotator;

	public JobParseDoc(String name, String filepath) {
		super(name);
		this.filepath = filepath;
	}
	

	public void setViewDocumentAnotator(ViewDocumentAnotator viewDocumentAnotator) {
		this.viewDocumentAnotator = viewDocumentAnotator;
	}


	protected IStatus run(IProgressMonitor monitor) {
		monitor.beginTask("Now Parsing Document!", 4);

		System.out.println("Load...." + filepath);
		monitor.worked(1);

		monitor.subTask("Parse Document...");
		final DataIntermediateDoc dataIntermediateDoc = new DataIntermediateDoc(
				filepath);
		dataIntermediateDoc.parse();
		monitor.worked(2);

		// //Do du lieu vao bang
		// monitor.subTask("Bind data...");
		// listOfLists = new ArrayList<List>();
		// listOfLists = intermeidateDocData.getImageData();
		// monitor.worked(1);
		//		
		// monitor.subTask("Bind data...");
		// doc= intermeidateDocData.getHeading();
		// monitor.worked(1);

		PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
			public void run() {
				try {
					Shell shell = new Shell(PlatformUI.getWorkbench()
							.getDisplay());
					MessageDialog.openInformation(shell, "Parsing success!",
							"Document has been parsed!");

					// bind data in to heading
					JobParseDoc.this.getController().changeDocument(dataIntermediateDoc.getAllData(),filepath);
					try {
						JobParseDoc.this.getController().getCompositeDocComponentImage().getOpenSourceToolItem().setEnabled(false);
						JobParseDoc.this.getController().getCompositeDocComponentParagraph().getOpenSourceToolItem().setEnabled(false);
						JobParseDoc.this.getController().getCompositeDocComponentSection().getOpenSourceToolItem().setEnabled(false);
					} catch (Exception e) {
						e.printStackTrace();
					}
					JobParseDoc.this.getController().setSaveDataDocument(dataIntermediateDoc);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		monitor.done();
		return Status.OK_STATUS;
	}
}
