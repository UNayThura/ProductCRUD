import angular from 'angular';
import uiRouter from 'angular-ui-router'
import categoryListComponent from './categoryList.component'
import CategoryListServiece from './categoryList.service'

var categoryListModule = angular.module('categoryList',[
    uiRouter
])

.config(($stateProvider) => {
    "ngInject";

    $stateProvider
      .state('categoryList', {
        url: '/categoryList',
        component: 'categoryList'
        });
})

.service('CategoryListServiece', CategoryListServiece)
.component('categoryList', categoryListComponent)
.name;

export default categoryListModule;