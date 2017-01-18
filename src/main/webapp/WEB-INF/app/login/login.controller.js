(function(){
'use strict';

    angular.module('Login')
        .controller('LoginController', function($scope, $state, $stateParams, $location, LoginAuthenticationService){

        $scope.login = login;

        initLoginController();

        function initLoginController() {
            // reset login status
        	LoginAuthenticationService.logout();
        };

        function login() {
            $scope.loading = true;
            LoginAuthenticationService.login($scope.username, $scope.password, function (result) {
                if (result === true) {
                    $location.path('/');
                } else {
                	$scope.error = 'Nombre de usuario o contraseña incorrectos';
                	$scope.loading = false;
                }
            });
        }
   })
        
})();