/* @ngInject */
class ItineraryListController {

    constructor($itinerary, $scope) {
        this.$itinerary = $itinerary
        this.noRoutes = false
        this.searchPressed = false

        $scope.$on('flightsChanged', (event) => {
            console.log('flightsChanged')
            if (this.searchPressed)
            {
                this.getItineraries()
            }
        })
    }



    getItineraries() {
        this.searchPressed = true
        this.$itinerary.getItineraries(this.origin, this.destination).then((done) => {
            console.log(done)
            this.itineraries = done
            this.noRoutes = this.itineraries.length === 0 ? true : false
            let i
            for (i = 0; i < this.itineraries.length; i++) {
                let j;
                for (j = 1; j < this.itineraries[i].itinerary.length; j++) {
                    this.itineraries[i].itinerary[j].layoverTime = this.itineraries[i].itinerary[j].offset - (this.itineraries[i].itinerary[j - 1].offset + this.itineraries[i].itinerary[j - 1].flightTime)
                }
            }
            console.log(this.itineraries)
        })
    }

    changedSearch() {
        this.noRoutes = false
        this.searchPressed = false
        this.itineraries = undefined
    }
}

export default ItineraryListController