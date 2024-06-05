package tests.ui;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.reportportal.launches.models.User;
import com.reportportal.launches.pageobjects.pages.*;

import tests.base.BaseUiTest;


public class OpenLaunchUiTest extends BaseUiTest {
	private LoginPage loginPage;
	private MainPage mainPage;
	private LaunchesListPage launchesListPage;

	@BeforeMethod
	public void beforeMethod() {
		loginPage = new LoginPage();
		mainPage = new MainPage();
		launchesListPage = new LaunchesListPage();
	}

	@Test(description = "User is able to move to appropriate launch view clicking on launch name")
	public void validateUserIsAbleToMoveToAppropriateLaunchClickingOnLaunchNameUi() {
		LaunchPage launchPage = new LaunchPage();
		User testUser = User.builder().name("testuser6").password("testpassword6").build();
		String name = "Demo Api Tests";

		loginPage.login(testUser);
		mainPage.navigateToLaunches();
		launchesListPage.openLaunchByName(name);
		assertThat(launchPage.getLaunchName()).as("Launch name is not as expected").isEqualTo(name + " #5");
	}
}
