package com.reportportal.launches.models;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Launch {
	private String name;
	private String startTime;
	private String total;
	private String passed;
	private String failed;
	private String skipped;
	private String productBug;
	private String autoBug;
	private String systemIssue;
	private String toInvestigate;
}