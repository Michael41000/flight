/* @ngInject */
class UserItineraryController {
    constructor($user, $state) {
        this.mapisShown = false
        this.$user = $user
        this.$state = $state
    }

    $onInit() {
        console.log(this.itinerary.itinerary)
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

export default UserItineraryController