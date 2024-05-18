package com.reportportal.launches.cucumber.steps;

import static org.assertj.core.api.Assertions.assertThat;

import com.reportportal.launches.pageobjects.pages.MainPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


public class MainPageSteps {
	private final MainPage mainPage = new MainPage();

	@Then("User should see the {string} message")
	public void assertUserSeeMessage(String message) {
		assertThat(mainPage.getNotificationTextAndCloseTooltip()).isEqualTo(message);
	}

	@Given("User navigate to the Launches page")
	public void navigateToLaunchesPage() {
		mainPage.navigateToLaunches();
	}
}
