package org.jmeter.reporting.domain.aggregate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AggregateTimestamp implements Comparable<AggregateTimestamp> {

	private Long timestamp;

	@JsonProperty(value = "throughput_success")
	private int throughputSuccess;

	@JsonProperty(value = "throughput_error")
	private int throughputError;

	@JsonProperty(value = "thread_count")
	private int threadCount;

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

	public int getThreadCount() {
		return threadCount;
	}

	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
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
				+ ", throughputError=" + throughputError + ", threadCount="
				+ threadCount + "]";
	}

	@Override
	public int compareTo(AggregateTimestamp o) {
		return this.timestamp.compareTo(o.timestamp);
	}

}
