package org.dewa.framework.scriptmodules;

import static org.dewa.framework.utils.BaseClass.assertContains;
import static org.dewa.framework.utils.BaseClass.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.dewa.framework.utils.ExcelUtility;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Page;

public class VoiceListPageModules extends ParentModule {

	public VoiceListPageModules(Page page) {
		super(page);
		// TODO Auto-generated constructor stub
	}

	public void afterMethod() {
		if (getPage().locator("//*[contains(@tooltip,'Close')]").isVisible()) {
			getPage().locator("//*[contains(@tooltip,'Close')]").click();
		}
		getHomePage().clickOnHomeButton();
		getPage().waitForTimeout(2000);
		getPage().reload();
	}

	public void verifyVoiceListPageTitle(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		String title = getVoicedCustomerPage().getVoicedCustomersTitle();
		assertContains("Voice Page is Visibile ", "Voice Page is not Visible", "Voices", title);
	}

	public void verifyVoiceListPageComplaintTab(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().clickOnPageTab("Complaints");
		String url = getPage().url();
		System.out.println("Url is :" + url);
		assertContains("Complaints Page is Visibile ", "Complaints Page is not Visible", "Complaints", url);
	}

	public void verifyVoiceListPageSuggestionsTab(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().clickOnPageTab("Suggestions");
		String url = getPage().url();
		System.out.println("Url is :" + url);
		assertContains("Suggestions Page is Visibile ", "Suggestions Page is not Visible", "Suggestions", url);
	}

	public void verifyVoiceListPageUnhappyNeutralTab(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().clickOnPageTab("Unhappy and Neutral");
		String url = getPage().url();
		System.out.println("Url is :" + url);
		assertContains("Unhappy and Neutral Page is Visibile ", "Unhappy and Neutral Page is not Visible", "Unhappy",
				url);
	}

	public void verifyVoiceListPageWidgetStats(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		List<String> allExpectedWidgetsName=new ArrayList<String>();
		allExpectedWidgetsName.add("Top Service");
		allExpectedWidgetsName.add("Top SQL");
		allExpectedWidgetsName.add("Top Subject");
		allExpectedWidgetsName.add("Unhappy Channel");
		allExpectedWidgetsName.add("Top Source");
		allExpectedWidgetsName.add("Top Voice Type");
		List<String> allActualWidgetsName = getVoicesListPage().getAllWidgetsNames();
		System.out.println("Actual Widgets Count : "+allActualWidgetsName.size());
		for(String eachActualWidget:allActualWidgetsName) {
			System.out.println(eachActualWidget);
		}
		
		for (String eachExpectedWidget : allExpectedWidgetsName) {
			String expectedResult = eachExpectedWidget;
			boolean found = false;
			String actualResult = null;
			for (String eachActualWidget : allActualWidgetsName) {
				actualResult = eachActualWidget;
				if (expectedResult.contains(actualResult)) {
					found = true;
					break;
				}
			}
			System.out.println("Widget Name '" + expectedResult + "' exists in actual list: " + found);
			assertContains("Expected Widget Name is equals to Actual Widget Name",
					"Expected Widget Name is not equals to Actual Widget Name", actualResult, expectedResult);
		}
		
		boolean res = getVoicesListPage().checkAllWidgetsAvailibiltyInVoiceListPage();
		assertEquals("All the Widgets are visible in Voice List Page", "All the widgets are not available on ", true,
				res);
		
	}

	public void verifyVoiceListPageVoiceListSectionHeaders(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		List<String> allExpectedTableHeaders=new ArrayList<String>();
		//allExpectedTableHeaders.add("ID");
		allExpectedTableHeaders.add("Title & Date");
		allExpectedTableHeaders.add("Data Source");
		allExpectedTableHeaders.add("Channel");
		allExpectedTableHeaders.add("Voice Type");
		allExpectedTableHeaders.add("Subject");
		allExpectedTableHeaders.add("Services");
		allExpectedTableHeaders.add("SQL");
		allExpectedTableHeaders.add("Sentiments");
		allExpectedTableHeaders.add("AI Sentiment");
		allExpectedTableHeaders.add("Comment");
		allExpectedTableHeaders.add("Challenge ID");
		List<String> allActualTableHeaders = getVoicesListPage().getAllVoiceTableHeaders();
		
		System.out.println("Actual Headers count : "+allActualTableHeaders.size());
		for (String eachExpectedWidget : allExpectedTableHeaders) {
			String expectedResult = eachExpectedWidget;
			boolean found = false;
			String actualResult = null;
			for (String eachActualWidget : allActualTableHeaders) {
				System.out.println("Actual Headers : "+eachActualWidget);
				actualResult = eachActualWidget;
				if (expectedResult.contains(actualResult)) {
					found = true;
					break;
				}
			}
			System.out.println("Header Name '" + expectedResult + "' exists in actual list: " + found);
			assertContains("Expected Table Header Name is equals to Actual Table Header Name",
					"Expected Table Header Name is not equals to Actual Table Header Name", actualResult, expectedResult);
		}
		
		boolean res = getVoicesListPage().checkAllTheVoiceTableHeaders();
		assertEquals("All the Headers in the Voice List Table are visible",
				"All the Headers in the Voice List Table are not visible", true, res);
	}

	public void verifyVoiceListPageSingleFilters(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		int initialVoiceCount = getVoicesListPage().getTotalNumberOfVoice();

		getVoicesListPage().filterVoicesOnFilter("Voice Sentiments", "Happy");
		int happyFilterVoiceCount = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Voice Sentinemts Filter Count: " + happyFilterVoiceCount);
		boolean happyFilterPresence = getVoicesListPage().checkFilterPresenceOnTop("Happy");
		assertEquals("Voice Sentiments Happy Filter is Visible on top of the Page",
				"Voice Sentiments Happy Filter is not Visible on top of the Page", true, happyFilterPresence);
		assertEquals("Voice Sentinemts Filter is Working fine", "Voice Sentinemts Filter is not Working", false,
				happyFilterVoiceCount == initialVoiceCount);
		extentTest.get().log(Status.INFO, "Voice Sentinemts Filter Count: <b>" + happyFilterVoiceCount + "</b>");
		getVoicesListPage().clearFilter();
		
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Channel List", "Website");
		int websiteFilterVoiceCount = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Channel List Filter Count: " + websiteFilterVoiceCount);
		boolean websiteFilterPresence = getVoicesListPage().checkFilterPresenceOnTop("Website");
		assertEquals("Channel List Website Filter is Visible on top of the Page",
				"Channel List Website Filter is not Visible on top of the Page", true, websiteFilterPresence);
		assertEquals("Channel List Filter is Working fine", "Channel List Filter is not Working", false,
				websiteFilterVoiceCount == initialVoiceCount);
		extentTest.get().log(Status.INFO, "Channel List Filter Count: <b>" + websiteFilterVoiceCount + "</b>");
		getVoicesListPage().clearFilter();

		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Subject", "EV Charger");
		int subjectFilterVoiceCount = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Subject Filter Count: " + subjectFilterVoiceCount);
		boolean evChargerFilterPresence = getVoicesListPage().checkFilterPresenceOnTop("EV Charger");
		assertEquals("Subject EV Charger Filter is Visible on top of the Page",
				"Subject EV Charger Filter is not Visible on top of the Page", true, evChargerFilterPresence);
		assertEquals("Subject Filter is Working fine", "Subject Filter is not Working", false,
				subjectFilterVoiceCount == initialVoiceCount);
		extentTest.get().log(Status.INFO, "Subject Filter Count: <b>" + subjectFilterVoiceCount + "</b>");
		getVoicesListPage().clearFilter();

		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Voice Types", "Complaint");
		int voceTypeFilterVoiceCount = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Voice Types Filter Count: " + voceTypeFilterVoiceCount);
		boolean chargerFilterPresence = getVoicesListPage().checkFilterPresenceOnTop("Complaint");
		assertEquals("Voice Types Complaint Filter is Visible on top of the Page",
				"Voice Types Complaint Filter is not Visible on top of the Page", true, chargerFilterPresence);
		assertEquals("Voice Type Filter is Working fine", "Voice Type Filter is not Working", false,
				voceTypeFilterVoiceCount == initialVoiceCount);
		extentTest.get().log(Status.INFO, "Voice Types Filter Count: <b>" + voceTypeFilterVoiceCount + "</b>");
		getVoicesListPage().clearFilter();

		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Service Quality Level", "Accessibility");
		int sqlFilterVoiceCount = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("SQL Filter Count Accessibility: " + sqlFilterVoiceCount);
		boolean accessibilityFilterPresence = getVoicesListPage().checkFilterPresenceOnTop("Accessibility");
		assertEquals("SQL Accessibility Filter is Visible on top of the Page",
				"SQL Accessibility Filter is not Visible on top of the Page", true, accessibilityFilterPresence);
		assertEquals("SQL Filter is Working fine", "SQL Filter is not Working", false,
				sqlFilterVoiceCount == initialVoiceCount);
		extentTest.get().log(Status.INFO, "SQL Filter Count: <b>" + sqlFilterVoiceCount + "</b>");
		getVoicesListPage().clearFilter();

		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Services", "Billing Services");
		int serviceFilterVoiceCount = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Service Filter Count: " + serviceFilterVoiceCount);
		boolean billingServicesFilterPresence = getVoicesListPage().checkFilterPresenceOnTop("Billing Services");
		assertEquals("Services Billing Services Filter is Visible on top of the Page",
				"Services Billing Services Filter is not Visible on top of the Page", true,
				billingServicesFilterPresence);
		assertEquals("Service Filter is Working fine", "Service Filter is not Working", false,
				serviceFilterVoiceCount == initialVoiceCount);
		extentTest.get().log(Status.INFO, "Service Filter Count: <b>" + serviceFilterVoiceCount + "</b>");
		getVoicesListPage().clearFilter();
	}

	public void verifyVoiceListPageMultipleFilters(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		int initialVoiceCount = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Initial Voice Count: " + initialVoiceCount);
		getVoicesListPage().filterVoicesOnMultipleFilter("Voice Sentiments", "Happy", "Neutral");
		int voiceSentimentFilterVoiceCount = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Voice Sentimemts Filter Count for Happy and Neutral: " + voiceSentimentFilterVoiceCount);
		boolean voiceSentimentFilterPresence = getVoicesListPage().checkMultipleFilterPresenceOnTop("Happy", "Neutral");
		assertEquals("Voice Sentiments Happy, Neutral Filter is Visible on top of the Page",
				"Voice Sentiments Happy, Neutral Filter is not Visible on top of the Page", true, voiceSentimentFilterPresence);
		assertEquals("Voice Sentinemts Multiple Filter is Working fine", "Voice Sentinemts Multiple Filter is not Working", false,
				voiceSentimentFilterVoiceCount == initialVoiceCount);
		extentTest.get().log(Status.INFO, "Voice Sentinemts Count for Happy and Neutral: <b>" + voiceSentimentFilterVoiceCount + "</b>");
		getVoicesListPage().clearFilter();
		
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnMultipleFilter("Service Quality Level", "Accessibility", "Ease of use");
		int sqlFilterVoiceCount = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("SQL Count for Accessibility and Ease of use: " + sqlFilterVoiceCount);
		boolean sqlFilterPresence = getVoicesListPage().checkMultipleFilterPresenceOnTop("Accessibility", "Ease of use");
		assertEquals("SQL Accessibility, Ease of use Filter is Visible on top of the Page",
				"SQL Accessibility, Ease of use Filter is not Visible on top of the Page", true, sqlFilterPresence);
		assertEquals("SQL Multiple Filter is Working fine", "SQL Multiple Filter is not Working", false,
				sqlFilterVoiceCount == initialVoiceCount);
		extentTest.get().log(Status.INFO, "SQL Count for Accessibility and Ease of use: <b>" + sqlFilterVoiceCount + "</b>");
		getVoicesListPage().clearFilter();
		
		getVoicesListPage().showFilter();
		getVoicesListPage().selectMultipleFiltersInOneGo("Voice Types", "Complaint", "Subject", "EV Charger");
		int multipleFilterVoiceCount = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Voice Count for Complaint Voice Type and EV Charger Subject: " + multipleFilterVoiceCount);
		boolean complaintFilterPresence = getVoicesListPage().checkFilterPresenceOnTop("Complaint");
		boolean evChargerFilterPresence = getVoicesListPage().checkFilterPresenceOnTop("EV Charger");
		assertEquals("Complaint Voice Types and EV Charger Subject Filter is Visible on top of the Page",
				"Complaint Voice Types and EV Charger Subject Filter is not Visible on top of the Page", true, complaintFilterPresence&&evChargerFilterPresence);
		assertEquals("Voice Type and Subject Multiple Filter is Working fine", "Voice Type and Subject Multiple Filter is not Working", false,
				multipleFilterVoiceCount == initialVoiceCount);
		extentTest.get().log(Status.INFO, "Voice Count for Complaint Voice Type and EV Charger Subject: <b>" + sqlFilterVoiceCount + "</b>");
		getVoicesListPage().clearFilter();
	}
	
	public void verifyVoiceListClearFilters(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		int initialVoiceCount = getVoicesListPage().getTotalNumberOfVoice();
		getVoicesListPage().filterVoicesOnFilter("Voice Sentiments", "Happy");
		boolean happyFilterPresence = getVoicesListPage().checkFilterPresenceOnTop("Happy");
		assertEquals("Voice Sentiments Happy Filter is applied",
				"Voice Sentiments Happy Filter is not applied", true, happyFilterPresence);
		int happyFilterVoiceCount = getVoicesListPage().getTotalNumberOfVoice();
		assertEquals("Voice Sentinemts Filter is Working fine", "Voice Sentinemts Filter is not Working", false,
				happyFilterVoiceCount == initialVoiceCount);
		getVoicesListPage().clearFilter();
		boolean happyClearFilterPresence = getVoicesListPage().checkFilterPresenceOnTop("Happy");
		assertEquals("After Clearing the filter, Happy Filter is not Visible on top of the Page",
				"After Clearing the filter, Happy Filter is still Visible on top of the Page", false,
				happyClearFilterPresence);
	}
	
	public void verifyVoiceListHideFilters(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		assertEquals("Show Filter is visible in Voice List Page",
				"Show Filter is not visible in Voice List Page", true, getVoicesListPage().showFilterVisibility());
		getVoicesListPage().clickShowFilter();
		assertEquals("Hide Filter is visible in Voice List Page",
				"Hide Filter is not visible in Voice List Page", true, getVoicesListPage().hideFilterVisibility());
		getVoicesListPage().clickHideFilter();
		assertEquals("Hide Filter is hidden after clicking on Hide Filters",
				"Hide Filter is not hidden after clicking on Hide Filters", false, getVoicesListPage().hideFilterVisibility());
	}
	
	public void verifyVoiceListExcelExport(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().clickExport();
		boolean downloadSuccessStatus = getVoicesListPage().checkDownloadSuccessfullMessage();
		assertEquals("Excel File Exported Successfully","Excel File is not Exported", true, downloadSuccessStatus);
	}
	
	public void verifyVoiceListExportedExcelFile(String subsName, String projectName) {
		final String[] additionalPath = new String[1];
		String path=System.getProperty("user.dir")+"/ExportedExcel";
		downloadPdfSetup(path, additionalPath, getPage());
		additionalPath[0] = "VoiceList.xlsx";
		verifyVoiceListExcelExport(subsName, projectName);
		ExcelUtility excel=new ExcelUtility(path + "\\" + additionalPath[0]);
	}
	
	public void verifyPagination(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().clickOnNextPreviousBtn("next");
		assertEquals("Next Button is Working Successfully","Next Button is not Working", true, getVoicesListPage().checkPrevBtnDisability());
		getVoicesListPage().clickOnNextPreviousBtn("prev");
		assertEquals("Previous Button is Working Successfully","Previous Button is not Working", false, getVoicesListPage().checkPrevBtnDisability());
	}
	
	public void verifyShowAllFunctionality(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		int totalnumOfVoices = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Total Voices : "+totalnumOfVoices);
		getVoicesListPage().clickOnWidget("Top Service");
		int topServiceCount = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Top Service Count : "+topServiceCount);
		getVoicesListPage().clickOnShowAll();
		int voiceCountAfterShowAll = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Voice Count after Show All : "+voiceCountAfterShowAll);
		assertContains("Show All Button is working fine", "Show All Button is not working", String.valueOf(voiceCountAfterShowAll), String.valueOf(totalnumOfVoices));
	}
	
	public void verifyUnhappyChannelWidget(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		String widgetCount = getVoicesListPage().getHeaderWidgetsCount("Unhappy Channel");
		System.out.println("Widget Count for Unhappy Channel : "+widgetCount);
		getVoicesListPage().clickOnWidget("Unhappy Channel");
		int overAllCountAfterUnhappyCount = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Overall Count for Unhappy Channel : "+overAllCountAfterUnhappyCount);
		assertContains("Unhappy Widget is Working fine", "Unhappy Widget is not Working", String.valueOf(overAllCountAfterUnhappyCount), widgetCount);
	}
	
	public void verifyTopServiceWidget(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		String widgetCount = getVoicesListPage().getHeaderWidgetsCount("Top Service");
		System.out.println("Widget Count for Top Service : "+widgetCount);
		getVoicesListPage().clickOnWidget("Top Service");
		int overAllCount = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Overall Count for Top Service : "+overAllCount);
		assertContains("Top Service Widget is Working fine", "Top Service Widget is not Working", String.valueOf(overAllCount), widgetCount);
	}
	
	public void verifyTopSqlWidget(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		String widgetCount = getVoicesListPage().getHeaderWidgetsCount("Top SQL");
		System.out.println("Widget Count for Top SQL : "+widgetCount);
		getVoicesListPage().clickOnWidget("Top SQL");
		int overAllCount = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Overall Count for Top SQL : "+overAllCount);
		assertContains("Top SQL Widget is Working fine", "Top SQL Widget is not Working", String.valueOf(overAllCount), widgetCount);
	}
		
	public void verifyTopSubjectWidget(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		String widgetCount = getVoicesListPage().getHeaderWidgetsCount("Top Subject");
		System.out.println("Widget Count for Top Subject : "+widgetCount);
		getVoicesListPage().clickOnWidget("Top Subject");
		int overAllCount = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Overall Count for Top Subject : "+overAllCount);
		assertContains("Top Subject Widget is Working fine", "Top Subject Widget is not Working", String.valueOf(overAllCount), widgetCount);
		 //assertContains("Top Subject Widget is Working fine", 
         //        "Top Subject Widget is not Working", 
          //       String.valueOf(overAllCount), 
          //       String.valueOf(widgetCount));
	}
	
	
	public void verifyTopSourceWidget(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		String widgetCount = getVoicesListPage().getHeaderWidgetsCount("Top Source");
		System.out.println("Widget Count for Top Source : "+widgetCount);
		getVoicesListPage().clickOnWidget("Top Source");
		int overAllCount = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Overall Count for Top Source : "+overAllCount);
		//assertContains("Top Source Widget is Working fine", "Top Source Widget is not Working", String.valueOf(overAllCount), widgetCount);
		assertEquals("Top Source Widget is Working fine", "Top Source Widget is not Working", 
                String.valueOf(overAllCount), String.valueOf(widgetCount));
	}
	
	public void verifyTopVoiceTypeWidget(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		String widgetCount = getVoicesListPage().getHeaderWidgetsCount("Top Voice Type");
		System.out.println("Widget Count for Top Voice Type : "+widgetCount);
		getVoicesListPage().clickOnWidget("Top Voice Type");
		int overAllCount = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Overall Count for Top Voice Type : "+overAllCount);
		//assertContains("Top Voice Type Widget is Working fine", "Top Voice Type Widget is not Working", String.valueOf(overAllCount), widgetCount);
		assertEquals("Top Voice Type Widget is Working fine", "Top Voice Type Widget is not Working", 
                String.valueOf(overAllCount), String.valueOf(widgetCount));
		//}
	}
	
	public void verifyShowResult(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		int initialRowCount = getVoicesListPage().getRowCountInTable();
		System.out.println("Initial Row Count : "+initialRowCount);
		getVoicesListPage().increaseTableCount(50);
		int afterRowCount = getVoicesListPage().getRowCountInTable();
		System.out.println("After Row Count : "+afterRowCount);
		assertEquals("Show Result is Working Fine", "Show Result is not Working Fine", false, afterRowCount==initialRowCount);
	}
	
	public void viewDetailedReport(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		int initialVoiceCount = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Initial Voice Count : "+initialVoiceCount);
		String widgetCount = getVoicesListPage().getHeaderWidgetsCount("Top SQL");
		System.out.println("Widget Count for Top SQL : "+widgetCount);
		getVoicesListPage().clickOnWidget("Top SQL");
		getVoicesListPage().clickViewDetailedReport();
		int topSqlDashboardCount = getDashboardPage().getNumberOfVoicesCountFromFrame();
		System.out.println("Dashboard Top Sql Count "+topSqlDashboardCount);
		assertEquals("Top SQL count is equal, View Detailed Report is working fine", "Top SQL count is not equal, View Detailed Report is not working", Integer.parseInt(widgetCount), topSqlDashboardCount);
	}
	
	
	public void dayWeekMonth(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		int initialVoiceCount = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Initial Voice Count "+initialVoiceCount);
		getVoicesListPage().filerVoicesOnDayWeekMonth("Month");
		getVoicesListPage().filerVoicesOnDayWeekMonth("Week");
		getVoicesListPage().filerVoicesOnDayWeekMonth("Today");
		getVoicesListPage().filerVoicesOnDayWeekMonth("Yesterday");
		
	}	
}
