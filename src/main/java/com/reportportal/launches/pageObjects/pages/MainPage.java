package com.reportportal.launches.pageObjects.pages;

import com.microsoft.playwright.Locator;
import com.reportportal.launches.pageObjects.pages.base.BasePage;


public class MainPage extends BasePage {
	private final String notification = "#notification-root div p";

	public Locator getNotificationTooltip() {
		return getByLocator(notification);
	}

	public String getNotificationTextAndCloseTooltip() {
		String text = getNotificationTooltip().textContent();
		getNotificationTooltip().click();
		return text;
	}
}