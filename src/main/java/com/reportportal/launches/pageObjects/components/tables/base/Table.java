package com.reportportal.launches.pageObjects.components.tables.base;

import java.util.List;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import com.reportportal.launches.playwright.PlaywrightFacade;


public abstract class Table {
	private final Page page;

	private final String row = "div[data-id]";
	private final String headerColumn = "div[class*='headerCell__header']";
	private final String spinner = "div[class*='grid__spinner']";

	protected Table() {
		this.page = PlaywrightFacade.getInstance().getPage();
	}

	public List<ElementHandle> getAllRows() {
		return page.querySelectorAll(row);
	}

	public List<ElementHandle> getAllHeaderColumns() {
		return page.querySelectorAll(headerColumn);
	}

	public void waitForTableIsLoaded() {
		page.locator(spinner).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
	}
}
