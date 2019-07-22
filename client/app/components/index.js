import angular from 'angular';
import Navbar from './navbar/navbar';
import Hero from './hero/hero';
import User from './user/user';
import LogStatus from './logstatus/logstatus';

let commonModule = angular.module('app.components', [
  Navbar,
  Hero,
  User,
  LogStatus
])

  .name;

export default commonModule;
