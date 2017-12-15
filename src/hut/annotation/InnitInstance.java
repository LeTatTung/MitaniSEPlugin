package hut.annotation;

import java.util.ArrayList;
import java.util.List;


import ws.data.InstanceData;
import ws.data.PropertyMapData;


public class InnitInstance {
	InstanceData  packageField= new InstanceData();
	List<PropertyMapData> dataPropertyList = new ArrayList<PropertyMapData>();
	List<PropertyMapData> objectPropertyList   = new ArrayList<PropertyMapData>() ;
	
	
	public InstanceData getPackageField() {
		
		packageField.setDataPropertyList(dataPropertyList);
		packageField.setObjectPropertyList(objectPropertyList);		
		
		return packageField;
	}


	
	public InnitInstance(InstanceData packageField) {
		super();
		this.packageField = packageField;
	}



	public void addDataProperty(String propertyName, String propertyValue){
		PropertyMapData dataproperty = new PropertyMapData();
		dataproperty.setPropertyname(propertyName);
		dataproperty.setValue(propertyValue);
		dataPropertyList.add(dataproperty);
	}
	
	/**
	 * @param propertyName: ten cua property
	 * @param propertyValue: gia tri cua property
	 * @param type: ten cua class
	 */
	public void addObjectProperty(String propertyName, String propertyValue,String type){
		PropertyMapData dataproperty = new PropertyMapData();
		dataproperty.setTypeClass(type);
		dataproperty.setPropertyname(propertyName);
		dataproperty.setValue(propertyValue);
		objectPropertyList.add(dataproperty);
	}
	
}
