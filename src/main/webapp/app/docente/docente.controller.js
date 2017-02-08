(function(){
'use strict';

    angular.module('Docente')
        .controller('DocenteController', function($scope, $state, $stateParams, $location, $localStorage, $http, OperacionService){

        	OperacionService.getAllOperacionesByUser($localStorage.currentUser.username).then( function (result) {
                if (result != null && result != '') {
                	$scope.operaciones = result.data;
                	$scope.usuario = result.data[0].usuario 
//                	$state.go('docente');
                } else {
                	$scope.operaciones = null;
                }
            });


   })
        
})();