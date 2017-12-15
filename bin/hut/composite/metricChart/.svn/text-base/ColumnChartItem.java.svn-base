package hut.composite.metricChart;
import hut.composite.metricChart.RowChartItem;

import java.util.ArrayList;
import java.util.List;
public class ColumnChartItem {
	private List<RowChartItem> rows = new ArrayList<RowChartItem>();
	private String text = "";
	// kieu ma column mo ta, duoc dinh nghia trong ontology.Images
	private String type = "";
	public ColumnChartItem(String text, String type)
	{
		this.text = text;
		this.type = type;
	}
	public RowChartItem getRow(int index)
	{
		return this.rows.get(index);
	}
	public int getTotalValue()
	{
		int value = 0;
		for(int index = 0; index < rows.size() ; index++)
		{
			RowChartItem item = rows.get(index);
			value += item.getValue();
		}
		return value;
	}
	public int getSize()
	{
		return this.rows.size();
	}
	public void addRow(RowChartItem item)
	{
		rows.add(item);
	}
	public void removeRow(RowChartItem item)
	{
		rows.remove(item);
	}
	public void removeRow(int index)
	{
		rows.remove(index);
	}
	public List<RowChartItem> getRows() {
		return rows;
	}
	public void setRows(List<RowChartItem> rows) {
		this.rows = rows;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
