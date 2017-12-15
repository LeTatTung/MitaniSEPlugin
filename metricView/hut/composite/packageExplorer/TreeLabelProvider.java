package hut.composite.packageExplorer;

import hut.viewer.tree.TreeObject;
import hut.viewer.tree.TreeParent;
import ontology.images.Images;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class TreeLabelProvider extends LabelProvider {
	public String getText(Object element) {
		return element.toString();
	}

	public Image getImage(Object element) {			
		TreeObject tp=(TreeObject)element;
		PackageTreeNodeData node = (PackageTreeNodeData)tp.getData();
		
		return Images.imageRegistry.get(node.getType());
	}
}


