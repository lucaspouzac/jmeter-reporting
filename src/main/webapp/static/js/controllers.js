"use strict";

var jmeterReportingControllers = angular.module('jmeterReportingControllers', []);

// Home Controller
jmeterReportingControllers.controller("HomeCtrl" ,function ($scope, LoadTest) {
	
	LoadTest.fetchName().success(function(resp){
        $scope.loadTestsName = resp;
        
        $scope.loadTestsLast = new Array();
        $scope.loadTestsName.forEach(function(entry) {
            LoadTest.findLast(entry).success(function(lastLoadTest){
        	    $scope.loadTestsLast[entry] = lastLoadTest;
            });        
        });
        
        $scope.loadTestsLastRef = new Array();
        $scope.loadTestsName.forEach(function(entry) {
            LoadTest.findLastRef(entry).success(function(lastRefLoadTest){
        	    $scope.loadTestsLastRef[entry] = lastRefLoadTest;
            });        
        });
        
    });    
});

//LoadTest Controller
jmeterReportingControllers.controller("LoadTestCtrl" ,function ($scope, $routeParams, Series, Chart) {
	$scope.path = {
		name: $routeParams.name,
		version: $routeParams.version,
		run: $routeParams.run
	}
	
	Series.aggregate_by_sampler($routeParams.name, $routeParams.version, $routeParams.run, false).success(function(series){
		$scope.samplers = series;
    });
	
	Series.aggregate_by_sampler($routeParams.name, $routeParams.version, $routeParams.run, true).success(function(series){
		series.forEach(function(entry) {
	    	Chart.draw(entry.label, entry.aggs_timestamp, 'timestamp', ['avg_times_success', 'avg_times_error'], ['Average Times Success', 'Average Times Error'], ['green','red']);
	    });
    });
	
	Series.aggregate_by_timestamp($routeParams.name, $routeParams.version, $routeParams.run).success(function(series){
		$scope.global = series;
		Chart.draw('throughput', series.aggs_timestamp, 'timestamp', ['throughput_success', 'throughput_error'], ['Throughput Success', 'Throughput Error'], ['green','red']);
		Chart.draw('thread', series.aggs_timestamp, 'timestamp', ['threads'], ['Threads'], []);
	});
	
	Series.aggregate_by_httpcode($routeParams.name, $routeParams.version, $routeParams.run).success(function(series){
		for (var i=0; i<series.length; i++) {
			if (series[i].label.length == 3) {
				series[i].label = "HTTP " + series[i].label;
			} else {
				series[i].label = "Timeout";
			}
		}
		Morris.Donut({
			  element: 'httpcode',
			  data: series
		});
    });
	
});