package org.playwright.modules;

import java.util.Map;

import org.framework.playwright.utils.UtilityClass;
import org.playwright.pages.AppointmentPage;
import org.playwright.smoketestpages.DashboardPage;


import com.microsoft.playwright.Page;

public class ParentModule extends UtilityClass {

	private static ThreadLocal<AppointmentPage> appointmentPage = new ThreadLocal<>();
	
	public static AppointmentPage getAppointmentPage() {
        return appointmentPage.get();
    }
    
	Map<String, Object> data;

	public ParentModule(Map<String, Object> data, Page page) {
		
		appointmentPage.set(new AppointmentPage(page));	
		this.data = data;
	}
	
}