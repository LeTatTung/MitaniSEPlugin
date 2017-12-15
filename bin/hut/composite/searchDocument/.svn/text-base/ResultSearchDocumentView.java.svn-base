package hut.composite.searchDocument;

import hut.composite.search.tableWrap.SelectionListener;
import hut.composite.search.tableWrap.TableTextWrap;

import java.awt.Color;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.forms.widgets.FormToolkit;

import swing2swt.layout.BorderLayout;
import ws.data.ArrayListData;

public class ResultSearchDocumentView extends Composite {


	private TableTextWrap table;
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private List<String[]> items = new ArrayList<String[]>();
	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public ResultSearchDocumentView(Composite parent, int style) {
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
		notificationResultLabel.setText("Search Document Result");

		final Composite composite_1 = new Composite(this, SWT.EMBEDDED);
		composite_1.setLayout(new FillLayout());
		toolkit.adapt(composite_1);
		composite_1.setLayoutData(BorderLayout.CENTER);

		
		final Frame frame = SWT_AWT.new_Frame(composite_1);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAutoscrolls(true);
		frame.add(scrollPane);

		table = new TableTextWrap();
		scrollPane.setViewportView(table);
		table.setSelectionBackground(new Color(163,199,230));//238,238,238)); // 225, 239, 252
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setGridColor( new Color(238,238,238) );//SystemColor.inactiveCaptionText);
		//table.getTableHeader().setBackground(new Color(204,153,102));

		createActions();
	}
	
	public void bindData(List<ArrayListData> listInstance) {
		table.removeAll();
		//Lay cac gia tri header
		List<String> colitems = listInstance.get(0).getData();

		String[] vars = colitems.toArray(new String[0]);

		table.setColumns(vars);
		table.getColumnModel().getColumn(0).setPreferredWidth(400);
		table.getColumnModel().getColumn(1).setPreferredWidth(25);
		try {
			for (int i = 1; i < listInstance.size(); i++) {
				int index = 0;
				List<String> subitems = listInstance.get(i).getData();
				String[] item = new String[subitems.size()];

				for (int j = 0; j < subitems.size(); j++) {
					item[index] = subitems.get(j).toString();
					index++;
				}
				table.addRow(item);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	
	private void createActions() {
		table.addMouseListener(new SelectionListener(table));
	}

}
