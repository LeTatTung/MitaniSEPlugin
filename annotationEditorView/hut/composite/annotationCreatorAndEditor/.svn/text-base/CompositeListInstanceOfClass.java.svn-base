package hut.composite.annotationCreatorAndEditor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import hut.model.annotationCreatorAndEditor.DataInstance;
import ontology.images.Images;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import service.Service;

import ws.owl.ClassData;
import ws.owl.IndividualData;
import ws.owl.Message;
import ws.owl.WebServiceDelegate;
import ws.owl.WebServiceService;

import com.swtdesigner.SWTResourceManager;

public class CompositeListInstanceOfClass extends SuperCompositeAnnotationCreatorAndEditor {

//	private FormData fd_tableListInstance;
//	private Table tableListInstance;
	private Text textInputClassName;
	private Table tableListInstance;
	private Composite composite;

	private ToolItem selectItem=null;
	private ToolBar toolBar;
	private boolean isSelect;
	private String instanceFullName;
	private Label labelNotification;
	private java.util.List<String> listInstanceName;
	public CompositeListInstanceOfClass(Composite parent, int style) {
		super(parent, style);
		setLayout(new FormLayout());

		composite = new Composite(this, SWT.BORDER);
		composite.setLayout(new FormLayout());
//		fd_tableListInstance.right = new FormAttachment(composite, 0, SWT.RIGHT);
//		fd_tableListInstance.top = new FormAttachment(composite, 0, SWT.BOTTOM);
		final FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(0, 90);
		fd_composite.right = new FormAttachment(100, 0);
		fd_composite.top = new FormAttachment(0, 0);
		fd_composite.left = new FormAttachment(0, 0);
		composite.setLayoutData(fd_composite);

		 toolBar = new ToolBar(composite, SWT.NONE);
		 final FormData fd_toolBar = new FormData();
		 fd_toolBar.bottom = new FormAttachment(0, 45);
		 fd_toolBar.top = new FormAttachment(0, 0);
		 fd_toolBar.right = new FormAttachment(0, 500);
		 fd_toolBar.left = new FormAttachment(0, 0);
		 toolBar.setLayoutData(fd_toolBar);

		final ToolItem OptionToolItem = new ToolItem(toolBar, SWT.PUSH);
		OptionToolItem.setText("Option");
		OptionToolItem.setToolTipText("Option");
		OptionToolItem.setImage(Images.imageRegistry.get(Images.DESCRIBE));
		
		final ToolItem newIstanceToolItem = new ToolItem(toolBar, SWT.PUSH);
		newIstanceToolItem.setText("Add");
		//--------------------------  button Add: Click Event -----------------------------------------------------------------
		//---------------------------------------------------------------------------------------------------------------------
		newIstanceToolItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				
				DataInstance dataInstanceOutput = new DataInstance();
				dataInstanceOutput.setNewInstance(true);
				// chak chan la cho nay chua co OutputData o compositeClassList.
				if(getController().getCompositeClassList() == null )
				{
					if(getController().getCompositeLeftPopUp().getOutputData() == null )
					{
						MessageDialog.openInformation(new Shell(), "Notice", "Please chose a class !");
					}
					else
					{
						dataInstanceOutput.setClassData((ClassData)getController().getCompositeLeftPopUp().getOutputData());
					}
				}
				else
				{
					if(getController().getCompositeClassList().getOutputData() == null )
					{
						MessageDialog.openInformation(new Shell(), "Notice", "Please chose a class !");
					}
					else
					{
						dataInstanceOutput.setClassData((ClassData)getController().getCompositeClassList().getOutputData());
					}
				}
				
				
				//Phai xem lai dat ten instanceFullName sao cho no la duy nhat 
				//-------Tao 1 doi tuong moi --------

				
				String className = dataInstanceOutput.getClassData().getClassName() ; 

				String classFullName = dataInstanceOutput.getClassData().getClassURI();
				
				DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy.HH_mm_ss");
		        Date date = new Date();
		        String ID=dateFormat.format(date);
				String instanceFullName = classFullName + "-" + ID;
					
					dataInstanceOutput.setInstanceFullName(instanceFullName);
					setOutputData(dataInstanceOutput);
					
					System.out.println("dataInstanceOutput : instanceFullName - " + dataInstanceOutput.getInstanceFullName() + " ; className" + dataInstanceOutput.getClassData().getClassName() + " ; ClassURI" + dataInstanceOutput.getClassData().getClassURI());
					
					getController().updateChosenInstance();
					 
			}
		});
		//---------------------------------------------------------------------------------------------------------------------
		//---------------------------------------------------------------------------------------------------------------------
		newIstanceToolItem.setToolTipText("Add new Instance of this class");
		newIstanceToolItem.setImage(SWTResourceManager.getImage(CompositeListInstanceOfClass.class, "/ontology/images/add.gif"));

		labelNotification = new Label(composite, SWT.NONE);
		final FormData fd_labelNotification = new FormData();
		fd_labelNotification.right = new FormAttachment(100, 0);
		fd_labelNotification.bottom = new FormAttachment(0, 61);
		fd_labelNotification.top = new FormAttachment(0, 45);
		fd_labelNotification.left = new FormAttachment(0, 0);
		labelNotification.setLayoutData(fd_labelNotification);
		
		final ToolItem deleteInstanceToolItem = new ToolItem(toolBar, SWT.PUSH);
		deleteInstanceToolItem.setText("Delete");
		//--------------------------- deleteInstanceToolItem: add Selection Listener  -------------------------------------------------------------
		//-----------------------------------------------------------------------------------------------------------------------------------------
		deleteInstanceToolItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {


				TableItem[] tblItem = tableListInstance.getSelection();
				TableItem item = tblItem[0];
				CompositeListInstanceOfClass.this.instanceFullName = (String)item.getData();
				
				service.Service.webServiceDelegate.removeIndividual(null, CompositeListInstanceOfClass.this.instanceFullName);
				MessageDialog.openInformation(CompositeListInstanceOfClass.this.getShell(), "Delete successfully!", "The instance has been removed!");
				CompositeListInstanceOfClass.this.labelNotification.setText("");
				CompositeListInstanceOfClass.this.instanceFullName = "";
				getController().updateChosenClass();
			}
		});
		//---------------------------------------------------------------------------------------------------------------------
		//---------------------------------------------------------------------------------------------------------------------
		deleteInstanceToolItem.setToolTipText("Delete this Instance");
		deleteInstanceToolItem.setImage(SWTResourceManager.getImage(CompositeListInstanceOfClass.class, "/ontology/images/delete.gif"));

		 final ToolItem newItemToolItem = new ToolItem(toolBar, SWT.PUSH);
		 newItemToolItem.addSelectionListener(new SelectionAdapter() {
		 	public void widgetSelected(final SelectionEvent e) {
		 		if(Service.isAdmin){
		 			Message message = service.Service.webServiceDelegate.backupOWL(null);
		 			if (message.isSuccess())
		 				MessageDialog.openInformation(new Shell(), "Save successful!", message.getMessage());
		 			else
		 				MessageDialog.openError(new Shell(), "Error!", message.getMessage());
		 		}
		 		else{
		 			MessageDialog.openInformation(new Shell(), "Notification", "Only admin can backup data to OWL!");
		 		}
		 	}
		 });
		 newItemToolItem.setImage(SWTResourceManager.getImage(CompositeListInstanceOfClass.class, "/ontology/images/saveAnnotation.gif"));
		 newItemToolItem.setText("Save owl");

		textInputClassName = new Text(composite, SWT.BORDER);
		final FormData fd_textInputClassName = new FormData();
		fd_textInputClassName.right = new FormAttachment(100, -5);
		fd_textInputClassName.bottom = new FormAttachment(0, 83);
		fd_textInputClassName.top = new FormAttachment(0, 64);
		fd_textInputClassName.left = new FormAttachment(0, 78);
		textInputClassName.setLayoutData(fd_textInputClassName);
		//-- addModifuListener to textInputClassName----------------
		textInputClassName.addModifyListener(new ModifyListener() {
			public void modifyText(final ModifyEvent arg0) {
				java.util.List<String> tempList = new java.util.ArrayList<String>();
				cleanTable();
				String filterText = textInputClassName.getText();
				for(String instanceName : listInstanceName){
					if(instanceName.toLowerCase().contains(filterText.toLowerCase())){
						tempList.add(instanceName);
					}
				}
				bindDataToTable(tempList);
				
			}
		});

		final Label filterByNameLabel = new Label(composite, SWT.NONE);
		final FormData fd_filterByNameLabel = new FormData();
		fd_filterByNameLabel.bottom = new FormAttachment(0, 86);
		fd_filterByNameLabel.top = new FormAttachment(0, 67);
		fd_filterByNameLabel.right = new FormAttachment(0, 72);
		fd_filterByNameLabel.left = new FormAttachment(0, 0);
		filterByNameLabel.setLayoutData(fd_filterByNameLabel);
		filterByNameLabel.setText("Filter by Name ");
		
	

		tableListInstance = new Table(this, SWT.BORDER);
		//--------------------------- tableListInstance: Mouse Double Click event  ----------------------------------------------------------------
		//-----------------------------------------------------------------------------------------------------------------------------------------
		tableListInstance.addMouseListener(new MouseAdapter() {
			public void mouseDoubleClick(final MouseEvent e) {
				TableItem item = tableListInstance.getItem(tableListInstance.getSelectionIndex());
				String fullURI = (String) item.getData();
				
				System.out.println("fullURI: "+ fullURI);
				
				if(fullURI != null){
					labelNotification.setText(item.getText());
					
					DataInstance dataInstanceOutput = new DataInstance();
					if(getController().getCompositeClassList() != null ){
						dataInstanceOutput.setClassData((ClassData)getController().getCompositeClassList().getOutputData());
					}
					else{
						dataInstanceOutput.setClassData((ClassData)getController().getCompositeLeftPopUp().getOutputData());
					}
					dataInstanceOutput.setInstanceFullName(fullURI);
					
					setOutputData(dataInstanceOutput);
					getController().updateChosenInstance();
					
				}
			}
		});
		//-----------------------------------------------------------------------------------------------------------------------------------------
		//-----------------------------------------------------------------------------------------------------------------------------------------
		final FormData fd_tableListInstance = new FormData();
		fd_tableListInstance.right = new FormAttachment(100, 0);
		fd_tableListInstance.top = new FormAttachment(0, 89);
		fd_tableListInstance.bottom = new FormAttachment(100, 0);
		fd_tableListInstance.width = 0;
		fd_tableListInstance.height = 0;
		fd_tableListInstance.left = new FormAttachment(0, 0);
		tableListInstance.setLayoutData(fd_tableListInstance);
		tableListInstance.setHeaderVisible(false);
		
		
		// TODO Auto-generated constructor stub
	}

	public void addSelectItemOnToolBar()
	{
		selectItem = new ToolItem(toolBar, SWT.PUSH);
		selectItem.setToolTipText("Select this Instance");
		selectItem.setText("Select");
		//selectItem.setText("Select");
		selectItem.setImage(Images.imageRegistry.get(Images.SAVE));
		selectItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				System.out.println("Chay vao trong Selected Event ");
				isSelect=true;
				
				int index = tableListInstance.getSelectionIndex();
				System.out.println("index "+ index);
				TableItem item = tableListInstance.getItem(index);
				
				DataInstance instanceData = new DataInstance();
				instanceData.setInstanceFullName((String)item.getData());
				//-- chua xet den classData cho instanceData --
				
				System.out.println("instance Full Name : "+ instanceData.getInstanceFullName() );
				
				setOutputData(instanceData);
				System.out.println("Chay den truoc updateForPopUp");
				getController().updateForPopUp();
				System.out.println("Chay duoc den sau updateChosenInstance() + truoc close()");
				
				toolBar.getShell().close();
			}
		});

	}
	
	@Override
	public int updateInterface() {
		cleanTable();
		
		ClassData classData = (ClassData)getInputData();

		String classFullName = classData.getClassURI();

		System.out.println("classFullName" + classFullName + " - Class Name : " + classData.getClassName());
		
		java.util.List<String> listInst = service.Service.webServiceDelegate.listAllRelatedInstance(null, classFullName);
		listInstanceName = listInst;
		int quantityOfInstance = listInst.size();
		
		if(quantityOfInstance < 1 ){
			labelNotification.setText("There is no instance of class : " + classData.getClassName());
		}
		else 
			if(quantityOfInstance == 1 ){
				labelNotification.setText("There is one instance of class : " + classData.getClassName());
			}
			else{
				labelNotification.setText("There are "+ quantityOfInstance +" instances of class : " + classData.getClassName());
			}
		
		bindDataToTable(listInst);
		
		return 0;
	}
	
	public void cleanTable(){
		for (TableItem item : tableListInstance.getItems())
		{
			item.dispose();
		}
		tableListInstance.removeAll();
		for (Control control : tableListInstance.getChildren())
			control.dispose();
	}

	public void bindDataToTable(java.util.List<String> listIstance){

		for( String instanceName : listIstance){
			String localName = instanceName.substring(instanceName.indexOf("#")+1);
			TableItem item = new TableItem(tableListInstance, SWT.NONE);
			item.setData(instanceName);
			item.setText(localName);
			System.out.println(" instance Name : " + instanceName);
			
			}
	}
	// Insert instances to table
	//public void addInstanceToTable() {
		
	//}

}
