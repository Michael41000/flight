import flightListComponent from './flightList.component.js'

export default
  angular
    .module('flight.flightList', ['ui.router'])
    .filter('firstUpperRestLower', [function () {
      return function (string) {
        return string.charAt(0).toUpperCase() + string.slice(1).toLowerCase()
      }
    }])
    .component('flightListComponent', flightListComponent)
    .name
