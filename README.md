JMeter Reporting  [![Build Status](https://lucaspouzac.ci.cloudbees.com/buildStatus/icon?job=jmeter-reporting)](https://lucaspouzac.ci.cloudbees.com/job/jmeter-reporting/)
================

## Demo

[JMETER REPORTING](http://jmeter-reporting.lucaspouzac.eu.cloudbees.net/)

[ADMIN RESTX CONSOLE](http://jmeter-reporting.lucaspouzac.eu.cloudbees.net/api/@/ui/): `admin/admin` 

## Features

Reporting tool for JMeter load tests.

 - **_TODO_**

## Installation

### Pre-requisite

Default local MongoDB installation (port 27017) or add `-Dmongo.uri=[mongodb://localhost:27017/jmeter-reporting]` for remote connection.

### Run Application

JMETER REPORTING is a RestX application. 2 ways to run application : 
 - Run As Java Application `org.jmeter.reporting.AppServer` (use embed jetty)
 - Deploy war on application server.

## RoadMap

### Features

 - \[API/UI\] : Configuration and check results of load tests
 - \[API\] : authentification / roles
 - \[UI\] : new charts

### Integration & Tests

 - Release and versionning
 - JS tests with [Karma](http://karma-runner.github.io) 

### Performance 

 - Improvement aggregation queries (cache, indexes, upgrade MongoDB 2.6 ?)
 - Improvement charts generation (Replacing Morris JS ?)

## Development

### Frameworks used

 - [RestX](http://restx.io/)
 - [MongoDB](https://www.mongodb.org/)
 - [AngularJS](https://angularjs.org/)
 - [Twitter Bootstrap](http://getbootstrap.com/)
 - [MorrisJS](http://morrisjs.github.io/morris.js/)
 - [Angular File Upload](https://github.com/nervgh/angular-file-upload)

### Build

JMETER-REPORTING requires Java 7.

You can build it using either Maven 3.

With Maven: `mvn clean install test` (add -Dhttp.proxyHost=host -Dhttp.proxyPort=port if you are behind a proxy to download embed MongoDB)

### Contributing

Contributions are welcome, fork the repo, push your changes to a branch and send a Pull Request.

[![Built on](http://www.cloudbees.com/sites/default/files/Button-Built-on-CB-1.png)](https://console.cloudbees.com/a/lucaspouzac/home/)
