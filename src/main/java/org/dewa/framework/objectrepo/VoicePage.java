package org.dewa.framework.objectrepo;

import java.util.List;

import org.dewa.framework.utils.BaseClass;

import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class VoicePage extends BaseClass{

	public VoicePage(Page page) {
		super(page);
		// TODO Auto-generated constructor stub
	}
	
	public String getVoiceSummaryAttributes(String attributeName) {
		//label[contains(text(),'Subject')]/../following-sibling::p
		Locator element = getPage().locator("//label[contains(text(),'"+attributeName+"')]/../following-sibling::p").first();
		return element.textContent();
	}
	
	public void closeDocumentPage() {
		Locator element = getPage().locator("//*[contains(@iconname,'close')]").first();
		element.click();
		extentTest.get().log(Status.INFO, "Closed Voice Page");
		System.out.println("Closed the Voice Page");
	}
	
	public String fetchAISummaryTitle() {
		//div[text()='AI Summary']/../following-sibling::div/p[1]
		String text = getPage().locator("//div[text()='AI Summary']/../following-sibling::div/p[1]").textContent().trim();
		return text;
	}
	
	public String fetchAISummaryContent() {
		//div[text()='AI Summary']/../following-sibling::div/p[2]
		String text = getPage().locator("//div[text()='AI Summary']/../following-sibling::div/p[2]").textContent().trim();
		return text;
	}
	
	public String fetchAISummaryForNoData() {
		//div[@id='aiSummaryContainer']
		String text = getPage().locator("//div[@id='aiSummaryContainer']").textContent().trim();
		return text;
	}
	
	public String getAIorUserTitleAttribute(String attribute) {
		//label[contains(text(),'AI')]/..//span[contains(@class,'round')]
		String titleAttribute = getPage().locator("//label[contains(text(),'"+attribute+"')]/..//span[contains(@class,'round')]").getAttribute("title");
		return titleAttribute;
	}
	
	public boolean checkAISenimentAvailability() {
		List<Locator> allElements = getPage().locator("//div[@data-cy='summaries']//span[@class='flex justify-center items-center w-9 h-9 text-white rounded-full']").all();
		boolean res=false;
		if(allElements.size()==2) {
			res=true;
		}
		return res;
	}
	
	public boolean checkValueAvailabilityForAttribute(String attributeName) {
		Locator element = getPage().locator("//label[contains(text(),'"+attributeName+"')]/../following-sibling::p").first();
		return !(element.textContent().isBlank());
	}

}
