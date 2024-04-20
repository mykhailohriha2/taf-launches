package com.reportportal.launches.config;

import java.util.Optional;

import org.aeonbits.owner.ConfigFactory;

import lombok.experimental.UtilityClass;


@UtilityClass
public class ConfigHolder {
	private static class LazyHolder {
		static {
			Optional.ofNullable(System.getenv().get("ENV")).ifPresentOrElse(env -> System.setProperty("env", env),
					() -> System.setProperty("env", "chromeLocal"));
		}
		static final EnvConfig INSTANCE = ConfigFactory.create(EnvConfig.class);
	}

	public static EnvConfig getInstance() {
		return LazyHolder.INSTANCE;
	}
}
