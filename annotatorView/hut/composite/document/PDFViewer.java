package hut.composite.document;

import org.jpedal.PdfDecoder;
import org.jpedal.examples.simpleviewer.SimpleViewer;

public class PDFViewer extends SimpleViewer 
{	
	public PDFViewer()
	{
		super();
		init();
	}
	public PDFViewer(String fileName, int page)
	{
		super();
		try{
			init(fileName, page);
		}catch(Exception e)
		{
			System.out.println("Can't not open file :"+fileName + " at page "+page);
		}
	}
	public void init()
	{
		this.decode_pdf.setExtractionMode(PdfDecoder.TEXT);
		SimpleViewer.exitOnClose = false;
        this.setupViewer();
	}
	public void init(String fileName, int page)
	{
		this.decode_pdf.setExtractionMode(PdfDecoder.TEXT);
		SimpleViewer.exitOnClose = false;
		System.setProperty("org.jpedal.page", String.valueOf(page));
        this.setupViewer(fileName);
        //this.decode_pdf.setDisplayView(Display.CONTINUOUS, Display.DISPLAY_LEFT_ALIGNED);
        // scaling 100%
        this.decode_pdf.setExtractionMode(PdfDecoder.TEXT);
        this.currentGUI.setScaling(6);
	}	
    public static void main(String[] argv) {
        PDFViewer view = new PDFViewer("C:/data/jgraphmanual.pdf", 2);       
    }    
}
