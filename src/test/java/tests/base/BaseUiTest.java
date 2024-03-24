package tests.base;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import com.reportportal.launches.core.Generic;
import com.reportportal.launches.pages.LoginPage;
import com.reportportal.launches.pages.MainPage;


public abstract class BaseUiTest {
	protected Generic generic;
	protected LoginPage loginPage;
	protected MainPage mainPage;
	protected SoftAssertions softAssert;

	@BeforeClass
	public void beforeClass() {
		loginPage = new LoginPage();
		mainPage = new MainPage();
		softAssert = new SoftAssertions();
	}

	@BeforeMethod
	public void beforeMethod() {
		generic = new Generic();
	}

	@AfterMethod
	public void afterMethod() {
		generic.closeContext();
	}
}
