import flightComponent from './flight.component.js'

export default
  angular
    .module('flight.flight', [])
    .filter('firstUpperRestLower', [function () {
      return function (string) {
        if (string !== undefined)
          return string.charAt(0).toUpperCase() + string.slice(1).toLowerCase()
      }
    }])
    .component('flightComponent', flightComponent)
    .name
