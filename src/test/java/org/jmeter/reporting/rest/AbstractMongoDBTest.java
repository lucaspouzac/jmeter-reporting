package org.jmeter.reporting.rest;

import org.junit.BeforeClass;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

public abstract class AbstractMongoDBTest {

	@BeforeClass
	public static void setUp() throws Exception {
		MongodStarter runtime = MongodStarter.getDefaultInstance();
		MongodExecutable _mongodExe = runtime.prepare(new MongodConfigBuilder()
				.version(Version.Main.PRODUCTION)
				.net(new Net(27017, Network.localhostIsIPv6())).build());
		_mongodExe.start();
	}
	
}
