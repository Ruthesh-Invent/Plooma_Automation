package org.framework.playwright.utils;

import org.testng.Assert;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;

public class HelperFunctions extends UtilityClass{


    public static void login(Page page, String orgcode, String username, String password) {
        try {
            page.waitForSelector("//mat-label[normalize-space()='Orgcode']");
            Locator orgcodeField = page.locator("//mat-label[text()='Orgcode']/ancestor::mat-form-field//input");
            orgcodeField.fill(orgcode);
            Locator usernameField = page.locator("//mat-label[text()='Username']/ancestor::mat-form-field//input");
            usernameField.fill(username);
            Locator passwordField = page.locator("//mat-label[text()='Password']/ancestor::mat-form-field//input");
            passwordField.fill(password);
            Locator loginButton = page.locator("//span[contains(text(),'Login')]");
            loginButton.click();
            System.out.println("Login attempted using Orgcode + Username + Password");

            Locator error = page.locator("//div[contains(text(),'Something went wrong')]");
            if (error.isVisible()) {
                Assert.fail("Login failed — 'Something went wrong' appeared.");
            }
            System.out.println("Login successful!");

        } catch (PlaywrightException e) {
            Assert.fail("Login failed due to Playwright exception: " + e.getMessage());
        }
    }
    
    public static void logout() {
        try {
            Page page = getPage();
            page.locator("//img[@alt='User']").click();
            System.out.println("Clicked on User profile");
            page.locator("//a[normalize-space(text())='Logout']").click();
            System.out.println("Clicked on Logout");

            System.out.println("Logout successful");
        } catch (Exception e) {
            System.err.println("Error during logout: " + e.getMessage());
        }
    }


    public static void loadLoginState(BrowserContext context) {
        try {
            context.addCookies(context.cookies("state.json"));
            System.out.println( "Login state loaded successfully");
        } catch (Exception e) {
            System.out.println( "Failed to load login state");
        }
    }
}
