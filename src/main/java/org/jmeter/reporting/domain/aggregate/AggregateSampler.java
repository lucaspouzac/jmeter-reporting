package org.jmeter.reporting.domain.aggregate;

import java.util.SortedSet;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AggregateSampler implements Comparable<AggregateSampler> {

	private String sampler;

	@JsonProperty(value = "iterations")
	private int iterations;

	@JsonProperty(value = "avg_times")
	private double averageTimes;

	@JsonProperty(value = "avg_byt")
	private double averageByte;

	@JsonProperty(value = "success")
	private int success;

	@JsonProperty(value = "aggs_timestamp")
	@JsonInclude(Include.NON_NULL)
	private SortedSet<AggregateTimestamp> aggregatesTimestamp;

	@JsonProperty(value = "label")
	public String getLabel() {
		return sampler;
	}

	@JsonProperty(value = "_id")
	public void setSampler(String sampler) {
		this.sampler = sampler;
	}

	public int getIterations() {
		return iterations;
	}

	public void setIterations(int iterations) {
		this.iterations = iterations;
	}

	public double getAverageTimes() {
		return averageTimes;
	}

	public void setAverageTimes(double averageTimes) {
		this.averageTimes = averageTimes;
	}

	public double getAverageByte() {
		return averageByte;
	}

	public void setAverageByte(double averageByte) {
		this.averageByte = averageByte;
	}

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public SortedSet<AggregateTimestamp> getAggregatesTimestamp() {
		return aggregatesTimestamp;
	}

	public void setAggregatesTimestamp(
			SortedSet<AggregateTimestamp> aggregatesTimestamp) {
		this.aggregatesTimestamp = aggregatesTimestamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sampler == null) ? 0 : sampler.hashCode());
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
		AggregateSampler other = (AggregateSampler) obj;
		if (sampler == null) {
			if (other.sampler != null)
				return false;
		} else if (!sampler.equals(other.sampler))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AggregateSampler [sampler=" + sampler + ", iterations="
				+ iterations + ", averageTimes=" + averageTimes
				+ ", averageByte=" + averageByte + ", success=" + success
				+ ", aggregatesTimestamp=" + aggregatesTimestamp + "]";
	}

	@Override
	public int compareTo(AggregateSampler o) {
		return this.sampler.compareTo(o.sampler);
	}

}
