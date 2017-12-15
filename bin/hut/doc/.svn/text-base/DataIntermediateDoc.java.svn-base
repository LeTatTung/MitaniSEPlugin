package hut.doc;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.jpedal.PdfDecoder;
import org.jpedal.grouping.PdfGroupingAlgorithms;
import org.jpedal.objects.PdfImageData;
import org.jpedal.objects.PdfPageData;
import org.w3c.dom.Document;

import tool.parse.doc.ToolParseDoc;
import ws.owl.DocumentElement;
import ws.owl.DocumentSaveData;
import ws.owl.Section;

/**
 * @author KienCuong
 * Class dung de chua du lieu dung chung sau khi phan tich document, se lay
 * duoc cac du lieu imageDat,sectionData,paragraphData
 */
public class DataIntermediateDoc {
	private String id;
	private String document;	
	private List<List> imageData = new ArrayList<List>();
	private List<List> sectionData = new ArrayList<List>();
	private List<List> paragraphData = new ArrayList<List>();
	private List<List> secparaData = new ArrayList<List>();
	
	private Document doc;
	private HeadingStructure headingStructure;
	int startX = 0;
	int startY = 0;
	int x1, y1, x2, y2;
	String textContent;
	String textPage;
	String nameFile;
	public String getNameFile() {
		return nameFile;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public DataIntermediateDoc() {
		super();
	}

	public DataIntermediateDoc(String document) {
		super();
		this.document = document;
	}

	// Chuyen du lieu tu SaveDocumentData sang DataIntermidiateDoc
	public DataIntermediateDoc(DocumentSaveData saveData){
		
		// file name
		nameFile = saveData.getFileName();
		
		// set id cho document
		this.setId(saveData.getId());
		
		// du lieu paragraphs
		
		List<DocumentElement> paragraphsOnServer = saveData.getParagraphs();
		int paragraphsSize = paragraphsOnServer.size();
		ArrayList<String> tmpParagraphs = new ArrayList<String>();
		for (int i=0; i<paragraphsSize; i++){
			DocumentElement paragraphItemOnServer = paragraphsOnServer.get(i);
			ArrayList<String> paragraphItemOnClient = new ArrayList<String>();
			
			paragraphItemOnClient.add("Item"+i);
			paragraphItemOnClient.add(paragraphItemOnServer.getTextContent());
			paragraphItemOnClient.add(paragraphItemOnServer.getId());
			paragraphItemOnClient.add(paragraphItemOnServer.getLabel());
			paragraphItemOnClient.add(Integer.toString(paragraphItemOnServer.getBeginPage())); // trang chua paragraph
			paragraphItemOnClient.add(Integer.toString(i));           // stt cua paragraph
			paragraphItemOnClient.add(paragraphItemOnServer.getId()); // Id cua paragraph
			
			tmpParagraphs.add(paragraphItemOnClient.get(1));
			HashSet<String> hashSet = new HashSet<String>(tmpParagraphs);
			if (hashSet.size()==tmpParagraphs.size()){
				paragraphData.add(paragraphItemOnClient);
			}
			else{
				tmpParagraphs = new ArrayList<String>(hashSet);
			}
			//paragraphData.add(paragraphItemOnClient);
		}
		
		// du lieu cua images
		List<DocumentElement> imagesOnServer = saveData.getImages();
		int imagesSize = imagesOnServer.size();
		for(int i=0; i<imagesSize; i++){
			DocumentElement imageItemOnServer = imagesOnServer.get(i);
			ArrayList<String> imageItemOnClient = new ArrayList<String>();
			
			imageItemOnClient.add("Item "+i);
			imageItemOnClient.add(imageItemOnServer.getTextContent());
			imageItemOnClient.add(imageItemOnServer.getId());
			imageItemOnClient.add("image"+i); 
			imageItemOnClient.add(Integer.toString(imageItemOnServer.getBeginPage())); // trang chua image
			imageItemOnClient.add(Integer.toString(i));       // stt cua image
			imageItemOnClient.add(imageItemOnServer.getId()); // Id cua image nay
			
			//imageData.add(imageItemOnClient);
			imageData.add(imageItemOnClient);
		}
		
		// tao du lieu cho HeadingStructure
		HeadingStructure tempHeading = new HeadingStructure();
		tempHeading.setEndPageNumber(saveData.getPageNumbers());
		tempHeading.setDocumentName(saveData.getFileName());
		// tao HeadingNode root cho HeadingStructure tu root cua Section (lay tu ontology)
		tempHeading.setHeadingNodeFromSection(tempHeading.getRoot() ,saveData.getRootSection());
		
		this.headingStructure = tempHeading;
	}
	
	public List<List> getImageData() {
		return imageData;
	}

	public List<List> getSecparaData() {		
		return secparaData;
	}

	public List<List> getParagraphData() {
		return paragraphData;
	}
	
	/**
	 * Parse document: Ham nay goi nhung  ham parse cac thanh phan
	 */
	public void parse() {
		//Get name of file
		File file = new File(document);
		if (file.isFile()) {
			String tmpName = file.getName();
			this.nameFile=tmpName.substring(0,tmpName.indexOf("."));
		}
		PdfDecoder decodePdf = new PdfDecoder(false);
		decodePdf.init(true);
		try {
			decodePdf.openPdfFile(document);
		
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		doc = decodePdf.getOutlineAsXML();
		parseImage(decodePdf);
		parseHeading();
		parseParagraph(decodePdf);
	}
	/**
	 * @param decodePdf
	 * Tim kiem cac thong tin duoi file anh
	 */
	public void parseImage(PdfDecoder decodePdf) {
	
		int loopIndex = 0;
		ArrayList<String> listOfFiles = new ArrayList<String>();
		//Parse document
		int start = 1, end = decodePdf.getPageCount();
		for (int page = start; page < end + 1; page++) {
			try {
				//page=8;
				System.out.println("You are page: " + page);
				decodePdf.decodePage(page);
				PdfImageData infoImage = decodePdf.getPdfImageData();
				System.out.println("Count image: " + infoImage.getImageCount());
				//Trich xuat doi voi anh
				//Noi dung file anh
				for (int i = 0; i < infoImage.getImageCount(); i++) {

					x1 = (int) infoImage.getImageXCoord(i);
					y2 = (int) infoImage.getImageYCoord(i);
					x2 = x1 + (int) infoImage.getImageWidth(i);
					y1 = y2 + (int) infoImage.getImageHeight(i);
					x2 = x2 + 300;
					y2 = y2 - 40;
					//System.out.println(x1+","+y1+","+x2+","+y2);		   
				}
				//...
				if (infoImage.getImageCount() > 0) {
					//PdfDecoder.useTextExtraction();//pure text 
					PdfGroupingAlgorithms currentGrouping = decodePdf
							.getGroupingObject();
					//**The call to extract the text*//*
					textContent = currentGrouping.extractTextInRectangle(x1,
							y1, x2, y2, page, false, true);
					
					ToolParseDoc  toolParse = new ToolParseDoc();
					String[] resultArr = toolParse.SplitString(textContent, "</p>");					
					listOfFiles = new ArrayList<String>();
					listOfFiles.add("Item " + loopIndex);
					listOfFiles.add(resultArr[0]);
					listOfFiles.add(nameFile+"_image" + loopIndex+"_page"+page);
					listOfFiles.add("image" + loopIndex);   //La image thu may
					listOfFiles.add(Integer.toString(page));// Thuoc page bao nhieu
					listOfFiles.add(Integer.toString(loopIndex));
					loopIndex++;
					imageData.add(listOfFiles);
				}
				//Thuc thi extract dang text cua ca trang luon
				PdfPageData currentPageData = decodePdf.getPdfPageData();
				
				//Xoa cache cho cac anh sau
				decodePdf.clearScreen();

				//System.out.println("Ket thuc o page"+ page);
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}

	}

	/**
	 * @param decodePdf
	 * Ghi lai cac gia tri cho sectionData,paragraphData
	 */
	public void parseParagraph(PdfDecoder decodePdf) {
		DataExtractor dataExtractor = new DataExtractor(document);
		paragraphData 	= dataExtractor.getParagraphData();
		secparaData		= dataExtractor.getSecparaData();
		System.out.println("");
	}
	
	


	/**
	 * Lay heading tu noi dung file pdf
	 */
	public void parseHeading() {
		HeadingMatcher headingMatcher = new HeadingMatcher();
		headingStructure = headingMatcher.getHeadingFromPdfFile(this.document);
		//sectionData = headingStructure.getSectionData(headingStructure.root);
	}

	public HeadingStructure getHeading() {
		return headingStructure;
	}
	public DataIntermediateDoc getAllData() {
		DataIntermediateDoc result = new DataIntermediateDoc();
		result.doc = doc;
		result.imageData = imageData;
		result.paragraphData = paragraphData;
		result.sectionData = sectionData;
		result.headingStructure = headingStructure;
		return result;
	}
	

	/**
	 * @param pageStart
	 * @param pageEnd
	 * @return
	 * Ham nay se loc cac gia tri tu mot list lon ban dau khi phan tich document
	 * Phuc vu khi ta chon 1 thanh phan treenode cho bookmark thi se loc duoc cac
	 * thong tin chi co trong pham vi bookmark do
	 */
	public DataIntermediateDoc getSubData(int pageStart, int pageEnd, HeadingNode node) {
		DataIntermediateDoc result = new DataIntermediateDoc();

		List listItems;
		ArrayList<List> tmpList = new ArrayList<List>();
		ArrayList<String> listOfFiles = new ArrayList<String>();
		
		for (int i = 1; i < imageData.size(); i++) {
			listItems = imageData.get(i);
			int pageCurrent = Integer.parseInt(listItems.get(4).toString());
			if ((pageCurrent >= pageStart) && (pageCurrent <= pageEnd)) {
				tmpList.add(listItems);
			}
		}
		result.imageData = tmpList;
		
		/*ArrayList<List> tmpListSection = new ArrayList<List>();
		for (int i = 0; i < sectionData.size(); i++) {
			listItems = sectionData.get(i);
			int pageCurrent = Integer.parseInt(listItems.get(4).toString());
			if ((pageCurrent >= pageStart) && (pageCurrent <= pageEnd)) {
				tmpListSection.add(listItems);
			}
		}*/
		result.sectionData = this.getHeading().getSectionData(node);
		
		ArrayList<List> tmpListParagraph = new ArrayList<List>();
		for (int i = 0; i < paragraphData.size(); i++) {
			listItems = paragraphData.get(i);
			int pageCurrent = Integer.parseInt(listItems.get(4).toString());
			if ((pageCurrent >= pageStart) && (pageCurrent <= pageEnd)) {
				tmpListParagraph.add(listItems);
			}
		}
		Filter filter = new Filter();
		result.paragraphData = filter.removeShortParagraph(tmpListParagraph);
		return result;
	}
	
	public List<List> getSectionData() {
		return sectionData;
	}

	public String getDocument() {
		return document;
	}

	public String getAuthor() {
		return null;
	}
	
	

	
}
