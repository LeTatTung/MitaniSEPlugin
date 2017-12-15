package minitaniseplugin.views.testView;

import hut.composite.test.CompositeSearchTest;
import hut.composite.test.CompositeViewTest;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.part.ViewPart;

public class TestView extends ViewPart {
	
	private CompositeViewTest compositeViewTest;
	private CompositeSearchTest compositeSearchTest;
	public static final String ID = "minitaniseplugin.views.testView.TestView"; //$NON-NLS-1$

	/**
	 * Create contents of the view part
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		final TabFolder tabFolder = new TabFolder(parent, SWT.BOTTOM);

		final TabItem viewTestTabItem = new TabItem(tabFolder, SWT.NONE);
		viewTestTabItem.setText("  View Test     ");

		compositeViewTest = new CompositeViewTest(tabFolder, SWT.NONE);
		viewTestTabItem.setControl(compositeViewTest);

		final TabItem searchTestTabItem = new TabItem(tabFolder, SWT.NONE);
		searchTestTabItem.setText("  Search Test   ");

		compositeSearchTest = new CompositeSearchTest(tabFolder, SWT.NONE);
		searchTestTabItem.setControl(compositeSearchTest);
		
		
		
		
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
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
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
