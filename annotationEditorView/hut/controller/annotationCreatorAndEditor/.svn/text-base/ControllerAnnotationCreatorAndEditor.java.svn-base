package hut.controller.annotationCreatorAndEditor;

import hut.composite.annotationCreatorAndEditor.CompositeClassList;
import hut.composite.annotationCreatorAndEditor.CompositeListInstanceOfClass;
import hut.composite.annotationCreatorAndEditor.CompositeListPropertiesOfInstance;
import hut.composite.annotationCreatorAndEditor.CompositeLeftPopUp;
import hut.composite.annotationCreatorAndEditor.CompositePopUp;
import hut.composite.annotationCreatorAndEditor.CompositeRow;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class ControllerAnnotationCreatorAndEditor{

	private CompositeClassList compositeClassList ;
	private CompositeListInstanceOfClass compositeInstanceListOfClass;
	private CompositeListPropertiesOfInstance compositeInstancePropertiesList;
	private CompositeRow compositeRow;
	private CompositeLeftPopUp compositeLeftPopUp;
	private CompositePopUp compositePopUp;

	

	

	// constructor method
	public  ControllerAnnotationCreatorAndEditor(){
		
	}
	public  ControllerAnnotationCreatorAndEditor(CompositeClassList ccl, CompositeListInstanceOfClass cilc, CompositeListPropertiesOfInstance cipl){
		this.compositeClassList = ccl;
		this.compositeInstanceListOfClass = cilc;
		this.compositeInstancePropertiesList = cipl ; 
		
	}
	
	public  ControllerAnnotationCreatorAndEditor(CompositeLeftPopUp clpu, CompositeListInstanceOfClass cilc, CompositeListPropertiesOfInstance cipl){
		this.compositeLeftPopUp = clpu;
		this.compositeInstanceListOfClass = cilc;
		this.compositeInstancePropertiesList = cipl ; 
		
	}
	
	//-----------------GET & SET method------------------------------------------
	public CompositePopUp getCompositePopUp() {
		return compositePopUp;
	}

	public void setCompositePopUp(CompositePopUp compositePopUp) {
		this.compositePopUp = compositePopUp;
	}
	public CompositeLeftPopUp getCompositeLeftPopUp() {
		return compositeLeftPopUp;
	}
	public void setCompositeLeftPopUp(CompositeLeftPopUp compositeLeftPopUp) {
		this.compositeLeftPopUp = compositeLeftPopUp;
	}
	public CompositeClassList getCompositeClassList() {
		return compositeClassList;
	}
	public void setCompositeClassList(CompositeClassList compositeClassList) {
		this.compositeClassList = compositeClassList;
	}
	public CompositeListInstanceOfClass getCompositeInstanceListOfClass() {
		return compositeInstanceListOfClass;
	}
	public void setCompositeInstanceListOfClass(
			CompositeListInstanceOfClass compositeInstanceListOfClass) {
		this.compositeInstanceListOfClass = compositeInstanceListOfClass;
	}
	public CompositeListPropertiesOfInstance getCompositeInstancePropertiesList() {
		return compositeInstancePropertiesList;
	}
	public void setCompositeInstancePropertiesList(
			CompositeListPropertiesOfInstance compositeInstancePropertiesList) {
		this.compositeInstancePropertiesList = compositeInstancePropertiesList;
	}
	public CompositeRow getCompositeRow() {
		return compositeRow;
	}
	public void setCompositeRow(CompositeRow compositeRow) {
		this.compositeRow = compositeRow;
	}
	//-----------------------------------------------------------------------------
	public void updateChosenClass(){
		
		if(this.getCompositeClassList() == null ){
			this.getCompositeInstanceListOfClass().setInputData(this.getCompositeLeftPopUp().getOutputData());
		}
		else
		{
			this.getCompositeInstanceListOfClass().setInputData(this.getCompositeClassList().getOutputData());
		}
		this.getCompositeInstanceListOfClass().updateInterface();
		//this.getCompositeInstanceListOfClass().addInstanceToTable();
	}
	
	public void updateChosenInstance(){
		this.getCompositeInstancePropertiesList().setInputData(this.getCompositeInstanceListOfClass().getOutputData());
		this.getCompositeInstancePropertiesList().updateInterface();
		// 1. lay instanceFullName cua Instance nay` roi thuc hien nhet du lieu vao CompositeInstancePropertiesList
		
		// 2. xu ly phan PopUp hien ra nua 
//		this.getCompositePopUp().setOutputData(this.getCompositeInstanceListOfClass().getOutputData());
		
	}
	public void updateForPopUp(){
		
		// Chua chay ra sau ham nay duoc la vi Controller chua nhan duoc CompositePopUp ! => dang le la no phai nhan duoc roi chu :-/
		if(this.getCompositePopUp()==null){
			System.out.println("khong gan duoc ><");
		}
		else
			System.out.println("Gan duoc ><");
		this.getCompositePopUp().setOutputData(this.getCompositeInstanceListOfClass().getOutputData());
		System.out.println("chay ra sau setOutputData");
		// 1. this.getCompositePopUp().setOutputData(this.getCompositeListInstance().getOutputData());
	}

//	public void updateChosenClassFromPopUp(){
//		this.getCompositeInstanceListOfClass().setInputData(this.getCompositeLeftPopUp().getOutputData());
//		this.getCompositeInstanceListOfClass().updateInterface();
//	}
}
