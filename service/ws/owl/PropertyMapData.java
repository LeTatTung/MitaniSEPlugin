package ws.owl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for propertyMapData complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;propertyMapData&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;propertyname&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;typeClass&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;value&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "propertyMapData", propOrder = { "propertyname", "typeClass",
		"value" })
public class PropertyMapData {

	protected String propertyname;
	protected String typeClass;
	protected String value;

	/**
	 * Gets the value of the propertyname property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPropertyname() {
		return propertyname;
	}

	/**
	 * Sets the value of the propertyname property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPropertyname(String value) {
		this.propertyname = value;
	}

	/**
	 * Gets the value of the typeClass property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTypeClass() {
		return typeClass;
	}

	/**
	 * Sets the value of the typeClass property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTypeClass(String value) {
		this.typeClass = value;
	}

	/**
	 * Gets the value of the value property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value of the value property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
