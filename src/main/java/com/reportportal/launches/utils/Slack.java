package com.reportportal.launches.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.experimental.UtilityClass;


@UtilityClass
public class Slack {
	static String webhookUrl = "https://hooks.slack.com/services/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";

	public static void sendSlackNotification(String message, String color) {
		String jsonPayload = "{\"attachments\": [{\"color\": \"" + color + "\", \"text\": \"" + message + "\"}]}";
		RestAssured.given().contentType(ContentType.JSON).body(jsonPayload).post(webhookUrl);
	}
}
