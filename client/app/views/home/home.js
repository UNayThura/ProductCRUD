import angular from 'angular';
import uiRouter from 'angular-ui-router';
import homeComponent from './home.component';
import HomeService from './home.service'
let homeModule = angular.module('home', [
  uiRouter
])

  .config(($stateProvider, $urlRouterProvider) => {
    "ngInject";

    $urlRouterProvider.otherwise('/');

    $stateProvider
      .state('home', {
        url: '/',
        component: 'home'
      });
  })
  .service('HomeService', HomeService)
  .component('home', homeComponent)

  .name;

export default homeModule;
