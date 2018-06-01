package hust.composite.xmleditor;

import javax.swing.JTextPane;

public class XmlTextPane extends JTextPane {

    private static final long serialVersionUID = 1L;

    public XmlTextPane() {

        // Set editor kit
        this.setEditorKitForContentType("text/xml", new XmlEditorKit());
        this.setContentType("text/xml");
    }

}