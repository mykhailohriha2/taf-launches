package com.reportportal.launches.cucumber.hooks;

import com.reportportal.launches.playwright.PlaywrightFacade;

import io.cucumber.java.After;
import io.cucumber.java.Before;


public class CucumberHooks {
	private final PlaywrightFacade playwrightFactory = PlaywrightFacade.getInstance();

	@Before
	public void beforeEach() {
		playwrightFactory.startPlaywright();
	}

	@After
	public void afterEach() {
		playwrightFactory.closePlaywright();
	}
}
