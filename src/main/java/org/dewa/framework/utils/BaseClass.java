package org.dewa.framework.utils;

import static org.dewa.framework.utils.Logger.logFailed;
import static org.dewa.framework.utils.Logger.logPassed;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Download;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Mouse;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.BoundingBox;
import com.microsoft.playwright.options.MouseButton;
import com.microsoft.playwright.options.WaitForSelectorState;

public class BaseClass extends UtilityClass {

	public BaseClass(Page page) {
		initElements(page, this);
	}

	public static void click(Locator locator, String elementName) {
		try {
			locator.click();
			waitTillLoading();
			extentTest.get().log(Status.INFO, "Clicked on " + elementName);
			// extentTest.get().log(LogStatus.INFO, extentTest.get().addScreenCapture("." +
			// new UtilityClass().captureScreenshot(false, elementName + " Click")));
		} catch (Exception e) {
			//logFailed(e.toString());
			//extentTest.get().log(Status.FAIL, "Failed to click on " + elementName);
			// extentTest.get().log(LogStatus.INFO, extentTest.get().addScreenCapture("." +
			// new UtilityClass().captureScreenshot(false, elementName + " Click")));
		}
	}

	public static void clickandDrag(Locator locator, String elementName) {
		locator.click(new Locator.ClickOptions().setButton(MouseButton.RIGHT));
		extentTest.get().log(Status.INFO, "Clicked on " + elementName);
		// extentTest.get().log(LogStatus.INFO,
		// extentTest.get().addScreenCapture("."+new
		// UtilityClass().captureScreenshot(false, elementName+" Click")));
	}

	public static void doubleClick(Locator locator, String elementName) {
		locator.dblclick();
		extentTest.get().log(Status.INFO, "Clicked on " + elementName);
		// extentTest.get().log(LogStatus.INFO,
		// extentTest.get().addScreenCapture("."+new
		// UtilityClass().captureScreenshot(false, elementName+" Click")));
	}

	public static void sendText(Locator locator, String Textmessage) {
		locator.fill(Textmessage);
		//extentTest.get().log(LogStatus.INFO, "Text Sent : " + Textmessage);
		// extentTest.get().log(LogStatus.INFO,
		// extentTest.get().addScreenCapture("."+new
		// UtilityClass().captureScreenshot(false, Textmessage +" Sent")));
	}

	public String getText(Locator locator) {
		String text = null;
		try {
			text = locator.textContent();
			extentTest.get().log(Status.INFO, "Retrived Text: " + text);
			// extentTest.get().log(LogStatus.INFO, extentTest.get().addScreenCapture("." +
			// new UtilityClass().captureScreenshot(false, "Text")));
		} catch (Exception e) {
			logFailed(e.toString());
			extentTest.get().log(Status.FAIL, "failed to retrive Text");
			// extentTest.get().log(LogStatus.INFO, extentTest.get().addScreenCapture("." +
			// new UtilityClass().captureScreenshot(false, "String")));
		}
		return text;
	}

	public List<String> getTextList(Locator locator) {
		List<String> text = null;
		try {
			text = locator.allTextContents();
			extentTest.get().log(Status.INFO, "Retrived Text: " + text);
			// extentTest.get().log(LogStatus.INFO, extentTest.get().addScreenCapture("." +
			// new UtilityClass().captureScreenshot(false, "List")));
		} catch (Exception e) {
			logFailed(e.toString());
			extentTest.get().log(Status.FAIL, "failed to retrive Text");
			// extentTest.get().log(LogStatus.INFO, extentTest.get().addScreenCapture("." +
			// new UtilityClass().captureScreenshot(false, "List")));
		}
		return text;
	}

	public static void hover(Locator locator, String elementName) {
		locator.hover();
		extentTest.get().log(Status.INFO, "hovered on Element : " + elementName);
		// extentTest.get().log(LogStatus.INFO,
		// extentTest.get().addScreenCapture("."+new
		// UtilityClass().captureScreenshot(false, elementName +" hovered")));
	}

	public static void pressEscapeKey() {
		getPage().keyboard().press("Escape");
	}

	public static void pressEnterkey() {
		getPage().keyboard().press("Enter");
	}

	public static void assertEquals(String passMsg, String failMsg, int expected, int actual) {
		getPage().waitForTimeout(5000);
		if (expected == actual) {
			logPassed(passMsg, String.valueOf(expected), String.valueOf(actual));
		} else {
			logFailed(failMsg, String.valueOf(expected), String.valueOf(actual));
		}
		// extentTest.get().log(LogStatus.INFO,
		// extentTest.get().addScreenCapture("."+new
		// UtilityClass().captureScreenshot(false, "Validation")));
	}

	public static void assertEquals(String passMsg, String failMsg, char expected, char actual) {
		getPage().waitForTimeout(5000);
		if (expected == actual) {
			logPassed(passMsg, String.valueOf(expected), String.valueOf(actual));
		} else {
			logFailed(failMsg, String.valueOf(expected), String.valueOf(actual));
		}
		// extentTest.get().log(LogStatus.INFO,
		// extentTest.get().addScreenCapture("."+new
		// UtilityClass().captureScreenshot(false, "Validation")));
	}

	public static void assertEquals(String passMsg, String failMsg, boolean expected, boolean actual) {
		if (expected == actual) {
			extentTest.get().log(Status.PASS, passMsg);
		} else {
			extentTest.get().log(Status.SKIP, failMsg);
			Assert.fail();
		}
		// extentTest.get().log(LogStatus.INFO,
		// extentTest.get().addScreenCapture("."+new
		// UtilityClass().captureScreenshot(false, "Validation")));
	}

	public static <T> void assertEquals(String passMsg, String failMsg, List<T> expected, List<T> actual) {
		getPage().waitForTimeout(5000);
		if (expected.equals(actual)) {
			logPassed(passMsg, expected.toString(), actual.toString());
		} else {
			logFailed(failMsg, expected.toString(), actual.toString());
		}
		// extentTest.get().log(LogStatus.INFO,
		// extentTest.get().addScreenCapture("."+new
		// UtilityClass().captureScreenshot(false, "Validation")));
	}

	public static <T> void assertEquals(String passMsg, String failMsg, Set<T> expected, Set<T> actual) {
		getPage().waitForTimeout(5000);
		if (expected.equals(actual)) {
			logPassed(passMsg, expected.toString(), actual.toString());
		} else {
			logFailed(failMsg, expected.toString(), actual.toString());
		}
		// extentTest.get().log(LogStatus.INFO,
		// extentTest.get().addScreenCapture("."+new
		// UtilityClass().captureScreenshot(false, "Validation")));
	}

	public static <K, V> void assertEquals(String passMsg, String failMsg, Map<K, V> expected, Map<K, V> actual) {
		getPage().waitForTimeout(5000);
		if (expected.equals(actual)) {
			logPassed(passMsg, expected.toString(), actual.toString());
		} else {
			logFailed(failMsg, expected.toString(), actual.toString());
		}
		// extentTest.get().log(LogStatus.INFO,
		// extentTest.get().addScreenCapture("."+new
		// UtilityClass().captureScreenshot(false, "Validation")));
	}

	public static void assertEquals(String passMsg, String failMsg, String expected, String actual) {
		getPage().waitForTimeout(5000);
		if (expected.equals(actual)) {
			logPassed(passMsg, expected, actual);
		} else {
			logFailed(failMsg, expected, actual);
		}
		// extentTest.get().log(LogStatus.INFO,
		// extentTest.get().addScreenCapture("."+new
		// UtilityClass().captureScreenshot(false, "Validation")));
	}

	public static void assertContains(String passMsg, String failMsg, String actual, String expected) {
		if (expected.contains(actual)) {
			logPassed(passMsg, expected, actual);
		} else {
			logFailed(failMsg, expected, actual);
			Assert.fail();
		}
		// extentTest.get().log(LogStatus.INFO, extentTest.get().addScreenCapture("." +
		// new UtilityClass().captureScreenshot(false, "Validation")));
	}
	
	public void assertContains(String actual, String expected) {
	    if (actual == null || expected == null) {
	        Assert.fail("One or both values are null: Actual = " + actual + ", Expected = " + expected);
	    }
	    if (!actual.contains(expected)) {
	        Assert.fail("Assertion failed. Actual does not contain expected.\nActual: " + actual + "\nExpected: " + expected);
	    }
	}


	public static void assertContains(String passMsg, String failMsg, Set<String> expected, Set<String> actual) {
		if (expected.contains(actual)) {
			logPassed(passMsg, expected, actual);
		} else {
			logFailed(failMsg, expected, actual);
		}
		// extentTest.get().log(LogStatus.INFO, extentTest.get().addScreenCapture("." +
		// new UtilityClass().captureScreenshot(false, "Validation")));
	}

	public static void assertNotContains(String passMsg, String failMsg, String expected, String actual) {
		getPage().waitForTimeout(5000);
		if (!expected.contains(actual)) {
			logPassed(passMsg, expected, actual);
		} else {
			logFailed(failMsg, expected, actual);
		}
		// extentTest.get().log(LogStatus.INFO, extentTest.get().addScreenCapture("." +
		// new UtilityClass().captureScreenshot(false, "Validation")));

	}

	public static void scrollPage(int xAxis, int yAxis) {
		getPage().mouse().wheel(xAxis, yAxis);
	}

	public static String downloadFile(Locator downloadElement) {
		Download download = getPage().waitForDownload(() -> click(downloadElement, "download Icon"));
		String actualFileName = download.suggestedFilename();
		return actualFileName;
	}

	public void annotateDocumentValue(Locator element) {
		BoundingBox boundingBox = element.boundingBox();
		if (boundingBox != null) {
			double startX = boundingBox.x + boundingBox.width / 2;
			double startY = boundingBox.y + boundingBox.height / 2;
			double targetX = startX + 30;
			double targetY = startY + 0;
			getPage().waitForTimeout(2000);
			getPage().mouse().move(startX, startY);
			getPage().waitForTimeout(2000);
			getPage().mouse().click(startX, startY);
			getPage().waitForTimeout(500);
			getPage().mouse().click(startX, startY);

			getPage().mouse().down();
			getPage().waitForTimeout(1000);
			getPage().mouse().move(targetX, targetY, new Mouse.MoveOptions().setSteps(20)); // Drag to the new location
			getPage().waitForTimeout(1000);
			getPage().mouse().up();
			getPage().waitForTimeout(1000);
		}
	}
	
	
}
