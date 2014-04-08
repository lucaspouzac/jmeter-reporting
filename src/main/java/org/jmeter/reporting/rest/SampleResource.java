package org.jmeter.reporting.rest;

import org.jmeter.reporting.domain.Sample;
import org.jmeter.reporting.service.SampleService;

import restx.annotations.GET;
import restx.annotations.POST;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.security.PermitAll;

import com.google.common.base.Optional;

@Component
@RestxResource
@PermitAll
public class SampleResource {

	private final SampleService sampleService;

	public SampleResource(SampleService sampleService) {
		this.sampleService = sampleService;
	}

	@GET("/samples")
	public Iterable<Sample> find(Optional<Integer> skip, Optional<Integer> limit) {
		return sampleService.find(skip.or(0), limit.or(10));
	}

	@POST("/samples")
	public Sample save(Sample sample) {
		return sampleService.save(sample);
	}

}
