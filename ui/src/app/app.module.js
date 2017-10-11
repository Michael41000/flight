import flightMap from './map/map.module'
import flightListComponent from './flightList/flightList.module'
import signInSignUpComponent from './signInSignUp/signInSignUp.module'
import searchComponent from './search/search.module'
import itineraryComponent from './itinerary/itinerary.module'
import navbarComponent from './navbar/navbar.module'
import flightComponent from './flight/flight.module'
import userService from './user/user.service'



import apiUrl from './api.url'
import appComponent from './app.component.js'
import routes from './app.routes'

export default
  angular
    .module('flight', [
      'ngAria',
      'ngAnimate',
      'ngMaterial',
      'ngMessages',
      'ui.router',
      'ngCookies',

      flightMap,
      flightListComponent,
      signInSignUpComponent,
      searchComponent,
      itineraryComponent,
      navbarComponent, 
      flightComponent,
    ])
    .config(routes)
    .constant('apiUrl', apiUrl)
    .component('flightApp', appComponent)
    .service('$user', userService)
    .name
