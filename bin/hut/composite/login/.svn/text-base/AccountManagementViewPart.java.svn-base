package hut.composite.login;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;
import hut.composite.login.CompositeLogin;

public class AccountManagementViewPart extends ViewPart {

	public static final String ID = "hut.composite.login.AccountManagementViewPart";

	/**
	 * Create contents of the view part
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		FormToolkit toolkit = new FormToolkit(Display.getCurrent());
		Composite container = toolkit.createComposite(parent, SWT.NONE);
		container.setLayout(new FillLayout());
		toolkit.paintBordersFor(container);

		final Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new FillLayout());
		toolkit.adapt(composite);

		final CompositeLogin compositeLogin = new CompositeLogin(composite, SWT.NONE);
		toolkit.adapt(compositeLogin);

		createActions();
		initializeToolBar();
		initializeMenu();
	}

	/**
	 * Create the actions
	 */
	private void createActions() {
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
	}

}
