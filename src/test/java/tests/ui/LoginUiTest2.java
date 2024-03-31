package tests.ui;

import org.testng.annotations.Test;

import com.reportportal.launches.utils.UserFactory;

import tests.base.BaseUiTest;


// This test class was created only to test parallel execution. Will be deleted after expanding the test suite
public class LoginUiTest2 extends BaseUiTest {

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
