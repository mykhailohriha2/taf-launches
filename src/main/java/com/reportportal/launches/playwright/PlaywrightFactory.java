package com.reportportal.launches.playwright;

import static com.reportportal.launches.config.BrowserConfigProvider.getBrowserConfig;

import java.nio.file.Paths;
import java.util.Base64;

import com.microsoft.playwright.*;


public class PlaywrightFactory {
	private static final ThreadLocal<PlaywrightContext> playwrightContext = new ThreadLocal<>();

	public PlaywrightFactory() {
		playwrightContext.set(new PlaywrightContext());
		setPlaywright();
		setBrowser();
		setBrowserContext();
		setPage();
	}

	public void setPlaywright() {
		playwrightContext.get().setPlaywright(Playwright.create());
	}

	public void setBrowser() {
		String binary = getBrowserConfig().binary();
		String channel = getBrowserConfig().channel();
		boolean headless = getBrowserConfig().headless();
		switch (binary) {
			case "chromium" -> playwrightContext.get().setBrowser(getPlaywright().chromium().launch(
					new BrowserType.LaunchOptions().setChannel(channel).setHeadless(headless)));
			case "webkit" -> playwrightContext.get().setBrowser(
					getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(headless)));
			case "firefox" -> playwrightContext.get().setBrowser(
					getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless)));
			default -> throw new IllegalArgumentException("Invalid browser: " + binary);
		}
	}

	public void setBrowserContext() {
		playwrightContext.get().setBrowserContext(getBrowser().newContext());
	}

	public void setPage() {
		playwrightContext.get().setPage(getBrowserContext().newPage());
	}

	public static Playwright getPlaywright() {
		return playwrightContext.get().getPlaywright();
	}

	public static Browser getBrowser() {
		return playwrightContext.get().getBrowser();
	}

	public static BrowserContext getBrowserContext() {
		return playwrightContext.get().getBrowserContext();
	}

	public static Page getPage() {
		return playwrightContext.get().getPage();
	}

	public static String takeScreenshot() {
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		byte[] buffer = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
		return Base64.getEncoder().encodeToString(buffer);
	}

	public void removePlaywrightContext() {
		playwrightContext.remove();
	}
}
