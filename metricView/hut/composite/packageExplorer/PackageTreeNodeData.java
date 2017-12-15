package hut.composite.packageExplorer;

public class PackageTreeNodeData {
	private String type;
	private String name;	
	private String value;
	public String getName() {
		return name;
	}
	public String getValue()
	{
		return value;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * type duoc dinh nghia trong ontology.images.Images
	 * */
	public PackageTreeNodeData(String name, String type, String value) {
		super();
		this.name = name;
		this.type = type;		
		this.value = value;
	}	
	public PackageTreeNodeData(String name, String type) {
		super();
		this.name = name;
		this.type = type;		
	}	
	public PackageTreeNodeData(String name) {
		super();
		this.name = name;
	}
	
	public String toString()
	{
		return this.name;
	}

}
