package hut.composite.tree;

import java.util.List;

import com.hp.hpl.jena.ontology.Individual;

/**
 * @author KienCuong
 * Class khai bao kieu du lieu cua tree
 */
public class TreeNodeData {
	private String name;//Dung cho truong hop node gia, la node Library, con cua project, ko fai la 1 individual ma chua cac individual la cac file jar 
	private String type;
	private String typeSource;
	private Individual individual;
	private String title;
	private int pageStart;
	private int pageEnd;
	private String id;
    private List<String> listype;
	
	
	public String getId() {
		return id;
	}

	/**
	 * type duoc dinh nghia trong ontology.images.Images
	 * */
	public TreeNodeData(Individual individual, String type) {
		super();
		this.individual = individual;
		this.type = type;		
	}
	
	/**
	 * @param name
	 * @param type
	 * @param typeSource
	 * @param listype cac kieu vi du nhu public ,static ,...
	 * @param id  id cua individual
	 */
	public TreeNodeData(String name, String type,String typeSource,List<String> listype,String id) {
		super();
		this.name = name;
		this.type = type;
		this.typeSource = typeSource;
		this.listype = listype;
		this.id= id;
		this.individual=null;
	}
	public TreeNodeData(String lable,String type, String id){
		super();
		this.name = lable;
		this.id= id;
		this.typeSource = type;
	}
	public TreeNodeData(String title,int pageStart,int pageEnd){
		super();
		this.title= title;
		this.pageStart = pageStart;
		this.pageEnd = pageEnd;
	}
	
	public int getPageStart() {
		return pageStart;
	}

	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}

	public int getPageEnd() {
		return pageEnd;
	}

	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}

	public Individual getIndividual() {
		return individual;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setIndividual(Individual individual) {
		this.individual = individual;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String toString(int type)
	{
		
		
		switch (type) {
		case 0:
		{
			
		}
		case 1:
			return this.name;
		case 2:
			return this.individual.getLocalName();
		case 3:
			return this.title;
		default:
			return null;
		}
		
	}

	public String getTypeSource() {
		return typeSource;
	}

	public void setTypeSource(String typeSource) {
		this.typeSource = typeSource;
	}

	public List<String> getListype() {
		return listype;
	}

}

