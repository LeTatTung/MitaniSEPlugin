package hut.composite.assistant;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Composite;
 
/**
 * @author KienCuong
 * Class duoc dung de cac composite khac ke thua no
 */
public class CompositeAnnotatorSuper extends Composite {

	private Object inputData;
	private Object outputData;
	private String typeSource;//Truyen vao xem no loai gi--Method,Class,..
	private String sourceComponentName;//Ten cua source component
	private ControllerAnnotator controller;
	
	
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
	
	public String getTypeSource() {
		return typeSource;
	}
	public void setTypeSource(String typeSource) {
		this.typeSource = typeSource;
	}
	
	public String getSourceComponentName() {
		return sourceComponentName;
	}
	public void setSourceComponentName(String sourceComponentName) {
		this.sourceComponentName = sourceComponentName;
	}
	public void updateInterface()
	{
	}
	public ControllerAnnotator getController() {
		return controller;
	}
	

}
