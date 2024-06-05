package tests.ui;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.reportportal.launches.models.User;
import com.reportportal.launches.pageobjects.pages.*;

import tests.base.BaseUiTest;


public class CompareLaunchesUiTest extends BaseUiTest {
	private LoginPage loginPage;
	private MainPage mainPage;
	private LaunchesListPage launchesListPage;

	@BeforeMethod
	public void beforeMethod() {
		loginPage = new LoginPage();
		mainPage = new MainPage();
		launchesListPage = new LaunchesListPage();
	}

	@Test(description = "User is able to select several launches and compare them")
	public void validateUserIsAbleToSelectSeveralLaunchesAndCompareThemUi() {
		User testUser = User.builder().name("testuser4").password("testpassword4").build();

		loginPage.login(testUser);
		mainPage.navigateToLaunches();
		launchesListPage.selectLaunchByIndex(0);
		launchesListPage.selectLaunchByIndex(1);
		launchesListPage.expandActionsMenu();
		launchesListPage.clickCompareButton();
		assertThat(launchesListPage.isCompareWidgetVisible()).as("Compare widget is not visible").isTrue();
	}
}
