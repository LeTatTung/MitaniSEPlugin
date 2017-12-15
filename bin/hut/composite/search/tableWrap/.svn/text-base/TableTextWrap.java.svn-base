package hut.composite.search.tableWrap;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import ontology.exceptions.ArraySizeException;

public class TableTextWrap extends JTable {
	private static final long serialVersionUID = 1L;
	List<String[]> datas = new ArrayList<String[]>();
	List<String> headers = new ArrayList<String>();
	TableTextWrapModel model;
	TextAreaRenderer textAreaRenderer;

	public TableTextWrap()
	{
		super();		
		setAutoCreateColumnsFromModel(true);
		model = new TableTextWrapModel();
		super.setModel(model);	
	}
	public void setColumns(String[] colNames)
	{
		for (String header : colNames) {
			addColumn(header);
		}		
		
		textAreaRenderer = new TextAreaRenderer();
		for(int index = 0; index < headers.size(); index++)
		{
			getColumnModel().getColumn(index).setCellRenderer(textAreaRenderer);
			getColumnModel().getColumn(index).setResizable(true);
		}
	}
	public void addRow(String[] row) throws ArraySizeException
	{
		if(row.length != headers.size())
			throw new ArraySizeException();
		datas.add(row);
	}
	public void addColumn(String name)
	{
		headers.add(name);
		model.addColumn(name);
	}
	public void clearData()
	{
		datas.clear();
	}
	public void removeAll()
	{
		datas.clear();
		for(int index = headers.size() - 1; index >=0; index--)
		{
			TableColumn col = getColumnModel().getColumn(index);
			getColumnModel().removeColumn(col);
		}		
		headers.clear();
	}
	public String[] getRow(int index)
	{
		return datas.get(index);
	}
	public void removeRow(int index)
	{
		datas.remove(index);
	}
	public List<String> getHeaders()
	{
		return this.headers;
	}
	class TableTextWrapModel extends DefaultTableModel
	{
		private static final long serialVersionUID = 1L;
		@Override
		public int getColumnCount() {
			return headers.size();
		}
		@Override
		public int getRowCount() {
			return datas.size();
		}		
		@Override
		public Object getValueAt(int row, int col) {
			return datas.get(row)[col];
		}
		@Override
		public String getColumnName(int column) {
			return headers.get(column);
		}
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}		
	}	
	
}
