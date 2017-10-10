/* @ngInject */
class ItineraryService {
    constructor($flights) {
        this.$flights = $flights

        $flights.getFlights().then((done) => {
            this.flights = done



        })
    }

    getItinerary() {
        this.flights = this.flights.reduce((previous, current) => {
            console.log(current)
            if (current.origin.toLowerCase() === this.origin.toLowerCase()) {
                previous.push(current)
            }
            console.log(previous)
            return previous
        }, [])
    }

}


export default ItineraryService
