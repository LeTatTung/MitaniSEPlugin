package hut.composite.popup;

import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import hut.annotation.AnnotationAction;
import hut.composite.document.CompositeAnnotatorSuper;
import hut.composite.document.ControllerAnnotator;
import hut.composite.jtable.SelectionListener;
import hut.composite.jtable.TextAreaRenderer;

import mintani.valueconst.ConsistentOntology;
import mintani.valueconst.ConstValue;

import ontology.images.Images;

import org.eclipse.jdt.internal.ui.dialogs.DialogsMessages;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.swtdesigner.SWTResourceManager;

import service.Service;
import tool.parse.doc.ToolParseDoc;
import ws.data.NodeType;

public class CompositePopupResult extends CompositeAnnotatorSuper {
	private DefaultTableModel modelTable;
	private JTable table;
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private  Frame frame;
	private String[] columnNames = { "Choice","Content","Property", "Source Name", "Type","Page","Items","Document ID","Label","Sourcecode ID" };
	private int type;	
	private String property;
	private List<List> listResultData = new ArrayList<List>();
	private Button saveAnnotationButton;
	private Button closeButton;
	private ControllerAnnotator controller;
	
	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public CompositePopupResult(Composite parent, int style, int type, String property) {
		super(parent, style);
		setLayout(new FillLayout());
		this.type=type;
		this.property = property;
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		final SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		toolkit.adapt(sashForm, true, true);

		final Composite compositRelativeTable = new Composite(sashForm, SWT.EMBEDDED);
		compositRelativeTable.setLayout(new FillLayout());
		toolkit.adapt(compositRelativeTable);

		frame = SWT_AWT.new_Frame(compositRelativeTable);

		
		final Composite compositeWrapAction = new Composite(sashForm, SWT.NONE);
		compositeWrapAction.setCapture(true);
		toolkit.adapt(compositeWrapAction);

		saveAnnotationButton = new Button(compositeWrapAction, SWT.NONE);
		saveAnnotationButton.setCapture(true);
		saveAnnotationButton.setImage(Images.imageRegistry.get(Images.SAVE));
		toolkit.adapt(saveAnnotationButton, true, true);
		saveAnnotationButton.setText("Save Annotation");
		saveAnnotationButton.setBounds(10, 0, 116, 28);
		

		closeButton = new Button(compositeWrapAction, SWT.NONE);
		closeButton.setCapture(true);
		closeButton.setImage(Images.imageRegistry.get(Images.CLOSE_COMPOSITE));
		toolkit.adapt(closeButton, true, true);
		closeButton.setText("Close");
		closeButton.setBounds(132, 0, 101, 28);
		sashForm.setWeights(new int[] {321, 51 });
		
	}
	
	/**
	 * Ham bat su kien khi chon cac button 
	 */
	public void registerAction(){
		saveAnnotationButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				sendToServer();//Gui len server
				controller.refreshRelationProperties();
				//cap nhat bang lien ket code doc o phia duoi
				closeButton.getShell().dispose();
				//controller.getCompositeDocComponent().getOpenSourceToolItem().setEnabled(true);
				controller.getCompositeDocComponentImage().getOpenSourceToolItem().setEnabled(true);
				controller.getCompositeDocComponentParagraph().getOpenSourceToolItem().setEnabled(true);
				controller.getCompositeDocComponentSection().getOpenSourceToolItem().setEnabled(true);
			}
		});
		
		closeButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				saveAnnotationButton.getShell().dispose();
			}
		});	
		modelTable.addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				//Lay co
				int row 	= e.getFirstRow();
				int column  = e.getColumn();
				String content,label,documentId,page, relationProperty;//document
				String sourceId,typeSource;
				TableModel model = (TableModel) e.getSource();				
				Object data = model.getValueAt(row, column);
				content			 =  (model.getValueAt(row, column+1)).toString();
				sourceId		 =  (model.getValueAt(row, column+9)).toString();
				
				if (data.equals(true)) {
					label			 =  (model.getValueAt(row, column+8)).toString();
					documentId			 =  (model.getValueAt(row, column+7)).toString();
					page			 =  (model.getValueAt(row, column+5)).toString();
					typeSource		 =  (model.getValueAt(row, column+4)).toString();
					relationProperty =  (model.getValueAt(row, column+2)).toString();
					addListAnnotation(content, label, documentId, page, sourceId, typeSource, relationProperty);
					System.out.println("Add to list");
				}else{
					removeListAnnotation(content,sourceId);
				}
			}

			

			
		});
		
	
		
		
	}
	
	
	/**
	 * Goi ham gui len server 
	 */
	public void sendToServer(){
		for(int i=0; i<listResultData.size(); i++){
			ArrayList<String> itemRelationData = (ArrayList<String>)listResultData.get(i);
			String documentId = itemRelationData.get(2);
			String sourceCodeId = itemRelationData.get(4);
			String docPropertyId = ConsistentOntology.DOC_NAMESPACE+itemRelationData.get(6);
			/*System.out.println(docPropertyId);*/
			
			
			// Them thuoc tinh docProperty = sourceCodeId cho individual co Id documentId
			// ghi vao model chung
			Service.webServiceDelegate.addObjectProperty(null, docPropertyId, sourceCodeId, documentId);
			// ghi vao file owl cua source tuong ung
			
		}
		/*AnnotationAction annotationAction = new AnnotationAction();
		 annotationAction.saveAnnotationManual(listResultData,type,this.getCurrentBookmark());*/
		
	}
	
	
	private void removeListAnnotation(String documentId, String sourceId) {
		// TODO Auto-generated method stub
		List listItem;
		int findId=-1;
		for(int i=0;i<listResultData.size();i++){
			listItem = listResultData.get(i);
			if((listItem.get(2).equals(documentId))&&((listItem.get(4)).equals(sourceId))){
				findId=i;
				break;
			}
		}
		if( findId !=-1){
			listResultData.remove(findId);
		}
	}
	
	private void addListAnnotation(String content, String label,String documentId, String page, String sourceId,String typeSource, String relationProperty){
		
		ArrayList<String> listOfData = new ArrayList<String>();
		
		listOfData.add(content);
		listOfData.add(label);
		listOfData.add(documentId);
		listOfData.add(page);
		listOfData.add(sourceId);
		listOfData.add(typeSource);
		listOfData.add(relationProperty);
		listResultData.add(listOfData);
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
		registerAction();
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
		tableTemp.getColumnModel().getColumn(2).setCellRenderer(new TextAreaRenderer());
		tableTemp.addMouseListener(new SelectionListener());
		//Set lai do rong cho Jtable
		//tableTemp.setBounds(100, 100, 300, 600);
		setWidth(0, 15, tableTemp);
		setWidth(1, 200, tableTemp);
		setWidth(2, 100, tableTemp);
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
		String property ="";
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
		
		ToolParseDoc toolParse = new ToolParseDoc();
		List listItems;
		for (int loopIndex = 0; loopIndex < listOfLists.size(); loopIndex++) {
			listItems = listOfLists.get(loopIndex);
			
			content = toolParse.convertPlainText(listItems.get(1).toString());
			try {
				// choice,content,sourcodelablel,typesourcecode,page,stt,id,label,sourcecodeid
				String typeSource = listItems.get(8).toString();
				model.addRow(new Object[] { 
							Boolean.FALSE,content,this.property.substring(ConsistentOntology.DOC_NAMESPACE.length()), listItems.get(2).toString(),typeSource,listItems.get(3).toString(),listItems.get(4).toString(),
							listItems.get(5).toString(),listItems.get(6).toString(),listItems.get(7).toString()
							
				});
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}

		}

		return model;
	}
	
	
	
	/**
	 * Goi tu ben ngoai vao
	 */
	public void buildListRelation(){


		List<List>  listResultAnnotation =  new ArrayList<List>();
		
		List listDocItems,listSourceItems;
		List<List> listDocumentComponent = null ;
    	
		if(type==ConstValue.IMAGE){
			listDocumentComponent = (List<List>)this.getController().popupBindImageData();
		}
		if(type==ConstValue.PARAGRAPH){
			listDocumentComponent = (List<List>)this.getController().popupBindParagraphData();
		}
		if(type==ConstValue.SECTION){
			 listDocumentComponent = (List<List>)this.getController().popupBindSectionData();
		}
		List<List> listSourceComponent = (List<List>)this.getController().popupBindResultData();
		
		//List sourcecode:type,sourcecodeid,sourcelabel,type sourcecode
		//List document:type,content,page,stt,id,label
		//Tao 1 list co kieu nhu sau: type,content,sourcodelablel,page,stt,id,label,sourcecodeid
		for(int i=0;i<listDocumentComponent.size();i++){
			listDocItems = listDocumentComponent.get(i);
			for(int j=0;j<listSourceComponent.size();j++){
				listSourceItems = listSourceComponent.get(j); 
				//Add vao listResult
				ArrayList<String> listResultItem= new ArrayList<String>();
				listResultItem.add((String) listSourceItems.get(0));//Type
				listResultItem.add((String) listDocItems.get(1));   //1:Content of source  code component
				listResultItem.add((String) listSourceItems.get(2));//2 Label Source code component
				listResultItem.add((String) listDocItems.get(2));   //3 trang chua item do
				listResultItem.add((String) listDocItems.get(3));   //4 so thu item trong trang do
				listResultItem.add((String) listDocItems.get(4));   //5 id cua item document
				listResultItem.add((String) listDocItems.get(5));    //6 label cua item document
				listResultItem.add((String) listSourceItems.get(1)); //7 id cua thanh phan source code
				listResultItem.add((String) listSourceItems.get(3)); //8 Kieu cau source code theo dang String "CLASS"
				listResultAnnotation.add(listResultItem);
			}
		}
		bindDataListTable(listResultAnnotation);
		
		
		
		System.out.println("Build Relation");
		
	}

	public ControllerAnnotator getController() {
		return controller;
	}

	public void setController(ControllerAnnotator controller) {
		this.controller = controller;
	}

}
