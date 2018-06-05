package hut.composite.querycreator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import service.Service;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.variableMappingType;

import hut.data.queryinterchangedata.*;
import ws.owl.*;

public class RowController {

	private ComboboxObjectData comboboxObject;
	private ComboboxPredicateData comboboxPredicate;
	private ComboboxSubjectData comboboxSubject;

	public void predicateChange(){

		this.getComboboxObject().setDataInput((ObjectComboInputData)this.getComboboxPredicate().getDataOutput());
		String propertyName = ((ObjectComboInputData)this.getComboboxObject().getDataInput()).getPropertyName();
		PropertyData property = Service.webServiceDelegate.getPropertyByShortName(null, propertyName);
		if (property.isDatatypeProperty())
		{
			String type = Service.webServiceDelegate.getPropertySpecificDataType(null, property.getPropertURI());
			if (type.equals("string"))
			{
			ArrayList<String> tmpList = new ArrayList<String>();
			tmpList.add("Equals");
			tmpList.add("Contains");
			tmpList.add("Begins with");
			tmpList.add("Ends with");
			this.getComboboxObject().setListItem(tmpList);
			}

			if (type.equals("date"))
			{
			ArrayList<String> tmpList = new ArrayList<String>();
			tmpList.add("Date >");
			tmpList.add("Date <");
			tmpList.add("Date =");
			this.getComboboxObject().setListItem(tmpList);
			}

			if (type.equals("dateTime"))
			{
			ArrayList<String> tmpList = new ArrayList<String>();
			tmpList.add("Date Time >");
			tmpList.add("Date Time <");
			tmpList.add("Date Time =");
			this.getComboboxObject().setListItem(tmpList);
			}

			if (type.equals("nonNegativeInteger"))
			{
			ArrayList<String> tmpList = new ArrayList<String>();
			tmpList.add(">");
			tmpList.add("<");
			tmpList.add("=");
			this.getComboboxObject().setListItem(tmpList);
			}

			if (type.equals("boolean"))
			{
			ArrayList<String> tmpList = new ArrayList<String>();
			tmpList.add("true");
			tmpList.add("false");
			this.getComboboxObject().setListItem(tmpList);
			}
		}

		if (property.isObjectProperty())
		{
			this.getComboboxObject().setIsNewVariable(true);

			List<String> rangeList = new ArrayList<String>();
			List<String> classList = Service.webServiceDelegate.getObjectPropertyRanges(null, property.getPropertURI());
			for (String strClass:classList)
			{
				for (ClassData cd:Service.webServiceDelegate.getSubClasses(null, strClass, false)) {
					rangeList.add(cd.getClassURI());
				}
			}
			rangeList.addAll(classList);

			// Create a HashSet which allows no duplicates
			HashSet hashSet = new HashSet(rangeList);
			// Assign the HashSet to a new ArrayList
			rangeList = new ArrayList(hashSet);
			// Ensure correct order, since HashSet doesn't
			Collections.sort(rangeList);
			System.out.println("lay ra duoc range cua property trong predicate");

			if (!((ObjectComboInputData)this.getComboboxObject().getDataInput()).isNeedNewVariable())
			{
				this.getComboboxObject().setIsNewVariable(false);

				for (String tmp : rangeList) System.out.println(tmp);
				Map<String, String> variableMap = ((ObjectComboInputData)this.getComboboxObject().getDataInput()).getVariableMap();
				ArrayList<String> tmpList = new ArrayList<String>();
				for (int i = 0; i < rangeList.size(); i++)
				{
					for (String key : variableMap.keySet())
					{
						if (variableMap.get(key).equals(rangeList.get(i).substring(rangeList.get(i).lastIndexOf("#")+1)))
						{
							tmpList.add(key + "(" + variableMap.get(key) + ")");
						}
					}
				}

				this.comboboxObject.setListItem(tmpList);

			}
			else
			{
				this.getComboboxObject().setIsNewVariable(true);
				System.out.println("can co object moi");
				Map<String, String> variableMap = ((ObjectComboInputData)this.getComboboxObject().getDataInput()).getVariableMap();

				ArrayList<String> tmpList = new ArrayList<String>();
				List<String> tmpRangeList = new ArrayList<String>();
				for (String tmp : rangeList){
					tmpRangeList.add(tmp.substring(tmp.lastIndexOf("#")+1));
				}
				for (String key : variableMap.keySet()) System.out.println(variableMap.get(key));
				for (int i = 0; i < tmpRangeList.size(); i++)
				{
					tmpList.add(new NamingNewVariable(variableMap).generateNewName(tmpRangeList.get(i)) + "(" + tmpRangeList.get(i) + ")");
				}
				this.getComboboxObject().setListItem(tmpList);
			}
		}

	}

	public void subjectChange(){
		this.getComboboxPredicate().setDataInput(this.getComboboxSubject().getDataOutput());
		String className = (String)this.getComboboxPredicate().getDataInput();

		String classFullName = Service.webServiceDelegate.getClassByShortName(null, className).getClassURI();

		List<PropertyData> propertyList = Service.webServiceDelegate.getAllClassProperties(null, classFullName);
		ArrayList<String> tmpList = new ArrayList<String>();
		for (int i = 0; i < propertyList.size(); i++)
		{
			tmpList.add(propertyList.get(i).getPropertyName());
		}
		this.getComboboxPredicate().setListItem(tmpList);
	}

	public void objectChange(){

	}

	public ComboboxObjectData getComboboxObject() {
		return comboboxObject;
	}

	public void setComboboxObject(ComboboxObjectData comboboxObject) {
		this.comboboxObject = comboboxObject;
	}

	public ComboboxPredicateData getComboboxPredicate() {
		return comboboxPredicate;
	}

	public void setComboboxPredicate(ComboboxPredicateData comboboxPredicate) {
		this.comboboxPredicate = comboboxPredicate;
	}

	public ComboboxSubjectData getComboboxSubject() {
		return comboboxSubject;
	}

	public void setComboboxSubject(ComboboxSubjectData comboboxSubject) {
		this.comboboxSubject = comboboxSubject;
	}


}
