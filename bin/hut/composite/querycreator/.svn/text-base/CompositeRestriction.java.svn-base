package hut.composite.querycreator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ontology.images.Images;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.internal.preferences.PropertyListenerList;
import org.eclipse.ui.part.ViewPart;

import service.Service;
import ws.owl.*;
import hut.data.queryinterchangedata.*;
import hut.dialog.DateTimeDialog;
public class CompositeRestriction extends CompositeQuerySuper {

	private ComboboxSubjectData comboboxSubjectData = new ComboboxSubjectData();
	private ComboboxObjectData comboboxObjectData = new ComboboxObjectData();
	private ComboboxPredicateData comboboxPredicateData = new ComboboxPredicateData();
	
	private RowController rowController = new RowController();
	
	public Map<String, String> variableMap = new HashMap<String, String>();
		
	private Table table;
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	@Override
	public void updateInterface() {
	TableItem[] tableItems = table.getItems();
	for (int i = 0; i < tableItems.length; i++)
	{
		addDataComboSubject((CCombo)tableItems[i].getData("comboSubject"));
	}
	}
	public CompositeRestriction(Composite parent, int style) {
		super(parent, style);
		setLayout(new FormLayout());
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		
		comboboxSubjectData.setRowController(rowController);
		comboboxObjectData.setRowController(rowController);
		comboboxPredicateData.setRowController(rowController);
		
		rowController.setComboboxObject(comboboxObjectData);
		rowController.setComboboxPredicate(comboboxPredicateData);
		rowController.setComboboxSubject(comboboxSubjectData);
		
		if ((Map<String,String>)this.getInputData()!=null)
		{
			variableMap = (Map<String, String>)this.getInputData();
		}
		final TableViewer tableViewer = new TableViewer(this, SWT.NONE);
		table = tableViewer.getTable();
		final FormData fd_table = new FormData();
		fd_table.bottom = new FormAttachment(100, -40);
		fd_table.right = new FormAttachment(100, -5);
		fd_table.top = new FormAttachment(0, 5);
		fd_table.left = new FormAttachment(0, 5);
		table.setLayoutData(fd_table);
		toolkit.adapt(table, true, true);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		final TableColumn newObjectColumn = new TableColumn(table, SWT.NONE);
		newObjectColumn.setWidth(70);
		newObjectColumn.setText("New Object");

		final TableColumn subjectColumn = new TableColumn(table, SWT.NONE);
		subjectColumn.setWidth(127);
		subjectColumn.setText("Subject");

		final TableColumn predicateColumn = new TableColumn(table, SWT.NONE);
		predicateColumn.setWidth(127);
		predicateColumn.setText("Predicate");

		final TableColumn filterColumn = new TableColumn(table, SWT.NONE);
		filterColumn.setWidth(129);
		filterColumn.setText("Object/Filter");

		final TableColumn filterValueColumn = new TableColumn(table, SWT.NONE);
		filterValueColumn.setWidth(134);
		filterValueColumn.setText("Filter Value");

		final TableColumn optionalColumn = new TableColumn(table, SWT.NONE);
		optionalColumn.setWidth(60);
		optionalColumn.setText("Optional");

		final TableColumn btnDeleteRow_Column = new TableColumn(table, SWT.NONE);
		btnDeleteRow_Column.setWidth(50);

		final ToolBar toolBar = new ToolBar(this, SWT.NONE);
		final FormData fd_toolBar = new FormData();
		fd_toolBar.left = new FormAttachment(0, 5);
		fd_toolBar.right = new FormAttachment(100, -5);
		fd_toolBar.top = new FormAttachment(100, -35);
		fd_toolBar.bottom = new FormAttachment(100, -5);
		toolBar.setLayoutData(fd_toolBar);
		toolkit.adapt(toolBar, true, true);

		final ToolItem resetToolItem = new ToolItem(toolBar, SWT.PUSH);
		resetToolItem.setText("Reset");
		resetToolItem.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(final SelectionEvent e){
				resetTableRestriction();
			} 
		});

		final ToolItem arrangeToolItem = new ToolItem(toolBar, SWT.PUSH);
		arrangeToolItem.setText("Arrange");

		final ToolItem newRestrictionToolItem = new ToolItem(toolBar, SWT.PUSH);
		newRestrictionToolItem.setText("New Restriction");
		newRestrictionToolItem.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(final SelectionEvent e){
				addRestrictionRow("", "", "", "");
			}
		});
		
		
	}
	

	public void addRestrictionRow(String subject, String predicate, String objectFilter, String filterValue){
		
		TableItem item = new TableItem(table, SWT.NONE);
		TableEditor editor = new TableEditor(table);
				
		final Button btnCheckOptional = new Button(table,SWT.CHECK);
		editor.grabHorizontal = true;
		editor.setEditor(btnCheckOptional,item,5);
		
		editor = new TableEditor(table);
		final CCombo comboSubject = new CCombo(table,SWT.NONE);
		editor.grabHorizontal = true;
		editor.setEditor(comboSubject,item,1);
		
		editor = new TableEditor(table);
		final CCombo comboPredicate = new CCombo(table,SWT.NONE);
		editor.grabHorizontal = true;
		editor.setEditor(comboPredicate,item,2);
		
		editor = new TableEditor(table);
		final Button btnCheckNewObject = new Button(table,SWT.CHECK);
		editor.grabHorizontal = true;
		editor.setEditor(btnCheckNewObject,item,0);
		
		editor = new TableEditor(table);
		final CCombo comboObjectFilter = new CCombo(table,SWT.NONE);
		editor.grabHorizontal = true;
		editor.setEditor(comboObjectFilter,item,3);
		
		editor = new TableEditor(table);
		final Text textFilterValue = new Text(table,SWT.NONE);
		editor.grabHorizontal = true;
		editor.setEditor(textFilterValue,item,4);
		
		editor = new TableEditor(table);
		final Button btnDeleteRow = new Button(table,SWT.IMAGE_GIF);
		Image deleteImage = Images.imageRegistry.get(Images.DELETE);
		btnDeleteRow.setImage(deleteImage); 
		editor.grabHorizontal = true;
		editor.setEditor(btnDeleteRow,item,6);
				
		comboSubject.setText(subject);
		comboPredicate.setText(predicate);
		comboObjectFilter.setText(objectFilter);
		textFilterValue.setText(filterValue);
		comboSubject.setData(comboboxSubjectData);
		comboPredicate.setData(comboboxPredicateData);
		comboObjectFilter.setData(comboboxObjectData);
		
		addDataComboSubject(comboSubject);
		addDataComboPredicate(comboPredicate);
		addDataComboObjectFilter(comboObjectFilter);
				
		item.setData("checkOptional",btnCheckOptional);
		item.setData("comboSubject",comboSubject);
		item.setData("comboPredicate",comboPredicate);
		item.setData("checkNewObject",btnCheckNewObject);
		item.setData("comboObjectFilter",comboObjectFilter);
		item.setData("textFilterValue",textFilterValue);
		item.setData("btnDelete",btnDeleteRow);
		btnDeleteRow.setData(item);
		
		//Modify the subject combo create a new row
		comboSubject.addModifyListener(new ModifyListener(){

			public void modifyText(ModifyEvent event) {
				if (needToCreateNewRestriction()) addRestrictionRow("", "", "", "");			
			}
		});
		
		//Click the delete button will delete this row
		Listener deleteEventListener = new Listener(){
			public void handleEvent(Event event) {
				Button button = (Button)event.widget;
				TableItem item = (TableItem)button.getData(); 
				((Button)item.getData("checkOptional")).dispose();
				((CCombo)item.getData("comboSubject")).dispose();
				((CCombo)item.getData("comboPredicate")).dispose();
				((Button)item.getData("checkNewObject")).dispose();
				((CCombo)item.getData("comboObjectFilter")).dispose();
				((Text)item.getData("textFilterValue")).dispose();
				((Button)item.getData("btnDelete")).dispose();
				item.dispose();
				}
				   		
    	};
    	btnDeleteRow.addListener(SWT.Selection, deleteEventListener);
		
    	//set output data for ComboSubjectData when subject combobox lost focus  	
    	comboSubject.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent arg0) {
		
			}
			public void focusLost(FocusEvent event) {
			
			}
    	});
    	
    	//set output data for ComboPredicateData when predicate combobox lost focus  
    	comboPredicate.addFocusListener(new FocusListener(){
			public void focusGained(FocusEvent arg0) {
				String txt = comboPredicate.getText();
				String tmp = comboSubject.getText();
				int x = tmp.indexOf("(");
				int y = tmp.indexOf(")");
				String className = tmp.substring(x+1, y);
				comboboxSubjectData.setDataOutput(className);
				comboboxSubjectData.getRowController().subjectChange();
				addDataComboPredicate(comboPredicate);
				comboPredicate.setText(txt);
			}
			public void focusLost(FocusEvent event) {
				
			}
    		
    	});
    	
    	//set output data for ComboObjectData when object combo lost focus 
    	comboObjectFilter.addFocusListener(new FocusListener(){

			public void focusGained(FocusEvent arg0) {
				String txt = comboObjectFilter.getText();
				String tmp = comboPredicate.getText();
				comboboxPredicateData.setDataOutput(new ObjectComboInputData(btnCheckNewObject.getSelection(),variableMap,tmp));
				comboboxPredicateData.getRowController().predicateChange();
				addDataComboObjectFilter(comboObjectFilter);
				comboObjectFilter.setText(txt);			
			}

			public void focusLost(FocusEvent arg0) {
				String tmp = comboObjectFilter.getText();
				int x = tmp.indexOf("(");
				int y = tmp.indexOf(")");
				String newObjectName = tmp.substring(0, x);
				String type = tmp.substring(x + 1, y);
				comboboxObjectData.setDataOutput(new ObjectComboOutputData(newObjectName,comboboxObjectData.getIsNewVariable(),type));
				addNewVariable();
				comboObjectFilter.setText(tmp);
			}
    		
    	});
    	
    	
    	textFilterValue.addMouseListener(new MouseAdapter() {
    		public void mouseDown(final MouseEvent e) {
    			String propertyName = comboPredicate.getText();
				PropertyData property = Service.webServiceDelegate.getPropertyByShortName(null, propertyName);
				if (property.isDatatypeProperty())
				{
					String type = Service.webServiceDelegate.getPropertySpecificDataType(null, property.getPropertURI());
					if (type.equals("date") || type.equals("dateTime"))
					{
						DateTimeDialog dialog = new DateTimeDialog(table.getShell());
						if (dialog.open() == InputDialog.OK) {
							if(type.equals("date"))
								textFilterValue.setText(dialog.SYear + "-" + dialog.SMonth	+ "-" + dialog.SDay);
							else if(type.equals("dateTime"))
								textFilterValue.setText(dialog.SYear + "-" + dialog.SMonth	+ "-" + dialog.SDay + "T" + dialog.SHour + ":"+dialog.SMinute+":"+dialog.SSecond);							
						}
					}
				}
    		}
    	});
	}

	protected void updateRowData() {
		this.getQueryController().updateRowData();
		
	}
	private boolean needToCreateNewRestriction(){
		TableItem[] items = table.getItems();
		for (int i = 0; i < items.length; i++) {
			
			if (((CCombo)items[i].getData("comboSubject")).getText().equals(""))
				return false;
		}
		return true;
	}
	
	private void addDataComboSubject(CCombo comboSubject){
		if (((ComboboxSubjectData)comboSubject.getData()).getListItem()!=null)
		{
		ArrayList<String> tmpList = ((ComboboxSubjectData)comboSubject.getData()).getListItem();
		String tmp = comboSubject.getText();
		comboSubject.removeAll();
		for (int i = 0; i < tmpList.size(); i++)
		{
			comboSubject.add(tmpList.get(i));
		}
		comboSubject.setText(tmp);
	}
	}
	
	private void addDataComboPredicate(CCombo comboPredicate){
		if (((ComboboxPredicateData)comboPredicate.getData()).getListItem()!= null)
		{
			ArrayList<String> tmpList = ((ComboboxPredicateData)comboPredicate.getData()).getListItem();
			comboPredicate.removeAll();
			for (int i = 0; i < tmpList.size(); i++)
			{
				comboPredicate.add(tmpList.get(i));
			}
		}
	}
	
	private void addDataComboObjectFilter(CCombo comboObjectFilter){
		if (((ComboboxObjectData)comboObjectFilter.getData()).getListItem()!= null)
		{
			ArrayList<String> tmpList = ((ComboboxObjectData)comboObjectFilter.getData()).getListItem();
			comboObjectFilter.removeAll();
			for (int i = 0; i < tmpList.size(); i++)
			{
				comboObjectFilter.add(tmpList.get(i));
			}
		}		
	}
	
	
	
	public void updateComboboxSubjectData(){
		variableMap = (Map<String, String>)this.getInputData();
		if (variableMap != null)
		{
			ArrayList<String> tmpList = new ArrayList<String>();
			for (String key : variableMap.keySet())
			{
				if (!key.equals(""))
				tmpList.add(key + "(" + variableMap.get(key) + ")");
			}
			comboboxSubjectData.setListItem(tmpList);
		}
	}
	
	public void updateComboboxPredicateData(){
		String className = (String)comboboxPredicateData.getDataInput();
		List<PropertyData> propertyList = Service.webServiceDelegate.getAllClassProperties(null, className);
		ArrayList<String> tmpList = new ArrayList<String>();
		
	}
	
	private void resetTableRestriction()
	{
		table.removeAll();
		for (Control control:table.getChildren())
		{
			control.dispose();
		}
		System.out.println(variableMap.keySet().toString());
		addRestrictionRow("","","", "");
	}
	
	public void addNewVariable(){
		System.out.println("Da vao den addNewVariable");
		RestrictionOuputData outputData = new RestrictionOuputData();
		if (comboboxObjectData.getDataOutput() != null)
		{
			
			outputData.setHasNewVariable(((ObjectComboOutputData)comboboxObjectData.getDataOutput()).isNewVariable());
			if (((ObjectComboOutputData)comboboxObjectData.getDataOutput()).isNewVariable())
				variableMap.put(((ObjectComboOutputData)comboboxObjectData.getDataOutput()).getObjectChosen(), ((ObjectComboOutputData)comboboxObjectData.getDataOutput()).getObjectType());
		
		}
		
		System.out.println("da set xong variable map");
		ArrayList<RowData> rowList = new ArrayList<RowData>();
		TableItem[] items = table.getItems();
		System.out.println(items.length);
		for (int i = 0; i < items.length; i++)
		{
			System.out.println(i);
			boolean optional = ((Button)items[i].getData("checkOptional")).getSelection();
			
			String subject = (((CCombo)items[i].getData("comboSubject")).getText());
			
			String predicate = (((CCombo)items[i].getData("comboPredicate")).getText());
			
			String object = (((CCombo)items[i].getData("comboObjectFilter")).getText());
			
			String filterValue = ((Text)items[i].getData("textFilterValue")).getText();
			if ((subject == "") || (subject == null)) continue;
			
			System.out.println(optional);
			System.out.println(subject);
			System.out.println(predicate);
			System.out.println(object);
			System.out.println(filterValue);
			
			RowData rowData = new RowData(optional,subject,predicate,object,filterValue);
			rowList.add(rowData);
			int x = subject.indexOf("(");
			int y = subject.indexOf(")");
			
			String variableName = subject.substring(0,x);
			String variableType = subject.substring(x + 1, y);
			
			if (!variableMap.containsKey(variableName))
				variableMap.put(variableName, variableType);
		}
		outputData.setVariableMap(variableMap);
		outputData.setRowList(rowList);
		
		System.out.println("Noi dung cua variablelist truyen di");
		for (String s : outputData.getVariableMap().keySet())
		{
			System.out.println(s);
			System.out.println(outputData.getVariableMap().get(s));
		}
		System.out.println("Het noi dung cuar variablelist truyen di");
		this.setOutputData(outputData);
		this.getQueryController().AddVariable();
	}
	
	
	
}
