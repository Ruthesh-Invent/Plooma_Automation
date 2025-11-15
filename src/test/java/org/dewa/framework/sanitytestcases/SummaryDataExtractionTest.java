package org.dewa.framework.sanitytestcases;

import org.dewa.framework.basefunctions.CommonFunctions;
import org.dewa.framework.listener.RetryListener;
import org.dewa.framework.scriptmodules.ScriptModules;
import org.dewa.framework.utils.ExcelUtility;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(org.dewa.framework.listener.ListenerImplementation.class)
public class SummaryDataExtractionTest extends CommonFunctions{

	String filePath=System.getProperty("user.dir") +"/src/main/resources/DataExtraction_12-03.xlsx";
	
	@BeforeClass
	public void navigateToProjectFromHome() {
		String emailId = prop.getProperty("email");
        String password=prop.getProperty("password");
		new CommonFunctions().launchApplication(getUrl(), emailId, password);
	}
	
	@BeforeMethod
	public void login() {
		System.out.println("Before Method");
	}
	
	@DataProvider
	public Object[][] dataSupplier() {
		ExcelUtility excel=new ExcelUtility(filePath);
		Object[][] data = excel.getMultipleData("Sheet1");
		System.out.println("Total Ids Provided : "+data.length);
	    return data;
	}
	
	//, dataProvider = "dataSupplier", retryAnalyzer = RetryListener.class
	//int beatId, String voiceId, String date, String sentiment, String subService, String sql, String comments, String channel, String subChannel, String source, String a, String b, int c
	@Test(enabled = true, dataProvider = "dataSupplier", retryAnalyzer = RetryListener.class)
	public void summaryExtractionTest(String voiceId) {
		ScriptModules modules=new ScriptModules(getPage());
		modules.summaryExtraction(voiceId.trim(), filePath);
	}
	
//	@Test(priority = 2)
//	public void findPriorityTest() {
//		ScriptModules modules = new ScriptModules(getPage());
//	    String[] attributes = {
//	    		"Source", "Date", "Sentiment", "Aspect", "Channel", "Sub-Channel", 
//		        "Main Service", "Sub Service", "Service Journey Stage", "Type", "SQL", 
//		        "Subject", "Location", "Language", "Customer Category", "Customer Contact"
//	    };
//
//	    for (String attribute : attributes) {
//	        String priority = modules.getPriorityForAttribute(attribute, filePath);
//	        System.out.println(attribute + " Priority is: " + priority);
//	    }
//	    
//	    Map<String, String> priorityValues = modules.getPriorityValues(filePath);
//	    modules.writePriorityToSheet(filePath, priorityValues);
//	}
	
//	@Test
//	public void compareExpectedActualAndCalculateAccuracy() {
//		ScriptModules modules = new ScriptModules(getPage());
//		modules.compareAndCalculateAccuracy();
//	}
//	
//	@Test
//	public void calculateAccuracy() {
//		ScriptModules modules = new ScriptModules(getPage());
//		modules.calculateAndWriteAccuracy(System.getProperty("user.dir") +"/src/test/resources/E2E_030225_Run 1_20250203064508_20250203_064537.xlsx");
//	}
	
}
