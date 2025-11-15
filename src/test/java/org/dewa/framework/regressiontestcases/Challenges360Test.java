package org.dewa.framework.regressiontestcases;

import org.dewa.framework.basefunctions.CommonFunctions;
import org.dewa.framework.listener.RetryListener;
import org.dewa.framework.scriptmodules.Challenges360Modules;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(org.dewa.framework.listener.ListenerImplementation.class)
public class Challenges360Test extends CommonFunctions {

	Challenges360Modules modules = new Challenges360Modules(getPage());
	String subsName = prop.getProperty("subscriptionName");
	String projectName = prop.getProperty("projectName");

	@BeforeClass(alwaysRun = true)
	public void navigateToProjectFromHome() {
		String emailId = prop.getProperty("email");
		String password = prop.getProperty("password");
		new CommonFunctions().launchApplication(getUrl(), emailId, password);
		System.out.println("Before Class Executed");
	}

	// 1
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verify360ChallengesListPageTitleTest() {
		modules.verify360ChallengesListPageTitle(subsName, projectName);
	}
	
	// 2
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyTabsTest() {
		modules.verifyTabs(subsName, projectName);
	}
	
	// 3
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyStatsTotalChallengesTest() {
		modules.verifyStatsTotalChallenges(subsName, projectName);
	}
	
	// 4
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyStatsTopChallengeTest() {
		modules.verifyStatsTopChallenge(subsName, projectName);
	}
	
	// 5 to 10 Failures Bugs
	
	// 11 and 12
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyHideFiltersTest() {
		modules.verifyHideFilters(subsName, projectName);
	}
	
	// 13 Failure
	
	// 14
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyIdSearchTest() {
		modules.verifyIdSearch(subsName, projectName);
	}
	
	
	// 15 Depricated
	
	// 16 Bug
	
	// 17 , 18 , 19 Covered in Voice List Page
	
	// 20 Depricated
	
	// 21
	@Test(groups = "Regression", retryAnalyzer = RetryListener.class)
	public void verifyChallengesListPageTableHeadersTest() {
		modules.verifyChallengesListPageTableHeaders(subsName, projectName);
	}
	
	
	
}
