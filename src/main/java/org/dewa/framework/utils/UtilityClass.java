package org.dewa.framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

import org.dewa.framework.annotations.FindBy;
import org.dewa.framework.constants.FrameWorkConstants;
import org.dewa.framework.listener.ListenerImplementation;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Frame;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.BoundingBox;
import com.microsoft.playwright.options.WaitForSelectorState;

public class UtilityClass extends ListenerImplementation {

	public static Properties prop;

	private static ThreadLocal<Playwright> playwright = new ThreadLocal<Playwright>();
	private static ThreadLocal<Browser> browser = new ThreadLocal<Browser>();
	private static ThreadLocal<BrowserContext> context = new ThreadLocal<BrowserContext>();
	private static ThreadLocal<Page> page = new ThreadLocal<Page>();

	public static Playwright getPlaywright() {
		return playwright.get();
	}

	public static Browser getBrowser() {
		return browser.get();
	}

	public static BrowserContext getBrowserContext() {
		return context.get();
	}

	public static Page getPage() {
		return page.get();
	}

	public static void unload() {
		try {
			if (getPage() != null && !getPage().isClosed()) {
				getPage().close();
				page.remove();
			}

			if (getBrowserContext() != null) {
				getBrowserContext().close();
				context.remove();
			}

			if (getBrowser() != null) {
				getBrowser().close();
				browser.remove();
			}

			if (getPlaywright() != null) {
				getPlaywright().close();
				playwright.remove();
			}
		} catch (Exception e) {
			System.err.println("Error during unload: " + e.getMessage());
			e.printStackTrace();
		}
	}

	static {
		prop = new Properties();
		String propath = FrameWorkConstants.PROP_PATH;
		try (FileInputStream fis = new FileInputStream(propath)) {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Page initialization() {
		playwright.set(Playwright.create());
		String browserChoice = prop.getProperty("browser");
		boolean headless;
		headless = prop.getProperty("headless").equals("true");
		switch (browserChoice.toLowerCase()) {
		case "chrome":
			browser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless)
					.setArgs(List.of("--start-maximized")).setSlowMo(1000)));
			System.out.println("-------------------------------------------------");
			System.out.println("Chrome Browser Started");
			break;
		case "firefox":
			browser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless)
					.setArgs(List.of("--start-maximized")).setSlowMo(600)));
			System.out.println("fire Fox Browser Started Successfully");
			break;
		}
		// 1920, 1080
		context.set(getBrowser()
				.newContext(new Browser.NewContextOptions().setAcceptDownloads(true).setViewportSize(null)));
		page.set(getBrowserContext().newPage());
		page.get().setDefaultTimeout(30000);
		return getPage();
	}

	public String captureScreenshotForListener(String path, String screenshotName) {
		page.get().waitForTimeout(1000);
		path = path + screenshotName + new JavaUtility().getSystemDate() + ".png";
		page.get().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)));
		return path;
	}

	public void captureScreenshot(boolean failedScreenshot, String screenshotName) {
		// Ensure screenshots are saved inside the reports directory
		String screenshotsDir = Paths.get(FrameWorkConstants.REPORTS_PATH, "screenshots").toString();
		File reportsDir = new File(screenshotsDir);
		if (!reportsDir.exists()) {
			reportsDir.mkdirs(); // Create the directory if it does not exist
		}

		// Generate screenshot file name
		String screenshotFileName = screenshotName + "_" + new JavaUtility().getSystemDate() + ".png";
		String screenshotPath = Paths.get(screenshotsDir, screenshotFileName).toString();

		// Capture the screenshot
		page.get().waitForTimeout(1000);
		page.get().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)));

		try {
			// Use relative path from report to screenshots folder
			String relativePath = screenshotPath;
			File screenshotFile = new File(relativePath);
			if (screenshotFile.exists()) {
				System.out.println("Screenshot exists: " + relativePath);
				extentTest.get().log(Status.INFO, "Screenshot",
						MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());
			} else {
				System.out.println("Screenshot NOT FOUND: " + relativePath);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void captureScreenshotForPublishing(String testScriptName, String screenshotFor) {
		String basePath = null;
		if (screenshotFor.contains("skip")) {
			basePath = Paths.get(System.getProperty("user.dir"), "SkippedScreenshots").toString();
		} else if (screenshotFor.contains("fail")) {
			basePath = Paths.get(System.getProperty("user.dir"), "FailedScreenshots").toString();
		}
		String path = basePath + File.separator + testScriptName + ".png";
		page.get().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)));
	}

	public static void waitTillLoading() {
		getPage().waitForTimeout(2000);
		if (getPage().locator("//mat-spinner[@mode='indeterminate']").first().isVisible()) {
//			getPage().waitForSelector("//mat-spinner[@mode='indeterminate']",
//					new Page.WaitForSelectorOptions().setState(WaitForSelectorState.HIDDEN));
			while (!getPage().locator("//mat-spinner[@mode='indeterminate']").allInnerTexts().isEmpty()) {
				getPage().waitForTimeout(500); // Small wait before checking again
			}
		}
	}

	public static void wasteClickInBetweenTheScreen() {
		getPage().locator("//div[@class='cdk-overlay-container']").click();
	}

	public static void wasteDropdownClick(String locator) {
		Locator element = getPage().locator(locator);
		BoundingBox box = element.boundingBox();

		// Click outside the element
		if (box != null) {
			getPage().mouse().click(box.x + box.width + 10, box.y + box.height + 10);
		}
	}

	public static Page switchToWindowIndex(int index) {
		List<Page> allPages = context.get().pages();
		Page reqWindow = allPages.get(index);
		if (index < 0 || index >= allPages.size()) {
			throw new IllegalArgumentException("Invalid index: " + index);
		}
		reqWindow.bringToFront();
		return reqWindow;
	}

	public static void close() {
		if (browser != null) {
			browser.get().close();
		}
		if (playwright != null) {
			playwright.get().close();
		}
	}

	public static void initElements(Page page, Object obj) {
		Class<?> clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();

		for (Field field : fields) {
			if (field.getType().isAssignableFrom(Locator.class)) {
				if (field.isAnnotationPresent(FindBy.class)) {
					FindBy findBy = field.getAnnotation(FindBy.class);
					String selector = findBy.xpath();

					if (!selector.isEmpty()) {
						try {
							field.setAccessible(true);
							field.set(obj, page.locator(selector));
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	public void scrollDown() {
		page.get().mouse().wheel(0, 1000);
	}

	public void wasteClickUsingJavaScript(String xpath) {
		getPage().evaluate("xpath => { "
				+ "const element = document.evaluate(xpath, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue; "
				+ "if (element) element.click(); " + "}", xpath);
	}

	public String getTitleFromPopupFrame() {
		Locator iframeLocator = getPage().locator("//iframe[@class='w-full h-full']");
		Frame frame = iframeLocator.elementHandle().contentFrame();
		frame.locator("//a[@class='capitalize']")
				.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		String title = frame.locator("//a[@class='capitalize']").textContent().trim();
		return title;
	}

	public void closePopupFrame() {
		Locator iframeLocator = getPage().locator("//iframe[@class='w-full h-full']");
		Frame frame = iframeLocator.elementHandle().contentFrame();
		frame.locator("//button[@tooltip='Close']").click();
	}

	public Frame getFrame() {
		Locator iframeLocator = getPage().locator("//iframe[@class='w-full h-full']");
		Frame frame = iframeLocator.elementHandle().contentFrame();
		return frame;
	}

	public static void downloadPdfSetup(String downloadPath, String[] additionalPath, Page page) {
		page.onDownload(download -> {
			try {
				// Ensure the base download directory exists
				File downloadDir = new File(downloadPath);
				if (!downloadDir.exists()) {
					downloadDir.mkdirs();
				}

				// Construct the full file path
				String originalFileName = additionalPath[0];
				String fullPath = downloadPath + "\\" + originalFileName;

				// Save the file
				download.saveAs(Paths.get(fullPath));
				// System.out.println("File saved to: " + fullPath);
			} catch (PlaywrightException e) {
				// System.err.println("Failed to save download: " + e.getMessage());
				e.printStackTrace();
			} catch (Exception e) {
				System.err.println("Unexpected error: " + e.getMessage());
				e.printStackTrace();
			}
		});
	}

	// Not working as expected
	// To Use :
	// scrollInsideTheScrollBarTillEnd("div.flex.flex-col.h-full.modal.w-80.bot-card");

	public void scrollInsideTheScrollBarTillEnd(String scrollElementLocator) {
		// String containerSelector = ".your-scrollable-container-class"; // e.g.,
		// "#chatBox", ".log-container"

		int previousHeight = 0;
		int currentHeight = 1;

		while (previousHeight != currentHeight) {
			previousHeight = currentHeight;

			// Scroll to the bottom of the scrollable container
			getPage().evaluate("selector => {" + "const el = document.querySelector(selector);"
					+ "el.scrollTop = el.scrollHeight;" + "}", scrollElementLocator);

			// Wait for new items to load
			getPage().waitForTimeout(1000); // Adjust timeout if needed

			// Get current scroll height
			currentHeight = (int) getPage().evaluate("selector => document.querySelector(selector).scrollHeight",
					scrollElementLocator);
		}
	}

	public void waitUntillProgressBar() {
		Locator progressBars = getPage().locator("//div[@role='progressbar']");
		int count = progressBars.count();

		for (int i = 0; i < count; i++) {
			progressBars.nth(i)
					.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN).setTimeout(10000)); // Optional:
		}
	}
	
	public void waitForSuccessMessageToAppearAndDissapear() {
		getPage().locator("//div[@aria-live='assertive']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		getPage().locator("//div[@aria-live='assertive']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
	}

}
