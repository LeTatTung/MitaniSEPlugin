package hut.composite.filterlist;

import java.util.ArrayList;
import java.util.Iterator;
import hut.views.graph.ListNodeData;


import ontology.images.Images;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;



public class CompositeFilterList extends Composite {

	private ListViewer listViewer = null;
	private Text text = null;
	private java.util.List list = null;
	private ListFilter filter = null;
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Action doubleClickAction = null;
	ListNodeData selectedNode = null;
	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public CompositeFilterList(Composite parent, int style) {
		super(parent, style);
		setLayout(new BorderLayout(0, 0));
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);

		final Composite composite = new Composite(this, SWT.NONE);
		final FormLayout formLayout = new FormLayout();
		formLayout.marginHeight = 1;
		composite.setLayout(formLayout);
		composite.setLayoutData(BorderLayout.NORTH);
		toolkit.adapt(composite);

		final CLabel filterLabel = new CLabel(composite, SWT.NONE);
		filterLabel.setImage(Images.imageRegistry.get(Images.FILTER));
		final FormData fd_filterLabel = new FormData();
		fd_filterLabel.left = new FormAttachment(0, 5);
		fd_filterLabel.bottom = new FormAttachment(0, 22);
		fd_filterLabel.top = new FormAttachment(0, 3);
		filterLabel.setLayoutData(fd_filterLabel);
		toolkit.adapt(filterLabel, true, true);
		filterLabel.setText("Filter");

		text = new Text(composite, SWT.BORDER);
		fd_filterLabel.right = new FormAttachment(text, 0, SWT.LEFT);
		
		final FormData fd_text = new FormData();
		fd_text.left = new FormAttachment(0, 60);
		fd_text.right = new FormAttachment(100, 0);
		fd_text.bottom = new FormAttachment(0, 22);
		fd_text.top = new FormAttachment(0, 3);
		text.setLayoutData(fd_text);
		toolkit.adapt(text, true, true);

		final Composite composite_1 = new Composite(this, SWT.BORDER);
		composite_1.setLayout(new BorderLayout(0, 0));
		toolkit.adapt(composite_1);
		composite_1.setLayoutData(BorderLayout.CENTER);

		listViewer = new ListViewer(composite_1, SWT.NONE|SWT.H_SCROLL |SWT.V_SCROLL);
		List list_1 = listViewer.getList();
		list_1.setLayoutData(BorderLayout.CENTER);
		toolkit.adapt(list_1, true, true);
		
		filter = new ListFilter("");
		initList();
		createActions();
		
		//
	}
	private void createActions()
	{
		text.addKeyListener(new KeyAdapter() {
			public void keyReleased(final KeyEvent e) {
				listViewer.removeFilter(filter);
				filter.setText(text.getText());
				listViewer.addFilter(filter);
			}
		});
		listViewer.addDoubleClickListener(new IDoubleClickListener(){
			public void doubleClick(DoubleClickEvent event) {
				IStructuredSelection selection = (IStructuredSelection) event.getSelection();
				selectedNode = (ListNodeData) selection.getFirstElement();
				if(doubleClickAction != null)
					doubleClickAction.run();
			}});
	}
	public ListNodeData getDoubleClickNode()
	{
		return this.selectedNode;
	}
	public void registerDoubleClickAction(Action doubleClickAction)
	{
		this.doubleClickAction = doubleClickAction;
	}
	public void addSelectionChangedListener(ISelectionChangedListener listener)
	{
		listViewer.addSelectionChangedListener(listener); 
	}
	public ArrayList<ListNodeData> getSelectedItems()
	{
		IStructuredSelection sel = (IStructuredSelection) listViewer.getSelection();
		Iterator iters = sel.iterator();
		ArrayList<ListNodeData> list = new ArrayList<ListNodeData>();
		while(iters.hasNext())
		{
			ListNodeData item = (ListNodeData) iters.next();
			list.add(item);
		}
		return list;
	}
	private void initList()
	{
		listViewer.setContentProvider(new FilterListContentProvider());
		listViewer.setLabelProvider(new FilterListLabelProvider());
		listViewer.setSorter(new ViewerSorter(){
			public int compare(Viewer viewer, Object e1, Object e2) {
		        return ((ListNodeData)e1).compareTo((ListNodeData)e2);
		      }
		});
		list = new ArrayList<ListNodeData>();
		listViewer.setInput(list);
	}
	public void addItem(ListNodeData item)
	{
		list.add(item);
		listViewer.refresh();
	}
	public void addItem(ListNodeData item, int index)
	{
		list.add(index, item);
		listViewer.refresh();
	}
	public void removeItem(ListNodeData item)
	{
		list.remove(item);
		listViewer.refresh();
	}
	public void removeItem(int index)
	{
		list.remove(index);
		listViewer.refresh();
	}
	public Iterator getItems()
	{
		return list.iterator();
	}
}
