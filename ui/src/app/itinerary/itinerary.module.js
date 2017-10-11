import itineraryComponent from './itinerary.component.js'
import itineraryService from './itinerary.service'
import routes from './itinerary.routes'

export default
  angular
    .module('flight.itinerary', ['ui.router'])
    .config(routes)
    .filter('firstUpperRestLower', [function () {
      return function (string) {
        if (string !== undefined)
          return string.charAt(0).toUpperCase() + string.slice(1).toLowerCase()
      }
    }])
    .component('itineraryComponent', itineraryComponent)
    .service('$itinerary', itineraryService)
    .name