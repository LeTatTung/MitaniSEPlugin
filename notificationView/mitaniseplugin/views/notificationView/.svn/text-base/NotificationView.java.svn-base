package mitaniseplugin.views.notificationView;

import hut.views.notification.ViewNotification;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;

public class NotificationView extends ViewPart {
	
	private ViewNotification viewNotification;
	public static final String ID = "mitaniseplugin.views.notificationView.NotificationView"; //$NON-NLS-1$

	/**
	 * Create contents of the view part
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		final TabFolder tabFolder = new TabFolder(parent, SWT.BOTTOM);
	

		final TabItem viewNotificationTabItem = new TabItem(tabFolder, SWT.NONE);
		viewNotificationTabItem.setText("View Notification");

		viewNotification= new ViewNotification(tabFolder, SWT.NONE);
		
		viewNotificationTabItem.setControl(viewNotification);

		final TabItem manageNotificationTabItem = new TabItem(tabFolder, SWT.NONE);
		manageNotificationTabItem.setText("Manage Notification");
		FormToolkit toolkit = new FormToolkit(Display.getCurrent());
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
