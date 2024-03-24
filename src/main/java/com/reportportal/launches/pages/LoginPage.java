package com.reportportal.launches.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.reportportal.launches.model.User;
import com.reportportal.launches.pages.base.BasePage;


public class LoginPage extends BasePage {

	public LoginPage(Page page) {
		super(page);
	}

	private String loginField = "[name='login']";
	private String passwordField = "[name='password']";
	private String loginBtn = "[type='submit']";

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
