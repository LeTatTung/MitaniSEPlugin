package ws.owl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for documentSaveData complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;documentSaveData&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;fileName&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;id&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;images&quot; type=&quot;{http://owl.ws/}documentElement&quot; maxOccurs=&quot;unbounded&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;label&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;pageNumbers&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}int&quot;/&gt;
 *         &lt;element name=&quot;paragraphs&quot; type=&quot;{http://owl.ws/}documentElement&quot; maxOccurs=&quot;unbounded&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;rootSection&quot; type=&quot;{http://owl.ws/}section&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "documentSaveData", propOrder = { "fileName", "id", "images",
		"label", "pageNumbers", "paragraphs", "rootSection" })
public class DocumentSaveData {

	protected String fileName;
	protected String id;
	@XmlElement(nillable = true)
	protected List<DocumentElement> images;
	protected String label;
	protected int pageNumbers;
	@XmlElement(nillable = true)
	protected List<DocumentElement> paragraphs;
	protected Section rootSection;

	/**
	 * Gets the value of the fileName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the value of the fileName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFileName(String value) {
		this.fileName = value;
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
	 * Gets the value of the images property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the images property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getImages().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link DocumentElement }
	 * 
	 * 
	 */
	public List<DocumentElement> getImages() {
		if (images == null) {
			images = new ArrayList<DocumentElement>();
		}
		return this.images;
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
	 * Gets the value of the pageNumbers property.
	 * 
	 */
	public int getPageNumbers() {
		return pageNumbers;
	}

	/**
	 * Sets the value of the pageNumbers property.
	 * 
	 */
	public void setPageNumbers(int value) {
		this.pageNumbers = value;
	}

	/**
	 * Gets the value of the paragraphs property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the paragraphs property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getParagraphs().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link DocumentElement }
	 * 
	 * 
	 */
	public List<DocumentElement> getParagraphs() {
		if (paragraphs == null) {
			paragraphs = new ArrayList<DocumentElement>();
		}
		return this.paragraphs;
	}

	/**
	 * Gets the value of the rootSection property.
	 * 
	 * @return possible object is {@link Section }
	 * 
	 */
	public Section getRootSection() {
		return rootSection;
	}

	/**
	 * Sets the value of the rootSection property.
	 * 
	 * @param value
	 *            allowed object is {@link Section }
	 * 
	 */
	public void setRootSection(Section value) {
		this.rootSection = value;
	}

}
