package org.dewa.framework.regressiontestcases;

import org.dewa.framework.basefunctions.CommonFunctions;
import org.dewa.framework.listener.RetryListener;
import org.dewa.framework.scriptmodules.DashBoardModules;
import org.dewa.framework.scriptmodules.VoicePageModules;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(org.dewa.framework.listener.ListenerImplementation.class)
public class DashboardOverallSentimentScoreTest extends CommonFunctions {

	// Over all sentiment score dashboard is taken as a separate class to split and
	// execute
	// Count is continued here
	@BeforeClass(alwaysRun = true)
	public void navigateToProjectFromHome() {
		String emailId = prop.getProperty("email");
		String password = prop.getProperty("password");
		new CommonFunctions().launchApplication(getUrl(), emailId, password);
	}

//	// 27.1
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_SourceNoFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_SourceNoFilter();
//	}
//
//	// 27.2
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_ChannelNoFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_ChannelNoFilter();
//	}
//
//	// 27.3
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_ChannelWebsiteTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_ChannelWebsiteFilter();
//	}
//
//	// 27.4
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_ChannelSmartApplicationFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_ChannelSmartApplicationFilter();
//	}
//
//	// 27.5
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_ChannelWebsiteHappyFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_ChannelWebsiteHappyFilter();
//	}
//
//	// 27.6
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_ChannelWebsiteUnhappyFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_ChannelWebsiteUnhappyFilter();
//	}
//
//	// 27.7
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_ChannelWebsiteNeutralFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_ChannelWebsiteNeutralFilter();
//	}
//
//	// 27.8
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_ChannelSmartApplicationHappyFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_ChannelSmartApplicationHappyFilter();
//	}
//
//	// 27.9
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_ChannelSmartApplicationUnhappyFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_ChannelSmartApplicationUnhappyFilter();
//	}
//
//	// 27.10
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_ChannelSmartApplicationNeutralFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_ChannelSmartApplicationNeutralFilter();
//	}
//
//	// 27.11
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_SqlInformationQualityFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_SqlInformationQualityFilter();
//	}
//
//	// 27.12
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_SqlAccessibilityFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_SqlAccessibilityFilter();
//	}
//
//	// 27.13
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_SqlInformationQualityHappyFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_SqlInformationQualityHappyFilter();
//	}
//
//	// 27.14
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_SqlInformationQualityUnhappyFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_SqlInformationQualityUnhappyFilter();
//	}
//
//	// 27.15
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_SqlInformationQualityNeutralFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_SqlInformationQualityNeutralFilter();
//	}
//
//	// 27.16
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_SqlAccessibilityHappyFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_SqlAccessibilityHappyFilter();
//	}
//
//	// 27.17
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_SqlAccessibilityUnhappyFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_SqlAccessibilityUnhappyFilter();
//	}
//
//	// 27.18
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_SqlAccessibilityNeutralFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_SqlAccessibilityNeutralFilter();
//	}
//
//	// 27.19
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_AspectGenericFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_AspectGenericFilter();
//	}
//
//	// 27.20
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_AspectChannelFocusedFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_AspectChannelFocusedFilter();
//	}
//
//	// 27.21
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_AspectGenericHappyFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_AspectGenericHappyFilter();
//	}
//
//	// 27.22
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_AspectGenericUnhappyFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_AspectGenericUnhappyFilter();
//	}
//
//	// 27.23
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_AspectGenericNeutralFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_AspectGenericNeutralFilter();
//	}
//
//	// 27.24
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_AspectChannelFocusedHappyFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_AspectChannelFocusedHappyFilter();
//	}
//
//	// 27.25
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_AspectChannelFocusedUnhappyFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_AspectChannelFocusedUnhappyFilter();
//	}
//
//	// 27.26
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_AspectChannelFocusedNeutralFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_AspectChannelFocusedNeutralFilter();
//	}
//
//	// 27.27
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_ServiceBillingServicesFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_ServiceBillingServicesFilter();
//	}
//
//	// 27.28
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_ServiceNocServicesFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_ServiceNocServicesFilter();
//	}
//
//	// 27.29
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_ServiceBillingServicesHappyFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_ServiceBillingServicesHappyFilter();
//	}
//
//	// 27.30
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_ServiceBillingServicesUnhappyFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_ServiceBillingServicesUnhappyFilter();
//	}
//
//	// 27.31
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_ServiceBillingServicesNeutralFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_ServiceBillingServicesNeutralFilter();
//	}
//
//	// 27.32
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_ServiceNocServicesHappyFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_ServiceNocServicesHappyFilter();
//	}
//
//	// 27.33
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_ServiceNocServicesUnhappyFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_ServiceNocServicesUnhappyFilter();
//	}
//
//	// 27.34
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_ServiceNocServicesNeutralFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_ServiceNocServicesNeutralFilter();
//	}
//
//	// 27.35
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_SubServiceBillPaymentFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_SubServiceBillPaymentFilter();
//	}
//
//	// 27.36
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_SubServiceEvChargingFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_SubServiceEvChargingFilter();
//	}
//
//	// 27.37
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_SubServiceBillPaymentHappyFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_SubServiceBillPaymentHappyFilter();
//	}
//
//	// 27.38
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_SubServiceBillPaymentUnhappyFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_SubServiceBillPaymentUnhappyFilter();
//	}
//
//	// 27.39
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_SubServiceBillPaymentNeutralFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_SubServiceBillPaymentNeutralFilter();
//	}
//
//	// 27.40
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_SubServiceEvChargingHappyFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_SubServiceEvChargingHappyFilter();
//	}
//
//	// 27.41
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_SubServiceEvChargingUnhappyFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_SubServiceEvChargingUnhappyFilter();
//	}
//
//	// 27.42
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_SubServiceEvChargingNeutralFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_SubServiceEvChargingNeutralFilter();
//	}
//
//	// 27.43
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_CustomerCategoryBusinessFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_CustomerCategoryBusinessFilter();
//	}
//
//	// 27.44 DEWA Staff filter is not available in voice list page
//
//	// 27.45
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_CustomerCategoryBusinessHappyFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_CustomerCategoryBusinessHappyFilter();
//	}
//	
//	// 27.46
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_CustomerCategoryBusinessUnhappyFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_CustomerCategoryBusinessUnhappyFilter();
//	}
//	
//	// 27.47
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
//	public void verifyDashboardOverallSentimentScore_CustomerCategoryBusinessNeutralFilterTest() {
//		DashBoardModules modules = new DashBoardModules(getPage());
//		modules.verifyDashboardOverallSentimentScore_CustomerCategoryBusinessNeutralFilter();
//	}


	@AfterMethod(alwaysRun = true)
	public void driverClose() {
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.afterMethod();
	}

}
