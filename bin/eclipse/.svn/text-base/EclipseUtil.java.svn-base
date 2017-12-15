package eclipse;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.texteditor.ITextEditor;

public class EclipseUtil {
	public static IEditorPart openJavaFile(String projectName, String fileName, int offset, int length) {
		IWorkbenchWindow window = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();
		IWorkbenchPage page = window.getActivePage();
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

		for (IProject project : root.getProjects()) {
			if (project.isOpen() && project.getName().equals(projectName)) {
				IEditorPart editor = null;
				IFile file = project.getFile(fileName);
				try {
					editor = IDE.openEditor(page, file);
					((ITextEditor) editor).selectAndReveal(offset, length);
					return editor;
				} catch (PartInitException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return null;
	}
	public static void openJavaEditor(String path, String ID, EnumCodeComponentTYPE codeTYPE)
	{
		IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(path));
		ICompilationUnit unit=JavaCore.createCompilationUnitFrom(file);
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);		
		parser.setResolveBindings(true);
		parser.setSource(unit);
		
		CompilationUnit ast = (CompilationUnit) parser.createAST(null);
		DetectVisitor dv = new DetectVisitor(ast, ID, codeTYPE);
	
		path=path.substring(1);
		String project=path.substring(0, path.indexOf("/"));
		path=path.substring(path.indexOf("/")+1);
		
		openJavaFile(project, path, dv.getOffset(), dv.getLength());
	}

	/**
	 * Mở 1 perspective
	 * @param perspectiveId
	 */
	public static void showPerspective(String perspectiveId) {
		IWorkbench workbench = PlatformUI.getWorkbench();
		try {
			workbench.showPerspective(perspectiveId, workbench.getActiveWorkbenchWindow());
		} catch (WorkbenchException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Mở 1 view
	 * @param viewId
	 */
	public static void showView(String viewId) {
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage page = window.getActivePage();
		try {
			page.showView(viewId);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
}
