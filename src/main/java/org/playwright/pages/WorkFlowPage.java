package org.playwright.pages;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.framework.playwright.annotations.FindBy;
import org.framework.playwright.utils.BaseClass;
import com.microsoft.playwright.Page.WaitForSelectorOptions;

import static org.framework.playwright.utils.Logger.logInfo;

public class WorkFlowPage extends BaseClass {

    public WorkFlowPage(Page page) {
        super(page);
    }

    @FindBy(xpath = "//mat-panel-title[contains(text(),'Automation')]")
    private Locator iconAutomation;

    @FindBy(xpath ="//span[text()='Workflows']")
    private  Locator buttonWorkFlow;

        @FindBy(xpath ="(//div[contains(text(),'XFlow')])[1]")
    private Locator buttonCreateWorkFlow;

    public void expandAutomation(){
        click(iconAutomation,"Automation");
    }

    public void clickWorkFlows(){
        click(buttonWorkFlow,"Workflow");
    }

    public void clickCreateWorkFlow(){
        click(buttonCreateWorkFlow,"Create WorkFlow");
    }

    public String enterWorkFlowName(String workFlowName){
        Locator nameElement = getPage().locator("//input[@placeholder='Name']");
        sendText(nameElement,workFlowName);
        return workFlowName;
    }

    public String enterWorkFlowDescription(String description){
        Locator descriptionElement = getPage().locator("//textarea[@placeholder='Description']");
        sendText(descriptionElement,description);
        return description;
    }

    public void clickSaveWorkFlow(){
        Locator saveElement = getPage().locator("(//div[contains(text(),'Save XFlow')])[1]");
        click(saveElement,"Save XFlow");
    }

    public String getWorkFlowName(String name){
        Locator nameElement = getPage().locator("//span[contains(text(),'"+name+"')]");
        return getText(nameElement).trim();
    }

    public String getWorkFlowDescription(String description){
        Locator descriptionElement = getPage().locator("//p[contains(text(),'"+description+"')]");
        return getText(descriptionElement).trim();
    }

    public String getToastMessage(){
        Locator toastMessageElement = getPage().locator("//div[@class='mat-mdc-snack-bar-label mdc-snackbar__label']");
        return getText(toastMessageElement).trim();
    }

    public void ClickEditIcon(String workFlowName){
        Locator threeDotIcon = getPage().locator("//span[contains(text(),'"+workFlowName+"')]//parent::span//following-sibling::button");
        click(threeDotIcon,"three dot");
        Locator editIcon = getPage().locator("//button//div[contains(text(),'Edit')]");
        click(editIcon,"Edit");
    }

    public void hoverOnWorkFlow(String workFlowName){
        Locator workflowElement = getPage().locator("//span[contains(text(),'"+workFlowName+"')]");
        hover(workflowElement,workFlowName);
    }

    public void clearNameField(String textToClear){
        Locator nameElement = getPage().locator("//input[@placeholder='Name']");
        clear(nameElement,textToClear);
    }

    public void deleteWorkFlow(String workFlowName){
        Locator threeDotIcon = getPage().locator("//span[contains(text(),'"+workFlowName+"')]//parent::span//following-sibling::button");
        click(threeDotIcon,"three dot");
        Locator deleteIcon = getPage().locator("//button//div[contains(text(),'Delete')]");
        click(deleteIcon,"Delete");
    }

    public String getAlertMessage(){
        Locator alertElement = getPage().locator("//h1[contains(@id,'mat-mdc-dialog-title')]");
        return getText(alertElement);
    }

    public String getAlertVerbiage(){
        Locator verbiageElement = getPage().locator("(//div[contains(@class,'mat-mdc-dialog-content mdc-dialog__content')]//p)[1]");
        return getText(verbiageElement);
    }

    public void clickConfirmButton(){
        Locator buttonConfirm = getPage().locator("//button[@id='proceed']");
        click(buttonConfirm,"Confirm");
    }

    public void ClickWorkFlowName(){
        Locator nameElement = getPage().locator("//input[@placeholder='Name']");
        click(nameElement,"Name");
    }

    public String getColourCodeSection() {
        Locator colorCodeElement = getPage().locator("//mat-form-field[@class='mat-mdc-form-field ng-tns-c1798928316-34 mat-mdc-form-field-type-mat-input mat-form-field-appearance-outline mat-primary ng-star-inserted ng-dirty ng-touched mat-form-field-invalid ng-invalid mat-form-field-hide-placeholder']");
        String colorCode = colorCodeElement.evaluate("element => getComputedStyle(element).getPropertyValue('--bai-label-bg-color').trim()").toString();
        return colorCode;
    }

    public void closeWorkflow(){
        Locator closeIcon = getPage().locator("//icon[@_ngcontent-ng-c1392334112]");
        click(closeIcon,"Close");
    }

    public void clickSubscription(String subsName){
        Locator subsElement = getPage().locator("//div[@title='"+subsName+"']");
        click(subsElement,subsName);
    }

    public void clickCreatedXFlow(String xflowName){
        Locator xflowIcon = getPage().locator("//span[contains(text(),'"+xflowName+"')]");
        click(xflowIcon,xflowName);
    }

    public void dragAndDropXflowName(int index,String dragName,String dropName){
        Locator dragElement = getPage().locator("(//div[@id='graph-dashboard']//div[contains(@class,'cdk-drag example-box')])["+index+"]");
        Locator dropElement = getPage().locator("#cdk-drop-list-0 .graph-div-height");
        dragAndDrop(dragElement,dropElement,dragName,dropName);
    }

    public void clickAdvancedSettings() {
        Locator advancedSettingsButton = getPage().locator("//a[contains(text(),'Show Advanced Settings')]");
        click(advancedSettingsButton, "Advanced Settings");
    }

    public void hoverOnCardName(String cardName){
        Locator cardElement = getPage().locator("//div[contains(text(),'"+cardName+"')]//ancestor::div[contains(@class,'graph-node-card relative')]");
        hover(cardElement,cardName);
    }

    public void clickEditIcon(String cardName){
        Locator editIcon = getPage().locator("//div[contains(text(),'"+cardName+"')]//parent::div[contains(@class,'truncate')]//parent::div//..//parent::div//following-sibling::div//button[@iconname='edit']");
        click(editIcon,"edit Icon");
    }

    public void sendEmailId(String emailId){
        Locator emailField = getPage().locator("//input[@placeholder='Email-IDs (comma separated)']");
        sendText(emailField,emailId);
    }

    public String sendEmailSubject(String subject){
        Locator emailSubject = getPage().locator("//input[@placeholder='Mail Subject']");
        sendText(emailSubject,subject);
        return subject;
    }

    public String sendDescription(String Description){
        Locator emailDescription = getPage().locator("//textarea[@placeholder='Message']");
        sendText(emailDescription,Description);
        return Description;
    }

    public void clickSubmitButton(){
        Locator saveButton = getPage().locator("//button[@type='submit']");
        click(saveButton,"submit");
    }

    public void clickSaveButton(){
        Locator saveButton = getPage().locator("//button[@iconname='save']");
        click(saveButton,"Save");
    }

    public void clickExecuteButton(){
        Locator executeButton = getPage().locator("//button[@iconname='play']");
        hover(executeButton,"Execute");
    }

    public void clickQuickRunButton(){
        Locator quickRunButton = getPage().locator("//button//div[contains(text(),'Quick Run')]");
        click(quickRunButton,"Quick Run");
    }

    public void clickXflowSection(String sectionName){
        Locator sectionElement = getPage().locator("//div[contains(text(),'"+sectionName+"')]");
        click(sectionElement,sectionName);
    }

    public void waitForFirstStateCompletedPolling(long timeoutMs) {
        Locator cell = getPage().locator("(//td[contains(@class,'mat-column-state')])[1]");
        long start = System.currentTimeMillis();
        try {
            while (System.currentTimeMillis() - start < timeoutMs) {
                String txt = cell.textContent();
                if (txt != null && txt.trim().equals("Completed")) {
                    return;
                }
                Thread.sleep(200);  // poll every 200ms
            }
            throw new RuntimeException("Timed out waiting for state to become Completed");
        }
        catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public String selectDropdownCheckBox(String fieldName, String fieldValue) {
        Locator fieldElement = getPage().locator("//mat-label//div[text()='" + fieldName + "']");
        if (fieldElement.count() == 0) {
            fieldElement = getPage().locator(
                    "//mat-label[contains(text(),'"+fieldName+"')]/../../../..//parent::div//following-sibling::div//mat-select[@role='combobox']");
        }
        if (fieldElement.count() == 0) {
            fieldElement = getPage().locator(
                    "//mat-label//span[contains(text(),'"+fieldName+"')]/../../../..//parent::div//following-sibling::div//mat-select[@role='combobox']");

        }
        if(fieldElement.count()==0){
            fieldElement = getPage().locator("//mat-label[contains(text(),'"+fieldName+"')]//../../../following-sibling::div//mat-select[@role='combobox']");

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

    public void clickDropdownCheckBoxIfNotEnabled(String fieldName, String fieldValue){
       Locator fieldElement = getPage().locator("//mat-label[contains(text(),'"+fieldName+"')]//../../../following-sibling::div//mat-select[@role='combobox']");
       Locator fieldValueElement = getPage().locator("//span[text()='"+fieldValue+"']//parent::mat-option//mat-pseudo-checkbox");
       click(fieldElement,fieldName);
       if(!fieldValueElement.getAttribute("class").contains("mat-pseudo-checkbox-checked")){
           click(fieldValueElement,fieldValue);
       }
       else{
           logInfo(fieldValue + "is already enabled");
       }
    }

    public void sendApiUrl(String url){
        Locator apiElement = getPage().locator("//input[@placeholder='API URL']");
        sendText(apiElement,url);
    }

    public void clickPreviewButton(){
        Locator previewElement = getPage().locator("//div[contains(text(),'Preview')]");
        click(previewElement,"Preview");
    }

    public String getPreviewText(){
        Locator previewText = getPage().locator("//h4[contains(text(),'Preview')]//parent::div//following-sibling::pre");
        return getText(previewText);
    }

}
