/* @ngInject */
class FlightsService {
    constructor($http, apiUrl) {
        this.$http = $http
        this.apiUrl = apiUrl
    }

    getFlights() {
        return this.$http
            .get(`${this.apiUrl}/flights`)
            .then(result => result.data)
    }

    getFlightsByOrigin(origin) {
        return this.$http
        .get(`${this.apiUrl}/flights/origin/` + origin)
        .then(result => result.data)
    }

    getFlightsByDestination(destination) {
        return this.$http
        .get(`${this.apiUrl}/flights/destination/` + destination)
        .then(result => result.data)
    }

    getFlightsByOriginAndDestination(origin, destination) {
        return this.$http
            .get(`${this.apiUrl}/flights/origin/` + origin + '/destination/' + destination)
            .then(result => result.data)
    }

    getFlightsByOriginStartsWith(origin) {
        return this.$http
        .get(`${this.apiUrl}/flights/startsWith/origin/` + origin)
        .then(result => result.data)
    }

    getFlightsByDestinationStartsWith(destination) {
        return this.$http
        .get(`${this.apiUrl}/flights/startsWith/destination/` + destination)
        .then(result => result.data)
    }

    getFlightsByOriginStartsWithAndDestinationStartsWith(origin, destination) {
        return this.$http
            .get(`${this.apiUrl}/flights/startsWith/origin/` + origin + '/startsWith/destination/' + destination)
            .then(result => result.data)
    }

    

}

export default FlightsService
