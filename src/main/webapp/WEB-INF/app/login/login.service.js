(function(){
'use strict';

angular.module('Login')
	.factory('LoginAuthenticationService', function($http, config, $q, $localStorage) {
		return {
			
			/**
			 * 
			 * 
			 */
			login: function(userName, password){
				var usuario = {
						nombreUsuario: userName
						};
				return $http.post(config.ctxPath+'/loginAuthentication/login', usuario)
					.then(function(result) {
						return result.data;
					}
				);
			},
			logout: function(){
				delete $localStorage.currentUser;
	            $http.defaults.headers.common.Authorization = '';
			}
		};
	});
})();





//(function () {
//    'use strict';
//
//    angular
//        .module('carteleraApp')
//        .factory('LoginAuthenticationService', Service);
//
//    function Service($http, $localStorage) {
//        var service = {};
//
//        service.login = login;
//        service.logout = logout;
//
//        return service;
//
//        function login(username, password, callback) {
//            $http.post('/api/authenticate', { username: username, password: password })
//                .success(function (response) {
//                    // login successful if there's a token in the response
//                    if (response.token) {
//                        // store username and token in local storage to keep user logged in between page refreshes
//                        $localStorage.currentUser = { username: username, token: response.token };
//
//                        // add jwt token to auth header for all requests made by the $http service
//                        $http.defaults.headers.common.Authorization = 'Bearer ' + response.token;
//
//                        // execute callback with true to indicate successful login
//                        callback(true);
//                    } else {
//                        // execute callback with false to indicate failed login
//                        callback(false);
//                    }
//                });
//        }
//
//        function logout() {
//            // remove user from local storage and clear http auth header
//            delete $localStorage.currentUser;
//            $http.defaults.headers.common.Authorization = '';
//        }
//    }
//})();