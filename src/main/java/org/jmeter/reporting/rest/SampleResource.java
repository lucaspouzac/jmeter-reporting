package org.jmeter.reporting.rest;

import java.util.List;

import javax.inject.Named;

import org.jmeter.reporting.domain.Sample;
import org.jongo.Find;

import restx.annotations.GET;
import restx.annotations.POST;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.jongo.JongoCollection;
import restx.security.PermitAll;

import com.google.common.base.Optional;

@Component
@RestxResource
@PermitAll
public class SampleResource {

	private final JongoCollection samples;

	public SampleResource(@Named("samples") JongoCollection samples) {
		this.samples = samples;
	}

	@GET("/samples")
	public Iterable<Sample> findSamples(Optional<Integer> skip, Optional<Integer> limit) {
		List<Object> paramaters = null;
		String query = null;
		
		Find find = samples.get().find(query, paramaters);
		
		find.skip(skip.or(0)).limit(limit.or(10));
		return find.as(Sample.class);
	}

	@POST("/samples")
	public Sample createSample(Sample sample) {
		samples.get().save(sample);
		return sample;
	}

}
