package org.jmeter.reporting.rest;

import java.util.Date;
import java.util.List;

import org.jmeter.reporting.domain.LoadTest;
import org.jmeter.reporting.domain.LoadTestKey;
import org.jmeter.reporting.domain.Sample;
import org.jongo.Find;

import com.google.common.base.Optional;

import restx.annotations.GET;
import restx.annotations.POST;
import restx.annotations.RestxResource;
import restx.factory.Component;
import restx.security.PermitAll;

@Component
@RestxResource
@PermitAll
public class LoadTestResource {

    @GET("/load_test")
    public Iterable<LoadTest> findLoadTests(Optional<Integer> skip, Optional<Integer> limit) {
        return null;
    }

    @GET("/load_test/{name}/{version}/{date}")
    public Optional<LoadTest> findLoadTestByKey(String name, String version, Date date) {
        return null;
    }

    @POST("/load_test/start")
    public LoadTest start(LoadTestKey loadTestKey) {
        return null;
    }


    @POST("/load_test/stop")
    public LoadTest stop(LoadTestKey loadTestKey) {
        return null;
    }

}
