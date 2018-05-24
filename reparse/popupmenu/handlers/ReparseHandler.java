package popupmenu.handlers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import hut.composite.annotationManager.ASTParseProject;
import hut.composite.annotationManager.BKASTVisitor;
import hut.composite.annotationManager.CodeComponentNaming;
import hut.jobs.BKJob;
import hut.jobs.IObjectExecuteJob;
import mintani.valueconst.ConsistentOntology;
import service.Service;
import ws.owl.InstanceData;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class ReparseHandler extends AbstractHandler implements IObjectExecuteJob{
	private BKJob job;
	private IWorkbenchWindow window;
	private IJavaProject javaProject;
	public List<InstanceData> listofAnnotation = new ArrayList<InstanceData>();
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// get workbench window
		 window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		 MessageDialog.openInformation(window.getShell(),"Synchronize notice", "Project repeser run!");
		// set selection service
		ISelectionService service = window.getSelectionService();
		// set structured selection
		IStructuredSelection structured = (IStructuredSelection) service.getSelection();

		if (structured.getFirstElement() instanceof IJavaProject) {
			javaProject = (IJavaProject) structured.getFirstElement();
			System.out.println(javaProject.getElementName());
			ASTParseProject astParseProject = new ASTParseProject(javaProject.getElementName());
			astParseProject.run();
			newParse();
		}
		return null;
	}
	private void newParse() {
		job = new BKJob("Changing code ...");
		job.setObjectExecuteJob((IObjectExecuteJob) ReparseHandler.this);
		job.setUser(true);
		job.schedule();
	}
	

	@Override
	public void jobExecute(final IProgressMonitor monitor) {
		String uriProjectTeam = Service.uriProjectTeam;
		String uriWorkspace = "";

		/**
		 * Phai login, chon Du an dang lam, Du an phai tao workspace truoc
		 */
		if (uriProjectTeam == null) {
			MessageDialog.openInformation(window.getShell(),"Error", "You do not choose your working project yet! Login and choose one first");
			return;
		} else {
			List<String> lst = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, uriProjectTeam,
					ConsistentOntology.HAS_WORKSPACE);
			if (lst.size() != 0) {
				// Chi lay phan tu dau tien lam workspace, vi moi Du an chi co 1 workspace
				uriWorkspace = lst.get(0);
			} else {
				MessageDialog.openInformation(window.getShell(),"Error", "Your working project do not have workspace yet! Create one first");
				return;
			}
		}
		monitor.beginTask("Changing Code ...", 3);
		monitor.subTask("Preparing ...");
		monitor.worked(2);

		monitor.subTask("AST Parsing ...");
		CodeComponentNaming codeComponentNaming = new CodeComponentNaming();
		codeComponentNaming.setIdWorkspaceFull(uriWorkspace);
		BKASTVisitor bkVisitor = new BKASTVisitor(codeComponentNaming);
		Boolean willParse = false;
			System.out.println("Project");
//			IProject project = (IProject) ast;
			codeComponentNaming.setIdProject(javaProject.getElementName());

			// Kiem tra da ton tai instance tuong ung chua
			Boolean exist = Service.webServiceDelegate.checkExistIndividual(null,
					codeComponentNaming.getIdProjectFull());

				if (!exist)
					MessageDialog.openInformation(window.getShell(),"Error", "Project does not exist! Use Add and Parse function");
				else {
					Service.webServiceDelegate.removeCodeIndividual(null, codeComponentNaming.getIdProjectFull(),
							false);
					willParse = true;
				}
			if (willParse)
				bkVisitor.parseProject(javaProject);
		monitor.worked(1);
		listofAnnotation = bkVisitor.getListofAnnotation();

		monitor.beginTask("Sending list to server: (" + listofAnnotation.size() + ") ...", listofAnnotation.size());
		int GAP = 100;
		for (int i = 0; i < listofAnnotation.size(); i += GAP) {
			int min = Math.min(i + GAP, listofAnnotation.size());
			monitor.subTask("Sending " + i + " to " + (min - 1));
			Service.webServiceDelegate.saveValuesOfIndividual(null, listofAnnotation.subList(i, min), true);
			monitor.worked(min - i);
		}
	}
}
