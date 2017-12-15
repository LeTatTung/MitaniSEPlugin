package hut.doc;
import ontology.images.Images;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

public class HeadingNodeLableProvider implements ILabelProvider  {

	public void addListener(ILabelProviderListener arg0) {
		// TODO Auto-generated method stub

	}

	public void dispose() {
		// TODO Auto-generated method stub

	}

	public boolean isLabelProperty(Object arg0, String arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	public void removeListener(ILabelProviderListener arg0) {
		// TODO Auto-generated method stub

	}

	public Image getImage(Object element) {
		return Images.imageRegistry.get(Images.CLASS);
	}

	public String getText(Object element) {
		if (element instanceof HeadingNode) {
			HeadingNode tempNode = (HeadingNode) element;
			return tempNode.getFullIndexAsString() + " " +tempNode.getContent()+" [Page Start: "+tempNode.getPageNumber()+"; Page end: "+tempNode.getPageNext()+"]";
		}
		return null;
	}	
}
