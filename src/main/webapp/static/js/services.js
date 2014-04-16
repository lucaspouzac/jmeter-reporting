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