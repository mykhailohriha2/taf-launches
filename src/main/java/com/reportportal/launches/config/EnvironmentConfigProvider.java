package com.reportportal.launches.config;

import org.aeonbits.owner.ConfigFactory;

import lombok.experimental.UtilityClass;


@UtilityClass
public class EnvironmentConfigProvider {
	private static EnvironmentConfig environmentConfig;

	public static synchronized EnvironmentConfig getEnvConfig() {
		if (environmentConfig == null) {
			environmentConfig = ConfigFactory.create(EnvironmentConfig.class);
		}
		return environmentConfig;
	}
}
