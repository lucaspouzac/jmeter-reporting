package org.jmeter.reporting.rest;

import org.jmeter.reporting.domain.LoadTest;
import org.jmeter.reporting.domain.LoadTestKey;
import org.jmeter.reporting.domain.aggregate.AggregateSampler;
import org.jmeter.reporting.service.AggregateService;
import org.jmeter.reporting.service.LoadTestService;

import restx.annotations.GET;
import restx.annotations.POST;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.security.PermitAll;

import com.google.common.base.Optional;

@Component
@RestxResource
@PermitAll
public class LoadTestResource {

	private final LoadTestService loadTestService;

	private final AggregateService aggregateService;

	public LoadTestResource(LoadTestService loadTestService,
			AggregateService aggregateService) {
		this.loadTestService = loadTestService;
		this.aggregateService = aggregateService;
	}

	@GET("/load_tests")
	public Iterable<LoadTest> find(Optional<String> name,
			Optional<String> version, Optional<Integer> skip,
			Optional<Integer> limit) {
		if (name.isPresent()) {
			if (version.isPresent()) {
				return loadTestService.find(name.get(), version.get(),
						skip.or(0), limit.or(10));
			} else {
				return loadTestService.find(name.get(), skip.or(0),
						limit.or(10));
			}
		} else {
			return loadTestService.find(skip.or(0), limit.or(10));
		}
	}

	@GET("/load_tests/name")
	public Iterable<String> findName(Optional<Integer> skip,
			Optional<Integer> limit) {
		return loadTestService.findName(skip.or(0), limit.or(10));
	}

	@GET("/load_tests/find_last_ref/{name}")
	public Optional<LoadTest> findLastRef(String name) {
		return Optional.fromNullable(loadTestService.findLastRefByName(name));
	}

	@GET("/load_tests/find_last/{name}")
	public Optional<LoadTest> findLast(String name) {
		return Optional.fromNullable(loadTestService.findLastByName(name));
	}

	@GET("/load_tests/{name}/{version}/{run}")
	public Optional<LoadTest> findByKey(String name, String version, int run) {
		return loadTestService.findByKey(name, version, run);
	}

	@POST("/reference/{name}/{version}/{run}/{reference}")
	public LoadTest updateReference(String name, String version, int run,
			boolean reference) {
		return loadTestService.updateReference(name, version, run, reference);
	}

	@GET("/start/{name}/{version}")
	public LoadTest start(String name, String version) {
		int run = 1;

		// Get last run number
		Optional<LoadTest> ldTest = loadTestService.findLastByNameAndVersion(
				name, version);
		if (ldTest.isPresent()) {
			run = ldTest.get().getLoadTestKey().getRun() + 1;
		}

		// Create and save
		return loadTestService.save(new LoadTest(new LoadTestKey(name, version,
				run)));
	}

	@GET("/stop/{name}/{version}/{run}")
	public LoadTest stop(String name, String version, Integer run) {
		// Find loadTest
		Optional<LoadTest> loadTest = loadTestService.findByKey(name, version,
				run);
		if (loadTest.isPresent()) {

			// Pre calcul
			AggregateSampler aggregateSampler = aggregateService
					.aggregateGlobal(name, version, run);

			// Update Load Test
			loadTest.get().setAverageByte(aggregateSampler.getAverageByte());
			loadTest.get().setAverageTimes(aggregateSampler.getAverageTimes());
			loadTest.get().setIterations(aggregateSampler.getIterations());
			loadTest.get().setSuccess(aggregateSampler.getSuccess());
			loadTest.get().setFinish(true);

			// Save load test
			return loadTestService.save(loadTest.get());
		}
		// TODO
		return null;
	}
}
