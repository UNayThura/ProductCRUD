import angular from 'angular';
import uiRouter from 'angular-ui-router';
import productListComponent from './productList.component';
import ProductService from './productList.service'

let productListModule = angular.module('productList', [
  uiRouter
])

  .config(($stateProvider, $urlRouterProvider) => {
    "ngInject";

    $urlRouterProvider.otherwise('/');

    $stateProvider
      .state('productList', {
        url: '/',
        component: 'productList'
      });
  })
  .service('ProductService', ProductService)
  .component('productList', productListComponent)

  .name;

export default productListModule;
