(function(){
'use strict';

    angular.module('Login')
        .controller('LoginController', function($scope, $state, $stateParams, $location, AuthenticationService, $localStorage, $http){

        $scope.login = login;

        initLoginController();

        function initLoginController() {
            // reset login status
        	AuthenticationService.logout();
        };

        function login() {
            $scope.loading = true;
            AuthenticationService.login($scope.username, $scope.password).then( function (result) {
                if (result != null && result != '') {
                	$localStorage.currentUser = { username: $scope.username, token: result.data };
                	$http.defaults.headers.common.Authorization = result.data;
                	$state.go('alumno');
                } else {
                	$scope.error = 'Nombre de usuario o contraseña incorrectos';
                	$scope.loading = false;
                }
            });
        }
   })
        
})();