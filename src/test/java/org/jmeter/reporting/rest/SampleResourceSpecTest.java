package org.jmeter.reporting.rest;

import org.junit.runner.RunWith;

import restx.tests.FindSpecsIn;
import restx.tests.RestxSpecTestsRunner;

@RunWith(RestxSpecTestsRunner.class)
@FindSpecsIn("specs/sample")
public class SampleResourceSpecTest extends AbstractMongoDBTest {

    /**
     * Useless, thanks to both @RunWith(RestxSpecTestsRunner.class) & @FindSpecsIn()
     *
     * @Rule
     * public RestxSpecRule rule = new RestxSpecRule();
     *
     * @Test
     * public void test_spec() throws Exception {
     *     rule.runTest(specTestPath);
     * }
     */
}
