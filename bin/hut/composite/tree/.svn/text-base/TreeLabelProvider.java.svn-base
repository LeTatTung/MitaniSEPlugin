package hut.composite.tree;


import java.util.List;

import mintani.valueconst.ConsistentOntology;
import mintani.valueconst.ConstValue;
import ontology.images.Images;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class TreeLabelProvider extends LabelProvider {
	public String getText(Object element) {

		TreeObject tp = (TreeObject) element;
		TreeNodeData node = (TreeNodeData) tp.getData();
		return ((TreeNodeData) node).toString(ConstValue.SOURCETYPE);

	}

	public Image getImage(Object element) {
	
		List<String> listype;
		TreeObject tp = (TreeObject) element;
		TreeNodeData node = (TreeNodeData) tp.getData();
		String type = node.getTypeSource();
		
		if( type.equals("WORKSPACE")){
			return Images.imageRegistry.get(Images.PROJECT);
		}
		if( type.equals("FOLDERSOURCE")){
			return Images.imageRegistry.get(Images.FOLDER);
		}
		if( type.equals("PACKAGE")){
			return Images.imageRegistry.get(Images.PACKAGE);
		}
		
		if( type.equals("FIELD")){
			listype= node.getListype();
			return getImageType(listype,"FIELD");
		}
		if(type == null){
			return Images.imageRegistry.get(Images.CLASS);
		}
		
		if( type.equals("METHOD")){
			listype= node.getListype();
			return getImageType(listype,"METHOD");
		}else{
			return Images.imageRegistry.get(Images.CLASS);
		}
		
	}
	
	public Image getImageType(List<String> listype,String typeobject){
		if(typeobject.equals("FIELD")){
			if(listype.contains(ConsistentOntology.SEC_NAMESPACE+"Static")&&listype.contains("Final")){
				return Images.imageRegistry.get(Images.FIELD_STATIC_FINAL);
			}
			if(listype.contains(ConsistentOntology.SEC_NAMESPACE+"Static")){
				return Images.imageRegistry.get(Images.FIELD_STATIC);
			}
			if(listype.contains(ConsistentOntology.SEC_NAMESPACE+"Private")){
				return Images.imageRegistry.get(Images.FIELD_PRIVATE);
			}
			if(listype.contains(ConsistentOntology.SEC_NAMESPACE+"Static")&&listype.contains("Private")){
				return Images.imageRegistry.get(Images.FIELD_PRIVATE);
			}
		}
		else{
			if(listype.contains(ConsistentOntology.SEC_NAMESPACE+"Private")){
				return Images.imageRegistry.get(Images.METHOD_PRIVATE);
			}
			if(listype.contains(ConsistentOntology.SEC_NAMESPACE+"Public")){
				return Images.imageRegistry.get(Images.METHOD_PUBLIC);
			}
		}
		
		return Images.imageRegistry.get(Images.FIELD);
	}
}
