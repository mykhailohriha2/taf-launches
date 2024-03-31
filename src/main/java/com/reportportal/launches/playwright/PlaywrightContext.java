package com.reportportal.launches.playwright;

import com.microsoft.playwright.*;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class PlaywrightContext {
	private Playwright playwright;
	private Browser browser;
	private BrowserContext browserContext;
	private Page page;
}
