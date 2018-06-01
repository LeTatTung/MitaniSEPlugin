package composite.ManageArtifactViewPart;

import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import hust.composite.xmleditor.XmlTextPane;

public class RDFComment {
	private String comment;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public RDFComment(String comment) {
		// TODO Auto-generated constructor stub
		initialize(comment);
	}

	private void initialize(String comment) {
		JFrame myFrame = new JFrame("RDF Comment");
		myFrame.setLocation(new Point(100, 100));
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel mainPanel = new JPanel();
		myFrame.getContentPane().add(mainPanel);
		// JTextArea display = new JTextArea (40,60);
		// display.setEditable ( false ); // set textArea non-editable
		// display.setText(comment);
		XmlTextPane xmlTextPane = new XmlTextPane();
		xmlTextPane.setText(comment);
		JScrollPane scroll = new JScrollPane(xmlTextPane);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		mainPanel.add(scroll);
		myFrame.pack();
		myFrame.setLocationByPlatform(true);
		myFrame.setVisible(true);
	}

}
