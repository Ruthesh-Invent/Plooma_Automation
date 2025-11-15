package org.dewa.framework.objectrepo;

import java.util.List;

import org.dewa.framework.utils.BaseClass;

import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class CustomerDetailsPage extends BaseClass {

	public CustomerDetailsPage(Page page) {
		super(page);
		// TODO Auto-generated constructor stub
	}

	public String getCustomerName() {
		String customerName = getPage().locator("iframe[name='htmlPanel']").contentFrame()
				.locator("//div[@class='doc-title']").textContent().trim();
		return customerName;
	}

	public String getCustomerID() {
		String customerID = getPage().locator("//span[text()='ID:']/following-sibling::span").textContent();
		return customerID;
	}

	public void clickCloseCustomer() {
		Locator element = getPage().locator("//*[contains(@tooltip,'Close')]");
		element.click();
		extentTest.get().log(Status.INFO, "Closed Customer Page");
	}

	public boolean verifyAllCustomerDocumentAttributes() {
		List<Locator> allAttributes = getPage().locator("iframe[name='htmlPanel']").contentFrame()
				.locator("//html[@id='structureHTML']/body/div/div[2]/div/div//label").all();
		boolean res = true;
		for (Locator eachAttribute : allAttributes) {
			if (!(eachAttribute.isVisible())) {
				res = false;
				extentTest.get().log(Status.FAIL, eachAttribute.textContent().trim() + " is not visible");
				break;
			}
		}
		return res;
	}

	public void clickNextButton() {
		Locator element = getPage().locator("//button[@data-cy='next-doc']");
		element.click();
		extentTest.get().log(Status.INFO, "Clicked on Next Customer Button");
	}

	public void clickPreviousButton() {
		Locator element = getPage().locator("//button[@data-cy='prev-doc']");
		element.click();
		extentTest.get().log(Status.INFO, "Clicked on Previous Customer Button");
	}

	public boolean verifyCustomersSummaryTitle() {
		getPage().waitForSelector("//a[contains(text(),'Customer Summary')]",
				new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
		Locator element = getPage().locator("//a[contains(text(),'Customer Summary')]");
		return element.isVisible();
	}

	public boolean verifyAllCustomerSummaryAttributes() {
		List<Locator> allAttributes = getPage()
				.locator("//app-widgets-pane//section//ktd-grid-item//app-widget-header//h4[text()]").all();
		boolean res = true;
		for (Locator eachAttribute : allAttributes) {
			if (!(eachAttribute.isVisible())) {
				res = false;
				extentTest.get().log(Status.FAIL, eachAttribute.textContent().trim() + " is not visible");
				break;
			}
		}
		return res;
	}

	public String getNumOfVoicesValue() {
		String value = getPage().locator(
				"//h4[contains(@class,'font-semibold') and contains(text(),'No of Voices')]/ancestor::app-widget-header/../h4")
				.textContent().trim();
		return value;
	}

	public String getTopChannelValue() {
		String value = getPage().locator(
				"//h4[contains(@class,'font-semibold') and contains(text(),'Top Channel')]/ancestor::app-widget-header/../div//h4")
				.textContent().trim();
		return value;
	}

	public String getTopServiceValue() {
		String value = getPage().locator(
				"//h4[contains(@class,'font-semibold') and contains(text(),'Top Service')]/ancestor::app-widget-header/../div//h4")
				.textContent().trim();
		return value;
	}

	public String getTopSqlValue() {
		String value = getPage().locator(
				"//h4[contains(@class,'font-semibold') and contains(text(),'Top SQL')]/ancestor::app-widget-header/../div//h4")
				.textContent().trim();
		return value;
	}

	public boolean checkVoiceDetailedReportVisibility() {
		getPage().waitForSelector("//div[contains(text(),'Voice Detailed Report')]",
				new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
		Locator element = getPage().locator("//div[contains(text(),'Voice Detailed Report')]");
		return element.isVisible();
	}

	public boolean checkViewAllVoicesVisibility() {
		getPage().waitForSelector("//div[contains(text(),'View All Voices')]",
				new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
		Locator element = getPage().locator("//div[contains(text(),'View All Voices')]");
		return element.isVisible();
	}

	public void clickVoiceDetailedReport() {
		Locator element = getPage().locator("//div[contains(text(),'Voice Detailed Report')]");
		element.click();
		extentTest.get().log(Status.INFO, "Clicked on Voice Detailed Report Button");
	}

	public void clickViewAllVoices() {
		getPage().waitForSelector("//div[contains(text(),'View All Voices')]",
				new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
		// getPage().waitForTimeout(2000);
		Locator element = getPage().locator("//div[contains(text(),'View All Voices')]");
		element.click();
		extentTest.get().log(Status.INFO, "Clicked on View All Voices Button");
	}

	public boolean checkCustomerTypeVisibility() {
		Locator element = getPage().locator("iframe[name='htmlPanel']").contentFrame()
				.locator("//div[@class='doc-title']/following-sibling::div/span");
		return element.isVisible();
	}

	public String getCustomerType() {
		Locator element = getPage().locator("iframe[name='htmlPanel']").contentFrame()
				.locator("//div[@class='doc-title']/following-sibling::div/span");
		return element.textContent().trim();
	}

	public void clickNoOfVoices() {
		Locator element = getPage().locator(
				"//h4[contains(@class,'font-semibold') and contains(text(),'No of Voices')]/ancestor::app-widget-header/../h4");
		element.click();
		extentTest.get().log(Status.INFO, "Clicked on View Number of Voices");
	}

}
