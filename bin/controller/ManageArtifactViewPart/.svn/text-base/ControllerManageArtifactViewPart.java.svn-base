package controller.ManageArtifactViewPart;

import org.apache.log4j.Logger;

import composite.ManageArtifactViewPart.CommentContent;
import composite.ManageArtifactViewPart.CommentManager;
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
	
	
	/**
	 * Controller cho Comment
	 */
	private CommentManager commentManager;
	private CommentContent commentContent;
	private Logger logger = Logger.getLogger(this.getClass());
	
	public void setCommentManager(CommentManager commentManager) {
		this.commentManager = commentManager;
	}

	public void setCommentContent(CommentContent commentContent) {
		this.commentContent = commentContent;
	}

	public void bindIdToRightComposite() {
		String id, typeSource,sourceName;
		id = (String) commentManager.getOutputData();
		typeSource = (String) commentManager.getTypeSource();
		sourceName = commentManager.getSourceComponentName();
		commentContent.setInputData(id);
		//Ghi vao log
		logger.info(commentContent.getInputData());
		logger.info(typeSource);
		
		commentContent.addDataToComposite(id, typeSource,sourceName);
		
	}
}
