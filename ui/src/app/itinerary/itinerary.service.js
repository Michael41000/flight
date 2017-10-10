/* @ngInject */
class ItineraryService {
    constructor($http, apiUrl) {
        this.$http = $http
        this.apiUrl = apiUrl
    }

    getFastestItinerary(origin, destination) {
        console.log('hello')
        return this.$http
            .get(`${this.apiUrl}/itinerary/fastest/origin/` + origin + '/destination/' + destination)
            .then(result => result.data)
    }

}


export default ItineraryService
