"use strict";

jmeterReportingApp.factory("LoadTest", function ($http) {
    var API_URI = '/api/load_tests';

    return {

        fetchName : function() {
            return $http.get(API_URI + '/name');
        },

        find : function(name, version, skip, limit) {
        	var url = API_URI + '?name=' + name;
        	if (version != undefined) {
        		url = url + '&version=' + version;
        	}
        	if (skip != undefined) {
        		url = url + '&skip=' + skip;
        	}
        	if (limit != undefined) {
        		url = url + '&limit=' + limit;
        	}
            return $http.get(url);
        },

        findOne : function(name, version, run) {
        	var url = API_URI + '/' + name + '/' + version + '/' + run;
            return $http.get(url);
        },

	    findLast : function(name) {
	        return $http.get(API_URI + '/find_last/' + name);
	    },

	    findLastRef : function(name) {
	        return $http.get(API_URI + '/find_last_ref/' + name);
	    },

	    updateReference : function(name, version, run, reference) {
	        return $http.post('/api/reference/' + name + '/' + version + '/' + run + '/' + reference);
	    }
    
    };

});

jmeterReportingApp.factory("Series", [ '$http', function (async) {
    var API_URI = '/api/series';

    return {

        aggregate_by_timestamp : function(name, version, run, interval) {
        	var url = API_URI + '/aggregate_by_timestamp/' + name + '/' + version + '/' + run;
        	if (interval != undefined) {
        		 url = url + '?interval=' + interval
        	}
            return async({
            	method: 'GET',
            	url: url
            });
        },

        aggregate_by_httpcode : function(name, version, run) {
            return async({
            	method: 'GET',
            	url: API_URI + '/aggregate_by_httpcode/' + name + '/' + version + '/' + run
            });
	    },

        aggregate_by_sampler : function(name, version, run, sampler, details, interval) {
        	var url = API_URI + '/aggregate_by_sampler/' + name + '/' + version + '/' + run + '?details=' + details
        	if (interval != undefined) {
       			url = url + '&interval=' + interval
        	}
        	if (sampler != undefined) {
       			url = url + '&sampler=' + sampler
        	}
            return async({
            	method: 'GET',
            	url: url
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
        	return Morris.Line({
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