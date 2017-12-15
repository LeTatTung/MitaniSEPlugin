package hut.composite.test;

import java.util.ArrayList;
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
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import service.Service;
import swing2swt.layout.BorderLayout;


public class CompositeViewTest extends Composite {

	private ToolItem newItemToolItem ;
	private  CompositeTestComponent compositeViewAllTest;
	private Composite composite;
	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public CompositeViewTest(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout());
		createComponentLayout();
		
	}
	
	public void createComponentLayout(){
		final CTabFolder tabFolder = new CTabFolder(this, SWT.NONE);
		tabFolder.setSimple(false);
		
		final CTabItem viewTestTabItem = new CTabItem(tabFolder, SWT.NONE);
		viewTestTabItem.setImage(Images.imageRegistry.get(Images.TEST));
		viewTestTabItem.setText(" Overview Test");
		
		
		//Chon tab duoc select
		tabFolder.setSelection(0);
		
		composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new BorderLayout(0, 0));
		
		viewTestTabItem.setControl(composite);

		

		final Composite composite_2 = new Composite(composite, SWT.NONE);
		composite_2.setLayout(new FillLayout());
		composite_2.setLayoutData(BorderLayout.CENTER);

		final TabFolder tabFolder_1 = new TabFolder(composite_2, SWT.NONE);

		final TabItem viewAllTabItem = new TabItem(tabFolder_1, SWT.NONE);
		viewAllTabItem.setText("   View All ");

		compositeViewAllTest = new CompositeTestComponent(tabFolder_1, SWT.NONE,1);
		viewAllTabItem.setControl(compositeViewAllTest);
		
		final TabItem testSuccessTabItem = new TabItem(tabFolder_1, SWT.NONE);
		testSuccessTabItem.setText("Test Success");

		final Composite composite_4 = new Composite(tabFolder_1, SWT.NONE);
		testSuccessTabItem.setControl(composite_4);

		final TabItem testProcessingTabItem = new TabItem(tabFolder_1, SWT.NONE);
		testProcessingTabItem.setText("Test Processing");

		final Composite composite_5 = new Composite(tabFolder_1, SWT.NONE);
		testProcessingTabItem.setControl(composite_5);

		final TabItem testRequireTabItem = new TabItem(tabFolder_1, SWT.NONE);
		testRequireTabItem.setText("Test Require");
		
		
		
		//Khoi tao gia tri
		innitData();
	}
	
	
	public void createComponentLayoutManage(){
		final Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.NORTH);
		composite_1.setLayout(new BorderLayout(0, 0));

		final ToolBar toolBar = new ToolBar(composite_1, SWT.NONE);
		toolBar.setLayoutData(BorderLayout.NORTH);

		newItemToolItem = new ToolItem(toolBar, SWT.NONE);
		newItemToolItem.setImage(Images.imageRegistry.get(Images.ADD));
		newItemToolItem.setText("Add New Test");
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
		
		final CompositeViewTreeSourceTest  compositeViewTreeSourceTest = new CompositeViewTreeSourceTest(shell,SWT.NONE);
		//open shell
		shell.open();
	}
	
	public void innitData(){
		List<List> listOfResult = new ArrayList<List>();
		
		
		//Lay tu server ve nhung tat ca test
		String query =ConsistentOntology.prefix+
					  "\n SELECT  ?label   ?content ?methodtest ?testfor ?testedAt  "+		
					  "\n WHERE" +
					  "\n {" +
					  "\n ?X  SEC:hasDirectType 'Test' . " 
					  +"\n ?X  rdfs:label ?label.	"
					  +"\n ?X  SEC:hasContentRequire ?content."
					  +"\n ?X  SEC:testedAt ?testedAt."
					  +"\n ?X  SEC:hasStatusTest '0'."
					  +"\n ?X  SEC:hasMethodTest ?Y."
					  +"\n ?Y rdfs:label ?methodtest."
					  +" \n ?X  SEC:hasTestFor ?Z."
					  +"\n ?Z rdfs:label ?testfor."
					  +"\n}" ;
					  
		
	    List<ws.data.ArrayListData> result = Service.dataServiceDelegate.sparqlResultList(null, query);
	    //List cac ket qua moi lisbao gom : label,content, methodtest,testfor,id cua test .Theo dung thu tu
	    for (int i = 0; i < result.size(); i++){
	    	List<String> subList = new ArrayList<String>();
	    	List<String> items = result.get(i).getData();	    	
	    	String label		= items.get(0).toString();
	    	String content	 	= items.get(1).toString();
	    	String methodTest	= items.get(2).toString();
	    	String testFor		= items.get(3).toString();
	    	String idTest		= items.get(4).toString();
	    	subList.add(label);
	    	subList.add(content);
	    	subList.add(methodTest);
	    	subList.add(testFor);
	    	subList.add(idTest);
	    	listOfResult.add(subList);
	    }
	    
		System.out.println(query);
		
		compositeViewAllTest.bindDataListTable(listOfResult);
		
	}
	

}
