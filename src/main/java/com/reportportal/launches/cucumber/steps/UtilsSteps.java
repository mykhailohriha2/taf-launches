package com.reportportal.launches.cucumber.steps;

import static org.apache.http.HttpStatus.SC_OK;

import java.util.*;

import com.reportportal.launches.api.ReportPortalClient;
import com.reportportal.launches.models.User;

import io.cucumber.java.en.Given;


public class UtilsSteps {
	private final ReportPortalClient reportPortalClient = ReportPortalClient.getInstance();

	@Given("Demo data is created for the users and their projects")
	public void createDemoDataForUsers(io.cucumber.datatable.DataTable dataTable) {
		List<User> users = new ArrayList<>();
		for (Map<String, String> map : dataTable.asMaps(String.class, String.class)) {
			users.add(User.builder().name(map.get("username")).password(map.get("userpassword")).defaultProject(
					map.get("project")).build());
		}
		users.forEach(user -> {
			reportPortalClient.createSession(user);
			reportPortalClient.sendPostGenerateDemoDataForProject(user.getDefaultProject(), SC_OK);
		});
	}
}
