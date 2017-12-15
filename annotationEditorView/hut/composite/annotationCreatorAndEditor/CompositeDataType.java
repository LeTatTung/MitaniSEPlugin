package hut.composite.annotationCreatorAndEditor;


import ontology.images.Images;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class CompositeDataType extends Composite {
	private Text text;
	private ToolItem saveToolItem;
	private ToolItem deleteToolItem;
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private boolean isSaved = false;
	
	public CompositeDataType(Composite parent, int style) {
		super(parent, style);
		setLayout(new FormLayout());
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);

		final Composite composite = new Composite(this, SWT.NONE);
		final FormData fd_composite = new FormData();
		fd_composite.right = new FormAttachment(100, -5);
		fd_composite.top = new FormAttachment(0, 5);
		fd_composite.left = new FormAttachment(0, 5);
		composite.setLayoutData(fd_composite);
		composite.setLayout(new FormLayout());
		toolkit.adapt(composite);

		final ToolBar toolBar = new ToolBar(composite, SWT.NONE);
		final FormData fd_toolBar = new FormData();
		fd_toolBar.bottom = new FormAttachment(100, -5);
		fd_toolBar.right = new FormAttachment(0, 100);
		fd_toolBar.top = new FormAttachment(0, 5);
		fd_toolBar.left = new FormAttachment(0, 5);
		toolBar.setLayoutData(fd_toolBar);
		toolkit.adapt(toolBar, true, true);

		saveToolItem = new ToolItem(toolBar, SWT.PUSH);
		saveToolItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				isSaved = true;
				toolBar.getShell().close();
				
			}
		});
		saveToolItem.setText("save");
		saveToolItem.setImage(Images.imageRegistry.get(Images.SAVE));
		
		deleteToolItem = new ToolItem(toolBar, SWT.PUSH);
		deleteToolItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				text.setText("");
				toolBar.getShell().close();
				
			}
		});
		deleteToolItem.setText("delete");
		deleteToolItem.setImage(Images.imageRegistry.get(Images.DELETE));

		text = new Text(this, SWT.V_SCROLL | SWT.BORDER | SWT.H_SCROLL);
		fd_composite.bottom = new FormAttachment(text, -5, SWT.TOP);
		final FormData fd_text = new FormData();
		fd_text.top = new FormAttachment(0, 65);
		fd_text.right = new FormAttachment(100, -5);
		fd_text.left = new FormAttachment(0, 5);
		fd_text.bottom = new FormAttachment(100, -5);
		text.setLayoutData(fd_text);
		toolkit.adapt(text, true, true);
		//
		
		
	}
	
	public String getValue()
	{
		if (text.getText()=="")
			return null;
		return text.getText();
	}

	public void setValue(String value)
	{
		text.setText(value);
	}

	public boolean getIsSaved(){
		return isSaved;
	}
}

