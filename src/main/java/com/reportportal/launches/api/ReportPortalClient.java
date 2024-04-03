package com.reportportal.launches.api;

import static com.reportportal.launches.datatypes.api.RequestPath.BASE_URL;
import static com.reportportal.launches.datatypes.api.RequestPath.REPORT_PORTAL_SERVICES;

import com.reportportal.launches.api.base.BaseClient;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReportPortalClient extends BaseClient {
	private static class LazyHolder {
		static final ReportPortalClient INSTANCE = new ReportPortalClient();
	}

	public static ReportPortalClient getInstance() {
		return LazyHolder.INSTANCE;
	}

	public Response sendGetReportPortalInfo(int expectedStatusCode) {
		RequestSpecification spec = new RequestSpecBuilder().setBaseUri(
				BASE_URL.getPath() + REPORT_PORTAL_SERVICES.getPath()).build();
		return sendGetRequest(spec, expectedStatusCode);
	}
}
