import angular from 'angular';
import uiRouter from 'angular-ui-router';
import logStatusComponent from './logstatus.component';

let logStatusModule = angular.module('logstatus', [
  uiRouter
])

  .component('logstatus', logStatusComponent)

  .name;

export default logStatusModule;
