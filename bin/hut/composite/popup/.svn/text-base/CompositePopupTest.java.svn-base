package hut.composite.popup;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class CompositePopupTest extends Composite {

	private Text text;
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public CompositePopupTest(Composite parent, int style) {
		super(parent, style);
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);

		final CLabel testIdLabel = new CLabel(this, SWT.NONE);
		toolkit.adapt(testIdLabel, true, true);
		testIdLabel.setText("Test ID");
		testIdLabel.setBounds(10, 28, 38, 19);

		text = new Text(this, SWT.BORDER);
		toolkit.adapt(text, true, true);
		text.setBounds(99, 28, 191, 25);

		final CLabel methodTestLabel = new CLabel(this, SWT.NONE);
		toolkit.adapt(methodTestLabel, true, true);
		methodTestLabel.setText("Method Test");
		methodTestLabel.setBounds(10, 77, 73, 19);

		final CLabel testForLabel = new CLabel(this, SWT.NONE);
		toolkit.adapt(testForLabel, true, true);
		testForLabel.setText("Test For");
		testForLabel.setBounds(10, 120, 73, 19);

		final CLabel label_2 = new CLabel(this, SWT.NONE);
		toolkit.adapt(label_2, true, true);
		label_2.setText("label");
		label_2.setBounds(10, 168, 38, 19);

		final CLabel successLabel = new CLabel(this, SWT.NONE);
		toolkit.adapt(successLabel, true, true);
		successLabel.setText("Success");
		successLabel.setBounds(10, 221, 50, 19);

		final CLabel label_4 = new CLabel(this, SWT.NONE);
		toolkit.adapt(label_4, true, true);
		label_4.setText("label");
		label_4.setBounds(10, 270, 38, 19);

		final CLabel label_5 = new CLabel(this, SWT.NONE);
		toolkit.adapt(label_5, true, true);
		label_5.setText("label");
		label_5.setBounds(10, 320, 38, 19);
		//
	}

}
