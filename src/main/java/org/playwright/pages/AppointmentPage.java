package org.playwright.pages;

import org.framework.playwright.utils.BaseClass;

import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class AppointmentPage extends BaseClass {

	public AppointmentPage(Page page) {
		super(page);
	}

	public void clickMainModule(String moduleName) {
	    String xpath = "//span[normalize-space()='" + moduleName + "']/parent::a";
	    Locator moduleElement = getPage().locator(xpath);
	    click(moduleElement, moduleName);
	}
	
	public void clickSubmodule(String subModuleName){
		
		String xpath = "//a[normalize-space(text())='" + subModuleName + "']";
		Locator submoduleElement = getPage().locator(xpath);
		click(submoduleElement, subModuleName);	
		 System.out.println("Clicked on the subModuleName ");
	}
	
	public void clickAddapointment() {
	    String xpath = "//mat-icon[normalize-space()='add']";
	    Locator element = getPage().locator(xpath);
	    element.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
	    element.click();
	    System.out.println("Clicked the add icon");
	}
	
	public  void selectDropdownByFieldNameAndValue(String fieldName, String value) {
		Locator dropdown = getPage().locator("//mat-label[normalize-space(text())='" + fieldName + "']/ancestor::div[contains(@class,'mat-form-field-infix')]/input[@role='combobox']");
	    click(dropdown, fieldName);
	    Locator optionToSelect = getPage().locator("//mat-option//span[normalize-space(text())='" + value + "']");
	    click(optionToSelect,value);
	    getPage().waitForTimeout(500);   
	    extentTest.get().log(Status.INFO, "Selected value '" + value + "' for dropdown field '" + fieldName + "'");
	}
	
	public void clicksave() {
	    String xpath = "//span[normalize-space()='Save']";
	    Locator element = getPage().locator(xpath);
	    element.click();
	}
	
//	public void clickYes() {
//	    Locator yesButton = getPage().locator("//a[normalize-space()='Yes' and contains(@class, 'mat-primary')]");
//	    yesButton.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(10000));
//	    yesButton.click();
//	    System.out.println("Clicked on 'Yes' button. Waiting for 5 seconds...");
//	    try { Thread.sleep(5000); } catch (InterruptedException e) { e.printStackTrace(); }
//	}


    public int getTotalAppointmentCount() {

        Locator pagination = getPage().locator("li.page-item.m-r-5.ng-star-inserted").first();
        String text = pagination.textContent().trim();  
        String total = text.split("of")[1].trim();
        return Integer.parseInt(total);
    }

    public void saveAppointmentHandleAllPopups() {
        getPage().locator("//span[normalize-space()='Save']").click();
        System.out.println("Clicked Save button.");
        sleepSeconds(5); 
        Locator conflictYesButton = getPage().locator("//a[normalize-space()='Yes' and contains(@class, 'mat-primary')]");
        Locator beyondOfficeHoursYesButton = getPage().locator(
                "//div[normalize-space(text())='Warning Alert']//following::a[normalize-space()='Yes' and contains(@class,'mat-primary')][1]");
        boolean popupHandled;
        do {
            popupHandled = false;
                if (conflictYesButton.isVisible(new Locator.IsVisibleOptions().setTimeout(1000))) {
                System.out.println("Conflict popup detected. Waiting 5 seconds before clicking 'Yes'...");
                sleepSeconds(5); 
                conflictYesButton.click();
                System.out.println("Clicked Yes on conflict popup.");
                popupHandled = true;
                sleepSeconds(5); 
            }

            if (beyondOfficeHoursYesButton.isVisible(new Locator.IsVisibleOptions().setTimeout(1000))) {
                System.out.println("Beyond office hours popup detected. Waiting 5 seconds before clicking 'Yes'...");
                sleepSeconds(5); 
                beyondOfficeHoursYesButton.click();
                System.out.println("Clicked Yes on beyond office hours popup.");
                popupHandled = true;
                sleepSeconds(5); 
            }

        } while (popupHandled);
        sleepSeconds(5);
        System.out.println("All popups handled. Appointment saved successfully.");
    }
    
    private void sleepSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}