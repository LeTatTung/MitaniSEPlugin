package hut.composite.assistant;

import org.apache.log4j.Logger;

public class SeControllerAnnotator {
  private SeCommentManager seCommentManager;
  private SeCommentContent seCommentContent;
  private Logger logger = Logger.getLogger(this.getClass());

  public void setCommentManager(SeCommentManager seCommentManager) {
    this.seCommentManager = seCommentManager;
  }

  public void setCommentContent(SeCommentContent seCommentContent) {
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
