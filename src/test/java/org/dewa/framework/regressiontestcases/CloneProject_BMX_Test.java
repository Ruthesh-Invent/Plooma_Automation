package org.dewa.framework.regressiontestcases;

import org.dewa.framework.basefunctions.CommonFunctions;
import org.dewa.framework.listener.RetryListener;
import org.dewa.framework.scriptmodules.CloningProjectModules;
import org.dewa.framework.utils.DataFaker;
import org.dewa.framework.utils.PropertiesUtility;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(org.dewa.framework.listener.ListenerImplementation.class)
public class CloneProject_BMX_Test extends CommonFunctions{
	
	CloningProjectModules modules=new CloningProjectModules(getPage());
	
	String propertiesPath = System.getProperty("user.dir")+"\\src\\test\\resources\\project_cloning.properties";
	PropertiesUtility propUtil=new PropertiesUtility(propertiesPath);
	String subscriptionName = propUtil.getProperty("base_subscription_name");
	String projectName = propUtil.getProperty("base_project_name");

	@BeforeClass(alwaysRun = true)
	public void navigateToProjectFromHome() {
		String emailId = prop.getProperty("email");
		String password = prop.getProperty("password");
		new CommonFunctions().launchApplication(getUrl(), emailId, password);
		System.out.println("Before Class Executed");
	}
	
	// To download bmx file
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class, priority = 1)
//	public void downloadBMXFileTest() {
//		System.out.println("Base Subscription Name : "+subscriptionName);
//		System.out.println("Base Project Name : "+projectName);
//		modules.downloadBMXFile(subscriptionName, projectName);
//		modules.captureToggleNames_ApiNames(subscriptionName, projectName);
//		
//		String toggleName_toggleResultPath = System.getProperty("user.dir") + "/toggle_jsons_download/toggleName_toggleResult.json";
//		String apiName_ToggleResultPath = System.getProperty("user.dir") + "/toggle_jsons_download/apiName_toggleResult.json";
//		modules.captureToggleResultUsingUiNames(subscriptionName, projectName, toggleName_toggleResultPath, apiName_ToggleResultPath);
//	}
	
//	@Test
//	public void readConfigurationFromProject() {
//		String jsonPath = System.getProperty("user.dir") + "/bmx_jsons/toggleName_toggleResult.json";
//		String apiName_ToggleResultPath = System.getProperty("user.dir") + "/bmx_jsons/apiName_toggleResult.json";
//		modules.readToggleUI_Status_UsingUiNames(subscriptionName, projectName, jsonPath, apiName_ToggleResultPath);
//	}
	
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class, priority = 2, dependsOnMethods = "downloadBMXFileTest")
//	public void validateProjectModuleTest() {
//		modules.verifyProjectModule(subscriptionName, projectName);
//	}
//	
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class, priority = 3, dependsOnMethods = "downloadBMXFileTest")
//	public void validateDataModelModuleTest() {
//		modules.verifyDataModelModule(subscriptionName, projectName);
//	}
//	
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class, priority = 4, dependsOnMethods = "downloadBMXFileTest")
//	public void validateAutomationModuleTest() {
//		modules.verifyAutomation(subscriptionName, projectName);
//	}
//	
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class, priority = 5, dependsOnMethods = "downloadBMXFileTest")
//	public void validateSearchModuleTest() {
//		modules.verifySearchModule(subscriptionName, projectName);
//	}
//	
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class, priority = 6, dependsOnMethods = "downloadBMXFileTest")
//	public void validateChatModuleTest() {
//		modules.verifyChatModule(subscriptionName, projectName);
//	}
//	
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class, priority = 7, dependsOnMethods = "downloadBMXFileTest")
//	public void validateIntelligenceModuleTest() {
//		modules.verifyIntelligenceModule(subscriptionName, projectName);
//	}
//	
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class, priority = 8, dependsOnMethods = "downloadBMXFileTest")
//	public void validateAiBuilderModuleTest() {
//		modules.verifyAiBuilderModule(subscriptionName, projectName);
//	}
//	
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class, priority = 9, dependsOnMethods = "downloadBMXFileTest")
//	public void validateAgentBuilderModuleTest() {
//		modules.verifyAgentBuilderModule(subscriptionName, projectName);
//	}
//	
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class, priority = 10, dependsOnMethods = "downloadBMXFileTest")
//	public void validateAssetBuilderModuleTest() {
//		modules.verifyAssetBuilderModule(subscriptionName, projectName);
//	}
//	
//	@Test(groups = "Regression", retryAnalyzer = RetryListener.class, priority = 11, dependsOnMethods = "downloadBMXFileTest")
//	public void validateDataLakeModuleTest() {
//		modules.verifyDataLakeModule(subscriptionName, projectName);
//	}
	
	String newProjectName = DataFaker.generateRandomString(5)+ " Auto Test";
	
	String downloadedBmxPath = System.getProperty("user.dir") + "/DownloadedBmx/"+projectName+".bmx";
	
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class, priority = 12)
	public void createProjectUsingBmxTest() {
		String subscriptionName="VOCTest";
		System.out.println("Project Name : "+newProjectName);
		String description = DataFaker.generateFakeDescription();
		System.out.println("Description : "+description);
		System.out.println("Bmx Path Name : "+downloadedBmxPath);
		modules.createProjectUsingBmx(subscriptionName, newProjectName, description, downloadedBmxPath);
		String toggleName_toggleResultPath = System.getProperty("user.dir") + "/upload_bmx_jsons/toggleName_toggleResult.json";
		String apiName_ToggleResultPath = System.getProperty("user.dir") + "/upload_bmx_jsons/apiName_toggleResult.json";
		modules.captureToggleResultUsingUiNames(subscriptionName, newProjectName, toggleName_toggleResultPath, apiName_ToggleResultPath);
	}
	
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class, priority = 13, dependsOnMethods = "createProjectUsingBmxTest")
	public void verifyProjectModuleUploadTest() {
	 modules.verifyProjectModuleUpload(subscriptionName, newProjectName);
	}
	
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class, priority = 14, dependsOnMethods = "createProjectUsingBmxTest")
	public void verifyDataModelModuleUploadTest() {
		modules.verifyDataModelModuleUpload(subscriptionName, newProjectName);
	}
	
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class, priority = 15, dependsOnMethods = "createProjectUsingBmxTest")
	public void verifyAutomationUploadTest() {
		modules.verifyAutomationUpload(subscriptionName, newProjectName);
	}
	
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class, priority = 16, dependsOnMethods = "createProjectUsingBmxTest")
	public void verifySearchModuleUploadTest() {
		modules.verifySearchModuleUpload(subscriptionName, newProjectName);
	}
	
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class, priority = 17, dependsOnMethods = "createProjectUsingBmxTest")
	public void verifyChatModuleUploadTest() {
		modules.verifyChatModuleUpload(subscriptionName, newProjectName);
	}
	
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class, priority = 18, dependsOnMethods = "createProjectUsingBmxTest")
	public void verifyIntelligenceModuleUploadTest() {
		modules.verifyIntelligenceModuleUpload(subscriptionName, newProjectName);
	}
	
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class, priority = 19, dependsOnMethods = "createProjectUsingBmxTest")
	public void verifyAiBuilderModuleUploadTest() {
		modules.verifyAiBuilderModuleUpload(subscriptionName, newProjectName);
	}
	
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class, priority = 20, dependsOnMethods = "createProjectUsingBmxTest")
	public void verifyAgentBuilderModuleUploadTest() {
		modules.verifyAgentBuilderModuleUpload(subscriptionName, newProjectName);
	}
	
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class, priority = 21, dependsOnMethods = "createProjectUsingBmxTest")
	public void verifyAssetBuilderModuleUploadTest() {
		modules.verifyAssetBuilderModuleUpload(subscriptionName, newProjectName);
	}
	
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class, priority = 22, dependsOnMethods = "createProjectUsingBmxTest")
	public void verifyDataLakeModuleUploadTest() {
		modules.verifyDataLakeModuleUpload(subscriptionName, newProjectName);
	}
	
}
