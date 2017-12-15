package ws.owl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for propertyData complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;propertyData&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;datatypeProperty&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}boolean&quot;/&gt;
 *         &lt;element name=&quot;enComment&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;enLabel&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;hasSubProperty&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}boolean&quot;/&gt;
 *         &lt;element name=&quot;hasSuperProperty&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}boolean&quot;/&gt;
 *         &lt;element name=&quot;objectProperty&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}boolean&quot;/&gt;
 *         &lt;element name=&quot;propertURI&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;propertyName&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;vnComment&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;vnLabel&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "propertyData", propOrder = { "datatypeProperty", "enComment",
		"enLabel", "hasSubProperty", "hasSuperProperty", "objectProperty",
		"propertURI", "propertyName", "vnComment", "vnLabel" })
public class PropertyData {

	protected boolean datatypeProperty;
	protected String enComment;
	protected String enLabel;
	protected boolean hasSubProperty;
	protected boolean hasSuperProperty;
	protected boolean objectProperty;
	protected String propertURI;
	protected String propertyName;
	protected String vnComment;
	protected String vnLabel;

	/**
	 * Gets the value of the datatypeProperty property.
	 * 
	 */
	public boolean isDatatypeProperty() {
		return datatypeProperty;
	}

	/**
	 * Sets the value of the datatypeProperty property.
	 * 
	 */
	public void setDatatypeProperty(boolean value) {
		this.datatypeProperty = value;
	}

	/**
	 * Gets the value of the enComment property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEnComment() {
		return enComment;
	}

	/**
	 * Sets the value of the enComment property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEnComment(String value) {
		this.enComment = value;
	}

	/**
	 * Gets the value of the enLabel property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getEnLabel() {
		return enLabel;
	}

	/**
	 * Sets the value of the enLabel property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setEnLabel(String value) {
		this.enLabel = value;
	}

	/**
	 * Gets the value of the hasSubProperty property.
	 * 
	 */
	public boolean isHasSubProperty() {
		return hasSubProperty;
	}

	/**
	 * Sets the value of the hasSubProperty property.
	 * 
	 */
	public void setHasSubProperty(boolean value) {
		this.hasSubProperty = value;
	}

	/**
	 * Gets the value of the hasSuperProperty property.
	 * 
	 */
	public boolean isHasSuperProperty() {
		return hasSuperProperty;
	}

	/**
	 * Sets the value of the hasSuperProperty property.
	 * 
	 */
	public void setHasSuperProperty(boolean value) {
		this.hasSuperProperty = value;
	}

	/**
	 * Gets the value of the objectProperty property.
	 * 
	 */
	public boolean isObjectProperty() {
		return objectProperty;
	}

	/**
	 * Sets the value of the objectProperty property.
	 * 
	 */
	public void setObjectProperty(boolean value) {
		this.objectProperty = value;
	}

	/**
	 * Gets the value of the propertURI property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPropertURI() {
		return propertURI;
	}

	/**
	 * Sets the value of the propertURI property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPropertURI(String value) {
		this.propertURI = value;
	}

	/**
	 * Gets the value of the propertyName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * Sets the value of the propertyName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPropertyName(String value) {
		this.propertyName = value;
	}

	/**
	 * Gets the value of the vnComment property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getVnComment() {
		return vnComment;
	}

	/**
	 * Sets the value of the vnComment property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setVnComment(String value) {
		this.vnComment = value;
	}

	/**
	 * Gets the value of the vnLabel property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getVnLabel() {
		return vnLabel;
	}

	/**
	 * Sets the value of the vnLabel property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setVnLabel(String value) {
		this.vnLabel = value;
	}

}
