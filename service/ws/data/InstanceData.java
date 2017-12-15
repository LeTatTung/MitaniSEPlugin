package ws.data;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for instanceData complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;instanceData&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;className&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;dataPropertyList&quot; type=&quot;{http://data.ws/}propertyMapData&quot; maxOccurs=&quot;unbounded&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;instanceID&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;instanceLabel&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;objectPropertyList&quot; type=&quot;{http://data.ws/}propertyMapData&quot; maxOccurs=&quot;unbounded&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "instanceData", propOrder = { "className", "dataPropertyList",
		"instanceID", "instanceLabel", "objectPropertyList" })
public class InstanceData {

	protected String className;
	@XmlElement(nillable = true)
	protected List<PropertyMapData> dataPropertyList;
	protected String instanceID;
	protected String instanceLabel;
	@XmlElement(nillable = true)
	protected List<PropertyMapData> objectPropertyList;

	/**
	 * Gets the value of the className property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * Sets the value of the className property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setClassName(String value) {
		this.className = value;
	}

	/**
	 * Gets the value of the dataPropertyList property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the dataPropertyList property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getDataPropertyList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link PropertyMapData }
	 * 
	 * 
	 */
	public List<PropertyMapData> getDataPropertyList() {
		if (dataPropertyList == null) {
			dataPropertyList = new ArrayList<PropertyMapData>();
		}
		return this.dataPropertyList;
	}

	/**
	 * Gets the value of the instanceID property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getInstanceID() {
		return instanceID;
	}

	/**
	 * Sets the value of the instanceID property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setInstanceID(String value) {
		this.instanceID = value;
	}

	/**
	 * Gets the value of the instanceLabel property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getInstanceLabel() {
		return instanceLabel;
	}

	/**
	 * Sets the value of the instanceLabel property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setInstanceLabel(String value) {
		this.instanceLabel = value;
	}

	/**
	 * Gets the value of the objectPropertyList property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the objectPropertyList property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getObjectPropertyList().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link PropertyMapData }
	 * 
	 * 
	 */
	public List<PropertyMapData> getObjectPropertyList() {
		if (objectPropertyList == null) {
			objectPropertyList = new ArrayList<PropertyMapData>();
		}
		return this.objectPropertyList;
	}

	public void setDataPropertyList(List<PropertyMapData> dataPropertyList) {
		this.dataPropertyList = dataPropertyList;
	}

	public void setObjectPropertyList(List<PropertyMapData> objectPropertyList) {
		this.objectPropertyList = objectPropertyList;
	}

}
