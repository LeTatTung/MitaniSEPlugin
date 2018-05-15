package suggestTopic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ws.owl.ClassData;

public class CompareTopic {

	public ArrayList<String> initData() {
		ArrayList<String> arrayTopicData = new ArrayList<>();
		String topicURI = "http://hut.edu.vn/ontology/document#Topic";
		List<ClassData> listSubClassTopicParent = service.Service
				.webServiceDelegate.getSubClasses(null, topicURI, true);
		for (ClassData subclassTopic: listSubClassTopicParent) {
			arrayTopicData.add(subclassTopic.getClassName());
			List<ClassData> listSubClassTopic = service.Service
					.webServiceDelegate.getSubClasses(null, subclassTopic.getClassURI(), true);
			for (ClassData subClassData : listSubClassTopic) {
				arrayTopicData.add(subClassData.getClassName());
			}
		}
		Set<String> setString = new HashSet<>(arrayTopicData);
		arrayTopicData = new ArrayList<String>(setString);
		System.out.println("HAHAHAHAHAHAHA: " + arrayTopicData);
		return arrayTopicData;
	}
	
//	private boolean hasChildren(ClassData parent) {
//		List<ClassData> subClassList = service.Service.webServiceDelegate.getSubClasses(null, parent.getClassURI(), true);
//		for (ClassData classData : subClassList){
//			if (classData.getClassName().equals("Nothing")) return false;
//		}
//		return parent.isHasSubClass();
//	}
	
	private int getSimilarity(String compOne, String compTwo) {
		int length1 = compOne.length();
		int length2 = compTwo.length();
		int count  = 0;
		char[] comp1 = compOne.toLowerCase().toCharArray();
		char[] comp2 = compTwo.toLowerCase().toCharArray();
		
		for (int i = 0; i< length1; i++) {
			for (int j = 0; j< length2; j++) {
				if (comp1[i] == comp2[j]) {
					count += 1; 
					break;
				}
			}
		}
		return count;
	}
	// tim thang max trong mang so nguyen 
	private int findMax(ArrayList<Integer> numbers) {
		int max = 0;
		for (int i =0; i< numbers.size(); i++) {
			if (numbers.get(i) > max) {
				max = numbers.get(i);
			}
		}
		return max;
	}
	
	//nhan dau vao la 1 mang so nguyen, tra ve vi tri cua thang max
//	private int getIndexMax(ArrayList<Integer> numbers) {
//		int max = 0 ;
//		for(int i = 0; i < numbers.size(); i ++) {
//			if (numbers.get(i) > max) {
//				max = numbers.get(i);
//			}
//		}
//		
//		for(int index = 0; index < numbers.size(); index ++) {
//			if (numbers.get(index) == max) {
//				return index;
//			}
//		}
//		return -1;
//	}
	// nhan dau vao la gia tri max, tra ve cac vi tri trong mang co gia tri max
	private ArrayList<Integer> getArrayIndexMax(int max, ArrayList<Integer> numbers){
		ArrayList<Integer> arrayIndexMax = new ArrayList<>();
		for (int i =0; i< numbers.size(); i++) {
			if (numbers.get(i) == max) {
				arrayIndexMax.add(i);
			} 
		}
		return arrayIndexMax;
	}
	// nhan dau vao la 1 string, so sanh voi cac string trong 1 mang
	// tra ve thang trong mang giong nhat voi thang nhap vao
	
	public String getSimilarityString(String input, ArrayList<String> arrayConstantString) {
		String result = "";
		for (String constantString : arrayConstantString) {
			if (constantString.toLowerCase().contains(input.toLowerCase())) {
				result = constantString;
			}
		}
		return result;
	}
	
	// trong truong hop muon lay nhieu thang giong nhat thi phai tra ve 1 mang
	public ArrayList<String> getSimilarityListString(String input, ArrayList<String> arrayConstantString) {
		ArrayList<Integer> numbers = new ArrayList<>();
		ArrayList<String> result  = new ArrayList<>();
		for (int i = 0; i < arrayConstantString.size(); i++) {
			System.out.println("Similarity: " + getSimilarity(input, arrayConstantString.get(i)));
			 numbers.add((Integer)getSimilarity(input, arrayConstantString.get(i)));
		}
		ArrayList<Integer> arrayIndexMax = getArrayIndexMax(findMax(numbers), numbers);
		for (int i = 0; i< arrayIndexMax.size(); i++) {
			result.add(arrayConstantString.get(arrayIndexMax.get(i)));
		}
		return result;
	}
	// kiem tra xem xau dau vao co dung 1 phan hay ko
	public boolean hasContains(String input, ArrayList<String> arrayConstantString) {
		for (String constantString : arrayConstantString) {
			if (constantString.toLowerCase().contains(input.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	
}