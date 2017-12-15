package hut.composite.metricChart;

import hut.composite.metricChart.ColumnChartItem;
import hut.composite.metricChart.RowChartItem;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
public class CompositeMetricChart extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	
	private List<ColumnChartItem> columns = new ArrayList<ColumnChartItem>();
	private String text = "";
	private String uri = "";
	private String tooltip = "";
	private String type = ""; 
	public CompositeMetricChart(Composite parent, int style) {
		super(parent, style);		
		toolkit.paintBordersFor(this);
		this.addControlListener(new ControlListener(){
			public void controlMoved(ControlEvent e) {}
			public void controlResized(ControlEvent e) {
				paintChart();
			}});
	}
	public int getColumnSize()
	{
		return columns.size();
	}
	public void addColumn(ColumnChartItem column)
	{
		this.columns.add(column);
	}
	public void removeColumn(ColumnChartItem column)
	{
		this.columns.remove(column);
	}
	public void removeColumn(int index)
	{
		this.columns.remove(index);
	}
	public ColumnChartItem getColumn(int index)
	{
		return this.columns.get(index);
	}
		
	public void paintChart()
	{		
		int numColumns = this.columns.size();
		if(numColumns == 0)
			return;
		if(numColumns > 10)
			setSize(12 * numColumns, getSize().y);
		int width = getSize().x / numColumns;
		for(int i= 0 ; i< numColumns; i++)
		{
			int x = i * getSize().x / numColumns;
			int y = 0;
			ColumnChartItem column = columns.get(i);
			// voi moi row
			int totalVaule = column.getTotalValue();
			if(totalVaule == 0)
			{
				// cho luon row dau tien full toan bo cot
				RowChartItem row0 = column.getRow(0);
				row0.setBounds(x, 0, width, getSize().y);
				row0.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_MAGENTA));
			}
			else
			{
				for(int k=0; k < column.getSize(); k++)
				{
					RowChartItem row = column.getRow(k);
					int height = row.getValue() * getSize().y / totalVaule;
					row.setBounds(x, y, width , height);
					//System.out.println(x + " " + y + " " + width + " " + height);
					y += height;
				}
			}
		}
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getTooltip() {
		return tooltip;
	}
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
