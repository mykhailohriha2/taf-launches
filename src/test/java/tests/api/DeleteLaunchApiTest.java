package tests.api;

import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

import com.reportportal.launches.api.ReportPortalClient;
import com.reportportal.launches.models.User;
import com.reportportal.launches.utils.RandomUtils;

import tests.base.BaseApiTest;


public class DeleteLaunchApiTest extends BaseApiTest {

	private final ReportPortalClient reportPortalClient = ReportPortalClient.getInstance();

	@Test(description = "User is able to remove launch by id via DELETE request")
	public void validateUserIsAbleToRemoveLaunchByIdApi() {
		User user = User.builder().name("testuserapi1").password("testuserapipassword1").defaultProject(
				"Testuserapi1_personal").build();

		reportPortalClient.createSession(user);
		reportPortalClient.sendDeleteLaunchesInProjectByIds(user.getDefaultProject(),
				getAllLaunchesIds(user.getDefaultProject()), SC_OK);
		reportPortalClient.sendPostGenerateDemoDataForProject(user.getDefaultProject(), SC_OK);

		List<Integer> currentIds = getAllLaunchesIds(user.getDefaultProject());
		reportPortalClient.sendDeleteLaunchesInProjectByIds(user.getDefaultProject(),
				Collections.singletonList(currentIds.get(0)), SC_OK);
		currentIds.remove(0);

		List<Integer> expectedIds = getAllLaunchesIds(user.getDefaultProject());

		assertThat(currentIds).as("ID is not as expected").isEqualTo(expectedIds);
	}

	@Test(description = "User is able to remove launch by id via DELETE request")
	public void validateUserIsAbleToRemoveLaunchByIdNegativeScenarioApi() {
		User user = User.builder().name("testuserapi2").password("testuserapipassword2").defaultProject(
				"Testuserapi2_personal").build();

		reportPortalClient.createSession(user);
		reportPortalClient.sendPostGenerateDemoDataForProject(user.getDefaultProject(), SC_OK);

		List<Integer> currentIds = getAllLaunchesIds(user.getDefaultProject());

		reportPortalClient.sendDeleteLaunchesInProjectByIds(user.getDefaultProject(),
				Collections.singletonList(RandomUtils.getRandomValueExceptList(currentIds)), SC_OK);

		List<Integer> expectedIds = getAllLaunchesIds(user.getDefaultProject());

		assertThat(currentIds).as("ID is not as expected").isEqualTo(expectedIds);
	}

	private List<Integer> getAllLaunchesIds(String project) {
		return reportPortalClient.sendGetLaunchesByProject(project, SC_OK).jsonPath().getList("content.id");
	}
}