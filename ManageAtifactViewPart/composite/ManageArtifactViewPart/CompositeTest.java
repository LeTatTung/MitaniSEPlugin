package composite.ManageArtifactViewPart;

import hut.composite.annotationCreatorAndEditor.CompositeListPropertiesOfInstance;
import hut.composite.jtable.TextAreaRenderer;
import hut.model.annotationCreatorAndEditor.DataInstance;

import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import mintani.valueconst.ConsistentOntology;
import model.ManageArtifactViewPart.PersonData;

import ontology.images.Images;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;

import service.Service;
import tool.parse.doc.ToolParseDoc;
import ws.owl.ClassData;
import ws.owl.IndividualData;

import com.swtdesigner.SWTResourceManager;

/**
 * @author Administrator
 *
 */
public class CompositeTest extends SuperComposite {

	
	private Combo comboFilterByDev;
	private DefaultTableModel modelTable;
	private JTable table;
	private Frame frame;
	private ToolBar toolBar;
	private int type;
	private String[] columnNames = { "Delete","Name", "Content", "Tester execute", "Success", "Tested At" };
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private List<List> listFullData;//List nay chua raw text. table chi hien ra plain text cho phan content
	List<List> listOfList = new ArrayList<List>();
	private int typeTestToShow;
	Composite composite_2;
	
	//-- Ham tao giao dien tu dong ----------
	public CompositeTest(Composite parent, int style, int type, int typeTestToShow) {

		super(parent, style);
		this.type=type;
		this.typeTestToShow = typeTestToShow;
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		setLayout(new FillLayout());

		final SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		
		final Composite composite_1 = new Composite(sashForm, SWT.NONE);
		toolkit.adapt(composite_1);

		toolBar = new ToolBar(composite_1, SWT.NONE);
		toolBar.setBounds(0, 0, 500, 42);
		toolkit.adapt(toolBar, true, true);

		final ToolItem toolItemRefresh = new ToolItem(toolBar, SWT.PUSH);
		toolItemRefresh.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				innitData();
				comboFilterByDev.removeAll();
				addDeveloperNameToFilter();
			}
		});
		toolItemRefresh.setImage(SWTResourceManager.getImage(CompositeTest.class, "/ontology/images/refresh.gif"));
		toolItemRefresh.setText("refresh");

		final ToolItem toolItemNewTest = new ToolItem(toolBar, SWT.PUSH);
		toolItemNewTest.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				
		 				try {
		 					Shell shell = new Shell(PlatformUI.getWorkbench().getDisplay());
							shell.setText("Edit test !");
							shell.setImage(Images.imageRegistry.get(Images.BOOKMARK));
							shell.setLayout(new FillLayout());
							shell.setSize(510, 680);
							
							
							DataInstance dataInstance = new DataInstance();//(DataInstance)itemSelected.getData("Data");
							dataInstance.setNewInstance(true);
							ClassData classData = Service.webServiceDelegate.getClassByName(null, ConsistentOntology.TEST);
							dataInstance.setClassData(classData);
							
							DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy.HH_mm_ss");
					        Date date = new Date();
					        String ID=dateFormat.format(date);
							
					        String instanceFullName = classData.getClassURI()+ "-" + ID;
							dataInstance.setInstanceFullName(instanceFullName);
							
							System.out.println("Test instanceData");
							CompositeListPropertiesOfInstance compositeListProperties = new CompositeListPropertiesOfInstance(shell, SWT.NONE);
							System.out.println("Test instanceData 2");
							compositeListProperties.setInputData(dataInstance);
							System.out.println("Test instanceData 3");
							compositeListProperties.updateInterface();
							
							shell.setVisible(true);
							
							shell.addListener(SWT.Close, new Listener() {
								public void handleEvent(Event e) {
									innitData();
								}
							});
							
		 				} catch (Exception ex) {
		 					ex.printStackTrace();
		 				}
		 			}
		 		
		});
		
		toolItemNewTest.setText("New Test");
		toolItemNewTest.setImage(Images.imageRegistry.get(Images.ADD));

		final ToolItem toolItemDelete = new ToolItem(toolBar, SWT.PUSH);
		toolItemDelete.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				DeleteTest();
			}

			
		});
		toolItemDelete.setText("Delete Test");
		toolItemDelete.setImage(Images.imageRegistry.get(Images.DELETE));

		final Composite composite = new Composite(sashForm, SWT.EMBEDDED);
		composite.setLayout(new FillLayout());
		toolkit.adapt(composite);
		toolkit.adapt(sashForm, true, true);
		
		composite_2 = new Composite(composite, SWT.EMBEDDED);
		final FillLayout fillLayout_1 = new FillLayout();
		fillLayout_1.marginHeight = 3;
		composite_2.setLayout(fillLayout_1);
		//Call function bin data
		frame = SWT_AWT.new_Frame(composite_2);

		final Composite composite_3 = new Composite(sashForm, SWT.NONE);
		toolkit.adapt(composite_3);

		final Label filterByTesterLabel = new Label(composite_3, SWT.NONE);
		toolkit.adapt(filterByTesterLabel, true, true);
		filterByTesterLabel.setText("Filter by Tester");
		filterByTesterLabel.setBounds(10, 10, 80, 20);

		comboFilterByDev = new Combo(composite_3, SWT.NONE);
		comboFilterByDev.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				int index  = comboFilterByDev.getSelectionIndex();
				List<PersonData> comboData = (List<PersonData>)comboFilterByDev.getData();
				String fullTesterURI = comboData.get(index).getFullURI();
				List<List> tempListOfList = new ArrayList<List>();
				for(List list: listOfList){
					if(list.get(6).toString().contains(fullTesterURI + "\n")){
						tempListOfList.add(list);
					}
				}
				bindDataListTable(tempListOfList);
			}
		});
		toolkit.adapt(comboFilterByDev, true, true);
		comboFilterByDev.setBounds(96, 7, 111, 21);
		addDeveloperNameToFilter();
		sashForm.setWeights(new int[] {42, 278, 49 });

		innitData();
	}
	private void addDeveloperNameToFilter() {
		List<PersonData> comboData = new ArrayList<PersonData>();
		List<String> listTester = Service.webServiceDelegate.listClassInstance(null, ConsistentOntology.TESTER);
		//-- all tester label
		PersonData allTester = new PersonData();
		String allTesterName = "All tester";
		allTester.setFullURI("");
		allTester.setName(allTesterName);
		comboData.add(allTester);
		comboFilterByDev.add(allTesterName);
		
		for(String tester: listTester){
			String testerName = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, tester, ConsistentOntology.HAS_NAME).get(0);
			PersonData personData = new PersonData();
			personData.setFullURI(tester);
			personData.setName(testerName);
			comboData.add(personData);
			comboFilterByDev.add(testerName);
		}
		comboFilterByDev.setData(comboData);
		
	}
	
	/*TableColumn includeColumn = jTable1.getColumnModel().getColumn(0);
            includeColumn.setCellEditor(new DefaultCellEditor(new JCheckBox()));
*/
	//---------------------------------------
	
	public void innitData(){
		List<List> listOfResult = new ArrayList<List>();
		
	    
	    List<String> listTest = Service.webServiceDelegate.listAllRelatedInstance(null, ConsistentOntology.TEST);
	    for(String test: listTest){
	    	List<String> subList = new ArrayList<String>();
	    	
	    	String  testName = "";
	    	List<String> listTestName = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, test, ConsistentOntology.HAS_NAME);
	    	if(listTestName.size() == 0 ){
	    		testName += " " + test.substring(test.lastIndexOf("#")+1) + "\n";
	    	}
	    	else{
	    		for(String s: listTestName){
		    		testName += " "+s +"\n";
		    	}
	    	}
	    	
	    	String  description = "";
	    	for(String s:Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, test, ConsistentOntology.HAS_DESCRIPTION)){
	    		description += " "+s +"\n";
	    	}
	    	
	    	String  tester = "";
	    	String  testerURI = "";
	    	List<String> listTester = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, test, ConsistentOntology.TEST_IS_EXECUTE_BY_TESTER);
	    	//-- xu ly cho truong hop test ko co tester luc chon filter la All Tester
	    	if(listTester.size()==0){
	    		testerURI = "\n";
	    	}
	    	for(String s:listTester){
	    		tester += " "+Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, s, ConsistentOntology.HAS_NAME).get(0) + "\n";
	    		testerURI += s + "\n";
	    	}
	    	
	    	String  isSuccess = "";
	    	boolean addFlag = true;
	    	List<String> listIsSuccess = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, test, ConsistentOntology.TEST_IS_SUCCESSFUL);
	    	if(listIsSuccess.size()==0 && this.typeTestToShow == 1){
	    		addFlag = false;
	    	}
	    	for(String s: listIsSuccess){
	    		switch (this.typeTestToShow) {
				case 0://-- All test
					if(s.equals("1") || s.equals("true") ){
		    			isSuccess += " Yes\n";
		    		}
		    		else{
		    			isSuccess += " No\n";
		    		}
			    	break;
				case 1://-- only Successful Test
					if(s.equals("1") || s.equals("true")){
						isSuccess += " Yes\n";
					}
					else{
						addFlag = false;
					}
					break;
				case 2://-- only Processsing Test
					if(!(s.equals("1") || s.equals("true"))){
						isSuccess += " No\n";
					}
					else{
						addFlag = false;
					}
					break;
				default:
					break;
				}
	    		
	    		break;
	    	}
	    	
	    	String  testAt = "";
	    	for(String s:Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, test, ConsistentOntology.TEST_EXECUTE_AT)){
	    		testAt += " "+s +"\n";
	    	}
	    	
	    	subList.add(testName);
	    	subList.add(description);
	    	subList.add(tester);
	    	subList.add(isSuccess);
	    	subList.add(testAt);
	    	subList.add(test);
	    	subList.add(testerURI);
	    	
	    	if(addFlag){
	    		listOfResult.add(subList);
	    	}
	    	
	    }
	    
	    this.listOfList = listOfResult ; 

		bindDataListTable(listOfResult);
		
	}
	//-- DeleteTest
	private void DeleteTest() {
		for(int i=0;i<table.getRowCount();i++){
			boolean choosenToDelete = Boolean.parseBoolean(table.getValueAt(i, 0).toString()) ;
			if(choosenToDelete){
				String fullURIofTest = (String)listOfList.get(i).get(5);
				Service.webServiceDelegate.removeIndividual(null, fullURIofTest);
			}
		}
		innitData();
		MessageDialog.openInformation(new Shell(), "Notice", "Delete test(s) succesfully !");
		
	}
	/**
	 * @param listOfLists
	 * Do du lieu vao Jtable
	 */
	public void bindDataListTable(List<List> listOfLists) {
		//table.removeAll();
		frame.removeAll();
		table = creatJTable(listOfLists);
		
		frame.add(new JScrollPane(table));
		this.listFullData = listOfLists;
		this.creatAction();		
		frame.setVisible(true);

	}
	
	/**
	 * @param listOfLists
	 * @return
	 * Tao Jtable voi model duoc lay
	 */
	public JTable creatJTable(List<List> listOfLists) {
		modelTable = getModelTable(listOfLists);
		final JTable tableTemp = new JTable(modelTable) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				if (vColIndex == 0)
					return true;
				else
					return false;
			}
		};
		TableColumn includeColumn = tableTemp.getColumnModel().getColumn(0);
        includeColumn.setCellEditor(new DefaultCellEditor(new JCheckBox()));
        for(int i=1; i< tableTemp.getColumnCount(); i++){
        	tableTemp.getColumnModel().getColumn(i).setCellRenderer(new TextAreaRenderer());
        }
//		tableTemp.getColumnModel().getColumn(2).setCellRenderer(new TextAreaRenderer());
//		tableTemp.getColumnModel().getColumn(3).setCellRenderer(new TextAreaRenderer());
		//tableTemp.addMouseListener(new SelectionListener(tableTemp));
		//Set lai do rong cho Jtable
		setWidth(0, 20, tableTemp);
		setWidth(1, 50, tableTemp);
		setWidth(2, 250, tableTemp);
		setWidth(3, 50, tableTemp);
		setWidth(4, 20, tableTemp);
		tableTemp.addMouseListener(new MouseAdapter(){
		     public void mouseClicked(MouseEvent e){
		      if (e.getClickCount() > 1){ 
		    	 System.out.println("double click event");
		    	 final String fullURI = (String)listOfList.get(((JTable)e.getSource()).getSelectedRow()).get(5);
		    	 
		    	 System.out.println("fullURI = " + fullURI);
		    	 PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
		 			public void run() {
		 				try {
		 					Shell shell = new Shell(PlatformUI.getWorkbench().getDisplay());
							shell.setText("Edit test !");
							shell.setImage(Images.imageRegistry.get(Images.BOOKMARK));
							shell.setLayout(new FillLayout());
							shell.setSize(510, 680);
							
							
							DataInstance dataInstance = new DataInstance();//(DataInstance)itemSelected.getData("Data");
							dataInstance.setInstanceFullName(fullURI);
							
							CompositeListPropertiesOfInstance compositeListProperties = new CompositeListPropertiesOfInstance(shell, SWT.NONE);
							compositeListProperties.setInputData(dataInstance);
							compositeListProperties.updateInterface();
							shell.setVisible(true);
							
							shell.addListener(SWT.Close, new Listener() {
								public void handleEvent(Event e) {
									innitData();
								}
							});
							
		 				} catch (Exception e) {
		 					e.printStackTrace();
		 				}
		 			}
		 		});
		    	 
		    	 
		         }
		      }
		     } );


//		setWidth(3, 5, tableTemp);
		return tableTemp;
	}

	/**
	 * @param listOfLists
	 * @return
	 * Tao model cho Jtable
	 */
	private DefaultTableModel getModelTable(List<List> listOfLists) {
		String content="";
		DefaultTableModel model = new DefaultTableModel() {
			public Class<?> getColumnClass(int columnIndex) {
				if (getRowCount() > 0 && getValueAt(0, columnIndex) != null)
					return getValueAt(0, columnIndex).getClass();
				return super.getColumnClass(columnIndex);
			}
		};
		for (int col = 0; col < columnNames.length; col++) {
			model.addColumn(columnNames[col]);
		}
		

		List listItems;
		for (int loopIndex = 0; loopIndex < listOfLists.size(); loopIndex++) {
			listItems = listOfLists.get(loopIndex);
			
			ToolParseDoc toolParse  = new ToolParseDoc();
			content=toolParse.convertPlainText(listItems.get(1).toString());
			model.addRow(new Object[] {  false, listItems.get(0).toString(),content, listItems.get(2).toString(),listItems.get(3).toString(), listItems.get(4).toString()});
		}

		return model;
	}
	
	/**
	 * @param vColIndex
	 * @param vColWidth
	 * @param tableTemp
	 * Set lai do rong cho  cho cac cot
	 */
	private void setWidth(int vColIndex, int vColWidth, JTable tableTemp) {
		TableColumn col = tableTemp.getColumnModel().getColumn(vColIndex);
		col.setPreferredWidth(vColWidth);
	}
	

	/**
	 * Tao cac action chung ca table lan cac button
	 */
	public void creatAction() {
		modelTable.addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
//				String content,lable,methodtest,testfor,idtest;
//				int row = e.getFirstRow();
//				int column = e.getColumn();
//				TableModel model = (TableModel) e.getSource();				
//				Object data = model.getValueAt(row, column);
//				int countColumn= model.getColumnCount();
//				if (data instanceof Boolean)
//					System.out.println("Value changed in Row: " + row
//							+ " Column: " + column + " New Value = " + data);
//				if (data.equals(true)) {
//					System.out.println("View detail");
//					//Lay thong tin tu tabel
//					lable		 = model.getValueAt(row, column+1).toString();//Lay thuoc trang bao nhieu
//					content      =  model.getValueAt(row, column+2).toString();
//					methodtest   =  model.getValueAt(row, column+3).toString();
//					testfor 	 =  model.getValueAt(row, column+4).toString();
//					idtest	 	 =  model.getValueAt(row, column+5).toString();
//					//lay vi tri
//					//Update vao mot list gom co vi tri,trang,noi dung
//					//updateList(poisition, page, typetemp,tempContent,idImage,labelImage);
//					
//				} else {
//					System.out.println("Remove to list");
//				}
			}
		});
		
		
		
	}
	
	
	@Override
	int updateInterface() {
		// TODO Auto-generated method stub
		return 0;
	}

}
