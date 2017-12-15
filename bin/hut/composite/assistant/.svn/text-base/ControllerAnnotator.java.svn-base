package hut.composite.assistant;

import org.apache.log4j.Logger;

public class ControllerAnnotator {
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