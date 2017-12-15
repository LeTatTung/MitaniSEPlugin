package hut.composite.test;


import hut.annotation.AnnotationAction;
import hut.composite.jtable.SelectionListener;
import hut.composite.jtable.TextAreaRenderer;
import hut.composite.popup.CompositePopupAnnotation;
import hut.composite.popup.CompositePopupSourceCode;
import hut.doc.DataIntermediateDoc;

import mintani.valueconst.ConstValue;
import java.awt.Frame;

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

public class CompositeTestComponent extends CompositeAnnotatorSuper {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private DefaultTableModel modelTable;
	private JTable table;
	private String[] columnNames = { "Name", "Content", "Method Test", "Test For", "Tested At" };
	private Frame frame;
	private ToolBar toolBar;
	
	private int type;		
	private List<List> listFullData;//List nay chua raw text. table chi hien ra plain text cho phan content
	/**
	 * Create the composite
	 * @param parent 
	 * @param style
	 */
	public CompositeTestComponent(Composite parent, int style,int type) {
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
		sashForm.setWeights(new int[] {339, 33 });

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
			model.addRow(new Object[] { Boolean.FALSE,content, listItems.get(3).toString(),listItems.get(2).toString(), listItems.get(4).toString(),listItems.get(1).toString()});
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
		//tableTemp.addMouseListener(new SelectionListener(tableTemp));
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
	 * Tao cac action chung ca table lan cac button
	 */
	public void creatAction() {
		modelTable.addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				String content,lable,methodtest,testfor,idtest;
				int row = e.getFirstRow();
				int column = e.getColumn();
				TableModel model = (TableModel) e.getSource();				
				Object data = model.getValueAt(row, column);
				int countColumn= model.getColumnCount();
				if (data instanceof Boolean)
					System.out.println("Value changed in Row: " + row
							+ " Column: " + column + " New Value = " + data);
				if (data.equals(true)) {
					System.out.println("View detail");
					//Lay thong tin tu tabel
					lable		 = model.getValueAt(row, column+1).toString();//Lay thuoc trang bao nhieu
					content      =  model.getValueAt(row, column+2).toString();
					methodtest   =  model.getValueAt(row, column+3).toString();
					testfor 	 =  model.getValueAt(row, column+4).toString();
					idtest	 	 =  model.getValueAt(row, column+5).toString();
					//lay vi tri
					//Update vao mot list gom co vi tri,trang,noi dung
					//updateList(poisition, page, typetemp,tempContent,idImage,labelImage);
					
				} else {
					System.out.println("Remove to list");
				}
			}
		});
		
		
		
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
}
