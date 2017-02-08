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
					//por ahora va a operación. La idea es rutear cada perfil a su "menú", como docente.
                 			if("alumno" == $rootScope.role)
                     			$state.go('operacion');
                 	}else
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
