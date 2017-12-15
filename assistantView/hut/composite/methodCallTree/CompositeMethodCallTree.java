package hut.composite.methodCallTree;


import hut.composite.packageExplorer.TreeContentProvider;
import hut.composite.packageExplorer.TreeLabelProvider;
import hut.viewer.tree.TreeObject;
import hut.viewer.tree.TreeParent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mintani.valueconst.ConsistentOntology;

import ontology.images.Images;


import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.forms.widgets.FormToolkit;

import service.Service;
import swing2swt.layout.BorderLayout;


public class CompositeMethodCallTree extends Composite {

	private TreeViewer treeViewer;
	
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public CompositeMethodCallTree(Composite parent, int style) {
		super(parent, style);		
		setLayout(new BorderLayout(0, 0));
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);

		final Composite composite = new Composite(this, SWT.BORDER);
		composite.setLayout(new FormLayout());
		composite.setLayoutData(BorderLayout.NORTH);
		toolkit.adapt(composite);
		final Button expandAllButton = new Button(composite, SWT.NONE);
		expandAllButton.setImage(Images.imageRegistry.get(Images.EXPAND_ALL));
		expandAllButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				treeViewer.expandAll();
			}
		});
		final FormData fd_expandAllButton = new FormData();
		fd_expandAllButton.top = new FormAttachment(0, 2);
		fd_expandAllButton.right = new FormAttachment(0, 85);
		fd_expandAllButton.left = new FormAttachment(0, 1);
		expandAllButton.setLayoutData(fd_expandAllButton);
		toolkit.adapt(expandAllButton, true, true);
		expandAllButton.setText("Expand All");

		final Button collapseAllButton = new Button(composite, SWT.NONE);
		collapseAllButton.setImage(Images.imageRegistry.get(Images.COLLAPSE_ALL));
		collapseAllButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				treeViewer.collapseAll();
			}
		});
		final FormData fd_collapseAllButton = new FormData();
		fd_collapseAllButton.top = new FormAttachment(0, 2);
		fd_collapseAllButton.left = new FormAttachment(0, 87);
		fd_collapseAllButton.bottom = new FormAttachment(expandAllButton, 26, SWT.TOP);
		fd_collapseAllButton.right = new FormAttachment(expandAllButton, 90, SWT.RIGHT);
		collapseAllButton.setLayoutData(fd_collapseAllButton);
		toolkit.adapt(collapseAllButton, true, true);
		collapseAllButton.setText("Collapse All");

		Button removeButton;
		removeButton = new Button(composite, SWT.NONE);
		removeButton.setImage(Images.imageRegistry.get(Images.DELETE));
		final FormData fd_removeButton = new FormData();
		fd_removeButton.bottom = new FormAttachment(collapseAllButton, 26, SWT.TOP);
		fd_removeButton.top = new FormAttachment(collapseAllButton, 0, SWT.TOP);
		fd_removeButton.right = new FormAttachment(collapseAllButton, 80, SWT.RIGHT);
		fd_removeButton.left = new FormAttachment(collapseAllButton, 2, SWT.RIGHT);
		removeButton.setLayoutData(fd_removeButton);
		removeButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
				Iterator iters = selection.iterator();
				TreeParent root = (TreeParent) treeViewer.getInput();
				while(iters.hasNext())
				{
					TreeObject obj = (TreeObject) iters.next();
					if(obj.getParent().equals(root))
						root.removeChild(obj);
				}
				treeViewer.refresh();
			}
		});
		toolkit.adapt(removeButton, true, true);
		removeButton.setText("Remove");

		Button removeAllButton;
		removeAllButton = toolkit.createButton(composite, "Remove All", SWT.NONE);
		removeAllButton.setImage(Images.imageRegistry.get(Images.DELETE));
		
		removeAllButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				TreeParent root = (TreeParent) treeViewer.getInput();
				for (TreeObject obj : root.getChildren()) {
					root.removeChild(obj);
				}
				treeViewer.refresh();
			}
		});
		final FormData fd_removeAllButton = new FormData();
		fd_removeAllButton.top = new FormAttachment(0, 2);
		fd_removeAllButton.bottom = new FormAttachment(removeButton, 0, SWT.BOTTOM);
		fd_removeAllButton.right = new FormAttachment(removeButton, 94, SWT.RIGHT);
		fd_removeAllButton.left = new FormAttachment(removeButton, 2, SWT.RIGHT);
		removeAllButton.setLayoutData(fd_removeAllButton);

		treeViewer = new TreeViewer(this, SWT.BORDER | SWT.MULTI);
		treeViewer.setLabelProvider(new TreeLabelProvider());
		treeViewer.setContentProvider(new TreeContentProvider());
		
		TreeParent root = new TreeParent(null, null);
		treeViewer.setInput(root);
		Tree tree = treeViewer.getTree();
		toolkit.adapt(tree, true, true);
		tree.setLayoutData(BorderLayout.CENTER);
		//
		createActions();
	}
	public TreeViewer getTreeViewer()
	{
		return this.treeViewer;
	}
	private void createActions()
	{
		//treeViewer.expandAll();
		treeViewer.addDoubleClickListener(new IDoubleClickListener(){
			public void doubleClick(DoubleClickEvent event) {
				IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
				TreeObject item = (TreeObject) selection.getFirstElement();
				boolean isExpanded = treeViewer.getExpandedState(item);
				if(isExpanded) // expand thi collapse
					treeViewer.setExpandedState(item, false);
				else	   // collapse thi expand				
					treeViewer.setExpandedState(item, true);
												
				MethodTreeNodeData data = (MethodTreeNodeData) item.getData();
				if(!data.getType().equals(Images.FINISH_CALL))
				{				
					TreeParent parent = (TreeParent) item;
					if(parent.getChildren().length == 0) // chua load child thi moi add
					{	
						List[] list = getMethodsUsed(data.getUri());
						List<String> methodsUsedURI = list[0];
						List<String> methodsUsedName = list[1];
					
						if(methodsUsedName.size() == 0)
						{
							TreeObject obj = new TreeObject(new MethodTreeNodeData("finish clall", Images.FINISH_CALL,""), parent);
							parent.addChild(obj);
						}
						else
						{
							for ( int index = 0; index < methodsUsedName.size(); index++){
								TreeParent child = new TreeParent(new MethodTreeNodeData(
										methodsUsedName.get(index), 
										Images.METHOD, 
										methodsUsedURI.get(index)), parent);
								parent.addChild(child);
							}
						}
						// kiem tra neu sau khi add them ma chi co mot item (la finish call)
						// thi se expand doi tuong do
						treeViewer.setExpandedState(item, true);
					}
				}	
				
				treeViewer.refresh();				
			}			
		});
		
	}
	/**
	 *@param method: id cua method (khong bao gom namespace)
	 *vi du: hut.owl.OWLLoader/saveOWL
	 * */
	private List<String>[] getMethodsUsed(String methodURI)
	{
		methodURI = ConsistentOntology.SEC_NAMESPACE+methodURI;
		List<String>[] results = new List[2];
		results[0] = new ArrayList<String>();
		results[1] = new ArrayList<String>();
		String queryString =   
				ConsistentOntology.prefix+
				"\nSELECT ?method ?label" +	// ?layer2 ?method1 ?method2 				
				"\nWHERE " +
				"\n{" +
					" \n<" + methodURI +"> SEC:usesMethod ?method . " +
					"\n ?method SEC:hasDirectType 'Method'. " +
					"\n ?method rdfs:label ?label . " +
				"\n} "  ;
		
		System.out.println(queryString);		
	    java.util.List<ws.data.ArrayListData> result = Service.dataServiceDelegate.sparqlResultList(null, queryString);
	    
	    for (int i = 1; i < result.size(); i++){
	    	java.util.List<String> items = result.get(i).getData();	
	    	String resultsId = items.get(0).toString();
	    	String resultLabel= items.get(1).toString();
	    	results[0].add(resultsId);
	    	results[1].add(resultLabel);
	    	
	    	
	    }
	    
		/*QueryExecution qe = QueryExecutionFactory.create(queryString, Syntax.syntaxARQ, loader.getModel());
		try
        {
            ResultSet resultset = qe.execSelect();
            while (resultset.hasNext())
            {
                QuerySolution soln = resultset.nextSolution();
                Resource methodResource = soln.getResource("method");
                results[0].add(methodResource.getURI().substring(methodResource.getURI().indexOf('#')+1));
                results[1].add(methodResource.getURI());
            }
        }
        finally
        {
            qe.close();
        }*/
		return results;
	}
}
