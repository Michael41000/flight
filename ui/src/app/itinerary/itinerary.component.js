import templateUrl from './itinerary.component.html'
import itineraryController from './itinerary.controller'

export default {
  templateUrl,
  controller: itineraryController,
  controllerAs: '$itineraryCtrl',
  bindings: {
      itinerary: '='
  }
}
