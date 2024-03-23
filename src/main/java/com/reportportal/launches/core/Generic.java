package com.reportportal.launches.core;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;


public class Generic {
	protected static ThreadLocal<Page> page = new ThreadLocal<>();

	public Generic() {
		page.set(Base.getBasePage());
	}

	public Generic(String set) {
	}

	public static Page getPage() {
		return page.get();
	}

	public Locator getById(String selector) {
		return page.get().locator("#" + selector);
	}

	public Locator getByLocator(String selector) {
		return page.get().locator(selector);
	}

	public Locator getByText(String selector) {
		return page.get().getByText(selector, new Page.GetByTextOptions().setExact(true));
	}

	public Locator getByTitle(String selector) {
		return page.get().getByTitle(selector, new Page.GetByTitleOptions().setExact(true));
	}

	public Locator getByLabel(String selector) {
		return page.get().getByLabel(selector, new Page.GetByLabelOptions().setExact(true));
	}

	public Locator getByPlaceholder(String selector) {
		return page.get().getByPlaceholder(selector, new Page.GetByPlaceholderOptions().setExact(true));
	}

	public Locator getByAltTxt(String selector) {
		return page.get().getByAltText(selector, new Page.GetByAltTextOptions().setExact(true));
	}

	public Locator getByTestDataId(String id) {
		return page.get().getByTestId(id);
	}

	public void hardWait(int millis) {
		page.get().waitForTimeout(millis);
	}

	public void navigate(String url) {
		page.get().navigate(url);
	}

	public void closeContext() {
		page.get().context().browser().close();
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
}
