package org.jmeter.reporting.service;

import javax.inject.Named;

import org.jmeter.reporting.domain.Sample;
import org.jongo.Find;

import restx.factory.Component;
import restx.jongo.JongoCollection;

@Component
public class SampleService {

	private final JongoCollection samples;

	public SampleService(@Named("samples") JongoCollection samples) {
		this.samples = samples;
	}

	public Iterable<Sample> find(int skip, int limit) {
		Find find = samples.get().find();
		find.skip(skip).limit(limit);
		return find.as(Sample.class);
	}

	public Sample save(Sample sample) {
		samples.get().save(sample);
		return sample;
	}
}
