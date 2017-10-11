import flightListComponent from './flightList.component'
import flightsService from './../flights/flights.service'
import routes from './flightList.routes'

export default
  angular
    .module('flight.flightList', ['ui.router'])
    .config(routes)
    .filter('firstUpperRestLower', [function () {
      return function (string) {
        if (string !== undefined)
          return string.charAt(0).toUpperCase() + string.slice(1).toLowerCase()
      }
    }])
    .component('flightListComponent', flightListComponent)
    .service('$flights', flightsService)
    .name
