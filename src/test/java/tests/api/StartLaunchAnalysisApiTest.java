package tests.api;

import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.testng.annotations.Test;

import com.reportportal.launches.api.ReportPortalClient;
import com.reportportal.launches.datatypes.constants.Messages;
import com.reportportal.launches.models.User;
import com.reportportal.launches.models.dto.AnalyzeLaunchRQ;
import com.reportportal.launches.utils.WebApiUtils;

import io.restassured.response.Response;
import tests.base.BaseApiTest;


public class StartLaunchAnalysisApiTest extends BaseApiTest {

	private final ReportPortalClient reportPortalClient = ReportPortalClient.getInstance();
	private final User user = User.builder().name("testuserapi3").password("testuserapipassword3").defaultProject(
			"testuserapi3_personal").build();

	@Test(description = "User is able to Start launch analysis via POST request")
	public void validateUserIsAbleToStartLaunchAnalysisApi() {
		int validateLaunchId = 220;
		AnalyzeLaunchRQ analyzeLaunchRQ = AnalyzeLaunchRQ.builder().analyzeItemsMode(
				List.of("TO_INVESTIGATE")).analyzerMode("ALL").analyzerTypeName("autoAnalyzer").launchId(
				validateLaunchId).build();

		reportPortalClient.createSession(user);
		Response response = reportPortalClient.sendPostStartLaunchAnalysisById(user.getDefaultProject(), analyzeLaunchRQ,
				SC_OK);

		String message = WebApiUtils.getBodyElementValueFromResponse(response, "message");
		assertThat(message).as("The message is not as expected").isEqualTo(
				String.format(Messages.ANALYSIS_STARTED, validateLaunchId));
	}

	@Test(description = "User is able to Start launch analysis via POST request")
	public void validateUserIsAbleToStartLaunchAnalysisNegativeScenarioApi() {
		int invalidateLaunchId = 221;
		AnalyzeLaunchRQ analyzeLaunchRQ = AnalyzeLaunchRQ.builder().analyzeItemsMode(
				List.of("TO_INVESTIGATE")).analyzerMode("ALL").analyzerTypeName("autoAnalyzer").launchId(
				invalidateLaunchId).build();

		reportPortalClient.createSession(user);
		Response response = reportPortalClient.sendPostStartLaunchAnalysisById(user.getDefaultProject(), analyzeLaunchRQ,
				SC_NOT_FOUND);

		String message = WebApiUtils.getBodyElementValueFromResponse(response, "message");
		assertThat(message).as("The message is not as expected").isEqualTo(
				String.format(Messages.ANALYSIS_STARTED_FAILED, invalidateLaunchId));
	}
}
