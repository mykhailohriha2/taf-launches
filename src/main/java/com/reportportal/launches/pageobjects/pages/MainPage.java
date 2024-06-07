package com.reportportal.launches.pageobjects.pages;

import com.microsoft.playwright.Locator;
import com.reportportal.launches.pageobjects.pages.base.BasePage;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class MainPage extends BasePage {
	private static final String notification = "#notification-root div p";

	public Locator getNotificationTooltip() {
		return getByLocator(notification);
	}

	public String getNotificationTextAndCloseTooltip() {
		String text = getNotificationTooltip().textContent();
		getNotificationTooltip().click();
		log.info("Get notification text and close tooltip");
		return text;
	}
}