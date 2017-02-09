(function(){
'use strict';

angular.module('Login', []);
angular.module('Alumno', []);
angular.module('Docente', []);
angular.module('Authentication', []);
angular.module('Operacion', []);
angular.module('Publicador', []);
angular.module('Administrador', []);



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
     'Operacion',
     'Administrador',
     'Publicador'
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
                templateUrl: 'alumno/home.html',
                controller: 'AlumnoController',
            })
        .state('docente', {
                url: '/docente',
                templateUrl: 'docente/home.html',
                controller: 'DocenteController',
                
            })
         .state('operacion', {
                url: '/operacion',
                templateUrl: 'operacion/menuOperacion.html',
                controller: 'OperacionController',
            })
         .state('administrador', {
                url: '/administrador',
                templateUrl: 'administrador/home.html',
                controller: 'AdministradorController',
            })
           .state('publicador', {
                url: '/publicador',
                templateUrl: 'publicador/home.html',
                controller: 'PublicadorController',
            })

})

.run(function($rootScope, $http, $location, $localStorage, AuthenticationService, config) {
	
	//obtener el rol al iniciar la aplicación
	//validar el rol al cambiar de estado
	
	
    // mantener usuario 
//    if ($localStorage.currentUser) {
//        $http.defaults.headers.common.Authorization = $localStorage.token;
//
//    }
    
    //validar autenticación al cambiar de estado
    $rootScope.$on('$locationChangeStart', function (event, next, current) {
        var publicPages = ['/login'];
        var restrictedPage = publicPages.indexOf($location.path()) === -1;
        //Si no está logueado e intenta acceder a una página restringida
        if (restrictedPage && !$localStorage.currentUser) {
            $location.path('/login');
            $rootScope.loggedIn = false;

        }
        //Está logueado, validar el token, si es válido ver si puede acceder
        else if($localStorage.currentUser){
        	
        AuthenticationService.validateToken($localStorage.currentUser.username).then( function (result) {
        		if(!result){
					$location.path('/login');
        			$rootScope.loggedIn = false;
				}
        		else{
        			/*Se valida que no ingrese a una página de otro rol.*/
            		if(!$location.path() == $rootScope.role){
            			$location.path('/login');
            			$rootScope.loggedIn = false;
            		}
        		}
        	});
        	
//        	$http.post(config.ctxPath+'/authentication/validate')
//			.then(function(result) {
//				if(!result){
//					$location.path('/login');
//					$rootScope.loggedIn = false;
//				}
//			});
        }
//        	else
//        		/*Se valida que no ingrese a una página de otro rol.*/
//        		if(!$location.path() == $rootScope.role){
//        			$location.path('/login');
//        			$rootScope.loggedIn = false;
//        		}
       

        
    });
})


})();
