package org.jmeter.reporting.service;

import java.util.Iterator;

import javax.inject.Named;

import org.jmeter.reporting.domain.LoadTest;
import org.jongo.Distinct;
import org.jongo.Find;
import org.jongo.FindOne;

import restx.factory.Component;
import restx.jongo.JongoCollection;

import com.google.common.base.Optional;

@Component
public class LoadTestService {

	private final JongoCollection loadTests;

	public LoadTestService(@Named("load_tests") JongoCollection loadTests) {
		this.loadTests = loadTests;
	}

	public Iterable<LoadTest> find(int skip, int limit) {
		Find find = loadTests.get().find();
		return find.skip(skip).limit(limit).as(LoadTest.class);
	}

	public Iterable<LoadTest> find(String name, int skip, int limit) {
		return find("{ ltKey.name: # }", skip, limit, name);
	}

	public Iterable<LoadTest> find(String name, String version, int skip,
			int limit) {
		return find("{ ltKey.name: #, ltKey.version: # }", skip, limit, name,
				version);
	}

	public Optional<LoadTest> findByKey(String name, String version, Integer run) {
		FindOne find = loadTests.get().findOne(
				"{ltKey.name: #, ltKey.version: #, ltKey.run: #}", name,
				version, run);
		return Optional.fromNullable(find.as(LoadTest.class));
	}

	public Iterable<String> findName(int skip, int limit) {
		Distinct distinct = loadTests.get().distinct("ltKey.name");
		return distinct.as(String.class);
	}

	public LoadTest findLastByName(String name) {
		return findLast("{ ltKey.name: # }", name);
	}

	public LoadTest findLastRefByName(String name) {
		return findLast("{ ltKey.name: #, ref: true }", name);
	}

	public Optional<LoadTest> findLastByNameAndVersion(String name,
			String version) {
		LoadTest result = null;

		Find find = loadTests.get().find("{ ltKey.name: #, ltKey.version: # }",
				name, version);
		find.limit(1);
		find.sort("{ltKey.run: -1}");
		Iterable<LoadTest> ldTests = find.as(LoadTest.class);

		if (ldTests.iterator().hasNext()) {
			result = ldTests.iterator().next();
		}
		return Optional.fromNullable(result);
	}

	public LoadTest save(LoadTest loadTest) {
		// Save
		loadTests.get().save(loadTest);
		return loadTest;
	}

	public LoadTest updateReference(String name, String version, int run,
			boolean reference) {
		LoadTest loadTest = findByKey(name, version, run).get();
		loadTest.setReference(reference);
		loadTests
				.get()
				.update("{ltKey.name: #, ltKey.version: #, ltKey.run: #}",
						name, version, run).with(loadTest);
		return loadTest;
	}

	private Iterable<LoadTest> find(String query, int skip, int limit,
			Object... parameters) {
		Find find = loadTests.get().find(query, parameters);
		find.sort("{date: -1}");
		return find.skip(skip).limit(limit).as(LoadTest.class);
	}

	private LoadTest findLast(String query, Object... parameters) {
		Find find = loadTests.get().find(query, parameters);
		find.sort("{date: -1}");
		find.limit(1);
		LoadTest result = null;
		Iterator<LoadTest> lst = find.as(LoadTest.class).iterator();
		if (lst.hasNext()) {
			result = lst.next();
		}
		return result;
	}

}
