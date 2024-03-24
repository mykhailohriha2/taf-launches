package com.reportportal.launches.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.reportportal.launches.pages.base.BasePage;


public class MainPage extends BasePage {

	public MainPage(Page page) {
		super(page);
	}

	private String notification = "#notification-root div p";

	public Locator getNotificationTooltip() {
		return getByLocator(notification);
	}

	public String getNotificationText() {
		return getNotificationTooltip().textContent();
	}

}