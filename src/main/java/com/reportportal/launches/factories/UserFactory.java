package com.reportportal.launches.factories;

import static com.reportportal.launches.config.EnvironmentConfigProvider.getEnvConfig;

import com.reportportal.launches.models.User;

import lombok.experimental.UtilityClass;


@UtilityClass
public class UserFactory {
	public static User getDefaultUser() {
		return User.builder().name(getEnvConfig().defaultUserName()).password(
				getEnvConfig().defaultUserPassword()).build();
	}

	public static User getAdminUser() {
		return User.builder().name(getEnvConfig().adminUserName()).password(getEnvConfig().adminUserPassword()).build();
	}
}
