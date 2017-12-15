package hut.views.notification;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.widgets.FormToolkit;
import swing2swt.layout.BorderLayout;

public class ResultNotificationView extends Composite {

	private Table table;
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public ResultNotificationView(Composite parent, int style) {
		super(parent, style);
		setLayout(new BorderLayout(0, 0));
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);

		final Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(BorderLayout.NORTH);
		composite.setLayout(new BorderLayout(0, 0));
		toolkit.adapt(composite);

		final CLabel notificationResultLabel = new CLabel(composite, SWT.NONE);
		notificationResultLabel.setLayoutData(BorderLayout.CENTER);
		toolkit.adapt(notificationResultLabel, true, true);
		notificationResultLabel.setText("Notification Result");

		final Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setLayout(new BorderLayout(0, 0));
		toolkit.adapt(composite_1);
		composite_1.setLayoutData(BorderLayout.CENTER);

		table = toolkit.createTable(composite_1, SWT.NONE);
		table.setLayoutData(BorderLayout.CENTER);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		//
	}

}
