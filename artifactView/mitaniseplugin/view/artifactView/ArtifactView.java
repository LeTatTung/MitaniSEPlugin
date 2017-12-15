package mitaniseplugin.view.artifactView;

import hut.composite.requirement.CompositeSearchRequirement;
import hut.composite.requirement.CompositeViewRequirement;
import hut.composite.test.CompositeSearchTest;
import hut.composite.test.CompositeViewTest;
import hut.views.notification.ViewNotification;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;
import hut.composite.searchDocument.CompositeSearchDocument;

public class ArtifactView extends ViewPart {
	
	private CompositeViewRequirement compositeViewRequirement;
	private CompositeSearchRequirement compositeSearchRequirement;
	private CompositeViewTest compositeViewTest;
	private CompositeSearchTest compositeSearchTest;
	private ViewNotification viewNotification;
	public static final String ID = "mitaniseplugin.view.RequirementView.RequirementView"; //$NON-NLS-1$

	/**
	 * Create contents of the view part
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		final TabFolder tabFolder = new TabFolder(parent, SWT.BOTTOM);

		final TabItem searchDocumentTabItem = new TabItem(tabFolder, SWT.NONE);
		searchDocumentTabItem.setText("search Document");

		final CompositeSearchDocument compositeSearchDocument = new CompositeSearchDocument(tabFolder, SWT.NONE);
		searchDocumentTabItem.setControl(compositeSearchDocument);

		final TabItem viewRequirementTabItem = new TabItem(tabFolder, SWT.NONE);
		viewRequirementTabItem.setText(" Requirement View    ");

		compositeViewRequirement = new CompositeViewRequirement(tabFolder, SWT.NONE);
		compositeViewRequirement.setLayout(new FillLayout());
		viewRequirementTabItem.setControl(compositeViewRequirement);

		final TabItem searchRequirementTabItem = new TabItem(tabFolder, SWT.NONE);
		searchRequirementTabItem.setText("  Search Requirement   ");

		compositeSearchRequirement = new CompositeSearchRequirement(tabFolder, SWT.NONE);
		searchRequirementTabItem.setControl(compositeSearchRequirement);

		final TabItem viewTestTabItem = new TabItem(tabFolder, SWT.NONE);
		viewTestTabItem.setText("  Test View     ");

		compositeViewTest = new CompositeViewTest(tabFolder, SWT.NONE);
		compositeViewTest.setLayout(new FillLayout());
		viewTestTabItem.setControl(compositeViewTest);

		final TabItem searchTestTabItem = new TabItem(tabFolder, SWT.NONE);
		searchTestTabItem.setText("  Search Test   ");

		compositeSearchTest = new CompositeSearchTest(tabFolder, SWT.NONE);
		searchTestTabItem.setControl(compositeSearchTest);

		final TabItem viewNotificationTabItem = new TabItem(tabFolder, SWT.NONE);
		viewNotificationTabItem.setText(" Notification View ");

		viewNotification= new ViewNotification(tabFolder, SWT.NONE);
		
		viewNotificationTabItem.setControl(viewNotification);
		
		
		
		
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
		IToolBarManager toolbarManager = getViewSite().getActionBars().getToolBarManager();
	}

	/**
	 * Initialize the menu
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

}
