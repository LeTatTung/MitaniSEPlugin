package hut.composite.assistant;




import hut.composite.filterlist.CompositeFilterList;
import hut.composite.filterlist.FilterListContentProvider;
import hut.composite.filterlist.FilterListLabelProvider;
import hut.composite.methodCallTree.CompositeMethodCallTree;
import hut.composite.methodCallTree.MethodTreeNodeData;

import java.util.ArrayList;


import mintani.valueconst.ConsistentOntology;


import hut.viewer.tree.TreeParent;
import hut.views.graph.ListNodeData;


import ontology.images.Images;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.forms.widgets.FormToolkit;

import service.Service;
import swing2swt.layout.BorderLayout;


public class MethodCallHierarchy extends Composite {

	private ListViewer listMethodsViewer = null;
	private CompositeFilterList compositeFilterList;
	private CompositeMethodCallTree compositeMethodCallTree;
	private java.util.List<ListNodeData> listMethods = null;
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	ListNodeData selectedNode = null;
	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public MethodCallHierarchy(Composite parent, int style) {
		super(parent, style);
		
		setLayout(new FillLayout());
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);

		final SashForm sashForm = new SashForm(this, SWT.NONE);

		final Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FillLayout());
		toolkit.adapt(composite);

		final SashForm sashForm_1 = new SashForm(composite, SWT.VERTICAL);

		final Composite composite_2 = new Composite(sashForm_1, SWT.NONE);
		composite_2.setLayout(new FillLayout());
		toolkit.adapt(composite_2);
		//Phan filter list
		compositeFilterList = new CompositeFilterList(composite_2, SWT.NONE);
		toolkit.adapt(compositeFilterList);
		toolkit.adapt(sashForm_1, true, true);
		
		toolkit.adapt(sashForm_1, true, true);

		final Composite composite_3 = new Composite(sashForm_1, SWT.BORDER);
		composite_3.setLayout(new BorderLayout(0, 0));
		toolkit.adapt(composite_3);

		final Composite composite_4 = new Composite(composite_3, SWT.NONE);
		composite_4.setLayoutData(BorderLayout.NORTH);
		composite_4.setLayout(new BorderLayout(0, 0));
		toolkit.adapt(composite_4);

		final CLabel methodsInClassLabel = new CLabel(composite_4, SWT.BORDER);
		methodsInClassLabel.setImage(Images.imageRegistry.get(Images.METHOD));
		methodsInClassLabel.setLayoutData(BorderLayout.NORTH);
		toolkit.adapt(methodsInClassLabel, true, true);
		methodsInClassLabel.setText("Methods in the class");

		listMethodsViewer = new ListViewer(composite_3, SWT.V_SCROLL | SWT.BORDER | SWT.H_SCROLL);
		listMethodsViewer.setContentProvider(new FilterListContentProvider());
		listMethodsViewer.setLabelProvider(new FilterListLabelProvider());
		listMethodsViewer.setSorter(new ViewerSorter(){
			public int compare(Viewer viewer, Object e1, Object e2) {
		        return ((ListNodeData)e1).compareTo((ListNodeData)e2);
		      }
		});
		listMethods = new ArrayList<ListNodeData>();
		listMethodsViewer.setInput(listMethods);
		List list = listMethodsViewer.getList();
		toolkit.adapt(list, true, true);
		list.setLayoutData(BorderLayout.CENTER);
		sashForm_1.setWeights(new int[] {235, 137 });
		toolkit.adapt(sashForm, true, true);

		final Composite composite_1 = new Composite(sashForm, SWT.BORDER);
		composite_1.setLayout(new FillLayout());
		toolkit.adapt(composite_1);

		final Composite composite_5 = new Composite(composite_1, SWT.BORDER);
		composite_5.setLayout(new BorderLayout(0, 0));
		toolkit.adapt(composite_5);

		final Composite composite_6 = new Composite(composite_5, SWT.NONE);
		composite_6.setLayout(new BorderLayout(0, 0));
		composite_6.setLayoutData(BorderLayout.NORTH);
		toolkit.adapt(composite_6);

		final CLabel methodCallFlowLabel = new CLabel(composite_6, SWT.BORDER);
		methodCallFlowLabel.setImage(Images.imageRegistry.get(Images.CALLFLOW));
		toolkit.adapt(methodCallFlowLabel, true, true);
		methodCallFlowLabel.setText("Method Call Flow");

		compositeMethodCallTree = new CompositeMethodCallTree(composite_5, SWT.NONE);
		compositeMethodCallTree.setLayout(new BorderLayout(0, 0));
		toolkit.adapt(compositeMethodCallTree);
		compositeMethodCallTree.setLayoutData(BorderLayout.CENTER);
		sashForm.setWeights(new int[] {162, 335 });
		//
		
		//Tach thanh phan action
		createActions();
		
		//Khoi tao du lieu truyen vao
		
		innitData();
	}
	private void createActions()
	{
		compositeFilterList.registerDoubleClickAction(new Action(){
			public void run()
			{
				showMethodsOfClass();
			}
		});
		listMethodsViewer.addDoubleClickListener(new IDoubleClickListener(){
			public void doubleClick(DoubleClickEvent event) {
				IStructuredSelection selection = (IStructuredSelection) event.getSelection();
				ListNodeData selectedMethod = (ListNodeData) selection.getFirstElement();
				if(selectedMethod != null)
				{
					TreeParent root = (TreeParent) compositeMethodCallTree.getTreeViewer().getInput();
					TreeParent method = new TreeParent(new MethodTreeNodeData(selectedMethod.getText(), Images.METHOD, selectedMethod.getUri()), root);
					root.addChild(method);
				}
				compositeMethodCallTree.getTreeViewer().refresh();
			}});
	}	
	private void innitData(){
		String queryString = ConsistentOntology.prefix
							 +"\n SELECT ?class ?label" 			
							 +"\n WHERE " 
							 +"\n  {" 					
							 +	"\n ?class SEC:hasDirectType 'Class'. " 
							 +  "\n ?class rdfs:label ?label " 
							 +  "\n} "  ;
		//Thuc thi cau truy van,chu y hien tai phai dung prefix la doc,do 2 ontology bi trung thuoc tinh hasDirectType
		
		//Thuc thi cau query tren service	
	    java.util.List<ws.data.ArrayListData> result = Service.dataServiceDelegate.sparqlResultList(null, queryString);
	    System.out.println(queryString);
	    
	    for (int i = 1; i < result.size(); i++){
	    	java.util.List<String> items = result.get(i).getData();	
	    	String resultId = items.get(0).toString();
	    	String resultLabel= items.get(1).toString();
	    	 compositeFilterList.addItem(new ListNodeData(resultLabel,resultId));
	    }
	    
	    
	    
	
	}
	
	
	public ListNodeData getDoubleClickNode()
	{
		return this.selectedNode;
	}
	
	private void showMethodsOfClass()
	{
		// xoa het cac method cu o trong list di
		if(listMethods.size() > 0)
		{
			for(int i= listMethods.size()-1; i>=0; i--)
				listMethods.remove(i);
		}
		// add cac method moi vao
		ListNodeData node = compositeFilterList.getDoubleClickNode();
		
		if(node != null)
		{
			String selectedClass = ConsistentOntology.SEC_NAMESPACE+node.getUri();
			String queryString =   
				ConsistentOntology.prefix
				+"\n SELECT ?method ?label"			
				+"\n WHERE " 
				+"\n{" 					
					+"\n <"+selectedClass+"> SEC:hasMethod ?method . " 
					+"\n ?method SEC:hasDirectType 'Method'. " 
					+"\n ?method rdfs:label ?label " 
				+"\n} "  ;
			
	
		    java.util.List<ws.data.ArrayListData> result = Service.dataServiceDelegate.sparqlResultList(null, queryString);
		    for (int i = 1; i < result.size(); i++){
		    	java.util.List<String> items = result.get(i).getData();	
		    	String resultId = items.get(0).toString();
		    	String resultLabel= items.get(1).toString();
		    	listMethods.add(new ListNodeData(resultLabel,resultId));
		    }
		}
		
		listMethodsViewer.refresh();
	}	

}
