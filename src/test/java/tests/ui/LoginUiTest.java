package tests.ui;

import org.testng.annotations.Test;

import com.reportportal.launches.utils.UserUtils;

import tests.base.BaseUiTest;


public class LoginUiTest extends BaseUiTest {

	@Test
	public void validateUserIsAbleToLogin() {
		generic.navigate("http://localhost:8080/");
		loginPage.login(UserUtils.getAdminUser());
		softAssert.assertThat(mainPage.getNotificationText()).isEqualTo("Signed in successfully");
		softAssert.assertAll();
	}
}