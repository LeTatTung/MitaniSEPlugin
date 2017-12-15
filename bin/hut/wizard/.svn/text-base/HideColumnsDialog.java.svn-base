package hut.wizard;

import java.util.ArrayList;
import java.util.List;



import ontology.images.Images;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;

import org.eclipse.swt.widgets.Shell;



public class HideColumnsDialog extends Dialog {

	public static List<String> selectedColumns = new ArrayList<String>();
	public static List<String> columns = new ArrayList<String>();
	Group group = null;
	/**
	 * Create the dialog
	 * @param parentShell
	 */
	public HideColumnsDialog(Shell parentShell) {
		super(parentShell);
		selectedColumns.clear();
		columns.clear();
	}

	/**
	 * Create contents of the dialog
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		final GridLayout gridLayout_1 = new GridLayout();
		gridLayout_1.numColumns = 3;
		composite.setLayout(gridLayout_1);

		final Button selectAllButton = new Button(composite, SWT.NONE);
		selectAllButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				for (Control children : group.getChildren()) {
					if (children instanceof Button) {
						Button button = (Button) children;
						button.setSelection(true);						
					}
				}	
			}
		});
		selectAllButton.setText("Select All");

		final Button deselectAllButton = new Button(composite, SWT.NONE);
		deselectAllButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				for (Control children : group.getChildren()) {
					if (children instanceof Button) {
						Button button = (Button) children;
						button.setSelection(false);						
					}
				}
			}
		});
		deselectAllButton.setText("Deselect All");

		final Button revertSelectionButton = new Button(composite, SWT.NONE);
		revertSelectionButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				for (Control children : group.getChildren()) {
					if (children instanceof Button) {
						Button button = (Button) children;
						button.setSelection(!button.getSelection());						
					}
				}
			}
		});
		revertSelectionButton.setText("Inverse Selection");
		
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new FillLayout());

		
		group = new Group(container, SWT.NONE);
		group.setText("Check column to show");
		final GridLayout gridLayout_2 = new GridLayout();
		gridLayout_2.numColumns = 3;
		group.setLayout(gridLayout_2);

		for (String column : columns) {
			Button button = new Button(group, SWT.CHECK);
			button.setText(column);
			if(selectedColumns.contains(column))
				button.setSelection(true);
		}
				
		return container;
	}

	/**
	 * Create contents of the button bar
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(355, 319);
	}
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Hide Column");
		newShell.setImage(Images.imageRegistry.get(Images.HIDDEN));
	}
	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.OK_ID) {
			selectedColumns.clear();
			Control[] childrens = group.getChildren();
			for (Control children : childrens) {
				if (children instanceof Button) {
					Button button = (Button) children;
					if(button.getSelection())
					{
						selectedColumns.add(button.getText());
					}
				}
			}			
		}
		super.buttonPressed(buttonId);
	}
}
