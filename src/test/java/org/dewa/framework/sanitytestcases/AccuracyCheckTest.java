package org.dewa.framework.sanitytestcases;

import org.dewa.framework.basefunctions.CommonFunctions;
import org.dewa.framework.listener.RetryListener;
import org.dewa.framework.scriptmodules.ScriptModules;
import org.dewa.framework.utils.ExcelUtility;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(org.dewa.framework.listener.ListenerImplementation.class)
public class AccuracyCheckTest extends CommonFunctions{

	@BeforeMethod
	public void navigateToProjectFromHome() {
		System.out.println("Before Method");
		String emailId = prop.getProperty("email");
        String password=prop.getProperty("password");
		new CommonFunctions().launchApplication(getUrl(), emailId, password);
	}
	
	@DataProvider
	public Object[][] dataSupplier() {
		ExcelUtility excel=new ExcelUtility(System.getProperty("user.dir") +"/src/test/resources/E2E-Test Data - Matrix 1 1.xlsx");
		Object[][] data = excel.getMultipleData("Sheet2");
	    return data;
	}
	
	@Test(priority = 1, dataProvider = "dataSupplier", retryAnalyzer = RetryListener.class, enabled = false)
	public void sanityTest(String type, String sentiment, String mainServices, String subServices, String sql, String source, String comments, String voiceId, String status, String remark) {
		ScriptModules modules=new ScriptModules(getPage());
		modules.accuracyCheck(type, sentiment, mainServices, subServices, sql, source, comments, voiceId, status, remark);
	}
	
	@DataProvider
	public Object[][] dataSupplierForCalculation() {
		ExcelUtility excel=new ExcelUtility(System.getProperty("user.dir") +"/src/test/resources/E2E-Test Data - Matrix 1 1.xlsx");
		Object[][] data = excel.getMultipleData("Accuracy");
	    return data;
	}
	
	@Test(priority = 2, dataProvider = "dataSupplierForCalculation",  dependsOnMethods = "sanityTest", enabled = false)
	public void accuracyCalulationTest(String attributeName, int expected, int actual) {
		ScriptModules modules=new ScriptModules(getPage());
		modules.accuracyCalulation(attributeName, expected, actual);
	}
	
	@Test(priority = 3, dependsOnMethods = "accuracyCalulationTest", enabled = false)
	public void totalAccuracyTest() {
		ScriptModules modules=new ScriptModules(getPage());
		modules.calculateTotalAccuracy();
		int[] cloumnsToDelete= {2, 3};
		ExcelUtility.clearColumns(System.getProperty("user.dir") +"/src/test/resources/E2E-Test Data - Matrix 1 1.xlsx", "Accuracy", cloumnsToDelete);
	}
}
