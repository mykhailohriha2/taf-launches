package com.reportportal.launches.factory;

import java.nio.file.Paths;
import java.util.Base64;

import com.microsoft.playwright.*;
import com.reportportal.launches.config.PropertiesController;


public class PlaywrightFactory {

	private static ThreadLocal<Playwright> playwright = new ThreadLocal<>();
	private static ThreadLocal<Browser> browser = new ThreadLocal<>();
	private static ThreadLocal<BrowserContext> browserContext = new ThreadLocal<>();
	private static ThreadLocal<Page> page = new ThreadLocal<>();

	public void setPlaywright() {
		playwright.set(Playwright.create());
	}

	public void setBrowser() {
		String binary = PropertiesController.getBrowserPropertyByKey("binary");
		String channel = PropertiesController.getBrowserPropertyByKey("channel");
		String headless = PropertiesController.getBrowserPropertyByKey("headless");
		switch (binary) {
			case "chromium" -> browser.set(playwright.get().chromium().launch(
					new BrowserType.LaunchOptions().setChannel(channel).setHeadless(Boolean.parseBoolean(headless))));
			case "webkit" -> browser.set(playwright.get().webkit().launch(
					new BrowserType.LaunchOptions().setHeadless(Boolean.parseBoolean(headless))));
			case "firefox" -> browser.set(playwright.get().firefox().launch(
					new BrowserType.LaunchOptions().setHeadless(Boolean.parseBoolean(headless))));
			default -> throw new IllegalArgumentException("Invalid browser: " + browser);
		}
	}

	public void setBrowserContext() {
		browserContext.set(browser.get().newContext());
	}

	public void setPage() {
		page.set(browserContext.get().newPage());
	}

	public static Playwright getPlaywright() {
		return playwright.get();
	}

	public static Browser getBrowser() {
		return browser.get();
	}

	public static BrowserContext getBrowserContext() {
		return browserContext.get();
	}

	public static Page getPage() {
		return page.get();
	}

	public static String takeScreenshot() {
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		byte[] buffer = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
		return Base64.getEncoder().encodeToString(buffer);
	}

}
