/* @ngInject */
class UserItinerariesController {

    constructor($user) {
        this.$user = $user
    }

    getItineraries() {
        this.$user.getItineraries(this.$user.username).then((done) => {
            console.log(done)
            this.itineraries = done
        })
    }
}

export default UserItinerariesController