(function(){
'use strict';

    angular.module('Login')
        .controller('LoginController', function($scope, $state, $stateParams, $location, AuthenticationService, $localStorage, $http, $rootScope){

        $scope.login = login;

        initLoginController();

        function initLoginController() {
            // reset login status
        	AuthenticationService.logout();
            $rootScope.loggedIn = false;
        };

        
        
        function login() {
        	AuthenticationService.login($scope.username, $scope.password).then( function (result) {
           	 
            	if (result != null && result != '') {
                 	$localStorage.currentUser = { username: $scope.username, token: result.data };
                 	$http.defaults.headers.common.Authorization = result.data;
                 	$rootScope.loggedIn = true;
                 	$state.go('operacion');
                 }
            	else {
                	$scope.error = 'Nombre de usuario o contraseña incorrectos';
                	$scope.loading = false;
                }
                  
                }, function(error){
                	$scope.error = 'Nombre de usuario o contraseña incorrectos';
                	$scope.loading = false;
                });
        };
});     
})();