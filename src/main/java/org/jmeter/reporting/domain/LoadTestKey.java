package org.jmeter.reporting.domain;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoadTestKey {

	@JsonProperty(value = "name")
	@NotNull
	private String name;

	@JsonProperty(value = "version")
	@NotNull
	private String version;

	@JsonProperty(value = "run")
	@NotNull
	private Integer run;

	public LoadTestKey() {
	}

	public LoadTestKey(String name, String version, Integer run) {
		super();
		this.name = name;
		this.version = version;
		this.run = run;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getRun() {
		return run;
	}

	public void setRun(Integer pRun) {
		run = pRun;
	}

}
