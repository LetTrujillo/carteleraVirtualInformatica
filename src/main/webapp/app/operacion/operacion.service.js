(function(){
'use strict';

angular.module('Operacion')
	.factory('OperacionService', function($http, config, $q, $localStorage) {
		return {

			getAllOperacionesByUser: function(username){
				return $http.get(config.basePath+'/permiso/'+username)
					.then(function(result) {
						return result;
					}
				);
			}

		};
	});
})();
