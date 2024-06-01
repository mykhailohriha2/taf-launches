package tests.ui;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Test;

import com.reportportal.launches.models.User;
import com.reportportal.launches.pageobjects.pages.*;

import tests.base.BaseUiTest;


public class CompareLaunchesUiTest extends BaseUiTest {

	@Test(description = "User is able to select several launches and compare them")
	public void validateUserIsAbleToSelectSeveralLaunchesAndCompareThemUi() {
		LoginPage loginPage = new LoginPage();
		MainPage mainPage = new MainPage();
		LaunchesPage launchesPage = new LaunchesPage();
		User testUser = User.builder().name("testuser4").password("testpassword4").build();

		loginPage.login(testUser);
		mainPage.navigateToLaunches();
		launchesPage.selectLaunchByIndex(0);
		launchesPage.selectLaunchByIndex(1);
		launchesPage.expandActionsMenu();
		launchesPage.clickCompareButton();
		assertThat(launchesPage.isCompareWidgetVisible()).as("Compare widget is not visible").isTrue();
	}
}
