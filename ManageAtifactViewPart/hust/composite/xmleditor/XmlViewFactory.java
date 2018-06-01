package hust.composite.xmleditor;

import javax.swing.text.Element;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;

public class XmlViewFactory extends Object implements ViewFactory {

	@Override
	public View create(Element element) {
		// TODO Auto-generated method stub
		return new XmlView(element);
	}

}
