(function(){
'use strict';

    angular.module('Administrador')
        .controller('AdministradorController', function($scope, $localStorage, OperacionService){

        	OperacionService.getAllOperacionesByUser($localStorage.currentUser.username).then( function (result) {
                if (result != null && result != '') {
                	$scope.operaciones = result.data;
                	$scope.usuario = result.data[0].usuario;
                } else {
                	$scope.operaciones = null;
                }
            });


   })
        
})();