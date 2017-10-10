import flightMap from './map/map.module'
import flightListComponent from './flightList/flightList.module'
import signInSignUpComponent from './signInSignUp/signInSignUp.module'
import searchComponent from './search/search.module'
import itineraryComponent from './itinerary/itinerary.module'

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

      flightMap,
      flightListComponent,
      signInSignUpComponent,
      searchComponent,
      itineraryComponent
    ])
    .config(routes)
    .constant('apiUrl', apiUrl)
    .component('flightApp', appComponent)
    .name
