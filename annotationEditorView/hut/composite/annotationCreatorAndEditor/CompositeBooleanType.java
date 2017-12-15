package hut.composite.annotationCreatorAndEditor;

import ontology.images.Images;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class CompositeBooleanType extends Composite {
	
	private Button trueButton;
	private Button falseButton;
	private boolean isSaved= false;
	
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public CompositeBooleanType(Composite parent, int style) {
		super(parent, style);
		setLayout(new FormLayout());
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);

		final Composite composite = new Composite(this, SWT.BORDER);
		composite.setLayout(new FormLayout());
		final FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(0, 52);
		fd_composite.top = new FormAttachment(0, 0);
		fd_composite.right = new FormAttachment(0, 500);
		fd_composite.left = new FormAttachment(0, 0);
		composite.setLayoutData(fd_composite);
		toolkit.adapt(composite);

		final ToolBar toolBar = new ToolBar(composite, SWT.NONE);
		toolBar.setLayout(new FormLayout());
		final FormData fd_toolBar = new FormData();
		fd_toolBar.bottom = new FormAttachment(100, -5);
		fd_toolBar.right = new FormAttachment(100, 0);
		fd_toolBar.top = new FormAttachment(0, 0);
		fd_toolBar.left = new FormAttachment(0, 0);
		toolBar.setLayoutData(fd_toolBar);
		toolkit.adapt(toolBar, true, true);

		final ToolItem toolItemSave = new ToolItem(toolBar, SWT.PUSH);
		toolItemSave.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				isSaved = true;
//				if(trueButton.getSelection()){
//					setValue("true");
//				}
//				else{
//					setValue("false");
//				}
				toolBar.getShell().close();
			}
		});
		toolItemSave.setImage(Images.imageRegistry.get(Images.SAVE));
		toolItemSave.setText("Save");

		Composite composite_1;
		composite_1 = new Composite(this, SWT.BORDER);
		composite_1.setLayout(new FormLayout());
		final FormData fd_composite_1 = new FormData();
		fd_composite_1.top = new FormAttachment(0, 50);
		fd_composite_1.bottom = new FormAttachment(0, 375);
		fd_composite_1.right = new FormAttachment(100, 0);
		fd_composite_1.left = new FormAttachment(0, 0);
		composite_1.setLayoutData(fd_composite_1);
		toolkit.adapt(composite_1);

		trueButton = new Button(composite_1, SWT.RADIO);
		final FormData fd_trueButton = new FormData();
		fd_trueButton.bottom = new FormAttachment(0, 54);
		fd_trueButton.top = new FormAttachment(0, 38);
		fd_trueButton.right = new FormAttachment(0, 79);
		fd_trueButton.left = new FormAttachment(0, 36);
		trueButton.setLayoutData(fd_trueButton);
		trueButton.setSelection(true);
		toolkit.adapt(trueButton, true, true);
		trueButton.setText("True");

		falseButton = new Button(composite_1, SWT.RADIO);
		final FormData fd_falseButton = new FormData();
		fd_falseButton.right = new FormAttachment(0, 137);
		fd_falseButton.bottom = new FormAttachment(0, 54);
		fd_falseButton.top = new FormAttachment(0, 38);
		fd_falseButton.left = new FormAttachment(0, 85);
		falseButton.setLayoutData(fd_falseButton);
		toolkit.adapt(falseButton, true, true);
		falseButton.setText("False");
		//
	}

	public void setValue(String value){
		if(value.equals("true")){
			trueButton.setSelection(true);
			falseButton.setSelection(false);
		}
		else{
			falseButton.setSelection(true);
			trueButton.setSelection(false);
		}
	}
	
	public String getValue(){
		if(trueButton.getSelection()){
			return "true";
		}
		else
			return "false";
	}
	
	public boolean getIsSaved(){
		return isSaved;
	}
}
