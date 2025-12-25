package org.playwright.modules;

import java.util.HashMap;
import java.util.Map;

import org.playwright.pages.EncounterPage;
import org.testng.Assert;

import com.microsoft.playwright.Page;

public class Ecountermodule extends ParentModule {
	
	

  public Ecountermodule(Map<String, Object> data, Page page) {
		super(data, page);
	}
  
  
    public void validateencounterform() {
    	Map<String, Object> projectInfo = new HashMap<String, Object>();
    	String name = data.get("patientName").toString();
    	getDashboardPage().clickMainModule("Billing");
    	getDashboardPage().clickSubmodule("Encounter Form");
    	int initialCount =getDashboardPage().getTileCount("Pending");
    	getEncounterPage().searchPatientNameAndClickassign(name);
    	getEncounterPage().markinprogress();
    	getPage().waitForURL(url -> url.contains("/encounterform/edit/"),
				new Page.WaitForURLOptions().setTimeout(50000));

		String actualUrl = getPage().url();
		String expectedPath = "/encounterform/edit/";
		Assert.assertTrue(actualUrl.contains(expectedPath),
				"URL validation failed. Expected URL path: " + expectedPath + " but got: " + actualUrl);

		System.out.println("URL validation passed → Redirected to: " + actualUrl + " (Encounter edit form)");
		getEncounterPage().selectplaceofservice("11-Office");
		getEncounterPage().Approveencounter();
	}

	}