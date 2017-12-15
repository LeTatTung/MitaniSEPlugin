package hut.composite.document;

import hut.composite.annotationCreatorAndEditor.CompositeListPropertiesOfInstance;
import hut.composite.jtable.SelectionListener;
import hut.composite.jtable.TextAreaRenderer;
import hut.model.annotationCreatorAndEditor.DataInstance;

import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import mintani.valueconst.ConsistentOntology;
import mintani.valueconst.ConstValue;

import ontology.images.Images;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import com.swtdesigner.SWTResourceManager;
import javax.swing.table.TableColumn;

import service.Service;
import tool.parse.doc.ToolParseDoc;
import ws.owl.ArrayListData;
import ws.owl.WebServiceDelegate;
import ws.owl.WebServiceService;

public class CompositeAnnotationResult extends CompositeAnnotatorSuper {
	private DefaultTableModel modelTable;
	private JTable table;
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
    private  Frame frame;
    private String[] columnNames = { "ID", "Content", "Page", "property", "Source Type","Source Label" ,"Created At"};
    String documentId;
    
	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public CompositeAnnotationResult(Composite parent, int style, String documentId) {
		super(parent, style);
		setLayout(new FillLayout());
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
		toolkit.adapt(compositeWrapAction);

		
		sashForm.setWeights(new int[] {334, 38 });
		
		//get Annotation by data from server
		getRelateToCode(documentId);
		this.documentId = documentId;
		
	}
	
	
	public void getRelateToCode(String documentId){
		List<List>  listResultRevice =  new ArrayList<List>();
		//Lay ra cac document element ma co lien he voi code
		DocumentRelateToCode relationProvider = new DocumentRelateToCode(documentId);
		ArrayList<String> listElementHasRelation = relationProvider.getRelation();
		
		// voi moi id cua documentElement lay duoc ta lay ra thong tin lien ket voi code
		for (String uriElement: listElementHasRelation)
		{
			//Lay ra lop cua element (co the la Section, Image hoac Paragraph)
			String uriClass = Service.webServiceDelegate.getClassOfIndividual(null, uriElement);
			/**
			 * Tiep den, lay ra lan luot cac thuoc tinh mong muon
			 */
			
			String content = "";//Co the co nhieu content
			for (String s:Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, uriElement, ConsistentOntology.DOCUMENT_ELEMENT_HAS_TEXT_CONTENT))
			{
				content += s+"\n";
			}
			
			
			String page = "";//Co the co nhieu page end, nhung chi lay 1
			page +=Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, uriElement, ConsistentOntology.DOCUMENT_ELEMENT_HAS_PAGE_END).get(0);
			
			
			/**
			 * Lay ra tat ca cac thanh phan code lien quan, co the lay name, uri, ...
			 */
			String relatedCode="";
			//Dau tien lay ra cac quan he co the co voi code
			List<String> lstProperties = Service.webServiceDelegate.getPropertiesOfClassByRange(null, uriClass, ConsistentOntology.SOFTWARE_COMPONENT);
			
			for (String property:lstProperties)
			{
				//Vi du lay ra localname + type
				for (String s:Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, uriElement, property))
				{
					// dung de luu du lieu cho mot dong
					ArrayList<String> relationProperties = new ArrayList<String>();
					
					relationProperties.add(uriElement);
					relationProperties.add(content);
					relationProperties.add(page);
					
					property=property.substring(property.indexOf("#") + 1);
					relationProperties.add(property);
					
					String tmp = Service.webServiceDelegate.getClassOfIndividual(null, s);
					tmp=tmp.substring(tmp.indexOf("#") + 1);
					relationProperties.add(tmp); // lay ra ten lop cua thanh phan source code
					
					s=s.substring(s.indexOf("#") + 1);
					relationProperties.add(s);   // lay ra label cua thanh phan source code
					
					
					
					relatedCode +=  property + "  " + s + "("+tmp+")\n";
					
					listResultRevice.add(relationProperties);
					
				}
			}
			//relationProperties.add(relatedCode);
			
			//Vd in ra manh hinh, tuong duong khi in ra bang
			System.out.println("-------------------------------------");
			uriElement = uriElement.substring(uriElement.indexOf("#") + 1);
			System.out.println("Element: " + uriElement);
			System.out.println("page: " + page);
			System.out.println("relation:" + relatedCode);
		
		}
		
		//Tam thoi chua goi webservice	
	    /*List<ws.data.ArrayListData> result = Service.dataServiceDelegate.sparqlResultList(null, query);*/
	   
	    bindDataListTable(listResultRevice);
		
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
	}
	
	/**
	 * @param listOfLists
	 * @return
	 * Tao Jtable voi model duoc lay
	 */
	public JTable creatJTable(final List<List> listOfLists) {
		modelTable = getModelTable(listOfLists);
		JTable tableTemp = new JTable(modelTable) {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				if (vColIndex == 0)
					return true;
				else
					return false;
			}
		};
		tableTemp.getColumnModel().getColumn(0).setCellRenderer(new TextAreaRenderer());
		tableTemp.addMouseListener(new MouseAdapter() {
			public void mouseClicked(final MouseEvent e) {
				if (e.getClickCount() > 1){ 
			    	 System.out.println("double click event");
			    	// final String fullURI = (String)?listOfList.get(((JTable)e.getSource()).getSelectedRow()).get(8);
			    	 final String fullURI = (String)listOfLists.get(((JTable)e.getSource()).getSelectedRow()).get(0);
			    	 System.out.println("fullURI = " + fullURI);
			    	 PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
			 			public void run() {
			 				try {
			 					final Shell shell = new Shell(PlatformUI.getWorkbench().getDisplay());
								shell.setText("Edit requirement !");
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
										getController().refreshRelationProperties();
										shell.dispose();
										//getRelateToCode(documentId);
									}
								});
								
			 				} catch (Exception e) {
			 					e.printStackTrace();
			 				}
			 			}
			 		});
			    	 
			    	 
			         }
			}
		});
		//Set lai do rong cho Jtable
		setWidth(0, 250, tableTemp);
		setWidth(1, 50, tableTemp);
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
			ToolParseDoc toolParse = new ToolParseDoc();
			//content = listItems.get(0).toString();
			try {
				model.addRow(new Object[] { 
						listItems.get(0).toString(),
						toolParse.convertPlainText(listItems.get(1).toString()),
						listItems.get(2).toString(),
						listItems.get(3).toString(),
						listItems.get(4).toString(),
						listItems.get(5).toString() });
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}

		}

		return model;
	}
	
	

}
