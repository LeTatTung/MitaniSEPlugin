package hut.composite.requirement;

//import hut.composite.annotationEditor.CompositeInstance;
import hut.composite.requireChart.RequireChart;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mintani.valueconst.ConsistentOntology;
import ontology.images.Images;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import service.Service;
import swing2swt.layout.BorderLayout;


public class CompositeViewRequirement extends Composite {

	private ToolItem newItemToolItem ;
	private  CompositeRequirementComponent compositeViewAllTest;
	private CompositeRequirementComponent compositeViewFinish ;
	private CompositeRequirementComponent compositeViewProcessing;
	private RequireChart composite_3;
	private Composite compositeToolBar;
	private Composite composite;
	private ToolItem refreshItemToolItem;
	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public CompositeViewRequirement(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout());
		creatComponentLayout();
		
	}
	
	
	public void creatComponentLayout(){
		final CTabFolder tabFolder = new CTabFolder(this, SWT.NONE);
		tabFolder.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		tabFolder.setSimple(false);
		
		final CTabItem viewTestTabItem = new CTabItem(tabFolder, SWT.NONE);
		viewTestTabItem.setImage(Images.imageRegistry.get(Images.TEST));
		viewTestTabItem.setText("     Overview Requirement");
		
		
		//Chon tab duoc select
		tabFolder.setSelection(0);
		
		composite = new Composite(tabFolder, SWT.NONE);
		composite.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		composite.setLayout(new BorderLayout(0, 0));
		
		viewTestTabItem.setControl(composite);

		
		
		final Composite composite_2 = new Composite(composite, SWT.NONE);
		composite_2.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		composite_2.setLayout(new FillLayout());
		composite_2.setLayoutData(BorderLayout.CENTER);

		final TabFolder tabFolder_1 = new TabFolder(composite_2, SWT.NONE);
		tabFolder_1.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));

		final TabItem viewAllTabItem = new TabItem(tabFolder_1, SWT.NONE);
		viewAllTabItem.setText("   View All ");

		compositeViewAllTest = new CompositeRequirementComponent(tabFolder_1, SWT.NONE,1);
		viewAllTabItem.setControl(compositeViewAllTest);
		
		final TabItem testSuccessTabItem = new TabItem(tabFolder_1, SWT.NONE);
		testSuccessTabItem.setText("Requirement Finish");

		compositeViewFinish = new CompositeRequirementComponent(tabFolder_1, SWT.NONE,1);
		compositeViewFinish.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		testSuccessTabItem.setControl(compositeViewFinish);

		final TabItem testProcessingTabItem = new TabItem(tabFolder_1, SWT.NONE);
		testProcessingTabItem.setText("Requirement Processing");

		compositeViewProcessing = new CompositeRequirementComponent(tabFolder_1, SWT.NONE,1);
		testProcessingTabItem.setControl(compositeViewProcessing);

		final TabItem testRequireTabItem = new TabItem(tabFolder_1, SWT.NONE);
		testRequireTabItem.setText("Require Dashboard");

		composite_3 = new RequireChart(tabFolder_1, SWT.NONE);
		testRequireTabItem.setControl(composite_3);
		
		
		
		//Khoi tao gia tri
		innitData();
	}
	
	public void creatComponentLayoutManage(){
		compositeToolBar = new Composite(composite, SWT.NONE);
		compositeToolBar.setLayoutData(BorderLayout.NORTH);
		compositeToolBar.setLayout(new BorderLayout(0, 0));

		final ToolBar toolBar = new ToolBar(compositeToolBar, SWT.NONE);
		toolBar.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		toolBar.setLayoutData(BorderLayout.NORTH);
		newItemToolItem = new ToolItem(toolBar, SWT.NONE);
		newItemToolItem.setImage(Images.imageRegistry.get(Images.ADD));
		newItemToolItem.setText("Add New Requirement");

		 refreshItemToolItem = new ToolItem(toolBar, SWT.PUSH);
		refreshItemToolItem.setImage(Images.imageRegistry.get(Images.REFRESH));
		refreshItemToolItem.setText("Refresh");
		//Ham khai bao cac action cho form
		registerAction();
	}
	
	public void registerAction(){
		newItemToolItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) 
			{
				openCompositeTreeSoureceTest();
			}
		});
		
		refreshItemToolItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				innitData();
			}
		});
	}
	
	public void openCompositeTreeSoureceTest(){
		Shell shell = new Shell(newItemToolItem.getDisplay());
		shell.setText("Source Tree");
		shell.setImage(Images.imageRegistry.get(Images.LIBRARY));
		shell.setLayout(new FillLayout());
		shell.setSize(820,500);
		
		int x=Math.max(shell.getDisplay().getCursorLocation().x-shell.getSize().x, 0);
		int y=Math.max(shell.getDisplay().getCursorLocation().y-shell.getSize().y, 0);
		shell.setLocation(x,y);
		
		//B1: Tao 1 instance tren model tai server
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//'T'hh:mm:ss"); // yyyy/MM/dd HH:mm:ss
        Date date = new Date();    
		creatAInstance(ConsistentOntology.SEC_NAMESPACE+"requirement_"+date.toString(),ConsistentOntology.SEC_NAMESPACE+"Requirement");
		//B2: Mo shell la mot Instance Composite ung voi dau vao la id
		
		
		//final CompositeInstance  compositeInstance = new CompositeInstance(shell,SWT.NONE);
		//compositeInstance.setInstanceFullName2(ConsistentOntology.SEC_NAMESPACE+"requirement_"+date.toString(),ConsistentOntology.REQUIREMENT,"Requirement_"+date.toString());
		//Open new shells
		
		shell.open();
	}
	
	public void innitData() {
		//Xem tat ca requirement
		
		performQuery(ConsistentOntology.TYPE_VIEW_ALL,0,compositeViewAllTest);
		
		// Truong hop da thuc hien requirement
		
		
		performQuery(ConsistentOntology.TYPE_VIEW_COMPONENT,2,compositeViewFinish);
		//Truong hop con dang thuc thi
		
		
		performQuery(ConsistentOntology.TYPE_VIEW_COMPONENT,1,compositeViewProcessing);
	}
	
	
	public void performQuery(String type,int value,CompositeRequirementComponent compositeBind){
		List<List> listRequirement = new ArrayList<List>();
		String query ="";
		//Kiem tra dau vao cua query
		
		if(type.equals(ConsistentOntology.TYPE_VIEW_ALL)){
			query = ConsistentOntology.prefix
			+ "\n SELECT  ?label   ?content ?startdate ?enddate  "
			+ "\n WHERE" + "\n {"
			+ "\n  ?X  SEC:hasDirectType 'Requirement'. "
			+ "\n optional{?X  rdfs:label ?label.}"
			+ "\n  optional{?X  SEC:hasContentRequire ?content.}"
			+ "\n  optional{?X  SEC:hasStartDate ?startdate.}"
			+ "\n  optional{?X  SEC:hasEndDate ?enddate.}" + "\n}";
		}else{
			query = ConsistentOntology.prefix
			+ "\n SELECT  ?label   ?content ?startdate ?enddate  "
			+ "\n WHERE" + "\n {"
			+ "\n  ?X  SEC:hasDirectType 'Requirement'. "
			+ "\n  ?X  SEC:hasStatusRequire '"+value+"'^^xsd:integer."
			+ "\n optional{?X  rdfs:label ?label.}"
			+ "\n  optional{?X  SEC:hasContentRequire ?content.}"
			+ "\n  optional{?X  SEC:hasStartDate ?startdate.}"
			+ "\n  optional{?X  SEC:hasEndDate ?enddate.}" + "\n}";
		}
		
		System.out.println(query);

	    List<ws.data.ArrayListData> result = Service.dataServiceDelegate.sparqlResultList(null, query);
	    for (int i = 1; i < result.size(); i++) {
			List<String> subList = new ArrayList<String>();
			List<String> items = result.get(i).getData();
			String label = items.get(0).toString();
			String content = items.get(1).toString();
			String startdate = items.get(2).toString();
			String endate = items.get(3).toString();
			// String idTest = items.get(4).toString();
			subList.add(label);
			subList.add(content);
			subList.add(startdate);
			subList.add(endate);
			// subList.add(idTest);
			listRequirement.add(subList);
		}
	    
	    compositeBind.bindDataListTable(listRequirement);
	}
	
	public void creatAInstance(String id, String nameClass){		
		
		//Hien sua ten ham cho trung voi tren service
		Service.webServiceDelegate.createInstance(null, id, nameClass); 
		
		/*DataServiceService service_data = new DataServiceService();
		DataServiceDelegate delegate_data = service_data.getDataServicePort();
		delegate_data.creatArtifactInstance(id, nameClass);*/
	}
	
	
	

}
