package com.reportportal.launches.core;

import com.microsoft.playwright.*;


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
		// properties
		String binary = "chromium";
		String channel = "chrome";
		boolean headless = false;
		switch (binary) {
			case "chromium":
				browser.set(getPlaywright().chromium().launch(
						new BrowserType.LaunchOptions().setChannel(channel).setHeadless(Boolean.valueOf(headless))));
				break;
			case "webkit":
				browser.set(getPlaywright().webkit().launch(
						new BrowserType.LaunchOptions().setChannel(channel).setHeadless(Boolean.valueOf(headless))));
				break;
			case "firefox":
				browser.set(getPlaywright().webkit().launch(
						new BrowserType.LaunchOptions().setChannel(channel).setHeadless(Boolean.valueOf(headless))));
				break;
			default:
				browser.set(getPlaywright().chromium().launch(
						new BrowserType.LaunchOptions().setChannel(channel).setHeadless(Boolean.valueOf(headless))));
				break;
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
		return base.get().getPage();
	}

}