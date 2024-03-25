package com.reportportal.launches.utils;

import static com.reportportal.launches.config.PropertiesController.getEnvPropertyByKey;

import com.reportportal.launches.model.User;


public class UserUtils {
	private UserUtils() {
	}

	public static final String DEFAULT_USER_NAME = "default.user.name";
	public static final String ADMIN_USER_NAME = "admin.user.name";
	public static final String DEFAULT_USER_PASSWORD = "default.user.password";
	public static final String ADMIN_USER_PASSWORD = "admin.user.password";

	public static User getDefaultUser() {
		return User.builder().name(getEnvPropertyByKey(DEFAULT_USER_NAME)).password(
				getEnvPropertyByKey(DEFAULT_USER_PASSWORD)).build();
	}

	public static User getAdminUser() {
		return User.builder().name(getEnvPropertyByKey(ADMIN_USER_NAME)).password(
				getEnvPropertyByKey(ADMIN_USER_PASSWORD)).build();
	}
}
