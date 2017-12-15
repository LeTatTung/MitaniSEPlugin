package ws.data;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for nodeData complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;nodeData&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;childList&quot; type=&quot;{http://data.ws/}nodeData&quot; maxOccurs=&quot;unbounded&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;id&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;label&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;listtype&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; maxOccurs=&quot;unbounded&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;nodetype&quot; type=&quot;{http://data.ws/}nodeType&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;parent&quot; type=&quot;{http://data.ws/}nodeData&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "nodeData", propOrder = { "childList", "id", "label",
		"listtype", "nodetype", "parent" })
public class NodeData {

	@XmlElement(nillable = true)
	protected List<NodeData> childList;
	protected String id;
	protected String label;
	@XmlElement(nillable = true)
	protected List<String> listtype;
	protected NodeType nodetype;
	protected NodeData parent;

	/**
	 * Gets the value of the childList property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the childList property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getChildList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link NodeData }
	 * 
	 * 
	 */
	public List<NodeData> getChildList() {
		if (childList == null) {
			childList = new ArrayList<NodeData>();
		}
		return this.childList;
	}

	/**
	 * Gets the value of the id property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the value of the id property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setId(String value) {
		this.id = value;
	}

	/**
	 * Gets the value of the label property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the value of the label property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLabel(String value) {
		this.label = value;
	}

	/**
	 * Gets the value of the listtype property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the listtype property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getListtype().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link String }
	 * 
	 * 
	 */
	public List<String> getListtype() {
		if (listtype == null) {
			listtype = new ArrayList<String>();
		}
		return this.listtype;
	}

	/**
	 * Gets the value of the nodetype property.
	 * 
	 * @return possible object is {@link NodeType }
	 * 
	 */
	public NodeType getNodetype() {
		return nodetype;
	}

	/**
	 * Sets the value of the nodetype property.
	 * 
	 * @param value
	 *            allowed object is {@link NodeType }
	 * 
	 */
	public void setNodetype(NodeType value) {
		this.nodetype = value;
	}

	/**
	 * Gets the value of the parent property.
	 * 
	 * @return possible object is {@link NodeData }
	 * 
	 */
	public NodeData getParent() {
		return parent;
	}

	/**
	 * Sets the value of the parent property.
	 * 
	 * @param value
	 *            allowed object is {@link NodeData }
	 * 
	 */
	public void setParent(NodeData value) {
		this.parent = value;
	}

}
