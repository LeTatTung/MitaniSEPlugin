package hut.composite.document;

import hut.doc.DataIntermediateDoc;

import java.awt.Frame;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import mintani.valueconst.ConstValue;

import org.eclipse.core.internal.jobs.Worker;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.forms.widgets.FormToolkit;
import hut.composite.document.CompositeDocComponent;

public class CompositeDocComponentAll extends CompositeAnnotatorSuper {

	private Table table;
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private CompositeDocComponent compositeDocComponentImage;
	private CompositeDocComponent compositeDocComponentParagraph;
	private CompositeDocComponent compositeDocComponentSection;
	private ControllerAnnotator controllerAnnotator;
	private CLabel actionLabel;
	private List<List> data;
	
	public void setData(List<List> data) {
		this.data = data;
	}

	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public CompositeDocComponentAll(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout());
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);

		final SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);

		final Composite compositeUp = new Composite(sashForm, SWT.NONE);
		toolkit.adapt(compositeUp);

		actionLabel = new CLabel(compositeUp, SWT.NONE);
		toolkit.adapt(actionLabel, true, true);
		actionLabel.setBounds(0, 0, 419, 19);
		toolkit.adapt(sashForm, true, true);

		final Composite compositeDown = new Composite(sashForm, SWT.NONE);
		compositeDown.setLayout(new FillLayout());
		toolkit.adapt(compositeDown);

		final TabFolder tabFolder = new TabFolder(compositeDown, SWT.NONE);
		toolkit.adapt(tabFolder, true, true);
		
		
		final TabItem tabItemImage = new TabItem(tabFolder, SWT.NONE);
		tabItemImage.setText("Image");
		final Composite compositeImage = new Composite(tabFolder, SWT.NONE);
		compositeImage.setLayout(new FillLayout());
		toolkit.adapt(compositeImage);
		tabItemImage.setControl(compositeImage);
		compositeDocComponentImage = new CompositeDocComponent(compositeImage, SWT.NONE,ConstValue.IMAGE);
		toolkit.adapt(compositeDocComponentImage);
		
		final TabItem tabItemParagraph = new TabItem(tabFolder, SWT.NONE);
		tabItemParagraph.setText("Paragraph");
		final Composite compositeParagraph = new Composite(tabFolder, SWT.NONE);
		compositeParagraph.setLayout(new FillLayout());		
		toolkit.adapt(compositeParagraph);
		tabItemParagraph.setControl(compositeParagraph);
		compositeDocComponentParagraph = new CompositeDocComponent(compositeParagraph, SWT.NONE,ConstValue.PARAGRAPH);
		toolkit.adapt(compositeDocComponentParagraph);	
		
  
		final TabItem tabItemSection = new TabItem(tabFolder, SWT.NONE);
		tabItemSection.setText("Section");
		final Composite compositeSection = new Composite(tabFolder, SWT.NONE);
		compositeSection.setLayout(new FillLayout());
		toolkit.adapt(compositeSection);
		tabItemSection.setControl(compositeSection);
		compositeDocComponentSection = new CompositeDocComponent(compositeSection, SWT.NONE,ConstValue.SECTION);
		toolkit.adapt(compositeDocComponentSection);
		sashForm.setWeights(new int[] {36, 336 });
		
	}
		
	
	
	@Override
	public void setController(ControllerAnnotator controller) {
		// TODO Auto-generated method stub
		super.setController(controller);
		compositeDocComponentImage.setController(controller);
		compositeDocComponentParagraph.setController(controller);
		compositeDocComponentSection.setController(controller);
		
		
		
		//add composite cho controller
		controller.setCompositeDocComponentImage(compositeDocComponentImage);
		controller.setCompositeDocComponentParagraph(compositeDocComponentParagraph);
		controller.setCompositeDocComponentSection(compositeDocComponentSection);

	}

	@Override
	public void updateInterface(){
		String currentPath =this.getCurrentPathFile();
		try {
		compositeDocComponentParagraph.bindDataListTable(((DataIntermediateDoc)this.getInputData()).getParagraphData(),currentPath);
		compositeDocComponentImage.bindDataListTable(((DataIntermediateDoc)this.getInputData()).getImageData(),currentPath);
		compositeDocComponentImage.creatAction();
		compositeDocComponentSection.bindDataListTable(((DataIntermediateDoc)this.getInputData()).getSectionData(),currentPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateAction(String action){
		actionLabel.setText(action);
	}
	

}
