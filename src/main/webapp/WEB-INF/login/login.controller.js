(function(){
'use strict';

    angular.module('carteleraApp')
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
                	$scope.error = 'Username or password is incorrect';
                	$scope.loading = false;
                }
            });
        }
   })
        
})();