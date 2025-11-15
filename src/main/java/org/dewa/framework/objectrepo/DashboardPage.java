package org.dewa.framework.objectrepo;


import java.util.List;

import org.dewa.framework.utils.BaseClass;

import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class DashboardPage extends BaseClass {

	public DashboardPage(Page page) {
		super(page);
		// TODO Auto-generated constructor stub
	}

	public String getDashboardTitle() {
		String title = getPage().locator("//a[@class='capitalize']").textContent().trim();
		return title;
	}

	public void clickOnCancel() {
		Locator element = getPage().locator("//button[@iconname='close']");
		element.click();
		extentTest.get().log(Status.INFO, "Clicked on Close Dashboard Page");
	}

	public void filterDashboardByDropdown(String dropdownName, String optionName) {
		//mat-label[text()='SQL']
		String dropDownXpath = "//mat-label[text()='"+dropdownName+"']";
		getPage().locator(dropDownXpath).click();
		// mat-option//span[contains(text(),'DDA Instant Happines')]
		getPage().waitForTimeout(5000);
		getPage().locator("//input[@placeholder='Search']").fill(optionName);
		getPage().locator("//mat-option//span[contains(text(),'" + optionName+ "')]").click();
		extentTest.get().log(Status.INFO,
				"Filtered " + "<b>" + dropdownName + "</b>" + " on " + "<b>" + optionName + "</b>" + " option");
		wasteDropdownClick(dropDownXpath);
		getPage().waitForTimeout(3000);
		// waitTillLoading();
	}

	public int getNumberOfVoicesCount() {
		Locator element = getPage().locator(
				"//h4[contains(text(),'Number of Voices')]//ancestor::app-widget-header/following-sibling::h4");
		return Integer.parseInt(element.textContent().trim().replace(",", ""));
	}
	
	public void clickOnVoicesCount() {
		Locator element = getPage().locator(
				"//h4[contains(text(),'Number of Voices')]//ancestor::app-widget-header/following-sibling::h4");
		element.click();
	}

	// This will work only if we hover on the widget
	public int getSourceDistributionBoardWidgetCount() {
		// h4[contains(text(),'Source Distribution
		// Board')]/ancestor::app-query-widget//b
		Locator element = getPage()
				.locator("//h4[contains(text(),'Source Distribution Board')]/ancestor::app-query-widget//b");
		return Integer.parseInt(element.textContent().trim());
	}

	public void clickOnMoreFilter() {
		Locator element = getPage().locator("//button[@iconname='more-option']");
		element.click();
		extentTest.get().log(Status.INFO, "Clicked on More Filter");
	}

	public void selectOptionsFromAdvancedFiltersDropdown(String dropdownName, String optionName) {
		//mat-label[text()='Customer Category']
		String xpath = "//mat-label[text()='"+dropdownName+"']";
		getPage().locator(xpath).click();
		getPage().locator("//input[@placeholder='Search']").fill(optionName);
		getPage().locator("//mat-option//span[contains(text(),'" + optionName+ "')]").click();
		extentTest.get().log(Status.INFO,
				"Filtered " + "<b>" + dropdownName + "</b>" + " on " + "<b>" + optionName + "</b>" + " option");
		wasteDropdownClick(xpath);
		getPage().locator("//div[contains(text(),'Apply Filter')]").click();
		getPage().waitForTimeout(3000);
	}
	
	public void clickApplyFilter() {
		getPage().locator("//div[contains(text(),'Apply Filter')]").click();
		extentTest.get().log(Status.INFO, "Clicked on Apply Filter");
	}
	
	public void scrollToWidget(String widgetName) {
		//h4[contains(@class,'font-semibold text-base') and contains(text(),'Top Subject')]/ancestor::app-query-widget/div
		Locator element = getPage().locator("//h4[contains(@class,'font-semibold text-base') and contains(text(),'"+widgetName+"')]/ancestor::app-query-widget/div").first();
		element.scrollIntoViewIfNeeded();
		//getPage().mouse().wheel(0, 200);
	}
	
	public void clickOnResetFilter() {
		Locator element = getPage().locator("//div[contains(text(),'Reset')]");
		element.click();
		extentTest.get().log(Status.INFO, "Clicked on Reset Filter");
	}
	
	public void filterVoicesOnTodayWeekMonth(String option) {
		getPage().locator("//app-date-range-filter[@datefilter='DateFilter']//mat-form-field[2]").click();
		//span[text()='Today']
		Locator element = getPage().locator("//span[text()='"+option+"']");
		element.click();
		waitTillLoading();
		extentTest.get().log(Status.INFO, "Filtered Voices from "+option);
	}
	
	public boolean checkVisibilityOfWidget(String widgetName) {
		//h4[contains(@class,'font-semibold text-base') and contains(text(),'Top Source')]
		Locator element = getPage().locator("//h4[contains(@class,'font-semibold') and contains(text(),'"+widgetName+"')]");
		return element.isVisible();
	}
	
//	public String getWidgetText(String widgetName) {
//		//h4[contains(@class,'font-semibold text-base') and contains(text(),'Top Channel')]/ancestor::app-query-widget/div//h4
//		
//		Locator element = getPage().locator("//h4[contains(@class,'font-semibold text-base') and contains(text(),'"+widgetName+"')]/ancestor::app-query-widget/div//h4");
//		return element.textContent().trim();
//	}
	
	public String getWidgetText(String widgetName) {
	    Locator element = getPage().locator(
	        "//h4[contains(@class,'font-semibold') and contains(text(),'"+widgetName+"')]/ancestor::app-query-widget/div//h4"
	    );
	    
	    // Wait for the element to be visible (timeout 30 seconds)
	    element.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(60000));
	    
	    String text = element.textContent();
	    
	    if (text != null) {
	        return text.trim();
	    } else {
	        throw new RuntimeException("Widget text not found or element is not visible for widget: " + widgetName);
	    }
	}

	
	
	public String getToolTip() {
		Locator element = getPage().locator("//div[@class='mdc-tooltip__surface mdc-tooltip__surface-animation']");
		return element.textContent().trim();
	}
	
	public boolean checkTooltipVisibility() {
		Locator element = getPage().locator("//div[@class='mdc-tooltip__surface mdc-tooltip__surface-animation']");
		String text = element.textContent().trim();
		return (!text.isEmpty());
	}
	
	public String spliWidgetTextAndGetOutput(String widgetText, String whichText) {
        // Remove unwanted characters and split the input
        
        String value=null;
        
        if(widgetText.contains(" ")) {
        	String part1 = widgetText.substring(0, widgetText.indexOf(" - "));
            String part2 = widgetText.substring(widgetText.indexOf(" - ") + 3, widgetText.indexOf(" ("));
            String part3 = widgetText.substring(widgetText.indexOf("(") + 1, widgetText.indexOf(")")) ;
            switch (whichText) {
    		case "Channel":value=part1;
    			break;
    		case "Count":value=part2;
    			break;
    		case "Percentage":value=part3;
    			break;
    		default: System.out.println("Give Proper Widget Text Option");
    			break;
    		}
        }
        else {
        	String cleanedInput = widgetText.replace("  - ", " ").replace("(", "").replace(")", "");
            String[] parts = cleanedInput.split(" ");
        	int i=0;
            switch (whichText) {
    		case "Channel":i=0;
    			break;
    		case "Count":i=1;
    			break;
    		case "Percentage":i=2;
    			break;
    		default: System.out.println("Give Proper Widget Text Option");
    			break;
    		}
            value=parts[i];
        }
        
        return value;
    }
	
	
	public void hoverOnWidgetIcon(String widgetName) {
		//h4[contains(@class,'font-semibold text-base') and contains(text(),'Number of Voices')]/../icon
		Locator element = getPage().locator("//h4[contains(@class,'font-semibold') and contains(text(),'"+widgetName+"')]/../icon").first();
		element.hover();
	}
	
	
	
	public boolean checkWidgetChartVisibility(String chartName) {
		//h4[contains(@class,'font-semibold text-base') and contains(text(),'Voice Distribution View')]/ancestor::app-query-widget//app-widget-charts//canvas
		getPage().locator("//h4[contains(@class,'font-semibold text-base') and contains(text(),'"+chartName+"')]/ancestor::app-query-widget//app-widget-charts//canvas").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		Locator element = getPage().locator("//h4[contains(@class,'font-semibold text-base') and contains(text(),'"+chartName+"')]/ancestor::app-query-widget//app-widget-charts//canvas");
		return element.isVisible();
	}
	
	public void clickDayMonthYearInVoiceDistrubutionView(String option) {
		//div[text()='year']
		Locator element = getPage().locator("//div[text()='"+option+"']");
		element.click();
		extentTest.get().log(Status.INFO, "Filtered Voices on "+option);
	}
	
	public void clickOnOverallSentimentScoreHeaders(String header) {
		//h4[contains(@class,'font-semibold text-base') and contains(text(),'Overall Sentiment Score')]/ancestor::app-multi-widgets//div[contains(text(),'Source')]
		Locator element = getPage().locator("//h4[contains(@class,'font-semibold text-base') and contains(text(),'Overall Sentiment Score')]/ancestor::app-multi-widgets//div[contains(text(),'"+header+"')]").first();
		element.click();
		getPage().mouse().wheel(0, 300);
		extentTest.get().log(Status.INFO, "Clicked on "+header+" under Overall Sentiment Score Widget");
	}
	
	public int checkHowManyWidgetsAvailableUnderOverallSpectrumScore(String filterOption) {
		//h4[contains(@class,'font-semibold text-base') and contains(text(),'Overall Sentiment Score')]/ancestor::app-multi-widgets//div[@id='d_Overall Sentiment Score_Channel']//canvas
		int count =0;
		if(getPage().locator("//h4[contains(@class,'font-semibold text-base') and contains(text(),'Overall Sentiment Score')]/ancestor::app-multi-widgets//div[@id='d_Overall Sentiment Score_"+filterOption+"']//h4[text()='No data available']").isVisible()) {
			count=0;
		}
		else {
			getPage().waitForSelector("//h4[contains(@class,'font-semibold text-base') and contains(text(),'Overall Sentiment Score')]/ancestor::app-multi-widgets//div[@id='d_Overall Sentiment Score_"+filterOption+"']//canvas", 
				    new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
			List<Locator> allElements = getPage().locator("//h4[contains(@class,'font-semibold text-base') and contains(text(),'Overall Sentiment Score')]/ancestor::app-multi-widgets//div[@id='d_Overall Sentiment Score_"+filterOption+"']//canvas").all();
			count = allElements.size();
		}
		return count;
	}
	
	public void sendTextAndFilterInAdvancedFilter(String optionName, String text) {
		//mat-label[text()='Aspect']
		Locator element = getPage().locator("//mat-label[text()='"+optionName+"']");
		element.fill(text);
		getPage().locator("//div[contains(text(),'Apply Filter')]").click();
		extentTest.get().log(Status.INFO, "Filtered based on "+text+" "+optionName);
	}
	
	public void clickOnNextButtonInSubServiceUnderOverallSentimentScore() {
		getPage().locator("//div[@id='dTab_Overall Sentiment Score_Sub Service']/ancestor::app-multi-widgets//button[@iconname='next' and not(@hidden)]").click();
		extentTest.get().log(Status.INFO, "Clicked on Next Button in Overall Sentiment Score Widget");
	}
	
	public boolean visibilityOfNextBttnInSunSrvceUndrOvrallSentimentScore() {
		boolean res = getPage().locator("//div[@id='dTab_Overall Sentiment Score_Sub Service']/ancestor::app-multi-widgets//button[@iconname='next' and not(@hidden)]").isVisible();
		return res;
	}
	
	public boolean visibilityOfPreviousBttnInSunSrvceUndrOvrallSentimentScore() {
		boolean res = getPage().locator("//div[@id='dTab_Overall Sentiment Score_Sub Service']/ancestor::app-multi-widgets//button[@iconname='prev' and not(@hidden)]").isVisible();
		return res;
	}
	
	public void clickOnPreviousButtonInSubServiceUnderOverallSentimentScore() {
		getPage().locator("//div[@id='dTab_Overall Sentiment Score_Sub Service']/ancestor::app-multi-widgets//button[@iconname='prev' and not(@hidden)]").click();
		extentTest.get().log(Status.INFO, "Clicked on Previous Button in Overall Sentiment Score Widget");
	}
	
	public void selectFromTopSubjectFilter(String filterOption) {
		getPage().locator("//span[text()='Subjects']").click();
		//span[contains(text(),'Final Bill')]
		getPage().locator("//input[@placeholder='Search']").fill(filterOption);
		getPage().locator("//mat-option//span[contains(text(),'" + filterOption+ "')]").click();
		wasteClickInBetweenTheScreen();
		extentTest.get().log(Status.INFO, "Filtered <b>Top Subject</b> Widget on <b>"+filterOption+"</b>");
	}
	
	public int getNumberOfVoicesCountFromFrame() {
		Locator element = getFrame().locator(
				"//h4[contains(text(),'Number of Voices')]//ancestor::app-widget-header/following-sibling::h4");
		return Integer.parseInt(element.textContent().trim().replace(",", ""));
	}
	
}
