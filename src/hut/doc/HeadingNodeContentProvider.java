package hut.doc;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class HeadingNodeContentProvider implements ITreeContentProvider {
	public void dispose() {
		// TODO Auto-generated method stub
	}

	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		// TODO Auto-generated method stub

	}
	
	public Object[] getElements(Object element){
		return getChildren(element);
	}
	
	public Object getParent(Object element){
		if (element instanceof HeadingNode) {
			HeadingNode tempNode = (HeadingNode) element;
			return tempNode.getParent();
		}
		else return null;
	}
	
	public boolean hasChildren(Object element){
		if (element instanceof HeadingNode) {
			HeadingNode tempNode = (HeadingNode) element;
			if (tempNode.getChildrens().size()!=0) return true;
			else return false;
		}
		else return false;
	}

	public Object[] getChildren(Object element) {
		if (element instanceof HeadingNode) {
			HeadingNode tempNode = (HeadingNode) element;
			return tempNode.getChildrens().toArray();
		}
		else return null;
	}
}
