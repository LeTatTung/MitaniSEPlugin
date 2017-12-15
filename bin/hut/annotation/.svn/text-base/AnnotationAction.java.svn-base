package hut.annotation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mintani.valueconst.ConsistentOntology;
import mintani.valueconst.ConstValue;

import service.Service;
import tool.parse.doc.ToolParseDoc;
import ws.data.ArrayListData;
import ws.data.DataServiceDelegate;
import ws.data.InstanceData;

public class AnnotationAction {
	private DataServiceDelegate delegate;
	public List<ws.data.InstanceData> listofAnnotation = new ArrayList<ws.data.InstanceData>() ;
	
	/**
	 * 
	 */
	public AnnotationAction() {
		delegate = Service.dataServiceDelegate;
	}
	
	public void saveAnnotationImage(List<List> imageList) {
		List listItems;
		String content;
		for (int loopIndex = 0; loopIndex < imageList.size(); loopIndex++) {
			listItems = imageList.get(loopIndex);
			ToolParseDoc toolParse = new ToolParseDoc();
			content = toolParse.convertPlainText(listItems.get(1).toString());
			
			
			ArrayList<String> listsubContent = toolParse.getContentFont(listItems.get(1).toString(), "Courier");
			ws.data.InstanceData imageInstance = new ws.data.InstanceData();
			imageInstance.setClassName("Image");
			imageInstance.setInstanceID(listItems.get(4).toString());
			imageInstance.setInstanceLabel(listItems.get(5).toString());
			InnitInstance initImageInstance = new InnitInstance(imageInstance);
			initImageInstance.addDataProperty(ConsistentOntology.HAS_TEXT_CONTENT, content);
			
			//Thoi diem tao annotation gan vao cho annotation
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//'T'hh:mm:ss"); // yyyy/MM/dd HH:mm:ss
	        Date date = new Date();        
	        initImageInstance.addDataProperty(ConsistentOntology.CREATED_AT, date.toString());
			
			
			listofAnnotation.add(initImageInstance.getPackageField());
			
			
		}
		
		delegate.saveAnnotationClient(null, listofAnnotation);
	}
	
	public void saveAnnotationManual(List<List> imageList,int type,String currentPathFile) {
		List listItems;
		String content,sourceId,sourceType;
		String itemId,itemLabel,page;
		
		listofAnnotation = new ArrayList<ws.data.InstanceData>() ;		
		for (int loopIndex = 0; loopIndex < imageList.size(); loopIndex++) {
			listItems = imageList.get(loopIndex);
			ToolParseDoc toolParse = new ToolParseDoc();
			
			content 	= toolParse.convertPlainText(listItems.get(0).toString());
			itemId  	= listItems.get(2).toString();
			itemLabel	= listItems.get(1).toString();
			page        = listItems.get(3).toString();
			sourceId  	= listItems.get(4).toString();
			sourceType = listItems.get(5).toString();
			
			
			//ArrayList<String> listsubContent = toolParse.getContentFont(listItems.get(1).toString(), "CourierNewPSMT");
			ws.data.InstanceData annotationInstance = new ws.data.InstanceData();
			if(type==ConstValue.IMAGE){
				annotationInstance.setClassName(ConsistentOntology.DOC_NAMESPACE+"Image");
			}
			if(type==ConstValue.PARAGRAPH){
				annotationInstance.setClassName(ConsistentOntology.DOC_NAMESPACE+"Paragraph");
			}
			if(type==ConstValue.SECTION){
				annotationInstance.setClassName(ConsistentOntology.DOC_NAMESPACE+"Section");
			}
			annotationInstance.setInstanceID(ConsistentOntology.DOC_NAMESPACE+itemId);
			annotationInstance.setInstanceLabel(itemLabel);
			InnitInstance initAnnotationInstance = new InnitInstance(annotationInstance);
			initAnnotationInstance.addDataProperty(ConsistentOntology.HAS_TEXT_CONTENT, content);
			initAnnotationInstance.addDataProperty(ConsistentOntology.IN_PAGE, page);
			
			/*initAnnotationInstance.addDataProperty("hasRelationSource", sourceId);
			initAnnotationInstance.addDataProperty("hasRelationType", sourceType);
			*/
			initAnnotationInstance.addObjectProperty(ConsistentOntology.HAS_RELATION_SOURCE, sourceId, ConsistentOntology.SEC_NAMESPACE+"SoftwareComponent");
			//Create at ( dataproperty)
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");//'T'hh:mm:ss"); // yyyy/MM/dd HH:mm:ss
	        Date date = new Date();        
	        initAnnotationInstance.addDataProperty(ConsistentOntology.CREATED_AT, dateFormat.format(date).toString());
			
			listofAnnotation.add(initAnnotationInstance.getPackageField());
			
			
		}
		
		delegate.saveAnnotationClient(null, listofAnnotation);
	}
	
	
	public void saveAnnotationWithList( List<InstanceData> lisAnnotation){
		try{
			delegate.saveAnnotationClient(null, lisAnnotation);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public void saveMatchSourceCode(List<List> dataList,int type){
		List listItems ;
		List<List> tmpDataList = new ArrayList<List>();
		String content,itemId,itemLabel,page,tmpContent;
		String currentPathFile="";
		for (int loopIndex = 0; loopIndex < dataList.size(); loopIndex++) {
			listItems = dataList.get(loopIndex);
	    //List<String> test = delegate.listClassInstance(ConsistentOntology.SEC_NAMESPACE+"Method");
			ToolParseDoc toolParse = new ToolParseDoc();
			
			content 	= toolParse.convertPlainText(listItems.get(1).toString());
			itemId  	= listItems.get(4).toString();
			itemLabel	= listItems.get(5).toString();
			page        = listItems.get(2).toString();
		
			creatListData("Class", tmpDataList, content, itemId, itemLabel, page);
			creatListData("Method", tmpDataList, content, itemId, itemLabel, page);
			creatListData("Field", tmpDataList, content, itemId, itemLabel, page);
			
			
			
			
			if(tmpDataList !=null){
				saveAnnotationManual(tmpDataList,type,currentPathFile);
			}
		//B1 Lay instance cua class ve
		//So khop
		//B2 Lay instance cua method ve
		//So khop
		//B3 .Lay instance cua field ve
		//So khop
		
	    System.out.println(builSparql("Class"));
		}	
	}
	
	public void creatListData(String typeSource,List<List> tmpDataList,String content,String itemId,String itemLabel,String page){
		String strTmp="";
		List<ArrayListData> listGetInstance = delegate.sparqlResultList(null, builSparql(typeSource));	
		for (int i = 1; i < listGetInstance.size(); i++) {
			List<String> items = listGetInstance.get(i).getData();	
			
			String labelId = items.get(1).toString();
			String sourceId = items.get(0).toString();
			if(sourceId.contains("/")){
				strTmp = sourceId.substring(sourceId.lastIndexOf("/")+1);
				
			}else{
				strTmp = sourceId.substring(sourceId.lastIndexOf(".")+1);
			}
			if((content.contains(labelId))||(content.contains(strTmp))){
				ArrayList<String> listOfData = new ArrayList<String>();
				listOfData.add(content);
				listOfData.add(itemLabel);
				listOfData.add(itemId);
				listOfData.add(page);
				listOfData.add(ConsistentOntology.SEC_NAMESPACE+sourceId);
				listOfData.add(typeSource);					
				tmpDataList.add(listOfData);
			}
			
		}
	}
	
	/**
	 * @param nameClass: ten cua concept trong ontology
	 * @return
	 */
	public String builSparql(String nameClass){
		
		StringBuffer query = new StringBuffer(ConsistentOntology.prefix)
		.append("\nSELECT ?X ?label ")
		.append("\nWHERE")
		.append("{\n")
		.append("\n?X SEC:hasDirectType '"+ nameClass+"' .")
		.append("\n?X rdfs:label ?label .")		
		.append("\n}");
		return query.toString();
	}
	
	public List<ArrayListData> runQuery(String query){
		
		List<ArrayListData> listInstance = delegate.sparqlResultList(null, query);	
		return listInstance;
	}
	
	
	
	
	
	
}
