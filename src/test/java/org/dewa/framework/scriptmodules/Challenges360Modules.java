package org.dewa.framework.scriptmodules;

import static org.dewa.framework.utils.BaseClass.assertContains;
import static org.dewa.framework.utils.BaseClass.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Page;

public class Challenges360Modules extends ParentModule{

	public Challenges360Modules(Page page) {
		super(page);
		// TODO Auto-generated constructor stub
	}
	
	public void verify360ChallengesListPageTitle(String subsName, String projectName) {
		navigateTill360_ChallengesListPage(subsName, projectName);
		String title = getVoicedCustomerPage().getVoicedCustomersTitle();
		assertContains("360 Challenges List Page is Visibile ", "360 Challenges List Page is not Visible", "360 Challenges", title);
	}
	
	public void verifyTabs(String subsName, String projectName) {
		navigateTill360_ChallengesListPage(subsName, projectName);
		List<String> allTabs = getChallenges360ListPage().getAllTabsText();
		System.out.println("Total Tab Count "+allTabs.size());
		List<String> expectedTabs=new ArrayList<String>();
		expectedTabs.add("All Problem");
		expectedTabs.add("Time Sensitive");
		for(int i=0;i<allTabs.size();i++) {
			assertContains("Expected Tab is Equal to Actual Tab", "Expected Tab is not Equal to Actual Tab", allTabs.get(i), expectedTabs.get(i));
		}
	}
	
	public void verifyStatsTotalChallenges(String subsName, String projectName) {
		navigateTill360_ChallengesListPage(subsName, projectName);
		int totalChallengesCount = getChallenges360ListPage().getTotalChallengesCount();
		System.out.println("Total Challenges Count : "+totalChallengesCount);
		assertEquals("Total Challenges Count is Visible", "Total Challenges Count is not Visible", true, totalChallengesCount>0);
		extentTest.get().log(Status.PASS, "Total Challenges Count is : "+totalChallengesCount);
	}
	
	public void verifyStatsTopChallenge(String subsName, String projectName) {
		navigateTill360_ChallengesListPage(subsName, projectName);
		String widgetValue = getChallenges360ListPage().getWidgetValue("Top Challenge").replaceAll("[^0-9]+", " ").trim().split(" ")[0];
		System.out.println("Widget Value : "+widgetValue);
		assertEquals("Top Challenge Count is Visible. <br> Top Challenge Count : <b>"+widgetValue+"</b> </br>", "TTop Challenge Count is not Visible", true, Integer.valueOf(widgetValue)>0);
	}
	
	public void verifyHideFilters(String subsName, String projectName) {
		navigateTill360_ChallengesListPage(subsName, projectName);
		getVoicedCustomerPage().clickOnShowFilter();
		boolean filiterPresenseBefore = getVoicedCustomerPage().checkFilterAvailability();
		assertEquals("Filter Options are available after clicking on Show Filter","Filter Options are not available after clicking on Show Filter", true, filiterPresenseBefore);
		getVoicedCustomerPage().clickOnShowFilter();
		boolean filiterPresenseAfter = getPage().locator("//div[@class='search-filter-header']").isVisible();
		assertEquals("Filter Options are hidden after clicking Hide Filter","Filter Options are not hidden after clicking Hide Filter", false, filiterPresenseAfter);
	}
	
	public void verifyIdSearch(String subsName, String projectName) {
		navigateTill360_ChallengesListPage(subsName, projectName);
		String challengeId="7c6f4f85-6654-41f5-a141-05631a88e1e6";
		getVoicesListPage().filterDocumentByTitle(challengeId);
		boolean searchResult = getVoicesListPage().checkResultsForSearchedVoidId();
		System.out.println("Search Result : "+searchResult);
	}
	
	public void verifyChallengesListPageTableHeaders(String subsName, String projectName) {
		navigateTill360_ChallengesListPage(subsName, projectName);
		List<String> allExpectedTableHeaders=new ArrayList<String>();
		allExpectedTableHeaders.add("ID");
		allExpectedTableHeaders.add("Title & Date");
		allExpectedTableHeaders.add("No of Voice");
		allExpectedTableHeaders.add("Problem");
		allExpectedTableHeaders.add("AI Recommendation");
		allExpectedTableHeaders.add("Affected Aspect");
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

	
}
