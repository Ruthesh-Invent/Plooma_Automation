package org.dewa.framework.regressiontestcases;

import org.dewa.framework.basefunctions.CommonFunctions;
import org.dewa.framework.scriptmodules.ScriptModules;
import org.dewa.framework.utils.ExcelUtility;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(org.dewa.framework.listener.ListenerImplementation.class)
public class VoiceTypeAndSQLTest extends CommonFunctions{

	@BeforeMethod
	public void navigateToProjectFromHome() {
		String emailId = prop.getProperty("email");
        String password=prop.getProperty("password");
		new CommonFunctions().launchApplication(getUrl(), emailId, password);
	}
	
	@DataProvider
	public Object[][] dataSupplier() {
		ExcelUtility excel=new ExcelUtility(System.getProperty("user.dir") +"/src/test/resources/RegTest_040225_Voicetype_SQL_Run 1_20250204082010_20250204_082019.xlsx");
		Object[][] data = excel.getMultipleData("RegTest_040225_Voicetype_SQL_Ru");
	    return data;
	}
	
	@Test(dataProvider = "dataSupplier", enabled = false)
	public void voiceTypeAndSQLTest(String voiceId, String date, String sentiment, String subService, String sql, String comments, String channel, String subChannel, String source) {
		ScriptModules modules=new ScriptModules(getPage());
		modules.summaryExtraction(voiceId, System.getProperty("user.dir") +"/src/test/resources/RegTest_040225_Voicetype_SQL_Run 1_20250204082010_20250204_082019.xlsx");
	}
}
