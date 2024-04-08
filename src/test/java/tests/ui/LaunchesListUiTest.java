package tests.ui;

import static java.util.Collections.reverseOrder;

import java.util.List;
import java.util.stream.Collectors;

import org.testng.annotations.Test;

import com.reportportal.launches.models.Launch;
import com.reportportal.launches.models.User;
import com.reportportal.launches.pageObjects.pages.*;

import tests.base.BaseUiTest;


public class LaunchesListUiTest extends BaseUiTest {

	@Test(description = "User is able to see launches list sorted by most recent")
	public void validateUserIsAbleToSeeLaunchesListSortedByMostRecentUi() {
		LoginPage loginPage = new LoginPage();
		MainPage mainPage = new MainPage();
		LaunchesPage launchesPage = new LaunchesPage();
		User testUser = User.builder().name("testuser1").password("testpassword1").build();

		loginPage.login(testUser);
		mainPage.navigateToLaunches();
		List<String> launchesStartTime = launchesPage.getLaunchTable().getAllLaunches().stream().map(
				Launch::getStartTime).collect(Collectors.toList());
		softAssert.assertThat(launchesStartTime.stream().sorted(reverseOrder()).collect(Collectors.toList())).as(
				"Launches are sorted not as expected").isEqualTo(launchesStartTime);
		softAssert.assertAll();
	}
}