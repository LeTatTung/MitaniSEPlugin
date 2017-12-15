package hut.composite.querycreator;

import service.Service;
import ws.owl.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ontology.images.Images;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.widgets.FormToolkit;


public class CompositeVariable extends CompositeQuerySuper {

	// 1/1 Output 
	private Map<String, String> variableMap = new HashMap<String, String>();
	
	private ToolItem fastChooseToolItem;
	private CompositePopUp compositePopUp;
	private Table table;
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private ArrayList<String> listClass = new ArrayList<String>();
	public Map<String, String> getVariableMap() {
		return variableMap;
	}

	public void setVariableMap(Map<String, String> variableMap) {
		this.variableMap = variableMap;
	}

	/**
	 * Constructor, Truy vấn, khởi tạo listClass
	 * @param parent
	 * @param style
	 */
	public CompositeVariable(Composite parent, int style) {
		super(parent, style);
		setLayout(new FormLayout());
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		
		

		List<ClassData> tmpList = Service.webServiceDelegate.listClasses(null);
		for (int i = 0; i < tmpList.size(); i++)
		{
			listClass.add(tmpList.get(i).getClassName());
		}
		
		final TableViewer tableViewer = new TableViewer(this, SWT.NONE);
		table = tableViewer.getTable();
		final FormData fd_table = new FormData();
		fd_table.bottom = new FormAttachment(100, -46);
		fd_table.top = new FormAttachment(0, 5);
		fd_table.left = new FormAttachment(0, 5);
		fd_table.right = new FormAttachment(100, -5);
		table.setLayoutData(fd_table);
		toolkit.adapt(table, true, true);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		final TableColumn nameColumn = new TableColumn(table, SWT.NONE);
		nameColumn.setWidth(121);
		nameColumn.setText("Name");

		final TableColumn TypeColumn = new TableColumn(table, SWT.NONE);
		TypeColumn.setWidth(143);
		TypeColumn.setText("Type");

		final TableColumn btnDeleteRow_Column = new TableColumn(table, SWT.NONE);
		btnDeleteRow_Column.setWidth(49);

		final ToolBar toolBar = new ToolBar(this, SWT.NONE);
		final FormData fd_toolBar = new FormData();
		fd_toolBar.right = new FormAttachment(100, -5);
		fd_toolBar.top = new FormAttachment(100, -41);
		fd_toolBar.bottom = new FormAttachment(100, -5);
		fd_toolBar.left = new FormAttachment(0, 6);
		toolBar.setLayoutData(fd_toolBar);
		toolkit.adapt(toolBar, true, true);

		final ToolItem resetToolItem = new ToolItem(toolBar, SWT.PUSH);
		resetToolItem.setText("Reset");
		resetToolItem.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(final SelectionEvent e){
				resetTableVariable();
			}
		});

		final ToolItem arrangeToolItem = new ToolItem(toolBar, SWT.PUSH);
		arrangeToolItem.setText("Arrange");

		final ToolItem newVariableToolItem = new ToolItem(toolBar, SWT.PUSH);
		newVariableToolItem.setText("New variable");
		newVariableToolItem.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(final SelectionEvent e){
				addVariableRow("", "");
			}
			
		});

		fastChooseToolItem = new ToolItem(toolBar, SWT.PUSH);
		fastChooseToolItem.setText("Fast Choose");
		fastChooseToolItem.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(final SelectionEvent e){
				createPopUpShell();
			}
		});
		
	}
	
	protected void createPopUpShell() {
		
		Shell shell = new Shell(fastChooseToolItem.getDisplay());
		shell.setText("Fast Choose");
		shell.setImage(Images.imageRegistry.get(Images.COPY));
		shell.setLayout(new FillLayout());
		shell.setSize(720,400);
		int x=Math.max(shell.getDisplay().getCursorLocation().x-shell.getSize().x, 0);
		int y=Math.max(shell.getDisplay().getCursorLocation().y-shell.getSize().y, 0);
		shell.setLocation(x,y);
		
		compositePopUp = new CompositePopUp(shell,SWT.NONE);
		compositePopUp.setQueryController(this.getQueryController());
		this.getQueryController().setCompositePopUp(compositePopUp);
		shell.addListener(SWT.Close, new Listener() {
			public void handleEvent(Event e) {				
				
				compositePopUp.dispose();
			}
		});
		
		shell.open();
	}

	private void addClassDataCombo(CCombo combo)
	{
		for (int i = 0; i < listClass.size(); i++)
		{
			combo.add(listClass.get(i));
		}
	}
	
	public void addVariableRow(String variable, String className)
	{
		final TableItem item = new TableItem(table, SWT.NONE);
		TableEditor editor = new TableEditor(table);
		
		final Text textVariable = new Text(table,SWT.NONE);
		editor.grabHorizontal = true;
		editor.setEditor(textVariable, item, 0);
		
		editor = new TableEditor(table);
		final CCombo comboType = new CCombo(table, SWT.NONE);
		editor.grabHorizontal = true;
		editor.setEditor(comboType, item, 1);
		addClassDataCombo(comboType);
		
		editor = new TableEditor(table);
		Image deleteImage = Images.imageRegistry.get(Images.DELETE);
		Button btnDeleteRow = new Button(table,SWT.IMAGE_GIF);
		btnDeleteRow.setImage(deleteImage);
		editor.grabHorizontal = true;
		editor.setEditor(btnDeleteRow, item, 2);
		
		textVariable.setText(variable);
		comboType.setText(className);
		
		item.setData("text", textVariable);
		item.setData("combo", comboType);
		item.setData("delete", btnDeleteRow);
		btnDeleteRow.setData(item);
		
		if (variableMap.get(variable)!=null)
    		variableMap.remove(variable);
    	variableMap.put(variable,className);
    	
    	//Delete row
    	Listener deleteEventListener = new Listener(){
			public void handleEvent(Event event) {
				Button button = (Button)event.widget;
				TableItem item = (TableItem)button.getData(); 
				variableMap.remove(((Text)item.getData("text")).getText());
				((Text)item.getData("text")).dispose();
				((CCombo)item.getData("combo")).dispose();
				((Button)item.getData("delete")).dispose();
				updateVariableListForRestrictionTab();
				item.dispose();
				}
				   		
    	};
    	btnDeleteRow.addListener(SWT.Selection, deleteEventListener);
    	
    	//Modify
    	textVariable.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				if (needToCreateNewVariable()) addVariableRow("", "");
			}
    	});
    	
    	//Focus
    	textVariable.addFocusListener(new FocusListener(){
    		public void focusLost(final FocusEvent e) {
    			variableMap.put(((Text) e.widget).getText(),comboType.getText());
    			updateVariableListForRestrictionTab();
    		}
    		public void focusGained(final FocusEvent e) {
    			String tmp=((Text) e.widget).getText();
    			if (variableMap.get(tmp)!=null)
    	    		variableMap.remove(tmp);
    			updateVariableListForRestrictionTab();
    		}
    	});
    	
    	comboType.addFocusListener(new FocusAdapter() {
    		public void focusLost(final FocusEvent e) {
    			String tmp = textVariable.getText();
    			if (variableMap.get(tmp)!=null)
    	    		variableMap.remove(tmp);
    			variableMap.put(tmp,((CCombo) e.widget).getText());
    			updateVariableListForRestrictionTab();
    		}
    	});
    	
    	
	}
		
	private boolean needToCreateNewVariable() {
		TableItem[] items = table.getItems();
		for (int i = 0; i < items.length; i++) {
			
			if (((Text)items[i].getData("text")).getText().equals(""))
				return false;
		}
		return true;
	}

	private void resetTableVariable()
	{
		variableMap=new HashMap();
		updateVariableListForRestrictionTab();
		table.removeAll();
		for (Control control:table.getChildren())
		{
			control.dispose();
		}
		addVariableRow("","");
	}
	
	public void updateVariableListForRestrictionTab(){
		this.setOutputData(variableMap);
		this.getQueryController().variableCompositeVariableChange();
	}
	
	public void updataVariableListForVariableTab(){
		
		TableItem[] items = table.getItems();
		ArrayList<String> tmpList = new ArrayList<String>();
		for (int i = 0; i < items.length; i++) {
			
			 tmpList.add(((Text)items[i].getData("text")).getText());
				
		}
		
		ArrayList<String> lstAdd = new ArrayList<String>();
		for (String key : variableMap.keySet())
		{
			System.out.println(key);
			if (!tmpList.contains(key))
				lstAdd.add(key);
		}
		for (String key: lstAdd)
			addVariableRow(key, variableMap.get(key));	
	}
	

}
