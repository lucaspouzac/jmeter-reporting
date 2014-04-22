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

jmeterReportingApp.factory("Series", function ($http) {
    var API_URI = '/api/series';

    return {

        throughput : function(name, version, run) {
            return $http.get(API_URI + '/throughput/' + name + '/' + version + '/' + run);
        },

	    thread_count : function(name, version, run) {
	        return $http.get(API_URI + '/thread_count/' + name + '/' + version + '/' + run);
	    }
        
    };

});


jmeterReportingApp.factory("Chart", function () {
    return {
        draw : function(name, data, label) {
    		Morris.Line({
    			  element: name,
    			  data: data,
    			  xkey: '_id',
    			  ykeys: ['value'],
    			  labels: [label],
    			  pointSize: 2,
    			  lineWidth: 2,
    			  smooth: true
    			});
        }        
    };

});