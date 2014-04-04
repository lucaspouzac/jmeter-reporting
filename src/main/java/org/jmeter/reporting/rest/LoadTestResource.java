package org.jmeter.reporting.rest;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.jmeter.reporting.domain.LoadTest;
import org.jmeter.reporting.domain.LoadTestKey;
import org.jmeter.reporting.domain.Sample;
import org.jmeter.reporting.service.LoadTestService;
import org.jongo.Find;
import org.jongo.FindOne;

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
public class LoadTestResource {

    private final LoadTestService loadTestService;

    @Inject
    public LoadTestResource(LoadTestService loadTestService) {
        this.loadTestService = loadTestService;
    }

    @GET("/load_tests")
    public Iterable<LoadTest> findLoadTests(Optional<Integer> skip, Optional<Integer> limit) {
        return loadTestService.find(skip, limit);
    }

    @GET("/load_tests/{name}/{version}/{run}")
    public Optional<LoadTest> findLoadTestByKey(String name, String version, Integer run) {
        return loadTestService.findByKey(name, version, run);
    }

    @POST("/start/{name}/{version}")
    public LoadTest start(String name, String version) {
        int run = 1;

        // Get last run number
        Optional<LoadTest> ldTest = loadTestService.findLastByNameAndVersion(name, version);
        if (ldTest.isPresent()) {
            run = ldTest.get().getLoadTestKey().getRun() + 1;
        }

        // Create and save
        return loadTestService.createNew(name, version, run);
    }


    @POST("/stop/{name}/{version}/{run}")
    public LoadTest stop(String name, String version, Integer run) {
        return null;
    }

}
