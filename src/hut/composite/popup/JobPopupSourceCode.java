package hut.composite.popup;

import java.util.List;

import mintani.valueconst.ConsistentOntology;
import mintani.valueconst.ConstValue;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import service.Service;

import ws.data.NodeData;

import hut.composite.document.JobSuper;
import hut.composite.tree.TreeNodeData;

/**
 * Dung de chon quan he document voi code, co checkbox tren tree
 */
public class JobPopupSourceCode extends JobSuper {
	private TreeParent root;
	private NodeData nodeClient ;
	private TreeViewer treeViewer;
	private CheckboxTreeViewer  checkboxTreeViewer;
	public JobPopupSourceCode(String name) {
		super(name);
	}
	
	

	public JobPopupSourceCode(String name, TreeViewer treeViewer,CheckboxTreeViewer  checkboxTreeViewer) {
		super(name);
		this.treeViewer = treeViewer;
		this.checkboxTreeViewer = checkboxTreeViewer;
		
	}
	public JobPopupSourceCode(String name,CheckboxTreeViewer  checkboxTreeViewer) {
		super(name);
		this.checkboxTreeViewer = checkboxTreeViewer;
		
	}


	public TreeParent getRoot() {
		return root;
	}
	protected IStatus run(IProgressMonitor monitor) {
		monitor.beginTask("Request to server!", 4);
		
		monitor.worked(1);
		monitor.subTask("Requesting ...");
		
		String workspacename;
		monitor.worked(2);
		monitor.subTask("Innit request...");
		
			
		monitor.worked(3);
		monitor.subTask("Get data from Server");
		/**
		 * Phai login, chon Du an dang lam, Du an phai tao workspace truoc
		 */
		String uriProjectTeam = Service.uriProjectTeam;
		String uriWorkspace = "";
		if (uriProjectTeam == null)
		{
			PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
				public void run() {
					try {
						MessageDialog.openError(null, "Error", "You do not choose your working project yet! Login and choose one first");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			return Status.OK_STATUS;
		}
		else
		{
			List<String> lst = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, uriProjectTeam, ConsistentOntology.HAS_WORKSPACE);
			if (lst.size() != 0)
			{
				//Chi lay phan tu dau tien lam workspace, vi moi Du an chi co 1 workspace
				uriWorkspace = lst.get(0);
			}
			else
			{
				PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
					public void run() {
						try {
							MessageDialog.openError(new Shell(PlatformUI.getWorkbench().getDisplay()), "Error", "Your working project do not have workspace yet! Create one first");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				return Status.OK_STATUS;
			}
		}
		
		nodeClient = Service.dataServiceDelegate.getNodeData(uriWorkspace);
		
		root = new TreeParent(null, null);		
		if (nodeClient != null) {
			readChildNodes(nodeClient, root);

		}
		
		PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
			public void run() {
				try {
					Shell shell = new Shell(PlatformUI.getWorkbench().getDisplay());
//					MessageDialog.openInformation(shell, "Request success!", "Request success!");

					root = new TreeParent(null, null);		
					if (nodeClient != null) {
						readChildNodes(nodeClient, root);

					}
					if(treeViewer != null){
						treeViewer.setInput(root);
						treeViewer.refresh();
						treeViewer.expandToLevel(4);
					}
					if(checkboxTreeViewer != null){
						checkboxTreeViewer.setInput(root);
						checkboxTreeViewer.refresh();
						checkboxTreeViewer.expandToLevel(4);
					}
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		monitor.done();
		return Status.OK_STATUS;
	}
	
	private String standardize(String URI)//Cac ten uri phai chuan tac
	{
		URI=URI.replace("[", "");
		URI=URI.replace("]", "");
		URI=URI.replace("/", "" );
		return URI;
	}
	
	private void readChildNodes(NodeData rootdata,TreeParent root) {
		
		 List<NodeData> test = rootdata.getChildList();	
		 
		   for(int i=0;i<test.size();i++){
			  
			  NodeData itemNode = test.get(i);
			  	List<String> listype = itemNode.getListtype();
				if (itemNode.getChildList().size() > 0) {
					TreeParent parentTree = new TreeParent(new TreeNodeData(itemNode.getLabel(),Integer.toString(ConstValue.SOURCETYPE),itemNode.getNodetype().name(),listype,itemNode.getId()),null);
					root.addChild(parentTree);
					readChildNodes(itemNode, parentTree);
				}
			   else{
				    TreeObject objectTree = new TreeObject(new TreeNodeData(itemNode.getLabel(),Integer.toString(ConstValue.SOURCETYPE),itemNode.getNodetype().name(),listype,itemNode.getId()), null);
				    root.addChild(objectTree);
			   }
		   }
	}
	

}
