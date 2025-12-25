package org.playwright.modules;

import java.util.Map;

import org.framework.playwright.utils.UtilityClass;
import org.playwright.pages.AppointmentPage;
import org.playwright.pages.EncounterPage;
import org.playwright.smoketestpages.DashboardPage;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.microsoft.playwright.Page;

public class ParentModule extends UtilityClass {

	private static ThreadLocal<AppointmentPage> appointmentPage = new ThreadLocal<>();
	private static ThreadLocal<DashboardPage> dashboardPage = new ThreadLocal<>();
    private static final ThreadLocal<EncounterPage> encounterPage = new ThreadLocal<>();


	public static AppointmentPage getAppointmentPage() {
		return appointmentPage.get();
	}

	public static DashboardPage getDashboardPage() {
		return dashboardPage.get();
	}
	
	 public static EncounterPage getEncounterPage() {
	        return encounterPage.get();
	    }
	 

	Map<String, Object> data;

	public ParentModule(Map<String, Object> data, Page page) {

		dashboardPage.set(new DashboardPage(page));
		appointmentPage.set(new AppointmentPage(page));
		encounterPage.set(new EncounterPage(page));
		this.data = data;
	}

}