package ws.owl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for individualData complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;individualData&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;enComment&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;enLabel&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;individualName&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;individualURI&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
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
@XmlType(name = "individualData", propOrder = { "enComment", "enLabel",
		"individualName", "individualURI", "vnComment", "vnLabel" })
public class IndividualData {

	protected String enComment;
	protected String enLabel;
	protected String individualName;
	protected String individualURI;
	protected String vnComment;
	protected String vnLabel;

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
	 * Gets the value of the individualName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIndividualName() {
		return individualName;
	}

	/**
	 * Sets the value of the individualName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIndividualName(String value) {
		this.individualName = value;
	}

	/**
	 * Gets the value of the individualURI property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIndividualURI() {
		return individualURI;
	}

	/**
	 * Sets the value of the individualURI property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIndividualURI(String value) {
		this.individualURI = value;
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
