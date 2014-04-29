package org.jmeter.reporting;

import java.nio.file.Paths;

import javax.inject.Named;

import restx.factory.Module;
import restx.factory.Provides;
import restx.mongo.MongoModule;
import restx.security.BCryptCredentialsStrategy;
import restx.security.BasicPrincipalAuthenticator;
import restx.security.CredentialsStrategy;
import restx.security.FileBasedUserRepository;
import restx.security.SecuritySettings;
import restx.security.SignatureKey;
import restx.security.StdBasicPrincipalAuthenticator;
import restx.security.StdUser;
import restx.security.StdUserService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableSet;

@Module
public class AppModule {
	@Provides
	public SignatureKey signatureKey() {
		return new SignatureKey(
				"8841820556718780011 9aacd131-df1e-41e1-898b-7559de88f059 jmeter-reporting jmeter-reporting"
						.getBytes(Charsets.UTF_8));
	}

	@Provides
	@Named("restx.admin.password")
	public String restxAdminPassword() {
		return "admin";
	}

	@Provides
	@Named("app.name")
	public String appName() {
		return "jmeter-reporting";
	}

	@Provides
	public CredentialsStrategy credentialsStrategy() {
		return new BCryptCredentialsStrategy();
	}

	@Provides
	public BasicPrincipalAuthenticator basicPrincipalAuthenticator(
			SecuritySettings securitySettings,
			CredentialsStrategy credentialsStrategy,
			@Named("restx.admin.passwordHash") String defaultAdminPasswordHash,
			ObjectMapper mapper) {
		return new StdBasicPrincipalAuthenticator(new StdUserService<>(
		// use file based users repository.
		// Developer's note: prefer another storage mechanism for your users if
		// you need real user management
		// and better perf
				new FileBasedUserRepository<>(StdUser.class, // this is the
																// class for the
																// User objects,
																// that you can
																// get in your
																// app code
						// with RestxSession.current().getPrincipal().get()
						// it can be a custom user class, it just need to be
						// json deserializable
						mapper,

						// this is the default restx admin, useful to access the
						// restx admin console.
						// if one user with restx-admin role is defined in the
						// repository, this default user won't be
						// available anymore
						new StdUser("admin", ImmutableSet.<String> of("*")),

						// the path where users are stored
						Paths.get("data/users.json"),

						// the path where credentials are stored. isolating both
						// is a good practice in terms of security
						// it is strongly recommended to follow this approach
						// even if you use your own repository
						Paths.get("data/credentials.json"),

						// tells that we want to reload the files dynamically if
						// they are touched.
						// this has a performance impact, if you know your users
						// / credentials never change without a
						// restart you can disable this to get better perfs
						true), credentialsStrategy, defaultAdminPasswordHash),
				securitySettings);
	}

	@Provides
	@Named(MongoModule.MONGO_DB_NAME)
	public String dbName() {
		return "jmeter-reporting";
	}
}
