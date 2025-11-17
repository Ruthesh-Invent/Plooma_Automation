package baseFunctions;

import static org.framework.playwright.utils.BaseClass.click;
import static org.framework.playwright.utils.BaseClass.sendText;

import org.framework.playwright.utils.HelperFunctions;
import org.framework.playwright.utils.UtilityClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CommonFunctions extends UtilityClass {

    public static String getUrl() {
        return prop.getProperty("url");
    }
    
    public void launchApplication(String url, String orgcode, String username, String password) {
        Page page = initialization();
        page.navigate(url, new Page.NavigateOptions().setTimeout(90000));
        HelperFunctions.login(page, orgcode, username, password);
    }

    public void launchUrl(String url, Page.NavigateOptions navigateOptions) {
        Page page = initialization();
        page.navigate(url, navigateOptions);
        page.waitForLoadState();
    }

	
	public void driverClose() {
		if(getPage().url().contains("null")) {
			getPage().locator("//*[name()='svg' and @xmlns='http://www.w3.org/2000/svg']").click();
			System.out.println("Clicked on Cancel button");
		}
		getPage().reload();
		getPage().locator("//a[@title='Home']").click();
		System.out.println("Clicked on Home button");
		System.out.println("-------------------------------------------------");
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod() {
		getPage().reload();
		if(getPage().locator("//app-error-message").isVisible()) {
			getPage().locator("//*[name()='svg' and @xmlns='http://www.w3.org/2000/svg']").click();
			System.out.println("Clicked on Cancel button");
		}
		Locator closeIcon = getPage().locator("//button[contains(@class,'mr-1 w-[24px] h-[24px] ng-tns-c747761558')]");
		if(closeIcon.count()>0){
			closeIcon.click();
		}
		//getPage().locator("//a[@title='Home']").click();
//		(try {
//			HelperFunctions.logout();
//			System.out.println("Logged Out Successfully");
//			System.out.println("Logging out and closing the page...");
//			getPage().close(); // or context/browser as per your flow
//			getPage().context().browser().close(); // Close entire browser
//		} catch (Exception e) {
//			System.err.println("Error during driverClose: " + e.getMessage());
//			e.printStackTrace();
//		})
		System.out.println("After Method Executed");
		System.out.println("------------------------------------");
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		try {
			if (getPage() != null && !getPage().isClosed()) {
				HelperFunctions.logout();
				System.out.println("Logged Out Successfully");
			} else {
				System.out.println("Page is already closed or null, skipping logout.");
			}
		} catch (Exception e) {
			System.err.println("Error during logout: " + e.getMessage());
		}

		try {
			getPage().close(); // or context/browser as per your flow
			getPage().context().browser().close(); // Close entire browser
			System.out.println("Browser Closed");
		} catch (Exception e) {
			System.err.println("Error during unload: " + e.getMessage());
		}
		System.out.println("------------------------------------");
		System.out.println("After Class Executed");
	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		String url = getUrl();
		report.setSystemInfo("Author Name", "Ruthesh");
		report.setSystemInfo("Environment", url);
		System.out.println("After Suite Executed");
	}
}
