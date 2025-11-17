package org.framework.playwright.utils;


import net.datafaker.Faker;

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
    
   
}

