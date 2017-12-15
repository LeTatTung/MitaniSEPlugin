package hut.composite.admin;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;
import hut.composite.admin.CompositeAdmin;

public class AdminView extends ViewPart {

	public static final String ID = "hut.composite.admin.AdminView"; //$NON-NLS-1$

	/**
	 * Create contents of the view part
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		FormToolkit toolkit = new FormToolkit(Display.getCurrent());
		Composite container = toolkit.createComposite(parent, SWT.NONE);
		container.setLayout(new FormLayout());
		toolkit.paintBordersFor(container);

		final CompositeAdmin compositeAdmin = new CompositeAdmin(container, SWT.NONE);
		final FormData fd_compositeAdmin = new FormData();
		fd_compositeAdmin.bottom = new FormAttachment(100, -5);
		fd_compositeAdmin.right = new FormAttachment(100, -5);
		fd_compositeAdmin.top = new FormAttachment(0, 5);
		fd_compositeAdmin.left = new FormAttachment(0, 5);
		compositeAdmin.setLayoutData(fd_compositeAdmin);
		compositeAdmin.setLayout(new FormLayout());
		toolkit.adapt(compositeAdmin);
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
