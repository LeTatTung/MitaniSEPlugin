package documentsManager;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class ContentComposite extends Composite {

	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public ContentComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout());
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);

		final SashForm sashForm = new SashForm(this, SWT.NONE);
		toolkit.adapt(sashForm, true, true);
		//
	}

}
