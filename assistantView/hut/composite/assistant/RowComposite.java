package hut.composite.assistant;


import java.util.ArrayList;
import java.util.List;

import hut.composite.assistant.DataTypeComposite;
import ontology.images.Images;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.widgets.FormToolkit;

import ws.owl.WebServiceDelegate;
import ws.owl.WebServiceService;



public class RowComposite extends Composite {
	private Table table;
	public ToolItem addItem;
	public ToolItem deleteItem;
	private ToolItem uriItem;
	private ToolItem editItemToolBar;
	private ToolItem deleteItemToolBar;
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public RowComposite(Composite parent, int style,List<String> listValuePropertyName) {
		super(parent, style);
		setLayout(new FormLayout());
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);

		table = new Table(this, SWT.BORDER);
		final FormData fd_table = new FormData();
		fd_table.right = new FormAttachment(100, -2);
		fd_table.bottom = new FormAttachment(0, 79);
		fd_table.left = new FormAttachment(0, 0);
		table.setLayoutData(fd_table);
		toolkit.adapt(table, true, true);

		
		
		ToolBar toolBar;
		toolBar = new ToolBar(this, SWT.FLAT);
		fd_table.top = new FormAttachment(toolBar, 5, SWT.BOTTOM);
		final FormData fd_toolBar = new FormData();
		fd_toolBar.right = new FormAttachment(100, -2);
		fd_toolBar.left = new FormAttachment(100, -65);
		fd_toolBar.bottom = new FormAttachment(0, 22);
		fd_toolBar.top = new FormAttachment(0, 0);
		toolBar.setLayoutData(fd_toolBar);
		toolkit.adapt(toolBar, true, true);

		editItemToolBar= new ToolItem(toolBar, SWT.PUSH);
		editItemToolBar.setImage(Images.imageRegistry.get(Images.ADD));
		
		
		
		deleteItemToolBar = new ToolItem(toolBar, SWT.PUSH);
		deleteItemToolBar.setImage(Images.imageRegistry.get(Images.DELETE));
		
		
		
		if(listValuePropertyName != null){		
			for(int i=0;i<listValuePropertyName.size();i++){
				TableItem item = new TableItem(table, SWT.NONE);
				item.setData(listValuePropertyName.get(i).toString());
				item.setText(listValuePropertyName.get(i).toString());
				item.setImage(Images.imageRegistry.get(Images.FIELD));
			}
		}	
		//Tach rieng action ra
		registerAction();
		
		
		//addItemToTable();
		//
	}
	
	
	
	public void processDoubleClick(){
		
		System.out.println("Double Click");
		final TableItem item = table.getItem(table.getSelectionIndex());
		
		Shell shell = new Shell(deleteItemToolBar.getDisplay());
		shell.setText("Editor");
		shell.setImage(Images.imageRegistry.get(Images.BOOKMARK));
		shell.setLayout(new FillLayout());
		shell.setSize(320,200);
		
		int x=Math.max(shell.getDisplay().getCursorLocation().x-shell.getSize().x, 0);
		int y=Math.max(shell.getDisplay().getCursorLocation().y-shell.getSize().y, 0);
		shell.setLocation(x,y);
		
		final DataTypeComposite dataTypeComposite=new DataTypeComposite(shell,SWT.NONE);
		shell.addListener(SWT.Close, new Listener() {
			public void handleEvent(Event e) {
				String s=dataTypeComposite.getValue();
				if (s!=null)
				{
					item.setText(s);
				}
				dataTypeComposite.dispose();
			}
		});
		dataTypeComposite.setValue(item.getText());
		shell.open();
	}
	
	public void addItemToTable()//Dung de them 1 individual vao trong bang gia tri
	{
		TableItem item = new TableItem(table, SWT.NONE,0);
		String s="bbb";
		item.setText(s);
		item.setImage(Images.imageRegistry.get(Images.METHOD_PUBLIC));
		
	}
	
	/*
	 * remove item
	 */
	
	public void removeItemProcess(){
		int selected=table.getSelectionIndex();
		TableItem item=table.getItem(selected);
		//Note: co the remove thuoc tinh o day cung duoc,hoac sau nay ta update lai tat cho individual do
		table.remove(selected);
		
	}
	
	
	public void  addItemProcess(){
		Shell shell = new Shell(editItemToolBar.getDisplay());
		shell.setText("Editor");
		shell.setImage(Images.imageRegistry.get(Images.BOOKMARK));
		shell.setLayout(new FillLayout());
		shell.setSize(320,200);
		
		int x=Math.max(shell.getDisplay().getCursorLocation().x-shell.getSize().x, 0);
		int y=Math.max(shell.getDisplay().getCursorLocation().y-shell.getSize().y, 0);
		shell.setLocation(x,y);
		
		final DataTypeComposite dataTypeComposite=new DataTypeComposite(shell,SWT.NONE);
		shell.addListener(SWT.Close, new Listener() {
			public void handleEvent(Event e) {
				String s=dataTypeComposite.getValue();
				if (s!=null)
				{
					TableItem item = new TableItem(table, SWT.NONE,0);
					item.setText(s);
				}
				dataTypeComposite.dispose();
			}
		});
		
		shell.open();
	}
	
	
	public void registerAction(){
		editItemToolBar.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) 
			{
				addItemProcess();
			}
		});
		
		deleteItemToolBar.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(final SelectionEvent e) 
			{
				removeItemProcess();
			}
		});
		
		table.addListener(SWT.MouseDoubleClick, new Listener() {
		    public void handleEvent(Event event) {
		       processDoubleClick();
		    }
		});
	}
	
	public List<String> getListDataValue()
	{
		List<String> result=new ArrayList();
		for (TableItem item: table.getItems())
		{
			result.add(item.getText());
		}
		return result;
	}

}
