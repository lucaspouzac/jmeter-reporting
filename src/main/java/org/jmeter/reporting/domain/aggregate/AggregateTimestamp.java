package org.jmeter.reporting.domain.aggregate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AggregateTimestamp implements Comparable<AggregateTimestamp> {

	private Long timestamp;

	@JsonProperty(value = "throughput_success")
	private int throughputSuccess;

	@JsonProperty(value = "throughput_error")
	private int throughputError;

	private int timeSuccess;

	private int timeError;

	@JsonProperty(value = "threads")
	@JsonInclude(Include.NON_NULL)
	private Integer threads;

	@JsonProperty(value = "timestamp")
	public Long getTimestamp() {
		return timestamp;
	}

	@JsonProperty(value = "_id")
	public void setId(Long timestamp) {
		this.timestamp = timestamp;
	}

	public int getThroughputSuccess() {
		return throughputSuccess;
	}

	public void setThroughputSuccess(int throughputSuccess) {
		this.throughputSuccess = throughputSuccess;
	}

	public int getThroughputError() {
		return throughputError;
	}

	public void setThroughputError(int throughputError) {
		this.throughputError = throughputError;
	}
	
	@JsonProperty(value = "avg_times_success")
	public double getAvgTimesSuccess() {
		return throughputSuccess > 0 ? (double) timeSuccess / throughputSuccess : 0;
	}

	@JsonProperty(value = "time_success")
	public void setTimeSuccess(int timeSuccess) {
		this.timeSuccess = timeSuccess;
	}
	
	@JsonProperty(value = "avg_times_error")
	public double getAvgTimesError() {
		return throughputError > 0 ? (double) timeError / throughputError : 0;
	}

	@JsonProperty(value = "time_error")
	public void setTimeError(int timeError) {
		this.timeError = timeError;
	}

	public Integer getThreads() {
		return threads;
	}

	public void setThreads(Integer threads) {
		this.threads = threads;
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
		AggregateTimestamp other = (AggregateTimestamp) obj;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AggregateTimestamp [timestamp=" + timestamp
				+ ", throughputSuccess=" + throughputSuccess
				+ ", throughputError=" + throughputError + ", threads="
				+ threads + "]";
	}

	@Override
	public int compareTo(AggregateTimestamp o) {
		return this.timestamp.compareTo(o.timestamp);
	}

}
