/* @ngInject */
class UserItinerariesController {

    constructor($user) {
        this.$user = $user

        this.origin = ''

        this.destination = ''

        this.totalFlightTime = 0

        this.totalLayoverTime = 0

        this.searchUserItineraries()
    }

    searchUserItineraries() {
        const origin = this.origin.trim()
        const destination = this.destination.trim()
        if (origin === '' && destination === '') {
            this.$user.getItineraries(this.$user.username).then((done) => {
                this.itineraries = done
                this.calculateTimes()
            })
        }
        else if (origin !== '' && destination === '') {
            this.$user.getItinerariesByOriginStartsWith(this.$user.username, origin).then((done) => {
                this.itineraries = done
                this.calculateTimes()
            })
        }
        else if (origin === '' && destination !== '') {
            this.$user.getItinerariesByDestinationStartsWith(this.$user.username, destination).then((done) => {
                this.itineraries = done
                this.calculateTimes()
            })
        }
        else {
            this.$user.getItinerariesByOriginStartsWithAndDestinationStartsWith(this.$user.username, origin, destination).then((done) => {
                this.itineraries = done
                this.calculateTimes()
            })
        }
    }

    calculateTimes() {
        this.totalFlightTime = 0
        this.totalLayoverTime = 0
        for (let i = 0; i < this.itineraries.length; i++) {
            for (let j = 1; j < this.itineraries[i].itinerary.length; j++) {
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
    }
}

export default UserItinerariesController