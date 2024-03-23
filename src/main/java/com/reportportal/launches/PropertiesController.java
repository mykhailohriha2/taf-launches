package com.reportportal.launches;

import java.util.ResourceBundle;


public class PropertiesController {
	private PropertiesController() {
	}

	private static final ResourceBundle envBundle = ResourceBundle.getBundle(System.getProperty("env"));
	private static final ResourceBundle browserBundle = ResourceBundle.getBundle(System.getProperty("browser"));

	public static String getBrowserPropertyByKey(String key) {
		return browserBundle.getString(key);
	}

	public static String getEnvPropertyByKey(String key) {
		return envBundle.getString(key);
	}
}
