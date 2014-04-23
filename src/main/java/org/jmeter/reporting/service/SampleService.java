package org.jmeter.reporting.service;

import java.util.List;

import javax.inject.Named;

import org.jmeter.reporting.domain.Sample;
import org.jmeter.reporting.domain.aggregate.AggregateHttpCode;
import org.jmeter.reporting.domain.aggregate.AggregateSampler;
import org.jmeter.reporting.domain.aggregate.AggregateTimestamp;
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

	public List<AggregateTimestamp> aggregateByTimestamp(String name,
			String version, int run, int interval) {
		List<AggregateTimestamp> aggregateSamples = samples
				.get()
				.aggregate(createMatchAggregateQuery(name, version, run))
				.and(new StringBuilder(
						"{'$group' : {'_id' : { '$multiply' : [ { '$subtract' : [ {'$divide' : ['$ts', ")
						.append(interval)
						.append(" ]}, { '$mod' : [{'$divide' : ['$ts', ")
						.append(interval)
						.append(" ]},1] } ] } , ")
						.append(interval)
						.append("] }, 'throughput_success' : { '$sum' : { '$cond' : [ '$s', 1, 0 ] } }, 'throughput_error' : { '$sum' : { '$cond' : [ '$s', 0, 1 ] } }, 'thread_count' :  { '$avg' : '$ng' } }}")
						.toString()).as(AggregateTimestamp.class);

		return aggregateSamples;
	}

	public List<AggregateHttpCode> aggregateByHttpCode(String name,
			String version, int run) {
		List<AggregateHttpCode> aggregateSamples = samples.get()
				.aggregate(createMatchAggregateQuery(name, version, run))
				.and("{'$group' : {'_id' : '$rc', 'count' : { '$sum' : 1 } }}")
				.as(AggregateHttpCode.class);

		return aggregateSamples;
	}

	public List<AggregateSampler> aggregateBySampler(String name,
			String version, int run) {
		List<AggregateSampler> aggregateSamples = samples
				.get()
				.aggregate(createMatchAggregateQuery(name, version, run))
				.and("{'$group' : {'_id' : '$lb' , 'iterations' : { '$sum' : 1 }, 'avg_time' : { '$avg' : '$t' }, 'avg_byt' : { '$avg' : '$by' }, 'success' : { '$sum' : { '$cond' : [ '$s', 1, 0 ] } } }}")
				.as(AggregateSampler.class);

		return aggregateSamples;
	}

	private String createMatchAggregateQuery(String name, String version,
			int run) {
		return new StringBuilder("{$match:{'ltKey': { 'name': '").append(name)
				.append("', 'version': '").append(version).append("', 'run': ")
				.append(run).append(" }}}").toString();
	}

}
