package org.playwright.modules;

import java.util.Map;

import org.testng.Assert;

import com.microsoft.playwright.Page;

public class AppointmentModule extends ParentModule {

	public AppointmentModule(Map<String, Object> data, Page page) {
		super(data, page);
	}

	        public void appointmentcreation() {
	    	getAppointmentPage().clickMainModule("Appointments");
	    	getAppointmentPage().clickSubmodule("Appointments List");
	    	  int beforeCount = getAppointmentPage().getTotalAppointmentCount();
	          System.out.println("Before count = " + beforeCount);
	    	getAppointmentPage().clickAddapointment();
	    	getAppointmentPage().selectDropdownByFieldNameAndValue("Provider", "Kathryn Rowe");
	    	getAppointmentPage().selectDropdownByFieldNameAndValue("Patient", "Steve Smith");
	    	getAppointmentPage().selectDropdownByFieldNameAndValue("Visit Reason", "OT Evaluation");
	    	getAppointmentPage().selectDropdownByFieldNameAndValue("Service Location", "MLee Therapy");
	    	getAppointmentPage().saveAppointmentHandleAllPopups();
	    	//getAppointmentPage().clickYes();
	    	  int afterCount = getAppointmentPage().getTotalAppointmentCount();
	          System.out.println("After count = " + afterCount);
	          Assert.assertTrue(afterCount > beforeCount,
	                  "Appointment was NOT added! Count did NOT increase.");
	          System.out.println("Appointment creation validated by count increase.");
	      }
	  }
