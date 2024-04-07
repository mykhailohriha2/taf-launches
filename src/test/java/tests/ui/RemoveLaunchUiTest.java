package tests.ui;

import static com.reportportal.launches.datatypes.constants.Messages.*;

import org.testng.annotations.Test;

import com.reportportal.launches.factories.UserFactory;
import com.reportportal.launches.pageObjects.pages.*;

import tests.base.BaseUiTest;


public class RemoveLaunchUiTest extends BaseUiTest {

	@Test(description = "User is able to remove launch")
	public void validateUserIsAbleToRemoveLaunchUi() {
		LoginPage loginPage = new LoginPage();
		MainPage mainPage = new MainPage();
		LaunchesPage launchesPage = new LaunchesPage();

		loginPage.login(UserFactory.getAdminUser());
		softAssert.assertThat(mainPage.getNotificationTextAndCloseTooltip()).isEqualTo(SIGNED_IN_SUCCESSFULLY);
		mainPage.navigateToLaunches();
		launchesPage.clickDeleteLaunchByIndex(0);
		softAssert.assertThat(launchesPage.getMessageBeforeConfirmation()).as(
				"The message before confirmation is not as expected").isEqualTo(DELETE_LAUNCH_CONFIRMATION);
		launchesPage.clickConfirmDelete();
		softAssert.assertThat(mainPage.getNotificationTextAndCloseTooltip()).isEqualTo(LAUNCH_WAS_DELETED);
		softAssert.assertAll();
	}
}
