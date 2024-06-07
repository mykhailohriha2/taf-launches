package com.reportportal.launches.cucumber.steps;

import com.reportportal.launches.pageobjects.pages.LaunchesListPage;

import io.cucumber.java.en.When;


public class LaunchesSteps {
	private final LaunchesListPage launchesListPage = new LaunchesListPage();

	@When("User removes the launch with {int} index")
	public void removeLaunchByIndex(int index) {
		launchesListPage.clickDeleteLaunchByIndex(index);
		launchesListPage.clickConfirmDelete();
	}
}
