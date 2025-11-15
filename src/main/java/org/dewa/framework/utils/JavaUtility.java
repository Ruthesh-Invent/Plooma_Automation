package org.dewa.framework.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class JavaUtility {

	public int getRandomNumber(int range) {
		Random random=new Random();
		return random.nextInt(range);
	}
	
	public String getSystemDate() {
		Date date=new Date();
		return date.toString().replace(" ", "_").replace(":", "_");
	}

	public static String getCurrentDateAndTime(){
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
		String formattedDate = dateTime.format(formatter);
		return formattedDate;
	}
	
	public String convertDateFormat(String inputFormat, String outputFormat, String inputDate) {
	        LocalDate date = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern(inputFormat));
	        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(outputFormat);
	        String outputDate = date.format(outputFormatter);
	        return outputDate;
	}
	
	public String getMonthYear(String date) {
        // Parse the input date string
        LocalDate localDate = LocalDate.parse(date + "-01");
        
        // Get the month and year
        String month = localDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        int year = localDate.getYear();
        
        // Return the formatted string
        return month + " " + year;
    }
	
	
}
