package hut.doc;

import java.awt.Point;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jpedal.PdfDecoder;
import org.jpedal.exception.PdfException;
import org.jpedal.grouping.PdfGroupingAlgorithms;
import org.jpedal.objects.PdfPageData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;

/**
 * @author KienCuong
 * Class dung de trich xuat du lieu cho document dang: paragraph ,section
 */
public class DataExtractor {

	private Pattern sectionPattern = Pattern.compile("^([0-9]+\\.?)+[\\s]*[A-Z][ \\w]*[\\s]*");
	private Pattern fontSizePattern = Pattern.compile("[0-9]+");
	private Pattern fontTypePattern = Pattern.compile(":[A-z]+$");
	private Pattern letterPattern = Pattern.compile("[a-zA-Z]");
	private Pattern dotlistPattern = Pattern.compile("[ ]*[.]{2,}[0-9]+");
	private String outputDir;
	protected String separator = System.getProperty("file.separator");
	/**output where we put files*/
	protected String user_dir = System.getProperty("user.dir");
	//Du lieu can tra ve

	private List<List> paragraphData = new ArrayList<List>();
	private List<List> secparaData = new ArrayList<List>();
	private String file;
	private int start;
	private int end;
	private String fileName;
	private String prefix = ".txt";
	private int number=0;

	/**
	 * @param file: Ham sructure cua class, khoi tao dau vao la duong dan cua file document
	 */
	public DataExtractor(String file) {
		super();
		this.file = file;

		//action
		this.extractToFile(file);
		this.analysisDocument();

	}
	public List<List> getSecparaData() {
		return secparaData;
	}

	public List<List> getParagraphData() {
		return paragraphData;
	}

	private void analysisDocument() {
		number =0;
		for (int i = start; i < end+1; i++) {
			String filexml = outputDir + fileName + "_" + i + prefix;
			this.analysisFile(filexml, i);
		}

	}

	private void analysisFile(String filexml, int page) {
		
		DOMParser parser = new DOMParser();
		try {
			parser.parse(filexml);
			Document dom	= parser.getDocument();
			analysisFileComponent(dom, page);

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param filexml: dau vao la 1 file co ding dang xml
	 * @param page: trang cua document
	 * Phan tich 1 filexml tra ve 1 list sectionData,paragraphData
	 */
	private void analysisFileComponent(Document dom, int page) {
		int sectionItems = 0, paragraphItems = 0;
		int index = 0;
		String rawtext="";
		String valuetemp="",stringtemp="";
		List<List> tempData = null;
		NodeList list = null;
		list = dom.getElementsByTagName("p");		
		//Set<Node> boldNodes = new HashSet<Node>();
		for (int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);
			String value = node.getTextContent();
			
			//Lay text ban dau tu file xml
			
			
			//Kiem tra section or paragraph
			ArrayList<String> listItem = new ArrayList<String>();
			ArrayList<String> listSameItem = new ArrayList<String>();
			String[] splits = dotlistPattern.split(value);
			for (String v : splits) {
				// xac dinh section hay paragraph		
				if (hasLetter(v)) {
					if (isSection(v)) {
							index++;
						    rawtext=getContentXml(node,1);
							// Do vao list cho section
							sectionItems++;
							//Hien comment
							/*listItem.add("Item " + sectionItems);
							listItem.add(rawtext);
							listItem.add(fileName+"_section" + sectionItems+"_page"+page);
							listItem.add("section" + sectionItems);// La section thu may
							listItem.add(Integer.toString(page));  // Thuoc page bao nhieu
							listItem.add(Integer.toString(index));
							sectionData.add(listItem);*/
						
					} else {
						  
							//Kiem tra truong hop <p>dfasdfadf<font>1.2.This is section but not match width patter </font></p>
							//Chia nho thanh cac the voi font
							Element element = (Element) node;
							NodeList title = element.getElementsByTagName("font");							
							for (int j = 0; j < title.getLength(); j++) {
								Node nodenew = title.item(j);
								valuetemp = nodenew.getTextContent();
								String[] splitstemp = dotlistPattern.split(valuetemp);
								for (String s : splitstemp) {
									// xac dinh section hay paragraph		
									if (hasLetter(s)) {
										if (isSection(s)) {	
											//kiem tra neu checkString khac null thi add paragraph
											if(stringtemp !=""){
												if(stringtemp.length()>100){
													index++;
													ArrayList<String> listStore = new ArrayList<String>();
													paragraphItems++;
													listStore.add("Item " + paragraphItems);
													listStore.add(stringtemp);
													listStore.add(fileName+"_paragraph" + paragraphItems+"_page"+page);
													listStore.add("paragraph" + paragraphItems);//La paragraph thu may
													listStore.add(Integer.toString(page));		//Thuoc page bao nhieu
													listStore.add(Integer.toString(index));
													paragraphData.add(listStore);
													stringtemp="";													
												}
											}
											index++;
											rawtext=getContentXml(nodenew,1);
											// Do vao list cho section
											sectionItems++;
											//Hien comment
											/*listItem.add("Item " + sectionItems);
											listItem.add(rawtext);
											listItem.add(fileName+"_section" + sectionItems+"_page"+page);
											listItem.add("section" + sectionItems);
											listItem.add(Integer.toString(page));
											listItem.add(Integer.toString(index));
											sectionData.add(listItem);*/
											stringtemp="";
										}else{
											rawtext=getContentXml(nodenew,1);
											stringtemp +=rawtext;
											if(j==(title.getLength()-1)){
												if(stringtemp.length()>100){
													index++;
													ArrayList<String> listStore = new ArrayList<String>();
													paragraphItems++;
													listStore.add("Item " + paragraphItems);
													listStore.add(stringtemp);
													listStore.add(fileName+"_paragraph" + paragraphItems+"_page"+page);
													listStore.add("paragraph" + paragraphItems);
													listStore.add(Integer.toString(page));
													listStore.add(Integer.toString(index));
													paragraphData.add(listStore);
													stringtemp="";													
													
												}
											}
											
										}
										
									}
								}
							}
						
						// Do vao list cho pragraph
					}
				}
			}
		}

		
	}
	
	
	/**
	 * @param node: mot node khi goi phan tich document  parse
	 * @param type type==1 neu phan tich theo the font, type!=1 ,phan tich theo the p
	 * @return
	 */
	private String getContentXml(Node node,int type){
		String temptext="";
		String rawtext ="";
		String value = node.getTextContent();
		if (type == 1) {
			NamedNodeMap attributes = node.getAttributes();
			for (int k = 0; k < attributes.getLength(); k++) {
				Node att = attributes.item(k);
				if (att.getNodeName().equals("face")) {
					temptext = att.toString();
				} else if (att.getNodeName().equals("style")) {
					temptext += " " + att.toString();
				}
			}
			rawtext ="<font "+temptext+">"+value+"</font>";
		}else{
			Element element = (Element) node;
			NodeList title = element.getElementsByTagName("font");
			
			for (int j = 0; j < title.getLength(); j++) {
				Node nodenew = title.item(j);
				// lay cac gia tri thuoc tinh

				temptext = "";
				NamedNodeMap attributes = nodenew.getAttributes();
				for (int k = 0; k < attributes.getLength(); k++) {
					Node att = attributes.item(k);
					if (att.getNodeName().equals("face")) {
						temptext = att.toString();
					} else if (att.getNodeName().equals("style")) {
						temptext += " " + att.toString();
					}
				}
				rawtext += "<font " + temptext + ">" + nodenew.getTextContent() + "</font>";
			}
		}
		
		return rawtext;
	}

	/**
	 * kiem tra xem mot doan text co dinh dang cua mot section khong
	 * vi du ve section: "2.1.1. This is the title of section"
	 */
	private boolean isSection(String text) {
		if (text == null)
			return false;
		Matcher match = sectionPattern.matcher(text);
		if (match.find())
			return true;
		else
			return false;
		//		return match.lookingAt();		
	}

	/**
	 * @param text
	 * @return
	 * Kiem tra  xem co phai la chu cai khong
	 */
	private boolean hasLetter(String text) {
		if (text == null)
			return false;
		Matcher match = letterPattern.matcher(text);
		if (match.find())
			return true;
		else
			return false;
		//return match.lookingAt();	
	}

	/**
	 * @param text: co dang "font-size:14pt;font-style:BoldItalic"
	 * @return 14
	 * */
	private String getFontSize(String text) {
		Matcher match = fontSizePattern.matcher(text);
		if (match.find())
			return match.group();
		else
			return "12";
	}

	/**
	 * @param text: co dang "font-size:14pt;font-style:BoldItalic"
	 * @return BoldItalic
	 * */
	private String getFontType(String text) {
		Matcher match = fontTypePattern.matcher(text);
		if (match.find())
			return match.group().substring(1); // remove the : at start
		else
			return "Regular";
	}

	/**
	 * @param filepath:duong dan tai lieu can phan tich
	 * Ham tao cac file xml tu document
	 */
	public void extractToFile(String filepath) {
		String encoding = System.getProperty("file.encoding");
		File filexml;
		PdfDecoder decodePdf = new PdfDecoder(false);
		//decodePdf.setExtractionMode(PdfDecoder.TEXT);
		decodePdf.init(true);
		try {
			decodePdf.openPdfFile(filepath);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		fileName = new File(filepath).getName();
		if (fileName.lastIndexOf('.') != -1)
			fileName = fileName.substring(0, fileName.lastIndexOf('.'));

		outputDir = user_dir + "text" + separator + fileName + separator;
		//ensure a directory for data
		File page_path = new File(outputDir + separator);
		if (page_path.exists() == false)
			page_path.mkdirs();

		start = 1;
		end = decodePdf.getPageCount();
		PdfPageData currentPageData = decodePdf.getPdfPageData();
		for (int page = start; page < end + 1; page++) {
			//decode the page
			try {
				decodePdf.decodePage(page);
				/** create a grouping object to apply grouping to data*/
				PdfGroupingAlgorithms currentGrouping;
				currentGrouping = decodePdf.getGroupingObject();
				int x1 = currentPageData.getMediaBoxX(page);
				int x2 = currentPageData.getMediaBoxWidth(page) + x1;
				int y2 = currentPageData.getMediaBoxY(page);
				int y1 = currentPageData.getMediaBoxHeight(page) + y2;

				/**The call to extract the text*/
				String text = null;

				try {
					text = currentGrouping.extractTextInRectangle(x1, y1, x2,
							y2, page, true, true);
				} catch (PdfException e) {
					decodePdf.closePdfFile();
					System.err.println("Exception " + e.getMessage()
							+ " in file "
							+ decodePdf.getObjectStore().fullFileName);
					e.printStackTrace();
				}

				filexml = new File(outputDir + fileName + "_" + page + prefix);

				if (filexml.exists() == false) {
					try {
						encoding="UTF-8";
						OutputStreamWriter output_stream = new OutputStreamWriter(
								new FileOutputStream(outputDir + fileName + "_"
										+ page + prefix), encoding);

						output_stream
								.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n");
						output_stream
								.write("<!-- Pixel Location of text x1,y1,x2,y2\n");
						output_stream.write("(x1,y1 is top left corner)\n");
						output_stream.write("(x2,y2 is bottom right corner)\n");
						output_stream
								.write("(origin is bottom left corner)  -->\n");
						output_stream.write("\n\n<ARTICLE>\n");
						output_stream.write("<LOCATION x1=\"" + 2 + "\" "
								+ "y1=\"" + 3 + "\" " + "x2=\"" + 4 + "\" "
								+ "y2=\"" + 5 + "\" />\n");
						output_stream.write("\n\n<TEXT>\n");
						// NOTE DATA IS TECHNICALLY UNICODE
						output_stream.write(text); // write actual data
						output_stream.write("\n\n</TEXT>\n");
						output_stream.write("\n\n</ARTICLE>\n");
						output_stream.close();

					} catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}

		}

	}

	
}
