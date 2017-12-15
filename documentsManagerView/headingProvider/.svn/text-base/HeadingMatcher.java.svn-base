package headingProvider;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTree;

import org.jpedal.PdfDecoder;
import org.jpedal.exception.PdfException;
import org.jpedal.grouping.PdfGroupingAlgorithms;
import org.jpedal.objects.PdfPageData;

import tool.parse.doc.ToolParseDoc;

/**
 * 
 * This class allows get heading from file
 * 
 * @author Mr Hien
 * 
 */
public class HeadingMatcher {
	/**
	 * Instructor with out parameter
	 */
	public HeadingMatcher() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * This method is used to get JTree type of a HeadingStructure
	 * 
	 * @param headingStructure
	 * @return
	 */
	public JTree getJTreeOfHeading(HeadingStructure headingStructure) {
		JTree jTreeOfHeading = new JTree();
		jTreeOfHeading = headingStructure.getJTree();
		return jTreeOfHeading;
	}

	/**
	 * This method is used to get HeadingStructure from *.txt file
	 * 
	 * @param documentFilePath
	 * @return
	 * @throws IOException
	 */
	public HeadingStructure getHeading(String documentFilePath)
			throws IOException {
		File inputFile = new File(documentFilePath);
		FileInputStream fileStream = new FileInputStream(inputFile);
		BufferedInputStream fileBufferStream = new BufferedInputStream(
				fileStream);
		DataInputStream fileData = new DataInputStream(fileBufferStream);
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				fileData));

		HeadingStructure documentHeadingStructure = new HeadingStructure(
				inputFile.getName(), "Unknow");

		String currentLine;
		int countLine = 0;
		while ((currentLine = reader.readLine()) != null) {
			countLine++;
			Pattern indexPattern = Pattern
					.compile("(\\s)*[1-9]+[0-9]*(\\.[0-9]*)*(\\s)+");
			Matcher indexMatcher = indexPattern.matcher(currentLine);

			Pattern headingContenPattenrn = Pattern.compile("(\\S)(.)+");

			if (indexMatcher.find() && (indexMatcher.start() == 0)) {
				Matcher headingContentMatcher = headingContenPattenrn
						.matcher(currentLine.substring(indexMatcher.end()));
				if (headingContentMatcher.find()) {
					try {
						String fullIndexOfHeadingAsString = indexMatcher
								.group();
						String headingContent = headingContentMatcher.group();
						ArrayList<Integer> fullIndexOfParentHeading = new ArrayList<Integer>();
						fullIndexOfParentHeading
								.addAll(this
										.getFullIndexOfParentFromString(fullIndexOfHeadingAsString));
						documentHeadingStructure.addNewNode(headingContent,
								fullIndexOfParentHeading);

					} catch (Exception e) {
						// TODO: handle exception
						System.err.println(e.getCause());
					}
				}
			}
		}
		return documentHeadingStructure;
	}

	/**
	 * This method allow get Heading from *.pdf file
	 * @param pdfFilePath
	 * @return
	 */
	public HeadingStructure getHeadingFromPdfFile(String pdfFilePath){
		// Dung de luu du lieu cua heading thu duoc
		HeadingStructure headingStructureResult = new HeadingStructure();
		
		// Dung de phan tich file pdf
		PdfDecoder pdfDecoder = new PdfDecoder(false);
		pdfDecoder.setExtractionMode(PdfDecoder.TEXT);
		pdfDecoder.init(true);
		// Dung de lay plain text tu trang xml
		ToolParseDoc toolGetPlainText = new ToolParseDoc();
		
		try {
			pdfDecoder.openPdfFile(pdfFilePath);
		} catch (PdfException e) {
			System.out.println(e.getMessage());
		}
		// Lay so trang cua file
		int pageCount = pdfDecoder.getPageCount();
		
		// Duyet tung trang
		PdfPageData pageData = pdfDecoder.getPdfPageData();;
		PdfGroupingAlgorithms pdfGrouping;
		for (int pageNumber=1; pageNumber<= pageCount; pageNumber++){
			try {
				pdfDecoder.decodePage(pageNumber);
				pdfGrouping = pdfDecoder.getGroupingObject();
				//-----------------------------------------------------------------
				//Doc xu lieu text nam trong cac khung hinh chu nhat gioi han trang
				// 			++++++++++++++++++++++(x2,y2)
				// 			+						+
				// 			+						+
				//		(x1,y1)++++++++++++++++++++++
				//-----------------------------------------------------------------
				int x1 = pageData.getMediaBoxX(pageNumber);
				int x2 = x1+pageData.getCropBoxWidth(pageNumber);
				int y2 = pageData.getMediaBoxY(pageNumber);
				int y1 = y2+pageData.getMediaBoxHeight(pageNumber);
				String xmlContentOfPage = pdfGrouping.extractTextInRectangle(x1, y1, x2, y2, pageNumber, true, true);
				String textContenOfPage = toolGetPlainText.convertPlainText(xmlContentOfPage);
				// bat tung dong 1
				Pattern NewLine = Pattern.compile("\\n");
				Matcher LineMatcher = NewLine.matcher(textContenOfPage);
				ArrayList<Integer> indexStartLine = new ArrayList<Integer>();
				indexStartLine.add(0);
				while (LineMatcher.find()) {
					indexStartLine.add(LineMatcher.start());
				}
				indexStartLine.add(textContenOfPage.length()-1);
				
				// luu du lieu tung dong cua trang vao 1 mang cac xau
				ArrayList<String> arrayLine = new ArrayList<String>();

				for (int i = 0; i < indexStartLine.size() - 1; i++) {
					arrayLine.add(textContenOfPage.substring(indexStartLine
							.get(i), indexStartLine.get(i + 1)));
				}
				
				// Doc tung dong trong page
				for (int i = 0; i < arrayLine.size(); i++) {
					 //System.out.println("Page "+pageNumber+", line "+i+" : "+
					 //ArrayLine.get(i));
					String currentLine = arrayLine.get(i);
					Pattern indexPattern = Pattern
							.compile("(\\s)*[1-9]+[0-9]*(\\.[0-9]*)*(\\s)+");
					Matcher indexMatcher = indexPattern.matcher(currentLine);

					Pattern headingContenPattenrn = Pattern
							.compile("(\\S)(.)+");

					if (indexMatcher.find() && (indexMatcher.start() == 0)) {
						Matcher headingContentMatcher = headingContenPattenrn
								.matcher(currentLine.substring(indexMatcher
										.end()));
						if (headingContentMatcher.find()) {
							try {
								String fullIndexOfHeadingAsString = indexMatcher
										.group();
								String headingContent = headingContentMatcher
										.group();
								ArrayList<Integer> fullIndexOfParentHeading = new ArrayList<Integer>();
								fullIndexOfParentHeading
										.addAll(this
												.getFullIndexOfParentFromString(fullIndexOfHeadingAsString));
								headingStructureResult.addNewNode(
										headingContent,
										fullIndexOfParentHeading, pageNumber);

							} catch (Exception e) {
								// TODO: handle exception
								System.err.println(e.getCause());
							}
						}
					}
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return headingStructureResult;
	}
	

	/**
	 * This method is used to get parent's fullIndex of node from String of
	 * fullIndex
	 * 
	 * @param fullIndexAsString
	 * @return
	 */
	public ArrayList<Integer> getFullIndexOfParentFromString(
			String fullIndexAsString) {
		ArrayList<Integer> fullIndex = new ArrayList<Integer>();
		Pattern fullIndexPattern = Pattern
				.compile("(\\s)*[1-9]+[0-9]*(\\.[0-9]*)*(\\s)+");
		Matcher fullIndexMatcher = fullIndexPattern.matcher(fullIndexAsString);
		if (fullIndexMatcher.find() && fullIndexMatcher.start() == 0) {
			Pattern indexPattern = Pattern.compile("[0-9]+");
			Matcher indexMatcher = indexPattern.matcher(fullIndexMatcher
					.group());
			while (indexMatcher.find()) {
				fullIndex.add(Integer.parseInt(indexMatcher.group()));
			}
		}
		fullIndex.remove(fullIndex.size() - 1);
		return fullIndex;
	}
}
