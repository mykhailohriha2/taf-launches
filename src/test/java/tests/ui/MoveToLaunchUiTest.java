package tests.ui;

import org.testng.annotations.*;

import com.reportportal.launches.models.User;
import com.reportportal.launches.pageobjects.pages.*;

import tests.base.BaseUiTest;


public class MoveToLaunchUiTest extends BaseUiTest {
	private LaunchPage launchPage;
	private LoginPage loginPage;
	private MainPage mainPage;
	private LaunchesListPage launchesListPage;

	@BeforeMethod
	public void beforeMethod() {
		loginPage = new LoginPage();
		mainPage = new MainPage();
		launchesListPage = new LaunchesListPage();
		launchPage = new LaunchPage();
	}

	@Test(description = "User is able to move to appropriate launch view clicking on total/passed/failed/skipped")
	public void validateUserIsAbleToMoveToAppropriateLaunchUi() {
		User testUser = User.builder().name("testuser5").password("testpassword5").build();

		loginPage.login(testUser);
		mainPage.navigateToLaunches();
		launchesListPage.clickTotalByIndex(0);
		assertLaunchIsAppropriate("Passed, Failed, Skipped, Interrupted");

		mainPage.navigateToLaunches();
		launchesListPage.clickPassedByIndex(0);
		assertLaunchIsAppropriate("Passed");

		mainPage.navigateToLaunches();
		launchesListPage.clickFailedByIndex(0);
		assertLaunchIsAppropriate("Failed, Interrupted");

		mainPage.navigateToLaunches();
		launchesListPage.clickSkippedByIndex(0);
		assertLaunchIsAppropriate("Skipped");
		softAssert.assertAll();
	}

	private void assertLaunchIsAppropriate(String expectedStatus) {
		softAssert.assertThat(launchPage.getMethodTypeSelectedText()).as("Method type is not as expected").isEqualTo(
				"Test");
		softAssert.assertThat(launchPage.getStatusSelectedText()).as("Method type is not as expected").isEqualTo(
				expectedStatus);
	}
}
