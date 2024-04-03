package tests.api;

import static com.reportportal.launches.utils.WebApiUtils.getBodyElementValueFromResponse;
import static org.apache.http.HttpStatus.SC_OK;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.reportportal.launches.api.ReportPortalClient;

import io.restassured.response.Response;
import tests.base.BaseApiTest;


public class ReportPortalApiTest extends BaseApiTest {

	@BeforeMethod
	public void beforeMethod() {
		softAssert = new SoftAssertions();
	}

	@Test
	public void verifyThatReportPortalServicesAreRunning() {
		String expectedApiServiceName = "API Service";
		String expectedApiServiceVersion = "5.11.0";
		String expectedUatServiceName = "Authorization Service";
		String expectedUatServiceVersion = "5.11.0";
		String expectedUiServiceName = "Service UI";
		String expectedUiServiceVersion = "5.11.0";
		Response reportPortalInfoResponse = ReportPortalClient.getInstance().sendGetReportPortalInfo(SC_OK);
		assertThatServiceIsUpAndRunning(reportPortalInfoResponse, "api.build.name", "api.build.version",
				expectedApiServiceName, expectedApiServiceVersion);
		assertThatServiceIsUpAndRunning(reportPortalInfoResponse, "uat.build.name", "uat.build.version",
				expectedUatServiceName, expectedUatServiceVersion);
		assertThatServiceIsUpAndRunning(reportPortalInfoResponse, "ui.build.name", "ui.build.version",
				expectedUiServiceName, expectedUiServiceVersion);
		softAssert.assertAll();
	}

	private void assertThatServiceIsUpAndRunning(Response reportPortalInfoResponse, String pathToServiceName,
			String pathToServiceVersion, String expectedServiceName, String expectedServiceVersion) {
		String serviceName = getBodyElementValueFromResponse(reportPortalInfoResponse, pathToServiceName);
		String serviceVersion = getBodyElementValueFromResponse(reportPortalInfoResponse, pathToServiceVersion);
		softAssert.assertThat(serviceName).as("Service name is as expected").isEqualTo(expectedServiceName);
		softAssert.assertThat(serviceVersion).as("Service version is as expected").isEqualTo(expectedServiceVersion);
	}
}
