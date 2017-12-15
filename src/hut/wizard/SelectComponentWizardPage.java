package hut.wizard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import mintani.valueconst.ConsistentOntology;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import service.Service;
import swing2swt.layout.BorderLayout;


public class SelectComponentWizardPage extends WizardPage {

	private CCombo comboFind;
	private Text textFind;
	private Table table;
	private CCombo combo;
	private CheckboxTableViewer tableViewer;
	private ViewerFilter filter;
	private List<String[]> items = new ArrayList<String[]>(); 
	private List<String> components = new ArrayList<String>();
	
	private ISelectionChangedListener listener = null;
	
	/**
	 * Create the wizard
	 */
	public SelectComponentWizardPage(List<String> components) {
		super("wizardPage");
		setTitle("Select Software Component");
		setDescription("Select component that this test for");
		setPageComplete(true);
	
		if(components != null)
			this.components = components;
		}

	/**
	 * Create contents of the wizard
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new BorderLayout(0, 0));
		//
		setControl(container);

		final Composite composite = new Composite(container, SWT.NONE);
		composite.setLayoutData(BorderLayout.NORTH);

		final Label typeComponentLabel = new Label(composite, SWT.NONE);
		typeComponentLabel.setBounds(0, 2, 93, 20);
		typeComponentLabel.setText("Type Component");

		combo = new CCombo(composite, SWT.BORDER);
		combo.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		combo.setEditable(false);
		combo.setBounds(108, 0, 162, 23);
		for (String component : components) {
			combo.add(component);
		}
		combo.select(0);

		final Button hideColumnsButton = new Button(composite, SWT.NONE);
		hideColumnsButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				HideColumnsDialog dialog = new HideColumnsDialog(((Button)e.widget).getShell());
				
				TableColumn[] columns = table.getColumns();
				for(int i= 1; i < columns.length; i++)
				{
					HideColumnsDialog.columns.add(columns[i].getText());
					if(columns[i].getWidth() != 0)
						HideColumnsDialog.selectedColumns.add(columns[i].getText());
				}
				
				if(dialog.open() == InputDialog.OK)
				{
					List<String> list = HideColumnsDialog.selectedColumns;					
					for(int i = 1; i< columns.length ; i++)
					{
						if(!list.contains(columns[i].getText())) // --> hide
							hideColumn(i);
						else
							showColumn(i);
					}
				}				
			}
		});
		hideColumnsButton.setText("Hide Columns");
		hideColumnsButton.setBounds(290, 0, 123, 25);

		final Composite composite_1 = new Composite(container, SWT.NONE);
		final FillLayout fillLayout_1 = new FillLayout();
		fillLayout_1.marginHeight = 3;
		composite_1.setLayout(fillLayout_1);

		table = new Table(composite_1, SWT.CHECK | SWT.FULL_SELECTION | SWT.SINGLE);
		tableViewer = new CheckboxTableViewer(table); 
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		final Composite composite_2 = new Composite(container, SWT.NONE);
		composite_2.setLayoutData(BorderLayout.SOUTH);

		final Label filterLabel = new Label(composite_2, SWT.NONE);
		filterLabel.setBounds(0, 3, 26, 19);
		filterLabel.setText("Find");

		textFind = new Text(composite_2, SWT.BORDER);
		textFind.addModifyListener(new ModifyListener() {
			public void modifyText(final ModifyEvent e) {
				updateFilter();
			}
		});
		textFind.setBounds(32, 0, 185, 19);

		final Label inColumnLabel = new Label(composite_2, SWT.NONE);
		inColumnLabel.setText("In column");
		inColumnLabel.setBounds(235, 3, 46, 13);

		comboFind = new CCombo(composite_2, SWT.BORDER);
		comboFind.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		comboFind.setEditable(false);
		comboFind.add("All");
		comboFind.select(0);
		comboFind.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				updateFilter();
			}
		});
		comboFind.setBounds(289, 0, 183, 19);
		createActions();
		initData(combo.getItem(0));
	}
	private void updateFilter()
	{
		if(filter != null)
			tableViewer.removeFilter(filter);
		filter = new ViewerFilter(){
			@Override
			public boolean select(Viewer viewer, Object parent, Object element) {
				String[] item = (String[]) element;
				String columnName = comboFind.getText();
				int index = getIndexOfColumn(columnName);
				if(index == 0) // All
				{
					for (String string : item) {
						if(string.contains(textFind.getText()))
							return true;
					}
					return false;
				}
				else if(item[index].contains(textFind.getText()))
					return true;
				return false;
			}};
		tableViewer.addFilter(filter);
	}
	private int getIndexOfColumn(String columnName)
	{
		TableColumn[] columns = table.getColumns();
		for (int i= 1; i< columns.length;i++) {
			TableColumn column = columns[i];
			if(column.getText().equals(columnName))
				return i;
		}
		return 0; // All
	}
	private List<String> initHeader(String className)
	{
		List<String> properties = null;//new ArrayList<String>();		
		try {
			properties = new ArrayList<String>();
			properties.add("Label");
			//OntologyUtil.getAllProperties(	loader.getModel(),ConsistentAxioms.ns + className, false);
			// xap xep va loai bo cac property trung nhau
			Collections.sort(properties);
			List<Integer> listIndex = new ArrayList<Integer>();
			for(int i= properties.size() - 1; i>0; i--)
			{
				if(properties.get(i).equals(properties.get(i-1)))
					listIndex.add(new Integer(i));
			}
			for (Integer integer: listIndex) {
				properties.remove(integer.intValue());
			}
			if(properties.contains("hasDirectType"))
				properties.remove(new String("hasDirectType"));
			
			properties.add(0, "URI");
			TableColumn checkColumn = new TableColumn(table, SWT.NONE);
			checkColumn.setText(" ");
			checkColumn.setWidth(20);
			checkColumn.setResizable(false);
			comboFind.removeAll();
			comboFind.add("All");
			for (int i=0; i<properties.size(); i++) {
				TableColumn column = new TableColumn (table, SWT.NONE);
				column.setText (properties.get(i));
				comboFind.add(properties.get(i));					
			}	
			comboFind.select(0);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return properties;
	}
	
	private void initData(String className)
	{
		//List<String> properties = initHeader(className);
		StringBuffer query = new StringBuffer(ConsistentOntology.prefix)
						.append("SELECT DISTINCT ?URI ?Label ")
						.append("where{\n")
						.append("?URI SEC:hasDirectType '").append(className).append("' ");
//		for (int i=1; i< properties.size(); i++) { //bo qua property: URI voi index=0
//			String property = properties.get(i);
//			query.append("\noptional{")
//							.append("?URI sec:").append(property).append(" ?").append(property)
//							.append("}");
//		}
		query.append("\noptional{?URI rdfs:label ?Label}");
		query.append("}");
		//System.out.println(query.toString());
		
		
		//Thuc hien truy van len webservice
		

	    List<ws.data.ArrayListData> result = Service.dataServiceDelegate.sparqlResultList(null, query.toString());
	    
	    
	    List<String> colitems = result.get(0).getData();
	    for (int i = 0; i < colitems.size(); i++)
		{
	    	TableColumn column = new TableColumn (table, SWT.NONE);
			column.setText (colitems.get(i).toString());
			comboFind.add(colitems.get(i).toString());	
		}
	    boolean[] hasData = new boolean[colitems.size()];
	    for (int i = 1; i < result.size(); i++){
	    	int index = 0;
	    	List<String> subitems = result.get(i).getData();
	    	String[] item = new String[subitems.size()];
	    	
	    	for(int j=0; j<subitems.size();j++){
	    		item[index]= subitems.get(j).toString();
	    		
	    		if(!item[index].equals(""))
        			hasData[index] = true;
	    		
	    		index++;
	    	}   	
	    	items.add(item);
	    }
			
	    tableViewer.refresh();			
        for (int i = 0; i < colitems.size(); i++) {
			table.getColumn(i).pack();
			if(!hasData[i]) // no dat
				//showColumn(i);
				hideColumn(i);
		}	
	    
		//properties.clear();
	}
	private void createActions()
	{
		combo.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent arg0) {
				fireChangeComponent();
			}});
		tableViewer.setContentProvider(new TableContentProvider());
		tableViewer.setLabelProvider(new TableLabelProvider());
		tableViewer.setInput(items);
		
		if(listener != null)
			tableViewer.addSelectionChangedListener(listener);	
		tableViewer.addSelectionChangedListener(new ISelectionChangedListener(){
			public void selectionChanged(SelectionChangedEvent e) {
				updateButton();
			}});
	}
	
	private void fireChangeComponent()
	{
		clearTable();		
		//items.clear();
		initData(combo.getText());
	}
	
	private void clearTable()
	{
		table.removeAll();
		for (TableColumn column : table.getColumns()) {
			column.dispose();
		}
		items.clear();
	}
	@Override
	public boolean isPageComplete() {
		int count = tableViewer.getCheckedElements().length;			
		if(count > 0)
			return true;
		else
			return false;
	}

	public void updateButton()
	{
		getWizard().getContainer().updateButtons();		
	}
	public void hideColumn(int index)
	{
		TableColumn column = table.getColumns()[index];
		boolean found = false;
		for (String item : comboFind.getItems()) {
			if(item.equals(column.getText()))
			{
				found = true;
				break;
			}
		}
		if(found)
		{
			comboFind.remove(column.getText());
		}
		column.setResizable(true);
		column.setWidth(0);							
		column.setResizable(false);
	}
	public void showColumn(int index)
	{
		TableColumn column = table.getColumns()[index];
		column.setResizable(true);
		column.pack();
		boolean found = false;

		for (String item : comboFind.getItems()) {
			if(item.equals(column.getText()))
			{
				found = true;
				break;
			}
		}
		if(!found)
		{
			comboFind.add(column.getText());
			String[] items = comboFind.getItems();
			comboFind.removeAll();			
			Arrays.sort(items);
			for (String string : items) {
				comboFind.add(string);
			}
		}

	}
	public Object[] getSelectedComponent()
	{
		return tableViewer.getCheckedElements();
	}
	public void setISelectionChangedListener(ISelectionChangedListener listen)
	{
		this.listener = listen;
	}
	class TableContentProvider implements IStructuredContentProvider {
		public void dispose() {
		}
		public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		}
		public Object[] getElements(Object arg0) {
			//return ((List)arg0).toArray();
			return items.toArray();
		}
	}

	class TableLabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object arg0, int arg1) {			
			return null;
		}
		public String getColumnText(Object item, int index) {
			return ((String[])item)[index];
		}
		public void addListener(ILabelProviderListener arg0) {
		}
		public void dispose() {
		}
		public boolean isLabelProperty(Object arg0, String arg1) {
			return false;
		}
		public void removeListener(ILabelProviderListener arg0) {			
		}	  
	}

	public CheckboxTableViewer getTableViewer() {
		return tableViewer;
	}
}
