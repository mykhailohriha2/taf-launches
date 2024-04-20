package com.reportportal.launches.pageobjects.pages;

import com.microsoft.playwright.ElementHandle;
import com.reportportal.launches.pageobjects.components.tables.LaunchTable;
import com.reportportal.launches.pageobjects.pages.base.BasePage;

import lombok.Getter;


public class LaunchesPage extends BasePage {
	@Getter
	private final LaunchTable launchTable = new LaunchTable();
	private static final String launchMenu = "div[class*='hamburger__hamburger--']";
	private static final String messageBeforeConfirmation = "div[class*='modalContent']";
	private static final String confirmDeleteBtn = "//button[contains(text(), 'Delete')]";

	public void clickDeleteLaunchByIndex(int index) {
		launchTable.waitForTableIsLoaded();
		ElementHandle elementHandle = getAllByLocator(launchMenu).get(index);
		elementHandle.click();
		elementHandle.querySelector("text='Delete'").click();
	}

	public String getMessageBeforeConfirmation() {
		return getByLocator(messageBeforeConfirmation).textContent();
	}

	public void clickConfirmDelete() {
		getByLocator(confirmDeleteBtn).click();
	}
}
