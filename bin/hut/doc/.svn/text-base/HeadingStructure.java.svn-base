package hut.doc;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTree;

import ws.owl.Section;

/**
 * 
 * This class used to define a heading
 * 
 * @author Mr Hien
 * 
 */
public class HeadingStructure {
	HeadingNode root;
	int nowLevel;
	int endPageNumber;
	String documentName;
	String documentAuthor;

	public HeadingStructure() {
		// TODO Auto-generated constructor stub
		root = new HeadingNode();
		nowLevel = 0;
		documentAuthor = "Unknow";
		documentName = "Unknow";
	}

	/**
	 * Constructor with parameter
	 * 
	 * @param documentAutor
	 * @param documentName
	 */
	public HeadingStructure(String documentName, String documentAutor) {
		this.documentName = documentName;
		this.documentAuthor = documentAutor;
		root = new HeadingNode();
		root.setFullIndex(new ArrayList<Integer>());
		root.setIndex(0);
		root.setLevel(0);
		root.setContent(documentName);
	}

	/**
	 * This method used to add a new node to heading if add a first heading
	 * fullIndexOfParent is empty
	 * 
	 * @param content
	 * @param level
	 * @param parentIndex
	 * @return
	 */
	public boolean addNewNode(String content,
			ArrayList<Integer> fullIndexOfParent) {
		if (fullIndexOfParent.isEmpty()) {
			this.getRoot().addAChildrent(content);
		} else {
			HeadingNode parentNode = new HeadingNode();
			try {
				parentNode = this.getRoot().searchLowerNode(fullIndexOfParent);
				if (parentNode == null) {
					return false;
				} else
					parentNode.addAChildrent(content);
			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}
		}
		return true;
	}

	/**
	 * Them 1 nut vao cay
	 * 
	 * @param content
	 * @param fullIndexOfParent
	 * @param pageNumber
	 * @return
	 */
	public boolean addNewNode(String content,
			ArrayList<Integer> fullIndexOfParent, int pageNumber) {
		if (fullIndexOfParent.isEmpty()) {
			this.getRoot().addAChildrent(content, pageNumber);
		} else {
			HeadingNode parentNode = new HeadingNode();
			try {
				parentNode = this.getRoot().searchLowerNode(fullIndexOfParent);
				if (parentNode == null) {
					return false;
				} else
					parentNode.addAChildrent(content, pageNumber);
			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}
		}
		return true;
	}

	/**
	 * Them 1 nut vao cay heading
	 * 
	 * @param headingNode
	 * @return
	 */
	public boolean addNewNode(HeadingNode headingNode) {
		try {
			addNewNode(headingNode.getContent(), headingNode.getParent()
					.getFullIndex());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public int getNextPageNumberOfThisNode(HeadingNode thisNode){
		HeadingNode nextNode = null;
		ArrayList<Integer> fullIndexOfThisNode = new ArrayList<Integer>();
		ArrayList<Integer> fullIndexOfNextNode = new ArrayList<Integer>();
		fullIndexOfThisNode = thisNode.getFullIndex();
		
		//tao gia tri cho fullIndexOfNextNode
		for (int i=0; i<fullIndexOfThisNode.size()-1; i++){
			fullIndexOfNextNode.add(fullIndexOfThisNode.get(i));
		}
		fullIndexOfNextNode.add(fullIndexOfThisNode.get(fullIndexOfThisNode.size()-1)+1);
		
		// Neu tim thay nextNode trong cay heading thi tra ve
		try {
			nextNode = thisNode.getParent().searchLowerNode(fullIndexOfNextNode);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		if (nextNode!=null){
			return nextNode.getPageNumber();
		}
		else{
			if (thisNode.getParent().getLevel()>0){
				return getNextPageNumberOfThisNode(thisNode.getParent());
			}
			else return this.getEndPageNumber();
		}
	}

	/**
	 * This method used to add a new node to heading if add a first heading
	 * fullIndexOfParent is empty
	 * 
	 * @param content
	 * @param fullIndexOfParentArray
	 * @return
	 */
	public boolean addNewNode(String content, int[] fullIndexOfParentArray) {
		if (fullIndexOfParentArray.length == 0) {
			this.root.addAChildrent(content);
		} else {
			ArrayList<Integer> fullIndexOfParent = new ArrayList<Integer>();
			for (int i = 0; i < fullIndexOfParentArray.length; i++) {
				fullIndexOfParent.add(fullIndexOfParentArray[i]);
			}
			HeadingNode parentNode = new HeadingNode();
			try {
				parentNode = this.getRoot().searchLowerNode(fullIndexOfParent);
				parentNode.addAChildrent(content);
			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}
		}
		return true;
	}

	/**
	 * This method used to search a node of Heading
	 * 
	 * @param level
	 * @param index
	 * @return
	 */
	public HeadingNode searchNode(ArrayList<Integer> fullIndex) {
		if (fullIndex.isEmpty()) {
			return root;
		} else {
			return this.getRoot().searchLowerNode(fullIndex);
		}
	}

	public void showContent() {
		this.getRoot().showContent();
	}

	public JTree getJTree() {
		JTree jTreeOfThisHeadingStructure = new JTree(this.getRoot()
				.getTreeNode());
		return jTreeOfThisHeadingStructure;
	}

	// All getters method and setters method of this class
	public HeadingNode getRoot() {
		return root;
	}

	public void setRoot(HeadingNode root) {
		this.root = root;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentAuthor() {
		return documentAuthor;
	}

	public void setDocumentAuthor(String documentAuthor) {
		this.documentAuthor = documentAuthor;
	}

	public int getEndPageNumber() {
		return endPageNumber;
	}

	public void setEndPageNumber(int endPageNumber) {
		this.endPageNumber = endPageNumber;
	}

	public List<List> getSectionData(HeadingNode node) {
		ArrayList<List> sectionData = new ArrayList<List>();
		sectionData.add(node.getSection());
		for(int i=0; i<node.getChildrens().size(); i++)
		{
			List<List> tempList = getSectionData(node.getChildrens().get(i));
			for(int j =0; j<tempList.size(); j++){
				sectionData.add(tempList.get(j));
			}
		}
		return sectionData;
	}

	// tao du lieu cho cac node con cua Heading tu rootSection lay tu server
	public void setHeadingNodeFromSection(HeadingNode headingNode, Section rootSection) {
		HeadingMatcher headingMatcher = new HeadingMatcher();
		// chuyen du lieu tu rootSection sang headingNode
		
		headingNode.setId(rootSection.getId());
		
		// lay ra full index
		String fullIndexAsString = rootSection.getFullIndex()+" ";
		ArrayList<Integer> fullIndex = new ArrayList<Integer>();
		if (fullIndexAsString != " "){
		try {
			fullIndex.addAll(headingMatcher.getFullIndexFromString(fullIndexAsString));
			headingNode.setFullIndex(fullIndex);
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		// lay ra index
		if (fullIndex.size()>0){
		try {
			headingNode.setIndex(fullIndex.get(fullIndex.size()-1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		
		// lay ra level
		try {
			headingNode.setLevel(rootSection.getLevel());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		headingNode.setContent(rootSection.getTextContent());
		headingNode.setPageNumber(rootSection.getBeginPage());
		headingNode.setPageNext(rootSection.getEndPage());
		headingNode.setNodeSection(this.getDocumentName());
		
		// voi tat ca cac con, chuyen du lieu con cua rootSection vao con cua headingNode
		int childrensSize = rootSection.getChildrens().size();
		if (childrensSize>0){
			headingNode.setChildrens(new ArrayList<HeadingNode>());
			for(int i=0; i<childrensSize; i++){
				headingNode.getChildrens().add(new HeadingNode());
			}
			for(int i=0; i<childrensSize; i++){
				HeadingNode childNode = new HeadingNode();
				setHeadingNodeFromSection(childNode, rootSection.getChildrens().get(i));
				//headingNode.getChildrens().add(childNode);
				headingNode.getChildrens().get(childNode.getIndex()-1).setHeadingNode(childNode);
			}
		}		
	}
}
