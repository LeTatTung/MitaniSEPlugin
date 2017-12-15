package hut.composite.admin;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import ws.owl.DataTypePropertyMapData;
import ws.owl.InstanceData;
import ws.owl.ObjectPropertyMapData;

public class CompositeInstanceData extends Composite {

	private InstanceData instanceData;
	private TableColumn propertyTableColumn; 
	private TableColumn valueTableColumn;
	private Table table;
	private Text text;
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	private Button buttonfullURI; 
	
	public CompositeInstanceData(Composite parent, int style) {
		super(parent, style);
		setLayout(new FormLayout());
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);

		buttonfullURI = new Button(this, SWT.CHECK);
		buttonfullURI.setText("full URI");
		buttonfullURI.setSelection(true);
		final FormData fd_buttonfullURI = new FormData();
		fd_buttonfullURI.top = new FormAttachment(0, 5);
		fd_buttonfullURI.left = new FormAttachment(0, 5);
		buttonfullURI.setLayoutData(fd_buttonfullURI);
		toolkit.adapt(buttonfullURI, true, true);
		
		final Label label = new Label(this, SWT.NONE);
		final FormData fd_label = new FormData();
		fd_label.top = new FormAttachment(buttonfullURI, 5, SWT.BOTTOM);
		fd_label.left = new FormAttachment(buttonfullURI, 0, SWT.LEFT);
		label.setLayoutData(fd_label);
		toolkit.adapt(label, true, true);
		label.setText("ID :");

		text = new Text(this, SWT.NONE);
		final FormData fd_text = new FormData();
		fd_text.right = new FormAttachment(100, -5);
		fd_text.top = new FormAttachment(label, 0, SWT.TOP);
		fd_text.left = new FormAttachment(label, 5, SWT.RIGHT);
		text.setLayoutData(fd_text);
		toolkit.adapt(text, true, true);

		table = new Table(this, SWT.BORDER);
		final FormData fd_table = new FormData();
		fd_table.bottom = new FormAttachment(100, -5);
		fd_table.right = new FormAttachment(text, 0, SWT.RIGHT);
		fd_table.top = new FormAttachment(label, 5, SWT.BOTTOM);
		fd_table.left = new FormAttachment(label, 0, SWT.LEFT);
		table.setLayoutData(fd_table);
		toolkit.adapt(table, true, true);
		table.setLinesVisible(false);
		table.setHeaderVisible(true);

		propertyTableColumn = new TableColumn(table, SWT.NONE);
		propertyTableColumn.setWidth(189);
		propertyTableColumn.setText("Property");

		valueTableColumn = new TableColumn(table, SWT.NONE);
		valueTableColumn.setWidth(296);
		valueTableColumn.setText("Value");
		
	}

	public InstanceData getInstanceData() {
		return instanceData;
	}

	public void setInstanceData(InstanceData instanceData) {
		this.instanceData = instanceData;
	}

	public void updateTable() {
		List<DataTypePropertyMapData> dataPropertyList = instanceData.getDataPropertyList();
		List<ObjectPropertyMapData> objectPropertyList = instanceData.getObjectPropertyList();
		
		for (DataTypePropertyMapData dataTypePropertyMapData : dataPropertyList){
			addRow(dataTypePropertyMapData.getPropertyName(),dataTypePropertyMapData.getValue());
		}
		
		for (ObjectPropertyMapData objectPropertyMapData : objectPropertyList){
			addRow(objectPropertyMapData.getPropertyName(),objectPropertyMapData.getIndividualValue());
		}
		
	}

	private void addRow(String propertyName, String value) {
		
		TableItem item = new TableItem(table,SWT.NONE);
		TableEditor editor = new TableEditor(table);
		Text textProperty = new Text(table,SWT.NONE);
		textProperty.setText(propertyName.substring(propertyName.indexOf("#") + 1));
		editor.grabHorizontal = true;
		editor.setEditor(textProperty, item, 0);
		
		Text textValue = new Text(table,SWT.NONE);
		textValue.setText(value);
		editor = new TableEditor(table);
		editor.grabHorizontal = true;
		editor.setEditor(textValue, item, 1);
	}

}
