package suggestTopic;

import java.util.ArrayList;
import java.util.Arrays;
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
		System.out.println("ARRAY LIST TOPIC: " + arrayTopicData);
		return arrayTopicData;
	}
	

	
	private int calculateLevenshteinDistance(String x, String y) {
	    int[][] dp = new int[x.length() + 1][y.length() + 1];
	 
	    for (int i = 0; i <= x.length(); i++) {
	        for (int j = 0; j <= y.length(); j++) {
	            if (i == 0) {
	                dp[i][j] = j;
	            }
	            else if (j == 0) {
	                dp[i][j] = i;
	            }
	            else {
	                dp[i][j] = min(dp[i - 1][j - 1] 
	                 + costOfSubstitution(x.charAt(i - 1), y.charAt(j - 1)), 
	                  dp[i - 1][j] + 1, 
	                  dp[i][j - 1] + 1);
	            }
	        }
	    }
	 
	    return dp[x.length()][y.length()];
	}
	
	private int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }
	
	private int min(int... numbers) {
        return Arrays.stream(numbers)
          .min().orElse(Integer.MAX_VALUE);
    }
	// tim thang min trong mang so nguyen 
	private int findMin(ArrayList<Integer> numbers) {
		int min = numbers.get(0);
		for (int i =0; i< numbers.size(); i++) {
			if (numbers.get(i) <= min) {
				min = numbers.get(i);
			}
		}
		return min;
	}
	// nhan dau vao la gia tri min, tra ve cac vi tri trong mang co gia tri min
	private ArrayList<Integer> getArrayIndexMin(int min, ArrayList<Integer> numbers){
		ArrayList<Integer> arrayIndexMin = new ArrayList<>();
		for (int i =0; i< numbers.size(); i++) {
			if (numbers.get(i) == min) {
				arrayIndexMin.add(i);
			} 
		}
		return arrayIndexMin;
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
			System.out.println("Dissimilarity: " + calculateLevenshteinDistance(input, arrayConstantString.get(i))
			+ " Topic: [" + arrayConstantString.get(i)+ "]");
			 numbers.add((Integer)calculateLevenshteinDistance(input, arrayConstantString.get(i)));
		}
		ArrayList<Integer> arrayIndexMin = getArrayIndexMin(findMin(numbers), numbers);
		for (int i = 0; i< arrayIndexMin.size(); i++) {
			result.add(arrayConstantString.get(arrayIndexMin.get(i)));
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