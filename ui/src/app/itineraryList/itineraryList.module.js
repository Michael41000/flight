import itineraryListComponent from './itineraryList.component.js'
import itineraryService from './../itinerary/itinerary.service'
import routes from './itineraryList.routes'

export default
  angular
    .module('flight.itineraryList', ['ui.router'])
    .config(routes)
    .filter('firstUpperRestLower', [function () {
      return function (string) {
        if (string !== undefined)
          return string.charAt(0).toUpperCase() + string.slice(1).toLowerCase()
      }
    }])
    .component('itineraryListComponent', itineraryListComponent)
    .service('$itinerary', itineraryService)
    .name