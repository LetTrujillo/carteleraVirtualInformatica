(function(){
'use strict';

angular.module('Login', []);
angular.module('Alumno', []);
angular.module('Docente', []);
angular.module('Authentication', []);
angular.module('Operacion', []);



//Mientras el cliente posea un token válido, puede ser considerado "autenticado". 
//Podemos persistir este estado en varias visitas de página almacenando el JWT usando localStorage.

angular.module('carteleraApp', [
	// Extras
	'ui.router',
	'ngMessages',
	'ngStorage',

     // Modulos
     'Login',
     'Alumno',
     'Docente',
     'Authentication',
     'Operacion'
])

.constant('config', {
    appName: 'Cartelera Virtual',
    basePath: '/CarteleraVirtualInformatica',
    ctxPath:  '/CarteleraVirtualInformatica/api',
    pageSize: 20
})
.config(function ($stateProvider, $urlRouterProvider, $httpProvider) {
	
    // ruta por defecto
    $urlRouterProvider.otherwise("/login");

    // rutas
    $stateProvider
        .state('login', {
            url: '/login',
            templateUrl: 'login/login.html',
            controller: 'LoginController',
        })
        .state('alumno', {
                url: '/alumno',
                templateUrl: 'alumno/menu.html',
                controller: 'AlumnoController',
            })
        .state('docente', {
                url: '/docente',
                templateUrl: 'docente/menu.html',
                controller: 'DocenteController',
            })
         .state('operacion', {
                url: '/operacion',
                templateUrl: 'operacion/menuOperacion.html',
                controller: 'OperacionController',
            })

})

.run(function($rootScope, $http, $location, $localStorage, AuthenticationService) {
	
	//obtener el rol al iniciar la aplicación
	//validar el rol al cambiar de estado
	
	
    // mantener usuario 
    if ($localStorage.currentUser) {
        $http.defaults.headers.common.Authorization = $localStorage.token;

    }
    
    //validar autenticación al cambiar de estado
    $rootScope.$on('$locationChangeStart', function (event, next, current) {
        var publicPages = ['/login'];
        var restrictedPage = publicPages.indexOf($location.path()) === -1;
        //Si no está logueado e intenta acceder a una página restringida
        if (restrictedPage && !$localStorage.currentUser) {
            $location.path('/login');
        }
        //Está logueado, validar el token por si intenta acceder a una página restringida
        else if($localStorage.currentUser){
        	if(!AuthenticationService.validateToken($localStorage.currentUser.username)){
        		$location.path('/login');
        	}
        }
        
    });
})


})();
