package hut.composite.projectTotalView;

import hut.DataProvider.Provider;

import java.util.ArrayList;
import java.util.List;

import mintani.valueconst.ConsistentOntology;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.forms.widgets.FormToolkit;
import com.swtdesigner.SWTResourceManager;
import swing2swt.layout.BorderLayout;

public class TotalView extends Composite {

	private Table table;
	private Combo combo;
	private TableViewer tableViewer;
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	/**
	 * phan luu du lieu tong the cho toan view
	 */
	ArrayList<String> listProjectTeam = new ArrayList<String>();
	String projectSelectedId            = null;
	ArrayList<String> developerTeam   = new ArrayList<String>();
	ArrayList<String> testerTeam      = new ArrayList<String>();
	
	ArrayList<Requirement> requirements    = new ArrayList<Requirement>();
	ArrayList<Developer> developers        = new ArrayList<Developer>();
	ArrayList<Tester>    testers           = new ArrayList<Tester>(); 
	
	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public TotalView(Composite parent, int style) {
		super(parent, style);
		setLayout(new BorderLayout(0, 0));
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);

		final Group resultComposite = new Group(this, SWT.NONE);
		resultComposite.setFont(SWTResourceManager.getFont("Georgia", 12, SWT.BOLD | SWT.ITALIC));
		resultComposite.setText("Rate of process");
		resultComposite.setLayout(new BorderLayout(0, 0));
		toolkit.adapt(resultComposite);
		resultComposite.setLayoutData(BorderLayout.CENTER);

		final Composite topComposite = new Composite(resultComposite, SWT.NONE);
		topComposite.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.BOLD));
		topComposite.setLayoutData(BorderLayout.NORTH);
		topComposite.setLayout(new BorderLayout(0, 0));
		toolkit.adapt(topComposite);

		// khi click chon projectTeam moi thi tu dong cap nhat thong tin cho view
		final ComboViewer comboViewer = new ComboViewer(topComposite, SWT.BORDER);
		comboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(final SelectionChangedEvent arg0) {
				// Id cua project duoc chon
				projectSelectedId = ConsistentOntology.DOC_NAMESPACE+combo.getItem(combo.getSelectionIndex());
				
				// listId cua developerTeam
				
				
			}
		});
		combo = comboViewer.getCombo();
		combo.setLayoutData(BorderLayout.CENTER);
		
		// Lay ve list cac ProjectTeam
		Provider provider = new Provider();
		List<String> listProjectId = provider.getListProjectTeam();
		String[] listItem = new String[listProjectId.size()];
		for(int i=0; i<listItem.length; i++){
			listItem[i] = listProjectId.get(i).substring(ConsistentOntology.DOC_NAMESPACE.length());
		}
		
		combo.setItems(listItem);
		
		toolkit.adapt(combo, true, true);

		final Button viewRateOfButton = new Button(topComposite, SWT.NONE);
		viewRateOfButton.setFont(SWTResourceManager.getFont("Tahoma", 10, SWT.NONE));
		toolkit.adapt(viewRateOfButton, true, true);
		viewRateOfButton.setText("refresh rate of process");
		viewRateOfButton.setLayoutData(BorderLayout.EAST);

		final CLabel chooselabel = new CLabel(topComposite, SWT.NONE);
		chooselabel.setFont(SWTResourceManager.getFont("", 10, SWT.BOLD));
		toolkit.adapt(chooselabel, true, true);
		chooselabel.setText("choose a project");
		chooselabel.setLayoutData(BorderLayout.WEST);

		final Composite centerComposite = new Composite(resultComposite, SWT.NONE);
		centerComposite.setLayout(new BorderLayout(0, 0));
		toolkit.adapt(centerComposite);
		centerComposite.setLayoutData(BorderLayout.CENTER);

		final Composite topInformationComposite = new Composite(centerComposite, SWT.NONE);
		topInformationComposite.setLayout(new BorderLayout(0, 0));
		topInformationComposite.setLayoutData(BorderLayout.NORTH);
		toolkit.adapt(topInformationComposite);

		final Group generalInformationGroup = new Group(topInformationComposite, SWT.NONE);
		generalInformationGroup.setText("General information");
		generalInformationGroup.setLayoutData(BorderLayout.CENTER);
		generalInformationGroup.setLayout(new BorderLayout(0, 0));
		toolkit.adapt(generalInformationGroup);

		tableViewer = new TableViewer(generalInformationGroup, SWT.NONE);
		tableViewer.setContentProvider(new TotalTableContentprovider());
		
		table = tableViewer.getTable();
		table.setLayoutData(BorderLayout.CENTER);
		toolkit.adapt(table, true, true);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		final TableColumn newColumnTableColumn = new TableColumn(table, SWT.NONE);
		newColumnTableColumn.setWidth(53);
		newColumnTableColumn.setText("ID");

		final TableColumn newColumnTableColumn_1 = new TableColumn(table, SWT.NONE);
		newColumnTableColumn_1.setWidth(99);
		newColumnTableColumn_1.setText("Developer team");

		final TableColumn newColumnTableColumn_2 = new TableColumn(table, SWT.NONE);
		newColumnTableColumn_2.setWidth(82);
		newColumnTableColumn_2.setText("Tester team");

		final TableColumn newColumnTableColumn_3 = new TableColumn(table, SWT.NONE);
		newColumnTableColumn_3.setWidth(75);
		newColumnTableColumn_3.setText("Developer");

		final TableColumn newColumnTableColumn_4 = new TableColumn(table, SWT.NONE);
		newColumnTableColumn_4.setWidth(72);
		newColumnTableColumn_4.setText("Tester");

		final TableColumn newColumnTableColumn_5 = new TableColumn(table, SWT.NONE);
		newColumnTableColumn_5.setWidth(100);
		newColumnTableColumn_5.setText("requirement");

		final Composite centerInformationComposite = new Composite(centerComposite, SWT.NONE);
		centerInformationComposite.setLayout(new FillLayout());
		toolkit.adapt(centerInformationComposite);
		centerInformationComposite.setLayoutData(BorderLayout.CENTER);

		final Group detailsGroup = new Group(centerInformationComposite, SWT.NONE);
		detailsGroup.setText("Detail");
		detailsGroup.setLayout(new BorderLayout(0, 0));
		toolkit.adapt(detailsGroup);

		final TabFolder tabFolder = new TabFolder(detailsGroup, SWT.NONE);
		tabFolder.setLayoutData(BorderLayout.CENTER);
		toolkit.adapt(tabFolder, true, true);

		final TabItem requirementsDetailTabItem = new TabItem(tabFolder, SWT.NONE);
		requirementsDetailTabItem.setText("Requirements detail");

		final Composite RequirementComposite = new Composite(tabFolder, SWT.NONE);
		toolkit.adapt(RequirementComposite);
		requirementsDetailTabItem.setControl(RequirementComposite);

		final TabItem developerTabItem = new TabItem(tabFolder, SWT.NONE);
		developerTabItem.setText("Developers detail");

		final Composite DeveloperComposite = new Composite(tabFolder, SWT.NONE);
		toolkit.adapt(DeveloperComposite);
		developerTabItem.setControl(DeveloperComposite);

		final TabItem testersDetailTabItem = new TabItem(tabFolder, SWT.NONE);
		testersDetailTabItem.setText("Testers detail");

		final Composite TestersComposite = new Composite(tabFolder, SWT.NONE);
		toolkit.adapt(TestersComposite);
		testersDetailTabItem.setControl(TestersComposite);

		final TabItem designerDetailTabItem = new TabItem(tabFolder, SWT.NONE);
		designerDetailTabItem.setText("Designers detail");
		//
	}

}
