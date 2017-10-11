/* @ngInject */
class ItineraryListController {

    constructor($itinerary) {
        this.$itinerary = $itinerary
    }

    getFastestItinerary() {
        this.$itinerary.getFastestItinerary(this.origin, this.destination).then((done) => {
            this.flights = done
            console.log(this.flights)
        })
    }

    getItineraries() {
        this.$itinerary.getItineraries(this.origin, this.destination).then((done) => {
            console.log(done)
            this.itineraries = done
            this.noRoutes = this.itineraries.length === 0 ? true : false
            let i
            for (i = 0; i < this.itineraries.length; i++) {
                console.log(this.itineraries[i])
                let j;
                for (j = 1; j < this.itineraries[i].itinerary.length; j++) {
                    console.log(this.itineraries[i].itinerary[j])
                    this.itineraries[i].itinerary[j].layoverTime = this.itineraries[i].itinerary[j].offset - (this.itineraries[i].itinerary[j - 1].offset + this.itineraries[i].itinerary[j - 1].flightTime)
                }
            }
            console.log(this.itineraries)
        })
    }
}

export default ItineraryListController