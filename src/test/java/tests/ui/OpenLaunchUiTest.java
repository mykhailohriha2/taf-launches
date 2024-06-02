package tests.ui;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

import com.reportportal.launches.models.User;
import com.reportportal.launches.pageobjects.pages.*;

import tests.base.BaseUiTest;


public class OpenLaunchUiTest extends BaseUiTest {


	@Test(description = "User is able to move to appropriate launch view clicking on launch name")
	public void validateUserIsAbleToMoveToAppropriateLaunchClickingOnLaunchNameUi() {
		LoginPage loginPage = new LoginPage();
		MainPage mainPage = new MainPage();
		LaunchesListPage launchesListPage = new LaunchesListPage();
		LaunchPage launchPage = new LaunchPage();
		User testUser = User.builder().name("testuser6").password("testpassword6").build();
		String name = "Demo Api Tests";

		loginPage.login(testUser);
		mainPage.navigateToLaunches();
		launchesListPage.openLaunchByName(name);
		assertThat(launchPage.getLaunchName()).as("Launch name is not as expected").isEqualTo(name + " #5");
	}
}
