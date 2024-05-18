package com.reportportal.launches.api;

import static com.reportportal.launches.datatypes.api.RequestPath.*;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.ContentType.URLENC;
import static org.apache.http.HttpStatus.SC_OK;

import java.util.List;

import com.reportportal.launches.api.base.BaseClient;
import com.reportportal.launches.models.User;
import com.reportportal.launches.utils.WebApiUtils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReportPortalClient extends BaseClient {
	private final ThreadLocal<String> sessionToken = new ThreadLocal<>();

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

	public Response sendPostGenerateDemoDataForProject(String project, int expectedStatusCode) {
		RequestSpecification spec = new RequestSpecBuilder()
				.setBaseUri(BASE_URL.getPath())
				.setBasePath(GENERATE_DEMO_DATA.getPath())
				.addPathParam("projectName", project)
				.addHeader("Authorization", sessionToken.get())
				.setContentType(JSON)
				.setBody("{}")
				.build();
		return sendPostRequest(spec, expectedStatusCode);
	}

	public Response sendGetLaunchesByProject(String project, int expectedStatusCode) {
		RequestSpecification spec = new RequestSpecBuilder()
				.setBaseUri(BASE_URL.getPath())
				.setBasePath(LAUNCH_BY_PROJECT_NAME.getPath())
				.addPathParam("projectName", project)
				.addHeader("Authorization", sessionToken.get())
				.setContentType(JSON)
				.build();
		return sendGetRequest(spec, expectedStatusCode);
	}

	public Response sendDeleteLaunchesInProjectByIds(String project, List<Integer> launchIds,  int expectedStatusCode) {
		RequestSpecification spec = new RequestSpecBuilder()
				.setBaseUri(BASE_URL.getPath())
				.setBasePath(LAUNCH_BY_PROJECT_NAME.getPath())
				.addPathParam("projectName", project)
				.addHeader("Authorization", sessionToken.get())
				.setContentType(JSON)
				.setBody("{ \"ids\": " + launchIds +" }")
				.build();
		return sendDeleteRequest(spec, expectedStatusCode);
	}

	public Response sendPostToken(User user, int expectedStatusCode) {
		String tokenType = "Basic ";
		String defaultToken = "dWk6dWltYW4=";
		RequestSpecification spec = new RequestSpecBuilder()
				.setBaseUri(BASE_URL.getPath() + String.format(TOKEN.getPath()))
				.addHeader("Authorization", tokenType + defaultToken)
				.addFormParam("grant_type", "password")
				.addFormParam("username", user.getName())
				.addFormParam("password", user.getPassword())
				.setContentType(URLENC)
				.build();
		return sendPostRequest(spec, expectedStatusCode);
	}

	public void createSession(User user) {
		Response response = sendPostToken(user, SC_OK);
		String token = WebApiUtils.getBodyElementValueFromResponse(response, "access_token");
		String tokenType = WebApiUtils.getBodyElementValueFromResponse(response, "token_type");
		sessionToken.set(tokenType + " " + token);
	}
}
