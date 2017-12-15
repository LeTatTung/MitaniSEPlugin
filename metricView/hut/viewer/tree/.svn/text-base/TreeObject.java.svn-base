package hut.viewer.tree;

import org.eclipse.core.runtime.IAdaptable;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntProperty;

public class TreeObject implements IAdaptable {
	private Object data;
	private TreeParent parent;
	
	/**
	 * Doi tuong luu tru khi phan tich source code
	 */
	private Object ast;
	
	public Object getAst() {
		return ast;
	}
	public void setAst(Object ast) {
		this.ast = ast;
	}
	public TreeObject(Object data, TreeParent parent)
	{
		this.data = data;
		this.parent = parent;
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
		if (data instanceof OntClass) 
		{
			OntClass cls = (OntClass) data;
			return cls.getLocalName();
		}
		else if(data instanceof OntProperty)
		{
			OntProperty pro = (OntProperty) data;
			return pro.getLocalName();
		}
		return this.data.toString();
	}
	
	/*
	 * Dung cho chuc nang nhieu language
	 */
	public String toString(int indexLanguage) {

		if (data instanceof OntClass) 
		{
			OntClass cls = (OntClass) data;
			String s="";
			switch (indexLanguage) {
			case 0:
				s=cls.getLocalName();
				break;
			case 1:
				s=cls.getLabel("en");
				break;
			case 2:
				s=cls.getLabel("vn");
				break;
			default:
				s="";
				break;
			}
			if (s==null)
				s=cls.getLocalName();
			return s;
		}
		else if(data instanceof OntProperty)
		{
			OntProperty pro = (OntProperty) data;
			String s="";
			switch (indexLanguage) {
			case 0:
				s=pro.getLocalName();
				break;
			case 1:
				s=pro.getLabel("en");
				break;
			case 2:
				s=pro.getLabel("vn");
				break;
			default:
				s="";
				break;
			}
			if (s==null)
				s=pro.getLocalName();
			return s;
		}
		return this.data.toString();
		
	}
	@SuppressWarnings("unchecked")
	public Object getAdapter(Class key) {
		return null;
	}	
}
