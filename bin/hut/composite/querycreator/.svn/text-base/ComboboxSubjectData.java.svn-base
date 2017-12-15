package hut.composite.querycreator;

import java.util.ArrayList;
import java.util.Map;

public class ComboboxSubjectData extends ComboboxDataSuper {

	@Override
	public void updateInterface() {
		Map<String, String> subjectMap = (Map<String, String>) this.getDataInput();
		ArrayList<String> tmpList = new ArrayList<String>();
		for (String key : subjectMap.keySet()) {
			String tmpItem = key + subjectMap.get(key);
			tmpList.add(tmpItem);
		}
		this.setListItem(tmpList);
	}
}
