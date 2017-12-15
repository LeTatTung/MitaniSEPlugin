package hut.view.annotationCreatorAndEditor;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FormAttachment;
import hut.composite.annotationCreatorAndEditor.CompositeClassList;
import hut.composite.annotationCreatorAndEditor.CompositeListInstanceOfClass;
import hut.composite.annotationCreatorAndEditor.CompositeListPropertiesOfInstance;
import hut.controller.annotationCreatorAndEditor.ControllerAnnotationCreatorAndEditor;

import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;

public class ViewAnnotationCreatorAndEditor extends ViewPart {

	public static final String ID = "hut.view.annotationCreator.ViewAnnotationCreatorAndEditor"; //$NON-NLS-1$

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

		final CompositeClassList compositeClassList = new CompositeClassList(sashForm, SWT.NONE);
		toolkit.adapt(compositeClassList);
		sashForm.setLayout(new FormLayout());
		final FormData fd_sashForm = new FormData();
		fd_sashForm.bottom = new FormAttachment(100, -5);
		fd_sashForm.right = new FormAttachment(100, -5);
		fd_sashForm.left = new FormAttachment(0, 5);
		fd_sashForm.top = new FormAttachment(0, 5);
		sashForm.setLayoutData(fd_sashForm);
		toolkit.adapt(sashForm, true, true);

		final CompositeListInstanceOfClass compositeInstanceListOfClass = new CompositeListInstanceOfClass(sashForm, SWT.NONE);
		toolkit.adapt(compositeInstanceListOfClass);

		final CompositeListPropertiesOfInstance compositeInstancePropertiesList = new CompositeListPropertiesOfInstance(sashForm, SWT.NONE);
		toolkit.adapt(compositeInstancePropertiesList);
		sashForm.setWeights(new int[] {177, 210, 334 });
		
		//--------------------------- Init Controller and setController() for composites -----------------------------------------------------------------------------------------------------
		//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		ControllerAnnotationCreatorAndEditor controller = new ControllerAnnotationCreatorAndEditor(compositeClassList, compositeInstanceListOfClass, compositeInstancePropertiesList);
		compositeClassList.setController(controller);
		compositeInstanceListOfClass.setController(controller);
		compositeInstancePropertiesList.setController(controller);
		//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
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
