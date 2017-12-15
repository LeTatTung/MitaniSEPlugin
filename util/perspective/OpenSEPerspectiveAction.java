package perspective;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import eclipse.EclipseUtil;

public class OpenSEPerspectiveAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;

	public OpenSEPerspectiveAction() {
	}

	public void run(IAction action) {
		EclipseUtil.showPerspective(PerspectiveSESemantic.PERSPECTIVE_SE_SEMANTIC);
	}

	public void selectionChanged(IAction action, ISelection selection) {
	}

	public void dispose() {
	}

	public void init(IWorkbenchWindow window) {
		this.window = window;
	}
}