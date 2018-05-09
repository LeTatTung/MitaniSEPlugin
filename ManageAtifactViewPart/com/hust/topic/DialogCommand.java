package com.hust.topic;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class DialogCommand extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFrame frame;

	String simpleDialogDesc = "Topic not found!!! Did you mean:";
	private ArrayList<String> arrayDialogString = new ArrayList<>();

	public DialogCommand(JFrame frame, ArrayList<String> arrayDialogString) {
		this.frame = frame;
		this.arrayDialogString = arrayDialogString;
		// Create the components.
		JPanel frequentPanel = createSimpleDialogBox();
		// System.out.println(output);

		// Lay them out.
		Border padding = BorderFactory.createEmptyBorder(20, 20, 5, 20);
		frequentPanel.setBorder(padding);
		add(frequentPanel);
	}

	private JPanel createSimpleDialogBox() {
		final int numLabel = arrayDialogString.size();
		JLabel[] labels = new JLabel[numLabel];
				
		for (int i = 0; i < numLabel; i++) {
			labels[i] = new JLabel(arrayDialogString.get(i));
		}

		return createPane(simpleDialogDesc + ":", labels);
	}

	private JPanel createPane(String description, JLabel[] labels) {

		int num = labels.length;
		JPanel box = new JPanel();
		JLabel label = new JLabel(description);

		box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
		box.add(label);

		for (int i = 0; i < num; i++) {
			box.add(labels[i]);
		}

		JPanel pane = new JPanel(new BorderLayout());
		pane.add(box, BorderLayout.PAGE_START);
		return pane;
	}
}
