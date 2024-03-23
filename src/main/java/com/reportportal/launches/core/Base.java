package com.reportportal.launches.core;

import com.microsoft.playwright.*;
import com.reportportal.launches.PropertiesController;


public class Base {
	private static ThreadLocal<Playwright> playwright = new ThreadLocal<>();
	private static ThreadLocal<Browser> browser = new ThreadLocal<>();
	private static ThreadLocal<BrowserContext> browserContext = new ThreadLocal<>();
	private static ThreadLocal<Page> page = new ThreadLocal<>();
	private static ThreadLocal<Base> base = new ThreadLocal<>();

	private static void setPlaywright() {
		playwright.set(Playwright.create());
	}

	private static void setBrowser() {
		String binary = PropertiesController.getBrowserPropertyByKey("binary");
		String channel = PropertiesController.getBrowserPropertyByKey("channel");
		String headless = PropertiesController.getBrowserPropertyByKey("headless");
		switch (binary) {
			case "chromium" -> browser.set(getPlaywright().chromium().launch(
					new BrowserType.LaunchOptions().setChannel(channel).setHeadless(Boolean.parseBoolean(headless))));
			case "webkit" -> browser.set(getPlaywright().webkit().launch(
					new BrowserType.LaunchOptions().setHeadless(Boolean.parseBoolean(headless))));
			case "firefox" -> browser.set(getPlaywright().firefox().launch(
					new BrowserType.LaunchOptions().setHeadless(Boolean.parseBoolean(headless))));
			default -> throw new IllegalArgumentException("Invalid browser: " + browser);
		}
	}

	private static void setBrowserContext() {
		browserContext.set(getBrowser().newContext());
	}

	private static void setPage() {
		page.set(getBrowserContext().newPage());
	}

	private static Playwright getPlaywright() {
		return playwright.get();
	}

	private static Browser getBrowser() {
		return browser.get();
	}

	private static BrowserContext getBrowserContext() {
		return browserContext.get();
	}

	private static Page getPage() {
		return page.get();
	}

	private Base() {
		setPlaywright();
		setBrowser();
		setBrowserContext();
		setPage();
	}

	public static Page getBasePage() {
		if (base.get() == null) {
			base.set(new Base());
		}
		return Base.getPage();
	}

}