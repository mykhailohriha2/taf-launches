package com.reportportal.launches.pageObjects.pages;

import com.microsoft.playwright.Locator;
import com.reportportal.launches.models.User;
import com.reportportal.launches.pageObjects.pages.base.BasePage;


public class LoginPage extends BasePage {
	private final String loginField = "[name='login']";
	private final String passwordField = "[name='password']";
	private final String loginBtn = "[type='submit']";

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
		click(getLoginBtn());
	}
}