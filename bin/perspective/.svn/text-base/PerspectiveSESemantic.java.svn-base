package perspective;
import org.eclipse.ui.*;

public class PerspectiveSESemantic
   implements IPerspectiveFactory
{
	public static final String ACCOUNT_VIEW = "hut.composite.login.AccountManagementViewPart";
	public static final String QUERY_VIEW = "hut.composite.querycreator.QueryCreatorViewPart";
	public static final String ANNOTATION_EDITOR_VIEW ="hut.view.annotationCreatorAndEditor.ViewAnnotationCreatorAndEditor";
	public static final String CODE_DOC_VIEW ="mitaniseplugin.views.ViewDocumentAnotator";
	public static final String MANAGE_ARTIFACT_VIEW ="view.ManageArtifactViewPart.ManageArtifactViewPart";	
	public static final String TOTAL_VIEW ="hut.viewPart.TotalView.TotalProjectView";
	public static final String PERSPECTIVE_SE_SEMANTIC="perspective.PerspectiveSESemantic";

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();

		IFolderLayout left = layout.createFolder("left", IPageLayout.LEFT, 0.15f, editorArea);
		left.addView("org.eclipse.jdt.ui.PackageExplorer");
		left.addView(IPageLayout.ID_OUTLINE);

		IFolderLayout bottom = layout.createFolder("bottom", IPageLayout.BOTTOM, 0.44f, editorArea);
		bottom.addView(ACCOUNT_VIEW);
		bottom.addView(QUERY_VIEW);
		bottom.addView(ANNOTATION_EDITOR_VIEW);
		bottom.addView(CODE_DOC_VIEW);
		bottom.addView(MANAGE_ARTIFACT_VIEW);
		bottom.addView(TOTAL_VIEW);
		bottom.addView(IPageLayout.ID_TASK_LIST);
		bottom.addView(IPageLayout.ID_PROBLEM_VIEW);

		//Add Shortcut
		layout.addShowViewShortcut(ACCOUNT_VIEW);
		layout.addShowViewShortcut(QUERY_VIEW);
		layout.addShowViewShortcut(ANNOTATION_EDITOR_VIEW);
		layout.addShowViewShortcut(CODE_DOC_VIEW);
		layout.addShowViewShortcut(MANAGE_ARTIFACT_VIEW);
		layout.addShowViewShortcut(TOTAL_VIEW);
		layout.addPerspectiveShortcut("org.eclipse.jdt.ui.JavaPerspective");
   }
}
