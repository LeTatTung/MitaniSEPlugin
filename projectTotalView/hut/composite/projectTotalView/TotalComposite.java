package hut.composite.projectTotalView;

import hut.DataProvider.Provider;
import hut.DataProvider.TableContentProvider;
import hut.DataProvider.TableLabelProvider;

import java.util.ArrayList;
import java.util.List;

import mintani.valueconst.ConsistentOntology;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
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
import org.eclipse.ui.forms.widgets.FormToolkit;
import com.swtdesigner.SWTResourceManager;
import swing2swt.layout.BorderLayout;

public class TotalComposite extends Composite {

	private Table table_3;
	private Table table_2;
	private Table table_1;
	private Table table;
	private Combo combo;
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	Provider provider = new Provider();
	TableViewer totalTableViewer;
	TableViewer requirementsViewer;
	TableViewer developersViewer;
	TableViewer testersViewer;
	
	
	/**
	 * phan luu du lieu tong the cho toan view
	 */
	ArrayList<String> listProjectTeam = new ArrayList<String>();
	String projectSelectedId            = null;
	
	List<String> requirements = new ArrayList<String>();
	ArrayList<String> developerTeams= new ArrayList<String>();
	ArrayList<String> testerTeams = new ArrayList<String>();
	
	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public TotalComposite(Composite parent, int style) {
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
				
				// load lai toan bo du lieu
				refreshAllData();
			}
		});
		combo = comboViewer.getCombo();
		combo.setLayoutData(BorderLayout.CENTER);
		
		// Lay ve list cac ProjectTeam
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

		totalTableViewer = new TableViewer(generalInformationGroup, SWT.NONE);
		String[] titles = {"ID", "name", "Description", "Developer team", "Tester team", "Requirement", "Developers", "Testers"};
		int[]    bounds = {200 ,200, 100, 100, 100,100, 100,100};
		createColumns(totalTableViewer, titles, bounds);
		totalTableViewer.setContentProvider(new TableContentProvider());
		totalTableViewer.setLabelProvider(new TableLabelProvider());
		setDataForTotalTable();
	
		table = totalTableViewer.getTable();
		table.setLayoutData(BorderLayout.CENTER);
		toolkit.adapt(table, true, true);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

	
		
		

		
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
		RequirementComposite.setLayout(new BorderLayout(0, 0));
		toolkit.adapt(RequirementComposite);
		requirementsDetailTabItem.setControl(RequirementComposite);

		requirementsViewer = new TableViewer(RequirementComposite, SWT.NONE);
		String[] requirementTitles = {"ID", "Describe by doc", "Code by developer", "Current status", "installed in code element"};
		int[]    requirementBounds = {200 ,200, 200, 100, 200};
		createColumns(requirementsViewer, requirementTitles, requirementBounds);
		requirementsViewer.setContentProvider(new TableContentProvider());
		requirementsViewer.setLabelProvider(new TableLabelProvider());
		setDataForRequirementTable();
		
		table_1 = requirementsViewer.getTable();
		table_1.setLayoutData(BorderLayout.CENTER);
		toolkit.adapt(table_1, true, true);
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);

		final TabItem developerTabItem = new TabItem(tabFolder, SWT.NONE);
		developerTabItem.setText("Developers detail");

		final Composite DeveloperComposite = new Composite(tabFolder, SWT.NONE);
		DeveloperComposite.setLayout(new BorderLayout(0, 0));
		toolkit.adapt(DeveloperComposite);
		developerTabItem.setControl(DeveloperComposite);

		developersViewer = new TableViewer(DeveloperComposite, SWT.NONE);
		String[] developerTitles = {"ID", "Name", "Requirements", "completed", "installing", "not install"};
		int[] developerBounds = {200 ,200, 200, 100, 100, 100};
		createColumns(developersViewer, developerTitles, developerBounds);
		developersViewer.setContentProvider(new TableContentProvider());
		developersViewer.setLabelProvider(new TableLabelProvider());
		setDataForDeveloperTable();
		
		table_2 = developersViewer.getTable();
		table_2.setLayoutData(BorderLayout.CENTER);
		toolkit.adapt(table_2, true, true);
		table_2.setLinesVisible(true);
		table_2.setHeaderVisible(true);

		final TabItem testersDetailTabItem = new TabItem(tabFolder, SWT.NONE);
		testersDetailTabItem.setText("Testers detail");

		final Composite TestersComposite = new Composite(tabFolder, SWT.NONE);
		TestersComposite.setLayout(new BorderLayout(0, 0));
		toolkit.adapt(TestersComposite);
		testersDetailTabItem.setControl(TestersComposite);

		testersViewer = new TableViewer(TestersComposite, SWT.NONE);
		String[] testerTitles = {"ID", "Name", "Test", "Test success", "Is Testing", "Not test"};
		int[] testerBounds = {200 ,200, 200, 200, 200, 200};
		createColumns(testersViewer, testerTitles, testerBounds);
		testersViewer.setContentProvider(new TableContentProvider());
		testersViewer.setLabelProvider(new TableLabelProvider());
		setDataForTesterTable();
		
		table_3 = testersViewer.getTable();
		table_3.setLayoutData(BorderLayout.CENTER);
		toolkit.adapt(table_3, true, true);
		table_3.setLinesVisible(true);
		table_3.setHeaderVisible(true);
		//
	}

	private void createColumns(TableViewer tableViewer, String[] titles, int[] bounds) {
		for (int i = 0; i < titles.length; i++) {
			TableViewerColumn column = new TableViewerColumn(tableViewer, SWT.NONE);
			column.getColumn().setText(titles[i]);
			if (bounds.length == titles.length){
				column.getColumn().setWidth(bounds[i]);
			}
			column.getColumn().setResizable(true);
			column.getColumn().setMoveable(true);
		}
		Table table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
	}
	
	private void refreshAllData() {
		provider = new Provider();
		setDataForTotalTable();
		setDataForRequirementTable();
		setDataForDeveloperTable();
		setDataForTesterTable();
	}

	private void setDataForTotalTable(){
		ArrayList<ArrayList<String>> regularInformation=null;
		if (projectSelectedId!=null){
			// lay ra thong tin chung ve project
			regularInformation = provider.getGeneralInformation(projectSelectedId);
		}
		totalTableViewer.setInput(regularInformation);
	}
	
	private void setDataForRequirementTable(){
		ArrayList<ArrayList<String>> requirementsInformation=null;
		if (projectSelectedId!=null){
			// lay ra thong tin chung ve project
			requirementsInformation = provider.getrequirementsInformation(projectSelectedId);
		}
		requirementsViewer.setInput(requirementsInformation);
	}
	
	private void setDataForDeveloperTable() {
		ArrayList<ArrayList<String>> developersInformation = null;
		if (projectSelectedId!=null){
			// lay ra thong tin chung ve project
			developersInformation = provider.getDevelopersInformation(projectSelectedId);
		}
		developersViewer.setInput(developersInformation);
	}
	
	private void setDataForTesterTable() {
		ArrayList<ArrayList<String>> testersInformation = null;
		if (projectSelectedId!=null){
			// lay ra thong tin chung ve project
			testersInformation = provider.getTestersInformation(projectSelectedId);
		}
		testersViewer.setInput(testersInformation);
	}

	public void setRequirements(List<String> requirements) {
		this.requirements = requirements;
	}

	public ArrayList<String> getDeveloperTeams() {
		return developerTeams;
	}

	public void setDeveloperTeams(ArrayList<String> developerTeams) {
		this.developerTeams = developerTeams;
	}
}
