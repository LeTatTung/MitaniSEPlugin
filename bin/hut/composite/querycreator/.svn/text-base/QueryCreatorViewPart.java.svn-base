package hut.composite.querycreator;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import hut.composite.querycreator.CompositeVariable;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;
import hut.composite.querycreator.CompositeRestriction;
import hut.composite.querycreator.CompositeResult;

public class QueryCreatorViewPart extends ViewPart {

	public static final String ID = "hut.composite.querycreator.QueryCreatorViewPart"; //$NON-NLS-1$

	/**
	 * Create contents of the view part
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FormLayout());
		FormToolkit toolkit = new FormToolkit(Display.getCurrent());

		final TabFolder tabFolder = new TabFolder(parent, SWT.NONE);
		final FormData fd_tabFolder = new FormData();
		fd_tabFolder.bottom = new FormAttachment(100, -5);
		fd_tabFolder.right = new FormAttachment(100, -5);
		fd_tabFolder.top = new FormAttachment(0, 0);
		fd_tabFolder.left = new FormAttachment(0, 0);
		tabFolder.setLayoutData(fd_tabFolder);
		toolkit.adapt(tabFolder, true, true);

		final TabItem variableTabItem = new TabItem(tabFolder, SWT.NONE);
		variableTabItem.setText("Variable");

		final TabItem restrictionTabItem = new TabItem(tabFolder, SWT.NONE);
		restrictionTabItem.setText("Restriction");

		final TabItem resultTabItem = new TabItem(tabFolder, SWT.NONE);
		resultTabItem.setText("Result");

		final CompositeResult compositeResult = new CompositeResult(tabFolder, SWT.NONE);
		toolkit.adapt(compositeResult);
		resultTabItem.setControl(compositeResult);

		final CompositeRestriction compositeRestriction = new CompositeRestriction(tabFolder, SWT.NONE);
		toolkit.adapt(compositeRestriction);
		restrictionTabItem.setControl(compositeRestriction);
		
		System.out.println(compositeRestriction.variableMap.keySet().toString());
		compositeRestriction.addRestrictionRow("", "", "", "");

		final CompositeVariable compositeVariable = new CompositeVariable(tabFolder, SWT.NONE);
		compositeVariable.addVariableRow("", "");
		toolkit.adapt(compositeVariable);
		variableTabItem.setControl(compositeVariable);
		
		QueryController queryController = new QueryController();
		queryController.setCompositeRestriction(compositeRestriction);
		queryController.setCompositeResult(compositeResult);
		queryController.setCompositeVariable(compositeVariable);
		
		compositeRestriction.setQueryController(queryController);
		compositeResult.setQueryController(queryController);
		compositeVariable.setQueryController(queryController);
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
