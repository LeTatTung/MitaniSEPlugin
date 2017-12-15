package hut.composite.requirement;






import hut.dialog.DateTimeDialog;

import java.io.File;


import javax.swing.JFileChooser;




import mintani.valueconst.ConsistentOntology;
import ontology.images.Images;

import org.eclipse.jface.dialogs.InputDialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import swing2swt.layout.BorderLayout;



public class CompositeSearchRequirement extends Composite {
	private Button useStatusButton;
	private Button useStartDateButton;
	private Button useEndDateButton;
	private Text descriptionIn;
	private Text hasEndDate;
	private CCombo comboTestAt;
	private CCombo comboLastModifiedAt;
	private Text hasStartDate;
	private Text requireContent;
	private CCombo comboDeveloper;
	private Button queryButton;
	private	Button useDescriptionInButton;
	private Button selectRequirementButton;
	private Button newButton;
	private Button processingButton;
	private Button finishButton;
	private	CompositeResultsView compositeResultsView;
	private	CompositeResultsView compositeResultsView_1;
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public CompositeSearchRequirement(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout());
		
		final CTabFolder tabFolder = new CTabFolder(this, SWT.NONE);
//		tabFolder.addSelectionListener(new SelectionAdapter() {
//			public void widgetSelected(final SelectionEvent e) {
//				if(tabFolder.getSelectionIndex() != 0) // select table search
//				{
//					initTableSearch();
//				}					
//			}
//		});
		tabFolder.setSimple(false);

		final CTabItem semanticSearchTabItem = new CTabItem(tabFolder, SWT.NONE);
		semanticSearchTabItem.setImage(Images.imageRegistry.get(Images.SEARCH));
		semanticSearchTabItem.setText("Semantic Search");
		
		final Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new FillLayout());
		semanticSearchTabItem.setControl(composite);

		final ExpandBar expandBar = new ExpandBar(composite, SWT.NONE);

		final ExpandItem expandItemSearchOptions = new ExpandItem(expandBar, SWT.NONE);
		expandItemSearchOptions.setExpanded(true);
		expandItemSearchOptions.setText("Search Options");

		final ExpandItem expandItemSearchResults = new ExpandItem(expandBar, SWT.NONE);
		//expandItemSearchResults.setHeight(200);
		expandItemSearchResults.setExpanded(true);
		expandItemSearchResults.setText("Results");
				
		
		final Composite compositeSearchOptions = new Composite(expandBar, SWT.NONE);
		compositeSearchOptions.setSize(300, 300);
		final Label developerLabel = new Label(compositeSearchOptions, SWT.NONE);
		developerLabel.setBounds(5, 10, 83, 20);
		developerLabel.setText("Require Content");

		final Label successLabel = new Label(compositeSearchOptions, SWT.NONE);
		successLabel.setBounds(5, 36, 83, 13);
		successLabel.setText("Require Status");

		final Label lastModifiedAtLabel = new Label(compositeSearchOptions, SWT.NONE);
		lastModifiedAtLabel.setBounds(5, 62, 94, 13);
		lastModifiedAtLabel.setText("Start Date");

		final Label testAtLabel = new Label(compositeSearchOptions, SWT.NONE);
		testAtLabel.setBounds(5, 93, 83, 13);
		testAtLabel.setText("End Date");

		final Label descriptionInLabel = new Label(compositeSearchOptions, SWT.NONE);
		descriptionInLabel.setBounds(5, 123, 94, 30);
		descriptionInLabel.setText("Description In \nDocument");

		queryButton = new Button(compositeSearchOptions, SWT.NONE);
		queryButton.setImage(Images.imageRegistry.get(Images.RUN));
		queryButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				query();
			}
		});
		queryButton.setBounds(11, 176, 77, 23);
		queryButton.setText("Query");
		expandItemSearchOptions.setHeight(compositeSearchOptions.computeSize(SWT.DEFAULT, SWT.DEFAULT).y + 10);
		expandItemSearchOptions.setControl(compositeSearchOptions);

		comboDeveloper = new CCombo(compositeSearchOptions, SWT.BORDER);
		comboDeveloper.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		comboDeveloper.setEditable(false);
		comboDeveloper.setBounds(106, 10, 83, 20);

		requireContent = new Text(compositeSearchOptions, SWT.BORDER);
		requireContent.setBounds(217, 10, 162, 20);

		hasStartDate = new Text(compositeSearchOptions, SWT.BORDER);
		hasStartDate.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				DateTimeDialog dialog = new DateTimeDialog(((Text) e.widget).getShell());
    			if (dialog.open() == InputDialog.OK)
    				((Text) e.widget).setText(dialog.SYear+"-"+dialog.SMonth+"-"+dialog.SDay
    						//+'T'+dialog.SHour+":"+dialog.SMinute+":"+dialog.SSecond
    						);
			}
		});
		hasStartDate.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		hasStartDate.setEditable(false);
		hasStartDate.setBounds(217, 59, 162, 20);

		comboLastModifiedAt = new CCombo(compositeSearchOptions, SWT.BORDER);
		comboLastModifiedAt.setEditable(false);
		comboLastModifiedAt.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		comboLastModifiedAt.setBounds(105, 60, 84, 21);

		comboTestAt = new CCombo(compositeSearchOptions, SWT.BORDER);
		comboTestAt.setEditable(false);
		comboTestAt.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		comboTestAt.setBounds(105, 90, 84, 21);

		hasEndDate = new Text(compositeSearchOptions, SWT.BORDER);
		hasEndDate.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				DateTimeDialog dialog = new DateTimeDialog(((Text) e.widget).getShell());
    			if (dialog.open() == InputDialog.OK)
    				((Text) e.widget).setText(dialog.SYear+"-"+dialog.SMonth+"-"+dialog.SDay
    						//+'T'+dialog.SHour+":"+dialog.SMinute+":"+dialog.SSecond
    						);
			}
		});
		hasEndDate.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		hasEndDate.setEditable(false);
		hasEndDate.setBounds(217, 90, 162, 20);

		descriptionIn = new Text(compositeSearchOptions, SWT.BORDER);
		descriptionIn.setEditable(false);
		descriptionIn.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		descriptionIn.setBounds(105, 133, 274, 20);

		useDescriptionInButton = new Button(compositeSearchOptions, SWT.CHECK);
		useDescriptionInButton.setText("Use this option");
		useDescriptionInButton.setBounds(457, 134, 96, 16);

		useEndDateButton = new Button(compositeSearchOptions, SWT.CHECK);
		useEndDateButton.setBounds(457, 93, 96, 16);
		useEndDateButton.setText("Use this option");

		useStartDateButton = new Button(compositeSearchOptions, SWT.CHECK);
		useStartDateButton.setBounds(457, 62, 96, 16);
		useStartDateButton.setText("Use this option");

		useStatusButton = new Button(compositeSearchOptions, SWT.CHECK);
		useStatusButton.setBounds(457, 35, 96, 16);
		useStatusButton.setText("Use this option");

		selectRequirementButton = new Button(compositeSearchOptions, SWT.NONE);
		selectRequirementButton.setImage(Images.imageRegistry.get(Images.SELECT));
		selectRequirementButton.setText("Select");
		selectRequirementButton.setBounds(385, 131, 65, 23);
		selectRequirementButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {/*
				List<String> components = new ArrayList<String>();
				components.add("Requirement");			
				final SelectSoftwareComponentWizardPage selectRequirementPage = new SelectSoftwareComponentWizardPage(components);
				Wizard wizard = new Wizard(){
					public boolean performFinish() {
						Object[] checked = selectRequirementPage.getSelectedComponent();
						for (Object check : checked) {
							if (check instanceof String[]) {
								String[] row = (String[]) check;	
								requirement.setText(row[0]);
								break;
							}
						}
						return true;
					}
				};
				wizard.addPage(selectRequirementPage);
				WizardDialog dialog = new WizardDialog(((Button)e.widget).getShell(),wizard);
				dialog.setPageSize(500, 300);
				dialog.open();*/
			}
		});

	    newButton = new Button(compositeSearchOptions, SWT.RADIO);
		newButton.setText("New Test");
		newButton.setBounds(106, 31, 83, 23);

		processingButton = new Button(compositeSearchOptions, SWT.RADIO);
		processingButton.setText("Processing");
		processingButton.setBounds(195, 31, 94, 23);

		finishButton =  new Button(compositeSearchOptions, SWT.RADIO);
		finishButton.setText("Finish");
		finishButton.setBounds(314, 31, 65, 23);

		final Composite compositeSearchResults = new Composite(expandBar, SWT.NONE);
		compositeSearchResults.setLayout(new FillLayout());
		expandItemSearchResults.setControl(compositeSearchResults);
		compositeResultsView = new CompositeResultsView(compositeSearchResults, SWT.NONE);
		expandItemSearchResults.setHeight(250);

		final CTabItem tableSearchTabItem = new CTabItem(tabFolder, SWT.NONE);
		tableSearchTabItem.setImage(Images.imageRegistry.get(Images.FILTER));
		tableSearchTabItem.setText("Result Search");

		
		final Composite compositeTableSearch = new Composite(tabFolder, SWT.NONE);
		compositeTableSearch.setLayout(new BorderLayout(0, 0));
		tableSearchTabItem.setControl(compositeTableSearch);

		compositeResultsView_1 = new CompositeResultsView(compositeTableSearch, SWT.NONE);

		final Composite composite_1 = new Composite(compositeTableSearch, SWT.NONE);
		composite_1.setLayout(new RowLayout());
		composite_1.setLayoutData(BorderLayout.NORTH);

		final Button refreshButton = new Button(composite_1, SWT.NONE);
		refreshButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				//initTableSearch();
			}
		});
		refreshButton.setImage(Images.imageRegistry.get(Images.REFRESH));
		refreshButton.setText("Refresh");
		
		tabFolder.setSelection(0);
		initData();
		//
	}
	
	private void initData()
	{
		comboDeveloper.add("Contains");
		comboDeveloper.add("Starts with");
		comboDeveloper.add("End with");
		comboDeveloper.add("Equal");
		comboDeveloper.select(0);
		
		comboLastModifiedAt.add(">");
		comboLastModifiedAt.add(">=");
		comboLastModifiedAt.add("<");
		comboLastModifiedAt.add("<=");
		comboLastModifiedAt.add("=");
		comboLastModifiedAt.select(0);
				
		comboTestAt.add(">");
		comboTestAt.add(">=");
		comboTestAt.add("<");
		comboTestAt.add("<=");
		comboTestAt.add("=");
		comboTestAt.select(0);		
		//initTableSearch();		
	}
	
	private void query()
	{
		StringBuffer query = new StringBuffer(ConsistentOntology.prefix)
			//.append("SELECT * \n")
			.append("\nSELECT DISTINCT ?Require ?startDate ?endDate ?requirecontent\n")
			.append("WHERE\n")
			.append("{\n")
			.append("?Require SEC:hasDirectType ?Type . \n");
		StringBuffer filter = new StringBuffer("FILTER((?Type = 'Requirement')) \n");
		if(!requireContent.getText().trim().equals(""))
		{
			query.append("?Require SEC:hasContentRequire ?requirecontent . \n");
			switch (comboDeveloper.getSelectionIndex()) {
				case 0: // contains
					filter.append("FILTER(fn:contains(fn:lower-case(?requirecontent),'" + requireContent.getText().toLowerCase() + "'))\n");
					break;
				case 1: // start with
					filter.append("FILTER(fn:starts-with(fn:lower-case(?requirecontent),'" + requireContent.getText().toLowerCase() + "'))\n");
					break;
				case 2: // end with
					filter.append("FILTER(fn:ends-with(fn:lower-case(?requirecontent) ,'" + requireContent.getText().toLowerCase() + "'))\n");
					break;
				case 3: // equal
					filter.append("FILTER(fn:lower-case(?requirecontent) = '" + requireContent.getText().toLowerCase() + "')\n");
					break;
			} 
		}
		else
			query.append("optional{?Require SEC:hasContentRequire ?requirecontent}\n");
		
	  if(useStatusButton.getSelection()){
		  query.append("?Require SEC:hasStatusRequire ?statusrequire . \n");
		  if(newButton.getSelection()){
			  filter.append("FILTER(?statusrequire = '0'^^xsd:integer)\n");
		  }
		  if(processingButton.getSelection()){
			  filter.append("FILTER(?statusrequire = '1'^^xsd:integer)\n");
		  }
		  if(finishButton.getSelection()){
			  filter.append("FILTER(?statusrequire = '2'^^xsd:integer)\n");
		  }
		  
	  }else{
		  query.append("optional{?Require SEC:hasStatusRequire ?statusrequire}\n");
	  }
		
		
		
		if(useStartDateButton.getSelection())
		{
			if(!hasStartDate.getText().equals(""))
			{
				query.append("?Require SEC:hasStartDate ?startDate . \n");
				switch (comboLastModifiedAt.getSelectionIndex()) {				
					case 0: // >
						filter.append("FILTER(?startDate > '" + hasStartDate.getText() + "'^^xsd:date)\n");
						break;
					case 1: // >=
						filter.append("FILTER(?startDate >= '" + hasStartDate.getText() + "'^^xsd:date)\n");
						break;
					case 2: // <
						filter.append("FILTER(?startDate < '" + hasStartDate.getText() + "'^^xsd:date)\n");
						break;
					case 3: // <=
						filter.append("FILTER(?startDate <= '" + hasStartDate.getText() + "'^^xsd:date)\n");
						break;
					case 4: // =
						filter.append("FILTER(?startDate = '" + hasStartDate.getText() + "'^^xsd:date)\n");
						break;	
				} 
			}
			else
				query.append("optional{?Require SEC:hasStartDate ?startDate}\n");	
		}
		else
			query.append("optional{?Require SEC:hasStartDate ?startDate}\n");	
		if(useEndDateButton.getSelection())
		{
			if(!hasEndDate.getText().equals(""))
			{
				query.append("?Require SEC:hasEndDate ?endDate . ");
				switch (comboTestAt.getSelectionIndex()) {
					case 0: // >
						filter.append("FILTER(?endDate > '" + hasEndDate.getText() + "'^^xsd:date)\n");
						break;
					case 1: // >=
						filter.append("FILTER(?endDate >= '" + hasEndDate.getText() + "'^^xsd:date)\n");
						break;
					case 2: // <
						filter.append("FILTER(?endDate < '" + hasEndDate.getText() + "'^^xsd:date)\n");
						break;
					case 3: // <=
						filter.append("FILTER(?endDate <= ('" + hasEndDate.getText() + "'^^xsd:date)\n");
						break;
					case 4: // =
						filter.append("FILTER(?endDate = '" + hasEndDate.getText() + "'^^xsd:date)\n");
						break;
				} 
			}
			else
				query.append("optional{?Require SEC:hasEndDate ?endDate}\n");	
		}
		else		
			query.append("optional{?Require SEC:hasEndDate ?endDate}\n");		
		/*if(useDescriptionInButton.getSelection())
		{
			if(!descriptionIn.getText().equals(""))
			{
				query.append("?Test SEC:hasDescriptionInDocument ?hasDescriptionInDocument . \n");
				query.append("?hasDescriptionInDocument sec:localURI ?DocumentURI . \n");
				filter.append("FILTER(fn:contains(?DocumentURI,'"+ descriptionIn.getText() + "'))\n");
			}
		}
		else
			query.append("optional{?Test sec:hasDescriptionInDocument ?hasDescriptionInDocument}\n");
		*/
		
		//query.append("optional{?Test SEC:hasDescriptionInElement ?hasDescriptionInElement}\n");
		query.append(filter).append("}");
		System.out.println(query.toString());
		runQuery(query.toString());
		
	}
	
	private void runQuery(String query)
	{
		compositeResultsView.query(query);
	}

}
