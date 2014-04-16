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