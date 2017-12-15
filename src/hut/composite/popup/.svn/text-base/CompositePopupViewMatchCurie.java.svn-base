package hut.composite.popup;

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

import hut.annotation.AnnotationAction;
import hut.composite.document.CompositeAnnotationResult;
import hut.composite.document.CompositeAnnotatorSuper;
import hut.composite.jtable.SelectionListener;
import hut.composite.jtable.TextAreaRenderer;

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
import org.eclipse.ui.forms.widgets.FormToolkit;

import tool.parse.doc.ToolParseDoc;

import com.swtdesigner.SWTResourceManager;

public class CompositePopupViewMatchCurie extends CompositeAnnotatorSuper {
	private DefaultTableModel modelTable;
	private JTable table;
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private String[] columnNames = { "Choice", "Content", "Id Document", "Page",
			 "Courie","SourceCode","Type" };
	private String[] columnNames2 = { "Choice", "Content", "Id Document", "Page",
			 "Courie"};
	private Frame frame;
	private Button saveButton;
	private Button closeButton;
	private int type;
	private List<List> listResultData = new ArrayList<List>();

	/**
	 * Create the composite
	 * 
	 * @param parent
	 * @param style
	 */
	public CompositePopupViewMatchCurie(Composite parent, int style, int type) {
		super(parent, style);
		setLayout(new FillLayout());
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);
		this.type = type;
		final SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		toolkit.adapt(sashForm, true, true);

		final Composite compositRelativeTable = new Composite(sashForm,
				SWT.EMBEDDED);
		compositRelativeTable.setLayout(new FillLayout());
		toolkit.adapt(compositRelativeTable);

		frame = SWT_AWT.new_Frame(compositRelativeTable);

		final Composite compositeWrapAction = new Composite(sashForm, SWT.NONE);
		toolkit.adapt(compositeWrapAction);

		saveButton = new Button(compositeWrapAction, SWT.NONE);
		saveButton.setBounds(286, 5, 123, 29);
		saveButton.setImage(Images.imageRegistry.get(Images.EXPORT_SERVICE));
		toolkit.adapt(saveButton, true, true);
		saveButton.setText("Save Annotation");

		closeButton = new Button(compositeWrapAction, SWT.NONE);
		closeButton.setBounds(415, 5, 61, 29);
		
		closeButton.setImage(Images.imageRegistry.get(Images.CLOSE_COMPOSITE));
		toolkit.adapt(closeButton, true, true);
		closeButton.setText("Close");

		sashForm.setWeights(new int[] { 334, 38 });
		//
	}

	/**
	 * @param listOfLists
	 *            Do du lieu vao Jtable
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
	 * @return Tao Jtable voi model duoc lay
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
		tableTemp.getColumnModel().getColumn(1).setCellRenderer(
				new TextAreaRenderer());
		tableTemp.addMouseListener(new SelectionListener(tableTemp));
		// Set lai do rong cho Jtable
		setWidth(0, 50, tableTemp);
		setWidth(1, 250, tableTemp);
		setWidth(2, 50, tableTemp);
		return tableTemp;
	}

	/**
	 * @param vColIndex
	 * @param vColWidth
	 * @param tableTemp
	 *            Set lai do rong cho cho cac cot
	 */
	private void setWidth(int vColIndex, int vColWidth, JTable tableTemp) {
		TableColumn col = tableTemp.getColumnModel().getColumn(vColIndex);
		col.setPreferredWidth(vColWidth);
	}

	/**
	 * @param listOfLists
	 * @return Tao model cho Jtable
	 */
	private DefaultTableModel getModelTable(List<List> listOfLists) {
		String content = "";
		DefaultTableModel model = new DefaultTableModel() {
			public Class<?> getColumnClass(int columnIndex) {
				if (getRowCount() > 0 && getValueAt(0, columnIndex) != null)
					return getValueAt(0, columnIndex).getClass();
				return super.getColumnClass(columnIndex);
			}
		};
		if(type==1){
			for (int col = 0; col < columnNames.length; col++) {
				model.addColumn(columnNames[col]);
			}
		}else{
			for (int col = 0; col < columnNames.length; col++) {
				model.addColumn(columnNames[col]);
			}
		}
		// Cho nay se lay cac font dang CourierNewPSMT

		List listItems;
		for (int loopIndex = 0; loopIndex < listOfLists.size(); loopIndex++) {
			listItems = listOfLists.get(loopIndex);
			ToolParseDoc toolParse = new ToolParseDoc();			
			content = toolParse.convertPlainText(listItems.get(0).toString());
			model.addRow(new Object[] { Boolean.FALSE, content,
							listItems.get(1).toString(),
							listItems.get(2).toString(),
							listItems.get(3).toString(),
							listItems.get(4).toString(),
							listItems.get(5).toString()});
		}

		return model;
	}

	public void createAction() {
		
		modelTable.addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				// Lay co
				int row = e.getFirstRow();
				int column = e.getColumn();
				String content, sourceId, page;// document
				String componentDocumentId, typeSource;
				String label, documentId;
				TableModel model = (TableModel) e.getSource();
				Object data = model.getValueAt(row, column);
				content = (model.getValueAt(row, column + 1)).toString();
				sourceId = (model.getValueAt(row, column + 5)).toString();
				documentId		 =  (model.getValueAt(row, column+2)).toString();
				if (data.equals(true)) {
					label			 =  (model.getValueAt(row, column+2)).toString();					
					page			 =  (model.getValueAt(row, column+3)).toString();
					typeSource		 =  (model.getValueAt(row, column+6)).toString();
					
					addListAnnotation(content,label,documentId,page,sourceId,typeSource);
					System.out.println("Add to list");
				} else {
					System.out.println("Remove to list");
					removeListAnnotation(content, documentId);
				}
			}
		});
		// Button Close cho form
		closeButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				saveButton.getShell().dispose();
			}
		});

		// Action cho button next
		saveButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				sendToServer();//Gui len server
				closeButton.getShell().dispose();
			}
		});

	}
	
	/**
	 * Goi ham gui len server 
	 */
	public void sendToServer(){
		String currentfilepath="";
		AnnotationAction annotationAction = new AnnotationAction();
		annotationAction.saveAnnotationManual(listResultData,type,currentfilepath);
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

	private void addListAnnotation(String content, String label,String componentDocumentId,
			String page, String sourceId,  String typeSource) {

		ArrayList<String> listOfData = new ArrayList<String>();

		listOfData.add(content);
		listOfData.add(label);
		listOfData.add(componentDocumentId);
		listOfData.add(page);
		listOfData.add(ConsistentOntology.SEC_NAMESPACE+sourceId);
		listOfData.add(typeSource);
		listResultData.add(listOfData);
	}

}
