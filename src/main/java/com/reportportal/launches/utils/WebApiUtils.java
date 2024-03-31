package com.reportportal.launches.utils;

import io.restassured.response.Response;
import lombok.experimental.UtilityClass;


@UtilityClass
public class WebApiUtils {
	public String getBodyElementValueFromResponse(Response resp, String pathToElement) {
		return resp.getBody().jsonPath().getString(pathToElement);
	}
}
