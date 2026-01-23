package org.playwright.modules;

import java.util.Map;

import org.framework.playwright.utils.DataFaker;
import org.testng.Assert;

import com.microsoft.playwright.Page;

public class Patientcreationmodule  extends ParentModule{

	public Patientcreationmodule(Map<String, Object> data, Page page) {
		super(data, page);
	}

	public void patientcreation(Map<String, Object> data) {
		getDashboardPage().clickMainModule("Patients");
		getDashboardPage().clickSubmodule("Patient List");
		getPatientlistpage().addpatient();
		getPatientlistpage().verfiyintakeformtitle();
		getPatientlistpage().fillInputByLabel("FirstName", DataFaker.generateFirstName());
		getPatientlistpage().fillInputByLabel("LastName", DataFaker.generateLastName());
		getPatientlistpage().fillInputByLabel("DOB", DataFaker.generateDOB());
		getPatientlistpage().fillInputByLabel("PhoneNumber", DataFaker.generateFakePhoneNumber());
		getPatientlistpage().fillInputByLabel("EmailId", DataFaker.generateEmail());
		getPatientlistpage().continuedemograph();
		getPage().waitForURL(url -> url.contains("/patient/edit/"),
		new Page.WaitForURLOptions().setTimeout(10000));
		String actualUrl = getPage().url();
		String expectedPath = "/patient/edit/";
		Assert.assertTrue(actualUrl.contains(expectedPath),
				"URL validation failed. Expected URL path: " + expectedPath + " but got: " + actualUrl);
		System.out.println("URL validation passed → Redirected to: " + actualUrl + " (Patinet edit)");
		getAppointmentPage().selectDropdownByFieldNameAndValue("Gender Identity",data.get("Gender").toString());
		getPatientlistpage().selectMatAutocompleteByLabel("Zip", data.get("zip").toString());
		getPatientlistpage().address("Street Address", DataFaker.generateFakeAddress());
		getPatientlistpage().clicknxt();
		getPatientlistpage().emrgencycontactname("efirstName", DataFaker.generateFirstName());
		getPatientlistpage().emrgencycontactname("elastName", DataFaker.generateLastName());
		getPatientlistpage().selectMatAutocompleteByLabel("Primary Phone Type", data.get("Phone Type").toString());
		getPatientlistpage().emergnxtbtn();	
		getPatientlistpage().selectemergencycontact();
		getPatientlistpage().selectinsurance();
		getAppointmentPage().selectDropdownByFieldNameAndValue("Insurance Company", data.get("Insurance Name").toString());
		getPatientlistpage().fillinsuranceetails("Policy Number", DataFaker.generatePolicyNumber());
		getPatientlistpage().fillinsuranceetails("Start Date", DataFaker.generatePolicyStartDate());
		getPatientlistpage().fillinsuranceaddress("Street Address",DataFaker.generateFakeAddress());
		getPatientlistpage().fillinsuranceaddress("Zip", data.get("Insurance Zip").toString());
		getPatientlistpage().saveinsurance();
		getPatientlistpage().finnxt();
	}
}
