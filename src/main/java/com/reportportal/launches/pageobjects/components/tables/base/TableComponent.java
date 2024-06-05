package com.reportportal.launches.pageobjects.components.tables.base;

import java.util.List;

import com.microsoft.playwright.Locator;
import com.reportportal.launches.pageobjects.components.BaseComponent;


public abstract class TableComponent extends BaseComponent {

	private static final String row = "div[data-id]";
	private static final String headerColumn = "div[class*='headerCell__header']";
	private static final String spinner = "div[class*='grid__spinner']";


	public List<Locator> getAllRows() {
		return getAllByLocator(row);
	}

	public List<Locator> getAllHeaderColumns() {
		return getAllByLocator(headerColumn);
	}

	public void waitForTableIsLoaded() {
		waitHelper.waitForHidden(getByLocator(spinner));
	}
}
