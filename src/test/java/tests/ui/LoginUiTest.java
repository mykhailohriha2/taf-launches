package tests.ui;

import org.testng.annotations.Test;

import com.reportportal.launches.utils.UserUtils;

import tests.base.Base;


public class LoginUiTest extends Base {

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