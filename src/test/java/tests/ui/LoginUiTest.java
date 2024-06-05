package tests.ui;

import static com.reportportal.launches.datatypes.constants.Messages.SIGNED_IN_SUCCESSFULLY;
import static com.reportportal.launches.factories.UserFactory.getAdminUser;
import static com.reportportal.launches.factories.UserFactory.getDefaultUser;
import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.*;

import com.reportportal.launches.models.User;
import com.reportportal.launches.pageobjects.pages.*;

import tests.base.BaseUiTest;


public class LoginUiTest extends BaseUiTest {
	private LoginPage loginPage;
	private MainPage mainPage;

	@BeforeMethod
	public void beforeMethod() {
		loginPage = new LoginPage();
		mainPage = new MainPage();
	}

	@DataProvider(name = "user type", parallel = false)
	public Object[] users() {
		return new Object[] { getDefaultUser(), getAdminUser() };
	}

	@Test(dataProvider = "user type")
	public void validateUserIsAbleToLogin(User user) {
		loginPage.login(user);
		assertThat(mainPage.getNotificationTextAndCloseTooltip()).isEqualTo(SIGNED_IN_SUCCESSFULLY);
	}
}