package com.reportportal.launches.cucumber.steps;

import com.reportportal.launches.config.ConfigHolder;
import com.reportportal.launches.pageobjects.pages.LoginPage;

import io.cucumber.java.en.*;


public class LoginSteps {
	private final LoginPage loginPage = new LoginPage();

	@Given("User opens the Login page")
	public void openLoginPage() {
		loginPage.navigate(ConfigHolder.getInstance().baseUrl());
	}

	@When("User enters {string} in login field")
	public void enterLogin(String login) {
		loginPage.enterLogin(login);
	}

	@When("User enters {string} in password field")
	public void enterPassword(String password) {
		loginPage.enterPassword(password);
	}

	@When("User click on the login button")
	public void clickLoginButton() {
		loginPage.clickLoginButton();
	}

	@Given("User logs in with {string} and {string}")
	public void login(String login, String password) {
		enterLogin(login);
		enterPassword(password);
		clickLoginButton();
	}
}
