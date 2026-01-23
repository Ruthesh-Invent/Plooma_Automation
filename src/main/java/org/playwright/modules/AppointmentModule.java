package org.playwright.modules;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.WaitForURLOptions;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitUntilState;

public class AppointmentModule extends ParentModule {

	public AppointmentModule(Map<String, Object> data, Page page) {
		super(data, page);
	}
	
	private String getValue(Map<String, Object> data, String key) {
	    return data.getOrDefault(key, "").toString();
	}

	
	public void appointmentcreation(Map<String, Object> data) {

	    getDashboardPage().clickMainModule("Appointments");
	    getDashboardPage().clickSubmodule("Appointments List");

	    int beforeCount = getAppointmentPage().getTotalAppointmentCount();
	    System.out.println("Before count = " + beforeCount);

	    getAppointmentPage().clickAddapointment();

	    getAppointmentPage().selectDropdownByFieldNameAndValue("Provider", data.get("provider").toString());

	    getAppointmentPage().selectDropdownByFieldNameAndValue(
	            "Patient", data.get("patient").toString());

	    getAppointmentPage().selectDropdownByFieldNameAndValue(
	            "Visit Reason", data.get("visitReason").toString());

	    getAppointmentPage().selectDropdownByFieldNameAndValue(
	            "Service Location", data.get("serviceLocation").toString());

	    getAppointmentPage().saveAppointmentHandleAllPopups();

	    int afterCount = getAppointmentPage().getTotalAppointmentCount();
	    Assert.assertTrue(afterCount > beforeCount,
	            "Appointment was NOT added! Count did NOT increase.");

	    extentTest.get().log(Status.PASS,
	            "Appointment created successfully from JSON data");

	    getAppointmentPage().navigateToDashboard();
	}


	public void appointmentcheckout(Map<String, Object> data) {

		Map<String, Object> projectInfo = new HashMap<String, Object>();
		String pocTemplate = getValue(data, "pocTemplate");
		String renderingProvider = getValue(data, "renderingProvider");
		String diagnosisCode = getValue(data, "diagnosisCode");
		String procedureCode = getValue(data, "procedureCode");
		
		String subjectValue = data.get("subjective").toString();
		String assessmentValue = data.get("assessment").toString();
		String patientName = data.get("patientName").toString();
		appointmentcreation(data);
		getDashboardPage().navigateToTile("Scheduled");
		int initialCount = getDashboardPage().getTileCount("Scheduled");
		getDashboardPage().searchPatientNameAndClickEdit(patientName);
		getDashboardPage().changestatus("statusType", "Checked In");
		waitForLoadingToFinish();
		int updatedCount = getDashboardPage().getTileCount("Scheduled");
		Assert.assertEquals(updatedCount, initialCount - 1,
				"Scheduled tile count did not decrease as expected after status change.");		
		getDashboardPage().navigateToTile("Checked In");
		getDashboardPage().searchPatientNameAndClicknote(patientName);

		// wait for navigation
		getPage().waitForLoadState();

		// wait for URL to match appointment
		getPage().waitForURL(url -> url.contains("/appointmentpoc/create/"),
				new Page.WaitForURLOptions().setTimeout(10000));

		String actualUrl = getPage().url();
		String expectedPath = "/appointmentpoc/create/";

		Assert.assertTrue(actualUrl.contains(expectedPath),
				"URL validation failed. Expected URL path: " + expectedPath + " but got: " + actualUrl);

		System.out.println("URL validation passed → Redirected to: " + actualUrl + " (Clinical Notes)");
		getDashboardPage().selectpoctype("Template Name", pocTemplate);
		getDashboardPage().selectgoalandobj();
		getDashboardPage().clicksavetocontinue();
		getPage().waitForLoadState();
		getPage().waitForURL(url -> url.contains("clinicalnote"),
				new WaitForURLOptions().setTimeout(60000).setWaitUntil(WaitUntilState.LOAD));
		String actUrl = getPage().url();
		String expectPath = "clinicalnote";
		Assert.assertTrue(actUrl.contains(expectPath),
				"URL validation failed. Expected URL path: " + expectPath + " but got: " + actUrl);

		System.out.println("URL validation passed → Redirected to: " + actUrl + " (Clinical Notes)");
		getPage().waitForLoadState();
		getDashboardPage().enterFieldValue("Subjective", subjectValue);
		getDashboardPage().enterFieldValue("Assessment", assessmentValue);
		getDashboardPage().clicksignandcontinue();
		getPage().waitForURL(url -> url.contains("superbill"),
				new WaitForURLOptions().setTimeout(60000).setWaitUntil(WaitUntilState.LOAD));
		String actual = getPage().url();
		String expected = "superbill";
		Assert.assertTrue(actualUrl.contains(expectedPath),
				"URL validation failed. Expected URL path: " + expected + " but got: " + actual);

		System.out.println("URL validation passed → Redirected to: " + expected + " (superbill)");
		getAppointmentPage().selectDropdownByFieldNameAndValue(
		        "Rendering Provider", renderingProvider);

		getDashboardPage().selectICDcode(
		        "Diagnosis Codes", diagnosisCode);

		getDashboardPage().selectICDcode(
		        "Procedure Codes", procedureCode);
		
//		getAppointmentPage().selectDropdownByFieldNameAndValue("Rendering Provider", "David J");
//		getDashboardPage().selectICDcode("Diagnosis Codes", "R47.9 - Unspecified speech disturbances");
//		getDashboardPage().selectICDcode("Procedure Codes",
//				"92507 - Treatment of speech, language, voice, communication, and/or auditory processing disorder; individual");
		getDashboardPage().clicksumbit();
		getPage().waitForLoadState();
		getPage().waitForURL(url -> url.contains("/dashboard/main"),
				new WaitForURLOptions().setTimeout(60000).setWaitUntil(WaitUntilState.LOAD));
//		            String Url = getPage().url();
//		            String expectedurl = "clinicalnote";
//		            Assert.assertTrue(actualUrl.contains(expectedurl),
//		                    "URL validation failed. Expected URL path: " + expectedurl + " but got: " + Url);
//
//		            System.out.println("URL validation passed → Redirected to: " + Url + " (dashboard/main)");
	}

	public void validateSchedule(Map<String, Object> data) {
		Map<String, Object> projectInfo = new HashMap<String, Object>();
		String Name = data.get("patientName").toString();
		appointmentcreation(data);
		getDashboardPage().navigateToTile("Scheduled");
		int initialCount = getDashboardPage().getTileCount("Scheduled");
		getDashboardPage().searchPatientNameAndClickEdit(Name);
		getDashboardPage().changestatus("statusType", "Checked In");
		waitForLoadingToFinish();
		int updatedCount = getDashboardPage().getTileCount("Scheduled");
		Assert.assertEquals(updatedCount, initialCount - 1,
				"Scheduled tile count did not decrease as expected after status change.");
	}

	public void validatecheckedin(Map<String, Object> data) {
		String name = data.get("patientName").toString();
		validateSchedule(data);
		getDashboardPage().navigateToTile("Checked In");
		getDashboardPage().searchPatientNameAndClicknote(name);
		getPage().waitForLoadState();
		getPage().waitForURL(url -> url.contains("/appointmentpoc/create/"),
				new Page.WaitForURLOptions().setTimeout(10000));

		String actualUrl = getPage().url();
		String expectedPath = "/appointmentpoc/create/";

		Assert.assertTrue(actualUrl.contains(expectedPath),
				"URL validation failed. Expected URL path: " + expectedPath + " but got: " + actualUrl);

		System.out.println("URL validation passed → Redirected to: " + actualUrl + " (Clinical Notes)");
	}

	public void validateAppointmentPOC(Map<String, Object> data) {
		Map<String, Object> projectInfo = new HashMap<String, Object>();
		String Name = data.get("patientName").toString();
		validateSchedule(data);
		validatecheckedin(data);
//		            getDashboardPage().selectappointmentpocdropdown("Others", "Occupational Therapy");
		getDashboardPage().selectpoctype("Template Name", "Test_POC");
		getDashboardPage().selectgoalandobj();
		getDashboardPage().clicksavetocontinue();
		getPage().waitForLoadState();
		getPage().waitForURL(url -> url.contains("clinicalnote"), new Page.WaitForURLOptions().setTimeout(10000));

		String actualUrl = getPage().url();
		String expectedPath = "clinicalnote";
		Assert.assertTrue(actualUrl.contains(expectedPath),
				"URL validation failed. Expected URL path: " + expectedPath + " but got: " + actualUrl);

		System.out.println("URL validation passed → Redirected to: " + actualUrl + " (Clinical Notes)");
	}

	public void validateclinicalnotes(Map<String, Object> data) {
		String Subjectvalue = data.get("Subjective").toString();
		String Assesmentvalue = data.get("Assesment").toString();
		validateAppointmentPOC(data);
		getDashboardPage().enterFieldValue("Subjective", Subjectvalue);
		getDashboardPage().enterFieldValue("Assessment", Assesmentvalue);
		getDashboardPage().clicksignandcontinue();
		getPage().waitForURL(url -> url.contains("superbill"),
				new WaitForURLOptions().setTimeout(60000).setWaitUntil(WaitUntilState.LOAD));
		String actual = getPage().url();
		String expected = "superbill";
		Assert.assertTrue(actual.contains(expected),
				"URL validation failed. Expected URL path: " + expected + " but got: " + actual);

		System.out.println("URL validation passed → Redirected to: " + expected + " (superbill)");

	}

	public void validatesuperbill(Map<String, Object> data) {
		validateclinicalnotes(data);
		getAppointmentPage().selectDropdownByFieldNameAndValue("Rendering Provider", "David J");
		getDashboardPage().selectICDcode("Diagnosis Codes", "R47.9 - Unspecified speech disturbances");
		getDashboardPage().selectICDcode("Procedure Codes",
				"92507 - Treatment of speech, language, voice, communication, and/or auditory processing disorder; individual");
		getDashboardPage().clicksumbit();
		getPage().waitForLoadState();
		getPage().waitForURL(url -> url.contains("/dashboard/main"),
				new WaitForURLOptions().setTimeout(60000).setWaitUntil(WaitUntilState.LOAD));
		String Url = getPage().url();
		String expectedurl = "clinicalnote";
		Assert.assertTrue(Url.contains(expectedurl),
				"URL validation failed. Expected URL path: " + expectedurl + " but got: " + Url);

		System.out.println("URL validation passed → Redirected to: " + Url + " (dashboard/main)");
	}

}
