package hut.composite.annotationCreatorAndEditor;

import hut.composite.annotationCreatorAndEditor.CompositeClassList.TreeContent;
import hut.composite.annotationCreatorAndEditor.CompositeClassList.TreeLabel;

import java.util.ArrayList;

import ontology.images.Images;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Tree;

import ws.owl.ClassData;
import ws.owl.WebServiceDelegate;
import ws.owl.WebServiceService;

public class CompositeLeftPopUp extends
		SuperCompositeAnnotationCreatorAndEditor {

	private Tree tree;

	
	
	public CompositeLeftPopUp(Composite parent, int style, java.util.List<String> classList) {
		super(parent, style);
		setLayout(new FormLayout());

		final TabFolder tabFolder = new TabFolder(this, SWT.BORDER);
		final FormData fd_tabFolder = new FormData();
		fd_tabFolder.bottom = new FormAttachment(100, 0);
		fd_tabFolder.right = new FormAttachment(100, 0);
		fd_tabFolder.top = new FormAttachment(0, 0);
		fd_tabFolder.left = new FormAttachment(0, 0);
		tabFolder.setLayoutData(fd_tabFolder);

		final TabItem classListTabItem = new TabItem(tabFolder, SWT.NONE);
		classListTabItem.setText("Class List");

		final TreeViewer treeViewerClassList = new TreeViewer(tabFolder, SWT.BORDER);
		treeViewerClassList.setContentProvider(new TreeContent());
		treeViewerClassList.setLabelProvider(new TreeLabel());
		
		// SetInput
		System.out.println("before setInput");
		
		/*String[] inputStringArray = new String[]();
		int i= 0;
		for(String inputString : classList){
			inputStringArray[i] = inputString;
			i++;
			
		}*/
		
		treeViewerClassList.setInput((String[])classList.toArray(new String[0]));
		
		//Thu thuat khac phuc loi du khong co con, van hien dau +, bam vao bien mat dau +
		treeViewerClassList.expandAll();
		treeViewerClassList.collapseAll();
		treeViewerClassList.expandToLevel(2);

		System.out.println(" List String in Composite Left Popup:"+(String[])classList.toArray(new String[0]));
		
		//-------------------------- treeViewer Double Click Event ------------------------------------------------------------
		//---------------------------------------------------------------------------------------------------------------------
		treeViewerClassList.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(final DoubleClickEvent event) {
				ISelection selection = event.getSelection();
				ClassData classData = (ClassData) ((IStructuredSelection)selection).getFirstElement();
				setOutputData(classData);
				getController().updateChosenClass();
			}
		});
		//---------------------------------------------------------------------------------------------------------------------
		//---------------------------------------------------------------------------------------------------------------------
		tree = treeViewerClassList.getTree();
		classListTabItem.setControl(tree);
		// TODO Auto-generated constructor stub
	}
	@Override
	public int updateInterface() {
		
		return 0;
	}

	/*
	 *  class TreeContent
	 */
	//---------------------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------
	class TreeContent implements ITreeContentProvider
	{
		
	/*
	 * Lay ra chi phan Tree la con cua ParentNode thoi
	 * 
	 */
	public Object[] getChildren(Object ParentNode) {
		System.out.println("Su dung ham GetElements()");
		if (ParentNode instanceof ClassData && hasChildren(ParentNode))
		
		{
			String parentClassURI = ((ClassData)ParentNode).getClassURI();
			java.util.List<ClassData> listSubClass = service.Service.webServiceDelegate.getSubClasses(null, parentClassURI, true);
			return listSubClass.toArray();
		}
		else return new Object[0];
	}

	public Object getParent(Object ChildNode) {
		
		return null;
	}

	public boolean hasChildren(Object ParentNode) {
		if (ParentNode instanceof ClassData)
		{
			java.util.List<ClassData> subClassList = service.Service.webServiceDelegate.getSubClasses(null, ((ClassData)ParentNode).getClassURI(), true);
		for (ClassData classData : subClassList)
		{
			if (classData.getClassName().equals("Nothing")) return false;
		}
		return ((ClassData)ParentNode).isHasSubClass();
		}
		else return false;
		
	}

	/*
	 * Day la ham lay toan bo Tree 
	 * Ham nay duoc su dung khi view duoc tao ra
	 */
	public Object[] getElements(Object rootNode) {
		String[] listClassRoot = (String[])rootNode;
		ArrayList<ClassData> tmpList = new ArrayList<ClassData>();
		for(String classRootName : listClassRoot){
			System.out.println("ClassRoot la: " + classRootName);
			//List<ClassData> classList = delegate.getSubClasses("http://hut.edu.vn/ontology/document#"+ classRootName);
			ClassData classRoot = service.Service.webServiceDelegate.getClassByName(null, /*"http://hut.edu.vn/ontology/document#"+*/ classRootName);
			if(classRoot != null){
			tmpList.add(classRoot);
			}
		}
		
		
		return tmpList.toArray();
		
		
	}

	public void dispose() {
		
		
	}

	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		
		
	}
	
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------
	
	/*
	 * class TreeLabel
	 */
	//---------------------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------
	class TreeLabel implements ILabelProvider
	{
		 
		 public TreeLabel() {
		 
			  }
		 public Image getImage(Object arg0) {
			return Images.imageRegistry.get(Images.CLASS);
		}

		public String getText(Object Node) {
			if (Node instanceof ClassData)
				return ((ClassData)Node).getClassName();
			else
				return null;
		}

		public void addListener(ILabelProviderListener arg0) {
			
			
		}

		public void dispose() {
			
		}

		public boolean isLabelProperty(Object arg0, String arg1) {
			
			return false;
		}

		public void removeListener(ILabelProviderListener arg0) {
			
			
		}

		//---------------------------------------------------------------------------------------------------------------------
		//---------------------------------------------------------------------------------------------------------------------
		
		
	}

}
