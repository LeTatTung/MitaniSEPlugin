package hut.composite.search.tableWrap;



import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectionListener extends MouseAdapter {
	TableTextWrap table;
	public SelectionListener(TableTextWrap table) {
		this.table = table;
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			int index = table.rowAtPoint(e.getPoint());
			if(index < 0)
				return;
			String[] row = table.getRow(index);
			
			String documentURI = row[table.headers.indexOf("content")];
			int page = Integer.parseInt(row[table.headers.indexOf("page")]);
			System.out.println(documentURI + " " + page);
			//PDFViewer view = 
				//new PDFViewer(documentURI, page);			
		}
	}
}