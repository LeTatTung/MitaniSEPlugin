package hut.composite.annotationManager;

import hut.composite.packageExplorer.PackageTreeNodeData;
import hut.viewer.tree.TreeParent;

import java.util.List;

import ontology.images.Images;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

/**
 * Dung cay project, den Method, Field de reparse,add moi
 * @author DatTT
 */
class ASTParseProject extends ASTVisitor {
	public TreeParent Root = new TreeParent(null, null);
	private String projectName;

	public ASTParseProject(String projectName) {
		this.projectName = projectName;
	}

	public void run() {
		IWorkspaceRoot workspace = ResourcesPlugin.getWorkspace().getRoot();
		IProject[] projects = workspace.getProjects();
		for (IProject project : projects) {
			if (project.getName().equals(projectName))
				if (project.isOpen()) {
					Root = new TreeParent(new PackageTreeNodeData(project.getName(), Images.PROJECT), null);
					Root.setAst(project);					
					try {
						this.visit(project, Root);//tpProject);	
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
		}
	}

	public void visit(IProject project, TreeParent tpProject)
			throws Exception {

		try {
			IJavaProject javaProject = JavaCore.create(project);
			IPackageFragmentRoot[] roots = javaProject.getPackageFragmentRoots();//.getAllPackageFragmentRoots();
			for (IPackageFragmentRoot root : roots) {

				if (root.isArchive())// Library
				{
				} else if (root.isExternal()) {
				} else //Folder source code
				{
					IJavaElement[] childs;
					try {
						TreeParent tpPackageFolder = new TreeParent(new PackageTreeNodeData(root.getElementName(),Images.PACKAGEFOLDER), null);
						tpProject.addChild(tpPackageFolder);
						tpPackageFolder.setAst(root);

						childs = root.getChildren();
						for (IJavaElement child : childs) {
							if (child instanceof IPackageFragment && child.getElementName() != "") {
								IPackageFragment p = (IPackageFragment) child;
								if (p.getCompilationUnits().length > 0) {
									TreeParent tpPackage = new TreeParent(new PackageTreeNodeData(child.getElementName(),Images.PACKAGE), null);
									tpPackage.setAst(child);
									
									tpPackageFolder.addChild(tpPackage);
									this.parseIPackageFragment(p,tpPackage);
								}
							}
						}

					} catch (JavaModelException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
	}

	public void parseIPackageFragment(IPackageFragment pack, TreeParent tpPackage) throws Exception {
		IJavaElement[] javaFiles = pack.getChildren();
		for (IJavaElement javaFile : javaFiles) {
			if (javaFile instanceof ICompilationUnit) {
				TreeParent tpJava = new TreeParent(new PackageTreeNodeData(javaFile.getElementName(), Images.JAVA),null);
				tpJava.setAst(javaFile);
				
				tpPackage.addChild(tpJava);

				ASTParser parser = ASTParser.newParser(AST.JLS3);
				parser.setKind(ASTParser.K_COMPILATION_UNIT);
				parser.setResolveBindings(true);    	
				parser.setSource((ICompilationUnit)javaFile);
				CompilationUnit compilationUnit = (CompilationUnit) parser.createAST(null); 
				
				List<AbstractTypeDeclaration> abstractTypes = compilationUnit.types();
				for (AbstractTypeDeclaration abstractType : abstractTypes) {
					if (abstractType instanceof TypeDeclaration) {
						TypeDeclaration type = (TypeDeclaration) abstractType;
						if(type.isInterface()) // interface
				    	{
							TreeParent tpInterface= new TreeParent(new PackageTreeNodeData(type.getName().toString(), Images.INTERFACE),null);
							tpInterface.setAst(type);
							tpJava.addChild(tpInterface);
				    	}
						else
							parseClass(type, tpJava);
					}
				}
			}
		}
	}
	public void parseClass(TypeDeclaration type, TreeParent tpJava)
	{
		TreeParent tpClass= new TreeParent(new PackageTreeNodeData(type.getName().toString(), Images.CLASS),null);
		tpClass.setAst(type);
		
		tpJava.addChild(tpClass);
		
		//Field
		FieldDeclaration[] fields = type.getFields();
		for (FieldDeclaration field : fields) {
			String name = field.fragments().get(0).toString();
			name = name.contains("=") ? name.substring(0, name.indexOf('=')): name;	
			TreeParent tpField = new TreeParent(new PackageTreeNodeData(name, Images.FIELD),null);
			tpField.setAst(field);
			
			tpClass.addChild(tpField);
		}
		
		//Method
		MethodDeclaration[] methods = type.getMethods();
		for (MethodDeclaration method : methods) {
			TreeParent tpMethod = new TreeParent(new PackageTreeNodeData(method.getName().toString(), Images.METHOD),null);
			tpMethod.setAst(method);
			
			tpClass.addChild(tpMethod);
		}
	}
}

