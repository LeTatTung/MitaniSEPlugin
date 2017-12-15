package mitaniseplugin.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import ws.owl.InstanceData;

public class listContentProvider implements IStructuredContentProvider {

	public Object[] getElements(Object list) {
		ArrayList<String> listDocument = new ArrayList<String>();
		try {
			listDocument = (ArrayList<String>) list;
		} catch (Exception e) {
			System.err.println(e.getCause());
			return null;
		}
		return listDocument.toArray();
	}

	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

}
