package ws.owl;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for languageEnum.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * <p>
 * 
 * <pre>
 * &lt;simpleType name=&quot;languageEnum&quot;&gt;
 *   &lt;restriction base=&quot;{http://www.w3.org/2001/XMLSchema}string&quot;&gt;
 *     &lt;enumeration value=&quot;EN&quot;/&gt;
 *     &lt;enumeration value=&quot;DE&quot;/&gt;
 *     &lt;enumeration value=&quot;FR&quot;/&gt;
 *     &lt;enumeration value=&quot;ES&quot;/&gt;
 *     &lt;enumeration value=&quot;PT&quot;/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "languageEnum")
@XmlEnum
public enum LanguageEnum {

	EN, DE, FR, ES, PT;

	public String value() {
		return name();
	}

	public static LanguageEnum fromValue(String v) {
		return valueOf(v);
	}

}
