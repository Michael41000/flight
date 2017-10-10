import itineraryComponent from './itinerary.component.js'
import itineraryService from './itinerary.service'
import routes from './itinerary.routes'

export default
  angular
    .module('flight.itinerary', ['ui.router'])
    .config(routes)
    .component('itineraryComponent', itineraryComponent)
    .service('$itinerary', itineraryService)
    .name