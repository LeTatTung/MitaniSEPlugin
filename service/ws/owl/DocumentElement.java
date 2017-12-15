package ws.owl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for documentElement complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;documentElement&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;beginPage&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}int&quot;/&gt;
 *         &lt;element name=&quot;endPage&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}int&quot;/&gt;
 *         &lt;element name=&quot;id&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;label&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;textContent&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "documentElement", propOrder = { "beginPage", "endPage", "id",
		"label", "textContent" })
public class DocumentElement {

	protected int beginPage;
	protected int endPage;
	protected String id;
	protected String label;
	protected String textContent;

	/**
	 * Gets the value of the beginPage property.
	 * 
	 */
	public int getBeginPage() {
		return beginPage;
	}

	/**
	 * Sets the value of the beginPage property.
	 * 
	 */
	public void setBeginPage(int value) {
		this.beginPage = value;
	}

	/**
	 * Gets the value of the endPage property.
	 * 
	 */
	public int getEndPage() {
		return endPage;
	}

	/**
	 * Sets the value of the endPage property.
	 * 
	 */
	public void setEndPage(int value) {
		this.endPage = value;
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
	 * Gets the value of the textContent property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTextContent() {
		return textContent;
	}

	/**
	 * Sets the value of the textContent property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTextContent(String value) {
		this.textContent = value;
	}

}
