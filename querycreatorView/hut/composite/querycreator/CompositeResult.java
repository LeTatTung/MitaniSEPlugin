package hut.composite.querycreator;

import hut.composite.annotationCreatorAndEditor.CompositeListPropertiesOfInstance;
import hut.data.queryinterchangedata.RestrictionOuputData;
import hut.data.queryinterchangedata.RowData;
import hut.jobs.BKJob;
import hut.jobs.IObjectExecuteJob;
import hut.model.annotationCreatorAndEditor.DataInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mintani.valueconst.ConsistentOntology;
import ontology.images.Images;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TableTree;
import org.eclipse.swt.custom.TableTreeItem;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;

import com.hp.hpl.jena.ontology.Individual;

import eclipse.EnumCodeComponentTYPE;
import eclipse.EclipseUtil;

import service.Service;

import ws.owl.*;
import Consistent.*;
public class CompositeResult extends CompositeQuerySuper implements IObjectExecuteJob {
	
	// In case of using the sparqlresultmap
	private List<String> listResultVariable = new ArrayList<String>();
	private List<MapData> listResultMap = new ArrayList<MapData>();
	private List<ArrayList<String>> resultStringArrayList = new ArrayList<ArrayList<String>>();
	private List<ArrayListData> listResultArrayListData = new ArrayList<ArrayListData>();
	private String VALUE[]={"1","2","3","5","10","20"};
	private Combo pageCombo;
	private Table table;
	private TableTree resultTableTree;
	private int startIndex = 0;
	private int numberPerPage = 3;
	private List<String> listCaredVariable;
	private Map caredPropertyMap;
	private int maxNumberOfCaredProperty;
	private ArrayList<String> declaredVariableList;
	private TableTree variableChosenTableTree;
	
	/**
	 * Hiển thị text thông báo về kết quả
	 */
	private Label label;
	
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	
	/**
	 * Cho query vao trong job
	 */
	private BKJob job;

	public CompositeResult(Composite parent, int style) {
		super(parent, style);
		setLayout(new FormLayout());
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);

		label = new Label(this, SWT.NONE);
		final FormData fd_label = new FormData();
		fd_label.right = new FormAttachment(100, -200);
		fd_label.bottom = new FormAttachment(100, -8);
		fd_label.top = new FormAttachment(100, -22);
		fd_label.left = new FormAttachment(0, 150);
		label.setLayoutData(fd_label);
		label.setAlignment(SWT.RIGHT);
		toolkit.adapt(label, true, true);
		label.setText("Label");
		
		final ToolBar toolBar = new ToolBar(this, SWT.NONE);
		final FormData fd_toolBar = new FormData();
		fd_toolBar.bottom = new FormAttachment(100, -3);
		fd_toolBar.right = new FormAttachment(0, 200);
		fd_toolBar.left = new FormAttachment(0, 0);
		fd_toolBar.top = new FormAttachment(100, -28);
		toolBar.setLayoutData(fd_toolBar);
		toolkit.adapt(toolBar, true, true);

		/**
		 * Update button in chosen tree
		 */
		final ToolItem updateToolItem = new ToolItem(toolBar, SWT.PUSH);
		updateToolItem.setText("Update");
		updateToolItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent event) {
				//Khởi tạo, lấy lại list các biến đã được khai báo
				declaredVariableList = new ArrayList<String>();
				
				updateChosenVariableList();
				updateVariableChosenTree();
			}

		});

		/**
		 * Query button in chosen tree
		 */
		final ToolItem queryToolItem = new ToolItem(toolBar, SWT.PUSH);
		queryToolItem.setText("Query");
		queryToolItem.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(final SelectionEvent event) {
				job = new BKJob("Querying ...");
				job.setObjectExecuteJob(CompositeResult.this);
				job.setUser(true);
				job.schedule();
			}
		});

		SashForm sashForm;
		sashForm = new SashForm(this, SWT.HORIZONTAL);

		final Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FormLayout());
		toolkit.adapt(composite);

		/** 
		 * Chosen tree
		 */
		variableChosenTableTree = new TableTree(composite, SWT.BORDER | SWT.CHECK);
		final FormData fd_variableChosenTableTree = new FormData();
		fd_variableChosenTableTree.bottom = new FormAttachment(100, 0);
		fd_variableChosenTableTree.right = new FormAttachment(100, 0);
		fd_variableChosenTableTree.top = new FormAttachment(0, 0);
		fd_variableChosenTableTree.left = new FormAttachment(0, 0);
		variableChosenTableTree.setLayoutData(fd_variableChosenTableTree);
		toolkit.adapt(variableChosenTableTree, true, true);
		final FormData fd_sashForm = new FormData();
		fd_sashForm.left = new FormAttachment(0, 0);
		fd_sashForm.bottom = new FormAttachment(100, -33);
		fd_sashForm.right = new FormAttachment(100, 0);
		fd_sashForm.top = new FormAttachment(0, 0);
		sashForm.setLayoutData(fd_sashForm);
		toolkit.adapt(sashForm, true, true);

		final Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FormLayout());
		toolkit.adapt(composite_1);

		/** 
		 * Result tree
		 */
		resultTableTree = new TableTree(composite_1, SWT.BORDER);
		table = resultTableTree.getTable();
		final FormData fd_table_1 = new FormData();
		fd_table_1.top = new FormAttachment(0, 5);
		fd_table_1.left = new FormAttachment(0, 5);
		table.setLayoutData(fd_table_1);
		final FormData fd_tableTree = new FormData();
		fd_tableTree.bottom = new FormAttachment(100, 0);
		fd_tableTree.right = new FormAttachment(100, 0);
		fd_tableTree.top = new FormAttachment(0, 0);
		fd_tableTree.left = new FormAttachment(0, 0);
		resultTableTree.setLayoutData(fd_tableTree);
		toolkit.adapt(resultTableTree, true, true);

		table.setCursor(new Cursor(parent.getDisplay(),SWT.CURSOR_HAND));
		
		table.addListener(SWT.MouseDoubleClick, new Listener() {//Khi bam vao link thi hien graph display
		    public void handleEvent(Event event) {
		        Rectangle rect = resultTableTree.getClientArea();
		        int itemCount = resultTableTree.getItemCount();
		        int columnCount = table.getColumnCount();
		        int i = 0;
		        while (i < itemCount) {
		            TableTreeItem item = resultTableTree.getItem(i);
		            for (int j = 0; j < columnCount; j++) {
		                Rectangle bounds = item.getBounds(j);
		                if (bounds.y > rect.height) return;
		                if (bounds.contains(event.x, event.y)) {
		                    String s=(String)item.getData("ID"+j);		                    
		                    System.out.println(s);		                    
		                    String classFullURI=Service.webServiceDelegate.getClassOfIndividual(null, s);
		                    
		                    Boolean openEditor = true;
		                    
		                    Boolean type = classFullURI.equals(ConsistentOntology.CLASS)|| classFullURI.equals(ConsistentOntology.ABSTRACT_CLASS) || classFullURI.equals(ConsistentOntology.INTERFACE);
		                    Boolean method = classFullURI.equals(ConsistentOntology.METHOD);
		                    Boolean field = classFullURI.equals(ConsistentOntology.FIELD);
		                    if (type || method || field)
		                    {
		                    	String path = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, s, ConsistentOntology.FULL_PATH).get(0);
		                    	
		                    	String ID = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, s, ConsistentOntology.HAS_NAME).get(0); 
		                    		//Service.webServiceDelegate.getIndividualByName(null, s).getEnLabel();
		                    		//
		                    	System.out.println("heheh");
		                    	if (path!=null)
		                    	{
		                    		EnumCodeComponentTYPE codeType = null;
		                    		if (type)
		                    			codeType = EnumCodeComponentTYPE.TYPEDECLARATION;
	                    			else if (method)
	                    				codeType = EnumCodeComponentTYPE.METHOD;
	                    			else if (field)
	                    				codeType = EnumCodeComponentTYPE.FIELD;
		                    		System.out.println(codeType);
		                    		EclipseUtil.openJavaEditor(path, ID, codeType);
		                    		System.out.println(codeType);
		                    		
		                    		openEditor = false;
		                    	}

		                    }
		                    if (openEditor)
	                    	{
		                    	Shell shell = new Shell(table.getDisplay());
		    					shell.setText("Edit instance !");
		    					shell.setImage(Images.imageRegistry.get(Images.BOOKMARK));
		    					shell.setLayout(new FillLayout());
		    					shell.setSize(510, 680);
		    					
		    					DataInstance dataInstance = new DataInstance();
		    					dataInstance.setInstanceFullName(s);
		    					dataInstance.setNewInstance(false);
		    					System.out.println("instanceFullName :" + dataInstance.getInstanceFullName() );
		    					
		    					CompositeListPropertiesOfInstance compositeListProperties = new CompositeListPropertiesOfInstance(shell, SWT.NONE);
		    					compositeListProperties.setInputData(dataInstance);
		    					compositeListProperties.updateInterface();
		    					
		    					shell.open();
	                    	}
		                    	
		                }
		            }
		            i++;
		        }
		    }
		});
		

		ToolBar toolBar_2;
		ToolBar toolBar_3;

		
		Composite composite_2;
		composite_2 = new Composite(this, SWT.NONE);
		composite_2.setLayout(new FillLayout());
		final FormData fd_composite_2 = new FormData();
		fd_composite_2.left = new FormAttachment(100, -145);
		fd_composite_2.right = new FormAttachment(100, -5);
		fd_composite_2.top = new FormAttachment(100, -28);
		fd_composite_2.bottom = new FormAttachment(100, -3);
		composite_2.setLayoutData(fd_composite_2);
		toolkit.adapt(composite_2);

		final ToolBar toolBar_1 = new ToolBar(composite_2, SWT.NONE);
		toolkit.adapt(toolBar_1, true, true);
		toolBar_2 = new ToolBar(composite_2, SWT.NONE);

		/**
		 * First button in result tree
		 */
		final ToolItem firstToolItem = new ToolItem(toolBar_1, SWT.PUSH);
		firstToolItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				startIndex = 0;
				displayResultPage();
			}
		});
		firstToolItem.setText("<<");

		/**
		 * previous button in result tree
		 */
		final ToolItem previousToolItem = new ToolItem(toolBar_1, SWT.PUSH);
		previousToolItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				startIndex -= numberPerPage;
				if (startIndex<0) startIndex=0;
				displayResultPage();
			}
		});
		previousToolItem.setText("<");
		toolkit.adapt(toolBar_2, true, true);

		/**
		 * refresh button in result tree
		 */
		final ToolItem refreshToolItem = new ToolItem(toolBar_2, SWT.PUSH);
		refreshToolItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				generateCaredItems();
				displayResultPage();
			}
		});
		refreshToolItem.setImage(Images.imageRegistry.get(Images.REFRESH));
		toolBar_3 = new ToolBar(composite_2, SWT.NONE);
		toolkit.adapt(toolBar_3, true, true);

		/**
		 * next button in result tree
		 */
		final ToolItem nextToolItem = new ToolItem(toolBar_3, SWT.PUSH);
		nextToolItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				int i=resultStringArrayList.size();
				if (startIndex+numberPerPage<i)
				{
					startIndex += numberPerPage;
					displayResultPage();
				}
			}
		});
		nextToolItem.setText(">");

		/**
		 * last button in result tree
		 */
		final ToolItem lastToolItem = new ToolItem(toolBar_3, SWT.PUSH);
		lastToolItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				startIndex = resultStringArrayList.size() - numberPerPage;
				if (startIndex < 0) startIndex=0;
				displayResultPage();
			}
		});
		lastToolItem.setText(">>");
		
		pageCombo = new Combo(this, SWT.NONE);
		final FormData fd_pageCombo = new FormData();
		fd_pageCombo.left = new FormAttachment(100, -195);
		fd_pageCombo.right = new FormAttachment(100, -145);
		fd_pageCombo.bottom = new FormAttachment(100, -7);
		fd_pageCombo.top = new FormAttachment(100, -26);
		pageCombo.setLayoutData(fd_pageCombo);
		
		pageCombo.addModifyListener(new ModifyListener() {
			public void modifyText(final ModifyEvent arg0) {
				try {
					numberPerPage = Integer.parseInt(pageCombo.getText());
					displayResultPage();
				} catch (NumberFormatException e) {
					numberPerPage = 3;
					pageCombo.setText("3");
					displayResultPage();
				}
			}
		});
		pageCombo.setText("3");
		toolkit.adapt(pageCombo, true, true);

				
		
		/**
		 * Page combo to choose page to display
		 */
		
		for (String s:VALUE)
			pageCombo.add(s);
		numberPerPage = 3;
		sashForm.setWeights(new int[] { 275, 212 });

		/**
		 * Event listener for chosen tree
		 */
		variableChosenTableTree.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event event) {

				if (event.detail == SWT.CHECK) {
					TableTreeItem item = (TableTreeItem) event.item;
					if (item.getParentItem() == null) {
						item.setGrayed(false);
						for (TableTreeItem childItem : item.getItems()) {
							childItem.setChecked(item.getChecked());
						}
					} else {
						TableTreeItem parentItem = item.getParentItem();
						Boolean checkAll = true;
						Boolean notCheckAll = true;
						for (TableTreeItem childItem : parentItem.getItems()) {
							if (!childItem.getChecked()) {
								checkAll = false;
								break;
							}
						}
						for (TableTreeItem childItem : parentItem.getItems()) {
							if (childItem.getChecked()) {
								notCheckAll = false;
								break;
							}
						}
						if (checkAll) {
							parentItem.setGrayed(false);
							parentItem.setChecked(true);
						} else if (notCheckAll) {
							parentItem.setGrayed(false);
							parentItem.setChecked(false);
						} else {
							parentItem.setGrayed(true);
							parentItem.setChecked(true);
						}
					}
				}
			}

		});

		//Update the chosen variable list
		updateChosenVariableList();		
	}

	/**
	 * Execute the query string, return querydata 
	 * @param queryString
	 * @return
	 */
	protected List<ArrayListData> executeQuery(String queryString) {
		//QueryData resultMap = delegate.sparqlResultMap(queryString);
		/*System.out.println("-------------resultMap---------------");
		System.out.println(resultMap.getVariableList());
		for (MapData key : resultMap.getResultMap())
		{
			System.out.println(key.getKey() + ":" + key.getObject());
		}*/
		List<ArrayListData> resultList = Service.webServiceDelegate.sparqlResultList(null, queryString);
		return resultList;
		
	}

	/**
	 * Generate the query string, return string
	 * @return
	 */
	protected String generateQuery() {
		if (listCaredVariable.size() == 0)
			{
			updateVariableChosenTree();
			return "";
			}
		
		else 
		{
			StringBuffer result=new StringBuffer("");
			result.append(Consistent.prefix);
			result.append(generateSelect());
			result.append(generateWhere());
			
			
			return result.toString();
		}
		
	}

	/**
	 * Generate the select section in query string
	 * @return
	 */
	protected String generateSelect(){
		StringBuffer result=new StringBuffer("\nSELECT DISTINCT");
		for (String s:listCaredVariable)
		{
			int x = s.indexOf("(");
			result.append(" ?"+ s.substring(0,x));
		}
		return result.toString();
	}
	
	/**
	 * Generate the where section in query string
	 * @return
	 */
	protected String generateWhere(){
		StringBuffer result=new StringBuffer("\nWHERE\n{");
		result.append(generateDeclareVariableTriple());
		result.append(generateRestrictionTriple());
		result.append("\n}");
		return result.toString();
	}
	
	/**
	 * Generate the variable declaration triple in where section 
	 * @return
	 */
	private String generateDeclareVariableTriple()
	{
		StringBuffer result=new StringBuffer("");
		String className;
		for (String s:declaredVariableList)
		{
			
			int x = s.indexOf("(");
			int y = s.indexOf(")");
			className= s.substring(x + 1, y);
			String variableName = s.substring(0, x);
			String classFullName = Service.webServiceDelegate.getClassByShortName(null, className).getClassURI();
			if (classFullName!=null)
				result.append("\n?"+ variableName +" rdf:type <"+ classFullName +">.");
		}
		return result.toString();
	}
	
	/**
	 * Generate the restriction triple in where section
	 * @return
	 */
	private String generateRestrictionTriple()
	{
		StringBuffer result=new StringBuffer("");
		QueryUtil.reset();
		
		for (RowData rowData : ((RestrictionOuputData) this.getInputData()).getRowList())
		{
			String subject = rowData.getSubject();
			String predicate = rowData.getPredicate();
			String object = rowData.getObject();
			String value = rowData.getFilterValue();
			Boolean optional= rowData.isOptional();
			
			if(subject!="" && predicate!="" && object!="")
				try {
					String s = (String) Service.webServiceDelegate.getPropertyByShortName(null, predicate).getPropertURI();
					String type = Service.webServiceDelegate.getPropertySpecificDataType(null, s);
					int x = subject.indexOf("(");
					String subjectVariable = subject.substring(0, x);
					if (type != null)
					{
						//Data property don xuong cuoi moi nhanh
						result.append(QueryUtil.DataTriple(subjectVariable, null, s, object, value, optional));
					}
					else
					{
						int y = object.indexOf("(");
						String objectVariable = object.substring(0, y);
								
						//Object property cho het len dau moi nhanh
						result.insert(0,QueryUtil.ObjectTriple(subjectVariable, null, s, objectVariable, optional));
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			
		}
	
		return result.toString();
	}
	
	/**
	 * declare which variable is displayed
	 */
	protected void generateCaredItems() {		
		caredPropertyMap=new HashMap();
		maxNumberOfCaredProperty = 0;
		
		int Max=0;//Dem instance co nhieu thuoc tinh duoc chon nhat
		for (TableTreeItem parentItem : variableChosenTableTree.getItems())
		{
			if (parentItem.getChecked())
			{
				String variableName=parentItem.getText();
				int tmpMax=0;	
				if (!listCaredVariable.contains(variableName))
					listCaredVariable.add(variableName);
								
				List<String> tmpList=new ArrayList();
				int i=0;
				for (TableTreeItem childItem:parentItem.getItems())
				{
					if (childItem.getChecked())
					{
						
						tmpList.add(childItem.getText());
						tmpMax++;
					}
					i++;
				}
				if (!caredPropertyMap.containsKey(variableName))
					caredPropertyMap.put(variableName, tmpList);
				if (tmpMax>Max)
					Max=tmpMax;
			}
		}
		
		maxNumberOfCaredProperty=Max;
		
		System.out.println("Cac bien duoc chon : ");
		System.out.println(this.listCaredVariable);
		System.out.println("Cac thuoc tinh duoc chon");
		System.out.println(this.caredPropertyMap);	
	}

	/**
	 * update the chosen variable list when there's a change in variable composite or restriction composite
	 */
	public void updateChosenVariableList() {
		System.out.println("Result variable list is :");
		System.out.println(declaredVariableList);

		if (this.getInputData() != null)

		{
			if (this.getInputData() instanceof RestrictionOuputData) {
				RestrictionOuputData inputData = (RestrictionOuputData) this.getInputData();
				if (inputData != null) {

					Map<String, String> tmpVariableMap = inputData.getVariableMap();
					for (String tmpVariable : tmpVariableMap.keySet()) {
						if ((tmpVariable != "")&& (!declaredVariableList.contains(tmpVariable + "(" + tmpVariableMap.get(tmpVariable) + ")"))) {
							declaredVariableList.add(tmpVariable + "(" + tmpVariableMap.get(tmpVariable) + ")");
						}
					}
					System.out.println("Result variable list after update is :");
					System.out.println(declaredVariableList);

				}

			}

			if (this.getInputData() instanceof Map) {
				Map<String, String> tmpVariableMap = (Map<String, String>) this.getInputData();
				for (String tmpVariable : tmpVariableMap.keySet()) {
					if ((tmpVariable != "") && (!declaredVariableList.contains(tmpVariable + "(" + tmpVariableMap.get(tmpVariable) + ")"))) {
						declaredVariableList.add(tmpVariable + "("	+ tmpVariableMap.get(tmpVariable) + ")");
					}
				}
				System.out.println("Result variable list after update is :");
				System.out.println(declaredVariableList);
			}
		}
	}

	protected void updateVariableChosenTree() {

		variableChosenTableTree.removeAll();
		for (String parentText : declaredVariableList) {
			TableTreeItem parent = new TableTreeItem(variableChosenTableTree,SWT.NONE);
			parent.setText(0, parentText);
			int x = parentText.indexOf("(");
			int y = parentText.indexOf(")");
			String className = parentText.substring(x + 1, y);
			System.out.println("Da vao den phan tao lai cay :");
			System.out.println("ten thuoc tinh la :" + className);
			String classFullName = Service.webServiceDelegate.getClassByShortName(null, className).getClassURI();
			List<PropertyData> propertyList = Service.webServiceDelegate.getAllClassProperties(null, classFullName);
			for (int j = 0; j < propertyList.size(); j++) {

				TableTreeItem child = new TableTreeItem(parent, SWT.NONE);
				child.setText(0, propertyList.get(j).getPropertyName());
			}
		}
	}
	
	public void resetResultTable()
	{
		removeResultTableComponent();
		
		TableColumn column;
		for (String s:listCaredVariable)
		{
			column = new TableColumn(table, SWT.LEFT);
			column.setText(s);
			column.setWidth(300);
		}
	}
	public void removeResultTableComponent()
	{
		table.setLinesVisible(false);
		table.setHeaderVisible(true);
		
		for (TableTreeItem s:resultTableTree.getItems())
			s.dispose();
		for (TableColumn s:table.getColumns())
			s.dispose();
	}

	public void displayResult()
	{
		startIndex=0;
		resetResultTable();
		displayResultPage();		
	}
	public void displayResultPage()
	{
		for (TableTreeItem s:resultTableTree.getItems())
			s.dispose();
		int min=Math.min(startIndex + numberPerPage, resultStringArrayList.size());
		
		for (int i=startIndex; i<min; ++i)
		{
			ArrayList<String> ID = resultStringArrayList.get(i);
			addResultIntoColumn(ID);
		}
		
		setResultText();
	}
		
	
	private void addResultIntoColumn(ArrayList<String> ID)
	{
		int number=listCaredVariable.size();
		if (number==0) return;
		
		TableTreeItem parent = new TableTreeItem(resultTableTree, SWT.NONE);
		
		TableTreeItem child;
		for (int i=0; i<number; ++i)
		{
			//parent.setText(i, fullURI?ID[i]:ID[i].substring(ID[i].indexOf("#")));//full URI thi hien het, con khong, bo namespace
			parent.setText(i,ID.get(i).substring(ID.get(i).indexOf("#") + 1));
			parent.setData("ID"+i,ID.get(i));
		}
		
		//Tao cac child item 1 lan duy nhat o column dau tien
		String variable= listCaredVariable.get(0);
		List<String> caredProperty = (List<String>) caredPropertyMap.get(variable);
		/*for (String s : caredProperty)
		{
			System.out.println(s);
		}*/
		List<List<String>> propertyValue = new ArrayList();//Lay ra cac gia tri cho cac thuoc tinh quan tam
		for (String s : caredProperty)
			try {
				System.out.println(s);
				String propertyFullName = Service.webServiceDelegate.getPropertyByShortName(null, s).getPropertURI();
				System.out.println(ID.get(0).substring(ID.get(0).indexOf("#") + 1));
				
				propertyValue.add(Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, ID.get(0), propertyFullName));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		for (List<String> tmp : propertyValue)
			System.out.println(propertyValue);
		int index=0;
		for (String s : caredProperty)
		{
			child = new TableTreeItem(parent, SWT.NONE);			
			
			StringBuffer display=new StringBuffer("");
			
			display.append(s + ": ");
			List<String> tmpList=propertyValue.get(index);
			if (tmpList.size()!=0)
			{
				display.append(tmpList.get(0));
				for (int k=1;k<tmpList.size();++k)
					display.append(", "+tmpList.get(k));
			}
			
			child.setText(0, display.toString());
		    index++;
		}
		for (int i=caredProperty.size();i< maxNumberOfCaredProperty;++i)
			child = new TableTreeItem(parent, SWT.NONE);
		
		
		//Cac child o column tiep theo chi can setText
		for (int i=1; i<number; ++i)
		{
			variable = listCaredVariable.get(i);
			caredProperty= (List<String>) caredPropertyMap.get(variable);
			
			propertyValue = new ArrayList();
			for (String s: caredProperty)
			{
				System.out.println(s);
				String propertyFullName = Service.webServiceDelegate.getPropertyByShortName(null, s).getPropertURI();
				
				propertyValue.add(Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, ID.get(i), propertyFullName));
			}
			index=0;
			for (String s : caredProperty)
			{
				child = parent.getItem(index);
				
				StringBuffer display=new StringBuffer("");
				
					display.append(s +": ");
				List<String> tmpList=propertyValue.get(index);
				if (tmpList.size()!=0)
				{
					display.append(tmpList.get(0));
					for (int k=1;k<tmpList.size();++k)
						display.append(", "+tmpList.get(k));
				}
				
				child.setText(i,display.toString());
			    index++;
			}
		}
		parent.setExpanded(true);
		parent = new TableTreeItem(resultTableTree, SWT.NONE);//Them 1 dong trong cho dep ???
	}
	
	private void setResultText()
	{
		int i =resultStringArrayList.size();
		if (i ==0)
			label.setText("No results found!");
		else
			label.setText("Found: "+i
					+" results. Showing: "+(startIndex+1)+"-"+Math.min(startIndex + numberPerPage, i));
	}

	public void jobExecute(final IProgressMonitor monitor) {
		monitor.beginTask("Querying to server ...", 4);
		
		monitor.subTask("Generate query string ...");
		monitor.worked(1);		
		PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
			public void run() {
				try {			
					//Lấy dữ liệu từ bên CompositeRestriction
					CompositeResult.this.getQueryController().updateRowData();
					
					//Chỉ khởi tạo lại list listCaredVariable khi bấm query. Khi bấm refresh chỉ cần listCaredProperty thay đổi
					listCaredVariable = new ArrayList<String>();
					generateCaredItems();
					System.out.println("had generated cared Items");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		String queryString = generateQuery();
		System.out.println(queryString);
		monitor.worked(1);
		monitor.subTask("Execute query ...");		
		final List<ArrayListData> result = executeQuery(queryString);
		final List<ArrayList<String>> tmpArray = new ArrayList<ArrayList<String>>();
		
		monitor.worked(1);
		monitor.subTask("Display result ...");		
		PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
			public void run() {
				try {				
					for (int i = 1 ; i < result.size(); i++)
					{
						tmpArray.add((ArrayList<String>) result.get(i).getData());
					}
					
					resultStringArrayList = tmpArray;
					for (int i = 0; i < resultStringArrayList.size(); i++)
						System.out.println(resultStringArrayList.get(i));
					
					if (listCaredVariable.size() == 0)
					{
						resetResultTable();
						return;
					}
					else
						displayResult();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
