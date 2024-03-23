package com.reportportal.launches.pages;

import com.microsoft.playwright.Locator;
import com.reportportal.launches.core.Generic;
import com.reportportal.launches.model.User;


public class LoginPage {
	private Generic generic = new Generic("");
	private String loginField = "[name='login']";
	private String passwordField = "[name='password']";
	private String loginBtn = "[type='submit']";

	public Locator getLoginField() {
		return generic.getByLocator(loginField);
	}

	public Locator getPasswordField() {
		return generic.getByLocator(passwordField);
	}

	public Locator getLoginBtn() {
		return generic.getByLocator(loginBtn);
	}

	public void login(User user) {
		generic.fill(getLoginField(), user.getName());
		generic.fill(getPasswordField(), user.getPassword());
		generic.click(getLoginBtn());
	}
}
