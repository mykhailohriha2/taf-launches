package com.reportportal.launches.pageobjects.components.tables;

import java.util.ArrayList;
import java.util.List;

import com.microsoft.playwright.ElementHandle;
import com.reportportal.launches.models.Launch;
import com.reportportal.launches.pageobjects.components.tables.base.TableComponent;


public class LaunchTable extends TableComponent {
	private static final String launchNameColumn = "td[class*='launchSuiteGrid__name'] div[class*='itemInfo__name']";
	private static final String startTimeColumn = "div[class*='launchSuiteGrid__start-time'] span[class*='absRelTime__absolute']";
	private static final String totalColumn = "div[class*='launchSuiteGrid__total'] a[class*='executionStatistics']";
	private static final String passedColumn = "div[class*='launchSuiteGrid__passed'] a[class*='executionStatistics']";
	private static final String failedColumn = "div[class*='launchSuiteGrid__failed']  a[class*='value']";
	private static final String skippedColumn = "div[class*='launchSuiteGrid__skipped']  a[class*='value']";
	private static final String productBugColumn = "div[class*='launchSuiteGrid__pb'] div[class*='total']";
	private static final String autoBugColumn = "div[class*='launchSuiteGrid__ab'] div[class*='total']";
	private static final String systemIssueColumn = "div[class*='launchSuiteGrid__si'] div[class*='total']";
	private static final String toInvestigateColumn = "div[class*='launchSuiteGrid__ti'] div[class*='total']";
	private static final String launchCheckbox = "td[class*='checkboxCell__checkbox-cell'] div";

	public List<Launch> getAllLaunches() {
		List<Launch> launches = new ArrayList<>();
		getAllRows().forEach(elementHandle -> launches.add(buildLaunchWithElementHandle(elementHandle)));
		return launches;
	}

	public void selectLaunchByIndex(int index) {
		getAllRows().get(index).querySelector(launchCheckbox).check();
	}

	private Launch buildLaunchWithElementHandle(ElementHandle elementHandle) {
		return Launch.builder().name(getTextContentFromElementHandle(elementHandle, launchNameColumn)).startTime(
				getTextContentFromElementHandle(elementHandle, startTimeColumn)).total(
				getTextContentFromElementHandle(elementHandle, totalColumn)).passed(
				getTextContentFromElementHandle(elementHandle, passedColumn)).failed(
				getTextContentFromElementHandle(elementHandle, failedColumn)).skipped(
				getTextContentFromElementHandle(elementHandle, skippedColumn)).productBug(
				getTextContentFromElementHandle(elementHandle, productBugColumn)).autoBug(
				getTextContentFromElementHandle(elementHandle, autoBugColumn)).systemIssue(
				getTextContentFromElementHandle(elementHandle, systemIssueColumn)).toInvestigate(
				getTextContentFromElementHandle(elementHandle, toInvestigateColumn)).build();
	}

	private String getTextContentFromElementHandle(ElementHandle elementHandle, String querySelector) {
		try {
			return elementHandle.querySelector(querySelector).textContent();
		} catch (NullPointerException e) {
			return "";
		}
	}
}