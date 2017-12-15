package hut.composite.admin;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.widgets.FormToolkit;

import ws.owl.InstanceData;

public class CompositeInstanceRow extends Composite {

	private InstanceData instanceData;
	private Table table;
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	
	public CompositeInstanceRow(Composite parent, int style) {
		super(parent, style);
		setLayout(new FormLayout());
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);

		table = new Table(this, SWT.BORDER);
		final FormData fd_table = new FormData();
		fd_table.bottom = new FormAttachment(100, -5);
		fd_table.right = new FormAttachment(100, -5);
		fd_table.top = new FormAttachment(0, 5);
		fd_table.left = new FormAttachment(0, 5);
		table.setLayoutData(fd_table);
		toolkit.adapt(table, true, true);
		table.setLinesVisible(false);
		table.setHeaderVisible(false);
		
		
		
	}


	public InstanceData getInstanceData() {
		return instanceData;
	}


	public void setInstanceData(InstanceData instanceData) {
		this.instanceData = instanceData;
	}

}
