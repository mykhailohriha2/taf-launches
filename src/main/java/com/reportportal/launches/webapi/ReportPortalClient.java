package com.reportportal.launches.webapi;

import static com.reportportal.launches.datastructures.webapi.RequestPath.BASE_URL;
import static com.reportportal.launches.datastructures.webapi.RequestPath.REPORT_PORTAL_SERVICES;

import com.reportportal.launches.webapi.base.BaseClient;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class ReportPortalClient extends BaseClient {
	public Response sendGetReportPortalInfo(int expectedStatusCode) {
		RequestSpecification spec = new RequestSpecBuilder().setBaseUri(
				BASE_URL.getPath() + REPORT_PORTAL_SERVICES.getPath()).build();
		return sendGetRequest(spec, expectedStatusCode);
	}
}
