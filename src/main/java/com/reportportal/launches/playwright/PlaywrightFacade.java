package com.reportportal.launches.playwright;

import java.nio.file.Paths;
import java.util.Base64;

import com.microsoft.playwright.*;
import com.reportportal.launches.config.ConfigHolder;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlaywrightFacade {
	private static final ThreadLocal<PlaywrightSession> playwrightSession = ThreadLocal.withInitial(
			PlaywrightSession::new);

	private static class LazyHolder {
		static final PlaywrightFacade INSTANCE = new PlaywrightFacade();
	}

	public static PlaywrightFacade getInstance() {
		return PlaywrightFacade.LazyHolder.INSTANCE;
	}

	public void setPlaywright() {
		playwrightSession.get().setPlaywright(Playwright.create());
	}

	public void setBrowser() {
		String binary = ConfigHolder.getInstance().binary();
		String channel = ConfigHolder.getInstance().channel();
		boolean headless = ConfigHolder.getInstance().headless();
		switch (binary) {
			case "chromium" -> playwrightSession.get().setBrowser(getPlaywright().chromium().launch(
					new BrowserType.LaunchOptions().setChannel(channel).setHeadless(headless)));
			case "webkit" -> playwrightSession.get().setBrowser(
					getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(headless)));
			case "firefox" -> playwrightSession.get().setBrowser(
					getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(headless)));
			default -> throw new IllegalArgumentException("Invalid browser: " + binary);
		}
	}

	public void setBrowserContext() {
		playwrightSession.get().setBrowserContext(getBrowser().newContext());
	}

	public void setPage() {
		playwrightSession.get().setPage(getBrowserContext().newPage());
	}

	public static Playwright getPlaywright() {
		return playwrightSession.get().getPlaywright();
	}

	public static Browser getBrowser() {
		return playwrightSession.get().getBrowser();
	}

	public static BrowserContext getBrowserContext() {
		return playwrightSession.get().getBrowserContext();
	}

	public Page getPage() {
		return playwrightSession.get().getPage();
	}

	public static String takeScreenshot() {
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		byte[] buffer = getInstance().getPage().screenshot(
				new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
		return Base64.getEncoder().encodeToString(buffer);
	}

	public void startPlaywright() {
		setPlaywright();
		setBrowser();
		setBrowserContext();
		setPage();
	}

	public void closePlaywright() {
		getBrowserContext().close();
		getBrowser().close();
		getPlaywright().close();
		playwrightSession.remove();
	}
}
