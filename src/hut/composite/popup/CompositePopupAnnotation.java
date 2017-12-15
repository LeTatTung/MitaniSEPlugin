package hut.composite.popup;



import hut.composite.document.CompositeAnnotatorSuper;
import hut.composite.document.ControllerAnnotator;
import hut.composite.jtable.SelectionListener;
import hut.composite.jtable.TextAreaRenderer;


import java.awt.BorderLayout;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import mintani.valueconst.ConsistentOntology;

import ontology.images.Images;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;


import service.Service;
import tool.parse.doc.ToolParseDoc;

public class CompositePopupAnnotation extends CompositeAnnotatorSuper {
	private DefaultTableModel modelTable;
	private JTable table;
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
    private  Frame frame;
    private String[] columnNames = { "Choice", "Content", "Label", "ID", "Page","Courie" };
    private Button nextButton;
    private Button closeButton;
    private Button bottomFromCodeButton;
    private Button topFromDocumentButton;
    private List<List> listResultData = new ArrayList<List>();
    private CompositePopupViewMatchCurie compositePopupViewMatchCurie;
    private int type;
    /**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public CompositePopupAnnotation(Composite parent, int style,int type) {
		super(parent, style);
		setLayout(new FillLayout());
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		this.type= type;
		final SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		toolkit.adapt(sashForm, true, true);

		final Composite compositRelativeTable = new Composite(sashForm, SWT.EMBEDDED);
		compositRelativeTable.setLayout(new FillLayout());
		toolkit.adapt(compositRelativeTable);

		frame = SWT_AWT.new_Frame(compositRelativeTable);

		
		final Composite compositeWrapAction = new Composite(sashForm, SWT.NONE);
		toolkit.adapt(compositeWrapAction);
		
		nextButton = new Button(compositeWrapAction, SWT.NONE);
		nextButton.setBounds(333, 5, 76, 29);
		nextButton.setImage(Images.imageRegistry.get(Images.RUN));
		toolkit.adapt(nextButton, true, true);
		nextButton.setText("Next");

		closeButton = new Button(compositeWrapAction, SWT.NONE);
		closeButton.setBounds(415, 5, 61, 29);
		closeButton.setImage(Images.imageRegistry.get(Images.CLOSE_COMPOSITE));
		
		toolkit.adapt(closeButton, true, true);
		closeButton.setText("Close");

		bottomFromCodeButton = toolkit.createButton(compositeWrapAction, "Bottom From Code ", SWT.RADIO);
		bottomFromCodeButton.setBounds(10, 8, 121, 23);

		topFromDocumentButton = toolkit.createButton(compositeWrapAction, "Top From Document", SWT.RADIO);
		topFromDocumentButton.setSelection(true);
		topFromDocumentButton.setBounds(137, 8, 121, 23);
		sashForm.setWeights(new int[] {334, 38 });
		
		
		//Ham goi action
		//createAction();
	}
	@Override
	public void setController(ControllerAnnotator controller) {
		// TODO Auto-generated method stub
		super.setController(controller);
	}
	/**
	 * @param listOfLists
	 * Do du lieu vao Jtable
	 */
	public void bindDataListTable(List<List> listOfLists) {
		frame.removeAll();
		table = creatJTable(listOfLists);
		frame.add(new JScrollPane(table));
		frame.setVisible(true);
		createAction();
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
		tableTemp.addMouseListener(new SelectionListener(tableTemp));
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
		
		//Cho nay se lay cac font dang CourierNewPSMT

		List listItems;
		for (int loopIndex = 0; loopIndex < listOfLists.size(); loopIndex++) {
			listItems = listOfLists.get(loopIndex);
			ToolParseDoc  toolParse = new ToolParseDoc();
			ArrayList<String> listsubContent = toolParse.getContentFont(listItems.get(1).toString(), "Courier");			
			content=toolParse.convertPlainText(listItems.get(1).toString());
			
			for (int j=0;j<listsubContent.size();j++){
				String subItem= listsubContent.get(j);
				
				try {
					if((subItem != null)&&(subItem != "")){
						model.addRow(new Object[] { Boolean.FALSE, content,
								listItems.get(5).toString(),
								listItems.get(4).toString(),
								listItems.get(2).toString(), subItem });
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e);
				}
			}
			if (listsubContent.size()==0){
				try{
					model.addRow(new Object[] { Boolean.FALSE,content, listItems.get(1).toString(),listItems.get(4).toString(), listItems.get(5).toString()});
				}catch (Exception e) {
					// TODO: handle exception
					System.out.println(e);
				}
			}
			
		}

		return model;
	}
	
	/*
	 * Ham nay duoc tao ra de test lay ra section cha cua paragrap
	 */
	public void findSectionParagrap(List<List> subList,List<List> paragraphData,List<List> sectionData){
		List items, itemPara;
		int indexofSublist=0,pageofSublist;
		String content;
		for(int i=0;i<subList.size();i++){
			items = subList.get(i);
			//lay index cua cai items nay			
			for (int j=0;j<paragraphData.size();j++){
				itemPara = paragraphData.get(j);
				if ((itemPara.get(3).equals(items.get(3)))&& (itemPara.get(4).equals( items.get(2)))) {
					indexofSublist = Integer.parseInt((String) itemPara.get(5));
				}
				if (indexofSublist != 0) {
					pageofSublist = Integer.parseInt((String) itemPara.get(4));					
					content= findParent(indexofSublist, pageofSublist,sectionData);
					//System.out.println(content);
					break;
				}
			}
			//tim trong page hoac 1 so page truoc no co section voi chi so index:thoa man > chi so index vua tim dc va be nhat
			
		}
		
		
	}
	
	private String findParent(int index,int page,List<List> sectionData){
		List itemSection;
		int flag=0,pageSection,indexSection ;
		String result="";
		boolean check = false;
		while(check==false){
			for(int i=0;i<sectionData.size();i++){
				itemSection   = sectionData.get(i);
				pageSection   = Integer.parseInt(itemSection.get(4).toString());
				indexSection  = Integer.parseInt(itemSection.get(5).toString());
				if(pageSection==page){
					if((indexSection<index)&&(indexSection>flag)){
						flag = indexSection;
						result= itemSection.get(1).toString();
						check = true;
						
					}
				}
			
			}
			page--;
		}
		
		return result;
		
	}
	
	public void createAction(){
		
		
		modelTable.addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				//Lay co
				int row 	= e.getFirstRow();
				int column  = e.getColumn();
				String content,sourceId,page;//document
				String componentDocumentId,typeSource;
				TableModel model = (TableModel) e.getSource();				
				Object data = model.getValueAt(row, column);
				content			 =  (model.getValueAt(row, column+1)).toString();
				componentDocumentId	 =  (model.getValueAt(row, column+3)).toString();
				sourceId			 =  (model.getValueAt(row, column+5)).toString();
				
				if (data.equals(true)) {					
					page			 =  (model.getValueAt(row, column+4)).toString();				
					addListAnnotation(content,componentDocumentId,page,sourceId);
					System.out.println("Add to list");
				}else{
					System.out.println("Remove to list");
					removeListAnnotation(content,componentDocumentId);
				}
			}
		});
		//Button Close cho form
		closeButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				nextButton.getShell().dispose();
			}
		});		
		
		//Action cho button next
		nextButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				
				checkCourie();
				
			}
		});		
		
		
		
	}
		
	private void removeListAnnotation(String content, String componentDocumenId) {
		// TODO Auto-generated method stub
		List listItem;
		int findId = -1;
		for (int i = 0; i < listResultData.size(); i++) {
			listItem = listResultData.get(i);
			if ((listItem.get(0).equals(content))
					&& ((listItem.get(2)).equals(componentDocumenId))) {
				findId = i;
				break;
			}
		}
		if (findId != -1) {
			listResultData.remove(findId);
		}
	}

	private void addListAnnotation(String content,String componentDocumentId, String page, String sourceId) {

		ArrayList<String> listOfData = new ArrayList<String>();

		listOfData.add(content);
		listOfData.add(componentDocumentId);
		listOfData.add(page);
		listOfData.add(sourceId);
		listResultData.add(listOfData);
	}
	
	
	/**
	 * Goi ham gui len server 
	 */
	public void checkCourie(){
		List listItem;
		
		List<List> listDataSource = new ArrayList<List>();
		String content, page, strCourie,idDocument;
		//B1: Lay ra currie viet cau truy van tiem kiem trong ontology, so sanh dang text de lay ra id, class
		for(int i=0; i<listResultData.size();i++){
			listItem = listResultData.get(i);
			content		= listItem.get(0).toString();
			idDocument	= listItem.get(1).toString();
			page    	= listItem.get(2).toString();
			strCourie   = listItem.get(3).toString();
			
			List<List> listIdSource = matchCurie(strCourie);
			for(int j=1;j<listIdSource.size();j++){
				listItem= listIdSource.get(j);
				List<String> subList = new ArrayList<String>();
				subList.add(content);
				subList.add(idDocument);
				subList.add(page);
				subList.add(strCourie);
				subList.add(listItem.get(0).toString());//Day la Id cua source;
				subList.add(listItem.get(1).toString());//Day la kieu cua source;
				listDataSource.add(subList);
			}
			//Add vao list
			
		}
		
		//B2: Cho ra 1 bang cho phep user chon., ung voi truong hop co roi
		//Open shell
		Shell shell = new Shell(closeButton.getShell());
		shell.setText("Relation doc-source table");
		shell.setImage(Images.imageRegistry.get(Images.DOCUMENT));
		shell.setLayout(new FillLayout());
		shell.setSize(920,400);
		
		int x=Math.max(shell.getDisplay().getCursorLocation().x-shell.getSize().x, 0);
		int y=Math.max(shell.getDisplay().getCursorLocation().y-shell.getSize().y, 0);
		shell.setLocation(x,y);
		compositePopupViewMatchCurie = new CompositePopupViewMatchCurie(shell,SWT.NONE,1);
		compositePopupViewMatchCurie.bindDataListTable(listDataSource);
		compositePopupViewMatchCurie.setController(this.getController());
		shell.addListener(SWT.Close, new Listener() {
			public void handleEvent(Event e) {	
				
				compositePopupViewMatchCurie.dispose();
			}
		});
		
		shell.open();
		
		//this.getShell().dispose();
		
	}
	
	public List<List> matchCurie(String strCourie){
		List<List> listIdSource = new ArrayList<List>();
	
		String[] splitArray = strCourie.split("\\.");
		for(int i=0; i<splitArray.length;i++){
			System.out.println(splitArray[i].toString());
			String contentCourie = splitArray[i].toString();
			contentCourie=contentCourie.trim();
			//Thuc hien cau truy van
			String queryString = ConsistentOntology.prefix
			+ "\n SELECT ?X ?directType"
			+ "\n WHERE " 
			+ "\n {" 
			+"\n ?X rdf:type SEC:SoftwareComponent."
			+"\n ?X  SEC:hasDirectType ?directType."
			+"\n ?X rdfs:label ?label ."
			+"\n filter (fn:contains(str(?X),'"+contentCourie+"')||fn:contains(str(?label),'"+contentCourie+"'))."			
			+ "\n}";
			
			System.out.println(queryString);		
		    List<ws.data.ArrayListData> result = Service.dataServiceDelegate.sparqlResultList(null, queryString);
		    for (int j = 0; j < result.size(); j++){
		    	List<String> items = result.get(j).getData();
		    	String idSource 	= items.get(0).toString();
		    	String typeSource   = items.get(1).toString();
		    	List<String> subList = new ArrayList<String>();
		    	subList.add(idSource);
		    	subList.add(typeSource);
		    	listIdSource.add(subList);
		    }
		}
		
		return listIdSource;
	}

}
