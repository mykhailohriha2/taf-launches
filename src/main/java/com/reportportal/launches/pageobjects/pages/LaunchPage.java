package com.reportportal.launches.pageobjects.pages;

import com.reportportal.launches.pageobjects.pages.base.BasePage;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class LaunchPage extends BasePage {

	private static final String methodTypeSelected = "//span[text()='Method type']/../..//span[starts-with(@class, 'inputDropdown__value')]";
	private static final String statusSelected = "//span[text()='Status']/../..//span[starts-with(@class, 'inputDropdown__value')]";
	private static final String launchName = "span[class*='breadcrumb__link-item']  span";

	public String getMethodTypeSelectedText() {
		log.info("Get method type selected text");
		return getByLocator(methodTypeSelected).textContent();
	}

	public String getStatusSelectedText() {
		log.info("Get status selected text");
		return getByLocator(statusSelected).textContent();
	}

	public String getLaunchName() {
		log.info("Get launch name");
		return getByLocator(launchName).textContent();
	}
}
