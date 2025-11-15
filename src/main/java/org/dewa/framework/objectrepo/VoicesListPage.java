package org.dewa.framework.objectrepo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dewa.framework.utils.BaseClass;

import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class VoicesListPage extends BaseClass {

	public VoicesListPage(Page page) {
		super(page);
	}

	public static void select100CountPerPage() {
		getPage().locator("//*[contains(text(),'Showing')]/../mat-select").click();
		getPage().locator("//*[text()=' 100 ']").click();
	}

	public void clearFilter() {
		//getPage().locator("//*[contains(text(),'Show Filters')]").click();
		Locator element = getPage().locator("//button[contains(text(),'Reset')]");
		element.click();
		//getPage().locator("//div[contains(text(),'Hide Filters')]").click();
		extentTest.get().log(Status.INFO, "Clicked on Clear Filter");
	}
	
	public void filterDocumentByTitle(String docTitle) {
		Locator titleTB = getPage().locator("//input[@placeholder='ID Search']");
		titleTB.fill(docTitle);
		getPage().keyboard().press("Enter");
	}

	public void clickOnDocument(String docTitle) {
		// div[@title='e098e9b5-182d-44aa']
		Locator element = getPage().locator("//div[@title='" + docTitle + "']").first();
		element.click();
		System.out.println("Clicked on Doc : " + docTitle);
		extentTest.get().log(Status.INFO, "Clicked on the Document: " + docTitle);
	}

	public void clickHideFilter() {
		Locator element = getPage().locator("//*[contains(text(),'Hide Filters')]");
		element.click();
		extentTest.get().log(Status.INFO, "Clicked on Hide Filters");
	}

	public boolean visibilityOfGraphicWidgets(String widgetName) {
		Locator element = getPage().locator("//app-widget-header//*[contains(text(),'" + widgetName + "')]");
		return element.isVisible();
	}

	public boolean visibilityOfOptions() {
		Locator element = getPage().locator("//*[contains(text(),'Options')]");
		return element.isVisible();
	}

	public boolean visibilityOfSearchTextTB() {
		Locator element = getPage().locator("//*[contains(text(),'Search Text')]");
		return element.isVisible();
	}

	public void clickOnSideNavigationBarOptions(String optionName) {
		// span[text()='Voice']
		getPage().locator("//app-bot-sidebar[contains(@class,'block')]").hover();
		getPage().waitForTimeout(1000);
		Locator navigationOption = getPage().locator("//span[text()='" + optionName + "']");
		click(navigationOption, optionName);
		getPage().locator("//input[@placeholder='ID Search']").hover();
		System.out.println("Clicked on " + optionName);
	}

	public boolean checkResultsForSearchedVoidId() {
		// tbody//tr[@role='row']
		List<Locator> elements = getPage().locator("//tbody//tr[@role='row']").all();
		boolean res = false;
		if (elements.size() > 0) {
			res = true;
		}
		return res;
	}

	public String getTitle() {
		String title = getPage().locator("//a[@class='capitalize']").textContent().trim();
		return title;
	}

	public int getTotalNumberOfVoice() {
		getPage().waitForTimeout(2000);
		getPage().locator("//div[@data-layer='Title']/div")
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(10000));
		String fullText = getPage().locator("//div[@data-layer='Title']/div").textContent().trim();
		String[] splittedText = fullText.split(" ");
		System.out.println("2nd Index in Array "+splittedText[2]);
		String totalVoiceCount = splittedText[2].replace("(", "").replace(")", "").replace(",", "");
		System.out.println("Total Voice count : "+totalVoiceCount);
		return Integer.valueOf(totalVoiceCount);
	}

	public void filterVoicesOnTodayWeekMonth(String option) {
		getPage().locator("//app-date-range-filter[@datefilter='DateFilter']//mat-form-field[2]").click();
		// span[text()='Today']
		Locator element = getPage().locator("//span[text()='" + option + "']");
		element.click();
		waitTillLoading();
		extentTest.get().log(Status.INFO, "Filtered Voices from " + option);
	}

	public void filterVoicesOnFilter(String filterName, String filterText) {
		//getPage().locator("//div[contains(text(),'Show Filters')]").click()
	   //  ;
		// div[@class='search-filter-header']/ancestor::app-document-filter/div/div[2]//mat-label[text()='Channel
		// List']
		Locator element = getPage().locator(
				"//div[@class='search-filter-header']/ancestor::app-document-filter/div/div[2]//mat-label[text()='"
						+ filterName + "']");
		element.click();
		getPage().locator("//input[@placeholder='Search']").fill(filterText);
		getPage().locator("//mat-option//span[contains(text(),'" + filterText + "')]").click();
		wasteClickInBetweenTheScreen();
		getPage().locator("//div[contains(text(),'Apply Filter')]").click();
		getPage().locator("//div[contains(text(),'Hide Filters')]").click();
		extentTest.get().log(Status.INFO, "Filtered Voices on <b>" + filterText + " : " + filterName + "</b>");
	}
	
	public void filterVoicesOnFilter1(String filterName, String filterText) {
	    try {
	       // Locator showFilters = getPage().locator("//div[contains(text(),'Show Filters')]");
	       // showFilters.waitFor(new Locator.WaitForOptions().setTimeout(30000).setState(WaitForSelectorState.VISIBLE));
	      //  showFilters.click();

	        Locator filterLabel = getPage().locator("//div[@class='search-filter-header']/ancestor::app-document-filter/div/div[2]//mat-label[text()='" + filterName + "']");
	        filterLabel.waitFor(new Locator.WaitForOptions().setTimeout(30000).setState(WaitForSelectorState.VISIBLE));
	        filterLabel.click();

	        Locator searchInput = getPage().locator("//input[@placeholder='Search']");
	        searchInput.waitFor(new Locator.WaitForOptions().setTimeout(30000).setState(WaitForSelectorState.VISIBLE));
	        searchInput.fill(filterText);

	        Locator filterOption = getPage().locator("//mat-option//span[contains(text(),'" + filterText + "')]");
	        filterOption.waitFor(new Locator.WaitForOptions().setTimeout(30000).setState(WaitForSelectorState.VISIBLE));
	        filterOption.click();

	        wasteClickInBetweenTheScreen();

	        Locator applyFilter = getPage().locator("//div[contains(text(),'Apply Filter')]");
	        applyFilter.waitFor(new Locator.WaitForOptions().setTimeout(30000).setState(WaitForSelectorState.VISIBLE));
	        applyFilter.click();

	        Locator hideFilters = getPage().locator("//div[contains(text(),'Hide Filters')]");
	        hideFilters.waitFor(new Locator.WaitForOptions().setTimeout(30000).setState(WaitForSelectorState.VISIBLE));
	        hideFilters.click();

	        extentTest.get().log(Status.INFO, "Filtered Voices on <b>" + filterText + " : " + filterName + "</b>");
	    } catch (Exception e) {
	        extentTest.get().log(Status.FAIL, "Failed to filter voices on " + filterName + " with text " + filterText + ". Error: " + e.getMessage());
	        throw e; // Re-throw so test fails properly
	    }
	}


	public void filterVoicesOnFilterTextBox(String filterName, String filterText) {
		//getPage().locator("//div[contains(text(),'Show Filters')]").click();
		// div[@class='search-filter-header']/ancestor::app-document-filter/div/div[2]//mat-label[text()='Channel
		// List']
		Locator element = getPage().locator(
				"//div[@class='search-filter-header']/ancestor::app-document-filter/div/div[2]//mat-label[text()='"
						+ filterName + "']");
		element.fill(filterText);
		getPage().locator("//div[contains(text(),'Apply Filter')]").click();
		getPage().locator("//div[contains(text(),'Hide Filters')]").click();
		extentTest.get().log(Status.INFO, "Filtered Voices on " + filterText + " " + filterName);
	}

	public void selectAllOptionsFromFilter(String filterName) {
		//getPage().locator("//div[contains(text(),'Show Filters')]").click();
		// div[@class='search-filter-header']/ancestor::app-document-filter/div/div[2]//mat-label[text()='Channel
		// List']
		Locator element = getPage().locator(
				"//div[@class='search-filter-header']/ancestor::app-document-filter/div/div[2]//mat-label[text()='"
						+ filterName + "']");
		element.click();
		List<Locator> allOptions = getPage().locator("//div[contains(@id,'cdk-overlay')]//mat-option").all();
		for (Locator eachOption : allOptions) {
			eachOption.click();
		}
		wasteClickInBetweenTheScreen();
		getPage().locator("//div[contains(text(),'Apply Filter')]").click();
		getPage().locator("//div[contains(text(),'Hide Filters')]").click();
		extentTest.get().log(Status.INFO, "Filtered Voices on all the options under " + filterName);
	}

	public void clickOnPageTab(String tabName) {
		//bot-tab// span[text()='Complaints']
		getPage().locator("//bot-tab//span[text()='" + tabName + "']").click();
		extentTest.get().log(Status.INFO, "Clicked on " + tabName + "Tab");
	}

	public boolean checkAllWidgetsAvailibiltyInVoiceListPage() {
		boolean res1 = checkWidgetAvailability();
		getPage().locator("//button[@iconname='next']").click();
		boolean res2 = checkWidgetAvailability();
		return res1 && res2;

	}

	public List<String> getAllWidgetsNames() {
		List<Locator> allWidgets = getPage().locator("//app-widget-header//h4[contains(@class,'font-semibold')]").all();
		List<String> allWidgetText = new ArrayList<String>();
		for (Locator eachWidget : allWidgets) {
			allWidgetText.add(eachWidget.textContent().trim());
		}
		getPage().locator("//button[@iconname='next']").click();
		allWidgets = getPage().locator("//app-widget-header//h4[contains(@class,'font-semibold')]").all();
		for (Locator eachWidget : allWidgets) {
			allWidgetText.add(eachWidget.textContent().trim());
		}
		getPage().locator("//button[@iconname='prev']").click();
		return allWidgetText;
	}

	public static boolean checkWidgetAvailability() {
		List<Locator> allWidgets = getPage().locator("//h4[contains(@class,'font-semibold text-base')]").all();
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

	public List<String> getAllVoiceTableHeaders() {
		getPage().locator("(//thead//tr//th)[1]").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		List<Locator> allHeaderElements = getPage().locator("//thead//tr//th").all();
		List<String> allHeaders = new ArrayList<String>();
		for (Locator eachHeaderElement : allHeaderElements) {
			allHeaders.add(eachHeaderElement.textContent().trim());
		}
		return allHeaders;
	}

	public boolean checkAllTheVoiceTableHeaders() {
		getPage().locator("(//thead//tr//th)[1]").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		List<Locator> allElements = getPage().locator("//thead//tr//th").all();
		boolean res = true;
		List<String> allHeaders = new ArrayList<String>();
		for (Locator eachWidget : allElements) {
			allHeaders.add(eachWidget.textContent().trim());
			if (!(eachWidget.isVisible())) {
				res = false;
				extentTest.get().log(Status.FAIL, eachWidget.textContent().trim() + " is not visible");
				break;
			}
		}
		getPage().waitForTimeout(2000);
		for (String eachHeader : allHeaders) {
			extentTest.get().log(Status.INFO,
					"Voice List Table Header is visible, Header Name :<b>" + eachHeader + "</b>");
		}
		return res;
	}

	public boolean checkFilterPresenceOnTop(String expectedFilter) {
		// span[@mattooltipposition='below']
		List<Locator> allElements = getPage().locator("//span[@mattooltipposition='below']").all();
		List<String> allFilters = new ArrayList<String>();
		for (Locator eachElement : allElements) {
			allFilters.add(eachElement.textContent().trim());
		}
		boolean res = false;
		for (String eachFilter : allFilters) {
			if (eachFilter.contains(expectedFilter)) {
				res = true;
				break;
			}
		}
		return res;
	}

	public void filterVoicesOnMultipleFilter(String filterName, String filterText1, String filterText2) {
		//getPage().locator("//div[contains(text(),'Show Filters')]").click();
		// div[@class='search-filter-header']/ancestor::app-document-filter/div/div[2]//mat-label[text()='Channel
		// List']
		Locator element = getPage().locator(
				"//div[@class='search-filter-header']/ancestor::app-document-filter/div/div[2]//mat-label[text()='"
						+ filterName + "']");
		element.click();
		Locator searchBox = getPage().locator("//input[@placeholder='Search']");
		searchBox.fill(filterText1);
		getPage().locator("//mat-option//span[contains(text(),'" + filterText1 + "')]").click();
		wasteClickInBetweenTheScreen();
		element.click();
		searchBox.clear();
		searchBox.fill(filterText2);
		getPage().locator("//mat-option//span[contains(text(),'" + filterText2 + "')]").click();
		wasteClickInBetweenTheScreen();
		getPage().locator("//div[contains(text(),'Apply Filter')]").click();
		getPage().locator("//div[contains(text(),'Hide Filters')]").click();
		extentTest.get().log(Status.INFO, "Filtered Voices on <b>" + filterText1 + " : " + filterName + "</b>");
	}

	public boolean checkMultipleFilterPresenceOnTop(String expectedFilter1, String expectedFilter2) {
		// span[@mattooltipposition='below']
		List<Locator> allElements = getPage().locator("//span[@mattooltipposition='below']").all();
		List<String> allFiltersText = new ArrayList<String>();
		for (Locator eachElement : allElements) {
			eachElement.hover();
			String hoverText = getPage().locator("//div[@class='mdc-tooltip__surface mdc-tooltip__surface-animation']")
					.textContent().trim();
			allFiltersText.add(hoverText);
		}
		boolean res = false;
		for (String eachFilter : allFiltersText) {
			if (eachFilter.contains(expectedFilter1) && eachFilter.contains(expectedFilter2)) {
				res = true;
				break;
			}
		}
		return res;
	}

	public void selectMultipleFiltersInOneGo(String filter1, String option1, String filter2, String option2) {
		getPage().locator("//div[contains(text(),'Show Filters')]").click();
		// div[@class='search-filter-header']/ancestor::app-document-filter/div/div[2]//mat-label[text()='Channel
		// List']
		Locator element = getPage().locator(
				"//div[@class='search-filter-header']/ancestor::app-document-filter/div/div[2]//mat-label[text()='"
						+ filter1 + "']");
		element.click();
		getPage().locator("//input[@placeholder='Search']").fill(option1);
		getPage().locator("//mat-option//span[contains(text(),'" + option1 + "')]").click();
		wasteClickInBetweenTheScreen();
		Locator element2 = getPage().locator(
				"//div[@class='search-filter-header']/ancestor::app-document-filter/div/div[2]//mat-label[text()='"
						+ filter2 + "']");
		element2.click();
		getPage().locator("//input[@placeholder='Search']").fill(option2);
		getPage().locator("//mat-option//span[contains(text(),'" + option2 + "')]").click();
		wasteClickInBetweenTheScreen();
		getPage().locator("//div[contains(text(),'Apply Filter')]").click();
		getPage().locator("//div[contains(text(),'Hide Filters')]").click();
		extentTest.get().log(Status.INFO, "Filtered Voices on <b>" + filter1 + " : " + option1 + "</b>");
		extentTest.get().log(Status.INFO, "Filtered Voices on <b>" + filter2 + " : " + option2 + "</b>");
	}

	public void clickShowFilter() {
		getPage().locator("//div[contains(text(),'Show Filters')]").click();
		extentTest.get().log(Status.INFO, "Clicked on Show Filters");
	}

	public boolean showFilterVisibility() {
		return getPage().locator("//div[contains(text(),'Show Filters')]").isVisible();
	}

	public boolean hideFilterVisibility() {
		return getPage().locator("//div[contains(text(),'Hide Filters')]").isVisible();
	}

	public void searchVoiceUsingSerachText(String text) {
		Locator element = getPage().locator("//*[contains(text(),'Search Text')]");
		element.fill(text);
	}

	public void clickExport() {
		getPage().locator("//*[@iconname='download1']").click();
		extentTest.get().log(Status.INFO, "Clicked on Export");
		getPage().locator("//mat-spinner[@role='progressbar']")
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN).setTimeout(60000));
	}

	public boolean checkDownloadSuccessfullMessage() {
		getPage().locator("//div[@aria-live='assertive']")
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		Locator element = getPage().locator("//div[@aria-live='assertive']");
		return element.isVisible();
	}

	public void clickOnStudio() {
		// icon[@name='setting/new-studio']/../..
		getPage().waitForTimeout(1000);
		getPage().locator("//icon[@name='setting/new-studio']/../..")
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		Locator element = getPage().locator("//icon[@name='setting/new-studio']/../..");
		element.hover();
		element.click();
		extentTest.get().log(Status.INFO, "Clicked on Studio Page");
	}

	public void clickOnNextPreviousBtn(String next_prev) {
		// button[@data-cy='next']
		getPage().locator("//button[@data-cy='" + next_prev + "']").click();
		extentTest.get().log(Status.INFO, "Clicked on " + next_prev + " button");
	}

	public boolean checkPrevBtnDisability() {
		// button[@data-cy='prev']
		return getPage().locator("//button[@data-cy='prev']").isEnabled();
	}

	public void clickOnWidget(String widgetName) {
		// h4[contains(text(),'Top Service')]
		boolean res = true;
		while (res) {
			if (getPage().locator("//h4[contains(text(),'" + widgetName + "')]").isVisible()) {
				// h4[contains(text(),'Top
				// Service')]/ancestor::app-query-widget/div//h4[contains(@class,'cursor-pointer')]
				getPage().locator("//h4[contains(text(),'" + widgetName
						+ "')]/ancestor::app-query-widget/div//h4[contains(@class,'cursor-pointer')]").click();
				extentTest.get().log(Status.INFO, "Clicked on " + widgetName + " widget");
				res = false;
			} else {
				getPage().locator("//button[@iconname='next']").click();
			}
		}
	}

	public void clickOnShowAll() {
		// div[text()='Show All']
		getPage().locator("//div[text()='Show All']").click();
		extentTest.get().log(Status.INFO, "Clicked on Show All Button");
	}

	public String getWidgetText(String widgetName, String countOrText) {
		// h4[contains(text(),'Top Service')]
		boolean res = true;
		String widgetText = null;
		while (res) {
			if (getPage().locator("//h4[contains(text(),'" + widgetName + "')]").isVisible()) {
				// h4[contains(text(),'Top
				// Service')]/ancestor::app-query-widget/div//h4[contains(@class,'cursor-pointer')]
				widgetText = getPage()
						.locator("//h4[contains(text(),'" + widgetName
								+ "')]/ancestor::app-query-widget/div//h4[contains(@class,'cursor-pointer')]")
						.textContent().trim();
				res = false;
			} else {
				getPage().locator("//button[@iconname='next']").click();
			}
		}

		if (countOrText.contains("Count")) {
			widgetText = widgetText.split("\\(")[0].replaceAll("[^0-9]", "");
		} else if (countOrText.contains("Text")) {
			widgetText = widgetText.split(" - ")[0];
		}
		return widgetText;
	}

	public int getRowCountInTable() {
		// tbody//tr[@role='row']
		waitUntillProgressBar();
		List<Locator> allRowsElements = getPage().locator("//tbody//tr[@role='row']").all();
		int totalRows = allRowsElements.size() / 2;
		return totalRows;
	}

	public void increaseTableCount(int count) {
		getPage().locator("//span[text()='Show result:']/../mat-select").click();
		getPage().locator("//span[text()=' " + count + " ']").click();
		extentTest.get().log(Status.INFO, "Increased Show Results Count to " + count);
	}

	//chan
	public String getHeaderWidgetsCount1(String widgetName) {
		// h4[contains(text(),'Top Service')]
		String widgetText=null;
	boolean res = true;
		while (res) {
			if (getPage().locator("//h4[contains(text(),'" + widgetName + "')]").isVisible()) {
				// h4[contains(text(),'Top Service')]/ancestor::app-query-widget/div//h4[contains(@class,'cursor-pointer')]
				getPage().locator("//h4[contains(text(),'" + widgetName
						+ "')]/ancestor::app-query-widget/div//h4[contains(@class,'cursor-pointer')]").hover();
				widgetText=getPage().locator("//div[@class='mdc-tooltip__surface mdc-tooltip__surface-animation']").textContent();
				res = false;
			} else {
				getPage().locator("//button[@iconname='next']").click();
			}
		}
		
		String[] splittedText = widgetText.split("-");
		return splittedText[1].trim();
	}
	

//	public String getHeaderWidgetsCount(String widgetName) {
//	    String widgetText = null;
//	    boolean res = true;
//
//	    while (res) {
//	        if (getPage().locator("//h4[contains(text(),'" + widgetName + "')]").isVisible()) {
//	            getPage().locator("//h4[contains(text(),'" + widgetName
//	                    + "')]/ancestor::app-query-widget/div//h4[contains(@class,'cursor-pointer')]").hover();
//
//	            widgetText = getPage().locator("//div[@class='mdc-tooltip__surface mdc-tooltip__surface-animation']").textContent();
//	            res = false;
//	        } else {
//	            getPage().locator("//button[@iconname='next']").click();
//	        }
//	    }
//
//	    if (widgetText == null || widgetText.isEmpty()) {
//	        throw new RuntimeException("Tooltip text for widget '" + widgetName + "' is empty or null");
//	    }
//
//	    String[] splittedText = widgetText.split("-");
//
//	    if (splittedText.length < 2) {
//	        throw new RuntimeException("Tooltip text format invalid, expected '-' separator: '" + widgetText + "'");
//	    }
//
//	    return splittedText[1].trim();
//	}

	public void clickViewDetailedReport() {
		//*[contains(text(),'View Detailed Report')]
		getPage().locator("//*[contains(text(),'View Detailed Report')]").click();
		extentTest.get().log(Status.INFO, "Clicked on View Detailed Report");
	}
	
	public void clickOnDateFilter() {
		getPage().locator("//mat-icon[contains(text(),'calendar')]").click();
		extentTest.get().log(Status.INFO, "Clicked on Date Filter");
	}
	
	public void selectTabsInDateFilter(String tabOption) {
		//div[@role='tablist']//span[contains(text(),'Month')]
		getPage().locator("//div[@role='tablist']//span[contains(text(),'"+tabOption+"')]").click();
		extentTest.get().log(Status.INFO, "Selected "+tabOption+" from Date Filter");
	}
	
	public void selectToday_Week_Month(String option) {
		//mat-radio-group[@role='radiogroup']//label[contains(text(),'Today')]
		getPage().locator("//mat-radio-group[@role='radiogroup']//label[contains(text(),'"+option+"')]").click();
		getPage().locator("//button//span[contains(text(),'Apply')]").click();
		extentTest.get().log(Status.INFO, "Selected "+option+" from Date Filter");
		
	}
	
	public void filerVoicesOnDayWeekMonth(String option) {
		clickOnDateFilter();
		selectTabsInDateFilter("Custom");
		selectToday_Week_Month(option);
	}

    public String getHeaderWidgetsCount(String widgetName) {
    String widgetText = null;
    boolean found = false;

    while (!found) {
        if (getPage().locator("//h4[contains(text(),'" + widgetName + "')]").isVisible()) {
            getPage().locator("//h4[contains(text(),'" + widgetName + "')]/ancestor::app-query-widget/div//h4[contains(@class,'cursor-pointer')]").hover();
            widgetText = getPage().locator("//div[@class='mdc-tooltip__surface mdc-tooltip__surface-animation']").textContent();
            found = true;
        } else {
            getPage().locator("//button[@iconname='next']").click();
        }
    }

    // Extract percentage using regex
    Pattern pattern = Pattern.compile("\\((\\d+(\\.\\d+)?)%\\)");
    Matcher matcher = pattern.matcher(widgetText);
    if (matcher.find()) {
        return matcher.group(1); // Returns "40.0"
    } else {
        throw new RuntimeException("Widget tooltip does not contain a valid percentage: " + widgetText);
    }
}
    public void showFilter() {
		getPage().locator("//div[contains(text(),'Show Filters')]").click();
	}

}
