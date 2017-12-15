package hut.composite.querycreator;

import java.util.Map;

public class NamingNewVariable {

	private Map<String, String> variableMap;

	public Map<String, String> getVariableMap() {
		return variableMap;
	}

	public void setVariableMap(Map<String, String> variableMap) {
		this.variableMap = variableMap;
	}

	public NamingNewVariable(Map<String, String> variableMap) {
		super();
		this.variableMap = variableMap;
	}
	
	public String generateNewName(String type){
		/*if (!this.getVariableMap().containsValue(type))
		{
			return null;
		}
		else*/
		{
			int count = 1;
			String newName = type.concat(String.valueOf(count));
			while (this.getVariableMap().containsKey(newName))
			{
				count++;
				newName = type.concat(String.valueOf(count));
			}
			return newName;
		}
	}
}
