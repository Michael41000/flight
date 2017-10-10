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

    

}

export default FlightsService
