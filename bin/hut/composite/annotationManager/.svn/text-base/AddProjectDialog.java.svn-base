package hut.composite.annotationManager;

import hut.composite.packageExplorer.PackageTreeNodeData;
import hut.composite.packageExplorer.TreeContentProvider;
import hut.composite.packageExplorer.TreeLabelProvider;
import hut.viewer.tree.TreeObject;
import hut.viewer.tree.TreeParent;
import ontology.images.Images;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import swing2swt.layout.BorderLayout;


public class AddProjectDialog extends Dialog {

	protected String result = "";
	protected Shell shell;
	private TreeViewer treeViewer;
	/**
	 * Create the dialog
	 * @param parent
	 * @param style
	 */
	public AddProjectDialog(Shell parent, int style) {
		super(parent, style);
	}

	/**
	 * Create the dialog
	 * @param parent
	 */
	public AddProjectDialog(Shell parent) {
		this(parent, SWT.NONE);
	}

	/**
	 * Open the dialog
	 * @return the result
	 */
	public String open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		return result;
	}

	/**
	 * Create contents of the dialog
	 */
	protected void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setImage(Images.imageRegistry.get(Images.ADD));
		shell.setLayout(new BorderLayout(0, 0));
		shell.setSize(185, 265);
		shell.setText("Add Project in workspace");

		treeViewer = new TreeViewer(shell, SWT.BORDER | SWT.SINGLE);
		treeViewer.setLabelProvider(new TreeLabelProvider());
		treeViewer.setContentProvider(new TreeContentProvider());
		initProject();
		//final Tree tree = treeViewer.getTree();

		final Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new RowLayout());
		composite.setLayoutData(BorderLayout.SOUTH);

		final Button okButton = new Button(composite, SWT.NONE);
		final RowData rd_okButton = new RowData();
		rd_okButton.width = 58;
		okButton.setLayoutData(rd_okButton);
		okButton.setText("OK");

		final Button cancelButton = new Button(composite, SWT.NONE);
		final RowData rd_cancelButton = new RowData();
		rd_cancelButton.width = 68;
		cancelButton.setLayoutData(rd_cancelButton);
		cancelButton.setText("Cancel");
		
		Listener listener = new Listener() {
			public void handleEvent(Event event) {
				if (event.widget == okButton) {
					IStructuredSelection sel = (IStructuredSelection) treeViewer.getSelection();
					if(sel.isEmpty())
						return;
					PackageTreeNodeData project = (PackageTreeNodeData) ((TreeObject)sel.getFirstElement()).getData();
					result = project.getName();
					shell.close();
				} else {
					shell.close();
				}
			}
		};
		okButton.addListener(SWT.Selection, listener);
		cancelButton.addListener(SWT.Selection, listener);
	}
	private void initProject()
	{
		TreeParent root = new TreeParent(null, null);
		IWorkspaceRoot workspace = ResourcesPlugin.getWorkspace().getRoot();
		IProject[] projects = workspace.getProjects();
		for (IProject project : projects)
			if (project.isOpen())
			{
				TreeObject pro = new TreeObject(new PackageTreeNodeData(project.getName(), Images.PROJECT), root);
				root.addChild(pro);
			}
		treeViewer.setInput(root);
	}
}
