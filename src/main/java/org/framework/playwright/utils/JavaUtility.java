package org.framework.playwright.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
		return date.toString().replace(" ", "-").replace(":", "-");
	}

	public String getSystemDate(String pattern) {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern);
		return LocalDateTime.now().format(fmt);
	}

	public static String getCurrentDateAndTime(){
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
		String formattedDate = dateTime.format(formatter);
		return formattedDate;
	}
	
	public String convertDateFormat(String inputFormat, String outputFormat, String inputDate) {
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(inputFormat);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(outputFormat);
        LocalDate date = LocalDate.parse(inputDate, inputFormatter);
        return date.format(outputFormatter);
	}

	
	
	
}
