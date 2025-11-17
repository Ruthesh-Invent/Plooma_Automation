package org.playwright.smoketestpages;

import org.framework.playwright.utils.BaseClass;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class DashboardPage extends BaseClass{

	public DashboardPage(Page page) {
		super(page);
		// TODO Auto-generated constructor stub
	}
	
	public void clickMainModule(String moduleName) {
	    // XPath: //span[normalize-space()='Appointments']/parent::a
	    String xpath = "//span[normalize-space()='" + moduleName + "']/parent::a";
	    Locator moduleElement = getPage().locator(xpath);
	    click(moduleElement, moduleName);
	}
	
	public void clickSubmodule(String subModuleName){
		
		String xpath = "//a[normalize-space(text())='" + subModuleName + "']";
		Locator submoduleElement = getPage().locator(xpath);
		click(submoduleElement, subModuleName);
		
	}
}

