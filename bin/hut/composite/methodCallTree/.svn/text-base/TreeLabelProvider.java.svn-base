package hut.composite.methodCallTree;





import ontology.images.Images;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import hut.composite.packageExplorer.PackageTreeNodeData;
import hut.viewer.tree.TreeObject;


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


