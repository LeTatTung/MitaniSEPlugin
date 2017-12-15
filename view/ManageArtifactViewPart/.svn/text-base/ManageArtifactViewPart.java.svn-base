package view.ManageArtifactViewPart;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;
import composite.ManageArtifactViewPart.CompositeSourceCodeTree;
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
		FormToolkit toolkit = new FormToolkit(Display.getCurrent());
		Composite container = toolkit.createComposite(parent, SWT.NONE);
		container.setLayout(new FormLayout());
		toolkit.paintBordersFor(container);

		final SashForm sashForm = new SashForm(container, SWT.NONE);
		final FormData fd_sashForm = new FormData();
		fd_sashForm.right = new FormAttachment(100, -5);
		fd_sashForm.left = new FormAttachment(0, 0);
		fd_sashForm.bottom = new FormAttachment(100, -5);
		fd_sashForm.top = new FormAttachment(0, 0);
		sashForm.setLayoutData(fd_sashForm);

		final CompositeSourceCodeTree compositeSourceCodeTree = new CompositeSourceCodeTree(sashForm, SWT.NONE);
		toolkit.adapt(compositeSourceCodeTree);

		final CompositeListPropertiesOfInstance compositeListPropertiesOfInstance = new CompositeListPropertiesOfInstance(sashForm, SWT.NONE);
		toolkit.adapt(compositeListPropertiesOfInstance);
		toolkit.adapt(sashForm, true, true);
		sashForm.setWeights(new int[] {169, 322 });
		//
		createActions();
		initializeToolBar();
		initializeMenu();
		
		ControllerManageArtifactViewPart controller = new ControllerManageArtifactViewPart();
		
		controller.setCompositeListPropertiesOfInstance(compositeListPropertiesOfInstance);
		controller.setCompositeSourceCodeTree(compositeSourceCodeTree);
		
		compositeListPropertiesOfInstance.setController(controller);
		compositeSourceCodeTree.setController(controller);
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
