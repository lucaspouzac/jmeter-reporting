package org.jmeter.reporting.domain;

import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sample {

	@Id
	@ObjectId
	private String key;

	@JsonProperty(value = "hn", required = true)
	private String hostname;

	@JsonProperty(value = "gt", required = true)
	private Integer groupThreads;

	@JsonProperty(value = "rc", required = true)
	private String responseCode;

	@JsonProperty(value = "rcOk")
	private Boolean responseCodeOK;

	@JsonProperty(value = "rm")
	private String responseMessage;

	@JsonProperty(value = "tn")
	private String threadName;

	@JsonProperty(value = "lb")
	private String sampleLabel;

	@JsonProperty(value = "data")
	private String responseData;

	@JsonProperty(value = "sd")
	private String samplerData;

	@JsonProperty(value = "t")
	private Long time;

	@JsonProperty(value = "s")
	private Boolean successful;

	@JsonProperty(value = "dt")
	private String dataType;

	@JsonProperty(value = "de")
	private String dataEncoding;

	@JsonProperty(value = "reqH")
	private String requestHeaders;

	@JsonProperty(value = "resH")
	private String responseHeaders;

	@JsonProperty(value = "ct")
	private String contentType;

	@JsonProperty(value = "it")
	private Long idleTime;

	@JsonProperty(value = "et")
	private Long endTime;

	@JsonProperty(value = "st")
	private Long startTime;

	@JsonProperty(value = "byt")
	private Integer bytes;

	@JsonProperty(value = "lt")
	private Long latency;

	@JsonProperty(value = "url")
	private String url;

	@JsonProperty(value = "at")
	private Integer allThreads;

	@JsonProperty(value = "hs")
	private Integer headersSize;

	@JsonProperty(value = "bs")
	private Integer bodySize;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public Integer getGroupThreads() {
		return groupThreads;
	}

	public void setGroupThreads(Integer groupThreads) {
		this.groupThreads = groupThreads;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public Boolean getResponseCodeOK() {
		return responseCodeOK;
	}

	public void setResponseCodeOK(Boolean responseCodeOK) {
		this.responseCodeOK = responseCodeOK;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public String getSampleLabel() {
		return sampleLabel;
	}

	public void setSampleLabel(String sampleLabel) {
		this.sampleLabel = sampleLabel;
	}

	public String getResponseData() {
		return responseData;
	}

	public void setResponseData(String responseData) {
		this.responseData = responseData;
	}

	public String getSamplerData() {
		return samplerData;
	}

	public void setSamplerData(String samplerData) {
		this.samplerData = samplerData;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Boolean getSuccessful() {
		return successful;
	}

	public void setSuccessful(Boolean successful) {
		this.successful = successful;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDataEncoding() {
		return dataEncoding;
	}

	public void setDataEncoding(String dataEncoding) {
		this.dataEncoding = dataEncoding;
	}

	public String getRequestHeaders() {
		return requestHeaders;
	}

	public void setRequestHeaders(String requestHeaders) {
		this.requestHeaders = requestHeaders;
	}

	public String getResponseHeaders() {
		return responseHeaders;
	}

	public void setResponseHeaders(String responseHeaders) {
		this.responseHeaders = responseHeaders;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Long getIdleTime() {
		return idleTime;
	}

	public void setIdleTime(Long idleTime) {
		this.idleTime = idleTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Integer getBytes() {
		return bytes;
	}

	public void setBytes(Integer bytes) {
		this.bytes = bytes;
	}

	public Long getLatency() {
		return latency;
	}

	public void setLatency(Long latency) {
		this.latency = latency;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getAllThreads() {
		return allThreads;
	}

	public void setAllThreads(Integer allThreads) {
		this.allThreads = allThreads;
	}

	public Integer getHeadersSize() {
		return headersSize;
	}

	public void setHeadersSize(Integer headersSize) {
		this.headersSize = headersSize;
	}

	public Integer getBodySize() {
		return bodySize;
	}

	public void setBodySize(Integer bodySize) {
		this.bodySize = bodySize;
	}

}
