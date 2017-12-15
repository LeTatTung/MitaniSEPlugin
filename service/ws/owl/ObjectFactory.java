package ws.owl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the ws.owl package.
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

	private final static QName _CheckExistIndividual_QNAME = new QName(
			"http://owl.ws/", "checkExistIndividual");
	private final static QName _ChangePasswordResponse_QNAME = new QName(
			"http://owl.ws/", "changePasswordResponse");
	private final static QName _IsInitited_QNAME = new QName("http://owl.ws/",
			"isInitited");
	private final static QName _IsInititedResponse_QNAME = new QName(
			"http://owl.ws/", "isInititedResponse");
	private final static QName _GetSubPropertiesResponse_QNAME = new QName(
			"http://owl.ws/", "getSubPropertiesResponse");
	private final static QName _ListProperties_QNAME = new QName(
			"http://owl.ws/", "listProperties");
	private final static QName _ValidateUser_QNAME = new QName(
			"http://owl.ws/", "validateUser");
	private final static QName _GetIndividualByShortName_QNAME = new QName(
			"http://owl.ws/", "getIndividualByShortName");
	private final static QName _AddDatatypeProperty_QNAME = new QName(
			"http://owl.ws/", "addDatatypeProperty");
	private final static QName _GetPropertySpecificDataType_QNAME = new QName(
			"http://owl.ws/", "getPropertySpecificDataType");
	private final static QName _AddLabelForResource_QNAME = new QName(
			"http://owl.ws/", "addLabelForResource");
	private final static QName _GetObjectPropertyRangesResponse_QNAME = new QName(
			"http://owl.ws/", "getObjectPropertyRangesResponse");
	private final static QName _GetDomainResponse_QNAME = new QName(
			"http://owl.ws/", "getDomainResponse");
	private final static QName _GetSubClassesResponse_QNAME = new QName(
			"http://owl.ws/", "getSubClassesResponse");
	private final static QName _BackupOWLResponse_QNAME = new QName(
			"http://owl.ws/", "backupOWLResponse");
	private final static QName _GetPropertyByShortNameResponse_QNAME = new QName(
			"http://owl.ws/", "getPropertyByShortNameResponse");
	private final static QName _ListAllRelatedInstance_QNAME = new QName(
			"http://owl.ws/", "listAllRelatedInstance");
	private final static QName _GetClassByName_QNAME = new QName(
			"http://owl.ws/", "getClassByName");
	private final static QName _SaveValuesOfIndividual_QNAME = new QName(
			"http://owl.ws/", "saveValuesOfIndividual");
	private final static QName _WriteToOWL_QNAME = new QName("http://owl.ws/",
			"writeToOWL");
	private final static QName _GetSuperClassesResponse_QNAME = new QName(
			"http://owl.ws/", "getSuperClassesResponse");
	private final static QName _GetLabelFromResourceResponse_QNAME = new QName(
			"http://owl.ws/", "getLabelFromResourceResponse");
	private final static QName _GetSuperProperties_QNAME = new QName(
			"http://owl.ws/", "getSuperProperties");
	private final static QName _RemoveUserResponse_QNAME = new QName(
			"http://owl.ws/", "removeUserResponse");
	private final static QName _GetObjectPropertyDomains_QNAME = new QName(
			"http://owl.ws/", "getObjectPropertyDomains");
	private final static QName _GetValuesOfIndividualResponse_QNAME = new QName(
			"http://owl.ws/", "getValuesOfIndividualResponse");
	private final static QName _AddObjectPropertyResponse_QNAME = new QName(
			"http://owl.ws/", "addObjectPropertyResponse");
	private final static QName _CreateInstanceResponse_QNAME = new QName(
			"http://owl.ws/", "createInstanceResponse");
	private final static QName _AddUser_QNAME = new QName("http://owl.ws/",
			"addUser");
	private final static QName _SparqlResultListResponse_QNAME = new QName(
			"http://owl.ws/", "SparqlResultListResponse");
	private final static QName _CreateInstance_QNAME = new QName(
			"http://owl.ws/", "createInstance");
	private final static QName _GetSuperPropertiesResponse_QNAME = new QName(
			"http://owl.ws/", "getSuperPropertiesResponse");
	private final static QName _GetShareData_QNAME = new QName(
			"http://owl.ws/", "getShareData");
	private final static QName _GetObjectPropertyRanges_QNAME = new QName(
			"http://owl.ws/", "getObjectPropertyRanges");
	private final static QName _GetPropertySpecificDataTypeResponse_QNAME = new QName(
			"http://owl.ws/", "getPropertySpecificDataTypeResponse");
	private final static QName _RemoveIndividual_QNAME = new QName(
			"http://owl.ws/", "removeIndividual");
	private final static QName _GetIndividualByNameResponse_QNAME = new QName(
			"http://owl.ws/", "getIndividualByNameResponse");
	private final static QName _SaveValuesOfIndividualResponse_QNAME = new QName(
			"http://owl.ws/", "saveValuesOfIndividualResponse");
	private final static QName _GetValueOfSpecificPropertyForIndividualResponse_QNAME = new QName(
			"http://owl.ws/", "getValueOfSpecificPropertyForIndividualResponse");
	private final static QName _GetAllClassProperties_QNAME = new QName(
			"http://owl.ws/", "getAllClassProperties");
	private final static QName _GetValuesOfIndividual_QNAME = new QName(
			"http://owl.ws/", "getValuesOfIndividual");
	private final static QName _SaveDocumentToOWL_QNAME = new QName(
			"http://owl.ws/", "saveDocumentToOWL");
	private final static QName _AddObjectProperty_QNAME = new QName(
			"http://owl.ws/", "addObjectProperty");
	private final static QName _ChangePassword_QNAME = new QName(
			"http://owl.ws/", "changePassword");
	private final static QName _GetSuperClasses_QNAME = new QName(
			"http://owl.ws/", "getSuperClasses");
	private final static QName _GetPropertyByShortName_QNAME = new QName(
			"http://owl.ws/", "getPropertyByShortName");
	private final static QName _ListClassesResponse_QNAME = new QName(
			"http://owl.ws/", "listClassesResponse");
	private final static QName _AddUserResponse_QNAME = new QName(
			"http://owl.ws/", "addUserResponse");
	private final static QName _GetShareDataResponse_QNAME = new QName(
			"http://owl.ws/", "getShareDataResponse");
	private final static QName _SaveDocumentToOWLResponse_QNAME = new QName(
			"http://owl.ws/", "saveDocumentToOWLResponse");
	private final static QName _GetDomain_QNAME = new QName("http://owl.ws/",
			"getDomain");
	private final static QName _ListClassInstanceResponse_QNAME = new QName(
			"http://owl.ws/", "listClassInstanceResponse");
	private final static QName _RemoveCodeIndividualResponse_QNAME = new QName(
			"http://owl.ws/", "removeCodeIndividualResponse");
	private final static QName _ListClasses_QNAME = new QName("http://owl.ws/",
			"listClasses");
	private final static QName _GetClassOfIndividualResponse_QNAME = new QName(
			"http://owl.ws/", "getClassOfIndividualResponse");
	private final static QName _GetPropertiesOfClassByRangeResponse_QNAME = new QName(
			"http://owl.ws/", "getPropertiesOfClassByRangeResponse");
	private final static QName _RemoveIndividualResponse_QNAME = new QName(
			"http://owl.ws/", "removeIndividualResponse");
	private final static QName _ListClassInstance_QNAME = new QName(
			"http://owl.ws/", "listClassInstance");
	private final static QName _GetIndividualByName_QNAME = new QName(
			"http://owl.ws/", "getIndividualByName");
	private final static QName _SparqlResultMapResponse_QNAME = new QName(
			"http://owl.ws/", "SparqlResultMapResponse");
	private final static QName _ListPropertiesResponse_QNAME = new QName(
			"http://owl.ws/", "listPropertiesResponse");
	private final static QName _ListAllRelatedInstanceResponse_QNAME = new QName(
			"http://owl.ws/", "listAllRelatedInstanceResponse");
	private final static QName _GetIndividualByShortNameResponse_QNAME = new QName(
			"http://owl.ws/", "getIndividualByShortNameResponse");
	private final static QName _RemoveCodeIndividual_QNAME = new QName(
			"http://owl.ws/", "removeCodeIndividual");
	private final static QName _GetPropertiesOfClassByRange_QNAME = new QName(
			"http://owl.ws/", "getPropertiesOfClassByRange");
	private final static QName _GetDocumentSaveDataResponse_QNAME = new QName(
			"http://owl.ws/", "getDocumentSaveDataResponse");
	private final static QName _ValidateUserResponse_QNAME = new QName(
			"http://owl.ws/", "validateUserResponse");
	private final static QName _GetPropertyByName_QNAME = new QName(
			"http://owl.ws/", "getPropertyByName");
	private final static QName _GetPropertyByNameResponse_QNAME = new QName(
			"http://owl.ws/", "getPropertyByNameResponse");
	private final static QName _ReloadOntology_QNAME = new QName(
			"http://owl.ws/", "reloadOntology");
	private final static QName _RemoveUser_QNAME = new QName("http://owl.ws/",
			"removeUser");
	private final static QName _GetValueOfSpecificPropertyForIndividual_QNAME = new QName(
			"http://owl.ws/", "getValueOfSpecificPropertyForIndividual");
	private final static QName _WriteToOWLResponse_QNAME = new QName(
			"http://owl.ws/", "writeToOWLResponse");
	private final static QName _GetClassByShortNameResponse_QNAME = new QName(
			"http://owl.ws/", "getClassByShortNameResponse");
	private final static QName _GetSubProperties_QNAME = new QName(
			"http://owl.ws/", "getSubProperties");
	private final static QName _GetDocumentSaveData_QNAME = new QName(
			"http://owl.ws/", "getDocumentSaveData");
	private final static QName _GetClassByNameResponse_QNAME = new QName(
			"http://owl.ws/", "getClassByNameResponse");
	private final static QName _AddLabelForResourceResponse_QNAME = new QName(
			"http://owl.ws/", "addLabelForResourceResponse");
	private final static QName _GetSubClasses_QNAME = new QName(
			"http://owl.ws/", "getSubClasses");
	private final static QName _SparqlResultMap_QNAME = new QName(
			"http://owl.ws/", "SparqlResultMap");
	private final static QName _CheckExistIndividualResponse_QNAME = new QName(
			"http://owl.ws/", "checkExistIndividualResponse");
	private final static QName _BackupOWL_QNAME = new QName("http://owl.ws/",
			"backupOWL");
	private final static QName _GetClassOfIndividual_QNAME = new QName(
			"http://owl.ws/", "getClassOfIndividual");
	private final static QName _SparqlResultList_QNAME = new QName(
			"http://owl.ws/", "SparqlResultList");
	private final static QName _GetClassByShortName_QNAME = new QName(
			"http://owl.ws/", "getClassByShortName");
	private final static QName _GetLabelFromResource_QNAME = new QName(
			"http://owl.ws/", "getLabelFromResource");
	private final static QName _AddDatatypePropertyResponse_QNAME = new QName(
			"http://owl.ws/", "addDatatypePropertyResponse");
	private final static QName _GetAllClassPropertiesResponse_QNAME = new QName(
			"http://owl.ws/", "getAllClassPropertiesResponse");
	private final static QName _ReloadOntologyResponse_QNAME = new QName(
			"http://owl.ws/", "reloadOntologyResponse");
	private final static QName _GetObjectPropertyDomainsResponse_QNAME = new QName(
			"http://owl.ws/", "getObjectPropertyDomainsResponse");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: ws.owl
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link SparqlResultListResponse }
	 * 
	 */
	public SparqlResultListResponse createSparqlResultListResponse() {
		return new SparqlResultListResponse();
	}

	/**
	 * Create an instance of {@link GetClassByNameResponse }
	 * 
	 */
	public GetClassByNameResponse createGetClassByNameResponse() {
		return new GetClassByNameResponse();
	}

	/**
	 * Create an instance of {@link SparqlResultMapResponse }
	 * 
	 */
	public SparqlResultMapResponse createSparqlResultMapResponse() {
		return new SparqlResultMapResponse();
	}

	/**
	 * Create an instance of {@link CreateInstance }
	 * 
	 */
	public CreateInstance createCreateInstance() {
		return new CreateInstance();
	}

	/**
	 * Create an instance of {@link IndividualData }
	 * 
	 */
	public IndividualData createIndividualData() {
		return new IndividualData();
	}

	/**
	 * Create an instance of {@link DocumentElement }
	 * 
	 */
	public DocumentElement createDocumentElement() {
		return new DocumentElement();
	}

	/**
	 * Create an instance of {@link BkIndividualProperty }
	 * 
	 */
	public BkIndividualProperty createBkIndividualProperty() {
		return new BkIndividualProperty();
	}

	/**
	 * Create an instance of {@link WriteToOWLResponse }
	 * 
	 */
	public WriteToOWLResponse createWriteToOWLResponse() {
		return new WriteToOWLResponse();
	}

	/**
	 * Create an instance of {@link ResourceData }
	 * 
	 */
	public ResourceData createResourceData() {
		return new ResourceData();
	}

	/**
	 * Create an instance of {@link ClassData }
	 * 
	 */
	public ClassData createClassData() {
		return new ClassData();
	}

	/**
	 * Create an instance of {@link RemoveUser }
	 * 
	 */
	public RemoveUser createRemoveUser() {
		return new RemoveUser();
	}

	/**
	 * Create an instance of {@link ListClassesResponse }
	 * 
	 */
	public ListClassesResponse createListClassesResponse() {
		return new ListClassesResponse();
	}

	/**
	 * Create an instance of {@link ListClassInstanceResponse }
	 * 
	 */
	public ListClassInstanceResponse createListClassInstanceResponse() {
		return new ListClassInstanceResponse();
	}

	/**
	 * Create an instance of {@link GetObjectPropertyRanges }
	 * 
	 */
	public GetObjectPropertyRanges createGetObjectPropertyRanges() {
		return new GetObjectPropertyRanges();
	}

	/**
	 * Create an instance of {@link IsInititedResponse }
	 * 
	 */
	public IsInititedResponse createIsInititedResponse() {
		return new IsInititedResponse();
	}

	/**
	 * Create an instance of {@link GetSuperProperties }
	 * 
	 */
	public GetSuperProperties createGetSuperProperties() {
		return new GetSuperProperties();
	}

	/**
	 * Create an instance of {@link GetPropertySpecificDataType }
	 * 
	 */
	public GetPropertySpecificDataType createGetPropertySpecificDataType() {
		return new GetPropertySpecificDataType();
	}

	/**
	 * Create an instance of {@link AddUser }
	 * 
	 */
	public AddUser createAddUser() {
		return new AddUser();
	}

	/**
	 * Create an instance of {@link ReloadOntology }
	 * 
	 */
	public ReloadOntology createReloadOntology() {
		return new ReloadOntology();
	}

	/**
	 * Create an instance of
	 * {@link GetValueOfSpecificPropertyForIndividualResponse }
	 * 
	 */
	public GetValueOfSpecificPropertyForIndividualResponse createGetValueOfSpecificPropertyForIndividualResponse() {
		return new GetValueOfSpecificPropertyForIndividualResponse();
	}

	/**
	 * Create an instance of {@link CreateInstanceResponse }
	 * 
	 */
	public CreateInstanceResponse createCreateInstanceResponse() {
		return new CreateInstanceResponse();
	}

	/**
	 * Create an instance of {@link GetAllClassPropertiesResponse }
	 * 
	 */
	public GetAllClassPropertiesResponse createGetAllClassPropertiesResponse() {
		return new GetAllClassPropertiesResponse();
	}

	/**
	 * Create an instance of {@link SaveDocumentToOWLResponse }
	 * 
	 */
	public SaveDocumentToOWLResponse createSaveDocumentToOWLResponse() {
		return new SaveDocumentToOWLResponse();
	}

	/**
	 * Create an instance of {@link GetClassByShortName }
	 * 
	 */
	public GetClassByShortName createGetClassByShortName() {
		return new GetClassByShortName();
	}

	/**
	 * Create an instance of {@link SparqlResultList }
	 * 
	 */
	public SparqlResultList createSparqlResultList() {
		return new SparqlResultList();
	}

	/**
	 * Create an instance of {@link RemoveUserResponse }
	 * 
	 */
	public RemoveUserResponse createRemoveUserResponse() {
		return new RemoveUserResponse();
	}

	/**
	 * Create an instance of {@link GetSuperClassesResponse }
	 * 
	 */
	public GetSuperClassesResponse createGetSuperClassesResponse() {
		return new GetSuperClassesResponse();
	}

	/**
	 * Create an instance of {@link GetObjectPropertyRangesResponse }
	 * 
	 */
	public GetObjectPropertyRangesResponse createGetObjectPropertyRangesResponse() {
		return new GetObjectPropertyRangesResponse();
	}

	/**
	 * Create an instance of {@link RemoveCodeIndividual }
	 * 
	 */
	public RemoveCodeIndividual createRemoveCodeIndividual() {
		return new RemoveCodeIndividual();
	}

	/**
	 * Create an instance of {@link GetObjectPropertyDomains }
	 * 
	 */
	public GetObjectPropertyDomains createGetObjectPropertyDomains() {
		return new GetObjectPropertyDomains();
	}

	/**
	 * Create an instance of {@link GetIndividualByNameResponse }
	 * 
	 */
	public GetIndividualByNameResponse createGetIndividualByNameResponse() {
		return new GetIndividualByNameResponse();
	}

	/**
	 * Create an instance of {@link WriteToOWL }
	 * 
	 */
	public WriteToOWL createWriteToOWL() {
		return new WriteToOWL();
	}

	/**
	 * Create an instance of {@link GetValuesOfIndividual }
	 * 
	 */
	public GetValuesOfIndividual createGetValuesOfIndividual() {
		return new GetValuesOfIndividual();
	}

	/**
	 * Create an instance of {@link GetPropertyByShortName }
	 * 
	 */
	public GetPropertyByShortName createGetPropertyByShortName() {
		return new GetPropertyByShortName();
	}

	/**
	 * Create an instance of {@link ListProperties }
	 * 
	 */
	public ListProperties createListProperties() {
		return new ListProperties();
	}

	/**
	 * Create an instance of {@link GetClassOfIndividual }
	 * 
	 */
	public GetClassOfIndividual createGetClassOfIndividual() {
		return new GetClassOfIndividual();
	}

	/**
	 * Create an instance of {@link PropertyMapData }
	 * 
	 */
	public PropertyMapData createPropertyMapData() {
		return new PropertyMapData();
	}

	/**
	 * Create an instance of {@link ListAllRelatedInstanceResponse }
	 * 
	 */
	public ListAllRelatedInstanceResponse createListAllRelatedInstanceResponse() {
		return new ListAllRelatedInstanceResponse();
	}

	/**
	 * Create an instance of {@link CheckExistIndividualResponse }
	 * 
	 */
	public CheckExistIndividualResponse createCheckExistIndividualResponse() {
		return new CheckExistIndividualResponse();
	}

	/**
	 * Create an instance of {@link GetAllClassProperties }
	 * 
	 */
	public GetAllClassProperties createGetAllClassProperties() {
		return new GetAllClassProperties();
	}

	/**
	 * Create an instance of {@link GetPropertyByShortNameResponse }
	 * 
	 */
	public GetPropertyByShortNameResponse createGetPropertyByShortNameResponse() {
		return new GetPropertyByShortNameResponse();
	}

	/**
	 * Create an instance of {@link SparqlResultMap }
	 * 
	 */
	public SparqlResultMap createSparqlResultMap() {
		return new SparqlResultMap();
	}

	/**
	 * Create an instance of {@link RemoveIndividual }
	 * 
	 */
	public RemoveIndividual createRemoveIndividual() {
		return new RemoveIndividual();
	}

	/**
	 * Create an instance of {@link AddDatatypeProperty }
	 * 
	 */
	public AddDatatypeProperty createAddDatatypeProperty() {
		return new AddDatatypeProperty();
	}

	/**
	 * Create an instance of {@link ShareData }
	 * 
	 */
	public ShareData createShareData() {
		return new ShareData();
	}

	/**
	 * Create an instance of {@link SaveValuesOfIndividual }
	 * 
	 */
	public SaveValuesOfIndividual createSaveValuesOfIndividual() {
		return new SaveValuesOfIndividual();
	}

	/**
	 * Create an instance of {@link AddLabelForResourceResponse }
	 * 
	 */
	public AddLabelForResourceResponse createAddLabelForResourceResponse() {
		return new AddLabelForResourceResponse();
	}

	/**
	 * Create an instance of {@link AddLabelForResource }
	 * 
	 */
	public AddLabelForResource createAddLabelForResource() {
		return new AddLabelForResource();
	}

	/**
	 * Create an instance of {@link GetClassByName }
	 * 
	 */
	public GetClassByName createGetClassByName() {
		return new GetClassByName();
	}

	/**
	 * Create an instance of {@link GetLabelFromResourceResponse }
	 * 
	 */
	public GetLabelFromResourceResponse createGetLabelFromResourceResponse() {
		return new GetLabelFromResourceResponse();
	}

	/**
	 * Create an instance of {@link GetValuesOfIndividualResponse }
	 * 
	 */
	public GetValuesOfIndividualResponse createGetValuesOfIndividualResponse() {
		return new GetValuesOfIndividualResponse();
	}

	/**
	 * Create an instance of {@link IsInitited }
	 * 
	 */
	public IsInitited createIsInitited() {
		return new IsInitited();
	}

	/**
	 * Create an instance of {@link ArrayListData }
	 * 
	 */
	public ArrayListData createArrayListData() {
		return new ArrayListData();
	}

	/**
	 * Create an instance of {@link GetLabelFromResource }
	 * 
	 */
	public GetLabelFromResource createGetLabelFromResource() {
		return new GetLabelFromResource();
	}

	/**
	 * Create an instance of {@link GetDocumentSaveData }
	 * 
	 */
	public GetDocumentSaveData createGetDocumentSaveData() {
		return new GetDocumentSaveData();
	}

	/**
	 * Create an instance of {@link ReloadOntologyResponse }
	 * 
	 */
	public ReloadOntologyResponse createReloadOntologyResponse() {
		return new ReloadOntologyResponse();
	}

	/**
	 * Create an instance of {@link BackupOWL }
	 * 
	 */
	public BackupOWL createBackupOWL() {
		return new BackupOWL();
	}

	/**
	 * Create an instance of {@link GetPropertySpecificDataTypeResponse }
	 * 
	 */
	public GetPropertySpecificDataTypeResponse createGetPropertySpecificDataTypeResponse() {
		return new GetPropertySpecificDataTypeResponse();
	}

	/**
	 * Create an instance of {@link Message }
	 * 
	 */
	public Message createMessage() {
		return new Message();
	}

	/**
	 * Create an instance of {@link QueryData }
	 * 
	 */
	public QueryData createQueryData() {
		return new QueryData();
	}

	/**
	 * Create an instance of {@link ListPropertiesResponse }
	 * 
	 */
	public ListPropertiesResponse createListPropertiesResponse() {
		return new ListPropertiesResponse();
	}

	/**
	 * Create an instance of {@link GetSuperClasses }
	 * 
	 */
	public GetSuperClasses createGetSuperClasses() {
		return new GetSuperClasses();
	}

	/**
	 * Create an instance of {@link SaveValuesOfIndividualResponse }
	 * 
	 */
	public SaveValuesOfIndividualResponse createSaveValuesOfIndividualResponse() {
		return new SaveValuesOfIndividualResponse();
	}

	/**
	 * Create an instance of {@link ListAllRelatedInstance }
	 * 
	 */
	public ListAllRelatedInstance createListAllRelatedInstance() {
		return new ListAllRelatedInstance();
	}

	/**
	 * Create an instance of {@link CheckExistIndividual }
	 * 
	 */
	public CheckExistIndividual createCheckExistIndividual() {
		return new CheckExistIndividual();
	}

	/**
	 * Create an instance of {@link GetDomainResponse }
	 * 
	 */
	public GetDomainResponse createGetDomainResponse() {
		return new GetDomainResponse();
	}

	/**
	 * Create an instance of {@link GetObjectPropertyDomainsResponse }
	 * 
	 */
	public GetObjectPropertyDomainsResponse createGetObjectPropertyDomainsResponse() {
		return new GetObjectPropertyDomainsResponse();
	}

	/**
	 * Create an instance of {@link GetClassOfIndividualResponse }
	 * 
	 */
	public GetClassOfIndividualResponse createGetClassOfIndividualResponse() {
		return new GetClassOfIndividualResponse();
	}

	/**
	 * Create an instance of {@link Section }
	 * 
	 */
	public Section createSection() {
		return new Section();
	}

	/**
	 * Create an instance of {@link AddObjectProperty }
	 * 
	 */
	public AddObjectProperty createAddObjectProperty() {
		return new AddObjectProperty();
	}

	/**
	 * Create an instance of {@link GetSubPropertiesResponse }
	 * 
	 */
	public GetSubPropertiesResponse createGetSubPropertiesResponse() {
		return new GetSubPropertiesResponse();
	}

	/**
	 * Create an instance of {@link GetIndividualByShortNameResponse }
	 * 
	 */
	public GetIndividualByShortNameResponse createGetIndividualByShortNameResponse() {
		return new GetIndividualByShortNameResponse();
	}

	/**
	 * Create an instance of {@link SaveDocumentToOWL }
	 * 
	 */
	public SaveDocumentToOWL createSaveDocumentToOWL() {
		return new SaveDocumentToOWL();
	}

	/**
	 * Create an instance of {@link AddDatatypePropertyResponse }
	 * 
	 */
	public AddDatatypePropertyResponse createAddDatatypePropertyResponse() {
		return new AddDatatypePropertyResponse();
	}

	/**
	 * Create an instance of {@link GetSubClassesResponse }
	 * 
	 */
	public GetSubClassesResponse createGetSubClassesResponse() {
		return new GetSubClassesResponse();
	}

	/**
	 * Create an instance of {@link AddObjectPropertyResponse }
	 * 
	 */
	public AddObjectPropertyResponse createAddObjectPropertyResponse() {
		return new AddObjectPropertyResponse();
	}

	/**
	 * Create an instance of {@link ValidateUser }
	 * 
	 */
	public ValidateUser createValidateUser() {
		return new ValidateUser();
	}

	/**
	 * Create an instance of {@link GetSubClasses }
	 * 
	 */
	public GetSubClasses createGetSubClasses() {
		return new GetSubClasses();
	}

	/**
	 * Create an instance of {@link ValidateUserResponse }
	 * 
	 */
	public ValidateUserResponse createValidateUserResponse() {
		return new ValidateUserResponse();
	}

	/**
	 * Create an instance of {@link InstanceData }
	 * 
	 */
	public InstanceData createInstanceData() {
		return new InstanceData();
	}

	/**
	 * Create an instance of {@link GetIndividualByName }
	 * 
	 */
	public GetIndividualByName createGetIndividualByName() {
		return new GetIndividualByName();
	}

	/**
	 * Create an instance of {@link GetPropertiesOfClassByRangeResponse }
	 * 
	 */
	public GetPropertiesOfClassByRangeResponse createGetPropertiesOfClassByRangeResponse() {
		return new GetPropertiesOfClassByRangeResponse();
	}

	/**
	 * Create an instance of {@link RemoveIndividualResponse }
	 * 
	 */
	public RemoveIndividualResponse createRemoveIndividualResponse() {
		return new RemoveIndividualResponse();
	}

	/**
	 * Create an instance of {@link GetShareDataResponse }
	 * 
	 */
	public GetShareDataResponse createGetShareDataResponse() {
		return new GetShareDataResponse();
	}

	/**
	 * Create an instance of {@link MapData }
	 * 
	 */
	public MapData createMapData() {
		return new MapData();
	}

	/**
	 * Create an instance of {@link DocumentSaveData }
	 * 
	 */
	public DocumentSaveData createDocumentSaveData() {
		return new DocumentSaveData();
	}

	/**
	 * Create an instance of {@link ChangePasswordResponse }
	 * 
	 */
	public ChangePasswordResponse createChangePasswordResponse() {
		return new ChangePasswordResponse();
	}

	/**
	 * Create an instance of {@link RemoveCodeIndividualResponse }
	 * 
	 */
	public RemoveCodeIndividualResponse createRemoveCodeIndividualResponse() {
		return new RemoveCodeIndividualResponse();
	}

	/**
	 * Create an instance of {@link AddUserResponse }
	 * 
	 */
	public AddUserResponse createAddUserResponse() {
		return new AddUserResponse();
	}

	/**
	 * Create an instance of {@link GetSubProperties }
	 * 
	 */
	public GetSubProperties createGetSubProperties() {
		return new GetSubProperties();
	}

	/**
	 * Create an instance of {@link ListClasses }
	 * 
	 */
	public ListClasses createListClasses() {
		return new ListClasses();
	}

	/**
	 * Create an instance of {@link GetPropertyByNameResponse }
	 * 
	 */
	public GetPropertyByNameResponse createGetPropertyByNameResponse() {
		return new GetPropertyByNameResponse();
	}

	/**
	 * Create an instance of {@link GetDomain }
	 * 
	 */
	public GetDomain createGetDomain() {
		return new GetDomain();
	}

	/**
	 * Create an instance of {@link GetSuperPropertiesResponse }
	 * 
	 */
	public GetSuperPropertiesResponse createGetSuperPropertiesResponse() {
		return new GetSuperPropertiesResponse();
	}

	/**
	 * Create an instance of {@link GetClassByShortNameResponse }
	 * 
	 */
	public GetClassByShortNameResponse createGetClassByShortNameResponse() {
		return new GetClassByShortNameResponse();
	}

	/**
	 * Create an instance of {@link BackupOWLResponse }
	 * 
	 */
	public BackupOWLResponse createBackupOWLResponse() {
		return new BackupOWLResponse();
	}

	/**
	 * Create an instance of {@link GetDocumentSaveDataResponse }
	 * 
	 */
	public GetDocumentSaveDataResponse createGetDocumentSaveDataResponse() {
		return new GetDocumentSaveDataResponse();
	}

	/**
	 * Create an instance of {@link GetPropertyByName }
	 * 
	 */
	public GetPropertyByName createGetPropertyByName() {
		return new GetPropertyByName();
	}

	/**
	 * Create an instance of {@link ChangePassword }
	 * 
	 */
	public ChangePassword createChangePassword() {
		return new ChangePassword();
	}

	/**
	 * Create an instance of {@link GetIndividualByShortName }
	 * 
	 */
	public GetIndividualByShortName createGetIndividualByShortName() {
		return new GetIndividualByShortName();
	}

	/**
	 * Create an instance of {@link GetPropertiesOfClassByRange }
	 * 
	 */
	public GetPropertiesOfClassByRange createGetPropertiesOfClassByRange() {
		return new GetPropertiesOfClassByRange();
	}

	/**
	 * Create an instance of {@link ListClassInstance }
	 * 
	 */
	public ListClassInstance createListClassInstance() {
		return new ListClassInstance();
	}

	/**
	 * Create an instance of {@link PropertyData }
	 * 
	 */
	public PropertyData createPropertyData() {
		return new PropertyData();
	}

	/**
	 * Create an instance of {@link GetShareData }
	 * 
	 */
	public GetShareData createGetShareData() {
		return new GetShareData();
	}

	/**
	 * Create an instance of {@link GetValueOfSpecificPropertyForIndividual }
	 * 
	 */
	public GetValueOfSpecificPropertyForIndividual createGetValueOfSpecificPropertyForIndividual() {
		return new GetValueOfSpecificPropertyForIndividual();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link CheckExistIndividual }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "checkExistIndividual")
	public JAXBElement<CheckExistIndividual> createCheckExistIndividual(
			CheckExistIndividual value) {
		return new JAXBElement<CheckExistIndividual>(
				_CheckExistIndividual_QNAME, CheckExistIndividual.class, null,
				value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ChangePasswordResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "changePasswordResponse")
	public JAXBElement<ChangePasswordResponse> createChangePasswordResponse(
			ChangePasswordResponse value) {
		return new JAXBElement<ChangePasswordResponse>(
				_ChangePasswordResponse_QNAME, ChangePasswordResponse.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link IsInitited }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "isInitited")
	public JAXBElement<IsInitited> createIsInitited(IsInitited value) {
		return new JAXBElement<IsInitited>(_IsInitited_QNAME, IsInitited.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link IsInititedResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "isInititedResponse")
	public JAXBElement<IsInititedResponse> createIsInititedResponse(
			IsInititedResponse value) {
		return new JAXBElement<IsInititedResponse>(_IsInititedResponse_QNAME,
				IsInititedResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetSubPropertiesResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getSubPropertiesResponse")
	public JAXBElement<GetSubPropertiesResponse> createGetSubPropertiesResponse(
			GetSubPropertiesResponse value) {
		return new JAXBElement<GetSubPropertiesResponse>(
				_GetSubPropertiesResponse_QNAME,
				GetSubPropertiesResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ListProperties }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "listProperties")
	public JAXBElement<ListProperties> createListProperties(ListProperties value) {
		return new JAXBElement<ListProperties>(_ListProperties_QNAME,
				ListProperties.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ValidateUser }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "validateUser")
	public JAXBElement<ValidateUser> createValidateUser(ValidateUser value) {
		return new JAXBElement<ValidateUser>(_ValidateUser_QNAME,
				ValidateUser.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetIndividualByShortName }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getIndividualByShortName")
	public JAXBElement<GetIndividualByShortName> createGetIndividualByShortName(
			GetIndividualByShortName value) {
		return new JAXBElement<GetIndividualByShortName>(
				_GetIndividualByShortName_QNAME,
				GetIndividualByShortName.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link AddDatatypeProperty }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "addDatatypeProperty")
	public JAXBElement<AddDatatypeProperty> createAddDatatypeProperty(
			AddDatatypeProperty value) {
		return new JAXBElement<AddDatatypeProperty>(_AddDatatypeProperty_QNAME,
				AddDatatypeProperty.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetPropertySpecificDataType }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getPropertySpecificDataType")
	public JAXBElement<GetPropertySpecificDataType> createGetPropertySpecificDataType(
			GetPropertySpecificDataType value) {
		return new JAXBElement<GetPropertySpecificDataType>(
				_GetPropertySpecificDataType_QNAME,
				GetPropertySpecificDataType.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link AddLabelForResource }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "addLabelForResource")
	public JAXBElement<AddLabelForResource> createAddLabelForResource(
			AddLabelForResource value) {
		return new JAXBElement<AddLabelForResource>(_AddLabelForResource_QNAME,
				AddLabelForResource.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetObjectPropertyRangesResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getObjectPropertyRangesResponse")
	public JAXBElement<GetObjectPropertyRangesResponse> createGetObjectPropertyRangesResponse(
			GetObjectPropertyRangesResponse value) {
		return new JAXBElement<GetObjectPropertyRangesResponse>(
				_GetObjectPropertyRangesResponse_QNAME,
				GetObjectPropertyRangesResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetDomainResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getDomainResponse")
	public JAXBElement<GetDomainResponse> createGetDomainResponse(
			GetDomainResponse value) {
		return new JAXBElement<GetDomainResponse>(_GetDomainResponse_QNAME,
				GetDomainResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetSubClassesResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getSubClassesResponse")
	public JAXBElement<GetSubClassesResponse> createGetSubClassesResponse(
			GetSubClassesResponse value) {
		return new JAXBElement<GetSubClassesResponse>(
				_GetSubClassesResponse_QNAME, GetSubClassesResponse.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link BackupOWLResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "backupOWLResponse")
	public JAXBElement<BackupOWLResponse> createBackupOWLResponse(
			BackupOWLResponse value) {
		return new JAXBElement<BackupOWLResponse>(_BackupOWLResponse_QNAME,
				BackupOWLResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetPropertyByShortNameResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getPropertyByShortNameResponse")
	public JAXBElement<GetPropertyByShortNameResponse> createGetPropertyByShortNameResponse(
			GetPropertyByShortNameResponse value) {
		return new JAXBElement<GetPropertyByShortNameResponse>(
				_GetPropertyByShortNameResponse_QNAME,
				GetPropertyByShortNameResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ListAllRelatedInstance }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "listAllRelatedInstance")
	public JAXBElement<ListAllRelatedInstance> createListAllRelatedInstance(
			ListAllRelatedInstance value) {
		return new JAXBElement<ListAllRelatedInstance>(
				_ListAllRelatedInstance_QNAME, ListAllRelatedInstance.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetClassByName }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getClassByName")
	public JAXBElement<GetClassByName> createGetClassByName(GetClassByName value) {
		return new JAXBElement<GetClassByName>(_GetClassByName_QNAME,
				GetClassByName.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link SaveValuesOfIndividual }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "saveValuesOfIndividual")
	public JAXBElement<SaveValuesOfIndividual> createSaveValuesOfIndividual(
			SaveValuesOfIndividual value) {
		return new JAXBElement<SaveValuesOfIndividual>(
				_SaveValuesOfIndividual_QNAME, SaveValuesOfIndividual.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link WriteToOWL }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "writeToOWL")
	public JAXBElement<WriteToOWL> createWriteToOWL(WriteToOWL value) {
		return new JAXBElement<WriteToOWL>(_WriteToOWL_QNAME, WriteToOWL.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetSuperClassesResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getSuperClassesResponse")
	public JAXBElement<GetSuperClassesResponse> createGetSuperClassesResponse(
			GetSuperClassesResponse value) {
		return new JAXBElement<GetSuperClassesResponse>(
				_GetSuperClassesResponse_QNAME, GetSuperClassesResponse.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetLabelFromResourceResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getLabelFromResourceResponse")
	public JAXBElement<GetLabelFromResourceResponse> createGetLabelFromResourceResponse(
			GetLabelFromResourceResponse value) {
		return new JAXBElement<GetLabelFromResourceResponse>(
				_GetLabelFromResourceResponse_QNAME,
				GetLabelFromResourceResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetSuperProperties }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getSuperProperties")
	public JAXBElement<GetSuperProperties> createGetSuperProperties(
			GetSuperProperties value) {
		return new JAXBElement<GetSuperProperties>(_GetSuperProperties_QNAME,
				GetSuperProperties.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link RemoveUserResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "removeUserResponse")
	public JAXBElement<RemoveUserResponse> createRemoveUserResponse(
			RemoveUserResponse value) {
		return new JAXBElement<RemoveUserResponse>(_RemoveUserResponse_QNAME,
				RemoveUserResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetObjectPropertyDomains }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getObjectPropertyDomains")
	public JAXBElement<GetObjectPropertyDomains> createGetObjectPropertyDomains(
			GetObjectPropertyDomains value) {
		return new JAXBElement<GetObjectPropertyDomains>(
				_GetObjectPropertyDomains_QNAME,
				GetObjectPropertyDomains.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetValuesOfIndividualResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getValuesOfIndividualResponse")
	public JAXBElement<GetValuesOfIndividualResponse> createGetValuesOfIndividualResponse(
			GetValuesOfIndividualResponse value) {
		return new JAXBElement<GetValuesOfIndividualResponse>(
				_GetValuesOfIndividualResponse_QNAME,
				GetValuesOfIndividualResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link AddObjectPropertyResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "addObjectPropertyResponse")
	public JAXBElement<AddObjectPropertyResponse> createAddObjectPropertyResponse(
			AddObjectPropertyResponse value) {
		return new JAXBElement<AddObjectPropertyResponse>(
				_AddObjectPropertyResponse_QNAME,
				AddObjectPropertyResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link CreateInstanceResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "createInstanceResponse")
	public JAXBElement<CreateInstanceResponse> createCreateInstanceResponse(
			CreateInstanceResponse value) {
		return new JAXBElement<CreateInstanceResponse>(
				_CreateInstanceResponse_QNAME, CreateInstanceResponse.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link AddUser }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "addUser")
	public JAXBElement<AddUser> createAddUser(AddUser value) {
		return new JAXBElement<AddUser>(_AddUser_QNAME, AddUser.class, null,
				value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link SparqlResultListResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "SparqlResultListResponse")
	public JAXBElement<SparqlResultListResponse> createSparqlResultListResponse(
			SparqlResultListResponse value) {
		return new JAXBElement<SparqlResultListResponse>(
				_SparqlResultListResponse_QNAME,
				SparqlResultListResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link CreateInstance }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "createInstance")
	public JAXBElement<CreateInstance> createCreateInstance(CreateInstance value) {
		return new JAXBElement<CreateInstance>(_CreateInstance_QNAME,
				CreateInstance.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetSuperPropertiesResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getSuperPropertiesResponse")
	public JAXBElement<GetSuperPropertiesResponse> createGetSuperPropertiesResponse(
			GetSuperPropertiesResponse value) {
		return new JAXBElement<GetSuperPropertiesResponse>(
				_GetSuperPropertiesResponse_QNAME,
				GetSuperPropertiesResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetShareData }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getShareData")
	public JAXBElement<GetShareData> createGetShareData(GetShareData value) {
		return new JAXBElement<GetShareData>(_GetShareData_QNAME,
				GetShareData.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetObjectPropertyRanges }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getObjectPropertyRanges")
	public JAXBElement<GetObjectPropertyRanges> createGetObjectPropertyRanges(
			GetObjectPropertyRanges value) {
		return new JAXBElement<GetObjectPropertyRanges>(
				_GetObjectPropertyRanges_QNAME, GetObjectPropertyRanges.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetPropertySpecificDataTypeResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getPropertySpecificDataTypeResponse")
	public JAXBElement<GetPropertySpecificDataTypeResponse> createGetPropertySpecificDataTypeResponse(
			GetPropertySpecificDataTypeResponse value) {
		return new JAXBElement<GetPropertySpecificDataTypeResponse>(
				_GetPropertySpecificDataTypeResponse_QNAME,
				GetPropertySpecificDataTypeResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link RemoveIndividual }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "removeIndividual")
	public JAXBElement<RemoveIndividual> createRemoveIndividual(
			RemoveIndividual value) {
		return new JAXBElement<RemoveIndividual>(_RemoveIndividual_QNAME,
				RemoveIndividual.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetIndividualByNameResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getIndividualByNameResponse")
	public JAXBElement<GetIndividualByNameResponse> createGetIndividualByNameResponse(
			GetIndividualByNameResponse value) {
		return new JAXBElement<GetIndividualByNameResponse>(
				_GetIndividualByNameResponse_QNAME,
				GetIndividualByNameResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link SaveValuesOfIndividualResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "saveValuesOfIndividualResponse")
	public JAXBElement<SaveValuesOfIndividualResponse> createSaveValuesOfIndividualResponse(
			SaveValuesOfIndividualResponse value) {
		return new JAXBElement<SaveValuesOfIndividualResponse>(
				_SaveValuesOfIndividualResponse_QNAME,
				SaveValuesOfIndividualResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetValueOfSpecificPropertyForIndividualResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getValueOfSpecificPropertyForIndividualResponse")
	public JAXBElement<GetValueOfSpecificPropertyForIndividualResponse> createGetValueOfSpecificPropertyForIndividualResponse(
			GetValueOfSpecificPropertyForIndividualResponse value) {
		return new JAXBElement<GetValueOfSpecificPropertyForIndividualResponse>(
				_GetValueOfSpecificPropertyForIndividualResponse_QNAME,
				GetValueOfSpecificPropertyForIndividualResponse.class, null,
				value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetAllClassProperties }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getAllClassProperties")
	public JAXBElement<GetAllClassProperties> createGetAllClassProperties(
			GetAllClassProperties value) {
		return new JAXBElement<GetAllClassProperties>(
				_GetAllClassProperties_QNAME, GetAllClassProperties.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetValuesOfIndividual }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getValuesOfIndividual")
	public JAXBElement<GetValuesOfIndividual> createGetValuesOfIndividual(
			GetValuesOfIndividual value) {
		return new JAXBElement<GetValuesOfIndividual>(
				_GetValuesOfIndividual_QNAME, GetValuesOfIndividual.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link SaveDocumentToOWL }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "saveDocumentToOWL")
	public JAXBElement<SaveDocumentToOWL> createSaveDocumentToOWL(
			SaveDocumentToOWL value) {
		return new JAXBElement<SaveDocumentToOWL>(_SaveDocumentToOWL_QNAME,
				SaveDocumentToOWL.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link AddObjectProperty }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "addObjectProperty")
	public JAXBElement<AddObjectProperty> createAddObjectProperty(
			AddObjectProperty value) {
		return new JAXBElement<AddObjectProperty>(_AddObjectProperty_QNAME,
				AddObjectProperty.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ChangePassword }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "changePassword")
	public JAXBElement<ChangePassword> createChangePassword(ChangePassword value) {
		return new JAXBElement<ChangePassword>(_ChangePassword_QNAME,
				ChangePassword.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetSuperClasses }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getSuperClasses")
	public JAXBElement<GetSuperClasses> createGetSuperClasses(
			GetSuperClasses value) {
		return new JAXBElement<GetSuperClasses>(_GetSuperClasses_QNAME,
				GetSuperClasses.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetPropertyByShortName }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getPropertyByShortName")
	public JAXBElement<GetPropertyByShortName> createGetPropertyByShortName(
			GetPropertyByShortName value) {
		return new JAXBElement<GetPropertyByShortName>(
				_GetPropertyByShortName_QNAME, GetPropertyByShortName.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ListClassesResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "listClassesResponse")
	public JAXBElement<ListClassesResponse> createListClassesResponse(
			ListClassesResponse value) {
		return new JAXBElement<ListClassesResponse>(_ListClassesResponse_QNAME,
				ListClassesResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link AddUserResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "addUserResponse")
	public JAXBElement<AddUserResponse> createAddUserResponse(
			AddUserResponse value) {
		return new JAXBElement<AddUserResponse>(_AddUserResponse_QNAME,
				AddUserResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetShareDataResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getShareDataResponse")
	public JAXBElement<GetShareDataResponse> createGetShareDataResponse(
			GetShareDataResponse value) {
		return new JAXBElement<GetShareDataResponse>(
				_GetShareDataResponse_QNAME, GetShareDataResponse.class, null,
				value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link SaveDocumentToOWLResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "saveDocumentToOWLResponse")
	public JAXBElement<SaveDocumentToOWLResponse> createSaveDocumentToOWLResponse(
			SaveDocumentToOWLResponse value) {
		return new JAXBElement<SaveDocumentToOWLResponse>(
				_SaveDocumentToOWLResponse_QNAME,
				SaveDocumentToOWLResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetDomain }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getDomain")
	public JAXBElement<GetDomain> createGetDomain(GetDomain value) {
		return new JAXBElement<GetDomain>(_GetDomain_QNAME, GetDomain.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ListClassInstanceResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "listClassInstanceResponse")
	public JAXBElement<ListClassInstanceResponse> createListClassInstanceResponse(
			ListClassInstanceResponse value) {
		return new JAXBElement<ListClassInstanceResponse>(
				_ListClassInstanceResponse_QNAME,
				ListClassInstanceResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link RemoveCodeIndividualResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "removeCodeIndividualResponse")
	public JAXBElement<RemoveCodeIndividualResponse> createRemoveCodeIndividualResponse(
			RemoveCodeIndividualResponse value) {
		return new JAXBElement<RemoveCodeIndividualResponse>(
				_RemoveCodeIndividualResponse_QNAME,
				RemoveCodeIndividualResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ListClasses }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "listClasses")
	public JAXBElement<ListClasses> createListClasses(ListClasses value) {
		return new JAXBElement<ListClasses>(_ListClasses_QNAME,
				ListClasses.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetClassOfIndividualResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getClassOfIndividualResponse")
	public JAXBElement<GetClassOfIndividualResponse> createGetClassOfIndividualResponse(
			GetClassOfIndividualResponse value) {
		return new JAXBElement<GetClassOfIndividualResponse>(
				_GetClassOfIndividualResponse_QNAME,
				GetClassOfIndividualResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetPropertiesOfClassByRangeResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getPropertiesOfClassByRangeResponse")
	public JAXBElement<GetPropertiesOfClassByRangeResponse> createGetPropertiesOfClassByRangeResponse(
			GetPropertiesOfClassByRangeResponse value) {
		return new JAXBElement<GetPropertiesOfClassByRangeResponse>(
				_GetPropertiesOfClassByRangeResponse_QNAME,
				GetPropertiesOfClassByRangeResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link RemoveIndividualResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "removeIndividualResponse")
	public JAXBElement<RemoveIndividualResponse> createRemoveIndividualResponse(
			RemoveIndividualResponse value) {
		return new JAXBElement<RemoveIndividualResponse>(
				_RemoveIndividualResponse_QNAME,
				RemoveIndividualResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ListClassInstance }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "listClassInstance")
	public JAXBElement<ListClassInstance> createListClassInstance(
			ListClassInstance value) {
		return new JAXBElement<ListClassInstance>(_ListClassInstance_QNAME,
				ListClassInstance.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetIndividualByName }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getIndividualByName")
	public JAXBElement<GetIndividualByName> createGetIndividualByName(
			GetIndividualByName value) {
		return new JAXBElement<GetIndividualByName>(_GetIndividualByName_QNAME,
				GetIndividualByName.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link SparqlResultMapResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "SparqlResultMapResponse")
	public JAXBElement<SparqlResultMapResponse> createSparqlResultMapResponse(
			SparqlResultMapResponse value) {
		return new JAXBElement<SparqlResultMapResponse>(
				_SparqlResultMapResponse_QNAME, SparqlResultMapResponse.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ListPropertiesResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "listPropertiesResponse")
	public JAXBElement<ListPropertiesResponse> createListPropertiesResponse(
			ListPropertiesResponse value) {
		return new JAXBElement<ListPropertiesResponse>(
				_ListPropertiesResponse_QNAME, ListPropertiesResponse.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ListAllRelatedInstanceResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "listAllRelatedInstanceResponse")
	public JAXBElement<ListAllRelatedInstanceResponse> createListAllRelatedInstanceResponse(
			ListAllRelatedInstanceResponse value) {
		return new JAXBElement<ListAllRelatedInstanceResponse>(
				_ListAllRelatedInstanceResponse_QNAME,
				ListAllRelatedInstanceResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetIndividualByShortNameResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getIndividualByShortNameResponse")
	public JAXBElement<GetIndividualByShortNameResponse> createGetIndividualByShortNameResponse(
			GetIndividualByShortNameResponse value) {
		return new JAXBElement<GetIndividualByShortNameResponse>(
				_GetIndividualByShortNameResponse_QNAME,
				GetIndividualByShortNameResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link RemoveCodeIndividual }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "removeCodeIndividual")
	public JAXBElement<RemoveCodeIndividual> createRemoveCodeIndividual(
			RemoveCodeIndividual value) {
		return new JAXBElement<RemoveCodeIndividual>(
				_RemoveCodeIndividual_QNAME, RemoveCodeIndividual.class, null,
				value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetPropertiesOfClassByRange }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getPropertiesOfClassByRange")
	public JAXBElement<GetPropertiesOfClassByRange> createGetPropertiesOfClassByRange(
			GetPropertiesOfClassByRange value) {
		return new JAXBElement<GetPropertiesOfClassByRange>(
				_GetPropertiesOfClassByRange_QNAME,
				GetPropertiesOfClassByRange.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetDocumentSaveDataResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getDocumentSaveDataResponse")
	public JAXBElement<GetDocumentSaveDataResponse> createGetDocumentSaveDataResponse(
			GetDocumentSaveDataResponse value) {
		return new JAXBElement<GetDocumentSaveDataResponse>(
				_GetDocumentSaveDataResponse_QNAME,
				GetDocumentSaveDataResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ValidateUserResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "validateUserResponse")
	public JAXBElement<ValidateUserResponse> createValidateUserResponse(
			ValidateUserResponse value) {
		return new JAXBElement<ValidateUserResponse>(
				_ValidateUserResponse_QNAME, ValidateUserResponse.class, null,
				value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetPropertyByName }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getPropertyByName")
	public JAXBElement<GetPropertyByName> createGetPropertyByName(
			GetPropertyByName value) {
		return new JAXBElement<GetPropertyByName>(_GetPropertyByName_QNAME,
				GetPropertyByName.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetPropertyByNameResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getPropertyByNameResponse")
	public JAXBElement<GetPropertyByNameResponse> createGetPropertyByNameResponse(
			GetPropertyByNameResponse value) {
		return new JAXBElement<GetPropertyByNameResponse>(
				_GetPropertyByNameResponse_QNAME,
				GetPropertyByNameResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ReloadOntology }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "reloadOntology")
	public JAXBElement<ReloadOntology> createReloadOntology(ReloadOntology value) {
		return new JAXBElement<ReloadOntology>(_ReloadOntology_QNAME,
				ReloadOntology.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link RemoveUser }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "removeUser")
	public JAXBElement<RemoveUser> createRemoveUser(RemoveUser value) {
		return new JAXBElement<RemoveUser>(_RemoveUser_QNAME, RemoveUser.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetValueOfSpecificPropertyForIndividual }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getValueOfSpecificPropertyForIndividual")
	public JAXBElement<GetValueOfSpecificPropertyForIndividual> createGetValueOfSpecificPropertyForIndividual(
			GetValueOfSpecificPropertyForIndividual value) {
		return new JAXBElement<GetValueOfSpecificPropertyForIndividual>(
				_GetValueOfSpecificPropertyForIndividual_QNAME,
				GetValueOfSpecificPropertyForIndividual.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link WriteToOWLResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "writeToOWLResponse")
	public JAXBElement<WriteToOWLResponse> createWriteToOWLResponse(
			WriteToOWLResponse value) {
		return new JAXBElement<WriteToOWLResponse>(_WriteToOWLResponse_QNAME,
				WriteToOWLResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetClassByShortNameResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getClassByShortNameResponse")
	public JAXBElement<GetClassByShortNameResponse> createGetClassByShortNameResponse(
			GetClassByShortNameResponse value) {
		return new JAXBElement<GetClassByShortNameResponse>(
				_GetClassByShortNameResponse_QNAME,
				GetClassByShortNameResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetSubProperties }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getSubProperties")
	public JAXBElement<GetSubProperties> createGetSubProperties(
			GetSubProperties value) {
		return new JAXBElement<GetSubProperties>(_GetSubProperties_QNAME,
				GetSubProperties.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetDocumentSaveData }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getDocumentSaveData")
	public JAXBElement<GetDocumentSaveData> createGetDocumentSaveData(
			GetDocumentSaveData value) {
		return new JAXBElement<GetDocumentSaveData>(_GetDocumentSaveData_QNAME,
				GetDocumentSaveData.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetClassByNameResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getClassByNameResponse")
	public JAXBElement<GetClassByNameResponse> createGetClassByNameResponse(
			GetClassByNameResponse value) {
		return new JAXBElement<GetClassByNameResponse>(
				_GetClassByNameResponse_QNAME, GetClassByNameResponse.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link AddLabelForResourceResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "addLabelForResourceResponse")
	public JAXBElement<AddLabelForResourceResponse> createAddLabelForResourceResponse(
			AddLabelForResourceResponse value) {
		return new JAXBElement<AddLabelForResourceResponse>(
				_AddLabelForResourceResponse_QNAME,
				AddLabelForResourceResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetSubClasses }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getSubClasses")
	public JAXBElement<GetSubClasses> createGetSubClasses(GetSubClasses value) {
		return new JAXBElement<GetSubClasses>(_GetSubClasses_QNAME,
				GetSubClasses.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link SparqlResultMap }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "SparqlResultMap")
	public JAXBElement<SparqlResultMap> createSparqlResultMap(
			SparqlResultMap value) {
		return new JAXBElement<SparqlResultMap>(_SparqlResultMap_QNAME,
				SparqlResultMap.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link CheckExistIndividualResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "checkExistIndividualResponse")
	public JAXBElement<CheckExistIndividualResponse> createCheckExistIndividualResponse(
			CheckExistIndividualResponse value) {
		return new JAXBElement<CheckExistIndividualResponse>(
				_CheckExistIndividualResponse_QNAME,
				CheckExistIndividualResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link BackupOWL }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "backupOWL")
	public JAXBElement<BackupOWL> createBackupOWL(BackupOWL value) {
		return new JAXBElement<BackupOWL>(_BackupOWL_QNAME, BackupOWL.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetClassOfIndividual }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getClassOfIndividual")
	public JAXBElement<GetClassOfIndividual> createGetClassOfIndividual(
			GetClassOfIndividual value) {
		return new JAXBElement<GetClassOfIndividual>(
				_GetClassOfIndividual_QNAME, GetClassOfIndividual.class, null,
				value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link SparqlResultList }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "SparqlResultList")
	public JAXBElement<SparqlResultList> createSparqlResultList(
			SparqlResultList value) {
		return new JAXBElement<SparqlResultList>(_SparqlResultList_QNAME,
				SparqlResultList.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetClassByShortName }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getClassByShortName")
	public JAXBElement<GetClassByShortName> createGetClassByShortName(
			GetClassByShortName value) {
		return new JAXBElement<GetClassByShortName>(_GetClassByShortName_QNAME,
				GetClassByShortName.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetLabelFromResource }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getLabelFromResource")
	public JAXBElement<GetLabelFromResource> createGetLabelFromResource(
			GetLabelFromResource value) {
		return new JAXBElement<GetLabelFromResource>(
				_GetLabelFromResource_QNAME, GetLabelFromResource.class, null,
				value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link AddDatatypePropertyResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "addDatatypePropertyResponse")
	public JAXBElement<AddDatatypePropertyResponse> createAddDatatypePropertyResponse(
			AddDatatypePropertyResponse value) {
		return new JAXBElement<AddDatatypePropertyResponse>(
				_AddDatatypePropertyResponse_QNAME,
				AddDatatypePropertyResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetAllClassPropertiesResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getAllClassPropertiesResponse")
	public JAXBElement<GetAllClassPropertiesResponse> createGetAllClassPropertiesResponse(
			GetAllClassPropertiesResponse value) {
		return new JAXBElement<GetAllClassPropertiesResponse>(
				_GetAllClassPropertiesResponse_QNAME,
				GetAllClassPropertiesResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ReloadOntologyResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "reloadOntologyResponse")
	public JAXBElement<ReloadOntologyResponse> createReloadOntologyResponse(
			ReloadOntologyResponse value) {
		return new JAXBElement<ReloadOntologyResponse>(
				_ReloadOntologyResponse_QNAME, ReloadOntologyResponse.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetObjectPropertyDomainsResponse }{@code >}}
	 * 
	 */
	@XmlElementDecl(namespace = "http://owl.ws/", name = "getObjectPropertyDomainsResponse")
	public JAXBElement<GetObjectPropertyDomainsResponse> createGetObjectPropertyDomainsResponse(
			GetObjectPropertyDomainsResponse value) {
		return new JAXBElement<GetObjectPropertyDomainsResponse>(
				_GetObjectPropertyDomainsResponse_QNAME,
				GetObjectPropertyDomainsResponse.class, null, value);
	}

}
