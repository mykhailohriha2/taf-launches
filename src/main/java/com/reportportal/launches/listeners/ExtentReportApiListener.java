package com.reportportal.launches.listeners;

import org.testng.ITestResult;


public class ExtentReportApiListener extends ExtentReportListener {
	@Override
	public synchronized void onTestFailure(ITestResult result) {
		log.info(result.getMethod().getMethodName() + " failed!");
		test.get().fail(result.getThrowable());
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}
}