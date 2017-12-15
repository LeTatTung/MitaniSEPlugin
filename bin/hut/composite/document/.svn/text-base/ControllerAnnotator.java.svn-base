package hut.composite.document;

import hut.composite.popup.CompositePopupResult;
import hut.composite.popup.CompositePopupSourceCode;
import hut.doc.DataIntermediateDoc;
import hut.doc.HeadingNode;
import hut.doc.HeadingStructure;

import java.util.ArrayList;
import java.util.List;

import mintani.valueconst.ConsistentOntology;
import mitaniseplugin.views.ChooserSavedDocumentShell;
import mitaniseplugin.views.ViewDocumentAnotator;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolItem;

import service.Service;

import ws.data.DataServiceDelegate;
import ws.owl.InstanceData;
import ws.owl.PropertyMapData;
import ws.owl.Section;
import ws.owl.WebServiceDelegate;
import ws.owl.WebServiceService;

/**
 * @author KienCuong
 * 
 */
public class ControllerAnnotator {
	private CompositeDocOutline compositeDocOutline;
	private CompositeDocComponentAll compositeDocComponentAll;
	private CompositeDocComponent compositeDocComponentImage;
	private CompositeDocComponent compositeDocComponentParagraph;
	private CompositeDocComponent compositeDocComponentSection;
	private CompositePopupSourceCode compositePopupSourceCode;
	private CompositePopupResult compositePopupResult;
	private ViewDocumentAnotator viewDocumentAnotator;
	private ToolItem openSourceToolItem ;
	private String currentDocumentID;
	
	// Composite lon o duoi cung.
	private CompositeAnnotationResult compositeAnnotationResult;
	private JobParseDoc jobParseDoc;
	private DataIntermediateDoc saveDataDocument;
	private ChooserSavedDocumentShell chooserSavedDocumentShell;

	public ChooserSavedDocumentShell getChooserSavedDocumentShell() {
		return chooserSavedDocumentShell;
	}

	public ViewDocumentAnotator getViewDocumentAnotator() {
		return viewDocumentAnotator;
	}

	public void setViewDocumentAnotator(ViewDocumentAnotator viewDocumentAnotator) {
		this.viewDocumentAnotator = viewDocumentAnotator;
	}

	public void setChooserSavedDocumentShell(
			ChooserSavedDocumentShell chooserSavedDocumentShell) {
		this.chooserSavedDocumentShell = chooserSavedDocumentShell;
	}

	public void setCompositeDocOutline(CompositeDocOutline compositeDocOutline) {
		this.compositeDocOutline = compositeDocOutline;
	}

	public void setCompositeDocComponentAll(
			CompositeDocComponentAll compositeDocComponentAll) {
		this.compositeDocComponentAll = compositeDocComponentAll;
	}

	public String getCurrentDocumentID() {
		return currentDocumentID;
	}

	public void setCurrentDocumentID(String currentDocumentID) {
		this.currentDocumentID = currentDocumentID;
	}


	public void setCompositeDocComponentImage(
			CompositeDocComponent compositeDocComponentImage) {
		this.compositeDocComponentImage = compositeDocComponentImage;
	}

	public void setCompositeDocComponentParagraph(
			CompositeDocComponent compositeDocComponentParagraph) {
		this.compositeDocComponentParagraph = compositeDocComponentParagraph;
	}

	public void setCompositeDocComponentSection(
			CompositeDocComponent compositeDocComponentSection) {
		this.compositeDocComponentSection = compositeDocComponentSection;
	}

	public void setCompositePopupSourceCode(
			CompositePopupSourceCode compositePopupSourceCode) {
		this.compositePopupSourceCode = compositePopupSourceCode;
	}


	public void setCompositeAnnotationResult(
			CompositeAnnotationResult compositeAnnotationResult) {
		this.compositeAnnotationResult = compositeAnnotationResult;
	}

	public void setCompositePopupResult(
			CompositePopupResult compositePopupResult) {
		this.compositePopupResult = compositePopupResult;
	}

	public ControllerAnnotator() {

	}

	/**
	 * Change the document
	 */
	public void changeDocument(Object inputData, String currentPathFile) {

		compositeDocOutline.setInputData(inputData);
		compositeDocOutline.updateInterface();

		// Set duong dan file pdf cho toan bo controller thong qua 1 composite
		// bat ki
		compositeDocOutline.setCurrentPathFile(currentPathFile);

		// Set duong dan file pdf cho compositePopupResult
		// compositePopupResult.setCurrentPathFile(currentPathFile);

	}
	
	/**
	 * refresh composite lien ket code-doc
	 */
	
	public void refreshRelationProperties(){
		this.getViewDocumentAnotator().refreshUnderAnnotation();
	}

	/**
	 * Lay current book mark cho tat cac cac composite
	 */
	public String getBookMark() {
		return this.compositeDocOutline.getCurrentBookmark();
	}

	/**
	 * Lay current path cho tat ca cac composite
	 */

	public String getCurrentPath() {
		return this.compositeDocOutline.getCurrentPathFile();
	}

	/**
	 * Change the heading from composite outline
	 */
	public void changeHeading() {
		compositeDocComponentAll.setInputData(compositeDocOutline
				.getOutputData());
		try {
			compositeDocComponentAll.setCurrentPathFile(compositeDocOutline
					.getCurrentPathFile());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			compositeDocComponentAll.updateInterface();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
/*		System.out.println();*/
	}

	/**
	 * Cho du lieu vao popup cua phan image
	 * 
	 * @return
	 */
	public Object popupBindImageData() {
		// System.out.println(compositeDocComponentImage.getInputData());
		return compositeDocComponentImage.getInputData();
	}

	/**
	 * Cho du lieu vao phan popup cua section
	 */
	public Object popupBindSectionData() {
		return compositeDocComponentSection.getInputData();
	}

	/**
	 * 
	 */
	public Object popupBindParagraphData() {
		compositeDocComponentParagraph.setOutputData(compositeDocOutline
				.getInputData());
		return compositeDocComponentParagraph.getInputData();
	}

	public Object popupBindResultData() {
		return compositePopupSourceCode.getInputData();
	}

	public void updateAction(String action) {
		compositeDocComponentAll.updateAction(action);
	}

	public void setJobparseDoc(JobParseDoc job) {
		this.jobParseDoc = job;
	}

	public void setSaveDataDocument(DataIntermediateDoc dataIntermediateDoc) {
		this.saveDataDocument = dataIntermediateDoc;

	}

	public CompositeDocComponent getCompositeDocComponentImage() {
		return compositeDocComponentImage;
	}

	public CompositeDocComponent getCompositeDocComponentParagraph() {
		return compositeDocComponentParagraph;
	}

	public CompositeDocComponent getCompositeDocComponentSection() {
		return compositeDocComponentSection;
	}

	/**
	 * ham nay dung de gui du lieu sau khi phan tich cua document len server va
	 * luu vao ontology
	 */
	public void sendDocumentDataToServer() {
		if (saveDataDocument == null) {
			Shell shell = new Shell(Display.getCurrent());
			MessageDialog.openWarning(shell, "Message",
					"Please parse a document!!!");
		} else {
			String fileName = saveDataDocument.getNameFile();
			System.out.println("document: " + fileName);
			ArrayList<InstanceData> listInstance = new ArrayList<InstanceData>();

			// instance luu thong tin chung
			InstanceData documentInstance = new InstanceData();
			DocumentComponentNaming documentNaming = new DocumentComponentNaming();
			HeadingStructure sectionStructure = saveDataDocument.getHeading();
			List<List> paragraph = saveDataDocument.getParagraphData();
			List<List> image = saveDataDocument.getParagraphData();

			// Luu thong tin chung ve document
			documentInstance.setClassName(ConsistentOntology.DOC_TYPE_CLASS);
			documentNaming.setIdDocument(fileName);
			documentInstance.setInstanceID(documentNaming.getIdDocumentFull());
			documentInstance.setInstanceLabel(fileName);
			
			/**
			 * Luu tong so trang cho document
			 */
			PropertyMapData textContentSectionProperty = new PropertyMapData();
			textContentSectionProperty
				.setPropertyname(ConsistentOntology.DOCUMENT_HAS_PAGE_NUMBERS);
			textContentSectionProperty.setValue(String.valueOf(sectionStructure.getEndPageNumber()));
			documentInstance.getDataPropertyList().add(textContentSectionProperty);

			/**
			 * tao instanceData cho tat ca cac section, image va paragraph
			 */
			ArrayList<InstanceData> listAllInstance = new ArrayList<InstanceData>();
			HeadingNode root = sectionStructure.getRoot();
			root.setPageNumber(1);
			root.setPageNext(1);
			listAllInstance = getListInstanceData(root,
					documentNaming, documentInstance, null, saveDataDocument);
			listInstance.addAll(listAllInstance);

			

			// add documentInstance vao list
			listInstance.add(documentInstance);

			// Test
			for (int i = 0; i < listInstance.size(); i++) {
				System.out
						.println("------------------------------------------------------------------");
				System.out.println(listInstance.get(i).getInstanceID());
			}

			// gui list nay len server (luu vao ontology)
			// luu vao model chung
//			Service.webServiceDelegate.saveValuesOfIndividual(null, listInstance);
//
//			// luu vao model rong (ko co anotation) va ghi ra file owl
//			Service.webServiceDelegate.reloadOntology("JavaDocumentOntology.owl");
//			Service.webServiceDelegate.saveValuesOfIndividual(
//					"JavaDocumentOntology.owl", listInstance);
//			Service.webServiceDelegate.saveDocumentToOWL("JavaDocumentOntology.owl", fileName);
//
//			Shell shell = new Shell(Display.getCurrent());
//			MessageDialog.openInformation(shell, "Success!", "Data of "
//					+ fileName + " have been Saved!!");
			
			JobSaveAnnotation jobSave = new JobSaveAnnotation(null, fileName, listInstance);
			jobSave.saveAnnotation();
		}
	}

	/**
	 * lay ra mot list cac instance tu 1 node thuat toan: tao instance luu thong
	 * tin node do for (voi moi con cua node dang xet){ lay ra ID gia cua node
	 * con (thuc chat thi ID nay chua ton tai) add thuoc tinh
	 * hasSection vao node dang xet }
	 */
	private ArrayList<InstanceData> getListInstanceData(
			HeadingNode currentSection, DocumentComponentNaming naming,
			InstanceData documentInstance, InstanceData parentSectionInstance,
			DataIntermediateDoc saveDataDocument) {
			ArrayList<InstanceData> result = new ArrayList<InstanceData>();
			int sizeOfChildrens = currentSection.getChildrens().size();
			String fullIndex = currentSection.getFullIndexAsString();
			String pageStart = String.valueOf(currentSection.getPageNumber());
			String pageEnd = String.valueOf(currentSection.getPageNext());
			String textContent = currentSection.getContent();

			InstanceData sectionInstance = new InstanceData();
			sectionInstance.setClassName(ConsistentOntology.SECTION);
		if (currentSection.getLevel() == 0) {
			naming.setIdSection("_root");
			sectionInstance.setInstanceID(naming.getIdSectionFull());
			sectionInstance.setInstanceLabel(naming.getIdSection());
		} else {
			naming.setIdSection(fullIndex + "PageStart_" + pageStart
					+ "_PageEnd_" + pageEnd);
			sectionInstance.setInstanceID(naming.getIdSectionFull());
			sectionInstance.setInstanceLabel(naming.getIdSection());
		}

		// section nay la thanh phan cua document nao
			PropertyMapData documentContainsSectionProperty = new PropertyMapData();
			documentContainsSectionProperty
			.setTypeClass(ConsistentOntology.SECTION);
			documentContainsSectionProperty
				.setPropertyname(ConsistentOntology.SECTION_HAS_SECTION);
			documentContainsSectionProperty.setValue(sectionInstance
				.getInstanceID());
			documentInstance.getObjectPropertyList().add(
				documentContainsSectionProperty);

		// them thuoc tich textContent cho section
			PropertyMapData textContentSectionProperty = new PropertyMapData();
			textContentSectionProperty
				.setPropertyname(ConsistentOntology.DOCUMENT_ELEMENT_HAS_TEXT_CONTENT);
			textContentSectionProperty.setValue(textContent);
			sectionInstance.getDataPropertyList().add(textContentSectionProperty);

		// them thuoc tinh full Index cho section
			PropertyMapData fullIndexSectionProperty = new PropertyMapData();
			fullIndexSectionProperty.setPropertyname(ConsistentOntology.SECTION_HAS_FULL_INDEX);
			fullIndexSectionProperty.setValue(currentSection.getFullIndexAsString());
			sectionInstance.getDataPropertyList().add(fullIndexSectionProperty);
			
		// them thuoc tinh level cho section
			PropertyMapData levelOfSection = new PropertyMapData();
			levelOfSection.setPropertyname(ConsistentOntology.SECTION_HAS_LEVEL);
			levelOfSection.setValue(String.valueOf(currentSection.getLevel()));
			sectionInstance.getDataPropertyList().add(levelOfSection);
			
		// them thuoc tinh page begin cho section
			PropertyMapData pageStartSectionProperty = new PropertyMapData();
			pageStartSectionProperty
				.setPropertyname(ConsistentOntology.DOCUMENT_ELEMENT_HAS_PAGE_BEGIN);
			pageStartSectionProperty.setValue(pageStart);
			sectionInstance.getDataPropertyList().add(pageStartSectionProperty);

		// them thuoc tinh page end cho section
			PropertyMapData pageEndSectionProperty = new PropertyMapData();
			pageEndSectionProperty
				.setPropertyname(ConsistentOntology.DOCUMENT_ELEMENT_HAS_PAGE_END);
			pageEndSectionProperty.setValue(pageEnd);
			sectionInstance.getDataPropertyList().add(pageEndSectionProperty);

		if (sizeOfChildrens <= 0) {
			// vi la section la' nen phai them ca image va paragraph thuoc
			// section nay luon
				ArrayList<InstanceData> imageInstances = new ArrayList<InstanceData>();
				ArrayList<InstanceData> paragraphInstances = new ArrayList<InstanceData>();
				List<List> images = saveDataDocument.getImageData();
				List<List> paragraphs = saveDataDocument.getParagraphData();
			
			// tao list image instance
			for (int i = 0; i < images.size(); i++) {
				ArrayList<String> currentImage = (ArrayList<String>) images.get(i);
				int currentImagePage = Integer.valueOf(currentImage.get(4));
				if (currentImagePage < currentSection.getPageNext()){
					if (currentImagePage >= currentSection.getPageNumber()) {
						 InstanceData imageInstance = new InstanceData(); 
						 naming.setIdImage(Integer.valueOf(currentImage.get(4)),
						 Integer.valueOf(currentImage.get(5)));
						 imageInstance.setClassName(ConsistentOntology.IMAGE);
						 imageInstance.setInstanceID(naming.getIdImageFull());
						 imageInstance.setInstanceLabel(naming.getIdImage());
						  // gan thuoc tinh doi tuong cho section
						 PropertyMapData sectionContainsProperyData = new PropertyMapData();
						 sectionContainsProperyData.setTypeClass(ConsistentOntology.IMAGE);
						 sectionContainsProperyData.setPropertyname(ConsistentOntology.SECTION_HAS_IMAGE);
						 sectionContainsProperyData.setValue(imageInstance.getInstanceID());
						 sectionInstance.getObjectPropertyList().add(sectionContainsProperyData);
						 
						  // gan thuoc tinh noi dung cho image PropertyMapData
						  PropertyMapData imageContentProperyData = new PropertyMapData();
						  imageContentProperyData.setPropertyname(ConsistentOntology.DOCUMENT_ELEMENT_HAS_TEXT_CONTENT);
						  imageContentProperyData.setValue(currentImage.get(1));
						  imageInstance.getDataPropertyList().add(imageContentProperyData);
						  
						   // gan thuoc tinh begin page cho image PropertyMapData
						  PropertyMapData imagePageProperyData = new PropertyMapData();
						  imagePageProperyData.setPropertyname(ConsistentOntology.DOCUMENT_ELEMENT_HAS_PAGE_BEGIN);
						  imagePageProperyData.setValue(currentImage.get(4));
						  imageInstance.getDataPropertyList().add(imagePageProperyData);
						  
						  // gan thuoc tinh end page cho image PropertyMapData
						  PropertyMapData imagePageEndProperyData = new PropertyMapData();
						  imagePageEndProperyData.setPropertyname(ConsistentOntology.DOCUMENT_ELEMENT_HAS_PAGE_END);
						  imagePageEndProperyData.setValue(currentImage.get(4));
						  imageInstance.getDataPropertyList().add(imagePageEndProperyData);
						  
						  imageInstances.add(imageInstance);
					}
				}
			}
			
			// tao paragraph instance
			for (int i = 0; i < paragraphs.size(); i++) {
				ArrayList<String> currentParagraph = (ArrayList<String>) paragraphs.get(i);
				int currentParagraphPage = Integer.valueOf(currentParagraph.get(4));
				if (currentParagraphPage <= currentSection.getPageNext()){
					if (currentParagraphPage >= currentSection.getPageNumber()) {
						 InstanceData paragraphInstance = new InstanceData(); 
						 naming.setIdParagraph(Integer.valueOf(currentParagraph.get(4)),
						 Integer.valueOf(currentParagraph.get(5)));
						 
						 paragraphInstance.setClassName(ConsistentOntology.PARAGRAPH);
						 paragraphInstance.setInstanceID(naming.getIdParagraphFull());
						 paragraphInstance.setInstanceLabel(naming.getIdParagraph());
						 
						  // gan thuoc tinh doi tuong cho section
						 PropertyMapData sectionContainsProperyData = new PropertyMapData();
						 sectionContainsProperyData.setTypeClass(ConsistentOntology.PARAGRAPH);
						 sectionContainsProperyData.setPropertyname(ConsistentOntology.SECTION_HAS_PARAGRAPH);
						 sectionContainsProperyData.setValue(paragraphInstance.getInstanceID());
						 sectionInstance.getObjectPropertyList().add(sectionContainsProperyData);
						 
						  // gan thuoc tinh noi dung cho image PropertyMapData
						  PropertyMapData imageContentProperyData = new PropertyMapData();
						  imageContentProperyData.setPropertyname(ConsistentOntology.DOCUMENT_ELEMENT_HAS_TEXT_CONTENT);
						  imageContentProperyData.setValue(currentParagraph.get(1));
						  paragraphInstance.getDataPropertyList().add(imageContentProperyData);
						  
						   // gan thuoc tinh begin page, end page cho image PropertyMapData
						  PropertyMapData imagePageProperyData = new PropertyMapData();
						  imagePageProperyData.setPropertyname(ConsistentOntology.DOCUMENT_ELEMENT_HAS_PAGE_BEGIN);
						  imagePageProperyData.setValue(currentParagraph.get(4));
						  paragraphInstance.getDataPropertyList().add(imagePageProperyData);
						  
						  // gan thuoc tinh end page cho paragraph PropertyMapData
						  PropertyMapData paragraphPageEndProperyData = new PropertyMapData();
						  paragraphPageEndProperyData.setPropertyname(ConsistentOntology.DOCUMENT_ELEMENT_HAS_PAGE_END);
						  paragraphPageEndProperyData.setValue(currentParagraph.get(4));
						  paragraphInstance.getDataPropertyList().add(paragraphPageEndProperyData);
						  
						  paragraphInstances.add(paragraphInstance);
					}
				}
			}

			// them instance cua section nay vao
			result.addAll(imageInstances);
			result.addAll(paragraphInstances);
			result.add(sectionInstance);
		} else {
			// De quy tao instance cho cac section
			for (int i = 0; i < sizeOfChildrens; i++) {
				HeadingNode tmpNode = currentSection.getChildrens().get(i);
				String fullIndexOfChildNode = tmpNode.getFullIndexAsString();
				String pageStartOfChildNode = String.valueOf(tmpNode
						.getPageNumber());
				String pageEndOfChildNode = String.valueOf(tmpNode
						.getPageNext());

				// them thuoc tinh section has section
				PropertyMapData childSectionProperty = new PropertyMapData();
				childSectionProperty.setTypeClass(ConsistentOntology.SECTION);
				childSectionProperty
						.setPropertyname(ConsistentOntology.SECTION_HAS_SECTION);
				naming.setIdSection(fullIndexOfChildNode + "PageStart_"
						+ pageStartOfChildNode + "_PageEnd_"
						+ pageEndOfChildNode);
				childSectionProperty.setValue(naming.getIdSectionFull());
				sectionInstance.getObjectPropertyList().add(
						childSectionProperty);

				result.addAll(getListInstanceData(tmpNode, naming,
						documentInstance, sectionInstance, saveDataDocument));
			}
			result.add(sectionInstance);
		}
		return result;
	}


	public ToolItem getOpenSourceToolItem() {
		return openSourceToolItem;
	}

	public void setOpenSourceToolItem(ToolItem openSourceToolItem) {
		this.openSourceToolItem = openSourceToolItem;
	}

}
