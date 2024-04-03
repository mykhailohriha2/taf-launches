package com.reportportal.launches.config;

import org.aeonbits.owner.Config;


@Config.Sources({ "file:./src/main/resources/${env}.properties" })
public interface EnvConfig extends Config {
	@Key("binary")
	String binary();

	@Key("channel")
	String channel();

	@Key("headless")
	boolean headless();

	@Key("default.user.name")
	String defaultUserName();

	@Key("default.user.password")
	String defaultUserPassword();

	@Key("admin.user.name")
	String adminUserName();

	@Key("admin.user.password")
	String adminUserPassword();

	@Key("baseUrl")
	String baseUrl();
}
