package hut.composite.filterlist;

import hut.views.graph.ListNodeData;

import java.util.List;


import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class FilterListContentProvider implements IStructuredContentProvider{

	public Object[] getElements(Object inputElement) {
		List<ListNodeData> list = (List<ListNodeData>)inputElement;
		return list.toArray();
	}

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

}
