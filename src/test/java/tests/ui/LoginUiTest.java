package tests.ui;

import static com.reportportal.launches.datastructures.Messages.SIGNED_IN_SUCCESSFULLY;
import static com.reportportal.launches.factories.UserFactory.getAdminUser;
import static com.reportportal.launches.factories.UserFactory.getDefaultUser;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.reportportal.launches.models.User;
import com.reportportal.launches.pages.LoginPage;
import com.reportportal.launches.pages.MainPage;

import tests.base.BaseUiTest;


public class LoginUiTest extends BaseUiTest {

	@DataProvider(name = "user type")
	public Object[] users() {
		return new Object[] { getDefaultUser(), getAdminUser() };
	}

	@Test(dataProvider = "user type")
	public void validateUserIsAbleToLogin(User user) {
		LoginPage loginPage = new LoginPage();
		MainPage mainPage = new MainPage();
		loginPage.login(user);
		softAssert.assertThat(mainPage.getNotificationText()).isEqualTo(SIGNED_IN_SUCCESSFULLY);
		softAssert.assertAll();
	}
}