package org.framework.playwright.listener;

import java.io.File;

import org.framework.playwright.constants.FrameWorkConstants;
import org.framework.playwright.utils.JavaUtility;
import org.framework.playwright.utils.UtilityClass;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplimentation implements ITestListener, ISuiteListener{
	
	
	public static ExtentReports report;
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
	
	@Override
	public void onStart(ISuite suite) {
		File reportsDir = new File(FrameWorkConstants.REPORTS_PATH);
		if (!reportsDir.exists()) {
			reportsDir.mkdirs(); // Create the directory if it does not exist
		}

		String reportFilePath = FrameWorkConstants.REPORTS_PATH + File.separator + suite.getName() + "_"
				+ new JavaUtility().getSystemDate() + ".html";
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFilePath);
		sparkReporter.config().setTheme(Theme.STANDARD);
		sparkReporter.config().setDocumentTitle("Test Execution Report");
		sparkReporter.config().setReportName(suite.getName() + " Suite Execution");

		report = new ExtentReports();
		report.attachReporter(sparkReporter);

		System.out.println(suite.getName() + " has started execution.");
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest test = report.createTest(result.getName());
		extentTest.set(test);
		extentTest.get().log(Status.INFO, result.getName() + " has started execution.");
		System.out.println(result.getName() + " has started execution.");
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, result.getName() + " has <b>Successfully Executed</b>");
		System.out.println(result.getName() + " has successfully executed.");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		new UtilityClass().captureScreenshotForPublishing(result.getName());
		String failureReason = result.getThrowable() != null ? result.getThrowable().getMessage()
				: "No error message available.";
		if (failureReason.contains("waiting for locator")) {
			extentTest.get().log(Status.FAIL, result.getName() + " has failed.<br><b>Reason</b> : Element not Found");
		}
		extentTest.get().log(Status.FAIL, result.getName() + " has failed <br> Reason: "+"<b>Actual Result is not equal to Expected Result</b>");
		System.out.println(result.getName() + " is failed.");
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.get().log(Status.SKIP, result.getName() + " is skipped. <b>Retrying..</b>");
		new UtilityClass().captureScreenshotForPublishing(result.getName());
		System.out.println(result.getName() + " is skipped.");
	}

	@Override
	public void onFinish(ITestContext suite) {
		report.flush();
		System.out.println(suite.getName() + " has finished execution. Report generated.");
	}
	
}
