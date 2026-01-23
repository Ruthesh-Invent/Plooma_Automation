package org.playwright.pages;

import org.framework.playwright.utils.BaseClass;
import org.testng.Assert;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class Patientlistpage extends BaseClass {

	public Patientlistpage(Page page) {
		super(page);
	}

	public void addpatient() {
		Locator addicon = getPage().locator("//mat-icon[text()=\"add\"]");
		addicon.click();
	}
	
	public void verfiyintakeformtitle() {
		Locator popupTitle =getPage().locator("//div[contains(@class,'font-weight-bold') and normalize-space()='Patient Intake']");
		Assert.assertTrue(popupTitle.isVisible(),"Patient Intake popup did not open after clicking Add icon");
	    System.out.println("Patient Intake popup opened successfully");
	}
	
	//input[@formcontrolname='FirstName']
	//span[text()=' Continue Demographics ']
	
	public void fillInputByLabel(String labelName, String value) {
	    String xpath = "//input[@formcontrolname='" + labelName + "']";
	    Locator inputField = getPage().locator(xpath);
	    inputField.waitFor();
	    inputField.fill(value);
	}
	
	public void continuedemograph() {
		Locator demographics = getPage().locator("//span[text()=' Continue Demographics ']");
				demographics.click();
	}
	
	public void selectMatAutocompleteByLabel(String label, String value) {
	    Locator input = getPage().locator("//mat-label[normalize-space()='" + label + "']" +"/ancestor::mat-form-field//input" ).first();
	    input.waitFor();
	    input.click();
	    Locator option = getPage().locator("//mat-option//span[normalize-space()='" + value + "']");
	    option.waitFor();
	    option.click();
	}
	
public void clicknxt() {
	Locator nxtbtn = getPage().locator("//button[@type='submit' and contains(@class,'float-right') and .//span[normalize-space()='Next']]").first();
	nxtbtn.click();
}

public void emergnxtbtn() {
	Locator btn = getPage().locator("(//div[contains(@class,'align-self-center')]//button[@type='submit' and .//span[normalize-space()='Next']])[2]");
	btn.click();
}

public void emrgencycontactname(String labelName, String value) {
	 String xpath = "//input[@name='" + labelName + "']";
	    Locator inputField = getPage().locator(xpath);
	    inputField.waitFor();
	    inputField.fill(value);
	}

public void address(String labelName, String value) {	
	String xpath="(//mat-form-field[.//mat-label[normalize-space()='" + labelName + "']]//input)[1]";
	Locator inputfield = getPage().locator(xpath);
	inputfield.waitFor();
	inputfield.fill(value);
}
	
public void selectemergencycontact() {
	
	Locator radiobtn = getPage().locator("(//input[@type='radio' and @name='responsibleParty'])[2]");
	radiobtn.click();
}

public void selectinsurance() {	
	Locator radiobtn = getPage().locator("(//input[@type='radio' and @name='methodOfPayment'])[2]");
	radiobtn.click();
}

public void fillinsuranceetails(String labelName ,String value) {
	  String xpath = "//mat-label[normalize-space(text())='" + labelName + "']";
	    Locator inputField = getPage().locator(xpath);
	    inputField.waitFor();
	    inputField.fill(value);
	}
	
public void fillinsuranceaddress(String labelName ,String value) {
	  String xpath = "(//mat-label[normalize-space(text())='" + labelName + "'])[3]";
	    Locator inputField = getPage().locator(xpath);
	    inputField.waitFor();
	    inputField.fill(value);
	}
public void saveinsurance() {
	Locator btn = getPage().locator("//span[normalize-space(text())='Save']");
	btn.click();
}

public void finnxt() {
	Locator btn = getPage().locator("(//button[normalize-space(.)='Next'])[3]");
	btn.click();
}

public void sumbit() {
	Locator btn = getPage().locator("//span[normalize-space(text())='Submit']");
	btn.click();
}

}


	

