/* @ngInject */
class FlightListController {
    constructor($flights) {
        this.$flights = $flights

        $flights.getFlights().then((done) => {
            this.flights = done
        })

        this.origin = ''
        this.destination = ''
    }

    searchFlights() {
        const origin = this.origin.trim()
        const destination = this.destination.trim()
        if (origin === '' && destination === '') {
            this.$flights.getFlights().then((done) => {
                console.log(done)
                this.flights = done
            })
        }
        else if (origin !== '' && destination === '') {
            this.$flights.getFlightsByOriginStartsWith(origin).then((done) => {
                console.log(done)
                this.flights = done
            })
        }
        else if (origin === '' && destination !== '') {
            this.$flights.getFlightsByDestinationStartsWith(destination).then((done) => {
                console.log(done)
                this.flights = done
            })
        }
        else {
            this.$flights.getFlightsByOriginStartsWithAndDestinationStartsWith(origin, destination).then((done) => {
                console.log(done)
                this.flights = done
            })
        }
    }
}

export default FlightListController