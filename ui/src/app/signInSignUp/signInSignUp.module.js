import signInSignUpComponent from './signInSignUp.component.js'
import userService from './../user/user.service'
import routes from './signInSignUp.routes'

export default
  angular
    .module('flight.signInSignUp', ['ui.router'])
    .config(routes)
    .component('signInSignUpComponent', signInSignUpComponent)
    .service('$user', userService)
    .name