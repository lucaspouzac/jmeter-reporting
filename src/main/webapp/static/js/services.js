"use strict";

jmeterReportingApp.factory("LoadTest", function ($http) {
    var API_URI = '/api/load_tests';

    return {

        fetchName : function() {
            return $http.get(API_URI + '/name');
        },

	    findLast : function(name) {
	        return $http.get(API_URI + '/find_last/' + name);
	    },

	    findLastRef : function(name) {
	        return $http.get(API_URI + '/find_last_ref/' + name);
	    }
    
    };

});

jmeterReportingApp.factory("Series", [ '$http', function (async) {
    var API_URI = '/api/series';

    return {

        aggregate_by_timestamp : function(name, version, run) {
            return async({
            	method: 'GET',
            	url: API_URI + '/aggregate_by_timestamp/' + name + '/' + version + '/' + run + '?details=true'
            });
        },

        aggregate_by_httpcode : function(name, version, run) {
            return async({
            	method: 'GET',
            	url: API_URI + '/aggregate_by_httpcode/' + name + '/' + version + '/' + run
            });
	    },

        aggregate_by_sampler : function(name, version, run, details) {
            return async({
            	method: 'GET',
            	url: API_URI + '/aggregate_by_sampler/' + name + '/' + version + '/' + run + '?details=' + details
            });
	    }
        
    };

}]);


jmeterReportingApp.factory("Chart", function () {
    return {
        draw : function(name, data, xKey, yKeys, label, lineColors) {
        	if (lineColors.length == 0) {
        		lineColors = Morris.Line.prototype.defaults.lineColors;
        	}
        	Morris.Line({
    			  element: name,
    			  data: data,
    			  xkey: xKey,
    			  ykeys: yKeys,
    			  labels: label,
    			  pointSize: 0,
    			  lineWidth: 2,
    			  smooth: true,
    			  lineColors : lineColors//#0b62a4
    			});
        }        
    };

});