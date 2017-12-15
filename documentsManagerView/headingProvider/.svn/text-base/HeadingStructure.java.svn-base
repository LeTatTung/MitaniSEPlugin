package headingProvider;

import java.util.ArrayList;

import javax.swing.JTree;

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

	/**
	 * Them 1 mang cac nut vao cay heading
	 * 
	 * @param arrayHeadingNode
	 * @return
	 */
	public boolean addNewNode(ArrayList<HeadingNode> arrayHeadingNode) {
		for (int i = 0; i < arrayHeadingNode.size(); i++) {
			try {
				addNewNode(arrayHeadingNode.get(i).getContent(),
						arrayHeadingNode.get(i).getParent().getFullIndex());
			} catch (Exception e) {
				return false;
			}
		}
		return true;
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

}
