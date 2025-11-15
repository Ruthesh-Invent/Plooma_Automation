package org.dewa.framework.regressiontestcases;

import org.dewa.framework.basefunctions.CommonFunctions;
import org.dewa.framework.listener.RetryListener;
import org.dewa.framework.scriptmodules.DashBoardModules;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(org.dewa.framework.listener.ListenerImplementation.class)
public class DashboardPageTest extends CommonFunctions {

	@BeforeClass(alwaysRun = true)
	public void navigateToProjectFromHome() {
		String emailId = prop.getProperty("email");
		String password = prop.getProperty("password");
		new CommonFunctions().dashboardLaunchApplication(getUrl(), emailId, password);
	}

	String subsName = prop.getProperty("subscriptionName");
	String projectName = prop.getProperty("projectName");

	// 1
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardTitleTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardTitle(subsName, projectName);
	}

	// 2 Deferred

	// 3
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardFilter(subsName, projectName);
	}

	// 4
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardAdvancedFiltersTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardAdvancedFilters(subsName, projectName);
	}

	// 5
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyDashboardClearFiltersTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardClearFilters(subsName, projectName);
	}

	// 6
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class, enabled = false)
	public void verifyDashboardTodayWeekMonthTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardTodayWeekMonth(subsName, projectName);
	}

	// 7 Manual Fail Need to complete Automation
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class, enabled = false)
	public void verifyDashboardDateRangeTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardDateRange(subsName, projectName);
	}

	// 8 Deferred

	// 9
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardNumberOfVoicesTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardNumberOfVoices(subsName, projectName);
	}

	// 10
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyDashboardNumberOfVoicesDeepLinkTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardNumberOfVoicesDeepLink(subsName, projectName);
	}

	// 11
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardNumberOfVoicesInformationIconTooltipTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardNumberOfVoicesInformationIconTooltip(subsName, projectName);
	}

	// 12 Manual Failure

	// 13
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardTopChannelTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardTopChannel(subsName, projectName);
	}

	// 14
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardTopChannelInformationIconTooltipTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardTopChannelInformationIconTooltip(subsName, projectName);
	}

	// 15
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyDashboardTopVoiceTypeTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardTopVoiceType(subsName, projectName);
	}

	// 16
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardTopVoiceTypeInformationIconTooltipTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardTopVoiceTypeInformationIconTooltip(subsName, projectName);
	}

	// 17
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyDashboardTopSourceTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardTopSource(subsName, projectName);
	}

	// 18
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardTopSourceIconTooltipTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardTopSourceIconTooltip(subsName, projectName);
	}

	// 19
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardTopSqlTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardTopSql(subsName, projectName);
	}

	// 20
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardTopSqlIconTooltipTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardTopSqlIconTooltip(subsName, projectName);
	}

	// 21
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardVoiceDistributionViewTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardVoiceDistributionView(subsName, projectName);
	}

	// 22
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyDashboardVoiceDistributionViewDayMonthYearTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardVoiceDistributionViewDayMonthYear(subsName, projectName);
	}

	// 23
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardVoiceDistributionViewYearFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardVoiceDistributionViewYearFilter(subsName, projectName);
	}

	// 24 Chart options. (Chart/graph) (MI)
	// 25 Chart options. (Chart/graph) (MI)

	// 26
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardVoiceDistributionViewIconTooltipTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardVoiceDistributionViewIconTooltip(subsName, projectName);
	}

	// 27.1
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_SourceNoFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_SourceNoFilter(subsName, projectName);
	}

	// 27.2
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_ChannelNoFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_ChannelNoFilter(subsName, projectName);
	}

	// 27.3
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_ChannelWebsiteTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_ChannelWebsiteFilter(subsName, projectName);
	}

	// 27.4
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_ChannelSmartApplicationFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_ChannelSmartApplicationFilter(subsName, projectName);
	}

	// 27.5
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_ChannelWebsiteHappyFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_ChannelWebsiteHappyFilter(subsName, projectName);
	}

	// 27.6
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_ChannelWebsiteUnhappyFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_ChannelWebsiteUnhappyFilter(subsName, projectName);
	}

	// 27.7
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_ChannelWebsiteNeutralFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_ChannelWebsiteNeutralFilter(subsName, projectName);
	}

	// 27.8
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_ChannelSmartApplicationHappyFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_ChannelSmartApplicationHappyFilter(subsName, projectName);
	}

	// 27.9
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_ChannelSmartApplicationUnhappyFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_ChannelSmartApplicationUnhappyFilter(subsName, projectName);
	}

	// 27.10
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_ChannelSmartApplicationNeutralFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_ChannelSmartApplicationNeutralFilter(subsName, projectName);
	}

	// 27.11
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_SqlInformationQualityFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_SqlInformationQualityFilter(subsName, projectName);
	}

	// 27.12
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_SqlAccessibilityFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_SqlAccessibilityFilter(subsName, projectName);
	}

	// 27.13
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_SqlInformationQualityHappyFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_SqlInformationQualityHappyFilter(subsName, projectName);
	}

	// 27.14
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_SqlInformationQualityUnhappyFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_SqlInformationQualityUnhappyFilter(subsName, projectName);
	}

	// 27.15
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_SqlInformationQualityNeutralFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_SqlInformationQualityNeutralFilter(subsName, projectName);
	}

	// 27.16
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_SqlAccessibilityHappyFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_SqlAccessibilityHappyFilter(subsName, projectName);
	}

	// 27.17
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_SqlAccessibilityUnhappyFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_SqlAccessibilityUnhappyFilter(subsName, projectName);
	}

	// 27.18 
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_SqlAccessibilityNeutralFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_SqlAccessibilityNeutralFilter(subsName, projectName);
	}

	// 27.19
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_AspectGenericFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_AspectGenericFilter(subsName, projectName);
	}

	// 27.20
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_AspectChannelFocusedFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_AspectChannelFocusedFilter(subsName, projectName);
	}

	// 27.21
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_AspectGenericHappyFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_AspectGenericHappyFilter(subsName, projectName);
	}

	// 27.22
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_AspectGenericUnhappyFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_AspectGenericUnhappyFilter(subsName, projectName);
	}

	// 27.23
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_AspectGenericNeutralFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_AspectGenericNeutralFilter(subsName, projectName);
	}

	// 27.24
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_AspectChannelFocusedHappyFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_AspectChannelFocusedHappyFilter(subsName, projectName);
	}

	// 27.25
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_AspectChannelFocusedUnhappyFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_AspectChannelFocusedUnhappyFilter(subsName, projectName);
	}

	// 27.26
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_AspectChannelFocusedNeutralFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_AspectChannelFocusedNeutralFilter(subsName, projectName);
	}

	// 27.27
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_ServiceBillingServicesFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_ServiceBillingServicesFilter(subsName, projectName);
	}

	// 27.28
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_ServiceNocServicesFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_ServiceNocServicesFilter(subsName, projectName);
	}

	// 27.29
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_ServiceBillingServicesHappyFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_ServiceBillingServicesHappyFilter(subsName, projectName);
	}

	// 27.30
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_ServiceBillingServicesUnhappyFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_ServiceBillingServicesUnhappyFilter(subsName, projectName);
	}

	// 27.31
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_ServiceBillingServicesNeutralFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_ServiceBillingServicesNeutralFilter(subsName, projectName);
	}

	// 27.32
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_ServiceNocServicesHappyFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_ServiceNocServicesHappyFilter(subsName, projectName);
	}

	// 27.33
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_ServiceNocServicesUnhappyFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_ServiceNocServicesUnhappyFilter(subsName, projectName);
	}

	// 27.34
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_ServiceNocServicesNeutralFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_ServiceNocServicesNeutralFilter(subsName, projectName);
	}

	// 27.35
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_SubServiceBillPaymentFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_SubServiceBillPaymentFilter(subsName, projectName);
	}

	// 27.36
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_SubServiceEvChargingFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_SubServiceEvChargingFilter(subsName, projectName);
	}

	// 27.37
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_SubServiceBillPaymentHappyFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_SubServiceBillPaymentHappyFilter(subsName, projectName);
	}

	// 27.38
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_SubServiceBillPaymentUnhappyFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_SubServiceBillPaymentUnhappyFilter(subsName, projectName);
	}

	// 27.39
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_SubServiceBillPaymentNeutralFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_SubServiceBillPaymentNeutralFilter(subsName, projectName);
	}

	// 27.40
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_SubServiceEvChargingHappyFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_SubServiceEvChargingHappyFilter(subsName, projectName);
	}

	// 27.41
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_SubServiceEvChargingUnhappyFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_SubServiceEvChargingUnhappyFilter(subsName, projectName);
	}

	// 27.42
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_SubServiceEvChargingNeutralFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_SubServiceEvChargingNeutralFilter(subsName, projectName);
	}

	// 27.43
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_CustomerCategoryBusinessFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_CustomerCategoryBusinessFilter(subsName, projectName);
	}

	// 27.44 DEWA Staff filter is not available in voice list page

	// 27.45
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_CustomerCategoryBusinessHappyFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_CustomerCategoryBusinessHappyFilter(subsName, projectName);
	}

	// 27.46
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_CustomerCategoryBusinessUnhappyFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_CustomerCategoryBusinessUnhappyFilter(subsName, projectName);
	}

	// 27.47
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScore_CustomerCategoryBusinessNeutralFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScore_CustomerCategoryBusinessNeutralFilter(subsName, projectName);
	}

	// 28 MI Clicking on widget Deeplink

	// 29
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScoreIconTooltipTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScoreIconTooltip(subsName, projectName);
	}

	// 30
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardOverallSentimentScorePageNavigatorTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardOverallSentimentScorePageNavigator(subsName, projectName);
	}

	// 31 Number/Percentage MI

	// 32.1
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardVoiceTypesPerSQLChartTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardVoiceTypesPerSQLChart(subsName, projectName);
	}

	// 32.2
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyDashboardVoiceTypesPerSQLChartAccessibilityTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardVoiceTypesPerSQLChartAccessibility(subsName, projectName);
	}

	// 32.3
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardVoiceTypesPerSQLChartEaseOfUseTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardVoiceTypesPerSQLChartEaseOfUse(subsName, projectName);
	}

	// 32.4
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardVoiceTypesPerSQLChartInformationQualityTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardVoiceTypesPerSQLChartInformationQuality(subsName, projectName);
	}

	// 32.5
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardVoiceTypesPerSQLChartAccessibility_ComplaintTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardVoiceTypesPerSQLChartAccessibility_Complaint(subsName, projectName);
	}

	// 32.6
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyDashboardVoiceTypesPerSQLChartEaseOfUse_AppreciationTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardVoiceTypesPerSQLChartEaseOfUse_Appreciation(subsName, projectName);
	}

	// 33 Not Feasible Deep Link

	// 34
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardVoiceTypePerSqlIconTooltipTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardVoiceTypePerSqlIconTooltip(subsName, projectName);
	}

	// 35 Not Feasible

	// 36 Not Feasible

	// 37 Bug Inquirey Filter Spelling issue

	// 38 Not Feasible Deep Link

	// 39
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardVoiceTypeSpectrumIconTooltipTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardVoiceTypeSpectrumIconTooltip(subsName, projectName);
	}

	// 40 Not Feasible

	// 41 Not Feasible

	// 42
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardSourceDistributionBoardChartTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardSourceDistributionBoardChart(subsName, projectName);
	}

	// 43 Not Feasible

	// 44
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyDashboardSourceDistributionBoardIconTooltipTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardSourceDistributionBoardIconTooltip(subsName, projectName);
	}

	// 45 Deferred
	// 46 Deferred

	// 47.1
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardTopSubjectDropdownFilter_EVChargerTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardTopSubjectDropdownFilter_EVCharger(subsName, projectName);
	}

	// 47.2
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardTopSubjectDropdownFilter_BillPaymentsTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardTopSubjectDropdownFilter_BillPayments(subsName, projectName);
	}

	// 48.1
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardTopSubjectDropdownFilter_BillingInquiryTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardTopSubjectDropdownFilter_BillingInquiry(subsName, projectName);
	}

	// 48.2
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyDashboardTopSubjectDropdownFilter_SmartApplicationTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardTopSubjectDropdownFilter_SmartApplication(subsName, projectName);
	}

	// 49 Not Feasible
	// 50 Not Feasible

	// 51
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardTopSubjectIconTooltipTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardTopSubjectIconTooltip(subsName, projectName);
	}

	// 52 Not Feasible Percentage/Numbers

	// 53.1
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardCustomerSpectrumChartTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardCustomerSpectrumChart_BusinessFilter(subsName, projectName);
	}

	// 53.2
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardCustomerSpectrumChart_IndividualLocalFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardCustomerSpectrumChart_IndividualLocalFilter(subsName, projectName);
	}

	// 54 Not Feasible

	// 55
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyDashboardCustomerSpectrumIconTooltipTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardCustomerSpectrumIconTooltip(subsName, projectName);
	}

	// 56 Not Feasible
	// 57 Not Feasible

	// 58.1
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardChannelDistributionBoard_NoFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardChannelDistributionBoard_NoFilter(subsName, projectName);
	}

	// 58.2
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardChannelDistributionBoard_DewaWebsiteFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardChannelDistributionBoard_DewaWebsiteFilter(subsName, projectName);
	}

	// 58.3
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyDashboardChannelDistributionBoard_SmartApplicationFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardChannelDistributionBoard_SmartApplicationFilter(subsName, projectName);
	}

	// 59 Not Feasible

	// 60
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardChannelDistributionBoardIconTooltipTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardChannelDistributionBoardIconTooltip(subsName, projectName);
	}

	// 61 Not Feasible

	// 62.1 
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardServiceInsightBoard_BillingServicesFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardServiceInsightBoard_BillingServicesFilter(subsName, projectName);
	}

	// 62.2
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyDashboardServiceInsightBoard_NocServicesFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardServiceInsightBoard_NocServicesFilter(subsName, projectName);
	}

	// 63 Not Feasible

	// 64
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardServiceInsightBoardIconTooltipTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardServiceInsightBoardIconTooltip(subsName, projectName);
	}

	// 65 Not Feasible

	// 66 Sub Service Bug Filter Mismatch
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardSubServiceInsightBoard_BillingServicesFilterTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardSubServiceInsightBoard_BillingServicesFilter(subsName, projectName);
	}

	// 67 Not Feasible

	// 68
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyDashboardSubServiceInsightBoardIconTooltipTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardSubServiceInsightBoardIconTooltip(subsName, projectName);
	}

	// 69

	// 70
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void verifyDashboardServiceVsSQL_EaseOfUseNocServicesTest() {
		DashBoardModules modules = new DashBoardModules(getPage());
		modules.verifyDashboardServiceVsSQL_EaseOfUseNocServices(subsName, projectName);
	}

}

//MI ->> Manual Intervention

//Notes  
//2 Deferred
//7 Manual Failure
//8 Deferred
//12 Manual Failure
//18 Manual fail
//24 MI (Chart/graph)
//25 MI (Chart/graph)
//28 MI Clicking on widget Deeplink
//31 Number/Percentage MI
//33 Not Feasible Deep Link
//35 Not Feasible
// 36 Not Feasible
// 37 Bug Inquirey Filter Spelling issue
// 38 Not Feasible Deep Link
//40 Not Feasible
// 41 Not Feasible
//43 Not Feasible
//45 Deferred
// 46 Deferred
//49 Not Feasible 
// 50 Not Feasible
//52 Not Feasible
//54 Not Feasible
// 56 Not Feasible
// 57 Not Feasible
//59 Not Feasible
//61 Not Feasible
//63 Not Feasible
// 65 Not Feasible
// 66 SUb Service Bug Filter Mismatch
