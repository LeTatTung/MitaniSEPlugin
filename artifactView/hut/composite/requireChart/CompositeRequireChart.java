package hut.composite.requireChart;

import hut.composite.requireChart.ColumnChartItemRequire;
import hut.composite.requireChart.RowChartItem;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
public class CompositeRequireChart extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	
	private List<ColumnChartItemRequire> columns = new ArrayList<ColumnChartItemRequire>();
	private String text = "";
	private String uri = "";
	private String tooltip = "";
	private String type = ""; 
	public CompositeRequireChart(Composite parent, int style) {
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
	public void addColumn(ColumnChartItemRequire column)
	{
		this.columns.add(column);
	}
	public void removeColumn(ColumnChartItemRequire column)
	{
		this.columns.remove(column);
	}
	public void removeColumn(int index)
	{
		this.columns.remove(index);
	}
	public ColumnChartItemRequire getColumn(int index)
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
		setSize(500 * numColumns, getSize().y);
		int width = getSize().x / numColumns;
		for(int i= 0 ; i< numColumns; i++)
		{
			int x = i * getSize().x / numColumns;
			int y = 0;
			ColumnChartItemRequire column = columns.get(i);
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
				
				//Tong so
				RowChartItem row = column.getRow(0);
				int sum = row.getSum();
				int height = row.getValue() * getSize().y / (3*totalVaule);
				row.setBounds(x, y+20, width+50 , height);
				
				//Da hoan thanh
				row = column.getRow(1);
				y = y+50+height;		
				float sumFinish=  new Float(row.getSum())/new Float(sum);
			    height = row.getValue() * getSize().y / (3*totalVaule);				
				row.setBounds(x, y, (int) ((width+50)*sumFinish) , height);
				
				
				//Dang trien khai
				row = column.getRow(2);
				y = y+20+height;
				float sumProcess= new Float(row.getSum())/new Float(sum);
			    height = row.getValue() * getSize().y / (3*totalVaule);				
				row.setBounds(x, y, (int) ((width+50)*sumProcess) , height);
				
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

	