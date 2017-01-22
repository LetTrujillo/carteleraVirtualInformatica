(function(){
'use strict';

    angular.module('Operacion')
        .controller('OperacionController', function($scope, $state, $stateParams, $location, OperacionService, $localStorage, $http){

        //recuperar los permisos
        	OperacionService.getAllOperacionesByUser($localStorage.currentUser.username).then( function (result) {
                if (result != null && result != '') {
                	$scope.operaciones = result;
                	$state.go('operacion');
                } else {
                	$scope.operaciones = null;
                }
            });

   })
        
})();