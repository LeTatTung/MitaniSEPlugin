package hut.composite.annotationCreatorAndEditor;

import java.util.List;

import org.eclipse.swt.SWT;
import hut.composite.annotationCreatorAndEditor.CompositeLeftPopUp;
import hut.composite.annotationCreatorAndEditor.CompositeListInstanceOfClass;
import hut.composite.annotationCreatorAndEditor.CompositeListPropertiesOfInstance;
import hut.controller.annotationCreatorAndEditor.ControllerAnnotationCreatorAndEditor;

import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;

public class CompositePopUp extends SuperCompositeAnnotationCreatorAndEditor {
	
	private CompositeLeftPopUp compositeLeftPopUp;
	private CompositeListInstanceOfClass compositeInstanceListOfClass;
	private CompositeListPropertiesOfInstance compositeInstancePropertiesList;
	private ControllerAnnotationCreatorAndEditor controller;
	
	
	public CompositePopUp(Composite parent, int style, List<String> classList) {
		super(parent, style);
		setLayout(new FormLayout());

		final SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setLayout(new FormLayout());
		final FormData fd_sashForm = new FormData();
		fd_sashForm.right = new FormAttachment(100, 0);
		fd_sashForm.bottom = new FormAttachment(100, 0);
		fd_sashForm.top = new FormAttachment(0, 0);
		fd_sashForm.left = new FormAttachment(0, 0);
		sashForm.setLayoutData(fd_sashForm);

		System.out.println("before new CompositeLeftPopUp");
		compositeLeftPopUp = new CompositeLeftPopUp(sashForm, SWT.NONE, classList);
		System.out.println("after new CompositeLeftPopUp");
		
//		compositeLeftPopUp.setInputData(classList);
//		compositeLeftPopUp.updateInterface();
		
		final FormData fd_compositeLeftPopUp = new FormData();
		fd_compositeLeftPopUp.bottom = new FormAttachment(0, 375);
		fd_compositeLeftPopUp.top = new FormAttachment(0, 0);
		fd_compositeLeftPopUp.right = new FormAttachment(0, 164);
		fd_compositeLeftPopUp.left = new FormAttachment(0, 0);
		compositeLeftPopUp.setLayoutData(fd_compositeLeftPopUp);

		compositeInstanceListOfClass = new CompositeListInstanceOfClass(sashForm, SWT.NONE);
		final FormData fd_compositeInstanceListOfClass = new FormData();
		fd_compositeInstanceListOfClass.bottom = new FormAttachment(0, 375);
		fd_compositeInstanceListOfClass.top = new FormAttachment(0, 0);
		fd_compositeInstanceListOfClass.right = new FormAttachment(0, 331);
		fd_compositeInstanceListOfClass.left = new FormAttachment(0, 167);
		compositeInstanceListOfClass.setLayoutData(fd_compositeInstanceListOfClass);
		// Them SelectItem vao ToolBar
		compositeInstanceListOfClass.addSelectItemOnToolBar();

		compositeInstancePropertiesList = new CompositeListPropertiesOfInstance(sashForm, SWT.NONE);
		final FormData fd_compositeInstancePropertiesList = new FormData();
		fd_compositeInstancePropertiesList.bottom = new FormAttachment(0, 375);
		fd_compositeInstancePropertiesList.top = new FormAttachment(0, 0);
		fd_compositeInstancePropertiesList.right = new FormAttachment(0, 500);
		fd_compositeInstancePropertiesList.left = new FormAttachment(0, 334);
		compositeInstancePropertiesList.setLayoutData(fd_compositeInstancePropertiesList);
		
		sashForm.setWeights(new int[] {1, 1, 1 });

		controller = new ControllerAnnotationCreatorAndEditor(compositeLeftPopUp, compositeInstanceListOfClass, compositeInstancePropertiesList);
		controller.setCompositePopUp(this);
		compositeLeftPopUp.setController(controller);
		compositeInstanceListOfClass.setController(controller);
		compositeInstancePropertiesList.setController(controller);
		
	}

	@Override
	int updateInterface() {
		
		return 0;
	}

}
