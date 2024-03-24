package com.reportportal.launches.services;

import static com.reportportal.launches.datastructures.RequestPaths.BASE_URL;
import static com.reportportal.launches.datastructures.RequestPaths.REPORT_PORTAL_SERVICES;

import com.reportportal.launches.services.base.BaseApiService;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class ReportPortalService extends BaseApiService {
	public Response sendGetReportPortalInfo(int expectedStatusCode) {
		RequestSpecification spec = new RequestSpecBuilder().setBaseUri(
				BASE_URL.getPath() + REPORT_PORTAL_SERVICES.getPath()).build();
		return sendGetRequest(spec, expectedStatusCode);
	}
}
