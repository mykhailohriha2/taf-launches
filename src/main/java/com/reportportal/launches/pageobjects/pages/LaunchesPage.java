package com.reportportal.launches.pageobjects.pages;

import com.microsoft.playwright.ElementHandle;
import com.reportportal.launches.pageobjects.components.tables.LaunchTable;
import com.reportportal.launches.pageobjects.pages.base.BasePage;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class LaunchesPage extends BasePage {
	@Getter
	private final LaunchTable launchTable = new LaunchTable();
	private static final String launchMenu = "div[class*='hamburger__hamburger--']";
	private static final String actionsButton = "div[class*='ghostMenuButton__ghost-menu-button']";
	private static final String messageBeforeConfirmation = "div[class*='modalContent']";
	private static final String confirmDeleteBtn = "//button[contains(text(), 'Delete')]";
	private static final String compareLaunchesLabel = "span[class*='modalHeader__modal-title']";

	public void clickDeleteLaunchByIndex(int index) {
		launchTable.waitForTableIsLoaded();
		ElementHandle elementHandle = getAllByLocator(launchMenu).get(index);
		elementHandle.click();
		elementHandle.querySelector("text='Delete'").click();
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

	public boolean isCompareWidgetVisible() {
		return getByLocator(compareLaunchesLabel).isVisible();
	}

	public String getMessageBeforeConfirmation() {
		return getByLocator(messageBeforeConfirmation).textContent();
	}

}
