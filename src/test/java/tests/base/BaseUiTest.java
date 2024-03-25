package tests.base;

import static com.reportportal.launches.factory.PlaywrightFactory.*;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.*;

import com.reportportal.launches.factory.PlaywrightFactory;
import com.reportportal.launches.config.PropertiesController;
import com.reportportal.launches.listeners.ExtentReportListener;
import com.reportportal.launches.pages.LoginPage;
import com.reportportal.launches.pages.MainPage;


@Listeners(ExtentReportListener.class)
public abstract class BaseUiTest {
	private final PlaywrightFactory playwrightFactory = new PlaywrightFactory();

	protected SoftAssertions softAssert;

	protected LoginPage loginPage;
	protected MainPage mainPage;

	@BeforeClass
	public void setUpBrowser() {
		playwrightFactory.setPlaywright();
		playwrightFactory.setBrowser();
	}

	@BeforeMethod
	public void setUp() {
		playwrightFactory.setBrowserContext();
		playwrightFactory.setPage();
		// To discussion: Is that the right approach of managing pages?
		loginPage = new LoginPage(getPage());
		mainPage = new MainPage(getPage());
		softAssert = new SoftAssertions();
		getPage().navigate(PropertiesController.getEnvPropertyByKey("baseUrl"));
	}

	@AfterMethod
	public void closeContext() {
		getPage().context().close();
	}

	@AfterClass
	public void closeBrowser() {
		getBrowser().close();
		getPlaywright().close();
	}
}