package mitaniseplugin.views;

import hut.composite.document.ControllerAnnotator;
import hut.doc.DataIntermediateDoc;

import java.util.ArrayList;

import ontology.images.Images;

import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import service.Service;
import swing2swt.layout.FlowLayout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolItem;

import com.swtdesigner.SWTResourceManager;
import swing2swt.layout.BorderLayout;
import ws.owl.DocumentSaveData;
import ws.owl.InstanceData;
import ws.owl.WebServiceDelegate;
import ws.owl.WebServiceService;

public class ChooserSavedDocumentShell extends Shell {
	private ControllerAnnotator controller;
	private List list;
	ListViewer listViewer = null;
	private static ArrayList<String> inputData = new ArrayList<String>();
	
	/**
	 * Launch the application
	 * @param args
	 */
/*	public static void main(String args[]) {
		
		try {
			Display display = Display.getDefault();
			ChooserSavedDocumentShell shell = new ChooserSavedDocumentShell(
					display, SWT.SHELL_TRIM);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch())
					display.sleep();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	public void setInputData(ArrayList<String> inputData) {
		this.inputData = inputData;
	}

	/**
	 * Create the shell
	 * @param display
	 * @param style
	 */
	public ChooserSavedDocumentShell(Display display, int style, ArrayList<String> inputData) {
		super(display, style);
		setInputData(inputData);
		createContents();
		setLayout(new FillLayout());
	}

	/**
	 * Create contents of the window
	 */
	protected void createContents() {
		/*WebServiceService service = new WebServiceService();
		delegate = service.getWebServicePort();
		inputData = (ArrayList<String>) delegate.listClassInstance(null, "Library");
		*/
		/*inputData.add("CauTrucDuLieuVaGiaiThuat");
		inputData.add("ToanRoiRac");
		inputData.add("QuanLyDuAnCNPPT");
		inputData.add("ChienTranhVaHoaBinh");*/
		
		setText("List of documents");
		setSize(250, 375);

		final Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new BorderLayout(0, 0));

		final CLabel selectADocumentLabel = new CLabel(composite, SWT.SHADOW_OUT);
		//selectADocumentLabel.setImage(Images.imageRegistry.get(Images.BOOKMARK));
		selectADocumentLabel.setBackground(SWTResourceManager.getColor(192, 192, 192));
		selectADocumentLabel.setLayoutData(BorderLayout.NORTH);
		selectADocumentLabel.setText("Select a document");
		
		listViewer = new ListViewer(composite, SWT.BORDER);
		listViewer.setContentProvider(new listContentProvider());
		listViewer.setLabelProvider(new listLabelProvider());
		listViewer.setInput(inputData);
		list = listViewer.getList();
		list.setLayoutData(BorderLayout.CENTER);

		final Composite composite_1 = new Composite(composite, SWT.NONE);
		composite_1.setLayout(new FillLayout());
		composite_1.setLayoutData(BorderLayout.SOUTH);

		final Button loadDataFromButton = new Button(composite_1, SWT.NONE);
		loadDataFromButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				
				// Lay ve Id instance cua document duoc chon
				int selectionIndex = list.getSelectionIndex();
				String idSelection = (String)listViewer.getElementAt(selectionIndex);
				System.out.println(idSelection);
				
				DocumentSaveData saveData = new DocumentSaveData();
				saveData = Service.webServiceDelegate.getDocumentSaveData(null, idSelection);
				
				DataIntermediateDoc dataIntermediateDoc = new DataIntermediateDoc(saveData);
				//dataIntermediateDoc.getHeading().showContent();
				controller.setCurrentDocumentID(idSelection);
				controller.refreshRelationProperties();
				controller.changeDocument(dataIntermediateDoc, dataIntermediateDoc.getNameFile());
				// Dong shell chon document lai
				loadDataFromButton.getShell().close();
				try {
					controller.getCompositeDocComponentImage().getOpenSourceToolItem().setEnabled(true);
					controller.getCompositeDocComponentParagraph().getOpenSourceToolItem().setEnabled(true);
					controller.getCompositeDocComponentSection().getOpenSourceToolItem().setEnabled(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
			}
		});
		loadDataFromButton.setText("Load data from server");
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public ControllerAnnotator getController() {
		return controller;
	}

	public void setController(ControllerAnnotator controller) {
		this.controller = controller;
	}

}
