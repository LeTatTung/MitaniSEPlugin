package mitaniseplugin.views;

import java.io.File;
import java.util.ArrayList;

import mintani.valueconst.ConsistentOntology;
import ontology.images.Images;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;

import service.Service;

import ws.owl.WebServiceDelegate;

import hut.composite.annotationManager.CompositeCodeParserView;
import hut.composite.document.CompositeAnnotationResult;
import hut.composite.document.CompositeDocComponent;
import hut.composite.document.CompositeDocComponentAll;
import hut.composite.document.CompositeDocOutline;
import hut.composite.document.ControllerAnnotator;
import hut.composite.document.JobParseDoc;
import hut.composite.popup.CompositePopupResult;

public class ViewDocumentAnotator extends ViewPart {

	public static final String ID = "mitaniseplugin.views.ddd"; //$NON-NLS-1$

	// filepath
	private String filepath;
	private Table table;
	private SashForm sashFormWrapAll;
	private SashForm sashForm_1;
	private TabItem classTabItem;
	private ToolItem opendocumentToolItem;
	private ToolBar toolBar;
	public CompositeDocOutline compositeDocOutline;
	public CompositeDocComponentAll compositeDocComponentAll;
	public CompositeAnnotationResult compositeAnnotationResult;
	public CompositePopupResult compositePopupResult;
	public CompositeDocComponent compositeDocComponent;
	private ControllerAnnotator controllerAnnotator;
	private JobParseDoc job;
	String documentId;
	public static WebServiceDelegate delegate;

	/**
	 * Create contents of the view part
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		FormToolkit toolkit = new FormToolkit(Display.getCurrent());

		final TabFolder tabFolder_1 = new TabFolder(parent, SWT.BOTTOM);
		toolkit.adapt(tabFolder_1, true, true);

		final TabItem sourceCodeAnnotatorTabItem = new TabItem(tabFolder_1,
				SWT.NONE);
		sourceCodeAnnotatorTabItem.setControl(new CompositeCodeParserView(
				tabFolder_1, SWT.NULL));
		sourceCodeAnnotatorTabItem.setText("Source Code Annotator");

		final TabItem documentAnnotatorTabItem = new TabItem(tabFolder_1,
				SWT.NONE);
		documentAnnotatorTabItem.setText("Document Annotator");

		Composite container = toolkit.createComposite(tabFolder_1, SWT.NONE);
		container.setLayout(new FillLayout());
		toolkit.paintBordersFor(container);
		documentAnnotatorTabItem.setControl(container);

		sashFormWrapAll = new SashForm(container, SWT.NONE);
		sashFormWrapAll.setOrientation(SWT.VERTICAL);

		final Composite composite = new Composite(sashFormWrapAll, SWT.NONE);
		composite.setLayout(new FillLayout());
		toolkit.adapt(composite);
		final SashForm sachFormWrapComponent = new SashForm(composite, SWT.NONE);
		sachFormWrapComponent.setBackground(Display.getCurrent()
				.getSystemColor(SWT.COLOR_WHITE));

		final Composite compositeStructor = new Composite(
				sachFormWrapComponent, SWT.NONE);
		compositeStructor.setLayout(new FillLayout());
		toolkit.adapt(compositeStructor);
		final SashForm sashForm_3 = new SashForm(compositeStructor, SWT.NONE);
		sashForm_3.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_WHITE));
		sashForm_3.setOrientation(SWT.VERTICAL);
		toolkit.adapt(sashForm_3, true, true);

		final Composite composite_1 = new Composite(sashForm_3, SWT.NONE);
		composite_1.setLayout(new FillLayout());
		toolkit.adapt(composite_1);

		toolBar = new ToolBar(composite_1, SWT.NONE);
		toolkit.adapt(toolBar, true, true);

		final ToolItem opendocumentToolItem = new ToolItem(toolBar, SWT.PUSH);
		opendocumentToolItem
				.setImage(Images.imageRegistry.get(Images.PDF_FILE));
		opendocumentToolItem.setToolTipText("Parse new document");
		opendocumentToolItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				openfile();
			}
		});

		final ToolItem refreshItemToolItem = new ToolItem(toolBar, SWT.PUSH);
		refreshItemToolItem.setToolTipText("refresh relational properties");
		refreshItemToolItem.setImage(Images.imageRegistry.get(Images.REFRESH));
		refreshItemToolItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				refreshUnderAnnotation();
			}
		});

		final ToolItem saveAntotationToolItem = new ToolItem(toolBar, SWT.PUSH);
		saveAntotationToolItem.setToolTipText("Send data to server");
		// Xu ly su kien click save button: chuyen DataIntermediate thanh
		// list<Instance> va gui len server
		saveAntotationToolItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				controllerAnnotator.sendDocumentDataToServer();
			}
		});
		saveAntotationToolItem.setImage(Images.imageRegistry
				.get(Images.SAVEANNOTATION));

		final ToolItem selectFromServerToolItem = new ToolItem(toolBar,
				SWT.PUSH);
		selectFromServerToolItem.setToolTipText("Open document from server");

		selectFromServerToolItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				ArrayList<String> inputData = new ArrayList<String>();
				inputData = (ArrayList<String>) Service.webServiceDelegate.listClassInstance(null, ConsistentOntology.DOC_TYPE_CLASS);
				ChooserSavedDocumentShell listDocumetShell = new ChooserSavedDocumentShell(
						Display.findDisplay(Thread.currentThread()),
						SWT.SHELL_TRIM, inputData);
				listDocumetShell.setController(controllerAnnotator);
				listDocumetShell.setVisible(true);
			}
		});
		selectFromServerToolItem.setImage(Images.imageRegistry
				.get(Images.MANAGE_DOCUMENT));
		final TabFolder tabFolder = new TabFolder(sashForm_3, SWT.NONE);
		toolkit.adapt(tabFolder, true, true);

		// Tab hien thi heading cua cay
		classTabItem = new TabItem(tabFolder, SWT.NONE);
		classTabItem.setText("Heading");
		compositeDocOutline = new CompositeDocOutline(tabFolder, SWT.NONE);
		toolkit.adapt(compositeDocOutline);
		classTabItem.setControl(compositeDocOutline);
		sashForm_3.setWeights(new int[] { 20, 198 });

		final Composite compositeExplorer = new Composite(
				sachFormWrapComponent, SWT.NONE);
		compositeExplorer.setLayout(new FillLayout());
		toolkit.adapt(compositeExplorer);
		compositeDocComponentAll = new CompositeDocComponentAll(
				compositeExplorer, SWT.NONE);
		toolkit.adapt(compositeDocComponentAll);
		sachFormWrapComponent.setWeights(new int[] { 132, 337 });

		// Phan view cua ket qua sau khi chon phan tich nhung phan nao cua
		// document
		// sashForm.setWeights(new int[] {1 });

		final Composite compositeExplorerResult = new Composite(
				sashFormWrapAll, SWT.NONE);
		compositeExplorerResult.setLayout(new FillLayout());
		toolkit.adapt(compositeExplorerResult);
		compositeAnnotationResult = new CompositeAnnotationResult(
				compositeExplorerResult, SWT.NONE, documentId);
		compositeAnnotationResult.setLayout(new FillLayout());
		toolkit.adapt(compositeAnnotationResult);
		sashFormWrapAll.setWeights(new int[] { 240, 103 });

		// Thiet lap quan he giua cac composite thong qua Controller
		setController();

		// Cho kieu cursor hand
		Cursor cursor = new Cursor(parent.getDisplay(), SWT.CURSOR_HAND);

		//
		createActions();
		initializeToolBar();
		initializeMenu();
	}

	public void refreshUnderAnnotation() {
		documentId = controllerAnnotator.getCurrentDocumentID();
		compositeAnnotationResult.getRelateToCode(documentId);
	}

	// Khoi tao controller cho tat ca cac Composite cha: Gom 4
	// composite:trai,giua, phai ,duoi
	private void setController() {
		// Khoi tao controller
		controllerAnnotator = new ControllerAnnotator();

		controllerAnnotator.setViewDocumentAnotator(this);
		// Lien ket controller voi cac composite
		controllerAnnotator.setJobparseDoc(job);
		controllerAnnotator
				.setCompositeDocComponentAll(compositeDocComponentAll);
		controllerAnnotator.setCompositeDocOutline(compositeDocOutline);
		controllerAnnotator
				.setCompositeAnnotationResult(compositeAnnotationResult);
		
		controllerAnnotator.setCompositePopupResult(compositePopupResult);
		// Lien ket composite voi cac Controller
		compositeDocComponentAll.setController(controllerAnnotator);
		compositeDocOutline.setController(controllerAnnotator);
		compositeAnnotationResult.setController(controllerAnnotator);
	}

	private void openfile() {
		FileDialog dialog = new FileDialog(toolBar.getShell(), SWT.NULL);
		String path = dialog.open();
		if (path != null) {
			File file = new File(path);
			if (file.isFile()) {
				System.out.println(file.toString());
				filepath = file.toString();

				// Add jobs
				try {
					job = new JobParseDoc("Parsing ", filepath);
					job.setController(controllerAnnotator);
					job.setViewDocumentAnotator(this);
					job.setUser(true);
					job.schedule();
				} catch (Exception e) {
				}
			} else
				System.out.println("No file choice");

		}
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

	public CompositeDocComponent getCompositeDocComponent() {
		return compositeDocComponent;
	}

	public void setCompositeDocComponent(CompositeDocComponent compositeDocComponent) {
		this.compositeDocComponent = compositeDocComponent;
	}

}
