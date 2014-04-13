'use strict';

/* App Module */
var jmeterReporting = angular.module('jmeterReportingApp', [
	'ngRoute',
	'jmeterReportingControllers'
]);

jmeterReporting.config(['$routeProvider', function($routeProvider) {
        $routeProvider
        	// home
	        .when('/home', {
	            templateUrl: 'partials/home.html',
	            controller : 'HomeCtrl'
	        })
            .otherwise({
                redirectTo: '/home'
            });
}]);