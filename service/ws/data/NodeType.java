package ws.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for nodeType.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * <p>
 * 
 * <pre>
 * &lt;simpleType name=&quot;nodeType&quot;&gt;
 *   &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}string&quot;&gt;
 *     &lt;enumeration value=&quot;WORKSPACE&quot;/&gt;
 *     &lt;enumeration value=&quot;PROJECT&quot;/&gt;
 *     &lt;enumeration value=&quot;FOLDERSOURCE&quot;/&gt;
 *     &lt;enumeration value=&quot;PACKAGE&quot;/&gt;
 *     &lt;enumeration value=&quot;CLASS&quot;/&gt;
 *     &lt;enumeration value=&quot;METHOD&quot;/&gt;
 *     &lt;enumeration value=&quot;FIELD&quot;/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "nodeType")
@XmlEnum
public enum NodeType {

	WORKSPACE, PROJECT, FOLDERSOURCE, PACKAGE, CLASS, METHOD, FIELD;

	public String value() {
		return name();
	}

	public static NodeType fromValue(String v) {
		return valueOf(v);
	}

}
