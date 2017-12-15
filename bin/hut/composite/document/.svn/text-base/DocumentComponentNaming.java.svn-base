package hut.composite.document;

import mintani.valueconst.ConsistentOntology;

public class DocumentComponentNaming {

	private String idDocument;
	private String idSection;
	private String idParagraph;
	private String idImage;
		
	private String idDocumentFull;
	private String idSectionFull;
	private String idParagraphFull;
	private String idImageFull;
	
	public void setIdDocument(String idDocument)
	{
		this.idDocument = idDocument;
		this.idDocumentFull = ConsistentOntology.DOC_NAMESPACE + idDocument;
	}
	
	public void setIdSection (String idSection)
	{
		this.idSection = idSection;
		this.idSectionFull = this.idDocumentFull + ".Section" + idSection;
	}
	
	/**
	 * Paragraph dat ten dac biet, count chi ra la paragraph thu may trong moi section
	 * @param count
	 */
	public void setIdParagraph (int page, int count)
	{
		String tmp = ".Paragraph"+ count+"_page"+page;
		this.idParagraph = tmp;
		this.idParagraphFull = this.idDocumentFull + tmp;
	}
	
	public void setIdImage (int page, int count)
	{
		String tmp = ".Image"+ count+"_page"+page;
		this.idImage = tmp;
		this.idImageFull = this.idDocumentFull + tmp;
	}
	
	public String getIdDocument() {
		return idDocument;
	}

	public String getIdSection() {
		return idSection;
	}

	public String getIdParagraph() {
		return idParagraph;
	}

	public String getIdImage() {
		return idImage;
	}

	public String getIdDocumentFull() {
		return idDocumentFull;
	}

	public String getIdSectionFull() {
		return idSectionFull;
	}

	public String getIdParagraphFull() {
		return idParagraphFull;
	}

	public String getIdImageFull() {
		return idImageFull;
	}
	

	
	
	
	
}
