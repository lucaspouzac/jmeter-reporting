'use strict';

/* App Module */
var jmeterReportingApp = angular.module('jmeterReportingApp', [
	'ngRoute',
	'jmeterReportingControllers', 
	'angularFileUpload'
]);

jmeterReportingApp.config(['$routeProvider', function($routeProvider) {
        $routeProvider
        	// home
	        .when('/home', {
	            templateUrl: 'partials/home.html',
	            controller : 'HomeCtrl'
	        })
	        .when('/load_test/:name/:version/:run', {
	            templateUrl: 'partials/loadtest.html',
	            controller : 'LoadTestCtrl'
	        })
	        .when('/history/:name', {
	            templateUrl: 'partials/history.html',
	            controller : 'HistoryCtrl'
	        })
	        .when('/history/:name/:version', {
	            templateUrl: 'partials/history.html',
	            controller : 'HistoryCtrl'
	        })
	        .when('/config_load_test', {
	            templateUrl: 'partials/config_load_test.html',
	            controller : 'ConfigLoadTestCtrl'
	        })
	        .when('/upload_jtl', {
	            templateUrl: 'partials/upload_jtl.html',
	            controller : 'UploadJtlCtrl'
	        })
            .otherwise({
                redirectTo: '/home'
            });
}]);