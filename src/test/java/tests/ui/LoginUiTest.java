package tests.ui;

import static com.reportportal.launches.utils.UserUtils.getAdminUser;
import static com.reportportal.launches.utils.UserUtils.getDefaultUser;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.reportportal.launches.model.User;

import tests.base.BaseUiTest;


public class LoginUiTest extends BaseUiTest {

	@DataProvider(name = "user type")
	public Object[] users() {
		return new Object[] { getDefaultUser(), getAdminUser() };
	}

	@Test(dataProvider = "user type")
	public void validateUserIsAbleToLogin(User user) {
		loginPage.login(user);
		softAssert.assertThat(mainPage.getNotificationText()).isEqualTo("Signed in successfully");
		softAssert.assertAll();
	}
}