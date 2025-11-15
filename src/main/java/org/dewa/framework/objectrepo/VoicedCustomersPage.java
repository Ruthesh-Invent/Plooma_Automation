package org.dewa.framework.objectrepo;

import java.util.ArrayList;
import java.util.List;

import org.dewa.framework.utils.BaseClass;

import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class VoicedCustomersPage extends BaseClass {

	public VoicedCustomersPage(Page page) {
		super(page);
		// TODO Auto-generated constructor stub
	}

	public String getVoicedCustomersTitle() {
		String title = getPage().locator("//a[@class='capitalize']").textContent();
		return title;
	}

	public boolean checkaAllWidgetsAvailibiltyInVoicedCustomerListPage() {
		List<Locator> allWidgets = getPage().locator("//h4[contains(@class,'font-semibold')]").all();
		boolean res = true;
		for (Locator eachWidget : allWidgets) {
			if (!(eachWidget.isVisible())) {
				res = false;
				extentTest.get().log(Status.FAIL, eachWidget.textContent().trim() + " is not visible");
				break;
			}
		}
		return res;
	}
	
	public List<String> getAllCustomerTableHeaders() {
		List<Locator> allColumnsHeaderElements = getPage().locator("//th[@role='columnheader']").all();
		List<String> allHeaders=new ArrayList<String>();
		for(Locator eachHeader:allColumnsHeaderElements) {
			allHeaders.add(eachHeader.textContent().trim());
		}
		return allHeaders;
	}

	public boolean checkaAllColumnsAvailibiltyInVoicedCustomerListPage() {
		List<Locator> allColumnsHeader = getPage().locator("//th[@role='columnheader']").all();
		boolean res = true;
		for (Locator eachColumn : allColumnsHeader) {
			if (!(eachColumn.isVisible())) {
				res = false;
				extentTest.get().log(Status.FAIL, eachColumn.textContent().trim() + " is not visible");
				break;
			}
		}
		return res;
	}

	public void clickOnAllCustomers_R_Consumers_R_Builders(String option) {
		// span[text()='All Customers']
		getPage().locator("//span[text()='" + option + "']").click();
		extentTest.get().log(Status.INFO, "Clicked on " + option);
	}

	public boolean checkTotalVoicedCustomerVisibility() {
		// div[@id='echartComp']
		Locator element = getPage().locator("//div[@id='echartComp']");
		return element.isVisible();
	}

	public boolean checkWidgetVisibility(String widgetName) {
		// h4[contains(@class,'font-semibold') and contains(text(),'Most Active Builder')]
		getPage().locator("//h4[contains(@class,'font-semibold') and contains(text(),'" + widgetName + "')]").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		Locator element = getPage().locator("//h4[contains(@class,'font-semibold') and contains(text(),'" + widgetName + "')]");
		return element.isVisible();
	}

	public String getWidgetValue(String widgetName) {
		Locator element = getPage().locator("//h4[contains(@class,'font-semibold') and contains(text(),'" + widgetName
				+ "')]/ancestor::app-query-widget/div//h4").first();
		return element.textContent().trim();
	}

	public String getCustomersRequireingAttentionCount() {
		// //h4[contains(@class,'font-semibold') and contains(text(),'Customers Requiring Attention')]/ancestor::app-widget-header/../h4
		Locator element = getPage().locator(
				"//h4[contains(@class,'font-semibold') and contains(text(),'Customers Requiring Attention')]/ancestor::app-widget-header/../h4");
		return element.textContent().trim();
	}

	public void clickOnShowFilter() {
		Locator element = getPage().locator("//button[@iconname='tune']");
		element.click();
		extentTest.get().log(Status.INFO, "Clicked on Filter");
	}

	public boolean checkAllFilterOptionsAvailability() {
		Locator element1 = getPage().locator("(//input[@placeholder='Customer Account Number'])[2]");
		Locator element2 = getPage().locator("(//input[@placeholder='Customer Contact'])[2]");
		Locator element3 = getPage().locator("(//input[@placeholder='Customer Category'])[2]");
		Locator element4 = getPage().locator("(//input[@placeholder='Customer Name'])[2]");
		Locator element5 = getPage().locator("(//input[@placeholder='Customer ID'])[3]");
		List<Locator> allElements = new ArrayList<Locator>();
		allElements.add(element1);
		allElements.add(element2);
		allElements.add(element3);
		allElements.add(element4);
		allElements.add(element5);
		boolean res = true;
		for (Locator eachElement : allElements) {
			if (!(eachElement.isVisible())) {
				res = false;
				extentTest.get().log(Status.FAIL, eachElement.getAttribute("placeholder") + " is not visible");
				break;
			}
		}
		return res;
	}

	public boolean checkFilterAvailability() {
		Locator element = getPage().locator("//div[@class='search-filter-header']");
		return element.isVisible();
	}

	public void searchByID(String value) {
		Locator element = getPage().locator("//mat-label[text()='ID Search']");
		element.fill(value);
		getPage().keyboard().press("Enter");
	}

	public boolean verifyIdSearch() {
		List<Locator> allElements = getPage().locator("//tbody[@role='rowgroup']/tr").all();
		int count = allElements.size() / 2;
		boolean res = false;
		if (count == 1) {
			res = true;
		}
		return res;
	}

	public void clickOnExcelDownload() {
		Locator element = getPage().locator("//button[@iconname='download1']");
		element.click();
		extentTest.get().log(Status.INFO, "Clicked on Excel Download");
	}

	public boolean checkDownloadSuccessfullMessage() {
		getPage().locator("//div[@aria-live='assertive']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		Locator element = getPage().locator("//div[@aria-live='assertive']");
		return element.isVisible();
	}

	public void clickOnFirstCustomer() {
		Locator element = getPage().locator("//tbody[@role='rowgroup']/tr[1]/td[1]");
		element.click();
		extentTest.get().log(Status.INFO, "Clicked on Document");
	}

	public int getTotalCountOfCustomersDisplayed() {
		List<Locator> allElements = getPage().locator("//tbody[@role='rowgroup']/tr").all();
		int count = allElements.size() / 2;
		return count;
	}

	public void changeShowCountPerPage(String count) {
		getPage().locator("//span[text()='Show result:']/following-sibling::mat-select").click();
		// mat-option//span[contains(text(),'10')]
		Locator element = getPage().locator("//mat-option//span[contains(text(),'" + count + "')]").first();
		element.click();
		extentTest.get().log(Status.INFO, "Show Count per page changed for " + count);
	}

	public void clickOnCustomerByName(String customerName) {
		// tbody[@role='rowgroup']//td[3]//div[contains(@title,'ZAKARIYA MOHAMMAD
		// OTHMAN')]
		Locator element = getPage()
				.locator("//tbody[@role='rowgroup']//td[3]//div[contains(@title,'" + customerName + "')]");
		element.click();
		extentTest.get().log(Status.INFO, "Clicked on Customer " + customerName);
	}

	public String getNumOfVoiceAfterFilter() {
		String voiceCount = getPage().locator("//tbody[@role='rowgroup']//td[5]//div").textContent().trim();
		return voiceCount;
	}

	public String getPrimaryChannelAfterFilter() {
		String primaryChannel = getPage().locator("//tbody[@role='rowgroup']//td[6]//div").textContent().trim();
		return primaryChannel;
	}

	public void clickOnCustomerByCustId(String customerId) {
		Locator idElement = getPage()
				.locator("//tbody[@role='rowgroup']//td[1]//div[contains(@title,'" + customerId + "')]");
		idElement.click();
		extentTest.get().log(Status.INFO, "Clicked on Customer Id " + customerId);
	}

	public boolean checkDateRangeFilterVisibility() {
		Locator element = getPage().locator("//button[@aria-label='Open calendar']");
		return element.isVisible();
	}

	public void clickDateRangeFilter() {
		Locator element = getPage().locator("//button[@aria-label='Open calendar']");
		element.click();
	}

	public boolean checkMonthAndYearVisibility() {
		Locator element = getPage().locator("//button[@aria-label='Choose month and year']");
		return element.isVisible();
	}

	public boolean checkCalenderVisibility() {
		Locator element = getPage().locator("//tbody[@class='mat-calendar-body']");
		return element.isVisible();
	}

	public boolean checkTodayYesterdayElementAvailabilityInDateRangeFilter() {
		List<Locator> elements = getPage().locator("//mat-radio-group[@role='radiogroup']//label").all();
		boolean res = true;
		for (Locator eachColumn : elements) {
			if (!(eachColumn.isVisible())) {
				res = false;
				extentTest.get().log(Status.FAIL, eachColumn.textContent().trim() + " is not visible");
				break;
			}
		}
		return res;
	}

	public void clickOnTodayWeekMonthDropdown() {
		Locator element = getPage().locator("//app-date-range-filter[@datefilter='DateFilter']//mat-select");
		element.click();
		extentTest.get().log(Status.INFO, "Clicked on Day, Week, Month Filter Dropdown");
	}

	public boolean checkTodayWeekMonthVisibility() {
		List<Locator> elements = getPage().locator("//mat-option").all();
		boolean res = true;
		for (Locator eachColumn : elements) {
			if (!(eachColumn.isVisible())) {
				res = false;
				extentTest.get().log(Status.FAIL, eachColumn.textContent().trim() + " is not visible");
				break;
			}
		}
		return res;
	}
	
	public void searchBySearchText(String text) {
		Locator element = getPage().locator("//app-advance-header-filter//input[@formcontrolname='SearchText']");
		element.fill(text);
		getPage().keyboard().press("Enter");
		extentTest.get().log(Status.INFO, "Searching by "+text);
	}
	
	public boolean nameAvailabilityInTheList(String name) {
		List<Locator> allNames = getPage().locator("//tbody//td[3]//div[text()]").all();
		boolean res=false;
		for(Locator eachName:allNames) {
			String actualName = eachName.textContent().trim().replace(" ", "");
			if(actualName.contains(name.trim().replace(" ", ""))) {
				res=true;
				break;
			}
		}
		return res;
	}

}
