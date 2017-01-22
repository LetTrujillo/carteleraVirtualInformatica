(function(){
'use strict';

angular.module('Authentication')
	.factory('AuthenticationService', function($http, config, $q, $localStorage) {
		return {

			login: function(userName, password){
				var usuario = {
						username: userName,
						password: password
						};
				return $http.post(config.ctxPath+'/authentication/login', usuario)
					.then(function(result) {
						return result;
					}
				);
			},
			logout: function(){
				delete $localStorage.currentUser;
	            $http.defaults.headers.common.Authorization = '';
			},
			validateToken: function(userName){
				return $http.post(config.ctxPath+'/authentication/validate')
					.then(function(result) {
						return result;
					}
				);
			}
		};
	});
})();
