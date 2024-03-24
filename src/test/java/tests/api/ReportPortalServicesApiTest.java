package tests.api;

import static org.apache.http.HttpStatus.SC_OK;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import tests.base.BaseApiTest;


public class ReportPortalServicesApiTest extends BaseApiTest {
	private String expectedApiServiceName;
	private String expectedApiServiceVersion;
	private String expectedUatServiceName;
	private String expectedUatServiceVersion;
	private String expectedUiServiceName;
	private String expectedUiServiceVersion;
	private SoftAssertions softAssertions;

	@BeforeClass
	public void initData() {
		expectedApiServiceName = "API Service";
		expectedApiServiceVersion = "5.11.0";
		expectedUatServiceName = "Authorization Service";
		expectedUatServiceVersion = "5.11.0";
		expectedUiServiceName = "Service UI";
		expectedUiServiceVersion = "5.11.0";
		softAssertions = new SoftAssertions();
	}

	@Test
	public void verifyThatReportPortalServicesAreRunning() {
		Response reportPortalInfoResponse = reportPortalService.sendGetReportPortalInfo(SC_OK);
		assertThatServiceIsUpAndRunning(reportPortalInfoResponse, "api.build.name", "api.build.version",
				expectedApiServiceName, expectedApiServiceVersion);
		assertThatServiceIsUpAndRunning(reportPortalInfoResponse, "uat.build.name", "uat.build.version",
				expectedUatServiceName, expectedUatServiceVersion);
		assertThatServiceIsUpAndRunning(reportPortalInfoResponse, "ui.build.name", "ui.build.version",
				expectedUiServiceName, expectedUiServiceVersion);
		softAssertions.assertAll();
	}

	private void assertThatServiceIsUpAndRunning(Response reportPortalInfoResponse, String pathToServiceName,
			String pathToServiceVersion, String expectedServiceName, String expectedServiceVersion) {
		String serviceName = reportPortalService.getBodyElementValueFromResponse(reportPortalInfoResponse,
				pathToServiceName);
		String serviceVersion = reportPortalService.getBodyElementValueFromResponse(reportPortalInfoResponse,
				pathToServiceVersion);
		softAssertions.assertThat(serviceName).as("Service name is as expected").isEqualTo(expectedServiceName);
		softAssertions.assertThat(serviceVersion).as("Service version is as expected").isEqualTo(expectedServiceVersion);
	}
}
