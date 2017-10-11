/* @ngInject */
class ItineraryService {
    constructor($http, apiUrl) {
        this.$http = $http
        this.apiUrl = apiUrl
    }

    getFastestItinerary(origin, destination) {
        return this.$http
            .get(`${this.apiUrl}/itinerary/fastest/origin/` + origin + '/destination/' + destination)
            .then(result => result.data)
    }

    getItineraries(origin, destination) {
        return this.$http
            .get(`${this.apiUrl}/itinerary/origin/` + origin + '/destination/' + destination)
            .then(result => result.data)
    }


}


export default ItineraryService
