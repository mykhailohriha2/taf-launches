package com.reportportal.launches.datastructures.webapi;

import com.reportportal.launches.config.PropertiesController;


public enum RequestPaths {
	BASE_URL(PropertiesController.getEnvPropertyByKey("baseUrl")), REPORT_PORTAL_SERVICES("/composite/info");

	private final String requestPath;

	RequestPaths(String path) {
		this.requestPath = path;
	}

	public String getPath() {
		return requestPath;
	}

}
