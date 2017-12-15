package hut.DataProvider;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.texteditor.CaseAction;

import mintani.valueconst.ConsistentOntology;

import service.Service;

public class Provider {
	
	static List<String> requirements = new ArrayList<String>();
	static ArrayList<String> developerTeams= new ArrayList<String>();
	static ArrayList<String> testerTeams = new ArrayList<String>();
	
	public List<String> getRequirements() {
		return requirements;
	}

	public void setRequirements(List<String> requirements) {
		this.requirements = requirements;
	}

	public ArrayList<String> getDeveloperTeams() {
		return developerTeams;
	}

	public void setDeveloperTeams(ArrayList<String> developerTeams) {
		this.developerTeams = developerTeams;
	}

	public ArrayList<String> getTesterTeams() {
		return testerTeams;
	}

	public void setTesterTeams(ArrayList<String> testerTeams) {
		this.testerTeams = testerTeams;
	}

	public Provider() {
		setRequirements(new ArrayList<String>());
		setDeveloperTeams(new ArrayList<String>());
		setTesterTeams(new ArrayList<String>());
	}
	
	// Lay ve cac project team
	public List<String> getListProjectTeam(){
		List<String> result = null;
		try {
			result = Service.webServiceDelegate.listClassInstance(null, ConsistentOntology.PROJECT_TEAM);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<ArrayList<String>> getGeneralInformation(
			String projectSelectedId) {
		/*List<String> requirements = new ArrayList<String>();
		ArrayList<String> developerTeams= new ArrayList<String>();
		ArrayList<String> testerTeams = new ArrayList<String>();*/
		
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList<String> generalInfor = new ArrayList<String>();
		// 1. ID
		generalInfor.add(projectSelectedId);
		
		// 2. Name
		List<String> tmpName = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, projectSelectedId, ConsistentOntology.HAS_NAME);
		String projectName = null;
		if (tmpName.size()>0){
			projectName = tmpName.get(0);
		}
		generalInfor.add(projectName);
		
		// 3. Description
		List<String> tmpDescription = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, projectSelectedId, ConsistentOntology.HAS_DESCRIPTION);
		String description= null;
		if (tmpDescription.size()>0){
			description = tmpDescription.get(0);
		}
		generalInfor.add(description);
		
		List<String> tmpSubTeam = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, projectSelectedId, ConsistentOntology.INCLUDE_SUBTEAM);
		
		// 4. 5. DeveloperTeam, TesterTeam
		if (tmpSubTeam.size()>0)
		{
			for(int i=0; i<tmpSubTeam.size(); i++){
				String classId = Service.webServiceDelegate.getClassOfIndividual(null, tmpSubTeam.get(i));
				if (classId.equalsIgnoreCase(ConsistentOntology.DEVELOPER_TEAM)){
					developerTeams.add(tmpSubTeam.get(i));
				}
				else if (classId.equalsIgnoreCase(ConsistentOntology.TESTER_TEAM)){
					testerTeams.add(tmpSubTeam.get(i));
				}
			}
		}
			// so developerTeam
			generalInfor.add(String.valueOf(developerTeams.size()));
			
			// so testerTeam
			generalInfor.add(String.valueOf(testerTeams.size()));
			
		// 6. Requirement
			requirements = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, projectSelectedId, ConsistentOntology.PROJECT_TEAM_HAS_REQUIREMENT);
			generalInfor.add(String.valueOf(requirements.size()));
		
		// 7. Developers
			List<String> developers = new ArrayList<String>();
			for(int i=0; i<developerTeams.size(); i++){
				List<String> developersInThisTeam = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, developerTeams.get(i), ConsistentOntology.HAS_MEMBER);
				developers.addAll(developersInThisTeam);
			}
			generalInfor.add(String.valueOf(developers.size()));
			
		// 8. Testers
			List<String> testers = new ArrayList<String>();
			for(int i=0; i<testerTeams.size(); i++){
				List<String> testersInThisTeam = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, testerTeams.get(i), ConsistentOntology.HAS_MEMBER);
				testers.addAll(testersInThisTeam);
			}
			generalInfor.add(String.valueOf(testers.size()));
			
		result.add(generalInfor);	
		return result;
	}

	public ArrayList<ArrayList<String>> getrequirementsInformation(
			String projectSelectedId) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		
		for (int i=0; i<requirements.size(); i++){
			ArrayList<String> row = new ArrayList<String>();
			String requirementID = requirements.get(i);
			//1. Id cua requirement
			row.add(requirementID);
			
			//2. Described o document nao
			List<String> listDocumentId = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, requirementID, ConsistentOntology.REQUIREMENT_DESCRIBED_IN_DOC);
			String documentId = "";
			if (listDocumentId.size()>0){
				documentId = listDocumentId.get(0);
			}
			row.add(documentId.substring(ConsistentOntology.DOC_NAMESPACE.length()));
			
			//3. Code by developer nao
			List<String> listDeveloper = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, requirementID, ConsistentOntology.REQUIREMENT_CODE_BY_DEVELOPER);
			String codeBy = "";
			if (listDeveloper.size()>0){
			for (int j=0; j< listDeveloper.size();j++){
				codeBy+= listDeveloper.get(j).substring(ConsistentOntology.DOC_NAMESPACE.length())+" | ";
			}
			}
			row.add(codeBy);
			
			// 4. Current status
			List<String> isComplete = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, requirementID, ConsistentOntology.REQUIREMENT_IS_INSTALLED);
			String status = null;
			if (isComplete.size()>0){
				if (Boolean.valueOf(isComplete.get(0)) == true){
					status = "Completed";
				}
				else if (Boolean.valueOf(isComplete.get(0)) == false){
					status = "being installed";
				}
				else {
					status = "not installed";
				}
			}
			row.add(status);
			
			// 5. Installed in class nao
			List<String> listClassId = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, requirementID, ConsistentOntology.REQUIREMENT_IS_INSTALLED_IN_CODE);
			String listClass = "";
			int count=0;
			if(listClassId.size()>0){
				for(int j = 0; j<listClassId.size();j++){
					listClass +=listClassId.get(j) + " | ";
					count++;
				}
			}
			//listClass += count + " class: "+listClass;
			row.add(listClass);
			
			result.add(row);
		}
		return result;
	}

	public ArrayList<ArrayList<String>> getDevelopersInformation(
			String projectSelectedId) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		List<String> listDevelopersId = new ArrayList<String>();
		if (developerTeams.size()>0){
			for (int i=0; i<developerTeams.size(); i++){
				List<String> listDevelopersInThisTeam = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, developerTeams.get(i), ConsistentOntology.HAS_MEMBER);
				listDevelopersId.addAll(listDevelopersInThisTeam);
			}
		}
		
		for(int i=0; i<listDevelopersId.size(); i++){
			ArrayList<String> thisRow = new ArrayList<String>();
			//1. Id
			thisRow.add(listDevelopersId.get(i));
			
			//2. Name
			List<String> tmpFullName = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, listDevelopersId.get(i), ConsistentOntology.HAS_FULL_NAME);
			String fullName=null;
			if (tmpFullName.size()>0){
				 fullName = tmpFullName.get(0);
			}
			else {  // Thay Full Name boi Name neu khong co FullName
				tmpFullName = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, listDevelopersId.get(i), ConsistentOntology.HAS_NAME);
				if (tmpFullName.size()>0){
					 fullName = tmpFullName.get(0);
				}
				
			}
			thisRow.add(fullName);
			
			// 3. co bao nhieu requirement
			List<String> listRequirement = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, listDevelopersId.get(i), ConsistentOntology.DEVELOPER_CODE_REQUIREMENT);
			thisRow.add(String.valueOf(listRequirement.size()));
			
			// 4. 5. 6. so da duoc cai, so dang cai dat va so chua cai dat
			int isCompleted=0, isInstalling=0, notInstall=0;
			if (listRequirement.size()>0) {
				for(int j=0; j<listRequirement.size(); j++){
					String requirementID = listRequirement.get(j);
					List<String> tmpStatus = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, requirementID, ConsistentOntology.REQUIREMENT_IS_INSTALLED);
					if (tmpStatus.size()>0) {
						if (Boolean.valueOf(tmpStatus.get(0))==true){
							isCompleted++;
						}
						else if (Boolean.valueOf(tmpStatus.get(0))==false){
							isInstalling++;
						}
						else {
							notInstall++;
						}
					}
					else notInstall++;
				}
			}
			thisRow.add(String.valueOf(isCompleted));
			thisRow.add(String.valueOf(isInstalling));
			thisRow.add(String.valueOf(notInstall));

			result.add(thisRow);
		}
		
		return result;
	}

	public ArrayList<ArrayList<String>> getTestersInformation(
			String projectSelectedId) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		List<String> listTestersId = new ArrayList<String>();
		if (testerTeams.size()>0){
			for (int i=0; i<testerTeams.size(); i++){
				List<String> listDevelopersInThisTeam = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, testerTeams.get(i), ConsistentOntology.HAS_MEMBER);
				listTestersId.addAll(listDevelopersInThisTeam);
			}
		}
		
		for(int i=0; i<listTestersId.size(); i++){
			ArrayList<String> thisRow = new ArrayList<String>();
			//1. Id
			thisRow.add(listTestersId.get(i));
			
			//2. Name
			List<String> tmpFullName = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, listTestersId.get(i), ConsistentOntology.HAS_FULL_NAME);
			String fullName=null;
			if (tmpFullName.size()>0){
				 fullName = tmpFullName.get(0);
			}
			else {  // Thay Full Name boi Name neu khong co FullName
				tmpFullName = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, listTestersId.get(i), ConsistentOntology.HAS_NAME);
				if (tmpFullName.size()>0){
					 fullName = tmpFullName.get(0);
				}
				
			}	
			thisRow.add(fullName);
			
			// 3. co bao nhieu test
			//List<String> listTest = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, listTestersId.get(i), ConsistentOntology.TEST_IS_EXECUTE_BY_TESTER);
			List<String> listTest = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, listTestersId.get(i), ConsistentOntology.TESTER_EXECUTE_TEST);
			thisRow.add(String.valueOf(listTest.size()));
			
			// 4. 5. 6. so da duoc cai, so dang cai dat va so chua cai dat
			int isCompleted=0, isInstalling=0, notInstall=0;
			if (listTest.size()>0) {
				for(int j=0; j<listTest.size(); j++){
					String testID = listTest.get(j);
					List<String> tmpStatus = Service.webServiceDelegate.getValueOfSpecificPropertyForIndividual(null, testID, ConsistentOntology.TEST_IS_SUCCESSFUL);
					if (tmpStatus.size()>0) {
						if (Boolean.valueOf(tmpStatus.get(0))==true){
							isCompleted++;
						}
						else if (Boolean.valueOf(tmpStatus.get(0))==false){
							isInstalling++;
						}
						else {
							notInstall++;
						}
					}
					else notInstall++;
				}
			}
			thisRow.add(String.valueOf(isCompleted));
			thisRow.add(String.valueOf(isInstalling));
			thisRow.add(String.valueOf(notInstall));

			result.add(thisRow);
		}
		
		return result;
	}
}
