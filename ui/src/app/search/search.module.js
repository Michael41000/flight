import searchComponent from './search.component'
import flightsService from './../flights/flights.service'
import routes from './search.routes'

export default
  angular
    .module('flight.search', ['ui.router'])
    .config(routes)
    .component('searchComponent', searchComponent)
    .service('$flights', flightsService)
    .name