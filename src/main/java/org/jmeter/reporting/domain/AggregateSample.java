package org.jmeter.reporting.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AggregateSample {

	@JsonProperty(value = "_id")
	private Long timestamp;

	@JsonProperty(value = "value")
	private Integer value;

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "AggregateSample [timestamp=" + timestamp + ", value=" + value + "]";
	}

}
