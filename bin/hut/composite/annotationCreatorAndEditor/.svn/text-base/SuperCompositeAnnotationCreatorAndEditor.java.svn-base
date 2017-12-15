package hut.composite.annotationCreatorAndEditor;

import hut.controller.annotationCreatorAndEditor.ControllerAnnotationCreatorAndEditor;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;

abstract class SuperCompositeAnnotationCreatorAndEditor extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Object outputData;
	private Object inputData;
	private ControllerAnnotationCreatorAndEditor controller;
	


	public ControllerAnnotationCreatorAndEditor getController() {
		return controller;
	}


	

	public void setController(ControllerAnnotationCreatorAndEditor controller) {
		this.controller = controller;
	}


	abstract int updateInterface();

	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public SuperCompositeAnnotationCreatorAndEditor(Composite parent, int style) {
		super(parent, style);
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		//
	}



	public Object getOutputData() {
		return outputData;
	}



	public void setOutputData(Object outputData) {
		this.outputData = outputData;
	}



	public Object getInputData() {
		return inputData;
	}



	public void setInputData(Object inputData) {
		this.inputData = inputData;
	}

}
