package hut.composite.metricChart;
import hut.composite.packageExplorer.JobBuildSourceCode;
import hut.composite.metricChart.ColumnChartItem;
import hut.composite.metricChart.CompositeMetricChart;
import hut.composite.metricChart.MetricChart;
import hut.composite.metricChart.RowChartItem;
import hut.composite.tree.TreeContentProvider;
import hut.composite.tree.TreeLabelProvider;
import hut.composite.tree.TreeNodeData;
import hut.composite.tree.TreeObject;
import hut.eclipse.EclipseUtils;
import java.util.List;
import mintani.valueconst.ConsistentOntology;
import ontology.images.Images;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Widget;

import org.eclipse.ui.forms.widgets.FormToolkit;

import service.Service;
import swing2swt.layout.BorderLayout;


public class MetricChart extends Composite {

	private Tree tree;
	private TableViewer tableViewer;
	private Table table;
	private Composite compositeChartList;
	private CompositeMetricChart chart;
	private ScrolledComposite compositeScroll;
	private FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	MouseAdapter mouseListener = null;	
	private TreeViewer treeViewer ;
	private ToolItem refreshItemToolItem;
	 final String[] nameColumn = {"Name", "ID" };
	public MetricChart(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout());
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);

		final SashForm sashForm = new SashForm(this, SWT.NONE);

		final Composite composteClassBrowser = new Composite(sashForm, SWT.NONE);
		composteClassBrowser.setLayout(new BorderLayout(0, 0));
		toolkit.adapt(composteClassBrowser);

		final SashForm sashForm_2 = new SashForm(composteClassBrowser, SWT.NONE);
		final CLabel label_1 = new CLabel(sashForm_2, SWT.NONE);
		label_1.setImage(Images.imageRegistry.get(Images.PACKAGE));
		toolkit.adapt(label_1, true, true);
		label_1.setText("Class Browser");
		
		label_1.setLayoutData(BorderLayout.NORTH);
		toolkit.adapt(sashForm_2, true, true);
		sashForm_2.setLayoutData(BorderLayout.NORTH);

		final ToolBar toolBar = new ToolBar(sashForm_2, SWT.NONE);
		toolkit.adapt(toolBar, true, true);

		refreshItemToolItem = new ToolItem(toolBar, SWT.PUSH);
		
		refreshItemToolItem.setImage(Images.imageRegistry.get(Images.REFRESH));
		sashForm_2.setWeights(new int[] {126, 31 });

		final Composite compositeTreeSource = new Composite(composteClassBrowser,SWT.BORDER | SWT.NONE);
		compositeTreeSource.setLayout(new FillLayout());
		toolkit.adapt(compositeTreeSource);
		compositeTreeSource.setLayoutData(BorderLayout.CENTER);

		treeViewer = new TreeViewer(compositeTreeSource, SWT.NONE);
		treeViewer.setContentProvider(new TreeContentProvider());
		treeViewer.setLabelProvider(new TreeLabelProvider());
		tree = treeViewer.getTree();
		toolkit.adapt(tree, true, true);

		toolkit.adapt(sashForm, true, true);

		final SashForm sashForm_1 = new SashForm(sashForm, SWT.VERTICAL);
		toolkit.adapt(sashForm_1, true, true);

		final Composite composite_1 = new Composite(sashForm_1, SWT.NONE);
		composite_1.setLayout(new BorderLayout(0, 0));
		toolkit.adapt(composite_1);

		final Composite composite = new Composite(sashForm_1, SWT.NONE);
		composite.setLayout(new BorderLayout(0, 0));
		toolkit.adapt(composite);

		final CLabel label = new CLabel(composite, SWT.BORDER | SWT.NONE);
		label.setLayoutData(BorderLayout.NORTH);
		toolkit.adapt(label, true, true);
		label.setText("Detail of metric");
		label.setImage(Images.imageRegistry.get(Images.DESCRIBE));
		tableViewer = new TableViewer(composite, SWT.BORDER | SWT.NONE);
		table = tableViewer.getTable();
		toolkit.adapt(table, true, true);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setLayoutData(BorderLayout.CENTER);		
		TableColumn column = new TableColumn(table, SWT.CENTER);
		column.pack();
		
		final CLabel metricLabel = new CLabel(composite_1, SWT.SHADOW_IN
				| SWT.BORDER);
		metricLabel.setImage(Images.imageRegistry.get(Images.METRIC));
		toolkit.adapt(metricLabel, true, true);		
		metricLabel.setText("Metric");
		metricLabel.setImage(Images.imageRegistry.get(Images.CHART));
		metricLabel.setLayoutData(BorderLayout.NORTH);

		compositeScroll = new ScrolledComposite(composite_1, SWT.BORDER
				| SWT.V_SCROLL | SWT.H_SCROLL);
		compositeScroll.setAlwaysShowScrollBars(true);
		compositeScroll.setLayout(new FillLayout());
		toolkit.adapt(compositeScroll);
		
		compositeChartList = new Composite(compositeScroll, SWT.NONE);
		toolkit.adapt(compositeChartList);
		// compositeChartList.setLayout(new FillLayout());
		compositeScroll.addControlListener(new ControlListener() {
			public void controlMoved(ControlEvent e) {
			}

			public void controlResized(ControlEvent e) {
				compositeChartList.setSize(compositeScroll.getSize().x,
						compositeScroll.getSize().y);
				layoutChart();
			}
		});
		compositeScroll.setContent(compositeChartList);

		chart = new CompositeMetricChart(compositeScroll, SWT.NONE);
		sashForm.setWeights(new int[] {161, 336 });

		sashForm_1.setWeights(new int[] { 271, 101 });
		//
		creatTree();
		createActions();
	}
	
	
	
	public void creatTree() {		
		
		JobBuildSourceCode jobBuildSourceCode = new JobBuildSourceCode("Build sourcetree",treeViewer);		
		//jobBuildSourceCode.setUser(true);
		jobBuildSourceCode.schedule();	
		
	}
	private void createActions() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				MetricChart.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(treeViewer.getControl());
		treeViewer.getControl().setMenu(menu);
		
		
		//Su kien kich chuot de xem chi tiet cac thanh phan ben trong cua mot metric
		
		mouseListener= new MouseAdapter()
		{			
			public void mouseDown(MouseEvent e) {
				Widget widget = e.widget;
				if(widget instanceof RowChartItem)
				{
					RowChartItem item = (RowChartItem)widget;
					String type = item.getType();
					String parentUri = item.getParentURI();
					String query = ConsistentOntology.prefix;
					System.out.println(parentUri);
					
					String parentId  =parentUri;
					
					if(type.equals(Images.FIELD)){
						
					}
					if(type.equals(Images.METHOD)){
						query +="SELECT ?method ?label " +
						"WHERE" +
						"{" +
							"<" + parentId + "> SEC:hasMethod ?method . " 
							+" ?method rdfs:label ?label."
						+"}" 
						+"ORDER BY ?method";	
					}
					MetricChart.this.showRowItem(query);
					
					
					
					
				}
			}
		}	;
				
		//cho tabelview
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseDoubleClick(MouseEvent event) {
				Point pt = new Point(event.x, event.y);
				TableItem item = table.getItem(pt);
				String methodID = item.getText(1);
				EclipseUtils eclipseUtils = new EclipseUtils();
				eclipseUtils.openMethod(methodID);
			}
		});
		//refresh lai cay
		refreshItemToolItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				creatTree();
			}
		});
	}
	private void clearTable()
	{
		table.removeAll();
		for (TableColumn column : table.getColumns()) {
			column.dispose();
		}
		
		
	}
	private void showRowItem( String queryString){
		clearTable();
		
		for (int i=0; i< 2 ; i++) {
	    	TableColumn column = new TableColumn(table, SWT.CENTER);
	    	if(i==0){
	    		column.setText("Name ");
	    		column.setWidth(300);
	    	}else
	    	{
	    		column.setText("ID");
	    		column.setWidth(200);
	    	}
	    	
			
		}
	    for (int loopIndex = 0; loopIndex < 2; loopIndex++) {
	        table.getColumn(loopIndex).pack();
	        table.getColumn(loopIndex).setWidth(200);
	      }
		
		//Thuc thi cau query tren service	
	    List<ws.data.ArrayListData> result = Service.dataServiceDelegate.sparqlResultList(null, queryString);
	    
	    
	    
	    boolean noneData[] = new boolean[result.size()];	    
	    for (int i = 0; i < result.size(); i++){
	    	List<String> items = result.get(i).getData();	    	
	    	String resultId = items.get(0).toString();
	    	String resultLabel= items.get(1).toString();
	    	
	    	TableItem item = new TableItem(table, SWT.NULL);
	    	item.setText(0,resultLabel);
	    	
	    	item.setText(1,resultId);
	    	item.setText(2,resultId);
	    }
	   
	    
	    
	    
	    
	    
	 
	    
	    //Heading
	    
	    // To test
	    
		
	}
	private void fillContextMenu(IMenuManager manager) {
		Action actionAddToChart = new Action() {
			
			public void run() {
				IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
				TreeObject obj = (TreeObject) selection.getFirstElement();
				TreeNodeData data = (TreeNodeData) obj.getData();
				String nodeType = data.getTypeSource().toLowerCase();
				System.out.println(nodeType);
				if (nodeType.equals(Images.PACKAGE)) {
					String packageId = data.getId();
					String packageName = data.getName();
					showPackageMetric(packageId,packageName);
					System.out.println("show package metric");
				} else if (nodeType.equals(Images.CLASS)) {
					System.out.println("show class metric");
					/*TreeParent packNode = obj.getParent();
					PackageTreeNodeData pack = (PackageTreeNodeData) packNode.getData();
					String className = data.getName().substring(0, data.getName().lastIndexOf(".java"));*/
					
					//Lay id cua class
					String classId = data.getId();
					String className = data.getName();
					System.out.println(classId);
					showClassMetric(classId,className);	
				}
			}
		};
		manager.add(actionAddToChart);
		actionAddToChart.setText("Show the metric");
		actionAddToChart.setToolTipText("Show the metric");		
		//manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	/*
	 * Hien thi do do cho class
	 */
	public void showClassMetric(String classID,String className) {
		clearChart();				
		chart.setType(Images.JAVA);
		chart.setText(classID);					
		chart.setUri(ConsistentOntology.SEC_NAMESPACE + className);
		chart.setToolTipText(className);
		
		String classURI = classID;
		System.out.println("classURI: " + classURI);
		
		
		//Tao cau truy van
		
		String queryString = ConsistentOntology.prefix
		+ "\n SELECT ?numInterfaces ?numAttributes ?numMethods "
		+ "\n WHERE " 
		+ "\n {" 				
		+ "\n <"+ classURI +"> SEC:hasMetric ?metric. "
		+ "\n ?metric SEC:numInterfaces ?numInterfaces. "
		+ "\n ?metric SEC:numAttributes ?numAttributes. "
		+ "\n ?metric SEC:numMethods    ?numMethods. "
		+ "\n } " ;
		
	    List<ws.data.ArrayListData> result = Service.dataServiceDelegate.sparqlResultList(null, queryString);
	    
	    //Phan tu 1: numInterface, phan tu 2 :numAttribute, Phan tu 3: numMethods
	    for (int i = 1; i < result.size(); i++){ //bat dau tu phan tu 1 vi phan tu khong chi la label
	    	
	    	List<String> items = result.get(i).getData();
	    	
	    	String numInterface = items.get(0).toString();
	    	String numField 	= items.get(1).toString();
	    	String numMethod	= items.get(2).toString();
	    	
	    	
	    	RowChartItem itemInters = new RowChartItem(chart, SWT.NONE,
	    			numInterface, Images.INTERFACE, Integer.parseInt(numInterface), className,classID);
			itemInters.addMouseListener(mouseListener);
			
			RowChartItem itemField = new RowChartItem(chart, SWT.NONE, numField, Images.FIELD, Integer.parseInt(numField), className,classID);
			itemField.addMouseListener(mouseListener); 
			
			RowChartItem itemMethods = new RowChartItem(chart, SWT.NONE,numMethod, Images.METHOD, Integer.parseInt(numMethod),className,classID);
			itemMethods.addMouseListener(mouseListener);
	    	
			
			ColumnChartItem column = new ColumnChartItem(classID,Images.CLASS);
			column.addRow(itemInters);
			column.addRow(itemField);
			column.addRow(itemMethods);
			chart.addColumn(column);
	    }
		
		System.out.println(queryString);
		
		layoutChart();
	}
	
	
	/*
	 * Hien thi do do cho package
	 */
	public void showPackageMetric(String packageId,String packageName) {
		String packURI = packageId;
		clearChart();
		chart.setType(Images.PACKAGE);
		chart.setText(packageName);					
		chart.setUri(packURI);
		chart.setToolTipText(packageName);
		String queryString = ConsistentOntology.prefix
		+ "\n SELECT (count(?class) AS ?cls) (sum(?numMethods) AS ?methods) (count(?interface) AS ?inters) "
		+ "\n WHERE " 
		+ "\n {" 	
			  +"\n {"
			    + "\n <"+ packURI +"> SEC:hasClass ?class. "
				+ "\n ?class SEC:hasMetric ?metric. "
				+ "\n ?metric SEC:numMethods    ?numMethods. "
			  +"\n }"
			  + "UNION" 
				+ "\n {"
				+ " <"+ packURI +"> SEC:hasInterface ?interface ."
				+ "\n }"
		+ "\n } ";
		System.out.println(queryString);	
	    List<ws.data.ArrayListData> result = Service.dataServiceDelegate.sparqlResultList(null, queryString);
	    for (int i = 1; i < result.size(); i++){ //Bat dau tu 1 vi phan tu 0 chi la label
	    	
	    	List<String> items = result.get(i).getData();
	    	
	    	String numClass     = items.get(0).toString();
	    	String numMethod 	= items.get(1).toString();
	    	String numInter	    = items.get(2).toString();
	    	
	    	
	    	RowChartItem itemClasses = new RowChartItem(chart, SWT.NONE,
	    			numClass, Images.CLASS_OF_PACKAGE, Integer.parseInt(numClass), packageName,packageId);
			//itemInters.addMouseListener(mouseListener);
			
			RowChartItem itemMethod = new RowChartItem(chart, SWT.NONE, numMethod, Images.METHOD_OF_PACKAGE, Integer.parseInt(numMethod), packageName,packageId);
			/*itemAtrs.addMouseListener(mouseListener); */
			
			RowChartItem itemInters = new RowChartItem(chart, SWT.NONE,numInter, Images.INTERFACE, Integer.parseInt(numInter),packageName,packageId);
			/*itemMethods.addMouseListener(mouseListener);*/
	    	ColumnChartItem column = new ColumnChartItem(packURI,Images.PACKAGE);
			column.addRow(itemClasses);
			column.addRow(itemMethod);
			column.addRow(itemInters);
			chart.addColumn(column);
	    }
		
		System.out.println(queryString);
		
		
	
		/*WebServiceService service = new WebServiceService();
		WebServiceDelegate delegate = service.getWebServicePort();		
	    List<ArrayListData> result = delegate.sparqlResultList(queryString);*/
		
		queryString = ConsistentOntology.prefix
		+ "\n SELECT ?class (sum(?inters) as ?inter) (sum(?methods) as ?method) (sum(?attrs) as ?attr) (count(?class1) as ?usesClass)  "
		+ "\n WHERE " + 
		   "\n{" + 
			"\n <"+ packURI +">  SEC:hasClass ?class . "
			+ "\n ?class SEC:hasMetric ?metric . "
			+ "\n ?metric SEC:numInterfaces ?inters . "
			+ "\n ?metric SEC:numAttributes ?attrs . "
			+ "\n ?metric SEC:numMethods    ?methods . "
			+ "\n optional{ ?class1 SEC:usesClass ?class }"
		    +"\n} " 
		+"\n group by ?class ";	
		List<ws.data.ArrayListData> result2 = Service.dataServiceDelegate.sparqlResultList(null, queryString);
		
		System.out.println(queryString);
		
		   for (int i = 1; i < result2.size(); i++){
		    	
		    	List<String> items = result2.get(i).getData();
		    	String nameClass    = items.get(0).toString();
		    	String numInter     = items.get(1).toString();
		    	String numMethod 	= items.get(2).toString();
		    	String numField	    = items.get(3).toString();
		    	String numUseClass  = items.get(4).toString();
		    	
		    	RowChartItem itemInters = new RowChartItem(chart, SWT.NONE,
		    			numInter, Images.INTERFACE_IMPLEMENTED, Integer.parseInt(numInter), nameClass,nameClass);
				//itemInters.addMouseListener(mouseListener);
				
				RowChartItem itemMethods = new RowChartItem(chart, SWT.NONE, numMethod, Images.METHOD, Integer.parseInt(numMethod), nameClass,nameClass);
				/*itemAtrs.addMouseListener(mouseListener); */
				
				RowChartItem itemAttrs = new RowChartItem(chart, SWT.NONE,numField, Images.FIELD, Integer.parseInt(numField),nameClass,nameClass);
				/*itemMethods.addMouseListener(mouseListener);*/
				RowChartItem itemUseClass = new RowChartItem(chart, SWT.NONE,numUseClass, Images.USESCLASS, Integer.parseInt(numUseClass),nameClass,nameClass);
				
				ColumnChartItem column = new ColumnChartItem(nameClass,Images.JAVA);
				column.addRow(itemInters);
				column.addRow(itemMethods);
				column.addRow(itemAttrs);
				column.addRow(itemUseClass);
				chart.addColumn(column);
		    }
		layoutChart();
	}
	
	public void layoutChart() {		
		int chartHeight = 280;		
		int chartWidth = 100;
		if(chart.getColumnSize() < 5)
			chartWidth = chart.getColumnSize() * 20;
		Point size = compositeChartList.getSize();		
		int y = (size.y - chartHeight) / 2;		
		if (y < 0)
			y = 0;
		chart.setBounds(10, y, chartWidth, chartHeight);		
		chart.paintChart();
	}
	public void clearChart() {
		int size = chart.getColumnSize();
		if (size > 0) {
			for (int i = size - 1; i >= 0; i--) {
				ColumnChartItem col = chart.getColumn(i);
				for (int k = col.getSize() - 1; k >= 0; k--) {
					RowChartItem row = col.getRow(k);
					col.removeRow(row);
					row.dispose();
				}
				chart.removeColumn(i);
			}
		}
		chart.dispose();
		chart = new CompositeMetricChart(compositeChartList, SWT.NONE);
		chart.setLayout(new swing2swt.layout.GridLayout());
		toolkit.adapt(chart);
		compositeChartList.redraw();
	}
	
}
