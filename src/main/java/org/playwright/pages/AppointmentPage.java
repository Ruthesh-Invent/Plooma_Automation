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
	
	public String getFirstRowPatientName() {
        return getPage().locator("//tbody/tr[1]/td[2]").textContent().trim();
    }

    public String getFirstRowProviderName() {
        return getPage().locator("//tbody/tr[1]/td[3]").textContent().trim();
    }

    public String getFirstRowVisitReason() {
        return getPage().locator("//tbody/tr[1]/td[4]").textContent().trim();
    }

    public String getFirstRowServiceLocation() {
        return getPage().locator("//tbody/tr[1]/td[5]").textContent().trim();
    }

//    public boolean isAppointmentInFirstRow(String patient, String provider, String visitReason, String location) {
//
//        try {
//            getPage().waitForTimeout(1500); // wait for table refresh
//        } catch (Exception ignored) {}
//
//        boolean patientMatch = getFirstRowPatientName().equals(patient);
//        boolean providerMatch = getFirstRowProviderName().equals(provider);
//        boolean reasonMatch = getFirstRowVisitReason().equals(visitReason);
//        boolean locationMatch = getFirstRowServiceLocation().equals(location);
//
//        return patientMatch && providerMatch && reasonMatch && locationMatch;
//    }
    
    public boolean isAppointmentInFirstRow(String patient, String provider, String reason, String location) {
        
        for (int i = 0; i < 20; i++) {  // retry for ~10 seconds
            try {
                String fName = getFirstRowPatientName();
                String fProvider = getFirstRowProviderName();
                String fReason = getFirstRowVisitReason();
                String fLocation = getFirstRowServiceLocation();

                if (fName.equals(patient) &&
                    fProvider.equals(provider) &&
                    fReason.equals(reason) &&
                    fLocation.equals(location)) {
                    return true; // match found
                }

            } catch (Exception ignored) {}

            getPage().waitForTimeout(500); // wait before retry
        }

        return false; // never matched
    }
    
    public boolean isAppointmentPresent(String patient, String provider, String facility, String visitReason) {

        String rowXpath =
                "//table//tbody//tr[td[2][normalize-space()='" + patient + "'] " +
                "and td[3][contains(normalize-space(),'" + provider + "')] " +
                "and td[4][contains(normalize-space(),'" + facility + "')] " +
                "and td[5][contains(normalize-space(),'" + visitReason + "')]]";

        Locator row = getPage().locator(rowXpath);

        try {
            row.first().waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(8000));
            
            extentTest.get().log(Status.PASS, "Appointment found in table for " + patient);
            return true;

        } catch (Exception e) {
            extentTest.get().log(Status.FAIL, "❌ Appointment NOT found for: " + patient);
            return false;
        }
    }
    public boolean waitForSuccessToast() {

        String toastXpath = "//*[contains(text(),'Successfully') or contains(text(),'successfully')]";

        Locator toast = getPage().locator(toastXpath);

        try {
            toast.waitFor(new Locator.WaitForOptions()
                    .setTimeout(5000)
                    .setState(WaitForSelectorState.VISIBLE));

            extentTest.get().log(Status.PASS,
                    "Success toast captured: " + toast.innerText());
            return true;

        } catch (Exception e) {
            extentTest.get().log(Status.FAIL,
                    "No success toast message detected!");
            return false;
        }
    }
    public int getTotalAppointmentCount() {

        Locator pagination = getPage().locator("li.page-item.m-r-5.ng-star-inserted").first();

        String text = pagination.textContent().trim();  
        // Example: "1 - 10 of 38982"

        String total = text.split("of")[1].trim();
        return Integer.parseInt(total);
    }


}