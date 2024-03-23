package com.reportportal.launches.pages;

import com.microsoft.playwright.Locator;
import com.reportportal.launches.core.Generic;


public class MainPage {
	private Generic generic = new Generic("");
	private String notification = "#notification-root div p";

	public Locator getNotificationTooltip() {
		return generic.getByLocator(notification);
	}

	public String getNotificationText() {
		return getNotificationTooltip().textContent();
	}

}