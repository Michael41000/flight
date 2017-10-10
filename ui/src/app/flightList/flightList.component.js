import templateUrl from './flightList.component.html'
import flightListController from './flightList.controller'

export default {
  templateUrl,
  controller: flightListController,
  controllerAs: '$flightListCtrl',
  bindings: {
      flights: '='
  }
}
