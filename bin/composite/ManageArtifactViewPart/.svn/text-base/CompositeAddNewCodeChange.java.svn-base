package composite.ManageArtifactViewPart;

import java.util.List;

import mintani.valueconst.ConsistentOntology;

import org.eclipse.swt.widgets.Composite;

import service.Service;

import ws.owl.PropertyData;

import hut.composite.annotationCreatorAndEditor.CompositeListPropertiesOfInstance;
import hut.model.annotationCreatorAndEditor.DataInstance;

public class CompositeAddNewCodeChange extends
		CompositeListPropertiesOfInstance {

	public CompositeAddNewCodeChange(Composite parent, int style) {
		super(parent, style);
		// TODO Auto-generated constructor stub
	}

	public int updateInterface(){

		DataInstance dataInstanceInput = (DataInstance)getInputData();
		this.setInstanceFullName(dataInstanceInput.getInstanceFullName());
		
		//-- If is existed Instance ----------------------------------------------------
//		if(!dataInstanceInput.isNewInstance()){
//			isNewInstance = false;
//			clearTable();
//			bindDataToTable();
//			if(!isFullURI){
//				textID.setText(this.instanceFullName.substring(this.instanceFullName.indexOf("#")+1));
//			}
//			else{
//				textID.setText(this.instanceFullName);
//			}
//			textID.setEditable(false);
//				
//		}
//		// if instance is new instance
//		else{
		
			String totalString = dataInstanceInput.getInstanceFullName();
			String instanceName = totalString.substring(0, totalString.lastIndexOf("::::"));
			String sourceCodeURI = totalString.substring(totalString.lastIndexOf("::::")+4);
		
			clearTable();
			this.setClassName(dataInstanceInput.getClassData().getClassName());
			this.setInstanceFullName(instanceName);
			
			if(!this.isFullURI()){
				this.getTextID().setText(this.getInstanceFullName().substring(this.getInstanceFullName().indexOf("#")+1));
			}
			else{
				this.getTextID().setText(this.getInstanceFullName());
			}
			this.getTextID().setEditable(true);
			
			//---  retrieve All Properties of this Class --
			List<PropertyData> listPropertyData = service.Service.webServiceDelegate.getAllClassProperties(null, dataInstanceInput.getClassData().getClassURI());
			for(PropertyData propertyData : listPropertyData)
			{
				String propertyName = propertyData.getPropertyName();
				System.out.println("propertyName: : : :"+ propertyName);
				java.util.List<String> value = new java.util.ArrayList<String>();
				
				//-- Neu la thuoc tinh CODE_CHANGE_BY thi them fullURI cua thanh phan code duoc chon vao
				if(propertyData.getPropertURI().equals(ConsistentOntology.CODE_CHANGED_OF)){
					value.add(sourceCodeURI);
				}
				
				//-- Neu la thuoc tinh IS_DISCARDED thi them gia tri "0" <=> false vao
				if(propertyData.getPropertURI().equals(ConsistentOntology.IS_DISCARDED)  ){
					value.add("false");
				}
				
				//-- Neu la thuoc tinh CODE_CHANGE_BY thi them uri cua developer
				if(propertyData.getPropertURI().equals(ConsistentOntology.CODE_CHANGED_BY)){
					value.add(Service.username);
				}
				
				addRow(propertyName, propertyData, value);
			}
//		}
		return 0;
	}
}
