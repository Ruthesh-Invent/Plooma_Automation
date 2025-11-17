package org.framework.playwright.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.framework.playwright.listener.ListenerImplimentation;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.Status;


public class Logger extends ListenerImplimentation {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Logger.class);

    public static void logPassed(String msg, String expected, String actual) {
		extentTest.get().log(Status.PASS,
				msg + "<br><b>Expected</b> : "+expected +" <b>Actual</b>: "+actual);
	}

	public static void logPassed(String msg, int expected, int actual) {
		extentTest.get().log(Status.PASS,
				msg + "<br><b>Expected</b> : "+expected +" <b>Actual</b>: "+actual);
	}

	public static void logPassed(String msg, List<String> expected, List<String> actual) {
		extentTest.get().log(Status.PASS, msg);
	}

	public static void logPassed(String msg, Set<String> expected, Set<String> actual) {
		extentTest.get().log(Status.PASS, msg);
	}

	public static void logFailed(String msg, String expected, String actual) {
		extentTest.get().log(Status.SKIP,
				msg + "<br><b>Expected</b> : "+expected +" <b>Actual</b>: "+actual);
	}

	public static void logFailed(String msg, int expected, int actual) {
		extentTest.get().log(Status.SKIP,
				msg + "<br><b>Expected</b> : "+expected +" <b>Actual</b>: "+actual);
	}

	public static void logFailed(String msg, List<String> expected, List<String> actual) {
		extentTest.get().log(Status.FAIL, msg);
	}

	public static void logFailed(String msg, Set<String> expected, Set<String> actual) {
		extentTest.get().log(Status.FAIL,
				msg + "<br><b>Expected</b> : "+expected +" <b>Actual</b>: "+actual);
	}

	public static <K, V> void logPassed(String msg, Map<K, V> expected, Object actual) {
		extentTest.get().log(Status.PASS, msg);
	}

	public static <K, V> void logFailed(String msg, Map<K, V> expected, Object actual) {
		extentTest.get().log(Status.FAIL, msg);
	}

	public static void logInfo(String message) {
		extentTest.get().log(Status.INFO, message);
	}

	public static void logPassed(String message) {
		extentTest.get().log(Status.PASS, message);
	}

	public static void logFailed(String message) {
		extentTest.get().log(Status.FAIL, message);
	}
}
