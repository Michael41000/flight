import templateUrl from './itinerary.component.html'

/* @ngInject */
class ItineraryController {

  constructor ($itinerary) {
    this.$itinerary = $itinerary

    
  }

  

}

export default {
  templateUrl,
  controller: ItineraryController,
  controllerAs: '$itineraryCtrl'
}
