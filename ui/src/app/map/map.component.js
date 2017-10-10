import templateUrl from './map.component.html'
import mapController from './map.controller'



export default {
  templateUrl,
  controller: mapController,
  controllerAs: '$mapCtrl',
  bindings: {
    flights: '='
  }
}
