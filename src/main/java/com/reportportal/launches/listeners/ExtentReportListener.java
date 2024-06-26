package com.reportportal.launches.listeners;

import static com.reportportal.launches.playwright.PlaywrightFacade.takeScreenshot;
import static com.reportportal.launches.utils.Slack.sendSlackNotification;

import java.io.IOException;
import java.nio.file.*;
import java.util.Calendar;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReportListener implements ITestListener {

	private static final String OUTPUT_FOLDER = "./build/";
	private static final String FILE_NAME = "TestExecutionReport.html";
	protected static final Logger log = LogManager.getLogger(ExtentReportListener.class);
	private static final ExtentReports extent = init();
	protected static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	private static ExtentReports extentReports;


	private static ExtentReports init() {
		Path path = Paths.get(OUTPUT_FOLDER);
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		extentReports = new ExtentReports();
		ExtentSparkReporter reporter = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME);
		reporter.config().setReportName("Automation Tests Result");
		extentReports.attachReporter(reporter);
		return extentReports;
	}

	@Override
	public synchronized void onStart(ITestContext context) {
		log.info("Test Suite started " + context.getSuite().getName());
		sendSlackNotification(String.format("TEST RUN STARTED - *%s*", context.getSuite().getName()), "#FFFFFF");
	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		log.info("Test Suite is ending " + context.getSuite().getName());
		extent.flush();
		test.remove();
		String suiteName = context.getSuite().getName();
		String suiteResult;
		String suiteResultColor;
		if(context.getFailedTests().size() > 0) {
			suiteResult = "*FAILED*";
			suiteResultColor = "danger";
		}
		else {
			suiteResult = "*PASS*";
			suiteResultColor = "good";
		}
		sendSlackNotification(String.format("TEST RUN COMPLETED - *%s*. The RESULT is - %s", suiteName, suiteResult), suiteResultColor);
	}

	@Override
	public synchronized void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String qualifiedName = result.getMethod().getQualifiedName();
		int last = qualifiedName.lastIndexOf(".");
		int mid = qualifiedName.substring(0, last).lastIndexOf(".");
		String className = qualifiedName.substring(mid + 1, last);
		log.info(methodName + " started!");
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
				result.getMethod().getDescription());
		extentTest.assignCategory(result.getTestContext().getSuite().getName());
		extentTest.assignCategory(className);
		test.set(extentTest);
		test.get().getModel().setStartTime(getTime(result.getStartMillis()));
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		log.info(result.getMethod().getMethodName() + " passed!");
		test.get().pass("Test passed");
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		log.info(result.getMethod().getMethodName() + " failed!");
		test.get().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshot(),
				result.getMethod().getMethodName()).build());
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		log.info(result.getMethod().getMethodName() + " skipped!");
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	@Override
	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		log.info("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName());
	}

	protected Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
}
