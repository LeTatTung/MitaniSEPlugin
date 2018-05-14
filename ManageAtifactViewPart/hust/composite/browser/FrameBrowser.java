package hust.composite.browser;

import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkListener;

import hust.composite.browser.ActivatedHyperlinkListener;

public class FrameBrowser {

	private String link;
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	public FrameBrowser(String link) {
		this.link = link;
	}
	public void createBrowser(String link) {
	    JFrame frame = new JFrame("View Comment RDF2HTML");
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	    try {
	      JEditorPane editorPane = new JEditorPane(link);
	      editorPane.setEditable(false);

	      HyperlinkListener hyperlinkListener = new ActivatedHyperlinkListener(
	          editorPane);
	      editorPane.addHyperlinkListener(hyperlinkListener);

	      JScrollPane scrollPane = new JScrollPane(editorPane);
	      frame.add(scrollPane);
	    } catch (IOException e) {
	      System.err.println("Unable to load: " + e);
	    }

	    frame.setSize(800, 550);
	    frame.setVisible(true);
	}
}
