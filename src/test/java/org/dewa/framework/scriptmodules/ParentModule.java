package org.dewa.framework.scriptmodules;

import java.nio.file.Paths;

import org.dewa.framework.api_utility.ApiUtility;
import org.dewa.framework.objectrepo.Challenges360ListPage;
import org.dewa.framework.objectrepo.CustomerDetailsPage;
import org.dewa.framework.objectrepo.DashboardPage;
import org.dewa.framework.objectrepo.HomePage;
import org.dewa.framework.objectrepo.StudioPage;
import org.dewa.framework.objectrepo.VoicePage;
import org.dewa.framework.objectrepo.VoicedCustomersPage;
import org.dewa.framework.objectrepo.VoicesListPage;
import org.dewa.framework.utils.HelperFunctions;
import org.dewa.framework.utils.UtilityClass;

import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class ParentModule extends UtilityClass{
	
	ApiUtility api=new ApiUtility();

	private static ThreadLocal<HomePage> homePage = new ThreadLocal<>();
	private static ThreadLocal<VoicesListPage> voicesListPage = new ThreadLocal<>();
	private static ThreadLocal<VoicePage> voicePage = new ThreadLocal<>();
	private static ThreadLocal<VoicedCustomersPage> voicedCustomersPage = new ThreadLocal<>();
	private static ThreadLocal<CustomerDetailsPage> customerDetailsPage = new ThreadLocal<>();
	private static ThreadLocal<DashboardPage> dashboardPage = new ThreadLocal<>();
	private static ThreadLocal<StudioPage> studioPage = new ThreadLocal<>();
	private static ThreadLocal<Challenges360ListPage> challenges360ListPage = new ThreadLocal<>();
	

	public ParentModule(Page page) {
		homePage.set(new HomePage(page));
		voicesListPage.set(new VoicesListPage(page));
		voicePage.set(new VoicePage(page));
		voicedCustomersPage.set(new VoicedCustomersPage(page));
		customerDetailsPage.set(new CustomerDetailsPage(page));
		dashboardPage.set(new DashboardPage(page));
		studioPage.set(new StudioPage(page));
		challenges360ListPage.set(new Challenges360ListPage(page));
	}

	public static HomePage getHomePage() {
		return homePage.get();
	}

	public static VoicesListPage getVoicesListPage() {
		return voicesListPage.get();
	}

	public static VoicePage getVoicePage() {
		return voicePage.get();
	}
	
	public static VoicedCustomersPage getVoicedCustomerPage() {
		return voicedCustomersPage.get();
	}
	
	public static CustomerDetailsPage getCustomerDetailsPage() {
		return customerDetailsPage.get();
	}
	
	public static DashboardPage getDashboardPage() {
		return dashboardPage.get();
	}
	
	public static StudioPage getStudioPage() {
		return studioPage.get();
	}
	
	public static Challenges360ListPage getChallenges360ListPage() {
		return challenges360ListPage.get();
	}
	
	public void navigateTillVoicePage(String subsName, String projectName, String voiceId) {
		getHomePage().clickSubscription(subsName);
		getHomePage().searchProjectAndClick(projectName);
		getPage().locator("//app-bot-sidebar[contains(@class,'block')]").hover();
		getPage().waitForTimeout(1000);
		Locator navigationOption = getPage().locator("//span[text()='Voices']");
		navigationOption.click();
		getPage().locator("//icon[@name='setting/new-studio']").hover();
		getVoicesListPage().filterDocumentByTitle(voiceId);
		getVoicesListPage().clickOnDocument(voiceId);
	}
	
	    public void navigateTillDashboard(String subsName, String projectName) {
		getHomePage().clickSubscription(subsName);
		String subscriptionId = api.getSubscriptionId(subsName);
		HelperFunctions.saveAuthToken(subscriptionId, "SubsId");
		getHomePage().searchProjectAndClick(projectName);
		String finonId = api.getFinonId(subsName, subscriptionId, projectName);
		HelperFunctions.saveAuthToken(finonId, "FinonId");
		getPage().locator("//app-bot-sidebar[contains(@class,'block')]").hover();
		getPage().waitForTimeout(1000);
		Locator navigationOption = getPage().locator("//div[contains(@class, 'grow') and .//span[contains(text(), 'Voice Insights')]]");
		navigationOption.click();
		getPage().locator("//icon[@name='setting/new-studio']").hover();
		getPage().locator("//bot-tab//nav//span[text()='Voice Insights']").click();
		extentTest.get().log(Status.INFO, "Clicked on Dashboard Page");
	}
	
	
	public void navigateTillVoiceListPage(String subsName, String projectName) {
		getHomePage().clickSubscription(subsName);
		getHomePage().searchProjectAndClick(projectName);
		getPage().locator("//app-bot-sidebar[contains(@class,'block')]").hover();
		getPage().waitForTimeout(1000);
		Locator navigationOption = getPage().locator("//span[text()='Voices']");
		navigationOption.click();
		getPage().locator("//icon[@name='setting/new-studio']").hover();
		extentTest.get().log(Status.INFO, "Clicked on Voice List Page");
	}
	
	public void navigateTillVoicedCustomerPage(String subsName, String projectName) {
		getHomePage().clickSubscription(subsName);
		getHomePage().searchProjectAndClick(projectName);
		getPage().locator("//app-bot-sidebar[contains(@class,'block')]").hover();
		getPage().waitForTimeout(1000);
		Locator navigationOption = getPage().locator("//span[text()='Voiced Customers']");
		navigationOption.click();
		getPage().locator("//mat-label[text()='ID Search']").hover();
		extentTest.get().log(Status.INFO, "Clicked on Voiced Customers Page");
	}
	
	public void navigateTillCustomerDetailsPage(String subsName, String projectName, String customerID) {
		getHomePage().clickSubscription(subsName);
		getHomePage().searchProjectAndClick(projectName);
		getPage().locator("//app-bot-sidebar[contains(@class,'block')]").hover();
		getPage().waitForTimeout(1000);
		Locator navigationOption = getPage().locator("//span[text()='Voiced Customers']");
		navigationOption.click();
		getPage().locator("//mat-label[text()='ID Search']").hover();
		extentTest.get().log(Status.INFO, "Clicked on Voiced Customers Page");
		Locator idSeachBox = getPage().locator("//mat-label[text()='ID Search']");
		idSeachBox.fill(customerID);
		getPage().keyboard().press("Enter");
		waitTillLoading();
		Locator idElement = getPage().locator("//tbody[@role='rowgroup']//td[1]//div[contains(@title,'"+customerID+"')]");
		idElement.click();
		extentTest.get().log(Status.INFO, "Clicked on Customer Id "+customerID);
	}
	
	public void navigateFromOnePageToAnother(String pageName) {
		getPage().locator("//app-bot-sidebar[contains(@class,'block')]").hover();
		getPage().waitForTimeout(1000);
		//span[contains(text(),'Dashboard')]
		Locator navigationOption = getPage().locator("//span[contains(text(),'"+pageName+"')]");
		navigationOption.click();
		getPage().locator("//icon[@name='setting/new-studio']").hover();
		if(pageName.contains("Voice Insights")) {
			getPage().locator("//bot-tab//nav//span[text()='Voice Insights']").click();
		}
		waitTillLoading();
	}
	
	public void navigateTill360_ChallengesListPage(String subsName, String projectName) {
		getHomePage().clickSubscription(subsName);
		getHomePage().searchProjectAndClick(projectName);
		getPage().locator("//app-bot-sidebar[contains(@class,'block')]").hover();
		getPage().waitForTimeout(1000);
		Locator navigationOption = getPage().locator("//span[text()='360 Challenges']");
		navigationOption.click();
		getPage().locator("//icon[@name='setting/new-studio']").hover();
		extentTest.get().log(Status.INFO, "Clicked on 360 Challenges Page");
	}
	
	
	
	
	

	
	
	
}
