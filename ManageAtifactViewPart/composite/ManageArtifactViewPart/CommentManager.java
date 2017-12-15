package composite.ManageArtifactViewPart;

import hut.composite.tree.TreeObject;
import hut.composite.packageExplorer.JobBuildSourceCode;
import hut.composite.tree.TreeContentProvider;
import hut.composite.tree.TreeLabelProvider;
import hut.composite.tree.TreeNodeData;
import ontology.images.Images;

import org.eclipse.jface.action.Action;
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
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.forms.widgets.FormToolkit;

import swing2swt.layout.BorderLayout;



import org.apache.log4j.Logger;

import controller.ManageArtifactViewPart.ControllerManageArtifactViewPart;

public class CommentManager extends SuperComposite {
	private String typeSource;//Truyen vao xem no loai gi--Method,Class,..
	private String sourceComponentName;//Ten cua source component

	private Tree tree;
	private TreeViewer treeViewer;
	private ControllerManageArtifactViewPart controllerManageArtifactViewPart;
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Logger logger = Logger.getLogger(this.getClass());
	private CommentContent commentContent;
	private ToolItem refreshItemToolItem;
	
	public String getTypeSource() {
		return typeSource;
	}
	public void setTypeSource(String typeSource) {
		this.typeSource = typeSource;
	}
	
	public String getSourceComponentName() {
		return sourceComponentName;
	}
	public void setSourceComponentName(String sourceComponentName) {
		this.sourceComponentName = sourceComponentName;
	}
	
	
	
	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public CommentManager(Composite parent, int style) {
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

		final Composite composite_right = new Composite(sashForm, SWT.BORDER);
		composite_right.setLayout(new FillLayout());
		toolkit.adapt(composite_right);

		final Composite compositeContentComment = new Composite(
				composite_right, SWT.NONE);
		compositeContentComment.setLayout(new BorderLayout(0, 0));
		toolkit.adapt(compositeContentComment);

		final Composite compositeLableComment = new Composite(
				compositeContentComment, SWT.BORDER);
		compositeLableComment.setLayout(new BorderLayout(0, 0));
		compositeLableComment.setLayoutData(BorderLayout.NORTH);
		toolkit.adapt(compositeLableComment);

		final CLabel methodCallFlowLabel = new CLabel(compositeLableComment,
				SWT.NONE);
		methodCallFlowLabel.setImage(Images.imageRegistry.get(Images.COMMENT));
		toolkit.adapt(methodCallFlowLabel, true, true);
		methodCallFlowLabel.setText("Manage Semantic Comment");

		commentContent = new CommentContent(compositeContentComment, SWT.NONE);
		commentContent.setLayout(new BorderLayout(0, 0));
		toolkit.adapt(commentContent);
		commentContent.setLayoutData(BorderLayout.CENTER);

		sashForm.setWeights(new int[] { 162, 335 });
		//

		//Ham goi cay, nhung nen de bam refresh moi ra
		//creatTree();

		//Set controller cho treeviewer va phan composite ben phair
		setController();

		//Ham goi su kien
		registerAction();
	}

	public void setController() {
		controllerManageArtifactViewPart = new ControllerManageArtifactViewPart();
		controllerManageArtifactViewPart.setCommentContent(commentContent);
		controllerManageArtifactViewPart.setCommentManager(this);

		this.setController(controllerManageArtifactViewPart);
		commentContent.setController(controllerManageArtifactViewPart);
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
				CommentManager.this.setOutputData(data.getId());
				CommentManager.this.setTypeSource(data.getTypeSource());
				CommentManager.this.setSourceComponentName(data.getName());
				CommentManager.this.getController().bindIdToRightComposite();
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

	@Override
	int updateInterface() {
		return 0;
	}

}
