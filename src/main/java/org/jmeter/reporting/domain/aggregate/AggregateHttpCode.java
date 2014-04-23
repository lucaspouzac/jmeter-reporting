package org.jmeter.reporting.domain.aggregate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AggregateHttpCode implements
		Comparable<AggregateHttpCode> {

	private String httpCode;

	private int count = 0;	
	
	
	@JsonProperty(value = "label")
	public String getLabel() {
		return httpCode;
	}
	
	@JsonProperty(value = "_id")
	public void setHttpCode(String httpCode) {
		this.httpCode = httpCode;
	}

	@JsonProperty(value = "value")
	public int getValue() {
		return count;
	}

	@JsonProperty(value = "count")
	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((httpCode == null) ? 0 : httpCode.hashCode());
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
		AggregateHttpCode other = (AggregateHttpCode) obj;
		if (httpCode == null) {
			if (other.httpCode != null)
				return false;
		} else if (!httpCode.equals(other.httpCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AggregateHttpCode [httpCode=" + httpCode + ", count=" + count + "]";
	}

	@Override
	public int compareTo(AggregateHttpCode o) {
		return this.httpCode.compareTo(o.httpCode);
	}

}
