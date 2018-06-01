package hust.composite.xmleditor;

import javax.swing.text.StyledEditorKit;
import javax.swing.text.ViewFactory;

public class XmlEditorKit extends StyledEditorKit {

    private static final long serialVersionUID = 1L;
    private ViewFactory xmlViewFactory;

    public XmlEditorKit() {
        xmlViewFactory = new XmlViewFactory();
    }

    @Override
    public ViewFactory getViewFactory() {
        return xmlViewFactory;
    }

    @Override
    public String getContentType() {
        return "text/xml";
    }
}
