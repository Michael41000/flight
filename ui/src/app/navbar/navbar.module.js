import navbarComponent from './navbar.component.js'
import navbarService from './navbar.service'
import userService from './../user/user.service'

export default
  angular
    .module('flight.navbar', ['ui.router'])
    .component('navbarComponent', navbarComponent)
    .service('$navbar', navbarService)
    .service('$user', userService)
    .name
