package hut.doc;

import java.util.ArrayList;
import java.util.List;

import tool.parse.doc.ToolParseDoc;

public class Filter {
	public Filter() {
		// TODO Auto-generated constructor stub
	}
	
	// Loc paragraph co noi dung nho
	public List<List> removeShortParagraph(List<List> inputArray){
		List<List> outPutArray = new ArrayList<List>();
 		ToolParseDoc xmlTool = new ToolParseDoc();
			for (int i=0; i<inputArray.size(); i++){
				ArrayList<String> item = (ArrayList<String>) inputArray.get(i);
				
				// Neu noi dung paragraph nho hon 100 ki tu thi bo ra khoi list
				String plainText = xmlTool.convertPlainText(item.get(1));
				if (plainText.length()>100){
					outPutArray.add(inputArray.get(i));
				}
			}
		return outPutArray;
	}
}
