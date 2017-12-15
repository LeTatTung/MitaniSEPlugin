package ws.owl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for section complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name=&quot;section&quot;&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base=&quot;{http://owl.ws/}documentElement&quot;&gt;
 *       &lt;sequence&gt;
 *         &lt;element name=&quot;childrens&quot; type=&quot;{http://owl.ws/}section&quot; maxOccurs=&quot;unbounded&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;fullIndex&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}string&quot; minOccurs=&quot;0&quot;/&gt;
 *         &lt;element name=&quot;level&quot; type=&quot;{http://www.w3.org/2001/XMLSchema}int&quot;/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "section", propOrder = { "childrens", "fullIndex", "level" })
public class Section extends DocumentElement {

	@XmlElement(nillable = true)
	protected List<Section> childrens;
	protected String fullIndex;
	protected int level;

	/**
	 * Gets the value of the childrens property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the childrens property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getChildrens().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link Section }
	 * 
	 * 
	 */
	public List<Section> getChildrens() {
		if (childrens == null) {
			childrens = new ArrayList<Section>();
		}
		return this.childrens;
	}

	/**
	 * Gets the value of the fullIndex property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFullIndex() {
		return fullIndex;
	}

	/**
	 * Sets the value of the fullIndex property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFullIndex(String value) {
		this.fullIndex = value;
	}

	/**
	 * Gets the value of the level property.
	 * 
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Sets the value of the level property.
	 * 
	 */
	public void setLevel(int value) {
		this.level = value;
	}

}
