package org.playwright.modules;

import java.util.Map;

import org.framework.playwright.utils.UtilityClass;
import org.playwright.pages.AppointmentPage;
import org.playwright.smoketestpages.DashboardPage;
import org.playwright.smoketestpages.SmokeStudioPage;

import com.microsoft.playwright.Page;

public class ParentModule extends UtilityClass {

	private static ThreadLocal<SmokeStudioPage> smokeStudioPage = new ThreadLocal<>();
	private static ThreadLocal<AppointmentPage> appointmentPage = new ThreadLocal<>();
	
	public static SmokeStudioPage getSmokeStudioPage() {
		return smokeStudioPage.get();
	}
	
	public static AppointmentPage getAppointmentPage() {
        return appointmentPage.get();
    }
    
	Map<String, Object> data;

	public ParentModule(Map<String, Object> data, Page page) {
		
		smokeStudioPage.set(new SmokeStudioPage(page));
		appointmentPage.set(new AppointmentPage(page));	
		this.data = data;
	}
	
}