package tests.ui;

import static com.reportportal.launches.datastructures.Messages.SIGNED_IN_SUCCESSFULLY;
import static com.reportportal.launches.utils.UserFactory.getAdminUser;
import static com.reportportal.launches.utils.UserFactory.getDefaultUser;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.reportportal.launches.models.User;

import tests.base.BaseUiTest;


public class LoginUiTest extends BaseUiTest {

	@DataProvider(name = "user type")
	public Object[] users() {
		return new Object[] { getDefaultUser(), getAdminUser() };
	}

	@Test(dataProvider = "user type")
	public void validateUserIsAbleToLogin(User user) {
		loginPage.login(user);
		softAssert.assertThat(mainPage.getNotificationText()).isEqualTo(SIGNED_IN_SUCCESSFULLY);
		softAssert.assertAll();
	}
}