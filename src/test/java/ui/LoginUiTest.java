package ui;

import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import com.reportportal.launches.core.Generic;
import com.reportportal.launches.pages.LoginPage;
import com.reportportal.launches.pages.MainPage;


public class LoginUiTest {
	Generic generic;
	LoginPage loginPage;
	MainPage mainPage;
	SoftAssert softAssert;

	@BeforeClass
	public void beforeClass() {
		loginPage = new LoginPage();
		mainPage = new MainPage();
		softAssert = new SoftAssert();
	}

	@BeforeMethod
	public void beforeMethod() {
		generic = new Generic();
	}

	@Test
	public void validateUserIsAbleToLogin() {
		generic.navigate("http://localhost:8080/");
		loginPage.login("superadmin", "erebus");
		softAssert.assertEquals("", "");
		softAssert.assertAll();
	}

	@AfterMethod
	public void afterMethod() {
		generic.closeContext();
	}
}