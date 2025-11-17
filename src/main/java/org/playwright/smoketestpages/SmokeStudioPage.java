package org.playwright.smoketestpages;

import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Locator.WaitForOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.BoundingBox;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

import org.bouncycastle.LICENSE;
import org.framework.playwright.annotations.FindBy;
import org.framework.playwright.utils.BaseClass;
import org.framework.playwright.utils.DataFaker;

import static org.framework.playwright.utils.BaseClass.assertSetEquals;

import java.nio.file.Path;
import java.time.Duration;
import java.util.*;

public class SmokeStudioPage extends BaseClass {

	public SmokeStudioPage(Page page) {
		super(page);
	}

	@FindBy(xpath = "//div[text()='Project Overview']")
	private Locator projectOverview;

	@FindBy(xpath = "//icon[@name='setting/new-studio']")
	private Locator buttonStudio;

	@FindBy(xpath = "//div[text()=' Submit ']")
	private Locator buttonSubmit;

	@FindBy(xpath = "//a[@title='Home']")
	private Locator homePageIcon;

	public String getOverviewText() {
		return getText(projectOverview).trim();
	}

	public void enableDisableToggleButton(String buttonName, boolean isTrue) {
		Locator toggleElement = getPage().locator("//div[contains(text(),'" + buttonName
				+ "')]//parent::div//following-sibling::mat-slide-toggle//button[@aria-checked]");
		if (isTrue) {
			click(toggleElement, buttonName);
		} else {
			click(toggleElement, buttonName);
		}
	}

	public void clickToggleButton(String buttonName) {
		getPage().locator("//div[contains(text(),'" + buttonName
				+ "')]//parent::div//following-sibling::mat-slide-toggle//button[@aria-checked]").click();
	}

	public void clickSections(String sectionName) {
		// *[contains(text(),'AI Builder')]
		Locator sectionElement = getPage().locator("//*[contains(text(),'" + sectionName + "')]").first();
		click(sectionElement, sectionName);
	}

	public void clickSubModules(String subModuleNames) {
		// *[contains(text(),'Taxonomy')]
		Locator sectionElement = getPage().locator("//*[contains(text(),'" + subModuleNames + "')]").first();
		click(sectionElement, subModuleNames);
	}

	public void clickSubSections(String sectionName) {
		// div[contains(text(),'Taxonomy')]
		Locator sectionElement = getPage().locator("//div[contains(text(),'" + sectionName + "')]").first();
		click(sectionElement, sectionName);
	}

	public void clickActionPane(){
		Locator sectionElement =getPage().locator("//span[contains(text(),'Action Pane')]");
		click(sectionElement,"Action Pane");
	}

	public String selectDropdownCheckBox(String fieldName, String fieldValue) {
		Locator fieldElement = getPage().locator("//mat-label//div[text()='" + fieldName + "']");
		if (fieldElement.count() == 0) {
			fieldElement = getPage().locator(
					"//mat-label[contains(text(),'Can be moved to')]/../../../..//parent::div//following-sibling::div//mat-select[@role='combobox']");
		}
		if (fieldElement.count() == 0) {
			fieldElement = getPage().locator(
					"//mat-label//span[contains(text(),'Can be moved to')]/../../../..//parent::div//following-sibling::div//mat-select[@role='combobox']");

		}
		System.out.println("Count is : " + fieldElement.count());
		click(fieldElement, fieldName);
		Locator fieldValueElement = getPage().locator("//mat-label//div[text()='" + fieldName
				+ "']//ancestor::mat-form-field//following::div//mat-option//span[contains(text(),'" + fieldValue
				+ "')]/../mat-pseudo-checkbox");
		if (fieldValueElement.count() == 0) {
			fieldValueElement = getPage().locator("//mat-label//span[contains(text(),'" + fieldName
					+ "')]//ancestor::mat-form-field//following::div//mat-option//span[contains(text(),'" + fieldValue
					+ "')]/../mat-pseudo-checkbox");
		}
		if (fieldValueElement.count() == 0) {
			fieldValueElement = getPage().locator("//mat-label[contains(text(),'" + fieldName
					+ "')]//ancestor::mat-form-field//following::div//mat-option//span[contains(text(),'" + fieldValue
					+ "')]/../mat-pseudo-checkbox");
		}
		click(fieldValueElement, fieldValue);
		wasteDropdownClick("//a[contains(text(),'Hide Advanced Settings')]");
		return fieldValue;
	}

//	public void clickStudioPage() {
//		getPage().locator("//icon[@name='setting/new-studio']/../..").dblclick();
//		extentTest.get().log(Status.INFO, "Clicked on Studio Button");
//	}
	
	public void clickSubmitButton() {
		if (buttonSubmit.count() > 1) {
			Locator submitButton = getPage().locator("(//div[text()=' Submit '])[2]");
			click(submitButton, "Submit button");
			getPage().locator("//div[@aria-live='assertive']")
					.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
			getPage().locator("//div[@aria-live='assertive']")
					.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
		} else {
			click(buttonSubmit, "Submit Button");
//			getPage().locator("//div[@aria-live='assertive']")
//					.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//			getPage().locator("//div[@aria-live='assertive']")
//					.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
		}
	}

	public void clickStudioSubmitButton() {
		if (buttonSubmit.count() > 1) {
			Locator submitButton = getPage().locator("(//div[text()=' Submit '])[2]");
			click(submitButton, "Submit button");
		} else {
			click(buttonSubmit, "Submit Button");
		}
	}

	public void clickHomePage() {
		click(homePageIcon, "Home");
	}

	public void clickDropdown(String fieldName, String fieldValue) {
		Locator fieldElement = getPage().locator("//mat-label//div[text()='" + fieldName
				+ "']//ancestor::mat-form-field//following-sibling::div//mat-select[@role='combobox']");
		if (fieldElement.count() == 0) {
			fieldElement = getPage().locator("//mat-label[text()='" + fieldName
					+ "']//ancestor::mat-form-field//following-sibling::div//mat-select[@role='combobox']");
		}
		if (fieldElement.count() == 0) {
			fieldElement = getPage().locator("//mat-label[contains(text(),'" + fieldName
					+ "')]//ancestor::mat-form-field//following-sibling::div//mat-select[@role='combobox']");
		}
		if (fieldElement.count() == 0) {
			fieldElement = getPage().locator("//mat-label//span[contains(text(),'" + fieldName
					+ "')]//ancestor::mat-form-field//following-sibling::div//mat-select[@role='combobox']");
		}
		click(fieldElement, fieldName);
		Locator fieldValueElement = getPage()
				.locator("//div[@role='listbox']//*[contains(text(),'" + fieldValue + "')]");
		if (fieldValueElement.count() == 0) {
			fieldValueElement = getPage()
					.locator("//div[@role='listbox']//mat-option//*[contains(text(),'" + fieldValue + "')]");
		}
		click(fieldValueElement, fieldValue);
	}

	public String getLandingPageText() {
		Locator landingPage = getPage().locator("//span[text()='Overview']");
		return getText(landingPage).trim();
	}

	public String getDocumentPageText() {
		Locator landingPage = getPage().locator("//span[text()='Documents Page']");
		return getText(landingPage).trim();
	}

	public List<String> getSubSectionsText() {
		List<String> subSections = getPage().locator("//div[@role='tab']").allTextContents();
		return subSections;
	}

	public void clickCreateWorkFlow() {
		Locator createWorkFlow = getPage().locator("(//div[contains(text(),'Workflow')])[1]");
		click(createWorkFlow, "Create WorkFlow");
	}

	public String enterWorkFlowName(String workFlowName) {
		Locator workFlowElement = getPage().locator("//input[@formcontrolname='Name']");
		sendText(workFlowElement, workFlowName);
		return workFlowName;
	}

	public String enterWorkFlowDescription(String description) {
		Locator workFlowDescription = getPage().locator("//textarea[@formcontrolname='Description']");
		sendText(workFlowDescription, description);
		return description;
	}

	public void clickSaveWorkFlow() {
		Locator saveElement = getPage().locator("//button//div[contains(text(),'Save Workflow')]");
		click(saveElement, "Save WorkFlow");
	}

//	public void clickWorkFlowName(String workFlowName) {
//		Locator workFlowElement = getPage().locator("//span[contains(text(),'" + workFlowName + "')]");
//		click(workFlowElement, workFlowName);
//	}
	
	public void clickWorkFlowName(String workFlowName) {
	    Locator workFlowElement = getPage().locator("//span[contains(text(),'" + workFlowName + "')]");
	 workFlowElement.click();
	}


	public void clickCreateState() {
		Locator createStateElement = getPage().locator("(//div[contains(text(),'State')])[1]");
		click(createStateElement, "Create State");
	}

	public void enterWorkFlowStateName(String stateName) {
		Locator stateElement = getPage().locator("//input[@formcontrolname='name']");
		sendText(stateElement, stateName);
	}

	public void enterDescription(String description) {
		Locator descriptionElement = getPage().locator("//textarea[@formcontrolname='Description']");
		sendText(descriptionElement, description);
	}

	public void clickSaveButton() {
		Locator saveButton = getPage().locator("//button[@type='submit']");
		click(saveButton, "Save");
		waitForSuccessMessageToAppearAndDissapear();
	}

	public void clickAdvancedSettings() {
		Locator advancedSettingsButton = getPage().locator("//mat-icon[contains(text(),'expand_more')]");
		click(advancedSettingsButton, "Advanced Settings");
	}

	public void clickRadioButton(String buttonName) {
		Locator radioButtonElement = getPage()
				.locator("//label[contains(text(),'" + buttonName + "')]/..//div[@class='mdc-radio']");
		click(radioButtonElement, buttonName);
	}

	public void hoverOnStage(String stageName) {
		Locator editElement = getPage().locator("//p[text()='" + stageName + "']//ancestor::*[@id]");
		hover(editElement, stageName);
		getPage().locator("//p[text()='" + stageName + "']/../..//following-sibling::div//button[@iconname='edit']")
		.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
	}

	public void editWorkFlowStage(String stageName) {
		Locator editElement = getPage()
				.locator("//p[text()='" + stageName + "']/../..//following-sibling::div//button[@iconname='edit']");
		editElement.click();
		extentTest.get().log(Status.INFO, "Clicked on Edit "+stageName);
	}

	public Map<String, String> getWorkFlowDetails() {
		Map<String, String> workFlowDetails = new LinkedHashMap<>();
		List<Locator> stageElements = getPage().locator("//div[contains(@class,'node-name')]")
				.all();
		for (int i = 1; i <= stageElements.size(); i++) {
			workFlowDetails.put(getPage().locator(
					"(//div[@class='mat-mdc-tooltip-trigger node-name flex justify-center node-label-collapsed'])[" + i
							+ "]")
					.textContent().trim(),
					getPage().locator(
							"(//div[@class='mat-mdc-tooltip-trigger node-desc node-desc-collapsed ng-star-inserted'])["
									+ i + "]")
							.textContent().trim());
		}
		return workFlowDetails;
	}

	public void clickEntities() {
		Locator entityElement = getPage().locator("(//span[text()='Entities'])[1]");
		click(entityElement, "Entities");
	}

	public void clickCreateEntity() {
		Locator createElement = getPage().locator("(//div[contains(text(),'Entity')])[1]");
		click(createElement, "Entity");
	}

	public String sendTextEntityName(String entityName) {
		Locator entityNameLocator = getPage().locator("//input[@formcontrolname='EntityName']");
		sendText(entityNameLocator, entityName);
		return entityName;
	}

	public void clearEntityName(String entityName) {
		Locator entityNameLocator = getPage().locator("//input[@formcontrolname='EntityName']");
		clear(entityNameLocator, entityName);
	}

	public void sendEntityDescription(String Description) {
		Locator descriptionElement = getPage().locator("//textarea[@formcontrolname='Description']");
		sendText(descriptionElement, Description);
	}

	public void clickEntitySaveButton() {
		Locator entitySave = getPage().locator("//div[contains(text(),'Save')]");
		click(entitySave, "Save");
	}

//	public void hoverOnWorkFlow(String workFlow) {
//		Locator workFlowName = getPage().locator("//span[contains(text(),'" + workFlow + "')]/../../..");
//		hover(workFlowName, workFlow);
//	}
	
	public void hoverOnWorkFlow(String workFlow) {
	    Locator workFlowName = getPage().locator("//span[contains(text(),'" + workFlow + "')]/../../..");

	    // Wait until the element is visible before hovering
	    workFlowName.waitFor(new Locator.WaitForOptions()
	        .setState(WaitForSelectorState.VISIBLE)
	        .setTimeout(60000)); // increase timeout to 60s for reliability
	    hover(workFlowName, workFlow);
	}

	public void clickThreeDots(String workFlow) {
		Locator threeDotIcon = getPage().locator(
				"//span[contains(text(),'" + workFlow + "')]//parent::span//button[@iconname='three-dots-vertical']");
		click(threeDotIcon, workFlow);
	}

	public void clickValidateButton() {
		Locator validateButton = getPage().locator("//div[text()='Validate']");
		click(validateButton, "Validate");
	}

//	public void clickDeleteButton() {
//		Locator deleteButton = getPage().locator("//button//div[contains(text(),'Delete')]");
//		deleteButton.click();
//		extentTest.get().log(Status.INFO, "Clikcked on Delete Workflow");
//	}
//	
	public void clickDeleteButton() {
	    Locator deleteButton = getPage().locator("//button//div[normalize-space(text())='Delete']");
	    deleteButton.waitFor(new Locator.WaitForOptions()
	            .setTimeout(10000)
	            .setState(WaitForSelectorState.VISIBLE));
	    deleteButton.scrollIntoViewIfNeeded();
	    deleteButton.click();
	    extentTest.get().log(Status.INFO, "Clicked Delete Workflow button");
	}

	public String getToastMessage() {
		Locator toastMessageElement = getPage().locator("//div[@class='mat-mdc-snack-bar-label mdc-snackbar__label']");
		return getText(toastMessageElement).trim();
	}

	public String getAlertMessage() {
		Locator alertElement = getPage().locator("//h1[contains(@id,'mat-mdc-dialog-title')]");
		return getText(alertElement);
	}

	public String getAlertVerbiage() {
		Locator verbiageElement = getPage()
				.locator("(//div[contains(@class,'mat-mdc-dialog-content mdc-dialog__content')]//p)[1]");
		return getText(verbiageElement);
	}

	public void clickConfirmButton() {
		Locator confirmButton = getPage().locator("//button[@id='proceed']");
		click(confirmButton, "Confirm");
	}

	public void clickDataSource() {
		Locator dataSource = getPage().locator("(//div[contains(text(),'Data Source')])[1]");
		click(dataSource, "Data Source");
	}

	public void hoverOnEntity(String entityName) {
		Locator entityElement = getPage()
				.locator("//span[text()='" + entityName + "']//parent::span[contains(@class,'header truncate')]");
		hover(entityElement, entityName);
	}

	public void clickOnEntity(String entityName) {
		Locator entityElement = getPage()
				.locator("//span[text()='" + entityName + "']//parent::span[contains(@class,'header truncate')]");
		click(entityElement, entityName);
	}

	public void clickEditButton() {
		Locator editButton = getPage().locator("(//div[contains(text(),'Edit')])[2]");
		click(editButton, "Edit");
	}

	public String getEntityName() {
		Locator entityElement = getPage()
				.locator("//div[contains(text(),'Entity Name')]//parent::div//following-sibling::div[text()]");
		return getText(entityElement).trim();
	}

	public void clickUploadFile() {
		Locator uploadElement = getPage().locator("//div[(text()=' Upload File ')]");
		click(uploadElement, "Upload File");
	}

	public void uploadFile(Path filePath, String fileName) {
		Locator inputFile = getPage().locator("(//input[@type='file'])[1]");
		uploadNewFile(inputFile, filePath, fileName);
	}

	public void clickNextButton() {
		Locator nextButton = getPage().locator("(//div[contains(text(),'Next')])[1]");
		click(nextButton, "Next Button");
	}

	public void setPriority(String priority) {
		Locator priorityElement = getPage().locator("//button[contains(text(),'" + priority + "')]");
		click(priorityElement, priority);
	}

	public void clickEntityDropdown(String fieldName, String fieldValue) {
		Locator fieldElement = getPage().locator("//mat-label[contains(text(),'" + fieldName
				+ "')]//ancestor::mat-form-field//following-sibling::div//mat-select[@role='combobox']");
		click(fieldElement, fieldName);
		Locator searchElement = getPage().locator("//input[@placeholder='Search']");
		sendText(searchElement, fieldValue);
		Locator fieldValueElement = getPage().locator("//span[contains(text(),'" + fieldValue + "')]");
		click(fieldValueElement, fieldValue);
	}

	public void clickAddSource() {
		Locator element = getPage().locator("//div[contains(text(),'Add Source')]");
		click(element, "Add Source");
//		getPage().locator("//div[@aria-live='assertive']")
//				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//		getPage().locator("//div[@aria-live='assertive']")
//				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
	}

	public String getAttributeTitle() {
		Locator statusElement = getPage().locator("(//img[@title])[1]");
		return getAttribute(statusElement, "title");
	}

	public void clickCreatedEntity(String entityName) {
		Locator arrowDownButton = getPage().locator("//button[@aria-haspopup='menu']//icon[@name='arrowDown']");
		Locator entityNameElement = getPage().locator("//a[contains(text(),'" + entityName + "')]");
		if(entityNameElement.isVisible()){
			click(entityNameElement, entityName);
		}
		else{
			click(arrowDownButton,"ArrowDown");
			click(entityNameElement, entityName);
			wasteClickInBetweenTheScreen();
		}
	}

	public String getDocumentName(String documentName) {
		Locator documentElement = getPage().locator("//h4[@title='" + documentName + "']");
		return getText(documentElement);
	}

//	public void clickRefreshIcon() {
//		Locator refreshElement = getPage().locator("//button[@iconname='new-refresh']");
//		click(refreshElement, "Refresh Icon");
//	}
	
	public void clickRefreshIcon() {
	    Locator refreshElement = getPage().locator("//button[@iconname='new-refresh']");
	    refreshElement.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(20000));
	    refreshElement.click(new Locator.ClickOptions().setForce(true));
	}

	public void clickCreatedEntityByDropdown(String entityName) {
		Locator overviewElement = getPage().locator("//icon[@name='arrowDown']");
		click(overviewElement, "Arrow Down");
		Locator entityNameElement = getPage().locator("//a[contains(text(),'" + entityName + "')]");
		click(entityNameElement, entityName);
	}

	public boolean workFlowAvailability(String workflowName) {
		boolean res = false;
		scrollDownTillEnd("//span[@class='name truncate']");
		List<Locator> allWorkflowElements = getPage().locator("//span[@class='name truncate']").all();
		List<String> allWorkflowNames = new ArrayList<String>();
		for (Locator eachWorkflowElement : allWorkflowElements) {
			allWorkflowNames.add(eachWorkflowElement.textContent().trim());
		}
		if (allWorkflowNames.contains(workflowName)) {
			res = true;
		}
		return res;
	}

	public void scrollDownTillEnd(String locator) {
		List<Locator> allElements = getPage().locator(locator).all();
		int currentCount = 0;
		int totalCount = allElements.size();

		while (currentCount < totalCount) {
			currentCount = totalCount;
			getPage().locator(locator).last().scrollIntoViewIfNeeded();
			getPage().locator(locator).last().click();
			getPage().mouse().wheel(0, -500);
			getPage().mouse().wheel(0, 600);
			allElements = getPage().locator(locator).all();
			totalCount = allElements.size();
		}
	}

	public void clickOnWorkflowConfiDownArrowUnderActionPane() {
		getPage().locator("//*[@role='img' and contains(text(),'expand_more')]").click();
		extentTest.get().log(Status.INFO, "Clicked on Expand Workflow Configuration in Action Pane");
	}

	public void deleteAllConfiguredWorkflow() {
		// button[@iconname='delete']
		List<Locator> allWorkflowElements = getPage().locator("//button[@iconname='delete']").all();
		if (allWorkflowElements.size() > 0) {
			for (Locator eachWorkflowDelete : allWorkflowElements) {
				eachWorkflowDelete.click();
			}
		}
	}

	public void clickOnAddWorkFlowInActionPane() {
		getPage().locator("//*[@iconname='add' and contains(@mattooltip,'workflow')]").click();
		extentTest.get().log(Status.INFO, "Clicked on Add Workflow in Action Pane");
	}

	public void selectWorkflowInActionPane(String workflowName) {
		getPage().locator("//*[@placeholder='Select the Workflow']").click();
		// mat-option//span[contains(text(),'Sample')]
		getPage().locator("//mat-option//span[contains(text(),'" + workflowName + "')]").click();
		extentTest.get().log(Status.INFO, "Selected " + workflowName + " from the workflow dropdown list");
		selectAllStatesInActionPane();
		getPage().locator("(//*[@mattooltip='Submit']//div[contains(text(),'Submit')])[2]").click();
		extentTest.get().log(Status.INFO, workflowName + " Workflow Configuration is done");
	}

	public void selectAllStatesInActionPane() {
		getPage().locator("//*[@placeholder='Select states']").click();
		List<Locator> allOptions = getPage().locator("//mat-option").all();
		for (Locator eachOption : allOptions) {
			eachOption.click();
		}
		wasteDropdownClick("(//mat-option)[1]");
		extentTest.get().log(Status.INFO, "Selected all States from the States dropdown list");
	}

	public void clickOnPiper() {
		getPage().locator("//*[@tooltip='Piper']").click();
		getPage().locator("//*[contains(@placeholder,'Ask Piper anything')]")
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		extentTest.get().log(Status.INFO, "Clicked on Piper");
	}

	public void clickOnNewChatInPiper() {
		getPage()
				.locator(
						"//*[@iconname='menu-panel']/following-sibling::button[contains(@mattooltip,'Start new chat')]")
				.click();
		getPage().locator("//*[contains(text(),'what should we dive into today')]")
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		extentTest.get().log(Status.INFO, "Clicked on New Chat in Piper");
	}

	public void sendQuestionInPiper(String question) {
		getPage().locator("//*[contains(@placeholder,'Ask Piper anything')]").fill(question);
		getPage().keyboard().press("Enter");
		// img[contains(@class,'server-typing')]
		getPage().locator("//img[contains(@class,'server-typing')]")
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
		extentTest.get().log(Status.INFO, "Sent " + question + " as Question for Piper");
	}

	public String capturePiperResponse() {
		return getPage().locator("//app-markdown//div[contains(@class,'markdown_output')]").textContent().trim();
	}

	public void clickCreateFunction() {
		Locator createFunctionButton = getPage().locator("//button//div[contains(text(),'Function')]");
		click(createFunctionButton, "Create Function");
	}

	public String enterFunctionName(String libraryName) {
		Locator fieldElement = getPage().locator("//input[@name='LibraryName']");
		sendText(fieldElement, libraryName);
		return libraryName;
	}

	public void clickFunctionType(String function) {
		Locator functionType = getPage().locator("//h3[text()='" + function + "']");
		click(functionType, function);
	}

	public String enterCode(String code) {
		Locator codeElement = getPage().locator("//textarea[@role='textbox']");
		sendText(codeElement, code);
		return code;
	}

	public void enterPropertyName(String propertyName) {
		Locator propertyElement = getPage().locator("(//input[@placeholder='Property Name'])[1]");
		sendText(propertyElement, propertyName);
	}

	public void clickAddFunction() {
		Locator functionElement = getPage().locator("//button//div[contains(text(),'Add Function')]");
		click(functionElement, "Add Function");
	}

	public void clickSearchButton() {
		Locator searchElement = getPage().locator("//icon[@name='search']");
		click(searchElement, "Search");
	}
	
	
	public void enterSearchValue(String searchValue) {
		Locator seacrhElement = getPage().locator("//input[@placeholder='Enter Value']");
		sendText(seacrhElement, searchValue);
	}
	
	
//public void enterSearchValue(String searchValue) {
//	 try {
//	Thread.sleep(5000);
//		// Corrected variable name
//	    Locator searchElement = getPage().locator("//input[@placeholder='Enter Value']");
//
//	        // Wait until the input is visible (increased timeout)
//	        searchElement.waitFor(new Locator.WaitForOptions()
//	                .setState(WaitForSelectorState.VISIBLE)
//	                .setTimeout(120000)); // 120 seconds timeout
//
//	        // Scroll into view in case it's offscreen
//	        searchElement.scrollIntoViewIfNeeded();
//
//	        // Clear any existing text
//	        searchElement.fill("");
//
//	        // Enter the search value
//	        searchElement.fill(searchValue);
//
//	        System.out.println("Search value '" + searchValue + "' entered successfully.");
//	    } catch (Exception e) {
//	        System.err.println("Failed to enter search value: " + e.getMessage());
//	       
//	    }
//	}

	
	public String getFunctionName() {
		Locator functionElement = getPage()
				.locator("//div[contains(text(),'Function Name')]//parent::div//following-sibling::div");
		return getText(functionElement).trim();
	}

	public void hoverOnFunction(String functionName) {
		Locator functionElement = getPage().locator("//span[contains(text(),'" + functionName + "')]");
		hover(functionElement, functionName);
	}

	public void clickOnFunction(String functionName) {
		Locator functionElement = getPage().locator("//span[contains(text(),'" + functionName + "')]");
		hover(functionElement, functionName);
	}

	public String getCode() {
		Locator codeElement = getPage().locator("//textarea[@role='textbox']");
		return getInputValue(codeElement).trim();
	}

	public void clickConvertToTool() {
		Locator converterElement = getPage().locator("//button//div[contains(text(),'Convert To Tool')]");
		click(converterElement, "Convert to Tool");
	}

	public List<String> getFunctionNames() {
		Locator functionElement = getPage().locator("//span[@class='name truncate']");
		return functionElement.allTextContents();
	}

	public void clickAddButton() {
		Locator addButton = getPage().locator("(//div[contains(text(),'Add')])[1]");
		click(addButton, "Add");
	}

	public void clickAddTool() {
		Locator addButton = getPage().locator("(//button//div[contains(text(),'Tool')])[1]");
		click(addButton, "Add Tool");
	}

	public void sendFunctionName(String functionName) {
		Locator sendArea = getPage().locator("//input[@formcontrolname='searchQuery']");
		sendText(sendArea, functionName);
	}

	public void clickCard(String cardName) {
		//mat-card//h3[contains(text(),'Anita Jenkins')]//ancestor::mat-card//div[contains(text(),'Click to import')]
		Locator cardElement = getPage().locator("//mat-card//h3[contains(text(),'"+cardName+"')]//ancestor::mat-card//div[contains(text(),'Click to import')]");
		cardElement.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		cardElement.hover();
		cardElement.click();
	}

	public void clickCloseIcon() {
		Locator closeIcon = getPage().locator("//div[contains(text(),'Add Tool')]/following-sibling::button");
		click(closeIcon, "Close");
	}

	public void closePiper() {
		getPage().locator("//*[@iconname='close']").click();
		extentTest.get().log(Status.INFO, "Closed Piper");
	}

	public void scrollDownToLastElement(String locator) {
		// span[contains(@class,'label-name')]
		List<Locator> allElements = getPage().locator(locator).all();
		int currentCount = 0;
		int totalCount = allElements.size();

		while (currentCount < totalCount) {
			currentCount = totalCount;
			getPage().locator(locator).last().scrollIntoViewIfNeeded();
			getPage().locator(locator).last().click();
			getPage().mouse().wheel(0, -500);
			getPage().mouse().wheel(0, 600);
			allElements = getPage().locator(locator).all();
			totalCount = allElements.size();
		}
	}
	

	public String getIngestionStatusOfDocument(String filename) {
//		String[] parts = filename.split("_");
//		String shortName = null;
//		if (parts.length >= 3) {
//			shortName = String.join("_", parts[0], parts[1], parts[2]);
//		}else {
//			
//		}
		
		String trimmedName = filename.length() > 15 ? filename.substring(0, 15) : filename;
		//a[starts-with(text(),' ArghyaDasgup')]/ancestor::td/following-sibling::td[contains(@class,'column-Status')]/img
		String status = getPage().locator("//*[starts-with(text(),' "+trimmedName+"') or contains(text(),'Job')]/ancestor::td/following-sibling::td[contains(@class,'column-Status')]/img")
				.first().getAttribute("title").trim();
		return status;
    }

	public void clickWorkFlowConfigurationDropdown(){
		Locator configDropdown = getPage().locator("//div[contains(text(),'Workflow Configuration')]//div//mat-icon");
		click(configDropdown,"ConfigurationDropdown");
	}

	public void clickAddWorkFlowButton(){
		Locator addButton = getPage().locator("//div[contains(text(),'Workflow Configuration')]//button[@iconname='add']");
		click(addButton,"add");
	}

	public void clickCheckBoxDropdown(String fieldName){
		Locator checkBoxDropdown = getPage().locator("//mat-label[text()='"+fieldName+"']//ancestor::mat-form-field//following-sibling::div//mat-select[@role='combobox']");
		click(checkBoxDropdown,fieldName);
		Locator checkBoxElements = getPage().locator("//mat-option//mat-pseudo-checkbox");
		for(int i=0;i<checkBoxElements.count();i++){
			click(checkBoxElements.nth(i),checkBoxElements.nth(i).textContent());
		}
	}
	
	public void waitForSelectorForDocument(String documentName, int timeoutInSeconds) {
	    // Assuming each document row has a selector containing the document name
	    String docSelector = String.format("//tr[td/text()='%s']//td[contains(@class,'status')]", documentName);

	    try {
	        getPage().locator(docSelector).waitFor(
	            new Locator.WaitForOptions().setTimeout(timeoutInSeconds * 1000).setState(WaitForSelectorState.VISIBLE)
	        );
	    } catch (PlaywrightException e) {
	        System.out.println("Warning: Document status element not found within " + timeoutInSeconds + " seconds for " + documentName);
	    }
	}
	
	public void waitForDocumentStatus(String documentName, int timeoutInSeconds) {
	    String selector = "//tr[td[text()='" + documentName + "']]//td[@class='status']";
	    try {
	        getPage().locator(selector).waitFor(new WaitForOptions()
	            .setTimeout(timeoutInSeconds * 1000)
	            .setState(WaitForSelectorState.VISIBLE)); // Use enum, not string
	        System.out.println("Document status element is now visible for: " + documentName);
	    } catch (Exception e) {
	        System.out.println("WARNING: Document status element not found within " 
	            + timeoutInSeconds + " seconds for " + documentName);
	    }
	}
	
	public void clickStudioPage() {
	    Locator studioButton = getPage().locator("//icon[@name='setting/new-studio']/../..");
	    studioButton.waitFor(new Locator.WaitForOptions()
	            .setState(WaitForSelectorState.VISIBLE)
	            .setTimeout(30000));
	    getPage().waitForTimeout(500);
	    studioButton.dblclick();
	    extentTest.get().log(Status.INFO, "Clicked on Studio Button Successfully");
	}
}