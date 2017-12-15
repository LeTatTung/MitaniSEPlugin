package hut.views.graph;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ToolTipManager;


@SuppressWarnings("serial")
public class ArrayListModel extends JList {

	SortedListModel model;

	public SortedListModel getModel() {
		return model;
	}

	public ArrayListModel() {
		model = new SortedListModel();
		setModel(model);
		ToolTipManager.sharedInstance().registerComponent(this);
	}

	public void add(Object obj) {
		model.add(obj);
	}

	public void remove(Object obj) {
		model.removeElement(obj);
	}
	public void removeAllElements() {
		if(model.getSize() == 0)
			return;
		for(int i = model.getSize() -1 ; i >=0 ;i--)
		{
			removeElement(i);
		}
	}
	public Object getElement(int index)
	{
		return model.getElementAt(index);
	}
	public void removeElement(int index) {
		model.removeElement(model.getElementAt(index));
	}

	public String getToolTipText(MouseEvent event) {
		Point p = event.getPoint();
		int location = locationToIndex(p);
		if(location >=0 && location < model.getSize())
		{
			ListNodeData data = ((ListNodeData) model.getElementAt(location));
			if(data != null)
				return data.getUri();
		}
		return "";
	}

	static ArrayListModel list;

	public static void main(String args[]) {
		JFrame frame = new JFrame("Custom Tip Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Properties props = System.getProperties();
		list = new ArrayListModel();

		list.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) 
			{
				if(e.getClickCount() == 2)
				{
				     int index = list.locationToIndex(e.getPoint());				    
				     Object item = list.getModel().getElementAt(index);;
				     list.ensureIndexIsVisible(index);
				     System.out.println("Double clicked on " + item);				     
				}				
			}
		});
		JScrollPane scrollPane = new JScrollPane(list);
//		list.getModel().add("dafd");
		list.getModel().add(new ListNodeData("test2","uri2"));
		list.getModel().add(new ListNodeData("test3","uri3"));
		frame.getContentPane().add(scrollPane);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
}
