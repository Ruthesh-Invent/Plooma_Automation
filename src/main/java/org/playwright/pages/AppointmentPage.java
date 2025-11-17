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
	
	public void clickyes() {
	    String xpath = "//a[contains(text(), 'Yes')]";
	    Locator element = getPage().locator(xpath);
	    element.click();
	}
	
    public int getTotalAppointmentCount() {

        Locator pagination = getPage().locator("li.page-item.m-r-5.ng-star-inserted").first();
        String text = pagination.textContent().trim();  
        String total = text.split("of")[1].trim();
        return Integer.parseInt(total);
    }


}