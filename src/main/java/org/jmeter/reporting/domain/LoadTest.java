package org.jmeter.reporting.domain;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoadTest {

	@Id
	@ObjectId
	private String key;

	@JsonProperty(value = "ltKey")
	@NotNull
	@Valid
	private LoadTestKey loadTestKey;

	@JsonProperty(value = "date")
	@NotNull
	private Date date = new Date();

	@JsonProperty(value = "st")
	private int status;

	@JsonProperty(value = "ref")
	private boolean reference;

	public String getKey() {
		return key;
	}

	public void setKey(String pKey) {
		key = pKey;
	}

	public LoadTestKey getLoadTestKey() {
		return loadTestKey;
	}

	public void setLoadTestKey(LoadTestKey pLoadTestKey) {
		loadTestKey = pLoadTestKey;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int pStatus) {
		status = pStatus;
	}

	public boolean isReference() {
		return reference;
	}

	public void setReference(boolean pReference) {
		reference = pReference;
	}

}
