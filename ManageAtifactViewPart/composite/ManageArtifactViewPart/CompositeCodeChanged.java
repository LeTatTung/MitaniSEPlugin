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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;

import service.Service;
import tool.parse.doc.ToolParseDoc;
import ws.owl.ClassData;

import com.swtdesigner.SWTResourceManager;

public class CompositeCodeChanged extends SuperComposite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private ToolBar toolBar;
	Composite composite_2;
	private Frame frame;
	private JTable table;
	private DefaultTableModel modelTable;
	boolean choseDicardedCodeToDisplay;
	private String[] columnNames = { "Delete","Name", "Description", "Changed By", "Change Of", "Changed At", "Is Discarded", "Related Document" };
	private List<List> listFullData;
	List<List> listOfList = new ArrayList<List>();

	public CompositeCodeChanged(Composite parent, int style) {


		super(parent, style);
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
				innitData(choseDicardedCodeToDisplay);
			}
		});
		toolItemRefresh.setImage(SWTResourceManager.getImage(CompositeTest.class, "/ontology/images/refresh.gif"));
		toolItemRefresh.setText("refresh");

		final ToolItem toolItemDelete = new ToolItem(toolBar, SWT.PUSH);
		toolItemDelete.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				deleteCodeChange();
			}


		});
		toolItemDelete.setText("Delete");
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

		final Button codeDiscardedButton = new Button(composite_3, SWT.NONE);
		codeDiscardedButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				choseDicardedCodeToDisplay = true;
				innitData(choseDicardedCodeToDisplay);
			}
		});
		toolkit.adapt(codeDiscardedButton, true, true);
		codeDiscardedButton.setText("Codes Discarded");
		codeDiscardedButton.setBounds(253, 0, 101, 30);

		final Button codeNotDiscardedButton = new Button(composite_3, SWT.NONE);
		codeNotDiscardedButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				choseDicardedCodeToDisplay = false;
				innitData(choseDicardedCodeToDisplay);
			}
		});
		toolkit.adapt(codeNotDiscardedButton, true, true);
		codeNotDiscardedButton.setText("Codes Not Discarded");
		codeNotDiscardedButton.setBounds(115, 0, 120, 30);
		sashForm.setWeights(new int[] {43, 284, 42 });


		innitData(false);
	}

	public void innitData(boolean choseDicardedCodeToDisplay){

		List<List> listOfResult = new ArrayList<List>();


	    List<String> listCodeChange = Service.webServiceDelegate.listClassInstance(null, ConsistentOntology.CODE_CHANGE);
	    for(String codeChangeURI: listCodeChange){
	    	List<String> subList = new ArrayList<String>();

	    	String  codeChangeName = "";
	    	List<String> listCodeName = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, codeChangeURI, ConsistentOntology.HAS_NAME);
	    	if(listCodeName.size()==0){
	    		codeChangeName += " " + codeChangeURI.substring(codeChangeURI.lastIndexOf("#")+1)+"\n";
	    	}
	    	else{
	    		for(String s:listCodeName){
		    		codeChangeName += " "+s +"\n";
		    	}
	    	}


	    	String  description = "";
	    	for(String s:Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, codeChangeURI, ConsistentOntology.HAS_DESCRIPTION)){
	    		description += " "+s +"\n";
	    	}

	    	String  developer = "";
	    	String  developerURI = "";
	    	List<String> listDeveloper = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, codeChangeURI, ConsistentOntology.CODE_CHANGED_BY);
	    	//-- xu ly cho truong hop test ko co tester luc chon filter la All Tester
	    	if(listDeveloper.size()==0){
	    		developerURI = "\n";
	    	}
	    	for(String s:listDeveloper){
	    		List<String> listName = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, s, ConsistentOntology.HAS_NAME);

	    		if(listName.size()==0){
	    			developer += " "+ s.substring(s.lastIndexOf("#")+1) + "\n";
	    			// Neu khong co ten thi kiem tra co Full Name khong
	    			listName = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, s, ConsistentOntology.HAS_FULL_NAME);
	    			if(listName.size()!=0) developer += " "+listName.get(0) + "\n";
	    		}
	    		else{
	    			developer += " "+listName.get(0) + "\n";
	    		}
	    		developerURI += s + "\n";
	    	}

	    	String  componentChanged = "";
	    	String  componentURI = "";
	    	List<String> listcomponent = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, codeChangeURI, ConsistentOntology.CODE_CHANGED_OF);

	    	for(String s:listcomponent){
	    		List<String> listName = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, s, ConsistentOntology.HAS_NAME);

	    		if(listName.size()==0){
	    			componentChanged += " "+ s.substring(s.lastIndexOf("#")+1) + "\n";
	    		}
	    		else{
	    			componentChanged += " "+listName.get(0) + "\n";
	    		}
	    		componentURI += s + "\n";
	    	}
	    	// Document lien quan den Code Component cua Code Change
	    	// String  relatedDocument = ""; Tam thoi chua dung vi document it co ten
	    	String  relatedDocumentURI = "";

	    	for(String s:listcomponent){
	    		List<String> listRelatedDocument = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, s, ConsistentOntology.CODE_RELATED_TO_DOC);

	    		for(String r:listRelatedDocument){

		    		relatedDocumentURI += r + "\n";
		    	}

	    	}




	    	String  changedAt = "";
	    	for(String s:Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, codeChangeURI, ConsistentOntology.CODE_CHANGED_AT)){
	    		changedAt += " "+s +"\n";
	    	}
	    	//private String[] columnNames = { "Delete","Name", "Description", "Changed By", "Change Of", "Changed At", "Is Discarded" };
	    	String  isDiscarded = "";
	    	boolean isBooleanDiscarded = false;
	    	List<String> list = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, codeChangeURI, ConsistentOntology.IS_DISCARDED);
	    	if(list.size() >0 ){
	    		String boolValue = list.get(0);
	    		isBooleanDiscarded = Boolean.parseBoolean(list.get(0));
		    	if(boolValue.equals("1") || boolValue.equals("true")  || boolValue.equals("True")){
		    		isBooleanDiscarded = true;
		    		isDiscarded = "Yes";
		    	}
		    	else{
		    		isBooleanDiscarded = false;
		    		isDiscarded = "No";
		    	}
	    	}

	    	if(isBooleanDiscarded == choseDicardedCodeToDisplay){
		    	subList.add(codeChangeName);
		    	subList.add(description);
		    	subList.add(developer);
		    	subList.add(componentChanged);
		    	subList.add(changedAt);
		    	subList.add(isDiscarded);
		    	subList.add(relatedDocumentURI);
		    	subList.add(codeChangeURI);
		    	subList.add(developerURI);

		    	listOfResult.add(subList);
	    	}


	    }
	    this.listOfList = listOfResult;
	    bindDataListTable(listOfResult);
	}

	/**
	 * @param listOfLists
	 * Do du lieu vao Jtable
	 */
	public void bindDataListTable(List<List> listOfLists) {
		//table.removeAll();
		frame.removeAll();
		table = creatJTable(listOfLists);

		this.listFullData = listOfLists;
		frame.add(new JScrollPane(table));
		//this.creatAction();	=> thay khong can thiet tao ham nay
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

		//Set lai do rong cho Jtable
		setWidth(0, 20, tableTemp);
		setWidth(1, 50, tableTemp);
		setWidth(2, 250, tableTemp);
		setWidth(3, 50, tableTemp);
		setWidth(4, 20, tableTemp);
		tableTemp.addMouseListener(new MouseAdapter(){
		     public void mouseClicked(MouseEvent e){
		      if (e.getClickCount() > 1){
		    	 final String fullURI = (String)listOfList.get(((JTable)e.getSource()).getSelectedRow()).get(7);

		    	 PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
		 			public void run() {
		 				try {
		 					Shell shell = new Shell(PlatformUI.getWorkbench().getDisplay());
							shell.setText("Edit Code Change !");
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
									innitData(choseDicardedCodeToDisplay);
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
			model.addRow(new Object[] {  false, listItems.get(0).toString(),content, listItems.get(2).toString(),listItems.get(3).toString(), listItems.get(4).toString(), listItems.get(5).toString(),listItems.get(6).toString() });
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

	private void deleteCodeChange(){
		for(int i=0;i<table.getRowCount();i++){
			boolean choosenToDelete = Boolean.parseBoolean(table.getValueAt(i, 0).toString()) ;
			if(choosenToDelete){
				String fullURIofTest = (String)listOfList.get(i).get(6);
				Service.webServiceDelegate.removeIndividual(null, fullURIofTest);
			}
		}
		innitData(choseDicardedCodeToDisplay);
		MessageDialog.openInformation(new Shell(), "Notice", "Delete code Change(s) succesfully !");
	}

	@Override
	int updateInterface() {
		// TODO Auto-generated method stub
		return 0;
	}

}
