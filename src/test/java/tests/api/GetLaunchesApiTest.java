package tests.api;

import static org.apache.http.HttpStatus.SC_OK;

import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.reportportal.launches.api.ReportPortalClient;
import com.reportportal.launches.models.User;

import io.restassured.response.Response;
import tests.base.BaseApiTest;


public class GetLaunchesApiTest extends BaseApiTest {

	private ReportPortalClient reportPortalClient;

	@BeforeMethod
	public void beforeMethod() {
		reportPortalClient = ReportPortalClient.getInstance();
	}

	@Test(description = "User is able to get all launches by filter via GET request")
	public void validateUserIsAbleToGetAllLaunchesApi() {
		User user = User.builder().name("testuser2").password("testpassword2").build();
		String testProject = "testuser2_personal";

		reportPortalClient.createSession(user);
		Response response = reportPortalClient.sendGetLaunchesByProject(testProject, SC_OK);
		List<Integer> idS = response.jsonPath().getList("content.id");
		if (idS.size() != 0) {
			reportPortalClient.sendDeleteLaunchByProject(testProject, idS, SC_OK);
		}

		validateLaunchesAmount(testProject, "0");
		reportPortalClient.sendPostGenerateDemoData(testProject, SC_OK);
		validateLaunchesAmount(testProject, "5");
		softAssert.assertAll();
	}

	private void validateLaunchesAmount(String testProject, String expected) {
		String launchesBeforeGenerating = String.valueOf(
				reportPortalClient.sendGetLaunchesByProject(testProject, SC_OK).getBody().jsonPath().getList(
						"content").size());
		softAssert.assertThat(launchesBeforeGenerating).as(
				"The launches amount is not as expected").isEqualTo(expected);
	}
}
