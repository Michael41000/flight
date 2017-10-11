import userItinerariesComponent from './userItineraries.component.js'
import itineraryService from './../itinerary/itinerary.service'
import routes from './userItineraries.routes'

export default
    angular
        .module('flight.userItineraries', [])
        .config(routes)
        .filter('firstUpperRestLower', [function () {
            return function (string) {
                if (string !== undefined)
                    return string.charAt(0).toUpperCase() + string.slice(1).toLowerCase()
            }
        }])
        .component('userItinerariesComponent', userItinerariesComponent)
        .service('$itinerary', itineraryService)
        .name