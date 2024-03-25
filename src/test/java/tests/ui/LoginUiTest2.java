package tests.ui;

import org.testng.annotations.Test;

import com.reportportal.launches.utils.UserUtils;

import tests.base.BaseUiTest;

// This test class was created only to test parallel execution. Will be deleted after expanding the test suite
public class LoginUiTest2 extends BaseUiTest {

	@Test
	public void validateUserIsAbleToLogin() {
		loginPage.login(UserUtils.getAdminUser());
		softAssert.assertThat(mainPage.getNotificationText()).isEqualTo("Signed in successfully");
		softAssert.assertAll();
	}

	@Test
	public void validateUserIsAbleToLogin2() {
		loginPage.login(UserUtils.getAdminUser());
		softAssert.assertThat(mainPage.getNotificationText()).isEqualTo("Signed in successfully");
		softAssert.assertAll();
	}

	@Test
	public void validateUserIsAbleToLogin3() {
		loginPage.login(UserUtils.getAdminUser());
		softAssert.assertThat(mainPage.getNotificationText()).isEqualTo("Signed in successfully");
		softAssert.assertAll();
	}

	@Test
	public void validateUserIsAbleToLogin4() {
		loginPage.login(UserUtils.getDefaultUser());
		softAssert.assertThat(mainPage.getNotificationText()).isEqualTo("Signed in successfully");
		softAssert.assertAll();
	}
}
