package suggestTopic;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.ITypeRoot;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.internal.corext.dom.NodeFinder;
import org.eclipse.jdt.internal.ui.javaeditor.JavaEditor;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jdt.ui.SharedASTProvider;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;

import com.hust.topic.CompareTopic;

import mitani.activator.Activator;

@SuppressWarnings("restriction")
public class SuggestTopicOperations {

	static String simpleDialogDesc = "Simple topic dialogs";

	private static JPanel createPane(String description, JRadioButton[] radioButtons, JButton showButton) {

		int numChoices = radioButtons.length;
		JPanel box = new JPanel();
		JLabel label = new JLabel(description);

		box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
		box.add(label);

		for (int i = 0; i < numChoices; i++) {
			box.add(radioButtons[i]);
		}

		JPanel pane = new JPanel(new BorderLayout());
		pane.add(box, BorderLayout.PAGE_START);
		pane.add(showButton, BorderLayout.PAGE_END);
		return pane;
	}

	static void suggestTopic(JavaEditor editor){
		// Editor not editable...bail out
		if (!editor.isEditable()) {
			Display.getCurrent().beep();
			return;
		}

		// Get source viewer
		ISourceViewer viewer = editor.getViewer();
		if (viewer == null)
			return;

		// Get the caret position
		Point selectedRange = viewer.getSelectedRange();
		int caretAt = selectedRange.x;
		int length = selectedRange.y;
		// Get the Java element
		ITypeRoot element = JavaUI.getEditorInputTypeRoot(editor.getEditorInput());
		if (element == null)
			return;

		// Get the compilation unit AST
		CompilationUnit ast = SharedASTProvider.getAST(element, SharedASTProvider.WAIT_YES, null);
		if (ast == null)
			return;

		// Find the node at caret position
		NodeFinder finder = new NodeFinder(caretAt, length);
		ast.accept(finder);
		ASTNode node = finder.getCoveringNode();

		if (node != null) {
			int firstStart = node.getParent().getStartPosition();
			System.out.println("FIRST START POSITION: " + firstStart);
			System.out.println("PARENT: " + node.getParent().toString());
			// khoi tao list chua cac topic cua he thong
			CompareTopic compareTopic = new CompareTopic();
			ArrayList<String> arrayTopicData = compareTopic.initData();
			// lay ra content cua topic tren java editor
			String input = node.getParent().toString();
			TopicContent topic = new TopicContent(input);
			String topicContent = topic.getTopicContent(input);
			int countChar = topic.countChar(input, '*');
			if (compareTopic.hasContains(topicContent, arrayTopicData)) {
				String comment = "";
				String similarityString = compareTopic.getSimilarityString(topicContent, arrayTopicData);
				System.out.println("similarityString|: " +similarityString);
				int addLength = 0;
				if (countChar == 1) {
					comment = "@topic " + similarityString;
					addLength = similarityString.length() - topicContent.length() -1;
				} else if (countChar > 1) {
					comment = " /** \n" + " * @topic " + similarityString;
					addLength = similarityString.length() - topicContent.length() +1;
				}
				System.out.println("comment|: " +comment);
				try {
					viewer.getDocument().replace(firstStart, comment.length() -addLength, comment);
				} catch (BadLocationException e) {
					Activator.getDefault().getLog()
							.log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Could not suggest topic items."));
					return;
				}
			} else {
				ArrayList<String> arrayDialogString = compareTopic.getSimilarityListString(topicContent,
						arrayTopicData);
				// Create and set up the window.
				JFrame frame = new JFrame("Suggest Topic");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setLocationRelativeTo(null);
				// Create and set up the content pane.

				final int numButtons = arrayDialogString.size();
				JRadioButton[] radioButtons = new JRadioButton[numButtons];
				final ButtonGroup group = new ButtonGroup();

				JButton showItButton = null;

				for (int i = 0; i < numButtons; i++) {
					radioButtons[i] = new JRadioButton(arrayDialogString.get(i));
					radioButtons[i].setActionCommand(arrayDialogString.get(i));
					group.add(radioButtons[i]);
				}

				radioButtons[0].setSelected(true);

				showItButton = new JButton("OK");
				JPanel newContentPane = createPane(simpleDialogDesc + ":", radioButtons, showItButton);
				newContentPane.setOpaque(true); // content panes must be opaque
				Border padding = BorderFactory.createEmptyBorder(20, 20, 5, 20);
				newContentPane.setBorder(padding);
				frame.setContentPane(newContentPane);

				// Display the window.
				frame.pack();
				frame.setVisible(true);

				showItButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String command = group.getSelection().getActionCommand();
						String comment_new = "";
						int addLength = 0;
						if (countChar == 1) {
							comment_new = "@topic " + command;
							addLength = command.length() - topicContent.length() -1;
						} else if (countChar > 1) {
							comment_new = " /** \n" + " * @topic " + command;
							addLength = command.length() - topicContent.length() +1;
						}
						try {
							viewer.getDocument().replace(firstStart, comment_new.length() - addLength, comment_new);
						} catch (BadLocationException e1) {
							Activator.getDefault().getLog()
									.log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Could not suggest topic items."));
							return;
						}
						frame.dispose();

					}

				});

			}
		}

	}
}