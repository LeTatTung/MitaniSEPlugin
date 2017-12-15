package hut.composite.filterlist;

import hut.views.graph.ListNodeData;
import ontology.images.Images;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

public class FilterListLabelProvider implements ILabelProvider{

	public Image getImage(Object element) {	
		return Images.imageRegistry.get(Images.CLASS);
	}

	public String getText(Object element) {		
		return ((ListNodeData) element).getText();
	}

	public void addListener(ILabelProviderListener listener) {		
	}

	public void dispose() {
	}

	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	public void removeListener(ILabelProviderListener listener) {
	}

}
