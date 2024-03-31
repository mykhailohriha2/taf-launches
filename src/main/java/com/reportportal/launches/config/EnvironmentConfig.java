package com.reportportal.launches.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({ "system:properties", "file:./src/main/resources/environment.properties" })
public interface EnvironmentConfig extends Config {
	@Key("default.user.name")
	String defaultUserName();

	@Key("default.user.password")
	String defaultUserPassword();

	@Key("admin.user.name")
	String adminUserName();

	@Key("admin.user.password")
	String adminUserPassword();

	String baseUrl();
}
