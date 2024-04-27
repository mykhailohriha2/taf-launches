package com.reportportal.launches.cucumber.listeners;

import static com.reportportal.launches.playwright.PlaywrightFacade.takeScreenshot;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.Status;
import io.cucumber.plugin.event.*;


public class CucumberExtentReportListener implements ConcurrentEventListener {
	private ExtentSparkReporter htmlReporter;
	static ExtentReports reports;
	private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	public CucumberExtentReportListener() {
		String filename = System.getProperty("user.dir") + "./build/" + "TestExecutionReport.html";
		htmlReporter = new ExtentSparkReporter(filename);
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
	}

	@Override
	public void setEventPublisher(EventPublisher publisher) {
		publisher.registerHandlerFor(TestCaseStarted.class, this::handleTestCaseStarted);
		publisher.registerHandlerFor(TestStepFinished.class, this::handleTestStepFinished);
		publisher.registerHandlerFor(TestCaseFinished.class, this::handleTestCaseFinished);
	}

	private void handleTestCaseStarted(TestCaseStarted event) {
		ExtentTest extentTest = reports.createTest(event.getTestCase().getName());
		test.set(extentTest);
	}

	private void handleTestStepFinished(TestStepFinished event) {
		TestStep testStep = event.getTestStep();
		String stepText;
		if (testStep instanceof PickleStepTestStep) {
			stepText = ((PickleStepTestStep) testStep).getStep().getText();
		} else {
			stepText = testStep.getCodeLocation();
		}
		if (event.getResult().getStatus().is(Status.FAILED)) {
			test.get().fail(stepText, MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshot()).build());
		} else if (event.getResult().getStatus().is(Status.PASSED)) {
			test.get().pass(stepText + " - passed");
		}
	}

	private void handleTestCaseFinished(TestCaseFinished event) {
		reports.flush();
		test.remove();
	}
}
