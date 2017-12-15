package hut.composite.annotationCreatorAndEditor;

import java.util.List;


import hut.model.annotationCreatorAndEditor.DataInstance;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import service.Service;


//import ws.data.PropertyMapData;
import ws.owl.BkIndividualProperty;
import ws.owl.ClassData;
import ws.owl.IndividualData;
import ws.owl.InstanceData;
import ws.owl.PropertyData;
import ws.owl.PropertyMapData;
import ws.owl.WebServiceDelegate;
import ws.owl.WebServiceService;

import com.swtdesigner.SWTResourceManager;

public class CompositeListPropertiesOfInstance extends
		SuperCompositeAnnotationCreatorAndEditor {

	private Label classLabel;
	private Table table;
	private Text textID;
	private boolean isFullURI = false;
	private boolean  isNewInstance = true;
	private String instanceFullName;
	private String className;
	private List<String> propertyList;
	
	
	
	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public Text getTextID() {
		return textID;
	}

	public void setTextID(Text textID) {
		this.textID = textID;
	}

	public boolean isFullURI() {
		return isFullURI;
	}

	public void setFullURI(boolean isFullURI) {
		this.isFullURI = isFullURI;
	}

	public boolean isNewInstance() {
		return isNewInstance;
	}

	public void setNewInstance(boolean isNewInstance) {
		this.isNewInstance = isNewInstance;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<String> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(List<String> propertyList) {
		this.propertyList = propertyList;
	}

	public String getInstanceFullName() {
		return instanceFullName;
	}

	public void setInstanceFullName(String instanceFullName) {
		this.instanceFullName = instanceFullName;
	}

	public CompositeListPropertiesOfInstance(Composite parent, int style) {
		super(parent, style);
		setLayout(new FormLayout());

		final Composite composite = new Composite(this, SWT.BORDER);
		composite.setLayout(new FormLayout());
		final FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(0, 90);
		fd_composite.top = new FormAttachment(0, 0);
		fd_composite.right = new FormAttachment(100, 0);
		fd_composite.left = new FormAttachment(0, 0);
		composite.setLayoutData(fd_composite);

		final ToolBar toolBar = new ToolBar(composite, SWT.NONE);
		toolBar.setLayout(new FormLayout());
		final FormData fd_toolBar = new FormData();
		fd_toolBar.bottom = new FormAttachment(0, 45);
		fd_toolBar.top = new FormAttachment(0, 0);
		fd_toolBar.left = new FormAttachment(0, 0);
		toolBar.setLayoutData(fd_toolBar);

		final ToolItem descriptionToolItem = new ToolItem(toolBar, SWT.PUSH);
		descriptionToolItem.setToolTipText("Description");
		descriptionToolItem.setText("Description");
		descriptionToolItem.setImage(SWTResourceManager.getImage(CompositeListPropertiesOfInstance.class, "/ontology/images/describe.gif"));

		//---------------- Save Button WidgetSelected Event ---------------------------------------------------------------------------------
		//-----------------------------------------------------------------------------------------------------------------------------------
		final ToolItem saveToolItem = new ToolItem(toolBar, SWT.PUSH);
		saveToolItem.setToolTipText("Save");
		saveToolItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {

				DataInstance dataInstanceInput = (DataInstance)getInputData();
				isNewInstance = dataInstanceInput.isNewInstance();
				if( isFullURI ){
					instanceFullName = textID.getText(); 
				}
				else{
					String temp = instanceFullName ; 
					
					instanceFullName = temp.substring(0, temp.lastIndexOf("#")+1) + textID.getText();
				}
				
				//-- If is new Instance -------------
				if( isNewInstance && service.Service.webServiceDelegate.checkExistIndividual(null, instanceFullName))
				{
					MessageDialog.openInformation(new Shell(), "Notice", "One instance with this name is existed !");
					// i want a break method here ! 
					return ;
				}
				
				textID.setEditable(false);
				String classFullName ;
				if(dataInstanceInput.getClassData()!= null)
					{
						classFullName = dataInstanceInput.getClassData().getClassURI();
					}
				else //-- truong hop ma hien thi tu dong trong compositeRow thi ko xac dinh duoc className
				{
					classFullName= service.Service.webServiceDelegate.getClassOfIndividual(null, CompositeListPropertiesOfInstance.this.instanceFullName);
				}
				service.Service.webServiceDelegate.removeIndividual(null, CompositeListPropertiesOfInstance.this.instanceFullName);
//				service.Service.webServiceDelegate.createInstance(null, CompositeListPropertiesOfInstance.this.instanceFullName, className);
				
			
				
				List<InstanceData> list= new java.util.ArrayList<InstanceData>();
				InstanceData iData= new InstanceData();
		        iData.setInstanceID(instanceFullName);
		        iData.setClassName(classFullName);
		        PropertyMapData pm;
		        
				for (TableItem item: table.getItems())
				{
					//--- check this -----------
					CompositeRow compositeRow = (CompositeRow)item.getData("value");
					
					PropertyData propertyData = (PropertyData)item.getData("propertyData"); 
					String propertyName = propertyData.getPropertURI();
					
					if (propertyData.isDatatypeProperty()){
						//System.out.println(compositeRow.getValue().size());
						for (Object value: compositeRow.getValue())
							{
								pm= new PropertyMapData();
						        pm.setPropertyname(propertyName);
						        pm.setValue((String)value);
						        iData.getDataPropertyList().add(pm);
//							service.Service.webServiceDelegate.addDatatypeProperty(null, propertyName, value, CompositeListPropertiesOfInstance.this.instanceFullName);
							}
					}
					else
					{
						//--- getValue(): return List<Object>
						for (Object value: compositeRow.getValue())
						{
							if(value instanceof DataInstance){
								
								
								DataInstance  dataInstance = (DataInstance)value;
								
//								String className = ((DataInstance)value).getClassData().getClassURI();
								
								pm= new PropertyMapData();
								//-- Phai lay ra duoc className cua Object Property
							    
							    	System.out.println("check "+ " className :" + className); 
							    //- tam thoi dang set className = null --
								pm.setTypeClass(null);
							    pm.setPropertyname(propertyName);
							    pm.setValue(((DataInstance)value).getInstanceFullName());
							    iData.getObjectPropertyList().add(pm);
							}
//							service.Service.webServiceDelegate.addObjectProperty(null, propertyName, value, CompositeListPropertiesOfInstance.this.instanceFullName);
						}
					}
							
				}
				
				 list.add(iData);
			       
				 service.Service.webServiceDelegate.saveValuesOfIndividual(null, list, false);
				
				MessageDialog.openInformation(new Shell(), "Save successfully!", "This instance has been saved!");
				
				// Neu la them 1 instance moi thi phai refresh lai Table List Instance
				if(dataInstanceInput.isNewInstance() ){
					if(getController() != null){
						getController().updateChosenClass();
					}
					
				}
				((DataInstance)getInputData()).setNewInstance(false);
			}
		});
		//-----------------------------------------------------------------------------------------------------------------------------------
		//-----------------------------------------------------------------------------------------------------------------------------------
		saveToolItem.setText("Save");		
		saveToolItem.setImage(SWTResourceManager.getImage(CompositeListPropertiesOfInstance.class, "/ontology/images/save.gif"));

		final Button fullUriButton;
		fullUriButton = new Button(composite, SWT.CHECK);
		fd_toolBar.right = new FormAttachment(fullUriButton, -5, SWT.LEFT);
		final FormData fd_fullUriButton = new FormData();
		fd_fullUriButton.left = new FormAttachment(0, 200);
		fd_fullUriButton.right = new FormAttachment(0, 270);
		fd_fullUriButton.bottom = new FormAttachment(0, 21);
		fd_fullUriButton.top = new FormAttachment(0, 5);
		fullUriButton.setLayoutData(fd_fullUriButton);
		fullUriButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				
				isFullURI = fullUriButton.getSelection(); 
				
				if(!fullUriButton.getSelection()){
					textID.setText(CompositeListPropertiesOfInstance.this.instanceFullName.substring(CompositeListPropertiesOfInstance.this.instanceFullName.indexOf("#")+1));
				}
				else{
					textID.setText(CompositeListPropertiesOfInstance.this.instanceFullName);
				}
				
			}
		});
		fullUriButton.setText("Full URI");

		final Label idLabel = new Label(composite, SWT.NONE);
		final FormData fd_idLabel = new FormData();
		fd_idLabel.bottom = new FormAttachment(0, 86);
		fd_idLabel.top = new FormAttachment(0, 67);
		fd_idLabel.right = new FormAttachment(0, 25);
		fd_idLabel.left = new FormAttachment(0, 4);
		idLabel.setLayoutData(fd_idLabel);
		idLabel.setText("ID ");

		textID = new Text(composite, SWT.BORDER);
		final FormData fd_textID = new FormData();
		fd_textID.left = new FormAttachment(0, 34);
		fd_textID.right = new FormAttachment(100, -5);
		fd_textID.bottom = new FormAttachment(0, 83);
		fd_textID.top = new FormAttachment(0, 64);
		textID.setLayoutData(fd_textID);

		table = new Table(this, SWT.BORDER | SWT.HIDE_SELECTION);

		classLabel = new Label(composite, SWT.NONE);
		final FormData fd_classLabel = new FormData();
		fd_classLabel.left = new FormAttachment(0, 4);
		fd_classLabel.bottom = new FormAttachment(0, 61);
		fd_classLabel.right = new FormAttachment(100, 0);
		fd_classLabel.top = new FormAttachment(0, 45);
		classLabel.setLayoutData(fd_classLabel);
		classLabel.setText("Class:");
		final FormData fd_table = new FormData();
		fd_table.top = new FormAttachment(0, 89);
		fd_table.right = new FormAttachment(100, 0);
		fd_table.left = new FormAttachment(0, 0);
		fd_table.bottom = new FormAttachment(100, 0);
		table.setLayoutData(fd_table);
		table.setHeaderVisible(true);
		final Listener paintListener = new Listener() {
			public void handleEvent(Event event) {
		        switch (event.type) {
		        case SWT.MeasureItem: 
		        	event.width = 300;
		        	event.height = 85;
		        	break;
		        }
			}
		};
		
		table.addListener(SWT.MeasureItem, paintListener);

		final TableColumn propertyTableColumn = new TableColumn(table, SWT.NONE);
		propertyTableColumn.setAlignment(SWT.CENTER);
		propertyTableColumn.setWidth(108);
		propertyTableColumn.setText("Property");

		final TableColumn valueTableColumn = new TableColumn(table, SWT.NONE);
		valueTableColumn.setWidth(336);
		valueTableColumn.setText("Value");
		// TODO Auto-generated constructor stub
	}

	//-------------- updateInterface() method ----------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------
	@Override
	public int updateInterface() {
		DataInstance dataInstanceInput = (DataInstance)getInputData();
		instanceFullName = dataInstanceInput.getInstanceFullName();
		System.out.println("instanceFullName: "+ instanceFullName);
		//-- If is existed Instance ----------------------------------------------------
		if(!dataInstanceInput.isNewInstance()){
			isNewInstance = false;
			clearTable();
			bindDataToTable();
			if(!isFullURI){
				textID.setText(this.instanceFullName.substring(this.instanceFullName.indexOf("#")+1));
			}
			else{
				textID.setText(this.instanceFullName);
			}
			textID.setEditable(false);
			if(dataInstanceInput.getClassData() != null){
				if(dataInstanceInput.getClassData().getClassName()!= null){
					classLabel.setText("Class: "+dataInstanceInput.getClassData().getClassName());
				}
				
			}
			else{
				String s = Service.webServiceDelegate.getClassOfIndividual(null, instanceFullName);
				s = s.substring(s.indexOf("#") + 1);
				classLabel.setText("Class: " + s);
			}
				
		}
		// if instance is new instance
		else{
			clearTable();
			className = dataInstanceInput.getClassData().getClassName();
			instanceFullName = dataInstanceInput.getInstanceFullName();
			
			System.out.println("classFullName"+ instanceFullName);
			
			if(!isFullURI){
				textID.setText(this.instanceFullName.substring(this.instanceFullName.indexOf("#")+1));
			}
			else{
				textID.setText(this.instanceFullName);
			}
			textID.setEditable(true);
			
			//---  retrive All Properties of this Class --
			List<PropertyData> listPropertyData = service.Service.webServiceDelegate.getAllClassProperties(null, dataInstanceInput.getClassData().getClassURI());
			for(PropertyData propertyData : listPropertyData)
			{
				String propertyName = propertyData.getPropertyName();
				System.out.println("propertyName: : : :"+ propertyName);
				java.util.List<String> value = new java.util.ArrayList<String>();
				
				addRow(propertyName, propertyData, value);
			}
		}
		
		return 0;
	}
	//--------------------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------
	
	
	//-------------- clearTable() method ---------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------
	protected void clearTable() {
		for (TableItem item : table.getItems())
		{
			item.dispose();
		}
		table.removeAll();
		
		for (Control control : table.getChildren())
		{
			control.dispose();
		}
		
	}
	//--------------------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------

	
	//A-------------- bindDataToTable() method ----------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------
	public void bindDataToTable(){
		
		
		IndividualData tmpIndividual = service.Service.webServiceDelegate.getIndividualByName(null, instanceFullName);
		
		{
		//propertyList = delegate.getValuesOfIndividual(null, instanceFullName);
			String tmp = instanceFullName;
//			String prefix = tmp.substring(0, tmp.lastIndexOf("#"));
			
			List<BkIndividualProperty> listBkIndividualProperty = service.Service.webServiceDelegate.getValuesOfIndividual(null, instanceFullName);
			
			if(listBkIndividualProperty.size() > 0) {
				for(BkIndividualProperty individualProperty : listBkIndividualProperty){
					String propertyBkName = individualProperty.getProperty();
					List<String> value = individualProperty.getListValue();
					
					PropertyData propertyData = service.Service.webServiceDelegate.getPropertyByName(null, propertyBkName);
					String tmpPropertyName = propertyBkName;
					String propertyPrefix = tmpPropertyName.substring(0, tmpPropertyName.lastIndexOf("#"));
					
					//if(!propertyPrefix.equals(prefix)) continue;
					
					addRow(propertyBkName,propertyData,value);
					
				}
			}
		
		
		
		}
	}
	//--------------------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------
	
	
	//-------------- addRow() method -------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------
	protected void addRow(String propertyName,PropertyData propertyData, List<String> value) {
		TableItem item = new TableItem(table,SWT.NONE);
		item.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		TableEditor editor = new TableEditor(table);
		
		
		Text textProperty = new Text(table,SWT.NONE);
		textProperty.setText(propertyName.substring(propertyName.indexOf("#") + 1));
		editor.grabHorizontal = true;
		editor.setEditor(textProperty, item, 0);
		
		CompositeRow compositeRow = new CompositeRow(table,SWT.NONE,instanceFullName,propertyData,value);
		compositeRow.setController(this.getController());
		if(propertyData.isObjectProperty()){
			compositeRow.addUriToolBarItemForObjectProperty();
		}
		
		editor = new TableEditor(table);
		editor.grabHorizontal = true;
		editor.setEditor(compositeRow, item, 1);
		
		DataInstance dataInstance = new DataInstance();
		dataInstance.setInstanceFullName(instanceFullName);
		
		item.setData("propertyName", propertyName);
		item.setData("propertyData", propertyData);
		item.setData("value", compositeRow);
		item.setData("Data", dataInstance);
		
		
	}
	//--------------------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------------------------------------------------
}
