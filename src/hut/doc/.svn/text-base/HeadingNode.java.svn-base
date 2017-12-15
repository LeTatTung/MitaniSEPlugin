package hut.doc;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * 
 * This class used to define a node of heading structure
 * 
 * @author Mr Hien
 * 
 */
public class HeadingNode {

	int level;
	int index;
	int pageNumber;
	int pageNext;
	String content;
	HeadingNode parent;
	ArrayList<HeadingNode> childrens;
	ArrayList<Integer> fullIndex;
	ArrayList<String> nodeSection;
	String Id;
	
	public void setNodeSection(ArrayList<String> nodeSection) {
		this.nodeSection = nodeSection;
	}

	public ArrayList<String> getNodeSection() {
		return nodeSection;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public HeadingNode() {
		// TODO Auto-generated constructor stub
		content = "";
		parent = null;
		childrens = new ArrayList<HeadingNode>();
		fullIndex = new ArrayList<Integer>();
		nodeSection = new ArrayList<String>();
	}


	/**
	 * This method is used to create node with same data with exits node
	 * 
	 * @param dataNode
	 * @return
	 */
	public boolean setHeadingNode(HeadingNode dataNode) {
		try {
			this.setId(dataNode.getId());
			this.setLevel(dataNode.getLevel());
			this.setIndex(dataNode.getIndex());
			this.setContent(dataNode.getContent());
			this.setChildrens(dataNode.getChildrens());
			this.setParent(dataNode.getParent());
			this.setPageNumber(dataNode.getPageNumber());
			this.setPageNext(dataNode.getPageNext());
			this.setFullIndex(dataNode.getFullIndex());
			this.setNodeSection(dataNode.getNodeSection());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	/**
	 * This method is used to get all content in a heading node
	 * 
	 * @return void
	 */
	public void showContent() {
		StringBuffer content = new StringBuffer("");
		for (int i = 0; i < this.getLevel(); i++) {
			content.append("  ");
		}

		// Display itself content
		content.append(this.getFullIndexAsString() + " " + this.getContent());
		System.out.println(content);

		// Display sub content
		for (int i = 0; i < this.getChildrens().size(); i++) {
			this.getChildrens().get(i).showContent();
		}
	}

	public DefaultMutableTreeNode getTreeNode() {
		DefaultMutableTreeNode thisNode = new DefaultMutableTreeNode(this
				.getFullIndexAsString()
				+ " " + this.getContent());
		for (int i = 0; i < this.getChildrens().size(); i++) {
			thisNode.add(this.getChildrens().get(i).getTreeNode());
		}
		return thisNode;
	}

	/**
	 * This method is used to add a children node into current node
	 * 
	 * @param childrentContent
	 * @return boolean
	 */
	public boolean addAChildrent(String childrentContent) {
		try {
			HeadingNode tempNode = new HeadingNode();
			tempNode.setContent(childrentContent);
			tempNode.setLevel(this.level + 1);
			tempNode.setIndex(this.getChildrens().size() + 1);
			tempNode.setParent(this);
			tempNode.getFullIndex().addAll(fullIndex);
			tempNode.getFullIndex().add(Integer.valueOf(tempNode.getIndex()));
			this.getChildrens().add(tempNode);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getCause());
			return false;
		}
		return true;
	}
	
	public boolean addAChildrent(String childrentContent, int pageNumber) {
		try {
			HeadingNode tempNode = new HeadingNode();
			tempNode.setContent(childrentContent);
			tempNode.setPageNumber(pageNumber);
			tempNode.setLevel(this.level + 1);
			tempNode.setIndex(this.getChildrens().size() + 1);
			tempNode.setParent(this);
			tempNode.getFullIndex().addAll(fullIndex);
			tempNode.getFullIndex().add(Integer.valueOf(tempNode.getIndex()));
			this.getChildrens().add(tempNode);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getCause());
			return false;
		}
		return true;
	}

	/**
	 * This method is used to search a children of current node by childrenIndex
	 * 
	 * @param childrenIndex
	 * @return HeadingNode
	 */
	public HeadingNode searchChildrentNode(int childrenIndex) {
		HeadingNode sesultNode = null;
		try {
			if (this.getChildrens().size() > (childrenIndex - 1)) {
				sesultNode = this.getChildrens().get(childrenIndex - 1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return sesultNode;
	}

	/**
	 * This method is used to find a lower level node by fullIndex
	 * 
	 * @param fullIndex
	 * @return
	 */
	public HeadingNode searchLowerNode(ArrayList<Integer> fullIndexOfSearchNode) {
		HeadingNode currentNode = this;
		HeadingNode result = null;
		//currentNode = this;
		ArrayList<Integer> fullIndexOfCurrentNode = new ArrayList<Integer>();
		fullIndexOfCurrentNode.addAll(this.getFullIndex());

		int fullIndexOfSearchNodeSize = fullIndexOfSearchNode.size();
		int fullIndexOfCurentNodeSize = fullIndexOfCurrentNode.size();
		

		if (fullIndexOfSearchNodeSize == fullIndexOfCurentNodeSize) {
			boolean flag = true;
			for (int i = 0; i < fullIndexOfCurrentNode.size(); i++) {
				if (fullIndexOfCurrentNode.get(i) != fullIndexOfSearchNode
						.get(i))
					flag = false;
			}
			if (flag) {
				result = currentNode;
			}
		} else if (fullIndexOfSearchNodeSize > fullIndexOfCurentNodeSize) {
				if (currentNode.searchChildrentNode(fullIndexOfSearchNode
						.get(fullIndexOfCurentNodeSize)) != null) {
					result = currentNode.searchChildrentNode(
							fullIndexOfSearchNode
									.get(fullIndexOfCurentNodeSize))
							.searchLowerNode(fullIndexOfSearchNode);
				}
		}

		return result;
	}

	// All getters method and setters method of this class
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public HeadingNode getParent() {
		return parent;
	}

	public void setParent(HeadingNode parent) {
		this.parent = parent;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public ArrayList<HeadingNode> getChildrens() {
		return childrens;
	}

	public void setChildrens(ArrayList<HeadingNode> childrens) {
		this.childrens = childrens;
	}

	public ArrayList<Integer> getFullIndex() {
		return fullIndex;
	}

	public ArrayList<String> getSection() {
		return nodeSection;
	}

	public void setNodeSection(String fileName) {
		ArrayList<String> tempNode = new ArrayList<String>();
		tempNode.add("Item " + getFullIndexAsString());
		tempNode.add(content);
		//tempNode.add(fileName+"_section" + getFullIndexAsString()+"_page"+pageNumber);
		if (this.getId()!=null){	
			tempNode.add(this.getId());
		}
		else{
			tempNode.add("Have no ID!!!");
		}
		
		tempNode.add("section" + getFullIndexAsString());// La section thu may
		tempNode.add(Integer.toString(pageNumber));      // Thuoc page bao nhieu
		tempNode.add(Integer.toString(index));
		nodeSection.addAll(tempNode);
	}

	public String getFullIndexAsString() {
		StringBuffer tempFullIndexAsString = new StringBuffer();
		for (int i = 0; i < this.getFullIndex().size(); i++) {
			tempFullIndexAsString.append(this.getFullIndex().get(i) + ".");
		}
		return tempFullIndexAsString.toString();
	}

	public void setFullIndex(ArrayList<Integer> fullIndex) {
		this.fullIndex = fullIndex;
	}
	
	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageNext() {
		return pageNext;
	}

	public void setPageNext(int pageNext) {
		this.pageNext = pageNext;
	}
}
