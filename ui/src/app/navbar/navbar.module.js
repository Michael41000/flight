import navbarComponent from './navbar.component.js'
import userService from './../user/user.service'

export default
  angular
    .module('flight.navbar', ['ui.router'])
    .component('navbarComponent', navbarComponent)
    .service('$user', userService)
    .name
