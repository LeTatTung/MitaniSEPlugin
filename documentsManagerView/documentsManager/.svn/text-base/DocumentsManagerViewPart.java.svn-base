package documentsManager;


import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import documentsManager.ContentComposite;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;
import documentsManager.HeadingTreeCompostite;

public class DocumentsManagerViewPart extends ViewPart {

	public static final String ID = "documentsManager.DocumentsManagerViewPart"; //$NON-NLS-1$

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

		final SashForm sashForm = new SashForm(container, SWT.NONE);

		final HeadingTreeCompostite headingTreeCompostite = new HeadingTreeCompostite(sashForm, SWT.NONE);
		toolkit.adapt(headingTreeCompostite);

		final ContentComposite contentComposite = new ContentComposite(sashForm, SWT.BORDER);
		toolkit.adapt(contentComposite);
		toolkit.adapt(sashForm, true, true);
		sashForm.setWeights(new int[] {268, 268 });
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
