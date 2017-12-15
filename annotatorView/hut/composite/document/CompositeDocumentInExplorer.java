package hut.composite.document;

import java.util.List;

import ontology.images.Images;

import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.jpedal.PdfDecoder;
import org.jpedal.grouping.PdfGroupingAlgorithms;
import org.jpedal.objects.PdfImageData;

public class CompositeDocumentInExplorer extends CompositeAnnotatorSuper {

	private Table table;
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private CheckboxTableViewer tableViewer;
	int startX = 0;
	int startY = 0;
	int x1;
	int y1;
	int x2;
	int y2;

	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public CompositeDocumentInExplorer(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout());
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);

		final SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);

		final Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FillLayout());
		toolkit.adapt(composite);
		table = new Table(composite, SWT.MULTI | SWT.BORDER | SWT.CHECK);
		table.setHeaderVisible(true);
		table.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (event.detail == SWT.CHECK) {
					//text.setText("You checked " + event.item);
					System.out.println("dddd");
				} else {
					//text.setText("You selected " + event.item);
					System.out.println("aaaa");
				}
			}
		});
		table.addListener(SWT.MouseEnter, new Listener() {
			public void handleEvent(Event event) {
				Point pt = new Point(event.x, event.y);
				TableItem tableItem = table.getItem(pt);
				System.out.println(tableItem.getText());

			}
		});

		table.addMouseListener(new MouseAdapter() {
			public void mouseDoubleClick(MouseEvent event) {
				Point pt = new Point(event.x, event.y);
				TableItem item = table.getItem(pt);
				String col0text = item.getText(0);
				String col1text = item.getText(1);
				System.out.println("Clicked");
				System.out.println(col0text);
			}
		});

		final TableColumn newColumnTableColumn = new TableColumn(table,
				SWT.NONE);
		newColumnTableColumn.setWidth(70);
		newColumnTableColumn.setText("Choice");

		final TableColumn newColumnTableColumn_1 = new TableColumn(table,
				SWT.NONE);
		newColumnTableColumn_1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {

			}
		});
		newColumnTableColumn_1.setWidth(350);
		newColumnTableColumn_1.setText("Content");

		final TableColumn newColumnTableColumn_2 = new TableColumn(table,
				SWT.NONE);
		newColumnTableColumn_2.setWidth(70);
		newColumnTableColumn_2.setText("Label");

		final TableColumn newColumnTableColumn_3 = new TableColumn(table,
				SWT.SELECTED);
		newColumnTableColumn_3.setWidth(70);
		newColumnTableColumn_3.setText("ID");

		final TableColumn newColumnTableColumn_4 = new TableColumn(table,
				SWT.NONE);
		newColumnTableColumn_4.setWidth(70);
		newColumnTableColumn_4.setText("Page");
		
		toolkit.adapt(sashForm, true, true);
		final Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout());
		toolkit.adapt(composite_1);

		final ToolBar toolBar = new ToolBar(composite_1, SWT.NONE);
		toolkit.adapt(toolBar, true, true);

		final ToolItem parseToolItem = new ToolItem(toolBar, SWT.PUSH);
		parseToolItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
			}
		});
		parseToolItem.setText("<Parse>");
		//parseToolItem.setImage(Images.imageRegistry.get(Images.DESCRIBE));

		final ToolItem analyzeToolItem = new ToolItem(toolBar, SWT.PUSH);
		analyzeToolItem.setText("<Analyze Sentence>");

		final ToolBar toolBar_1 = new ToolBar(composite_1, SWT.NONE);
		toolkit.adapt(toolBar_1, true, true);

		final ToolBar toolBar_2 = new ToolBar(composite_1, SWT.NONE);
		toolkit.adapt(toolBar_2, true, true);

		final ToolItem viewdocumentToolItem = new ToolItem(toolBar_2, SWT.PUSH);
		viewdocumentToolItem.setText("<View Document>");
		sashForm.setWeights(new int[] { 333, 39 });
		//
	}

	 
	/**
	 * @param filename
	 * Do du lieu cho phan list cua image
	 */
	public void binDataToTable(String filename) {
		System.out.println("Phan tich anh cua document");
		String textContent = "";
		int loopIndex = 0;
		table.removeAll();
		PdfDecoder decodePdf = new PdfDecoder(false);
		decodePdf.init(true);
		try {
			decodePdf.openPdfFile(filename);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		int start = 1, end = decodePdf.getPageCount();
		for (int page = start; page < end + 1; page++) {
			try {
				//page=8;
				System.out.println("Dang o page: " + page);
				decodePdf.decodePage(page);
				PdfImageData infoImage = decodePdf.getPdfImageData();

				System.out
						.println("So luong anh: " + infoImage.getImageCount());

				//Content of image
				for (int i = 0; i < infoImage.getImageCount(); i++) {

					x1 = (int) infoImage.getImageXCoord(i);
					y2 = (int) infoImage.getImageYCoord(i);
					x2 = x1 + (int) infoImage.getImageWidth(i);
					y1 = y2 + (int) infoImage.getImageHeight(i);
					x2 = x2 + 300;
					y2 = y2 - 40;
					System.out.println(x1 + "," + y1 + "," + x2 + "," + y2);
					/*decodePdf.setExtractionMode(PdfDecoder.TEXT); 
					 *//** create a grouping object to apply grouping to data*/
					/*
									    PdfGroupingAlgorithms currentGrouping =decodePdf.getGroupingObject();
					 *//**The call to extract the text*/
					/*
									    textContent =currentGrouping.extractTextInRectangle(x1,y1,x2,y2,page,false,true);*/
					// System.out.println("Noi dung anh"+textContent);
				}
				if (infoImage.getImageCount() > 0) {
					//decodePdf.setExtractionMode(PdfDecoder.TEXT);
					PdfDecoder.useTextExtraction();
					PdfGroupingAlgorithms currentGrouping = decodePdf
							.getGroupingObject();
					//**The call to extract the text*//*
					textContent = currentGrouping.extractTextInRectangle(x1,
							y1, x2, y2, page, false, true);
					System.out.println("Noi dung anh" + textContent);
					//decodePdf.setExtractionMode(PdfDecoder.XFORMMETADATA);

					TableItem item = new TableItem(table, SWT.NULL);
					item.setText("Item " + loopIndex);
					item.setText(0, "Item " + loopIndex);
					item.setText(1, textContent);
					item.setText(2, "image" + loopIndex);
					item.setText(3, filename + "image" + loopIndex);
					item.setText(4, Integer.toString(page));
					loopIndex++;
				}
				decodePdf.clearScreen();

				//System.out.println("Ket thuc o page"+ page);
			} catch (Exception ex) {
				System.out.println(ex.getMessage());

			}
		}
	}

	public void bindDataListTable(List<List> listOfLists) {
		table.removeAll();
		List listItems;
		for (int loopIndex = 1; loopIndex < listOfLists.size(); loopIndex++) {
			listItems = listOfLists.get(loopIndex);
			TableItem item = new TableItem(table, SWT.NULL);
			item.setText("Item " + loopIndex);
			item.setText(0, "Item " + loopIndex);
			item.setText(1, listItems.get(1).toString());
			item.setText(2, listItems.get(4).toString());
			item.setText(3, listItems.get(3).toString());
			item.setText(4, listItems.get(4).toString());
		}

	}

	public void readDocument() {

	}

}
