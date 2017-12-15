package mitaniseplugin.view.manageArtifactView;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import hut.composite.test.CompositeViewTest;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;
import hut.composite.manageDocument.CompositeManageDocument;
import hut.composite.requirement.CompositeViewRequirement;

public class ManageArtifactView extends ViewPart {

	public static final String ID = "mitaniseplugin.view.manageArtifactView.ManageArtifactView"; //$NON-NLS-1$

	/**
	 * Create contents of the view part
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		FormToolkit toolkit = new FormToolkit(Display.getCurrent());
		final TabFolder tabFolder = new TabFolder(parent, SWT.BOTTOM);
		toolkit.adapt(tabFolder, true, true);

		final TabItem manageRequirementTabItem = new TabItem(tabFolder, SWT.NONE);
		manageRequirementTabItem.setText("Manage Requirement");

		final CompositeViewRequirement compositeViewRequirement = new CompositeViewRequirement(tabFolder, SWT.NONE);
		compositeViewRequirement.setLayout(new FillLayout());
		compositeViewRequirement.creatComponentLayoutManage();
		toolkit.adapt(compositeViewRequirement);
		manageRequirementTabItem.setControl(compositeViewRequirement);

		final TabItem manageTestTabItem = new TabItem(tabFolder, SWT.NONE);
		manageTestTabItem.setText("Manage Test");

		final CompositeViewTest compositeViewTest = new CompositeViewTest(tabFolder, SWT.NONE);
		compositeViewTest.createComponentLayoutManage();
		compositeViewTest.setLayout(new FillLayout());
		toolkit.adapt(compositeViewTest);
		manageTestTabItem.setControl(compositeViewTest);

		final TabItem manageDocumentTabItem = new TabItem(tabFolder, SWT.NONE);
		manageDocumentTabItem.setText("Manage Document");

		final CompositeManageDocument compositeManageDocument = new CompositeManageDocument(tabFolder, SWT.NONE);
		toolkit.adapt(compositeManageDocument);
		manageDocumentTabItem.setControl(compositeManageDocument);
		
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
