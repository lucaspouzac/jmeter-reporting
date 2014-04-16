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
            .otherwise({
                redirectTo: '/home'
            });
}]);