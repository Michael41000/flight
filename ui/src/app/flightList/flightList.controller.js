/* @ngInject */
class FlightListController {
    constructor($flights) {
        this.$flights = $flights

        $flights.getFlights().then((done) => {
            this.flights = done
        })
    }
}

export default FlightListController