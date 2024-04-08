package tests.ui;

import java.util.List;

import org.testng.annotations.*;

import com.reportportal.launches.models.User;
import com.reportportal.launches.pageobjects.pages.LoginPage;
import com.reportportal.launches.pageobjects.pages.MainPage;
import com.reportportal.launches.utils.FileUtils;

import tests.base.BaseUiTest;


// This test class was created only to test parallel execution. Will be deleted after expanding the test suite
public class LoginUiTest2 extends BaseUiTest {

	private LoginPage loginPage;
	private MainPage mainPage;

	@DataProvider(name = "users")
	public Object[][] createData() {
		String csvFile = "./src/test/resources/testData.csv";
		List<Object[]> list = FileUtils.readFromCsvFile(csvFile);
		return list.toArray(new Object[list.size()][]);
	}

	@BeforeMethod
	public void beforeMethod() {
		loginPage = new LoginPage();
		mainPage = new MainPage();
	}

	@Test(dataProvider = "users")
	public void validateUserIsAbleToLogin(String userName, String userPassword) {
		User user = User.builder().name(userName).password(userPassword).build();
		loginPage.login(user);
		softAssert.assertThat(mainPage.getNotificationTextAndCloseTooltip()).isEqualTo("Signed in successfully");
		softAssert.assertAll();
	}

}
