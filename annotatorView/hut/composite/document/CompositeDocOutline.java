package hut.composite.document;
import hut.annotation.AnnotationAction;
import hut.composite.tree.TreeNodeData;
import hut.doc.DataIntermediateDoc;
import hut.doc.HeadingNode;
import hut.doc.HeadingNodeContentProvider;
import hut.doc.HeadingNodeLableProvider;
import hut.doc.HeadingStructure;

import java.awt.Label;
import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import mintani.valueconst.ConstValue;
import ontology.images.Images;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.forms.widgets.FormToolkit;

import service.Service;
import tool.parse.doc.ToolParseDoc;
import ws.data.DocumentData;

/**
 * @author KienCuong
 *
 */
public class CompositeDocOutline extends CompositeAnnotatorSuper {
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private TreeViewer treeViewer;// Khoi tao treeview sau do add vao tree co	
	private Tree tree;
	private Label label;
	private String filename;
	private String test;
	private TreeParent invisibleRoot;
	private DefaultMutableTreeNode top = new DefaultMutableTreeNode("Root");
	private int page;
	private int pagenext;
	private String title;
	private List<List> listImages;
	private List<List> listSections;
	private List<List> listParagraphs;
	private List<List> imageData;
	private List<List> sectionData;
	private CompositeDocComponentAll compositeDocumentExplorer;
	private ToolItem autoItemToolItem;
	private ToolItem matchItemToolItem;
	/**
	 * Create the composite
	 * 
	 * @param parent
	 * @param style
	 */
	public CompositeDocOutline(Composite parent, int style) {
		super(parent, style);
		
		this.createComponent();

	}

	/**
	 * @param duong dan file pdf
	 */
	public void setInput(String filename) {
		this.filename = filename;
	}

	@Override
	public void updateInterface() {
		this.setBookMark(((DataIntermediateDoc) this.getInputData()).getHeading());
		this.listImages 	= ((DataIntermediateDoc) this.getInputData()).getImageData();
		this.listSections 	= ((DataIntermediateDoc) this.getInputData()).getSectionData();
		this.listParagraphs = ((DataIntermediateDoc) this.getInputData()).getParagraphData();
		//Goi ham xu ly luu vao dang cau truc cua server de tra len server
	}

	/**
	 * Viet rieng tao tung thanh phan cua giao dien
	 */
	public void createComponent() {
		setLayout(new FillLayout());
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);

		final SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);

		final Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout());
		toolkit.adapt(composite_1);

		// tree = toolkit.createTree(composite_1, SWT.NONE);
		treeViewer = new TreeViewer(composite_1, SWT.NONE);
		treeViewer.setContentProvider(new HeadingNodeContentProvider());
		treeViewer.setLabelProvider(new HeadingNodeLableProvider());
		tree = treeViewer.getTree();
		toolkit.adapt(tree, true, true);
		toolkit.adapt(sashForm, true, true);

		final Composite composite = new Composite(sashForm, SWT.NONE);
		toolkit.adapt(composite, true, true);
		composite.setLayout(new FillLayout());
		toolkit.adapt(composite);

		final ToolBar toolBar = new ToolBar(composite, SWT.FLAT);
		toolkit.adapt(toolBar, true, true);

		autoItemToolItem = new ToolItem(toolBar, SWT.NONE);
		autoItemToolItem.setText("<<Gate Analyze>>");

		matchItemToolItem = new ToolItem(toolBar, SWT.PUSH);
		
		matchItemToolItem.setText("<<Matching Analyze>>");
		sashForm.setWeights(new int[] {324, 48 });
		// Action listener
		registerAction();

	}

	public void setBookMark(HeadingStructure headingStructure) {
		treeViewer.setInput(headingStructure.getRoot());
		treeViewer.refresh();
		treeViewer.expandToLevel(1);
	}

	
/*	*//**
	 * @param doc
	 * Coi bookmark la cau truc cua thu muc
	 *//*
	public void creatBookMark(HeadingStructure headingStructure) {
		Node rootNode = null;
		if (doc != null)
			rootNode = doc.getFirstChild();

		invisibleRoot = new TreeParent(null, null);
		if (rootNode != null) {
			readChildNodes(rootNode, top, 0, invisibleRoot);

		}
	}*/

	/**
	 * @param rootNode
	 * @param topNode
	 * @param nodeIndex
	 * @param root
	 * @return
	 */
	/*public String readChildNodes(Node rootNode, DefaultMutableTreeNode topNode,int nodeIndex, TreeParent root) {

		String Content = "";
		if (topNode == null)
			topNode = top;

		NodeList children = rootNode.getChildNodes();
		int childCount = children.getLength();
		System.out.println(childCount);
		nodeIndex++;
		for (int i = 0; i < childCount; i++) {

			Node child = children.item(i);
			Element currentElement = (Element) child;
			title = currentElement.getAttribute("title");
			page = Integer.parseInt(currentElement.getAttribute("page"));
			// String rawDest = currentElement.getAttribute("Dest");
			Node demo = child;

			if (demo.getNextSibling() != null) {
				Node test = demo.getNextSibling();
				Element currentElementNext = (Element) test;
				pagenext = Integer.parseInt(currentElementNext.getAttribute("page"));// TreeItem tmpitem = new
				// TreeItem((Tree) child,0);
				System.out.println(title);
				System.out.println("______________________");
				System.out.println(page + "________________" + pagenext);

			} else {
				if ((demo.getParentNode() != null) && (demo.hasChildNodes())) {
					Node parent = demo.getParentNode();
					while (parent.getNextSibling() == null) {
						parent = parent.getParentNode();
					}
					if (parent != null) {
						Element currentElementNext = (Element) (parent.getNextSibling());
						pagenext = Integer.parseInt(currentElementNext.getAttribute("page"));
						System.out.println(title);
						System.out.println("______________________");
						System.out
								.println(page + "________________" + pagenext);
					}
				} else {
					Element currentElementNext = (Element) demo;
					System.out.println(title);
					pagenext = Integer.parseInt(currentElementNext.getAttribute("page"));
					System.out.println(pagenext + "________________" + pagenext);
				}
			}
			pagenext = pagenext-1;
			// String ref=currentElement.getAttribute("objectRef");
			DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(title);
			if (child.hasChildNodes()) {
				TreeParent parentTree = new TreeParent(new TreeNodeData(title,page, pagenext), null);
				root.addChild(parentTree);
				Content += readChildNodes(child, childNode, nodeIndex,parentTree);
			} else {
				TreeObject objectTree = new TreeObject(new TreeNodeData(title,page, pagenext), null);
				root.addChild(objectTree);
			}
		}
		return Content;
	}*/
	
	/**
	 * Ham don nhan cac su kien
	 */
	public void registerAction() {
		Action action;
		action = new Action() {
			public void run() {
				HeadingNode nodeSelection = getSelectedNode();
				//TreeNodeData node = (TreeNodeData) nodeSelection.getData();
				//System.out.println(((TreeNodeData) node).toString(3));
				int pageStart = nodeSelection.getPageNumber();
				int pageEnd = nodeSelection.getPageNext();
				if(pageEnd==0) pageEnd=1;
				//gan book mark hien thoi phong truong hop noi dung cua image,section,rong
				//CompositeDocOutline.this.setCurrentBookmark(((TreeNodeData) node).toString(3));
				//CompositeDocOutline.this.setCurrentBookmark(nodeSelection.getContent());
				//Luu mot du lieu bao ham tat ca cac du lieu paragraph, section ,image
			
				DataIntermediateDoc subData = ((DataIntermediateDoc) CompositeDocOutline.this.getInputData()).getSubData(pageStart, pageEnd, nodeSelection);
				// Tao du lieu trung gian
				CompositeDocOutline.this.setOutputData(subData);
				
				// Chon load lai cac composite
				CompositeDocOutline.this.getController().changeHeading();
			}
		};

		registerDoubleClickAction(action);
		
		//Su kien cho auto analyze , gui bo 3 list len server
		autoItemToolItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) 
			{
				CompositeDocOutline.this.getController().updateAction("Action: Send data to server");
				sendListToServer(CompositeDocOutline.this.listImages,CompositeDocOutline.this.listSections,CompositeDocOutline.this.listParagraphs);
				
				//Hien thi trang thai hoat dong cho user biet
				CompositeDocOutline.this.getController().updateAction("Action: Send data success");
			}
		});
		
		
		matchItemToolItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				AnnotationAction annotationAction = new AnnotationAction();
				//Check bo list cho section truoc xem sao
				annotationAction.saveMatchSourceCode(CompositeDocOutline.this.listParagraphs,ConstValue.PARAGRAPH );
				
			}
		});
	}
	
	/**
	 * @param action
	 * Su kien kich dup chuot
	 */
	public void registerDoubleClickAction(final Action action) {
		treeViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(final DoubleClickEvent arg0) {
				action.run();
			}
		});
	}

	// Xu ly su kien chon mot node

	/*public TreeObject getSelectedNode() {
		ISelection selection = treeViewer.getSelection();
		TreeObject obj = (TreeObject) ((IStructuredSelection) selection).getFirstElement();
		return obj;
	}*/
	
	public HeadingNode getSelectedNode(){
		HeadingNode nodeResult=null;
		ISelection selection = treeViewer.getSelection();
		if (selection != null && selection instanceof IStructuredSelection) {
			IStructuredSelection sel = (IStructuredSelection) selection;
			nodeResult=(HeadingNode) sel.getFirstElement();
		}
		return nodeResult;
	}
	
	
	public void sendListToServer(List<List> listImages,List<List> listSections, List<List> listParagraphs) {
		
		List<DocumentData> listImageData= new ArrayList<DocumentData>();
		List<DocumentData> listSectionData= new ArrayList<DocumentData>();
		List<DocumentData> listParagraphData= new ArrayList<DocumentData>();
		// Xu ly voi bo list cho image;
		bindToList(listImages, listImageData);
		
		//Xu ly voi bo list cho section;
		bindToList(listSections, listSectionData);
		
		//Xu ly voi bo list cho paragraph
		bindToList(listParagraphs, listParagraphData);
		
		
		//Gui ca 3 list len server, xu ly tach, trich bang Gate cho phan auto
		Service.dataServiceDelegate.processAutoAnalyze(listImageData,listSectionData,listParagraphData);
		
	}
	
	public void bindToList(List<List> listDataInput, List<DocumentData> listDataOutput){
		
		if(listDataInput !=null ){
			for (int i = 0; i < listDataInput.size(); i++) {
				List listItems = listDataInput.get(i);
				DocumentData documentData = new DocumentData();
				ToolParseDoc toolParse  = new ToolParseDoc();
				String content=toolParse.convertPlainText(listItems.get(1).toString());
				String id  = listItems.get(2).toString();
				String page = listItems.get(4).toString();
				documentData.setContent(content);
				documentData.setId(id);
				documentData.setPage(page);
				listDataOutput.add(documentData);
			}
		}
	}
	
	

}

// Other Class

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
