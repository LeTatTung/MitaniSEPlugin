package hut.composite.svn;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class NameComposite extends Composite {

	private Text text;
	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public NameComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new FormLayout());

		final Label pleaseInsertFileLabel = new Label(this, SWT.NONE);
		final FormData fd_pleaseInsertFileLabel = new FormData();
		fd_pleaseInsertFileLabel.bottom = new FormAttachment(0, 25);
		fd_pleaseInsertFileLabel.right = new FormAttachment(0, 170);
		fd_pleaseInsertFileLabel.top = new FormAttachment(0, 5);
		fd_pleaseInsertFileLabel.left = new FormAttachment(0, 5);
		pleaseInsertFileLabel.setLayoutData(fd_pleaseInsertFileLabel);
		pleaseInsertFileLabel.setText("Please insert file name");

		text = new Text(this, SWT.BORDER);
		final FormData fd_text = new FormData();
		fd_text.right = new FormAttachment(0, 220);
		fd_text.left = new FormAttachment(0, 5);
		fd_text.bottom = new FormAttachment(pleaseInsertFileLabel, 25, SWT.BOTTOM);
		fd_text.top = new FormAttachment(pleaseInsertFileLabel, 0, SWT.BOTTOM);
		text.setLayoutData(fd_text);

		final Button okButton = new Button(this, SWT.NONE);
		okButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				okButton.getShell().close();
				
			}
		});
		final FormData fd_okButton = new FormData();
		fd_okButton.right = new FormAttachment(0, 81);
		fd_okButton.left = new FormAttachment(0, 55);
		okButton.setLayoutData(fd_okButton);
		okButton.setText("OK");

		final Button cancelButton;
		cancelButton = new Button(this, SWT.NONE);
		fd_okButton.top = new FormAttachment(cancelButton, -23, SWT.BOTTOM);
		fd_okButton.bottom = new FormAttachment(cancelButton, 0, SWT.BOTTOM);
		cancelButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				text.setText("");
				cancelButton.getShell().close();
			}
		});
		final FormData fd_cancelButton = new FormData();
		fd_cancelButton.top = new FormAttachment(0, 67);
		fd_cancelButton.bottom = new FormAttachment(0, 90);
		fd_cancelButton.left = new FormAttachment(pleaseInsertFileLabel, -44, SWT.RIGHT);
		fd_cancelButton.right = new FormAttachment(pleaseInsertFileLabel, 0, SWT.RIGHT);
		cancelButton.setLayoutData(fd_cancelButton);
		cancelButton.setText("Cancel");
		//
	}
	
	public String getValue()
	{
		if (text.getText()=="")
			return null;
		return text.getText();
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
