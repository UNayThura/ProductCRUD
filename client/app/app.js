import angular from 'angular';
import 'jquery';
import 'popper.js'
import 'bootstrap';
import Bootstrap from 'bootstrap/dist/css/bootstrap.css'; // eslint-disable-line no-unused-vars

import uiRouter from 'angular-ui-router';
import Components from './components';
import Views from './views';
import AppComponent from './app.component';
import 'normalize.css';


angular.module('app', [
  uiRouter,
  Components,
  Views,
])
  .config(($locationProvider) => {
    "ngInject";
    // @see: https://github.com/angular-ui/ui-router/wiki/Frequently-Asked-Questions
    // #how-to-configure-your-server-to-work-with-html5mode
    $locationProvider.html5Mode(true).hashPrefix('!');
  })

  .component('app', AppComponent);
