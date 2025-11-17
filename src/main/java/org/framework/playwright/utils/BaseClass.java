package org.framework.playwright.utils;

import static org.framework.playwright.utils.Logger.logFailed;
import static org.framework.playwright.utils.Logger.logPassed;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Download;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Mouse;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.TimeoutError;
import com.microsoft.playwright.options.BoundingBox;
import com.microsoft.playwright.options.MouseButton;

import com.microsoft.playwright.options.WaitForSelectorState;

import org.testng.Assert;

public class BaseClass extends UtilityClass {

	public BaseClass(Page page) {
		initElements(page, this);
	}

	public static void click(Locator element, String elementName) {
			element.click();
			waitTillLoading();
			extentTest.get().log(Status.INFO, "Clicked on " + elementName);
			// extentTest.get().log(Status.INFO, extentTest.get().addScreenCapture("." +
			// new UtilityClass().captureScreenshot(false, elementName + " Click")));
			//page.mouse().move(500, 500);  // Move the mouse to position (500, 500)
			// extentTest.get().log(Status.INFO, extentTest.get().addScreenCapture("." +
			// new UtilityClass().captureScreenshot(false, elementName + " Click")));
		}
	

	public static void clickandDrag(Locator locator, String elementName) {
		locator.click(new Locator.ClickOptions().setButton(MouseButton.RIGHT));
		extentTest.get().log(Status.INFO, "Clicked on " + elementName);
		// extentTest.get().log(Status.INFO,
		// extentTest.get().addScreenCapture("."+new
		// UtilityClass().captureScreenshot(false, elementName+" Click")));
	}

	public static void doubleClick(Locator locator, String elementName) {
		locator.dblclick();
		extentTest.get().log(Status.INFO, "Clicked on " + elementName);
		// extentTest.get().log(Status.INFO,
		// extentTest.get().addScreenCapture("."+new
		// UtilityClass().captureScreenshot(false, elementName+" Click")));
	}

//	public static void sendText(Locator locator, String Textmessage) {
//		locator.fill(Textmessage);
//		extentTest.get().log(Status.INFO, "Text Sent : " + Textmessage);
//		// extentTest.get().log(Status.INFO,
//		// extentTest.get().addScreenCapture("."+new
//		// UtilityClass().captureScreenshot(false, Textmessage +" Sent")));
//	}
	
	public static void sendText(Locator locator, String Textmessage) {
	    try {
	        // Ensure the locator is visible and enabled before attempting to fill
	        locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(10000)); // Wait up to 10s
	        locator.fill(Textmessage);
	        extentTest.get().log(Status.INFO, "Text Sent : " + Textmessage);
	    } catch (TimeoutError e) {
	        // Take a debug screenshot to help diagnose failures
	        getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get("sendText-timeout-debug.png")));
	        extentTest.get().log(Status.FAIL, "Timeout while waiting for element to be visible for fill: " + locator + ", message: " + Textmessage);
	        throw e;
	    }
	}

	public String getText(Locator locator) {
		String text = null;
		try {
			text = locator.textContent();
			extentTest.get().log(Status.INFO, "Retrived Text: " + text);
			// extentTest.get().log(Status.INFO, extentTest.get().addScreenCapture("." +
			// new UtilityClass().captureScreenshot(false, "Text")));
		} catch (Exception e) {
			logFailed(e.toString());
			extentTest.get().log(Status.FAIL, "failed to retrive Text");
			// extentTest.get().log(Status.INFO, extentTest.get().addScreenCapture("." +
			// new UtilityClass().captureScreenshot(false, "String")));
		}
		return text;
	}
	

	public String getInputValue(Locator locator) {
		String text = null;
		try {
			text = locator.inputValue();
			extentTest.get().log(Status.INFO, "Retrived Text: " + text);
			// extentTest.get().log(Status.INFO, extentTest.get().addScreenCapture("." +
			// new UtilityClass().captureScreenshot(false, "Text")));
		} catch (Exception e) {
			logFailed(e.toString());
			extentTest.get().log(Status.FAIL, "failed to retrive Text");
			// extentTest.get().log(Status.INFO, extentTest.get().addScreenCapture("." +
			// new UtilityClass().captureScreenshot(false, "String")));
		}
		return text;
	}

	public List<String> getTextList(Locator locator) {
		List<String> text = null;
		try {
			text = locator.allTextContents();
			extentTest.get().log(Status.INFO, "Retrived Text: " + text);
			// extentTest.get().log(Status.INFO, extentTest.get().addScreenCapture("." +
			// new UtilityClass().captureScreenshot(false, "List")));
		} catch (Exception e) {
			logFailed(e.toString());
			extentTest.get().log(Status.FAIL, "failed to retrive Text");
			// extentTest.get().log(Status.INFO, extentTest.get().addScreenCapture("." +
			// new UtilityClass().captureScreenshot(false, "List")));
		}
		return text;
	}

	public static void hover(Locator locator, String elementName) {
		locator.hover();
		extentTest.get().log(Status.INFO, "hovered on Element : " + elementName);
		// extentTest.get().log(Status.INFO,
		// extentTest.get().addScreenCapture("."+new
		// UtilityClass().captureScreenshot(false, elementName +" hovered")));
	}

	public static void clear(Locator locator,String text){
		locator.clear();
		extentTest.get().log(Status.INFO, "cleared the text : " + text);
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
		softAssert.assertAll();
		// extentTest.get().log(Status.INFO,
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
		softAssert.assertAll();
		// extentTest.get().log(Status.INFO,
		// extentTest.get().addScreenCapture("."+new
		// UtilityClass().captureScreenshot(false, "Validation")));
	}

	public static void assertEquals(String passMsg, String failMsg, boolean expected, boolean actual) {
		if (expected == actual) {
			extentTest.get().log(Status.PASS, passMsg);
		} else {
			extentTest.get().log(Status.FAIL, failMsg);
			Assert.fail();
		}
		// extentTest.get().log(Status.INFO,
		// extentTest.get().addScreenCapture("."+new
		// UtilityClass().captureScreenshot(false, "Validation")));
	}

	public static <T> void assertEquals(String passMsg, String failMsg, List<T> expected, List<T> actual) {
		//getPage().waitForTimeout(5000);
		if (expected.equals(actual)) {
			logPassed(passMsg, expected.toString(), actual.toString());
		} else {
			logFailed(failMsg, expected.toString(), actual.toString());
			Assert.fail();
		}
	}

	public static <T> void assertEquals(String passMsg, String failMsg, Set<T> expected, Set<T> actual) {
		getPage().waitForTimeout(5000);
		if (expected.equals(actual)) {
			logPassed(passMsg, expected.toString(), actual.toString());
		} else {
			logFailed(failMsg, expected.toString(), actual.toString());
		}
		softAssert.assertAll();
		// extentTest.get().log(Status.INFO,
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
		softAssert.assertAll();
		// extentTest.get().log(Status.INFO,
		// extentTest.get().addScreenCapture("."+new
		// UtilityClass().captureScreenshot(false, "Validation")));
	}

	public static void assertEquals(String passMsg, String failMsg, String expected, String actual) {
		getPage().waitForTimeout(5000);
		if (expected.equals(actual)) {
			logPassed(passMsg, expected, actual);
		} else {
			logFailed(failMsg, expected, actual);
			Assert.fail();
		}
		// extentTest.get().log(Status.INFO,
		// extentTest.get().addScreenCapture("."+new
		// UtilityClass().captureScreenshot(false, "Validation")));
	}

	public static void assertContains(String passMsg, String failMsg, String expected, String actual) {
		if (expected.contains(actual)) {
			logPassed(passMsg, expected, actual);
		} else {
			logFailed(failMsg, expected, actual);
			Assert.fail();
		}
		// extentTest.get().log(Status.INFO, extentTest.get().addScreenCapture("." +
		// new UtilityClass().captureScreenshot(false, "Validation")));
	}

	public static void assertContains(String passMsg, String failMsg, Set<String> expected, Set<String> actual) {
		if (expected.contains(actual)) {
			logPassed(passMsg, expected, actual);
		} else {
			logFailed(failMsg, expected, actual);
		}
		softAssert.assertAll();
		// extentTest.get().log(Status.INFO, extentTest.get().addScreenCapture("." +
		// new UtilityClass().captureScreenshot(false, "Validation")));
	}

	public static void assertSetEquals(String passMsg, String failMsg, List<String> expected,List<String> actual){
		if(new HashSet<>(expected).equals(new HashSet<>(actual))){
			logPassed(passMsg, expected, actual);
		} else {
			logFailed(failMsg, expected, actual);
		}
		softAssert.assertAll();
	}

	public static void assertNotContains(String passMsg, String failMsg, String expected, String actual) {
		getPage().waitForTimeout(5000);
		if (!expected.contains(actual)) {
			logPassed(passMsg, expected, actual);
		} else {
			logFailed(failMsg, expected, actual);
		}
		softAssert.assertAll();
		// extentTest.get().log(Status.INFO, extentTest.get().addScreenCapture("." +
		// new UtilityClass().captureScreenshot(false, "Validation")));

	}

	public static void assertDateInRange(LocalDate actualDate, LocalDate startDate, LocalDate endDate, String passMsg) {
		getPage().waitForTimeout(5000);
		if (actualDate.isEqual(startDate)) {
			logPassed(passMsg + ": The date is equal to the start date " + startDate);
		} else if (actualDate.isAfter(startDate) && actualDate.isBefore(endDate)) {
			logPassed(passMsg + ": The date " + actualDate + " is within the range.");
		} else if (actualDate.isEqual(endDate)) {
			logPassed(passMsg + ": The date is equal to the end date " + endDate);
		} else {
			logFailed("Date is out of range : The date " + actualDate + " is not within the range (" + startDate
					+ " to " + endDate + ").");
		}
	}

	public static void scrollPage(int xAxis, int yAxis) {
		getPage().mouse().wheel(xAxis, yAxis);
	}

	public static Locator getLocatorInfo(String locatorName) {
		Locator locator = getPage().locator("//mat-label[contains(text(),'" + locatorName
				+ "')]//ancestor::mat-form-field//following-sibling::div//mat-select[@role='combobox']");
		return locator;
	}

	public static void selectMultiSelectDropdown(Locator dropDownLocator, List<String> checkBoxLocators) {
		click(dropDownLocator, "Dropdown");
		for (String locator : checkBoxLocators) {
			Locator checkBoxLocator = getPage()
					.locator("//div[contains(text(),'"+locator+"')]//ancestor::mat-option//mat-pseudo-checkbox");
			if (checkBoxLocator.getAttribute("class").contains("mat-pseudo-checkbox-checked")) {
				logPassed(locator + " is already enabled");
			} else {
				click(checkBoxLocator, locator);
			}
		}
	}
	
	

	public static void selectMultiSelectDropdown(Locator dropDownLocator, List<String> checkBoxLocators,boolean isTrue) {
		click(dropDownLocator, "Dropdown");
		for (String locator : checkBoxLocators) {
			Locator checkBoxLocator = getPage()
					.locator("//div[contains(text(),'"+locator+"')]//ancestor::span//ancestor::mat-option//mat-pseudo-checkbox");
			if (isTrue) {
				click(checkBoxLocator, locator);
			} else {
				click(checkBoxLocator, locator);
			}
		}
	}

	public static String downloadFile(Locator downloadElement) {
		Download download = getPage().waitForDownload(() -> click(downloadElement, "download Icon"));
		String actualFileName = download.suggestedFilename();
		return actualFileName;
	}

	 public boolean isElementNotPresent(Locator locator) {
	        try {
	            return locator.count() == 0 || !locator.isVisible();
	        } catch (Exception e) {
	            // Treat any exception as element not present
	            return true;
	        }
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

	
	public void dragAndDrop(Locator source, Locator target, String sourceName, String targetName) {
	    try {
	        BoundingBox sourceBox = source.boundingBox();
	        BoundingBox targetBox = target.boundingBox();

	        if (sourceBox == null || targetBox == null) {
	            extentTest.get().log(Status.FAIL, "Unable to locate elements for drag and drop.");
	            return;
	        }

	        double startX = sourceBox.x + sourceBox.width / 2;
	        double startY = sourceBox.y + sourceBox.height / 2;
	        double endX = targetBox.x + targetBox.width / 2;
	        double endY = targetBox.y + targetBox.height / 2;

	        getPage().mouse().move(startX, startY);
	        getPage().mouse().down();
	        getPage().mouse().move(endX, endY, new Mouse.MoveOptions().setSteps(20));
	        getPage().mouse().up();

	        extentTest.get().log(Status.PASS, "Dragged '" + sourceName + "' and dropped on '" + targetName + "' successfully.");
	    } catch (Exception e) {
	        extentTest.get().log(Status.FAIL, "Failed drag and drop from '" + sourceName + "' to '" + targetName + "'. Exception: " + e.getMessage());
	    }
	}

	


	public static void uploadNewFile(Locator uploadElement, Path filePath 	, String FileName){
		uploadElement.setInputFiles(filePath.toFile().toPath());
		extentTest.get().log(Status.PASS, "uploaded the file :" + FileName);
	}

	public String getAttribute(Locator element,String attributeName){
		String retrivedText = element.getAttribute(attributeName);
		extentTest.get().log(Status.INFO, "uploaded the file :" + retrivedText);
		return retrivedText;
	}


	public void XflowdragAndDrop(Locator source, Locator target, String sourceName, String targetName) {
	    try {
	        BoundingBox sourceBox = source.boundingBox();
	        BoundingBox targetBox = target.boundingBox();
 
	        if (sourceBox == null || targetBox == null) {
	            extentTest.get().log(Status.FAIL, "Unable to locate elements for drag and drop.");
	            return;
	        }
 
	        double startX = sourceBox.x + sourceBox.width / 2;
	        double startY = sourceBox.y + sourceBox.height / 2;
	        double endX = targetBox.x + targetBox.width / 2;
	        double endY = targetBox.y + targetBox.height / 2;
 
	        getPage().mouse().move(startX, startY);
	        getPage().mouse().down();
	        getPage().mouse().move(endX, endY, new Mouse.MoveOptions().setSteps(20));
	        getPage().mouse().up();
 
	        extentTest.get().log(Status.PASS, "Dragged '" + sourceName + "' and dropped on '" + targetName + "' successfully.");
	    } catch (Exception e) {
	        extentTest.get().log(Status.FAIL, "Failed drag and drop from '" + sourceName + "' to '" + targetName + "'. Exception: " + e.getMessage());
	    }
	}
 
	



}

