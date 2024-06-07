package com.reportportal.launches.pageobjects.pages.base;

import com.reportportal.launches.pageobjects.BasePageObject;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class BasePage extends BasePageObject {

	public void navigateToLaunches() {
		getByLocator("div[class*='sidebar'] a[href*='launches']").click();
		log.info("NAVIGATE TO LAUCNHES");
	}
}
