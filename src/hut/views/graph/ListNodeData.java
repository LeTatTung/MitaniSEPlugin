package hut.views.graph;

public class ListNodeData implements Comparable{
	private String text = "";
	private String uri = "";
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	public ListNodeData(String text, String uri)
	{
		this.text = text;
		this.uri = uri;
	}
	public String toString()
	{
		return this.text;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	/**
	 * xap xep theo thu tu tang dan cua text
	 * */
	public int compareTo(Object obj) {
		ListNodeData value = (ListNodeData)obj;
		return (-1)*value.getText().compareTo(this.text);		
	}
}
