package tests.ui;

import static com.reportportal.launches.datatypes.constants.Messages.*;
import static org.apache.http.HttpStatus.SC_OK;

import org.testng.annotations.Test;

import com.reportportal.launches.api.ReportPortalClient;
import com.reportportal.launches.models.User;
import com.reportportal.launches.pageobjects.pages.*;

import tests.base.BaseUiTest;


public class RemoveLaunchUiTest extends BaseUiTest {

	@Test(description = "User is able to remove launch")
	public void validateUserIsAbleToRemoveLaunchUi() {
		LoginPage loginPage = new LoginPage();
		MainPage mainPage = new MainPage();
		LaunchesListPage launchesListPage = new LaunchesListPage();
		ReportPortalClient reportPortalClient = ReportPortalClient.getInstance();
		User user = User.builder().name("testuser3").password("testpassword3").defaultProject(
				"testuser3_personal").build();
		reportPortalClient.createSession(user);
		reportPortalClient.sendPostGenerateDemoDataForProject(user.getDefaultProject(), SC_OK);

		loginPage.login(user);
		softAssert.assertThat(mainPage.getNotificationTextAndCloseTooltip()).isEqualTo(SIGNED_IN_SUCCESSFULLY);
		mainPage.navigateToLaunches();
		launchesListPage.clickDeleteLaunchByIndex(0);
		softAssert.assertThat(launchesListPage.getMessageBeforeConfirmation()).isEqualTo(DELETE_LAUNCH_CONFIRMATION);
		launchesListPage.clickConfirmDelete();
		softAssert.assertThat(mainPage.getNotificationTextAndCloseTooltip()).isEqualTo(LAUNCH_WAS_DELETED);
		softAssert.assertAll();
	}
}
