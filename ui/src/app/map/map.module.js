import mapComponent from './map.component.js'
import mapService from './map.service'
import routes from './map.routes'

export default
  angular
    .module('flight.map', ['ngMap', 'ui.router'])
    .config(routes)
    .component('flightMap', mapComponent)
    .service('$map', mapService)
    .name
