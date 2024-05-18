package tests.base;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.*;

import com.reportportal.launches.config.ConfigHolder;
import com.reportportal.launches.listeners.ExtentReportListener;
import com.reportportal.launches.playwright.PlaywrightFacade;


@Listeners(ExtentReportListener.class)
public abstract class BaseUiTest {
	private final PlaywrightFacade playwrightFacade = PlaywrightFacade.getInstance();
	protected SoftAssertions softAssert;

	@BeforeMethod
	public void setUp() {
		playwrightFacade.startPlaywright();
		playwrightFacade.getPage().navigate(ConfigHolder.getInstance().baseUrl());
		softAssert = new SoftAssertions();
	}

	@AfterMethod
	public void tearDown() {
		playwrightFacade.closePlaywright();
	}
}