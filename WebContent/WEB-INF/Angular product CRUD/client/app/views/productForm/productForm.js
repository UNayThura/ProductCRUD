import angular from 'angular';
import uiRouter from 'angular-ui-router';
import ProductFormComponent from './productForm.component';
import ProductFormService from './productForm.service';


var productFormModule = angular.module('productForm', [
  uiRouter,
])

  .config(($stateProvider) => {
    "ngInject";
    $stateProvider
      .state('productForm', {
        url: '/productForm',
        component: 'productForm'
      });
  })
  .service('ProductFormService', ProductFormService)
  .component('productForm', ProductFormComponent)

  .name;


export default productFormModule;
