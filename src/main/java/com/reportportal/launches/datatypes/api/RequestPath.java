package com.reportportal.launches.datatypes.api;

import com.reportportal.launches.config.ConfigHolder;

import lombok.Getter;


@Getter
public enum RequestPath {
	BASE_URL(ConfigHolder.getInstance().baseUrl()),  //
	REPORT_PORTAL_SERVICES("/composite/info"),  //
	GENERATE_DEMO_DATA("/api/v1/demo/{projectName}/generate"),  //
	LAUNCH_BY_PROJECT_NAME("/api/v1/{projectName}/launch"),  //
	START_LAUNCH_ANALYSIS("/api/v1/{projectName}/launch/analyze"), //
	TOKEN("/uat/sso/oauth/token");

	private final String path;

	RequestPath(String path) {
		this.path = path;
	}
}
