package com.reportportal.launches.api.base;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class BaseClient {
	protected Response sendPostRequest(RequestSpecification requestSpecification, int expectedStatusCode) {
		return given().spec(requestSpecification).when().post().then().statusCode(
				expectedStatusCode).extract().response();
	}

	protected Response sendGetRequest(RequestSpecification requestSpecification, int expectedStatusCode) {
		return given().spec(requestSpecification).contentType(ContentType.JSON).when().get().then().statusCode(
				expectedStatusCode).extract().response();
	}

	protected Response sendDeleteRequest(RequestSpecification requestSpecification, int expectedStatusCode) {
		return given().spec(requestSpecification).when().delete().then().statusCode(
				expectedStatusCode).extract().response();
	}

	protected Response sendPutRequest(RequestSpecification requestSpecification, int expectedStatusCode) {
		return given().spec(requestSpecification).when().put().then().statusCode(expectedStatusCode).extract().response();
	}
}
