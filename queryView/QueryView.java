
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.part.*;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.jface.action.*;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.*;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.custom.SashForm;
import ws.owl.*;

class MyDefaultTableModel extends DefaultTableModel{
	public Vector getColumnIdentifiers() {
		return columnIdentifiers;
	}
}

public class QueryView extends ViewPart {

	public static final String ID = "QueryView"; //$NON-NLS-1$

	/**
	 * Create contents of the view part
	 * @param parent
	 */
	private JTable table;
	private Frame frame;
	private JScrollPane scrollPane;
	private Text text;
	private MyDefaultTableModel model;
	private FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	
	public void removeColumnAndData(JTable table, int vColIndex) {
		MyDefaultTableModel model = (MyDefaultTableModel) table.getModel();
		TableColumn col = table.getColumnModel().getColumn(vColIndex);
		int columnModelIndex = col.getModelIndex();
		Vector data = model.getDataVector();
		Vector colIds = model.getColumnIdentifiers();
		// Xóa cột trong bảng 
		table.removeColumn(col);
		// Xóa header của cột trong model
		colIds.removeElementAt(columnModelIndex);
		// Xóa dữ liệu
		data.removeAllElements();
		
		model.setDataVector(data, colIds);
		// Sửa lại chỉ số trong table column
		
		Enumeration<TableColumn> enums = table.getColumnModel().getColumns();

		for (; enums.hasMoreElements();) {
			TableColumn c = (TableColumn) enums.nextElement();
			if (c.getModelIndex() >= columnModelIndex) {
				c.setModelIndex(c.getModelIndex() - 1);
			}
		}
		model.fireTableStructureChanged();
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FormLayout());

		final SashForm sashForm = new SashForm(parent, SWT.VERTICAL);

		final Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FormLayout());
		toolkit.adapt(composite);

		final Label sparqlQueryLabel = new Label(composite, SWT.NONE);
		final FormData fd_sparqlQueryLabel = new FormData();
		fd_sparqlQueryLabel.bottom = new FormAttachment(0, 16);
		fd_sparqlQueryLabel.top = new FormAttachment(0, 3);
		fd_sparqlQueryLabel.right = new FormAttachment(0, 75);
		fd_sparqlQueryLabel.left = new FormAttachment(0, 3);
		sparqlQueryLabel.setLayoutData(fd_sparqlQueryLabel);
		toolkit.adapt(sparqlQueryLabel, true, true);
		sparqlQueryLabel.setText("SPARQL Query");

		text = new Text(composite, SWT.BORDER | SWT.MULTI|SWT.V_SCROLL|SWT.H_SCROLL);
		final FormData fd_text = new FormData();
		fd_text.bottom = new FormAttachment(100, -32);
		fd_text.right = new FormAttachment(100, -5);
		fd_text.top = new FormAttachment(0, 20);
		fd_text.left = new FormAttachment(0, 5);
		text.setLayoutData(fd_text);
		toolkit.adapt(text, true, true);

		Button resetButton;
		resetButton = new Button(composite, SWT.NONE);
		final FormData fd_resetButton = new FormData();
		fd_resetButton.top = new FormAttachment(text, 2, SWT.DEFAULT);
		fd_resetButton.bottom = new FormAttachment(100, -7);
		fd_resetButton.left = new FormAttachment(100, -194);
		fd_resetButton.right = new FormAttachment(100, -154);
		resetButton.setLayoutData(fd_resetButton);
		toolkit.adapt(resetButton, true, true);
		resetButton.setText("Reset");

		Button submitButton;
		submitButton = new Button(composite, SWT.NONE);
		final FormData fd_submitButton = new FormData();
		fd_submitButton.bottom = new FormAttachment(100, -7);
		fd_submitButton.top = new FormAttachment(text, 2, SWT.DEFAULT);
		fd_submitButton.left = new FormAttachment(0, 150);
		submitButton.setLayoutData(fd_submitButton);
		toolkit.adapt(submitButton, true, true);
		submitButton.setText("Submit");
		final FormData fd_sashForm = new FormData();
		fd_sashForm.top = new FormAttachment(0, 5);
		fd_sashForm.bottom = new FormAttachment(100, -5);
		fd_sashForm.right = new FormAttachment(100, -5);
		fd_sashForm.left = new FormAttachment(0, 5);
		sashForm.setLayoutData(fd_sashForm);
		toolkit.adapt(sashForm, true, true);

		final Composite composite_1 = new Composite(sashForm, SWT.EMBEDDED);
		composite_1.setLayout(new FillLayout());
		toolkit.adapt(composite_1);

		frame = SWT_AWT.new_Frame(composite_1);
		frame.setLayout(new BorderLayout());
		sashForm.setWeights(new int[] { 1, 1 });

		model = new MyDefaultTableModel();
		table = new JTable(model);
		model.addColumn("Varrable");
	    model.addColumn("Value");
		model.addRow(new Object[] {"v1","v2"});
		scrollPane = new JScrollPane(table);
		scrollPane.setAutoscrolls(true);
		frame.add(scrollPane);

		// reset event handler
		Listener resetListener = new Listener() {
			public void handleEvent(Event event) {
				
				text.setText("");
				text.setFocus();
			}
		};

		resetButton.addListener(SWT.Selection, resetListener);

		// Submit event handler
		Listener submitListener = new Listener() {
			public void handleEvent(Event event) {
								
				if (table.getColumnCount() > 0) {
					int count = table.getColumnCount();
					for (int i = 0; i < count; i++) {
						removeColumnAndData(table, 0);
					}
				} 
				try
				{
					Job job = new Job("Query Progress") {
					     protected IStatus run(IProgressMonitor monitor) {
					    	 monitor.beginTask("Now query to the server", 2);
					    	 monitor.worked(1);
					    	 PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
					 			public void run() {
					 				try {
					 					
					 					WebServiceService service = new WebServiceService();
										WebServiceDelegate delegate = service.getWebServicePort();
										List<ArrayListData> result = delegate.sparqlResultList(text.getText());
										List<String> varlist = result.get(0).getData(); 
										System.out.println(varlist);
										for (int i = 0; i < varlist.size(); i++)
										{
											model.addColumn(varlist.get(i));
										}
										
										for (int i = 1; i < result.size(); i++)
										{
											System.out.println(result.get(i).getData());
											model.addRow(result.get(i).getData().toArray());
										}
										
										Shell shell = new Shell(PlatformUI.getWorkbench().getDisplay());
										MessageDialog.openInformation(shell, "Query success!","Query result returned from server!");

					 				} catch (Exception e) {
					 					e.printStackTrace();
					 				}
					 			}
					 		});
					    	 	
					    	 monitor.done();
					           return Status.OK_STATUS;
					        }
					     };
					  job.setUser(true);
					  job.schedule();
				}
				catch(Exception e)
				{
					
				}
				
		}
			
		};

		submitButton.addListener(SWT.Selection, submitListener);
		initializeToolBar();

	}

	/**
	 * Create the actions
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar
	 */
	private void initializeToolBar() {
		IToolBarManager tbm = getViewSite().getActionBars().getToolBarManager();
	}

	/**
	 * Initialize the menu
	 */
	private void initializeMenu() {
		IMenuManager manager = getViewSite().getActionBars().getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

}
