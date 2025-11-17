package smoke_test_cases;

import java.util.HashMap;
import java.util.Map;

import org.framework.playwright.listener.RetryListener;
import org.framework.playwright.utils.DataFaker;
import org.playwright.dataproviderclass.DataProviderClasses;
import org.playwright.modules.AppointmentModule;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import baseFunctions.CommonFunctions;

@Listeners(org.framework.playwright.listener.ListenerImplimentation.class)
public class AppointmentPageSmokeTest extends CommonFunctions {

	@BeforeClass(alwaysRun = true)
	public void launchApplication() {
	    CommonFunctions common = new CommonFunctions();

	    String url = common.getUrl();
	    String orgCode = prop.getProperty("Orgcode");
	    String username = prop.getProperty("Username");
	    String password = prop.getProperty("Password");
	    common.launchApplication(url, orgCode, username, password);
	}

	
	@Test(enabled = true,groups = { "Smoke" },retryAnalyzer = RetryListener.class)
	    public void appointmentCreationTest() {
	    AppointmentModule appointment = new AppointmentModule(null, getPage());
	    appointment.appointmentcreation();
	}
}