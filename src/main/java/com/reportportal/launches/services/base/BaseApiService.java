package com.reportportal.launches.services.base;

import io.restassured.response.Response;


public class BaseApiService extends BaseRequest {
	public String getBodyElementValueFromResponse(Response resp, String pathToElement) {
		return resp.getBody().jsonPath().getString(pathToElement);
	}
}
