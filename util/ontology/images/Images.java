package ontology.images;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 *
 * Class  khai bao de goi duoc anh theo lap trinh
 */
public class Images {
	public static ImageRegistry imageRegistry = new ImageRegistry();
	public static final String MANAGE_ANNOTATION = "ManageAnnotation";
	public static final String CLOSE_COMPOSITE = "close";
	public static final String EXPORT_SERVICE = "export_service";
	public static final String COMMENT = "comment";
	public static final String SAVECOMMENT = "savecomment";
	public static final String SAVEANNOTATION = "saveannotation";
	public static final String CALLFLOW = "callflow";
	public static final String METRIC = "metric";
	public static final String USESCLASS = "usesClass";
	public static final String CLASS = "class";
	public static final String PDF_FILE = "pdf";
	public static final String CLASSPARSEERROR = "classParseError";
	public static final String CLASSPARSEOK = "classParseOK";
	public static final String ABSTRACT_CLASS = "abstractClass";
	public static final String JAVA = "java";
	public static final String PACKAGE = "package";
	public static final String PACKAGE_FIELD = "package_field";
	public static final String INTERFACE = "interface";
	public static final String PROJECT = "project";
	public static final String LIBRARY = "library";
	public static final String PACKAGEFOLDER = "packageFolder";
	public static final String JAR = "jar";
	public static final String FOLDER = "folder";
	public static final String FILE = "file";
	public static final String FIELD = "field";
	public static final String FIELD_STATIC = "field_static";
	public static final String FIELD_STATIC_FINAL = "field_static_final";
	public static final String FIELD_PRIVATE = "field_private";
	public static final String FIELD_PUBLIC = "field_public";
	public static final String LAYER = "layer";
	public static final String CHANGE_LEFT = "change_left";
	public static final String CHANGE_RIGHT = "change_right";
	public static final String COPY = "copy";
	public static final String RUN = "run";
	public static final String STOP= "stop";
	public static final String METHOD = "method";
	public static final String METHODCONSTRUCTOR = "method_constructor";
	public static final String METHODPUBLIC = "method_public";
	public static final String METHODSTATIC = "method_static";
	public static final String FINISH_CALL = "finishCall";
	public static final String REFRESH = "refresh";
	public static final String INTERFACE_IMPLEMENTED = "interface_implemented";
	public static final String METHOD_OF_PACKAGE = "method_of_package";
	public static final String CLASS_OF_PACKAGE = "class_of_package";
	public static final String ADD = "add";
	public static final String DELETE = "delete";
	public static final String SAVE = "save";
	public static final String URI = "uri";
	public static final String TEST = "test";
	public static final String URI_NONE = "urinone";
	public static final String COLLAPSE_ALL = "collapseall";
	public static final String BOOKMARK = "bookmark";
	public static final String LOCAL_EXPORT = "localexport";
	public static final String SERVICE_EXPORT = "serviceexport";
	public static final String DESCRIBE = "describe";
	public static final String PDFPARSEERROR = "pdfParseError";
	public static final String PDFPARSEOK = "pdfParseOK";
	public static final String DOCUMENT = "document";
	public static final String MANAGE_DOCUMENT = "document";
	public static final String METHOD_PRIVATE = "methodPrivate";
	public static final String METHOD_PROTECTED = "methodProtected";
	public static final String METHOD_PUBLIC = "methodPublic";
	public static final String METHOD_DEFAULT = "methodDefault";
	public static final String FIELD_PROTECTED = "fieldProtected";
	public static final String FIELD_DEFAULT = "fieldDefault";
	public static final String WARNING = "warning";
	public static final String SELECT= "selection";
	public static final String TREE_PACKAGE = "tree";
	public static final String STAR = "star";
	public static final String  EXPAND_ALL ="expand_all";
	public static final String  HIDDEN ="hidden";
	public static final String  SELECTFAVORITES ="selectfavorites";
	public static final String  FILTER ="filter";
	public static final String  CHART ="chart";
	public static final String  SEARCH ="search";
	static {
		String iconPath = "";

		imageRegistry.put(CLASS, ImageDescriptor.createFromFile(Images.class,
				iconPath + CLASS + ".gif"));
		imageRegistry.put(MANAGE_ANNOTATION, ImageDescriptor.createFromFile(Images.class,
				iconPath + MANAGE_ANNOTATION + ".gif"));
		imageRegistry.put(CLOSE_COMPOSITE, ImageDescriptor.createFromFile(Images.class,
				iconPath + CLOSE_COMPOSITE + ".png"));
		imageRegistry.put(EXPORT_SERVICE, ImageDescriptor.createFromFile(Images.class,
				iconPath + EXPORT_SERVICE + ".png"));
		imageRegistry.put(COMMENT, ImageDescriptor.createFromFile(Images.class,
				iconPath + COMMENT + ".png"));
		imageRegistry.put(SAVECOMMENT, ImageDescriptor.createFromFile(Images.class,
				iconPath + SAVECOMMENT + ".gif"));
		imageRegistry.put(SAVEANNOTATION, ImageDescriptor.createFromFile(Images.class,
				iconPath + SAVEANNOTATION + ".png"));
		imageRegistry.put(CALLFLOW, ImageDescriptor.createFromFile(Images.class,
				iconPath + CALLFLOW + ".png"));
		imageRegistry.put(METRIC, ImageDescriptor.createFromFile(Images.class,
				iconPath + METRIC + ".png"));
		imageRegistry.put(MANAGE_DOCUMENT, ImageDescriptor.createFromFile(Images.class,
				iconPath + MANAGE_DOCUMENT + ".png"));
		imageRegistry.put(DOCUMENT, ImageDescriptor.createFromFile(Images.class,
				iconPath + DOCUMENT + ".jpeg"));
		imageRegistry.put(PDFPARSEERROR, ImageDescriptor.createFromFile(Images.class,
				iconPath + PDFPARSEERROR + ".jpg"));
		imageRegistry.put(PDFPARSEOK, ImageDescriptor.createFromFile(Images.class,
				iconPath + PDFPARSEOK + ".jpg"));
		imageRegistry.put(CLASSPARSEERROR, ImageDescriptor.createFromFile(Images.class,
				iconPath + CLASSPARSEERROR + ".JPG"));
		imageRegistry.put(CLASSPARSEOK, ImageDescriptor.createFromFile(Images.class,
				iconPath + CLASSPARSEOK + ".JPG"));
		imageRegistry.put(ABSTRACT_CLASS, ImageDescriptor.createFromFile(Images.class,
				iconPath + ABSTRACT_CLASS + ".jpg"));
		imageRegistry.put(PACKAGE, ImageDescriptor.createFromFile(Images.class,
				iconPath + PACKAGE + ".gif"));
		imageRegistry.put(INTERFACE, ImageDescriptor.createFromFile(
				Images.class, iconPath + INTERFACE + ".gif"));
		imageRegistry.put(JAVA, ImageDescriptor.createFromFile(Images.class,
				iconPath + JAVA + ".gif"));
		imageRegistry.put(LIBRARY, ImageDescriptor.createFromFile(Images.class,
				iconPath + LIBRARY + ".gif"));
		imageRegistry.put(PACKAGEFOLDER, ImageDescriptor.createFromFile(Images.class,
				iconPath + PACKAGEFOLDER + ".gif"));
		imageRegistry.put(JAR, ImageDescriptor.createFromFile(Images.class,
				iconPath + JAR + ".gif"));		
		imageRegistry.put(FILE, ImageDescriptor.createFromFile(Images.class,
				iconPath + FILE + ".gif"));
		imageRegistry.put(RUN, ImageDescriptor.createFromFile(Images.class,
				iconPath + RUN + ".gif"));
		imageRegistry.put(FIELD, ImageDescriptor.createFromFile(Images.class,
				iconPath + FIELD + ".gif"));
		imageRegistry.put(FIELD_STATIC, ImageDescriptor.createFromFile(Images.class,
				iconPath + FIELD_STATIC + ".gif"));
		imageRegistry.put(FIELD_STATIC_FINAL, ImageDescriptor.createFromFile(Images.class,
				iconPath + FIELD_STATIC_FINAL+ ".gif"));
		imageRegistry.put(FIELD_PRIVATE, ImageDescriptor.createFromFile(Images.class,
				iconPath + FIELD_PRIVATE + ".gif"));
		imageRegistry.put(FIELD_PUBLIC, ImageDescriptor.createFromFile(Images.class,
				iconPath + FIELD_PUBLIC + ".gif"));
		imageRegistry.put(CHANGE_LEFT, ImageDescriptor.createFromFile(Images.class,
				iconPath + CHANGE_LEFT + ".gif"));
		imageRegistry.put(CHANGE_RIGHT, ImageDescriptor.createFromFile(Images.class,
				iconPath + CHANGE_RIGHT + ".gif"));
		imageRegistry.put(LAYER, ImageDescriptor.createFromFile(Images.class,
				iconPath + LAYER + ".jpg"));
		imageRegistry.put(FINISH_CALL, ImageDescriptor.createFromFile(Images.class,
				iconPath + FINISH_CALL + ".jpg"));
		imageRegistry.put(METHOD, ImageDescriptor.createFromFile(Images.class,
				iconPath + METHOD + ".gif"));
		imageRegistry.put(REFRESH, ImageDescriptor.createFromFile(Images.class,
				iconPath + REFRESH + ".gif"));
		imageRegistry.put(COLLAPSE_ALL, ImageDescriptor.createFromFile(Images.class,
				iconPath + COLLAPSE_ALL + ".gif"));
		imageRegistry.put(BOOKMARK, ImageDescriptor.createFromFile(Images.class,
				iconPath + BOOKMARK + ".gif"));
		imageRegistry.put(LOCAL_EXPORT, ImageDescriptor.createFromFile(Images.class,
				iconPath + LOCAL_EXPORT + ".gif"));
		imageRegistry.put(SERVICE_EXPORT, ImageDescriptor.createFromFile(Images.class,
				iconPath + SERVICE_EXPORT + ".gif"));
		
		imageRegistry.put(INTERFACE_IMPLEMENTED, ImageDescriptor.createFromFile(Images.class,
				iconPath + INTERFACE + ".gif"));
		imageRegistry.put(METHOD_OF_PACKAGE, ImageDescriptor.createFromFile(Images.class,
				iconPath + METHOD + ".gif"));
		imageRegistry.put(ADD, ImageDescriptor.createFromFile(Images.class,
				iconPath + ADD + ".gif"));
		imageRegistry.put(STOP, ImageDescriptor.createFromFile(Images.class,
				iconPath + STOP + ".jpg"));
		imageRegistry.put(DELETE, ImageDescriptor.createFromFile(Images.class,
				iconPath + DELETE + ".gif"));
		imageRegistry.put(SAVE, ImageDescriptor.createFromFile(Images.class,
				iconPath + SAVE + ".gif"));
		imageRegistry.put(URI, ImageDescriptor.createFromFile(Images.class,
				iconPath + URI + ".gif"));
		imageRegistry.put(URI_NONE, ImageDescriptor.createFromFile(Images.class,
				iconPath + URI_NONE + ".gif"));
		imageRegistry.put(DESCRIBE, ImageDescriptor.createFromFile(Images.class,
				iconPath + DESCRIBE + ".gif"));
		imageRegistry.put(PDF_FILE, ImageDescriptor.createFromFile(Images.class,
				iconPath + PDF_FILE + ".jpg"));
		
		imageRegistry.put(FIELD_PRIVATE, ImageDescriptor.createFromFile(Images.class,
				iconPath + FIELD_PRIVATE + ".gif"));
		imageRegistry.put(FIELD_PROTECTED, ImageDescriptor.createFromFile(Images.class,
				iconPath + FIELD_PROTECTED + ".gif"));
		imageRegistry.put(FIELD_PUBLIC, ImageDescriptor.createFromFile(Images.class,
				iconPath + FIELD_PUBLIC + ".gif"));
		imageRegistry.put(FIELD_DEFAULT, ImageDescriptor.createFromFile(Images.class,
				iconPath + FIELD_DEFAULT + ".gif"));
		imageRegistry.put(METHOD_PRIVATE, ImageDescriptor.createFromFile(Images.class,
				iconPath + METHOD_PRIVATE + ".gif"));
		imageRegistry.put(METHOD_PROTECTED, ImageDescriptor.createFromFile(Images.class,
				iconPath + METHOD_PROTECTED + ".gif"));
		imageRegistry.put(METHOD_PUBLIC, ImageDescriptor.createFromFile(Images.class,
				iconPath + METHOD_PUBLIC + ".gif"));
		imageRegistry.put(METHOD_DEFAULT, ImageDescriptor.createFromFile(Images.class,
				iconPath + METHOD_DEFAULT + ".gif"));
		imageRegistry.put(METHOD_DEFAULT, ImageDescriptor.createFromFile(Images.class,
				iconPath + METHOD_DEFAULT + ".gif"));
		
		imageRegistry.put(WARNING, ImageDescriptor.createFromFile(Images.class,
				iconPath + WARNING + ".gif"));
		imageRegistry.put(SELECT, ImageDescriptor.createFromFile(Images.class,
				iconPath + SELECT + ".gif"));		
		imageRegistry.put(STAR, ImageDescriptor.createFromFile(Images.class,
				iconPath + STAR + ".gif"));
		imageRegistry.put(SELECTFAVORITES, ImageDescriptor.createFromFile(Images.class,
				iconPath + SELECTFAVORITES + ".gif"));
		imageRegistry.put(TEST, ImageDescriptor.createFromFile(Images.class,
				iconPath + TEST + ".gif"));
		imageRegistry.put(HIDDEN, ImageDescriptor.createFromFile(Images.class,iconPath + HIDDEN + ".gif"));
		imageRegistry.put(FILTER, ImageDescriptor.createFromFile(Images.class,iconPath + FILTER + ".jpeg"));
		imageRegistry.put(CHART, ImageDescriptor.createFromFile(Images.class,iconPath + CHART + ".jpeg"));
		imageRegistry.put(SEARCH, ImageDescriptor.createFromFile(Images.class,iconPath + SEARCH + ".jpeg"));
		
		
		
		imageRegistry.put(FOLDER, PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER));
		imageRegistry.put(PROJECT, PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_PROJECT));
		imageRegistry.put(COPY, PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_TOOL_COPY));
		imageRegistry.put(TREE_PACKAGE, ImageDescriptor.createFromFile(Images.class,iconPath + TREE_PACKAGE + ".gif"));
		
	}
}
