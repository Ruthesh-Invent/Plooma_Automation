package org.playwright.pages;

import com.aventstack.extentreports.Status;
import com.itextpdf.text.log.SysoCounter;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.framework.playwright.annotations.FindBy;
import org.framework.playwright.utils.BaseClass;

public class HomePage extends BaseClass {

	public HomePage(Page page) {
		super(page);
		expectedResponse = new StringBuilder();
	}

	StringBuilder actualResponse;
	StringBuilder expectedResponse;

	//@FindBy(xpath = "//div[contains(text(),'Project')]")

    @FindBy(xpath = "(//div[contains(text(),'Project')])[1]")

    private Locator addNewProjectButton;

	@FindBy(xpath = "//input[@formcontrolname='FinonName']")
	private Locator inputProjectName;

	@FindBy(xpath = "//textarea[@formcontrolname='Description']")
	private Locator inputProjectDescription;

	@FindBy(xpath = "//button[@class='self-end primary']")
	private Locator buttonCreate;

	@FindBy(xpath = "//img[@class='text-white ng-star-inserted']")
	private Locator dashboardicon;

	@FindBy(xpath = "//input[@placeholder='Search Projects']")
	private Locator searhbar;

	@FindBy(xpath = "//a[@href='/LH_QA/Dofy/studio/project/overview']")
	private Locator Navimenustudiconfig;

	@FindBy(xpath = "//div[text()=' Agent Test ']")
	private Locator projectHover;

	public void clickSubscription(String subsName) {
		Locator subsElement = getPage()
				.locator("//div[@class='subscription-name text-dark truncate' and text()=' " + subsName + " ']");
		click(subsElement, subsName);
	}

	private Locator projects = getPage().locator("//button[text()=' Projects ']");

	public Locator getProjects() {
		return projects;
	}

	public void clickNavigationmenu() {
		hover(projectHover, "hover");
		click(Navimenustudiconfig, "Navigation menu");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void searchProjectAndClick(String projectName) {
		Locator searchElement = getPage().locator("//input[@placeholder='Search Projects']");
		sendText(searchElement, projectName);
		pressEnterkey();
		Locator element = getPage().locator("//div[contains(text(),'" + projectName + "')]");
		getPage().waitForTimeout(2000);
		if (isElementExists(element)) {
			element.click();
			extentTest.get().log(Status.INFO, "Clicked on  : <b>" + projectName + "</b>");
		} else {
			Locator loadMoreElement = getPage().locator("//div[contains(text(),'Load more')]");
			for (int i = 0; i < loadMoreElement.count(); i++) {
				click(loadMoreElement, "Load More");
				getPage().waitForTimeout(2000);
				if (isElementExists(element)) {
					click(element, projectName);
					break;
				}
			}
		}
	}
	
	public void searchProjectAndHover(String projectName) {
		Locator searchElement = getPage().locator("//input[@placeholder='Search Projects']");
		sendText(searchElement, projectName);
		pressEnterkey();
		Locator element = getPage().locator("//div[contains(text(),'" + projectName + "')]");
		getPage().waitForTimeout(2000);
		if (isElementExists(element)) {
			hover(element, projectName);
		} else {
			Locator loadMoreElement = getPage().locator("//div[contains(text(),'Load more')]");
			for (int i = 0; i < loadMoreElement.count(); i++) {
				click(loadMoreElement, "Load More");
				getPage().waitForTimeout(2000);
				if (isElementExists(element)) {
					hover(element, projectName);
					break;
				}
			}
		}
	}

	public void selectProject(String projectName) {
		Locator locator = getPage().locator("//div[text()=' " + projectName + " ']");
		click(locator, projectName);
	}

	public void clickOnNavigationMenu(String menuName) {
		// a[contains(text(),'Dashboard')]
		Locator element = getPage().locator("//a[contains(text(),'" + menuName + "')]");
		click(element, menuName + " Navigation Option");
	}

	public void clickStageUserDropdown(String dropdownName) {
		Locator dropdownElement = getPage().locator(
				"//h6[text()='" + dropdownName + "']//parent::*//following-sibling::div//mat-select[@role='combobox']");
		click(dropdownElement, dropdownName);

	}

	public void clickAddNewProject() {
		click(addNewProjectButton, "Add New Project");
	}

	public void clickCreateNewProject() {
		Locator createOption = getPage().locator("//button//div[contains(text(),'Create New')]");
		click(createOption, "Create New Project");
	}

	public String sendTextProjectName(String projectName) {
		sendText(inputProjectName, projectName);
		return projectName;
	}

	public String sendTextProjectDescription(String projectDescription) {
		sendText(inputProjectDescription, projectDescription);
		return projectDescription;
	}

	public void clickCreateButton() {
		click(buttonCreate, "Create button");
	}

	public String getProjectName(String projectName) {
		Locator projectElement = getPage()
				.locator("//div[contains(@class,'project-name') and contains(text(),'" + projectName + "')]");
		return getText(projectElement).trim();
	}
 
	public String getProjectDescription(String projectName) {
		// div[contains(@class,'project-name') and
		// contains(text(),'Marvin_Bashirian')]/following-sibling::div[@data-cy='project-desc']//span
		Locator projectElement = getPage().locator("//div[contains(@class,'project-name') and contains(text(),'"
				+ projectName + "')]/following-sibling::div[@data-cy='project-desc']//span");
		return getText(projectElement).trim();
	}

	/*public void clickDeletedProjectsIcon() {
		//Locator deletedProjectIcon = getPage().locator("//div[contains(@class,'deleted-projects')]");
		Locator deletedProjectIcon = getPage().locator("//button[@class='mat-mdc-tooltip-trigger absolute right-5 flat ng-star-inserted']");
		click(deletedProjectIcon, "Deleted projects icon");
	}*/
	public void clickDeletedProjectsIcon() {

	    try {

	        Locator deletedProjectIcon = getPage().locator("//button[@class='mat-mdc-tooltip-trigger absolute right-5 flat ng-star-inserted']");
	 
	        // Check if the button is visible and enabled before clicking

	        if (deletedProjectIcon.isVisible()) {

	            click(deletedProjectIcon, "Deleted projects icon");

	            System.out.println("Deleted projects icon button clicked successfully.");

	        } else {

	            System.out.println(" Deleted projects icon button is not visible.");

	        }

	    } catch (Exception e) {

	        System.out.println("Failed to click the Deleted projects icon button: " + e.getMessage());

	    }

	}

	public void navigateToProjectDashboard(String projectName) {
		Locator projectElement = getPage().locator("//div[contains(text(),'" + projectName
				+ "')]//parent::div/../../following-sibling::div//a[contains(@class,'cy-_dashboard')]");
		click(projectElement, "Dashboard");
	}

	public void navigateToProjectStudioPage(String projectName) {
		Locator projectElement = getPage().locator("//div[contains(text(),'" + projectName
				+ "')]//parent::div/../../following-sibling::div//a[contains(@class,'cy-studio')]");
		click(projectElement, "Studio page");
	}

	public void clickHomePage() {
		Locator homePageElement = getPage().locator("//a[@title='Home']");
		click(homePageElement, "Home Page");
	}

	public void clickSubscriptionSettings() {
		Locator element = getPage().locator("//a[@iconname='settings']");
		click(element, "Settings");
	}

	public void clickOnSettingsMenu(String menuName) {
		// span[text()='Usage Details']
		Locator element = getPage().locator("//span[text()='" + menuName + "']");
		click(element, menuName);
	}

	public void selectProjectFromProjectDropdown(String projectName) {
		Locator fieldElement = getPage()
				.locator("//mat-label[contains(text(),'Project')]/../../../..//mat-select[@role='combobox']");
		click(fieldElement, "Select User");
		Locator searchElement = getPage().locator(
				"//mat-label[contains(text(),'Project')]//ancestor::mat-form-field//following::div//input[@placeholder='Search']");
		sendText(searchElement, projectName);
		Locator dropdownElement = getPage().locator(
				"//mat-label[contains(text(),'Project')]//ancestor::mat-form-field//following::div//input[@placeholder='Search']//ancestor::div//mat-option//span[contains(text(),'"
						+ projectName + "')]");
		click(dropdownElement, projectName);
	}

	public void clickOnSearchInSettings() {
		Locator element = getPage().locator("//div[contains(text(),'Search')]");
		click(element, "Search Button");
	}

	public boolean assertProjectSearchInUsageDetails(String projectName) {
		// th[text()='Project Name']/ancestor::table//td[1]
		boolean res = false;
		List<Locator> elements = getPage().locator("//th[text()='Project Name']/ancestor::table//td[1]").all();
		List<String> allText = new ArrayList<String>();
		for (Locator element : elements) {
			allText.add(element.textContent());
		}

		for (String text : allText) {
			if (text.contains(projectName)) {
				res = true;
				break;
			}
		}
		return res;
	}

	public void clickDateRangeInUsageDetails() {
		Locator element = getPage().locator("//mat-label[text()='Date Range']");
		click(element, "Date Rage");
	}

	public void selectDateRangeInUsageDetails(int fromDate, String fromHour, String fromMinute, int toDate,
			String toHour, String toMinute) {
		// span[text()=' 9 ' and @class='owl-dt-calendar-cell-content']
		Locator fromD = getPage()
				.locator("//span[text()=' " + fromDate + " ' and @class='owl-dt-calendar-cell-content']");
		click(fromD, fromDate + "th");
		Locator fromH = getPage().locator("(//input[@class='owl-dt-timer-input'])[1]");
		sendText(fromH, fromHour + "Hour");
		Locator fromM = getPage().locator("(//input[@class='owl-dt-timer-input'])[2]");
		sendText(fromM, fromMinute + " Minute");
		Locator toD = getPage().locator("//span[text()=' " + toDate + " ' and @class='owl-dt-calendar-cell-content']");
		click(toD, toDate + "th");
		Locator toH = getPage().locator("(//input[@class='owl-dt-timer-input'])[1]");
		sendText(toH, toHour + " Hour");
		Locator toM = getPage().locator("(//input[@class='owl-dt-timer-input'])[2]");
		sendText(toM, toMinute + " Minute");
	}

	public void clickSetInDateRange() {
		Locator element = getPage().locator("//span[text()=' Set ']");
		click(element, "Set");
	}

	public void clickCreateNewSubscription() {
		Locator element = getPage().locator("//div[text()=' Add New Subscription ']");
		click(element, "New Subscription");
	}

	public boolean checkCreateSubscriptionPopUpVisibility() {
		return getPage().locator("//h2[text()='Create Subscription']").isVisible();
	}

	public void sendNameSubscriptionNameTFInPopup(String name) {
		Locator element = getPage().locator("//input[@placeholder='Name']");
		sendText(element, name);
	}

	public void clickDeleteSubscription() {
		Locator element = getPage().locator("//button[contains(@class,'delete-subscription')]");
		click(element, "Delete Subscription");
	}

	public boolean checkDeleteSubscriptionPopUpVisibility() {
		return getPage().locator("//h1[text()='Delete Subscription']").isVisible();
	}

	public void getDeleteConfirmationText() {
		Locator element = getPage().locator("//p[contains(text(),'NOTE:')]");
		getText(element);
	}

	public void clickConfirmDelete() {
		Locator element = getPage().locator("//div[text()=' Confirm ']");
		click(element, "Confirm Delete");
	}

	public void sendTextToSubscriptionNameTF(String name) {
		Locator element = getPage().locator("//input[@name='SubscriptionName']");
		sendText(element, name);
	}

	public void clickSave() {
		Locator element = getPage().locator("//div[contains(text(),' Save ')]");
		click(element, "Save Button");
	}

	public boolean checkSubscriptionAvailability(String subscriptionName) {
		boolean res = false;
		List<Locator> allElements = getPage().locator("//div[contains(@class,'subscription-name')]").all();
		List<String> allName = new ArrayList<String>();
		for (Locator element : allElements) {
			allName.add(element.textContent());
		}
		for (String name : allName) {
			if (name.contains(subscriptionName)) {
				res = true;
				break;
			}
		}
		return res;
	}

	public boolean checkEnableRestoreProjectToggleButton() {
		Locator element = getPage().locator("//button[@name='ProjectRestoreEnabled']");
		return element.isChecked();
	}

	public void clickEnableRestoreProjectToggleButton() {
		Locator element = getPage().locator("//button[@name='ProjectRestoreEnabled']");
		click(element, "Enable Restore Project Toggle Button");
	}

	public boolean checkEnableDashboardToggleButton() {
		Locator element = getPage().locator("//button[@name='SubscriptionDashboardEnabled']");
		return element.isChecked();
	}

	public void clickEnableDashboardToggleButton() {
		Locator element = getPage().locator("//button[@name='SubscriptionDashboardEnabled']");
		click(element, "Enable Dashboard Toggle Button");
	}

	public void clickShowDeletedProject() {
		Locator element = getPage().locator("//div[contains(@class,'deleted-projects')]");
		click(element, "Show Deleted Project");
	}

	public boolean checkTheProjectsAvailabilityInProjectsListPage() {
		boolean res = false;
		List<Locator> elements = getPage().locator("//a[contains(@class,'cy-project-card')]").all();
		if (elements.size() > 0) {
			res = true;
		}
		return res;
	}

	public boolean checkDashboardAvailabilityInProjectListPage() {
		Locator element = getPage().locator("//button[text()=' Dashboard ']");
		return element.isEnabled();
	}

	public void clickOnAddUser() {
		Locator element = getPage().locator("//button[@data-cy='add-user']");
		click(element, "Add User");
	}

	public void sendMailToMailTextField(String mailId) {
		Locator element = getPage().locator("//input[@placeholder='Email']");
		sendText(element, mailId);
	}

	public void clickAutoGeneratePassword() {
		Locator element = getPage().locator("//label[text()=' Auto Generate Password ']");
		click(element, "Auto Generate Password");
	}

	public void clickAdvancedWhileAddingUser() {
		Locator element = getPage().locator("//div[contains(text(),'Advanced')]");
		click(element, "Advanced");
	}

	public void clickCreateUser() {
		Locator element = getPage().locator("//span[contains(text(),'Create')]");
		click(element, "Create User");
	}

	public boolean assertAddUserPopup() {
		return getPage().locator("//h2[text()='Add User']").isVisible();
	}

	public boolean checkAvailabilityOfEmailInTheUserList(String expectedEmailId) {
		boolean res = false;
		List<Locator> allElements = getPage().locator("//th[text()='Email']/ancestor::table/tbody/tr/td[3]").all();
		List<String> allEmail = new ArrayList<String>();
		for (Locator element : allElements) {
			allEmail.add(element.textContent());
		}
		for (String email : allEmail) {
			if (email.contains(expectedEmailId)) {
				res = true;
				break;
			}
		}
		return res;
	}

	public void changeRoleFromTheDropdownUsingMailId(String mailId, String roleName) {
		// td[text()='m4user@botminds.ai']/..//td//mat-select-trigger[text()=' Creator
		// ']
		Locator dropdown = getPage().locator("//td[text()='" + mailId + "']/..//td//mat-select/div/div[2]");
		click(dropdown, "Creator Dropdown");
		// span[contains(text(),'Annotator') and @class='mdc-list-item__primary-text']
		Locator dropdownOption = getPage()
				.locator("//span[contains(text(),'" + roleName + "') and @class='mdc-list-item__primary-text']");
		click(dropdownOption, roleName);
	}

	public String getRoleByEmail(String mailId) {
		Locator element = getPage().locator("//td[text()='" + mailId + "']/../td//mat-select");
		return getText(element);
	}

	public void hoverOnTheUserInUserDetailsPage(String userMailId) {
		// td[text()='manoclient@test.com']
		Locator element = getPage().locator("//td[text()='" + userMailId + "']");
		hover(element, userMailId);
	}

	public void clickOnHoverIcons(String mailId, String iconName) {
		// td[text()='manoclient@test.com']/..//button[contains(@iconname,'re-activate')]
		Locator element = getPage()
				.locator("//td[text()='" + mailId + "']/..//button[contains(@iconname,'" + iconName + "')]");
		click(element, mailId + " " + iconName);
	}

	public boolean checkUserInfoPopupVisibility() {
		return getPage().locator("//h2[text()='User Info']").isVisible();
	}

	public void closeUserViewPopup() {
		Locator element = getPage().locator("//button[@iconname='close']");
		click(element, "Close User View");
	}

	public boolean checkVisibilityOfManageProjectsPopup() {
		return getPage().locator("//h2[text()='Manage Projects']").isVisible();
	}

	public void clickOnProjectsInManageProjectsPopup(String projectName) {
		// span[text()=' Test Sample Project ']
		Locator element = getPage().locator("//span[text()=' " + projectName + " ']");
		click(element, projectName);
	}

	public void clickUpdateInManageProjectsPopup() {
		Locator element = getPage().locator("//span[text()=' Update ']");
		click(element, "Update");
	}

	public void closeManageProjectsPopup() {
		Locator element = getPage().locator("//mat-icon[text()='close']");
		click(element, "Close Manage Projects");
	}

	public boolean checkProjectAccessInManageProjectsPopup(String projectName) {
		// span[text()=' Testing ']/../..//input
		return getPage().locator("//span[text()=' " + projectName + " ']/../..//input").isChecked();
	}

	public boolean checkDeleteUserConfirmationPopup() {
		return getPage().locator("//h1[text()='Delete team member']").isVisible();
	}

	public void clickCancelOnDeleteConfirmationPopup() {
		Locator element = getPage().locator("//div[contains(text(),'Cancel')]");
		click(element, "Cancel Delete");
	}

	public void clickConfirmOnDeleteConfirmationPopup() {
		Locator element = getPage().locator("//div[contains(text(),'Confirm')]");
		click(element, "Cancel Delete");
	}

	public void sendColourToSubscriptionsUnderWhiteLabeling(String textBoxLabel, String colourCode) {
		// mat-label[text()='Alpha']/../../../../..//input
		Locator element = getPage().locator("//mat-label[text()='" + textBoxLabel + "']/../../../../..//input");
		sendText(element, colourCode);
	}

	public void pinUnpinSubscription(String subscriptionName) {
		// div[text()=' BOMAutomation
		// ']/..//button[contains(@class,'subscription-pin-icon')]
		Locator element = getPage().locator(
				"//div[text()=' " + subscriptionName + " ']/..//button[contains(@class,'subscription-pin-icon')]");
		click(element, "Pin/Unpin " + subscriptionName);
	}

	public boolean assertSuccessfullMessage() {
		return getPage().locator("//div[@aria-live='assertive']").isVisible();
	}

	public void clickStudio() {
		// a[contains(text(),'Studio')]
		Locator element = getPage().locator("//a[contains(text(),'Studio')]");
		click(element, "Studio");
	}

	public void clickdashboardicon() {
		click(dashboardicon, "icon");
	}

	public void createNewProject(String subsName, String projectName, String discription) {
		clickSubscription(subsName);
		clickOnAddNewProject();
		clickNewProject();
		enterProjectName_Description(projectName, discription);
	}

	public void clickNewProject() {
		Locator createOption = getPage().locator("//button//div[contains(text(),'Create New')]");
		click(createOption, "Create New Project");
	}

	public void clickOnAddNewProject() {
		getPage().locator("//button[@iconname='add']//div[contains(text(),'Project')]").click();
		extentTest.get().log(Status.INFO, "Clicked on Add New Project");
	}

	public void enterProjectName_Description(String projectName, String description) {
		// mat-label[text()='Project Name']
		getPage().locator("//mat-label[text()='Project Name']").fill(projectName);
		getPage().locator("//mat-label[text()='Description']").fill(description);
		getPage().locator("//button//div[contains(text(),'Create')]").click();
		extentTest.get().log(Status.INFO, "Project Name : <b>" + projectName + "</b>");
		extentTest.get().log(Status.INFO, "Description : <b>" + description + "</b>");
		waitForSuccessMessageToAppearAndDissapear();
	}

	public boolean checkProjectNameAvailability(String expectedProjectName) {
		// div[contains(@class,'project-name') and contains(text(),'Voice of Customer')]
		boolean res = false;
		while (true) {
			if (getPage()
					.locator(
							"//div[contains(@class,'project-name') and contains(text(),'" + expectedProjectName + "')]")
					.isVisible()) {
				res = true;
				break;
			} else if (getPage().locator("//button[@data-cy='load-more-docs']").isVisible()) {
				getPage().locator("//button[@data-cy='load-more-docs']").click();
			}
		}
		return res;
	}


	public void clearSearchTextBox() {
		Locator searchElement = getPage().locator("//input[@placeholder='Search Projects']");
		searchElement.clear();
	}

	public void clickpiper() {
		Locator element = getPage().locator("//button[@tooltip='Piper']");
		click(element, "Piper Icon");
	}

	public void setMessageInPiper(String query) {
		Locator element = getPage().locator("//textarea[@placeholder='Ask Piper anything about the platform...']");
		sendText(element, query);
		pressEnterkey();
		try {
			// Thread.sleep(Duration.ofSeconds(60));
			Thread.sleep(Duration.ofSeconds(60).toMillis());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getQueryResponse(String query) {

		//// p[text()='What is taxanomy']/parent::div/parent::div/following-sibling::div

		Locator element = getPage()
				.locator("//p[text()='" + query + "']/parent::div/parent::div/following-sibling::div");
		// Locator liElement =
		// getPage().locator("//p[text()='"+query+"']/parent::div/parent::div/following-sibling::div/descendant::li");
		return element.textContent().trim();

//        // System.out.println("Total elements found: " + count);
//         actualResponse= new StringBuilder();
//         for (int i = 0; i < count; i++) {
//             String text = element.nth(i).innerText();
//             actualResponse.append(text).append(" ");
//             System.out.println("Element " + (i + 1) + " Text: " + text);
//         }
//         for (int i = 0; i <liCount; i++) {
//             String text = liElement.nth(i).innerText();
//             actualResponse.append(text).append(" ");
//             System.out.println("Element " + (i + 1) + " Text: " + text);
//         }
//         System.out.println(actualResponse);
	}

//	public void searchProject(String DOFY) {
//		searhbar.fill(DOFY);
//	}

	public boolean validateBotRespose() {
		String actualresponse = getQueryResponse("what is taxonomy");

		String expectedResponse = "A taxonomy in Botminds is a way of organizing and labeling document data so that it’s easy for Botminds to capture information and show model results clearly and structurally. It helps break down documents into sections, pages, types, blocks, or layouts, making data extraction and automation faster and more accurate.";
		// expectedResponse.append("ted values LLM-generated outputs (e.g., summaries)
		// Lookups & enrichments Statistical aggregations (especially in L2) Contextual
		// insights (comparative reasoning) Predictive labels Taxonomy for L1: Candidate
		// Name, Total Experience (Derived) Taxonomy for L2 (Experience): Company Name,
		// Role, Duration ");
		return actualresponse.contains(expectedResponse);
	}

 
    public void searchProject(String DOFY) {
        searhbar.fill(DOFY); 
    }

    public void clickSubscriptionname(String subsName) {
		Locator subsElement = getPage()
				.locator("//div[@class='subscription-name text-dark truncate' and text()=' " + subsName + " ']");
		click(subsElement, subsName);
	}

}
