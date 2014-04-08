package org.jmeter.reporting.service;

import java.util.List;

import javax.inject.Named;

import org.jmeter.reporting.domain.AggregateSample;
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

	public List<AggregateSample> throughput(String name, String version,
			int run, int interval) {
		List<AggregateSample> aggregateSamples = samples.get()
				.aggregate(createMatchAggregateQuery(name, version, run))
				.and(createGroupAggregateQuery("{ '$sum' : 1 }", interval))
				.as(AggregateSample.class);

		return aggregateSamples;
	}

	public List<AggregateSample> threadCount(String name, String version,
			int run, int interval) {
		List<AggregateSample> aggregateSamples = samples.get()
				.aggregate(createMatchAggregateQuery(name, version, run))
				.and(createGroupAggregateQuery("{ '$avg' : '$ng' }", interval))
				.as(AggregateSample.class);

		return aggregateSamples;
	}

	private String createMatchAggregateQuery(String name, String version,
			int run) {
		return new StringBuilder("{$match:{'ltKey': { 'name': '").append(name)
				.append("', 'version': '").append(version).append("', 'run': ")
				.append(run).append(" }}}").toString();
	}

	private String createGroupAggregateQuery(String valueQuery, int interval) {
		return new StringBuilder(
				"{'$group' : {'_id' : { '$multiply' : [ { '$subtract' : [ {'$divide' : ['$ts', ")
				.append(interval)
				.append(" ]}, { '$mod' : [{'$divide' : ['$ts', ")
				.append(interval).append(" ]},1] } ] } , ").append(interval)
				.append("] }, 'value' : ").append(valueQuery).append(" }}")
				.toString();
	}
}
