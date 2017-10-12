import flightMap from './map/map.module'
import flightListComponent from './flightList/flightList.module'
import signInSignUpComponent from './signInSignUp/signInSignUp.module'
import itineraryComponent from './itinerary/itinerary.module'
import navbarComponent from './navbar/navbar.module'
import userService from './user/user.service'
import itineraryListComponent from './itineraryList/itineraryList.module'
import userItinerariesComponent from './userItineraries/userItineraries.module'
import userItineraryComponent from './userItinerary/userItinerary.module'



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
      itineraryComponent,
      itineraryListComponent,
      navbarComponent, 
      userItinerariesComponent,
      userItineraryComponent
    ])
    .config(routes)
    .constant('apiUrl', apiUrl)
    .component('flightApp', appComponent)
    //.constant('Stomp', Stomp)
    .service('$user', userService)
    .name
