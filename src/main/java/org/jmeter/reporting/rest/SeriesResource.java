package org.jmeter.reporting.rest;

import java.util.SortedSet;
import java.util.TreeSet;

import org.jmeter.reporting.domain.LoadTest;
import org.jmeter.reporting.domain.aggregate.AggregateHttpCode;
import org.jmeter.reporting.domain.aggregate.AggregateSampler;
import org.jmeter.reporting.service.AggregateService;
import org.jmeter.reporting.service.LoadTestService;

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

	private final AggregateService aggregateService;

	private final LoadTestService loadTestService;

	public SeriesResource(AggregateService aggregateService,
			LoadTestService loadTestService) {
		this.aggregateService = aggregateService;
		this.loadTestService = loadTestService;
	}

	@GET("/series/aggregate_by_timestamp/{name}/{version}/{run}")
	public Optional<AggregateSampler> aggregateByTimestamp(String name,
			String version, int run, Optional<Integer> interval) {
		AggregateSampler aggregateSampler = null;
		Optional<LoadTest> loadTest = loadTestService.findByKey(name, version,
				run);
		if (loadTest.isPresent()) {
			aggregateSampler = new AggregateSampler();
			aggregateSampler.setSampler("Global");
			aggregateSampler.setIterations(loadTest.get().getIterations());
			aggregateSampler.setAverageByte(loadTest.get().getAverageByte());
			aggregateSampler.setAverageTimes(loadTest.get().getAverageTimes());
			aggregateSampler.setSuccess(loadTest.get().getSuccess());
			aggregateSampler.setAggregatesTimestamp(new TreeSet<>(
					aggregateService.aggregateByTimestamp(name, version, run,
							interval.or(DEFAULT_INTERVAL))));
		}
		return Optional.fromNullable(aggregateSampler);
	}

	@GET("/series/aggregate_by_httpcode/{name}/{version}/{run}")
	public SortedSet<AggregateHttpCode> aggregateByHttpCode(String name,
			String version, int run) {
		return new TreeSet<>(aggregateService.aggregateByHttpCode(name,
				version, run));
	}

	@GET("/series/aggregate_by_sampler/{name}/{version}/{run}")
	public SortedSet<AggregateSampler> aggregateBySampler(String name,
			String version, int run, Optional<String> sampler,
			Optional<Boolean> details, Optional<Integer> interval) {
		SortedSet<AggregateSampler> aggregateSamplers = new TreeSet<>(
				aggregateService.aggregateBySampler(name, version, run,
						sampler.or(""), false));
		if (details.or(false)) {
			for (AggregateSampler aggregateSampler : aggregateSamplers) {
				aggregateSampler.setAggregatesTimestamp(new TreeSet<>(
						aggregateService.aggregateByTimestamp(name, version,
								run, aggregateSampler.getLabel(),
								interval.or(DEFAULT_INTERVAL))));
			}
		}
		return aggregateSamplers;
	}

}
