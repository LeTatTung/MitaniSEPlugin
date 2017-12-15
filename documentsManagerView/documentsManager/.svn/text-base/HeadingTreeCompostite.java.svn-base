package documentsManager;

import headingProvider.HeadingMatcher;
import headingProvider.HeadingNodeContentProvider;
import headingProvider.HeadingNodeLableProvider;
import headingProvider.HeadingStructure;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.forms.widgets.FormToolkit;
import com.swtdesigner.SWTResourceManager;
import swing2swt.layout.BorderLayout;

public class HeadingTreeCompostite extends Composite {

	private Tree tree;
	TreeViewer treeViewer;
	private Text InputFileText;
	HeadingStructure inputHeadingStruct=null;
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public HeadingTreeCompostite(Composite parent, int style) {
		super(parent, style);
		setLayout(new BorderLayout(0, 0));
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);

		final Composite TopComposite = new Composite(this, SWT.NONE);
		TopComposite.setLayoutData(BorderLayout.NORTH);
		TopComposite.setLayout(new BorderLayout(0, 0));
		toolkit.adapt(TopComposite);

		final SashForm sashForm = new SashForm(TopComposite, SWT.NONE);

		final Label fileNameLabel = new Label(sashForm, SWT.NONE);
		toolkit.adapt(fileNameLabel, true, true);
		fileNameLabel.setText("File Name");
		toolkit.adapt(sashForm, true, true);

		InputFileText = new Text(sashForm, SWT.BORDER);
		InputFileText.setText("Please input file name here ...");
		toolkit.adapt(InputFileText, true, true);

		final Button getHeadingButton = new Button(sashForm, SWT.NONE);
		
		toolkit.adapt(getHeadingButton, true, true);
		getHeadingButton.setText("Get Heading");
		sashForm.setWeights(new int[] {63, 264, 167 });

		final Composite TreeContentComposite = new Composite(this, SWT.BORDER);
		TreeContentComposite.setLayout(new BorderLayout(0, 0));
		toolkit.adapt(TreeContentComposite);
		TreeContentComposite.setLayoutData(BorderLayout.CENTER);

		final SashForm sashForm_1 = new SashForm(TreeContentComposite, SWT.NONE);

		final CLabel headingLabel = new CLabel(sashForm_1, SWT.NONE);
		headingLabel.setBackground(SWTResourceManager.getColor(255, 255, 255));
		headingLabel.setImage(SWTResourceManager.getImage(HeadingTreeCompostite.class, "/ontology/images/bookmark.gif"));
		toolkit.adapt(headingLabel, true, true);
		headingLabel.setText("No input file");

		treeViewer = new TreeViewer(TreeContentComposite, SWT.NONE);
		treeViewer.setContentProvider(new HeadingNodeContentProvider());
		treeViewer.setLabelProvider(new HeadingNodeLableProvider());
		tree = treeViewer.getTree();
		toolkit.adapt(tree, true, true);
		tree.setLayoutData(BorderLayout.CENTER);
		sashForm_1.setWeights(new int[] { 1 });
		sashForm_1.setLayoutData(BorderLayout.NORTH);
		toolkit.adapt(sashForm_1, true, true);
		//
		
		getHeadingButton.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				HeadingMatcher headingMatcher = new HeadingMatcher();
				inputHeadingStruct = headingMatcher.getHeadingFromPdfFile("C:\\Documents and Settings\\Administrator\\Desktop\\Mitani Case Tool\\"+InputFileText.getText());
				treeViewer.setInput(inputHeadingStruct.getRoot());
				headingLabel.setText("Heading Of "+InputFileText.getText());
			}
		});
	}

}
