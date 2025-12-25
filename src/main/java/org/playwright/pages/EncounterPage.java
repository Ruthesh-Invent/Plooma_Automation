package org.playwright.pages;

import java.util.Map;

import org.framework.playwright.utils.BaseClass;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class EncounterPage extends BaseClass {

	public EncounterPage(Page page) {
		super(page);
	}

	 public void searchPatientNameAndClickassign(String patientName) {

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

		    Locator editButtons = getPage().locator("//button[@mattooltip=\"Assign\"]");
		    int count = editButtons.count();

		    if (count > 0) {
		        System.out.println("Found " + count + " Notes buttons, clicking the first one.");
		        editButtons.nth(0).click();
		        getPage().waitForLoadState();
		        extentTest.get().log(Status.INFO,
		                "Clicked the assign button for patient '" + patientName + "' and waited for navigation.");
		    } else {
		        System.out.println("No assign buttons found for patient: " + patientName);
		    }

		    System.out.println("Search box cleared successfully.");
		}
	 
	 public void markinprogress() {
		 Locator clickyes = getPage().locator("//span[normalize-space(text())=\"Yes\"]");
		 clickyes.click();
	 }
	 
	 public void validateProcedureInEncounter(Map<String, String> expectedData) {

		    String expectedCode = expectedData.get("procedureCode");
		    String expectedUnitCharge = expectedData.get("unitCharge");
		    String expectedTotalCharge = expectedData.get("totalCharge");

		    Locator rows = getPage().locator("//table//tbody//tr");
		    boolean found = false;

		    for (int i = 0; i < rows.count(); i++) {

		        String actualProcedure =
		            rows.nth(i).locator("td:nth-child(3)").innerText().trim();

		        if (actualProcedure.contains(expectedCode)) {
		            found = true;

		            String actualUnitCharge =
		                rows.nth(i).locator("td:nth-child(6)").innerText().trim();

		            String actualTotalCharge =
		                rows.nth(i).locator("td:nth-child(7)").innerText().trim();

		            Assert.assertEquals(actualUnitCharge, expectedUnitCharge,
		                "Unit charge mismatch for procedure " + expectedCode);

		            Assert.assertEquals(actualTotalCharge, expectedTotalCharge,
		                "Total charge mismatch for procedure " + expectedCode);

		            extentTest.get().log(Status.PASS,
		                "Validated procedure " + expectedCode + " in Encounter");

		            break;
		        }
		    }

		    Assert.assertTrue(found,
		        "Procedure code NOT found in Encounter table: " + expectedCode);
		}
	 public void selectplaceofservice(String option) {

		    Locator placeOfServiceInput = getPage().locator("//input[@placeholder='Place Of Service']");
		    placeOfServiceInput.click();
		    Locator optionValue = getPage().locator("//span[normalize-space()='" + option + "']");
		    optionValue.click();
		}
	 
	 public void Approveencounter() {
		 Locator Aprbtn = getPage().locator("//span[normalize-space()=\"Approve\"]");
		 Aprbtn.click();
	 }
	 
	 public void getencounternum() {
		 Locator encounterText = getPage().locator("//b[contains(text(),'Encounter Form')]");
		 String fullText = encounterText.textContent().trim();
		 String encounterFormNumber = fullText.replaceAll("\\D+", "");
		 System.out.println("Encounter Form Number: " + encounterFormNumber);
	 }
}
