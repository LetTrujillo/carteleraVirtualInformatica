(function(){
'use strict';

    angular.module('Login')
        .controller('LoginController', function($scope, $state, $stateParams, $location, AuthenticationService, $localStorage, $http, $rootScope){

        $scope.login = login;

        initLoginController();

        function initLoginController() {
        	AuthenticationService.logout();
            $rootScope.loggedIn = false;
        };

        
        
        function login() {
        	AuthenticationService.login($scope.username, $scope.password).then( function (result) {
           	 
            	if (result != null && result != '') {
                 	$localStorage.currentUser = { username: $scope.username, token: result.data.token };
                 	$http.defaults.headers.common.Authorization = result.data.token;
                 	$rootScope.role = result.data.role;
                 	$rootScope.loggedIn = true;
                 	if($rootScope.role !== null && $rootScope.role !== ''){
                 		if("docente" == $rootScope.role)
                 			$state.go('docente');
                 		else
                 			if("alumno" == $rootScope.role)
                     			$state.go('alumno');
                 			else 
                 				if("administrador" == $rootScope.role)
                 					$state.go('administrador');
                 				else
                 					if("publicador" == $rootScope.role)
                     					$state.go('publicador');
                 	}else
                 		$state.go('login');
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
