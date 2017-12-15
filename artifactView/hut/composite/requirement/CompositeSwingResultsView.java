package hut.composite.requirement;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;

import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;



import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;



public class CompositeSwingResultsView extends Composite {

//	private CCombo comboFind;
//	private Text textFind;
	

	public CompositeSwingResultsView(Composite parent, int style) {
		super(parent, style);
		
		createControl(parent);
		createActions();
	}

	public void createControl(Composite parent) {
		// Composite container = new Composite(parent, SWT.NONE);
		setLayout(new swing2swt.layout.BorderLayout(0, 0));

		final Composite composite = new Composite(this, SWT.NONE);
		composite.setBackground(new org.eclipse.swt.graphics.Color(this.getDisplay(),163,199,230));
		composite.setLayout(new FormLayout());
		composite.setLayoutData(BorderLayout.NORTH);

		final CLabel resultsLabel = new CLabel(composite, SWT.NONE);
		resultsLabel.setBackground(new org.eclipse.swt.graphics.Color(this.getDisplay(),163,199,230));
		final FormData fd_resultsLabel = new FormData();
		fd_resultsLabel.right = new FormAttachment(0, 52);
		fd_resultsLabel.left = new FormAttachment(0, 5);
		resultsLabel.setLayoutData(fd_resultsLabel);
		resultsLabel.setText("Results");
		//resultsLabel.setFont(FontUtil.getFont("", 9, SWT.BOLD, false, true));
		final Composite composite_1 = new Composite(this, SWT.EMBEDDED);
		final FillLayout fillLayout_1 = new FillLayout();
		fillLayout_1.marginHeight = 3;
		composite_1.setLayout(fillLayout_1);

		final Frame frame = SWT_AWT.new_Frame(composite_1);

		final JScrollPane scrollPane = new JScrollPane();
		frame.add(scrollPane);

		/*table = new TableTextWrap();
		scrollPane.setColumnHeaderView(table);
		table.setSelectionBackground(new Color(163,199,230));//238,238,238)); // 225, 239, 252
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setGridColor( new Color(238,238,238) );//SystemColor.inactiveCaptionText);
*/		//table.getTableHeader().setBackground(new Color(204,153,102));

		createActions();
	}

	/*public void query(String query) {		
		table.removeAll();
		QueryExecution qe = QueryExecutionFactory.create(query.toString(),
				Syntax.syntaxARQ, loader.getModel());
		try {
			ResultSet results = qe.execSelect();
			String[] vars = (String[]) results.getResultVars().toArray(
					new String[0]);
			table.setColumns(vars);
			try 
			{
				while (results.hasNext()) 
				{
					String[] item = new String[vars.length];
					QuerySolution soln = results.nextSolution();
					int index = 0;
					for (String var : vars) 
					{
						RDFNode node = soln.get(var);
						if (node != null) 
						{
							if (node.isResource()) 
							{
								String uri = ((Resource) node).getURI();
								item[index] = uri
										.substring(uri.indexOf('#') + 1);
							} else if (node.isLiteral()) {
								Literal literal = (Literal) node;
								item[index] = literal.getLexicalForm();
							} else {
								item[index] = node.toString();
							}		
						}
						else {
							item[index] = "";
						}
						index++;
					}
					table.addRow(item);					
				}				
				table.getColumnModel().getColumn(0).setPreferredWidth(400);
				table.getColumnModel().getColumn(1).setPreferredWidth(25);
			} 
			catch (ArraySizeException e) {
				e.printStackTrace();
			}
		} 
		finally {
			qe.close();
		}
	}
*/
	private void createActions() {
		//table.addMouseListener(new SelectionListener(table));
	}
	/*public TableTextWrap getTable()
	{
		return table;
	}*/
	@Override
	protected void checkSubclass() {
	}
}
