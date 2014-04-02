package org.jmeter.reporting.domain;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoadTestKey {

	@JsonProperty(value = "name")
	@NotNull
	private String name;

	@JsonProperty(value = "version")
	@NotNull
	private String version;

	@JsonProperty(value = "date")
	@NotNull
	private Date date;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
