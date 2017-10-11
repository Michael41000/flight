import flightListComponent from './flightList.component'
import itineraryService from './../itinerary/itinerary.service'
import userService from './../user/user.service'

export default
  angular
    .module('flight.flightList', ['ui.router'])
    .filter('firstUpperRestLower', [function () {
      return function (string) {
        if (string !== undefined)
          return string.charAt(0).toUpperCase() + string.slice(1).toLowerCase()
      }
    }])
    .component('flightListComponent', flightListComponent)
    .service('$user', userService)
    .name
