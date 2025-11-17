package org.framework.playwright.utils;

import static org.framework.playwright.utils.Logger.logFailed;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Field;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Pattern;

import org.framework.playwright.annotations.FindBy;
import org.framework.playwright.constants.FrameWorkConstants;
import org.framework.playwright.listener.ListenerImplimentation;
import org.testng.ITestContext;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.google.gson.Gson;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.BoundingBox;
import com.microsoft.playwright.options.Cookie;
import com.microsoft.playwright.options.WaitForSelectorState;

public class UtilityClass extends ListenerImplimentation {

	public static Properties prop;
	public static ITestContext ctx;
	public static SoftAssert softAssert = new SoftAssert();

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
			if (getPage() != null) {
				getPage().close();
				if (page instanceof ThreadLocal) {
					page.remove(); // Only if it's a ThreadLocal variable
				}
			}
			if (getBrowserContext() != null) {
				getBrowserContext().close();
				if (context instanceof ThreadLocal) {
					context.remove();
				}
			}
			if (getBrowser() != null) {
				getBrowser().close();
				if (browser instanceof ThreadLocal) {
					browser.remove();
				}
			}
			if (getPlaywright() != null) {
				getPlaywright().close();
				if (playwright instanceof ThreadLocal) {
					playwright.remove();
				}
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
		headless = prop.getProperty("Headless").equals("true");
		switch (browserChoice.toLowerCase()) {
		case "chrome":
			browser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless)
					.setArgs(List.of("--start-maximized")).setSlowMo(700)));
			System.out.println("-------------------------------------------------");
			System.out.println("Chrome Browser Started Successfully");
			break;
		case "firefox":
			browser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless)
					.setArgs(List.of("--start-maximized")).setSlowMo(700)));
			System.out.println("fire Fox Browser Started Successfully");
			break;
		}
		context.set(getBrowser().newContext(
				new Browser.NewContextOptions().setAcceptDownloads(true).setViewportSize(null).setBypassCSP(true).setPermissions(Arrays.asList("clipboard-read", "clipboard-write"))));

				//new Browser.NewContextOptions().setAcceptDownloads(true).setViewportSize(1920, 1080).setBypassCSP(true).setPermissions(Arrays.asList("clipboard-read", "clipboard-write"));

		page.set(getBrowserContext().newPage());
		// page.get().context().setDefaultTimeout(30000);
		return getPage();
	}

	public static void waitTillLoading() {
		getPage().waitForTimeout(2000);
		getPage().waitForSelector("//mat-spinner[@mode='indeterminate']",
				new Page.WaitForSelectorOptions().setState(WaitForSelectorState.HIDDEN));
	}

	public void launchUrl(String url, Page.NavigateOptions navigateOptions) {
		initialization();
		getPage().navigate(url, navigateOptions);
	}

	// Generic method to load the cookies from the Json files
	private static void loadAndUseCookies(BrowserContext context) {
		try (Reader reader = new FileReader(
				System.getProperty("user.dir") + "/src/main/resources/airIndiaCookies.json")) {
			Gson gson = new Gson();
			List<Map<String, String>> cookieList = gson.fromJson(reader, List.class);

			List<Cookie> cookies = new ArrayList<>();
			for (Map<String, String> cookieMap : cookieList) {
				Cookie cookie = new Cookie(cookieMap.get("name"), cookieMap.get("value"));
				cookie.setDomain(cookieMap.get("domain"));
				cookie.setPath(cookieMap.get("path"));

				String expires = cookieMap.get("expires");
				if (expires != null && !"null".equals(expires) && !expires.equals("-1.0")) {
					try {
						if (expires.matches("[+-]?[0-9]*\\.?[0-9]+[eE][+-]?[0-9]+")) {
							long expiresMillis = (long) Double.parseDouble(expires); // Convert to long value
							cookie.setExpires(expiresMillis); // Set the expiration in seconds
						} else {
							SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
							Date expiresDate = sdf.parse(expires); // Attempt to parse the date
							long expiresMillis = expiresDate.getTime(); // Convert to milliseconds
							cookie.setExpires(expiresMillis / 1000.0); // Convert to seconds and set as double
						}
					} catch (ParseException e) {
						System.err.println("Failed to parse expires value: " + expires);
						e.printStackTrace();
					}
				}

				cookie.setHttpOnly(Boolean.parseBoolean(cookieMap.get("httpOnly")));
				cookie.setSecure(Boolean.parseBoolean(cookieMap.get("secure")));
				cookies.add(cookie);
			}

			context.addCookies(cookies);
			System.out.println("Cookies loaded and added to context");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Generic method to save the cookies from the application
	public static void saveCookiesToJson(Page page) {
		try (Writer writer = new FileWriter("cookies.json")) {
			List<Cookie> cookies = page.context().cookies();
			Gson gson = new Gson();
			List<Map<String, Object>> cookieList = new ArrayList<>();
			for (Cookie cookie : cookies) {
				Map<String, Object> cookieMap = new HashMap<>();
				cookieMap.put("name", cookie.name);
				cookieMap.put("value", cookie.value);
				cookieMap.put("domain", cookie.domain);
				cookieMap.put("path", cookie.path);
				cookieMap.put("expires", cookie.expires);
				cookieMap.put("httpOnly", cookie.httpOnly);
				cookieMap.put("secure", cookie.secure);
				cookieList.add(cookieMap);
			}
			gson.toJson(cookieList, writer);
			System.out.println("Cookies saved to cookies.json");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void captureScreenshotForListener(String path, String screenshotName) {
		page.get().waitForTimeout(1000);
		path = path + screenshotName + new JavaUtility().getSystemDate() + ".png";
		page.get().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)));
	}

	public String captureScreenshot(boolean failedScreenshot, String screenshotName) {
		page.get().waitForTimeout(1000);
		String path = "";
		if (failedScreenshot) {
			path = "./failed-screenshots/" + screenshotName + new JavaUtility().getSystemDate() + ".png";
		} else {
			path = "./screenshots/" + screenshotName + new JavaUtility().getSystemDate() + ".png";
		}
		page.get().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)));
		return path;
	}

	public void captureScreenshotForPublishing(String testScriptName) {
		page.get().screenshot(new Page.ScreenshotOptions()
				.setPath(Paths.get("./PublishFailedScreenshots/" + testScriptName + ".png")));
	}

	private static BrowserContext createContextWithToken(String accessToken) {
		BrowserContext context = browser.get().newContext();
		Page page = context.newPage();
		page.evaluate("token => window.localStorage.setItem('accessToken', token);", accessToken);
		return context;
	}

	public static BrowserContext getAccessToken(String url) {
		APIRequestContext requestContext = playwright.get().request()
				.newContext(new APIRequest.NewContextOptions().setHttpCredentials("emailId", "password"));
		requestContext.get(url);
		String state = requestContext.storageState();
		BrowserContext context = browser.get().newContext(new Browser.NewContextOptions().setStorageState(state));
		return context;
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

	public static void removeElements(List<Locator> elements) {
		for (int i = elements.size(); i >= 0; i--) {
			Locator element = elements.get(i);
			element.click();
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

	public static List<Locator> getLocators(Locator element) {
		List<Locator> elements = new ArrayList<>();
		int count = element.count();
		for (int i = 0; i < count; i++) {
			elements.add(element.nth(i));
		}
		return elements;
	}

	public void scrollDown() {
		page.get().mouse().wheel(0, 1000);
	}

	static class CustomBoundingBox {
		double x, y, width, height;

		CustomBoundingBox(double x, double y, double width, double height) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}
	}

	private static CustomBoundingBox combineBoundingBoxes(CustomBoundingBox box1, CustomBoundingBox box2) {
		double x = Math.min(box1.x, box2.x);
		double y = Math.min(box1.y, box2.y);
		double width = Math.max(box1.x + box1.width, box2.x + box2.width) - x;
		double height = Math.max(box1.y + box1.height, box2.y + box2.height) - y;

		return new CustomBoundingBox(x, y, width, height);
	}

	public static void clickElement(Locator element, String elementName) {
		element.click();
		extentTest.get().log(Status.INFO, "Clicked on " + elementName);
	}

	public static Map<String, Object> trimMap(Map<String, Object> originalMap) {
		Map<String, Object> trimmedMap = new HashMap<>();
		for (Map.Entry<String, Object> entry : originalMap.entrySet()) {
			String trimmedKey = entry.getKey().trim();
			Object value = entry.getValue();
			if (value instanceof String) {
				String trimmedValue = ((String) value).trim();
				trimmedMap.put(trimmedKey, trimmedValue);
			} else {
				trimmedMap.put(trimmedKey, value);
			}
		}
		return trimmedMap;
	}

	public boolean isElementExists(Locator locator) {
		return locator.count() > 0;
	}

	public void wasteClickUsingJavaScript(String xpath) {
		getPage().evaluate("xpath => { "
				+ "const element = document.evaluate(xpath, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue; "
				+ "if (element) element.click(); " + "}", xpath);
	}

	private static final String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz" + "0123456789";

	/**
	 * Builds a random alphanumeric string of exactly `length` chars.
	 */
	public String generateRandomString(int length) {
		SecureRandom rnd = new SecureRandom();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int idx = rnd.nextInt(CHAR_POOL.length());
			sb.append(CHAR_POOL.charAt(idx));
		}
		return sb.toString();
	}

	private static final String CHAR_POOL_WITH_SPECIAL_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
			+ "abcdefghijklmnopqrstuvwxyz" + "0123456789" + "!@#$%^&*()-_=+[]{}|;:'\",.<>?/`~";

	private static final SecureRandom RNG = new SecureRandom();

	/**
	 * Generates a random string of exactly `length` characters, using alphanumeric
	 * + special chars.
	 */
	public static String generateRandomStringWithSpecialCharacters(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			int idx = RNG.nextInt(CHAR_POOL_WITH_SPECIAL_CHARACTERS.length());
			sb.append(CHAR_POOL_WITH_SPECIAL_CHARACTERS.charAt(idx));
		}
		return sb.toString();
	}

	public static void wasteClickInBetweenTheScreen() {
		getPage().locator("//div[@class='cdk-overlay-container']").click();
	}

	public static String getTextToastMessage() {
		Locator toastMessageElement = getPage().locator("//div[@class='mat-mdc-snack-bar-label mdc-snackbar__label']");
		return getTextToastMessage(toastMessageElement).trim();
	}

	public static String getTextToastMessage(Locator locator) {
		String text = null;
		try {
			text = locator.textContent();
			extentTest.get().log(Status.INFO, "Retrived Text: " + text);
			// extentTest.get().log(LogStatus.INFO, extentTest.get().addScreenCapture("." +
			// new UtilityClass().captureScreenshot(false, "Text")));
		} catch (Exception e) {
			logFailed(e.toString());
			extentTest.get().log(Status.INFO, "failed to retrive Text");
			// extentTest.get().log(LogStatus.INFO, extentTest.get().addScreenCapture("." +
			// new UtilityClass().captureScreenshot(false, "String")));
		}
		return text;
	}

	public static void waitForSuccessMessageToAppearAndDissapear() {
		if (getPage().locator("//div[@aria-live='assertive']").isVisible()) {
			getPage().locator("//div[@aria-live='assertive']")
					.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
			getPage().locator("//div[@aria-live='assertive']")
					.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
		}
	}

	private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile("[^a-zA-Z0-9]");

	/**
	 * Replaces all non-alphanumeric characters in the input with '_'.
	 *
	 * @param input the string to sanitize; may be null
	 * @return a new string where every "special" character is '_' (or null if input
	 *         was null)
	 */
	public static String replaceSpecialCharacters(String input) {
		if (input == null) {
			return null;
		}
		return SPECIAL_CHAR_PATTERN.matcher(input).replaceAll("_");
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

	public static String getExtension(String filename) {
		int idx = filename.lastIndexOf('.');
		return (idx >= 0) ? filename.substring(idx) : "";
	}

	public static void wasteDropdownClick(String locator) {
		Locator element = getPage().locator(locator);
		BoundingBox box = element.boundingBox();
		if (box != null) {
			getPage().mouse().click(box.x + box.width + 10, box.y + box.height + 10);
		}
	}

	public static void waitTillAnElementIsVisible(String xPath) {
		getPage().locator(xPath).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
	}

	public static void waitTillAnElementIsHidden(String xPath) {
		getPage().locator(xPath).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
	}

	public Path getPathUsingPartialName(String directoryPath, String partialName) {
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(directoryPath),
				path -> path.getFileName().toString().contains(partialName))) {
			for (Path entry : stream) {
				return entry;
			}
		} catch (IOException e) {
			System.err.println("Error while searching for file: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public void renamePdfAndConcatinateNumber(String originalPath) {
		File originalFile = new File(originalPath);
		int randomNumber = new JavaUtility().getRandomNumber(10000);

		// Extract base name and extension
		String fileName = originalFile.getName(); // abc123.pdf
		String parentPath = originalFile.getParent(); // Folder path

		// Use regex to split name and number
		//String newFileName = fileName.replaceAll("(\\D+)(\\d+)(\\.\\w+)", "$1" + randomNumber + "$3");
		String newFileName = fileName.replaceAll("(doc_)(\\d{8}_\\d{6})(_.+\\.pdf)", "$1" + randomNumber + "$3");

		
		File renamedFile = new File(parentPath + File.separator + newFileName);
		
		if (originalFile.renameTo(renamedFile)) {
            System.out.println("File renamed from "+fileName+" to: " + renamedFile.getName());
        } else {
            System.out.println("Rename failed.");
        }
	}
	
	public void renameExcelAndConcatinateNumber(String originalPath) {
		File originalFile = new File(originalPath);
		int randomNumber = new JavaUtility().getRandomNumber(10000);
		
		// Extract base name and extension
		String fileName = originalFile.getName(); // abc123.pdf
		String parentPath = originalFile.getParent(); // Folder path
		
		// Use regex to split name and number
		//String newFileName = fileName.replaceAll("(\\D+)(\\d+)(\\.\\w+)", "$1" + randomNumber + "$3");
        String newFileName = fileName.replaceAll("^(sales).*\\.xlsx$", "$1_" + randomNumber + ".xlsx");
		
		
		File renamedFile = new File(parentPath + File.separator + newFileName);
		
		if (originalFile.renameTo(renamedFile)) {
			System.out.println("File renamed from "+fileName+" to: " + renamedFile.getName());
		} else {
			System.out.println("Rename failed.");
		}
	}
}

