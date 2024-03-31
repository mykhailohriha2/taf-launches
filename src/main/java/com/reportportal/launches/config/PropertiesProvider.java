package com.reportportal.launches.config;

import java.util.ResourceBundle;


public class PropertiesProvider {
	private PropertiesProvider() {
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
