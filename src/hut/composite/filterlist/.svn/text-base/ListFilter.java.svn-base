package hut.composite.filterlist;

import hut.views.graph.ListNodeData;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class ListFilter extends ViewerFilter{

	// chi hien thi nhung thanh phan list co chua doan text nay
	private String text = "";
	public void setText(String text)
	{
		this.text = text.toLowerCase();
	}
	public ListFilter(String text)
	{
		this.text = text.toLowerCase();
	}
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		ListNodeData ele = (ListNodeData) element;
		if(text.equals(""))
			return true;
		else
			return ele.getText().toLowerCase().contains(text);
	}	
}
