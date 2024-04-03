package tests.base;


import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Listeners;

import com.reportportal.launches.listeners.ExtentReportListener;


@Listeners(ExtentReportListener.class)
public abstract class BaseApiTest {
	protected SoftAssertions softAssert;
}
