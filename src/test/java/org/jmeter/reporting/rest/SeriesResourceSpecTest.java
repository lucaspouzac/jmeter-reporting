package org.jmeter.reporting.rest;

import org.junit.runner.RunWith;

import restx.jongo.specs.tests.MongoRestxSpecTestsRunner;
import restx.jongo.specs.tests.MongoVersion;
import restx.tests.FindSpecsIn;

@RunWith(MongoRestxSpecTestsRunner.class)
@FindSpecsIn("specs/series")
@MongoVersion
public class SeriesResourceSpecTest {

}
