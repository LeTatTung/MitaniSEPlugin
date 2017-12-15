package hut.composite.annotationManager;

import hut.composite.packageExplorer.PackageTreeNodeData;
import hut.composite.packageExplorer.TreeContentProvider;
import hut.composite.packageExplorer.TreeLabelProvider;
import hut.composite.querycreator.CompositeResult;
import hut.jobs.BKJob;
import hut.jobs.IObjectExecuteJob;
import hut.viewer.tree.TreeObject;
import hut.viewer.tree.TreeParent;
import java.util.ArrayList;
import java.util.List;

import mintani.valueconst.ConsistentOntology;
import ontology.images.Images;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.PlatformUI;

import service.Service;
import swing2swt.layout.BorderLayout;
import ws.owl.InstanceData;

public class CompositeCodeParserView extends Composite implements IObjectExecuteJob{
	public List<InstanceData> listofAnnotation = new ArrayList<InstanceData>();
	private TreeParent root = new TreeParent(null, null);
	private TreeViewer treeViewer;
	private Button parseButton;
	private Button removeButton;
	private Button parseAgainButton;
	private Button addProjectButton;
	private Button saveButton;

	/**
	 * Cho phan tich vao trong job
	 */
	private BKJob job;
	private Boolean newParse;
	
	/**
	 * Phan tu code được select trên cây
	 */
	private TreeObject obj;
	
	public CompositeCodeParserView(Composite parent, int style) {
		super(parent, style);
		setLayout(new BorderLayout(0, 0));

		final Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new FillLayout());
		composite.setLayoutData(BorderLayout.CENTER);

		treeViewer = new TreeViewer(composite, SWT.BORDER);
		treeViewer.setLabelProvider(new TreeLabelProvider());
		treeViewer.setContentProvider(new TreeContentProvider());
		treeViewer.setInput(root);
		final Tree tree = treeViewer.getTree();

		final Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setLayoutData(BorderLayout.SOUTH);

		final Composite composite_2 = new Composite(this, SWT.NONE);
		composite_2.setLayout(new RowLayout());
		composite_2.setLayoutData(BorderLayout.NORTH);

		addProjectButton = new Button(composite_2, SWT.NONE);
		addProjectButton.setImage(Images.imageRegistry.get(Images.ADD));
		addProjectButton.setText("Add Project");

		removeButton = new Button(composite_2, SWT.NONE);
		removeButton.setImage(Images.imageRegistry.get(Images.DELETE));
		removeButton.setText("Remove Project");

		parseButton = new Button(composite_2, SWT.NONE);
		parseButton.setImage(Images.imageRegistry.get(Images.RUN));
		parseButton.setText("Add && Parse");

		parseAgainButton = new Button(composite_2, SWT.NONE);
		parseAgainButton.setImage(Images.imageRegistry.get(Images.RUN));
		parseAgainButton.setText("Reparse");

		saveButton = new Button(composite_2, SWT.NONE);
		saveButton.setImage(Images.imageRegistry.get(Images.SAVE));
		saveButton.setText("Save Annotation");

		createActions();
	}

	private void createActions() {
		parseButton.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {	
				newParse(true);
			}
		});
		parseAgainButton.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				newParse(false);
			}
		});
		addProjectButton.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				AddProjectDialog dialog = new AddProjectDialog(addProjectButton.getShell());
				String project = dialog.open();
				if (!project.equals(""))
					addProject(project);
			}
		});
		removeButton.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				IStructuredSelection sel = (IStructuredSelection) treeViewer.getSelection();
				if (sel.isEmpty())
					return;
				TreeObject obj = ((TreeObject) sel.getFirstElement());
				
				PackageTreeNodeData node = (PackageTreeNodeData) obj.getData();
				if (node.getType().equals(Images.PROJECT)) {
					root.removeChild(obj);
					treeViewer.refresh();
				} else {
					MessageBox box = new MessageBox(parseButton.getShell(),SWT.ICON_ERROR | SWT.OK);
					box.setText("Error");
					box.setMessage("You must select a project");
					box.open();
				}
			}
		});
		saveButton.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
			}
		});
	}

	private String getTreeName(TreeObject obj) {
		return ((PackageTreeNodeData) obj.getData()).getName();
	}

	public void addProject(String projectName) {
		// kiem tra project duoc add vao da co chua.
		TreeParent root = (TreeParent) treeViewer.getInput();
		TreeObject[] projects = root.getChildren();
		for (TreeObject treeObject : projects) {
			PackageTreeNodeData node = (PackageTreeNodeData) treeObject.getData();
			if (node.getName().equals(projectName)) {
				MessageBox mes = new MessageBox(parseButton.getShell(),SWT.ICON_INFORMATION | SWT.OK);
				mes.setText("Message");
				mes.setMessage("This project have already added");
				mes.open();
				return;
			}
		}
		
		// phan tich workspace de lay ve cay phan cap class
		ASTParseProject ast = new ASTParseProject(projectName);
		ast.run();
		
		// them project vao cay quan ly project
		root.addChild(ast.Root);
		treeViewer.expandToLevel(4);
		treeViewer.refresh();
	}
	
	private void newParse(Boolean newParse)
	{
		this.newParse = newParse;
		job = new BKJob("Changing code ...");
		job.setObjectExecuteJob(CompositeCodeParserView.this);
		job.setUser(true);
		job.schedule();
	}
	
	public void jobExecute(final IProgressMonitor monitor)
	{
		String uriProjectTeam = Service.uriProjectTeam;
		String uriWorkspace = "";
		
		/**
		 * Phai login, chon Du an dang lam, Du an phai tao workspace truoc
		 */
		if (uriProjectTeam == null)
		{
			messageError("Error", "You do not choose your working project yet! Login and choose one first");
			return;
		}
		else
		{
			List<String> lst = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, uriProjectTeam, ConsistentOntology.HAS_WORKSPACE);
			if (lst.size() != 0)
			{
				//Chi lay phan tu dau tien lam workspace, vi moi Du an chi co 1 workspace
				uriWorkspace = lst.get(0);
			}
			else
			{
				messageError("Error", "Your working project do not have workspace yet! Create one first");
				return;
			}
		}
		
		/**
		 * Phai chon 1 phan tu code de phan tich
		 */
		obj = null;
		PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
			public void run() {
				try {
					IStructuredSelection sel = (IStructuredSelection) treeViewer.getSelection();
					if (sel.isEmpty())
					{
						MessageDialog.openError(parseButton.getShell(), "Error", "Choose a code component first!");
						return;
					}
					obj = ((TreeObject) sel.getFirstElement());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		if (obj == null)
			return;
		
		/**
		 * Neu tat ca thoa man, bat dau phan tich
		 */
		PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
			public void run() {
				try {	
					/**
					 * Set disabled
					 */
					parseAgainButton.setEnabled(false);
					parseButton.setEnabled(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		monitor.beginTask("Changing Code ...", 3);
		monitor.subTask("Preparing ...");
		monitor.worked(2);
		
		monitor.subTask("AST Parsing ...");		
		CodeComponentNaming codeComponentNaming = new CodeComponentNaming();
		codeComponentNaming.setIdWorkspaceFull(uriWorkspace);
		BKASTVisitor bkVisitor = new BKASTVisitor(codeComponentNaming);
		
		Object ast = obj.getAst();
		Boolean willParse = false;
		if (ast instanceof IProject)//Project
		{
			System.out.println("Project");
			IProject project = (IProject)ast;
			codeComponentNaming.setIdProject(project.getName());
			
			//Kiem tra da ton tai instance tuong ung chua
			Boolean exist = Service.webServiceDelegate.checkExistIndividual(null, codeComponentNaming.getIdProjectFull());
			
			if (newParse)
			{
				if (exist)
					messageError("Error", "Project exist! Use Reparse function");
				else
				{
					Service.webServiceDelegate.createInstance(null, codeComponentNaming.getIdProjectFull(), ConsistentOntology.PROJECT);
					Service.webServiceDelegate.addObjectProperty(null, ConsistentOntology.HAS_PROJECT, codeComponentNaming.getIdProjectFull(), codeComponentNaming.getIdWorkspaceFull());
					willParse = true;
				}
			}
			else
			{
				if (!exist)
					messageError("Error", "Project does not exist! Use Add and Parse function");
				else
				{
					Service.webServiceDelegate.removeCodeIndividual(null, codeComponentNaming.getIdProjectFull(), false);
					willParse = true;
				}
			}
			if (willParse)
				bkVisitor.parseProject(project);
		}
		else if (ast instanceof IPackageFragmentRoot)//Src folder
		{
			System.out.println("src folder");
			IPackageFragmentRoot src = (IPackageFragmentRoot) ast;
			IProject project = (IProject)obj.getParent().getAst();
			codeComponentNaming.setIdProject(project.getName());
			codeComponentNaming.setIdSourceFolder(src.getElementName());
			
			if (Service.webServiceDelegate.checkExistIndividual(null, codeComponentNaming.getIdProjectFull()))
			{
				Boolean exist = Service.webServiceDelegate.checkExistIndividual(null, codeComponentNaming.getIdSourceFolderFull());
				if (newParse)
				{
					if (exist)
						messageError("Error", "Source folder exist! Use Reparse function");
					else
					{
						Service.webServiceDelegate.createInstance(null, codeComponentNaming.getIdSourceFolderFull(), ConsistentOntology.FOLDERSOURCECODE);
						Service.webServiceDelegate.addObjectProperty(null, ConsistentOntology.HAS_FOLDER_SOURCE, codeComponentNaming.getIdSourceFolderFull(), codeComponentNaming.getIdProjectFull());
						willParse = true;
					}
				}
				else
				{
					if (!exist)
						messageError("Error", "Source folder  does not exist! Use Add and Parse function");
					else
					{
						Service.webServiceDelegate.removeCodeIndividual(null, codeComponentNaming.getIdSourceFolderFull(), false);
						willParse = true;
					}
				}
				if (willParse)
					bkVisitor.parseSourceFolder(src);
			}
			else
				messageError("Error", "The Corresponding Project still not added!");
		}
		else if (ast instanceof IPackageFragment)//Package
		{
			System.out.println("Package");
			IPackageFragment pack = (IPackageFragment) ast;
			IPackageFragmentRoot src = (IPackageFragmentRoot)obj.getParent().getAst();
			IProject project = (IProject)obj.getParent().getParent().getAst();
			codeComponentNaming.setIdProject(project.getName());
			codeComponentNaming.setIdSourceFolder(src.getElementName());
			codeComponentNaming.setIdPackage(pack.getElementName());
		
			if (Service.webServiceDelegate.checkExistIndividual(null, codeComponentNaming.getIdSourceFolderFull()))
			{
				Boolean exist = Service.webServiceDelegate.checkExistIndividual(null, codeComponentNaming.getIdPackageFull());
				if (newParse)
				{
					if (exist)
						messageError("Error", "Package exist! Use Reparse function");
					else
					{
						Service.webServiceDelegate.createInstance(null, codeComponentNaming.getIdPackageFull(), ConsistentOntology.PACKAGE);
						Service.webServiceDelegate.addObjectProperty(null, ConsistentOntology.HAS_PACKAGE, codeComponentNaming.getIdPackageFull(), codeComponentNaming.getIdSourceFolderFull());
						willParse = true;
					}
				}
				else
				{
					if (!exist)
						messageError("Error", "Package does not exist! Use Add and Parse function");
					else
					{
						Service.webServiceDelegate.removeCodeIndividual(null, codeComponentNaming.getIdPackageFull(), false);
						willParse = true;
					}
				}
				
				try {
					if (willParse)
						bkVisitor.parseIPackageFragment(pack);
				} catch (JavaModelException e) {
					e.printStackTrace();
				}
			}
			else
				messageError("Error", "The Corresponding SourceFolder still not added!");
		}
		else if (ast instanceof ICompilationUnit)//Java src file
		{
			System.out.println("Java src file");
			
			ICompilationUnit unit = (ICompilationUnit) ast;
			IPackageFragment pack = (IPackageFragment)obj.getParent().getAst();
			IPackageFragmentRoot src = (IPackageFragmentRoot)obj.getParent().getParent().getAst();
			IProject project = (IProject)obj.getParent().getParent().getParent().getAst();
			codeComponentNaming.setIdProject(project.getName());
			codeComponentNaming.setIdSourceFolder(src.getElementName());
			codeComponentNaming.setIdPackage(pack.getElementName());
			codeComponentNaming.setIdSourceFile(unit.getElementName());
			
			if (Service.webServiceDelegate.checkExistIndividual(null, codeComponentNaming.getIdPackageFull()))
			{
				Boolean exist = Service.webServiceDelegate.checkExistIndividual(null, codeComponentNaming.getIdSourceFileFull());
				if (newParse)
				{
					if (exist)
						messageError("Error", "Source file exist! Use Reparse function");
					else
					{
						Service.webServiceDelegate.createInstance(null, codeComponentNaming.getIdSourceFileFull(), ConsistentOntology.SOURCEFILE);
						Service.webServiceDelegate.addObjectProperty(null, ConsistentOntology.HAS_SOURCE, codeComponentNaming.getIdSourceFileFull(), codeComponentNaming.getIdPackageFull());
						willParse = true;
					}
				}
				else
				{
					if (!exist)
						messageError("Error", "Source file does not exist! Use Add and Parse function");
					else
					{
						Service.webServiceDelegate.removeCodeIndividual(null, codeComponentNaming.getIdSourceFileFull(), false);
						willParse = true;
					}
				}
				if (willParse)
					bkVisitor.parseSourceFile(unit);
			}
			else
				messageError("Error", "The Corresponding Package still not added!");
		}
		else if (ast instanceof TypeDeclaration)//Class & Interface
		{
			System.out.println("Class & Interface");
			
			TypeDeclaration type =(TypeDeclaration) ast;
			ICompilationUnit unit = (ICompilationUnit) obj.getParent().getAst();
			IPackageFragment pack = (IPackageFragment) obj.getParent().getParent().getAst();
			IPackageFragmentRoot src = (IPackageFragmentRoot)obj.getParent().getParent().getParent().getAst();
			IProject project = (IProject)obj.getParent().getParent().getParent().getParent().getAst();
			codeComponentNaming.setIdProject(project.getName());
			codeComponentNaming.setIdSourceFolder(src.getElementName());
			codeComponentNaming.setIdPackage(pack.getElementName());
			codeComponentNaming.setIdSourceFile(unit.getElementName());
			
			String relation = "";
			String idFullCI = "";
			String owlClass = "";
			if (type.isInterface())
			{
				codeComponentNaming.setIdInterface(type.getName().toString());
				relation = ConsistentOntology.HAS_INTERFACE;
				idFullCI = codeComponentNaming.getIdInterfaceFull();
				owlClass = ConsistentOntology.INTERFACE;
			}
			else
			{
				codeComponentNaming.setIdClass(type.getName().toString());
				relation = ConsistentOntology.HAS_CLASS;
				idFullCI = codeComponentNaming.getIdClassFull();
				owlClass = ConsistentOntology.CLASS;
			}
			
			String fullPath = unit.getPath().toString();
			
			if (Service.webServiceDelegate.checkExistIndividual(null, codeComponentNaming.getIdSourceFileFull()))
			{
				Boolean exist = Service.webServiceDelegate.checkExistIndividual(null, idFullCI);
				if (newParse)
				{
					if (exist)
						messageError("Error", "Class/Interface exist! Use Reparse function");
					else
					{
						Service.webServiceDelegate.createInstance(null, idFullCI, owlClass);
						Service.webServiceDelegate.addObjectProperty(null, relation, idFullCI, codeComponentNaming.getIdSourceFileFull());
						willParse = true;
					}
				}
				else
				{
					if (!exist)
						messageError("Error", "Class/Interface does not exist! Use Add and Parse function");
					else
					{
						Service.webServiceDelegate.removeCodeIndividual(null, idFullCI, false);
						willParse = true;
					}
				}
				if (willParse)
					bkVisitor.visitClass(type, fullPath);
			}
			else
				messageError("Error", "The Corresponding SourceFile still not added!");
		}
		else if (ast instanceof MethodDeclaration)//Method
		{
			System.out.println("Method");
			
			MethodDeclaration method =(MethodDeclaration) ast;
			TypeDeclaration type =(TypeDeclaration) obj.getParent().getAst();;
			ICompilationUnit unit = (ICompilationUnit) obj.getParent().getParent().getAst();
			IPackageFragment pack = (IPackageFragment) obj.getParent().getParent().getParent().getAst();
			IPackageFragmentRoot src = (IPackageFragmentRoot)obj.getParent().getParent().getParent().getParent().getAst();
			IProject project = (IProject)obj.getParent().getParent().getParent().getParent().getParent().getAst();
			codeComponentNaming.setIdProject(project.getName());
			codeComponentNaming.setIdSourceFolder(src.getElementName());
			codeComponentNaming.setIdPackage(pack.getElementName());
			codeComponentNaming.setIdSourceFile(unit.getElementName());	
			
			String idType= "";			
			if (type.isInterface())
			{
				codeComponentNaming.setIdInterface(type.getName().toString());
				idType = codeComponentNaming.getIdInterfaceFull();
			}
			else
			{
				codeComponentNaming.setIdClass(type.getName().toString());
				idType = codeComponentNaming.getIdClassFull();
			}
			
			codeComponentNaming.setIdMethod(type.getName().toString());
			
			if (Service.webServiceDelegate.checkExistIndividual(null, idType))
			{
				Boolean exist = Service.webServiceDelegate.checkExistIndividual(null, codeComponentNaming.getIdMethodFull());
				if (newParse)
				{
					if (exist)
						messageError("Error", "Method exist! Use Reparse function");
					else
					{
						Service.webServiceDelegate.createInstance(null, codeComponentNaming.getIdMethodFull(), ConsistentOntology.METHOD);
						Service.webServiceDelegate.addObjectProperty(null, ConsistentOntology.HAS_METHOD, codeComponentNaming.getIdMethodFull(), idType);
						willParse = true;
					}
				}
				else
				{
					if (!exist)
						messageError("Error", "Method does not exist! Use Add and Parse function");
					else
					{
						Service.webServiceDelegate.removeCodeIndividual(null, codeComponentNaming.getIdMethodFull(), false);
						willParse = true;
					}
				}
				try {
					if (willParse)
						bkVisitor.visitMethod(method);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else
				messageError("Error", "The Corresponding Class/Interface still not added!");
				
		}
		else if (ast instanceof FieldDeclaration)//Field
		{
			System.out.println("Field");
			
			FieldDeclaration field =(FieldDeclaration) ast;
			TypeDeclaration type =(TypeDeclaration) obj.getParent().getAst();;
			ICompilationUnit unit = (ICompilationUnit) obj.getParent().getParent().getAst();
			IPackageFragment pack = (IPackageFragment) obj.getParent().getParent().getParent().getAst();
			IPackageFragmentRoot src = (IPackageFragmentRoot)obj.getParent().getParent().getParent().getParent().getAst();
			IProject project = (IProject)obj.getParent().getParent().getParent().getParent().getParent().getAst();
			codeComponentNaming.setIdProject(project.getName());
			codeComponentNaming.setIdSourceFolder(src.getElementName());
			codeComponentNaming.setIdPackage(pack.getElementName());
			codeComponentNaming.setIdSourceFile(unit.getElementName());			
			
			String idType= "";
			if (type.isInterface())
			{
				codeComponentNaming.setIdInterface(type.getName().toString());
				idType = codeComponentNaming.getIdInterfaceFull();
			}
			else
			{
				codeComponentNaming.setIdClass(type.getName().toString());
				idType = codeComponentNaming.getIdClassFull();
			}
			
			String name = field.fragments().get(0).toString();
			name = name.contains("=") ? name.substring(0, name.indexOf('=')): name;	
			name = BKASTVisitor.standardize(name);
			codeComponentNaming.setIdField(name);
			
			if (Service.webServiceDelegate.checkExistIndividual(null, idType))
			{
				Boolean exist = Service.webServiceDelegate.checkExistIndividual(null, codeComponentNaming.getIdFieldFull());
				if (newParse)
				{
					if (exist)
						messageError("Error", "Field exist! Use Reparse function");
					else
					{
						Service.webServiceDelegate.createInstance(null, codeComponentNaming.getIdFieldFull(), ConsistentOntology.FIELD);
						Service.webServiceDelegate.addObjectProperty(null, ConsistentOntology.HAS_FIELD, codeComponentNaming.getIdFieldFull(), idType);
						willParse = true;
					}
				}
				else
				{
					if (!exist)
						messageError("Error", "Field does not exist! Use Add and Parse function");
					else
					{
						Service.webServiceDelegate.removeCodeIndividual(null, codeComponentNaming.getIdFieldFull(), false);
						willParse = true;
					}
				}
				try {
					if (willParse)
						bkVisitor.visitField(field);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else
				messageError("Error", "The Corresponding Class/Interface still not added!");
				
		}
		
		monitor.worked(1);
		listofAnnotation = bkVisitor.getListofAnnotation();
		
		monitor.beginTask("Sending list to server: ("+listofAnnotation.size()+") ...", listofAnnotation.size());
		int GAP = 100;
		for (int i=0; i<listofAnnotation.size(); i += GAP)
		{
			int min = Math.min(i+GAP, listofAnnotation.size());
			monitor.subTask("Sending "+i+" to "+ (min-1));
			Service.webServiceDelegate.saveValuesOfIndividual(null, listofAnnotation.subList(i, min), true);
			monitor.worked(min-i);
		}
		
		PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
			public void run() {
				try {			
					parseAgainButton.setEnabled(true);
					parseButton.setEnabled(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}


	@Override
	protected void checkSubclass() {
	}
	
	private void messageError(final String title, final String message)
	{
		PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
			public void run() {
				try {	
					MessageDialog.openError(parseButton.getShell(), title, message);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
