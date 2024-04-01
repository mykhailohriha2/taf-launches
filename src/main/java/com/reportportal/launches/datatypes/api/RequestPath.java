package com.reportportal.launches.datatypes.api;

import com.reportportal.launches.config.ConfigHolder;

import lombok.Getter;


@Getter
public enum RequestPath {
	BASE_URL(ConfigHolder.getInstance().baseUrl()), REPORT_PORTAL_SERVICES("/composite/info");

	private final String path;

	RequestPath(String path) {
		this.path = path;
	}
}
