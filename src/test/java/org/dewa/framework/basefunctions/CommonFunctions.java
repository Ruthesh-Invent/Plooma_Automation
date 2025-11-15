package org.dewa.framework.basefunctions;

import org.dewa.framework.utils.HelperFunctions;
import org.dewa.framework.utils.UtilityClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

import com.microsoft.playwright.Frame;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CommonFunctions extends UtilityClass {

	public static String getUrl() {
		String envi = System.getProperty("environment");
		if (envi == null) {
			return prop.getProperty("url");
		} else {
			return envi;
		}
	}

	public void launchApplication(String url, String emailId, String password) {
		Page tlpage = initialization();
		tlpage.navigate(url, new Page.NavigateOptions());
		HelperFunctions.login(tlpage, emailId, password);
	}

	public void dashboardLaunchApplication(String url, String emailId, String password) {
		Page tlpage = initialization();
		tlpage.navigate(url, new Page.NavigateOptions());
		HelperFunctions.dashboardLogin(tlpage, emailId, password);
	}

	public void launchUrl(String url, Page.NavigateOptions navigateOptions) {
		Page page = initialization();
		page.navigate(url, navigateOptions);
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod() {
		if (getPage().locator("//iframe[@class='w-full h-full']").isVisible()) {
			Locator iframeLocator = getPage().locator("//iframe[@class='w-full h-full']");
			Frame frame = iframeLocator.elementHandle().contentFrame();
			frame.locator("//*[( @tooltip='Close' or contains(@iconname,'close') )]").click();
		}
		if (getPage().locator("//*[contains(@tooltip,'Close')]").isVisible()) {
			getPage().locator("//*[contains(@tooltip,'Close')]").click();
		}
		getPage().waitForTimeout(1000);
		if(getPage().locator("//app-bot-sidebar[contains(@class,'block')]").isVisible()) {
			getPage().locator("//app-bot-sidebar[contains(@class,'block')]").hover();
			getPage().waitForTimeout(2000);
			System.out.println("Hovered on Side Bar");
			Locator element = getPage().locator("//app-bot-sidebar/div[1]/div[2]//*[contains(@mattooltip,'Home')]");
			element.click();
			System.out.println("Clicked on home page");
		}
		if(getPage().url().contains("studio")) {
			getPage().locator("//a[@title='Home']").click();
		}
		getPage().waitForTimeout(2000);
		getPage().reload();
		System.out.println("After Method Executed");
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		try {
			if (getPage() != null && !getPage().isClosed()) {
				// HelperFunctions.logout();
				//System.out.println("Logged Out Successfully");
			} else {
				System.out.println("Page is already closed or null, skipping logout.");
			}
		} catch (Exception e) {
			System.err.println("Error during logout: " + e.getMessage());
		}

		try {
			System.out.println("Browser Closed");
		} catch (Exception e) {
			System.err.println("Error during unload: " + e.getMessage());
		}

		// System.out.println("TearDown complete for thread: " +
		// Thread.currentThread().getId());
		System.out.println("------------------------------------");
		System.out.println("After Class Executed");
	}
	
	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		String url = getUrl();
		report.setSystemInfo("Author Name", "Manoj");
		report.setSystemInfo("Environment", url);
		System.out.println("After Suite Executed");
	}
}
