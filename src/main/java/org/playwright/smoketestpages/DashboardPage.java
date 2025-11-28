package org.playwright.smoketestpages;

import org.framework.playwright.utils.BaseClass;

import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class DashboardPage extends BaseClass{

	public DashboardPage(Page page) {
		super(page);
		// TODO Auto-generated constructor stub
	}
	
	public void clickMainModule(String moduleName) {
	    // XPath: //span[normalize-space()='Appointments']/parent::a
	    String xpath = "//span[normalize-space()='" + moduleName + "']/parent::a";
	    Locator moduleElement = getPage().locator(xpath);
	    click(moduleElement, moduleName);
	    extentTest.get().log(Status.INFO, "Clicked on main module '" + moduleName + "'");
	    
	}
	
	public void clickSubmodule(String subModuleName){
		
		String xpath = "//a[normalize-space(text())='" + subModuleName + "']";
		Locator submoduleElement = getPage().locator(xpath);
		click(submoduleElement, subModuleName);
		 extentTest.get().log(Status.INFO, "Clicked on submodule '" + subModuleName + "'");
	}
	
	
	public int getTileCount(String tileName) {
	    String xpath = "(//p[normalize-space()='" + tileName + "']/preceding-sibling::p[contains(@class,'stats-value')])[1]";
	    Locator countElement = getPage().locator(xpath);
	    countElement.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
	    String countText = countElement.innerText().trim();
	    int count = Integer.parseInt(countText);

	    // Print the count on console
	    System.out.println("Tile '" + tileName + "' count: " + count);
	    waitForLoadingToFinish();
	    return count;
	}

	 public void navigateToTile(String tileName) {
	        String xpath = "(//div[p[normalize-space()='" + tileName + "']])[1]";
	        Locator tileElement = getPage().locator(xpath);

	        // Wait until the tile is visible and clickable
	        tileElement.waitFor(new Locator.WaitForOptions().setState(com.microsoft.playwright.options.WaitForSelectorState.VISIBLE));

	        // Scroll into view if needed and click
	        tileElement.scrollIntoViewIfNeeded();
	        tileElement.click();
	        waitForLoadingToFinish();
	        extentTest.get().log(Status.INFO, "Navigated to tile '" + tileName + "'");
	        System.out.println("Navigated to tile: " + tileName);
	    }
	 
	 public void enterPatientName(String patientName) {
		    // Locator for the patient search input
		    Locator searchElement = getPage().locator("//input[@placeholder='Search']");

		    // Wait for the input to be visible
		    searchElement.waitFor(new Locator.WaitForOptions()
		            .setState(WaitForSelectorState.VISIBLE)
		            .setTimeout(10000));

		    // Clear existing text
		    searchElement.click();             // Focus
		    searchElement.press("Control+A");  // Select all
		    searchElement.press("Backspace");  // Delete

		    // Enter the patient name
		    searchElement.fill(patientName);
		}
	 
	 public void searchPatientNameAndClickEdit(String patientName) {
		    // Locator for the patient search input
		    Locator searchElement = getPage().locator("//input[@placeholder='Search']");

		    // Wait for the input to be visible
		    searchElement.waitFor(new Locator.WaitForOptions()
		            .setState(WaitForSelectorState.VISIBLE)
		            .setTimeout(10000));

		    // Enter the patient name
		    searchElement.fill(patientName);
		    getPage().waitForTimeout(3000);
		    searchElement.press("Enter"); // Optional, if Enter triggers search

		    // Wait for search results
		    waitForLoadingToFinish(); // Ensure global loader has disappeared
		    getPage().waitForTimeout(2000); // Optional: give results time to appear

		    // Locate all Edit buttons in the results
		    Locator editButtons = getPage().locator("//button[@smattooltip='Edit']");
		    int count = editButtons.count();

		    if (count > 0) {
		        System.out.println("Found " + count + " Edit buttons, clicking the first one.");
		        editButtons.nth(0).click(); // Click the first element
		    } else {
		        System.out.println("No Edit buttons found for patient: " + patientName);
		    }

		    waitForLoadingToFinish(); // Wait again if loader appears after click

		    // Clear the search box after editing
		    searchElement.fill("");
		    System.out.println("Search box cleared after updating status.");
		}


		 public void changestatus(String dropdownId, String valueToSelect) {
			    // Click the dropdown
			    Locator dropdown = getPage().locator("//mat-select[@id='" + dropdownId + "']");
			    dropdown.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(5000));
			    dropdown.click();

			    // Select the option
			    String optionXPath = "//span[normalize-space()='" + valueToSelect + "']";
			    Locator option = getPage().locator(optionXPath);
			    option.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(5000));
			    option.click();
			    getPage().waitForTimeout(2000);
			}

		 public void searchPatientNameAndClicknote(String patientName) {

			    Locator searchElement = getPage().locator("//input[@placeholder='Search']");

			    searchElement.waitFor(new Locator.WaitForOptions()
			            .setState(WaitForSelectorState.VISIBLE)
			            .setTimeout(10000));

			    searchElement.fill(patientName);
			    extentTest.get().log(Status.INFO, "Entered patient name '" + patientName + "' in search box.");
			    getPage().waitForTimeout(2000);
			    searchElement.press("Enter");

			    waitForLoadingToFinish();
			    getPage().waitForTimeout(2000);

			    Locator editButtons = getPage().locator("//button[@mattooltip='Notes']");
			    int count = editButtons.count();

			    if (count > 0) {
			        System.out.println("Found " + count + " Notes buttons, clicking the first one.");

			        // ⭐ Click Notes
			        editButtons.nth(0).click();

			        // ⭐ Wait for new page
			        getPage().waitForLoadState();
			        extentTest.get().log(Status.INFO,
			                "Clicked the Notes button for patient '" + patientName + "' and waited for navigation.");
			    } else {
			        System.out.println("No Notes buttons found for patient: " + patientName);
			    }

			    System.out.println("Search box cleared successfully.");
			}

	 public void selectappointmentpocdropdown(String label, String Value) {
		 
		 String xpath = "//mat-select[contains(@class,'mat-select')]//span[contains(@class,'mat-select-value-text')]//span[normalize-space()='" + label + "']";
		 Locator dropdownElement = getPage().locator(xpath);
		 dropdownElement.click();
		 
		 String Option = "//span[normalize-space()='" + Value + "']";
		 Locator Element= getPage().locator(Option);
		 Element.click();	 
	 }
	 
//	 public void selectpoctype(String label, String PocName) {
//		 String dropdownelement= "//mat-label[normalize-space(text())='" + label + "']";
//		 Locator Element = getPage().locator(dropdownelement);
//		 Element.waitFor();
//		 Element.click();
//		 
//		 String POcelement = "//mat-option[.//span[normalize-space(text())='" + PocName + "']]";
//		 Locator POC = getPage().locator(POcelement);
//		
//		 POC.click();
//	 
//		 
//	 }
	 
	 public void selectpoctype(String label, String pocName) {

		    // Click the dropdown trigger (NOT the label)
		    String dropdownelement =
		            "//mat-label[normalize-space(text())='" + label + "']" +
		            "/ancestor::mat-form-field//div[contains(@class,'mat-select-trigger')]";

		    Locator dropdown = getPage().locator(dropdownelement);

		    dropdown.waitFor(new Locator.WaitForOptions()
		            .setState(WaitForSelectorState.VISIBLE));

		    dropdown.click();  // Opens Angular Material dropdown


		    // Wait and click the option
		    String optionXpath =
		            "//mat-option//span[normalize-space(text())='" + pocName + "']";

		    Locator pocOption = getPage().locator(optionXpath);

		    pocOption.waitFor(new Locator.WaitForOptions()
		            .setState(WaitForSelectorState.VISIBLE));

		    pocOption.click();
		}

	 
	 public void selectgoalandobj() {
		 Locator checkbox = getPage().locator("//div[@id='app-objective-0']//input[@type='checkbox']").first();
				checkbox.waitFor(new Locator.WaitForOptions()
				        .setState(WaitForSelectorState.VISIBLE));
				checkbox.click();

}
	 public void clicksavetocontinue() {
		 Locator btn = getPage().locator("//span[normalize-space(text())='Continue To Soap Note']");
		 btn.click();
		 		 
	 }
	 
	 public void enterFieldValue(String sectionName, String value) {
		    String xpath = "//strong[contains(text(),'" + sectionName + "')]"
		                 + "/ancestor::div[contains(@class,'header')]"
		                 + "/following-sibling::div//app-custom-editor//div[@class='editor']";

		    Locator field = getPage().locator(xpath);
		    field.waitFor();
		    field.fill("");
		    field.type(value);
		    getPage().waitForTimeout(2000);
		}
	 
	 public void clicksignandcontinue() {
		 Locator btn = getPage().locator("//span[contains(@class,'mat-button-wrapper')][contains(normalize-space(),'Sign and continue')]");
		btn.waitFor();
		 btn.click();
	 }

	 public void selectICDcode(String codename,String ICDName) {
		 Locator inputfield = getPage().locator(
				    "(//mat-label[contains(text(),'" + codename + "')]/following::input[@placeholder='Enter ICD - 10 name'])[1]");
		    inputfield.click();
		    String optionXPath = "//mat-option[.//span[contains(text(),'" + ICDName + "')]]";
		    Locator dropdown = getPage().locator(optionXPath);
		    dropdown.dblclick();
		}

	   public void clicksumbit() {
		   Locator btn = getPage().locator("//button[span[contains(text(),'Submit & Checkout')]]");
		   btn.click();
		   Locator yesBtn = getPage().locator("//button[span[contains(text(),'Yes')]]");
		    yesBtn.waitFor();
		    yesBtn.click();
		   
	   }
}





