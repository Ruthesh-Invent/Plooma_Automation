package org.framework.playwright.utils;


import net.datafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataFaker {
    private static final Faker faker = new Faker(new Locale("en-US"));

    public static String generateFakeName() {
        return faker.name().fullName();
    }

    public static String generateFakeDescription() {
        return faker.lorem().sentence();
    }

    public static String generateFakeParagraph(){
        return faker.lorem().paragraph();
    }

    public static String generateFakeAddress() {
        return faker.address().fullAddress();
    }

    public static String generateFakeCompanyName() {
        return faker.company().name();
    }

    public static String generateFakePhoneNumber() {
        return faker.phoneNumber().phoneNumber();
    }
    public static String generateFakeModelName(){
        return faker.largeLanguageModel().textModel();
    }

    public static String generateFakeApiKey(){
        return faker.code().toString();
    }

    public static String generateFakeVersion(){
        return faker.idNumber().toString();
    }

    public static String getRandomUrl() {
		return "https://" + faker.internet().url().replace("-", "").replace("'", "");
	}
    
    public static String generateRandomString(int length) {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		Random random=new Random();
		StringBuilder name=new StringBuilder(length);
		
		for(int i=0;i<length;i++) {
			name.append(characters.charAt(random.nextInt(characters.length())));
		}
		
		return name.toString();
	}
    
    public static String generateFirstName() {
        return faker.name().firstName();
    }

    public static String generateLastName() {
        return faker.name().lastName();
    }
    
    public static String generateEmail() {
        return faker.internet().emailAddress();
    }
    
    public static String generateStreetAddress() {
        return faker.address().streetAddress();
    }

    /* ---------- Date of Birth (18–60 years) ---------- */

    public static String generateDOB() {

        LocalDate dob = faker.date()
                .birthday(18, 60)
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return dob.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    /* ---------- Mobile (starts with 5 or 6) ---------- */

    public static String generateUSMobileStartingWith5or6() {

        String firstDigit = faker.options().option("5", "6");
        String remainingDigits = faker.number().digits(9);

        return firstDigit + remainingDigits;
    }
    public static String generateInsuranceCompanyName() {
        return faker.company().name() + " Insurance";
    }
    public static String generatePolicyNumber() {
        return "POL-" + faker.bothify("####-????");
    }
    public static String generatePolicyStartDate() {
        LocalDate startDate = faker.date()
                .past(365 * 5, java.util.concurrent.TimeUnit.DAYS)
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        return startDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

}
   

