package tests.base;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.*;

import com.reportportal.launches.config.ConfigHolder;
import com.reportportal.launches.listeners.ExtentReportListener;
import com.reportportal.launches.playwright.PlaywrightFacade;


@Listeners(ExtentReportListener.class)
public abstract class BaseUiTest {

	protected SoftAssertions softAssert;
	private PlaywrightFacade playwrightFacade;

	@BeforeMethod
	public void setUp() {
		playwrightFacade = PlaywrightFacade.getInstance();
		playwrightFacade.setPlaywright();
		playwrightFacade.setBrowser();
		playwrightFacade.setBrowserContext();
		playwrightFacade.setPage();
		softAssert = new SoftAssertions();
		playwrightFacade.getPage().navigate(ConfigHolder.getInstance().baseUrl());
	}

	@AfterMethod
	public void tearDown() {
		playwrightFacade.closePlaywright();
	}
}