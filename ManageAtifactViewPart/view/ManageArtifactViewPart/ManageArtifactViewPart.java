package view.ManageArtifactViewPart;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;
import composite.ManageArtifactViewPart.CommentManager;
import composite.ManageArtifactViewPart.CompositeCodeChanged;
import composite.ManageArtifactViewPart.CompositeRequirement;
import composite.ManageArtifactViewPart.CompositeSourceCodeTree;
import composite.ManageArtifactViewPart.CompositeTest;
import composite.ManageArtifactViewPart.SemanticCommentManager;
import controller.ManageArtifactViewPart.ControllerManageArtifactViewPart;
import hut.composite.annotationCreatorAndEditor.CompositeListPropertiesOfInstance;

public class ManageArtifactViewPart extends ViewPart {

	public static final String ID = "view.ManageArtifactViewPart"; //$NON-NLS-1$

	/**
	 * Create contents of the view part
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		ControllerManageArtifactViewPart controller = new ControllerManageArtifactViewPart();

		FormToolkit toolkit = new FormToolkit(Display.getCurrent());

		final TabFolder tabFolder = new TabFolder(parent, SWT.NONE);
		toolkit.adapt(tabFolder, true, true);

		final TabItem tabManageRequirement = new TabItem(tabFolder, SWT.NONE);
		tabManageRequirement.setText("Manage Requirements");

		final CTabFolder tabFolder_1 = new CTabFolder(tabFolder, SWT.BOTTOM | SWT.FLAT);
		tabFolder_1.setCapture(true);
		tabFolder_1.setBorderVisible(true);
		tabFolder_1.setMRUVisible(true);
		toolkit.adapt(tabFolder_1, true, true);
		tabManageRequirement.setControl(tabFolder_1);

		final CTabItem tabItemViewAll = new CTabItem(tabFolder_1, SWT.NONE);
		tabItemViewAll.setText("View All");

		final CompositeRequirement compositeViewAllRequirement = new CompositeRequirement(tabFolder_1, SWT.NONE, 0, 0);
		toolkit.adapt(compositeViewAllRequirement);
		tabItemViewAll.setControl(compositeViewAllRequirement);



		final CTabItem tabItemFinishedRequirement = new CTabItem(tabFolder_1, SWT.NONE);

		tabItemFinishedRequirement.setText("Finished Requirement");

		final CompositeRequirement compositeViewFinishedRequirement = new CompositeRequirement(tabFolder_1, SWT.NONE, 0, 1);
		toolkit.adapt(compositeViewFinishedRequirement);
		tabItemFinishedRequirement.setControl(compositeViewFinishedRequirement);

		final CTabItem tabItemProcessingRequirement = new CTabItem(tabFolder_1, SWT.NONE);
		tabItemProcessingRequirement.setText("Processing Requirement");

		final CompositeRequirement compositeViewProcessingRequirement = new CompositeRequirement(tabFolder_1, SWT.NONE, 0, 2);
		toolkit.adapt(compositeViewProcessingRequirement);
		tabItemProcessingRequirement.setControl(compositeViewProcessingRequirement);

		tabFolder_1.setSelection(0);

		final TabItem tabManageTest = new TabItem(tabFolder, SWT.NONE);
		tabManageTest.setText("Manage Tests");

		final CTabFolder tabFolder_1_1 = new CTabFolder(tabFolder, SWT.BOTTOM | SWT.FLAT);
		tabFolder_1_1.setCapture(true);
		tabFolder_1_1.setBorderVisible(true);
		tabFolder_1_1.setMRUVisible(true);
		toolkit.adapt(tabFolder_1_1, true, true);
		tabManageTest.setControl(tabFolder_1_1);


		final CTabItem tabItemViewAllTest = new CTabItem(tabFolder_1_1, SWT.NONE);
		tabItemViewAllTest.setText("View All");

		final CTabItem tabItemSuccessfulTest = new CTabItem(tabFolder_1_1, SWT.NONE);
		tabItemSuccessfulTest.setText("Successful Test");

		final CTabItem tabItemProcessingTest = new CTabItem(tabFolder_1_1, SWT.NONE);
		tabItemProcessingTest.setText("Processing Test");

		tabFolder_1_1.setSelection(0);

		final TabItem tabManageCode = new TabItem(tabFolder, SWT.NONE);
		tabManageCode.setText("Manage Code");

		final SashForm sashForm_1 = new SashForm(tabFolder, SWT.NONE);

		final CompositeSourceCodeTree compositeSourceCodeTree = new CompositeSourceCodeTree(sashForm_1, SWT.NONE);
		toolkit.adapt(compositeSourceCodeTree);
		controller.setCompositeSourceCodeTree(compositeSourceCodeTree);
		compositeSourceCodeTree.setController(controller);

		final CompositeListPropertiesOfInstance compositeListPropertiesOfInstance = new CompositeListPropertiesOfInstance(sashForm_1, SWT.NONE);
		toolkit.adapt(compositeListPropertiesOfInstance);

		final CompositeTest compositeTest = new CompositeTest(tabFolder_1_1, SWT.NONE, 0, 0);
		toolkit.adapt(compositeTest);
		tabItemViewAllTest.setControl(compositeTest);

		final CompositeTest compositeSuccessfulTest = new CompositeTest(tabFolder_1_1, SWT.NONE, 0, 1);
		toolkit.adapt(compositeSuccessfulTest);
		tabItemSuccessfulTest.setControl(compositeSuccessfulTest);

		final CompositeTest compositeProcessingTest = new CompositeTest(tabFolder_1_1, SWT.NONE, 0, 2);
		toolkit.adapt(compositeProcessingTest);
		tabItemProcessingTest.setControl(compositeProcessingTest);

		controller.setCompositeListPropertiesOfInstance(compositeListPropertiesOfInstance);

		compositeListPropertiesOfInstance.setController(controller);
		toolkit.adapt(sashForm_1, true, true);
		tabManageCode.setControl(sashForm_1);

		final TabItem tabItemCodeChange = new TabItem(tabFolder, SWT.NONE);
		tabItemCodeChange.setText("Code Changed");

		final CompositeCodeChanged compositeCodeChanged = new CompositeCodeChanged(tabFolder, SWT.NONE);
		toolkit.adapt(compositeCodeChanged);
		tabItemCodeChange.setControl(compositeCodeChanged);

		final TabItem tabComment = new TabItem(tabFolder, SWT.NONE);
		tabComment.setText("Manage Comment");

		final CommentManager commentManager = new CommentManager(tabFolder, SWT.NONE);
		toolkit.adapt(commentManager);
		tabComment.setControl(commentManager);

		final TabItem tabSemanticComment = new TabItem(tabFolder, SWT.NONE);
		tabSemanticComment.setText("Semantic Comment");
		
		final SemanticCommentManager seCommentManager = new SemanticCommentManager(tabFolder, SWT.NONE);
		toolkit.adapt(seCommentManager);
		tabSemanticComment.setControl(seCommentManager);
		
		sashForm_1.setWeights(new int[] {1, 1 });
		//
		createActions();
		initializeToolBar();
		initializeMenu();


	}

	/**
	 * Create the actions
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar
	 */
	private void initializeToolBar() {
		IToolBarManager tbm = getViewSite().getActionBars().getToolBarManager();
	}

	/**
	 * Initialize the menu
	 */
	private void initializeMenu() {
		IMenuManager manager = getViewSite().getActionBars().getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

}
