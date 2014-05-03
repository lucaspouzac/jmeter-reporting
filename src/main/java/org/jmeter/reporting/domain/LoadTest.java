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

	@JsonProperty(value = "fn")
	@NotNull
	private boolean finish;

	@JsonProperty(value = "ref")
	@NotNull
	private boolean reference;

	@JsonProperty(value = "iterations")
	private int iterations;

	@JsonProperty(value = "avg_times")
	private double averageTimes;

	@JsonProperty(value = "avg_byt")
	private double averageByte;

	@JsonProperty(value = "success")
	private int success;

	public LoadTest() {
	}

	public LoadTest(LoadTestKey loadTestKey) {
		super();
		this.loadTestKey = loadTestKey;
	}

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

	public boolean isFinish() {
		return finish;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
	}

	public boolean isReference() {
		return reference;
	}

	public void setReference(boolean pReference) {
		reference = pReference;
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

}
