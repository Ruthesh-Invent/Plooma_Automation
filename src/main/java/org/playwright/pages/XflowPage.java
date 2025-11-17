package org.playwright.pages;

import org.framework.playwright.annotations.FindBy;
import org.framework.playwright.utils.BaseClass;
import org.testng.Assert;
import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class XflowPage extends BaseClass {

	public XflowPage(Page page) {
		super(page);
	}

	@FindBy(xpath = "//button[@class='primary ng-star-inserted']")
	private Locator CrtXflowbtn;

	@FindBy(xpath = "//input[@formcontrolname='Name']")
	private Locator xflowName;

	@FindBy(xpath = "//div[@class='mat-mdc-tooltip-trigger flex items-center gap-2'][normalize-space()='Save XFlow']")
	private Locator Save;

	@FindBy(xpath = "//body//app-root//app-workflow-activity-graph//div[5]")
	private Locator mailopertaor;

	@FindBy(xpath = "//div[@class='ng-star-inserted']//app-workflow-activity-graph")
	private Locator Workgraph;

	@FindBy(xpath = "\r\n"
			+ "//*[name()='div' and contains(@class,'graph-node')]//*[name()='div' and contains(@class,'absolute b')]")
	private Locator Hoverlay;

	@FindBy(xpath = "//*[name()='button' and contains(@title,'Edit Activ')]")
	private Locator Editicon;

	@FindBy(xpath = "//input[@placeholder='Email-IDs (comma separated)']")
	private Locator EnterEmail;

	@FindBy(xpath = "//input[@formcontrolname='subject']")
	private Locator Entersubject;

	@FindBy(xpath = "//div[@class='mdc-checkbox__mixedmark']")
	private Locator Output;

	@FindBy(xpath = "//button[@type='submit']")
	private Locator Savexflow;

	@FindBy(xpath = "//button[@iconname='save']")
	private Locator FinalSave;

	@FindBy(xpath = "//button[@iconname='play']")
	private Locator Execute;

	@FindBy(xpath = "//button[normalize-space(.)='Quick Run']")
	private Locator QuickRun;

	@FindBy(xpath = "//div[@class='mat-mdc-snack-bar-label mdc-snackbar__label' and normalize-space(text())='XFlow Run Initiated']")
	private Locator Sucessmessage;

	@FindBy(xpath = "(//icon[@class='mat-mdc-tooltip-trigger ng-star-inserted'])[2]")
	private Locator agentoperator;

	@FindBy(xpath = "(//icon[@class='mat-mdc-tooltip-trigger ng-star-inserted'])[1]")
	private Locator functionoperator;

	@FindBy(xpath = "//mat-select[@formcontrolname='agent_id']")
	private Locator Agentdropdown;

	@FindBy(xpath = "//span[@class='mdc-list-item__primary-text']//div")
	private Locator selectagent;

	@FindBy(xpath = "//div[normalize-space()='Add']//icon[@class='ng-star-inserted']")
	private Locator functionaddicon;

	@FindBy(xpath = "//div[contains(text(),'Runs')]")
	private Locator Runtab;

	@FindBy(xpath = "//input[@formcontrolname='searchQuery']")
	private Locator Searchfunction;

	@FindBy(xpath = "//div[contains(text(), 'Select Function')]")
	private Locator importfunction;

	@FindBy(xpath = "//p[normalize-space()='a']/following::textarea[@formcontrolname='Value'][1]")
	private Locator Value_A;

	@FindBy(xpath = "//p[normalize-space()='b']/following::textarea[@formcontrolname='Value'][1]")
	private Locator Value_B;

	@FindBy(xpath = "//button[@class='primary ng-star-inserted']//div[@class='mat-mdc-tooltip-trigger flex items-center gap-2'][normalize-space()='Save']")
	private Locator function_valuesave;
	
	@FindBy(xpath = "(//icon[@class='mat-mdc-tooltip-trigger ng-star-inserted'])[1]")
	private Locator Functionoperator;
	
	@FindBy(xpath = "//div[normalize-space(text())='result:']/following-sibling::pre[@class='whitespace-pre-wrap text-white text-left break-words']")
	private Locator Result;
	
	@FindBy(xpath = "//div[@class='mat-mdc-tooltip-trigger flex items-center gap-2'][normalize-space()='Preview']")
	private Locator Function_Preview;

	@FindBy(xpath = "(//icon[@class='mat-mdc-tooltip-trigger ng-star-inserted'])[3]")
	private Locator Bashoperator;

	@FindBy(xpath = "(//icon[@class='mat-mdc-tooltip-trigger ng-star-inserted'])[7]")
	private Locator Movetostate;

	@FindBy(xpath = "//input[@placeholder='API URL']")
	private Locator apiurl;

	@FindBy(xpath = "(//icon[@class='mat-mdc-tooltip-trigger ng-star-inserted'])[4]")
	private Locator apifetch;
	@FindBy(xpath = "//mat-label[contains(text(),'Request Method')]")
	private Locator Reqmethod;

	@FindBy(xpath = "//span[contains(text(),'GET')]")
	private Locator getselection;

	@FindBy(xpath = "//input[@placeholder='API URL']")
	private Locator geturl;

	@FindBy(xpath = "//span[contains(text(),'POST')]")
	private Locator Postselection;

	@FindBy(xpath = "//button[@iconname='edit']")
	private Locator HoverandClick;

	@FindBy(xpath = "//input[@ placeholder=\"Base Command\"]")
	private Locator Entercommand;

	@FindBy(xpath = "//button[@iconname='play']")
	private Locator hoverexecute;

	@FindBy(xpath = "//button[normalize-space(.)='Quick Run']")
	private Locator Quickrun;

	@FindBy(xpath = "//div[contains(text(),'Runs')]")
	private Locator Runstab;

	@FindBy(xpath = "//table[@class = 'mat-mdc-table mdc-data-table__table cdk-table mat-elevation-z2 w-full sticky-header-table']")
	private Locator Executiontable;

	@FindBy(xpath = "//button[@type='submit']//div[@class='mat-mdc-tooltip-trigger flex items-center gap-2'][normalize-space()='Save']")
	private Locator Clicksavebutton;

	@FindBy(xpath = "//h4[@class = 'panel-title truncate max-w-[65%]']")
	private Locator outerclick;

	@FindBy(xpath = "//div[@formgroupname='stateTransitionOperator']")
	private Locator clickworkflow;
	@FindBy(xpath = "//div[@class = 'mat-ripple mat-mdc-option-ripple mat-mdc-focus-indicator']")
	private Locator workflowselect;

	@FindBy(xpath = "//td[@class='mat-mdc-cell mdc-data-table__cell cdk-cell cdk-column-state mat-column-state ng-star-inserted']")
	private Locator actualresult;
	
	@FindBy(xpath = "//textarea[@placeholder ='Request Body']")
	private Locator Requestbody;
	

	@FindBy(xpath = "//*[@id=\"cdk-drop-list-2\"]/div/div[3]/div/div[6]")
	private Locator movetostate;
	
	@FindBy(xpath = "//span[contains(text(), 'Carla_Witting')]")
	private Locator selectworkflow;
	
	@FindBy(xpath = "//mat-label[contains(text(),'Workflow')]")
	private Locator clickworkflowbox;
	
	@FindBy(xpath = "//span[contains(text(),'SDLC Workflow')]")
	private Locator selectworkflowbox;
	
		
	@FindBy(xpath = "//span[contains(text(), 'stage4')]")
	private Locator selectmovetostate;
	
	@FindBy(xpath = "//mat-label[contains(text(),'Move to state')]")
	private Locator clickmovetostate;
	
	@FindBy(xpath = "//span[contains(text(),'Stage2 Analysis')]")
	private Locator selectOneMoveToStateOptions;
	
	@FindBy(xpath = "//button[normalize-space(.)='Trigger URL']")
	private Locator ClickTriggerUrl;
	
	@FindBy(xpath = "//mat-label[contains(text(),'Document Url')]")
	private Locator EnterDocUrl;
	
	@FindBy(xpath = "//button[normalize-space(.)='Trigger URL']")
	private Locator ClickTriggerbtn;
		
		//public void Crtxflowbtn() {

	@FindBy(xpath = "//div[contains(text(),'Edit Task')]/following-sibling::button")
	private Locator closeicon_function;
	
	@FindBy(xpath = "(//icon[@class='mat-mdc-tooltip-trigger ng-star-inserted'])[9]")
	private Locator xflowdrag;

	@FindBy(xpath = "(//icon[@class='mat-mdc-tooltip-trigger ng-star-inserted'])[10]")
	private Locator iteratoroperator;
	
	@FindBy(xpath = "(//icon[@class='mat-mdc-tooltip-trigger ng-star-inserted'])[11]")
	private Locator botoroperator;
	
	@FindBy(xpath = "//button[@type='submit']")
	private Locator iteratorsavevalue;
	

	@FindBy(xpath = "(//icon[@class='mat-mdc-tooltip-trigger ng-star-inserted'])[8]")
	private Locator conditionaldrag;
	@FindBy(xpath = "//input[contains(@placeholder, 'Email-IDs (comma separated)')]")
	private Locator emailtext;
	
	@FindBy(xpath = "//input[contains(@placeholder,'Mail Subject')]")
	private Locator subject;
	
	@FindBy(xpath = "//div[@class = 'mat-ripple mat-mdc-checkbox-ripple mat-mdc-focus-indicator']")
	private Locator attachement;
	
	@FindBy(xpath = "//textarea[@placeholder='Value']")
	private Locator con_value;
	
	@FindBy(xpath = "//div[contains(text(),'Edit Task')]")
	private Locator Headerclick;
	
	@FindBy(xpath = "//mat-label[contains(text(),'Can be Moved To')]")
	private Locator canbemoved;

	@FindBy(xpath = "(//icon[@class='mat-mdc-tooltip-trigger ng-star-inserted'])[5]")
	private Locator sqloroperator;

	
	public void Crtxflowbtn() {

		click(CrtXflowbtn, "Click on create Xflow button ");
	}

	public void enterName(String Xflow) {
		sendText(xflowName, Xflow);
	}
	
	public String sendTextXflowname(String Name) {
		sendText(xflowName, Name);
		return Name;
	}

	public void Clicksave() {
		click(Save, "Click on save button ");
	}

	public void dragMailOperatorToWorkGraph() {
		dragAndDrop(mailopertaor, Workgraph, "Mail Operator", "Work Graph");

	}
	
	public void dragfunctionOperatorToWorkGraph() {
		dragAndDrop(Functionoperator, Workgraph, "Mail Operator", "Work Graph");

	}

	public void Clickediticon() {
		click(Editicon, "Click on Edit icon ");
	}

	public void EnterEmail(String Emailid) {
		sendText(EnterEmail, Emailid);
	}

	public void EnterSubject(String Subject) {
		sendText(Entersubject, Subject);
	}

	public void Checkoutput() {
		click(Output, "Click on attach output ");
	}

	public void Clickonsavexflow() {
		click(Savexflow, "Click on Save xflow ");
	}

	public void Clickfinalsave() {
		click(FinalSave, "Click on Final Save ");
	}

	public void MouseHoverExecute() {
		hover(Execute, "Hover execute btn");
	}

	public void Clickquickrun() {
		click(QuickRun, "Click on QuickRun");
	}

	public void MouseHovereoverlay() {
		hover(Hoverlay, "Hover Overlay");
	}

	public void validateSuccessToast(Locator successMessageLocator, String expectedMessage) {
		try {
			successMessageLocator.waitFor(new Locator.WaitForOptions().setTimeout(5000));
			String actualMessage = successMessageLocator.textContent().trim();
				assertEquals("Toast message matched successfully", "Toast message did not match", expectedMessage,actualMessage);
		} catch (Exception e) {
			extentTest.get().log(Status.FAIL, "Failed to validate success toast");
		}
	}
	
	public void validateResult(String expectedMessage) {
	    try {
	        // Wait until the result element is visible (using timeout of 5000ms)
	        Result.waitFor(new Locator.WaitForOptions().setTimeout(8000));

	        // Get the actual result text
	        String actualMessage = Result.textContent().trim();

	        // Assert if the actual message matches the expected message
	        Assert.assertEquals(actualMessage, expectedMessage, "Result text does not match the expected value.");

	        // Optionally, log success to ExtentReports
	        extentTest.get().log(Status.PASS, "Validated the result text successfully: " + actualMessage);
	    } catch (Exception e) {
	        // Log failure in case of an error or element not found
	        extentTest.get().log(Status.FAIL, "Failed to validate result text. Error: " + e.getMessage());
	    }
	}



	public Locator getSuccessMessage() {
		return Sucessmessage;
	}

	public void draglOperatorToWorkGraph() {
		dragAndDrop(agentoperator, Workgraph, "Agent operator", "Work Graph");
	}

	public void exc1click() {
		click(QuickRun, "Click on Edit icon ");
	}

	public void hoverAndClickQuickRun() {
		try {
			Execute.hover();
			getPage().waitForTimeout(1000);
			QuickRun.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(5000));
			QuickRun.click();
			extentTest.get().log(Status.PASS, "Clicked on Quick Run");
		} catch (Exception e) {
			extentTest.get().log(Status.FAIL, "Quick Run click failed: " + e.getMessage());
		}
	}

	public void clickruntab() {
		click(Runtab, "Click on Edit icon ");
	}

	public void statusvalidation() {
		Locator statusCell = getPage().locator("//table//tr[1]/td[contains(@class, 'cdk-column-state')]");

		try {
			String actualStatus = "";
			int timeoutInSeconds = 50;
			boolean finalStatusReached = false;

			for (int i = 0; i < timeoutInSeconds; i++) {
				actualStatus = statusCell.textContent().trim();

				// Check if it's in a final state
				if (actualStatus.equalsIgnoreCase("Completed")) {
					assertEquals("Status matched successfully", "Status did not match", "Completed", actualStatus);
					finalStatusReached = true;
					break;
				} else if (actualStatus.equalsIgnoreCase("Failedrefresh")) {
					assertEquals("Status matched successfully", "Status did not match", "Completed", // expected
							actualStatus // actual
					);
					finalStatusReached = true;
					break;
				}

				getPage().waitForTimeout(8000);
			}

			if (!finalStatusReached) {
				extentTest.get().log(Status.FAIL,
						"Status did not reach a final state within timeout. Last known status: " + actualStatus);
			}

		} catch (Exception e) {
			extentTest.get().log(Status.FAIL, "Failed to validate status: " + e.getMessage());
		}
	}

	

	
	public void atualresultvalidate() {
		assertContains("exection completed for pass state", "execution completed for fail", "Completed",
				getTextToastMessage());
	}

	public void dragapifetchToWorkflowGraph() {
		dragAndDrop(apifetch, Workgraph, "Apifetch operator", "Work Graph");

	}

	public void reqmethod() {
		click(Reqmethod, "click the req method ");
	}

	public void getselection() {
		click(getselection, "Select the req method ");
	}

	public void selectReqMethod() {
		click(Reqmethod, "Open Request Method dropdown");
		click(getselection, "Select GET option");
	}

	public void selectPostReqMethod() {
		click(Reqmethod, "Open Request Method dropdown");
		click(Postselection, "Select POST option");
	}

	public void clickworkflow() {
		click(clickworkflow, "Click the workflow textbox ");
	}

	public void selectworkflow() {
		click(workflowselect, "Select the workflow from the dropdown ");
	}

	public void geturl(String Apiurl) {
		sendText(geturl, Apiurl);
	}

	public void MouseeHovereoverlay() {
		hover(Hoverlay, "Hover Overlay");
	}

	public void mousehoverclick() {
		click(HoverandClick, "Mouse hover and click");

	}

	public void execute() {
		click(hoverexecute, "Click execute button");

	}

	public void quickrun() {
		click(Quickrun, "Click quickrun button");

	}

	public void hoverAndClickQuickRunn() {
		try {
			hoverexecute.hover();
			getPage().waitForTimeout(1000);
			Quickrun.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(5000));
			Quickrun.click();
			extentTest.get().log(Status.PASS, "Clicked on Quick Run");
		} catch (Exception e) {
			extentTest.get().log(Status.FAIL, "Quick Run click failed: " + e.getMessage());
		}
	}

	public void outerclick() {
		click(outerclick, "Outer click");

	}

	public void Runstab() {
		click(Runstab, "Switch to runs tab");

	}

	public void table() {
		Assert.assertEquals(Executiontable, "Wait for table apperars");

	}

	public void Entercommand(String curl) {
		sendText(Entercommand, "echo Hello world");
	}

	public void commandsave() {
		click(Clicksavebutton, "Click on save button and save the command ");
	}
	
	public void cickaddicon_Function() {
		click(functionaddicon, "Click on save button and save the command ");
	}
	
	public void searchfunctionlibrary(String projectName) {
		Locator searchElement = getPage().locator("//input[@formcontrolname='searchQuery']");
		sendText(searchElement, projectName);
		pressEnterkey();
	}

	
	public void searchProjectAndClick(String projectName) {
		Locator searchElement = getPage().locator("//input[@placeholder='Search Projects']");
		sendText(searchElement, projectName);
		pressEnterkey();
		Locator element = getPage().locator("//div[contains(text(),'" + projectName + "')]");
		getPage().waitForTimeout(2000);
		if (isElementExists(element)) {
			click(element, projectName);
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

	public void dragbashOperatorToWorkGraph() {
		dragAndDrop(Bashoperator, Workgraph, "bashoperator operator", "Work Graph");

	}

	public void Enterurl(String curl) {
		sendText(apiurl, "echo Hello world");
	}

	public void dragmovetostateToWorkGraph() {
		dragAndDrop(Movetostate, Workgraph, "Move to state operator", "Work Graph");

	}
	
	public static Locator getOperatorByName(Page page, String operatorName) {
        String xpath = "//div[contains(@class, 'overflow-auto')]//icon[contains(@class, 'mat-mdc-tooltip-trigger') and text()='" + operatorName + "']";
        return page.locator(xpath);
    }
	
	public void enterValuesInFields() {
	    sendText(Value_A, "5");
	    sendText(Value_B, "5");
	}
	
	public void importfunction() {
		click(importfunction, "click import function");

	}
	

	public void Clickfunctonpreview() {
		click(Function_Preview, "Click on save button ");
	}
	
	public void Savefunctionvalue() {
		click(function_valuesave, "Click on save button ");
	}
	
	public void Reqbodytext(String reqBody) {
		sendText(Requestbody, reqBody);

	}
	
	public void clickcloseicon_funcion() {
		click(closeicon_function, "Click on close icon ");
	}
	
	public void setFunctionValue(String labelText, String value) {
	    Locator textareaElement = getPage().locator(
	        "//mat-label[contains(text(), '" + labelText + "')]"
	        + "/ancestor::div//div[contains(@class,'mat-mdc-form-field-infix')]//textarea[@placeholder='" + labelText + "']");
	    sendText(textareaElement, value);  
	}
	
	public void dragiteratoroperatorToWorkGraph() {
		dragAndDrop(iteratoroperator, Workgraph, "Mail Operator", "Work Graph");
	}
	
	public void selectIteratorOperatorDropdown(String optionValue) {
	    String dropdownXPath = "//mat-label[text()='Iterator Operator']";
	    Locator dropdown = getPage().locator(dropdownXPath);
	    dropdown.click();
	    String optionXPath =  "//mat-option//span[normalize-space(text())='" + optionValue + "']";
	    Locator option = getPage().locator(optionXPath);
	    option.waitFor();
	    option.click();
	}
	
	public void hoverAndClickEditIconOnGraphNode(String operatorName) {
	    String xpath = "//div[contains(text(),'" + operatorName + "') and contains(@class,'modal')]"
	                 + "/ancestor::div[contains(@class,'graph-node-card relative flex')]"
	                 + "//button[@iconname='edit']";
	    Locator editIconLocator = getPage().locator(xpath);
	    editIconLocator.waitFor(new Locator.WaitForOptions()
	        .setState(WaitForSelectorState.VISIBLE)
	        .setTimeout(60000));
	    editIconLocator.scrollIntoViewIfNeeded();
	    editIconLocator.hover();
	    System.out.println("Hovered over edit icon for node: " + operatorName);
	    editIconLocator.click(new Locator.ClickOptions().setForce(true));
	    System.out.println("Clicked edit icon for node: " + operatorName);
	}

	public void mouseHoverOnGraphNode(String operatorName) {
	    String xpath = "//div[contains(text(),'" + operatorName + "') and contains(@class,'modal')]"
	                 + "/ancestor::div[contains(@class,'graph-node-card relative flex')]";
	    
	    Locator node = getPage().locator(xpath);
	    node.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(60000));
	    node.scrollIntoViewIfNeeded();
	    node.hover();
	}
	
	public void dragMovetoStateToWorkflowGraph() {
		dragAndDrop(movetostate, Workgraph, "Move to state operator", "Work Graph");

	}
	public void selectMoveToState() {
		click(selectmovetostate, "Select the dropdown options");

	}
	public void clickworkflowbox() {
		click(clickworkflowbox, "Click on save button ");
	}
	public void selectworkflowbox() {
		click(selectworkflowbox, "Select the work flow options in the drop-down ");
	}
	public void clickmovetostate() {
		click(clickmovetostate, "Click the move to state field ");
	}
	public void selectOneMoveToStateOptions() {
		click(selectOneMoveToStateOptions, "Select One Move To State Options from the drop-down ");
	}
		
	public void ClickTriggerUrl() {
		try {
			Execute.hover();
			getPage().waitForTimeout(1000);
			ClickTriggerUrl.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(5000));
			ClickTriggerUrl.click();
			extentTest.get().log(Status.PASS, "Click on Trigger URL");
		} catch (Exception e) {
			extentTest.get().log(Status.FAIL, "Trigger URl click failed: " + e.getMessage());
		}
	}
	
	public void EnterDocUrl(String Docurl) {
		sendText(EnterDocUrl, Docurl);
	}
	
	public void ClickTriggerbtn() {
		click(ClickTriggerbtn, "Click the Trigger button in the popup ");
	}
	
	public void selectMoveToStateWorkflowDropdown(String optionValue) {
	    String dropdownXPath = "//mat-label[contains(text(),'Workflow')]";
	    Locator dropdown = getPage().locator(dropdownXPath);
	    dropdown.click();
	    //String optionXPath =  "//mat-option[normalize-space(text())='" + optionValue + "']";
	    String optionXPath = "//div[@class='cdk-overlay-pane']//mat-option//span[normalize-space(text())='" + optionValue + "']";

	    Locator option = getPage().locator(optionXPath);
	    option.waitFor();
	    option.click();
	}
	public void dragXflowOperatorToWorkGraph() {
		dragAndDrop(xflowdrag, Workgraph, "Xflow Operator", "Work Graph");

	}
	public void xflowDropdown(String optionValue) {
	    String dropdownXPath = "//mat-label[contains(text(),'Xflow Name')]";
	    Locator dropdown = getPage().locator(dropdownXPath);
	    dropdown.click();
	    //String optionXPath =  "//mat-option[normalize-space(text())='" + optionValue + "']";
	    String optionXPath = "//div[@class='cdk-overlay-pane mat-mdc-select-panel-above']//mat-option//span[normalize-space(text())='" + optionValue + "']";

	    Locator option = getPage().locator(optionXPath);
	    option.waitFor();
	    option.click();
	}

	public void Saveiterationvalue() {
		click(iteratorsavevalue, "Click on save button ");
	}
	
	public void dragbotoroperatorToWorkGraph() {
		dragAndDrop(botoroperator, Workgraph, "Mail Operator", "Work Graph");
	}
	
	public void selectProcessDropdown(String optionValue) {
	    String inputXPath = "//input[@placeholder='Select Process']";
	    Locator inputField = getPage().locator(inputXPath);
	    inputField.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(30000));
	    inputField.click();
	    String optionXPath = "//span[contains(text(),'" + optionValue + "')]";
	    Locator option = getPage().locator(optionXPath);
	    option.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(30000));
	    option.click();
	}
	
	public void selectBot(String botName) {
	    String botInputXPath = "//input[@placeholder='Select Bot']";
	    Locator botInput = getPage().locator(botInputXPath);
	    botInput.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(30000));
	    botInput.click();
	    String botOptionXPath = "//span[contains(text(),'" + botName + "')]";
	    Locator botOption = getPage().locator(botOptionXPath);
	    botOption.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(30000));
	    botOption.click();
	}
	
	public void enterMessageBody() {
	    String messageBodyXPath = "//textarea[@formcontrolname='message_body']";
	    Locator messageBody = getPage().locator(messageBodyXPath); 
	    String testData =
	    	    "\"SUBSCRIPTION_ID\": \"6569342759\",\n" +
	    	    "\"FINON_ID\": \"23456789876543\",\n" +
	    	    "\"DOC_ID\": \"wertyuiuytresw\",\n" +
	    	    "\"BM_USER_NAME\": \"cs@botminds.ai\",\n" +
	    	    "\"BM_PASSWORD\": \"UsFgyL8j2+QgIp1xVMi++y5lARJa7pfYc20hH1vbela8+eoBxvBxqX/NCtwZtm/k3CCqw/R0J1BizLBdIqS3iEyEcYMRez5v9nGf5ZvKyWcjDVluoHfRG9E71zSwfcrSd3wjKQFpqUzF8XgCxTm35NC+tkgMtzAEy04mdgqO4s0SZfDY+HbEoz8vYNsIDz6VovbnPBhHMXty14foCjwdMJRQjX3WQZzIP/ai6E9cQxMqX/wn5yWOcm/3NJ+NwTiwJYGT99UxiXZcuBm2eSTGsa5RlO0dg1fVCt2Y5+ezB4zrCZMASQ==\",\n" +
	    	    "\"BM_DOMAIN\": \"qa.botminds.ai\",\n" +
	    	    "\"INPUT_FILE_DRIVE_URL\": \"/wertyui/Input%20set-1\",\n" +
	    	    "\"TEMPLATE_FILE_DRIVE_URL\": \"/SRV Template Output\",\n" +
	    	    "\"OUTPUT_FILE_DRIVE_URL\": \"/SRV Final Report\",\n" +
	    	    "\"BLOB_ID\": \"eb5fd12b49cb3ef242df4710ec9da69a\",\n" +
	    	    "\"BLOB_IDS\": [\n" +
	    	    "  \"3a0d33fe3327a9ec75cb5fd2d173be00\",\n" +
	    	    "  \"ebdf6faf8f4fbddb2dce730a886b49b1\",\n" +
	    	    "  \"cc84b7d83926bfd4b07ef1e5865d391f\",\n" +
	    	    "  \"ca5bb98790fceeef86904600cee43a7a\"\n" +
	    	    "]";
	    System.out.println("Test Data JSON:\n" + testData);
	    messageBody.fill(testData);
	}
	public void dragconditionalOperatorToWorkGraph() {
		dragAndDrop(conditionaldrag, Workgraph, "Mail Operator", "Work Graph");

	}
	
	public void canBeMovedToDropdown(String optionValue) {
	    String dropdownXPath = "//mat-label[contains(text(),'Can be Moved To')]";
	    Locator dropdown = getPage().locator(dropdownXPath);
	    dropdown.click();
	    String optionXPath = "//div[@class='cdk-overlay-pane']//mat-option//span[normalize-space(text())='" + optionValue + "']";

	    Locator option = getPage().locator(optionXPath);
	    option.waitFor();
	    option.click();
	    wasteDropdownClick("//textarea[@placeholder='Task Description']");
	}	
	
	public void enterEmailText(String Email) {
		sendText(emailtext, Email);
	}
	
	public void enterSubject(String Sub) {
		sendText(subject, Sub);
	}
	
		
	public void attachementCheckedin() {
		click(attachement, "Click on the attchement checkbox ");
	}
	
	public void movesuccesscontrol(String optionValue) {
	    String dropdownXPath = "//mat-label[contains(text(),'Success Step')]";
	    Locator dropdown = getPage().locator(dropdownXPath);
	    dropdown.click();
	    String optionXPath = "//div[@class='cdk-overlay-pane']//mat-option//span[normalize-space(text())='" + optionValue + "']";

	    Locator option = getPage().locator(optionXPath);
	    option.waitFor();
	    option.click();
	}
	public void movefailurecontrol(String optionValue) {
	    String dropdownXPath = "//mat-label[contains(text(),'Failure Step')]";
	    Locator dropdown = getPage().locator(dropdownXPath);
	    dropdown.click();
	    String optionXPath = "//div[@class='cdk-overlay-pane']//mat-option//span[normalize-space(text())='" + optionValue + "']";

	    Locator option = getPage().locator(optionXPath);
	    option.waitFor();
	    option.click();
	}
	public void dropdownclick(String label, String optionValue) {
	   getPage().locator("//mat-label[contains(text(),'" + label + "')]").click();
	   getPage().locator("//div[@class='cdk-overlay-pane']//mat-option//span[normalize-space(text())='" + optionValue + "']").click();
	}
	
	public void Conditionalvalue(String value) {
		sendText(con_value, value);
	}
	//Headerclick
	
	public void headerclick() {
		click(Headerclick, "Click on outer header");
	}
	
		public void dragsqltoroperatorToWorkGraph() {
		dragAndDrop(sqloroperator, Workgraph, "Mail Operator", "Work Graph");
	}
	
	public void selectDropdownOption(String labelText, String optionValue) {
	    getPage().locator("//mat-label[contains(text(),'" + labelText + "')]").click();
	    getPage().locator("//span[contains(text(),'" + optionValue + "')]").click();
	}

	public void enterSQLQuery(String queryText) {
		String Sqlquery= "SELECT * FROM Employees;";
	    getPage().locator("//mat-label[contains(text(),'SQL Query')]/ancestor::mat-form-field//textarea").fill(Sqlquery);
	}
	
	public void selectAgent(String agentName) {
	    String agentDropdownXPath = "//mat-label[contains(text(), 'Agent')]";
	    Locator agentDropdown = getPage().locator(agentDropdownXPath);
	    agentDropdown.waitFor(new Locator.WaitForOptions()
	        .setState(WaitForSelectorState.VISIBLE)
	        .setTimeout(30000));
	    agentDropdown.click();
	    String agentOptionXPath = "//span[@class='mdc-list-item__primary-text']//div//span[normalize-space(text())='" + agentName + "']";
	    Locator agentOption = getPage().locator(agentOptionXPath);
	    agentOption.waitFor(new Locator.WaitForOptions()
	        .setState(WaitForSelectorState.VISIBLE)
	        .setTimeout(30000));
	    agentOption.click();
	}

	
}
	



