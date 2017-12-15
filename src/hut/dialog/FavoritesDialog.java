package hut.dialog;

import hut.composite.filterlist.CompositeFilterList;
import hut.views.graph.ListNodeData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;

import mintani.valueconst.ConsistentOntology;
import mitani.activator.Activator;

import ontology.images.Images;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import swing2swt.layout.BorderLayout;
import swing2swt.layout.FlowLayout;


public class FavoritesDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	private Properties props = new Properties();
	private Text text = null;
	private CompositeFilterList compositeFilterList;
	
	public FavoritesDialog(Shell parent, Text text) {
		super(parent, SWT.NONE);
		this.text = text;
	}

	/**
	 * Open the dialog
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		return result;
	}

	/**
	 * Create contents of the dialog
	 */
	protected void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.RESIZE);
		shell.setImage(Images.imageRegistry.get(Images.SELECTFAVORITES));
		shell.setLayout(new FillLayout());
		shell.setSize(353, 307);
		shell.setText("Select a item from list");

		final Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new BorderLayout(0, 0));

		compositeFilterList = new CompositeFilterList(composite, SWT.NONE);

		final Composite composite_1 = new Composite(composite, SWT.NONE);
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(2);
		flowLayout.setHgap(2);
		composite_1.setLayout(flowLayout);
		composite_1.setLayoutData(BorderLayout.SOUTH);

		final Button selectButton = new Button(composite_1, SWT.NONE);
		selectButton.setImage(Images.imageRegistry.get(Images.SELECTFAVORITES));
		selectButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				ArrayList<ListNodeData> items = compositeFilterList.getSelectedItems();
				for (ListNodeData item : items) {
					text.setText(item.getUri());
				}
				shell.close();
			}
		});
		selectButton.setText("Select");

		final Composite composite_2 = new Composite(composite, SWT.NONE);
		composite_2.setLayout(new RowLayout());
		composite_2.setLayoutData(BorderLayout.NORTH);

		final Button removeButton = new Button(composite_2, SWT.NONE);
		removeButton.setImage(Images.imageRegistry.get(Images.DELETE));
		removeButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				ArrayList<ListNodeData> items = compositeFilterList.getSelectedItems();
				for (ListNodeData item : items) {
					props.remove(item.getUri());
					compositeFilterList.removeItem(item);					
				}
				save();
			}
		});
		removeButton.setText("Remove");

		loadData();
		//
	}
	
	private void loadData()
	{		
		try {
			InputStream in = new FileInputStream(new File(ConsistentOntology.selectFile));
			props.load(in);
			Enumeration keys = props.keys();
			while(keys.hasMoreElements())
			{
				String key = (String)keys.nextElement();
				String value = (String)props.get(key);
				compositeFilterList.addItem(new ListNodeData(value, key));
			}
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void save()
	{
		OutputStream out;
		try {
			out = new FileOutputStream(new File(ConsistentOntology.selectFile));
			props.store(out, "favorites file");
		}
		catch (IOException e) {
			e.printStackTrace();		
		}
	}
}
