package com.reportportal.launches.config;

import org.aeonbits.owner.ConfigFactory;

import lombok.experimental.UtilityClass;


@UtilityClass
public class BrowserConfigProvider {
	private static BrowserConfig browserConfig;

	public static synchronized BrowserConfig getBrowserConfig() {
		if (browserConfig == null) {
			browserConfig = ConfigFactory.create(BrowserConfig.class);
		}
		return browserConfig;
	}
}
