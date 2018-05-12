package suggestTopic;

import org.eclipse.jdt.internal.ui.javaeditor.JavaEditor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;

@SuppressWarnings("restriction")
public class SuggestTopicAction implements IEditorActionDelegate {
    
    protected JavaEditor targetEditor;

    public void setActiveEditor(IAction action, IEditorPart targetEditor) {
        this.targetEditor = null;
        if (targetEditor instanceof JavaEditor) {
            this.targetEditor = (JavaEditor) targetEditor;
        }
    }

    public void run(IAction action) {
        if (targetEditor != null) {
            SuggestTopicOperations.suggestTopic(targetEditor);
        }
    }

    public void selectionChanged(IAction action, ISelection selection) {}
}
