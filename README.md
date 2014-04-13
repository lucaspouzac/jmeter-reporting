JMeter-Reporting [![Build Status](https://lucaspouzac.ci.cloudbees.com/buildStatus/icon?job=jmeter-reporting)](https://lucaspouzac.ci.cloudbees.com/job/jmeter-reporting/)
================

[![Built on](http://www.cloudbees.com/sites/default/files/Button-Built-on-CB-1.png)](https://console.cloudbees.com/a/lucaspouzac/home/)

In development

## Demo

Admin console (admin/admin): 

`http://jmeter-reporting.lucaspouzac.eu.cloudbees.net/api/@/ui/`

## Installation

### Pre-requisite

Default local MongoDB installation (port 27017) or add `-Dmongo.uri=[mongodb://localhost:27017/jmeter-reporting]` for remote connection.

### Run Application

Run As Java Application `org.jmeter.reporting.AppServer` (use embed jetty) or deploy war on application server.

## Development

### Build

JMETER-REPORTING requires Java 7.

You can build it using either Maven 3.

With Maven:

`mvn clean install test` (add -Dhttp.proxyHost=host -Dhttp.proxyPort=port if you are behind a proxy to download embed MongoDB)

### Contributing

Contributions are welcome, fork the repo, push your changes to a branch and send a Pull Request.
