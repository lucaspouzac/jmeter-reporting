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
jmeterReportingControllers.controller("LoadTestCtrl" ,function ($scope, $routeParams, LoadTest, Series, Chart) {
	$scope.path = {
		name: $routeParams.name,
		version: $routeParams.version,
		run: $routeParams.run
	}

    $scope.intervals = [
                        {name:'500 ms', value: 500},
                        {name:'1 s', value: 1000},
                        {name:'5 s', value: 5000},
                        {name:'10 s', value: 10000},
                        {name:'15 s', value: 15000},
                        {name:'30 s', value: 30000},
                        {name:'1 m', value: 60000},
                        {name:'5 m', value: 300000},
                        {name:'10 m', value: 600000},
                        {name:'15 m', value: 900000},
                        {name:'30 m', value: 1800000},
                        {name:'1 h', value: 3600000}
                     ];
    $scope.interval = $scope.intervals[1];

	LoadTest.findOne($routeParams.name, $routeParams.version, $routeParams.run).success(function(resp){
        $scope.loadTest = resp;
    });
    
	$scope.updateReference = function(ref) {
		LoadTest.updateReference($routeParams.name, $routeParams.version, $routeParams.run, ref).success(function(resp){
    		$scope.loadTest = resp;
        });
	}
	
    $scope.updateInterval = function(init) {		
    	Series.aggregate_by_sampler($routeParams.name, $routeParams.version, $routeParams.run, undefined, false, $scope.interval.value).success(function(series){
    		$scope.samplers = series;
        });
    	
    	Series.aggregate_by_timestamp($routeParams.name, $routeParams.version, $routeParams.run, $scope.interval.value).success(function(series){
    		if (init) {
        		$scope.global = series;
        		$scope.chartThroughput = Chart.draw('throughput', series.aggs_timestamp, 'timestamp', ['throughput_success', 'throughput_error'], ['Throughput Success', 'Throughput Error'], ['green','red']);
        		$scope.chartThread = Chart.draw('thread', series.aggs_timestamp, 'timestamp', ['threads'], ['Threads'], []);
    		} else {
    			$scope.chartThroughput.setData(series.aggs_timestamp);
    			$scope.chartThread.setData(series.aggs_timestamp);
    		}
    	});	
    }
    
    $scope.updateInterval(true);
	
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

//History Controller
jmeterReportingControllers.controller("HistoryCtrl" ,function ($scope, $routeParams, LoadTest) {
	$scope.path = {
		name: $routeParams.name,
		version: $routeParams.version
	}
	
	LoadTest.find($routeParams.name, $routeParams.version, 0, 0).success(function(resp){
        $scope.loadTests = resp;
    });    
});


//Details Controller
jmeterReportingControllers.controller("DetailsCtrl" ,function ($scope, $routeParams, Series, Chart) {
	$scope.path = {
			name: $routeParams.name,
			version: $routeParams.version,
			run: $routeParams.run,
			sampler: $routeParams.sampler
	}
    $scope.intervals = [
                        {name:'500 ms', value: 500},
                        {name:'1 s', value: 1000},
                        {name:'5 s', value: 5000},
                        {name:'10 s', value: 10000},
                        {name:'15 s', value: 15000},
                        {name:'30 s', value: 30000},
                        {name:'1 m', value: 60000},
                        {name:'5 m', value: 300000},
                        {name:'10 m', value: 600000},
                        {name:'15 m', value: 900000},
                        {name:'30 m', value: 1800000},
                        {name:'1 h', value: 3600000}
                     ];
    $scope.interval = $scope.intervals[1];
    
    $scope.updateInterval = function(init) {
    	Series.aggregate_by_sampler($routeParams.name, $routeParams.version, $routeParams.run, $routeParams.sampler, false, $scope.interval.value).success(function(series){
    		$scope.samplers = series;
        });
    	
    	Series.aggregate_by_sampler($routeParams.name, $routeParams.version, $routeParams.run, $routeParams.sampler, true, $scope.interval.value).success(function(series){
    		series.forEach(function(entry) {
    	    	Chart.draw(entry.label, entry.aggs_timestamp, 'timestamp', ['avg_times_success', 'avg_times_error'], ['Average Times Success', 'Average Times Error'], ['green','red']);
    	    });
        });	
    }
    
    $scope.updateInterval(true);

});

//Config Load Test Controller
jmeterReportingControllers.controller("ConfigLoadTestCtrl" ,function ($scope) {
	
});

//Upload JTL Controller
jmeterReportingControllers.controller("UploadJtlCtrl" ,function ($scope, $fileUploader) {

    $scope.api = {name:'api-test', version: '1.0'};
                     	
    // create a uploader with options
    var uploader = $scope.uploader = $fileUploader.create({
        scope: $scope, // to automatically update the html. Default: $rootScope
        filters: [
            function (item) { // first user filter
                return item.name.indexOf('.jtl', item.name.length - 4) !== -1;
            }
        ]
    });
    
    uploader.bind('beforeupload', function (event, item) {
    	item.url = 'api/upload/' + $scope.api.name + '/' + $scope.api.version; 
    });
});