/* @ngInject */
class ItineraryController {
    constructor($user, $state) {
        this.mapisShown = false
        this.$user = $user
        this.$state = $state

        
    }

    $onInit() {
        const totals = this.itinerary.itinerary.reduce((previous, current) => {
            previous[0] += current.flightTime
            if (current.layoverTime !== undefined)
            {
                previous[1] += current.layoverTime
            }
            return previous
        }, [0,0])

        this.totalFlightTime = totals[0]
        this.totalLayoverTime = totals[1]
    }

    showMap() {
        this.mapisShown = true;
    }

    hideMap() {
        this.mapisShown = false;
    }

    bookItinerary() {
        console.log('hello')
        const itineraryCredential = {}
        itineraryCredential.itinerary = this.itinerary
        itineraryCredential.credential = {}
        itineraryCredential.credential.username = this.$user.username
        itineraryCredential.credential.password = this.$user.password
        this.$user.saveItinerary(itineraryCredential).then((done) => {
            console.log(done)
        })
    }

    unbookItinerary() {
        console.log(this.itinerary)
        const credential = {}
        credential.username = this.$user.username
        credential.password = this.$user.password
        this.$user.deleteItinerary(this.itinerary.id, credential).then((done) => {
            this.$state.reload()
        })
    }
}

export default ItineraryController