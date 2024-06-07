package com.reportportal.launches.pageobjects.components.tables;

import java.util.ArrayList;
import java.util.List;

import com.microsoft.playwright.Locator;
import com.reportportal.launches.models.Launch;
import com.reportportal.launches.pageobjects.components.tables.base.TableComponent;

import lombok.extern.log4j.Log4j2;


@Log4j2
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
		getAllRows().forEach(row -> launches.add(buildLaunch(row)));
		return launches;
	}

	public void selectLaunchByIndex(int index) {
		getAllRows().get(index).locator(launchCheckbox).check();
	}

	public void clickTotalByIndex(int index) {
		getAllRows().get(index).locator(totalColumn).click();
	}

	public void clickPassedByIndex(int index) {
			getAllRows().get(index).locator(passedColumn).click();
	}

	public void clickFailedByIndex(int index) {
			getAllRows().get(index).locator(failedColumn).click();
	}

	public void clickSkippedByIndex(int index) {
			getAllRows().get(index).locator(skippedColumn).click();
	}

	public void openLaunchByName(String name) {
		for (Locator e : getAllRows()){
			if (hasMatch(e, name)){
				e.locator(launchNameColumn).click();
				break;
			}
		}
	}



	private Launch buildLaunch(Locator row) {
		return Launch.builder().name(getTextContent(row, launchNameColumn)).startTime(
				getTextContent(row, startTimeColumn)).total(
				getTextContent(row, totalColumn)).passed(
				getTextContent(row, passedColumn)).failed(
				getTextContent(row, failedColumn)).skipped(
				getTextContent(row, skippedColumn)).productBug(
				getTextContent(row, productBugColumn)).autoBug(
				getTextContent(row, autoBugColumn)).systemIssue(
				getTextContent(row, systemIssueColumn)).toInvestigate(
				getTextContent(row, toInvestigateColumn)).build();
	}

	private String getTextContent(Locator locator, String querySelector) {
		try {
			return locator.locator(querySelector).textContent();
		} catch (NullPointerException e) {
			return "";
		}
	}

	private boolean hasMatch(Locator e, String name) {
		return e.locator(launchNameColumn).textContent().equals(name);
	}
}