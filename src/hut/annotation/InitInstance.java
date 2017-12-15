package hut.annotation;

import java.util.ArrayList;
import java.util.List;


import ws.owl.InstanceData;
import ws.owl.PropertyMapData;


public class InitInstance {
	InstanceData  instanceData= new InstanceData();
	List<PropertyMapData> dataPropertyList = new ArrayList<PropertyMapData>();
	List<PropertyMapData> objectPropertyList   = new ArrayList<PropertyMapData>() ;
	
	
	public InstanceData getPackageField() {
		
		instanceData.setDataPropertyList(dataPropertyList);
		instanceData.setObjectPropertyList(objectPropertyList);		
		
		return instanceData;
	}


	
	public InitInstance(InstanceData packageField) {
		super();
		this.instanceData = packageField;
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
