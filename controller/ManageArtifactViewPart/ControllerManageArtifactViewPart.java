package controller.ManageArtifactViewPart;

import composite.ManageArtifactViewPart.CompositeSourceCodeTree;

import hut.composite.annotationCreatorAndEditor.CompositeListPropertiesOfInstance;
import hut.controller.annotationCreatorAndEditor.ControllerAnnotationCreatorAndEditor;


public class ControllerManageArtifactViewPart extends ControllerAnnotationCreatorAndEditor{
	
	
	private CompositeListPropertiesOfInstance compositeListPropertiesOfInstance;
	private CompositeSourceCodeTree compositeSourceCodeTree;
	
	
	//------ Get & Set method ---------------------------------------------------------
	public CompositeListPropertiesOfInstance getCompositeListPropertiesOfInstance() {
		return compositeListPropertiesOfInstance;
	}
	public void setCompositeListPropertiesOfInstance(
			CompositeListPropertiesOfInstance compositeListPropertiesOfInstance) {
		this.compositeListPropertiesOfInstance = compositeListPropertiesOfInstance;
	}
	public CompositeSourceCodeTree getCompositeSourceCodeTree() {
		return compositeSourceCodeTree;
	}
	public void setCompositeSourceCodeTree(
			CompositeSourceCodeTree compositeSourceCodeTree) {
		this.compositeSourceCodeTree = compositeSourceCodeTree;
	}
	//---------------------------------------------------------------------------------
	public void updateChoosenSourceCode(){
		getCompositeListPropertiesOfInstance().setInputData(getCompositeSourceCodeTree().getOutputData());
		getCompositeListPropertiesOfInstance().updateInterface();
	}
	

}
