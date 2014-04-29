package org.jmeter.reporting.rest.parts;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.fileupload.UploadContext;

import restx.RestxRequest;

public class RestXRequestContext implements UploadContext {

	private RestxRequest request;
	
	public RestXRequestContext(RestxRequest request) {
		this.request = request;
	}
	
	@Override
	public String getCharacterEncoding() {
		return "utf8";
	}

	@Override
	public String getContentType() {
		return request.getContentType();
	}

	@Override
	public int getContentLength() {
		// TODO PR to RestX project
		return -1;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return request.getContentStream();
	}

	@Override
	public long contentLength() {
		// TODO PR to RestX project
		return -1;
	}

}
