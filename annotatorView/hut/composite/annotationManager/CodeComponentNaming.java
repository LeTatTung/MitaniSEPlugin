package hut.composite.annotationManager;

import mintani.valueconst.ConsistentOntology;

public class CodeComponentNaming {
	private String idWorkspace;
	private String idWorkspaceFull;
	private String idProject;
	private String idProjectFull;
	private String idSourceFolder;
	private String idSourceFolderFull;
	private String idPackage;
	private String idPackageFull;
	private String idSourceFile;
	private String idSourceFileFull;
	private String idClass;
	private String idClassFull;
	private String idSubClass;
	private String idSubClassFull;
	private String idMetric;
	private String idMetricFull;
	private String idField;
	private String idFieldFull;
	private String idMethod;
	private String idMethodFull;
	private String idReturnType;
	private String idReturnTypeFull;
	private String idVariable;
	private String idVariableFull;
	private String idInterface;
	private String idInterfaceFull;
	private String idCommentClass;
	private String idCommentClassFull;
	private String idCommentMethod;
	private String idCommentMethodFull;
	private String idCommentField;
	private String idCommentFieldFull;
	
	/**
	 * Dung khi add them code moi de quan ly
	 * @return
	 */
	public void setIdWorkspaceFull(String idWorkspaceFull) {
		this.idWorkspaceFull = idWorkspaceFull;
	}
	
	
	public String getIdMetric() {
		return idMetric;
	}
	public String getIdCommentClass() {
		return idCommentClass;
	}
	public String getIdCommentClassFull() {
		return idCommentClassFull;
	}
	public String getIdCommentMethod() {
		return idCommentMethod;
	}
	public String getIdCommentMethodFull() {
		return idCommentMethodFull;
	}
	public String getIdCommentField() {
		return idCommentField;
	}
	public String getIdCommentFieldFull() {
		return idCommentFieldFull;
	}
	public String getIdMetricFull() {
		return idMetricFull;
	}
	public String getIdSubClass() {
		return idSubClass;
	}
	public String getIdSubClassFull() {
		return idSubClassFull;
	}
	public String getIdReturnType() {
		return idReturnType;
	}
	public String getIdReturnTypeFull() {
		return idReturnTypeFull;
	}
	public void setIdWorkspace(String idWorkspace) {
		this.idWorkspace = idWorkspace;
		this.idWorkspaceFull = ConsistentOntology.SEC_NAMESPACE+idWorkspace;
	}
	public void setIdProject(String idProject) {
		this.idProject = idProject;
		this.idProjectFull = this.idWorkspaceFull+"."+idProject;
	}
	public void setIdSourceFolder(String idSourceFolder) {
		this.idSourceFolder = idSourceFolder;
		this.idSourceFolderFull = this.idProjectFull+"."+idSourceFolder;
	}
	public void setIdPackage(String idPackage) {
		this.idPackage = idPackage;
		this.idPackageFull = this.idSourceFolderFull+"."+idPackage;
	}
	public void setIdSourceFile(String idSourceFile) {
		this.idSourceFile = idSourceFile;
		this.idSourceFileFull = this.idPackageFull+"."+idSourceFile;
	}
	public void setIdInterface(String idInterface) {
		this.idInterface = idInterface;
		this.idInterfaceFull = this.idPackageFull+"."+idInterface;
	}
	public void setIdClass(String idClass) {
		this.idClass = idClass;
		this.idClassFull = this.idPackageFull+"."+idClass;
	}
	public void setIdSubClass(String idSubClass) {
		this.idSubClass = idSubClass;
		this.idSubClassFull = this.idClassFull+"$"+idSubClass;
	}
	
	public void setIdCommentClass() {
		this.idCommentClassFull = this.idClassFull+"_comment";
	}
	public void setIdMetric() {
		this.idMetric = this.idClass+"_Metric";
		this.idMetricFull = this.idClassFull+"_Metric";
	}
	
	public void setIdField(String idField) {
		this.idField = idField;
		this.idFieldFull = this.idClassFull+"."+idField;
	}
	
	public void setIdCommentField() {
		this.idCommentFieldFull = this.idFieldFull+"_comment";
	}
	public void setIdMethod(String idMethod) {
		this.idMethod = idMethod;
		this.idMethodFull = this.idClassFull+"/"+idMethod;
	}
	public void setIdCommentMethod() {
		this.idCommentMethodFull = this.idMethodFull+"_comment";
	}
	public void setIdReturnType(String idReturnType) {
		this.idReturnType = idReturnType;
		this.idReturnTypeFull = this.idSourceFolderFull+"."+idReturnType;
	}
	public void setIdVariable(String idVariable) {
		this.idVariable = idVariable;
		this.idVariableFull = this.idMethodFull+"."+idVariable;
	}
	public String getIdInterface() {
		return idInterface;
	}
	public String getIdInterfaceFull() {
		return idInterfaceFull;
	}	
	
	public String getIdWorkspace() {
		return idWorkspace;
	}
	public String getIdWorkspaceFull() {
		return idWorkspaceFull;
	}
	public String getIdProject() {
		return idProject;
	}
	public String getIdProjectFull() {
		return idProjectFull;
	}
	public String getIdSourceFolder() {
		return idSourceFolder;
	}
	public String getIdSourceFolderFull() {
		return idSourceFolderFull;
	}
	public String getIdPackage() {
		return idPackage;
	}
	public String getIdPackageFull() {
		return idPackageFull;
	}
	public String getIdSourceFile() {
		return idSourceFile;
	}
	public String getIdSourceFileFull() {
		return idSourceFileFull;
	}
	public String getIdClass() {
		return idClass;
	}
	public String getIdClassFull() {
		return idClassFull;
	}
	public String getIdField() {
		return idField;
	}
	public String getIdFieldFull() {
		return idFieldFull;
	}
	public String getIdMethod() {
		return idMethod;
	}
	public String getIdMethodFull() {
		return idMethodFull;
	}
	public String getIdVariable() {
		return idVariable;
	}
	public String getIdVariableFull() {
		return idVariableFull;
	}
	
	
	
	
}

