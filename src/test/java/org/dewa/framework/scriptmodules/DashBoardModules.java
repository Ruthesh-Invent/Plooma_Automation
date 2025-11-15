package org.dewa.framework.scriptmodules;

import static org.dewa.framework.utils.BaseClass.assertContains;
import static org.dewa.framework.utils.BaseClass.assertEquals;

import java.util.List;
import java.util.Map;

import org.bson.assertions.Assertions;
import org.dewa.framework.api_utility.ApiUtility;
import org.dewa.framework.objectrepo.DashboardPage;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Frame;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class DashBoardModules extends ParentModule {

	ApiUtility api = new ApiUtility();

	public DashBoardModules(Page page) {
		super(page);
		// TODO Auto-generated constructor stub
	}

	public void verifyDashboardTitle(String subsName, String projectName) {
		navigateTillDashboard(subsName, projectName);
		String dashboardTitle = getDashboardPage().getDashboardTitle();
		System.out.println("Dashboard title is " + dashboardTitle);
		assertContains("Dashboard page title is Dashboard", "Dashboard page title is not Dashboard", dashboardTitle,
				"Voice Insights");
	}

	public void verifyDashboardFilter(String subsName, String projectName) {
		navigateTillDashboard(subsName, projectName);
		int initailVoiceCount = getDashboardPage().getNumberOfVoicesCount();
		System.out.println("Initial Voice Count " + initailVoiceCount);
		extentTest.get().log(Status.INFO,
				"Initial Voice Count without any Filter " + "<b>" + initailVoiceCount + "</b>");
		getDashboardPage().filterDashboardByDropdown("Data Source", "DDA Instant Happiness");
		int voiceCountAfterSoucre_DDA_InstantHappiness_Filter = getDashboardPage().getNumberOfVoicesCount();
		System.out.println("Number of Voice Count After Channel-> Website Filter is Changed "
				+ voiceCountAfterSoucre_DDA_InstantHappiness_Filter);
		getDashboardPage().scrollToWidget("Source Distribution Board");
		int sourceDistributionWidgetCount = api.getSourceDistributionBoradCountWithDDA_InstantHappinessFilter();
		System.out.println("Source Distribution Widget Count " + sourceDistributionWidgetCount);
		assertEquals("Total Number of Voice Count is Equal to Source Distribution Widget Count",
				"Total Number of Voice Count is not Equal to Source Distribution Widget Count",
				String.valueOf(sourceDistributionWidgetCount),
				String.valueOf(voiceCountAfterSoucre_DDA_InstantHappiness_Filter));
		getPage().reload();

		getDashboardPage().filterDashboardByDropdown("Channel", "Website");
		int voiceCountAfterChannel_WebsiteFilter = getDashboardPage().getNumberOfVoicesCount();
		System.out.println(
				"Number of Voice Count After Channel-> Website Filter " + voiceCountAfterChannel_WebsiteFilter);
		getDashboardPage().scrollToWidget("Channel Distribution Board");
		int channelDistributionWidgetCount = api.getChannelDistributionBoradCountWithChannel_WebsiteFilter();
		System.out.println("Channel Distribution Widget Count " + channelDistributionWidgetCount);
		assertEquals("Total Number of Voice Count is Equal to Channel Distribution Widget Count",
				"Total Number of Voice Count is not Equal to Channel Distribution Widget Count",
				String.valueOf(channelDistributionWidgetCount), String.valueOf(voiceCountAfterChannel_WebsiteFilter));
		getPage().reload();

		getDashboardPage().filterDashboardByDropdown("Channel", "Smart Application");
		int voiceCountAfterChannel_SmartApplicationFilter = getDashboardPage().getNumberOfVoicesCount();
		System.out.println("Number of Voice Count After Channel-> Smart Application Filter "
				+ voiceCountAfterChannel_SmartApplicationFilter);
		getDashboardPage().scrollToWidget("Channel Distribution Board");
		int channelSmartApplicationWidgetCount = api
				.getChannelDistributionBoradCountWithChannel_SmartApplicationFilter();
		System.out.println("Channel Distribution Widget Count " + channelSmartApplicationWidgetCount);
		assertEquals("Total Number of Voice Count is Equal to Channel Distribution Widget Count",
				"Total Number of Voice Count is not Equal to Channel Distribution Widget Count",
				String.valueOf(channelSmartApplicationWidgetCount),
				String.valueOf(voiceCountAfterChannel_SmartApplicationFilter));
		getPage().reload();

		getDashboardPage().filterDashboardByDropdown("Main Services", "Billing Services");
		int voiceCountAfterMainService_BillingServiceFilter = getDashboardPage().getNumberOfVoicesCount();
		System.out.println("Number of Voice Count After Main Service-> Billing Service Filter "
				+ voiceCountAfterMainService_BillingServiceFilter);
		getDashboardPage().scrollToWidget("Service Insight Board");
		int serviceInsightBillingServiceWidgetCount = api
				.getServiceInsightBoardCountWithMainService_BillingServiceFilter();
		System.out.println("Service Insight Widget Count " + serviceInsightBillingServiceWidgetCount);
		assertContains("Total Number of Voice Count is Equal to Service Insight Widget Count",
				"Total Number of Voice Count is not Equal to Service Insight Widget Count",
				String.valueOf(serviceInsightBillingServiceWidgetCount),
				String.valueOf(voiceCountAfterMainService_BillingServiceFilter));
		getPage().reload();

		getDashboardPage().filterDashboardByDropdown("Main Services", "NOC Services");
		int voiceCountAfterMainService_NOCServiceFilter = getDashboardPage().getNumberOfVoicesCount();
		System.out.println("Number of Voice Count After Main Service-> NOC Service Filter "
				+ voiceCountAfterMainService_NOCServiceFilter);
		getDashboardPage().scrollToWidget("Service Insight Board");
		int serviceInsightNocServiceWidgetCount = api.getServiceInsightBoardCountWithMainService_NocServiceFilter();
		System.out.println("Service Insight Widget Count " + serviceInsightNocServiceWidgetCount);
		assertEquals("Total Number of Voice Count is Equal to Service Insight Widget Count",
				"Total Number of Voice Count is not Equal to Service Insight Widget Count",
				String.valueOf(serviceInsightNocServiceWidgetCount),
				String.valueOf(voiceCountAfterMainService_NOCServiceFilter));
		getPage().reload();

		getDashboardPage().filterDashboardByDropdown("SQL", "Accessibility");
		int voiceCountAfterSQL_AccessibilityFilter = getDashboardPage().getNumberOfVoicesCount();
		System.out.println(
				"Number of Voice Count After SQL-> Accessibility Filter " + voiceCountAfterSQL_AccessibilityFilter);
		getDashboardPage().scrollToWidget("Voice Types Per SQL");
		int voicePerSQL_AccessibilityWidgetCount = api.getVoicePerSQL_CountWithSQL_AccessibilityFilter();
		System.out.println("Voice Per SQL Widget Count " + voicePerSQL_AccessibilityWidgetCount);
		assertEquals("Total Number of Voice Count is Equal to Voice Per SQL Widget Count",
				"Total Number of Voice Count is not Equal to Voice Per SQL Widget Count",
				String.valueOf(voicePerSQL_AccessibilityWidgetCount),
				String.valueOf(voiceCountAfterSQL_AccessibilityFilter));
		getPage().reload();

		getDashboardPage().filterDashboardByDropdown("SQL", "Ease of use");
		int voiceCountAfterSQL_EaseOfUseFilter = getDashboardPage().getNumberOfVoicesCount();
		System.out
				.println("Number of Voice Count After SQL-> Ease of Use Filter " + voiceCountAfterSQL_EaseOfUseFilter);
		getDashboardPage().scrollToWidget("Voice Types Per SQL");
		int voicePerSQL_EaseOfUseWidgetCount = api.getVoicePerSQL_CountWithSQL_EaseOfUseFilter();
		System.out.println("Voice Per SQL Widget Count " + voicePerSQL_EaseOfUseWidgetCount);
		assertEquals("Total Number of Voice Count is Equal to Voice Per SQL Widget Count",
				"Total Number of Voice Count is not Equal to Voice Per SQL Widget Count",
				String.valueOf(voicePerSQL_EaseOfUseWidgetCount), String.valueOf(voiceCountAfterSQL_EaseOfUseFilter));
		getPage().reload();
	}

	public void verifyDashboardAdvancedFilters(String subsName, String projectName) {
		navigateTillDashboard(subsName, projectName);
		int initailVoiceCount = getDashboardPage().getNumberOfVoicesCount();
		System.out.println("Initial Voice Count " + initailVoiceCount);
		extentTest.get().log(Status.INFO,
				"Initial Voice Count without any Filter " + "<b>" + initailVoiceCount + "</b>");

		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sentiment", "Neutral");
		int voiceCountAfterNeutralSentimentFilter = getDashboardPage().getNumberOfVoicesCount();
		System.out.println(
				"Number of Voice Count After Sentiment-> Neutral Filter " + voiceCountAfterNeutralSentimentFilter);
		// getDashboardPage().scrollToWidget("DDA Instant Happiness Meter");
		int DDAInsntHppinsMtrCount_SentimentNeutralFilter = api.getDDAInsntHppinsMtrCountWithSentiment_NeutralFilter();
		System.out.println("DDA Instant Happiness Meter Widget Count " + DDAInsntHppinsMtrCount_SentimentNeutralFilter);
		assertContains("Total Number of Voice Count is Equal to DDA Happiness Meter Widget Count",
				"Total Number of Voice Count is not Equal to DDA Happiness Meter Widget Count",
				String.valueOf(DDAInsntHppinsMtrCount_SentimentNeutralFilter),
				String.valueOf(voiceCountAfterNeutralSentimentFilter));
		getPage().reload();

		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sub Services", "Request for refund");
		int voiceCountAfterSubServicesRequestForRefundFilter = getDashboardPage().getNumberOfVoicesCount();
		System.out.println("Number of Voice Count After Sub Services-> Request for Refund Filter "
				+ voiceCountAfterSubServicesRequestForRefundFilter);
		getDashboardPage().scrollToWidget("Public Service Insight Board");
		int subServicesInsightCount_SubServicesRequestForRefundFilter = api
				.getSubservicesInsightBoradCountWithSubService_RequestForRefundFilter();
		System.out.println(
				"Sub Services Insight Widget Count " + subServicesInsightCount_SubServicesRequestForRefundFilter);
		assertContains("Total Number of Voice Count is Equal to Public Service Insight Board Widget Count",
				"Total Number of Voice Count is not Equal to Public Service Insight Board Widget Widget Count",
				String.valueOf(subServicesInsightCount_SubServicesRequestForRefundFilter),
				String.valueOf(voiceCountAfterSubServicesRequestForRefundFilter));
		getPage().reload();

		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Customer Category", "Business");
		int voiceCountAfterCustomerCategoryBusinessFilter = getDashboardPage().getNumberOfVoicesCount();
		System.out.println("Number of Voice Count After Customer Category-> Business Filter "
				+ voiceCountAfterCustomerCategoryBusinessFilter);
		getDashboardPage().scrollToWidget("Customer Spectrum");
		int customerSpectrumCount_CustomerCategory_BusinessFilter = api
				.getCustomerSpectrumCountWithCustomerCategoty_BusinessFilter();
		System.out.println("Customer Spectrum Widget Count " + customerSpectrumCount_CustomerCategory_BusinessFilter);
		assertContains("Total Number of Voice Count is Equal to Customer Spectrum Widget Count",
				"Total Number of Voice Count is not Equal to Customer Spectrum Widget Widget Count",
				String.valueOf(customerSpectrumCount_CustomerCategory_BusinessFilter),
				String.valueOf(voiceCountAfterCustomerCategoryBusinessFilter));
		getPage().reload();

		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Subjects", "EV Charger");
		int voiceCountAfterSubjectsEV_ChargerFilter = getDashboardPage().getNumberOfVoicesCount();
		System.out.println(
				"Number of Voice Count After Subjects-> EV Charger Filter " + voiceCountAfterSubjectsEV_ChargerFilter);
		getDashboardPage().scrollToWidget("Top Subject");
		int topSubjectCount_SubjectEV_ChargerFilter = api.getTopSubjectCountWithSubject_EV_ChargerFilter();
		System.out.println("Top Subject Widget Count " + topSubjectCount_SubjectEV_ChargerFilter);
		assertContains("Total Number of Voice Count is Equal to Top Subject Widget Count",
				"Total Number of Voice Count is not Equal to Top Subject Widget Widget Count",
				String.valueOf(topSubjectCount_SubjectEV_ChargerFilter),
				String.valueOf(voiceCountAfterSubjectsEV_ChargerFilter));
		getPage().reload();
	}

	public void verifyDashboardClearFilters(String subsName, String projectName) {
		navigateTillDashboard(subsName, projectName);
		int initailVoiceCount = getDashboardPage().getNumberOfVoicesCount();
		System.out.println("Initial Voice Count " + initailVoiceCount);
		extentTest.get().log(Status.INFO,
				"Initial Voice Count without any Filter " + "<b>" + initailVoiceCount + "</b>");

		getDashboardPage().filterDashboardByDropdown("Channel", "Website");
		int voiceCountAfterFilter = getDashboardPage().getNumberOfVoicesCount();
		extentTest.get().log(Status.INFO, "Voice Count After Filter " + "<b>" + voiceCountAfterFilter + "</b>");

		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().clickOnResetFilter();
		getPage().reload();
		int voiceCountAfterReset = getDashboardPage().getNumberOfVoicesCount();
		extentTest.get().log(Status.INFO, "Voice Count After Refresh " + "<b>" + voiceCountAfterReset + "</b>");
		assertEquals("All the filters are cleared, Clear Filter is Working Fine",
				"Filters are not cleared, Clear Filter is not Working", String.valueOf(voiceCountAfterReset),
				String.valueOf(initailVoiceCount));
	}

	public void verifyDashboardTodayWeekMonth(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		int initailVoiceCount_VoiceListPage = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Initial Voice Count in Voice List Page " + initailVoiceCount_VoiceListPage);

		getVoicesListPage().filterVoicesOnTodayWeekMonth("Today");
		int todayVoiceCount_VoiceListPage = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Today's Voice Count in Voice List Page " + todayVoiceCount_VoiceListPage);

		getVoicesListPage().filterVoicesOnTodayWeekMonth("Week");
		int weekVoiceCount_VoiceListPage = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("This Week's Voice Count in Voice List Page " + weekVoiceCount_VoiceListPage);

		getVoicesListPage().filterVoicesOnTodayWeekMonth("Month");
		int monthVoiceCount_VoiceListPage = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("This Month's Voice Count in Voice List Page " + monthVoiceCount_VoiceListPage);

		navigateFromOnePageToAnother("Dashboard");
		int initailVoiceCount_DashboardPage = getDashboardPage().getNumberOfVoicesCount();
		System.out.println("Initial Voice Count in Dashboard Page " + initailVoiceCount_DashboardPage);

		getDashboardPage().filterVoicesOnTodayWeekMonth("Today");
		int todayVoiceCount_DashboardPage = getDashboardPage().getNumberOfVoicesCount();
		System.out.println("Today's Voice Count in Dashboard Page " + todayVoiceCount_DashboardPage);

		getDashboardPage().filterVoicesOnTodayWeekMonth("Week");
		int weekVoiceCount_DashboardPage = getDashboardPage().getNumberOfVoicesCount();
		System.out.println("This Week's Voice Count in Dashboard Page " + weekVoiceCount_DashboardPage);

		getDashboardPage().filterVoicesOnTodayWeekMonth("Month");
		int monthVoiceCount_DashboardPage = getDashboardPage().getNumberOfVoicesCount();
		System.out.println("This Month's Voice Count in Dashboard Page " + monthVoiceCount_DashboardPage);

		extentTest.get().log(Status.INFO,
				"Voice count in <b>Voice Page after Today's Filter </b>" + todayVoiceCount_VoiceListPage);
		extentTest.get().log(Status.INFO,
				"Voice count in <b>Dashboard Page after Today's Filter </b>" + todayVoiceCount_DashboardPage);
		assertEquals("Voice Count in Voice Page after Today's Filter is equal to Dashboard's",
				"Voice Count in Voice Page after Today's Filter is not equal to Dashboard's",
				String.valueOf(todayVoiceCount_DashboardPage), String.valueOf(todayVoiceCount_VoiceListPage));

		extentTest.get().log(Status.INFO,
				"Voice count in <b>Voice Page after Week's Filter </b>" + weekVoiceCount_VoiceListPage);
		extentTest.get().log(Status.INFO,
				"Voice count in <b>Dashboard Page after Week's Filter </b>" + weekVoiceCount_DashboardPage);
		assertEquals("Voice Count in Voice Page after Week's Filter is equal to Dashboard's",
				"Voice Count in Voice Page after Week's Filter is not equal to Dashboard's",
				String.valueOf(weekVoiceCount_DashboardPage), String.valueOf(weekVoiceCount_VoiceListPage));

		extentTest.get().log(Status.INFO,
				"Voice count in <b>Voice Page after Month's Filter </b>" + monthVoiceCount_VoiceListPage);
		extentTest.get().log(Status.INFO,
				"Voice count in <b>Dashboard Page after Month's Filter </b>" + monthVoiceCount_DashboardPage);
		assertEquals("Voice Count in Voice Page after Month's Filter is equal to Dashboard's",
				"Voice Count in Voice Page after Month's Filter is not equal to Dashboard's",
				String.valueOf(monthVoiceCount_DashboardPage), String.valueOf(monthVoiceCount_VoiceListPage));
	}

	// Need to comomplete
	public void verifyDashboardDateRange(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		int initailVoiceCount_VoiceListPage = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Initial Voice Count in Voice List Page " + initailVoiceCount_VoiceListPage);
	}

	public void verifyDashboardNumberOfVoices(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		int initailVoiceCount_VoiceListPage = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Voice Count in Voice List Page " + initailVoiceCount_VoiceListPage);
		extentTest.get().log(Status.INFO, "Voice count in <b>Voice Page </b>" + initailVoiceCount_VoiceListPage);

		navigateFromOnePageToAnother("Voice Insights");
		int initailVoiceCount_DashboardPage = getDashboardPage().getNumberOfVoicesCount();
		System.out.println("Voice Count in Dashboard Page " + initailVoiceCount_DashboardPage);
		extentTest.get().log(Status.INFO, "Voice count in <b>Dashboard Page </b>" + initailVoiceCount_DashboardPage);

		assertEquals("Voice Count in Voice Page is equal to Dashboard Page",
				"Voice Count in Voice Page is equal not to Dashboard Page",
				String.valueOf(initailVoiceCount_DashboardPage), String.valueOf(initailVoiceCount_VoiceListPage));
	}

	public void verifyDashboardNumberOfVoicesDeepLink(String subsName, String projectName) {
		navigateTillDashboard(subsName, projectName);
		getDashboardPage().clickOnVoicesCount();
		String voicePageTitle = getTitleFromPopupFrame();
		closePopupFrame();
		System.out.println("Voice Page Title: " + voicePageTitle);
		assertEquals("After Clicking on Number Of Voices Voice List Page is Loaded",
				"After Clicking on Number Of Voices Voice List Page is not Loaded", String.valueOf(voicePageTitle),
				String.valueOf("Voices"));
	}

	public void verifyDashboardNumberOfVoicesInformationIconTooltip(String subsName, String projectName) {
		navigateTillDashboard(subsName, projectName);
		getDashboardPage().hoverOnWidgetIcon("Number of Voices");
		boolean tooltipVisibility = getDashboardPage().checkTooltipVisibility();
		System.out.println("Visibility " + tooltipVisibility);
		String tooltip = getDashboardPage().getToolTip();
		System.out.println("Tooltip " + tooltip);
		assertEquals("Tool Tip is Visible, Tool Tip : <b>" + tooltip + "</b>", "Tool Tip is not Visible", true,
				tooltipVisibility);
	}

	public void verifyDashboardTopChannel(String subsName, String projectName) {
		navigateTillDashboard(subsName, projectName);
		boolean topChannelVisibility = getDashboardPage().checkVisibilityOfWidget("Top Channel");
		String topChannelText = getDashboardPage().getWidgetText("Top Channel");
		String topChannel = getDashboardPage().spliWidgetTextAndGetOutput(topChannelText, "Channel");
		System.out.println("Top Channel " + topChannel);
		String topCount = getDashboardPage().spliWidgetTextAndGetOutput(topChannelText, "Count");
		System.out.println("Top Count " + topCount);
		String percentage = getDashboardPage().spliWidgetTextAndGetOutput(topChannelText, "Percentage");
		System.out.println("Percentage " + percentage);
		assertEquals("Top Channel is Visible", "Top Channel is not Visible", true, topChannelVisibility);
		extentTest.get().log(Status.INFO, "Top Channel is <b>" + topChannel + "</b>");
		extentTest.get().log(Status.INFO, "Top Channel Count is <b>" + topCount + "</b>");
		extentTest.get().log(Status.INFO, "Top Channel Percentage is <b>" + percentage + "</b>");
	}
	
	public void verifyDashboardTopChannelInformationIconTooltip(String subsName, String projectName) {
		navigateTillDashboard(subsName, projectName);
		getDashboardPage().hoverOnWidgetIcon("Top Channel");
		boolean tooltipVisibility = getDashboardPage().checkTooltipVisibility();
		System.out.println("Visibility " + tooltipVisibility);
		String tooltip = getDashboardPage().getToolTip();
		System.out.println("Tooltip " + tooltip);
		assertEquals("Top Channel Tool Tip is Visible, Tool Tip : <b>" + tooltip + "</b>",
				"Top Channel Tool Tip is not Visible", true, tooltipVisibility);
	}

	public void verifyDashboardTopVoiceType(String subsName, String projectName) {
		navigateTillDashboard(subsName, projectName);
		boolean topChannelVisibility = getDashboardPage().checkVisibilityOfWidget("Top Voice Type");
		String topVoiceTypeText = getDashboardPage().getWidgetText("Top Voice Type");
		String topVoiceType = getDashboardPage().spliWidgetTextAndGetOutput(topVoiceTypeText, "Channel");
		System.out.println("Top Voice Type " + topVoiceType);
		String topCount = getDashboardPage().spliWidgetTextAndGetOutput(topVoiceTypeText, "Count");
		System.out.println("Top Count " + topCount);
		String percentage = getDashboardPage().spliWidgetTextAndGetOutput(topVoiceTypeText, "Percentage");
		System.out.println("Percentage " + percentage);
		assertEquals("Top Voice Type is Visible", "Top Voice Type is not Visible", true, topChannelVisibility);
		extentTest.get().log(Status.INFO, "Top Voice Type is <b>" + topVoiceType + "</b>");
		extentTest.get().log(Status.INFO, "Top Voice Type Count is <b>" + topCount + "</b>");
		extentTest.get().log(Status.INFO, "Top Voice Type Percentage is <b>" + percentage + "</b>");
	}

	public void verifyDashboardTopVoiceTypeInformationIconTooltip(String subsName, String projectName) {
		navigateTillDashboard(subsName, projectName);
		getDashboardPage().hoverOnWidgetIcon("Top Voice Type");
		boolean tooltipVisibility = getDashboardPage().checkTooltipVisibility();
		System.out.println("Visibility " + tooltipVisibility);
		String tooltip = getDashboardPage().getToolTip();
		System.out.println("Tooltip " + tooltip);
		assertEquals("Top Voice Type Tool Tip is Visible, Tool Tip : <b>" + tooltip + "</b>",
				"Top Voice Type Tool Tip is not Visible", true, tooltipVisibility);
	}

	public void verifyDashboardTopSource(String subsName, String projectName) {
		navigateTillDashboard(subsName, projectName);
		boolean topChannelVisibility = getDashboardPage().checkVisibilityOfWidget("Top Source");
		String topSourceText = getDashboardPage().getWidgetText("Top Source");
		String topSource = getDashboardPage().spliWidgetTextAndGetOutput(topSourceText, "Channel");
		System.out.println("Top Source Type " + topSource);
		String topCount = getDashboardPage().spliWidgetTextAndGetOutput(topSourceText, "Count");
		System.out.println("Top Source Count " + topCount);
		String percentage = getDashboardPage().spliWidgetTextAndGetOutput(topSourceText, "Percentage");
		System.out.println("Top Source Percentage " + percentage);
		assertEquals("Top Voice Source is Visible", "Top Voice Source is not Visible", true, topChannelVisibility);
		extentTest.get().log(Status.INFO, "Top Source is <b>" + topSource + "</b>");
		extentTest.get().log(Status.INFO, "Top Source Count is <b>" + topCount + "</b>");
		extentTest.get().log(Status.INFO, "Top Source Percentage is <b>" + percentage + "</b>");
	}

	public void verifyDashboardTopSourceIconTooltip(String subsName, String projectName) {
		navigateTillDashboard(subsName, projectName);
		getDashboardPage().hoverOnWidgetIcon("Top Source");
		boolean tooltipVisibility = getDashboardPage().checkTooltipVisibility();
		System.out.println("Visibility " + tooltipVisibility);
		String tooltip = getDashboardPage().getToolTip();
		System.out.println("Tooltip " + tooltip);
		assertEquals("Top Source Tool Tip is Visible, Tool Tip : <b>" + tooltip + "</b>",
				"Top Source Tool Tip is not Visible", true, tooltipVisibility);
	}

	public void verifyDashboardTopSql(String subsName, String projectName) {
		navigateTillDashboard(subsName, projectName);
		boolean topChannelVisibility = getDashboardPage().checkVisibilityOfWidget("Top SQL");
		String topSqlText = getDashboardPage().getWidgetText("Top SQL");
		String topChannel = getDashboardPage().spliWidgetTextAndGetOutput(topSqlText, "Channel");
		System.out.println("Top Channel " + topChannel);
		String topCount = getDashboardPage().spliWidgetTextAndGetOutput(topSqlText, "Count");
		System.out.println("Top Count " + topCount);
		String percentage = getDashboardPage().spliWidgetTextAndGetOutput(topSqlText, "Percentage");
		System.out.println("Percentage " + percentage);
		assertEquals("Top SQL is Visible", "Top SQL is not Visible", true, topChannelVisibility);
		extentTest.get().log(Status.INFO, "Top SQL is <b>" + topChannel + "</b>");
		extentTest.get().log(Status.INFO, "Top SQL Count is <b>" + topCount + "</b>");
		extentTest.get().log(Status.INFO, "Top SQL Percentage is <b>" + percentage + "</b>");
	}

	public void verifyDashboardTopSqlIconTooltip(String subsName, String projectName) {
		navigateTillDashboard(subsName, projectName);
		getDashboardPage().hoverOnWidgetIcon("Top SQL");
		boolean tooltipVisibility = getDashboardPage().checkTooltipVisibility();
		System.out.println("Visibility " + tooltipVisibility);
		String tooltip = getDashboardPage().getToolTip();
		System.out.println("Tooltip " + tooltip);
		assertEquals("Top SQL Tool Tip is Visible, Tool Tip : <b>" + tooltip + "</b>",
				"Top SQL Tool Tip is not Visible", true, tooltipVisibility);
	}

	public void verifyDashboardVoiceDistributionView(String subsName, String projectName) {
		navigateTillDashboard(subsName, projectName);
		boolean widgetChartVisibility = getDashboardPage().checkWidgetChartVisibility("Voice Distribution View");
		System.out.println(widgetChartVisibility);
		assertEquals("Voice Distribution View is Visible", "Voice Distribution View is not Visible", true,
				widgetChartVisibility);
		Map<String, Integer> responseCapture = api.getVoiceDistributionWithYearAndNumber();
		for (Map.Entry<String, Integer> entry : responseCapture.entrySet()) {
			System.out.println("Voice Count for " + entry.getKey() + " is " + entry.getValue());
			extentTest.get().log(Status.INFO,
					"Voice Count for <b>" + entry.getKey() + "</b> : <b>" + entry.getValue() + "</b>");
		}
	}

	public void verifyDashboardVoiceDistributionViewDayMonthYear(String subsName, String projectName) {
		navigateTillDashboard(subsName, projectName);
		boolean yearChartVisibility = getDashboardPage().checkWidgetChartVisibility("Voice Distribution View");
		System.out.println(yearChartVisibility);
		assertEquals("Voice Distribution View for Year is Visible", "Voice Distribution View for Year is not Visible",
				true, yearChartVisibility);
		Map<String, Integer> yearResponse = api.getVoiceDistributionWithYearAndNumber();
		for (Map.Entry<String, Integer> entry : yearResponse.entrySet()) {
			System.out.println("Voice Count for " + entry.getKey() + " is " + entry.getValue());
			extentTest.get().log(Status.INFO,
					"Voice Count for <b>" + entry.getKey() + "</b> : <b>" + entry.getValue() + "</b>");
		}

		getDashboardPage().clickDayMonthYearInVoiceDistrubutionView("month");
		boolean monthChartVisibility = getDashboardPage().checkWidgetChartVisibility("Voice Distribution View");
		System.out.println(monthChartVisibility);
		assertEquals("Voice Distribution View for Month is Visible", "Voice Distribution View for Month is not Visible",
				true, monthChartVisibility);
		Map<String, Integer> monthResponse = api.getVoiceDistributionWithMonthAndNumber();
		for (Map.Entry<String, Integer> entry : monthResponse.entrySet()) {
			System.out.println("Voice Count for " + entry.getKey() + " is " + entry.getValue());
			extentTest.get().log(Status.INFO,
					"Voice Count for <b>" + entry.getKey() + "</b> : <b>" + entry.getValue() + "</b>");
		}

		getDashboardPage().clickDayMonthYearInVoiceDistrubutionView("day");
		boolean dayChartVisibility = getDashboardPage().checkWidgetChartVisibility("Voice Distribution View");
		System.out.println(dayChartVisibility);
		assertEquals("Voice Distribution View for Day is Visible", "Voice Distribution View for Day is not Visible",
				true, dayChartVisibility);
		Map<String, Integer> dayResponse = api.getVoiceDistributionWithDayAndNumber();
		for (Map.Entry<String, Integer> entry : dayResponse.entrySet()) {
			System.out.println("Voice Count for " + entry.getKey() + " is " + entry.getValue());
			extentTest.get().log(Status.INFO,
					"Voice Count for <b>" + entry.getKey() + "</b> : <b>" + entry.getValue() + "</b>");
		}
	}

	public void verifyDashboardVoiceDistributionViewYearFilter(String subsName, String projectName) {
		navigateTillDashboard(subsName, projectName);
		boolean widgetChartVisibility = getDashboardPage().checkWidgetChartVisibility("Voice Distribution View");
		System.out.println(widgetChartVisibility);
		assertEquals("Voice Distribution View for Year is Visible", "Voice Distribution View for Year is not Visible",
				true, widgetChartVisibility);
		Map<String, Integer> responseCapture = api.getVoiceDistributionWithYearAndNumber();
		for (Map.Entry<String, Integer> entry : responseCapture.entrySet()) {
			System.out.println("Voice Count for " + entry.getKey() + " is " + entry.getValue());
			extentTest.get().log(Status.INFO,
					"Voice Count for <b>" + entry.getKey() + "</b> : <b>" + entry.getValue() + "</b>");
		}
	}

	public void verifyDashboardVoiceDistributionViewIconTooltip(String subsName, String projectName) {
		navigateTillDashboard(subsName, projectName);
		getDashboardPage().hoverOnWidgetIcon("Voice Distribution View");
		boolean tooltipVisibility = getDashboardPage().checkTooltipVisibility();
		System.out.println("Visibility " + tooltipVisibility);
		String tooltip = getDashboardPage().getToolTip();
		System.out.println("Tooltip " + tooltip);
		assertEquals("Voice Disribution View Tool Tip is Visible, Tool Tip : <b>" + tooltip + "</b>",
				"Voice Disribution View Tool Tip is not Visible", true, tooltipVisibility);
	}

	public void verifyDashboardOverallSentimentScoreIconTooltip(String subsName, String projectName) {
		navigateTillDashboard(subsName, projectName);
		getDashboardPage().hoverOnWidgetIcon("Overall Sentiment Score");
		boolean tooltipVisibility = getDashboardPage().checkTooltipVisibility();
		System.out.println("Visibility " + tooltipVisibility);
		String tooltip = getDashboardPage().getToolTip();
		System.out.println("Tooltip " + tooltip);
		assertEquals("Overall Sentiment Score Tool Tip is Visible, Tool Tip : <b>" + tooltip + "</b>",
				"Overall Sentiment Score Tool Tip is not Visible", true, tooltipVisibility);
	}

	public void verifyDashboardOverallSentimentScore_SourceNoFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		// Source no filter
		int totalNumberOfVoicesInVoiceListPage_NoFilter = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page without any filter is "
				+ totalNumberOfVoicesInVoiceListPage_NoFilter);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Source");
		int sourceWidgetCount_NoFilter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Source");
		System.out.println(
				"Overall Sentiment Score - Source Widget Count with no filter : " + sourceWidgetCount_NoFilter);
		assertContains("Overall Sentiment Score - Source Widget Count without any Filter is Correct",
				"Overall Sentiment Score - Source Widget Count without any Filter is Incorrect",
				String.valueOf(sourceWidgetCount_NoFilter), "3");
		int overallSentimentScore_Source_NoFilter = api.getOverallSentimentScore_Source_Count();
		System.out.println("Overall Sentiment Score - Source in Dashboard Page without any filter is "
				+ overallSentimentScore_Source_NoFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Source without any Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Source without any Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_Source_NoFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_NoFilter));
		System.out.println("---------------");
	}
		

	public void verifyDashboardOverallSentimentScore_ChannelNoFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		int totalNumberOfVoicesInVoiceListPage_NoFilter = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page without any filter is "
				+ totalNumberOfVoicesInVoiceListPage_NoFilter);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Channel");
		int channelWidgetCount_NoFilter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Channel");
		System.out.println(
				"Overall Sentiment Score - Channel Widget Count with no filter : " + channelWidgetCount_NoFilter);
		assertContains("Overall Sentiment Score - Channel Widget Count without any filter is Correct",
				"Overall Sentiment Score - Channel Widget Count without any filter is Incorrect",
				String.valueOf(channelWidgetCount_NoFilter), "3");
		int overallSentimentScore_ChannelNoFilter = api.getOverallSentimentScore_Channel_Count_NoFilter();
		System.out.println("Overall Sentiment Score - Channel in Dashboard Page without any filter is "
				+ overallSentimentScore_ChannelNoFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Channel without any Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Channel without any Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_ChannelNoFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_NoFilter));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_ChannelWebsiteFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Channel List", "Website");
		int totalNumberOfVoicesInVoiceListPage_Website_Channel = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Website Channel Filter "
				+ totalNumberOfVoicesInVoiceListPage_Website_Channel);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().filterDashboardByDropdown("Channel", "Website");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Channel");
		int channelWidgetCount_WebsiteChannel_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Channel");
		System.out.println("Overall Sentiment Score - Channel Widget Count after Website Filter : "
				+ channelWidgetCount_WebsiteChannel_Filter);
		assertContains("Overall Sentiment Score - Channel with Website Filter Count is Correct",
				"Overall Sentiment Score - Channel with Website Filter Count is Incorrect",
				String.valueOf(channelWidgetCount_WebsiteChannel_Filter), "1");
		int overallSentimentScore_ChannelWebsiteFilter = api.getOverallSentimentScoreChannelCount_WebsiteFilter();
		System.out.println("Overall Sentiment Score - Channel in Dashboard Page with Website filter is "
				+ overallSentimentScore_ChannelWebsiteFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Channel with Website Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Channel with Website Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_ChannelWebsiteFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_Website_Channel));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_ChannelSmartApplicationFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Channel List", "Smart Application");
		int totalNumberOfVoicesInVoiceListPage_SmartApp_Channel = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Smart Application Channel Filter "
				+ totalNumberOfVoicesInVoiceListPage_SmartApp_Channel);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().filterDashboardByDropdown("Channel", "Smart Application");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Channel");
		int channelWidgetCount_SmartApp_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Channel");
		System.out.println("Overall Sentiment Score - Channel Widget Count after Smart Application Filter : "
				+ channelWidgetCount_SmartApp_Filter);
		assertContains("Overall Sentiment Score - Channel with Smart Application Filter Count is Correct",
				"Overall Sentiment Score - Channel with Smart Application Filter Count is Incorrect",
				String.valueOf(channelWidgetCount_SmartApp_Filter), "1");
		int overallSentimentScore_ChannelSmartAppFilter = api.getOverallSentimentScoreChannelCount_SmartAppFilter();
		System.out.println("Overall Sentiment Score - Channel in Dashboard Page with Smart Application filter is "
				+ overallSentimentScore_ChannelSmartAppFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Channel with Smart Application Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Channel with Smart Application Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_ChannelSmartAppFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_SmartApp_Channel));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_ChannelWebsiteHappyFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Channel List", "Website");
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Voice Sentiments", "Happy");
		int totalNumberOfVoicesInVoiceListPage_Website_Channel_And_Happy = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Website Channel and Happy Filter "
				+ totalNumberOfVoicesInVoiceListPage_Website_Channel_And_Happy);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().filterDashboardByDropdown("Channel", "Website");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sentiment", "Happy");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Channel");
		int channelWidgetCount_WebsiteHappy_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Channel");
		System.out.println("Overall Sentiment Score - Channel Widget Count after Website and Happy Filter : "
				+ channelWidgetCount_WebsiteHappy_Filter);
		assertContains("Overall Sentiment Score - Channel with Website and Happy Filter Count is Correct",
				"Overall Sentiment Score - Channel with Website and Happy Filter Count is Incorrect",
				String.valueOf(channelWidgetCount_WebsiteHappy_Filter), "1");
		int overallSentimentScore_ChannelWebsiteHappyFilter = api
				.getOverallSentimentScoreChannelCount_WebsiteHappyFilter();
		System.out.println("Overall Sentiment Score - Channel in Dashboard Page with Website Happy filter is "
				+ overallSentimentScore_ChannelWebsiteHappyFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Channel with Website Happy Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Channel with Website Happy Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_ChannelWebsiteHappyFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_Website_Channel_And_Happy));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_ChannelWebsiteUnhappyFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Channel List", "Website");
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Voice Sentiments", "Unhappy");
		int totalNumberOfVoicesInVoiceListPage_Website_Channel_And_Unhappy = getVoicesListPage()
				.getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Website Channel and Unhappy Filter "
				+ totalNumberOfVoicesInVoiceListPage_Website_Channel_And_Unhappy);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().filterDashboardByDropdown("Channel", "Website");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sentiment", "Unhappy");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Channel");
		int channelWidgetCount_WebsiteUnhappy_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Channel");
		System.out.println("Overall Sentiment Score - Channel Widget Count after Website and Unhappy Filter : "
				+ channelWidgetCount_WebsiteUnhappy_Filter);
		assertContains("Overall Sentiment Score - Channel with Website and Unhappy Filter Count is Correct",
				"Overall Sentiment Score - Channel with Website and Unhappy Filter Count is Incorrect",
				String.valueOf(channelWidgetCount_WebsiteUnhappy_Filter), "1");
		int overallSentimentScore_ChannelWebsiteUnhappyFilter = api
				.getOverallSentimentScoreChannelCount_WebsiteUnhappyFilter();
		System.out.println("Overall Sentiment Score - Channel in Dashboard Page with Website Unhappy filter is "
				+ overallSentimentScore_ChannelWebsiteUnhappyFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Channel with Website Unhappy Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Channel with Website Unhappy Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_ChannelWebsiteUnhappyFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_Website_Channel_And_Unhappy));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_ChannelWebsiteNeutralFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Channel List", "Website");
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Voice Sentiments", "Neutral");
		int totalNumberOfVoicesInVoiceListPage_Website_Channel_And_Neutral = getVoicesListPage()
				.getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Website Channel and Neutral Filter "
				+ totalNumberOfVoicesInVoiceListPage_Website_Channel_And_Neutral);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().filterDashboardByDropdown("Channel", "Website");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sentiment", "Neutral");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Channel");
		int channelWidgetCount_WebsiteNeutral_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Channel");
		System.out.println("Overall Sentiment Score - Channel Widget Count after Website and Neutral Filter : "
				+ channelWidgetCount_WebsiteNeutral_Filter);
		assertContains("Overall Sentiment Score - Channel with Website and Neutral Filter Count is Correct",
				"Overall Sentiment Score - Channel with Website and Neutral Filter Count is Incorrect",
				String.valueOf(channelWidgetCount_WebsiteNeutral_Filter), "1");
		int overallSentimentScore_ChannelWebsiteNeutralFilter = api
				.getOverallSentimentScoreChannelCount_WebsiteNeutralFilter();
		System.out.println("Overall Sentiment Score - Channel in Dashboard Page with Website Neutral filter is "
				+ overallSentimentScore_ChannelWebsiteNeutralFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Channel with Website Neutral Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Channel with Website Neutral Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_ChannelWebsiteNeutralFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_Website_Channel_And_Neutral));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_ChannelSmartApplicationHappyFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Channel List", "Smart Application");
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Voice Sentiments", "Happy");
		int totalNumberOfVoicesInVoiceListPage_SmartApplication_Channel_And_Happy = getVoicesListPage()
				.getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Smart Application Channel and Happy Filter "
				+ totalNumberOfVoicesInVoiceListPage_SmartApplication_Channel_And_Happy);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().filterDashboardByDropdown("Channel", "Smart Application");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sentiment", "Happy");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Channel");
		int channelWidgetCount_SmartApplicationHappy_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Channel");
		System.out.println("Overall Sentiment Score - Channel Widget Count after Smart Application and Happy Filter : "
				+ channelWidgetCount_SmartApplicationHappy_Filter);
		assertContains("Overall Sentiment Score - Channel with Smart Application and Happy Filter Count is Correct",
				"Overall Sentiment Score - Channel with Smart Application and Happy Filter Count is Incorrect",
				String.valueOf(channelWidgetCount_SmartApplicationHappy_Filter), "1");
		int overallSentimentScore_ChannelSmartApplicationHappyFilter = api
				.getOverallSentimentScoreChannelCount_SmartAppHappyFilter();
		System.out.println("Overall Sentiment Score - Channel in Dashboard Page with Smart Application Happy filter is "
				+ overallSentimentScore_ChannelSmartApplicationHappyFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Channel with Smart Application Happy Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Channel with Smart Application Happy Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_ChannelSmartApplicationHappyFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_SmartApplication_Channel_And_Happy));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_ChannelSmartApplicationUnhappyFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Channel List", "Smart Application");
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Voice Sentiments", "Unhappy");
		int totalNumberOfVoicesInVoiceListPage_SmartApplication_Channel_And_Unhappy = getVoicesListPage()
				.getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Smart Application Channel and Unhappy Filter "
				+ totalNumberOfVoicesInVoiceListPage_SmartApplication_Channel_And_Unhappy);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().filterDashboardByDropdown("Channel", "Smart Application");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sentiment", "Unhappy");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Channel");
		int channelWidgetCount_SmartApplicationUnhappy_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Channel");
		System.out
				.println("Overall Sentiment Score - Channel Widget Count after Smart Application and Unhappy Filter : "
						+ channelWidgetCount_SmartApplicationUnhappy_Filter);
		assertContains("Overall Sentiment Score - Channel with Smart Application and Unhappy Filter Count is Correct",
				"Overall Sentiment Score - Channel with Smart Application and Unhappy Filter Count is Incorrect",
				String.valueOf(channelWidgetCount_SmartApplicationUnhappy_Filter), "1");
		int overallSentimentScore_ChannelSmartApplicationUnhappyFilter = api
				.getOverallSentimentScoreChannelCount_SmartAppUnhappyFilter();
		System.out
				.println("Overall Sentiment Score - Channel in Dashboard Page with Smart Application Unhappy filter is "
						+ overallSentimentScore_ChannelSmartApplicationUnhappyFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Channel with Smart Application Unhappy Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Channel with Smart Application Unhappy Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_ChannelSmartApplicationUnhappyFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_SmartApplication_Channel_And_Unhappy));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_ChannelSmartApplicationNeutralFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Channel List", "Smart Application");
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Voice Sentiments", "Neutral");
		int totalNumberOfVoicesInVoiceListPage_SmartApplication_Channel_And_Neutral = getVoicesListPage()
				.getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Smart Application Channel and Neutral Filter "
				+ totalNumberOfVoicesInVoiceListPage_SmartApplication_Channel_And_Neutral);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().filterDashboardByDropdown("Channel", "Smart Application");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sentiment", "Neutral");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Channel");
		int channelWidgetCount_SmartApplicationNeutral_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Channel");
		System.out
				.println("Overall Sentiment Score - Channel Widget Count after Smart Application and Neutral Filter : "
						+ channelWidgetCount_SmartApplicationNeutral_Filter);
		assertContains("Overall Sentiment Score - Channel with Smart Application and Neutral Filter Count is Correct",
				"Overall Sentiment Score - Channel with Smart Application and Neutral Filter Count is Incorrect",
				String.valueOf(channelWidgetCount_SmartApplicationNeutral_Filter), "1");
		int overallSentimentScore_ChannelSmartApplicationNeutralFilter = api
				.getOverallSentimentScoreChannelCount_SmartAppNeutralFilter();
		System.out
				.println("Overall Sentiment Score - Channel in Dashboard Page with Smart Application Neutral filter is "
						+ overallSentimentScore_ChannelSmartApplicationNeutralFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Channel with Smart Application Neutral Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Channel with Smart Application Neutral Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_ChannelSmartApplicationNeutralFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_SmartApplication_Channel_And_Neutral));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_SqlInformationQualityFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Service Quality Level", "Information quality");
		int totalVoicesInVoiceListPage_SQL_InformationQuality = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with SQL Information Quality Filter "
				+ totalVoicesInVoiceListPage_SQL_InformationQuality);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().filterDashboardByDropdown("SQL", "Information Quality");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("SQL");
		int channelWidgetCount_SQL_InfoQuality_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("SQL");
		System.out.println("Overall Sentiment Score - SQL Count after Information Quality Filter : "
				+ channelWidgetCount_SQL_InfoQuality_Filter);
		assertContains("Overall Sentiment Score - SQL with Information Quality Filter Count is Correct",
				"Overall Sentiment Score - SQL with Information Quality Filter Count is Incorrect",
				String.valueOf(channelWidgetCount_SQL_InfoQuality_Filter), "1");
		int overallSentimentScore_SqlIformationQualityFilter = api
				.getOverallSentimentScoreSQLCount_InformationQualityFilter();
		System.out.println("Overall Sentiment Score - SQL in Dashboard Page with Information Quality filter is "
				+ overallSentimentScore_SqlIformationQualityFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - SQL with Information Quality Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - SQL with Information Quality Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_SqlIformationQualityFilter),
				String.valueOf(totalVoicesInVoiceListPage_SQL_InformationQuality));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_SqlAccessibilityFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Service Quality Level", "Accessibility");
		int totalVoicesInVoiceListPage_SQL_Accessibility = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with SQL Accessibility Filter "
				+ totalVoicesInVoiceListPage_SQL_Accessibility);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().filterDashboardByDropdown("SQL", "Accessibility");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("SQL");
		int channelWidgetCount_SQL_Accessibility_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("SQL");
		System.out.println("Overall Sentiment Score - SQL Count after Accessibility Filter : "
				+ channelWidgetCount_SQL_Accessibility_Filter);
		assertContains("Overall Sentiment Score - SQL with Accessibility Filter Count is Correct",
				"Overall Sentiment Score - SQL with Accessibility Filter Count is Incorrect",
				String.valueOf(channelWidgetCount_SQL_Accessibility_Filter), "1");
		int overallSentimentScore_SqlAccessibilityFilter = api.getOverallSentimentScoreSQLCount_AccessibilityFilter();
		System.out.println("Overall Sentiment Score - SQL in Dashboard Page with Accessibility filter is "
				+ overallSentimentScore_SqlAccessibilityFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - SQL with Accessibility Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - SQL with Accessibility Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_SqlAccessibilityFilter),
				String.valueOf(totalVoicesInVoiceListPage_SQL_Accessibility));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_SqlInformationQualityHappyFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Service Quality Level", "Information quality");
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Voice Sentiments", "Happy");
		int totalNumberOfVoicesInVoiceListPage_InformationQuality_Sql_And_Happy = getVoicesListPage()
				.getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Information Quality SQL and Happy Filter "
				+ totalNumberOfVoicesInVoiceListPage_InformationQuality_Sql_And_Happy);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().filterDashboardByDropdown("SQL", "Information quality");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sentiment", "Happy");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("SQL");
		int sqlWidgetCount_InformationQualityHappy_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("SQL");
		System.out.println("Overall Sentiment Score - SQL Widget Count after Information Quality and Happy Filter : "
				+ sqlWidgetCount_InformationQualityHappy_Filter);
		assertContains("Overall Sentiment Score - SQL with Information Quality and Happy Filter Count is Correct",
				"Overall Sentiment Score - SQL with Information Quality and Happy Filter Count is Incorrect",
				String.valueOf(sqlWidgetCount_InformationQualityHappy_Filter), "1");
		int overallSentimentScore_SqlInformationQualityHappyFilter = api
				.getOverallSentimentScoreSqlCount_InformationQualityHappyFilter();
		System.out.println("Overall Sentiment Score - SQL in Dashboard Page with Information Quality Happy filter is "
				+ overallSentimentScore_SqlInformationQualityHappyFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - SQL with Information Quality Happy Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - SQL with Information Quality Happy Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_SqlInformationQualityHappyFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_InformationQuality_Sql_And_Happy));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_SqlInformationQualityUnhappyFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Service Quality Level", "Information quality");
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Voice Sentiments", "Unhappy");
		int totalNumberOfVoicesInVoiceListPage_InformationQuality_Sql_And_Unhappy = getVoicesListPage()
				.getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Information Quality SQL and Unhappy Filter "
				+ totalNumberOfVoicesInVoiceListPage_InformationQuality_Sql_And_Unhappy);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().filterDashboardByDropdown("SQL", "Information quality");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sentiment", "Unhappy");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("SQL");
		int sqlWidgetCount_InformationQualityUnhappy_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("SQL");
		System.out.println("Overall Sentiment Score - SQL Widget Count after Information Quality and Unhappy Filter : "
				+ sqlWidgetCount_InformationQualityUnhappy_Filter);
		assertContains("Overall Sentiment Score - SQL with Information Quality and Unhappy Filter Count is Correct",
				"Overall Sentiment Score - SQL with Information Quality and Unhappy Filter Count is Incorrect",
				String.valueOf(sqlWidgetCount_InformationQualityUnhappy_Filter), "1");
		int overallSentimentScore_SqlInformationQualityUnhappyFilter = api
				.getOverallSentimentScoreSqlCount_InformationQualityUnhappyFilter();
		System.out.println("Overall Sentiment Score - SQL in Dashboard Page with Information Quality Unhappy filter is "
				+ overallSentimentScore_SqlInformationQualityUnhappyFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - SQL with Information Quality Unhappy Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - SQL with Information Quality Unhappy Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_SqlInformationQualityUnhappyFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_InformationQuality_Sql_And_Unhappy));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_SqlInformationQualityNeutralFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Service Quality Level", "Information quality");
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Voice Sentiments", "Neutral");
		int totalNumberOfVoicesInVoiceListPage_InformationQuality_Sql_And_Neutral = getVoicesListPage()
				.getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Information Quality SQL and Neutral Filter "
				+ totalNumberOfVoicesInVoiceListPage_InformationQuality_Sql_And_Neutral);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().filterDashboardByDropdown("SQL", "Information quality");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sentiment", "Neutral");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("SQL");
		int sqlWidgetCount_InformationQualityNeutral_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("SQL");
		System.out.println("Overall Sentiment Score - SQL Widget Count after Information Quality and Neutral Filter : "
				+ sqlWidgetCount_InformationQualityNeutral_Filter);
		assertContains("Overall Sentiment Score - SQL with Information Quality and Neutral Filter Count is Correct",
				"Overall Sentiment Score - SQL with Information Quality and Neutral Filter Count is Incorrect",
				String.valueOf(sqlWidgetCount_InformationQualityNeutral_Filter), "1");
		int overallSentimentScore_SqlInformationQualityNeutralFilter = api
				.getOverallSentimentScoreSqlCount_InformationQualityNeutralFilter();
		System.out.println("Overall Sentiment Score - SQL in Dashboard Page with Information Quality Neutral filter is "
				+ overallSentimentScore_SqlInformationQualityNeutralFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - SQL with Information Quality Neutral Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - SQL with Information Quality Neutral Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_SqlInformationQualityNeutralFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_InformationQuality_Sql_And_Neutral));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_SqlAccessibilityHappyFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Service Quality Level", "Accessibility");
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Voice Sentiments", "Happy");
		int totalNumberOfVoicesInVoiceListPage_Accessibility_Sql_And_Happy = getVoicesListPage()
				.getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Accessibility SQL and Happy Filter "
				+ totalNumberOfVoicesInVoiceListPage_Accessibility_Sql_And_Happy);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().filterDashboardByDropdown("SQL", "Accessibility");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sentiment", "Happy");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("SQL");
		int sqlWidgetCount_AccessibilityHappy_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("SQL");
		System.out.println("Overall Sentiment Score - SQL Widget Count after Accessibility and Happy Filter : "
				+ sqlWidgetCount_AccessibilityHappy_Filter);
		assertContains("Overall Sentiment Score - SQL with Accessibility and Happy Filter Count is Correct",
				"Overall Sentiment Score - SQL with Accessibility and Happy Filter Count is Incorrect",
				String.valueOf(sqlWidgetCount_AccessibilityHappy_Filter), "1");
		int overallSentimentScore_SqlAccessibilityHappyFilter = api
				.getOverallSentimentScoreSqlCount_AccessibilityHappyFilter();
		System.out.println("Overall Sentiment Score - SQL in Dashboard Page with Accessibility Happy filter is "
				+ overallSentimentScore_SqlAccessibilityHappyFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - SQL with Accessibility Happy Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - SQL with Accessibility Happy Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_SqlAccessibilityHappyFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_Accessibility_Sql_And_Happy));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_SqlAccessibilityUnhappyFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Service Quality Level", "Accessibility");
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Voice Sentiments", "Unhappy");
		int totalNumberOfVoicesInVoiceListPage_Accessibility_Sql_And_Unhappy = getVoicesListPage()
				.getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Accessibility SQL and Unhappy Filter "
				+ totalNumberOfVoicesInVoiceListPage_Accessibility_Sql_And_Unhappy);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().filterDashboardByDropdown("SQL", "Accessibility");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sentiment", "Unhappy");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("SQL");
		int sqlWidgetCount_AccessibilityUnhappy_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("SQL");
		System.out.println("Overall Sentiment Score - SQL Widget Count after Accessibility and Unhappy Filter : "
				+ sqlWidgetCount_AccessibilityUnhappy_Filter);
		assertContains("Overall Sentiment Score - SQL with Accessibility and Unhappy Filter Count is Correct",
				"Overall Sentiment Score - SQL with Accessibility and Unhappy Filter Count is Incorrect",
				String.valueOf(sqlWidgetCount_AccessibilityUnhappy_Filter), "1");
		int overallSentimentScore_SqlAccessibilityUnhappyFilter = api
				.getOverallSentimentScoreSqlCount_AccessibilityUnhappyFilter();
		System.out.println("Overall Sentiment Score - SQL in Dashboard Page with Accessibility Unhappy filter is "
				+ overallSentimentScore_SqlAccessibilityUnhappyFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - SQL with Accessibility Unhappy Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - SQL with Accessibility Unhappy Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_SqlAccessibilityUnhappyFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_Accessibility_Sql_And_Unhappy));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_SqlAccessibilityNeutralFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Service Quality Level", "Accessibility");
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Voice Sentiments", "Neutral");
		int totalNumberOfVoicesInVoiceListPage_Accessibility_Sql_And_Neutral = getVoicesListPage()
				.getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Accessibility SQL and Neutral Filter "
				+ totalNumberOfVoicesInVoiceListPage_Accessibility_Sql_And_Neutral);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().filterDashboardByDropdown("SQL", "Accessibility");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sentiment", "Neutral");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("SQL");
		int sqlWidgetCount_AccessibilityNeutral_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("SQL");
		System.out.println("Overall Sentiment Score - SQL Widget Count after Accessibility and Neutral Filter : "
				+ sqlWidgetCount_AccessibilityNeutral_Filter);
		assertContains("Overall Sentiment Score - SQL with Accessibility and Neutral Filter Count is Correct",
				"Overall Sentiment Score - SQL with Accessibility and Neutral Filter Count is Incorrect",
				String.valueOf(sqlWidgetCount_AccessibilityNeutral_Filter), "1");
		int overallSentimentScore_SqlAccessibilityNeutralFilter = api
				.getOverallSentimentScoreSqlCount_AccessibilityNeutralFilter();
		System.out.println("Overall Sentiment Score - SQL in Dashboard Page with Accessibility Neutral filter is "
				+ overallSentimentScore_SqlAccessibilityNeutralFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - SQL with Accessibility Neutral Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - SQL with Accessibility Neutral Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_SqlAccessibilityNeutralFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_Accessibility_Sql_And_Neutral));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_AspectGenericFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilterTextBox("Aspect", "Generic");
		int totalVoicesInVoiceListPage_Aspect_Generic = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Aspect Generic Filter "
				+ totalVoicesInVoiceListPage_Aspect_Generic);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().sendTextAndFilterInAdvancedFilter("Aspect", "Generic");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Aspect");
		int channelWidgetCount_Aspect_InfoQuality_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Aspect");
		System.out.println("Overall Sentiment Score - Aspect Count after Generic Filter : "
				+ channelWidgetCount_Aspect_InfoQuality_Filter);
		assertContains("Overall Sentiment Score - Aspect with Generic Filter Count is Correct",
				"Overall Sentiment Score - Aspect with Generic Filter Count is Incorrect",
				String.valueOf(channelWidgetCount_Aspect_InfoQuality_Filter), "1");
		int overallSentimentScore_AspectIformationQualityFilter = api
				.getOverallSentimentScoreAspectCount_GenericFilter();
		System.out.println("Overall Sentiment Score - Aspect in Dashboard Page with Generic filter is "
				+ overallSentimentScore_AspectIformationQualityFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Aspect with Generic Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Aspect with Generic Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_AspectIformationQualityFilter),
				String.valueOf(totalVoicesInVoiceListPage_Aspect_Generic));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_AspectChannelFocusedFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilterTextBox("Aspect", "Channel-Focused");
		int totalVoicesInVoiceListPage_Aspect_ChannelFocused = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Aspect ChannelFocused Filter "
				+ totalVoicesInVoiceListPage_Aspect_ChannelFocused);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().sendTextAndFilterInAdvancedFilter("Aspect", "Channel-Focused");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Aspect");
		int channelWidgetCount_Aspect_InfoQuality_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Aspect");
		System.out.println("Overall Sentiment Score - Aspect Count after Channel Focused Filter : "
				+ channelWidgetCount_Aspect_InfoQuality_Filter);
		assertContains("Overall Sentiment Score - Aspect with Channel Focused Filter Count is Correct",
				"Overall Sentiment Score - Aspect with Channel Focused Filter Count is Incorrect",
				String.valueOf(channelWidgetCount_Aspect_InfoQuality_Filter), "1");
		int overallSentimentScore_AspectIformationQualityFilter = api
				.getOverallSentimentScoreAspectCount_ChannelFocusedFilter();
		System.out.println("Overall Sentiment Score - Aspect in Dashboard Page with Channel Focused filter is "
				+ overallSentimentScore_AspectIformationQualityFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Aspect with Channel Focused Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Aspect with Channel Focused Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_AspectIformationQualityFilter),
				String.valueOf(totalVoicesInVoiceListPage_Aspect_ChannelFocused));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_AspectGenericHappyFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilterTextBox("Aspect", "Generic");
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Voice Sentiments", "Happy");
		int totalNumberOfVoicesInVoiceListPage_Aspect_Generic_And_Happy = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Generic Aspect and Happy Filter "
				+ totalNumberOfVoicesInVoiceListPage_Aspect_Generic_And_Happy);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().sendTextAndFilterInAdvancedFilter("Aspect", "Generic");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sentiment", "Happy");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Aspect");
		int AspectWidgetCount_GenericHappy_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Aspect");
		System.out.println("Overall Sentiment Score - Aspect Widget Count after Generic and Happy Filter : "
				+ AspectWidgetCount_GenericHappy_Filter);
		assertContains("Overall Sentiment Score - Aspect with Generic and Happy Filter Count is Correct",
				"Overall Sentiment Score - Aspect with Generic and Happy Filter Count is Incorrect",
				String.valueOf(AspectWidgetCount_GenericHappy_Filter), "1");
		int overallSentimentScore_AspectGenericHappyFilter = api
				.getOverallSentimentScoreAspectCount_GenericHappyFilter();
		System.out.println("Overall Sentiment Score - Aspect in Dashboard Page with Generic Happy filter is "
				+ overallSentimentScore_AspectGenericHappyFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Aspect with Generic Happy Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Aspect with Generic Happy Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_AspectGenericHappyFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_Aspect_Generic_And_Happy));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_AspectGenericUnhappyFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilterTextBox("Aspect", "Generic");
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Voice Sentiments", "Unhappy");
		int totalNumberOfVoicesInVoiceListPage_Aspect_Generic_And_Unhappy = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Generic Aspect and Unhappy Filter "
				+ totalNumberOfVoicesInVoiceListPage_Aspect_Generic_And_Unhappy);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().sendTextAndFilterInAdvancedFilter("Aspect", "Generic");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sentiment", "Unhappy");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Aspect");
		int AspectWidgetCount_GenericUnhappy_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Aspect");
		System.out.println("Overall Sentiment Score - Aspect Widget Count after Generic and Unhappy Filter : "
				+ AspectWidgetCount_GenericUnhappy_Filter);
		assertContains("Overall Sentiment Score - Aspect with Generic and Unhappy Filter Count is Correct",
				"Overall Sentiment Score - Aspect with Generic and Unhappy Filter Count is Incorrect",
				String.valueOf(AspectWidgetCount_GenericUnhappy_Filter), "1");
		int overallSentimentScore_AspectGenericUnhappyFilter = api
				.getOverallSentimentScoreAspectCount_GenericUnhappyFilter();
		System.out.println("Overall Sentiment Score - Aspect in Dashboard Page with Generic Unhappy filter is "
				+ overallSentimentScore_AspectGenericUnhappyFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Aspect with Generic Unhappy Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Aspect with Generic Unhappy Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_AspectGenericUnhappyFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_Aspect_Generic_And_Unhappy));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_AspectGenericNeutralFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilterTextBox("Aspect", "Generic");
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Voice Sentiments", "Neutral");
		int totalNumberOfVoicesInVoiceListPage_Aspect_Generic_And_Neutral = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Generic Aspect and Neutral Filter "
				+ totalNumberOfVoicesInVoiceListPage_Aspect_Generic_And_Neutral);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().sendTextAndFilterInAdvancedFilter("Aspect", "Generic");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sentiment", "Neutral");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Aspect");
		int AspectWidgetCount_GenericNeutral_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Aspect");
		System.out.println("Overall Sentiment Score - Aspect Widget Count after Generic and Neutral Filter : "
				+ AspectWidgetCount_GenericNeutral_Filter);
		assertContains("Overall Sentiment Score - Aspect with Generic and Neutral Filter Count is Correct",
				"Overall Sentiment Score - Aspect with Generic and Neutral Filter Count is Incorrect",
				String.valueOf(AspectWidgetCount_GenericNeutral_Filter), "1");
		int overallSentimentScore_AspectGenericNeutralFilter = api
				.getOverallSentimentScoreAspectCount_GenericNeutralFilter();
		System.out.println("Overall Sentiment Score - Aspect in Dashboard Page with Generic Neutral filter is "
				+ overallSentimentScore_AspectGenericNeutralFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Aspect with Generic Neutral Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Aspect with Generic Neutral Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_AspectGenericNeutralFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_Aspect_Generic_And_Neutral));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_AspectChannelFocusedHappyFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilterTextBox("Aspect", "Channel-Focused");
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Voice Sentiments", "Happy");
		int totalNumberOfVoicesInVoiceListPage_Aspect_ChannelFocused_And_Happy = getVoicesListPage()
				.getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Channel Focused Aspect and Happy Filter "
				+ totalNumberOfVoicesInVoiceListPage_Aspect_ChannelFocused_And_Happy);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().sendTextAndFilterInAdvancedFilter("Aspect", "Channel-Focused");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sentiment", "Happy");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Aspect");
		int AspectWidgetCount_ChannelFocusedHappy_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Aspect");
		System.out.println("Overall Sentiment Score - Aspect Widget Count after Channel Focused and Happy Filter : "
				+ AspectWidgetCount_ChannelFocusedHappy_Filter);
		assertContains("Overall Sentiment Score - Aspect with Channel Focused and Happy Filter Count is Correct",
				"Overall Sentiment Score - Aspect with Channel Focused and Happy Filter Count is Incorrect",
				String.valueOf(AspectWidgetCount_ChannelFocusedHappy_Filter), "1");
		int overallSentimentScore_AspectChannelFocusedHappyFilter = api
				.getOverallSentimentScoreAspectCount_ChannelFocusedHappyFilter();
		System.out.println("Overall Sentiment Score - Aspect in Dashboard Page with Channel Focused Happy filter is "
				+ overallSentimentScore_AspectChannelFocusedHappyFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Aspect with Channel Focused Happy Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Aspect with Channel Focused Happy Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_AspectChannelFocusedHappyFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_Aspect_ChannelFocused_And_Happy));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_AspectChannelFocusedUnhappyFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilterTextBox("Aspect", "Channel-Focused");
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Voice Sentiments", "Unhappy");
		int totalNumberOfVoicesInVoiceListPage_Aspect_ChannelFocused_And_Unhappy = getVoicesListPage()
				.getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Channel Focused Aspect and Unhappy Filter "
				+ totalNumberOfVoicesInVoiceListPage_Aspect_ChannelFocused_And_Unhappy);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().sendTextAndFilterInAdvancedFilter("Aspect", "Channel-Focused");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sentiment", "Unhappy");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Aspect");
		int AspectWidgetCount_ChannelFocusedUnhappy_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Aspect");
		System.out.println("Overall Sentiment Score - Aspect Widget Count after Channel Focused and Unhappy Filter : "
				+ AspectWidgetCount_ChannelFocusedUnhappy_Filter);
		assertContains("Overall Sentiment Score - Aspect with Channel Focused and Unhappy Filter Count is Correct",
				"Overall Sentiment Score - Aspect with Channel Focused and Unhappy Filter Count is Incorrect",
				String.valueOf(AspectWidgetCount_ChannelFocusedUnhappy_Filter), "1");
		int overallSentimentScore_AspectChannelFocusedUnhappyFilter = api
				.getOverallSentimentScoreAspectCount_ChannelFocusedUnhappyFilter();
		System.out.println("Overall Sentiment Score - Aspect in Dashboard Page with Channel Focused Unhappy filter is "
				+ overallSentimentScore_AspectChannelFocusedUnhappyFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Aspect with Channel Focused Unhappy Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Aspect with Channel Focused Unhappy Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_AspectChannelFocusedUnhappyFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_Aspect_ChannelFocused_And_Unhappy));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_AspectChannelFocusedNeutralFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilterTextBox("Aspect", "Channel-Focused");
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Voice Sentiments", "Neutral");
		int totalNumberOfVoicesInVoiceListPage_Aspect_ChannelFocused_And_Neutral = getVoicesListPage()
				.getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Channel Focused Aspect and Neutral Filter "
				+ totalNumberOfVoicesInVoiceListPage_Aspect_ChannelFocused_And_Neutral);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().sendTextAndFilterInAdvancedFilter("Aspect", "Channel-Focused");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sentiment", "Neutral");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Aspect");
		int AspectWidgetCount_ChannelFocusedNeutral_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Aspect");
		System.out.println("Overall Sentiment Score - Aspect Widget Count after Channel Focused and Neutral Filter : "
				+ AspectWidgetCount_ChannelFocusedNeutral_Filter);
		assertContains("Overall Sentiment Score - Aspect with Channel Focused and Neutral Filter Count is Correct",
				"Overall Sentiment Score - Aspect with Channel Focused and Neutral Filter Count is Incorrect",
				String.valueOf(AspectWidgetCount_ChannelFocusedNeutral_Filter), "1");
		int overallSentimentScore_AspectChannelFocusedNeutralFilter = api
				.getOverallSentimentScoreAspectCount_ChannelFocusedNeutralFilter();
		System.out.println("Overall Sentiment Score - Aspect in Dashboard Page with Channel Focused Neutral filter is "
				+ overallSentimentScore_AspectChannelFocusedNeutralFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Aspect with Channel Focused Neutral Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Aspect with Channel Focused Neutral Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_AspectChannelFocusedNeutralFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_Aspect_ChannelFocused_And_Neutral));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_ServiceBillingServicesFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Services", "Billing Services");
		int totalVoicesInVoiceListPage_Service_BillingServices = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Service Billing Services Filter "
				+ totalVoicesInVoiceListPage_Service_BillingServices);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().filterDashboardByDropdown("Main Services", "Billing Services");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Service");
		int channelWidgetCount_Service_InfoQuality_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Service");
		System.out.println("Overall Sentiment Score - Service Count after Billing Services Filter : "
				+ channelWidgetCount_Service_InfoQuality_Filter);
		assertContains("Overall Sentiment Score - Service with Billing Services Filter Count is Correct",
				"Overall Sentiment Score - Service with Billing Services Filter Count is Incorrect",
				String.valueOf(channelWidgetCount_Service_InfoQuality_Filter), "1");
		int overallSentimentScore_ServiceIformationQualityFilter = api
				.getOverallSentimentScoreServiceCount_BillingServicesFilter();
		System.out.println("Overall Sentiment Score - Service in Dashboard Page with Billing Services filter is "
				+ overallSentimentScore_ServiceIformationQualityFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Service with Billing Services Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Service with Billing Services Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_ServiceIformationQualityFilter),
				String.valueOf(totalVoicesInVoiceListPage_Service_BillingServices));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_ServiceNocServicesFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Services", "NOC Services");
		int totalVoicesInVoiceListPage_Service_NocServices = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Service NOC Services Filter "
				+ totalVoicesInVoiceListPage_Service_NocServices);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().filterDashboardByDropdown("Main Services", "NOC Services");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Service");
		int channelWidgetCount_Service_InfoQuality_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Service");
		System.out.println("Overall Sentiment Score - Service Count after NOC Services Filter : "
				+ channelWidgetCount_Service_InfoQuality_Filter);
		assertContains("Overall Sentiment Score - Service with NOC Services Filter Count is Correct",
				"Overall Sentiment Score - Service with NOC Services Filter Count is Incorrect",
				String.valueOf(channelWidgetCount_Service_InfoQuality_Filter), "1");
		int overallSentimentScore_ServiceIformationQualityFilter = api
				.getOverallSentimentScoreServiceCount_NocServicesFilter();
		System.out.println("Overall Sentiment Score - Service in Dashboard Page with NOC Services filter is "
				+ overallSentimentScore_ServiceIformationQualityFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Service with NOC Services Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Service with NOC Services Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_ServiceIformationQualityFilter),
				String.valueOf(totalVoicesInVoiceListPage_Service_NocServices));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_ServiceBillingServicesHappyFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Services", "Billing Services");
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Voice Sentiments", "Happy");
		int totalNumberOfVoicesInVoiceListPage_Services_BillingServices_And_Happy = getVoicesListPage()
				.getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Billing Services Services and Happy Filter "
				+ totalNumberOfVoicesInVoiceListPage_Services_BillingServices_And_Happy);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().filterDashboardByDropdown("Main Services", "Billing Services");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sentiment", "Happy");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Service");
		int ServicesWidgetCount_BillingServicesHappy_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Service");
		System.out.println("Overall Sentiment Score - Services Widget Count after Billing Services and Happy Filter : "
				+ ServicesWidgetCount_BillingServicesHappy_Filter);
		assertContains("Overall Sentiment Score - Services with Billing Services and Happy Filter Count is Correct",
				"Overall Sentiment Score - Services with Billing Services and Happy Filter Count is Incorrect",
				String.valueOf(ServicesWidgetCount_BillingServicesHappy_Filter), "1");
		int overallSentimentScore_ServicesBillingServicesHappyFilter = api
				.getOverallSentimentScoreServicesCount_BillingServicesHappyFilter();
		System.out.println("Overall Sentiment Score - Services in Dashboard Page with Billing Services Happy filter is "
				+ overallSentimentScore_ServicesBillingServicesHappyFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Services with Billing Services Happy Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Services with Billing Services Happy Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_ServicesBillingServicesHappyFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_Services_BillingServices_And_Happy));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_ServiceBillingServicesUnhappyFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Services", "Billing Services");
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Voice Sentiments", "Unhappy");
		int totalNumberOfVoicesInVoiceListPage_Services_BillingServices_And_Unhappy = getVoicesListPage()
				.getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Billing Services Services and Unhappy Filter "
				+ totalNumberOfVoicesInVoiceListPage_Services_BillingServices_And_Unhappy);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().filterDashboardByDropdown("Main Services", "Billing Services");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sentiment", "Unhappy");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Service");
		int ServicesWidgetCount_BillingServicesUnhappy_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Service");
		System.out
				.println("Overall Sentiment Score - Services Widget Count after Billing Services and Unhappy Filter : "
						+ ServicesWidgetCount_BillingServicesUnhappy_Filter);
		assertContains("Overall Sentiment Score - Services with Billing Services and Unhappy Filter Count is Correct",
				"Overall Sentiment Score - Services with Billing Services and Unhappy Filter Count is Incorrect",
				String.valueOf(ServicesWidgetCount_BillingServicesUnhappy_Filter), "1");
		int overallSentimentScore_ServicesBillingServicesUnhappyFilter = api
				.getOverallSentimentScoreServicesCount_BillingServicesUnhappyFilter();
		System.out
				.println("Overall Sentiment Score - Services in Dashboard Page with Billing Services Unhappy filter is "
						+ overallSentimentScore_ServicesBillingServicesUnhappyFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Services with Billing Services Unhappy Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Services with Billing Services Unhappy Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_ServicesBillingServicesUnhappyFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_Services_BillingServices_And_Unhappy));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_ServiceBillingServicesNeutralFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Services", "Billing Services");
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Voice Sentiments", "Neutral");
		int totalNumberOfVoicesInVoiceListPage_Services_BillingServices_And_Neutral = getVoicesListPage()
				.getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Billing Services Services and Neutral Filter "
				+ totalNumberOfVoicesInVoiceListPage_Services_BillingServices_And_Neutral);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().filterDashboardByDropdown("Main Services", "Billing Services");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sentiment", "Neutral");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Service");
		int ServicesWidgetCount_BillingServicesNeutral_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Service");
		System.out
				.println("Overall Sentiment Score - Services Widget Count after Billing Services and Neutral Filter : "
						+ ServicesWidgetCount_BillingServicesNeutral_Filter);
		assertContains("Overall Sentiment Score - Services with Billing Services and Neutral Filter Count is Correct",
				"Overall Sentiment Score - Services with Billing Services and Neutral Filter Count is Incorrect",
				String.valueOf(ServicesWidgetCount_BillingServicesNeutral_Filter), "1");
		int overallSentimentScore_ServicesBillingServicesNeutralFilter = api
				.getOverallSentimentScoreServicesCount_BillingServicesNeutralFilter();
		System.out
				.println("Overall Sentiment Score - Services in Dashboard Page with Billing Services Neutral filter is "
						+ overallSentimentScore_ServicesBillingServicesNeutralFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Services with Billing Services Neutral Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Services with Billing Services Neutral Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_ServicesBillingServicesNeutralFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_Services_BillingServices_And_Neutral));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_ServiceNocServicesHappyFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Services", "NOC Services");
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Voice Sentiments", "Happy");
		int totalNumberOfVoicesInVoiceListPage_Services_NocServices_And_Happy = getVoicesListPage()
				.getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with NOC Services Services and Happy Filter "
				+ totalNumberOfVoicesInVoiceListPage_Services_NocServices_And_Happy);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().filterDashboardByDropdown("Main Services", "NOC Services");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sentiment", "Happy");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Service");
		int ServicesWidgetCount_NocServicesHappy_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Service");
		System.out.println("Overall Sentiment Score - Services Widget Count after NOC Services and Happy Filter : "
				+ ServicesWidgetCount_NocServicesHappy_Filter);
		assertContains("Overall Sentiment Score - Services with NOC Services and Happy Filter Count is Correct",
				"Overall Sentiment Score - Services with NOC Services and Happy Filter Count is Incorrect",
				String.valueOf(ServicesWidgetCount_NocServicesHappy_Filter), "1");
		int overallSentimentScore_ServicesNocServicesHappyFilter = api
				.getOverallSentimentScoreServicesCount_NocServicesHappyFilter();
		System.out.println("Overall Sentiment Score - Services in Dashboard Page with NOC Services Happy filter is "
				+ overallSentimentScore_ServicesNocServicesHappyFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Services with NOC Services Happy Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Services with NOC Services Happy Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_ServicesNocServicesHappyFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_Services_NocServices_And_Happy));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_ServiceNocServicesUnhappyFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Services", "NOC Services");
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Voice Sentiments", "Unhappy");
		int totalNumberOfVoicesInVoiceListPage_Services_NocServices_And_Unhappy = getVoicesListPage()
				.getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with NOC Services Services and Unhappy Filter "
				+ totalNumberOfVoicesInVoiceListPage_Services_NocServices_And_Unhappy);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().filterDashboardByDropdown("Main Services", "NOC Services");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sentiment", "Unhappy");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Service");
		int ServicesWidgetCount_NocServicesUnhappy_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Service");
		System.out.println("Overall Sentiment Score - Services Widget Count after NOC Services and Unhappy Filter : "
				+ ServicesWidgetCount_NocServicesUnhappy_Filter);
		assertContains("Overall Sentiment Score - Services with NOC Services and Unhappy Filter Count is Correct",
				"Overall Sentiment Score - Services with NOC Services and Unhappy Filter Count is Incorrect",
				String.valueOf(ServicesWidgetCount_NocServicesUnhappy_Filter), "1");
		int overallSentimentScore_ServicesNocServicesUnhappyFilter = api
				.getOverallSentimentScoreServicesCount_NocServicesUnhappyFilter();
		System.out.println("Overall Sentiment Score - Services in Dashboard Page with NOC Services Unhappy filter is "
				+ overallSentimentScore_ServicesNocServicesUnhappyFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Services with NOC Services Unhappy Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Services with NOC Services Unhappy Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_ServicesNocServicesUnhappyFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_Services_NocServices_And_Unhappy));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_ServiceNocServicesNeutralFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Services", "NOC Services");
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Voice Sentiments", "Neutral");
		int totalNumberOfVoicesInVoiceListPage_Services_NocServices_And_Neutral = getVoicesListPage()
				.getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with NOC Services Services and Neutral Filter "
				+ totalNumberOfVoicesInVoiceListPage_Services_NocServices_And_Neutral);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().filterDashboardByDropdown("Main Services", "NOC Services");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sentiment", "Neutral");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Service");
		int ServicesWidgetCount_NocServicesNeutral_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Service");
		System.out.println("Overall Sentiment Score - Services Widget Count after NOC Services and Neutral Filter : "
				+ ServicesWidgetCount_NocServicesNeutral_Filter);
		assertContains("Overall Sentiment Score - Services with NOC Services and Neutral Filter Count is Correct",
				"Overall Sentiment Score - Services with NOC Services and Neutral Filter Count is Incorrect",
				String.valueOf(ServicesWidgetCount_NocServicesNeutral_Filter), "1");
		int overallSentimentScore_ServicesNocServicesNeutralFilter = api
				.getOverallSentimentScoreServicesCount_NocServicesNeutralFilter();
		System.out.println("Overall Sentiment Score - Services in Dashboard Page with NOC Services Neutral filter is "
				+ overallSentimentScore_ServicesNocServicesNeutralFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Services with NOC Services Neutral Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Services with NOC Services Neutral Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_ServicesNocServicesNeutralFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_Services_NocServices_And_Neutral));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_SubServiceBillPaymentFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Sub Service", "Bill Payment");
		int totalVoicesInVoiceListPage_SubService_BillPayment = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Sub Service Bill Payment Filter "
				+ totalVoicesInVoiceListPage_SubService_BillPayment);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sub Services", "Bill Payment");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Sub Service");
		int channelWidgetCount_SubService_InfoQuality_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Sub Service");
		System.out.println("Overall Sentiment Score - Sub Service Count after Bill Payment Filter : "
				+ channelWidgetCount_SubService_InfoQuality_Filter);
		assertContains("Overall Sentiment Score - Sub Service with Bill Payment Filter Count is Correct",
				"Overall Sentiment Score - Sub Service with Bill Payment Filter Count is Incorrect",
				String.valueOf(channelWidgetCount_SubService_InfoQuality_Filter), "1");
		int overallSentimentScore_SubServiceBillPaymentFilter = api
				.getOverallSentimentScoreSubServiceCount_BillPaymentFilter();
		System.out.println("Overall Sentiment Score - Sub Service in Dashboard Page with Bill Payment filter is "
				+ overallSentimentScore_SubServiceBillPaymentFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Sub Service with Bill Payment Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Sub Service with Bill Payment Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_SubServiceBillPaymentFilter),
				String.valueOf(totalVoicesInVoiceListPage_SubService_BillPayment));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_SubServiceEvChargingFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Sub Service", "EV charging");
		int totalVoicesInVoiceListPage_SubService_EvCharger = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Sub Service EV charger Filter "
				+ totalVoicesInVoiceListPage_SubService_EvCharger);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sub Services", "EV charging");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Sub Service");
		int channelWidgetCount_SubService_InfoQuality_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Sub Service");
		System.out.println("Overall Sentiment Score - Sub Service Count after EV charger Filter : "
				+ channelWidgetCount_SubService_InfoQuality_Filter);
		assertContains("Overall Sentiment Score - Sub Service with EV charger Filter Count is Correct",
				"Overall Sentiment Score - Sub Service with EV charger Filter Count is Incorrect",
				String.valueOf(channelWidgetCount_SubService_InfoQuality_Filter), "1");
		int overallSentimentScore_SubServiceEvChargerFilter = api
				.getOverallSentimentScoreSubServiceCount_EvChargerFilter();
		System.out.println("Overall Sentiment Score - Sub Service in Dashboard Page with EV charger filter is "
				+ overallSentimentScore_SubServiceEvChargerFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Sub Service with EV charger Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Sub Service with EV charger Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_SubServiceEvChargerFilter),
				String.valueOf(totalVoicesInVoiceListPage_SubService_EvCharger));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_SubServiceBillPaymentHappyFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Sub Service", "Bill Payment");
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Voice Sentiments", "Happy");
		int totalNumberOfVoicesInVoiceListPage_SubServices_BillPayment_And_Happy = getVoicesListPage()
				.getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Bill Payment Sub Services and Happy Filter "
				+ totalNumberOfVoicesInVoiceListPage_SubServices_BillPayment_And_Happy);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sub Services", "Bill Payment");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sentiment", "Happy");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Sub Service");
		int subServicesWidgetCount_BillPaymentHappy_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Sub Service");
		System.out.println("Overall Sentiment Score - Sub Services Widget Count after Bill Payment and Happy Filter : "
				+ subServicesWidgetCount_BillPaymentHappy_Filter);
		assertContains("Overall Sentiment Score - Sub Services with Bill Payment and Happy Filter Count is Correct",
				"Overall Sentiment Score - Sub Services with Bill Payment and Happy Filter Count is Incorrect",
				String.valueOf(subServicesWidgetCount_BillPaymentHappy_Filter), "1");
		int overallSentimentScore_SubServicesBillPaymentHappyFilter = api
				.getOverallSentimentScoreSubServicesCount_BillPaymentHappyFilter();
		System.out.println("Overall Sentiment Score - Sub Services in Dashboard Page with Bill Payment Happy filter is "
				+ overallSentimentScore_SubServicesBillPaymentHappyFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Sub Services with Bill Payment Happy Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Sub Services with Bill Payment Happy Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_SubServicesBillPaymentHappyFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_SubServices_BillPayment_And_Happy));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_SubServiceBillPaymentUnhappyFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Sub Service", "Bill Payment");
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Voice Sentiments", "Unhappy");
		int totalNumberOfVoicesInVoiceListPage_SubServices_BillPayment_And_Unhappy = getVoicesListPage()
				.getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Bill Payment Sub Services and Unhappy Filter "
				+ totalNumberOfVoicesInVoiceListPage_SubServices_BillPayment_And_Unhappy);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sub Services", "Bill Payment");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sentiment", "Unhappy");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Sub Service");
		int subServicesWidgetCount_BillPaymentUnhappy_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Sub Service");
		System.out
				.println("Overall Sentiment Score - Sub Services Widget Count after Bill Payment and Unhappy Filter : "
						+ subServicesWidgetCount_BillPaymentUnhappy_Filter);
		assertContains("Overall Sentiment Score - Sub Services with Bill Payment and Unhappy Filter Count is Correct",
				"Overall Sentiment Score - Sub Services with Bill Payment and Unhappy Filter Count is Incorrect",
				String.valueOf(subServicesWidgetCount_BillPaymentUnhappy_Filter), "1");
		int overallSentimentScore_SubServicesBillPaymentUnhappyFilter = api
				.getOverallSentimentScoreSubServicesCount_BillPaymentUnhappyFilter();
		System.out
				.println("Overall Sentiment Score - Sub Services in Dashboard Page with Bill Payment Unhappy filter is "
						+ overallSentimentScore_SubServicesBillPaymentUnhappyFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Sub Services with Bill Payment Unhappy Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Sub Services with Bill Payment Unhappy Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_SubServicesBillPaymentUnhappyFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_SubServices_BillPayment_And_Unhappy));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_SubServiceBillPaymentNeutralFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Sub Service", "Bill Payment");
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Voice Sentiments", "Neutral");
		int totalNumberOfVoicesInVoiceListPage_SubServices_BillPayment_And_Neutral = getVoicesListPage()
				.getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Bill Payment Sub Services and Neutral Filter "
				+ totalNumberOfVoicesInVoiceListPage_SubServices_BillPayment_And_Neutral);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sub Services", "Bill Payment");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sentiment", "Neutral");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Sub Service");
		int subServicesWidgetCount_BillPaymentNeutral_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Sub Service");
		System.out
				.println("Overall Sentiment Score - Sub Services Widget Count after Bill Payment and Neutral Filter : "
						+ subServicesWidgetCount_BillPaymentNeutral_Filter);
		assertContains("Overall Sentiment Score - Sub Services with Bill Payment and Neutral Filter Count is Correct",
				"Overall Sentiment Score - Sub Services with Bill Payment and Neutral Filter Count is Incorrect",
				String.valueOf(subServicesWidgetCount_BillPaymentNeutral_Filter), "1");
		int overallSentimentScore_SubServicesBillPaymentNeutralFilter = api
				.getOverallSentimentScoreSubServicesCount_BillPaymentNeutralFilter();
		System.out
				.println("Overall Sentiment Score - Sub Services in Dashboard Page with Bill Payment Neutral filter is "
						+ overallSentimentScore_SubServicesBillPaymentNeutralFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Sub Services with Bill Payment Neutral Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Sub Services with Bill Payment Neutral Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_SubServicesBillPaymentNeutralFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_SubServices_BillPayment_And_Neutral));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_SubServiceEvChargingHappyFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Sub Service", "EV charging");
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Voice Sentiments", "Happy");
		int totalNumberOfVoicesInVoiceListPage_SubServices_EvCharging_And_Happy = getVoicesListPage()
				.getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with EV charging Sub Services and Happy Filter "
				+ totalNumberOfVoicesInVoiceListPage_SubServices_EvCharging_And_Happy);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sub Services", "EV charging");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sentiment", "Happy");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Sub Service");
		int subServicesWidgetCount_EvChargingHappy_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Sub Service");
		System.out.println("Overall Sentiment Score - Sub Services Widget Count after EV charging and Happy Filter : "
				+ subServicesWidgetCount_EvChargingHappy_Filter);
		assertContains("Overall Sentiment Score - Sub Services with EV charging and Happy Filter Count is Correct",
				"Overall Sentiment Score - Sub Services with EV charging and Happy Filter Count is Incorrect",
				String.valueOf(subServicesWidgetCount_EvChargingHappy_Filter), "0");
		int overallSentimentScore_SubServicesEvChargingHappyFilter = api
				.getOverallSentimentScoreSubServicesCount_EvChargingHappyFilter();
		System.out.println("Overall Sentiment Score - Sub Services in Dashboard Page with EV charging Happy filter is "
				+ overallSentimentScore_SubServicesEvChargingHappyFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Sub Services with EV charging Happy Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Sub Services with EV charging Happy Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_SubServicesEvChargingHappyFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_SubServices_EvCharging_And_Happy));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_SubServiceEvChargingUnhappyFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Sub Service", "EV charging");
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Voice Sentiments", "Unhappy");
		int totalNumberOfVoicesInVoiceListPage_SubServices_EvCharging_And_Unhappy = getVoicesListPage()
				.getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with EV charging Sub Services and Unhappy Filter "
				+ totalNumberOfVoicesInVoiceListPage_SubServices_EvCharging_And_Unhappy);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sub Services", "EV charging");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sentiment", "Unhappy");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Sub Service");
		int subServicesWidgetCount_EvChargingUnhappy_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Sub Service");
		System.out.println("Overall Sentiment Score - Sub Services Widget Count after EV charging and Unhappy Filter : "
				+ subServicesWidgetCount_EvChargingUnhappy_Filter);
		assertContains("Overall Sentiment Score - Sub Services with EV charging and Unhappy Filter Count is Correct",
				"Overall Sentiment Score - Sub Services with EV charging and Unhappy Filter Count is Incorrect",
				String.valueOf(subServicesWidgetCount_EvChargingUnhappy_Filter), "1");
		int overallSentimentScore_SubServicesEvChargingUnhappyFilter = api
				.getOverallSentimentScoreSubServicesCount_EvChargingUnhappyFilter();
		System.out
				.println("Overall Sentiment Score - Sub Services in Dashboard Page with EV charging Unhappy filter is "
						+ overallSentimentScore_SubServicesEvChargingUnhappyFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Sub Services with EV charging Unhappy Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Sub Services with EV charging Unhappy Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_SubServicesEvChargingUnhappyFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_SubServices_EvCharging_And_Unhappy));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_SubServiceEvChargingNeutralFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Sub Service", "EV charging");
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Voice Sentiments", "Neutral");
		int totalNumberOfVoicesInVoiceListPage_SubServices_EvCharging_And_Neutral = getVoicesListPage()
				.getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with EV charging Sub Services and Neutral Filter "
				+ totalNumberOfVoicesInVoiceListPage_SubServices_EvCharging_And_Neutral);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sub Services", "EV charging");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sentiment", "Neutral");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Sub Service");
		int subServicesWidgetCount_EvChargingNeutral_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Sub Service");
		System.out.println("Overall Sentiment Score - Sub Services Widget Count after EV charging and Neutral Filter : "
				+ subServicesWidgetCount_EvChargingNeutral_Filter);
		assertContains("Overall Sentiment Score - Sub Services with EV charging and Neutral Filter Count is Correct",
				"Overall Sentiment Score - Sub Services with EV charging and Neutral Filter Count is Incorrect",
				String.valueOf(subServicesWidgetCount_EvChargingNeutral_Filter), "1");
		int overallSentimentScore_SubServicesEvChargingNeutralFilter = api
				.getOverallSentimentScoreSubServicesCount_EvChargingNeutralFilter();
		System.out
				.println("Overall Sentiment Score - Sub Services in Dashboard Page with EV charging Neutral filter is "
						+ overallSentimentScore_SubServicesEvChargingNeutralFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Sub Services with EV charging Neutral Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Sub Services with EV charging Neutral Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_SubServicesEvChargingNeutralFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_SubServices_EvCharging_And_Neutral));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_CustomerCategoryBusinessFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Customer Category", "Business");
		int totalVoicesInVoiceListPage_CustomerCategory_Business = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Customer Category Business Filter "
				+ totalVoicesInVoiceListPage_CustomerCategory_Business);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Customer Category", "Business");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Customer Category");
		int channelWidgetCount_CustomerCategory_InfoQuality_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Customer Category");
		System.out.println("Overall Sentiment Score - Customer Category Count after Business Filter : "
				+ channelWidgetCount_CustomerCategory_InfoQuality_Filter);
		assertContains("Overall Sentiment Score - Customer Category with Business Filter Count is Correct",
				"Overall Sentiment Score - Customer Category with Business Filter Count is Incorrect",
				String.valueOf(channelWidgetCount_CustomerCategory_InfoQuality_Filter), "1");
		int overallSentimentScore_CustomerCategoryBusinessFilter = api
				.getOverallSentimentScoreCustomerCategoryCount_BusinessFilter();
		System.out.println("Overall Sentiment Score - Customer Category in Dashboard Page with Business filter is "
				+ overallSentimentScore_CustomerCategoryBusinessFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Customer Category with Business Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Customer Category with Business Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_CustomerCategoryBusinessFilter),
				String.valueOf(totalVoicesInVoiceListPage_CustomerCategory_Business));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_CustomerCategoryDewaStaffFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Customer Category", "Business");
		int totalVoicesInVoiceListPage_CustomerCategory_Business = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Customer Category Business Filter "
				+ totalVoicesInVoiceListPage_CustomerCategory_Business);
		navigateFromOnePageToAnother("Dashboard");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Customer Category", "Business");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Customer Category");
		int channelWidgetCount_CustomerCategory_InfoQuality_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Customer Category");
		System.out.println("Overall Sentiment Score - Customer Category Count after Business Filter : "
				+ channelWidgetCount_CustomerCategory_InfoQuality_Filter);
		assertContains("Overall Sentiment Score - Customer Category with Business Filter Count is Correct",
				"Overall Sentiment Score - Customer Category with Business Filter Count is Incorrect",
				String.valueOf(channelWidgetCount_CustomerCategory_InfoQuality_Filter), "1");
		int overallSentimentScore_CustomerCategoryBusinessFilter = api
				.getOverallSentimentScoreCustomerCategoryCount_BusinessFilter();
		System.out.println("Overall Sentiment Score - Customer Category in Dashboard Page with Business filter is "
				+ overallSentimentScore_CustomerCategoryBusinessFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Customer Category with Business Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Customer Category with Business Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_CustomerCategoryBusinessFilter),
				String.valueOf(totalVoicesInVoiceListPage_CustomerCategory_Business));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_CustomerCategoryBusinessHappyFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Customer Category", "Business");
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Voice Sentiments", "Happy");
		int totalNumberOfVoicesInVoiceListPage_CustomerCategory_Business_And_Happy = getVoicesListPage()
				.getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Business Customer Categorys and Happy Filter "
				+ totalNumberOfVoicesInVoiceListPage_CustomerCategory_Business_And_Happy);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Customer Category", "Business");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sentiment", "Happy");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Customer Category");
		int CustomerCategoryWidgetCount_BusinessHappy_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Customer Category");
		System.out.println("Overall Sentiment Score - Customer Category Widget Count after Business and Happy Filter : "
				+ CustomerCategoryWidgetCount_BusinessHappy_Filter);
		assertContains("Overall Sentiment Score - Customer Category with Business and Happy Filter Count is Correct",
				"Overall Sentiment Score - Customer Category with Business and Happy Filter Count is Incorrect",
				String.valueOf(CustomerCategoryWidgetCount_BusinessHappy_Filter), "1");
		int overallSentimentScore_CustomerCategoryBusinessHappyFilter = api
				.getOverallSentimentScoreCustomerCategoryCount_BusinessHappyFilter();
		System.out
				.println("Overall Sentiment Score - Customer Category in Dashboard Page with Business Happy filter is "
						+ overallSentimentScore_CustomerCategoryBusinessHappyFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Customer Category with Business Happy Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Customer Category with Business Happy Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_CustomerCategoryBusinessHappyFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_CustomerCategory_Business_And_Happy));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_CustomerCategoryBusinessUnhappyFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Customer Category", "Business");
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Voice Sentiments", "Unhappy");
		int totalNumberOfVoicesInVoiceListPage_CustomerCategory_Business_And_Unhappy = getVoicesListPage()
				.getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Business Customer Categorys and Unhappy Filter "
				+ totalNumberOfVoicesInVoiceListPage_CustomerCategory_Business_And_Unhappy);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Customer Category", "Business");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sentiment", "Unhappy");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Customer Category");
		int CustomerCategoryWidgetCount_BusinessUnhappy_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Customer Category");
		System.out
				.println("Overall Sentiment Score - Customer Category Widget Count after Business and Unhappy Filter : "
						+ CustomerCategoryWidgetCount_BusinessUnhappy_Filter);
		assertContains("Overall Sentiment Score - Customer Category with Business and Unhappy Filter Count is Correct",
				"Overall Sentiment Score - Customer Category with Business and Unhappy Filter Count is Incorrect",
				String.valueOf(CustomerCategoryWidgetCount_BusinessUnhappy_Filter), "1");
		int overallSentimentScore_CustomerCategoryBusinessUnhappyFilter = api
				.getOverallSentimentScoreCustomerCategoryCount_BusinessUnhappyFilter();
		System.out.println(
				"Overall Sentiment Score - Customer Category in Dashboard Page with Business Unhappy filter is "
						+ overallSentimentScore_CustomerCategoryBusinessUnhappyFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Customer Category with Business Unhappy Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Customer Category with Business Unhappy Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_CustomerCategoryBusinessUnhappyFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_CustomerCategory_Business_And_Unhappy));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScore_CustomerCategoryBusinessNeutralFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Customer Category", "Business");
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Voice Sentiments", "Neutral");
		int totalNumberOfVoicesInVoiceListPage_CustomerCategory_Business_And_Neutral = getVoicesListPage()
				.getTotalNumberOfVoice();
		System.out.println("Total Voice Count in VoiceList Page with Business Customer Categorys and Neutral Filter "
				+ totalNumberOfVoicesInVoiceListPage_CustomerCategory_Business_And_Neutral);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Customer Category", "Business");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sentiment", "Neutral");
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Customer Category");
		int CustomerCategoryWidgetCount_BusinessNeutral_Filter = getDashboardPage()
				.checkHowManyWidgetsAvailableUnderOverallSpectrumScore("Customer Category");
		System.out
				.println("Overall Sentiment Score - Customer Category Widget Count after Business and Neutral Filter : "
						+ CustomerCategoryWidgetCount_BusinessNeutral_Filter);
		assertContains("Overall Sentiment Score - Customer Category with Business and Neutral Filter Count is Correct",
				"Overall Sentiment Score - Customer Category with Business and Neutral Filter Count is Incorrect",
				String.valueOf(CustomerCategoryWidgetCount_BusinessNeutral_Filter), "1");
		int overallSentimentScore_CustomerCategoryBusinessNeutralFilter = api
				.getOverallSentimentScoreCustomerCategoryCount_BusinessNeutralFilter();
		System.out.println(
				"Overall Sentiment Score - Customer Category in Dashboard Page with Business Neutral filter is "
						+ overallSentimentScore_CustomerCategoryBusinessNeutralFilter);
		assertContains(
				"Number of Voice Count in Overall Sentiment Score - Customer Category with Business Neutral Filter is Equal to Voice Count in Voice List Page",
				"Number of Voice Count in Overall Sentiment Score - Customer Category with Business Neutral Filter is not Equal to Voice Count in Voice List Page",
				String.valueOf(overallSentimentScore_CustomerCategoryBusinessNeutralFilter),
				String.valueOf(totalNumberOfVoicesInVoiceListPage_CustomerCategory_Business_And_Neutral));
		System.out.println("---------------");
	}

	public void verifyDashboardOverallSentimentScorePageNavigator(String subsName, String projectName) {
		navigateTillDashboard(subsName, projectName);
		getDashboardPage().clickOnOverallSentimentScoreHeaders("Sub Service");
		getDashboardPage().clickOnNextButtonInSubServiceUnderOverallSentimentScore();
		boolean nextBtnResult = getDashboardPage().visibilityOfPreviousBttnInSunSrvceUndrOvrallSentimentScore();
		System.out.println("After clicking Next Button, Privious Bttn Visibility " + nextBtnResult);
		assertEquals("Next button is working fine in Overall Sentiment Score Widget",
				"Next button is not working fine in Overall Sentiment Score Widget", true, nextBtnResult);
		getDashboardPage().clickOnPreviousButtonInSubServiceUnderOverallSentimentScore();
		boolean previousBtnResult = getDashboardPage().visibilityOfNextBttnInSunSrvceUndrOvrallSentimentScore();
		System.out.println("After clicking Privious Button, Next Bttn Visibility " + previousBtnResult);
		assertEquals("Previous button is working fine in Overall Sentiment Score Widget",
				"Previous button is not working fine in Overall Sentiment Score Widget", true, previousBtnResult);
	}

	public void verifyDashboardVoiceTypesPerSQLChart(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().selectAllOptionsFromFilter("Service Quality Level");
		int totalVoicesAfterAllSQLFilter = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Total Voices after all SQL Filter In Voice Page" + totalVoicesAfterAllSQLFilter);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().scrollToWidget("Voice Type Per SQL");
		int allVoiceTypesCountPerSQL = api.getAllVoiceTypesPerSQL();
		System.out.println("All Voice Types Count Per SQL in Dashboard Widget" + allVoiceTypesCountPerSQL);
		assertContains("Number of All Voice Types Count Per SQL in Dashboard Page is equal to Voice List Page's",
				"Number of All Voice Types Count Per SQL in Dashboard Page is not equal to Voice List Page's",
				String.valueOf(allVoiceTypesCountPerSQL), String.valueOf(totalVoicesAfterAllSQLFilter));
		Map<String, Integer> allValues = api.getNameAndCountForVoicePerSql();
		extentTest.get().log(Status.INFO, "Voice counts for each SQL are as follows 🔽");
		for (Map.Entry<String, Integer> entry : allValues.entrySet()) {
		    System.out.println("Name: " + entry.getKey() + ", Count: " + entry.getValue());
		    extentTest.get().log(Status.INFO, "Voice Type: <b>" + entry.getKey() + "</b> Count: <b>" + entry.getValue()+"</b>");
		}
	}
	
	public void verifyDashboardVoiceTypesPerSQLChartAccessibility(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Service Quality Level", "Accessibility");
		int totalVoicesAfterSqlAccessibilityFilter = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Total Voices after Accessibility SQL Filter In Voice Page " + totalVoicesAfterSqlAccessibilityFilter);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().filterDashboardByDropdown("SQL", "Accessibility");
		int dashboardOverallVoiceCount = getDashboardPage().getNumberOfVoicesCount();
		System.out.println("Overall Voice Count in Dashboard Page "+dashboardOverallVoiceCount);
		assertContains("Number of Voice Count in Dashboard Page for SQL Accessibility is equal to Voice List Page's",
				"Number of Voice Count in Dashboard Page for SQL Accessibility is not equal to Voice List Page's",
				String.valueOf(dashboardOverallVoiceCount), String.valueOf(totalVoicesAfterSqlAccessibilityFilter));
		getDashboardPage().scrollToWidget("Voice Type Per SQL");
		int voicePerSqlWidgetCountAccessibilityFilter = api.getVoiceTypesPerSqlAccessibilityFilter();
		System.out.println("Voice Per SQL Widget Count "+voicePerSqlWidgetCountAccessibilityFilter);
		assertContains("Voice Per SQL Widget Count is equal to Voice List Page's Voice Count for SQL Accessibility Filter",
				"Voice Per SQL Widget Count is not equal to Voice List Page's Voice Count for SQL Accessibility Filter",
				String.valueOf(voicePerSqlWidgetCountAccessibilityFilter), String.valueOf(totalVoicesAfterSqlAccessibilityFilter));
		Map<String, Integer> allValues = api.getNameAndCountForSqlAccessibility();
		extentTest.get().log(Status.INFO, "Voice Types Per SQL for Accessibility Filter are as follows 🔽");
		for (Map.Entry<String, Integer> entry : allValues.entrySet()) {
		    System.out.println("Name: " + entry.getKey() + ", Count: " + entry.getValue());
		    extentTest.get().log(Status.INFO, "Voice Type: <b>" + entry.getKey() + "</b> Count: <b>" + entry.getValue()+"</b>");
		}
	}
	
	public void verifyDashboardVoiceTypesPerSQLChartEaseOfUse(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Service Quality Level", "Ease of use");
		int totalVoicesAfterSqlEaseOfUseFilter = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Total Voices after Ease of Use SQL Filter In Voice Page " + totalVoicesAfterSqlEaseOfUseFilter);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().filterDashboardByDropdown("SQL", "Ease of use");
		int dashboardOverallVoiceCount = getDashboardPage().getNumberOfVoicesCount();
		System.out.println("Overall Voice Count in Dashboard Page "+dashboardOverallVoiceCount);
		assertContains("Number of Voice Count in Dashboard Page for SQL Ease of Use is equal to Voice List Page's",
				"Number of Voice Count in Dashboard Page for SQL Ease of Use is not equal to Voice List Page's",
				String.valueOf(dashboardOverallVoiceCount), String.valueOf(totalVoicesAfterSqlEaseOfUseFilter));
		getDashboardPage().scrollToWidget("Voice Type Per SQL");
		int voicePerSqlWidgetCountEaseOfUseFilter = api.getVoiceTypesPerSqlEaseOfUseFilter();
		System.out.println("Voice Per SQL Widget Count "+voicePerSqlWidgetCountEaseOfUseFilter);
		assertContains("Voice Per SQL Widget Count is equal to Voice List Page's Voice Count for SQL Ease of Use Filter",
				"Voice Per SQL Widget Count is not equal to Voice List Page's Voice Count for SQL Ease of Use Filter",
				String.valueOf(voicePerSqlWidgetCountEaseOfUseFilter), String.valueOf(totalVoicesAfterSqlEaseOfUseFilter));
		Map<String, Integer> allValues = api.getNameAndCountForSqlEaseOfUse();
		extentTest.get().log(Status.INFO, "Voice Types Per SQL for Ease of Use Filter are as follows 🔽");
		for (Map.Entry<String, Integer> entry : allValues.entrySet()) {
			System.out.println("Name: " + entry.getKey() + ", Count: " + entry.getValue());
			extentTest.get().log(Status.INFO, "Voice Type: <b>" + entry.getKey() + "</b> Count: <b>" + entry.getValue()+"</b>");
		}
	}
	
	public void verifyDashboardVoiceTypesPerSQLChartInformationQuality(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Service Quality Level", "Information quality");
		int totalVoicesAfterSqlInformationQualityFilter = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Total Voices after Information Quality SQL Filter In Voice Page " + totalVoicesAfterSqlInformationQualityFilter);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().filterDashboardByDropdown("SQL", "Information quality");
		int dashboardOverallVoiceCount = getDashboardPage().getNumberOfVoicesCount();
		System.out.println("Overall Voice Count in Dashboard Page "+dashboardOverallVoiceCount);
		assertContains("Number of Voice Count in Dashboard Page for SQL Information Quality is equal to Voice List Page's",
				"Number of Voice Count in Dashboard Page for SQL Information Quality is not equal to Voice List Page's",
				String.valueOf(dashboardOverallVoiceCount), String.valueOf(totalVoicesAfterSqlInformationQualityFilter));
		getDashboardPage().scrollToWidget("Voice Type Per SQL");
		int voicePerSqlWidgetCountInformationQualityFilter = api.getVoiceTypesPerSqlInformationQualityFilter();
		System.out.println("Voice Per SQL Widget Count "+voicePerSqlWidgetCountInformationQualityFilter);
		assertContains("Voice Per SQL Widget Count is equal to Voice List Page's Voice Count for SQL Information Quality Filter",
				"Voice Per SQL Widget Count is not equal to Voice List Page's Voice Count for SQL Information Quality Filter",
				String.valueOf(voicePerSqlWidgetCountInformationQualityFilter), String.valueOf(totalVoicesAfterSqlInformationQualityFilter));
		Map<String, Integer> allValues = api.getNameAndCountForSqlInformationQuality();
		extentTest.get().log(Status.INFO, "Voice Types Per SQL for Information Quality Filter are as follows 🔽");
		for (Map.Entry<String, Integer> entry : allValues.entrySet()) {
			System.out.println("Name: " + entry.getKey() + ", Count: " + entry.getValue());
			extentTest.get().log(Status.INFO, "Voice Type: <b>" + entry.getKey() + "</b> Count: <b>" + entry.getValue()+"</b>");
		}
	}
	
	
	public void verifyDashboardVoiceTypesPerSQLChartAccessibility_Complaint(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Service Quality Level", "Accessibility");
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Voice Types", "Complaint");
		int voicePageVoiceCount = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Total Voices after Accessibility SQL and Complaint Voice Type Filter In Voice Page " + voicePageVoiceCount);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().filterDashboardByDropdown("SQL", "Accessibility");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Voice Type", "Complaint");
		int dashboardOverallVoiceCount = getDashboardPage().getNumberOfVoicesCount();
		System.out.println("Overall Voice Count in Dashboard Page after Accessibility SQL and Complaint Voice Type Filter "+dashboardOverallVoiceCount);
		assertContains("Number of Voice Count in Dashboard Page for SQL Accessibility and Complaint Voice Type Filter is equal to Voice List Page's",
				"Number of Voice Count in Dashboard Page for SQL Accessibility and Complaint Voice Type Filter is not equal to Voice List Page's",
				String.valueOf(dashboardOverallVoiceCount), String.valueOf(voicePageVoiceCount));
		getDashboardPage().scrollToWidget("Voice Type Per SQL");
		int widgetVoiceCount = api.getVoiceTypesPerSqlAccessibility_ComplaintFilter();
		System.out.println("Voice Per SQL Widget Count "+widgetVoiceCount);
		assertContains("Voice Per SQL Widget Count is equal to Voice List Page's Voice Count for SQL Accessibility and Complaint Voice Type Filter",
				"Voice Per SQL Widget Count is not equal to Voice List Page's Voice Count for SQL Accessibility and Complaint Voice Type Filter",
				String.valueOf(widgetVoiceCount), String.valueOf(voicePageVoiceCount));
	}
	
	public void verifyDashboardVoiceTypesPerSQLChartEaseOfUse_Appreciation(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Service Quality Level", "Ease of use");
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Voice Types", "Appreciation");
		int voicePageVoiceCount = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Total Voices after Ease of Use SQL and Appreciation Voice Type Filter In Voice Page " + voicePageVoiceCount);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().filterDashboardByDropdown("SQL", "Ease of use");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Voice Type", "Appreciation");
		int dashboardOverallVoiceCount = getDashboardPage().getNumberOfVoicesCount();
		System.out.println("Overall Voice Count in Dashboard Page after Ease of Use SQL and Appreciation Voice Type Filter "+dashboardOverallVoiceCount);
		assertContains("Number of Voice Count in Dashboard Page for SQL Ease of Use and Appreciation Voice Type Filter is equal to Voice List Page's",
				"Number of Voice Count in Dashboard Page for SQL Ease of Use and Appreciation Voice Type Filter is not equal to Voice List Page's",
				String.valueOf(dashboardOverallVoiceCount), String.valueOf(voicePageVoiceCount));
		getDashboardPage().scrollToWidget("Voice Type Per SQL");
		int widgetVoiceCount = api.getVoiceTypesPerSqlEaseOfUse_AppreciationFilter();
		System.out.println("Voice Per SQL Widget Count "+widgetVoiceCount);
		assertContains("Voice Per SQL Widget Count is equal to Voice List Page's Voice Count for SQL Ease of Use and Appreciation Voice Type Filter",
				"Voice Per SQL Widget Count is not equal to Voice List Page's Voice Count for SQL Ease of Use and Appreciation Voice Type Filter",
				String.valueOf(widgetVoiceCount), String.valueOf(voicePageVoiceCount));
	}
	
	public void verifyDashboardVoiceTypePerSqlIconTooltip(String subsName, String projectName) {
		navigateTillDashboard(subsName, projectName);
		getDashboardPage().hoverOnWidgetIcon("Voice Type Per SQL");
		boolean tooltipVisibility = getDashboardPage().checkTooltipVisibility();
		System.out.println("Visibility " + tooltipVisibility);
		String tooltip = getDashboardPage().getToolTip();
		System.out.println("Tooltip " + tooltip);
		assertEquals("Voice Types Per SQL Tool Tip is Visible, Tool Tip : <b>" + tooltip + "</b>",
				"Voice Types Per SQL Tool Tip is not Visible", true, tooltipVisibility);
	}
	
	public void verifyDashboardVoiceTypeSpectrumIconTooltip(String subsName, String projectName) {
		navigateTillDashboard(subsName, projectName);
		getDashboardPage().hoverOnWidgetIcon("Voice Type Spectrum");
		boolean tooltipVisibility = getDashboardPage().checkTooltipVisibility();
		System.out.println("Visibility " + tooltipVisibility);
		String tooltip = getDashboardPage().getToolTip();
		System.out.println("Tooltip " + tooltip);
		assertEquals("Voice Type Spectrum Tool Tip is Visible, Tool Tip : <b>" + tooltip + "</b>",
				"Voice Type Spectrum Tool Tip is not Visible", true, tooltipVisibility);
	}
	
	public void verifyDashboardSourceDistributionBoardChart(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		int voicelistPageOverallCount = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Total Voices In Voice Page" + voicelistPageOverallCount);
		navigateFromOnePageToAnother("Voice Insights");
		int dashboardOverallVoiceCount = getDashboardPage().getNumberOfVoicesCount();
		System.out.println("Overall Voice Count in Dashboard Page "+dashboardOverallVoiceCount);
		assertContains("Number of Voice Count in Dashboard Page is equal to Voice List Page's",
				"Number of Voice Count in Dashboard Page is not equal to Voice List Page's",
				String.valueOf(dashboardOverallVoiceCount), String.valueOf(voicelistPageOverallCount));
		getDashboardPage().scrollToWidget("Source Distribution Board");
		int widgetVoiceCount = api.getSourceDistributionBoardCount();
		System.out.println("Voice Count in Source Distribution Board Widget " + widgetVoiceCount);
		assertContains("Voice Count in Source Distribution Board Widget is equal to Voice List Page's",
				"Voice Count in Source Distribution Board Widget is not equal to Voice List Page's",
				String.valueOf(widgetVoiceCount), String.valueOf(voicelistPageOverallCount));
	}
	
	public void verifyDashboardSourceDistributionBoardIconTooltip(String subsName, String projectName) {
		navigateTillDashboard(subsName, projectName);
		getDashboardPage().hoverOnWidgetIcon("Source Distribution Board");
		boolean tooltipVisibility = getDashboardPage().checkTooltipVisibility();
		System.out.println("Visibility " + tooltipVisibility);
		String tooltip = getDashboardPage().getToolTip();
		System.out.println("Tooltip " + tooltip);
		assertEquals("Source Distribution Board Tool Tip is Visible, Tool Tip : <b>" + tooltip + "</b>",
				"Source Distribution Board Tool Tip is not Visible", true, tooltipVisibility);
	}
	
	public void verifyDashboardTopSubjectDropdownFilter_EVCharger(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Subject", "EV Charger");
		int voicePageVoiceCount = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Voice Count After EV Charger Subject "+voicePageVoiceCount);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().selectFromTopSubjectFilter("EV Charger");
		int widgetCount = api.getTopSubjectCount_EvChargerFilter();
		System.out.println("Top Subject Widget Count after EV Charger Filter "+widgetCount);
		assertContains("Top Subject Widget Count after EV Charger Filter is equal to Voice List Page's",
				"Top Subject Widget Count after EV Charger Filter is not equal to Voice List Page's",
				String.valueOf(widgetCount), String.valueOf(voicePageVoiceCount));
	}
	
	public void verifyDashboardTopSubjectDropdownFilter_BillPayments(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Subject", "Bill Payments");
		int voicePageVoiceCount = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Voice Count After Bill Payments Subject "+voicePageVoiceCount);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().selectFromTopSubjectFilter("Bill Payments");
		int widgetCount = api.getTopSubjectCount_BillPaymentFilter();
		System.out.println("Top Subject Widget Count after Bill Payments Filter "+widgetCount);
		assertContains("Top Subject Widget Count after Bill Payments Filter is equal to Voice List Page's",
				"Top Subject Widget Count after Bill Payments Filter is not equal to Voice List Page's",
				String.valueOf(widgetCount), String.valueOf(voicePageVoiceCount));
	}
	
	
	public void verifyDashboardTopSubjectDropdownFilter_BillingInquiry(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Subject", "Billing Inquiry");
		int voicePageVoiceCount = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Voice Count After Billing Inquiry Subject "+voicePageVoiceCount);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Subjects", "Billing Inquiry");
		int dashboardOverallVoiceCount = getDashboardPage().getNumberOfVoicesCount();
		System.out.println("Overall Voice Count in Dashboard after Top Subject and Billing Inquiry Filter "+dashboardOverallVoiceCount);
		assertContains("Overall Voice Count in Dashboard after Top Subject and Billing Inquiry Filter is equal to Voice List Page's",
				"Overall Voice Count in Dashboard after Top Subject and Billing Inquiry Filter is not equal to Voice List Page's",
				String.valueOf(dashboardOverallVoiceCount), String.valueOf(voicePageVoiceCount));
		getDashboardPage().scrollToWidget("Top Subject");
		int widgetCount = api.getTopSubjectCount_BillingInquiryFilter();
		System.out.println("Top Subject Widget Count after Billing Inquiry Filter "+widgetCount);
		assertContains("Top Subject Widget Count after Billing Inquiry Filter is equal to Voice List Page's",
				"Top Subject Widget Count after Billing Inquiry Filter is not equal to Voice List Page's",
				String.valueOf(widgetCount), String.valueOf(voicePageVoiceCount));
	}
	
	public void verifyDashboardTopSubjectDropdownFilter_SmartApplication(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Subject", "Smart Application");
		int voicePageVoiceCount = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Voice Count After Smart Application Subject "+voicePageVoiceCount);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Subjects", "Smart Application");
		int dashboardOverallVoiceCount = getDashboardPage().getNumberOfVoicesCount();
		System.out.println("Overall Voice Count in Dashboard after Top Subject and Smart Application Filter "+dashboardOverallVoiceCount);
		assertContains("Overall Voice Count in Dashboard after Top Subject and Smart Application Filter is equal to Voice List Page's",
				"Overall Voice Count in Dashboard after Top Subject and Smart Application Filter is not equal to Voice List Page's",
				String.valueOf(dashboardOverallVoiceCount), String.valueOf(voicePageVoiceCount));
		getDashboardPage().scrollToWidget("Top Subject");
		int widgetCount = api.getTopSubjectCount_SmartApplicationFilter();
		System.out.println("Top Subject Widget Count after Smart Application Filter "+widgetCount);
		assertContains("Top Subject Widget Count after Smart Application Filter is equal to Voice List Page's",
				"Top Subject Widget Count after Smart Application Filter is not equal to Voice List Page's",
				String.valueOf(widgetCount), String.valueOf(voicePageVoiceCount));
	}
	
	public void verifyDashboardTopSubjectIconTooltip(String subsName, String projectName) {
		navigateTillDashboard(subsName, projectName);
		getDashboardPage().hoverOnWidgetIcon(" Top Subject ");
		boolean tooltipVisibility = getDashboardPage().checkTooltipVisibility();
		System.out.println("Visibility " + tooltipVisibility);
		String tooltip = getDashboardPage().getToolTip();
		System.out.println("Tooltip " + tooltip);
		assertEquals("Source Distribution Board Tool Tip is Visible, Tool Tip : <b>" + tooltip + "</b>",
				"Source Distribution Board Tool Tip is not Visible", true, tooltipVisibility);
	}
	
	public void verifyDashboardCustomerSpectrumChart_BusinessFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Customer Category", "Business");
		int voicePageVoiceCount = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Voice Count After Business Customer "+voicePageVoiceCount);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Customer Category", "Business");
		int dashboardOverallVoiceCount = getDashboardPage().getNumberOfVoicesCount();
		System.out.println("Overall Voice Count in Dashboard after Business Customer Filter "+dashboardOverallVoiceCount);
		assertContains("Overall Voice Count in Dashboard after Business Customer Filter is equal to Voice List Page's",
				"Overall Voice Count in Dashboard after Business Customer is not equal to Voice List Page's",
				String.valueOf(dashboardOverallVoiceCount), String.valueOf(voicePageVoiceCount));
		getDashboardPage().scrollToWidget("Customer Spectrum");
		int widgetCount = api.getCustomerCategory_BusinessFilter();
		System.out.println("Customer Spectrum Widget Count after Business Filter "+widgetCount);
		assertContains("Customer Spectrum Widget Count after Business Filter is equal to Voice List Page's",
				"Customer Spectrum Widget Count after Business Filter is not equal to Voice List Page's",
				String.valueOf(widgetCount), String.valueOf(voicePageVoiceCount));
	}
	
	public void verifyDashboardCustomerSpectrumChart_IndividualLocalFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Customer Category", "Individual (Local)");
		int voicePageVoiceCount = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Voice Count After Individual (Local) Customer "+voicePageVoiceCount);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Customer Category", "Individual (Local)");
		int dashboardOverallVoiceCount = getDashboardPage().getNumberOfVoicesCount();
		System.out.println("Overall Voice Count in Dashboard after Individual (Local) Customer Filter "+dashboardOverallVoiceCount);
		assertContains("Overall Voice Count in Dashboard after Individual (Local) Customer Filter is equal to Voice List Page's",
				"Overall Voice Count in Dashboard after Individual (Local) Customer is not equal to Voice List Page's",
				String.valueOf(dashboardOverallVoiceCount), String.valueOf(voicePageVoiceCount));
		getDashboardPage().scrollToWidget("Customer Spectrum");
		int widgetCount = api.getCustomerCategory_IndividualLocalFilter();
		System.out.println("Customer Spectrum Widget Count after Individual (Local) Filter "+widgetCount);
		assertContains("Customer Spectrum Widget Count after Individual (Local) Filter is equal to Voice List Page's",
				"Customer Spectrum Widget Count after Individual (Local) Filter is not equal to Voice List Page's",
				String.valueOf(widgetCount), String.valueOf(voicePageVoiceCount));
	}
	
	public void verifyDashboardCustomerSpectrumIconTooltip(String subsName, String projectName) {
		navigateTillDashboard(subsName, projectName);
		getDashboardPage().hoverOnWidgetIcon("Customer Spectrum");
		boolean tooltipVisibility = getDashboardPage().checkTooltipVisibility();
		System.out.println("Visibility " + tooltipVisibility);
		String tooltip = getDashboardPage().getToolTip();
		System.out.println("Tooltip " + tooltip);
		assertEquals("Customer Spectrum Tool Tip is Visible, Tool Tip : <b>" + tooltip + "</b>",
				"Customer Spectrum Tool Tip is not Visible", true, tooltipVisibility);
	}
	
	public void verifyDashboardChannelDistributionBoard_NoFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().selectAllOptionsFromFilter("Channel List");
		int voicePageVoiceCount = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Voice Count without any filter "+voicePageVoiceCount);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().scrollToWidget("Channel Distribution Board");
		int widgetCount = api.getChannelDistributionBoardNoFilter();
		System.out.println("Channel Distribution Board Widget Count without Filter "+widgetCount);
		assertContains("Channel Distribution Board Widget Count without Filter is equal to Voice List Page's",
				"Channel Distribution Board Widget Count without Filter is not equal to Voice List Page's",
				String.valueOf(widgetCount), String.valueOf(voicePageVoiceCount));
	}
	
	public void verifyDashboardChannelDistributionBoard_DewaWebsiteFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Channel List", "Website");
		int voicePageVoiceCount = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Voice Count After Website Channel in Voice List "+voicePageVoiceCount);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().filterDashboardByDropdown("Channel", "Website");
		int dashboardOverallVoiceCount = getDashboardPage().getNumberOfVoicesCount();
		System.out.println("Overall Voice Count in Dashboard after Website Channel Filter "+dashboardOverallVoiceCount);
		assertContains("Overall Voice Count in Dashboard after Website Channel Filter is equal to Voice List Page's",
				"Overall Voice Count in Dashboard after Website Channel is not equal to Voice List Page's",
				String.valueOf(dashboardOverallVoiceCount), String.valueOf(voicePageVoiceCount));
		getDashboardPage().scrollToWidget("Channel Distribution Board");
		int widgetCount = api.getChannelDistributionBoardWebsiteFilter();
		System.out.println("Channel Distribution Board Widget Count after Website Filter "+widgetCount);
		assertContains("Channel Distribution Board Widget Count after Website Filter is equal to Voice List Page's",
				"Channel Distribution Board Widget Count after Website Filter is not equal to Voice List Page's",
				String.valueOf(widgetCount), String.valueOf(voicePageVoiceCount));
	}
	
	public void verifyDashboardChannelDistributionBoard_SmartApplicationFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Channel List", "Smart Application");
		int voicePageVoiceCount = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Voice Count After Smart Application Channel in Voice List "+voicePageVoiceCount);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().filterDashboardByDropdown("Channel", "Smart Application");
		int dashboardOverallVoiceCount = getDashboardPage().getNumberOfVoicesCount();
		System.out.println("Overall Voice Count in Dashboard after Smart Application Channel Filter "+dashboardOverallVoiceCount);
		assertContains("Overall Voice Count in Dashboard after Smart Application Channel Filter is equal to Voice List Page's",
				"Overall Voice Count in Dashboard after Smart Application Channel is not equal to Voice List Page's",
				String.valueOf(dashboardOverallVoiceCount), String.valueOf(voicePageVoiceCount));
		getDashboardPage().scrollToWidget("Channel Distribution Board");
		int widgetCount = api.getChannelDistributionBoardSmartAppFilter();
		System.out.println("Channel Distribution Board Widget Count after Smart Application Filter "+widgetCount);
		assertContains("Channel Distribution Board Widget Count after Smart Application Filter is equal to Voice List Page's",
				"Channel Distribution Board Widget Count after Smart Application Filter is not equal to Voice List Page's",
				String.valueOf(widgetCount), String.valueOf(voicePageVoiceCount));
	}
	
	public void verifyDashboardChannelDistributionBoardIconTooltip(String subsName, String projectName) {
		navigateTillDashboard(subsName, projectName);
		getDashboardPage().hoverOnWidgetIcon("Channel Distribution Board");
		boolean tooltipVisibility = getDashboardPage().checkTooltipVisibility();
		System.out.println("Visibility " + tooltipVisibility);
		String tooltip = getDashboardPage().getToolTip();
		System.out.println("Tooltip " + tooltip);
		assertEquals("Channel Distribution Board Tool Tip is Visible, Tool Tip : <b>" + tooltip + "</b>",
				"Channel Distribution Board Tool Tip is not Visible", true, tooltipVisibility);
	}
	
	public void verifyDashboardServiceInsightBoard_BillingServicesFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Services", "Billing Services");
		int voicePageVoiceCount = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Voice Count After Services, Billing Services in Voice List "+voicePageVoiceCount);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().filterDashboardByDropdown("Main Services", "Billing Services");
		int dashboardOverallVoiceCount = getDashboardPage().getNumberOfVoicesCount();
		System.out.println("Overall Voice Count in Dashboard after Main Services, Billing Services Filter "+dashboardOverallVoiceCount);
		assertContains("Overall Voice Count in Dashboard after Main Services, Billing Services Filter is equal to Voice List Page's",
				"Overall Voice Count in Dashboard after Main Services, Billing Services is not equal to Voice List Page's",
				String.valueOf(dashboardOverallVoiceCount), String.valueOf(voicePageVoiceCount));
		getDashboardPage().scrollToWidget("Main Service Insight Board");
		int widgetCount = api.getServiceInsightBoardBillingServicesFilter();
		System.out.println("Service Insight Board Widget Count after Billing Services Filter "+widgetCount);
		assertContains("Service Insight Board Widget Count after Billing Services Filter is equal to Voice List Page's",
				"Service Insight Board Widget Count after Billing Services Filter is not equal to Voice List Page's",
				String.valueOf(widgetCount), String.valueOf(voicePageVoiceCount));
	}
	
	public void verifyDashboardServiceInsightBoard_NocServicesFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Services", "NOC Services");
		int voicePageVoiceCount = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Voice Count After Services, NOC Services in Voice List "+voicePageVoiceCount);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().filterDashboardByDropdown("Main Services", "NOC Services");
		int dashboardOverallVoiceCount = getDashboardPage().getNumberOfVoicesCount();
		System.out.println("Overall Voice Count in Dashboard after Main Services, NOC Services Filter "+dashboardOverallVoiceCount);
		assertContains("Overall Voice Count in Dashboard after Main Services, NOC Services Filter is equal to Voice List Page's",
				"Overall Voice Count in Dashboard after Main Services, NOC Services is not equal to Voice List Page's",
				String.valueOf(dashboardOverallVoiceCount), String.valueOf(voicePageVoiceCount));
		getDashboardPage().scrollToWidget("Main Service Insight Board");
		int widgetCount = api.getServiceInsightBoardNocServicesFilter();
		System.out.println("Service Insight Board Widget Count after NOC Services Filter "+widgetCount);
		assertContains("Service Insight Board Widget Count after NOC Services Filter is equal to Voice List Page's",
				"Service Insight Board Widget Count after NOC Services Filter is not equal to Voice List Page's",
				String.valueOf(widgetCount), String.valueOf(voicePageVoiceCount));
	}
	
	public void verifyDashboardServiceInsightBoardIconTooltip(String subsName, String projectName) {
		navigateTillDashboard(subsName, projectName);
		getDashboardPage().hoverOnWidgetIcon("Main Service Insight Board");
		boolean tooltipVisibility = getDashboardPage().checkTooltipVisibility();
		System.out.println("Visibility " + tooltipVisibility);
		String tooltip = getDashboardPage().getToolTip();
		System.out.println("Tooltip " + tooltip);
		assertEquals("Service Insight Board Tool Tip is Visible, Tool Tip : <b>" + tooltip + "</b>",
				"Service Insight Board Tool Tip is not Visible", true, tooltipVisibility);
	}
	
	public void verifyDashboardSubServiceInsightBoard_BillingServicesFilter(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Sub Service", "Bill Payment");
		int voicePageVoiceCount = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Voice Count After Sub Service, Bill Payment in Voice List "+voicePageVoiceCount);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().clickOnMoreFilter();
		getDashboardPage().selectOptionsFromAdvancedFiltersDropdown("Sub Services", "Bill Payment");
		int dashboardOverallVoiceCount = getDashboardPage().getNumberOfVoicesCount();
		System.out.println("Overall Voice Count in Dashboard after Sub Services, Bill Payment Filter "+dashboardOverallVoiceCount);
		assertContains("Overall Voice Count in Dashboard after Sub Services, Bill Payment Filter is equal to Voice List Page's",
				"Overall Voice Count in Dashboard after Sub Services, Bill Payment is not equal to Voice List Page's",
				String.valueOf(dashboardOverallVoiceCount), String.valueOf(voicePageVoiceCount));
		getDashboardPage().scrollToWidget("Public Service Insight Board");
		int widgetCount = api.getSubServiceInsightBoardBillPaymentFilter();
		System.out.println("Public Service Insight Board Widget Count after Bill Payment Filter "+widgetCount);
		assertContains("Public Service Insight Board Widget Count after Bill Payment Filter is equal to Voice List Page's",
				"Public Service Insight Board Widget Count after Bill Payment Filter is not equal to Voice List Page's",
				String.valueOf(widgetCount), String.valueOf(voicePageVoiceCount));
	}
	
	public void verifyDashboardSubServiceInsightBoardIconTooltip(String subsName, String projectName) {
		navigateTillDashboard(subsName, projectName);
		getDashboardPage().hoverOnWidgetIcon("Public Service Insight Board");
		boolean tooltipVisibility = getDashboardPage().checkTooltipVisibility();
		System.out.println("Visibility " + tooltipVisibility);
		String tooltip = getDashboardPage().getToolTip();
		System.out.println("Tooltip " + tooltip);
		assertEquals("Public Service Insight Board Tool Tip is Visible, Tool Tip : <b>" + tooltip + "</b>",
				"Public Service Insight Board Tool Tip is not Visible", true, tooltipVisibility);
	}
	
	public void verifyDashboardServiceVsSQL_EaseOfUseNocServices(String subsName, String projectName) {
		navigateTillVoiceListPage(subsName, projectName);
		getVoicesListPage().filterVoicesOnFilter("Service Quality Level", "Ease of use");
		getVoicesListPage().showFilter();
		getVoicesListPage().filterVoicesOnFilter("Services", "NOC Services");
		int voicePageVoiceCount = getVoicesListPage().getTotalNumberOfVoice();
		System.out.println("Voice Count After Ease of Use SQL and NOC Services in Voice List "+voicePageVoiceCount);
		navigateFromOnePageToAnother("Voice Insights");
		getDashboardPage().filterDashboardByDropdown("SQL", "Ease of use");
		getDashboardPage().filterDashboardByDropdown("Main Services", "NOC Services");
		int dashboardOverallVoiceCount = getDashboardPage().getNumberOfVoicesCount();
		System.out.println("Overall Voice Count in Dashboard after Ease of Use SQL and NOC Services Filter "+dashboardOverallVoiceCount);
		assertContains("Overall Voice Count in Dashboard after Ease of Use SQL and NOC Services Filter is equal to Voice List Page's",
				"Overall Voice Count in Dashboard after Ease of Use SQL and NOC Services is not equal to Voice List Page's",
				String.valueOf(dashboardOverallVoiceCount), String.valueOf(voicePageVoiceCount));
		getDashboardPage().scrollToWidget("Services vs SQL");
		int widgetCount = api.getServicesVsSql_EaseOfUse_NocServicesFilter();
		System.out.println("Services vs SQL Widget Count after Ease of Use SQL and NOC Services Filter "+widgetCount);
		assertContains("Services vs SQL Widget Count after Ease of Use SQL and NOC Services Filter is equal to Voice List Page's",
				"Services vs SQL Widget Count after Ease of Use SQL and NOC Services Filter is not equal to Voice List Page's",
				String.valueOf(widgetCount), String.valueOf(voicePageVoiceCount));
	}
	
	public void afterMethod(String subsName, String projectName) {
		if(getPage().locator("//iframe[@class='w-full h-full']").isVisible()) {
			Locator iframeLocator = getPage().locator("//iframe[@class='w-full h-full']");
			Frame frame = iframeLocator.elementHandle().contentFrame();
			frame.locator("//button[@tooltip='Close']").click();
		}
		getHomePage().clickOnHomeButton();
		getPage().waitForTimeout(2000);
		getPage().reload();
	}
}
