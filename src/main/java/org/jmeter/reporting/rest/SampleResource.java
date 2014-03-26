package org.jmeter.reporting.rest;

import javax.inject.Named;

import org.jmeter.reporting.domain.Sample;

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
	public Iterable<Sample> findSamples(Optional<String> name) {
		if (name.isPresent()) {
			return samples.get().find("{name: #}", name.get()).as(Sample.class);
		} else {
			return samples.get().find().as(Sample.class);
		}
	}

	@POST("/samples")
	public Sample createSample(Sample sample) {
		samples.get().save(sample);
		return sample;
	}

}
