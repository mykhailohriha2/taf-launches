package com.reportportal.launches.playwright;

import com.microsoft.playwright.*;

import lombok.Data;


@Data
public class PlaywrightSession {
	private Playwright playwright;
	private Browser browser;
	private BrowserContext browserContext;
	private Page page;
}
