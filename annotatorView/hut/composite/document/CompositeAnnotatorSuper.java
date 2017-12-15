package hut.composite.document;
import org.eclipse.swt.widgets.Composite;

/**
 * @author KienCuong
 * Class duoc dung de cac composite khac ke thua no
 */
public class CompositeAnnotatorSuper extends Composite {

	private Object inputData;
	private Object outputData;
	private String currentBookmark;
	private String currentPathFile;
	private ControllerAnnotator controller;
	private String status;
	
	public CompositeAnnotatorSuper(Composite parent, int style) {
		super(parent, style);
	}
	public void setController(ControllerAnnotator controller) {
		this.controller = controller;
	}
	
	public Object getInputData() {
		return inputData;
	}
	public void setInputData(Object inputData) {
		this.inputData = inputData;
	}
	public Object getOutputData() {
		return outputData;
	}
	public void setOutputData(Object outputData) {
		this.outputData = outputData;
	}
	public void updateInterface()
	{
	}
	public ControllerAnnotator getController() {
		return controller;
	}
	public String getCurrentBookmark() {
		return currentBookmark;
	}
	public void setCurrentBookmark(String currentBookmark) {
		this.currentBookmark = currentBookmark;
	}
	public String getCurrentPathFile() {
		return currentPathFile;
	}
	public void setCurrentPathFile(String currentPathFile) {
		this.currentPathFile = currentPathFile;
	}
	

}
