package org.dewa.framework.objectrepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dewa.framework.utils.BaseClass;

import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

public class StudioPage extends BaseClass {

	public StudioPage(Page page) {
		super(page);
		// TODO Auto-generated constructor stub
	}

	public void clickOnConfigUnderProjectOverview() {
		getPage().locator("//button[@mattooltip='Version Control']").click();
		extentTest.get().log(Status.INFO, "Clicked on Configure Button");
	}

	public void clickOnCommit(String commitMsg) {
		getPage().locator("//button[@iconname='commit']").click();
		getPage().waitForTimeout(2000);
		extentTest.get().log(Status.INFO, "Clicked on Commit Button");
		getPage().locator("//textarea[@id='commitMessage']").click();
		getPage().locator("//textarea[@id='commitMessage']").fill(commitMsg);
		extentTest.get().log(Status.INFO, "Commit Message : <b>" + commitMsg + "</b>");
		getPage().locator("//button//div[contains(text(),'Commit')]").click();
		getPage().locator("//div[@class='mdc-circular-progress__spinner-layer']")
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
		getPage().waitForLoadState(LoadState.LOAD);
		waitForSuccessMessageToAppearAndDissapear();
//		getPage().locator("//div[@aria-live='assertive']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//		getPage().locator("//div[@aria-live='assertive']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
		extentTest.get().log(Status.INFO, "Commit Successfull");
	}

	public void downloadBmxFile() {
		clickOnConfigUnderProjectOverview();
		getPage().locator("//button//div[text()='Quick Download']").click();
		waitForSuccessMessageToAppearAndDissapear();
//		getPage().locator("//div[@aria-live='assertive']")
//				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
//		getPage().locator("//div[@aria-live='assertive']")
//				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
		extentTest.get().log(Status.INFO, "BMX file downloaded successfully");
	}

	public void clickOnUploadBmxFile() {
		getPage().locator("//button[@iconname='upload']").click();
		extentTest.get().log(Status.INFO, "Cliked on Upload bmx File Button");

	}

	public void clickDeleteProject(String description) {
		getPage().locator("//button[@mattooltip='Delete Project']").click();
		getPage().locator("//textarea[@name='deletionreason']").fill(description);
		getPage().locator("//button//div[contains(text(),'Confirm')]").click();
		waitForSuccessMessageToAppearAndDissapear();
		getPage().reload();
	}

	public String getProjectName() {
		String projectName = getPage().locator("//input[@name='FinonName']").inputValue().trim();
		return projectName;
	}

	public String getDescriptionName() {
		String description = getPage().locator("//textarea[@placeholder='Enter Project Description']").inputValue()
				.trim();
		return description;
	}

	public String checkToggleSelection(String toggleName) {
		// div[contains(text(),'Set Working
		// Hours')]/ancestor::div[contains(@class,'toggle')]//button
		Locator toggleElement = getPage().locator(
				"//div[contains(text(),'" + toggleName + "')]/ancestor::div[contains(@class,'toggle')]//button");
		return toggleElement.getAttribute("aria-checked");
	}

	public Set<String> getAllToggleText() {
		getPage().waitForTimeout(2000);
//		 getPage().locator("(//button[@role='switch' and @type='button']/ancestor::div[contains(@class,'toggle-item')]//div[text()])[1]").waitFor(new
//		 Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		List<Locator> allToggleElements = getPage().locator(
				"//button[@role='switch' and @type='button']/ancestor::div[contains(@class,'toggle-item')]//div[text()]")
				.all();
		Set<String> allToggleText = new HashSet<String>();
		for (Locator eachToggleElement : allToggleElements) {
			allToggleText.add(eachToggleElement.textContent().trim());
		}

		return allToggleText;
	}

	public Map<String, String> getAllToggleTextAndJsonValue() {
		// getPage().locator("(//button[@role='switch' and
		// @type='button']/ancestor::div[contains(@class,'toggle-item')]//div[text()])[1]").waitFor(new
		// Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		// button[@role='switch' and
		// @type='button']/ancestor::div[contains(@class,'toggle-item')]//div[text()]
		List<Locator> allToggleElements = getPage().locator(
				"//button[@role='switch' and @type='button']/ancestor::div[contains(@class,'toggle-item')]//div[text()]")
				.all();
		// System.out.println("Total toggle elements "+allToggleElements.size());
		Set<String> allToggleText = new HashSet<String>();
		for (Locator eachToggleElement : allToggleElements) {
			allToggleText.add(eachToggleElement.textContent().trim());
		}
		// System.out.println("All Toggle Text Count "+allToggleText.size());

		// div[contains(text(),'Dynamic
		// Priority')]/ancestor::div[contains(@class,'toggle-item')]//button
		Map<String, String> toggle_JsonValues = new HashMap<String, String>();
		for (String eachToggleText : allToggleText) {
			String nameAttribute = getPage().locator("//div[contains(text(),'" + eachToggleText
					+ "')]/ancestor::div[contains(@class,'toggle-item')]//button").getAttribute("name");

			if (nameAttribute == null) {
				nameAttribute = eachToggleText.replace(" ", "").replace("-", "");
			}

			toggle_JsonValues.put(eachToggleText, nameAttribute);
		}
//		System.out.println("Toggle-Json Map Value Count "+toggle_JsonValues.size());
//		for (Map.Entry<String, String> entry : toggle_JsonValues.entrySet()) {
//		    System.out.println(entry.getKey() + " : " + entry.getValue());
//		}
		return toggle_JsonValues;
	}

	public void clickOnModules(String moduleName) {
		// mat-panel-title[contains(text(),'Automation')]
		getPage().locator("//mat-panel-title[contains(text(),'" + moduleName + "')]").click();
		extentTest.get().log(Status.INFO, "Cliked on " + moduleName);
	}

	public void clickOnSubModules(String moduleName, String subModuleName) {
		// mat-panel-title[contains(text(),'Chat')]/ancestor::mat-expansion-panel//span[contains(text(),'Audit')]
		getPage()
				.locator("//mat-panel-title[contains(text(),'" + moduleName
						+ "')]/ancestor::mat-expansion-panel//span[contains(text(),'" + subModuleName + "')]")
				.first().click();
		extentTest.get().log(Status.INFO, "Clicked on " + subModuleName);
	}

	public List<String> getAllNavigationMenusUnderAppearence() {
		List<Locator> allElements = getPage().locator("//span[@class='mdc-tab__text-label']").all();
		List<String> allMenus = new ArrayList<String>();
		for (Locator eachElement : allElements) {
			allMenus.add(eachElement.textContent().trim());
		}
		return allMenus;
	}

	public void clickOnNavigationMenuUnderModules(String navigationMenuName) {
		// span[@class='mdc-tab__text-label']//span[contains(text(),'Documents Page')]
		getPage().locator("//span[@class='mdc-tab__text-label']//span[contains(text(),'" + navigationMenuName + "')]")
				.click();
		extentTest.get().log(Status.INFO, "Clicked on " + navigationMenuName);
	}

	public void clickOnNavigationMenuUnderProject(String navigationMenuName) {
		// span[@class='mdc-tab__text-label']//div[contains(text(),'Landing Page')]
		getPage().locator("//span[@class='mdc-tab__text-label']//div[contains(text(),'"+navigationMenuName+"')]")
				.click();
		extentTest.get().log(Status.INFO, "Clicked on " + navigationMenuName);
	}
	
	public void clickOnNavigationMenuUnderChat(String navigationMenuName) {
		//button//div[contains(text(),'Default Questions')]
		getPage().locator("//button//div[contains(text(),'"+navigationMenuName+"')]")
		.click();
		extentTest.get().log(Status.INFO, "Clicked on " + navigationMenuName);
	}

	public void clickOnSeacrh_General_NavigationMenus(String menuName) {
		// button//div[contains(text(),'Similarity')]
		getPage().locator("//button//div[contains(text(),'" + menuName + "')]").click();
		extentTest.get().log(Status.INFO, "Clicked on " + menuName);
	}

	public List<String> getAllDropdownSelectedOptions() {
		// mat-chip-option//button//span[text()]
		List<Locator> allElements = getPage().locator("//mat-chip-option//button//span[text()]").all();
		List<String> allOptions = new ArrayList<String>();
		for (Locator element : allElements) {
			String eachText = element.textContent().trim();
			allOptions.add(eachText);
		}
		return allOptions;
	}

	public String[] getProjectOverviewPageTextBoxApiNamesAndValues(String textBoxName) {
		// div[contains(text(),'Per document unit
		// configuration')]/ancestor::div[contains(@class,'toggle')]//input
		Locator element = getPage().locator(
				"//div[contains(text(),'" + textBoxName + "')]/ancestor::div[contains(@class,'toggle')]//input");
		String[] nameAndValue = new String[2];
		nameAndValue[0] = element.getAttribute("name");
		nameAndValue[1] = element.inputValue();
		return nameAndValue;
	}

	public List<String> getProjectOverViewTextBox_Texts() {
		// input[contains(@id,'mat-input')]/ancestor::div[contains(@class,'toggle')]//div[text()]
		List<Locator> allTextElements = getPage()
				.locator("//input[contains(@id,'mat-input')]/ancestor::div[contains(@class,'toggle')]//div[text()]")
				.all();
		List<String> allText = new ArrayList<String>();
		for (Locator eachElement : allTextElements) {
			allText.add(eachElement.textContent().trim());
		}
		return allText;
	}

	public List<String> getAllDropdownNames() {
		// mat-select/ancestor::mat-form-field[@appearance='outline']//mat-label
		List<Locator> allDropdownElements = getPage()
				.locator("//mat-select/ancestor::mat-form-field[@appearance='outline']//mat-label").all();
		List<String> allDropdownNames = new ArrayList<String>();
		for (Locator eachDropdown : allDropdownElements) {
			allDropdownNames.add(eachDropdown.textContent().trim());
		}
		return allDropdownNames;
	}

	public String[] getDropdownApiNameAndSelectedOption(String dropdownName) {
		// mat-label[contains(text(),'Documents
		// View')]/ancestor::mat-form-field//mat-select
		String xpath = "(//mat-label[contains(text(),'" + dropdownName + "')]/ancestor::mat-form-field//mat-select | "
				+ "//div[contains(text(),'" + dropdownName + "')]/ancestor::mat-form-field//mat-select)";
		Locator dropdown = getPage().locator(xpath);
		String[] apiNameAndValue = new String[2];
		apiNameAndValue[0] = dropdown.getAttribute("name");
		apiNameAndValue[1] = dropdown.textContent().trim();
		return apiNameAndValue;
	}

	public List<String> getDropdownMultipleSelectedOptions(String dropdownName) {
		// mat-label[contains(text(),'Document
		// Icons')]/ancestor::mat-form-field//mat-select//button
		List<Locator> allElements = getPage().locator(
				"//mat-label[contains(text(),'" + dropdownName + "')]/ancestor::mat-form-field//mat-select//button")
				.all();
		List<String> allSelectedOptions = new ArrayList<String>();
		for (Locator eachElement : allElements) {
			allSelectedOptions.add(eachElement.textContent().trim().replace(".", ""));
		}
		return allSelectedOptions;
	}

	public List<String> getAllEntityNamesInDataMdel() {
		// span[contains(@class,'child-name')]
		getPage().locator("(//span[contains(@class,'child-name')])[1]")
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		List<Locator> allElements = getPage().locator("//span[contains(@class,'child-name')]").all();
		List<String> allEntityNames = new ArrayList<String>();
		for (Locator eachElement : allElements) {
			allEntityNames.add(eachElement.textContent().trim());
		}
		return allEntityNames;
	}

	public List<String> getAllTaxonomyNames() {
		// span[@class='name truncate']
		List<Locator> allElements = getPage().locator("//span[@class='name truncate']").all();
		int currentCount = 0;
		int totalCount = allElements.size();

		while (currentCount < totalCount) {
			currentCount = totalCount;
			getPage().locator("//span[@class='name truncate']").last().scrollIntoViewIfNeeded();
			getPage().locator("//span[@class='name truncate']").last().click();
			getPage().mouse().wheel(0, -500);
			getPage().mouse().wheel(0, 600);
			allElements = getPage().locator("//span[@class='name truncate']").all();
			totalCount = allElements.size();
		}
		List<String> allTaxonomyNames = new ArrayList<String>();
		for (Locator eachElement : allElements) {
			allTaxonomyNames.add(eachElement.textContent().trim());
		}
		return allTaxonomyNames;
	}

	public List<String> getAllAgentNames() {
		// span[contains(@class,'child-name truncate')]
		List<Locator> allElements = getPage().locator("//span[contains(@class,'child-name truncate')]").all();
		int currentCount = 0;
		int totalCount = allElements.size();

		while (currentCount < totalCount) {
			currentCount = totalCount;
			getPage().locator("//span[contains(@class,'child-name truncate')]").last().scrollIntoViewIfNeeded();
			getPage().locator("//span[contains(@class,'child-name truncate')]").last().click();
			getPage().mouse().wheel(0, -500);
			getPage().mouse().wheel(0, 600);
			allElements = getPage().locator("//span[contains(@class,'child-name truncate')]").all();
			totalCount = allElements.size();
		}
		List<String> allAgentsNames = new ArrayList<String>();
		for (Locator eachElement : allElements) {
			allAgentsNames.add(eachElement.textContent().trim());
		}
		return allAgentsNames;
	}

	public void clickOnTaxonomy(String taxonomyName) {
		// span[@class='name truncate' and text()=' Sentence Identification ']
		// span[@class='name truncate' and contains(text(),'Sentence Identification')]

		// span[@class='name truncate' and normalize-space(text())='test2']
//		String xpath = "//span[@class='name truncate' and text()=' "+taxonomyName+" '] | " +
//				"//span[@class='name truncate' and contains(text(),'"+taxonomyName+"')]";
		String xpath = "//span[@class='name truncate' and normalize-space(text())='" + taxonomyName + "']";
		getPage().locator(xpath).first().click();
		extentTest.get().log(Status.INFO, "Cliked on " + taxonomyName + " Taxonomy");
	}

	public List<String> getAllLabelNames() {
		scrollDownToLabel("//span[contains(@class,'label-name')]");
		// span[contains(@class,'label-name')]
		List<Locator> allElements = getPage().locator("//span[contains(@class,'label-name')]").all();
		List<String> allLabelNames = new ArrayList<String>();
		for (Locator eachElement : allElements) {
			allLabelNames.add(eachElement.textContent().trim());
		}
		return allLabelNames;
	}

	public void scrollDownToLabel(String locator) {
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

//		//div[@class='label-item']
//		if(getPage().locator("//div[@class='label-item']").first().isVisible()) {
//			Locator element = getPage().locator("//div[@class='label-item']").last();
//			element.hover();
//			getPage().mouse().wheel(0, 1000);
//			getPage().waitForTimeout(300);
//			getPage().mouse().wheel(0, 1000);
//			getPage().waitForTimeout(300);
//			getPage().mouse().wheel(0, 1000);
//		}
	}

	public List<String> getAllViewNamesUnderSearchViews() {
		// td[contains(@class,'cdk-column-Name mat-column-Name')]
		List<Locator> allViewNameLocators = getPage()
				.locator("//td[contains(@class,'cdk-column-Name mat-column-Name')]").all();
		List<String> allViewNames = new ArrayList<String>();
		for (Locator viewName : allViewNameLocators) {
			allViewNames.add(viewName.textContent().trim());
		}
		return allViewNames;
	}

	public List<String> getAllDescriptionNamesUnderSearchViews() {
		// td[contains(@class,'cdk-column-Description mat-column-Description')]
		List<Locator> allLocators = getPage()
				.locator("//td[contains(@class,'cdk-column-Description mat-column-Description')]").all();
		List<String> allNames = new ArrayList<String>();
		for (Locator viewName : allLocators) {
			allNames.add(viewName.textContent().trim().replace(".", ""));
		}
		return allNames;
	}

	public List<String> getAllQuestionsFromChatDefaultQuestions() {
		// input[@formcontrolname='Question']
		List<Locator> allLocators = getPage().locator("//input[@formcontrolname='Question']").all();
		List<String> allNames = new ArrayList<String>();
		for (Locator viewName : allLocators) {
			allNames.add(viewName.inputValue().trim());
		}
		return allNames;
	}

	public List<String> getAllWidgetNamesUnderIntellegenceDashbaord() {
		// div[contains(@class,'flex items-center justify-between')]//span
		List<Locator> allLocators = getPage()
				.locator("//div[contains(@class,'flex items-center justify-between')]//span").all();
		List<String> allNames = new ArrayList<String>();
		for (Locator viewName : allLocators) {
			allNames.add(viewName.textContent().trim());
		}
		return allNames;
	}

	public List<String> getAllAiModelNames() {
		// span[contains(@class,'child-name truncate')]
		List<Locator> allLocators = getPage().locator("//span[contains(@class,'child-name truncate')]").all();
		List<String> allNames = new ArrayList<String>();
		for (Locator viewName : allLocators) {
			allNames.add(viewName.textContent().trim());
		}
		return allNames;
	}

	public String getAllModelDescriptionUnderLLMs() {
		// div[contains(text(),'Model Description')]/../following-sibling::div
		Locator element = getPage().locator("//div[contains(text(),'Model  Description')]/../following-sibling::div");
		return element.textContent().trim();
	}

	public String getPromptText() {
		// div[contains(text(),'Prompt Text')]/following-sibling::div
		return getPage().locator("//div[contains(text(),'Prompt  Text')]/following-sibling::div").textContent().trim();
	}

	public void clickOnAiModel(String modelName) {
		// span[contains(@class,'child-name truncate') and contains(text(),'Voice
		// Copilot R1')]
		getPage().locator("//span[contains(@class,'child-name truncate') and contains(text(),'" + modelName + "')]")
				.first().click();
		extentTest.get().log(Status.INFO, "Clicked on " + modelName);
	}

	public List<String> getAllDataSheetNames() {
		// button//div[contains(@class,'font-bold text-sm text-dark')]
		getPage().locator("(//button//div[contains(@class,'font-bold text-sm text-dark')])[1]")
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

		List<Locator> allLocators = getPage().locator("//button//div[contains(@class,'font-bold text-sm text-dark')]")
				.all();
		List<String> allNames = new ArrayList<String>();
		for (Locator sheetName : allLocators) {
			allNames.add(sheetName.textContent().trim());
		}
		return allNames;
	}

	public List<String> getAllSelectedNavigationMenus() {
		// div[contains(@class,'flex menuHover items-center')]//p//div
		List<Locator> allLocators = getPage().locator("//div[contains(@class,'flex menuHover items-center')]//p//div")
				.all();
		List<String> allNames = new ArrayList<String>();
		for (Locator sheetName : allLocators) {
			allNames.add(sheetName.textContent().trim());
		}
		return allNames;
	}

}
