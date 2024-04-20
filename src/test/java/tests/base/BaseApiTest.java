package tests.base;


import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import com.reportportal.launches.listeners.ExtentReportApiListener;


@Listeners(ExtentReportApiListener.class)
public abstract class BaseApiTest {
	protected SoftAssertions softAssert;

	@BeforeMethod
	public void setUp() {
		softAssert = new SoftAssertions();
	}
}
