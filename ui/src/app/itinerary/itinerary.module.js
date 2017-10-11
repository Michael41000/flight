import itineraryComponent from './itinerary.component'
import userService from './../user/user.service'

export default
  angular
    .module('flight.itinerary', ['ui.router'])
    .filter('firstUpperRestLower', [function () {
      return function (string) {
        if (string !== undefined)
          return string.charAt(0).toUpperCase() + string.slice(1).toLowerCase()
      }
    }])
    .component('itineraryComponent', itineraryComponent)
    .service('$user', userService)
    .name