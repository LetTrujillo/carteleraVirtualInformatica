(function(){
'use strict';

angular.module('Operacion')
	.factory('OperacionService', function($http, config, $q, $localStorage) {
		return {
			//TODO: Obtener el usuario y recuperar los permisos
			getAllOperacionesByUser: function(username){
				return $http.get(config.basePath+'//'+username)
					.then(function(result) {
						return result;
					}
				);
			}
		};
	});
})();
