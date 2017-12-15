package ws.owl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for classData complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;classData&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;className&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;classURI&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;enComment&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;enLabel&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;hasSubClass&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}boolean&quot;/&gt;
 *         &lt;element name=&quot;hasSuperClass&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}boolean&quot;/&gt;
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
@XmlType(name = "classData", propOrder = { "className", "classURI",
		"enComment", "enLabel", "hasSubClass", "hasSuperClass", "vnComment",
		"vnLabel" })
public class ClassData {

	protected String className;
	protected String classURI;
	protected String enComment;
	protected String enLabel;
	protected boolean hasSubClass;
	protected boolean hasSuperClass;
	protected String vnComment;
	protected String vnLabel;

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
	 * Gets the value of the classURI property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getClassURI() {
		return classURI;
	}

	/**
	 * Sets the value of the classURI property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setClassURI(String value) {
		this.classURI = value;
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
	 * Gets the value of the hasSubClass property.
	 * 
	 */
	public boolean isHasSubClass() {
		return hasSubClass;
	}

	/**
	 * Sets the value of the hasSubClass property.
	 * 
	 */
	public void setHasSubClass(boolean value) {
		this.hasSubClass = value;
	}

	/**
	 * Gets the value of the hasSuperClass property.
	 * 
	 */
	public boolean isHasSuperClass() {
		return hasSuperClass;
	}

	/**
	 * Sets the value of the hasSuperClass property.
	 * 
	 */
	public void setHasSuperClass(boolean value) {
		this.hasSuperClass = value;
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
