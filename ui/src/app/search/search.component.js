import templateUrl from './search.component.html'

/* @ngInject */
class SearchController {

    constructor($flights) {
        this.$flights = $flights

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
            this.$flights.getFlightsByOrigin(origin).then((done) => {
                console.log(done)
                this.flights = done
            })
        }
        else if (origin === '' && destination !== '') {
            this.$flights.getFlightsByDestination(destination).then((done) => {
                console.log(done)
                this.flights = done
            })
        }
        else {
            this.$flights.getFlightsByOriginAndDestination(origin, destination).then((done) => {
                console.log(done)
                this.flights = done
            })
        }
    }

    changedOrigin() {
        this.searchFlights()
    }

    changedDestination() {
        this.searchFlights()
    }

}

export default {
    templateUrl,
    controller: SearchController,
    controllerAs: '$searchCtrl'
}