package com.reportportal.launches.factories;


import com.reportportal.launches.config.ConfigHolder;
import com.reportportal.launches.models.User;

import lombok.experimental.UtilityClass;


@UtilityClass
public class UserFactory {
	public static User getDefaultUser() {
		return User.builder().name(ConfigHolder.getInstance().defaultUserName()).password(
				ConfigHolder.getInstance().defaultUserPassword()).build();
	}

	public static User getAdminUser() {
		return User.builder().name(ConfigHolder.getInstance().adminUserName()).password(
				ConfigHolder.getInstance().adminUserPassword()).build();
	}
}
