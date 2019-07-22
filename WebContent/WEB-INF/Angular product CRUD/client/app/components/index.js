import angular from 'angular';
import Navbar from './navbar/navbar';

let commonModule = angular.module('app.components', [
  Navbar,

])

  .name;

export default commonModule;
