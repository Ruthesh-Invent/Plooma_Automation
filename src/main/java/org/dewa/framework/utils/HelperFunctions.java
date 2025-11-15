package org.dewa.framework.utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.dewa.framework.api_utility.ApiUtility;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HelperFunctions extends UtilityClass {

	private static final String CONFIG_FILE = System.getProperty("user.dir") + "\\src\\main\\resources\\api_auth.json";
	private static final String SUBS_FILE = System.getProperty("user.dir") + "\\src\\main\\resources\\subs.json";
	private static final String FINON_FILE = System.getProperty("user.dir") + "\\src\\main\\resources\\finon.json";

	public static void login(Page page, String emailId, String password) {

		Locator username = page.locator("//input[@formcontrolname='username']");
		username.fill(emailId);
		Locator passwordTF = page.locator("//input[@formcontrolname='password']");
		passwordTF.fill(password);
		Locator clickLogin = page.locator("//button[@class='cy-login-btn primary']");
		clickLogin.click();
//		page.waitForTimeout(10000);
//		String authToken = ApiUtility.getAuthTokenFromLogin();
//		saveAuthToken(authToken);
		System.out.println("Login is done");

	}

	public static void dashboardLogin(Page page, String emailId, String password) {
		Locator username = page.locator("//input[@formcontrolname='username']");
		username.fill(emailId);
		Locator passwordTF = page.locator("//input[@formcontrolname='password']");
		passwordTF.fill(password);
		Locator clickLogin = page.locator("//button[@class='cy-login-btn primary']");
		clickLogin.click();
		page.waitForTimeout(10000);
		String authToken = ApiUtility.getAuthTokenFromLogin();
		saveAuthToken(authToken, "Token");
		System.out.println("Login is done Successfully");
	}

	public static void saveAuthToken(String token, String fileName) {
		String filePath=null;
		if(fileName.contains("Token")) {
			filePath=CONFIG_FILE;
			try (FileWriter writer = new FileWriter(filePath)) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("auth_token", "Bearer " + token);
				writer.write(jsonObject.toString(4)); // Pretty print JSON
				System.out.println(fileName+" saved to " + filePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(fileName.contains("SubsId")) {
			filePath=SUBS_FILE;
			try (FileWriter writer = new FileWriter(filePath)) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("subsId", token);
				writer.write(jsonObject.toString(4)); // Pretty print JSON
				System.out.println(fileName+" saved to " + filePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(fileName.contains("FinonId")) {
			filePath=FINON_FILE;
			try (FileWriter writer = new FileWriter(filePath)) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("finonId", token);
				writer.write(jsonObject.toString(4)); // Pretty print JSON
				System.out.println(fileName+" saved to " + filePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String getAuthToken(String fileName) {
		String filePath=null;
		String key=null;
		if(fileName.contains("Token")) {
			filePath=CONFIG_FILE;
			key="auth_token";
		}
		else if(fileName.contains("SubsId")) {
			filePath=SUBS_FILE;
			key="subsId";
		}
		else if(fileName.contains("FinonId")) {
			filePath=FINON_FILE;
			key="finonId";
		}
		
		try (FileReader reader = new FileReader(filePath)) {
			JSONObject jsonObject = new JSONObject(new JSONTokener(reader));
			return jsonObject.getString(key);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
//	public static void saveSubsId(String id) {
//		try (FileWriter writer = new FileWriter(SUBS_FILE)) {
//			JSONObject jsonObject = new JSONObject();
//			jsonObject.put("subs_id", id);
//			writer.write(jsonObject.toString(4)); // Pretty print JSON
//			System.out.println("Subscription Id saved to " + SUBS_FILE);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	public static void logout() {
		getPage().locator("//icon[@name='studio/new_profile']").click();
		getPage().locator("//button[@iconname='logout']").click();
		getPage().locator("//button[@id='proceed']").click();
	}

//    boolean res=response.url().contains("/api/account/login");
//    if (res) {  // Check for login API URL
//        try {
//        	response.finished();
//            String responseBody = response.text(); // Get API response body
//            JSONObject json = new JSONObject(responseBody);
//            System.out.println(responseBody);
//            
//            // Extract AccessToken
//            if (json.has("AccessToken")) {
//                String authToken = json.getString("AccessToken");
//                saveAuthToken(authToken);
//                System.out.println("✅ Captured Auth Token: " + authToken);
//            } else {
//                System.out.println("❌ Token not found in response!");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
	
	public static void main(String[] args) {
		System.out.println(getAuthToken("FinonId"));;
	}

}
