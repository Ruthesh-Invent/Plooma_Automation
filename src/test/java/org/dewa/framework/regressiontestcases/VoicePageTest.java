package org.dewa.framework.regressiontestcases;

import org.dewa.framework.basefunctions.CommonFunctions;
import org.dewa.framework.listener.RetryListener;
import org.dewa.framework.scriptmodules.VoicePageModules;
import org.dewa.framework.utils.ExcelUtility;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(org.dewa.framework.listener.ListenerImplementation.class)
public class VoicePageTest extends CommonFunctions {

	String excelPath = System.getProperty("user.dir") + "/"
			+ "src/main/resources/Reg_VoiceAttributes_07-06.xlsx";
	String sheetName = "Reg_VoiceAttributes_150425_Inpu";
	ExcelUtility excel = new ExcelUtility(excelPath);

	String subsName = prop.getProperty("subscriptionName");
	String projectName = prop.getProperty("projectName");

	// Test Case Link
	// https://botmindsai-my.sharepoint.com/:x:/g/personal/kasthuri_p_botminds_ai/ESD-XUJXujpBs3_h9k6jP5ABDiqLQfZRIuS0UroVsYmuMw?wdOrigin=TEAMS-MAGLEV.p2p_ns.rwc&wdExp=TEAMS-TREATMENT&wdhostclicktime=1744015106244&web=1https://botmindsai-my.sharepoint.com/:x:/g/personal/kasthuri_p_botminds_ai/ESD-XUJXujpBs3_h9k6jP5ABDiqLQfZRIuS0UroVsYmuMw?wdOrigin=TEAMS-MAGLEV.p2p_ns.rwc&wdExp=TEAMS-TREATMENT&wdhostclicktime=1744015106244&web=1
	@BeforeClass(alwaysRun = true)
	public void navigateToProjectFromHome() {
		String emailId = prop.getProperty("email");
		String password = prop.getProperty("password");
		new CommonFunctions().launchApplication(getUrl(), emailId, password);
		System.out.println("Before Class Executed");
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() {
		System.out.println("Before Method");
	}

	// 1
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_001Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.sentimentHappySentimentNoComments(subsName, projectName, voiceId);
	}

	// 2
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_002Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.sentimentHappySentimentPositiveComments(subsName, projectName, voiceId);
	}

	// 3
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_003Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.sentimentHappySentimentNegativeComments(subsName, projectName, voiceId);
	}

	// 4
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_004Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.sentimentNeutralSentimentNoComments(subsName, projectName, voiceId);
	}

	// 5
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_005Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.sentimentNeutralSentimentNeutralComments(subsName, projectName, voiceId);
	}

	// 6
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_006Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.sentimentNeutralSentimentPositiveComments(subsName, projectName, voiceId);
	}

	// 7
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_007Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.sentimentNeutralSentimentNegativeComments(subsName, projectName, voiceId);
	}

	// 8
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_008Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.sentimentUnhappySentimentNoComments(subsName, projectName, voiceId);
	}

	// 9
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_009Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.sentimentUnhappySentimentNeutralComments(subsName, projectName, voiceId);
	}

	// 10
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_010Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.sentimentUnhappySentimentNegativeComments(subsName, projectName, voiceId);
	}

	// 11
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_011Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.sqlAccessibilityOfServicesHappyCustomer(subsName, projectName, voiceId);
	}

	// 12
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_012Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.sqlAccessibilityOfServicesUnhappyCustomer(subsName, projectName, voiceId);
	}

	// 13
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_013Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.sqlSpeedOfServiceDeliveryHappyCustomer(subsName, projectName, voiceId);
	}

	// 14
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_014Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.sqlSpeedOfServiceDeliveryUnhappyCustomer(subsName, projectName, voiceId);
	}

	// 15
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_015Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.sqlProfessionalismOfStaffHappyCustomer(subsName, projectName, voiceId);
	}

	// 16
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_016Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.sqlProfessionalismOfStaffHappyCustomer(subsName, projectName, voiceId);
	}

	// 17
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_017Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.sqlRespectForCustomerPrivacyHappyCustomer(subsName, projectName, voiceId);
	}

	// 18
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_018Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.sqlRespectForCustomerPrivacyHappyCustomer(subsName, projectName, voiceId);
	}

	// 19
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_019Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.sqlEaseOfUseHappyCustomer(subsName, projectName, voiceId);
	}

	// 20
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_020Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.sqlEaseOfUseHappyCustomer(subsName, projectName, voiceId);
	}

	// 21
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_021Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.sqlInformationQualityHappyCustomer(subsName, projectName, voiceId);
	}

	// 22
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_022Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.sqlInformationQualityHappyCustomer(subsName, projectName, voiceId);
	}

	// 23
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_023Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.sqlAppearanceOfServiceDeliveryHappyCustomer(subsName, projectName, voiceId);
	}

	// 24
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_024Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.sqlAppearanceOfServiceDeliveryHappyCustomer(subsName, projectName, voiceId);
	}

	// 25
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_025Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.sqlEaseOfUseHappyCustomer(subsName, projectName, voiceId);
	}

	// 26
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_026Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.sqlAccessibilityOfServicesUnhappyCustomer(subsName, projectName, voiceId);
	}

	// 27
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_027Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.sqlProfessionalismOfStaffNeutralCustomer(subsName, projectName, voiceId);
	}

	// 28
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_028Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.sqlNoComments(subsName, projectName, voiceId);
	}

	// 29
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_029Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.voiceTypeInquires(subsName, projectName, voiceId);
	}

	// 30
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_030Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.voiceTypeSuggestions(subsName, projectName, voiceId);
	}

	// 31
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_031Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.voiceTypeComments(subsName, projectName, voiceId);
	}

	// 32
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_032Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.voiceTypeCompliments(subsName, projectName, voiceId);
	}

	// 33
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_033Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.voiceTypeComplaints(subsName, projectName, voiceId);
	}

	// 34
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_034Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.voiceTypeNoComments(subsName, projectName, voiceId);
	}

	// 35
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_035Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.voiceTypeComplimentsUrdu(subsName, projectName, voiceId);
	}

	// 36
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_036Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.voiceTypeSuggestionsArabic(subsName, projectName, voiceId);
	}

	// 37
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_037Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.verifyCategorizationOfBillingServicesBillPayment(subsName, projectName, voiceId);
	}

	// 38
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_038Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.verifyCategorizationOfBillingServicesRequestForRefund(subsName, projectName, voiceId);
	}

	// 39
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_039Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.verifyCategorizationOfBillingServicesRequestForClearanceCertificate(subsName, projectName, voiceId);
	}

	// 40
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_040Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.verifyCategorizationOfElectricityWaterManagementServicesRequestForActivationOfElectricityWater(subsName,
				projectName, voiceId);
	}

	// 41
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_041Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.verifyCategorizationOfElectricityWaterManagementServicesRequestForDeactivationOfElectricityWater(
				subsName, projectName, voiceId);
	}

	// 42
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_042Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.verifyCategorizationOfSustainabilityConsumptionManagementServicesEVAccountAndChargingCardManagement(
				subsName, projectName, voiceId);
	}

	// 43
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_043Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.verifyCategorizationOfSustainabilityConsumptionManagementServicesRequestForConsumptionVerificationElectricityWater(
				subsName, projectName, voiceId);
	}

	// 44
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_044Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.verifyCategorizationOfElectricityNetworkServicesGettingElectricityConnection(subsName, projectName,
				voiceId);
	}

	// 45
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_045Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.verifyCategorizationOfElectricityNetworkServicesRequestForElectricityNetworkAndModification(subsName,
				projectName, voiceId);
	}

	// 46
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_046Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.verifyCategorizationOfNOCServicesRequestForBuildingNoObjectionCertificateWater(subsName, projectName,
				voiceId);
	}

	// 47
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_047Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.verifyCategorizationOfNOCServicesRequestForBuildingNoObjectionCertificateElectricity(subsName,
				projectName, voiceId);
	}

	// 48
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_048Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.verifyCategorizationOfWaterNetworkServicesGettingWaterConnection(subsName, projectName, voiceId);
	}

	// 49
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_049Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.verifyCategorizationOfWaterNetworkServicesRequestForWaterNetworkModification(subsName, projectName,
				voiceId);
	}

	// 50
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_050Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.verifyCategorizationOfElectricityNetworkServicesGettingElectricityConnectionUrdu(subsName, projectName,
				voiceId);
	}

	// 51
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_051Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.verifyCategorizationOfSustainabilityConsumptionManagementServicesEVChargingArabic(subsName, projectName,
				voiceId);
	}

	// 52
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_052Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.verifyMappingServiceCodesValidateCorrectMappingOfServiceCodesToSubServices771(subsName, projectName,
				voiceId);
	}

	// 53
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_053Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.verifyMappingServiceCodesValidateCorrectMappingOfServiceCodesToSubServices768(subsName, projectName,
				voiceId);
	}

	// 54
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_054Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.verifyMappingServiceCodesValidateCorrectMappingOfServiceCodesToSubServices4156(subsName, projectName,
				voiceId);
	}

	// 55
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_055Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.verifyCustomerInformationValidateMobileNumberExtraction(subsName, projectName, voiceId);
	}

	// 56
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_056Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.verifyCustomerInformationValidateMobileNumberExtraction(subsName, projectName, voiceId);
	}

	// 57 is Pending to develop

	// 58
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_058Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.verifyLocationDisplayLocationInVoiceSummaryPage(subsName, projectName, voiceId);
	}

	// 59
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_059Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.verifyLocationNoLocationInVoice(subsName, projectName, voiceId);
	}

	// 60
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_060Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.verifyLocationNoLocationInVoice(subsName, projectName, voiceId);
	}

	// 61
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_061Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.verifySubjectDisplaySubjectInVoiceSummaryPage(subsName, projectName, voiceId);
	}

	// 62
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_062Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.verifySourceDisplaySourceInVoiceSummaryPage(subsName, projectName, voiceId);
	}

	// 63 and 64
	@Test(groups = { "Regression", "Smoke" }, retryAnalyzer = RetryListener.class)
	public void VoC_Sentiment_063Test() {
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		String testCaseId = methodName.replace("Test", "");
		String voiceId = excel.getVoiceIDFromTestCaseID(sheetName, testCaseId);
		System.out.println("Voice ID is : " + voiceId);
		VoicePageModules modules = new VoicePageModules(getPage());
		modules.verifyDateANDTimeDisplayDateInVoiceSummaryPage(subsName, projectName, voiceId);
	}

//	@AfterMethod(alwaysRun = true)
//	public void driverClose() {
//		VoicePageModules modules = new VoicePageModules(getPage());
//		modules.afterMethod();
//	}

}

//57 is pending (Failure) Customer contact should be empty but is available 
