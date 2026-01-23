package org.playwright.pages;

import java.util.HashMap;
import java.util.Map;

import org.framework.playwright.utils.BaseClass;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class FindClaim extends BaseClass {

	public FindClaim(Page page) {
		super(page);
	}
	
	 public Map<String, String> captureFirstClaimCardData() {

	        Map<String, String> claimData = new HashMap<>();

	        // Locate first claim card
	        Locator firstCard = getPage()
	                .locator("div.col-xl-4.col-lg-4.col-md-6.col-sm-6.col-xs-6")
	                .first();

	        // Patient Name
	        claimData.put("PatientName",
	                firstCard.locator("h2 span").first().innerText().trim());

	        // Bill Amount
	        claimData.put("BillAmount",
	                firstCard.locator("text=BillAmount").locator("..").locator("strong")
	                        .innerText().trim());

	        // Allowed Amount
	        claimData.put("Allowed",
	                firstCard.locator("text=Allowed").locator("..").locator("strong")
	                        .innerText().trim());

	        // Adjustment
	        claimData.put("Adjustment",
	                firstCard.locator("text=Adjustment").locator("..").locator("strong")
	                        .innerText().trim());

	        // Receipt
	        claimData.put("Receipt",
	                firstCard.locator("text=Receipt").locator("..").locator("strong")
	                        .innerText().trim());

	        // Procedure Code
	        claimData.put("ProcedureCode",
	                firstCard.locator("text=Procedure-Code").locator("..").locator("span")
	                        .innerText().trim());

	        // Encounter / Claim
	        claimData.put("EncounterClaim",
	                firstCard.locator("text=Encounter/Claim").locator("..").locator("span")
	                        .innerText().trim());

	        // Date of Service
	        claimData.put("DateOfService",
	                firstCard.locator("text=Date of Service").locator("..").locator("span")
	                        .innerText().trim());

	        return claimData;
	    }
	}


