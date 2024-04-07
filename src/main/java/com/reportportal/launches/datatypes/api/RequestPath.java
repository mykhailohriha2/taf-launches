package com.reportportal.launches.datatypes.api;

import com.reportportal.launches.config.ConfigHolder;

import lombok.Getter;


@Getter
public enum RequestPath {
	BASE_URL(ConfigHolder.getInstance().baseUrl()),  //
	REPORT_PORTAL_SERVICES("/composite/info"),  //
	GENERATE("/api/v1/demo/{projectName}/generate"),  //
	LAUNCH_BY_PROJECT_NAME("/api/v1/{projectName}/launch"),  //
	TOKEN("/uat/sso/oauth/token");

	private final String path;

	RequestPath(String path) {
		this.path = path;
	}
}
