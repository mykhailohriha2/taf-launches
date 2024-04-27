package com.reportportal.launches.cucumber.steps;

import com.reportportal.launches.pageobjects.pages.LaunchesPage;

import io.cucumber.java.en.When;


public class LaunchesSteps {
	private final LaunchesPage launchesPage = new LaunchesPage();

	@When("User removes the launch with {int} index")
	public void removeLaunchByIndex(int index) {
		launchesPage.clickDeleteLaunchByIndex(index);
		launchesPage.clickConfirmDelete();
	}
}
