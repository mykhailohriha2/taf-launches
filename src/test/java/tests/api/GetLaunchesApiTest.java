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
		User user = User.builder().name("testuser2").password("testpassword2").defaultProject(
				"testuser2_personal").build();

		reportPortalClient.createSession(user);
		Response response = reportPortalClient.sendGetLaunchesByProject(user.getDefaultProject(), SC_OK);
		List<Integer> idS = response.jsonPath().getList("content.id");
		if (!idS.isEmpty()) {
			reportPortalClient.sendDeleteLaunchesByProject(user.getDefaultProject(), idS, SC_OK);
		}

		validateLaunchesAmount(user.getDefaultProject(), "0");
		reportPortalClient.sendPostGenerateDemoDataForProject(user.getDefaultProject(), SC_OK);
		validateLaunchesAmount(user.getDefaultProject(), "5");
		softAssert.assertAll();
	}

	private void validateLaunchesAmount(String testProject, String expectedAmount) {
		String launchesBeforeGenerating = String.valueOf(
				reportPortalClient.sendGetLaunchesByProject(testProject, SC_OK).getBody().jsonPath().getList(
						"content").size());
		softAssert.assertThat(launchesBeforeGenerating).as("The launches amount is not as expected").isEqualTo(expectedAmount);
	}
}
