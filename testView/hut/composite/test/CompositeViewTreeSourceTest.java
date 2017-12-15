package hut.composite.test;

import hut.composite.assistant.CommentManager;
import hut.composite.packageExplorer.JobBuildSourceCode;
import hut.composite.tree.TreeContentProvider;
import hut.composite.tree.TreeLabelProvider;
import hut.composite.tree.TreeNodeData;
import hut.composite.tree.TreeObject;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.forms.widgets.FormToolkit;
import com.swtdesigner.SWTResourceManager;
import swing2swt.layout.BorderLayout;

public class CompositeViewTreeSourceTest extends Composite {

	private Tree tree_left;
	private Tree tree_right;
	private TreeViewer treeViewerLeft;
	private TreeViewer treeViewerRight;
	private Button selectButton;
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
   
	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public CompositeViewTreeSourceTest(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout());
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		//
		
		

		final SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		toolkit.adapt(sashForm, true, true);

		final Composite composite_2 = new Composite(sashForm, SWT.NONE);
		composite_2.setLayout(new FillLayout());
		toolkit.adapt(composite_2);

		final Composite composite = new Composite(composite_2, SWT.NONE);
		composite.setLayout(new FillLayout());
		toolkit.adapt(composite);
		//cay ben trai
		treeViewerLeft = new TreeViewer(composite, SWT.NONE);
		treeViewerLeft.setContentProvider(new TreeContentProvider());
		treeViewerLeft.setLabelProvider(new TreeLabelProvider());
		tree_left = treeViewerLeft.getTree();
		toolkit.adapt(tree_left, true, true);

		final Composite composite_1 = new Composite(composite_2, SWT.NONE);
		composite_1.setLayout(new FillLayout());
		toolkit.adapt(composite_1);
		
		//cay ben phai
		treeViewerRight = new TreeViewer(composite_1, SWT.NONE);
		treeViewerRight.setContentProvider(new TreeContentProvider());
		treeViewerRight.setLabelProvider(new TreeLabelProvider());
		tree_right = treeViewerRight.getTree();
		toolkit.adapt(tree_right, true, true);

		final Composite composite_3 = toolkit.createComposite(sashForm, SWT.NONE);
		toolkit.paintBordersFor(composite_3);

		selectButton = toolkit.createButton(composite_3, "Select", SWT.NONE);
		selectButton.setImage(SWTResourceManager.getImage(CompositeViewTreeSourceTest.class, "/ontology/images/run.gif"));
		selectButton.setBounds(200, 10, 77, 25);
		sashForm.setWeights(new int[] {328, 44 });
		
		
		
		//ham khoi tao cay
		creatTree();
		
		//Khai bao nhan su kien cho cay
		
		registerAction();
	}
	
	public void creatTree() {		
		
		JobBuildSourceCode jobBuildSourceCode = new JobBuildSourceCode("Build sourcetree",treeViewerLeft,treeViewerRight);		
		jobBuildSourceCode.setUser(true);
		jobBuildSourceCode.schedule();	
		
	}
	
	
	
	/**
	 * @param action
	 * Su kien kich dup chuot
	 */
	public void registerDoubleClickAction(final Action action,final Action action2) {
		treeViewerLeft.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(final DoubleClickEvent arg0) {
				action.run();
			}
		});
		
		treeViewerRight.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(final DoubleClickEvent arg0) {
				action2.run();
			}
		});
	}
	
	/**
	 * Ham khai bao cac su kien cua cay
	 */
	public void registerAction() {
		Action action;
		action = new Action() {
			public void run() {
				IStructuredSelection selection = (IStructuredSelection) treeViewerLeft
						.getSelection();
				TreeObject obj = (TreeObject) selection.getFirstElement();
				TreeNodeData data = (TreeNodeData) obj.getData();
				
			}
		};
		
		//Su kien cay ben phai
		
		Action action2;
		action2 = new Action() {
			public void run() {
				IStructuredSelection selection = (IStructuredSelection) treeViewerRight
						.getSelection();
				TreeObject obj = (TreeObject) selection.getFirstElement();
				TreeNodeData data = (TreeNodeData) obj.getData();
				
			}
		};
		registerDoubleClickAction(action,action2);
		
		//Su kien chon button select
		selectButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				selectButton.getShell().dispose();
			}
		});	
		
		selectButton.getShell().addListener(SWT.Close, new Listener() {
			public void handleEvent(Event e) {
				selectButton.getShell().dispose();
			}
		});	
	}

}
