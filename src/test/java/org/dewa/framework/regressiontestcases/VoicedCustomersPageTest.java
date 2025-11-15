package org.dewa.framework.regressiontestcases;

import org.dewa.framework.basefunctions.CommonFunctions;
import org.dewa.framework.listener.RetryListener;
import org.dewa.framework.scriptmodules.VoicedCustomersModules;
import org.dewa.framework.utils.PropertiesUtility;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(org.dewa.framework.listener.ListenerImplementation.class)
public class VoicedCustomersPageTest extends CommonFunctions {

	VoicedCustomersModules modules = new VoicedCustomersModules(getPage());
	String propertiesPath = System.getProperty("user.dir") + "/src/main/resources/VoicedCustomers.properties";
	PropertiesUtility properties = new PropertiesUtility(propertiesPath);
	
	String subsName = prop.getProperty("subscriptionName");
	String projectName = prop.getProperty("projectName");

	@BeforeClass(alwaysRun = true)
	public void navigateToProjectFromHome() {
		String emailId = prop.getProperty("email");
		String password = prop.getProperty("password");
		new CommonFunctions().launchApplication(getUrl(), emailId, password);
		System.out.println("Before Class Executed");
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() {
		System.out.println("Before Method Executed");
	}

	// 1
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyCustomersListPageTitleTest() {
		modules.verifyCustomerListPageTitle(subsName, projectName);
	}

	// 2
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyCustomersListPageAllCustomersTest() {
		modules.verifyCustomersListPageAllCustomers(subsName, projectName);
	}

	// 3
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyCustomersListPageConsumersTest() {
		modules.verifyCustomersListPageConsumers(subsName, projectName);
	}

	// 4
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyCustomersListPageBuildersTest() {
		modules.verifyCustomersListPageBuilders(subsName, projectName);
	}

	// 5
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyCustomersListPageCustomersListTest() {
		modules.verifyCustomersListPageCustomersList(subsName, projectName);
	}

	// 6
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyCustomersListPageTotalVoicedCutomerTest() {
		modules.verifyCustomersListPageTotalVoicedCutomer(subsName, projectName);
	}

	// 7
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyCustomersListPageCustomerSentimentTest() {
		modules.verifyCustomersListPageCustomerSentiment(subsName, projectName);
	}

	// 8
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyCustomersListPageMostEngagedConsumerStatTest() {
		modules.verifyCustomersListPageMostEngagedConsumerStat(subsName, projectName);
	}

	// 9
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyCustomersListPageMostActiveBuilderStatTest() {
		modules.verifyCustomersListPageMostActiveBuilderStat(subsName, projectName);
	}

	// 10
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyCustomersListPagePrimaryCustomerInteractionChannelStatTest() {
		modules.verifyCustomersListPagePrimaryCustomerInteractionChannelStat(subsName, projectName);
	}

	// 11
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyCustomersListPageCustomersRequiringAttentionStatTest() {
		modules.verifyCustomersListPageCustomersRequiringAttentionStat(subsName, projectName);
	}

	// 12
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyCustomersListPageFilterPresenseTest() {
		modules.verifyCustomersListPageFilterPresense(subsName, projectName);
	}

	// 13 Feature not available (Filter Feature)

	// 14
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyCustomersListPageFilterTest() {
		modules.verifyCustomersListPageFilterPresense(subsName, projectName);
	}

	// 15
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyCustomersListPageHideFilterTest() {
		modules.verifyCustomersListPageHideFilter(subsName, projectName);
	}

	// 16
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyCustomersListPageSearchTest() {
		String customerName = properties.getProperty("customername");
		modules.verifyCustomersListPageSearch(subsName, projectName, customerName);
	}

	// 17
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyCustomersListPageIdSearchTest() {
		modules.verifyCustomersListPageIdSearch(subsName, projectName);
	}

	// 18
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class, enabled = false)
	public void verifyCustomersListPageDayWeekMonthTest() {
		modules.verifyCustomersListPageDayWeekMonth(subsName, projectName);
	}

	// 19
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class, enabled = false)
	public void verifyCustomersListDateRangeSelectorTest() {
		modules.verifyCustomersListDateRangeSelector(subsName, projectName);
	}

	// 20
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyCustomersListPageExcelExportTest() {
		modules.verifyCustomersListPageExcelExport(subsName, projectName);
	}	

	// 21
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyCustomersListPagePaginationTest() {
		modules.verifyCustomersListPagePagination(subsName, projectName);
	}

	// 22
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyCustomersListPageShowCountTest() {
		modules.verifyCustomersListPageShowCount(subsName, projectName);
	}

	// 23 Feature not available (Sort)

	// 24
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyCustomersDetailsPageProblemIDTest() {
		String customerId = properties.getProperty("customerid");
		modules.verifyCustomersDetailsPageProblemID(subsName, projectName,customerId);
	}

	// 25
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyCustomersDetailsCustomerTypeDisplayTest() {
		modules.verifyCustomersDetailsCustomerTypeDisplay(subsName, projectName);
	}

	// 26
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyCustomersDetailsPageCustomerNameTest() {
		String customerId = properties.getProperty("customerid");
		String customerName = properties.getProperty("customername");
		modules.verifyCustomersDetailsPageCustomerName(subsName, projectName,customerId, customerName);
	}

	// 27
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyCustomersDetailsPageCustomerInfoDisplayTest() {
		String customerId = properties.getProperty("customerid");
		modules.verifyCustomersDetailsPageCustomerInfoDisplay(subsName, projectName,customerId);
	}

	// 28
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyCustomersDetailsPagePrevNextCloseFunctionalityTest() {
		modules.verifyCustomersDetailsPagePrev_Next_Close_Functionality(subsName, projectName);
	}

	// 29
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyCustomersDetailsPageCustomerSummarySectionTest() {
		modules.verifyCustomersDetailsPageCustomerSummarySection(subsName, projectName);
	}

	// 30
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyCustomersDetailsNumberOfVoicesTest() {
		String customerId = properties.getProperty("customerid");
		modules.verifyCustomersDetailsNumberOfVoices(subsName, projectName,customerId);
	}

	// 31
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyCustomersDetailsTopSourceTest() {
		String customerId = properties.getProperty("customerid");
		modules.verifyCustomersDetailsTopSource(subsName, projectName,customerId);
	}

	// 32 Dashboard kind of

	// 33
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class, enabled = false)
	public void verifyCustomersDetailsTopServiceTest() {
		String customerId = properties.getProperty("customerid");
		modules.verifyCustomersDetailsTopService(subsName, projectName,customerId);
	}

	// 34
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class, enabled = false)
	public void verifyCustomersDetailsTopSQLTest() {
		String customerId = properties.getProperty("customerid");
		modules.verifyCustomersDetailsTopSQL(subsName, projectName,customerId);
	}

	// 35
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyCustomersDetailsVoiceDetailedReportTest() {
		String customerId = properties.getProperty("customerid");
		modules.verifyCustomersDetailsVoiceDetailedReport(subsName, projectName,customerId);
	}	

	// 36
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyCustomersDetailsVoiceDetailedReportDashboardCancelButtonTest() {
		String customerId = properties.getProperty("customerid");
		modules.verifyCustomersDetailsVoiceDetailedReportDashboardCancelButton(subsName, projectName,customerId);
	}

	// 37
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyCustomersDetailsViewAllVoiceTest() {
		String customerId = properties.getProperty("customerid");
		modules.verifyCustomersDetailsViewAllVoice(subsName, projectName,customerId);
	}

	// 38, 39 Will be done through manual (Ingestion required)

}

//Voiced Customer
//13--> feature not available 
//23--> Feature not available (Sort)
//32--> Widget kind of (Solution not found yet)
//38, 39--> Will be done through manual (Ingestion required)