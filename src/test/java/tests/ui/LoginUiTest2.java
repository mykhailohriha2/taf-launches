package tests.ui;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.reportportal.launches.factories.UserFactory;
import com.reportportal.launches.pages.LoginPage;
import com.reportportal.launches.pages.MainPage;

import tests.base.BaseUiTest;


// This test class was created only to test parallel execution. Will be deleted after expanding the test suite
public class LoginUiTest2 extends BaseUiTest {

	private LoginPage loginPage;
	private MainPage mainPage;

	@BeforeMethod
	public void beforeMethod() {
		loginPage = new LoginPage();
		mainPage = new MainPage();
	}

	@Test
	public void validateUserIsAbleToLogin() {
		loginPage.login(UserFactory.getAdminUser());
		softAssert.assertThat(mainPage.getNotificationText()).isEqualTo("Signed in successfully");
		softAssert.assertAll();
	}

	@Test
	public void validateUserIsAbleToLogin2() {
		loginPage.login(UserFactory.getAdminUser());
		softAssert.assertThat(mainPage.getNotificationText()).isEqualTo("Signed in successfully");
		softAssert.assertAll();
	}

	@Test
	public void validateUserIsAbleToLogin3() {
		loginPage.login(UserFactory.getAdminUser());
		softAssert.assertThat(mainPage.getNotificationText()).isEqualTo("Signed in successfully");
		softAssert.assertAll();
	}

	@Test
	public void validateUserIsAbleToLogin4() {
		loginPage.login(UserFactory.getDefaultUser());
		softAssert.assertThat(mainPage.getNotificationText()).isEqualTo("Signed in successfully");
		softAssert.assertAll();
	}
}
