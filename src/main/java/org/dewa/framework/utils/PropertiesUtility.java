package org.dewa.framework.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtility {
	
	String path;
	FileInputStream fis;
	
	public PropertiesUtility(String path) {
		this.path=path;
	}
	
	public String getProperty(String key) {
		try {
			fis = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Properties pObj=new Properties();
		try {
			pObj.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String data = pObj.getProperty(key);
		return data;
	}
	
	public static void main(String[] args) {
		PropertiesUtility p=new PropertiesUtility("./src/main/resources/VoicedCustomers.properties");
		String id = p.getProperty("customername");
		System.out.println(id);
	}

}
