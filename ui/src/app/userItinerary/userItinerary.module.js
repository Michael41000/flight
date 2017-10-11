import userItineraryComponent from './userItinerary.component'
import userService from './../user/user.service'

export default
  angular
    .module('flight.userItinerary', ['ui.router'])
    .filter('firstUpperRestLower', [function () {
      return function (string) {
        if (string !== undefined)
          return string.charAt(0).toUpperCase() + string.slice(1).toLowerCase()
      }
    }])
    .component('userItineraryComponent', userItineraryComponent)
    .service('$user', userService)
    .name