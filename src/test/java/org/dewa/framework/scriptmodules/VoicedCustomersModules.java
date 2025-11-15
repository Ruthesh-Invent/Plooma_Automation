package org.dewa.framework.scriptmodules;

import static org.dewa.framework.utils.BaseClass.assertContains;
import static org.dewa.framework.utils.BaseClass.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.microsoft.playwright.Frame;
import com.microsoft.playwright.Page;

public class VoicedCustomersModules extends ParentModule{

	public VoicedCustomersModules(Page page) {
		super(page);
		// TODO Auto-generated constructor stub
	}
	
	public void verifyCustomerListPageTitle(String subsName, String projectName) {
		navigateTillVoicedCustomerPage(subsName, projectName);
		String title = getVoicedCustomerPage().getVoicedCustomersTitle();
		System.out.println("Title "+ title);
		assertContains("Customer List Page is Titled as Voiced Customers", "Customer List Page is not Titled as Voiced Customers",
				title.trim(), "Voiced Customers");
	}
	
	public void verifyCustomersListPageAllCustomers(String subsName, String projectName) {
		navigateTillVoicedCustomerPage(subsName, projectName);
		boolean allWidgetsAvailability = getVoicedCustomerPage().checkaAllWidgetsAvailibiltyInVoicedCustomerListPage();
		assertEquals("All the Widgets are Visible", "All the Widgets are not Visible", true, allWidgetsAvailability);
	}
	
	public void verifyCustomersListPageConsumers(String subsName, String projectName) {
		navigateTillVoicedCustomerPage(subsName, projectName);
		getVoicedCustomerPage().clickOnAllCustomers_R_Consumers_R_Builders("Consumers");
		boolean allWidgetsAvailability = getVoicedCustomerPage().checkaAllWidgetsAvailibiltyInVoicedCustomerListPage();
		assertEquals("All the Widgets are Visible", "All the Widgets are not Visible", true, allWidgetsAvailability);
	}
	
	public void verifyCustomersListPageBuilders(String subsName, String projectName) {
		navigateTillVoicedCustomerPage(subsName, projectName);
		getVoicedCustomerPage().clickOnAllCustomers_R_Consumers_R_Builders("Builders");
		boolean allWidgetsAvailability = getVoicedCustomerPage().checkaAllWidgetsAvailibiltyInVoicedCustomerListPage();
		assertEquals("All the Widgets are Visible", "All the Widgets are not Visible", true, allWidgetsAvailability);
	}
	
	public void verifyCustomersListPageCustomersList(String subsName, String projectName) {
		navigateTillVoicedCustomerPage(subsName, projectName);
		List<String> allExpectedTableHeaders=new ArrayList<String>();
		allExpectedTableHeaders.add("ID");
		allExpectedTableHeaders.add("Customer Account");
		allExpectedTableHeaders.add("Customer Name");
		allExpectedTableHeaders.add("Customer Type");
		allExpectedTableHeaders.add("No of Voice");
		allExpectedTableHeaders.add("Primary Channel");
		allExpectedTableHeaders.add("Sentiments");
		List<String> actualHeaders = getVoicedCustomerPage().getAllCustomerTableHeaders();
		for (String eachExpectedHeader : allExpectedTableHeaders) {
			String expectedResult = eachExpectedHeader;
			boolean found = false;
			String actualResult = null;
			for (String eachActualHeader : actualHeaders) {
				actualResult = eachActualHeader;
				if (expectedResult.contains(actualResult)) {
					found = true;
					break;
				}
			}
			System.out.println("Table Header Name '" + expectedResult + "' exists in actual list: " + found);
			assertContains("Expected Table Header Name is equals to Actual Table Header Name",
					"Expected Table Header Name is not equals to Actual Table Header Name", actualResult, expectedResult);
		}
		
		boolean allColumnsAvailability = getVoicedCustomerPage().checkaAllColumnsAvailibiltyInVoicedCustomerListPage();
		assertEquals("All the Columns are Visible", "All the Columns are not Visible", true, allColumnsAvailability);
	}
	
	public void verifyCustomersListPageTotalVoicedCutomer(String subsName, String projectName) {
		navigateTillVoicedCustomerPage(subsName, projectName);
		boolean totalVoicedCustomerVisibility = getVoicedCustomerPage().checkTotalVoicedCustomerVisibility();
		assertEquals("Total Voiced Customer is Visible", "Total Voiced Customer is not Visible", true, totalVoicedCustomerVisibility);
	}
	
	public void verifyCustomersListPageCustomerSentiment(String subsName, String projectName) {
		navigateTillVoicedCustomerPage(subsName, projectName);
		boolean totalVoicedCustomerVisibility = getVoicedCustomerPage().checkTotalVoicedCustomerVisibility();
		assertEquals("Customer Sentiment is Visible", "Customer Sentiment is not Visible", true, totalVoicedCustomerVisibility);
	}
	
	public void verifyCustomersListPageMostEngagedConsumerStat(String subsName, String projectName) {
		navigateTillVoicedCustomerPage(subsName, projectName);
		boolean mostEngagedConsumerVisibility = getVoicedCustomerPage().checkWidgetVisibility("Most Engaged Consumer");
		String mostEngagedConsumerName = getVoicedCustomerPage().getWidgetValue("Most Engaged Consumer");
		assertEquals("Most Engaged Consumer is Visible and Name is "+"<b>"+mostEngagedConsumerName+"</b>", "Most Engaged Consumer is not Visible", true, mostEngagedConsumerVisibility);
	}
	
	public void verifyCustomersListPageMostActiveBuilderStat(String subsName, String projectName) {
		navigateTillVoicedCustomerPage(subsName, projectName);
		boolean mostActiveBuilderVisibility = getVoicedCustomerPage().checkWidgetVisibility("Most Active Builder");
		String mostActiveBuilderName = getVoicedCustomerPage().getWidgetValue("Most Active Builder");
		assertEquals("Most Active Builder is Visible and Name is "+"<b>"+mostActiveBuilderName+"</b>", "Most Active Builder is not Visible", true, mostActiveBuilderVisibility);
	}
	
	public void verifyCustomersListPagePrimaryCustomerInteractionChannelStat(String subsName, String projectName) {
		navigateTillVoicedCustomerPage(subsName, projectName);
		boolean primaryCustomerVisibility = getVoicedCustomerPage().checkWidgetVisibility("Primary Customer Interaction Channel");
		String primaryCustomerChannelName = getVoicedCustomerPage().getWidgetValue("Primary Customer Interaction Channel");
		assertEquals("Primary Customer Interaction Channel Widget is Visible and Primary Customer Interaction Channel is "+"<b>"+primaryCustomerChannelName+"</b>", "Primary Customer Interaction Channel Widget is not Visible", true, primaryCustomerVisibility);
	}
	
	public void verifyCustomersListPageCustomersRequiringAttentionStat(String subsName, String projectName) {
		navigateTillVoicedCustomerPage(subsName, projectName);
		boolean customerRequiringAttentionVisibility = getVoicedCustomerPage().checkWidgetVisibility("Customers Requiring Attention");
		String customerRequiringAttentionCount = getVoicedCustomerPage().getCustomersRequireingAttentionCount();
		assertEquals("Customers Requiring Attention Widget is Visible and Customers Requiring Attention Count is "+"<b>"+customerRequiringAttentionCount+"</b>", "Customers Requiring Attention Widget is not Visible", true, customerRequiringAttentionVisibility);
	}
	
	public void verifyCustomersListPageFilterPresense(String subsName, String projectName) {
		navigateTillVoicedCustomerPage(subsName, projectName);
		getVoicedCustomerPage().clickOnShowFilter();
		boolean filiterPresense = getVoicedCustomerPage().checkAllFilterOptionsAvailability();
		assertEquals("All Filter Options are Visible","All Filter Options are not Visible ", true, filiterPresense);
	}
	
	public void verifyCustomersListPageHideFilter(String subsName, String projectName) {
		navigateTillVoicedCustomerPage(subsName, projectName);
		getVoicedCustomerPage().clickOnShowFilter();
		boolean filiterPresenseBefore = getVoicedCustomerPage().checkFilterAvailability();
		assertEquals("Filter Options are available after clicking on Show Filter","Filter Options are not available after clicking on Show Filter", true, filiterPresenseBefore);
		getVoicedCustomerPage().clickOnShowFilter();
		boolean filiterPresenseAfter = getPage().locator("//div[@class='search-filter-header']").isVisible();
		assertEquals("Filter Options are hidden after clicking Hide Filter","Filter Options are not hidden after clicking Hide Filter", false, filiterPresenseAfter);
	}
	
	public void verifyCustomersListPageIdSearch(String subsName, String projectName) {
		navigateTillVoicedCustomerPage(subsName, projectName);
		getVoicedCustomerPage().searchByID("10335701");
		waitTillLoading();
		boolean searchResult = getVoicedCustomerPage().verifyIdSearch();
		assertEquals("Searched Customer Details is Visible Customer ID Search is working fine","Seacrhed Customer Details is not Visible Customer ID Search is not working fine", true, searchResult);
	}
	
	public void verifyCustomersListPageExcelExport(String subsName, String projectName) {
		navigateTillVoicedCustomerPage(subsName, projectName);
		waitTillLoading();
		getVoicedCustomerPage().clickOnExcelDownload();
		getPage().waitForTimeout(2000);
		boolean downloadSuccessStatus = getVoicedCustomerPage().checkDownloadSuccessfullMessage();
		assertEquals("Excel File Exported Successfully","Excel File is not Exported", true, downloadSuccessStatus);
	}
	
	public void verifyCustomersListPagePagination(String subsName, String projectName) {
		navigateTillVoicedCustomerPage(subsName, projectName);
		waitTillLoading();
		getVoicedCustomerPage().clickOnFirstCustomer();
		String documentPageUrl = getPage().url();
		getPage().goBack();
		String listPageUrl = getPage().url();
		boolean backResult=documentPageUrl.equals(listPageUrl);
		assertEquals("Backward navigation is working fine","Backward navigation is not working", false, backResult);
		getPage().goForward();
		String documentPageUrl2 = getPage().url();
		boolean forwordResult=documentPageUrl2.equals(documentPageUrl);
		assertEquals("Forward navigation is working fine","Forward navigation is not working", true, forwordResult);
		getCustomerDetailsPage().clickCloseCustomer();
	}
	
	public void verifyCustomersListPageShowCount(String subsName, String projectName) {
		navigateTillVoicedCustomerPage(subsName, projectName);
		waitTillLoading();
		int count1 = getVoicedCustomerPage().getTotalCountOfCustomersDisplayed();
		getVoicedCustomerPage().changeShowCountPerPage("100");
		waitTillLoading();
		int count2 = getVoicedCustomerPage().getTotalCountOfCustomersDisplayed();
		boolean result = count1==count2;
		assertEquals("Show Count per page is working fine","Show Count per page is not working", false, result);
	}
	
	public void verifyCustomersDetailsPageProblemID(String subsName, String projectName, String customerIdInput) {
		navigateTillCustomerDetailsPage(subsName, projectName, customerIdInput);
		String customerIdOutput = getCustomerDetailsPage().getCustomerID();
		assertContains("Selected Customer Details is Displayed. Customed ID is "+"<b>"+customerIdOutput+"</b>", "Selected Customer Details is not Displayed", customerIdInput, customerIdOutput);
		getCustomerDetailsPage().clickCloseCustomer();
	}
	
	public void verifyCustomersDetailsPageCustomerName(String subsName, String projectName,String customerIdInput, String expectedCustomerName) {
		navigateTillCustomerDetailsPage(subsName, projectName, customerIdInput);
		String actualCustomerName = getCustomerDetailsPage().getCustomerName();
		assertContains("Selected Customer Details is Displayed Customer Name is "+"<b>"+actualCustomerName+"</b>", "Selected Customer Details is not Displayed", expectedCustomerName, actualCustomerName);
		getCustomerDetailsPage().clickCloseCustomer();
	}
	
	public void verifyCustomersDetailsPageCustomerInfoDisplay(String subsName, String projectName, String customerIdInput) {
		navigateTillCustomerDetailsPage(subsName, projectName, customerIdInput);
		boolean allAttributesAvalability = getCustomerDetailsPage().verifyAllCustomerDocumentAttributes();
		assertEquals("All the Customer Details is Displayed Properly","All the Customer Details is not Displayed Properly", true, allAttributesAvalability);
		getCustomerDetailsPage().clickCloseCustomer();
	}
	
	public void verifyCustomersDetailsPagePrev_Next_Close_Functionality(String subsName, String projectName) {
		navigateTillVoicedCustomerPage(subsName, projectName);
		getVoicedCustomerPage().clickOnFirstCustomer();
		String custIdBefore = getCustomerDetailsPage().getCustomerID();
		String presentCustomerUrl = getPage().url();
		getCustomerDetailsPage().clickNextButton();
		String nextCustomerUrl = getPage().url();
		boolean nextButtonResult = nextCustomerUrl.equals(presentCustomerUrl);
		assertEquals("Next Button is working fine","Next Button is not working", false, nextButtonResult);
		getCustomerDetailsPage().clickPreviousButton();
		String custIdAfter = getCustomerDetailsPage().getCustomerID();
		boolean previousButtonResult = custIdAfter.equals(custIdBefore);
		assertEquals("Previous Button is working fine","Previous Button is not working", true, previousButtonResult);
		getCustomerDetailsPage().clickCloseCustomer();
		boolean filterVisibility = getVoicedCustomerPage().checkTotalVoicedCustomerVisibility();
		assertEquals("Close Button is working fine","Close Button is not working", true, filterVisibility);
	}
	
	public void verifyCustomersDetailsPageCustomerSummarySection(String subsName, String projectName) {
		navigateTillVoicedCustomerPage(subsName, projectName);
		getVoicedCustomerPage().clickOnFirstCustomer();
		boolean summaryTitleVisibility = getCustomerDetailsPage().verifyCustomersSummaryTitle();
		System.out.println("Tile Res "+summaryTitleVisibility);
		assertEquals("Customer Summary Title is Visible","Customer Summary Title is not Visible", true, summaryTitleVisibility);
		boolean summaryAttributesVisibility = getCustomerDetailsPage().verifyAllCustomerSummaryAttributes();
		System.out.println("Attributes Res "+summaryAttributesVisibility);
		assertEquals("All the Customer Summary Attributes are Visible","All the Customer Summary Attributes are not Visible", true, summaryAttributesVisibility);
		getCustomerDetailsPage().clickCloseCustomer();
	}
	
	public void verifyCustomersDetailsNumberOfVoices(String subsName, String projectName,String customerId) {
		navigateTillVoicedCustomerPage(subsName, projectName);
		getVoicedCustomerPage().searchByID(customerId);
		waitTillLoading();
		String listPageVoiceNumber = getVoicedCustomerPage().getNumOfVoiceAfterFilter();
		getVoicedCustomerPage().clickOnCustomerByCustId(customerId);
		String detailsPageVoiceNumber = getCustomerDetailsPage().getNumOfVoicesValue();
		assertContains("Expected Voice Count is equal to Actual Voice Count", "Expected Voice Count is not equal to Actual Voice Count", detailsPageVoiceNumber, listPageVoiceNumber);
		getCustomerDetailsPage().clickCloseCustomer();
	}
	
	public void verifyCustomersDetailsTopSource(String subsName, String projectName,String customerId) {
		navigateTillVoicedCustomerPage(subsName, projectName);
		getVoicedCustomerPage().searchByID(customerId);
		waitTillLoading();
		String listPagePrimarySource = getVoicedCustomerPage().getPrimaryChannelAfterFilter();
		getVoicedCustomerPage().clickOnCustomerByCustId(customerId);
		String detailsPageTopChannel = getCustomerDetailsPage().getTopChannelValue();
		assertContains("Expected Top Source is equal to Actual Top Source", "Expected Top Source is not equal to Actual Top Source", listPagePrimarySource, detailsPageTopChannel);
		getCustomerDetailsPage().clickCloseCustomer();
	}
	
//	public void verifyCustomersDetailsSentimentChart(String customerId) {
//		navigateTillVoicedCustomerPage(subsName, projectName);
//		getVoicedCustomerPage().searchByID(customerId);
//		waitTillLoading();
//		String listPagePrimarySource = getVoicedCustomerPage().getPrimaryChannelAfterFilter();
//		getVoicedCustomerPage().clickOnCustomerByCustId(customerId);
//		String detailsPageTopChannel = getCustomerDetailsPage().getTopChannelValue();
//		assertContains("Expected Top Source is equal to Actual Top Source", "Expected Top Source is not equal to Actual Top Source", listPagePrimarySource, detailsPageTopChannel);
//		getCustomerDetailsPage().clickCloseCustomer();
//	}
	
	
	public void verifyCustomersDetailsVoiceDetailedReport(String subsName, String projectName,String customerId) {
		navigateTillVoicedCustomerPage(subsName, projectName);
		getVoicedCustomerPage().searchByID(customerId);
		getVoicedCustomerPage().clickOnCustomerByCustId(customerId);
		boolean voiceDetailedVisibilityResult = getCustomerDetailsPage().checkVoiceDetailedReportVisibility();
		assertEquals("Voice Detailed Report Button is Visible","Voice Detailed Report Button is not Visible", true, voiceDetailedVisibilityResult);
		String detailsPageVoiceNumber = getCustomerDetailsPage().getNumOfVoicesValue();
		getPage().waitForTimeout(2000);
		getCustomerDetailsPage().clickVoiceDetailedReport();
		getPage().waitForTimeout(2000);
		String dashboardTitle = getDashboardPage().getDashboardTitle();
		assertContains("Dashboard Page is Visible after Clicking Voice Detailed Report Button", "Dashboard Page is not Visible after Clicking Voice Detailed Report Button", dashboardTitle, "Dashboard");
		int dashboardPageVoiceNumber=getDashboardPage().getNumberOfVoicesCount();
		assertContains("Voice Count form Dashboard Page is equal to Voice Detailed Page", "Voice Count form Dashboard Page is not equal to Voice Detailed Page", String.valueOf(dashboardPageVoiceNumber), detailsPageVoiceNumber);
	}
	
	public void verifyCustomersDetailsViewAllVoice(String subsName, String projectName,String customerId) {
		navigateTillVoicedCustomerPage(subsName, projectName);
		getVoicedCustomerPage().searchByID(customerId);
		getVoicedCustomerPage().clickOnCustomerByCustId(customerId);
		waitTillLoading();
		boolean voiceDetailedVisibilityResult = getCustomerDetailsPage().checkViewAllVoicesVisibility();
		assertEquals("View All Voices Button is Visible","View All Voices Button is not Visible", true, voiceDetailedVisibilityResult);
		getCustomerDetailsPage().clickViewAllVoices();
		String voicePageTitle = getVoicesListPage().getTitle();
		assertContains("Voice List Page is Visible after Clicking View All Voices Button", "Voice List Page is not Visible after Clicking View All Voices Button", voicePageTitle, "Voices");
	}
	
	public void verifyCustomersDetailsCustomerTypeDisplay(String subsName, String projectName) {
		navigateTillVoicedCustomerPage(subsName, projectName);
		getVoicedCustomerPage().clickOnFirstCustomer();
		waitTillLoading();
		boolean customerTypeVisibility = getCustomerDetailsPage().checkCustomerTypeVisibility();
		String customerType = getCustomerDetailsPage().getCustomerType();
		assertEquals("Customer Type is Visible and Customer Type is "+"<b>"+customerType+"</b>", "Customer Type is not Visible", true, customerTypeVisibility);
		getCustomerDetailsPage().clickCloseCustomer();
	}
	
	public void verifyCustomersListPageDayWeekMonth(String subsName, String projectName) {
		navigateTillVoicedCustomerPage(subsName, projectName);
		getVoicedCustomerPage().clickOnTodayWeekMonthDropdown();
		boolean todayWeekMonthVisibility = getVoicedCustomerPage().checkTodayWeekMonthVisibility();
		assertEquals("Day, Week, Month options are Visible","Day, Week, Month options are not Visible", true, todayWeekMonthVisibility);
		getPage().reload();
	}
	
	public void verifyCustomersListDateRangeSelector(String subsName, String projectName) {
		navigateTillVoicedCustomerPage(subsName, projectName);
		boolean dateRangeVisibility = getVoicedCustomerPage().checkDateRangeFilterVisibility();
		assertEquals("Date Range Filter is Visible","Date Range Filter is not Visible", true, dateRangeVisibility);
		getVoicedCustomerPage().clickDateRangeFilter();
		boolean monthYearVisibility = getVoicedCustomerPage().checkMonthAndYearVisibility();
		assertEquals("Month and Year Dropdown is Visible","Month and Year Dropdown is not Visible", true, monthYearVisibility);
		boolean calenderVisibility = getVoicedCustomerPage().checkCalenderVisibility();
		assertEquals("Calender is Visible to select the Dates","Calender is not Visible", true, calenderVisibility);
		boolean todayYesterdayElementsVisibility = getVoicedCustomerPage().checkTodayYesterdayElementAvailabilityInDateRangeFilter();
		assertEquals("Day, Week, Month, Custom, and all the options are Visible","All the options are not Visibile", true, todayYesterdayElementsVisibility);
		getPage().reload();
	}
	
	public void verifyCustomersDetailsTopService(String subsName, String projectName,String customerId) {
		navigateTillVoicedCustomerPage(subsName, projectName);
		getVoicedCustomerPage().searchByID(customerId);
		getVoicedCustomerPage().clickOnCustomerByCustId(customerId);
		waitTillLoading();
		String detailsPageTopService = getCustomerDetailsPage().getTopServiceValue();
		System.out.println("Deatils Page "+detailsPageTopService);
		getCustomerDetailsPage().clickNoOfVoices();
		String voicePageTitle=getTitleFromPopupFrame();
		System.out.println("Voice Title "+voicePageTitle);
		assertContains("Voice List Page is Visible after Clicking View All Voices Button", "Voice List Page is not Visible after Clicking View All Voices Button", voicePageTitle, "Voices");
		Frame frame = getFrame();
		String listPageTopService = frame.locator("//app-widget-header//h4[contains(text(),'Top Service')]//ancestor::app-query-widget/div//h4").first().textContent().trim();
		System.out.println("List Page "+listPageTopService);
		assertContains("Actual Top Service is equal to Expected Top Service", "Actual Top Service is not equal to Expected Top Service", listPageTopService, detailsPageTopService);
		closePopupFrame();
	}
	
	public void verifyCustomersDetailsTopSQL(String subsName, String projectName,String customerId) {
		navigateTillVoicedCustomerPage(subsName, projectName);
		getVoicedCustomerPage().searchByID(customerId);
		getVoicedCustomerPage().clickOnCustomerByCustId(customerId);
		waitTillLoading();
		String detailsPageTopSql = getCustomerDetailsPage().getTopSqlValue();
		System.out.println("Deatils Page "+detailsPageTopSql);
		getCustomerDetailsPage().clickNoOfVoices();
		String voicePageTitle=getTitleFromPopupFrame();
		assertContains("Voice List Page is Visible after Clicking View All Voices Button", "Voice List Page is not Visible after Clicking View All Voices Button", voicePageTitle, "Voices");
		Frame frame = getFrame();
		String listPageTopSql = frame.locator("//app-widget-header//h4[contains(text(),'Top SQL')]//ancestor::app-query-widget/div//h4").first().textContent().trim();
		System.out.println("List Page "+listPageTopSql);
		assertContains("Actual Top SQL is equal to Expected Top Service", "Actual Top SQL is not equal to Expected Top Service", listPageTopSql, detailsPageTopSql);
		closePopupFrame();
	}
	
	public void verifyCustomersDetailsVoiceDetailedReportDashboardCancelButton(String subsName, String projectName,String customerId) {
		navigateTillVoicedCustomerPage(subsName, projectName);
		getVoicedCustomerPage().searchByID(customerId);
		getVoicedCustomerPage().clickOnCustomerByCustId(customerId);
		waitTillLoading();
		boolean voiceDetailedVisibilityResultBefore = getCustomerDetailsPage().checkVoiceDetailedReportVisibility();
		assertEquals("Voice Detailed Report Button is Visible","Voice Detailed Report Button is not Visible", true, voiceDetailedVisibilityResultBefore);
		getPage().waitForTimeout(2000);
		getCustomerDetailsPage().clickVoiceDetailedReport();
		String dashboardTitle = getDashboardPage().getDashboardTitle();
		assertContains("Dashboard Page is Visible after Clicking Voice Detailed Report Button", "Dashboard Page is not Visible after Clicking Voice Detailed Report Button", dashboardTitle, "Dashboard");
		getDashboardPage().clickOnCancel();
		//boolean voiceDetailedVisibilityResultAfter = getPage().locator("//div[contains(text(),'Voice Detailed Report')]").isVisible();
		assertEquals("After clicking on Cancel Dashboard, Dashboard Page is Refreshed","After clicking on Cancel Dashboard, Dashboard Page is not Refreshed", dashboardTitle, "Dashboard");
	}
	
	public void verifyCustomersListPageSearch(String subsName, String projectName,String searchText) {
		navigateTillVoicedCustomerPage(subsName, projectName);
		getVoicedCustomerPage().searchBySearchText(searchText);
		waitTillLoading();
		boolean searchResult = getVoicedCustomerPage().nameAvailabilityInTheList(searchText);
		assertEquals("Searched Result is Visible in the List","Searched Result is not Visible in the List", true, searchResult);
		
	}
	
	
	
	
	

}
