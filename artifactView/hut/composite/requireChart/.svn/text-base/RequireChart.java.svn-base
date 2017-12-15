package hut.composite.requireChart;




import hut.composite.requirement.CompositeRequirementComponent;

import java.util.ArrayList;
import java.util.List;

import mintani.valueconst.ConsistentOntology;
import ontology.images.Images;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;

import service.Service;
import swing2swt.layout.BorderLayout;

public class RequireChart extends Composite {
	private CompositeRequireChart chart;
	private Composite compositeChartList;
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public RequireChart(Composite parent, int style) {
		super(parent, style);
		setLayout(new BorderLayout(0, 0));
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);

		final Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new FillLayout());
		composite.setLayoutData(BorderLayout.WEST);
		toolkit.adapt(composite);

		final SashForm sashForm = new SashForm(composite, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);

		final CLabel requLabel = new CLabel(sashForm, SWT.NONE);
		toolkit.adapt(requLabel, true, true);
		requLabel.setText("Require");
		toolkit.adapt(sashForm, true, true);

		final CLabel finishedLabel = new CLabel(sashForm, SWT.NONE);
		toolkit.adapt(finishedLabel, true, true);
		finishedLabel.setText("Finished");

		final CLabel countLabel = new CLabel(sashForm, SWT.NONE);
		toolkit.adapt(countLabel, true, true);
		countLabel.setText("Processing");

		final CLabel label = new CLabel(sashForm, SWT.NONE);
		toolkit.adapt(label, true, true);
		sashForm.setWeights(new int[] {47, 30, 42, 247 });

		compositeChartList = new Composite(this, SWT.NONE);
		toolkit.adapt(compositeChartList);
		chart = new CompositeRequireChart(this, SWT.NONE);
		chart.setLayout(new FillLayout());
		toolkit.adapt(chart);
		chart.setLayoutData(BorderLayout.CENTER);
		//
		showClassMetric("aaa","bbb");
	}
	
	public void showClassMetric(String classID,String className) {
		clearChart();				
		chart.setType(Images.JAVA);
		chart.setText(classID);					
		chart.setUri(ConsistentOntology.SEC_NAMESPACE + className);
		chart.setToolTipText(className);
		
		
		//Tong so requirement
		int numberAll     = performQuery(ConsistentOntology.TYPE_VIEW_ALL, 0);
		//Tong so da finish
		int numberFinish  = performQuery(ConsistentOntology.TYPE_VIEW_COMPONENT,2);
		//Tong so dang thuc hien
		int numberProcess = performQuery(ConsistentOntology.TYPE_VIEW_COMPONENT,1);
		
		
		//Tinh so phan tram hoan thanh
		
		int percentFinish  = (int)((new Float(numberFinish)/new Float(numberAll))*100);
		int percentProcess = (int)((new Float(numberProcess)/new Float(numberAll))*100);
		
		
		RowChartItem itemInters   = new RowChartItem(chart, SWT.NONE,"100%", Images.CLASS, Integer.parseInt("3"),numberAll);
		RowChartItem itemInterss  = new RowChartItem(chart, SWT.NONE,percentFinish+"%", Images.FIELD, Integer.parseInt("3"),numberFinish);
		RowChartItem itemIntersss = new RowChartItem(chart, SWT.NONE,percentProcess+"%", Images.METHOD, Integer.parseInt("3"),numberProcess);
		
		
		ColumnChartItemRequire column = new ColumnChartItemRequire(classID,Images.CLASS);
		column.addRow(itemInters);
		column.addRow(itemInterss);
		column.addRow(itemIntersss);
		
		chart.addColumn(column);
		layoutChart();
	}
	public void layoutChart() {		
		int chartHeight = 160;		
		int chartWidth = 100;
		if(chart.getColumnSize() < 5)
			chartWidth = chart.getColumnSize() * 20;
		Point size = compositeChartList.getSize();		
		int y = (size.y - chartHeight) / 2;		
		if (y < 0)
		 y = 0;
		chart.setBounds(0, 0, chartWidth, chartHeight);		
		chart.paintChart();
	}
	
	public void clearChart() {
		int size = chart.getColumnSize();
		if (size > 0) {
			for (int i = size - 1; i >= 0; i--) {
				ColumnChartItemRequire col = chart.getColumn(i);
				for (int k = col.getSize() - 1; k >= 0; k--) {
					RowChartItem row = col.getRow(k);
					col.removeRow(row);
					row.dispose();
				}
				chart.removeColumn(i);
			}
		}
		chart.dispose();
		chart = new CompositeRequireChart(compositeChartList, SWT.NONE);
		chart.setLayout(new swing2swt.layout.GridLayout());
		toolkit.adapt(chart);
		compositeChartList.redraw();
	}
	
	public int performQuery(String type,int value){
		String query ="",count="0";
		//Kiem tra dau vao cua query
		
		if(type.equals(ConsistentOntology.TYPE_VIEW_ALL)){
			query = ConsistentOntology.prefix
			+ "\n SELECT  (count(?X) AS ?countRequirement)  "
			+ "\n WHERE" + "\n {"
			+ "\n  ?X  SEC:hasDirectType 'Requirement'. "
			+ "\n}";
		}else{
			query = ConsistentOntology.prefix
			+ "\n SELECT  (count(?X) AS ?countRequirement) "
			+ "\n WHERE" + "\n {"
			+ "\n  ?X  SEC:hasDirectType 'Requirement'. "
			+ "\n  ?X  SEC:hasStatusRequire '"+value+"'^^xsd:integer."
			+ "\n}";
		}
		
		System.out.println(query);
			
	    List<ws.data.ArrayListData> result = Service.dataServiceDelegate.sparqlResultList(null, query);
	    for (int i = 0; i < result.size(); i++) {
			List<String> items = result.get(i).getData();
			count = items.get(0).toString();
		
		}
	    
	   return(Integer.parseInt(count));
	}
	

}
