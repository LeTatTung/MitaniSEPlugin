package hut.composite.annotationManager;

import hut.annotation.InitInstance;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mintani.valueconst.ConsistentOntology;
import service.Service;

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
import org.eclipse.jdt.core.dom.*;
import org.eclipse.swt.widgets.Display;

import ws.owl.PropertyMapData;
import ws.owl.InstanceData;

public class BKASTVisitor extends ASTVisitor {

	Display display;

	public List<InstanceData> listofAnnotation = new ArrayList<InstanceData>();
	private CodeComponentNaming codeComponentNaming = new CodeComponentNaming();
	/**
	 * Thay the nhung text bat dau bang @ ket thuc bang dau xuong dong, doi voi xu
	 * ly comment
	 */
	private Pattern replaceCommentPattern = Pattern.compile("@a*[ \\w]*[\\s]");

	public void setCodeComponentNaming(CodeComponentNaming codeComponentNaming) {
		this.codeComponentNaming = codeComponentNaming;
	}

	public BKASTVisitor(CodeComponentNaming codeComponentNaming) {
		this.codeComponentNaming = codeComponentNaming;
	}

	public List<InstanceData> getListofAnnotation() {
		return listofAnnotation;
	}

	public void sendList() {
		run();
	}

	public void run() {
		String workspacename;
		IWorkspaceRoot workspace = ResourcesPlugin.getWorkspace().getRoot();
		workspacename = workspace.toString();
		standardize(workspacename);
		codeComponentNaming.setIdWorkspace(workspacename.replace("/", ""));

		InstanceData workspaceInstance = new InstanceData();
		workspaceInstance.setClassName(ConsistentOntology.WORKSPACE);
		workspaceInstance.setInstanceID(codeComponentNaming.getIdWorkspaceFull());
		workspaceInstance.setInstanceLabel(codeComponentNaming.getIdWorkspace());

		InitInstance initWorkspaceInstance = new InitInstance(workspaceInstance);
		initWorkspaceInstance.addDataProperty(ConsistentOntology.HAS_NAME, workspacename);

		IProject[] projects = workspace.getProjects();

		for (IProject project : projects) {
			if (project.isOpen()) {
				codeComponentNaming.setIdProject(project.getName());
				initWorkspaceInstance.addObjectProperty(ConsistentOntology.HAS_PROJECT,
						codeComponentNaming.getIdProjectFull(), ConsistentOntology.PROJECT);
				parseProject(project);
			}
		}

		listofAnnotation.add(initWorkspaceInstance.getPackageField());

		System.out.println(listofAnnotation);
		System.out.println("AAA");
	}

	public void parseProject(IProject project) {
		InstanceData projectInstance = new InstanceData();
		projectInstance.setClassName(ConsistentOntology.PROJECT);
		projectInstance.setInstanceID(codeComponentNaming.getIdProjectFull());
		projectInstance.setInstanceLabel(codeComponentNaming.getIdProject());
		InitInstance initPackageInstance = new InitInstance(projectInstance);
		initPackageInstance.addDataProperty(ConsistentOntology.HAS_NAME, project.getName());

		IJavaProject javaProject = JavaCore.create(project);
		try {
			IPackageFragmentRoot[] roots = javaProject.getPackageFragmentRoots();
			for (IPackageFragmentRoot root : roots) {
				if ((!root.isArchive())) {
					codeComponentNaming.setIdSourceFolder(root.getElementName());
					initPackageInstance.addObjectProperty(ConsistentOntology.HAS_FOLDER_SOURCE,
							codeComponentNaming.getIdSourceFolderFull(), ConsistentOntology.FOLDERSOURCECODE);
					parseSourceFolder(root);

				}
			}

			listofAnnotation.add(initPackageInstance.getPackageField());

		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void parseProject(IJavaProject javaProject) {
		InstanceData projectInstance = new InstanceData();
		projectInstance.setClassName(ConsistentOntology.PROJECT);
		projectInstance.setInstanceID(codeComponentNaming.getIdProjectFull());
		projectInstance.setInstanceLabel(codeComponentNaming.getIdProject());
		InitInstance initPackageInstance = new InitInstance(projectInstance);
		initPackageInstance.addDataProperty(ConsistentOntology.HAS_NAME, javaProject.getElementName());

		try {
			IPackageFragmentRoot[] roots = javaProject.getPackageFragmentRoots();
			for (IPackageFragmentRoot root : roots) {
				if ((!root.isArchive())) {
					codeComponentNaming.setIdSourceFolder(root.getElementName());
					initPackageInstance.addObjectProperty(ConsistentOntology.HAS_FOLDER_SOURCE,
							codeComponentNaming.getIdSourceFolderFull(), ConsistentOntology.FOLDERSOURCECODE);
					parseSourceFolder(root);

				}
			}

			listofAnnotation.add(initPackageInstance.getPackageField());

		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Phan tich cac source folder
	 */
	public void parseSourceFolder(IPackageFragmentRoot sourceFolder) {
		IJavaElement[] childs;

		InstanceData sourceFolderInstance = new InstanceData();
		sourceFolderInstance.setClassName(ConsistentOntology.FOLDERSOURCECODE);
		sourceFolderInstance.setInstanceID(codeComponentNaming.getIdSourceFolderFull());
		sourceFolderInstance.setInstanceLabel(codeComponentNaming.getIdSourceFolder());

		InitInstance initSourceInstance = new InitInstance(sourceFolderInstance);
		initSourceInstance.addDataProperty(ConsistentOntology.HAS_NAME, sourceFolder.getElementName());

		try {
			childs = sourceFolder.getChildren();
			for (IJavaElement child : childs) {
				if (child instanceof IPackageFragment & child.getElementName() != "") {
					IPackageFragment p = (IPackageFragment) child;
					codeComponentNaming.setIdPackage(p.getElementName());
					initSourceInstance.addObjectProperty(ConsistentOntology.HAS_PACKAGE,
							codeComponentNaming.getIdPackageFull(), ConsistentOntology.PACKAGE);
					parseIPackageFragment(p);
				}
			}

			// Them vao annotation cho Source File
			listofAnnotation.add(initSourceInstance.getPackageField());

		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * Phan tich cac package
	 */
	@SuppressWarnings("unchecked")
	public void parseIPackageFragment(IPackageFragment pack) throws JavaModelException {
		InstanceData packageInstance = new InstanceData();

		packageInstance.setClassName(ConsistentOntology.PACKAGE);
		packageInstance.setInstanceLabel(codeComponentNaming.getIdPackage());
		packageInstance.setInstanceID(codeComponentNaming.getIdPackageFull());

		InitInstance initPackageInstance = new InitInstance(packageInstance);
		initPackageInstance.addDataProperty(ConsistentOntology.HAS_NAME, pack.getElementName());

		ICompilationUnit[] javaFiles = pack.getCompilationUnits();
		// Phan tich file theo ast
		for (ICompilationUnit unit : javaFiles) {
			codeComponentNaming.setIdSourceFile(unit.getElementName());
			initPackageInstance.addObjectProperty(ConsistentOntology.HAS_SOURCE,
					codeComponentNaming.getIdSourceFileFull(), ConsistentOntology.SOURCEFILE);

			parseSourceFile(unit);
		}
		// Them vao package co bao nhieu class
		initPackageInstance.addDataProperty(ConsistentOntology.NUM_CLASSES, new Integer(javaFiles.length).toString());
		listofAnnotation.add(initPackageInstance.getPackageField());
	}

	public void parseSourceFile(ICompilationUnit unit) {
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setResolveBindings(true);
		parser.setSource(unit);
		CompilationUnit compilationUnit = (CompilationUnit) parser.createAST(null);

		// Them vao list phant tu trong annotation la source file thuoc tinh hasClass
		// cho package

		InstanceData sourcefileInstance = new InstanceData();
		sourcefileInstance.setClassName(ConsistentOntology.SOURCEFILE);
		sourcefileInstance.setInstanceLabel(unit.getElementName());
		sourcefileInstance.setInstanceID(codeComponentNaming.getIdSourceFileFull());
		InitInstance initSourceInstance = new InitInstance(sourcefileInstance);
		initSourceInstance.addDataProperty(ConsistentOntology.HAS_NAME, unit.getElementName());

		initSourceInstance.addDataProperty(ConsistentOntology.FULL_PATH, unit.getPath().toString());

		List<AbstractTypeDeclaration> abstractTypes = compilationUnit.types();
		for (AbstractTypeDeclaration abstractType : abstractTypes) {
			if (abstractType instanceof TypeDeclaration) {
				TypeDeclaration type = (TypeDeclaration) abstractType;
				codeComponentNaming.setIdClass(type.getName().toString());

				initSourceInstance.addObjectProperty(ConsistentOntology.HAS_CLASS, codeComponentNaming.getIdClassFull(),
						ConsistentOntology.CLASS);
				initSourceInstance.addObjectProperty(ConsistentOntology.HAS_CLASS, codeComponentNaming.getIdClassFull(),
						ConsistentOntology.CLASS);
				if (type.isInterface()) // interface
				{
					initSourceInstance.addObjectProperty(ConsistentOntology.HAS_INTERFACE,
							codeComponentNaming.getIdClassFull(), ConsistentOntology.CLASS);
					initSourceInstance.addObjectProperty(ConsistentOntology.HAS_INTERFACE,
							codeComponentNaming.getIdClassFull(), ConsistentOntology.CLASS);

				}
				visitClass(type, unit.getPath().toString());
			}
		}

		// Them vao listofAnnoation sourcefile truoc
		listofAnnotation.add(initSourceInstance.getPackageField());

		// Them quan he goi ham chi trong mot source folder
		compilationUnit.accept(this);
	}

	@SuppressWarnings("unchecked")
	public boolean visitClass(TypeDeclaration type, String fullPath) {

		List superInterfaceTypes = type.superInterfaceTypes();
		if (type.isInterface()) // interface
		{

			InstanceData interfaceInstance = new InstanceData();
			codeComponentNaming.setIdInterface(type.getName().toString());

			interfaceInstance.setClassName(ConsistentOntology.INTERFACE);
			interfaceInstance.setInstanceLabel(codeComponentNaming.getIdInterface());
			interfaceInstance.setInstanceID(codeComponentNaming.getIdInterfaceFull());
			InitInstance initInterfaceInstance = new InitInstance(interfaceInstance);
			initInterfaceInstance.addDataProperty(ConsistentOntology.HAS_NAME, type.getName().toString());

			MethodDeclaration[] methods = type.getMethods();
			for (MethodDeclaration method : methods) {

				codeComponentNaming.setIdMethod(method.getName().toString());
				initInterfaceInstance.addObjectProperty(ConsistentOntology.HAS_METHOD,
						codeComponentNaming.getIdMethodFull(), ConsistentOntology.METHOD);
				try {
					visitMethod(method);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			List<Type> superInterfaces = superInterfaceTypes;// type.getSuperclassType();
			Iterator<Type> i = superInterfaces.iterator();
			while (i.hasNext()) {
				Type superInterface = i.next();
				String interfaceID = codeComponentNaming.getIdSourceFolderFull() + "." + superInterface.toString();
				ITypeBinding binding = superInterface.resolveBinding();
				if (binding != null)
					interfaceID = codeComponentNaming.getIdSourceFolderFull() + "." + binding.getBinaryName();

				InstanceData interfaceInstanceExtend = new InstanceData();
				interfaceInstanceExtend.setClassName(ConsistentOntology.INTERFACE);
				interfaceInstanceExtend.setInstanceID(interfaceID);
				if (binding != null)
					interfaceInstanceExtend.setInstanceLabel(binding.getName().toString());
				else
					interfaceInstanceExtend.setInstanceLabel(superInterface.toString());
				InitInstance initInterfaceInstanceExtend = new InitInstance(interfaceInstanceExtend);
				listofAnnotation.add(initInterfaceInstanceExtend.getPackageField());
				// Add node interface for list
				initInterfaceInstance.addDataProperty(ConsistentOntology.FULL_PATH, fullPath);
				initInterfaceInstance.addObjectProperty(ConsistentOntology.IMPLEMENTS_INTERFACE, interfaceID,
						ConsistentOntology.INTERFACE);
			}

			listofAnnotation.add(initInterfaceInstance.getPackageField());
		} else // class
		{
			InstanceData classInstance = new InstanceData();
			classInstance.setClassName(ConsistentOntology.CLASS);
			classInstance.setInstanceLabel(codeComponentNaming.getIdClass());
			classInstance.setInstanceID(codeComponentNaming.getIdClassFull());
			InitInstance initClassInstance = new InitInstance(classInstance);
			initClassInstance.addDataProperty(ConsistentOntology.HAS_NAME, type.getName().toString());

			// Method

			MethodDeclaration[] methods = type.getMethods();
			for (MethodDeclaration method : methods) {

				codeComponentNaming.setIdMethod(method.getName().toString());
				initClassInstance.addObjectProperty(ConsistentOntology.HAS_METHOD,
						codeComponentNaming.getIdMethodFull(), ConsistentOntology.METHOD);
				try {
					visitMethod(method);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// Add individual tuong ung voi viec add vao list truoc

				if (method.isConstructor()) {
					initClassInstance.addObjectProperty(ConsistentOntology.HAS_CONSTRUCTOR,
							codeComponentNaming.getIdMethodFull() + "_Constructor", ConsistentOntology.CONSTRUCTOR);
				}
			}

			// Field
			// Parse field
			FieldDeclaration[] fields = type.getFields();
			for (FieldDeclaration field : fields) {
				String name = field.fragments().get(0).toString();
				name = name.contains("=") ? name.substring(0, name.indexOf('=')) : name;
				name = standardize(name);
				codeComponentNaming.setIdField(name);
				initClassInstance.addObjectProperty(ConsistentOntology.HAS_FIELD, codeComponentNaming.getIdFieldFull(),
						ConsistentOntology.FIELD);
				try {
					visitField(field);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			List<Type> superInterfaces = superInterfaceTypes;// type.getSuperclassType();
			Iterator<Type> i = superInterfaces.iterator();
			while (i.hasNext()) {
				Type superInterface = i.next();
				String interfaceID = codeComponentNaming.getIdSourceFolderFull() + "." + superInterface.toString();
				ITypeBinding binding = superInterface.resolveBinding();
				if (binding != null)
					interfaceID = codeComponentNaming.getIdSourceFolderFull() + "." + binding.getBinaryName();

				// Them vao list cai interface nay truoc
				InstanceData interfaceInstanceExtend = new InstanceData();
				interfaceInstanceExtend.setClassName(ConsistentOntology.INTERFACE);
				interfaceInstanceExtend.setInstanceID(interfaceID);
				if (binding != null)
					interfaceInstanceExtend.setInstanceLabel(binding.getName().toString());
				else
					interfaceInstanceExtend.setInstanceLabel(superInterface.toString());
				InitInstance initInterfaceInstanceExtend = new InitInstance(interfaceInstanceExtend);
				listofAnnotation.add(initInterfaceInstanceExtend.getPackageField());

				initClassInstance.addObjectProperty(ConsistentOntology.IMPLEMENTS_INTERFACE, interfaceID,
						ConsistentOntology.INTERFACE);

			}

			Type superClass = type.getSuperclassType();
			if (superClass != null) {
				String superID = codeComponentNaming.getIdSourceFolderFull() + "." + superClass.toString();
				ITypeBinding binding = superClass.resolveBinding();
				if (binding != null)
					superID = codeComponentNaming.getIdSourceFolderFull() + "." + binding.getBinaryName();

				// Them vao list cai class nay truoc
				InstanceData superclassInstanceExtend = new InstanceData();
				superclassInstanceExtend.setClassName(ConsistentOntology.CLASS);
				superclassInstanceExtend.setInstanceID(superID);
				if (binding != null)
					superclassInstanceExtend.setInstanceLabel(binding.getName().toString());
				else
					superclassInstanceExtend.setInstanceLabel(superClass.toString());
				InitInstance initInterfaceInstanceExtend = new InitInstance(superclassInstanceExtend);
				listofAnnotation.add(initInterfaceInstanceExtend.getPackageField());

				initClassInstance.addObjectProperty(ConsistentOntology.EXTENDS, superID, ConsistentOntology.CLASS);

			}
			// Tao metric cho class
			codeComponentNaming.setIdMetric();
			initClassInstance.addObjectProperty(ConsistentOntology.HAS_METRIC, codeComponentNaming.getIdMetricFull(),
					ConsistentOntology.CLASS_METRIC);

			genClassMetric(type);

			// Tao comment
			if (type.getJavadoc() != null) {
				codeComponentNaming.setIdCommentClass();
				initClassInstance.addObjectProperty(ConsistentOntology.HAS_COMMENT,
						codeComponentNaming.getIdCommentClassFull(), ConsistentOntology.COMMENT);
				visitComment(type.getJavadoc(), codeComponentNaming.getIdCommentClassFull());

				// tao semantic comment
				codeComponentNaming.setIdSemanticCommentClass();
				initClassInstance.addObjectProperty(ConsistentOntology.HAS_SEMANTIC_COMMENT,
						codeComponentNaming.getIdSemanticCommentClassFull(), ConsistentOntology.SEMANTIC_COMMENT);
				visitSemanticComment(type.getJavadoc(), codeComponentNaming.getIdSemanticCommentClassFull(), 0);
			}

			// Ghe tham nhung class ben trong class nay
			for (TypeDeclaration subType : type.getTypes()) {
				codeComponentNaming.setIdClass(subType.getName().toString());
				initClassInstance.addObjectProperty(ConsistentOntology.HAS_CLASS, codeComponentNaming.getIdClassFull(),
						ConsistentOntology.CLASS);
				visitClass(subType, fullPath);
			}

			// add fullpath for this class

			initClassInstance.addDataProperty(ConsistentOntology.FULL_PATH, fullPath);
			listofAnnotation.add(initClassInstance.getPackageField());
		}
		return true;
	}

	public ArrayList<String> addPrimaryType() {
		ArrayList<String> primaryTypeArray = new ArrayList<String>();
		primaryTypeArray.add("void");
		primaryTypeArray.add("Boolean");
		primaryTypeArray.add("int");
		primaryTypeArray.add("byte");
		primaryTypeArray.add("char");
		primaryTypeArray.add("double");
		primaryTypeArray.add("float");
		primaryTypeArray.add("long");
		primaryTypeArray.add("short");
		primaryTypeArray.add("String");
		primaryTypeArray.add("List");
		return primaryTypeArray;
	}

	/*
	 * Class tham tung method.Neu de mac dinh la visit se la ham override .Tuy nhien
	 * de lay chinh xac ten thi ta nen lam nhu sau
	 */
	@SuppressWarnings("unchecked")
	public boolean visitMethod(MethodDeclaration method) throws Exception {

		ArrayList<String> primaryTypeArray = addPrimaryType();
		InstanceData instanceMethod = new InstanceData();
		String nameMethod = method.getName().toString();

		// codeComponentNaming.setIdMethod(nameMethod);
		instanceMethod.setClassName(ConsistentOntology.METHOD);

		instanceMethod.setInstanceID(codeComponentNaming.getIdMethodFull());

		InitInstance initMethodInstance = new InitInstance(instanceMethod);
		initMethodInstance.addDataProperty(ConsistentOntology.HAS_NAME, nameMethod);

		InstanceData instanceConstructor = new InstanceData();
		if (method.isConstructor()) {

			// add node Constructor in list

			// codeComponentNaming.setIdMethod(nameMethod);
			instanceConstructor.setClassName(ConsistentOntology.CONSTRUCTOR);
			instanceConstructor.setInstanceLabel(nameMethod);
			instanceConstructor.setInstanceID(codeComponentNaming.getIdMethodFull() + "_Constructor");
			initMethodInstance.addObjectProperty(ConsistentOntology.HAS_CONSTRUCTOR,
					codeComponentNaming.getIdMethodFull() + "_Constructor", ConsistentOntology.CONSTRUCTOR);
		} else {

		}
		// Kiem tra la kieu method gi: final,private ??....
		List<Object> modifier = method.modifiers();
		addModifierRelation(modifier, initMethodInstance);

		if (method.getJavadoc() != null) {
			// initMethodInstance.addObjectProperty("hasComment",
			// method.getJavadoc().toString(),"Comment");
			codeComponentNaming.setIdCommentMethod();
			initMethodInstance.addObjectProperty(ConsistentOntology.HAS_COMMENT,
					codeComponentNaming.getIdCommentMethodFull(), ConsistentOntology.COMMENT);
			visitComment(method.getJavadoc(), codeComponentNaming.getIdCommentMethodFull());

			codeComponentNaming.setIdSemanticCommentMethod();
			initMethodInstance.addObjectProperty(ConsistentOntology.HAS_SEMANTIC_COMMENT,
					codeComponentNaming.getIdSemanticCommentMethodFull(), ConsistentOntology.SEMANTIC_COMMENT);
			visitSemanticComment(method.getJavadoc(), codeComponentNaming.getIdSemanticCommentMethodFull(), 1);
		}
		if (method.resolveBinding().getReturnType() != null) {

			if (primaryTypeArray.contains(method.resolveBinding().getReturnType().getName())) {
				// chu y addobject ca 3 thanh phan phai day du
				initMethodInstance.addObjectProperty(ConsistentOntology.RETURN_TYPE,
						ConsistentOntology.SEC_NAMESPACE + method.resolveBinding().getReturnType().getName(),
						ConsistentOntology.JAVAPRIMARYTYPE);
			} else {
				codeComponentNaming.setIdReturnType(method.resolveBinding().getReturnType().getBinaryName());

				initMethodInstance.addObjectProperty(ConsistentOntology.RETURN_TYPE,
						standardize(codeComponentNaming.getIdReturnTypeFull()), ConsistentOntology.CLASS);
				// Chua check dc interface.
			}

		}

		ITypeBinding[] typeBidings = method.resolveBinding().getParameterTypes();
		String paramsName = "";
		String seperator = ", ";
		for (ITypeBinding typeBinding : typeBidings) {
			codeComponentNaming.setIdVariable(standardize(typeBinding.getName()));
			initMethodInstance.addObjectProperty(ConsistentOntology.HAS_PARAMATER_TYPE,
					codeComponentNaming.getIdVariableFull(), ConsistentOntology.PARAMATER);
			paramsName += typeBinding.getName() + seperator;
		}
		if (typeBidings.length > 0) {
			paramsName = paramsName.substring(0, paramsName.length() - seperator.length());

		}
		nameMethod = nameMethod + "(" + paramsName + ")";
		instanceMethod.setInstanceLabel(nameMethod);
		instanceConstructor.setInstanceLabel(nameMethod);
		listofAnnotation.add(instanceConstructor);
		listofAnnotation.add(initMethodInstance.getPackageField());

		return true;
	}

	public static String standardize(String URI)// Cac ten uri phai chuan tac
	{
		URI = URI.replace("[", "");
		URI = URI.replace("]", "");
		URI = URI.replace("<", "");
		URI = URI.replace(">", "");
		return URI;
	}

	private Boolean containtModifier(List list, String modifier) {
		for (Object s : list)
			if (s.toString().equals(modifier))
				return true;
		return false;
	}

	private void addModifierRelation(List<Object> list, InitInstance initInstance) throws Exception {
		if (containtModifier(list, "protected")) {
			initInstance.addObjectProperty(ConsistentOntology.HAS_MODIFIER,
					ConsistentOntology.SEC_NAMESPACE + "Protected", ConsistentOntology.JAVAMODIFIER);
		}

		if (containtModifier(list, "private")) {
			initInstance.addObjectProperty(ConsistentOntology.HAS_MODIFIER,
					ConsistentOntology.SEC_NAMESPACE + "Private", ConsistentOntology.JAVAMODIFIER);
		}

		if (containtModifier(list, "public")) {
			initInstance.addObjectProperty(ConsistentOntology.HAS_MODIFIER, ConsistentOntology.SEC_NAMESPACE + "Public",
					ConsistentOntology.JAVAMODIFIER);
		}

		if (containtModifier(list, "final")) {
			initInstance.addObjectProperty(ConsistentOntology.HAS_MODIFIER, ConsistentOntology.SEC_NAMESPACE + "Final",
					ConsistentOntology.JAVAMODIFIER);
		}
		if (containtModifier(list, "static")) {
			initInstance.addObjectProperty(ConsistentOntology.HAS_MODIFIER, ConsistentOntology.SEC_NAMESPACE + "Static",
					ConsistentOntology.JAVAMODIFIER);
		}
	}

	@SuppressWarnings("unchecked")
	public boolean visitField(FieldDeclaration field) throws Exception {
		InstanceData instanceField = new InstanceData();
		String name = field.fragments().get(0).toString();
		name = name.contains("=") ? name.substring(0, name.indexOf('=')) : name;
		instanceField.setClassName(ConsistentOntology.FIELD);
		instanceField.setInstanceLabel(name);
		instanceField.setInstanceID(codeComponentNaming.getIdFieldFull());

		InitInstance initFieldInstance = new InitInstance(instanceField);
		initFieldInstance.addDataProperty(ConsistentOntology.HAS_NAME, name);

		if (field.getJavadoc() != null) {
			codeComponentNaming.setIdCommentField();
			initFieldInstance.addObjectProperty(ConsistentOntology.HAS_COMMENT,
					codeComponentNaming.getIdCommentFieldFull(), ConsistentOntology.COMMENT);
			visitComment(field.getJavadoc(), codeComponentNaming.getIdCommentFieldFull());
		}

		List<Object> modifiers = field.modifiers();
		addModifierRelation(modifiers, initFieldInstance);
		listofAnnotation.add(initFieldInstance.getPackageField());

		return true;
	}

	private void genClassMetric(TypeDeclaration type) {
		int numMethods = type.getMethods().length;
		int numAttributes = type.getFields().length;
		int numInterfaces = type.superInterfaceTypes().size();
		InstanceData instanceMetricClass = new InstanceData();
		instanceMetricClass.setClassName(ConsistentOntology.CLASS_METRIC);
		instanceMetricClass.setInstanceLabel(codeComponentNaming.getIdMetric());
		instanceMetricClass.setInstanceID(codeComponentNaming.getIdMetricFull());

		InitInstance initMetricClassInstance = new InitInstance(instanceMetricClass);
		/*
		 * Individual metric =
		 * loader.getModel().getIndividual(namespace+this.theClassFull + "_metric");
		 * if(metric != null) return; metric = createIndividual(CLASS_METRIC,
		 * this.theClassFull + "_metric"); addObjectProperty(metric, IS_METRIC_OF,
		 * this.theClassFull); metric.addProperty(numMethodsPro, new
		 * Integer(numMethods).toString(), XSDDatatype.XSDnonNegativeInteger);
		 * metric.addProperty(numAttributesPro, new Integer(numAttributes).toString(),
		 * XSDDatatype.XSDnonNegativeInteger); metric.addProperty(numInterfacesPro, new
		 * Integer(numInterfaces).toString(), XSDDatatype.XSDnonNegativeInteger);
		 */
		initMetricClassInstance.addDataProperty(ConsistentOntology.NUM_ATTRIBUTES,
				new Integer(numAttributes).toString());
		initMetricClassInstance.addDataProperty(ConsistentOntology.NUM_METHODS, new Integer(numMethods).toString());
		initMetricClassInstance.addDataProperty(ConsistentOntology.NUM_INTERFACES,
				new Integer(numInterfaces).toString());

		listofAnnotation.add(initMetricClassInstance.getPackageField());

	}

	public boolean visit(MethodInvocation method) {
		String cls;
		String pack = "";
		String idMethod = "";
		String idMethodParent = "";

		// B1: Khoi tao 1 doi tuong de bind du lieu vao cho method
		// Them cac loi goi ham vao khi da co annotation san cua method nay roi

		/*
		 * StringBuffer methodURI = new StringBuffer(this.theClassFull).append("/");
		 * String methodFullName =
		 * methodURI.append(method.getName()).append("()").toString();
		 */

		IMethodBinding methodBinding = method.resolveMethodBinding();

		System.out.println("Dang ghe tham method:" + method.getName());
		if (methodBinding != null) {
			ITypeBinding declaringClass = methodBinding.getDeclaringClass();

			StringBuffer buff = new StringBuffer(declaringClass.getBinaryName());
			if (buff.indexOf("org") == -1) {
				cls = codeComponentNaming.getIdWorkspaceFull() + "." + buff.toString();
			} else {
				cls = buff.toString();
			}
			pack = new StringBuffer(declaringClass.getPackage().getName()).toString();
			System.out.println("Class cha:" + cls);
			System.out.println("Package cha : " + pack);

			InstanceData instanceMethod = new InstanceData();
			String nameMethod = method.getName().toString();
			// Xet 2 truong hop: neu no la subclass: ttt.views.Sample$SubAction thi minh chi
			// lay ten SubAction, truong hop2: neu no la class cha: ttt.views.Sample thi
			// minh lay Sample
			// Chi dung voi 2 cap do class
			if (buff.indexOf("$") == -1) {
				cls = buff.toString().substring(buff.lastIndexOf(".") + 1);
			} else {
				cls = buff.toString().substring(buff.indexOf("$") + 1);
			}
			if ((buff.indexOf("org") == -1) && (buff.indexOf("java") == -1)) {

				idMethod = codeComponentNaming.getIdSourceFolderFull() + "." + pack + "." + cls + "/" + nameMethod;
			} else {
				idMethod = ConsistentOntology.SEC_NAMESPACE + pack + "." + nameMethod;
			}

			// codeComponentNaming.setIdMethod(nameMethod);
			instanceMethod.setClassName(ConsistentOntology.METHOD);
			instanceMethod.setInstanceID(idMethod);

			// Kiem tra nhung method nao ma no dc su dung-- hay method cha su dung method
			// con
			ASTNode parent = method.getParent();
			while (true) {
				if (parent instanceof MethodDeclaration) {
					MethodDeclaration methodParent = (MethodDeclaration) parent;
					// tao instan tuong ung voi viec aad vao listannotation

					/*
					 * MethodInvocation methodParentInvocation = (MethodInvocation) parent;
					 * methodBinding = methodParentInvocation.resolveMethodBinding();
					 * 
					 * if(methodBinding != null){ declaringClass =
					 * methodBinding.getDeclaringClass(); buff = new
					 * StringBuffer(declaringClass.getBinaryName()); if(buff.indexOf("org")==-1){
					 * cls = codeComponentNaming.getIdWorkspaceFull()+"."+buff.toString(); }else{
					 * cls = buff.toString(); } pack = new
					 * StringBuffer(declaringClass.getPackage().getName()).toString();
					 * if(buff.indexOf("$")==-1){ cls=
					 * buff.toString().substring(buff.indexOf(".")+1); }else{ cls=
					 * buff.toString().substring(buff.indexOf("$")+1); } }
					 */

					InstanceData instanceMethodParent = new InstanceData();
					String nameMethodParent = methodParent.getName().toString();
					if ((buff.indexOf("org") == -1) && (buff.indexOf("java") == -1)) {
						idMethodParent = codeComponentNaming.getIdSourceFolderFull() + "." + pack + "." + cls + "/"
								+ nameMethodParent;
					} else {
						idMethodParent = ConsistentOntology.SEC_NAMESPACE + pack + "." + nameMethodParent;
					}
					// codeComponentNaming.setIdMethod(nameMethod);
					instanceMethodParent.setClassName(ConsistentOntology.METHOD);
					instanceMethodParent.setInstanceID(idMethodParent);
					instanceMethodParent.setInstanceLabel(nameMethodParent);
					InitInstance initMethodInstance = new InitInstance(instanceMethodParent);
					initMethodInstance.addObjectProperty(ConsistentOntology.USES_METHOD, idMethod,
							ConsistentOntology.METHOD);

					// Add vao list gui len server.
					listofAnnotation.add(initMethodInstance.getPackageField());
					break;
				} else if (parent == null)
					break;
				else
					parent = parent.getParent();

			}
		}
		return true;
	}

	/**
	 * @param javadoc
	 * @param idComment
	 * @return
	 */

	public boolean visitComment(Javadoc javadoc, String idComment) {
		InstanceData instanceComment = new InstanceData();
		instanceComment.setClassName(ConsistentOntology.COMMENT);
		instanceComment.setInstanceID(idComment);
		instanceComment.setInstanceLabel("Comment");
		InitInstance initCommentInstance = new InitInstance(instanceComment);
		String description = removeAllTagInComment(javadoc.toString());
		System.out.println("DESCRIPTION: " + description);
		description = description.replace("*", "");
		description = description.replace("/", "");
		description = description.replace("\\", "");
		if ((description != null) && (description != ""))
			initCommentInstance.addDataProperty(ConsistentOntology.DESCRIPTION, description);

		List commenttags = javadoc.tags();
		for (int k = 0; k < commenttags.size(); k++) {
			TagElement newtags = (TagElement) commenttags.get(k);
			// get list tags.
			if (newtags.getTagName() != null) {
				if (newtags.getTagName().equals("@author")) {
					List array_tags = newtags.fragments();
					// check ?
					for (int j = 0; j < array_tags.size(); j++) {
						initCommentInstance.addDataProperty(ConsistentOntology.AUTHOR, array_tags.get(j).toString());
					}
				}
				if (newtags.getTagName().equals("@param")) {
					List array_tags = newtags.fragments();
					// check ?
					for (int j = 0; j < array_tags.size(); j++) {
						initCommentInstance.addDataProperty(ConsistentOntology.PARAMS, array_tags.get(j).toString());
					}
				}
				if (newtags.getTagName().equals("@return")) {
					List array_tags = newtags.fragments();
					// check ?
					for (int j = 0; j < array_tags.size(); j++) {
						initCommentInstance.addDataProperty(ConsistentOntology.RETURN, array_tags.get(j).toString());
					}
				}
			}
		}

		listofAnnotation.add(initCommentInstance.getPackageField());
		return true;
	}

	public boolean visitSemanticComment(Javadoc javadoc, String idComment, int checkType) {
		InstanceData instanceComment = new InstanceData();
		instanceComment.setClassName(ConsistentOntology.SEMANTIC_COMMENT);
		instanceComment.setInstanceID(idComment);
		System.out.println("ID COMMENT: " + idComment);
		instanceComment.setInstanceLabel("SemanticComment");
		InitInstance initCommentInstance = new InitInstance(instanceComment);
		String description = removeAllTagInComment(javadoc.toString());
		System.out.println("DESCRIPTION: " + description);
		description = description.replace("*", "");
		description = description.replace("/", "");
		description = description.replace("\\", "");
		if ((description != null) && (description != ""))
			initCommentInstance.addDataProperty(ConsistentOntology.DESCRIPTION, description);

		List commenttags = javadoc.tags();
		for (int k = 0; k < commenttags.size(); k++) {
			TagElement newtags = (TagElement) commenttags.get(k);
			// get list tags.
			if (newtags.getTagName() != null) {
				if (newtags.getTagName().equals("@topic")) {
					List array_tags = newtags.fragments();
					// check ?
					for (int j = 0; j < array_tags.size(); j++) {
						addTopicToSoftwareComponent(idComment, array_tags.get(j).toString().trim(), checkType);
						initCommentInstance.addDataProperty(ConsistentOntology.TOPIC, array_tags.get(j).toString());
					}
				}
				if (newtags.getTagName().equals("@model")) {
					List array_tags = newtags.fragments();
					// check ?
					for (int j = 0; j < array_tags.size(); j++) {
						initCommentInstance.addDataProperty(ConsistentOntology.MODEL, array_tags.get(j).toString());
					}
				}
				if (newtags.getTagName().equals("@function")) {
					List array_tags = newtags.fragments();
					// check ?
					for (int j = 0; j < array_tags.size(); j++) {
						initCommentInstance.addDataProperty(ConsistentOntology.FUNCTION, array_tags.get(j).toString());
					}
				}
			}
		}

		listofAnnotation.add(initCommentInstance.getPackageField());
		return true;
	}
	
	private void addTopicToSoftwareComponent(String idComment, String topicContent, int checkType) {
		// add topic cho semantic comment
		System.out.println("TOPIC CONTENT: " +topicContent);
		List<InstanceData> listSC= new java.util.ArrayList<InstanceData>();
		InstanceData instanceDataSC = new InstanceData();
		instanceDataSC.setInstanceID(idComment);
		instanceDataSC.setClassName(ConsistentOntology.SEMANTIC_COMMENT);
		PropertyMapData pmSC = new PropertyMapData();
		pmSC.setTypeClass(null);
		pmSC.setPropertyname(ConsistentOntology.HAS_TOPIC);
		String pmValue = ConsistentOntology.DOC_NAMESPACE + topicContent + "_Instance";
		System.out.println("----------VALUE--------: "+ pmValue);
		pmSC.setValue(pmValue);
		instanceDataSC.getObjectPropertyList().add(pmSC);
		listSC.add(instanceDataSC);
		service.Service.webServiceDelegate.saveValuesOfIndividual(null, listSC, false);
		
		// add topic cho class hoac method
		// class thi checkType = 0, method thi checkType =1

		List<InstanceData> listCM = new java.util.ArrayList<InstanceData>();
		InstanceData instanceDataCM = new InstanceData();
		String idComments[] = idComment.split("_");
		String instanceIdCM = idComments[0];  
		instanceDataCM.setInstanceID(instanceIdCM);
		if (checkType == 0) {
			instanceDataCM.setClassName(ConsistentOntology.CLASS);
		}
		if (checkType == 1){
			instanceDataCM.setClassName(ConsistentOntology.METHOD);
		}
		PropertyMapData pmCM = new PropertyMapData();
		pmCM.setTypeClass(null);
		pmCM.setPropertyname(ConsistentOntology.HAS_TOPIC);
		pmCM.setValue(pmValue);
		instanceDataCM.getObjectPropertyList().add(pmCM);
		listCM.add(instanceDataCM);
		service.Service.webServiceDelegate.saveValuesOfIndividual(null, listCM, false);
	}
	
	public String removeAllTagInComment(String comment) {
		Matcher match = replaceCommentPattern.matcher(comment);
		return match.replaceAll("");
	}

}
