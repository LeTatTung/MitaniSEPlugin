package hut.composite.test;


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

import swing2swt.layout.BorderLayout;



public class CompositeSearchTest extends Composite {
	private Text requirement;
	private Button useSuccessButton;
	private Button useLastModifiedAtButton;
	private Button useTestedAtButton;
	private Text descriptionIn;
	private Text implementedBy;
	private Text testedAt;
	private CCombo comboTestAt;
	private CCombo comboLastModifiedAt;
	private Text lastModifiedAt;
	private Text developer;
	private CCombo comboDeveloper;
	private	Button successButton;
	private Button queryButton;
	private	Button selectSoftwareComponentButton;
	private	Button useImplementedByButton;
	private Button selectRequirementButton;
	private Button useRequirementButton;
	private	CompositeResultsView compositeResultsView;
	private	CompositeResultsView compositeResultsView_1;
	
	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public CompositeSearchTest(Composite parent, int style) {
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
		developerLabel.setText("Developer");

		successButton = new Button(compositeSearchOptions, SWT.CHECK);
		successButton.setBounds(105, 38, 30, 16);

		final Label successLabel = new Label(compositeSearchOptions, SWT.NONE);
		successLabel.setBounds(5, 36, 69, 13);
		successLabel.setText("Success");

		final Label lastModifiedAtLabel = new Label(compositeSearchOptions, SWT.NONE);
		lastModifiedAtLabel.setBounds(5, 62, 94, 13);
		lastModifiedAtLabel.setText("Last Modified At");

		final Label testAtLabel = new Label(compositeSearchOptions, SWT.NONE);
		testAtLabel.setBounds(5, 93, 83, 13);
		testAtLabel.setText("Tested At");

		final Label implementedByLabel = new Label(compositeSearchOptions, SWT.NONE);
		implementedByLabel.setBounds(5, 125, 94, 13);
		implementedByLabel.setText("Test Of");

		/*final Label descriptionInLabel = new Label(compositeSearchOptions, SWT.NONE);
		descriptionInLabel.setBounds(5, 154, 94, 30);
		descriptionInLabel.setText("Description In \nDocument");*/

		queryButton = new Button(compositeSearchOptions, SWT.NONE);
		queryButton.setImage(Images.imageRegistry.get(Images.RUN));
		queryButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				query();
			}
		});
		queryButton.setBounds(5, 218, 77, 23);
		queryButton.setText("Query");
		expandItemSearchOptions.setHeight(compositeSearchOptions.computeSize(SWT.DEFAULT, SWT.DEFAULT).y + 10);
		expandItemSearchOptions.setControl(compositeSearchOptions);

		comboDeveloper = new CCombo(compositeSearchOptions, SWT.BORDER);
		comboDeveloper.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		comboDeveloper.setEditable(false);
		comboDeveloper.setBounds(106, 10, 83, 20);

		developer = new Text(compositeSearchOptions, SWT.BORDER);
		developer.setBounds(217, 10, 162, 20);

		lastModifiedAt = new Text(compositeSearchOptions, SWT.BORDER);
		lastModifiedAt.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				DateTimeDialog dialog = new DateTimeDialog(((Text) e.widget).getShell());
    			if (dialog.open() == InputDialog.OK)
    				((Text) e.widget).setText(dialog.SYear+"-"+dialog.SMonth+"-"+dialog.SDay
    						+'T'+dialog.SHour+":"+dialog.SMinute+":"+dialog.SSecond
    						);
			}
		});
		lastModifiedAt.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		lastModifiedAt.setEditable(false);
		lastModifiedAt.setBounds(217, 59, 162, 20);

		comboLastModifiedAt = new CCombo(compositeSearchOptions, SWT.BORDER);
		comboLastModifiedAt.setEditable(false);
		comboLastModifiedAt.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		comboLastModifiedAt.setBounds(105, 60, 84, 21);

		comboTestAt = new CCombo(compositeSearchOptions, SWT.BORDER);
		comboTestAt.setEditable(false);
		comboTestAt.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		comboTestAt.setBounds(105, 90, 84, 21);

		testedAt = new Text(compositeSearchOptions, SWT.BORDER);
		testedAt.addMouseListener(new MouseAdapter() {
			public void mouseDown(final MouseEvent e) {
				DateTimeDialog dialog = new DateTimeDialog(((Text) e.widget).getShell());
    			if (dialog.open() == InputDialog.OK)
    				((Text) e.widget).setText(dialog.SYear+"-"+dialog.SMonth+"-"+dialog.SDay
    						+'T'+dialog.SHour+":"+dialog.SMinute+":"+dialog.SSecond
    						);
			}
		});
		testedAt.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		testedAt.setEditable(false);
		testedAt.setBounds(217, 90, 162, 20);

		implementedBy = new Text(compositeSearchOptions, SWT.BORDER);
		implementedBy.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		implementedBy.setEditable(false);
		implementedBy.setBounds(105, 122, 274, 20);

		selectSoftwareComponentButton = new Button(compositeSearchOptions, SWT.NONE);
		selectSoftwareComponentButton.setImage(Images.imageRegistry.get(Images.SELECT));
		selectSoftwareComponentButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				/*SelectSoftwareComponentWizard wizard = new SelectSoftwareComponentWizard(implementedBy);
				WizardDialog dialog = new WizardDialog(((Button)e.widget).getShell(),wizard);
				dialog.setPageSize(500, 300);
				dialog.open();*/
			}
		});
		selectSoftwareComponentButton.setText("Select");
		selectSoftwareComponentButton.setBounds(385, 120, 65, 23);

		/*descriptionIn = new Text(compositeSearchOptions, SWT.BORDER);
		descriptionIn.setEditable(false);
		descriptionIn.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		descriptionIn.setBounds(105, 158, 274, 20);*/

		/*browseButton = new Button(compositeSearchOptions, SWT.NONE);
		browseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

				int approved1=chooser.showSaveDialog(null);
				if(approved1==JFileChooser.APPROVE_OPTION){
					File file = chooser.getSelectedFile();
					descriptionIn.setText(file.getName());
				}
			}
		});
		browseButton.setText("Browse");
		browseButton.setBounds(385, 154, 65, 23);*/

		/*useDescriptionInButton = new Button(compositeSearchOptions, SWT.CHECK);
		useDescriptionInButton.setText("Use this option");
		useDescriptionInButton.setBounds(457, 158, 96, 16);*/

		useImplementedByButton = new Button(compositeSearchOptions, SWT.CHECK);
		useImplementedByButton.setBounds(457, 125, 96, 16);
		useImplementedByButton.setText("Use this option");

		useTestedAtButton = new Button(compositeSearchOptions, SWT.CHECK);
		useTestedAtButton.setBounds(457, 93, 96, 16);
		useTestedAtButton.setText("Use this option");

		useLastModifiedAtButton = new Button(compositeSearchOptions, SWT.CHECK);
		useLastModifiedAtButton.setBounds(457, 62, 96, 16);
		useLastModifiedAtButton.setText("Use this option");

		useSuccessButton = new Button(compositeSearchOptions, SWT.CHECK);
		useSuccessButton.setBounds(457, 35, 96, 16);
		useSuccessButton.setText("Use this option");

		final Label testForRequirementLabel = new Label(compositeSearchOptions, SWT.NONE);
		testForRequirementLabel.setText("Requirement");
		testForRequirementLabel.setBounds(5, 158, 83, 20);

		requirement = new Text(compositeSearchOptions, SWT.BORDER);
		requirement.setBounds(105, 155, 274, 20);

		selectRequirementButton = new Button(compositeSearchOptions, SWT.NONE);
		selectRequirementButton.setImage(Images.imageRegistry.get(Images.SELECT));
		selectRequirementButton.setText("Select");
		selectRequirementButton.setBounds(385, 153, 65, 23);
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
		
		useRequirementButton = new Button(compositeSearchOptions, SWT.CHECK);
		useRequirementButton.setText("Use this option");
		useRequirementButton.setBounds(457, 156, 85, 16);

		final Composite compositeSearchResults = new Composite(expandBar, SWT.NONE);
		compositeSearchResults.setLayout(new FillLayout());
		expandItemSearchResults.setControl(compositeSearchResults);
		compositeResultsView = new CompositeResultsView(compositeSearchResults, SWT.NONE);
		expandItemSearchResults.setHeight(250);

		final CTabItem tableSearchTabItem = new CTabItem(tabFolder, SWT.NONE);
		tableSearchTabItem.setImage(Images.imageRegistry.get(Images.SEARCH));
		tableSearchTabItem.setText("Table Search");

		
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
			.append("\nSELECT ?Test ?developer ?success ?lastModifiedAt ?testedAt ?isTestOf ?DocumentURI ?Requirement \n")
			.append("WHERE\n")
			.append("{\n")
			.append("?Test SEC:hasDirectType ?Type . \n");
		StringBuffer filter = new StringBuffer("FILTER((?Type = 'Test') || (?Type = 'UnitTest') || (?Type = 'IntergatedTest')) \n");
		if(!developer.getText().trim().equals(""))
		{
			query.append("?Test SEC:hasDeveloper ?developer . \n");
			switch (comboDeveloper.getSelectionIndex()) {
				case 0: // contains
					filter.append("FILTER(fn:contains(fn:lower-case(?developer),'" + developer.getText().toLowerCase() + "'))\n");
					break;
				case 1: // start with
					filter.append("FILTER(fn:starts-with(fn:lower-case(?developer),'" + developer.getText().toLowerCase() + "'))\n");
					break;
				case 2: // end with
					filter.append("FILTER(fn:ends-with(fn:lower-case(?developer) ,'" + developer.getText().toLowerCase() + "'))\n");
					break;
				case 3: // equal
					filter.append("FILTER(fn:lower-case(?developer) = '" + developer.getText().toLowerCase() + "')\n");
					break;
			} 
		}
		else
			query.append("optional{?Test SEC:hasDeveloper ?developer}\n");
		if(useSuccessButton.getSelection())
		{
			query.append("?Test SEC:succeeded \""+ String.valueOf(successButton.getSelection()) + "\"^^xsd:boolean . \n");
//			query.append("?Test sec:succeeded ?success ");			
		}
		else
			query.append("optional{?Test SEC:succeeded ?success}\n");	
		if(useLastModifiedAtButton.getSelection())
		{
			if(!lastModifiedAt.getText().equals(""))
			{
				query.append("?Test SEC:lastModifiedAt ?lastModifiedAt . \n");
				switch (comboLastModifiedAt.getSelectionIndex()) {				
					case 0: // >
						filter.append("FILTER(?lastModifiedAt > '" + lastModifiedAt.getText() + "'^^xsd:dateTime)\n");
						break;
					case 1: // >=
						filter.append("FILTER(?lastModifiedAt >= '" + lastModifiedAt.getText() + "'^^xsd:dateTime)\n");
						break;
					case 2: // <
						filter.append("FILTER(?lastModifiedAt < '" + lastModifiedAt.getText() + "'^^xsd:dateTime)\n");
						break;
					case 3: // <=
						filter.append("FILTER(?lastModifiedAt <= '" + lastModifiedAt.getText() + "'^^xsd:dateTime)\n");
						break;
					case 4: // =
						filter.append("FILTER(?lastModifiedAt = '" + lastModifiedAt.getText() + "'^^xsd:dateTime)\n");
						break;	
				} 
			}
			else
				query.append("optional{?Test SEC:lastModifiedAt ?lastModifiedAt}\n");	
		}
		else
			query.append("optional{?Test SEC:lastModifiedAt ?lastModifiedAt}\n");	
		if(useTestedAtButton.getSelection())
		{
			if(!testedAt.getText().equals(""))
			{
				query.append("?Test SEC:testedAt ?testedAt . ");
				switch (comboTestAt.getSelectionIndex()) {
					case 0: // >
						filter.append("FILTER(?testedAt > '" + testedAt.getText() + "'^^xsd:dateTime)\n");
						break;
					case 1: // >=
						filter.append("FILTER(?testedAt >= '" + testedAt.getText() + "'^^xsd:dateTime)\n");
						break;
					case 2: // <
						filter.append("FILTER(?testedAt < '" + testedAt.getText() + "'^^xsd:dateTime)\n");
						break;
					case 3: // <=
						filter.append("FILTER(?testedAt <= ('" + testedAt.getText() + "'^^xsd:dateTime)\n");
						break;
					case 4: // =
						filter.append("FILTER(?testedAt = '" + testedAt.getText() + "'^^xsd:dateTime)\n");
						break;
				} 
			}
			else
				query.append("optional{?Test SEC:testedAt ?testedAt}\n");	
		}
		else		
			query.append("optional{?Test SEC:testedAt ?testedAt}\n");		
		if(useImplementedByButton.getSelection())
		{
			if(!implementedBy.getText().equals(""))
			{
				query.append("?Test SEC:isTestOf <"+ implementedBy.getText()+"> . \n");
			}
		}
		else
			query.append("optional{?Test SEC:isTestOf ?isTestOf}\n");
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
		if(useRequirementButton.getSelection())
		{
			if(!requirement.getText().equals(""))
			{
				query.append("?Test SEC:isTestOf ?isTestOf . \n");
				query.append("<"+requirement.getText()+"> SEC:requirementEncodedBy ?isTestOf . \n");
				query.append("<"+requirement.getText()+"> rdfs:label ?Requirement . \n");
			}
		}
		else
		{
			query.append("optional{?Test SEC:isTestOf ?isTestOf . \n");
			query.append("?Requirement_URI SEC:requirementEncodedBy ?isTestOf . \n");
			query.append("?Requirement_URI SEC:hasDirectType 'Requirement' . \n");
			query.append("?Requirement_URI SEC:label ?Requirement . }\n");
		}
		query.append("optional{?Test SEC:seeAlso ?seeAlso}\n");
		query.append("optional{?Test SEC:hasTestResults ?testResult} \n");
		query.append("optional{?Test SEC:hasDescriptionInElement ?hasDescriptionInElement}\n");
		query.append(filter).append("}");
		System.out.println(query.toString());
		runQuery(query.toString());
		
	}
	
	private void runQuery(String query)
	{
		compositeResultsView.query(query);
	}
	
	//Khoi tao cho cac view
	
	private void initTableSearch()
	{
		String query = 
			ConsistentOntology.prefix+
			"SELECT * " +
			"WHERE" +
			"{" +
				"?Test SEC:hasDirectType ?Type . \n" +
				"optional{?Test SEC:hasDeveloper ?developer} \n"+
				"optional{?Test SEC:succeeded ?succeeded} \n" +
				"optional{?Test SEC:lastModifiedAt ?lastModifiedAt} \n"+
				"optional{?Test SEC:testedAt ?testedAt} \n"+
				"optional{?Test SEC:seeAlso ?seeAlso} \n"+
				"optional{?Test SEC:isTestOf ?isTestOf} \n"+ 
				"optional{?Test SEC:isImplementBy ?isImplementBy} \n"+
				"optional{?Test SEC:hasTestResults ?testResult} \n"+
				"optional{?Test SEC:hasDescriptionInDocument ?hasDescriptionInDocument} \n"+
				"optional{?Test SEC:hasDescriptionInElement ?hasDescriptionInElement} \n"+
				"FILTER((?Type = 'Test') || (?Type = 'UnitTest') || (?Type = 'IntergatedTest'))\n" +
			"}";	
		compositeResultsView_1.query(query);
		
		
	}

}
