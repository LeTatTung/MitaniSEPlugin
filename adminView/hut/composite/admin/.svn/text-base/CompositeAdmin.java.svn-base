package hut.composite.admin;

import ontology.images.Images;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
//import hut.composite.annotationEditor.CompositeInstance;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.widgets.FormToolkit;
import java.util.*;

import ws.owl.DataTypePropertyMapData;
import ws.owl.InstanceData;
import ws.owl.ObjectPropertyMapData;
import ws.owl.WebServiceDelegate;
import ws.owl.WebServiceService;
import hut.composite.admin.CompositeInstanceData;

public class CompositeAdmin extends Composite {

	private InstanceData chosenInstanceData;
	private CompositeInstanceData compositeInstanceData; 
	private WebServiceService webService = new WebServiceService();
	private WebServiceDelegate delegate = webService.getWebServicePort();
	private ToolItem addToolItem; 
	private ToolItem removeToolItem;
	private Label listOfInstancesLabel;
	private List list;
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());

	
	public CompositeAdmin(Composite parent, int style) {
		super(parent, style);
		setLayout(new FormLayout());
		toolkit.adapt(this);
		toolkit.paintBordersFor(this);

		listOfInstancesLabel = new Label(this, SWT.NONE);
		final FormData fd_listOfInstancesLabel = new FormData();
		fd_listOfInstancesLabel.top = new FormAttachment(0, 5);
		fd_listOfInstancesLabel.left = new FormAttachment(0, 5);
		listOfInstancesLabel.setLayoutData(fd_listOfInstancesLabel);
		toolkit.adapt(listOfInstancesLabel, true, true);
		listOfInstancesLabel.setText("List of instances have been sent :");

		list = new List(this, SWT.BORDER);
		final FormData fd_list = new FormData();
		fd_list.bottom = new FormAttachment(0, 40);
		fd_list.right = new FormAttachment(0, 285);
		fd_list.top = new FormAttachment(listOfInstancesLabel, 5, SWT.BOTTOM);
		fd_list.left = new FormAttachment(listOfInstancesLabel, 0, SWT.LEFT);
		list.setLayoutData(fd_list);
		toolkit.adapt(list, true, true);
		list.addListener(SWT.Selection, new Listener(){

			public void handleEvent(Event e) {
				int index = list.getSelectionIndex();
				String selectedInstanceData = list.getItem(index);
				if ((selectedInstanceData != null) && (selectedInstanceData != "")){
				
					InstanceData instanceData = delegate.getInstanceData(selectedInstanceData);
					compositeInstanceData.setInstanceData(instanceData);
					compositeInstanceData.updateTable();
					chosenInstanceData = instanceData;
				}
					
			} 
			
		});

		final Composite composite_1 = new Composite(this, SWT.NONE);
		final FormData fd_composite_1 = new FormData();
		fd_composite_1.top = new FormAttachment(0, 330);
		fd_composite_1.right = new FormAttachment(0, 495);
		fd_composite_1.left = new FormAttachment(0, 5);
		fd_composite_1.bottom = new FormAttachment(100, -5);
		composite_1.setLayoutData(fd_composite_1);
		composite_1.setLayout(new FormLayout());
		toolkit.adapt(composite_1);

		final ToolBar toolBar = new ToolBar(composite_1, SWT.NONE);
		final FormData fd_toolBar = new FormData();
		fd_toolBar.right = new FormAttachment(0, 120);
		fd_toolBar.top = new FormAttachment(0, 5);
		fd_toolBar.left = new FormAttachment(0, 5);
		toolBar.setLayoutData(fd_toolBar);
		toolkit.adapt(toolBar, true, true);

		addToolItem = new ToolItem(toolBar, SWT.PUSH);
		addToolItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				if (chosenInstanceData != null){
					if (MessageDialog.openConfirm(new Shell(), "Confirm", "Do you want to add this instance into the ontology?") == true){
						
						boolean success = delegate.createInstanceStandard(null, chosenInstanceData.getInstanceFullName(), chosenInstanceData.getClassName());
						
						for (DataTypePropertyMapData dataTypePropertyMapData : chosenInstanceData.getDataPropertyList()){
							delegate.addDatatypeProperty(null, dataTypePropertyMapData.getPropertyName(), dataTypePropertyMapData.getValue(), chosenInstanceData.getInstanceFullName());
						}
						
						for (ObjectPropertyMapData objectPropertyMapData : chosenInstanceData.getObjectPropertyList()){
							delegate.addObjectProperty(null, objectPropertyMapData.getPropertyName(), objectPropertyMapData.getIndividualValue(), chosenInstanceData.getInstanceFullName());
						}
						
						if (success) MessageDialog.openInformation(new Shell(), "Success!", "The instance has been added into the ontology!");
						
						
					}
				}
			}
		});
		addToolItem.setText("Add");
		addToolItem.setImage(Images.imageRegistry.get(Images.ADD));
		
		removeToolItem = new ToolItem(toolBar, SWT.PUSH);
		removeToolItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				
				if (chosenInstanceData != null){
					if (MessageDialog.openConfirm(new Shell(), "Confirm", "Do you really want to remove this instance data from list?")){
						delegate.removeInstanceDataFromList(chosenInstanceData);
						MessageDialog.openInformation(new Shell(), "Success!", "Instance data has been remove from list !");
					}
					
				}
				
			}
		});
		removeToolItem.setText("Remove");
		removeToolItem.setImage(Images.imageRegistry.get(Images.DELETE));
		
		
		refreshList();

		compositeInstanceData = new CompositeInstanceData(this, SWT.NONE);
		final FormData fd_compositeInstanceData = new FormData();
		fd_compositeInstanceData.bottom = new FormAttachment(composite_1, 0, SWT.TOP);
		fd_compositeInstanceData.right = new FormAttachment(composite_1, 0, SWT.RIGHT);
		fd_compositeInstanceData.top = new FormAttachment(list, 5, SWT.BOTTOM);
		fd_compositeInstanceData.left = new FormAttachment(list, 0, SWT.LEFT);
		compositeInstanceData.setLayoutData(fd_compositeInstanceData);
		compositeInstanceData.setLayout(new FormLayout());
		toolkit.adapt(compositeInstanceData);
		
	}


	private void refreshList() {
		
		list.removeAll();
		java.util.List<InstanceData> instanceList = delegate.getInstanceList();
		for (InstanceData instanceData : instanceList){
			String instanceFullName = instanceData.getInstanceFullName();
			list.add(instanceFullName);
		}
			
		
	}


	public InstanceData getChosenInstanceData() {
		return chosenInstanceData;
	}


	public void setChosenInstanceData(InstanceData chosenInstanceData) {
		this.chosenInstanceData = chosenInstanceData;
	}

}
