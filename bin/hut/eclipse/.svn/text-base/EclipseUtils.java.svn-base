package hut.eclipse;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.internal.corext.dom.ASTNodes;
import org.eclipse.jdt.internal.corext.dom.NodeFinder;
import org.eclipse.jdt.ui.IWorkingCopyManager;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.texteditor.ITextEditor;

public class EclipseUtils {
	
	
	public EclipseUtils() {
		super();
		// TODO Auto-generated constructor stub
	}
	// source co dang /project1/src/package1/Class1.java
	public static String getClassIdFromSource(String source)
	{
		String[] splits = source.split("/");
		String classFull = "";
		for (int i = 3; i < splits.length; i++) {
			classFull += splits[i] + ".";
		}
		classFull = classFull.substring(0, classFull.lastIndexOf(".java."));
		return classFull;
	}
	// filePath co dang /project1/src/package1/Class1.java
	public static ICompilationUnit getICompilationUnitFromFile(String filePath)
	{
		IFile file = ResourcesPlugin.getWorkspace()
			.getRoot().getFile(new Path(filePath));
		return JavaCore.createCompilationUnitFrom(file);
	}
	@SuppressWarnings("restriction")
	private static MethodDeclaration getMethodDeclarationNode(IMethod iMethod,
			CompilationUnit cuNode) throws JavaModelException {
		return (MethodDeclaration) ASTNodes.getParent(getNameNode(iMethod,
				cuNode), MethodDeclaration.class);
	}

	@SuppressWarnings("restriction")
	private static ASTNode getNameNode(IMember iMember, CompilationUnit cuNode)
			throws JavaModelException {
		return NodeFinder.perform(cuNode, iMember.getNameRange());
	}

	private static CompilationUnit getCompilationUnit(ICompilationUnit unit) {
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setResolveBindings(true);
		parser.setSource(unit);
		CompilationUnit ast = (CompilationUnit) parser.createAST(null);
		return ast;
	}
	public static String getClassId(ICompilationUnit unit)
	{
		IJavaElement element = unit.getParent();
		if (element instanceof IPackageFragment) {
			IPackageFragment pack = (IPackageFragment) element;
			String clsId = unit.getElementName();
			clsId = clsId.substring(0, clsId.lastIndexOf(".java"));
			return pack.getElementName()+"."+clsId;
		}
		return "blank_node";
	}
	

	/**
	 * Open a view
	 * 
	 * @param viewId:
	 *            id cua view --> co the dung cheat sheet editor de xem id cua
	 *            mot view vi du: viewId =
	 *            com.aptana.ide.js.ui.views.FileExplorerView
	 */
	public static void showView(String viewId) {
		IWorkbenchWindow window = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();
		IWorkbenchPage page = window.getActivePage();
		try {
			page.showView(viewId);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open a java file. Chu y, o day lam cho truong hop workspace co 1 project
	 * 
	 * @param fileName:
	 *            ten file can open, bao gom ten cua package vi du:
	 *            org.mitani.api.Class1
	 */
	public static IEditorPart openJavaFileInUIThread(String fileName) {
		// convert tu org.mitani.api.Class1 --> org/mitani/api/Class1
		fileName = fileName.substring(fileName.indexOf(".")+1);//loai bo Workspace vd: R.ttt.src.ttt.RunApplication , bo R
		fileName = fileName.substring(fileName.indexOf(".")+1); //loai bo Project bo ttt
		fileName = fileName.replace('.', '/');
		
		IWorkbenchWindow window = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();
		IWorkbenchPage page = window.getActivePage();
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

		for (IProject project : root.getProjects()) {
			if (project.isOpen()) {
				IEditorPart editor = null;
				IFile file = project.getFile(fileName + ".java");
				try {
					editor = IDE.openEditor(page, file);
					return editor;
				} catch (PartInitException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return null;
	}

	public static IEditorPart openJavaFile(String projectName, String fileName) {
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
					return editor;
				} catch (PartInitException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return null;
	}

	public static IEditorPart openComponent(String projectName,
			String fileName, int start, int length) {
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
					((ITextEditor) editor).selectAndReveal(start, length);
					return editor;
				} catch (PartInitException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return null;
	}

	private static String file;
	private static IEditorPart editor;
	private static boolean end = false;

	/**
	 * Open a java file. Chu y, o day lam cho truong hop workspace co 1 project
	 * 
	 * @param fileName:
	 *            ten file can open, bao gom ten cua package vi du:
	 *            org.mitani.api.Class1
	 * class co the la inner class: vi du: org.mitani.api.Class1$Class2
	 */
	public static IEditorPart openJavaFile(String fileName) {
		int index = fileName.indexOf('$');
		if(index != -1) // day la inner Class
			file = fileName.substring(0,index);
		else
			file = fileName;
		end = false;
		PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
			public void run() {
				editor = openJavaFileInUIThread(file);
				end = true;
			}
		});
		while (end == false)
			;
		return editor;
	}
	
	
	// methodID: org.jgraph.graph.BasicMarqueeHandler/getStartPoint()
	public  IEditorPart openMethod(String methodID)
	{
		String classFile = methodID.substring(0,methodID.lastIndexOf("/"));
		IEditorPart editor = EclipseUtils.openJavaFile(classFile);
		IWorkingCopyManager manager = JavaUI.getWorkingCopyManager();
		try {
			manager.connect(editor.getEditorInput());
			ICompilationUnit unit = manager.getWorkingCopy(editor.getEditorInput());
			// tim method trong file java
			GetMethod get = new GetMethod();
			String method = methodID.substring(methodID.indexOf("#")+1);
			get.parse(unit, method.substring(method.lastIndexOf("/")+1));
			// select va scroll den method do.
			if(get.find())
			{
				((ITextEditor) editor).selectAndReveal(get.getStart(), get.getLength());
			}			
			manager.disconnect(editor.getEditorInput());						
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return editor;
	}
	public static IEditorPart getActiveEditor() {
		IWorkbenchWindow window = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();
		IWorkbenchPage page = window.getActivePage();
		return page.getActiveEditor();
	}

	/**
	 * nhay toi mot dong trong editor
	 * 
	 * @param editor
	 *            --> editor quan ly bo soan thao
	 * @param lineNumber
	 *            --> dong can nhay toi
	 */
	public static void gotoMarker(IEditorPart editor, int lineNumber) {
		IResource resource = (IResource) editor.getEditorInput().getAdapter(
				IResource.class);
		IMarker marker;
		try {
			marker = resource.createMarker(IMarker.MARKER);
			marker.setAttribute(IMarker.LINE_NUMBER, lineNumber);
			marker.setAttribute(IMarker.TRANSIENT, true);
			marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO);
			marker.setAttribute(IMarker.MESSAGE, "message");
			IDE.gotoMarker(editor, marker);
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	public static void showPerspective(String perspectiveId) {
		IWorkbench workbench = PlatformUI.getWorkbench();
		try {
			workbench.showPerspective(perspectiveId, workbench
					.getActiveWorkbenchWindow());
		} catch (WorkbenchException e) {
			e.printStackTrace();
		}
	}
}
