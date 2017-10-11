/* @ngInject */
class UserItinerariesController {

    constructor($user) {
        this.$user = $user

        this.getItineraries()

        this.totalFlightTime = 0

        this.totalLayoverTime = 0
    }

    getItineraries() {
        this.$user.getItineraries(this.$user.username).then((done) => {
            console.log(done)
            this.itineraries = done
            let i
            for (i = 0; i < this.itineraries.length; i++) {
                let j;
                for (j = 1; j < this.itineraries[i].itinerary.length; j++) {
                    this.itineraries[i].itinerary[j].layoverTime = this.itineraries[i].itinerary[j].offset - (this.itineraries[i].itinerary[j - 1].offset + this.itineraries[i].itinerary[j - 1].flightTime)
                }
                const totals = this.itineraries[i].itinerary.reduce((previous, current) => {
                    previous[0] += current.flightTime
                    if (current.layoverTime !== undefined) {
                        previous[1] += current.layoverTime
                    }
                    return previous
                }, [0, 0])
                this.totalFlightTime += totals[0]
                this.totalLayoverTime += totals[1]
            }
        })
    }
}

export default UserItinerariesController