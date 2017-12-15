package mitaniseplugin.views;
import mintani.valueconst.ConsistentOntology;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
public class listLabelProvider implements ILabelProvider {

	public Image getImage(Object arg0) {
		return null;
	}

	// Lay label tu ID cua document
	public String getText(Object item) {
		if (item instanceof String) {
			int  prefixLength = ConsistentOntology.DOC_NAMESPACE.length();
			String documentId = (String) item;
			return documentId.substring(prefixLength);
		}
		return null;
	}

	public void addListener(ILabelProviderListener arg0) {

	}

	public void dispose() {

	}

	public boolean isLabelProperty(Object arg0, String arg1) {

		return false;
	}

	public void removeListener(ILabelProviderListener arg0) {

	}

}
