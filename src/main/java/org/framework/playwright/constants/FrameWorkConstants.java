package org.framework.playwright.constants;

import java.nio.file.Paths;

public class FrameWorkConstants {
    public static final String BASE_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\JsonFiles/";
    public static final String EXCELBASE_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\ExcelFiles/";
    public static final String PROP_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\data.properties";
    public static final String REPORTS_PATH = Paths.get(System.getProperty("user.dir"), "reports").toString();
    public static final String SCREENSHOT_PATH_PREFIX = System.getProperty("user.dir") + "/Screenshot";
    public static final String SCREENSHOT_PATH_SUFFIX = ".png";
    public static int TIME_OUT_SECONDS = 10;
    public static String APP_URL = "https://bmalpha.centralindia.cloudapp.azure.com/";
    public static final String PATH_NAME = "accessToken.txt";
    public static String PORTAL_URL = "https://qa.botminds.ai/";
    public static String QAENV_URL ="https://qa.botminds.ai/";
    public static String DEVENV_URL ="https://qa.botminds.ai/";

}
