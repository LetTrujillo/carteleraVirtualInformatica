(function(){
'use strict';

    angular.module('carteleraApp')
        .controller('LoginController', function($scope, $state, $stateParams, $location, AuthenticationService){

        $scope.login = login;

        initLoginController();

        function initLoginController() {
            // reset login status
        	AuthenticationService.logout();
        };

        function login() {
            $scope.loading = true;
            AuthenticationService.login($scope.username, $scope.password, function (result) {
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