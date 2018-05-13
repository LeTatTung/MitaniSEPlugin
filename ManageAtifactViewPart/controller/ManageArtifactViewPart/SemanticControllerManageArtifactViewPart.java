package controller.ManageArtifactViewPart;

import org.apache.log4j.Logger;

import composite.ManageArtifactViewPart.CompositeSourceCodeTree;
import composite.ManageArtifactViewPart.SemanticCommentContent;
import composite.ManageArtifactViewPart.SemanticCommentManager;
import hut.composite.annotationCreatorAndEditor.CompositeListPropertiesOfInstance;
import hut.controller.annotationCreatorAndEditor.ControllerAnnotationCreatorAndEditor;


public class SemanticControllerManageArtifactViewPart extends ControllerAnnotationCreatorAndEditor{


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
  private SemanticCommentManager seCommentManager;
  private SemanticCommentContent seCommentContent;
  private Logger logger = Logger.getLogger(this.getClass());

  public void setCommentManager(SemanticCommentManager seCommentManager) {
    this.seCommentManager = seCommentManager;
  }

  public void setCommentContent(SemanticCommentContent seCommentContent) {
    this.seCommentContent = seCommentContent;
  }

  public void bindIdToRightComposite() {
    String id, typeSource,sourceName;
    id = (String) seCommentManager.getOutputData();
    typeSource = (String) seCommentManager.getTypeSource();
    sourceName = seCommentManager.getSourceComponentName();
    seCommentContent.setInputData(id);
    //Ghi vao log
    logger.info(seCommentContent.getInputData());
    logger.info(typeSource);

    seCommentContent.addDataToComposite(id, typeSource,sourceName);

  }
}
