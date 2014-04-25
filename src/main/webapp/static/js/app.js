'use strict';

/* App Module */
var jmeterReportingApp = angular.module('jmeterReportingApp', [
	'ngRoute',
	'jmeterReportingControllers'
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
            .otherwise({
                redirectTo: '/home'
            });
}]);