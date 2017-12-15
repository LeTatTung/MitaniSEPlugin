package hut.composite.popup;

import hut.composite.document.CompositeAnnotatorSuper;
import hut.composite.document.ControllerAnnotator;

import hut.composite.tree.TreeNodeData;

import java.util.ArrayList;
import java.util.List;

import mintani.valueconst.ConsistentOntology;
import mintani.valueconst.ConstValue;
import ontology.images.Images;


import org.eclipse.core.runtime.IAdaptable;

import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;

import service.Service;

import Consistent.Consistent;

import com.swtdesigner.SWTResourceManager;


import ws.data.NodeData;


public class CompositePopupSourceCode extends CompositeAnnotatorSuper {
	

	private Combo combo;
	private Tree tree;
	private CheckboxTreeViewer  checkboxTreeViewer;
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private TreeParent invisibleRoot;
	private int type;
	private List<String> listProperty;
	private String propertySelected;
	private Button choiceButton;
	private CompositePopupResult  compositePopupResult;

	/**
	 * Create the composite
	 * @param parent
	 * @param style: Kiem tra xem du lieu dang xet la loai gi : Image hay Paragraph hay Section
	 */
	public CompositePopupSourceCode(Composite parent,int style,int type) {
		
		super(parent, style);	
		this.type= type;
		setLayout(new FillLayout());
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);		
		
		

		final Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new FillLayout());
		toolkit.adapt(composite);

		final SashForm sashForm = new SashForm(composite, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		toolkit.adapt(sashForm, true, true);

		checkboxTreeViewer = new CheckboxTreeViewer(sashForm, SWT.BORDER);
		checkboxTreeViewer.setContentProvider(new TreeContentProvider());
		checkboxTreeViewer.setLabelProvider(new TreeLabelProvider());
		tree = checkboxTreeViewer.getTree();
		toolkit.adapt(tree, true, true);

		final Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new RowLayout());
		toolkit.adapt(composite_1);


		//combo.setItem(i, listProperty.get(i));
		
		final CLabel relationPropertyLabel = new CLabel(composite_1, SWT.NONE);
		relationPropertyLabel.setImage(Images.imageRegistry.get(Images.METRIC));
		toolkit.adapt(relationPropertyLabel, true, true);
		relationPropertyLabel.setText("relation property");

		ComboViewer comboViewer = new ComboViewer(composite_1, SWT.BORDER);
		combo = comboViewer.getCombo();
		
		this.listProperty = new ArrayList<String>();
		if (type==ConstValue.IMAGE){
			listProperty = Service.webServiceDelegate.getPropertiesOfClassByRange(null, ConsistentOntology.IMAGE, ConsistentOntology.SOFTWARE_COMPONENT);
		}
		else if( type==ConstValue.PARAGRAPH){
			listProperty = Service.webServiceDelegate.getPropertiesOfClassByRange(null, ConsistentOntology.PARAGRAPH, ConsistentOntology.SOFTWARE_COMPONENT);
		}
		else {
			listProperty = Service.webServiceDelegate.getPropertiesOfClassByRange(null, ConsistentOntology.SECTION, ConsistentOntology.SOFTWARE_COMPONENT);
		}

		String[] listItem = new String[listProperty.size()];
		for(int i=0; i<listItem.length; i++){
			listItem[i] = listProperty.get(i).substring(ConsistentOntology.DOC_NAMESPACE.length());
		}
		
		combo.setItems(listItem);
		
		
		final RowData rd_combo = new RowData();
		rd_combo.width = 123;
		combo.setLayoutData(rd_combo);
		toolkit.adapt(combo, true, true);

		choiceButton = toolkit.createButton(composite_1, "Select", SWT.NONE);
		choiceButton.setImage(Images.imageRegistry.get(Images.RUN));
		
		choiceButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				creatActionRelation();
			}
		});
		sashForm.setWeights(new int[] {334, 38 });
		
		
		
		creatTreeView();
		
		//Ham goi action
		registerAction();
		
		
	}
	
	@Override
	public ControllerAnnotator getController() {
		// TODO Auto-generated method stub
		return super.getController();
	}
	
	public void creatTreeView() {	
		creatTree();
		//treeViewer.setInput(invisibleRoot);
		
	}
	
	public void creatTree() {		
		JobPopupSourceCode jobPopupSourceCode = new JobPopupSourceCode("Build sourcetree",checkboxTreeViewer);		
//		jobPopupSourceCode.setUser(true);
		jobPopupSourceCode.schedule();	
		
	}
	
	public void registerAction(){		
		
		//Bat su kien khi checkbox
		checkboxTreeViewer.addCheckStateListener(new ICheckStateListener() {
			  public void checkStateChanged(CheckStateChangedEvent event) {
				  if (event.getChecked()) {
			          // Tao su kien check cho tat ca thanh phan ben trong
					  //checkboxTreeViewer.setSubtreeChecked(event.getElement(), true);
					  //Add vao list
					  	TreeObject tp = getSelectedNode();
						TreeNodeData node = (TreeNodeData) tp.getData();
						System.out.println(((TreeNodeData) node).toString(1));
					   
				  }
				  else{
					  System.out.println("Remove checked");
				  }
			  }
		});
	}
	
	// Xu ly su kien chon mot node

	public TreeObject getSelectedNode() {
		ISelection selection = checkboxTreeViewer.getSelection();
		TreeObject obj = (TreeObject) ((IStructuredSelection) selection).getFirstElement();
		return obj;
	}
	
	public void creatActionRelation(){
		String propertySelected = listProperty.get(combo.getSelectionIndex());
		
		List<List>  listSourceAnnotation =  new ArrayList<List>();
		
		Object[] checkitems = checkboxTreeViewer.getCheckedElements();
		
		
		List<List> test = (List<List>) this.getController().popupBindImageData();
		
		
		for (Object object : checkitems) {
			TreeObject obj = (TreeObject)object;			
			TreeNodeData node = (TreeNodeData) obj.getData();
			ArrayList<String> listSourceItem= new ArrayList<String>();
			listSourceItem.add(node.getType());
			listSourceItem.add(node.getId());
			listSourceItem.add(node.getName());
			listSourceItem.add(node.getTypeSource());
			listSourceAnnotation.add(listSourceItem);
			
		}
		
		//Tac dong vao lop controller
		this.setInputData(listSourceAnnotation);
		this.getController().setCompositePopupSourceCode(this);
		this.getController().popupBindResultData();
		
		
		
		Shell shell = new Shell(choiceButton.getDisplay());
		shell.setText("Relation table");
		shell.setImage(Images.imageRegistry.get(Images.COPY));
		shell.setLayout(new FillLayout());
		shell.setSize(820,280);
		
		int x=Math.max(shell.getDisplay().getCursorLocation().x-shell.getSize().x, 0);
		int y=Math.max(shell.getDisplay().getCursorLocation().y-shell.getSize().y, 0);
		shell.setLocation(x,y);
		//Open them 1 shell nua hieb thi ket qua de nguoi dung chon sau do luu vao listAnnotation gui len server
		
		compositePopupResult  = new CompositePopupResult(shell,SWT.NONE,type, propertySelected);
		compositePopupResult.setController(this.getController());
		compositePopupResult.setCurrentPathFile(this.getCurrentPathFile());
		compositePopupResult.buildListRelation();
		shell.addListener(SWT.Close, new Listener() {
			public void handleEvent(Event e) {
				compositePopupResult.getController().getCompositeDocComponentImage().getOpenSourceToolItem().setEnabled(true);
				compositePopupResult.getController().getCompositeDocComponentParagraph().getOpenSourceToolItem().setEnabled(true);
				compositePopupResult.getController().getCompositeDocComponentSection().getOpenSourceToolItem().setEnabled(true);
				compositePopupResult.dispose();
			}
		});
		
		shell.open();
		//Dong lai shell cu lai 
		choiceButton.getShell().dispose();
	}
	


}


/**
 * @author KienCuong
 * Class lien quan toi cay thu muc, can viet tach roi ra
 */
class TreeObject implements IAdaptable {
	private String name;
	private Object data;
	private TreeParent parent;

	public TreeObject(Object data, TreeParent parent) {
		this.data = data;
		this.parent = parent;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setParent(TreeParent parent) {
		this.parent = parent;
	}

	public TreeParent getParent() {
		return parent;
	}

	public String toString() {
		return this.data.toString();
	}

	public Object getAdapter(Class key) {
		return null;
	}
}

class TreeParent extends TreeObject {
	private ArrayList children;

	public TreeParent(Object data, TreeParent parent) {
		super(data, parent);
		children = new ArrayList();
	}

	public void addChild(TreeObject child) {
		children.add(child);
		child.setParent(this);
	}

	public void removeChild(TreeObject child) {
		children.remove(child);
		child.setParent(null);
	}

	public TreeObject[] getChildren() {
		return (TreeObject[]) children.toArray(new TreeObject[children.size()]);
	}

	public boolean hasChildren() {
		return children.size() > 0;
	}

}

class TreeContentProvider implements IStructuredContentProvider,
		ITreeContentProvider {
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	public void dispose() {
	}

	public Object[] getElements(Object parent) {
		return getChildren(parent);
	}

	public Object[] getChildren(Object parent) {
		if (parent instanceof TreeParent) {
			return ((TreeParent) parent).getChildren();
		}
		return new Object[0];
	}

	public Object getParent(Object child) {
		if (child instanceof TreeObject) {
			return ((TreeObject) child).getParent();
		}
		return null;
	}

	public boolean hasChildren(Object parent) {
		if (parent instanceof TreeParent)
			return ((TreeParent) parent).hasChildren();
		return false;
	}
}

class TreeLabelProvider extends LabelProvider {
	public String getText(Object element) {

		TreeObject tp = (TreeObject) element;
		TreeNodeData node = (TreeNodeData) tp.getData();
		return ((TreeNodeData) node).toString(ConstValue.SOURCETYPE);

	}

	public Image getImage(Object element) {
	
		List<String> listype;
		TreeObject tp = (TreeObject) element;
		TreeNodeData node = (TreeNodeData) tp.getData();
		String type = node.getTypeSource();
		
		if( type.equals("WORKSPACE")){
			return Images.imageRegistry.get(Images.PROJECT);
		}
		if( type.equals("FOLDERSOURCE")){
			return Images.imageRegistry.get(Images.FOLDER);
		}
		if( type.equals("PACKAGE")){
			return Images.imageRegistry.get(Images.PACKAGE);
		}
		
		if( type.equals("FIELD")){
			listype= node.getListype();
			return getImageType(listype,"FIELD");
		}
		
		if( type.equals("METHOD")){
			listype= node.getListype();
			return getImageType(listype,"METHOD");
		}else{
			return Images.imageRegistry.get(Images.CLASS);
		}
	}
	
	public Image getImageType(List<String> listype,String typeobject){
		if(typeobject.equals("FIELD")){
			if(listype.contains("Static")&&listype.contains("Final")){
				return Images.imageRegistry.get(Images.FIELD_STATIC_FINAL);
			}
			if(listype.contains("Static")){
				return Images.imageRegistry.get(Images.FIELD_STATIC);
			}
			if(listype.contains("Private")){
				return Images.imageRegistry.get(Images.FIELD_PRIVATE);
			}
		}
		else{
			if(listype.contains("Private")){
				return Images.imageRegistry.get(Images.METHOD_PRIVATE);
			}
			if(listype.contains("Public")){
				return Images.imageRegistry.get(Images.METHOD_PUBLIC);
			}
		}
		
		return Images.imageRegistry.get(Images.FIELD);
	}
}
