package composite.ManageArtifactViewPart;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import hut.composite.packageExplorer.JobBuildSourceCode;
import hut.composite.tree.TreeContentProvider;
import hut.composite.tree.TreeLabelProvider;
import hut.composite.tree.TreeNodeData;
import hut.composite.tree.TreeObject;
import mintani.valueconst.ConsistentOntology;
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
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.forms.widgets.FormToolkit;


import service.Service;
import swing2swt.layout.BorderLayout;
import ws.owl.ClassData;
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
		compositeLableSource.setLayout(new FormLayout());
		compositeLableSource.setLayoutData(BorderLayout.NORTH);
		toolkit.adapt(compositeLableSource);

		final CLabel methodsInClassLabel = new CLabel(compositeLableSource,
				SWT.NONE);
		final FormData fd_methodsInClassLabel = new FormData();
		fd_methodsInClassLabel.bottom = new FormAttachment(0, 40);
		fd_methodsInClassLabel.right = new FormAttachment(0, 100);
		fd_methodsInClassLabel.top = new FormAttachment(0, 0);
		fd_methodsInClassLabel.left = new FormAttachment(0, 0);
		methodsInClassLabel.setLayoutData(fd_methodsInClassLabel);
		methodsInClassLabel.setImage(Images.imageRegistry.get(Images.TREE_PACKAGE));
		toolkit.adapt(methodsInClassLabel, true, true);
		methodsInClassLabel.setText("Source Tree");

		final ToolBar toolBar = new ToolBar(compositeLableSource, SWT.NONE);
		final FormData fd_toolBar = new FormData();
		fd_toolBar.right = new FormAttachment(100, 0);
		fd_toolBar.left = new FormAttachment(100, -230);
		fd_toolBar.bottom = new FormAttachment(0, 40);
		fd_toolBar.top = new FormAttachment(0, 0);
		toolBar.setLayoutData(fd_toolBar);
		toolkit.adapt(toolBar, true, true);

		refreshItemToolItem = new ToolItem(toolBar, SWT.PUSH);
		refreshItemToolItem.setText("Refresh");
		refreshItemToolItem.setImage(Images.imageRegistry.get(Images.REFRESH));

		final ToolItem toolItemAddCodeChange = new ToolItem(toolBar, SWT.PUSH);
		toolItemAddCodeChange.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				addCodeChange();
			}

			
		});
		toolItemAddCodeChange.setText("Add Code Change");
		toolItemAddCodeChange.setImage(Images.imageRegistry.get(Images.ADD));

		final ToolItem toolItemDelete = new ToolItem(toolBar, SWT.PUSH);
		toolItemDelete.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				deleteCodeComponent();
			}
		});
		toolItemDelete.setImage(Images.imageRegistry.get(Images.DELETE));
		toolItemDelete.setText("Delete");

		final Composite compositeTree = new Composite(compositeSourceTree,
				SWT.BORDER);
		compositeTree.setLayout(new BorderLayout(0, 0));
		toolkit.adapt(compositeTree);
		compositeTree.setLayoutData(BorderLayout.CENTER);

		treeViewer = new TreeViewer(compositeTree, SWT.NONE);
		treeViewer.setContentProvider(new TreeContentProvider());
		treeViewer.setLabelProvider(new TreeLabelProvider());
		tree = treeViewer.getTree();
		tree.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				addSelectedCode();
			}

		});
		toolkit.adapt(tree, true, true);

		toolkit.adapt(sashForm, true, true);
		
		//-- compositeRight  ---------------------
		sashForm.setWeights(new int[] {162 });

		//

		//Ham goi cay, nhung nen de bam refresh moi ra
		//creatTree();

		//Ham goi su kien
		registerAction();
	}
	
	protected void deleteCodeComponent() {
		DataInstance dataInstance = (DataInstance)CompositeSourceCodeTree.this.getOutputData();
		String instanceFullName = dataInstance.getInstanceFullName();
		Service.webServiceDelegate.removeCodeIndividual(null, instanceFullName, true);
		creatTree();
		MessageDialog.openInformation(new Shell(), "Success", "Delete successfully !");
		
		
	}

	public void creatTree() {
		JobBuildSourceCode jobBuildSourceCode = new JobBuildSourceCode("Build sourcetree", treeViewer);
		jobBuildSourceCode.setUser(true);
		jobBuildSourceCode.schedule();
	}
	
	private void addCodeChange() {
		Shell shell = new Shell(refreshItemToolItem.getDisplay());
		shell.setText("Add new Code Change !");
		shell.setImage(Images.imageRegistry.get(Images.BOOKMARK));
		shell.setLayout(new FillLayout());
		shell.setSize(510, 680);
		
		
		DataInstance dataInstance = new DataInstance();
		dataInstance = (DataInstance)CompositeSourceCodeTree.this.getOutputData();
		dataInstance.setNewInstance(true);
		
		ClassData classData = Service.webServiceDelegate.getClassByName(null, ConsistentOntology.CODE_CHANGE);
		dataInstance.setClassData(classData);
		
		//-- xu ly instanceFullName va fullURI cua SourceCodeComponent
		DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy.HH_mm_ss");
        Date date = new Date();
		String classFullName = classData.getClassURI();
		String ID=dateFormat.format(date);
		String instanceFullName = classFullName + "-" + ID;
		
		String sourceCodeURI = dataInstance.getInstanceFullName();
		
		dataInstance.setInstanceFullName(instanceFullName + "::::" + sourceCodeURI);//-- cong 2 thanh phan lai voi nhau
		
		CompositeSourceCodeTree.this.setOutputData(dataInstance);
		
		CompositeAddNewCodeChange compositeNewCodeChange = new CompositeAddNewCodeChange(shell, SWT.NONE);
		compositeNewCodeChange.setInputData(CompositeSourceCodeTree.this.getOutputData());
		compositeNewCodeChange.updateInterface();
		
		shell.setVisible(true);
		
	}
	
	
	private void addSelectedCode() {
	    IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
		TreeObject obj = (TreeObject) selection.getFirstElement();
		TreeNodeData data = (TreeNodeData) obj.getData();
		
		DataInstance dataInstance = new DataInstance();
		dataInstance.setNewInstance(true);
		ClassData classData = Service.webServiceDelegate.getClassByName(null, ConsistentOntology.CODE_CHANGE);
		dataInstance.setClassData(classData);
		
		CompositeSourceCodeTree.this.setOutputData(dataInstance);
		dataInstance.setInstanceFullName(data.getId());
		
	}
	
	public void registerAction() {
		Action action;
		action = new Action() {
			public void run() {
				IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
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
