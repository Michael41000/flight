/* @ngInject */
class FlightListController {
    constructor($flights, $scope) {
        this.$flights = $flights

        this.origin = ''
        this.destination = ''

        this.searchFlights()

        $scope.$on('flightsChanged', (event) => {
            console.log('flightsChanged')
            this.searchFlights()
        })
    }

    searchFlights() {
        const origin = this.origin.trim()
        const destination = this.destination.trim()
        if (origin === '' && destination === '') {
            this.$flights.getFlights().then((done) => {
                this.flights = done
            })
        }
        else if (origin !== '' && destination === '') {
            this.$flights.getFlightsByOriginStartsWith(origin).then((done) => {
                this.flights = done
            })
        }
        else if (origin === '' && destination !== '') {
            this.$flights.getFlightsByDestinationStartsWith(destination).then((done) => {
                this.flights = done
            })
        }
        else {
            this.$flights.getFlightsByOriginStartsWithAndDestinationStartsWith(origin, destination).then((done) => {
                this.flights = done
            })
        }
    }
}

export default FlightListController