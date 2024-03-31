package tests.base;

import static com.reportportal.launches.config.EnvironmentConfigProvider.getEnvConfig;
import static com.reportportal.launches.playwright.PlaywrightFactory.*;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.*;

import com.reportportal.launches.listeners.ExtentReportListener;
import com.reportportal.launches.pages.LoginPage;
import com.reportportal.launches.pages.MainPage;
import com.reportportal.launches.playwright.PlaywrightFactory;


@Listeners(ExtentReportListener.class)
public abstract class BaseUiTest {
	private PlaywrightFactory playwrightFactory;

	protected SoftAssertions softAssert;

	protected LoginPage loginPage;
	protected MainPage mainPage;

	@BeforeMethod
	public void setUp() {
		playwrightFactory = new PlaywrightFactory();
		loginPage = new LoginPage(getPage());
		mainPage = new MainPage(getPage());
		softAssert = new SoftAssertions();
		getPage().navigate(getEnvConfig().baseUrl());
	}

	@AfterMethod
	public void tearDown() {
		getPage().context().close();
		getBrowser().close();
		getPlaywright().close();
		playwrightFactory.removePlaywrightContext();
	}
}