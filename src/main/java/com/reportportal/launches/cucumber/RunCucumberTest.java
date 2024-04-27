package com.reportportal.launches.cucumber;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features = "src/test/resources/features", glue = { "com.reportportal.launches.cucumber" }, plugin = {
		"com.reportportal.launches.cucumber.listeners.CucumberExtentReportListener" })
public class RunCucumberTest extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}