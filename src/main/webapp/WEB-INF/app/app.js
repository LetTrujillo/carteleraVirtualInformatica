(function(){
'use strict';

angular.module('Login', []);

//var app=angular.module("carteleraApp",[]); 

angular.module('carteleraApp', [
	// Extras
	'ui.router',
	'ngMessages',
	'ngStorage',

     // Modulos
     'Login'
])
.constant('config', {
    appName: 'Cartelera Virtual',
    basePath: '/CarteleraVirtualInformatica',
    ctxPath:  '/CarteleraVirtualInformatica/api',
    pageSize: 20
})
.config(function ($stateProvider, $urlRouterProvider) {
    // ruta por defecto
    $urlRouterProvider.otherwise("/");

    // rutas
    $stateProvider
        .state('login', {
            url: '/login',
            templateUrl: 'login/login.html',
            controller: 'LoginController',
        });
})
.run(function($rootScope, $http, $location, $localStorage) {
    // keep user logged in after page refresh
    if ($localStorage.currentUser) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.currentUser.token;
    }

    // redirect to login page if not logged in and trying to access a restricted page
    $rootScope.$on('$locationChangeStart', function (event, next, current) {
        var publicPages = ['/login'];
        var restrictedPage = publicPages.indexOf($location.path()) === -1;
        if (restrictedPage && !$localStorage.currentUser) {
            $location.path('/login');
        }
    });
})


})();
