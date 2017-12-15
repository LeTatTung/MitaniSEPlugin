package composite.ManageArtifactViewPart;

import hut.composite.packageExplorer.JobBuildSourceCode;
import hut.composite.tree.TreeContentProvider;
import hut.composite.tree.TreeLabelProvider;
import hut.composite.tree.TreeNodeData;
import hut.composite.tree.TreeObject;
import ontology.images.Images;

import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.forms.widgets.FormToolkit;


import swing2swt.layout.BorderLayout;
import ws.owl.ClassData;
import hut.composite.annotationCreatorAndEditor.CompositeListPropertiesOfInstance;
import hut.composite.assistant.CommentContent;
import hut.composite.assistant.CommentManager;
import hut.composite.assistant.ControllerAnnotator;
import hut.composite.classExplorer.JobBuildOntClass;
import hut.model.annotationCreatorAndEditor.DataInstance;


public class CompositeSourceCodeTree extends SuperComposite {

	private Table table;
	private Tree tree;
	private TreeViewer treeViewer;
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Logger logger = Logger.getLogger(this.getClass());
	private ToolItem refreshItemToolItem;
	
	public CompositeSourceCodeTree(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout());
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);

		final SashForm sashForm = new SashForm(this, SWT.NONE);

		//-- compositeLeft--------------
		final Composite composite_left = new Composite(sashForm, SWT.NONE);
		composite_left.setLayout(new FillLayout());
		toolkit.adapt(composite_left);

		final SashForm sashForm_1 = new SashForm(composite_left, SWT.VERTICAL);

		toolkit.adapt(sashForm_1, true, true);

		final Composite compositeSourceTree = new Composite(sashForm_1,
				SWT.BORDER);
		compositeSourceTree.setLayout(new BorderLayout(0, 0));
		toolkit.adapt(compositeSourceTree);

		final Composite compositeLableSource = new Composite(
				compositeSourceTree, SWT.BORDER);
		compositeLableSource.setLayoutData(BorderLayout.NORTH);
		compositeLableSource.setLayout(new BorderLayout(0, 0));
		toolkit.adapt(compositeLableSource);

		final SashForm sashForm_2 = new SashForm(compositeLableSource, SWT.NONE);
		toolkit.adapt(sashForm_2, true, true);
		sashForm_2.setLayoutData(BorderLayout.SOUTH);

		final CLabel methodsInClassLabel = new CLabel(sashForm_2,
				SWT.NONE);
		methodsInClassLabel.setImage(Images.imageRegistry.get(Images.TREE_PACKAGE));
		methodsInClassLabel.setLayoutData(BorderLayout.NORTH);
		toolkit.adapt(methodsInClassLabel, true, true);
		methodsInClassLabel.setText("Source Tree");

		final ToolBar toolBar = new ToolBar(sashForm_2, SWT.NONE);
		toolkit.adapt(toolBar, true, true);

		refreshItemToolItem = new ToolItem(toolBar, SWT.PUSH);
		refreshItemToolItem.setImage(Images.imageRegistry.get(Images.REFRESH));
		sashForm_2.setWeights(new int[] {122, 28 });

		final Composite compositeTree = new Composite(compositeSourceTree,
				SWT.BORDER);
		compositeTree.setLayout(new BorderLayout(0, 0));
		toolkit.adapt(compositeTree);
		compositeTree.setLayoutData(BorderLayout.CENTER);

		treeViewer = new TreeViewer(compositeTree, SWT.NONE);
		treeViewer.setContentProvider(new TreeContentProvider());
		treeViewer.setLabelProvider(new TreeLabelProvider());
		tree = treeViewer.getTree();
		toolkit.adapt(tree, true, true);

		toolkit.adapt(sashForm, true, true);
		
		//-- compositeRight  ---------------------
		sashForm.setWeights(new int[] {162 });

		//

		//Ham goi cay
		creatTree();

		//Ham goi su kien
		registerAction();
	}
	
	public void creatTree() {
		JobBuildSourceCode jobBuildSourceCode = new JobBuildSourceCode("Build sourcetree", treeViewer);
		jobBuildSourceCode.setUser(true);
		jobBuildSourceCode.schedule();
	}
	
	public void registerAction() {
		Action action;
		action = new Action() {
			public void run() {
				IStructuredSelection selection = (IStructuredSelection) treeViewer
						.getSelection();
				TreeObject obj = (TreeObject) selection.getFirstElement();
				TreeNodeData data = (TreeNodeData) obj.getData();
				logger.info(data.getName());
				
				DataInstance dataInstance = new DataInstance();
				dataInstance.setNewInstance(false);
				dataInstance.setInstanceFullName(data.getId());
				
				String classURI = service.Service.webServiceDelegate.getClassOfIndividual(null, data.getId());
				ClassData classData = new ClassData();
				classData.setClassURI(classURI);
				
				dataInstance.setClassData(classData);
				
				CompositeSourceCodeTree.this.setOutputData(dataInstance);
				CompositeSourceCodeTree.this.getController().updateChoosenSourceCode();
				
//				CommentManager.this.setOutputData(data.getId());
//				CommentManager.this.setTypeSource(data.getTypeSource());
//				CommentManager.this.setSourceComponentName(data.getName());
//				CommentManager.this.getController().bindIdToRightComposite();
				logger.info("Lay tu cay thanh phan co id:" + data.getId());
			}
		};
		registerDoubleClickAction(action);
		
		//refresh lai cay
		refreshItemToolItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				creatTree();
			}
		});
	}
	
	private void showRowItem( ){
		clearTable();
		
		for (int i=0; i< 2 ; i++) {
	    	TableColumn column = new TableColumn(table, SWT.CENTER);
	    	if(i==0){
	    		column.setText("Name ");
	    		column.setWidth(300);
	    	}else
	    	{
	    		column.setText("Overview");
	    		column.setWidth(500);
	    	}
	    	
			
		}
	    for (int loopIndex = 0; loopIndex < 2; loopIndex++) {
	        table.getColumn(loopIndex).pack();
	        table.getColumn(loopIndex).setWidth(300);
	      }
	}
	
	public void registerDoubleClickAction(final Action action) {
		treeViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(final DoubleClickEvent arg0) {
				action.run();
			}
		});
	}
	
	private void clearTable()
	{
		table.removeAll();
		for (TableColumn column : table.getColumns()) {
			column.dispose();
		}
		
		
	}
	
	@Override
	int updateInterface() {
		// TODO Auto-generated method stub
		return 0;
	}

}
