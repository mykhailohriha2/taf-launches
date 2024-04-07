package com.reportportal.launches.pageObjects.pages.base;

import java.util.List;

import com.microsoft.playwright.*;
import com.reportportal.launches.playwright.PlaywrightFacade;


public class BasePage {
	protected Page page;

	public BasePage() {
		this.page = PlaywrightFacade.getInstance().getPage();
	}

	public Locator getById(String selector) {
		return page.locator("#" + selector);
	}

	public Locator getByLocator(String selector) {
		return page.locator(selector);
	}

	public List<ElementHandle> getAllByLocator(String selector) {
		return page.querySelectorAll(selector);
	}

	public Locator getByText(String selector) {
		return page.getByText(selector, new Page.GetByTextOptions().setExact(true));
	}

	public Locator getByTitle(String selector) {
		return page.getByTitle(selector, new Page.GetByTitleOptions().setExact(true));
	}

	public Locator getByLabel(String selector) {
		return page.getByLabel(selector, new Page.GetByLabelOptions().setExact(true));
	}

	public Locator getByPlaceholder(String selector) {
		return page.getByPlaceholder(selector, new Page.GetByPlaceholderOptions().setExact(true));
	}

	public Locator getByAltTxt(String selector) {
		return page.getByAltText(selector, new Page.GetByAltTextOptions().setExact(true));
	}

	public Locator getByTestDataId(String id) {
		return page.getByTestId(id);
	}

	public void hardWait(int millis) {
		page.waitForTimeout(millis);
	}

	public void navigate(String url) {
		page.navigate(url);
	}

	public void closeContext() {
		page.context().browser().close();
	}

	public void fillAndEnter(Locator locator, String text) {
		locator.fill(text);
		locator.press("Enter");
	}

	public void fill(Locator locator, String text) {
		locator.fill(text);
	}

	public void click(Locator locator) {
		locator.click();
	}

	public void getInnerText(Locator locator) {
		locator.innerText();
	}

	public void navigateToLaunches() {
		getByLocator("div[class*='sidebar'] a[href*='launches']").click();
	}
}
