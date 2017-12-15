package composite.ManageArtifactViewPart;

//import hut.controller.annotationCreatorAndEditor.ControllerAnnotationCreatorAndEditor;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;

import controller.ManageArtifactViewPart.ControllerManageArtifactViewPart;

public abstract class SuperComposite extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Object outputData;
	private Object inputData;
	private ControllerManageArtifactViewPart controller;
	


	public ControllerManageArtifactViewPart getController() {
		return controller;
	}


	

	public void setController(ControllerManageArtifactViewPart controller) {
		this.controller = controller;
	}


	abstract int updateInterface();

	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public SuperComposite(Composite parent, int style) {
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
