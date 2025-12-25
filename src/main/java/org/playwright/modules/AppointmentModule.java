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

	public void appointmentcreation() {
		getDashboardPage().clickMainModule("Appointments");
		getDashboardPage().clickSubmodule("Appointments List");
		int beforeCount = getAppointmentPage().getTotalAppointmentCount();
		System.out.println("Before count = " + beforeCount);
		getAppointmentPage().clickAddapointment();
		getAppointmentPage().selectDropdownByFieldNameAndValue("Provider", "Kathryn Rowe");
		getAppointmentPage().selectDropdownByFieldNameAndValue("Patient", "Paisley Wolf");
		getAppointmentPage().selectDropdownByFieldNameAndValue("Visit Reason", "OT Evaluation");
		getAppointmentPage().selectDropdownByFieldNameAndValue("Service Location", "MLee Therapy");
		getAppointmentPage().saveAppointmentHandleAllPopups();
		int afterCount = getAppointmentPage().getTotalAppointmentCount();
		System.out.println("After count = " + afterCount);
		Assert.assertTrue(afterCount > beforeCount, "Appointment was NOT added! Count did NOT increase.");
		System.out.println("Appointment creation validated by count increase.");
		if (afterCount > beforeCount) {
			extentTest.get().log(Status.PASS,
					"Appointment added successfully. Count increased from " + beforeCount + " to " + afterCount);
		} else {
			extentTest.get().log(Status.FAIL, "Appointment was NOT added! Count did NOT increase. Before: "
					+ beforeCount + ", After: " + afterCount);
		}
		getAppointmentPage().navigateToDashboard();
	}

	public void appointmentcheckout() {

		Map<String, Object> projectInfo = new HashMap<String, Object>();
		String Subjectvalue = data.get("Subjective").toString();
		String Assesmentvalue = data.get("Assesment").toString();
		String name = data.get("patientName").toString();
//	        	String Diagnosiscodevalue = data.get("Diagnosis code").toString();
//	        	appointmentcreation();
//	        	getAppointmentPage().navigateToDashboard();
		validateSchedule();
		getDashboardPage().navigateToTile("Checked In");
		getDashboardPage().searchPatientNameAndClicknote(name);

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

		getDashboardPage().selectpoctype("Template Name", "Test_POC");
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
		getDashboardPage().enterFieldValue("Subjective", Subjectvalue);
		getDashboardPage().enterFieldValue("Assessment", Assesmentvalue);
		getDashboardPage().clicksignandcontinue();
		getPage().waitForURL(url -> url.contains("superbill"),
				new WaitForURLOptions().setTimeout(60000).setWaitUntil(WaitUntilState.LOAD));
		String actual = getPage().url();
		String expected = "superbill";
		Assert.assertTrue(actualUrl.contains(expectedPath),
				"URL validation failed. Expected URL path: " + expected + " but got: " + actual);

		System.out.println("URL validation passed → Redirected to: " + expected + " (superbill)");
		getAppointmentPage().selectDropdownByFieldNameAndValue("Rendering Provider", "David J");
		getDashboardPage().selectICDcode("Diagnosis Codes", "R47.9 - Unspecified speech disturbances");
		getDashboardPage().selectICDcode("Procedure Codes",
				"92507 - Treatment of speech, language, voice, communication, and/or auditory processing disorder; individual");
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

	public void validateSchedule() {
		Map<String, Object> projectInfo = new HashMap<String, Object>();
		String Name = data.get("patientName").toString();
		appointmentcreation();
		getDashboardPage().navigateToTile("Scheduled");
		int initialCount = getDashboardPage().getTileCount("Scheduled");
		getDashboardPage().searchPatientNameAndClickEdit(Name);
		getDashboardPage().changestatus("statusType", "Checked In");
		waitForLoadingToFinish();
		int updatedCount = getDashboardPage().getTileCount("Scheduled");
		Assert.assertEquals(updatedCount, initialCount - 1,
				"Scheduled tile count did not decrease as expected after status change.");
	}

	public void validatecheckedin() {
		String name = data.get("patientName").toString();
		validateSchedule();
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

	public void validateAppointmentPOC() {
		Map<String, Object> projectInfo = new HashMap<String, Object>();
		String Name = data.get("patientName").toString();
		validateSchedule();
		validatecheckedin();
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

	public void validateclinicalnotes() {
		String Subjectvalue = data.get("Subjective").toString();
		String Assesmentvalue = data.get("Assesment").toString();
		validateAppointmentPOC();
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

	public void validatesuperbill() {
		validateclinicalnotes();
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
