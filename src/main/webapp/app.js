(function(){
'use strict';

//angular.module('Agent', []);


angular.module('carteleraApp', [
  // Extras
//  'ui.router',
//  'ui.bootstrap',
//  'MessageCenterModule',
//  'angular-loading-bar',
//  'pascalprecht.translate',
//  'ngTagsInput',
//  'ngAnimate',
//  'angular.filter',

  // Modulos
//  'Agent'
  
  
]).constant('config', {
    appName: 'Cartelera Virtual Informática',
    basePath: '/CarteleraVirtualInformatica',
    ctxPath:  '/CarteleraVirtualInformatica/api',
    pageSize: 20
})

function PruebaController($scope) {
  $scope.mensaje="Hola Mundo";
}


})();
