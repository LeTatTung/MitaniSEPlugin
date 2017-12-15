package hut.composite.document;


import hut.annotation.AnnotationAction;
import hut.composite.jtable.SelectionListener;
import hut.composite.jtable.TextAreaRenderer;
import hut.composite.popup.CompositePopupAnnotation;
import hut.composite.popup.CompositePopupSourceCode;
import hut.doc.DataIntermediateDoc;

import mintani.valueconst.ConstValue;
import java.awt.Frame;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import ontology.images.Images;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.widgets.FormToolkit;

import tool.parse.doc.ToolParseDoc;

public class CompositeDocComponent extends CompositeAnnotatorSuper {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private DefaultTableModel modelTable;
	private JTable table;
	private String[] columnNames = { "Select", "Content", "Label", "ID", "Page" };
	private Frame frame;
	private ToolBar toolBar;
	private ToolItem parseSemiautoItem;
	private ToolItem openSourceToolItem ;
	private ToolItem creatAnnotationToolItem;
	private List<List> subimageData = new ArrayList<List>();
	private List<List> subsectionData = new ArrayList<List>();
	private List<List> subparagraphData = new ArrayList<List>();
	private int type;	
	private CompositePopupAnnotation  popupComposite;
	private CompositePopupSourceCode poupCompositeSouceCode;
	private String currentPathFile;
	private List<List> listFullData;//List nay chua raw text. table chi hien ra plain text cho phan content
	
/*	private ArrayList<String> listIdImage = new ArrayList<String>();
	private ArrayList<String> listIdParagrap = new ArrayList<String>();
	private ArrayList<String> listIdSection = new ArrayList<String>();*/
	/**
	 * Create the composite
	 * @param parent 
	 * @param style
	 */
	public CompositeDocComponent(Composite parent, int style,int type) {
		super(parent, style);
		this.type=type;
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		setLayout(new FillLayout());

		final SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);

		final Composite composite = new Composite(sashForm, SWT.EMBEDDED);
		composite.setLayout(new FillLayout());
		toolkit.adapt(composite);
		toolkit.adapt(sashForm, true, true);
		final Composite composite_2 = new Composite(composite, SWT.EMBEDDED);
		final FillLayout fillLayout_1 = new FillLayout();
		fillLayout_1.marginHeight = 3;
		composite_2.setLayout(fillLayout_1);
		//Call function bin data
		frame = SWT_AWT.new_Frame(composite_2);
		
		final Composite composite_1 = new Composite(sashForm, SWT.NONE);
		toolkit.adapt(composite_1);

		toolBar = new ToolBar(composite_1, SWT.NONE);
		toolBar.setBounds(0, 0, 500, 34);
		toolkit.adapt(toolBar, true, true);

		parseSemiautoItem = new ToolItem(toolBar, SWT.PUSH);		
		parseSemiautoItem.setText("<<Semi courie>> ");
		parseSemiautoItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				creatActionParse();
			}
		});

		final ToolItem semiSentenceItem = new ToolItem(toolBar, SWT.PUSH);
		semiSentenceItem.setText("<<Semi sentence>>");

		openSourceToolItem = new ToolItem(toolBar, SWT.PUSH);
		openSourceToolItem.setText("<<Open Source Tree>>");
		openSourceToolItem.setEnabled(false);
		openSourceToolItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				openTreeSource();
				openSourceToolItem.setEnabled(false);
			}
		});

		creatAnnotationToolItem = new ToolItem(toolBar, SWT.PUSH);
		creatAnnotationToolItem.setText("<<Semi Matching>>");
		creatAnnotationToolItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				creatAnnotationAuto();
			}
		});
		
		sashForm.setWeights(new int[] {339, 33 });

	}

	@Override
	public void setController(ControllerAnnotator controller) {
		// TODO Auto-generated method stub
		super.setController(controller);
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
			if((listItems.get(1).toString() =="")||(listItems.get(1).toString() ==null)) content= CompositeDocComponent.this.getController().getBookMark();
			
			model.addRow(new Object[] { 
					Boolean.FALSE,
					content, 
					listItems.get(3).toString(),
					listItems.get(2).toString(), 
					listItems.get(4).toString(),
					listItems.get(1).toString()});
		}
		return model;
	}

	/**
	 * @param listOfLists
	 * @return
	 * Tao Jtable voi model duoc lay
	 */
	public JTable creatJTable(List<List> listOfLists) {
		modelTable = getModelTable(listOfLists);
		JTable tableTemp = new JTable(modelTable) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				if (vColIndex == 0)
					return true;
				else
					return false;
			}
		};
		tableTemp.getColumnModel().getColumn(1).setCellRenderer(new TextAreaRenderer());
		/*tableTemp.setRowHeight(0, 50);
		tableTemp.setRowHeight(3, 150);*/
		//Lang nghe su kien cho Jtable
		tableTemp.addMouseListener(new SelectionListener(tableTemp,this.getController().getCurrentPath()));
	
		//Set lai do rong cho Jtable
		setWidth(0, 50, tableTemp);
		setWidth(1, 250, tableTemp);
		setWidth(2, 50, tableTemp);
		return tableTemp;
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

	private void creatActionParse(){
	
		/*CompositeDocComponent.this.setInputData(subimageData);
		System.out.println(CompositeDocComponent.this.getInputData());
		CompositeDocComponent.this.getController().changeStatus();*/
		
		//Set Input data cho cac component
		setInputData();
		//Open shell dialog
		Shell shell = new Shell(parseSemiautoItem.getDisplay());
		shell.setText("Relation table");
		shell.setImage(Images.imageRegistry.get(Images.COPY));
		shell.setLayout(new FillLayout());
		shell.setSize(720,400);
		
		int x=Math.max(shell.getDisplay().getCursorLocation().x-shell.getSize().x, 0);
		int y=Math.max(shell.getDisplay().getCursorLocation().y-shell.getSize().y, 0);
		shell.setLocation(x,y);
		
		popupComposite=new CompositePopupAnnotation(shell,SWT.NONE,type);
		popupComposite.setController(this.getController());
		if(type==ConstValue.IMAGE){
			popupComposite.bindDataListTable((List<List>) this.getController().popupBindImageData());
		}
		if(type==ConstValue.SECTION){
			popupComposite.bindDataListTable((List<List>) this.getController().popupBindSectionData());
		}
		if(type==ConstValue.PARAGRAPH){
			popupComposite.bindDataListTable((List<List>) this.getController().popupBindParagraphData());
			List<List> paragraphData = ((DataIntermediateDoc)this.getOutputData()).getParagraphData();		
			List<List> sectionData = ((DataIntermediateDoc)this.getOutputData()).getSectionData();		
			
			popupComposite.findSectionParagrap((List<List>) this.getController().popupBindParagraphData(),paragraphData,sectionData);
	}
		shell.addListener(SWT.Close, new Listener() {
			public void handleEvent(Event e) {				
				
				popupComposite.dispose();
			}
		});
		
		shell.open();
		
	}
	
	public void setInputData(){
		if(type==ConstValue.IMAGE){
			this.setInputData(subimageData);
			//this.getController().popupBindImageData();
		}
		if(type==ConstValue.SECTION){
			this.setInputData(subsectionData);
			//this.getController().popupBindSectionData();
		}
		if(type==ConstValue.PARAGRAPH){
			this.setInputData(subparagraphData);
			//this.getController().popupBindParagraphData();
		}
	}
	
	private void openTreeSource(){

		//Set input data cho cac component
		setInputData();
		//Open shell dialog
		Shell shell = new Shell(parseSemiautoItem.getDisplay());
		//Shell shell = new Shell(this.getShell().getDisplay());
		shell.setText("Source Code");
		shell.setImage(Images.imageRegistry.get(Images.COPY));
		shell.setLayout(new FillLayout());
		shell.setSize(390,500);
		
		int x=Math.max(shell.getDisplay().getCursorLocation().x-shell.getSize().x, 0);
		int y=Math.max(shell.getDisplay().getCursorLocation().y-shell.getSize().y, 0);
		shell.setLocation(x,y);
		
		
		poupCompositeSouceCode = new CompositePopupSourceCode(shell,SWT.NONE,type);
		poupCompositeSouceCode.setController(this.getController());
		poupCompositeSouceCode.setCurrentPathFile(this.currentPathFile);
		shell.addListener(SWT.Close, new Listener() {
			public void handleEvent(Event e) {			
				poupCompositeSouceCode.dispose();
				openSourceToolItem.setEnabled(true);
			}
		});
		//Kiem tra neu da lay du lieu thanh cong.
		
		shell.open();
	
	}
	
	public void creatAnnotationAuto(){
		AnnotationAction annotationAction = new AnnotationAction();
		if(type==ConstValue.IMAGE){
			annotationAction.saveMatchSourceCode(subimageData,type);
		}
		if(type==ConstValue.SECTION){
			this.setInputData(subsectionData);
			//this.getController().popupBindSectionData();
			annotationAction.saveMatchSourceCode(subsectionData,type);
		}
		if(type==ConstValue.PARAGRAPH){
			this.setInputData(subparagraphData);
			//this.getController().popupBindParagraphData();
			annotationAction.saveMatchSourceCode(subparagraphData,type);
		}
	}
	
	
	
	/**
	 * Tao cac action chung ca table lan cac button
	 */
	/**
	 * Khi click vao 1 dong, lay ID cua dong day luu vao mang
	 */
	public void creatAction() {
		modelTable.addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {				
				String itemObject,contentOfItemSelected = "",idOfItemSelected="",labelOfItemSelected="";
				int typetemp=0;
				int poisition=0,pageOfItemSelected=0;				
				int row = e.getFirstRow();
				int column = e.getColumn();
				TableModel model = (TableModel) e.getSource();				
				Object data = model.getValueAt(row, column);
				if (data instanceof Boolean)
					System.out.println("Value changed in Row: " + row
							+ " Column: " + column + " New Value = " + data);
				if (data.equals(true)) {
				System.out.println("Add to list");
					//Kiem tra kieu 
					Object datatest = model.getValueAt(row, column+3);
					itemObject = datatest.toString();
					if(itemObject.indexOf("Image")!=-1){
						typetemp=ConstValue.IMAGE;//1: la image
						//poisition= Integer.parseInt(itemObject.substring(5,itemObject.length()));//lay vi tri cua item, vi du la image thu may
						
						}
					if(itemObject.indexOf("Paragraph")!=-1){
						typetemp = ConstValue.PARAGRAPH;
						//poisition= Integer.parseInt(itemObject.substring(9,itemObject.length()));//lay vi tri cua item, vi du la image thu may
						
					}
					if(itemObject.indexOf("Section")!=-1){
						typetemp = ConstValue.SECTION;
						//poisition= Integer.parseInt(itemObject.substring(7,itemObject.length()));//lay vi tri cua item, vi du la image thu may
						
					}
					
					//Lay thong tin tu tabel
					pageOfItemSelected		=  Integer.parseInt((model.getValueAt(row, column+4)).toString());//Lay thuoc trang bao nhieu
					//tempContent  =  getRawContent(poisition,page,typetemp);
					contentOfItemSelected 	=  model.getValueAt(row, column+1).toString(); // noi dung cua dong duoc chon
					idOfItemSelected   		=  model.getValueAt(row, column+3).toString(); // Id cua dong duoc chon
					labelOfItemSelected 	=  model.getValueAt(row, column+2).toString(); // label cua dong duoc chon
					//lay vi tri
					//Update vao mot list gom co vi tri,trang,noi dung
					//updateList(poisition, pageOfItemSelected, typetemp,contentOfItemSelected,idOfItemSelected,labelOfItemSelected);
					updateListSelected(pageOfItemSelected, typetemp, contentOfItemSelected, idOfItemSelected, labelOfItemSelected);
					
				} else {
					System.out.println("Remove to list");					
					//Kiem tra kieu 
					Object datatest = model.getValueAt(row, column+3);
					itemObject = datatest.toString();
					if(itemObject.indexOf("Image")!=-1){
						typetemp=ConstValue.IMAGE;//1: la image
						//poisition= Integer.parseInt(itemObject.substring(5,itemObject.length()));//lay vi tri cua item, vi du la image thu may
						
						}
					if(itemObject.indexOf("Paragraph")!=-1){
						typetemp = ConstValue.PARAGRAPH;
						//poisition= Integer.parseInt(itemObject.substring(9,itemObject.length()));//lay vi tri cua item, vi du la image thu may
						
					}
					if(itemObject.indexOf("Section")!=-1){
						typetemp = ConstValue.SECTION;
						//poisition= Integer.parseInt(itemObject.substring(7,itemObject.length()));//lay vi tri cua item, vi du la image thu may
						
					}
					
					//Lay thong tin tu tabel
					pageOfItemSelected		 =  Integer.parseInt((model.getValueAt(row, column+4)).toString());//Lay thuoc trang bao nhieu
					contentOfItemSelected  =  getRawContent(poisition,pageOfItemSelected,typetemp);
					idOfItemSelected      =  model.getValueAt(row, column+3).toString();
					
					removeListAnnotation(typetemp,contentOfItemSelected,idOfItemSelected);
				}
			}

			private void updateListSelected(int pageOfItemSelected,
					int typetemp, String contentOfItemSelected,
					String idOfItemSelected, String labelOfItemSelected) {
				List listItems;
				Boolean  check = true;
				String content;
				int chpage,chpoisition;
				ArrayList<String> listOfData = new ArrayList<String>();
				if(type==ConstValue.IMAGE){			
					//Kiem tra voi subimageData co trung khong
					for(int i=0;i<subimageData.size();i++){
						listItems = subimageData.get(i);
						/*chpage 		= Integer.parseInt(listItems.get(2).toString());
						chpoisition = Integer.parseInt(listItems.get(3).toString());*/
						String chId = listItems.get(4).toString();
						System.out.println(chId);
						/*if(chpage==page){
							if(chpoisition==poisition){
								check = false;
								break;
							}
						}*/
						if (chId == idOfItemSelected){
							check = false;
							break;
						}
					}
					
					if ( check == true){
						listOfData= addListData("image", contentOfItemSelected, pageOfItemSelected, 0, idOfItemSelected, labelOfItemSelected);
						subimageData.add(listOfData);
					}

				}
				
				if(type==ConstValue.SECTION){
					for(int i=0;i<subimageData.size();i++){
						listItems = subimageData.get(i);
						/*chpage 		= Integer.parseInt(listItems.get(2).toString());
						chpoisition = Integer.parseInt(listItems.get(3).toString());*/
						String chId = listItems.get(4).toString();
						System.out.println(chId);
						/*if(chpage==page){
							if(chpoisition==poisition){
								check = false;
								break;
							}
						}*/
						if (chId == idOfItemSelected){
							check = false;
							break;
						}
					}
					
					if ( check == true){
						
						listOfData = addListData("section", contentOfItemSelected, pageOfItemSelected, 0, idOfItemSelected, labelOfItemSelected);
						subsectionData.add(listOfData);
					}
				}
				
				if(type==ConstValue.PARAGRAPH){
					for(int i=0;i<subimageData.size();i++){
						listItems = subimageData.get(i);
						/*chpage 		= Integer.parseInt(listItems.get(2).toString());
						chpoisition = Integer.parseInt(listItems.get(3).toString());*/
						String chId = listItems.get(4).toString();
						System.out.println(chId);
						/*if(chpage==page){
							if(chpoisition==poisition){
								check = false;
								break;
							}
						}*/
						if (chId == idOfItemSelected){
							check = false;
							break;
						}
					}
					
					if ( check == true){
						
						listOfData = addListData("paragraph", contentOfItemSelected, pageOfItemSelected, 0, idOfItemSelected, labelOfItemSelected);
						subparagraphData.add(listOfData);
					}
				}
				
			}
		});
		
		
		
	}
	
	private void updateList(int poisition,int page,int type,String tempContent,String idImage,String labelImage){
			List listItems;
			Boolean  check = true;
			String content;
			int chpage,chpoisition;
			ArrayList<String> listOfData = new ArrayList<String>();
			if(type==ConstValue.IMAGE){			
				//Kiem tra voi subimageData co trung khong
				for(int i=0;i<subimageData.size();i++){
					listItems = subimageData.get(i);
					chpage 		= Integer.parseInt(listItems.get(2).toString());
					chpoisition = Integer.parseInt(listItems.get(3).toString());
					
					if(chpage==page){
						if(chpoisition==poisition){
							check = false;
							break;
						}
					}
				}
				
				if ( check == true){
					listOfData= addListData("image", tempContent, page, poisition, idImage, labelImage);
					subimageData.add(listOfData);
				}

			}
			
			if(type==ConstValue.SECTION){
				for(int i=0;i<subsectionData.size();i++){
					listItems = subsectionData.get(i);
					chpage 		= Integer.parseInt(listItems.get(2).toString());
					chpoisition = Integer.parseInt(listItems.get(3).toString());
					if(chpage==page){
						if(chpoisition==poisition){
							check = false;
							break;
						}
					}
				}
				
				if ( check == true){
					
					listOfData = addListData("section", tempContent, page, poisition, idImage, labelImage);
					subsectionData.add(listOfData);
				}
			}
			
			if(type==ConstValue.PARAGRAPH){
				for(int i=0;i<subparagraphData.size();i++){
					listItems = subparagraphData.get(i);
					chpage 		= Integer.parseInt(listItems.get(2).toString());
					chpoisition = Integer.parseInt(listItems.get(3).toString());
					if(chpage==page){
						if(chpoisition==poisition){
							check = false;
							break;
						}
					}
				}
				
				if ( check == true){
					
					listOfData = addListData("paragraph", tempContent, page, poisition, idImage, labelImage);
					subparagraphData.add(listOfData);
				}
			}
			
		}
	
	private void removeListAnnotation(int type, String content, String Id) {
	// TODO Auto-generated method stub
		List listItem;
		int findId = -1;
		List<List> tempDataList = new ArrayList<List>();
		if (type == ConstValue.IMAGE) {
			tempDataList =subimageData;
		}
		if (type == ConstValue.SECTION) {
			tempDataList =subsectionData;
		}
		if (type == ConstValue.PARAGRAPH) {
			tempDataList = subparagraphData;
		}
		for (int i = 0; i < tempDataList.size(); i++) {
			listItem = tempDataList.get(i);
			if ((listItem.get(4)).equals(Id)) {
				findId = i;
				break;
			}
		}
		if (findId != -1) {
			tempDataList.remove(findId);
		}
	}
	
	
	
	private ArrayList<String>  addListData(String type,String tempContent,int page,int poisition,String idImage,String labelImage){
		ArrayList<String> listOfData= new ArrayList<String>();
		listOfData.add(type);
		listOfData.add(tempContent);
		listOfData.add(Integer.toString(page));
		listOfData.add(Integer.toString(poisition));
		listOfData.add(idImage);
		listOfData.add(labelImage);
		return listOfData;
	}
	
	private String getRawContent(int position,int page,int typetemp){
		List subItem;
		int checkpoint=0, checkpage;
		String result="",itemObject;
		for (int i=0;i<listFullData.size();i++){
			subItem = listFullData.get(i);
			itemObject =  (String) subItem.get(3);//Vi tri tuong ung voi label cua no
			//Lay vi tri cua objec trong 1 trang vi du lay paragraph thu may trong trang
			if(type==ConstValue.IMAGE){
				//checkpoint= Integer.parseInt(itemObject.substring(5,itemObject.length()));
			}
			if(type==ConstValue.PARAGRAPH){
				//checkpoint= Integer.parseInt(itemObject.substring(9,itemObject.length()));
			}
			
			if(type==ConstValue.SECTION){
				//checkpoint= Integer.parseInt(itemObject.substring(7,itemObject.length()));
			}
			
			//Lay thuoc page thu bao nhieu
			checkpage  = Integer.parseInt((String) subItem.get(4));//Lay page bao nhieu
			if ((checkpoint==position)&&(checkpage==page)){
				result =  (subItem.get(1)).toString();
			}
		}
		return result;
	}

	/**
	 * @param listOfLists
	 * Do du lieu vao Jtable
	 */
	public void bindDataListTable(List<List> listOfLists,String currentFilePath) {
		//table.removeAll();
		frame.removeAll();

		table = creatJTable(listOfLists);
		frame.add(new JScrollPane(table));
		
		this.listFullData = listOfLists;
		this.currentPathFile = currentFilePath;
		this.creatAction();
		
		
		frame.setVisible(true);

	}

	public void updateDataList(List<List> listOfLists) {

		table.removeAll();
		table = creatJTable(listOfLists);
		frame.add(new JScrollPane(table));
	}

	@Override
	public ControllerAnnotator getController() {
		// TODO Auto-generated method stub
		return super.getController();
	}

	public ToolItem getOpenSourceToolItem() {
		return openSourceToolItem;
	}

}
