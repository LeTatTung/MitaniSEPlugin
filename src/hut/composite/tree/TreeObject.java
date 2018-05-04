package hut.composite.tree;

import org.eclipse.core.runtime.IAdaptable;


/**
 * @author KienCuong
 * Class lien quan toi cay thu muc, can viet tach roi ra
 */
public class TreeObject implements IAdaptable {
	private Object data;
	private TreeParent parent;
	private Object ast;

	public TreeObject(Object data, TreeParent parent) {
		this.data = data;
		this.parent = parent;
	}
	
	public Object getAst() {
		return ast;
	}

	public void setAst(Object ast) {
		this.ast = ast;
	}


	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setParent(TreeParent parent) {
		this.parent = parent;
	}

	public TreeParent getParent() {
		return parent;
	}

	public String toString() {
		return this.data.toString();
	}

	public Object getAdapter(Class key) {
		return null;
	}
	
}
