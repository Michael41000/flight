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
}

export default UserItineraryController