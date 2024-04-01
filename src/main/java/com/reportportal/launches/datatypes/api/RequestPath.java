package com.reportportal.launches.datatypes.api;

import static com.reportportal.launches.config.EnvironmentConfigProvider.getEnvConfig;

import lombok.Getter;


@Getter
public enum RequestPath {
	BASE_URL(getEnvConfig().baseUrl()), REPORT_PORTAL_SERVICES("/composite/info");

	private final String path;

	RequestPath(String path) {
		this.path = path;
	}
}
