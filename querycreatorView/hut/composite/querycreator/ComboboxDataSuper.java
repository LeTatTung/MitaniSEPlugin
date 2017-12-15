package hut.composite.querycreator;

import java.util.ArrayList;

import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Composite;

public class ComboboxDataSuper{

	private Object inputData;
	private Object outputData;
	private RowController rowController;
	private ArrayList<String> listItem;
	
	public void updateInterface(){
		
	}
	
	public Object getDataInput() {
		return inputData;
	}

	public void setDataInput(Object dataInput) {
		this.inputData = dataInput;
	}

	public Object getDataOutput() {
		return outputData;
	}

	public void setDataOutput(Object dataOutput) {
		this.outputData = dataOutput;
	}

	public RowController getRowController() {
		return rowController;
	}

	public void setRowController(RowController rowController) {
		this.rowController = rowController;
	}

	public ArrayList<String> getListItem() {
		return listItem;
	}

	public void setListItem(ArrayList<String> listItem) {
		this.listItem = listItem;
	}
	
}
