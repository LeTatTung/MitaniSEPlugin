package hut.composite.metricChart;

import ontology.images.Images;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class RowChartItem extends CLabel{
	private String text= "";
	private int value;
	// type duoc dinh nghia trong ontology.Images
	private String type;
	//Xac dinh ten cua thanh phan cha
	private String parentName;
	// xac dinh uri cua thanh phan chua
	private String parentURI;
	public RowChartItem(Composite parent, int style, String text, String type, int value,String parentName, String parentURI)
	{
		super(parent, style | SWT.BORDER);
		super.setText(text);
		super.setAlignment(SWT.CENTER);
		setCursor(new Cursor(Display.getCurrent(), SWT.CURSOR_HAND));
		this.text = text;
		this.type = type;
		this.value = value;
		this.parentURI = parentURI;
		this.parentName = parentName;
		//String parentLocalName = parentURI.substring(parentURI.indexOf('#')+1);
		String parentLocalName = parentName;
		
		if(this.type.equals(Images.CLASS_OF_PACKAGE))
		{
			super.setBackground(new Color(Display.getCurrent(),133,205,51));
			this.setToolTipText("Package: " + parentLocalName + " \n has " + text + " classes");
		}
		if(this.type.equals(Images.METHOD))
		{
			super.setBackground(new Color(Display.getCurrent(),133,205,51));
			this.setToolTipText("Class: " + parentLocalName + " \n has " + text + " methods");
		}
		else if(this.type.equals(Images.METHOD_OF_PACKAGE))
		{
			super.setBackground(new Color(Display.getCurrent(),76,178,181));
			this.setToolTipText("Package: " + parentLocalName +"\n has " + text + " methods");
		}
		else if(this.type.equals(Images.JAVA))
		{			
			super.setBackground(new Color(Display.getCurrent(),238,162,63));
			this.setToolTipText("Package: "+ parentLocalName +"\n has " + text + " classes");
		}
		else if(this.type.equals(Images.FIELD))
		{			
			super.setBackground(new Color(Display.getCurrent(),162,227,246));
			this.setToolTipText("Class: "+ parentLocalName +"\n has " + text + " fields");
		}
		else if(this.type.equals(Images.PACKAGE_FIELD))
		{			
			super.setBackground(new Color(Display.getCurrent(),162,227,246));
			this.setToolTipText("Package: "+ parentLocalName +"\n has " + text + " fields");
		}
		else if(this.type.equals(Images.INTERFACE))
		{
			super.setBackground(new Color(Display.getCurrent(),102,102,154));
			this.setToolTipText("Package: "+ parentLocalName +"\n has " + text + " interfaces");
		}
		else if(this.type.equals(Images.INTERFACE_IMPLEMENTED))
		{
			super.setBackground(new Color(Display.getCurrent(),167,208,80));
			this.setToolTipText("Class: "+ parentLocalName +" \n has implemented " + text + " interfaces");
		}
		else if(this.type.equals(Images.USESCLASS))
		{
			super.setBackground(new Color(Display.getCurrent(),230,209,35));
			this.setToolTipText("Class: "+ parentLocalName +"\n has "+ text +" \n class uses this class");
		}
		else
		{
			super.setBackground(new Color(Display.getCurrent(),230,209,35));
		}
		super.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
	}
	public String getText() {
		return text;
	}	
	public void setText(String text) {
		this.text = text;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getParentURI() {
		return parentURI;
	}
	public void setParentURI(String parentURI) {
		this.parentURI = parentURI;
	}	
}
