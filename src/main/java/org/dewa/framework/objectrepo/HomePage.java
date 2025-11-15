package org.dewa.framework.objectrepo;

import org.dewa.framework.api_utility.ApiUtility;
import org.dewa.framework.utils.BaseClass;

import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HomePage extends BaseClass {

	public HomePage(Page page) {
		super(page);
	}

	public void clickSubscription(String subscriptionName) {
		//*[contains(@class,'subscription') and @title='VOCTest']
		Locator subsElement = getPage().locator("//*[contains(@class,'subscription') and @title='" + subscriptionName + "']");
		subsElement.click();
		extentTest.get().log(Status.INFO, "Clicked on "+subscriptionName+" Subscription");
	}

	public void searchProjectAndClick(String projectName) {
		boolean res=true;
		while(res) {
			if(getPage().locator("//div[contains(@class,'project-name') and contains(text(),'"+projectName+"')]").first().isVisible()) {
				getPage().locator("//div[contains(@class,'project-name') and contains(text(),'"+projectName+"')]").first().click();
				res=false;
			}
			else {
				getPage().locator("//button[@data-cy='load-more-docs']").click();
			}
		}
		extentTest.get().log(Status.INFO, "Clicked on "+projectName+" Project");
		
	}
	
	public void clickOnNavigationMenus(String navigationName) {
		Locator element = getPage().locator("//*[contains(@data-cy,'btn') and contains(text(),'"+navigationName+"')]").first();
		click(element, navigationName);
	}
	
	public void clickOnHomeButton() {
		getPage().waitForTimeout(1000);
		if(getPage().locator("//app-bot-sidebar[contains(@class,'block')]").isVisible()) {
			getPage().locator("//app-bot-sidebar[contains(@class,'block')]").hover();
			getPage().waitForTimeout(2000);
			System.out.println("Hovered on Side Bar");
			Locator element = getPage().locator("//app-bot-sidebar/div[1]/div[2]//*[contains(@mattooltip,'Home')]");
			element.click();
			System.out.println("Clicked on home page");
		}
	}
	
	public void clickOnAddNewProject() {
		getPage().locator("//button[@iconname='add']").click();
		extentTest.get().log(Status.INFO, "Clicked on Add New Project");
	}
	
	public void enterProjectName_Description(String projectName, String description) {
		//mat-label[text()='Project Name']
		getPage().locator("//mat-label[text()='Project Name']").fill(projectName);
		getPage().locator("//mat-label[text()='Description']").fill(description);
		getPage().locator("//button//div[contains(text(),'Create')]").click();
		extentTest.get().log(Status.INFO, "Project Name : <b>"+projectName+"</b>");
		extentTest.get().log(Status.INFO, "Description : <b>"+description+"</b>");
		waitForSuccessMessageToAppearAndDissapear();
		getPage().reload();
	}
	
	public boolean checkProjectNameAvailability(String expectedProjectName) {
		//div[contains(@class,'project-name') and contains(text(),'Voice of Customer')]
		boolean res=false;
		while(true) {
			if(getPage().locator("//div[contains(@class,'project-name') and contains(text(),'"+expectedProjectName+"')]").isVisible()) {
				res=true;
				break;
			}
			else if(getPage().locator("//button[@data-cy='load-more-docs']").isVisible()) {
				getPage().locator("//button[@data-cy='load-more-docs']").click();
			}
		}
		return res;
//		boolean res=true;
//		while(res) {
//			if(getPage().locator("//button[@data-cy='load-more-docs']").isVisible()) {
//				getPage().locator("//button[@data-cy='load-more-docs']").click();
//			}
//			else {
//				res=false;
//			}
//		}
//		List<String> projects=new ArrayList<String>();
//		List<Locator> allProjectElements = getPage().locator("//div[contains(@class,'project-name')]").all();
//		for(Locator eachProjectElement:allProjectElements) {
//			String projectName=eachProjectElement.textContent().trim();
//			projects.add(projectName);
//		}
//		return projects.contains(expectedProjectName);
	}
	
	public void createNewProject(String subsName, String projectName, String discription) {
		clickSubscription(subsName);
		clickOnAddNewProject();
		enterProjectName_Description(projectName, discription);
	}
	


}
