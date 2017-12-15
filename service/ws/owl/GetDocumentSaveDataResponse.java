package ws.owl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for getDocumentSaveDataResponse complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;getDocumentSaveDataResponse&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}anyType&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;return&quot; type=&quot;{http://owl.ws/}documentSaveData&quot; minOccurs=&quot;0&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDocumentSaveDataResponse", propOrder = { "_return" })
public class GetDocumentSaveDataResponse {

	@XmlElement(name = "return")
	protected DocumentSaveData _return;

	/**
	 * Gets the value of the return property.
	 * 
	 * @return possible object is {@link DocumentSaveData }
	 * 
	 */
	public DocumentSaveData getReturn() {
		return _return;
	}

	/**
	 * Sets the value of the return property.
	 * 
	 * @param value
	 *            allowed object is {@link DocumentSaveData }
	 * 
	 */
	public void setReturn(DocumentSaveData value) {
		this._return = value;
	}

}
