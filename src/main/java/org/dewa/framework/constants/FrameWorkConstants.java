package org.dewa.framework.constants;

import java.nio.file.Paths;

public class FrameWorkConstants {
    public static final String BASE_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\JsonFiles/";
    public static final String PROP_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\data.properties";
    public static final String REPORTS_PATH = Paths.get(System.getProperty("user.dir"), "reports").toString();
    public static final String SCREENSHOT_PATH = System.getProperty("user.dir") + "/Screenshots";
    public static String APP_URL = "";

}
