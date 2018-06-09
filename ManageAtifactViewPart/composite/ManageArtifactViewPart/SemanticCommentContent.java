package composite.ManageArtifactViewPart;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mintani.valueconst.ConsistentOntology;
import net.redefer.rdf2html.RDF2HTML;
import ontology.images.Images;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;

import service.Service;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.widgets.FormToolkit;

import hust.composite.browser.FrameBrowser;
import processcomment.CommentTool;
import processcomment.CommentWriterType;

import swing2swt.layout.BorderLayout;
import ws.owl.InstanceData;
import ws.data.NodeType;
import ws.data.MapData;
import ws.owl.PropertyData;
import ws.owl.PropertyMapData;
import hut.annotation.InitInstance;
import hut.composite.assistant.MyComposite;

import org.apache.log4j.Logger;

public class SemanticCommentContent extends SemanticSuperComposite {

	private Table table;
	private TableColumn propertyColumn;
	private TableColumn valueColumn;
	private ToolItem saveAnnotationItem;
	private ToolItem saveCommentItem;
	private ToolItem saveCommentRdfItem;
	private ToolItem viewHTMLItem;
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private List<String> listPropertyName = new ArrayList<String>();
	private List<MapData> listMapProperty;
	private String id;
	private String sourceComponentName;
	private String fullPath;
	private String typeSource;
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * Create the composite
	 *
	 * @param parent
	 * @param style
	 */
	public SemanticCommentContent(Composite parent, int style) {
		super(parent, style);
		setLayout(new BorderLayout(0, 0));
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);

		final Composite composite_1 = new Composite(this, SWT.BORDER);
		composite_1.setLayout(new FormLayout());
		toolkit.adapt(composite_1);
		composite_1.setLayoutData(BorderLayout.NORTH);

		final ToolBar toolBar = new ToolBar(composite_1, SWT.NONE);
		final FormData fd_toolBar = new FormData();
		fd_toolBar.top = new FormAttachment(0, 5);
		fd_toolBar.bottom = new FormAttachment(100, -5);
		fd_toolBar.right = new FormAttachment(0, 500);
		fd_toolBar.left = new FormAttachment(0, 5);
		toolBar.setLayoutData(fd_toolBar);
		toolkit.adapt(toolBar, true, true);

		// Item save annotation
		saveAnnotationItem = new ToolItem(toolBar, SWT.PUSH);
		saveAnnotationItem.setText("Save Annotation");
		saveAnnotationItem.setImage(Images.imageRegistry.get(Images.SAVEANNOTATION));

		// Item save comment overwrite code
		saveCommentItem = new ToolItem(toolBar, SWT.PUSH);
		saveCommentItem.setText("Save Comment");
		saveCommentItem.setImage(Images.imageRegistry.get(Images.SAVECOMMENT));

		// Item save comment rdf file
		saveCommentRdfItem = new ToolItem(toolBar, SWT.PUSH);
		saveCommentRdfItem.setText("Save Comment RDF");
		saveCommentRdfItem.setImage(Images.imageRegistry.get(Images.SAVEANNOTATION));

		// call browser
		viewHTMLItem = new ToolItem(toolBar, SWT.PUSH);
		viewHTMLItem.setText("View HTML");
		viewHTMLItem.setImage(Images.imageRegistry.get(Images.SAVECOMMENT));

		final Composite composite = new Composite(this, SWT.BORDER);
		composite.setLayout(new FillLayout());
		toolkit.adapt(composite);

		table = new Table(composite, SWT.BORDER | SWT.HIDE_SELECTION);
		final FormData fd_table = new FormData();
		fd_table.top = new FormAttachment(0, 61);
		fd_table.right = new FormAttachment(100, 0);
		fd_table.bottom = new FormAttachment(100, 0);
		table.setLayoutData(fd_table);
		toolkit.adapt(table, true, true);
		table.setHeaderVisible(true);

		final TableColumn nullColumn = new TableColumn(table, SWT.NONE);

		propertyColumn = new TableColumn(table, SWT.NONE);
		propertyColumn.setText("Property");
		propertyColumn.setWidth(100);

		valueColumn = new TableColumn(table, SWT.NONE);
		valueColumn.setText("Value");
		valueColumn.setWidth(225);

		propertyColumn = new TableColumn(table, SWT.NONE);
		final Listener paintListener = new Listener() {
			public void handleEvent(Event event) {
				switch (event.type) {
				case SWT.MeasureItem:
					event.width = 225;
					event.height = 120;
					break;
				}
			}
		};

		table.addListener(SWT.MeasureItem, paintListener);

		registerAction();

		// Lay ra thuoc tinh string
		for (PropertyData pd : Service.webServiceDelegate.getAllClassProperties(null, ConsistentOntology.SEMANTIC_COMMENT)) {
			String uri = pd.getPropertURI();
			System.out.println("UUUUUUUUU" + uri);
			String s = Service.webServiceDelegate.getPropertySpecificDataType(null, uri);
			if (s != null && s.equals("string") && standardizeProperty(uri) != "")
				listPropertyName.add(uri);
		}

	}

	public void refresh() {
		table.removeAll();
		for (Control control : table.getChildren()) {
			control.dispose();
		}
	}

	/**
	 * @param id:
	 *            cua source code component Ham duoc goi dau tien tu su kien kich
	 *            vao mot thanh phan cua cay
	 */
	public void addDataToComposite(String id, String typeSource, String sourceName) {
		// khoi tao cac doi tuong
		innitContentComposite(id, typeSource, sourceName);

		// Lay danh sach cac property can them vao
		getListPropertyValue(id);

		// Refresh lai table,chu y co cac control ben trong no cung can dispose
		refresh();

		for (String propertyName : listPropertyName) {
			addRow(id, propertyName);
			System.out.println("PROPERTY NAME" + propertyName);
		}

	}

	public void innitContentComposite(String id, String typeSource, String sourceName) {
		this.id = id;
		this.typeSource = typeSource;
		this.sourceComponentName = sourceName;

		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		IPath location = root.getLocation();
		String tempPath = Service.webServiceDelegate
				.getValueOfSpecificPropertyForIndividual(null, id, ConsistentOntology.FULL_PATH).get(0);

		fullPath = location.toString() + tempPath;

		System.out.println(fullPath);
	}

	/*
	 * Ham goi tu webservice ve lay maaping ten propertyName va list value cua
	 * property name do
	 */
	public void getListPropertyValue(String id) {
		id = id + "_semantic_comment";
		listMapProperty = Service.dataServiceDelegate.getValuePropertyIndividual(null, listPropertyName, id);

	}

	public List<String> getArrayListProperty(String fullPropertyName) {
		System.out.println("FULL PROPERTY NAME: " + fullPropertyName);
		for (int i = 0; i < listMapProperty.size(); i++) {
			MapData mapData = listMapProperty.get(i);
			System.out.println("LIST MAP PROPERTY1: " + mapData.getKey() + " MAP OBJECT1: " + mapData.getObject());
			if (mapData.getKey().equals(fullPropertyName)) {
				System.out.println("LIST MAP PROPERTY2: " + mapData.getKey() + " MAP OBJECT2: " + mapData.getObject());
				return mapData.getObject();
			}
		}
		return null;
	}

	public void addRow(String id, String propertyName) {
		List<String> propertyValue;
		String localName = standardizeProperty(propertyName);
		System.out.println("LOCAL NAME: " + localName);
		TableItem item;
		item = new TableItem(table, SWT.NONE, 0);
		item.setText(1, localName);
		TableEditor editor = new TableEditor(table);
		// Test goi webservice
		/*
		 * WebServiceService service = new WebServiceService(); WebServiceDelegate
		 * delegate =service.getWebServicePort(); List<PropertyData> test =
		 * delegate.getAllClassProperties("Comment");
		 */
		propertyValue = getArrayListProperty(propertyName);
		System.out.println("PROPERTY VALUE" + propertyValue);
		MyComposite myComposite = new MyComposite(table, SWT.NONE, propertyValue);
		editor.grabHorizontal = true;

		editor.setEditor(myComposite, item, 2);

		item.setData(myComposite);
		item.setData("propertyname", propertyName);// ten cua property duoc truyen vao
	}

	public void registerAction() {
		saveCommentItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {

				logger.info("Ghi comment vao code");
				String comment = "";
				// Truong hop modifie, xoa tat removeAll cac relation,annotation
				// lien quan den individual do xong tao ra mot cai individual
				// moi co id cu
				for (TableItem item : table.getItems()) {
					String propertyName = (String) item.getData("propertyname");
					MyComposite myComposite = (MyComposite) item.getData();
					for (String value : myComposite.getListDataValue()) {
						comment += standardizeComment(propertyName, value);
						// comment += standardizeSemanticComment(propertyName, value);
					}
				}
				System.out.println(comment);
				if (comment != "") {
					System.out.println("COMMENT: " + comment);
					CommentTool commentTool = new CommentTool();
					System.out.println(typeSource);
					if (typeSource.equals("CLASS"))
						commentTool.addCommentClass(fullPath, sourceComponentName, comment,
								CommentWriterType.writeOver);
					if (typeSource.equals("METHOD")) {
						System.out.println(sourceComponentName.indexOf("(", 0));
						String tmpNameMethod = sourceComponentName.substring(0,
								sourceComponentName.indexOf("(", 0) + 1);
						System.out.println(tmpNameMethod);
						commentTool.addCommentMethod(fullPath, tmpNameMethod, comment, CommentWriterType.writeOver);
					}
				}

			}
		});

		// Ghi va annotation

		saveAnnotationItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				if (check()) {
					List<InstanceData> listofAnnotation = new ArrayList<InstanceData>();

					logger.info("Luu vao annotation");
					// Buoc 1: Tao instance cho sourece code .tuong duong voi viec them vao
					// listAnnotation gui len
					InstanceData sourceComponentInstance = new InstanceData();

					if (typeSource.equals(NodeType.CLASS.name())) {
						sourceComponentInstance.setClassName(ConsistentOntology.CLASS);
					}
					if (typeSource.equals(NodeType.METHOD.name())) {
						sourceComponentInstance.setClassName(ConsistentOntology.METHOD);
					}

					String idComment = id + "_semantic_comment";
					sourceComponentInstance.setInstanceID(id);// id cua class to
					sourceComponentInstance.setInstanceLabel(sourceComponentName);
					InitInstance initSourceInstance = new InitInstance(sourceComponentInstance);
					initSourceInstance.addObjectProperty(ConsistentOntology.HAS_SEMANTIC_COMMENT, idComment,
							ConsistentOntology.SEMANTIC_COMMENT);

					// Buoc 2:Luu cau truc annotation cho comment vao listAnnotation

					InstanceData commentInstance = new InstanceData();
					commentInstance.setClassName(ConsistentOntology.SEMANTIC_COMMENT);
					commentInstance.setInstanceID(idComment);
					commentInstance.setInstanceLabel(sourceComponentName + "_semantic_comment");
					InitInstance initcommentInstance = new InitInstance(commentInstance);

					for (TableItem item : table.getItems()) {
						String propertyName = (String) item.getData("propertyname");
						MyComposite myComposite = (MyComposite) item.getData();
						for (String value : myComposite.getListDataValue()) {
							initcommentInstance.addDataProperty(propertyName, value);

						}

					}

					// Them vao theo thu tu: commment truoc, source component sau

					listofAnnotation.add(initcommentInstance.getPackageField());
					listofAnnotation.add(initSourceInstance.getPackageField());

					Service.webServiceDelegate.removeIndividual(null, idComment);
					Service.webServiceDelegate.saveValuesOfIndividual(null, listofAnnotation, false);
					
					// add topic to SoftwareComponent
					addTopicToSoftwareComponent();
					System.out.println("hehe");
				}

			}
		});
		// Ghi comment ra RDF file
		saveCommentRdfItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				logger.info("Ghi comment ra RDF file");
				String rdfContent = generateRDFCommentFinal();
				String rdfFileName = "/home/tung/Data/resultRDF.xml";
				writeFile(rdfFileName, rdfContent);
				RDFComment rdfComment = new RDFComment(generateRDFCommentFinal());
			}
		});

		viewHTMLItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				logger.info("Call browser render RDF2HTML");
				String input = generateRDFCommentFinal();
				String outputFileName = "/home/tung/Data/result.html";
				RDF2HTML rdfConvert = new RDF2HTML(input, outputFileName);
				try {
					rdfConvert.performTask();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String link = "file:///home/tung/Data/result.html";
				FrameBrowser browser = new FrameBrowser(link);
				browser.createBrowser(link);
			}
		});
	}

	private void addTopicToSoftwareComponent() {
		// add topic cho semantic comment
		List<InstanceData> listSC= new java.util.ArrayList<InstanceData>();
		InstanceData instanceDataSC = new InstanceData();
		String idComment = id + "_semantic_comment";
		instanceDataSC.setInstanceID(idComment);
		instanceDataSC.setClassName(ConsistentOntology.SEMANTIC_COMMENT);
		PropertyMapData pmSC = new PropertyMapData();
		pmSC.setTypeClass(null);
		pmSC.setPropertyname(ConsistentOntology.HAS_TOPIC);
		for (TableItem item : table.getItems()) {
			MyComposite myComposite = (MyComposite) item.getData();
			for (String value : myComposite.getListDataValue()) {
				String pmValue = ConsistentOntology.DOC_NAMESPACE + value.trim() + "_Instance";
				System.out.println("----------VALUE--------: "+ pmValue);
				pmSC.setValue(pmValue);
			}

		}		
		instanceDataSC.getObjectPropertyList().add(pmSC);
		listSC.add(instanceDataSC);
		Service.webServiceDelegate.saveValuesOfIndividual(null, listSC, false);
		
		// add topic cho class hoac method

		List<InstanceData> listCM = new java.util.ArrayList<InstanceData>();
		InstanceData instanceDataCM = new InstanceData();
		String idComments[] = idComment.split("_");
		String instanceIdCM = idComments[0];  
		instanceDataCM.setInstanceID(instanceIdCM);
		if (typeSource.equals(NodeType.CLASS.name())) {
			instanceDataCM.setClassName(ConsistentOntology.CLASS);
		}
		if (typeSource.equals(NodeType.METHOD.name())) {
			instanceDataCM.setClassName(ConsistentOntology.METHOD);
		}
		PropertyMapData pmCM = new PropertyMapData();
		pmCM.setTypeClass(null);
		pmCM.setPropertyname(ConsistentOntology.HAS_TOPIC);
		for (TableItem item : table.getItems()) {
			MyComposite myComposite = (MyComposite) item.getData();
			for (String value : myComposite.getListDataValue()) {
				String pmValue = ConsistentOntology.DOC_NAMESPACE + value.trim() + "_Instance";
				System.out.println("----------VALUE--------: "+ pmValue);
				pmCM.setValue(pmValue);
			}

		}	
		instanceDataCM.getObjectPropertyList().add(pmCM);
		listCM.add(instanceDataCM);
		Service.webServiceDelegate.saveValuesOfIndividual(null, listCM, false);
	}
	private void writeFile(String fileName, String content) {
	       try {
	            FileWriter fw = new FileWriter(fileName);
	            fw.write(content);
	            fw.close();
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	        System.out.println("Success...");
	    }
	
	private String writeRdf(String id_semantic_comment) {
		String commentRdf = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" + "<!DOCTYPE rdf:RDF [\n"
				+ "  <!ENTITY swrl  \"http://www.w3.org/2003/11/swrl#\" >\n"
				+ "  <!ENTITY owl \"http://www.w3.org/2002/07/owl#\">\n"
				+ "  <!ENTITY rdf \"http://www.w3.org/1999/02/22-rdf-syntax-ns#\">\n"
				+ "  <!ENTITY rdfs \"http://www.w3.org/2000/01/rdf-schema#\">\n"
				+ "  <!ENTITY xsd \"http://www.w3.org/2001/XMLSchema#\">\n"
				+ "  <!ENTITY sourcecode \"http://hut.edu.vn/ontology/sourcecode#\" >\n"
				+ "  <!ENTITY document \"http://hut.edu.vn/ontology/document#\" >\n"
				+ "  <!ENTITY ruleml  \"http://www.w3.org/2003/11/ruleml#\" >\n" + "]>\n" + "<rdf:RDF\n"
				+ "  xmlns:owl=\"http://www.w3.org/2002/07/owl#\"\n"
				+ "  xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"\n"
				+ "            xmlns:semanticdoc=\"http://hut.edu.vn/ontology/semanticdoc\"\n"
				+ "  xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\"\n"
				+ "  xmlns:swrl=\"http://www.w3.org/2003/11/swrl#\"\n"
				+ "  xmlns:ruleml=\"http://www.w3.org/2003/11/ruleml#\">\n" + "\n"
				+ "  <swrl:Variable rdf:about=\"#semanticdocTopic\"/>\n"
				+ "  <swrl:Variable rdf:about=\"#semanticdocModel\"/>\n"
				+ "  <swrl:Variable rdf:about=\"#semanticdocFunction\"/>\n" + "\n"
				+ "  <swrl:Imp rdf:about=\"#semanticdoc\">\n" + "    <swrl:head rdf:parseType=\"Collection\">\n"
				+ "      <swrl:IndividualPropertyAtom>\n" + "        <swrl:propertyPredicate rdf:resource=\"";
		commentRdf = commentRdf + id_semantic_comment + "\"/>\n" + "        <swrl:argument1 rdf:resource=\"#semanticdocTopic\"/>\n"
				+ "        <swrl:argument2 rdf:resource=\"#semanticdocModel\"/>\n"
				+ "        <swrl:argument3 rdf:resource=\"#semanticdocFunction\"/>\n"
				+ "      </swrl:IndividualPropertyAtom>\n" + "    </swrl:head>\n" + "\n"
				+ "    <swrl:body rdf:parseType=\"Collection\">\n" + "      ";

		return commentRdf;
	}

	private String addPropertyComment(String propertyName, String value) {
		String commentContent = "";
		commentContent += "<rdf:Description rdf:about=\"" + propertyName + "\">\n" + "        <semanticdoc:value>"
				+ value + "</semanticdoc:value>\n" + "      </rdf:Description>\n" + "\n" + "      ";
		return commentContent;
	}

	private String generateRDFCommentFinal() {
		String comment = writeRdf(id);
		// Truong hop modifie, xoa tat removeAll cac relation,annotation
		// lien quan den individual do xong tao ra mot cai individual
		// moi co id cu
		for (TableItem item : table.getItems()) {
			String propertyName = (String) item.getData("propertyname");
			MyComposite myComposite = (MyComposite) item.getData();
			for (String value : myComposite.getListDataValue()) {
				comment += addPropertyComment(propertyName, value);
			}
		}
		comment += "\n" + "    </swrl:body>\n" + "  </swrl:Imp>\n" + "\n" + "</rdf:RDF>\n" + "";
		return comment;
	}

	/**
	 * @return Kiem tra chac chan la type source chi la Method hoac Classs.
	 */
	public boolean check() {
		if ((typeSource.equals(NodeType.CLASS.name())) || (typeSource.equals(NodeType.METHOD.name()))) {
			return true;
		}

		return false;
	}

	/**
	 *
	 * @param property
	 * @return
	 */
	private String standardizeComment(String property, String value) {
		// Chuan hoa thuoc tinh ontology thanh semanticdoc
		String result = standardizeProperty(property);

		// Tra ve semanticdoc
		if (result != "")
			return "\n * @" + result + " " + value;
		else
			return value;
	}

	// private String standardizeSemanticComment(String property, String value) {
	// String result = standardizeProperty(property);
	//
	// if (result != "")
	// return "\n * @Semanticdoc\n * @"+result+" "+value;
	// else
	// return "\n * @Semanticdoc\n" + value;
	// }
	//
	private String standardizeProperty(String property) {
		// Chuan hoa thuoc tinh ontology thanh semanticdoc
		String result = property.toLowerCase();
		if (result.contains("semanticdoctopic"))
			return "topic";
		if (result.contains("semanticdocmodel"))
			return "model";
		if (result.contains("semanticdocfunction"))
			return "function";
		return "";
	}

	@Override
	int updateInterface() {
		return 0;
	}
}
