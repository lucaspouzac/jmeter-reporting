JMeter-Reporting [![Build Status](https://buildhive.cloudbees.com/job/lucaspouzac/job/jmeter-reporting/badge/icon)](https://buildhive.cloudbees.com/job/lucaspouzac/job/jmeter-reporting/)
================

In development

Pre-requisite : Default local MongoDB installation (port 27017) or add -Dmongo.uri=[path] for remote connection.

Admin role necessary to run Integration Test : https://github.com/restx/restx/issues/76
==> Unable to run integration test on cloud

Run tests YAML : mvn clean test
Import eclispe : mvn eclipse:clean eclipse:eclipse
Run App : Run As Java Application org.jmeter.reporting.AppServer
Admin console (admin/admin) : http://localhost:8080/api/@/ui/
Games : http://localhost:8080/api/@/ui/api-docs/
