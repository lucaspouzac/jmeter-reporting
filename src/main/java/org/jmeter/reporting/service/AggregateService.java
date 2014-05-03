package org.jmeter.reporting.service;

import java.util.List;

import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.jmeter.reporting.domain.aggregate.AggregateHttpCode;
import org.jmeter.reporting.domain.aggregate.AggregateSampler;
import org.jmeter.reporting.domain.aggregate.AggregateTimestamp;
import org.jongo.Aggregate;

import restx.factory.Component;
import restx.jongo.JongoCollection;

@Component
public class AggregateService {

	private final JongoCollection samples;

	public AggregateService(@Named("samples") JongoCollection samples) {
		this.samples = samples;
	}

	public List<AggregateTimestamp> aggregateByTimestamp(String name,
			String version, int run, int interval) {
		return aggregateByTimestamp(name, version, run, null, interval);
	}

	public List<AggregateTimestamp> aggregateByTimestamp(String name,
			String version, int run, String sampler, int interval) {
		Aggregate agg = samples.get().aggregate(
				createMatchAggregateQuery(name, version, run, sampler));
		StringBuilder sb = new StringBuilder(
				"{'$group' : {'_id' : { '$multiply' : [ { '$subtract' : [ {'$divide' : ['$ts', ")
				.append(interval)
				.append(" ]}, { '$mod' : [{'$divide' : ['$ts', ")
				.append(interval)
				.append(" ]},1] } ] } , ")
				.append(interval)
				.append("] }, 'throughput_success' : { '$sum' : { '$cond' : [ '$s', {'$divide' : [ { '$multiply' : [ 1, 1000 ] }, ")
				.append(interval)
				.append("] }, 0 ] } }, 'throughput_error' : { '$sum' : { '$cond' : [ '$s', 0, {'$divide' : [ { '$multiply' : [ 1, 1000 ] }, ")
				.append(interval)
				.append("] } ] } }")
				.append(", 'time_success' : { '$sum' : { '$cond' : [ '$s', '$t', 0 ] } }, 'time_error' : { '$sum' : { '$cond' : [ '$s', 0, '$t' ] } }");
		if (StringUtils.isEmpty(sampler)) {
			sb.append(", 'threads' :  { '$avg' : '$ng' }");
		}
		sb.append(" }}");
		return agg.and(sb.toString()).as(AggregateTimestamp.class);
	}

	public List<AggregateHttpCode> aggregateByHttpCode(String name,
			String version, int run) {
		List<AggregateHttpCode> aggregateSamples = samples.get()
				.aggregate(createMatchAggregateQuery(name, version, run))
				.and("{'$group' : {'_id' : '$rc', 'count' : { '$sum' : 1 } }}")
				.as(AggregateHttpCode.class);

		return aggregateSamples;
	}

	public AggregateSampler aggregateGlobal(String name, String version, int run) {
		AggregateSampler aggregateSampler = null;
		List<AggregateSampler> aggregateSamplers = aggregateBySampler(name,
				version, run, null, true);
		if (aggregateSamplers.size() == 1) {
			aggregateSampler = aggregateSamplers.get(0);
		}
		return aggregateSampler;
	}

	public List<AggregateSampler> aggregateBySampler(String name,
			String version, int run, boolean global) {
		return aggregateBySampler(name, version, run, null, global);
	}

	public List<AggregateSampler> aggregateBySampler(String name,
			String version, int run, String sampler, boolean global) {
		String id = global ? "Global" : "$lb";
		List<AggregateSampler> aggregateSamples = samples
				.get()
				.aggregate(
						createMatchAggregateQuery(name, version, run, sampler))
				.and("{'$group' : {'_id' : '"
						+ id
						+ "' , 'iterations' : { '$sum' : 1 }, 'avg_times' : { '$avg' : '$t' }, 'avg_byt' : { '$avg' : '$by' }, 'success' : { '$sum' : { '$cond' : [ '$s', 1, 0 ] } } }}")
				.as(AggregateSampler.class);

		return aggregateSamples;
	}

	private String createMatchAggregateQuery(String name, String version,
			int run) {
		return createMatchAggregateQuery(name, version, run, null);
	}

	private String createMatchAggregateQuery(String name, String version,
			int run, String sampler) {
		StringBuilder sb = new StringBuilder("{$match:{'ltKey': { 'name': '")
				.append(name).append("', 'version': '").append(version)
				.append("', 'run': ").append(run).append(" } ");
		if (StringUtils.isNotEmpty(sampler)) {
			sb.append(", 'lb': \"").append(sampler).append("\"");
		}
		sb.append(" }}");
		return sb.toString();
	}

}
