package com.reportportal.launches.pageObjects.pages;

import java.util.ArrayList;
import java.util.List;

import com.microsoft.playwright.ElementHandle;
import com.reportportal.launches.models.Launch;
import com.reportportal.launches.pageObjects.pages.base.BasePage;


public class LaunchesPage extends BasePage {
	private final String grid = "div[class*='grid__grid']";
	private final String launchMenu = "div[class*='hamburger__hamburger--']";
	private final String messageBeforeConfirmation = "div[class*='modalContent']";
	private final String confirmDeleteBtn = "//button[contains(text(), 'Delete')]";
	private final String launch = "div[data-id]";
	private final String launchName = "td[class*='launchSuiteGrid__name'] div[class*='itemInfo__name']";
	private final String startTime = "div[class*='launchSuiteGrid__start-time'] span[class*='absRelTime__absolute']";
	private final String total = "div[class*='launchSuiteGrid__total'] a[class*='executionStatistics']";
	private final String passed = "div[class*='launchSuiteGrid__passed'] a[class*='executionStatistics']";
	private final String failed = "div[class*='launchSuiteGrid__failed']  a[class*='value']";
	private final String skipped = "div[class*='launchSuiteGrid__skipped']  a[class*='value']";
	private final String productBug = "div[class*='launchSuiteGrid__pb'] div[class*='total']";
	private final String autoBug = "div[class*='launchSuiteGrid__ab'] div[class*='total']";
	private final String systemIssue = "div[class*='launchSuiteGrid__si'] div[class*='total']";
	private final String toInvestigate = "div[class*='launchSuiteGrid__ti'] div[class*='total']";

	public List<Launch> getAllLaunches() {
		List<Launch> launches = new ArrayList<>();
		getAllByLocator(launch).forEach(elementHandle -> launches.add(buildLaunchWithElementHandle(elementHandle)));
		return launches;
	}

	public void clickDeleteLaunchByIndex(int index) {
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

	private String getTextContentFromElementHandle(ElementHandle elementHandle, String querySelector) {
		try {
			return elementHandle.querySelector(querySelector).textContent();
		} catch (NullPointerException e) {
			return "";
		}
	}

	private Launch buildLaunchWithElementHandle(ElementHandle elementHandle) {
		return Launch.builder().name(getTextContentFromElementHandle(elementHandle, launchName)).startTime(
				getTextContentFromElementHandle(elementHandle, startTime)).total(
				getTextContentFromElementHandle(elementHandle, total)).passed(
				getTextContentFromElementHandle(elementHandle, passed)).failed(
				getTextContentFromElementHandle(elementHandle, failed)).skipped(
				getTextContentFromElementHandle(elementHandle, skipped)).productBug(
				getTextContentFromElementHandle(elementHandle, productBug)).autoBug(
				getTextContentFromElementHandle(elementHandle, autoBug)).systemIssue(
				getTextContentFromElementHandle(elementHandle, systemIssue)).toInvestigate(
				getTextContentFromElementHandle(elementHandle, toInvestigate)).build();
	}
}
