package reorder;

public class TopicContent {

	private String input;
	
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public TopicContent(String input) {
		this.input = input;
	}
	
	//ham tra ve content cua topic vi du output la GraphTopic
	public String getTopicContent(String input) {
		String content = "";
		int count = countChar(input, '@');
		if (input.contains("@topic")) {
			if (count == 1) {
				content = input.substring(5, input.length());
			}
			if (count > 1) {
				String [] contents = input.split("\\*");
				for (String s : contents) {
					if (s.contains("@topic")) {
						content = s;
					}
				}
			}
		}
		String s = content.trim();
		return s.substring(7, s.length()).trim();
	}
	
	// ham tra ve so lan xuat hien cua ki tu trong xau dau vao
	public int countChar(String input, char character) {
		int count = 0;
		for (char c : input.toCharArray()) {
			if (c == character) {
				count += 1;
			}
		}
		return count;
	}
}
