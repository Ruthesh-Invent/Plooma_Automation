package org.dewa.framework.objectrepo;

import java.util.ArrayList;
import java.util.List;

import org.dewa.framework.utils.BaseClass;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class Challenges360ListPage extends BaseClass{

	public Challenges360ListPage(Page page) {
		super(page);
		// TODO Auto-generated constructor stub
	}
	
	public List<String> getAllTabsText() {
		//bot-tab//span[text()]
		List<Locator> allTabElements = getPage().locator("//bot-tab//span[text()]").all();
		List<String> allTabs=new ArrayList<String>();
		for(Locator eachTab:allTabElements) {
			allTabs.add(eachTab.textContent().trim());
		}
		return allTabs;
	}
	
	public Integer getTotalChallengesCount() {
		//h4[contains(text(),'Total Challenges')]/ancestor::app-widget-header/following-sibling::h4
		String count = getPage().locator("//h4[contains(text(),'Total Challenges')]/ancestor::app-widget-header/following-sibling::h4").textContent().trim();
		return Integer.valueOf(count);
	}
	
	public String getWidgetValue(String widgetName) {
		//h4[contains(text(),'Top Challenge')]/ancestor::app-query-widget/div//h4
		String widgetText = getPage().locator("//h4[contains(text(),'"+widgetName+"')]/ancestor::app-query-widget/div//h4").textContent().trim();
		return widgetText;
	}

}
