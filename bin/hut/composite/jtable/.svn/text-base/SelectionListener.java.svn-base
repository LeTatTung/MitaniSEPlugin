package hut.composite.jtable;


import hut.composite.document.CompositeAnnotationResult;
import hut.composite.document.PDFViewer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import mintani.valueconst.ConstValue;

/**
 * @author KienCuong
 * Class bat su kien kich dup vao 1 phan tu trong bang jtable
 */
public class SelectionListener extends MouseAdapter {
	private JTable table;
	private int typeTable;
	private String filePath;
	private CompositeAnnotationResult compositeAnnotationResult;
	
	public SelectionListener() {
		
	}
	public SelectionListener(int type,JTable table,CompositeAnnotationResult compositeAnnotationResult) {
		this.typeTable = type;//Nhan dang cua table
		this.table = table;
		this.compositeAnnotationResult = compositeAnnotationResult;
	}
	public SelectionListener(JTable table) {
		this.table = table;
	}
	public SelectionListener(JTable table,String filePath) {
		this.table = table;
		this.filePath = filePath;
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			//Neu su kien click cua bang o thanh phan composite duoi cung
			if(this.typeTable==ConstValue.TABLE_VIEW_RESULT){
				int row 		 = table.rowAtPoint(e.getPoint());
				int col  		 = 0;
				if(row < 0)
					return;
				TableModel model= table.getModel();
				String uri		 = model.getValueAt(row, col+4).toString();//Lay thuoc trang bao nhieu				
				this.compositeAnnotationResult.setOutputData(uri);				
			}
			else{
				int row = table.rowAtPoint(e.getPoint());
				int col  = 0;
				if(row < 0)
					return;
				TableModel model= table.getModel();
				int page		 =  Integer.parseInt((model.getValueAt(row, col+4)).toString());//Lay thuoc trang bao nhieu
				System.out.println(page);
				new PDFViewer(this.filePath, page);
			}
			
		}
	}
}