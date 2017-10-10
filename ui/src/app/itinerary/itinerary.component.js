import templateUrl from './itinerary.component.html'

/* @ngInject */
class ItineraryController {

  constructor ($itinerary, $state) {
    this.$itinerary = $itinerary
  }

  getFastestItinerary() {
    this.$itinerary.getFastestItinerary(this.origin, this.destination).then((done) => {
        this.flights = done
        console.log(this.flights)
    })
  }

  getItineraries() {
    this.$itinerary.getItineraries(this.origin, this.destination).then((done) =>{
      console.log(done)
    })
  }

  

}

export default {
  templateUrl,
  controller: ItineraryController,
  controllerAs: '$itineraryCtrl'
}
