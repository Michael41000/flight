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
            this.itineraries = done
            this.noRoutes = this.itineraries.length === 0 ? true : false
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