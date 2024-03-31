package com.reportportal.launches.datastructures.webapi;

import com.reportportal.launches.config.PropertiesProvider;

import lombok.Getter;


@Getter
public enum RequestPath {
	BASE_URL(PropertiesProvider.getEnvPropertyByKey("baseUrl")), REPORT_PORTAL_SERVICES("/composite/info");

	private final String path;

	RequestPath(String path) {
		this.path = path;
	}
}
