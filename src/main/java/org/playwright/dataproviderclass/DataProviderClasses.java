package org.playwright.dataproviderclass;
 
import org.framework.playwright.utils.ExcelUtility;

import org.framework.playwright.utils.JsonUtilties;

import org.testng.annotations.DataProvider;
 
import java.lang.reflect.Method;
 
public class DataProviderClasses {
 
    JsonUtilties jsonUtils = new JsonUtilties();

    ExcelUtility excelUtils=new ExcelUtility();
 

    @DataProvider(name="getProjectDetailsFromExcel")

    public Object[][] getProjectDetailsFromExcel(){

//    	ExcelUtility excel=new ExcelUtility()

    	return excelUtils.getMultipleData("Entity.xlsx","Sheet1");
    }


 
    @DataProvider(name ="getSmokeTestCasesData")

    public Object[] getSmokeTestCasesData(Method m) {

        return jsonUtils.readMultiJsonData("SmokeTestCases.json", m.getName());}
 
}
 