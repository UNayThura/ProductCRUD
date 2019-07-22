import angular from 'angular';
import productList from './productList/productList';
import productForm from './productForm/productForm';
import categoryList from './categoryList/categoryList';

let componentModule = angular.module('app.views', [
  productList,
  productForm,
  categoryList
])

  .name;

export default componentModule;
