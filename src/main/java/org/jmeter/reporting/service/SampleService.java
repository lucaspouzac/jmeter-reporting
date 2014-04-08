package org.jmeter.reporting.service;

import java.util.List;

import javax.inject.Named;

import org.jmeter.reporting.domain.Sample;
import org.jmeter.reporting.domain.AggregateSample;
import org.jongo.Find;

import restx.factory.Component;
import restx.jongo.JongoCollection;

import com.google.common.base.Optional;

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

	public List<AggregateSample> throughput(int interval) {
		List<AggregateSample> aggregateSamples = samples.get()
				.aggregate(createAggregateQuery("{ '$sum' : 1 }", interval))
				.as(AggregateSample.class);

		return aggregateSamples;
	}

	public List<AggregateSample> threadCount(int interval) {
		List<AggregateSample> aggregateSamples = samples.get()
				.aggregate(createAggregateQuery("{ '$avg' : '$ng' }", interval))
				.as(AggregateSample.class);

		return aggregateSamples;
	}

	private String createAggregateQuery(String valueQuery, int interval) {
		return "{'$group' : {'_id' : { '$multiply' : [ { '$subtract' : [ {'$divide' : ['$ts', "
				+ interval
				+ " ]}, { '$mod' : [{'$divide' : ['$ts', "
				+ interval
				+ " ]},1] } ] } , "
				+ interval
				+ "] }, 'value' : "
				+ valueQuery
				+ " }}";
	}
}
