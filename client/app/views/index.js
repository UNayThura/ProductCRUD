import angular from 'angular';
import Home from './home/home';
import About from './about/about';

let componentModule = angular.module('app.views', [
  Home,
  About,

])

  .name;

export default componentModule;
