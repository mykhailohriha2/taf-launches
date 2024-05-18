package com.reportportal.launches.pageobjects.pages;

import com.microsoft.playwright.Locator;
import com.reportportal.launches.models.User;
import com.reportportal.launches.pageobjects.pages.base.BasePage;


public class LoginPage extends BasePage {
	private static final String loginField = "[name='login']";
	private static final String passwordField = "[name='password']";
	private static final String loginBtn = "[type='submit']";

	public Locator getLoginField() {
		return getByLocator(loginField);
	}

	public Locator getPasswordField() {
		return getByLocator(passwordField);
	}

	public Locator getLoginBtn() {
		return getByLocator(loginBtn);
	}

	public void login(User user) {
		fill(getLoginField(), user.getName());
		fill(getPasswordField(), user.getPassword());
		clickLoginButton();
	}

	public void enterLogin(String login) {
		fill(getLoginField(), login);
	}

	public void enterPassword(String password) {
		fill(getPasswordField(), password);
	}

	public void clickLoginButton() {
		click(getLoginBtn());
	}
}
