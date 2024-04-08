package com.reportportal.launches.pageobjects.pages.base;

import com.reportportal.launches.pageobjects.BasePageObject;


public class BasePage extends BasePageObject {

	public void navigateToLaunches() {
		getByLocator("div[class*='sidebar'] a[href*='launches']").click();
	}
}
