package hut.composite.annotationCreatorAndEditor;

//import hut.composite.annotationEditor1.CompositeDatatype1;

import hut.controller.annotationCreatorAndEditor.ControllerAnnotationCreatorAndEditor;
import hut.dialog.DateTimeDialog;
import hut.model.annotationCreatorAndEditor.DataInstance;


import java.util.ArrayList;
import java.util.List;

import ontology.images.Images;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.widgets.FormToolkit;

import ws.owl.LanguageEnum;
import ws.owl.PropertyData;
import ws.owl.WebServiceDelegate;
import ws.owl.WebServiceService;

import com.swtdesigner.SWTResourceManager;

public class CompositeRow extends SuperCompositeAnnotationCreatorAndEditor {

	private Table table;
	private ToolBar toolBar;
	private ToolItem addToolItem;
	private ToolItem deleteToolItem;
	private ControllerAnnotationCreatorAndEditor controllerForPopup;
	
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	
	public CompositeRow(Composite parent, int style, final String instanceFullName, final PropertyData propertyData, List<String> value) {
		super(parent, style);
		setLayout(new FormLayout());
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);

		//--------------------------------------------------------------------------------------------------------------
		//--------------------------------------------------------------------------------------------------------------
		System.out.println("style: "+ style);
		System.out.println("instanceFullName: " + instanceFullName);
		System.out.println("PropertyData: "+  propertyData);
		System.out.println("value List: " +  value);
		//--------------------------------------------------------------------------------------------------------------
		//--------------------------------------------------------------------------------------------------------------
		toolBar = new ToolBar(this, SWT.NONE);
		final FormData fd_toolBar = new FormData();
		fd_toolBar.left = new FormAttachment(100, -91);
		fd_toolBar.bottom = new FormAttachment(0, 25);
		fd_toolBar.top = new FormAttachment(0, 0);
		toolBar.setLayoutData(fd_toolBar);
		toolkit.adapt(toolBar, true, true);

		
		
		
		
		//item.setImage(Images.imageRegistry.get(Images.FIELD));
		addToolItem = new ToolItem(toolBar, SWT.PUSH);
		//--------------------- addToolItem selected Listener ----------------------------------------------------------
		//--------------------------------------------------------------------------------------------------------------
		addToolItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				

				String propertyName = propertyData.getPropertURI();
				System.out.println(propertyName);
				
			//--------- If property is Data Type Property --------------------
 				if (propertyData.isDatatypeProperty()) {
					String type = service.Service.webServiceDelegate.getPropertySpecificDataType(null, propertyName);
					System.out.println(type);
					
				//-------- if Property is Date Type --------------------------
					if (type.equals("date") || type.equals("dateTime")) {
						TableItem item = new TableItem(table, SWT.NONE, 0);
						DateTimeDialog dialog = new DateTimeDialog(table.getShell());
						if (dialog.open() == InputDialog.OK) {
							
//							DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
							if(type.equals("date")){
								item.setText(dialog.SYear + "-" + dialog.SMonth	+ "-" + dialog.SDay);
								item.setData("Data",dialog.SYear + "-" + dialog.SMonth	+ "-" + dialog.SDay);
							}
							else
								if(type.equals("dateTime")){
									item.setText(dialog.SYear + "-" + dialog.SMonth	+ "-" + dialog.SDay + "  " + dialog.SHour + ":"+dialog.SMinute+":"+dialog.SSecond);
									item.setData("Data",dialog.SYear + "-" + dialog.SMonth	+ "-" + dialog.SDay + "T" + dialog.SHour + ":"+dialog.SMinute+":"+dialog.SSecond);
								}
							item.setImage(Images.imageRegistry.get(Images.FIELD));
						}
					} 
					
					else {
						if(type.equals("boolean")){
							Shell shell = new Shell(addToolItem.getDisplay());
							shell.setText("Editor");
							shell.setImage(Images.imageRegistry.get(Images.BOOKMARK));
							shell.setLayout(new FillLayout());
							shell.setSize(320,200);
							
							int x=Math.max(shell.getDisplay().getCursorLocation().x-shell.getSize().x, 0);
							int y=Math.max(shell.getDisplay().getCursorLocation().y-shell.getSize().y, 0);
							shell.setLocation(x,y);
							
							final CompositeBooleanType compositeBooleanType = new CompositeBooleanType(shell, SWT.NONE);
							
							shell.addListener(SWT.Close, new Listener() {
								public void handleEvent(Event e) {
									String s = compositeBooleanType.getValue();
									boolean isSaved = compositeBooleanType.getIsSaved();
									if (isSaved)
									{
										TableItem item = new TableItem(table, SWT.NONE,0);
										item.setText(s);
										item.setData("Data", s);
										item.setImage(Images.imageRegistry.get(Images.FIELD));
									}
									compositeBooleanType.dispose();
								}
							});
							
							shell.open();
							
						}
						else{
							{
								String label = service.Service.webServiceDelegate.getLabelFromResource(null, propertyName, LanguageEnum.DE);
								if (label != null) {
									//------------- If Label is Path Property ---------------------------
									if (label.equals("isPathProperty")) {

										TableItem item = new TableItem(table, SWT.NONE,0);
										FileDialog fd = new FileDialog(item.getParent().getShell(), SWT.OPEN);

										fd.setText("Choose File");

										fd.setFilterNames(new String[] {"Word Files (*.doc)","Text Files (*.txt)" });
										fd.setFilterExtensions(new String[] { "*.doc","*.txt" });

										String s = fd.open();
										if (s != null) {
											item.setText(s);
											item.setData("Data", s);
											item.setImage(Images.imageRegistry.get(Images.FIELD));
										}

									}
								}
								
								else 
								{
									Shell shell = new Shell(addToolItem.getDisplay());
									shell.setText("Editor");
									shell.setImage(Images.imageRegistry.get(Images.BOOKMARK));
									shell.setLayout(new FillLayout());
									shell.setSize(320,200);
									
									int x=Math.max(shell.getDisplay().getCursorLocation().x-shell.getSize().x, 0);
									int y=Math.max(shell.getDisplay().getCursorLocation().y-shell.getSize().y, 0);
									shell.setLocation(x,y);
									
									final CompositeDataType compositeDatatype = new CompositeDataType(shell,SWT.NONE);
									shell.addListener(SWT.Close, new Listener() {
										public void handleEvent(Event e) {
											String s = compositeDatatype.getValue();
											boolean isSaved = compositeDatatype.getIsSaved();
											if ((s!= null) && isSaved )
											{
												TableItem item = new TableItem(table, SWT.NONE,0);
												item.setText(s);
												item.setData("Data", s);
												item.setImage(Images.imageRegistry.get(Images.FIELD));
											}
											compositeDatatype.dispose();
										}
									});
									
									shell.open();
								}
							}
						}
					}

				}
			//------------- If Property is Object Property ---------------------------------------------	
				else if(propertyData.isObjectProperty())
				{
					
					System.out.println("isObjectProperty");
					Shell shell = new Shell(addToolItem.getDisplay());
					shell.setText("Chose an instance !");
					shell.setImage(Images.imageRegistry.get(Images.BOOKMARK));
					shell.setLayout(new FillLayout());
					
					// List ClassRange Of Object Property
					List<String> classList = service.Service.webServiceDelegate.getObjectPropertyRanges(null, propertyName);
					for(String classL: classList){
						System.out.println(classList.size()+ "  Rangle Property: " + classL);
					}
					//final CompositePopUp1 compositePopUp=new CompositePopUp1(shell,SWT.NONE,classList);
					final CompositePopUp compositePopUp = new CompositePopUp(shell,SWT.NONE, classList);
					
					
					//controllerForPopup = new ControllerAnnotationCreatorAndEditor();
					//compositePopUp.setController(getControllerForPopup());
					
					//compositePopUp.getController().setCompositePopUp(compositePopUp);
					
					shell.addListener(SWT.Close, new Listener() {
						public void handleEvent(Event e) {
							//Lay ra individual duoc select
							if (compositePopUp.getOutputData() instanceof DataInstance)
							{
							String newObjectPropertyValue = ((DataInstance)compositePopUp.getOutputData()).getInstanceFullName();
							if (newObjectPropertyValue != null)
							{
								TableItem item = new TableItem(table, SWT.NONE,0);
								item.setData("Data", (DataInstance)compositePopUp.getOutputData());
								
								String s = newObjectPropertyValue.substring(newObjectPropertyValue.lastIndexOf("#") + 1);
								item.setText(s);
								item.setImage(Images.imageRegistry.get(Images.METHOD_PRIVATE));
								
							}
							}
							
							compositePopUp.dispose();
						}
					});
					
					shell.open();
				}
					
			}
		});
		//--------------------------------------------------------------------------------------------------------------
		//--------------------------------------------------------------------------------------------------------------
		addToolItem.setImage(SWTResourceManager.getImage(CompositeRow.class, "/ontology/images/add.gif"));

		deleteToolItem = new ToolItem(toolBar, SWT.PUSH);
		//------------------ Delete Button : Selection Event -----------------------------------------------------------
		//--------------------------------------------------------------------------------------------------------------
		deleteToolItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				int index = table.getSelectionIndex();
//				TableItem item = table.getItem(index);
				table.remove(index);
			}
		});
		//--------------------------------------------------------------------------------------------------------------
		//--------------------------------------------------------------------------------------------------------------
		deleteToolItem.setImage(SWTResourceManager.getImage(CompositeRow.class, "/ontology/images/delete.gif"));

		table = new Table(this, SWT.BORDER);
		//-----------------Existing item Double Click ------------------------------------------------------------------
		//--------------------------------------------------------------------------------------------------------------
		table.addMouseListener(new MouseAdapter() {
			public void mouseDoubleClick(final MouseEvent e) {
				final TableItem itemSelected = table.getItem(table.getSelectionIndex());
				System.out.println("index: " + table.getSelectionIndex());
				
				//--------- Process Double Click Item -------------------
				//-------------------------------------------------------
				

				String propertyName = propertyData.getPropertURI();
				System.out.println(propertyName);
				
				//--------- If property is Data Type Property --------------------
 				if (propertyData.isDatatypeProperty()) { 
					String type = service.Service.webServiceDelegate.getPropertySpecificDataType(null, propertyName);
					System.out.println(type);
					
					//-------- if Property is date || dateTime Type --------------------------
					if (type.equals("date") || type.equals("dateTime")) {
						
						DateTimeDialog dialog = new DateTimeDialog(table.getShell());
						
						String data = (String)itemSelected.getData("Data");
						String[] arrayTime = data.split("-");
						// Chu y xem tung truong hop neu ko co du thi sao ? 
						String year = arrayTime[0];
						String month = arrayTime[1];
						String day = arrayTime[2];
						
						dialog.SYear = year;
						dialog.SMonth = month;
						dialog.SDay = day;
						
						
						if (dialog.open() == InputDialog.OK) {
							
							//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
							if(type.equals("date")){
								itemSelected.setText(dialog.SYear + "-" + dialog.SMonth	+ "-" + dialog.SDay);
								itemSelected.setData("Data",dialog.SYear + "-" + dialog.SMonth	+ "-" + dialog.SDay);
							}
							else
								if(type.equals("dateTime")){
									itemSelected.setText(dialog.SYear + "-" + dialog.SMonth	+ "-" + dialog.SDay + "  " + dialog.SHour + ":"+dialog.SMinute+":"+dialog.SSecond);
									itemSelected.setData("Data",dialog.SYear + "-" + dialog.SMonth	+ "-" + dialog.SDay + "'T'" + dialog.SHour + ":"+dialog.SMinute+":"+dialog.SSecond);
								}
							itemSelected.setImage(Images.imageRegistry.get(Images.FIELD));
						}
					} 
					
					else {
						if(type.equals("boolean")){
							Shell shell = new Shell(addToolItem.getDisplay());
							shell.setText("Editor");
							shell.setImage(Images.imageRegistry.get(Images.BOOKMARK));
							shell.setLayout(new FillLayout());
							shell.setSize(320,200);
							
							int x=Math.max(shell.getDisplay().getCursorLocation().x-shell.getSize().x, 0);
							int y=Math.max(shell.getDisplay().getCursorLocation().y-shell.getSize().y, 0);
							shell.setLocation(x,y);
							
							final CompositeBooleanType compositeBooleanType = new CompositeBooleanType(shell, SWT.NONE);
							compositeBooleanType.setValue((String)itemSelected.getData("Data"));
							
							shell.addListener(SWT.Close, new Listener() {
								public void handleEvent(Event e) {
									String s = compositeBooleanType.getValue();
									boolean isSaved = compositeBooleanType.getIsSaved();
									if (isSaved)
									{
										itemSelected.setText(s);
										itemSelected.setData("Data", s);
										itemSelected.setImage(Images.imageRegistry.get(Images.FIELD));
									}
									compositeBooleanType.dispose();
								}
							});
							
							shell.open();
							
						}
						else
						{
							String label = service.Service.webServiceDelegate.getLabelFromResource(null, propertyName, LanguageEnum.DE);
							if (label != null) {
								//------------- If Label is Path Property ---------------------------
								if (label.equals("isPathProperty")) {

									//TableItem item = new TableItem(table, SWT.NONE,0);
									FileDialog fd = new FileDialog(itemSelected.getParent().getShell(), SWT.OPEN);

									fd.setText("Choose File");

									fd.setFilterNames(new String[] {"Word Files (*.doc)","Text Files (*.txt)" });
									fd.setFilterExtensions(new String[] { "*.doc","*.txt" });
									
									// Set xem co link den thu muc da duoc chi ra khong ?
									
									String s = fd.open();
									if (s != null) {
										itemSelected.setText(s);
										itemSelected.setData("Data", s);
										itemSelected.setImage(Images.imageRegistry.get(Images.FIELD));
									}

								}
							}
							
							else 
							{
								Shell shell = new Shell(addToolItem.getDisplay());
								shell.setText("Editor");
								shell.setImage(Images.imageRegistry.get(Images.BOOKMARK));
								shell.setLayout(new FillLayout());
								shell.setSize(320,200);
								
								int x=Math.max(shell.getDisplay().getCursorLocation().x-shell.getSize().x, 0);
								int y=Math.max(shell.getDisplay().getCursorLocation().y-shell.getSize().y, 0);
								shell.setLocation(x,y);
								
								
								
								final CompositeDataType compositeDatatype = new CompositeDataType(shell,SWT.NONE);
								// set Data for compositeDataType here !
								compositeDatatype.setValue((String)itemSelected.getData("Data"));
								
								shell.addListener(SWT.Close, new Listener() {
									public void handleEvent(Event e) {
										String s = compositeDatatype.getValue();
										boolean isUsed = compositeDatatype.getIsSaved();
										
										if ((s!= null) && isUsed)
										{
											itemSelected.setText(s);
											itemSelected.setData("Data", s);
											itemSelected.setImage(Images.imageRegistry.get(Images.FIELD));
										}
										compositeDatatype.dispose();
									}
								});
								
								shell.open();
							}
						}
					}

				}
			//------------- If Property is Object Property ---------------------------------------------	
				else if(propertyData.isObjectProperty())
				{
					
					System.out.println("isObjectProperty");
					Shell shell = new Shell(addToolItem.getDisplay());
					shell.setText("Edit instance !");
					shell.setImage(Images.imageRegistry.get(Images.BOOKMARK));
					shell.setLayout(new FillLayout());
					shell.setSize(510, 680);
					
					DataInstance dataInstance = (DataInstance)itemSelected.getData("Data");
					System.out.println("instanceFullName :" + dataInstance.getInstanceFullName() );
					
//					dataInstance.setInstanceFullName(instanceFullName);
					
					
					CompositeListPropertiesOfInstance compositeListProperties = new CompositeListPropertiesOfInstance(shell, SWT.NONE);
					compositeListProperties.setInputData(dataInstance);
					compositeListProperties.updateInterface();
					
					
					
					shell.addListener(SWT.Close, new Listener() {
						public void handleEvent(Event e) {
							//Lay ra individual duoc select
//							if (compositePopUp.getOutputData() instanceof DataInstance)
//							{
//							String newObjectPropertyValue = ((DataInstance)compositePopUp.getOutputData()).getInstanceFullName();
//							if (newObjectPropertyValue != null)
//							{
//								
//								itemSelected.setData("Data", newObjectPropertyValue);
//								String s = newObjectPropertyValue.substring(newObjectPropertyValue.lastIndexOf("#") + 1);
//								itemSelected.setText(s);
//								itemSelected.setImage(Images.imageRegistry.get(Images.METHOD_PRIVATE));
//								
//							}
//							}
							
						}
					});
					
					shell.open();
				}
				//-------------------------------------------------------
			}

			
		});
		//--------------------------------------------------------------------------------------------------------------
		//--------------------------------------------------------------------------------------------------------------

		fd_toolBar.right = new FormAttachment(table, 0, SWT.RIGHT);
		table.setLayout(new FormLayout());
		final FormData fd_table = new FormData();
		fd_table.bottom = new FormAttachment(100, 0);
		fd_table.top = new FormAttachment(toolBar, 0, SWT.BOTTOM);
		fd_table.right = new FormAttachment(100, 0);
		fd_table.left = new FormAttachment(0, 0);
		fd_table.height = 0;
		fd_table.width = 0;
		table.setLayoutData(fd_table);
		toolkit.adapt(table, true, true);
		//--------------------------------------------------------------------------------------------------------------
		//--------------------------------------------------------------------------------------------------------------
		if (!propertyData.isDatatypeProperty()) {
			Cursor cursor = new Cursor(parent.getDisplay(), SWT.CURSOR_HAND);
			table.setCursor(cursor);
			table.setForeground(table.getDisplay().getSystemColor(SWT.COLOR_BLUE));

		}
		
		//-- use value for setData("Data", dataInstance) ---- xet cho tung dong trong CompositeRow khong phai compositeListProperties
		if (value.size() > 0) {
			for (String s : value) {
				
				TableItem item = new TableItem(table, SWT.NONE);
				if(propertyData.isObjectProperty()){
					DataInstance dataInstance = new DataInstance();
					dataInstance.setInstanceFullName(s);
					item.setData("Data",dataInstance);
					item.setData("fullURI", s);
				}
				else{
					item.setData("Data",s);
					item.setData("fullURI", s);
				}
				
				int x = s.indexOf("#");
						
				item.setText(s.substring(x + 1));
				
				if (propertyData.isDatatypeProperty())
					item.setImage(Images.imageRegistry.get(Images.FIELD));
				if (propertyData.isObjectProperty())
					item.setImage(Images.imageRegistry.get(Images.METHOD_PRIVATE));
			}
		}
		//--------------------------------------------------------------------------------------------------------------
		//--------------------------------------------------------------------------------------------------------------
	}

	public void addUriToolBarItemForObjectProperty(){
		final ToolItem URIitem = new ToolItem(toolBar, SWT.DROP_DOWN);
		URIitem.setImage(Images.imageRegistry.get(Images.URI));

		
		final Menu menu = new Menu(toolBar);
		addDropDown(URIitem, menu);

		final MenuItem fulluriMenuItem = new MenuItem(menu, SWT.NONE);
		fulluriMenuItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				menu.setDefaultItem(fulluriMenuItem);
				for(int i=0;i<table.getItemCount();i++){
					String fullURI = (String)table.getItem(i).getData("fullURI");
					
					table.getItem(i).setText(fullURI);
					
				}
				
			}
		});
		fulluriMenuItem.setText("Full URI");

		final MenuItem shortUriMenuItem = new MenuItem(menu, SWT.NONE);
		shortUriMenuItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				
				menu.setDefaultItem(shortUriMenuItem);
				for(int i=0;i<table.getItemCount();i++){
					String fullURI = (String)table.getItem(i).getData("fullURI");
					table.getItem(i).setText(fullURI.substring(fullURI.indexOf("#")+1));
					
				}
				
			}
		});
		shortUriMenuItem.setText("Short URI");
		
		menu.setDefaultItem(shortUriMenuItem);
		
	}
	//-- getValue()---------
	public List<Object> getValue(){
		List<Object> result=new ArrayList();
		for (TableItem item: table.getItems())
		{
			result.add(item.getData("Data"));
		}
		return result;
	}

	@Override
	int updateInterface() {
		// TODO Auto-generated method stub
		return 0;
	}

	private static void addDropDown(final ToolItem item, final Menu menu) {
		item.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (event.detail == SWT.ARROW) {
					Rectangle rect = item.getBounds();
					Point pt = new Point(rect.x, rect.y + rect.height);
					pt = item.getParent().toDisplay(pt);
					menu.setLocation(pt.x, pt.y);
					menu.setVisible(true);
				}
			}
		});
	}

	
	
	public ToolBar getToolBar() {
		return toolBar;
	}

	public void setToolBar(ToolBar toolBar) {
		this.toolBar = toolBar;
	}

	public ToolItem getAddToolItem() {
		return addToolItem;
	}

	public void setAddToolItem(ToolItem addToolItem) {
		this.addToolItem = addToolItem;
	}

	public ToolItem getDeleteToolItem() {
		return deleteToolItem;
	}

	public void setDeleteToolItem(ToolItem deleteToolItem) {
		this.deleteToolItem = deleteToolItem;
	}

	public ControllerAnnotationCreatorAndEditor getControllerForPopup() {
		return controllerForPopup;
	}

	public void setControllerForPopup(
			ControllerAnnotationCreatorAndEditor controllerForPopup) {
		this.controllerForPopup = controllerForPopup;
	}
}
