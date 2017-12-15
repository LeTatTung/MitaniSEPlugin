package hut.composite.requirement;


import hut.wizard.HideColumnsDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ontology.images.Images;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CLabel;
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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import service.Service;
import swing2swt.layout.BorderLayout;




public class CompositeResultsView extends Composite {

	private CCombo comboFind;
	private Text textFind;
	private Table table;
	private TableViewer tableViewer;
	private ViewerFilter filter;
	private List<String[]> items = new ArrayList<String[]>(); 
	
	public TableViewer getTableViewer()
	{
		return this.tableViewer;
	}
	public CompositeResultsView(Composite parent, int style) {
		super(parent, style);
		
		createControl(parent);
	}
	public void createControl(Composite parent) {
		//Composite container = new Composite(parent, SWT.NONE);
		setLayout(new BorderLayout(0, 0));
		
		final Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new FormLayout());
		composite.setLayoutData(BorderLayout.NORTH);

		
		final Button hideColumnsButton = new Button(composite, SWT.NONE);
		hideColumnsButton.setImage(Images.imageRegistry.get(Images.HIDDEN));
		final FormData fd_hideColumnsButton = new FormData();
		fd_hideColumnsButton.right = new FormAttachment(100, 0);
		fd_hideColumnsButton.bottom = new FormAttachment(0, 27);
		fd_hideColumnsButton.top = new FormAttachment(0, 1);
		hideColumnsButton.setLayoutData(fd_hideColumnsButton);
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
					comboFind.select(0);
				}				
			}
		});
		hideColumnsButton.setText(" Hide Columns ");

		final CLabel resultsLabel = new CLabel(composite, SWT.NONE);
		final FormData fd_resultsLabel = new FormData();
		fd_resultsLabel.right = new FormAttachment(0, 60);
		fd_resultsLabel.bottom = new FormAttachment(hideColumnsButton, 19, SWT.TOP);
		fd_resultsLabel.top = new FormAttachment(hideColumnsButton, 0, SWT.TOP);
		fd_resultsLabel.left = new FormAttachment(0, 5);
		resultsLabel.setLayoutData(fd_resultsLabel);
		resultsLabel.setText("Results");
		//resultsLabel.setFont(FontUtil.getFont("", 9, SWT.BOLD, false, true));
		final Composite composite_1 = new Composite(this, SWT.NONE);
		final FillLayout fillLayout_1 = new FillLayout();
		fillLayout_1.marginHeight = 3;
		composite_1.setLayout(fillLayout_1);

		table = new Table(composite_1, SWT.FULL_SELECTION | SWT.BORDER);
		tableViewer = new TableViewer(table); 
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		final Composite composite_2 = new Composite(this, SWT.NONE);
		composite_2.setLayoutData(BorderLayout.SOUTH);

		final Label filterLabel = new Label(composite_2, SWT.CENTER);
		filterLabel.setImage(Images.imageRegistry.get(Images.FILTER));
		filterLabel.setBounds(10, 3, 42, 19);
		filterLabel.setText("Find");

		textFind = new Text(composite_2, SWT.BORDER);
		textFind.addModifyListener(new ModifyListener() {
			public void modifyText(final ModifyEvent e) {
				updateFilter();
			}
		});
		textFind.setBounds(59, 0, 223, 19);

		final Label inColumnLabel = new Label(composite_2, SWT.NONE);
		inColumnLabel.setText("In column");
		inColumnLabel.setBounds(300, 3, 61, 13);

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
		comboFind.setBounds(367, 0, 183, 19);
		createActions();
	}
	public void query(String queryString){
		
		clearTable();
		int numVars =0 ;
		comboFind.removeAll();
		comboFind.add("All");
		comboFind.select(0);
		
		//Thuc thi cau query tren service		
	    List<ws.data.ArrayListData> result = Service.dataServiceDelegate.sparqlResultList(null, queryString);
	    
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
	    
		/*
		QueryExecution qe = QueryExecutionFactory.create(query.toString(), Syntax.syntaxARQ, loader.getModel());
		int numVars =0 ;
		comboFind.removeAll();
		comboFind.add("All");
		comboFind.select(0);
		
		try
        {
            ResultSet results = qe.execSelect();
            numVars = results.getResultVars().size();
            for (Object var : results.getResultVars()) {
            	TableColumn column = new TableColumn (table, SWT.NONE);
    			column.setText (var.toString());
    			comboFind.add(var.toString());	
            }
            boolean[] hasData = new boolean[numVars];
    		for (@SuppressWarnings("unused")
    		boolean b : hasData) {
    			b = false;
    		}
            while (results.hasNext())
            {            	
            	String[] item = new String[numVars];
            	QuerySolution soln = results.nextSolution();
            	int index = 0;
            	for (Object var : results.getResultVars()) {
            		RDFNode node = soln.get(var.toString());
            		if(node != null)
            		{
	            		if(node.isResource())
	            		{
	            			String uri = ((Resource)node).getURI();
	            			item[index] = uri.substring(uri.indexOf('#')+1);
	            		}
	            		else if(node.isLiteral())
	            		{
	            			Literal literal = (Literal)node;
	            			item[index] = literal.getLexicalForm();	            			
	            		}
	            		else
	            		{
	            			item[index] = node.toString(); 
	            		}
	            		if(!item[index].equals(""))
	            			hasData[index] = true;
            		}
            		else
            		{
            			item[index] = "";
            		}
            		index++;
				}
            	items.add(item);
            }
            tableViewer.refresh();			
            for (int i = 0; i < numVars; i++) {
    			table.getColumn(i).pack();
    			if(!hasData[i]) // no data
    				hideColumn(i);
    		}	
        }
        finally
        {
            qe.close();
        }*/
			
	}
	private void createActions()
	{		
		tableViewer.setContentProvider(new TableContentProvider());
		tableViewer.setLabelProvider(new TableLabelProvider());
		tableViewer.setInput(items);			
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
	private void clearTable()
	{
		table.removeAll();
		for (TableColumn column : table.getColumns()) {
			column.dispose();
		}
		items.clear();
	}
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
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
}
