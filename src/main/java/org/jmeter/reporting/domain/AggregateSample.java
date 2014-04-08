package org.jmeter.reporting.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AggregateSample implements Comparable<AggregateSample> {

	@JsonProperty(value = "_id")
	private Long timestamp;

	@JsonProperty(value = "value")
	private int value = 0;

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "AggregateSample [timestamp=" + timestamp + ", value=" + value
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((timestamp == null) ? 0 : timestamp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AggregateSample other = (AggregateSample) obj;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}

	@Override
	public int compareTo(AggregateSample o) {
		return this.timestamp.compareTo(o.timestamp);
	}

}
