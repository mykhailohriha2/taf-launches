package com.reportportal.launches.models.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;


@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class AnalyzeLaunchRQ {

	@JsonProperty("analyzeItemsMode")
	private List<String> analyzeItemsMode;

	@JsonProperty("analyzerMode")
	private String analyzerMode;

	@JsonProperty("analyzerTypeName")
	private String analyzerTypeName;

	@JsonProperty("launchId")
	private int launchId;
}
