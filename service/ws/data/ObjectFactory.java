package ws.data;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the ws.data package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _SparqlResultList_QNAME = new QName(
			"http://data.ws/", "SparqlResultList");
	private final static QName _ListClassInstanceResponse_QNAME = new QName(
			"http://data.ws/", "listClassInstanceResponse");
	private final static QName _ProcessAutoAnalyze_QNAME = new QName(
			"http://data.ws/", "processAutoAnalyze");
	private final static QName _GetNodeDataResponse_QNAME = new QName(
			"http://data.ws/", "getNodeDataResponse");
	private final static QName _GetValuePropertyIndividualResponse_QNAME = new QName(
			"http://data.ws/", "getValuePropertyIndividualResponse");
	private final static QName _IsInititedResponse_QNAME = new QName(
			"http://data.ws/", "isInititedResponse");
	private final static QName _CreatArtifactInstanceResponse_QNAME = new QName(
			"http://data.ws/", "creatArtifactInstanceResponse");
	private final static QName _GetClassDataResponse_QNAME = new QName(
			"http://data.ws/", "getClassDataResponse");
	private final static QName _SaveAnnotationClient_QNAME = new QName(
			"http://data.ws/", "saveAnnotationClient");
	private final static QName _ReloadOntology_QNAME = new QName(
			"http://data.ws/", "reloadOntology");
	private final static QName _GetNodeData_QNAME = new QName(
			"http://data.ws/", "getNodeData");
	private final static QName _ReloadOntologyResponse_QNAME = new QName(
			"http://data.ws/", "reloadOntologyResponse");
	private final static QName _SaveAnnotationClientResponse_QNAME = new QName(
			"http://data.ws/", "saveAnnotationClientResponse");
	private final static QName _SparqlResultListResponse_QNAME = new QName(
			"http://data.ws/", "SparqlResultListResponse");
	private final static QName _GetValuePropertyIndividual_QNAME = new QName(
			"http://data.ws/", "getValuePropertyIndividual");
	private final static QName _ListClassInstance_QNAME = new QName(
			"http://data.ws/", "listClassInstance");
	private final static QName _CreatArtifactInstance_QNAME = new QName(
			"http://data.ws/", "creatArtifactInstance");
	private final static QName _IsInitited_QNAME = new QName("http://data.ws/",
			"isInitited");
	private final static QName _GetClassData_QNAME = new QName(
			"http://data.ws/", "getClassData");
	private final static QName _ProcessAutoAnalyzeResponse_QNAME = new QName(
			"http://data.ws/", "processAutoAnalyzeResponse");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: ws.data
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link NodeData }
	 * 
	 */
	public NodeData createNodeData() {
		return new NodeData();
	}

	/**
	 * Create an instance of {@link GetNodeDataResponse }
	 * 
	 */
	public GetNodeDataResponse createGetNodeDataResponse() {
		return new GetNodeDataResponse();
	}

	/**
	 * Create an instance of {@link SparqlResultListResponse }
	 * 
	 */
	public SparqlResultListResponse createSparqlResultListResponse() {
		return new SparqlResultListResponse();
	}

	/**
	 * Create an instance of {@link CreatArtifactInstance }
	 * 
	 */
	public CreatArtifactInstance createCreatArtifactInstance() {
		return new CreatArtifactInstance();
	}

	/**
	 * Create an instance of {@link GetClassDataResponse }
	 * 
	 */
	public GetClassDataResponse createGetClassDataResponse() {
		return new GetClassDataResponse();
	}

	/**
	 * Create an instance of {@link ListClassInstance }
	 * 
	 */
	public ListClassInstance createListClassInstance() {
		return new ListClassInstance();
	}

	/**
	 * Create an instance of {@link MapData }
	 * 
	 */
	public MapData createMapData() {
		return new MapData();
	}

	/**
	 * Create an instance of {@link InstanceData }
	 * 
	 */
	public InstanceData createInstanceData() {
		return new InstanceData();
	}

	/**
	 * Create an instance of {@link SaveAnnotationClient }
	 * 
	 */
	public SaveAnnotationClient createSaveAnnotationClient() {
		return new SaveAnnotationClient();
	}

	/**
	 * Create an instance of {@link ListClassInstanceResponse }
	 * 
	 */
	public ListClassInstanceResponse createListClassInstanceResponse() {
		return new ListClassInstanceResponse();
	}

	/**
	 * Create an instance of {@link GetClassData }
	 * 
	 */
	public GetClassData createGetClassData() {
		return new GetClassData();
	}

	/**
	 * Create an instance of {@link SparqlResultList }
	 * 
	 */
	public SparqlResultList createSparqlResultList() {
		return new SparqlResultList();
	}

	/**
	 * Create an instance of {@link SaveAnnotationClientResponse }
	 * 
	 */
	public SaveAnnotationClientResponse createSaveAnnotationClientResponse() {
		return new SaveAnnotationClientResponse();
	}

	/**
	 * Create an instance of {@link IsInititedResponse }
	 * 
	 */
	public IsInititedResponse createIsInititedResponse() {
		return new IsInititedResponse();
	}

	/**
	 * Create an instance of {@link IsInitited }
	 * 
	 */
	public IsInitited createIsInitited() {
		return new IsInitited();
	}

	/**
	 * Create an instance of {@link GetNodeData }
	 * 
	 */
	public GetNodeData createGetNodeData() {
		return new GetNodeData();
	}

	/**
	 * Create an instance of {@link ArrayListData }
	 * 
	 */
	public ArrayListData createArrayListData() {
		return new ArrayListData();
	}

	/**
	 * Create an instance of {@link CreatArtifactInstanceResponse }
	 * 
	 */
	public CreatArtifactInstanceResponse createCreatArtifactInstanceResponse() {
		return new CreatArtifactInstanceResponse();
	}

	/**
	 * Create an instance of {@link ProcessAutoAnalyze }
	 * 
	 */
	public ProcessAutoAnalyze createProcessAutoAnalyze() {
		return new ProcessAutoAnalyze();
	}

	/**
	 * Create an instance of {@link ReloadOntology }
	 * 
	 */
	public ReloadOntology createReloadOntology() {
		return new ReloadOntology();
	}

	/**
	 * Create an instance of {@link GetValuePropertyIndividualResponse }
	 * 
	 */
	public GetValuePropertyIndividualResponse createGetValuePropertyIndividualResponse() {
		return new GetValuePropertyIndividualResponse();
	}

	/**
	 * Create an instance of {@link ReloadOntologyResponse }
	 * 
	 */
	public ReloadOntologyResponse createReloadOntologyResponse() {
		return new ReloadOntologyResponse();
	}

	/**
	 * Create an instance of {@link GetValuePropertyIndividual }
	 * 
	 */
	public GetValuePropertyIndividual createGetValuePropertyIndividual() {
		return new GetValuePropertyIndividual();
	}

	/**
	 * Create an instance of {@link ProcessAutoAnalyzeResponse }
	 * 
	 */
	public ProcessAutoAnalyzeResponse createProcessAutoAnalyzeResponse() {
		return new ProcessAutoAnalyzeResponse();
	}

	/**
	 * Create an instance of {@link DocumentData }
	 * 
	 */
	public DocumentData createDocumentData() {
		return new DocumentData();
	}

	/**
	 * Create an instance of {@link PropertyMapData }
	 * 
	 */
	public PropertyMapData createPropertyMapData() {
		return new PropertyMapData();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link SparqlResultList }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://data.ws/", name = "SparqlResultList")
	public JAXBElement<SparqlResultList> createSparqlResultList(
			SparqlResultList value) {
		return new JAXBElement<SparqlResultList>(_SparqlResultList_QNAME,
				SparqlResultList.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ListClassInstanceResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://data.ws/", name = "listClassInstanceResponse")
	public JAXBElement<ListClassInstanceResponse> createListClassInstanceResponse(
			ListClassInstanceResponse value) {
		return new JAXBElement<ListClassInstanceResponse>(
				_ListClassInstanceResponse_QNAME,
				ListClassInstanceResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ProcessAutoAnalyze }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://data.ws/", name = "processAutoAnalyze")
	public JAXBElement<ProcessAutoAnalyze> createProcessAutoAnalyze(
			ProcessAutoAnalyze value) {
		return new JAXBElement<ProcessAutoAnalyze>(_ProcessAutoAnalyze_QNAME,
				ProcessAutoAnalyze.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetNodeDataResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://data.ws/", name = "getNodeDataResponse")
	public JAXBElement<GetNodeDataResponse> createGetNodeDataResponse(
			GetNodeDataResponse value) {
		return new JAXBElement<GetNodeDataResponse>(_GetNodeDataResponse_QNAME,
				GetNodeDataResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetValuePropertyIndividualResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://data.ws/", name = "getValuePropertyIndividualResponse")
	public JAXBElement<GetValuePropertyIndividualResponse> createGetValuePropertyIndividualResponse(
			GetValuePropertyIndividualResponse value) {
		return new JAXBElement<GetValuePropertyIndividualResponse>(
				_GetValuePropertyIndividualResponse_QNAME,
				GetValuePropertyIndividualResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link IsInititedResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://data.ws/", name = "isInititedResponse")
	public JAXBElement<IsInititedResponse> createIsInititedResponse(
			IsInititedResponse value) {
		return new JAXBElement<IsInititedResponse>(_IsInititedResponse_QNAME,
				IsInititedResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link CreatArtifactInstanceResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://data.ws/", name = "creatArtifactInstanceResponse")
	public JAXBElement<CreatArtifactInstanceResponse> createCreatArtifactInstanceResponse(
			CreatArtifactInstanceResponse value) {
		return new JAXBElement<CreatArtifactInstanceResponse>(
				_CreatArtifactInstanceResponse_QNAME,
				CreatArtifactInstanceResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetClassDataResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://data.ws/", name = "getClassDataResponse")
	public JAXBElement<GetClassDataResponse> createGetClassDataResponse(
			GetClassDataResponse value) {
		return new JAXBElement<GetClassDataResponse>(
				_GetClassDataResponse_QNAME, GetClassDataResponse.class, null,
				value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link SaveAnnotationClient }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://data.ws/", name = "saveAnnotationClient")
	public JAXBElement<SaveAnnotationClient> createSaveAnnotationClient(
			SaveAnnotationClient value) {
		return new JAXBElement<SaveAnnotationClient>(
				_SaveAnnotationClient_QNAME, SaveAnnotationClient.class, null,
				value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ReloadOntology }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://data.ws/", name = "reloadOntology")
	public JAXBElement<ReloadOntology> createReloadOntology(ReloadOntology value) {
		return new JAXBElement<ReloadOntology>(_ReloadOntology_QNAME,
				ReloadOntology.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetNodeData }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://data.ws/", name = "getNodeData")
	public JAXBElement<GetNodeData> createGetNodeData(GetNodeData value) {
		return new JAXBElement<GetNodeData>(_GetNodeData_QNAME,
				GetNodeData.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ReloadOntologyResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://data.ws/", name = "reloadOntologyResponse")
	public JAXBElement<ReloadOntologyResponse> createReloadOntologyResponse(
			ReloadOntologyResponse value) {
		return new JAXBElement<ReloadOntologyResponse>(
				_ReloadOntologyResponse_QNAME, ReloadOntologyResponse.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link SaveAnnotationClientResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://data.ws/", name = "saveAnnotationClientResponse")
	public JAXBElement<SaveAnnotationClientResponse> createSaveAnnotationClientResponse(
			SaveAnnotationClientResponse value) {
		return new JAXBElement<SaveAnnotationClientResponse>(
				_SaveAnnotationClientResponse_QNAME,
				SaveAnnotationClientResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link SparqlResultListResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://data.ws/", name = "SparqlResultListResponse")
	public JAXBElement<SparqlResultListResponse> createSparqlResultListResponse(
			SparqlResultListResponse value) {
		return new JAXBElement<SparqlResultListResponse>(
				_SparqlResultListResponse_QNAME,
				SparqlResultListResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetValuePropertyIndividual }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://data.ws/", name = "getValuePropertyIndividual")
	public JAXBElement<GetValuePropertyIndividual> createGetValuePropertyIndividual(
			GetValuePropertyIndividual value) {
		return new JAXBElement<GetValuePropertyIndividual>(
				_GetValuePropertyIndividual_QNAME,
				GetValuePropertyIndividual.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ListClassInstance }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://data.ws/", name = "listClassInstance")
	public JAXBElement<ListClassInstance> createListClassInstance(
			ListClassInstance value) {
		return new JAXBElement<ListClassInstance>(_ListClassInstance_QNAME,
				ListClassInstance.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link CreatArtifactInstance }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://data.ws/", name = "creatArtifactInstance")
	public JAXBElement<CreatArtifactInstance> createCreatArtifactInstance(
			CreatArtifactInstance value) {
		return new JAXBElement<CreatArtifactInstance>(
				_CreatArtifactInstance_QNAME, CreatArtifactInstance.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link IsInitited }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://data.ws/", name = "isInitited")
	public JAXBElement<IsInitited> createIsInitited(IsInitited value) {
		return new JAXBElement<IsInitited>(_IsInitited_QNAME, IsInitited.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetClassData }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://data.ws/", name = "getClassData")
	public JAXBElement<GetClassData> createGetClassData(GetClassData value) {
		return new JAXBElement<GetClassData>(_GetClassData_QNAME,
				GetClassData.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ProcessAutoAnalyzeResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://data.ws/", name = "processAutoAnalyzeResponse")
	public JAXBElement<ProcessAutoAnalyzeResponse> createProcessAutoAnalyzeResponse(
			ProcessAutoAnalyzeResponse value) {
		return new JAXBElement<ProcessAutoAnalyzeResponse>(
				_ProcessAutoAnalyzeResponse_QNAME,
				ProcessAutoAnalyzeResponse.class, null, value);
	}

}
