package test;

import java.util.ArrayList;
import java.util.List;

import service.Service;

import ws.owl.ArrayListData;

import mintani.valueconst.ConsistentOntology;

public class DocumentRelateToCode {
	private String uriDocument;
	
	public DocumentRelateToCode(String uriDocument) {
		this.uriDocument = uriDocument;
	}
	
	public ArrayList<String> getRelation()
	{
		ArrayList<String> result = new ArrayList<String>();
		String query = 
			ConsistentOntology.prefix+
			"\nSELECT DISTINCT ?e"+
			"\n{"+			
			"\n?s rdf:type <" + ConsistentOntology.SOFTWARE_COMPONENT + ">."+
			"\n?e rdf:type <" + ConsistentOntology.DOCUMENT_ELEMENT + ">."+
			"\n<"+uriDocument+"> <" + ConsistentOntology.HAS_DOCUMENT_ELEMENT + "> ?e."+
			"\n?e <" + ConsistentOntology.HAS_RELATION_SOURCE + "> ?s."+			
			"\n}";
		System.out.println(query);
		List<ArrayListData> ad = Service.webServiceDelegate.sparqlResultList(null, query);
		for (int i=1; i<ad.size(); i++)
		{
			String s = ad.get(i).getData().get(0);
			if (s != null)
				result.add(s);
		}
		
		return result;
	}
	
	public static void main(String args[])
	{
		//Lay ra cac Element co quan he voi code
		DocumentRelateToCode d = new DocumentRelateToCode("http://hut.edu.vn/ontology/document#JAX-wsFramework");
		
		for (String uriElement: d.getRelation())
		{
			//Lay ra lop cua element (co the la Section, Image hoac Paragraph)
			String uriClass = Service.webServiceDelegate.getClassOfIndividual(null, uriElement);
			
			/**
			 * Tiep den, lay ra lan luot cac thuoc tinh mong muon
			 */
			
			String content = "";//Co the co nhieu content
			for (String s:Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, uriElement, ConsistentOntology.DOCUMENT_ELEMENT_HAS_TEXT_CONTENT))
			{
				content += s+"\n";
			}
			
			String page = "";//Co the co nhieu page end, nhung chi lay 1
			page +=Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, uriElement, ConsistentOntology.DOCUMENT_ELEMENT_HAS_PAGE_END).get(0);
			
			/**
			 * Lay ra tat ca cac thanh phan code lien quan, co the lay name, uri, ...
			 */
			String relatedCode="";
			//Dau tien lay ra cac quan he co the co voi code
			List<String> lstProperties = Service.webServiceDelegate.getPropertiesOfClassByRange(null, uriClass, ConsistentOntology.SOFTWARE_COMPONENT);
			
			for (String property:lstProperties)
			{
				//Vi du lay ra localname + type
				for (String s:Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, uriElement, property))
				{
					String tmp = Service.webServiceDelegate.getClassOfIndividual(null, s);
					tmp=tmp.substring(tmp.indexOf("#") + 1);
					s=s.substring(s.indexOf("#") + 1);
					property=property.substring(property.indexOf("#") + 1);
					relatedCode +=  property + "  " + s + "("+tmp+")\n";
					
					//Vd in ra manh hinh, tuong duong khi in ra bang
					System.out.println("-------------------------------------");
					uriElement = uriElement.substring(uriElement.indexOf("#") + 1);
					System.out.println("Element: " + uriElement);
					System.out.println("page: " + page);
					System.out.println("relation:" + relatedCode);
				}
			}
		}
	}

}
