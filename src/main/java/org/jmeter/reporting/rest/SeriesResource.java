package org.jmeter.reporting.rest;

import java.util.SortedSet;
import java.util.TreeSet;

import org.jmeter.reporting.domain.aggregate.AggregateHttpCode;
import org.jmeter.reporting.domain.aggregate.AggregateSampler;
import org.jmeter.reporting.domain.aggregate.AggregateTimestamp;
import org.jmeter.reporting.service.SampleService;

import restx.annotations.GET;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.security.PermitAll;

import com.google.common.base.Optional;

@Component
@RestxResource
@PermitAll
public class SeriesResource {

	private static final int DEFAULT_INTERVAL = 1000;

	private final SampleService sampleService;

	public SeriesResource(SampleService sampleService) {
		this.sampleService = sampleService;
	}

	@GET("/series/aggregate_by_timestamp/{name}/{version}/{run}")
	public SortedSet<AggregateTimestamp> aggregateByTimestamp(String name,
			String version, int run, Optional<Integer> interval) {
		return new TreeSet<>(sampleService.aggregateByTimestamp(name, version,
				run, interval.or(DEFAULT_INTERVAL)));
	}

	@GET("/series/aggregate_by_httpcode/{name}/{version}/{run}")
	public SortedSet<AggregateHttpCode> aggregateByHttpCode(String name,
			String version, int run) {
		return new TreeSet<>(sampleService.aggregateByHttpCode(name, version,
				run));
	}

	@GET("/series/aggregate_by_sampler/{name}/{version}/{run}")
	public SortedSet<AggregateSampler> aggregateBySampler(String name,
			String version, int run) {
		return new TreeSet<>(sampleService.aggregateBySampler(name, version,
				run));
	}

}
