package com.reportportal.launches.pages;

import com.microsoft.playwright.Locator;
import com.reportportal.launches.core.Generic;


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

	public void login(String username, String password) {
		generic.fill(getLoginField(), username);
		generic.fill(getPasswordField(), password);
		generic.click(getLoginBtn());
	}
}
