package test;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mintani.valueconst.ConsistentOntology;

import service.Service;


import ws.owl.*;

public class MyTest {
	public static WebServiceDelegate delegate;
	
	public static void printList(ArrayList<String> list)
	{
		if (list.isEmpty())
			System.out.println("list empty");
		else
			for (String s: list)
				System.out.println(s);
	}
	public static void printList(List<String> list)
	{
		if (list.isEmpty())
			System.out.println("list empty");
		else
			for (String s: list)
				System.out.println(s);
	}
	
	public static void testIndividualOperator()
	{
		System.out.println("Create instance abc of Class Library");
		delegate.createInstance(null, "lib", "http://hut.edu.vn/ontology/sourcecode#Library");
		delegate.createInstance(null, "pack", "http://hut.edu.vn/ontology/sourcecode#Package");
		delegate.createInstance(null, "src", "http://hut.edu.vn/ontology/sourcecode#FolderSourceCode");
		
		System.out.println("Library 1");
		printList(delegate.listClassInstance(null, "http://hut.edu.vn/ontology/sourcecode#Library"));
		System.out.println("Library all");
		printList(delegate.listAllRelatedInstance(null, "http://hut.edu.vn/ontology/sourcecode#Library"));	
		
		System.out.println("Get Class of a Individual");
		System.out.println(delegate.getClassOfIndividual(null, "lib"));
		
		System.out.println("Add property data");
		delegate.addDatatypeProperty(null, "http://hut.edu.vn/ontology/sourcecode#hasDirectType", "Library", "lib");
		delegate.addDatatypeProperty(null, "http://hut.edu.vn/ontology/sourcecode#hasDirectType", "MNP", "lib");
		delegate.addDatatypeProperty(null, "http://hut.edu.vn/ontology/sourcecode#hasDirectType", "Package", "pack");
		delegate.addDatatypeProperty(null, "http://hut.edu.vn/ontology/sourcecode#hasDirectType", "FolderSourceCode", "src");
		
		System.out.println("Add object property data");
		delegate.addObjectProperty(null, "http://hut.edu.vn/ontology/sourcecode#hasPackage", "pack", "lib");
		delegate.addObjectProperty(null, "http://hut.edu.vn/ontology/sourcecode#hasPackage", "pack", "src");
		
		System.out.println("Get all property data");
		for (BkIndividualProperty bk: delegate.getValuesOfIndividual(null, "pack"))
		{
			System.out.println(bk.getProperty());
			printList(bk.getListValue());
		}
		
		System.out.println("Get one property data");
		printList(delegate.getValueOfSpecificPropertyForIndividual(null, "lib", "http://hut.edu.vn/ontology/sourcecode#hasDirectType"));
		System.out.println("Get one property data");
		printList(delegate.getValueOfSpecificPropertyForIndividual(null, "pack", "http://hut.edu.vn/ontology/sourcecode#packageOf"));
		
		System.out.println("Get individual by name");
		System.out.println(delegate.getIndividualByName(null, "lib").getIndividualURI());
		
		System.out.println("Check individual exist");
//		System.out.println(delegate.checkexitsIndividual(null, "lib"));
//		System.out.println(delegate.checkexitsIndividual(null, "lib1234"));
		
		
		System.out.println("List individual");
		printList(delegate.listAllRelatedInstance(null, "http://hut.edu.vn/ontology/sourcecode#Library"));	
		System.out.println("Execute remove Individual");
		delegate.removeIndividual(null, "lib");
		System.out.println("List lai individual");
		printList(delegate.listAllRelatedInstance(null, "http://hut.edu.vn/ontology/sourcecode#Library"));
		
		System.out.println("Check Individual exist");
		System.out.println(delegate.checkExistIndividual(null, "http://hut.edu.vn/ontology/sourcecode#dfdf"));
		
		
	}
	
	public static void saveIndividualTest()
	{
		System.out.println("-----------------------------------");
		System.out.println("Check save Individual all");
		List<InstanceData> list= new ArrayList<InstanceData>();
		
		Service.webServiceDelegate.getClassByShortName(null, "Library");
		InstanceData iData= new InstanceData();
		iData.setInstanceID("http://hut.edu.vn/ontology/sourcecode#hehehe");
		iData.setClassName("http://hut.edu.vn/ontology/sourcecode#Library");
		
		PropertyMapData pm= new PropertyMapData();
		pm.setPropertyname("http://hut.edu.vn/ontology/sourcecode#hasDirectType");
		pm.setValue("LIBRARY");
		iData.getDataPropertyList().add(pm);
		
		pm= new PropertyMapData();
		pm.setTypeClass("http://hut.edu.vn/ontology/sourcecode#Package");
		pm.setPropertyname("http://hut.edu.vn/ontology/sourcecode#hasPackage");
		pm.setValue("http://hut.edu.vn/ontology/sourcecode#packpack");
		iData.getObjectPropertyList().add(pm);
		
		pm= new PropertyMapData();
		pm.setTypeClass("http://hut.edu.vn/ontology/sourcecode#Test");
		pm.setPropertyname("http://hut.edu.vn/ontology/sourcecode#hasTest");
		pm.setValue("http://hut.edu.vn/ontology/sourcecode#testee_____tet________________________________________");
		iData.getObjectPropertyList().add(pm);
		
		list.add(iData);
		
		delegate.saveValuesOfIndividual(null, list, false);
		
		System.out.println("List individual of Library");
		printList(delegate.listAllRelatedInstance(null, "http://hut.edu.vn/ontology/sourcecode#Package"));
		printList(delegate.listAllRelatedInstance(null, "http://hut.edu.vn/ontology/sourcecode#Library"));
		
		System.out.println("Get all property data");
		for (BkIndividualProperty bk: delegate.getValuesOfIndividual(null, "http://hut.edu.vn/ontology/sourcecode#hehehe"))
		{
			System.out.println(bk.getProperty());
			printList(bk.getListValue());
		}
		
	}
	
	public static void testQuery()
	{
		System.out.println("Query Testing");
		String query1 =
				"\nPREFIX sourcecode: <http://hut.edu.vn/ontology/sourcecode#>"+
				"\nPREFIX document: <http://hut.edu.vn/ontology/document#>"+
				"\nPREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
				"\nPREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "+
				"\nPREFIX fn: <http://www.w3.org/2005/xpath-functions#> "+
				"\nPREFIX owl: <http://www.w3.org/2002/07/owl#> "+
				"\nPREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"+
				"\nSELECT DISTINCT ?Method2 ?a"+
				"\nWHERE"+
				"\n{"+
				"\n?Method2 rdf:type <http://hut.edu.vn/ontology/sourcecode#Method>."+
				"\n?a rdf:type <http://hut.edu.vn/ontology/sourcecode#Class>."+
				"\n?Method1 rdf:type <http://hut.edu.vn/ontology/sourcecode#Method>."+
				"\n?a <http://hut.edu.vn/ontology/sourcecode#hasMethod> ?Method1."+
				"\n?Method1 <http://hut.edu.vn/ontology/sourcecode#hasName> ?x0 filter(fn:contains(fn:lower-case(?x0),'a'))."+
				"\n?Method1 <http://hut.edu.vn/ontology/sourcecode#usesMethod> ?Method2."+
				"\n?Method2 <http://hut.edu.vn/ontology/sourcecode#hasName> ?x1 filter(fn:contains(fn:lower-case(?x1),'a'))."+
				"\n}";
		String query2 = 
			"PREFIX sourcecode: <http://hut.edu.vn/ontology/sourcecode#>"+
			"\nPREFIX document: <http://hut.edu.vn/ontology/document#>"+
			"\nPREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "+
			"\nPREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+ 
			"\nPREFIX fn: <http://www.w3.org/2005/xpath-functions#> "+
			"\nPREFIX owl: <http://www.w3.org/2002/07/owl#> "+
			"\nPREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"+
			"\nSELECT DISTINCT ?a ?b "+
			"\nWHERE"+
			"\n{"+
			"\n?a rdf:type <http://hut.edu.vn/ontology/sourcecode#Class>."+
			"\n?b rdf:type <http://hut.edu.vn/ontology/sourcecode#Method>."+
			"\n?a <http://hut.edu.vn/ontology/sourcecode#hasMethod> ?b."+
			"\n?a <http://hut.edu.vn/ontology/sourcecode#hasName> ?x0 filter(fn:contains(fn:lower-case(?x0),'sharedata'))."+
			"\n}";
		
		String query=query1;
		System.out.println(query);
		long startTime = System.currentTimeMillis();
		for (ArrayListData ad: delegate.sparqlResultList(null, query))
		{
			System.out.println(ad.getData());
		}
		long endTime = System.currentTimeMillis();
		Date d = new Date(endTime-startTime);
		System.out.println((endTime-startTime));
		SimpleDateFormat  f = new SimpleDateFormat("HH:mm:ss");
		System.out.println("Time: " + f.format(d));
		System.out.println("End query testing");
	}
	
	public static void main(String args[])
	{
		WebServiceService test = new WebServiceService();
		WebServiceService service = new WebServiceService();
		delegate = service.getWebServicePort();

//		testIndividualOperator();
//		saveIndividualTest();
		testQuery();
	}
}
