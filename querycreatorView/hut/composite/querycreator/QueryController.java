package hut.composite.querycreator;

import hut.data.queryinterchangedata.RestrictionOuputData;

import java.util.ArrayList;
import java.util.Map;

public class QueryController {
	private CompositeVariable compositeVariable;
	private CompositeRestriction compositeRestriction;
	private CompositeResult compositeResult;
	private CompositePopUp compositePopUp;
	
	public void addClassFromPopUp(){
		this.compositeVariable.setInputData(this.compositePopUp.getOutputData());
		Map<String,ArrayList<String>> tmpMap = (Map<String,ArrayList<String>>)this.compositeVariable.getInputData();
		for (String s : tmpMap.keySet())
		{
			this.compositeVariable.addVariableRow(s, "Class"); 
			ArrayList<String> tmpList = tmpMap.get(s);
			for (String method : tmpList)
			{
				this.compositeVariable.addVariableRow(method, "Method");
			}
			this.compositeVariable.updataVariableListForVariableTab();
		}
	}
	
	public void addRestrictionFromPopUp() {
		this.compositeRestriction.setInputData(this.compositePopUp.getOutputData());
		Map<String,ArrayList<String>> tmpMap = (Map<String,ArrayList<String>>)this.compositeRestriction.getInputData();
		for (String s : tmpMap.keySet())
		{
			this.compositeRestriction.addRestrictionRow(s+"(Class)", "hasName", "Equals",s);
			for (String method : tmpMap.get(s))
			{
			
				this.compositeRestriction.addRestrictionRow(s+"(Class)", "hasMethod", method, "");
			this.compositeRestriction.addRestrictionRow(method+"(Method)", "hasName", "Equals",method);
			
			}
		}
		this.compositeRestriction.updateRowData();
	}
	
	public void DeleteVariable(){
		
	}
	
	public void updateRowData(){
		this.compositeRestriction.addNewVariable();
		this.compositeResult.setInputData((RestrictionOuputData)this.compositeRestriction.getOutputData());
		this.compositeResult.updateChosenVariableList();
	}
	public void AddVariable(){
		this.getCompositeVariable().setInputData((RestrictionOuputData)this.getCompositeRestriction().getOutputData());
		Map<String, String> newVariableMap = ((RestrictionOuputData)this.getCompositeVariable().getInputData()).getVariableMap();
		this.getCompositeVariable().updataVariableListForVariableTab();
		
		this.getCompositeVariable().updateVariableListForRestrictionTab();
	}
	
	public void variableCompositeVariableChange(){
		this.getCompositeResult().setInputData(this.getCompositeVariable().getOutputData());
		this.getCompositeRestriction().setInputData(this.getCompositeVariable().getOutputData());
		this.getCompositeRestriction().updateComboboxSubjectData();
		this.getCompositeRestriction().updateInterface();
		
	}
	
	public CompositeVariable getCompositeVariable() {
		return compositeVariable;
	}
	public void setCompositeVariable(CompositeVariable compositeVariable) {
		this.compositeVariable = compositeVariable;
	}
	public CompositeRestriction getCompositeRestriction() {
		return compositeRestriction;
	}
	public void setCompositeRestriction(CompositeRestriction compositeRestriction) {
		this.compositeRestriction = compositeRestriction;
	}
	public CompositeResult getCompositeResult() {
		return compositeResult;
	}
	public void setCompositeResult(CompositeResult compositeResult) {
		this.compositeResult = compositeResult;
	}

	public CompositePopUp getCompositePopUp() {
		return compositePopUp;
	}

	public void setCompositePopUp(CompositePopUp compositePopUp) {
		this.compositePopUp = compositePopUp;
	}

	
}
