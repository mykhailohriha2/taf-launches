package com.reportportal.launches.pageobjects.pages;

import com.microsoft.playwright.Locator;
import com.reportportal.launches.pageobjects.components.tables.LaunchTable;
import com.reportportal.launches.pageobjects.pages.base.BasePage;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class LaunchesListPage extends BasePage {
	@Getter
	private final LaunchTable launchTable = new LaunchTable();
	private static final String launchMenu = "div[class*='hamburger__hamburger--']";
	private static final String actionsButton = "div[class*='ghostMenuButton__ghost-menu-button']";
	private static final String messageBeforeConfirmation = "div[class*='modalContent']";
	private static final String confirmDeleteBtn = "//button[contains(text(), 'Delete')]";
	private static final String compareLaunchesLabel = "span[class*='modalHeader__modal-title']";

	public void clickDeleteLaunchByIndex(int index) {
		launchTable.waitForTableIsLoaded();
		Locator launch = getAllByLocator(launchMenu).get(index);
		launch.click();
		launch.locator("text='Delete'").click();
		log.info("Click delete launch by index " + index);
	}

	public void selectLaunchByIndex(int index) {
		launchTable.waitForTableIsLoaded();
		launchTable.selectLaunchByIndex(index);
		log.info("Select launch by index " + index);
	}

	public void expandActionsMenu() {
		getByLocator(actionsButton).click();
		log.info("Expand actions menu");
	}

	public void clickCompareButton() {
		getByText("Compare").click();
		log.info("Click compare button");
	}

	public void clickConfirmDelete() {
		getByLocator(confirmDeleteBtn).click();
		log.info("Click confirm delete button");
	}

	public void clickTotalByIndex(int index) {
		launchTable.clickTotalByIndex(index);
		log.info("Click total column by index: " + index);
	}

	public void clickPassedByIndex(int index) {
		launchTable.clickPassedByIndex(index);
		log.info("Click passed column by index: " + index);
	}

	public void clickFailedByIndex(int index) {
		launchTable.clickFailedByIndex(index);
		log.info("Click failed column by index: " + index);
	}

	public void clickSkippedByIndex(int index) {
		launchTable.clickSkippedByIndex(index);
		log.info("Click skipped column by index: " + index);
	}

	public void openLaunchByName(String name) {
		launchTable.openLaunchByName(name);
		log.info("Open launch by name: " + name);
	}

	public boolean isCompareWidgetVisible() {
		return getByLocator(compareLaunchesLabel).isVisible();
	}

	public String getMessageBeforeConfirmation() {
		return getByLocator(messageBeforeConfirmation).textContent();
	}

}
