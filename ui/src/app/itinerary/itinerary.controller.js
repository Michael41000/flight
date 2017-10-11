/* @ngInject */
class ItineraryController {
    constructor($user) {
        this.mapisShown = false
        this.$user = $user
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
}

export default ItineraryController