package tests.base;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.*;

import com.microsoft.playwright.*;
import com.reportportal.launches.PropertiesController;
import com.reportportal.launches.pages.LoginPage;
import com.reportportal.launches.pages.MainPage;


public class Base {
	private Playwright playwright;
	private Browser browser;
	private BrowserContext browserContext;
	private Page page;

	protected SoftAssertions softAssert;

	protected LoginPage loginPage;
	protected MainPage mainPage;

	private void setPlaywright() {
		playwright = Playwright.create();
	}

	private void setBrowser() {
		String binary = PropertiesController.getBrowserPropertyByKey("binary");
		String channel = PropertiesController.getBrowserPropertyByKey("channel");
		String headless = PropertiesController.getBrowserPropertyByKey("headless");
		switch (binary) {
			case "chromium" -> browser = playwright.chromium().launch(
					new BrowserType.LaunchOptions().setChannel(channel).setHeadless(Boolean.parseBoolean(headless)));
			case "webkit" -> browser = playwright.webkit().launch(
					new BrowserType.LaunchOptions().setHeadless(Boolean.parseBoolean(headless)));
			case "firefox" -> browser = playwright.firefox().launch(
					new BrowserType.LaunchOptions().setHeadless(Boolean.parseBoolean(headless)));
			default -> throw new IllegalArgumentException("Invalid browser: " + browser);
		}
	}

	private void setBrowserContext() {
		browserContext = browser.newContext();
	}

	private void setPage() {
		page = browserContext.newPage();
	}

	@BeforeClass
	public void setUpBrowser() {
		setPlaywright();
		setBrowser();
	}

	@BeforeMethod
	public void setUp() {
		setBrowserContext();
		setPage();
		// To discussion: Is that the right approach of managing pages?
		loginPage = new LoginPage(page);
		mainPage = new MainPage(page);
		softAssert = new SoftAssertions();
		page.navigate(PropertiesController.getEnvPropertyByKey("baseUrl"));
	}

	@AfterMethod
	public void closeContext() {
		page.context().close();
	}

	@AfterClass
	public void closeBrowser() {
		browser.close();
		playwright.close();
	}
}