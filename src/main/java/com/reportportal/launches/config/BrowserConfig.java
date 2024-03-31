package com.reportportal.launches.config;

import org.aeonbits.owner.Config;


@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({ "system:properties", "file:./src/main/resources/browserConfig.properties" })
public interface BrowserConfig extends Config {
	@DefaultValue("chromium")
	String binary();

	@DefaultValue("chrome")
	String channel();

	@DefaultValue("false")
	boolean headless();
}
