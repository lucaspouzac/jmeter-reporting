package org.jmeter.reporting.rest;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.jmeter.reporting.domain.AggregateSample;
import org.jmeter.reporting.service.LoadTestService;
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

	private final LoadTestService loadTestService;

	private final SampleService sampleService;

	public SeriesResource(LoadTestService loadTestService,
			SampleService sampleService) {
		this.loadTestService = loadTestService;
		this.sampleService = sampleService;
	}

	@GET("/series/throughput/{name}/{version}/{run}")
	public SortedSet<AggregateSample> throughput(String name, String version,
			int run, Optional<Integer> interval) {
		return convert(sampleService.throughput(name, version, run,
				interval.or(DEFAULT_INTERVAL)));
	}

	@GET("/series/thread_count/{name}/{version}/{run}")
	public SortedSet<AggregateSample> threadCount(String name, String version,
			int run, Optional<Integer> interval) {
		return convert(sampleService.threadCount(name, version, run,
				interval.or(DEFAULT_INTERVAL)));
	}

	private SortedSet<AggregateSample> convert(List<AggregateSample> aggs) {
		SortedSet<AggregateSample> result = new TreeSet<>(aggs);
		return result;
	}

}
