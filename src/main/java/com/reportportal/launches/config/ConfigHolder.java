package com.reportportal.launches.config;

import org.aeonbits.owner.ConfigFactory;

import lombok.experimental.UtilityClass;


@UtilityClass
public class ConfigHolder {
	private static class LazyHolder {
		static final EnvConfig INSTANCE = ConfigFactory.create(EnvConfig.class);
	}

	public static EnvConfig getInstance() {
		return LazyHolder.INSTANCE;
	}
}
