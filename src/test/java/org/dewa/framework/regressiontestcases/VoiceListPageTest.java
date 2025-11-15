package org.dewa.framework.regressiontestcases;

import org.dewa.framework.basefunctions.CommonFunctions;
import org.dewa.framework.listener.RetryListener;
import org.dewa.framework.scriptmodules.VoiceListPageModules;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(org.dewa.framework.listener.ListenerImplementation.class)
public class VoiceListPageTest extends CommonFunctions {

	VoiceListPageModules modules = new VoiceListPageModules(getPage());
	String subsName = prop.getProperty("subscriptionName");
	String projectName = prop.getProperty("projectName");

	@BeforeClass(alwaysRun = true)
	public void navigateToProjectFromHome() {
		String emailId = prop.getProperty("email");
		String password = prop.getProperty("password");
		new CommonFunctions().launchApplication(getUrl(), emailId, password);
		System.out.println("Before Class Executed");
	}

	// 1 and 2
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyVoiceListPageTitleTest() {
		modules.verifyVoiceListPageTitle(subsName, projectName);
	}

	// 3
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyVoiceListPageComplaintTabTest() {
		modules.verifyVoiceListPageComplaintTab(subsName, projectName);
	}

	// 4
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyVoiceListPageSuggestionsTabTest() {
		modules.verifyVoiceListPageSuggestionsTab(subsName, projectName);
	}

	// 5
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyVoiceListPageUnhappyNeutralTabTest() {
		modules.verifyVoiceListPageUnhappyNeutralTab(subsName, projectName);
	}

	// 6
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyVoiceListPageWidgetStatsTest() {
		modules.verifyVoiceListPageWidgetStats(subsName, projectName);
	}

	// 7
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyVoiceListPageVoiceListSectionHeadersTest() {
		modules.verifyVoiceListPageVoiceListSectionHeaders(subsName, projectName);
	}

	// 8
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyVoiceListPageSingleFiltersTest() {
		modules.verifyVoiceListPageSingleFilters(subsName, projectName);
	}

	// 9 
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyVoiceListPageMultipleFiltersTest() {
		modules.verifyVoiceListPageMultipleFilters(subsName, projectName);
	}
	
	// 10
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyVoiceListClearFiltersTest() {
		modules.verifyVoiceListClearFilters(subsName, projectName);
	}
	
	// 11
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyVoiceListHideFiltersTest() {
		modules.verifyVoiceListHideFilters(subsName, projectName);
	}
	
	// 12 Search Text Not working bug
	
	// 13 ID Search Already covered in Voice Page
	
	// 14 Day, Week, Month Depricated
	
	// 15 Date Range: Test Case to be updated
	
	// 16 //Bug download not triggered
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class, enabled = false)
	public void verifyVoiceListExcelExportTest() {
		modules.verifyVoiceListExcelExport(subsName, projectName);
	}
	
	// 17 Excel download is not happening bug
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class, enabled = false)
	public void verifyVoiceListExportedExcelFileTest() {
		modules.verifyVoiceListExportedExcelFile(subsName, projectName);
	}
	
	// 18
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyPaginationTest() {
		modules.verifyPagination(subsName, projectName);
	}
	
	// 19 Sorting : Depricated
	
	// 20 Bug
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyShowAllFunctionalityTest() {
		modules.verifyShowAllFunctionality(subsName, projectName);
	}

	//View Detailed Report
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyViewDetailedReportTest() {
		modules.viewDetailedReport(subsName, projectName);
	}
	
	// 22 
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyUnhappyChannelWidgetTest() {
		modules.verifyUnhappyChannelWidget(subsName, projectName);
	}
	
	// 23
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyTopServiceWidgetTest() {
		modules.verifyTopServiceWidget(subsName, projectName);
	}
	
	// 24
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyTopSqlWidgetTest() {
		modules.verifyTopSqlWidget(subsName, projectName);
	}
	
	// 25
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyTopSubjectWidgetTest() {
		modules.verifyTopSubjectWidget(subsName, projectName);
	}
	
	// 26
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyTopSourceWidgetTest() {
		modules.verifyTopSourceWidget(subsName, projectName);
	}
	
	// 27
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyTopVoiceTypeWidgetTest() {
		modules.verifyTopVoiceTypeWidget(subsName, projectName);
	}
	
	// 28
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyShowResultTest() {
		modules.verifyShowResult(subsName, projectName);
	}
	
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDayWeekMonthTest() {
		modules.dayWeekMonth(subsName, projectName);
	}
	
	
	
	
	// From 29 to 37 is already done in Voice Page
	
	
	
	
	
	
	
}

//8 Manual Failure Clear Filter is not working, Automation done
