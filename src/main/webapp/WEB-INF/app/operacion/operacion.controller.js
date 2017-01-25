(function(){
'use strict';

    angular.module('Operacion')
        .controller('OperacionController', function($scope, $state, $stateParams, $location, OperacionService, $localStorage, $http){

        //recuperar los permisos del usuario logueado y mostrarlos
        	OperacionService.getAllOperacionesByUser($localStorage.currentUser.username).then( function (result) {
                if (result != null && result != '') {
                	$scope.operaciones = result;
                } else {
                	$scope.operaciones = null;
                }
            });

   })
        
})();