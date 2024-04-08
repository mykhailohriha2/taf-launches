package com.reportportal.launches.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;


public class WaitHelper {
	public void waitForHidden(Locator locator) {
		locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
	}

	public void waitForVisible(Locator locator) {
		locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
	}
}
