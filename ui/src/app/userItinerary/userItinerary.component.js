import templateUrl from './userItinerary.component.html'
import userItineraryController from './userItinerary.controller'

export default {
  templateUrl,
  controller: userItineraryController,
  controllerAs: '$userItineraryCtrl',
  bindings: {
      itinerary: '='
  }
}
