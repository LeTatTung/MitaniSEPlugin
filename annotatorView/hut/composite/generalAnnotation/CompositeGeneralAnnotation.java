package hut.composite.generalAnnotation;

//import hut.composite.annotationEditor.CompositeInstance;
import hut.composite.assistant.CommentContent;
import hut.composite.assistant.CommentManager;
import hut.composite.packageExplorer.JobBuildSourceCode;
import hut.composite.tree.TreeContentProvider;
import hut.composite.tree.TreeLabelProvider;
import hut.composite.tree.TreeNodeData;
import hut.composite.tree.TreeObject;
import mintani.valueconst.ConsistentOntology;
import mitani.activator.Activator;

import ontology.images.Images;

import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.forms.widgets.FormToolkit;

import swing2swt.layout.BorderLayout;


public class CompositeGeneralAnnotation extends Composite {

	private Tree tree;
	private TreeViewer treeViewer;
	//private CompositeInstance compositeInstance;
	private Logger logger = Logger.getLogger(this.getClass());
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Composite compositePropertyAnnotation;
	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public CompositeGeneralAnnotation(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout());
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);

		final SashForm sashForm = new SashForm(this, SWT.NONE);

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

		final CLabel methodsInClassLabel = new CLabel(compositeLableSource,
				SWT.NONE);
		methodsInClassLabel.setImage(Images.imageRegistry.get(Images.TREE_PACKAGE));
		methodsInClassLabel.setLayoutData(BorderLayout.NORTH);
		toolkit.adapt(methodsInClassLabel, true, true);
		methodsInClassLabel.setText("Source Tree");

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

		final Composite composite_right = new Composite(sashForm, SWT.BORDER);
		composite_right.setLayout(new FillLayout());
		toolkit.adapt(composite_right);

		compositePropertyAnnotation = new Composite(composite_right, SWT.NONE);
		compositePropertyAnnotation.setLayout(new BorderLayout(0, 0));
		toolkit.adapt(compositePropertyAnnotation);

		final Composite compositeLableComment = new Composite(
				compositePropertyAnnotation, SWT.BORDER);
		compositeLableComment.setLayout(new BorderLayout(0, 0));
		compositeLableComment.setLayoutData(BorderLayout.NORTH);
		toolkit.adapt(compositeLableComment);

		final CLabel methodCallFlowLabel = new CLabel(compositeLableComment,
				SWT.NONE);
		methodCallFlowLabel.setImage(Images.imageRegistry.get(Images.MANAGE_ANNOTATION));
		toolkit.adapt(methodCallFlowLabel, true, true);
		methodCallFlowLabel.setText("Manage Annotation");

		
		//Khoi tao composite Instance Annotator
		
		//compositeInstance =  new CompositeInstance(compositePropertyAnnotation, SWT.NONE);
		
		/*compositeInstance =  new CompositeInstance(compositePropertyAnnotation, SWT.NONE,ConsistentOntology.SEC_NAMESPACE+"R.ttt.src.ttt.Activator/Activator");;
		compositeInstance.setLayout(new BorderLayout(0, 0));
		toolkit.adapt(compositeInstance);
		compositeInstance.setLayoutData(BorderLayout.CENTER);*/

		sashForm.setWeights(new int[] { 162, 335 });

		//Goi ham tao cay		
		creatTree();
		//Goi ham ve cac su kien
		registerAction();
	}
	
	
	/*
	 * Goi toi ham chua nhung loi goi toi webservice
	 */
	public void creatTree() {

		JobBuildSourceCode jobBuildSourceCode = new JobBuildSourceCode("Build sourcetree", treeViewer);
		jobBuildSourceCode.setUser(true);
		jobBuildSourceCode.schedule();

	}
	
	/**
	 * @param action
	 * Su kien kich dup chuot
	 */
	public void registerDoubleClickAction(final Action action) {
		treeViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(final DoubleClickEvent arg0) {
				action.run();
			}
		});
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
				
				//Set id dau vao cho composite Instance Annotator
				//compositeInstance.setInstanceFullName(data.getId());
				logger.info("Lay tu cay thanh phan co id:" + data.getId());
			}
		};
		registerDoubleClickAction(action);
	}
}
