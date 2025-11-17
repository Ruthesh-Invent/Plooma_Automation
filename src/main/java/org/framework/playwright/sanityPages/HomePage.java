package org.framework.playwright.sanityPages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.framework.playwright.annotations.FindBy;
import org.framework.playwright.utils.BaseClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.locks.LockSupport;

import static org.framework.playwright.utils.Logger.logFailed;
import static org.framework.playwright.utils.Logger.logPassed;

public class HomePage extends BaseClass {
    public HomePage(Page page) {
        super(page);
    }

    @FindBy(xpath = "//icon[@name='setting/new-studio']")
    private Locator buttonStudio;

    public String getSubscriptionText(){
        Locator subsElement = getPage().locator("//div[contains(text(),'BOMAutomation')]");
        return getText(subsElement).trim();
    }

    public void searchProjectAndHover(String projectName){
        Locator searchElement =getPage().locator("//input[@placeholder='Search Projects']");
        sendText(searchElement,projectName);
        pressEnterkey();
        getPage().waitForTimeout(1000);
        Locator element=getPage().locator("//div[contains(text(),'"+projectName+"')]");
        getPage().waitForTimeout(2000);
        if(isElementExists(element)){
            hover(element, projectName);
        }
        else{
            Locator loadMoreElement = getPage().locator("//div[contains(text(),'Load more')]");
            for(int i=0;i<loadMoreElement.count();i++){
                click(loadMoreElement,"Load More");
                getPage().waitForTimeout(2000);
                if(isElementExists(element)){
                    hover(element, projectName);
                    break;
                }
            }
        }
    }

    public String getProjectName(){
        Locator projectElement = getPage().locator("//div[contains(text(),'IngestionAutomation')]");
        return getText(projectElement).trim();
    }

    public void  searchProjectAndClick(String projectName){
        Locator searchElement =getPage().locator("//input[@placeholder='Search Projects']");
        searchElement.clear();
        sendText(searchElement,projectName);
        pressEnterkey();
        Locator element=getPage().locator("//div[translate(normalize-space(text()), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = '"+projectName.toLowerCase()+"']");
        //Locator element=getPage().locator("//div[contains(text(),'"+projectName+"')]");
        getPage().waitForTimeout(2000);
        if(isElementExists(element)){
            click(element, projectName);
        }
        else{
            Locator loadMoreElement = getPage().locator("//div[contains(text(),'Load more')]");
            for(int i=0;i<loadMoreElement.count();i++){
                click(loadMoreElement,"Load More");
                getPage().waitForTimeout(2000);
                if(isElementExists(element)){
                    click(element, projectName);
                    break;
                }
            }
        }
    }

    public void clickStudioPage(){

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        doubleClick(buttonStudio,"Studio Page");
    }

    public List<String> getSectionNames(){
        Locator sectionElements = getPage().locator("//mat-panel-title[text()]");
        List<String> sectionNames = new ArrayList<>();
        for(int i=0;i<sectionElements.count();i++){
            sectionNames.add(sectionElements.nth(i).textContent().trim());
        }
        return sectionNames;
    }

    public void clickCreatedEntity(String entityName) {
        Locator arrowDownButton = getPage().locator("//button[@aria-haspopup='menu']//icon[@name='arrowDown']");
        Locator entityNameElement = getPage().locator("//a[contains(text(),'" + entityName + "')]");
        if (entityNameElement.isVisible()) {
            click(entityNameElement, entityName);
        } else {
            click(arrowDownButton, "ArrowDown");
            click(entityNameElement, entityName);
            wasteClickInBetweenTheScreen();
        }
    }

    public void checkVisibilityOfFilterElement(){
        Locator filterButton = getPage().locator("//button[@color='transparent']//div[contains(text(),'Filters')]");
        if(filterButton.isVisible()){
            logPassed("Filter Button is visible");
        }else{
            logFailed("Filter Button is not Visible");
        }
    }

    public void clickChat(){
        Locator chatButton = getPage().locator("//button[normalize-space(text()) = 'Chat']");
        click(chatButton,"Chat");
    }

    public String getChatPageText(){
        Locator chatElement = getPage().locator("(//div[text()='Chatbot'])[1]");
        return getText(chatElement).trim();
    }

    public void clickDashboard(){
        Locator dashboardElement = getPage().locator("//button[@data-cy='dashboard-btn']");
        click(dashboardElement,"Dashboard");
    }

    public String getOverviewText(){
        Locator overviewElement = getPage().locator("//span[text()='Overview']");
        return getText(overviewElement).trim();
    }

    public void checkVisibilityOfDashboardWidget(){
        Locator widgetElement = getPage().locator("(//app-widget-core)[1]");
        if(widgetElement.isVisible()){
            logPassed("Widget is visible");
        }
        else{
            logFailed("widget is not visible");
        }
    }

    public String getDashboardWidgetName(){
        Locator widgetElement = getPage().locator("//app-widget-header//h4[contains(text(),'Total Documents')]");
        return getText(widgetElement).trim();
    }
}

